    
package RA;

import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Ruth_Cordon
 */

public class Asunto {
    
    private static String url = "jdbc:sqlserver://192.168.1.165:1433";
    private static String user = "SQL_PwC";
    private static String pass = "Ru8865No";
    
    static{
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        }catch(ClassNotFoundException e){
            System.out.println("No puedo cargar el driver de la BD");
        }
    }
    
//--------------------------------------------------------------------------------------------//
    
    // Inserta un nuevo asunto en la bd 
    // Crea el directorio en el servidor. 
    // Inserta una linea en interpolacion (si tipo=0,1)
    // Inserta en la tabla contactos (si jesus no es contatco de cliente, se inserta tb como contacto)
    // Devuelve true si el codigo del asunto ya existe
    public boolean NuevoAsunto(String codigo, String nombre,int PE, int norma, int periodicidad, int cliente, int responsable, int aero, String npos, ArrayList<String> contactosasunto, String Ncorto,String idioma,int tipo) throws SQLException{
      
      Connection con = null;
      
      boolean b=false;
      File folder=null;
      try{            
          
          Aerogenerador AG=new Aerogenerador();
          Cliente C=new Cliente();
          
          con = DriverManager.getConnection(url, user, pass);
          con.setAutoCommit(false);
          
          // Se comprueba si existe y el último insertado
          PreparedStatement ps = con.prepareStatement("Select Id_asunto,Codigo from Asunto order by Id_asunto");          
          ResultSet rs=ps.executeQuery(); 
          int Id=0;             
          while (rs.next()){ 
               Id=rs.getInt(1);
               if (rs.getString(2).toUpperCase().equals(codigo.toUpperCase())){
                   b=true;                
               }
          }
          rs.close();
          ps.close();
          if (b==false){
               Id++;
               String sql_insercion="Insert into Asunto (Id_asunto,Codigo,Nombre,PE,Norma,Periodicidad,Clave,Cliente,Responsable,Aerogenerador,Abierto,Posicion,Ncorto,Idioma,Tipo)" +
                                    " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
               PreparedStatement ps_insercion=con.prepareStatement(sql_insercion);
               ps_insercion.setInt(1, Id);
               ps_insercion.setString(2, codigo);
               ps_insercion.setString(3, nombre);
               ps_insercion.setInt(4, PE);
               ps_insercion.setInt(5, norma);
               if(periodicidad!=0){
                    ps_insercion.setInt(6, periodicidad);
               } else{
                    ps_insercion.setNull(6, java.sql.Types.INTEGER);
               }
               ps_insercion.setBoolean(7, false);
               ps_insercion.setInt(8, cliente);
               ps_insercion.setInt(9, responsable);
               ps_insercion.setInt(10, aero);
               ps_insercion.setBoolean(11, true);
               ps_insercion.setString(12, npos);
               ps_insercion.setString(13, Ncorto);
               ps_insercion.setString(14, idioma);
               ps_insercion.setInt(15, tipo);

               System.out.println(sql_insercion);
               ps_insercion.execute(); 
               ps_insercion.close();
               
               // Se inserta la linea de interpolacion
               double Vcut=AG.getVcut(aero);
               double Pn=AG.getPn(aero);
               if ((tipo==0) || (tipo==1)){
                   String sqli_interpolacion="Insert into Interpolacion (Asunto,Pnominal,Vcorte) values (?,?,?)";
                   PreparedStatement ps_interpolacion=con.prepareCall(sqli_interpolacion);
                   ps_interpolacion.setInt(1, Id);
                   ps_interpolacion.setDouble(2, Pn);
                   ps_interpolacion.setDouble(3, Vcut);
                   ps_interpolacion.execute();
                   ps_interpolacion.close();
               }
               
               // Se inseratn los contactos
               boolean jesus=false;
               int n=contactosasunto.size();
               for (int i=0;i<n;i++){
                    int Id_contacto=C.getIdContacto(contactosasunto.get(i));
                    if (Id_contacto==29){jesus=true;}
                    String sql_contatcos="Insert into AsuntoContacto (Contacto,Asunto,Notificacion,Adjunto) values (?,?,1,0)";
                    PreparedStatement ps_constactos=con.prepareStatement(sql_contatcos);
                    ps_constactos.setInt(1, Id_contacto);
                    ps_constactos.setInt(2, Id);
                    ps_constactos.execute();                   
                    ps_constactos.close();                
               }
               // Se inserta a jesus como contacto si no es el cliente
               if (!jesus){
                    String sql_jesus="Insert into AsuntoContacto values (29,?,1,1)";
                    PreparedStatement ps_jesus=con.prepareStatement(sql_jesus);
                    ps_jesus.setInt(1, Id);
                    ps_jesus.execute();
                    ps_jesus.close();             
               }
               
               // Se crea el directorio asociado
               folder=new File("\\\\Bsolar\\DatosSolar\\Curva\\Asuntos\\"+codigo+"."+nombre);
               folder.mkdir();
               folder=new File("\\\\Bsolar\\DatosSolar\\Curva\\Asuntos\\"+codigo+"."+nombre+"\\Insercion");
               folder.mkdir();
               folder=new File("\\\\Bsolar\\DatosSolar\\Curva\\Asuntos\\"+codigo+"."+nombre+"\\Edicion");
               folder.mkdir();
               folder=new File("\\\\Bsolar\\DatosSolar\\Curva\\Asuntos\\"+codigo+"."+nombre+"\\Revision");
               folder.mkdir();
               folder=new File("\\\\Bsolar\\DatosSolar\\Curva\\Asuntos\\"+codigo+"."+nombre+"\\Informes");
               folder.mkdir();
               folder=new File("\\\\Bsolar\\DatosSolar\\Curva\\Asuntos\\"+codigo+"."+nombre+"\\Correccion");
               folder.mkdir();

           }
           con.commit();
           con.close();
           
       }catch (SQLException e){
           if (folder!=null){folder.delete();}           
           e.printStackTrace();
           throw e;
       }finally{
           if (con!=null){con.close();}         
       }
       return b;
    }

//--------------------------------------------------------------------------------------------//
    
    // Devuelve el id de un asunto dado su nombre completo (codigo.nombre). Si no existe devuelve 0
    public static int getId(String nombrecompleto) throws SQLException {
       Connection con = null; 
       int id=0;
       try{            
            con = DriverManager.getConnection(url, user, pass);  
            
            int indice=nombrecompleto.indexOf(".");
            String codigo=nombrecompleto.substring(0,indice);
            
            String sql="Select Id_asunto from Asunto where Codigo=?";
            PreparedStatement ps = con.prepareStatement(sql);     
            ps.setString(1, codigo);
            ResultSet rs=ps.executeQuery();                       
            if (rs.next()){
                id=rs.getInt(1);
            }
            rs.close();
            ps.close();
            con.close();
        }catch(SQLException e){            
            e.printStackTrace();  
            throw e;
        }finally{
            if (con!=null){con.close();}    
        }
        return id;
    }
    
//--------------------------------------------------------------------------------------------//

    // Devuelve el nombre completo de un asunto (Codigo.Nombre). Devuelve null si el asunto no existe
    public  String getNombreCompleto(int asunto) throws SQLException{
        Connection con = null;
        String nombre=null;
        try{            
            con = DriverManager.getConnection(url, user, pass);           
            
            PreparedStatement ps = con.prepareStatement("select Codigo,Nombre from Asunto where Id_Asunto=?");
            ps.setInt(1, asunto);
            ResultSet rs=ps.executeQuery();
            if(rs.next()) {
                nombre=rs.getString(1)+"."+rs.getString(2);   
            }
            rs.close();
            ps.close();
            con.close();            
        }catch(SQLException e){            
            e.printStackTrace();
            throw e;
        }finally{
            if (con!=null){con.close();}            
        }
        return nombre;
    }
    
//--------------------------------------------------------------------------------------------//

    // Devuelve el id de la norma de aplicación de un asunto. 0 si no existe.
    public int getNorma(int asunto)throws SQLException{
        Connection con = null;
        int norma=0;
        try{            
            con=DriverManager.getConnection(url,user,pass); 
            
            PreparedStatement ps = con.prepareStatement("Select Norma from Asunto where Id_asunto=?");   
            ps.setInt(1, asunto);
            ResultSet rs=ps.executeQuery();
            if (rs.next()) {
                norma=rs.getInt(1);
            }
            rs.close();
            ps.close();
            con.close();
         }catch (SQLException e){
            e.printStackTrace();
            throw e;
        }finally{
            if (con!=null) con.close();
        }
        return (norma);
    }
    
//--------------------------------------------------------------------------------------------//
    
    //Devuelve la posición del aero
    public String getPosicion(int asunto)throws SQLException{
        Connection con = null;
        String pos="";
        try{            
            con = DriverManager.getConnection(url, user, pass);
           
            PreparedStatement ps = con.prepareStatement("Select Posicion from Asunto where Id_asunto=?");   
            ps.setInt(1, asunto);
            ResultSet rs=ps.executeQuery();
            if (rs.next()) {
                pos=rs.getString(1);     
            }
            rs.close();
            ps.close();
            con.close();
       }catch (SQLException e){
            e.printStackTrace();
            throw e;
        }finally{
            if (con!=null) con.close();     
        }
        return (pos);
    }

    
//--------------------------------------------------------------------------------------------//
 
   // Devuelve el modelo de aero
    public int getAero(int asunto)throws SQLException{
        Connection con = null;
        int aero=0;
        try{            
            con = DriverManager.getConnection(url, user, pass);
            
            PreparedStatement ps = con.prepareStatement("Select Aerogenerador from Asunto where Id_asunto=?");   
            ps.setInt(1, asunto);
            ResultSet rs=ps.executeQuery();
            if (rs.next()) {
                aero=rs.getInt(1);
            }
            rs.close();
            ps.close();
            con.close();
        }catch (SQLException e){
            e.printStackTrace();
            throw e;
        }finally{
            if (con!=null) con.close();     
        }
        return (aero);
    }
    
 //--------------------------------------------------------------------------------------------//
   
    // Devuelve los sites de RA asociados a un asunto
    public static ArrayList<Integer> getSitesRA(int asunto) throws SQLException {
       Connection con = null;             
       ArrayList <Integer> sites=new ArrayList <Integer>();
       try{            
            con=DriverManager.getConnection(url,user,pass);            
           
            PreparedStatement ps = con.prepareStatement("Select distinct Site from ConfiguracionRA where Asunto=?");   
            ps.setInt(1, asunto);
            ResultSet rs=ps.executeQuery();                       
            while (rs.next()){
                sites.add(rs.getInt(1));
            }
            rs.close();
            ps.close();
            con.close();
       }catch(SQLException e){            
            e.printStackTrace();
            throw e;             
       }finally{
            if (con!=null){con.close();}     
       }
       return sites; 
    }
 
//--------------------------------------------------------------------------------------------//
    
    // Genera el nuevo código a introducir
    public String getNuevoCodigo()throws SQLException{
       Connection con = null;  
       String nuevo_codigo="";
       
       try{      
           
          con = DriverManager.getConnection(url, user, pass);
          
          Calendar calendario = new GregorianCalendar();
          int anio =calendario.get(Calendar.YEAR);
          int maxq=0;
          
          PreparedStatement ps = con.prepareStatement("Select Codigo from Asunto");        
          ResultSet rs=ps.executeQuery();
          while (rs.next()) {
              String codigo=rs.getString(1);
              int pos=codigo.indexOf("-");
              String anno=codigo.substring(0, pos);
              int año=Integer.parseInt(anno);
              String numero=codigo.substring(pos+1);
              int q=Integer.parseInt(numero);
              if (anio==año){
                  if (q>maxq){
                     maxq=q;
                  }
               }
          }
          rs.close();
          ps.close();
          con.close();
          maxq++;
          String p=""+maxq;
          if (maxq/10>=1){
              if (maxq/100<1){
                 p="0"+p;
              }
          }else {
              p="00"+p;
          }
          nuevo_codigo=anio+"-"+p;
      }catch (SQLException e){
           e.printStackTrace();
           throw e;
      }finally{
           if (con!=null){con.close();}     
      }      
      return (nuevo_codigo);
    }

//--------------------------------------------------------------------------------------------//   
    
    // Modifica los campos de un asunto. True si la modificacion se ha realizado.
    public boolean ModificarAsunto(int asunto,String codigo, String nombre,int parque,int norma,int periodicidad,int cliente,int responsable,int aero, String npos, ArrayList<String> contactos, boolean estado,String nc,String idioma,  String FiniPC, String FiniSC,String FiniME1,String FiniME10) throws SQLException, Exception {
        Connection con = null;
        boolean b=false;
        try{
            Cliente C=new Cliente();
            
            con = DriverManager.getConnection(url, user, pass);
            con.setAutoCommit(false);
            
            String codigo_antiguo=this.getCodigo(asunto);
            String nombre_antiguo=this.getNombre(asunto);
            
            File folder=new File("\\\\Bsolar\\DatosSolar\\Curva\\Asuntos\\"+codigo_antiguo+"."+nombre_antiguo);
            FileWriter fw= new FileWriter("\\\\Bsolar\\DatosSolar\\Curva\\Asuntos\\"+codigo_antiguo+"."+nombre_antiguo+"\\Edicion\\update-"+asunto+".log",true);
            fw.write("--------------------------------------------------------------------------------\n");
            fw.write("\n");
            
            Date hoy= new Date();
            SimpleDateFormat formato=new SimpleDateFormat("dd-MM-yyyy hh:mm");
            String formatohoy=formato.format(hoy);
            
            fw.write("Proceso de Modificación (Asunto) a: "+formatohoy+"\n");
            fw.write("Estado previo:\n");
            
            ArrayList <String> datos_asunto=this.getAsunto(asunto);
            PreparedStatement ps=con.prepareStatement("Select * from Asunto");
            ResultSet rs=ps.executeQuery();
            ResultSetMetaData metadatos=rs.getMetaData();
            int columnas=metadatos.getColumnCount();
            ArrayList <String> nombres=new ArrayList<String>();
            for (int c=0;c<columnas;c++){
                nombres.add(c,metadatos.getColumnName(c+1));
            }
            for (int d=0;d<datos_asunto.size();d++){
                fw.write(nombres.get(d)+": ");
                fw.write(datos_asunto.get(d)+",\n");
            }
            ps.close();
            rs.close();
           
            String sql_update="Update Asunto set Codigo=?,Nombre=?,PE=?,Norma=?,Periodicidad=?,Cliente=?,Responsable=?,Aerogenerador=?,Abierto=?,Posicion=?,Ncorto=?,Idioma=?,FiniPC=?,FiniSC=?,FiniME1=?,FiniME10=? where Id_asunto=?";
            System.out.println(sql_update);
            PreparedStatement ps_update = con.prepareStatement(sql_update);
            ps_update.setString(1, codigo);
            ps_update.setString(2, nombre);
            ps_update.setInt(3, parque);
            ps_update.setInt(4, norma);
            if (periodicidad==0){ps_update.setNull(5, java.sql.Types.INTEGER);} else{ps_update.setInt(5, periodicidad);}
            ps_update.setInt(6, cliente);
            ps_update.setInt(7, responsable);
            ps_update.setInt(8, aero);
            ps_update.setBoolean(9, estado);
            ps_update.setString(10, npos);
            ps_update.setString(11, nc);
            ps_update.setString(12, idioma);
            if (FiniPC==null){ps_update.setNull(11, java.sql.Types.NVARCHAR);} else{ps_update.setString(13, FiniPC);}
            if (FiniSC==null){ps_update.setNull(15, java.sql.Types.NVARCHAR);} else{ps_update.setString(14, FiniSC);}
            if (FiniME1==null){ps_update.setNull(16, java.sql.Types.NVARCHAR);} else{ps_update.setString(15, FiniME1);}
            if (FiniME10==null){ps_update.setNull(17, java.sql.Types.NVARCHAR);} else{ps_update.setString(16, FiniME10);}
            ps_update.setInt(17, asunto);
             
            ps_update.executeUpdate();
            ps_update.close();
            
            String instruccion="Update Asunto set Codigo="+codigo+",Nombre="+nombre+", PE="+parque+",";
            if (norma==0){instruccion=instruccion+"Norma=null,";} else{instruccion=instruccion+"Norma="+norma+",";}
            if (periodicidad==0){instruccion=instruccion+"Periodicidad=null,";} else{instruccion=instruccion+"Periodicidad="+periodicidad+",";}
            instruccion=instruccion+"Cliente="+cliente+",";
            instruccion=instruccion+"Responsable="+responsable+",";      
            instruccion=instruccion+"Aerogenerador="+aero+",";
            instruccion=instruccion+"Abierto="+estado+",";
            instruccion=instruccion+"Posicion="+npos+",";
            instruccion=instruccion+"Ncorto="+nc+",";
            instruccion=instruccion+"Idioma="+idioma+",";
            if (FiniPC==null){instruccion=instruccion+"FiniPC=null,";} else{instruccion=instruccion+"FiniPC="+FiniPC+",";}
            if (FiniSC==null){instruccion=instruccion+"FiniSC=null,";} else{instruccion=instruccion+"FiniSC="+FiniSC+",";}
            if (FiniME1==null){instruccion=instruccion+"FiniME1=null,";} else{instruccion=instruccion+"FiniME1="+FiniME1+",";}
            if (FiniME10==null){instruccion=instruccion+"FiniME10=null,";} else{instruccion=instruccion+"FiniME10="+FiniME10+"";}
            instruccion=instruccion+" where Id_asunto="+asunto;
            System.out.println(instruccion);
                      
            b=true;

            fw.write("Intrucción:\n");
            fw.write(instruccion);
            fw.write("\n");
            
            String sql_contactos_1="Delete from AsuntoContacto where Asunto=?";
            PreparedStatement ps_contactos_1=con.prepareStatement(sql_contactos_1);
            ps_contactos_1.setInt(1, asunto);
            ps_contactos_1.execute();
            ps_contactos_1.close();
            
            fw.write("Delete from AsuntoContacto where Asunto="+asunto);
            fw.write("\n");
            System.out.println("Delete from AsuntoContacto where Asunto="+asunto);
            
            boolean jesus=false;
            for (int i=0;i<contactos.size();i++){
                 int Idc=C.getIdContacto(contactos.get(i));
                 if (Idc==29){jesus=true;}
                 
                 String sql_contactos_2="Insert into AsuntoContacto values (?,?,1,0)";
                 PreparedStatement ps_contactos_2=con.prepareStatement(sql_contactos_2);
                 ps_contactos_2.setInt(1, Idc);
                 ps_contactos_2.setInt(2, asunto);
                 ps_contactos_2.execute();
                 ps_contactos_2.close();
                 
                 fw.write("Insert into AsuntoContacto values ("+Idc+","+asunto+",1,0)");
                 fw.write("\n");
                 System.out.println("Insert into AsuntoContacto values ("+Idc+","+asunto+",1,0)");
            }
            
            if (!jesus){
                String sql_contactos_3="Insert into AsuntoContacto values (29,?,1,1)";
                PreparedStatement ps_contactos_3=con.prepareStatement(sql_contactos_3);
                ps_contactos_3.setInt(1, asunto);
                ps_contactos_3.execute();
                ps_contactos_3.close();
                
                fw.write("Insert into AsuntoContacto values (29,"+asunto+",1,1)");
                fw.write("\n");
                fw.write("Fin de Modificación\n");

                System.out.println("Insert into AsuntoContacto values (29,"+asunto+",1,1)");
            }
            fw.close();
           
            System.out.println("\\\\Bsolar\\DatosSolar\\Curva\\Asuntos\\"+codigo_antiguo+"."+nombre_antiguo);
            System.out.println("\\\\Bsolar\\DatosSolar\\Curva\\Asuntos\\"+codigo+"."+nombre);
            File foldern=new File("\\\\Bsolar\\DatosSolar\\Curva\\Asuntos\\"+codigo+"."+nombre);
            folder.renameTo(foldern);
   
            con.commit();
            con.close();            
      }catch(SQLException e){            
            e.printStackTrace();
            throw e;
      }catch (Exception e){
            e.printStackTrace();
            throw e;
      }finally{             
           if (con!=null){con.close();}    
      }
     return b;        
    }    
    
//--------------------------------------------------------------------------------------------//    
   
     // Devuelve todos los campos de un asunto.
    public ArrayList<String> getAsunto(int asunto) throws SQLException {
       Connection con = null;             
       ArrayList <String> datos=new ArrayList <String>();
       try{            
            con = DriverManager.getConnection(url, user, pass);         
            
            PreparedStatement ps = con.prepareStatement("Select * from Asunto where Id_Asunto=?");            
            ps.setInt(1, asunto);
            ResultSet rs=ps.executeQuery();                       
            while (rs.next()){
                datos.add(String.valueOf(rs.getInt("Id_asunto"))); // Id 0
                datos.add(rs.getString("Codigo")); // Codigo 1
                datos.add(rs.getString("Nombre")); // Nombre 2
                datos.add(""+rs.getInt("PE")); // PE 3
                datos.add(""+rs.getInt("Norma")); // Norma 4
                datos.add(""+rs.getInt("Periodicidad")); // Periodicidad 5
                datos.add(""+rs.getString("Clave")); // Clave 6
                datos.add(""+rs.getInt("Cliente")); // Cliente 7
                datos.add(""+rs.getInt("Responsable")); // Responsable 8
                datos.add(""+rs.getInt("Aerogenerador")); // Aerogenerador 9
                datos.add(rs.getString("Abierto")); // Abierto 10
                datos.add(rs.getString("Posicion")); // Posicion 11
                datos.add(rs.getString("Ncorto")); // N corto 12
                datos.add(rs.getString("Idioma")); // Idioma 13
                datos.add(rs.getString("FiniPC")); // Fini CP 14
                datos.add(rs.getString("FiniSC")); // Fini SC 15   
                datos.add(rs.getString("FiniME1")); // Fini ME1 16   
                datos.add(rs.getString("FiniME10")); // Fini ME10 17   
                datos.add(""+rs.getInt("Tipo")); // Tipo 18
            }
            rs.close();
            ps.close();
            con.close();
        }catch(SQLException e){            
            e.printStackTrace();     
            throw e;
        }finally{
            if (con!=null){con.close();}   
        }
        return datos;
    }

//--------------------------------------------------------------------------------------------//    

    // Devuelve codigo.nombre de todos los asuntos existentes (ordenados con código) según tipo,responsable y activo
    // Responsable=0 --> Todos Responsable!=0 --> Responsable
    public static ArrayList getAsuntos(int filtro_responsable,int codigo_tipo,boolean filtro_activo) throws SQLException {
       Connection con = null;             
       ArrayList <String> asuntos=new ArrayList <String>();
       try{   
            Numero N=new Numero();
            String tipo=N.getBinario(codigo_tipo,4);
            
            con = DriverManager.getConnection(url, user, pass);         
//            String sql="Select Codigo,Nombre from Asunto where 1=0";
//           
//            if (N.getBit(tipo,1,4)==1){ // Estandar
//                sql=sql+" union (Select Codigo,Nombre from Asunto where tipo=0";
//                if (filtro_responsable!=0){sql=sql+" and responsable="+filtro_responsable;}
//                if (filtro_activo){sql=sql+" and abierto=1";}
//                sql=sql+")";
//            }
//            if (N.getBit(tipo,2,4)==1){ // General
//               sql=sql+" union (Select Codigo,Nombre from Asunto where tipo=1";
//               if (filtro_responsable!=0){sql=sql+" and responsable="+filtro_responsable;}
//               if (filtro_activo){sql=sql+" and abierto=1";}
//               sql=sql+")";
//            }
//            if (N.getBit(tipo,3,4)==1){ // Minieólica
//               sql=sql+" union (Select Codigo,Nombre from Asunto where tipo=2";
//               if (filtro_responsable!=0){sql=sql+" and responsable="+filtro_responsable;}
//               if (filtro_activo){sql=sql+" and abierto=1";}
//               sql=sql+")";
//            }
//            if (N.getBit(tipo,4,4)==1){ // Acústica
//               sql=sql+" union (Select Codigo,Nombre from Asunto where tipo=3";
//               if (filtro_responsable!=0){sql=sql+" and responsable="+filtro_responsable;}
//               if (filtro_activo){sql=sql+" and abierto=1";}
//               sql=sql+")";
//            }
            
            String sql = "";
            
            for (int i = 0; i < 4; i++) {
                // 1 - Estandar
                // 2 - Estandar
                // 3 - Estandar
                // 4 - Estandar
                if (N.getBit(tipo, i+1, 4) == 1) {
                    if (sql.length() > 0)
                        sql += " union ";
                    
                    sql += "(Select Codigo, Nombre from Asunto where tipo = " + i;
                    if (filtro_responsable != 0)
                        sql += " and responsable = " + filtro_responsable;
                    if (filtro_activo)
                        sql += " and abierto = 1";
                    sql += ")";
                }
            }
            
            System.out.println(sql);
            PreparedStatement ps = con.prepareStatement(sql);            
            ResultSet rs=ps.executeQuery();                       
            
            while (rs.next())
                asuntos.add(rs.getString(1)+"."+rs.getString(2));
            
            rs.close();
            ps.close();
            con.close();
            
            if (asuntos.size()>1)
                ordenar(asuntos,1,asuntos.size()-1);
       } catch(SQLException e) {            
            e.printStackTrace();     
            throw e;
       } finally {
            if (con!=null)
                con.close();            
       }
       return asuntos;  
    } 
    
//--------------------------------------------------------------------------------------------//
    
  // Devuelve si un codigo tiene el formato valido
    public boolean isCodigo(String codigo)  {
       
       FechaRA F=new FechaRA();
       Numero N=new Numero();
       boolean valido=true;
          
       if (codigo.length()!=8){
           valido=false;
       } else {
            if (!codigo.substring(4, 5).equals("-")){
                valido=false;
            } else {
                if (!F.isAnio(codigo.substring(0,4))){
                    valido=false;
                } else{
                    if (!N.isEntero(codigo.substring(5))){
                        valido=false;
                    }
                }
            }
       }
     
       return valido;
    } 
    
//--------------------------------------------------------------------------------------------//      
    
    // Devuelve si un codigo ya existe en la BD
    public boolean ExisteCodigo(String codigo)throws SQLException{
        Connection con = null;
        boolean existe=false;
        try{            
            con = DriverManager.getConnection(url, user, pass);
            PreparedStatement ps = con.prepareStatement("Select Codigo from Asunto where Codigo=?"); 
            ps.setString(1, codigo);
            ResultSet rs=ps.executeQuery();
            if (rs.next()) {
                existe=true;     
            }
            rs.close();
            ps.close();
            con.close();
        }catch (SQLException e){
            e.printStackTrace();
            throw e;
        }finally{
            if (con!=null) con.close();
        }
        return existe;
    }

//--------------------------------------------------------------------------------------------//      
    
 // Ordena los asuntos por codigo
 public static void ordenar(ArrayList<String> asuntos, int izq, int der) {
    int i = izq;
    int j = der;
    
    String pivote = asuntos.get( (izq + der) / 2);
    do {
        while (EsMenor(asuntos.get(i),pivote)) i++;
        while (EsMayor(asuntos.get(j),pivote)) j--;
        if (i <= j) {
            String aux = asuntos.get(i);
            asuntos.set(i,asuntos.get(j));
            asuntos.set(j, aux);
            i++;
            j--;
        }
    }
    
    while (i <= j);
    if (izq < j) {
      ordenar(asuntos, izq, j);
    }
    if (i < der) {
      ordenar(asuntos, i, der);
    }
  }
 
 //--------------------------------------------------------------------------------------------//    

 // Indica si es menor un asunto que otro segun su codigo 
 public static boolean EsMenor(String x,String y){
      boolean menor=false;
      int ano1=Integer.parseInt(x.substring(0, 4));
      int numero1=Integer.parseInt(x.substring(5, 8));
      int ano2=Integer.parseInt(y.substring(0, 4));
      int numero2=Integer.parseInt(y.substring(5, 8));
      if (ano1<ano2){
        menor=true;
      } else{
        if (ano1==ano2){
            if (numero1<numero2){
                menor=true;
            }
        }
      }
      return menor;  
 }
 
 //--------------------------------------------------------------------------------------------//    

 // Indica si es mayor un asunto que otro segun su codigo 
 public static boolean EsMayor(String x,String y){
      boolean mayor=false;
      int ano1=Integer.parseInt(x.substring(0, 4));
      int numero1=Integer.parseInt(x.substring(5, 8));
      int ano2=Integer.parseInt(y.substring(0, 4));
      int numero2=Integer.parseInt(y.substring(5, 8));
      if (ano1>ano2){
        mayor=true;
      }else{
        if (ano1==ano2){
            if (numero1>numero2){
                mayor=true;
            }
        }
      }
      return mayor;  
 }
 
//--------------------------------------------------------------------------------------------//     

  
    // Devuelve el codigo de un asunto dando su identificador. Si no existe, devuelve ""
    public String getCodigo(int asunto)throws SQLException{
        Connection con = null;
        String codigo="";
        try{            
            con = DriverManager.getConnection(url, user, pass);
            
            PreparedStatement ps = con.prepareStatement("Select Codigo from Asunto where Id_asunto=?"); 
            ps.setInt(1, asunto);
            ResultSet rs=ps.executeQuery();
            if (rs.next()) {
                codigo=rs.getString(1);
                
            }
            rs.close();
            ps.close();
            con.close();
        }catch (SQLException e){
            e.printStackTrace();
            throw e;
        }
        finally{
             if (con!=null) con.close();
        }
        return (codigo);
    }
    
//--------------------------------------------------------------------------------------------//
  
    // Devuelve el nombre simple de un asunto. Devuelve null si no existe el asunto.
    public  String getNombre(int asunto) throws SQLException{
        Connection con = null;
        String nombre=null;
        try{            
            con = DriverManager.getConnection(url, user, pass);           
            
            PreparedStatement ps = con.prepareStatement("select Nombre from Asunto where Id_Asunto=?");
            ps.setInt(1, asunto);
            ResultSet rs=ps.executeQuery();
            if(rs.next()) {
                nombre=rs.getString(1);   
            }
            rs.close();
            ps.close();
            con.close();            
        }catch(SQLException e){            
            e.printStackTrace();    
            throw e;
        }finally{
            if (con!=null){con.close();}            
        }
        return nombre;
    }
      
//--------------------------------------------------------------------------------------------//
    
    // Devuelve la fecha de inicio de las mediciones de ruido de un site. null si no hay datos. 
    public String getFiniRA(int asunto,int site)throws SQLException{
        Connection con = null;
        String Fini=null;
        try{            
            con = DriverManager.getConnection(url, user, pass);
            
            String tabla="DatosSPL";
            if (site==105){tabla="DATOSOCT";}
            if (site==106){tabla="DATOSFFT";}
            PreparedStatement ps = con.prepareStatement("Select Fecha_Hora from "+tabla+asunto+" where Id=1");   
            ResultSet rs=ps.executeQuery();
            if (rs.next()) {
                Fini=rs.getString(1);
            }
            rs.close();
            ps.close();
            con.close();
        }catch (SQLException e){
            e.printStackTrace();
            throw e;
        }finally{
            if (con!=null) con.close();     
        }
        return (Fini);
    }
    
    //--------------------------------------------------------------------------------------------//

    // Devuelve los contactos externos de un asunto 
    public ArrayList getContactosExternos(int asunto,boolean filtro_web) throws SQLException {
       Connection con = null;             
       ArrayList <String> contactos=new ArrayList <String>();
        try{            
            con = DriverManager.getConnection(url, user, pass); 
            String sql="Select Contacto.Nombre from Contacto,AsuntoContacto where Contacto.Id_contacto=AsuntoContacto.Contacto and AsuntoContacto.Asunto=? and Contacto.Interno=0";
            if (filtro_web){sql=sql+"  and Contacto.Web=1 ";}
            System.out.println(sql);
            PreparedStatement ps = con.prepareStatement(sql);            
            ps.setInt(1, asunto);
            ResultSet rs=ps.executeQuery();   
            int i=0;
            while (rs.next()){
                contactos.add(rs.getString(1));
                i=i+1;
            }
            rs.close();
            ps.close();
            con.close();
            
        }catch(SQLException e){            
            e.printStackTrace(); 
            throw e;
        }finally{
            if (con!=null){con.close();}
        }
        return contactos;
    } 
    
//--------------------------------------------------------------------------------------------//    

    // Devuelve codigo+nombre de todos los asuntos activos de un parque de tipo=0 OR tipo=1
    public ArrayList getAsuntosActivosEnsayoParque(int pe) throws SQLException {
       Connection con = null;             
       ArrayList <String> asuntos=new ArrayList <String>();
        try{            
            con = DriverManager.getConnection(url, user, pass);         
            PreparedStatement ps = con.prepareStatement("Select Codigo,Nombre from Asunto where PE=? and Abierto=1 and (tipo=0 OR tipo=1)");            
            ps.setInt(1, pe);
            ResultSet rs=ps.executeQuery();   
            while (rs.next()){
                asuntos.add(rs.getString(1)+"."+rs.getString(2));
            }
            rs.close();
            ps.close();
            con.close();
            if (asuntos.size()>1){
                ordenar(asuntos,1,asuntos.size()-1);
            }
            
        }catch(SQLException e){            
            e.printStackTrace(); 
            throw e;
        }finally{
            if (con!=null){con.close();}
        }
        return asuntos;
    } 
//--------------------------------------------------------------------------------------------//

    // Devuelve el parque asociado al ensayo
    public int getParque(int asunto)throws SQLException{
        Connection con = null;
        int pe=0;
        try{            
            con = DriverManager.getConnection(url, user, pass);
            PreparedStatement ps = con.prepareStatement("Select PE from Asunto where Id_asunto=?"); 
            ps.setInt(1, asunto);
            ResultSet rs=ps.executeQuery();
            if (rs.next()) {
                pe=rs.getInt(1);      
            }
            rs.close();
            ps.close();
            con.close();
        }catch (SQLException e){
            e.printStackTrace();
            throw e;
        }finally{
            if (con!=null) con.close();
        }
        return (pe);
    }
} 