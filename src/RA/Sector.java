package RA;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author Ruth_Cordon
 */

public class Sector {
    
    private static String url = "jdbc:sqlserver://" + Global.IP_SERVER_GCP;
    private static String user = Global.USER_SERVER_GCP;
    private static String pass = Global.PASS_SERVER_GCP;
    
    static{
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        }catch(ClassNotFoundException e){
            System.out.println("No puedo cargar el driver de la BD");
        }
    }

//--------------------------------------------------------------------------------------------//           
    
    // Inserta el sector válido a un ensayo. Devuelve true si ya existe sector para el asunto.
    public boolean NuevoSector(int asunto, ArrayList <double []> S)throws SQLException{
       Connection con = null;
       boolean b=false;
       try{            
          con=DriverManager.getConnection(url,user,pass);  
          con.setAutoCommit(false);
          PreparedStatement ps = con.prepareStatement("Select * from Sector_Valido where Asunto=?"); 
          PreparedStatement ps2 = con.prepareStatement("Insert into Sector_Valido values (?,?,?,?)");   
          ps.setInt(1, asunto);
          ResultSet rs=ps.executeQuery();
          if (rs.next()){
              b=true;    
          }
          rs.close();
          ps.close();
          if (!b){
               int ns=1;
               for (int j=0;j<S.size();j++){
                    ps2.setInt(1, asunto);
                    ps2.setInt(2, ns);
                    ps2.setDouble(3, S.get(j)[0]);
                    ps2.setDouble(4, S.get(j)[1]);
                    ps2.execute();
                    ns=ns+1;
               }             
          }
          System.out.println("Select * from Sector_Valido where Asunto=?");
          ps2.close();            
          con.commit();
          con.close();
      }catch (SQLException e){
           e.printStackTrace();
      }
      finally{
           try {
               if (con!=null) 
                    con.close();
           } catch (SQLException e){
                e.printStackTrace();
           }
      }
      return b;
    }
    
//--------------------------------------------------------------------------------------------//      

    // Devuelve el sector de un asunto.
    public ArrayList <double []> getSector(int asunto) throws SQLException{
        Connection con = null;
        ArrayList <double []> L=new ArrayList <double []>();
        try {
            con=DriverManager.getConnection(url,user,pass);          
            PreparedStatement ps= con.prepareStatement("Select * from Sector_Valido where Asunto=?");   
            ps.setInt(1, asunto);
            ResultSet rs=ps.executeQuery();      
            int i=0;
            while (rs.next()){
                double [] Linea=new double [2];
                Linea [0]=rs.getDouble(3);
                Linea [1]=rs.getDouble(4);
                L.add(i, Linea);  
                i=i+1;
            } 
            rs.close();
            ps.close();
           con.close();     
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try {
              if (con!=null) con.close();
            } catch (SQLException e){
               e.printStackTrace();
            }
        }
        return L;
    }

//--------------------------------------------------------------------------------------------//      
 
    // Devuelve el sector redondeado de un asunto.
    public ArrayList <double []> getSectorRedondeado(int asunto,ArrayList<double[]> sectores) throws SQLException{
        ArrayList <double []> SR=new ArrayList <double []>();
        try {
            ArrayList <double[]> sv=getSector(asunto);      
            System.out.println("Sector Redondeado");
            for (int s=0;s<sv.size();s++){
                
                double[] dato=new double[2];
                int g=0;
                boolean encontrado=false;           
                while ((g<sectores.size()) && (!encontrado)){
                    if (sectores.get(g)[0]<=sectores.get(g)[1]){
                        if ((sv.get(s)[0]>=sectores.get(g)[0]) && (sv.get(s)[0]<sectores.get(g)[1])){
                            encontrado=true;
                            dato[0]=sectores.get(g)[0];
                        }
                    } else{
                        if (((sv.get(s)[0]>=sectores.get(g)[0]) && (sv.get(s)[0]<360)) || ((sv.get(s)[0]>=0) && (sv.get(s)[0]<sectores.get(g)[1]))){
                            encontrado=true;
                            dato[0]=sectores.get(g)[0];
                        }
                    }
                    g=g+1;
                }
                encontrado=false;
                g=0;
                while ((g<sectores.size()) && (!encontrado)){
                    if (sectores.get(g)[0]<=sectores.get(g)[1]){
                        if ((sv.get(s)[1]>sectores.get(g)[0]) && (sv.get(s)[1]<=sectores.get(g)[1])){
                             encontrado=true;
                             dato[1]=sectores.get(g)[1];
                        }
                    } else{
                        if (((sv.get(s)[1]>sectores.get(g)[0]) && (sv.get(s)[1]<=360)) || ((sv.get(s)[1]>0) && (sv.get(s)[1]<=sectores.get(g)[1]))){
                            encontrado=true;
                            dato[1]=sectores.get(g)[1];
                        }
                    }
                    g=g+1;
                }           
                SR.add(s, dato);
                System.out.println(dato[0]+" "+dato[1]);
            }     
        }catch(SQLException e){
            e.printStackTrace();
        }
        return SR;
    }

//--------------------------------------------------------------------------------------------//      
      
         
    
    // Devuelve el sector de un asunto.
    public ArrayList <double []> getSectorRedondeado(ArrayList<double[]> sv,ArrayList<double[]> sectores) throws SQLException{
        ArrayList <double []> SR=new ArrayList <double []>();
        for (int s=0;s<sv.size();s++){
                double[] dato=new double[2];
                int g=0;
                boolean encontrado=false;           
                while ((g<sectores.size()) && (!encontrado)){
                    if (sectores.get(g)[0]<=sectores.get(g)[1]){
                        if ((sv.get(s)[0]>=sectores.get(g)[0]) && (sv.get(s)[0]<sectores.get(g)[1])){
                            encontrado=true;
                            dato[0]=sectores.get(g)[0];
                        }
                    } else{
                        if (((sv.get(s)[0]>=sectores.get(g)[0]) && (sv.get(s)[0]<360)) || ((sv.get(s)[0]>=0) && (sv.get(s)[0]<sectores.get(g)[1]))){
                            encontrado=true;
                            dato[0]=sectores.get(g)[0];
                        }
                    }
                    g=g+1;
                }
                encontrado=false;
                g=0;
                while ((g<sectores.size()) && (!encontrado)){
                    if (sectores.get(g)[0]<=sectores.get(g)[1]){
                        if ((sv.get(s)[1]>=sectores.get(g)[0]) && (sv.get(s)[1]<sectores.get(g)[1])){
                             encontrado=true;
                             dato[1]=sectores.get(g)[1];
                        }
                    } else{
                        if (((sv.get(s)[1]>=sectores.get(g)[0]) && (sv.get(s)[1]<360)) || ((sv.get(s)[1]>=0) && (sv.get(s)[1]<sectores.get(g)[1]))){
                            encontrado=true;
                            dato[1]=sectores.get(g)[1];
                        }
                    }
                    g=g+1;
                }           
                SR.add(s, dato);
            }     
        
        return SR;
    }

//--------------------------------------------------------------------------------------------//      
    
    // Devuelve el sector valido de una posicion en un parque 
    public ArrayList<ArrayList <String []>> getSectorValido(String objeto,ArrayList<String[]> objetos,boolean dosD) throws SQLException {
        return getSectorValido(objeto, objetos, dosD, 20);
    }
    
    public ArrayList<ArrayList <String []>> getSectorValido(String objeto,ArrayList<String[]> objetos,boolean dosD, Integer numDfinPerturbacionSector) throws SQLException{
        
        Numero N=new Numero();
        
        ArrayList<ArrayList <String []>> valido=new ArrayList<ArrayList <String []>>();
        ArrayList <String []> tabla=new ArrayList<String[]>(); 
        
        System.out.println("Objeto "+objeto);
        double[] distancia=new double[objetos.size()];
        double[] distancia2=new double[objetos.size()]; // d/dn
        double[] amplitud=new double[objetos.size()];
        double[] alfa=new double[objetos.size()]; // Direccion
        double[][] limites=new double[objetos.size()][2];
        ArrayList <String[]> resultado=new ArrayList<String[]>();
        ArrayList <double[]> resultadoaux=new ArrayList<double[]>();
       
        
        int posicion=0;
        // Se busca la posición en el vector de objetos
        for (int i=0;i<objetos.size();i++){
             if (objetos.get(i)[0].equals(objeto)){
                 posicion=i;
                 System.out.println("La posicion de "+objetos.get(i)[0]+" es "+posicion);
             }
        }
        double x0=Double.parseDouble(objetos.get(posicion)[1]);
        double y0=Double.parseDouble(objetos.get(posicion)[2]);
        for (int i=0;i<objetos.size();i++){
            if (i!=posicion){
                
                 double x=Double.parseDouble(objetos.get(i)[1]);
                 double y=Double.parseDouble(objetos.get(i)[2]);
                 double dn=Double.parseDouble(objetos.get(i)[6]);
                 distancia[i]=java.lang.Math.sqrt(java.lang.Math.pow(x-x0,2)+java.lang.Math.pow(y-y0,2));
                 distancia2[i]=distancia[i]/dn;
                 amplitud[i]=10+(1.3*java.lang.Math.atan(2.5*dn/distancia[i]+0.15))*180/java.lang.Math.PI;
                 if ((x-x0)!=0){
                    alfa[i]=java.lang.Math.atan((y-y0)/(x-x0))*180/java.lang.Math.PI;
                 } else{
                    alfa[i]=90 * ((y - y0) / java.lang.Math.abs(y - y0));
                 }
                 if ((y-y0)!=0){
                     if ((x-x0)>0 && (y-y0)>0){
                         alfa[i]=java.lang.Math.abs(alfa[i]);
                     }
                     if ((x-x0)>0 && (y-y0)<0){
                         alfa[i]=alfa[i];
                     }
                     if ((x-x0)<0 && (y-y0)<0){
                         alfa[i]=alfa[i]-180;
                     }
                     if ((x-x0)<0 && (y-y0)>0){
                         alfa[i]=alfa[i]+180;
                     }
                     
                } else{
                    if ((x-x0)<0){
                        alfa[i]=180;
                    } else{
                        alfa[i]=0;
                    }
                }
                if (alfa[i]<0){alfa[i]=alfa[i]+360;} 
                if ((alfa[i]>=0) && (alfa[i]<=90)) {
                    alfa[i]=-alfa[i]+90;
                } else{
                    alfa[i]=-alfa[i]+450;
                }              
                limites[i][0]=N.redondear(alfa[i]-amplitud[i]/2, 2);
                limites[i][1]=N.redondear(alfa[i]+amplitud[i]/2, 2);
                if (limites[i][0]<0){limites[i][0]=limites[i][0]+360;}
                if (limites[i][1]>=360){limites[i][1]=limites[i][1]-360;}
                System.out.println("Limites objeto"+objetos.get(i)[0]+" "+limites[i][0]+" "+limites[i][1]);
            } 
        }
        
        // Me creo la tabla de sectores invalidados en aero
        for (int i=0;i<objetos.size();i++){
             if ((i!=posicion) && (distancia2[i]<=numDfinPerturbacionSector)){
                 String[] dato=new String[7];
                 dato[0]=objetos.get(i)[0]; // id
                 dato[1]=objetos.get(i)[5]; // anchura
                 dato[2]=objetos.get(i)[4]; //altura
                 dato[3]=String.valueOf(distancia[i]); //distancia
                 dato[4]=String.valueOf(alfa[i]); //direccion
                 dato[5]=String.valueOf(limites[i][0]); //limite inf
                 dato[6]=String.valueOf(limites[i][1]); //limite sup
                 if (!dosD){
                     if (distancia2[i]<2){
                        dato[5]="0"; //limite inf
                        dato[6]="360"; //limite sup
                     }
                 }
                 tabla.add(dato);     
             } 
        }
        System.out.println("Tabla de sectores invalidados");
        for (int i=0;i<tabla.size();i++){
            System.out.println(tabla.get(i)[0]+" "+tabla.get(i)[1]+" "+tabla.get(i)[2]+" "+tabla.get(i)[3]+" "+tabla.get(i)[4]+" "+tabla.get(i)[5]+" "+tabla.get(i)[6]);
        }
        
        // Se calculan los límites del sector valido
        System.out.println("Limites");
        // Se inicializa
        double[] aux=new double[2];
        if (tabla.size()!=0){
            aux[0]=Double.parseDouble(tabla.get(0)[5]);
            aux[1]=Double.parseDouble(tabla.get(0)[6]);
            resultadoaux.add(0,aux);
        }
        int n=0;
        for (int i=1;i<tabla.size();i++){

                double[] s1=new double[2];
                s1[0]=Double.parseDouble(tabla.get(i)[5]);
                s1[1]=Double.parseDouble(tabla.get(i)[6]);
                int j=0;
                boolean encontrado=false;
                while ((j<resultadoaux.size()) && (!encontrado)){
                    if ((interseccion(resultadoaux.get(j),s1)) || (interseccion(s1,resultadoaux.get(j)))){
                        encontrado=true;
                    } 
                    j=j+1;
                }
                if (!encontrado){
                    // Se añade a resultados aux
                    System.out.println("Se añade ["+s1[0]+","+s1[1]+"] a resultadoaux");
                    n=n+1;
                    resultadoaux.add(n, s1);   
                } else{ // Hay interseccion con j-1
                    double[] dato=new double[2];
                    dato[0]=resultadoaux.get(j-1)[0];
                    dato[1]=resultadoaux.get(j-1)[1];
                    if (resultadoaux.get(j-1)[0]<=resultadoaux.get(j-1)[1]){
                        if (s1[0]<=s1[1]){
                            if (resultadoaux.get(j-1)[0]>s1[0]){ // Se cambia el limite inferior
                                dato[0]=s1[0];
                            }
                            if (resultadoaux.get(j-1)[1]<s1[1]){ // Se cambia el limite superior
                                dato[1]=s1[1];
                            }
                        } else{
                            if (((resultadoaux.get(j-1)[0]>s1[0]) && (s1[0]>=0))|| (resultadoaux.get(j-1)[1]<s1[0]) && (s1[0]<=360)){ // Se cambia el limite inferior
                                dato[0]=s1[0];
                            }
                            if (((resultadoaux.get(j-1)[0]>s1[1]) && (s1[1]>=0))|| (resultadoaux.get(j-1)[1]<s1[1]) && (s1[1]<=360)){ // Se cambia el limite superior
                                dato[1]=s1[1];
                            }
                            if ((resultadoaux.get(j-1)[0]<=s1[0]) && (resultadoaux.get(j-1)[1]>=s1[0])&& (resultadoaux.get(j-1)[1]>=s1[1])&& (resultadoaux.get(j-1)[0]<=s1[1])){
                                dato[0]=0;
                                dato[1]=360;
                            }
                        }   
                    } else{
                        if ((resultadoaux.get(j-1)[0]>s1[0]) && (resultadoaux.get(j-1)[1]<s1[0])){ // Se cambia el limite inferior
                            dato[0]=s1[0];
                        }
                        if ((resultadoaux.get(j-1)[1]<s1[1]) && (resultadoaux.get(j-1)[0]>s1[1])){ // Se cambia el limite superior
                            dato[1]=s1[1];
                        }
                        if ((resultadoaux.get(j-1)[0]<=s1[0]) && (resultadoaux.get(j-1)[1]>=s1[0])&& (resultadoaux.get(j-1)[1]>=s1[1])&& (resultadoaux.get(j-1)[0]<=s1[1])){
                                dato[0]=0;
                                dato[1]=360;
                            }
                    }
                    resultadoaux.set(j-1,dato);
                }
        }
        
        // Se recorre resultadoaux tantas veces hasta que no se reduzca su dimension
        
        ArrayList <double[]> resultadoaux2=new ArrayList<double[]>();
        int ant=resultadoaux.size();
        int nuevo=resultadoaux2.size();
        while (nuevo<ant){
            
            resultadoaux2=new ArrayList<double[]>();
            aux=new double[2];
            aux[0]=resultadoaux.get(0)[0];
            aux[1]=resultadoaux.get(0)[1];
        
            n=0;
            resultadoaux2.add(0,aux);
            for (int i=1;i<resultadoaux.size();i++){
                double[] s1=new double[2];
                s1[0]=resultadoaux.get(i)[0];
                s1[1]=resultadoaux.get(i)[1];
                int j=0;
                boolean encontrado=false;
                while ((j<resultadoaux2.size()) && (!encontrado)){
                    if ((interseccion(resultadoaux2.get(j),s1)) || (interseccion(s1,resultadoaux2.get(j)))){
                        encontrado=true;
                    } 
                    j=j+1;
                }
                if (!encontrado){
                    // Se añade a resultados aux 2
                    n=n+1;
                    resultadoaux2.add(n, s1);   
                } else{ // Hay interseccion con j-1
                    double[] dato=new double[2];
                    dato[0]=resultadoaux2.get(j-1)[0];
                    dato[1]=resultadoaux2.get(j-1)[1];
                    if (resultadoaux2.get(j-1)[0]<=resultadoaux2.get(j-1)[1]){
                        if (s1[0]<=s1[1]){
                            if (resultadoaux2.get(j-1)[0]>s1[0]){ // Se cambia el limite inferior
                                dato[0]=s1[0];
                            }
                            if (resultadoaux2.get(j-1)[1]<s1[1]){ // Se cambia el limite superior
                                dato[1]=s1[1];
                            }
                        } else{
                            if (((resultadoaux2.get(j-1)[0]>s1[0]) && (s1[0]>=0))|| (resultadoaux2.get(j-1)[1]<s1[0]) && (s1[0]<=360)){ // Se cambia el limite inferior
                                dato[0]=s1[0];
                            }
                            if (((resultadoaux2.get(j-1)[0]>s1[1]) && (s1[1]>=0))|| (resultadoaux2.get(j-1)[1]<s1[1]) && (s1[1]<=360)){ // Se cambia el limite superior
                                dato[1]=s1[1];
                            }
                            if ((resultadoaux2.get(j-1)[0]<=s1[0]) && (resultadoaux2.get(j-1)[1]>=s1[0])&& (resultadoaux2.get(j-1)[1]>=s1[1])&& (resultadoaux2.get(j-1)[0]<=s1[1])){
                                dato[0]=0;
                                dato[1]=360;
                            }
                        }   
                    } else{
                        if ((resultadoaux2.get(j-1)[0]>s1[0]) && (resultadoaux2.get(j-1)[1]<s1[0])){ // Se cambia el limite inferior
                            dato[0]=s1[0];
                        }
                        if ((resultadoaux2.get(j-1)[1]<s1[1]) && (resultadoaux2.get(j-1)[0]>s1[1])){ // Se cambia el limite superior
                            dato[1]=s1[1];
                        }
                        if ((resultadoaux2.get(j-1)[0]<=s1[0]) && (resultadoaux2.get(j-1)[1]>=s1[0])&& (resultadoaux2.get(j-1)[1]>=s1[1])&& (resultadoaux2.get(j-1)[0]<=s1[1])){
                                dato[0]=0;
                                dato[1]=360;
                        }
                    }

                    resultadoaux2.set(j-1,dato);
                }
            }
            
            ant=resultadoaux.size();
            resultadoaux=new ArrayList<double[]>();
            for (int i=0;i<resultadoaux2.size();i++){
                double[] dato=new double[2];
                dato[0]=resultadoaux2.get(i)[0];
                dato[1]=resultadoaux2.get(i)[1];
                resultadoaux.add(i, dato);
            }
            nuevo=resultadoaux2.size();
            
        }
       
        for (int i=0;i<resultadoaux.size();i++){
            String[] dato=new String[2];
            dato[0]=String.valueOf(resultadoaux.get(i)[0]);
            dato[1]=String.valueOf(resultadoaux.get(i)[1]);
            resultado.add(i,dato);
        }
      
        valido.add(0,tabla);
        valido.add(1,resultado);
        
        return valido;
    }

//--------------------------------------------------------------------------------------------//      

    // Devuelve el sector valido de una torre en un parque 
    public ArrayList<ArrayList <String []>> getSectorValido(String[] torre,ArrayList<String[]> objetos,boolean dosD) throws SQLException{
        return getSectorValido(torre, objetos, dosD, 20);
    }
    
    public ArrayList<ArrayList <String []>> getSectorValido(String[] torre,ArrayList<String[]> objetos,boolean dosD, Integer numDfinPerturbacionSector) throws SQLException{
        
        ArrayList<ArrayList <String []>> valido=new ArrayList<ArrayList <String []>>();
        ArrayList <String []> tabla=new ArrayList<String[]>(); 
       
        double[] distancia=new double[objetos.size()];
        double[] distancia2=new double[objetos.size()]; // d/dn
        double[] amplitud=new double[objetos.size()];
        double[] alfa=new double[objetos.size()]; // Direccion
        double[][] limites=new double[objetos.size()][2];
        ArrayList <String[]> resultado=new ArrayList<String[]>();
        ArrayList <double[]> resultadoaux=new ArrayList<double[]>();
        
        Numero N=new Numero();
        
        double x0=Double.parseDouble(torre[1]);
        double y0=Double.parseDouble(torre[2]);
        for (int i=0;i<objetos.size();i++){
          
                 double x=Double.parseDouble(objetos.get(i)[1]);
                 double y=Double.parseDouble(objetos.get(i)[2]);
                 double dn=Double.parseDouble(objetos.get(i)[6]);
                 distancia[i]=java.lang.Math.sqrt(java.lang.Math.pow(x-x0,2)+java.lang.Math.pow(y-y0,2));
                 distancia2[i]=distancia[i]/dn;
                 amplitud[i]=10+(1.3*java.lang.Math.atan(2.5*dn/distancia[i]+0.15))*180/java.lang.Math.PI;
                 if ((x-x0)!=0){
                    alfa[i]=java.lang.Math.atan((y-y0)/(x-x0))*180/java.lang.Math.PI;
                 } else{
                    alfa[i]=90 * ((y - y0) / java.lang.Math.abs(y - y0));
                 }
                 if ((y-y0)!=0){
                     if ((x-x0)>0 && (y-y0)>0){
                         alfa[i]=java.lang.Math.abs(alfa[i]);
                     }
                     if ((x-x0)>0 && (y-y0)<0){
                         alfa[i]=alfa[i];
                     }
                     if ((x-x0)<0 && (y-y0)<0){
                         alfa[i]=alfa[i]-180;
                     }
                     if ((x-x0)<0 && (y-y0)>0){
                         alfa[i]=alfa[i]+180;
                     }
                     
                } else{
                    if ((x-x0)<0){
                        alfa[i]=180;
                    } else{
                        alfa[i]=0;
                    }
                }
                if (alfa[i]<0){alfa[i]=alfa[i]+360;} 
                if ((alfa[i]>=0) && (alfa[i]<=90)) {
                    alfa[i]=-alfa[i]+90;
                } else{
                    alfa[i]=-alfa[i]+450;
                }              
                limites[i][0]=N.redondear(alfa[i]-amplitud[i]/2, 2);
                limites[i][1]=N.redondear(alfa[i]+amplitud[i]/2, 2);
                if (limites[i][0]<0){limites[i][0]=limites[i][0]+360;}
                if (limites[i][1]>=360){limites[i][1]=limites[i][1]-360;}
             
        }
        // Me creo la tabla de sectores invalidados en aero
        for (int i=0;i<objetos.size();i++){
             if (distancia2[i]<=numDfinPerturbacionSector){
                 String[] dato=new String[7];
                 dato[0]=objetos.get(i)[0]; // id
                 dato[1]=objetos.get(i)[5]; // anchura
                 dato[2]=objetos.get(i)[4]; //altura
                 dato[3]=String.valueOf(distancia[i]); //distania
                 dato[4]=String.valueOf(alfa[i]); //direccion
                 dato[5]=String.valueOf(limites[i][0]); //limite inf
                 dato[6]=String.valueOf(limites[i][1]); //limite sup
                 if (!dosD){
                     if (distancia2[i]<2){
                        dato[5]="0"; //limite inf
                        dato[6]="360"; //limite sup
                     }
                 }
                 
                 tabla.add(dato);     
             } 
        }
        System.out.println("Tabla en torre");
        for (int i=0;i<tabla.size();i++){
            System.out.println(tabla.get(i)[0]+" "+tabla.get(i)[1]+" "+tabla.get(i)[2]+" "+tabla.get(i)[3]+" "+tabla.get(i)[4]+" "+tabla.get(i)[5]+" "+tabla.get(i)[6]);
        }
        // Se calculan los límites del sector valido
        int n=0;
        double[] aux=new double[2];
        if (tabla.size()!=0){
            
            aux[0]=Double.parseDouble(tabla.get(0)[5]);
            aux[1]=Double.parseDouble(tabla.get(0)[6]);
            System.out.println("Se añade ["+aux[0]+","+aux[1]+"] a resultadoaux");
            
            resultadoaux.add(0,aux);
        }
        for (int i=1;i<tabla.size();i++){
            double[] s1=new double[2];
            s1[0]=Double.parseDouble(tabla.get(i)[5]);
            s1[1]=Double.parseDouble(tabla.get(i)[6]);
            int j=0;
            boolean encontrado=false;
            while ((j<resultadoaux.size()) && (!encontrado)){
                if ((interseccion(resultadoaux.get(j),s1)) || (interseccion(s1,resultadoaux.get(j)))){
                    encontrado=true;
                } 
                j=j+1;
            }
            if (!encontrado){
                // Se añade a resultados aux
                System.out.println("Se añade ["+s1[0]+","+s1[1]+"] a resultadoaux");
                n=n+1;
                resultadoaux.add(n, s1);   
            } else{ // Hay interseccion con j-1
                double[] dato=new double[2];
                dato[0]=resultadoaux.get(j-1)[0];
                dato[1]=resultadoaux.get(j-1)[1];
                if (resultadoaux.get(j-1)[0]<=resultadoaux.get(j-1)[1]){
                      if (s1[0]<=s1[1]){
                            if (resultadoaux.get(j-1)[0]>s1[0]){ // Se cambia el limite inferior
                                dato[0]=s1[0];
                            }
                            if (resultadoaux.get(j-1)[1]<s1[1]){ // Se cambia el limite superior
                                dato[1]=s1[1];
                            }
                        } else{
                            if (((resultadoaux.get(j-1)[0]>s1[0]) && (s1[0]>=0))|| (resultadoaux.get(j-1)[1]<s1[0]) && (s1[0]<=360)){ // Se cambia el limite inferior
                                dato[0]=s1[0];
                            }
                            if (((resultadoaux.get(j-1)[0]>s1[1]) && (s1[1]>=0))|| (resultadoaux.get(j-1)[1]<s1[1]) && (s1[1]<=360)){ // Se cambia el limite superior
                                dato[1]=s1[1];
                            }
                            if ((resultadoaux.get(j-1)[0]<=s1[0]) && (resultadoaux.get(j-1)[1]>=s1[0])&& (resultadoaux.get(j-1)[1]>=s1[1])&& (resultadoaux.get(j-1)[0]<=s1[1])){
                                dato[0]=0;
                                dato[1]=360;
                            }
                        }   
                } else{
                    if ((resultadoaux.get(j-1)[0]>s1[0]) && (resultadoaux.get(j-1)[1]<s1[0])){ // Se cambia el limite inferior
                        dato[0]=s1[0];
                    }
                    if ((resultadoaux.get(j-1)[1]<s1[1]) && (resultadoaux.get(j-1)[0]>s1[1])){ // Se cambia el limite superior
                        dato[1]=s1[1];
                    }
                    if ((resultadoaux.get(j-1)[0]<=s1[0]) && (resultadoaux.get(j-1)[1]>=s1[0])&& (resultadoaux.get(j-1)[1]>=s1[1])&& (resultadoaux.get(j-1)[0]<=s1[1])){
                                dato[0]=0;
                                dato[1]=360;
                    }
                }
                
                System.out.println("Se modifica resultadoaux sector  ["+resultadoaux.get(j-1)[0]+","+resultadoaux.get(j-1)[1]+"] por  ["+dato[0]+","+dato[1]+"] ");
                resultadoaux.set(j-1,dato);
            }
        }
       // Se recorre resultadoaux tantas veces hasta que no se reduzca su dimension
        System.out.println("Se comprime");
       
        ArrayList <double[]> resultadoaux2=new ArrayList<double[]>();
        int ant=resultadoaux.size();
        int nuevo=resultadoaux2.size();
        while (nuevo<ant){
            
            resultadoaux2=new ArrayList<double[]>();
            aux=new double[2];
            aux[0]=resultadoaux.get(0)[0];
            aux[1]=resultadoaux.get(0)[1];
            System.out.println("Se añade ["+aux[0]+","+aux[1]+"] a resultadoaux2");
            n=0;
            resultadoaux2.add(0,aux);
            for (int i=1;i<resultadoaux.size();i++){
                double[] s1=new double[2];
                s1[0]=resultadoaux.get(i)[0];
                s1[1]=resultadoaux.get(i)[1];
                int j=0;
                boolean encontrado=false;
                while ((j<resultadoaux2.size()) && (!encontrado)){
                    if ((interseccion(resultadoaux2.get(j),s1)) ||(interseccion(s1,resultadoaux2.get(j)))) {
                        encontrado=true;
                    } 
                    j=j+1;
                }
                if (!encontrado){
                    // Se añade a resultados aux 2
                    System.out.println("Se añade ["+s1[0]+","+s1[1]+"] a resultadoaux2");
                    n=n+1;
                    resultadoaux2.add(n, s1);   
                } else{ // Hay interseccion con j-1
                    double[] dato=new double[2];
                    dato[0]=resultadoaux2.get(j-1)[0];
                    dato[1]=resultadoaux2.get(j-1)[1];
                    if (resultadoaux2.get(j-1)[0]<=resultadoaux2.get(j-1)[1]){
                      if (s1[0]<=s1[1]){
                            if (resultadoaux2.get(j-1)[0]>s1[0]){ // Se cambia el limite inferior
                                dato[0]=s1[0];
                            }
                            if (resultadoaux2.get(j-1)[1]<s1[1]){ // Se cambia el limite superior
                                dato[1]=s1[1];
                            }
                        } else{
                            if (((resultadoaux2.get(j-1)[0]>s1[0]) && (s1[0]>=0))|| (resultadoaux2.get(j-1)[1]<s1[0]) && (s1[0]<=360)){ // Se cambia el limite inferior
                                dato[0]=s1[0];
                            }
                            if (((resultadoaux2.get(j-1)[0]>s1[1]) && (s1[1]>=0))|| (resultadoaux2.get(j-1)[1]<s1[1]) && (s1[1]<=360)){ // Se cambia el limite superior
                                dato[1]=s1[1];
                            }
                            if ((resultadoaux2.get(j-1)[0]<=s1[0]) && (resultadoaux2.get(j-1)[1]>=s1[0])&& (resultadoaux2.get(j-1)[1]>=s1[1])&& (resultadoaux2.get(j-1)[0]<=s1[1])){
                                dato[0]=0;
                                dato[1]=360;
                            }
                        }   
                } else{
                    if ((resultadoaux2.get(j-1)[0]>s1[0]) && (resultadoaux2.get(j-1)[1]<s1[0])){ // Se cambia el limite inferior
                        dato[0]=s1[0];
                    }
                    if ((resultadoaux2.get(j-1)[1]<s1[1]) && (resultadoaux2.get(j-1)[0]>s1[1])){ // Se cambia el limite superior
                        dato[1]=s1[1];
                    }
                    if ((resultadoaux2.get(j-1)[0]<=s1[0]) && (resultadoaux2.get(j-1)[1]>=s1[0])&& (resultadoaux2.get(j-1)[1]>=s1[1])&& (resultadoaux2.get(j-1)[0]<=s1[1])){
                                dato[0]=0;
                                dato[1]=360;
                    }
                }
                resultadoaux2.set(j-1,dato);
                }
            }
            ant=resultadoaux.size();
            resultadoaux=new ArrayList<double[]>();
            for (int i=0;i<resultadoaux2.size();i++){
                double[] dato=new double[2];
                dato[0]=resultadoaux2.get(i)[0];
                dato[1]=resultadoaux2.get(i)[1];
                resultadoaux.add(i, dato);
            }
           
            nuevo=resultadoaux2.size();
        }
            
        
       System.out.println("Sector valido final");
        for (int i=0;i<resultadoaux.size();i++){
            String[] dato=new String[2];
             
            dato[0]=String.valueOf(resultadoaux.get(i)[0]);
            dato[1]=String.valueOf(resultadoaux.get(i)[1]);
            System.out.println(""+i+" "+dato[0]+" "+dato[1]);
            resultado.add(i,dato);
        }
      
        valido.add(0,tabla);
        valido.add(1,resultado);
        
        return valido;
    }

//--------------------------------------------------------------------------------------------//      
    
    // Devuelve la intersección de dos sectores
    private boolean interseccion (double[] s1, double[] s2){
        boolean interseccion=false;
        if (s1[0]<=s1[1]){
            if (((s2[0]>=s1[0]) && (s2[0]<=s1[1])) || ((s2[1]>=s1[0]) && (s2[1]<=s1[1]))){
                     interseccion=true;
            }    
        } else{
            if (((s2[0]>=s1[0]) && (s2[0]<360)) || ((s2[0]>=0) && (s2[1]<=s1[1])) || ((s2[1]>=s1[0]) && (s2[1]<360)) || ((s2[1]>=0) && (s2[1]<s1[1]))){
                   interseccion=true;
            }
        }
        return interseccion;    
    }

//--------------------------------------------------------------------------------------------//      

    // Devuelve el sector no valido resultante
    public ArrayList<String[]> getSVFinal (ArrayList<String[]> aero, ArrayList<String[]> torre){
        ArrayList<String[]> sv=new ArrayList<String[]>();
        ArrayList<double[]> resultadoaux=new ArrayList<double[]>();
         System.out.println("Se calcula el sv ");
        System.out.println("Se añade la torre");
        for (int i=0;i<torre.size();i++){
            double[] aux=new double[2];
            aux[0]=Double.parseDouble(torre.get(i)[0]);
            aux[1]=Double.parseDouble(torre.get(i)[1]);
            System.out.println("Se añade ["+aux[0]+","+aux[1]+"] a resultadofinal");
            resultadoaux.add(i,aux);
        }
        System.out.println("Se añade el aero");
        for (int i=0;i<aero.size();i++){
            double[] aux2=new double[2];
            aux2[0]=Double.parseDouble(aero.get(i)[0]);
            aux2[1]=Double.parseDouble(aero.get(i)[1]);
            
            System.out.println(""+aero.get(i)[0]+" "+aero.get(i)[1]);
            System.out.println("Se añade ["+aux2[0]+","+aux2[1]+"] a resultadofinal");
            resultadoaux.add(i+torre.size(),aux2);
        }
        
        // Se recorre resultadoaux tantas veces hasta que no se reduzca su dimension
        System.out.println("Tamaño de resultadofinal "+resultadoaux.size());
        ArrayList <double[]> resultadoaux2=new ArrayList<double[]>();
        int ant=resultadoaux.size();
        int nuevo=resultadoaux2.size();
        while (nuevo<ant){
            double[] aux=new double[2];
            resultadoaux2=new ArrayList<double[]>();
            aux=new double[2];
            aux[0]=resultadoaux.get(0)[0];
            aux[1]=resultadoaux.get(0)[1];
            System.out.println("Se añade ["+aux[0]+","+aux[1]+"] a resultadoaux2");
            int n=0;
            resultadoaux2.add(n,aux);
            for (int i=1;i<resultadoaux.size();i++){
                double[] s1=new double[2];
                s1[0]=resultadoaux.get(i)[0];
                s1[1]=resultadoaux.get(i)[1];
                System.out.println("s1(0) "+s1[0]+" s1(1)"+s1[1]);
                int j=0;
                boolean encontrado=false;
                while ((j<resultadoaux2.size()) && (!encontrado)){
                    if ((interseccion(resultadoaux2.get(j),s1)) ||(interseccion(s1,resultadoaux2.get(j)))) {
                        encontrado=true;
                        System.out.println("Hay interseccion de "+resultadoaux2.get(j)[0]+"-"+resultadoaux2.get(j)[1]+" con "+s1[0]+"-"+s1[1]);
                    } 
                    j=j+1;
                }
                if (!encontrado){
                    // Se añade a resultados aux 2
                    System.out.println("Se añade ["+s1[0]+","+s1[1]+"] a resultadoaux2");
                    n=n+1;
                    resultadoaux2.add(n, s1);   
                } else{ // Hay interseccion con j-1
                    double[] dato=new double[2];
                    dato[0]=resultadoaux2.get(j-1)[0];
                    dato[1]=resultadoaux2.get(j-1)[1];
                    if (resultadoaux2.get(j-1)[0]<=resultadoaux2.get(j-1)[1]){
                        if (s1[0]<=s1[1]){
                            if (resultadoaux2.get(j-1)[0]>s1[0]){ // Se cambia el limite inferior
                                dato[0]=s1[0];
                            }
                            if (resultadoaux2.get(j-1)[1]<s1[1]){ // Se cambia el limite superior
                                dato[1]=s1[1];
                            }
                        } else{
                            if (((resultadoaux2.get(j-1)[0]>s1[0]) && (s1[0]>=0))|| (resultadoaux2.get(j-1)[1]<s1[0]) && (s1[0]<=360)){ // Se cambia el limite inferior
                                dato[0]=s1[0];
                            }
                            if (((resultadoaux2.get(j-1)[0]>s1[1]) && (s1[1]>=0))|| (resultadoaux2.get(j-1)[1]<s1[1]) && (s1[1]<=360)){ // Se cambia el limite superior
                                dato[1]=s1[1];
                            }
                            if ((resultadoaux2.get(j-1)[0]<=s1[0]) && (resultadoaux2.get(j-1)[1]>=s1[0])&& (resultadoaux2.get(j-1)[1]>=s1[1])&& (resultadoaux2.get(j-1)[0]<=s1[1])){
                                dato[0]=0;
                                dato[1]=360;
                            }
                        }   
                    } else{
                          
                            if ((resultadoaux2.get(j-1)[0]>s1[0]) && (resultadoaux2.get(j-1)[1]<s1[0])){ // Se cambia el limite inferior
                                dato[0]=s1[0];
                            } 
                            if ((resultadoaux2.get(j-1)[1]<s1[1]) && (resultadoaux2.get(j-1)[0]>s1[1])){ // Se cambia el limite superior
                                dato[1]=s1[1];
                            }
                            // 1 
                            /*if ((resultadoaux2.get(j-1)[1]>s1[0]) && (resultadoaux2.get(j-1)[0]<s1[1])){ // Se cambia el limite inferior
                                System.out.println("1");
                                dato[0]=0;
                                dato[1]=360;
                            } 
                            if ((resultadoaux2.get(j-1)[1]>s1[0]) && (resultadoaux2.get(j-1)[1]>s1[1])){ // Se cambia el limite inferior
                                System.out.println("2");
                                dato[0]=0;
                                dato[1]=360;
                            } 
                            if ((resultadoaux2.get(j-1)[0]<s1[0]) && (resultadoaux2.get(j-1)[0]<s1[1])){ // Se cambia el limite inferior
                                System.out.println("3");
                                dato[0]=0;
                                dato[1]=360;
                            } */
                            if ((resultadoaux2.get(j-1)[0]<=s1[0]) && (resultadoaux2.get(j-1)[1]>=s1[0])&& (resultadoaux2.get(j-1)[1]>=s1[1])&& (resultadoaux2.get(j-1)[0]<=s1[1])){
                                dato[0]=0;
                                dato[1]=360;
                    }
                            
                           
                           
                            
                     
                    }
                    System.out.println("Se modifica resultadoaux2 sector  ["+resultadoaux2.get(j-1)[0]+","+resultadoaux2.get(j-1)[1]+"] por  ["+dato[0]+","+dato[1]+"] ");
                    resultadoaux2.set(j-1,dato);
                }
            }
            
            ant=resultadoaux.size();
            resultadoaux=new ArrayList<double[]>();
            for (int i=0;i<resultadoaux2.size();i++){
                double[] dato=new double[2];
                dato[0]=resultadoaux2.get(i)[0];
                dato[1]=resultadoaux2.get(i)[1];
                resultadoaux.add(i, dato);
            }
           
            nuevo=resultadoaux2.size();
            
        }
       /*  String[] dato=new String[2];
        for (int i=0;i<resultadoaux.size()-1;i++){
           
            dato[0]=String.valueOf(resultadoaux.get(i)[1]);
            dato[1]=String.valueOf(resultadoaux.get(i+1)[0]);
            sv.add(i, dato);
        } 
        dato[0]= String.valueOf(resultadoaux.get(resultadoaux.size()-1)[1]);  
        dato[1]= String.valueOf(resultadoaux.get(0)[0]);   
        sv.add(resultadoaux.size()-1,dato);*/
        System.out.println("Sector valido final");
        
            for (int i=0;i<resultadoaux.size();i++){
                String[] dato=new String[2];
                dato[0]=String.valueOf(resultadoaux.get(i)[0]);
                dato[1]=String.valueOf(resultadoaux.get(i)[1]);
                System.out.println(""+i+" "+dato[0]+" "+dato[1]);
                sv.add(i,dato);
            }
        
        return sv;
    }

//--------------------------------------------------------------------------------------------//      

    // Devuelve la malla al generar el analisis del terreno (A1(x,y,z),A2(x,y,z),A3(x,y,z),A4(x,y,z))
    public ArrayList<ArrayList <double []>> getMalla(ArrayList<double[]> grid,ArrayList<double[]> sectores,double[]ag,double L) {
        
        ArrayList <ArrayList <double []>> malla= new ArrayList <ArrayList<double []>>();
        
        ArrayList <double []> area1= new ArrayList <double []>();
        ArrayList <double []> area2= new ArrayList <double []>();
        ArrayList <double []> area3= new ArrayList <double []>();
        ArrayList <double []> area4= new ArrayList <double []>();
           
        double x0=ag[0];
        double y0=ag[1];
        double z0=ag[2];
        
        double[] dato=new double[3];   
        int n=grid.size();
        
        for (int i=0;i<n;i++){
            
            dato=new double[3];
                       
            // Se calcula el angulo
            double x=grid.get(i)[0];
            double y=grid.get(i)[1];
            double z=grid.get(i)[2];
            
            // Se trasladadn las coordenadas
            dato[0]=x-x0;
            dato[1]=y-y0;
            dato[2]=z-z0;
                      
            double distancia=java.lang.Math.sqrt(java.lang.Math.pow(x-x0, 2)+java.lang.Math.pow(y-y0, 2));
            double angulo=java.lang.Math.atan((y-y0)/(x-x0))*180/java.lang.Math.PI;
            
            if ((x-x0)!=0){
                angulo=java.lang.Math.atan((y-y0)/(x-x0))*180/java.lang.Math.PI;
            } else{
                angulo=90 * ((y - y0) / java.lang.Math.abs(y - y0));
            }
            if ((y-y0)!=0){
                if (((x-x0)>0) && ((y-y0)>0)){
                        angulo=java.lang.Math.abs(angulo);
                }
                else if (((x-x0)>0) && ((y-y0)<0)){
                        //angulo=angulo;
                }
                else if (((x-x0)<0) && ((y-y0)<0)){    
                        angulo=angulo-180;
                }   
                else if (((x-x0)<0) && ((y-y0)>0)){ 
                        angulo=angulo+180;
                }      
            } else{
                if ((x-x0)<0){
                   angulo=180;
                } else{
                   angulo=0;
                }
           }
           
           if (angulo<0){angulo=angulo+360;}
           if ((angulo>=0) && (angulo<=90)) {
                    angulo=-angulo+90;
           } else{
                    angulo=-angulo+450;
           }              
           
            // Se comprueba si pertenece al sector
            boolean sector=false;
            int j=0;
            while ((sector==false) && (j<sectores.size())){
                if (sectores.get(j)[0]<=sectores.get(j)[1]){
                    if ((angulo>=sectores.get(j)[0]) && (angulo<=sectores.get(j)[1])){
                        sector=true;
                    }
                } else{
                    if (((angulo>=sectores.get(j)[0]) && (angulo<=360)) ||((angulo>=0) && (angulo<=sectores.get(j)[1]))){
                        sector=true;
                    }
                }
                j=j+1;
            }
            
            // Se chequean las distancias
            if (distancia<2*L){ // Area 1                
                area1.add(dato);
            } else{
                if (distancia<4*L){
                    if (sector){ // Area2
                        area2.add(dato);
                    } else{ // Area3                       
                        area3.add(dato);
                    }
                } else{
                    if ((distancia<8*L) && (sector)){ // Area4                       
                        area4.add(dato);
                    } 
                }
            }
            
        }
        
        malla.add(0, area1);
        malla.add(1, area2);
        malla.add(2, area3);
        malla.add(3, area4);
        return malla;
    }

//--------------------------------------------------------------------------------------------// 

    // Obtienen la malla de resultados de terreno por áreas
    public ArrayList<ArrayList <double []>> getMallaSiemens(ArrayList<double[]> grid,ArrayList<double[]> sectores,double[] coordenadas,double dn) {
        
        ArrayList <ArrayList <double []>> malla= new ArrayList <ArrayList<double []>>();
        
        ArrayList <double []> area1= new ArrayList <double []>();
        ArrayList <double []> area2= new ArrayList <double []>();
        ArrayList <double []> area3= new ArrayList <double []>();
        ArrayList <double []> area4= new ArrayList <double []>();
        ArrayList <double []> area5= new ArrayList <double []>();
       
        double x0=coordenadas[0];
        double y0=coordenadas[1];
        double z0=coordenadas[2];
        
        double[] dato=new double[3];
        int n=grid.size();
        for (int i=0;i<n;i++){
            dato=new double[3];
            
            double x=grid.get(i)[0];
            double y=grid.get(i)[1];
            double z=grid.get(i)[2];
            // Se transladan las coordenadas
            dato[0]=x-x0;
            dato[1]=y-y0;
            dato[2]=z-z0;
            // Se calcula el angulo
            double distancia=java.lang.Math.sqrt(java.lang.Math.pow(x-x0, 2)+java.lang.Math.pow(y-y0, 2));
            double angulo=java.lang.Math.atan((y-y0)/(x-x0))*180/java.lang.Math.PI;
            
            if ((x-x0)!=0){
                angulo=java.lang.Math.atan((y-y0)/(x-x0))*180/java.lang.Math.PI;
            } else{
                angulo=90 * ((y - y0) / java.lang.Math.abs(y - y0));
            }
            if ((y-y0)!=0){
                if (((x-x0)>0) && ((y-y0)>0)){
                        angulo=java.lang.Math.abs(angulo);
                }
                if (((x-x0)>0) && ((y-y0)<0)){
                        angulo=angulo;
                }
                if (((x-x0)<0) && ((y-y0)<0)){    
                        angulo=angulo-180;
                }   
                if (((x-x0)<0) && ((y-y0)>0)){ 
                        angulo=angulo+180;
                }      
            } else{
                if ((x-x0)<0){
                   angulo=180;
                } else{
                   angulo=0;
                }
           }
           
           if (angulo<0){angulo=angulo+360;}
           if ((angulo>=0) && (angulo<=90)) {
                    angulo=-angulo+90;
           } else{
                    angulo=-angulo+450;
           }              
           
           // Se comprueba si pertenece al sector
           boolean sector=false;
           int j=0;
           while ((sector==false) && (j<sectores.size())){
                if (sectores.get(j)[0]<=sectores.get(j)[1]){
                    if ((angulo>=sectores.get(j)[0]) && (angulo<=sectores.get(j)[1])){
                        sector=true;
                    }
                } else{
                    if (((angulo>=sectores.get(j)[0]) && (angulo<=360)) ||((angulo>=0) && (angulo<=sectores.get(j)[1]))){
                        sector=true;
                    }
                }
                j=j+1;
           }
           
           // Se chequean las distancias
           if (distancia<4*dn){ // Area 1
                area1.add(dato);
            } else{
                if (distancia<8*dn){ // Area 2
                    area2.add(dato);
                } else{
                    if (distancia<16*dn){
                        if (sector){ // Area 3
                            area3.add(dato);
                        } else{ // Area4
                            area4.add(dato);
                        }
                    } else{
                        if ((distancia<24*dn) && (sector)){ // Area5
                            area5.add(dato);
                        } 
                    }
                }
            }
        }
        
        malla.add(0, area1);
        malla.add(1, area2);
        malla.add(2, area3);
        malla.add(3, area4);
        malla.add(4, area5);
        return malla;
    }

//--------------------------------------------------------------------------------------------// 
    
// Devuelve los puntos de orografia (pos 0 cumplen, pos 1 no cumplen)
    public ArrayList<ArrayList <double[]>> getPtosOro(ArrayList<ArrayList<double[]>> malla,double[][] planos,double hb, double dn) {
        
        ArrayList<ArrayList <double []>> oro=new ArrayList<ArrayList<double []>>();
        ArrayList <double []> cumple=new ArrayList<double[]>();
        ArrayList <double []> nocumple=new ArrayList<double[]>();
        
        double l1=0.04*(hb+dn);
        double l2=0.08*(hb+dn);
        double l3=0.13*(hb+dn);
        
        System.out.println(l1+" "+l2+" "+l3);
        for (int m=0;m<4;m++){
            if (m!=2){
              
                for (int dato=0;dato<malla.get(m).size();dato++){
                    
                    double x=malla.get(m).get(dato)[0];
                    double y=malla.get(m).get(dato)[1];
                    double z=malla.get(m).get(dato)[2];
                    double[] p=new double[3];
                    
                    p[0]=x; p[1]=y; p[2]=z;
                    // Se calcula la z est
                    double zest=planos[m][1]*y+planos[m][2]*x;
                    double var=z-zest;
                 
                    if (m==0){ // Area1
                        if ((var>l1) || (var<-1*l1)){
                            nocumple.add(p);
                        } else{
                            cumple.add(p);
                        }
                    } else{
                        if (m==1){ // Area2
                            if ((var>l2) || (var<-1*l2)){
                                nocumple.add(p);
                            } else{
                                cumple.add(p);
                            }
                        } else{
                            if (m==3){ // Area4
                                if ((var>l3) || (var<-1*l3)){
                                    nocumple.add(p);
                                } else{
                                    cumple.add(p);
                                }
                            }
                        }
                    }
                } 
           }
        }
        oro.add(0,cumple);
        oro.add(1,nocumple);
        return oro;
    }

//--------------------------------------------------------------------------------------------//    
    
    // Devuelve los puntos de orografia (pos 0 cumplen, pos 1 no cumplen)
    public ArrayList<ArrayList <double[]>> getPtosOroSiemens(ArrayList<ArrayList<double[]>> malla,double[][] planos,double hb, double dn) throws SQLException{
        
        ArrayList<ArrayList <double []>> oro=new ArrayList<ArrayList<double []>>();
        
        ArrayList <double []> cumple=new ArrayList<double[]>();
        ArrayList <double []> nocumple=new ArrayList<double[]>();
        
        double l1=0.08*(hb-dn/2.0);
        double l2=0.16*(hb-dn/2.0);
        double l3=0.32*(hb-dn/2.0);
        double l4=0.52*(hb-dn/2.0);
        System.out.println(l1+" "+l2+" "+l3+" "+l4);
        
        for (int m=0;m<5;m++){
            if (m!=3){
                //System.out.println("Tratamiento del area "+m);
                for (int dato=0;dato<malla.get(m).size();dato++){
                    
                    double x=malla.get(m).get(dato)[0];
                    double y=malla.get(m).get(dato)[1];
                    double z=malla.get(m).get(dato)[2];
                    
                    double[] p=new double[3];
                    p[0]=x; p[1]=y; p[2]=z;
                    
                    // Se calcula la z est
                    double zest=planos[m][1]*y+planos[m][2]*x;
                    double var=z-zest;
                    //System.out.println(x+" "+y+" "+z);
                    // System.out.println("Var "+var);
                    if (m==0){ // Area1
                        if ((var>l1) || (var<-1*l1)){
                            nocumple.add(p);
                        } else{
                            cumple.add(p);
                        }
                    } else{
                        if (m==1){ // Area2
                            if ((var>l2) || (var<-1*l2)){
                                nocumple.add(p);
                            } else{
                                cumple.add(p);
                            }
                        } else{
                            if (m==2){ // Area 3
                                if ((var>l3) || (var<-1*l3)){
                                    nocumple.add(p);
                                } else{
                                    cumple.add(p);
                                }
                            } else{
                                if (m==4){ // Area 5
                                    if ((var>l4) || (var<-1*l4)){
                                        nocumple.add(p);
                                    } else{
                                        cumple.add(p);
                                    }
                                }
                            }
                        }
                    }
                } 
           }
        }
        oro.add(0,cumple);
        oro.add(1,nocumple);
        return oro;
    }

//--------------------------------------------------------------------------------------------//    
    
    // Devuelve los puntos de orografia (pos 0 cumplen, pos 1 no cumplen)
    public Vector<Vector <double[]>> getPtosPen(Vector<Vector<double[]>> malla,double[][] planos,double hb, double dn) throws SQLException{
        Vector<Vector <double []>> oro=new Vector<Vector<double []>>();
        Vector <double []> cumple=new Vector<double[]>();
        Vector <double []> nocumple=new Vector<double[]>();
        for (int m=0;m<4;m++){
            if (m!=2){
                //System.out.println("Tratamiento del area "+m);
                for (int dato=0;dato<malla.get(m).size();dato++){
                    double x=malla.get(m).get(dato)[0];
                    double y=malla.get(m).get(dato)[1];
                    double z=malla.get(m).get(dato)[2];
                    double[] p=new double[3];
                    p[0]=x; p[1]=y; p[2]=z;
                    // Se calcula la pendiente y la distancia
                    double distancia=java.lang.Math.sqrt(java.lang.Math.pow(x, 2)+java.lang.Math.pow(y, 2));
                    double pendiente=100*(z/distancia);
                    
                    if (m==0){ // Area1
                        if ((pendiente>3) || (pendiente<-3)){
                            nocumple.add(p);
                        } else{
                            cumple.add(p);
                        }
                    } else{
                        if (m==1){ // Area2
                            if ((pendiente>5) || (pendiente<-5)){
                                nocumple.add(p);
                            } else{
                                cumple.add(p);
                            }
                        } else{
                            if (m==3){ // Area4
                                if ((pendiente>10) || (pendiente<-1*10)){
                                    nocumple.add(p);
                                } else{
                                    cumple.add(p);
                                }
                            }
                        }
                    }
                } 
           }
        }
        oro.add(0,cumple);
        oro.add(1,nocumple);
        return oro;
    }

//--------------------------------------------------------------------------------------------//    
    
     // Devuelve los puntos de orografia (pos 0 cumplen, pos 1 no cumplen)
    public ArrayList<ArrayList <double[]>> getPtosPenSiemens(ArrayList<ArrayList<double[]>> malla,double[][] planos,double hb, double dn) throws SQLException{
        
        ArrayList<ArrayList <double []>> oro=new ArrayList<ArrayList<double []>>();
        
        ArrayList <double []> cumple=new ArrayList<double[]>();
        ArrayList<double []> nocumple=new ArrayList<double[]>();
        for (int m=0;m<5;m++){
            if (m!=3){
                //System.out.println("Tratamiento del area "+m);
                for (int dato=0;dato<malla.get(m).size();dato++){
                    
                    double x=malla.get(m).get(dato)[0];
                    double y=malla.get(m).get(dato)[1];
                    double z=malla.get(m).get(dato)[2];
                    double[] p=new double[3];
                    p[0]=x; p[1]=y; p[2]=z;
                    
                    // Se calcula la pendiente y la distancia
                    double distancia=java.lang.Math.sqrt(java.lang.Math.pow(x, 2)+java.lang.Math.pow(y, 2));
                    double pendiente=100*(z/distancia);
                    
                    if (m==0){ // Area1
                        if ((pendiente>3) || (pendiente<-3)){
                            nocumple.add(p);
                        } else{
                            cumple.add(p);
                        }
                    } else{
                        if (m==1){ // Area2
                            if ((pendiente>3) || (pendiente<-3)){
                                nocumple.add(p);
                            } else{
                                cumple.add(p);
                            }
                        } else{
                            if (m==2){ // Area3
                                if ((pendiente>5) || (pendiente<-5)){
                                    nocumple.add(p);
                                } else{
                                    cumple.add(p);
                                }
                            } else{
                                if (m==4){ // Area5
                                    if ((pendiente>10) || (pendiente<-1*10)){
                                        nocumple.add(p);
                                    } else{
                                        cumple.add(p);
                                    }
                                }
                            }            
                        }
                    }
                } 
           }
        }
        oro.add(0,cumple);
        oro.add(1,nocumple);
        return oro;
    }

//--------------------------------------------------------------------------------------------//    
    
    // Devuelve las variaciones del terreno
    public double[] getVariaciones(ArrayList<ArrayList<double[]>> malla,double[][] planos) {
        
        double [] variaciones=new double [4];
        for (int i=0;i<4;i++){variaciones[i]=0;}
        for (int m=0;m<4;m++){
            if (m!=2){
                for (int dato=0;dato<malla.get(m).size();dato++){
                    double x=malla.get(m).get(dato)[0];
                    double y=malla.get(m).get(dato)[1];
                    double z=malla.get(m).get(dato)[2];
                    // Se calcula la z est
                    double zest=planos[m][1]*y+planos[m][2]*x;
                    double var=z-zest;
                    if (java.lang.Math.abs(var)>variaciones[m]) {
                       variaciones[m]=java.lang.Math.abs(var);
                    }    
                } 
           }
        }
        return variaciones;
    }

//--------------------------------------------------------------------------------------------//
    
    // Devuelve las variaciones del terreno
    public double[] getVariacionesSiemens(ArrayList<ArrayList<double[]>> malla,double[][] planos) throws SQLException{
        double [] variaciones=new double [5];
        for (int i=0;i<5;i++){variaciones[i]=0;}
        for (int m=0;m<5;m++){
            if (m!=3){
                for (int dato=0;dato<malla.get(m).size();dato++){
                    double x=malla.get(m).get(dato)[0];
                    double y=malla.get(m).get(dato)[1];
                    double z=malla.get(m).get(dato)[2];
                    // Se calcula la z est
                    double zest=planos[m][1]*y+planos[m][2]*x;
                    double var=z-zest;
                    if (java.lang.Math.abs(var)>variaciones[m]) {
                       variaciones[m]=java.lang.Math.abs(var);
                    }    
                } 
           }
        }
        return variaciones;
    }

//--------------------------------------------------------------------------------------------//
    
    // Devuelve las pendientes del terreno por area
    public double[] getPendientes(ArrayList<ArrayList<double[]>> malla,double[][] planos){
        
        double [] pendientes=new double [4];
        for (int m=0;m<4;m++){
            if (m!=2){
                pendientes[m]=java.lang.Math.abs(100*((java.lang.Math.pow(planos[m][2],2)+java.lang.Math.pow(planos[m][1],2))/planos[m][1])/java.lang.Math.sqrt(1+java.lang.Math.pow(planos[m][2], 2)/java.lang.Math.pow(planos[m][1], 2)));
            } else{
                 pendientes[m]=0;
                 for (int dato=0;dato<malla.get(m).size();dato++){
                    
                    double x=malla.get(m).get(dato)[0];
                    double y=malla.get(m).get(dato)[1];
                    double z=malla.get(m).get(dato)[2];
                    
                    // Se calcula la d
                    double d=java.lang.Math.sqrt(java.lang.Math.pow(x, 2)+java.lang.Math.pow(y, 2));
                    double p=z/d;
                   
                    if (java.lang.Math.abs(p)>pendientes[m]) {
                        pendientes[m]=java.lang.Math.abs(p);
                    }    
                }
                pendientes[m]=100*pendientes[m]; 
           }
        }
        return pendientes;
    }

//--------------------------------------------------------------------------------------------//    
 
    // Devuelve las pendientes del terreno por area
    public double[] getPendientesSiemens(ArrayList<ArrayList<double[]>> malla,double[][] planos) throws SQLException{
        
        double [] pendientes=new double [5];
        for (int m=0;m<5;m++){
            if (m!=3){
                pendientes[m]=java.lang.Math.abs(100*((java.lang.Math.pow(planos[m][2],2)+java.lang.Math.pow(planos[m][1],2))/planos[m][1])/java.lang.Math.sqrt(1+java.lang.Math.pow(planos[m][2], 2)/java.lang.Math.pow(planos[m][1], 2)));
            } else{
                 pendientes[m]=0;
                 for (int dato=0;dato<malla.get(m).size();dato++){
                    double x=malla.get(m).get(dato)[0];
                    double y=malla.get(m).get(dato)[1];
                    double z=malla.get(m).get(dato)[2];
                    
                    // Se calcula la d
                    double d=java.lang.Math.sqrt(java.lang.Math.pow(x, 2)+java.lang.Math.pow(y, 2));
                    double p=z/d;
                  
                    if (java.lang.Math.abs(p)>pendientes[m]) {
                       pendientes[m]=java.lang.Math.abs(p);
                    }    
                }
                pendientes[m]=100*pendientes[m]; 
           }
        }
        return pendientes;
    }

//--------------------------------------------------------------------------------------------//    
    
     public void Update_SV(int asunto,boolean[] editar,ArrayList<double[]> sector_valido,ArrayList<Double[]> factores)throws SQLException{
        
        Connection con=null;
              
        try{
            con = DriverManager.getConnection(url,user,pass); 
                        
            con.setAutoCommit(false);
            
            // Se modifica sector_valido
            if (editar[0]){
                // Se borra el sector_valido
                String sql_1="Delete from Sector_Valido where Asunto=?";
                PreparedStatement ps=con.prepareStatement(sql_1);
                ps.setInt(1, asunto);
                System.out.println("Delete from Sector_Valido where Asunto="+asunto);
                ps.execute();
                // Se inserta el nuevo
                String sql_2="Insert into Sector_Valido values (?,?,?,?)";
                PreparedStatement ps2=con.prepareStatement(sql_2);
                ps2.setInt(1, asunto);
                for (int i=0;i<sector_valido.size();i++){
                    ps2.setInt(2, (i+1));
                    ps2.setDouble(3, sector_valido.get(i)[0]);
                    ps2.setDouble(4, sector_valido.get(i)[1]);
                    System.out.println("Insert into Sector_Valido values ("+asunto+","+(i+1)+","+sector_valido.get(i)[0]+","+sector_valido.get(i)[1]+")");
                    ps2.execute();
                }
                ps.close();
                ps2.close();
            }
            
            // Se modifican los datosSC
            if (editar[1]){
                // Se modifican los datos a no_validos
                String sql_3="Update DatosSC set sv=0 where Asunto=?";
                PreparedStatement ps3=con.prepareStatement(sql_3);
                ps3.setInt(1, asunto);
                System.out.println("Update DatosSC set sv=0 where Asunto="+asunto);
                ps3.execute();
                int n=sector_valido.size();
                // Se modifican ls datos con el nuevo sector_valido
                String sql_4="Update DatosSC set sv=1 where Asunto=? and (";
                for (int i=0;i<n;i++){ 
                   double li=sector_valido.get(i)[0];
                   double ls=sector_valido.get(i)[1];
                   if (li>ls){ // Paso por cero
                       sql_4=sql_4+" ((med9>="+li+") and (med9<=360)) or ((med9>=0) and (med9<"+ls+"))";
                   }  else { // No pasa por cero
                        sql_4=sql_4+" ((med9>="+li+") and  (med9<"+ls+"))";
                   }
                   if (i!=sector_valido.size()-1){
                    sql_4=sql_4+" or ";
                   }
                }
                sql_4=sql_4+")";
                PreparedStatement ps4=con.prepareStatement(sql_4);
                ps4.setInt(1, asunto);
                System.out.println(sql_4);
                ps4.execute();
                ps3.close();
                ps4.close();
            }
            
            // Se modifican los factores
            if (editar[2]){
                // Se borran los factores
                String sql_5="Delete from Factor where Asunto=?";
                PreparedStatement ps5=con.prepareStatement(sql_5);
                ps5.setInt(1, asunto);
                System.out.println("Delete from Factor where Asunto="+asunto);
                ps5.execute();
                int n=factores.size();
                // Se insertan los nuevos factores
                String sql_6="Insert into Factor(Asunto,Nfactor,Linf,Lsup,Factor,U) values (?,?,?,?,?,?)";
                PreparedStatement ps6=con.prepareStatement(sql_6);
                ps6.setInt(1, asunto);
                for (int i=0;i<n;i++){
                    ps6.setInt(2, (i+1));
                    ps6.setDouble(3, factores.get(i)[0]);
                    ps6.setDouble(4, factores.get(i)[1]);
                    ps6.setDouble(5, factores.get(i)[2]);
                    ps6.setDouble(6, factores.get(i)[3]);
                    
                    System.out.println("Insert into Factor(Asunto,Nfactor,Linf,Lsup,Factor,U) values ("+asunto+","+(i+1)+","+factores.get(i)[0]+","+factores.get(i)[1]+","+factores.get(i)[2]+","+factores.get(i)[3]+")");
                    ps6.execute();
                }
                ps5.close();
                ps6.close();
            }
            
            // Se modifican los datosPC
            if (editar[3]){
                // Se modifican los datos a no_validos
                String sql_7="Update DatosPC set sv=0 where Asunto=?";
                PreparedStatement ps7=con.prepareStatement(sql_7);
                ps7.setInt(1, asunto);
                System.out.println("Update DatosPC set sv=0 where Asunto="+asunto);
                ps7.execute();
                int n=factores.size();
                // Se modifican ls datos con el nuevo sector_valido
                String sql_8="Update DatosPC set sv=1 where Asunto=? and (";
                for (int i=0;i<n;i++){ 
                   double li=factores.get(i)[0];
                   double ls=factores.get(i)[1];
                   if (li>ls){ // Paso por cero
                       sql_8=sql_8+" ((med4>="+li+") and (med4<=360)) or ((med4>=0) and (med4<"+ls+"))";
                   }  else { // No pasa por cero
                        sql_8=sql_8+" ((med4>="+li+") and  (med4<"+ls+"))";
                   }
                   if (i!=factores.size()-1){
                    sql_8=sql_8+" or ";
                   }
                }
                sql_8=sql_8+")";
                PreparedStatement ps8=con.prepareStatement(sql_8);
                ps8.setInt(1, asunto);
                System.out.println(sql_8);
                ps8.execute();
                ps7.close();
                ps8.close();
            }
            
            con.commit();
            con.close();
        } catch (SQLException e){
            e.printStackTrace();
            throw e;
        } 
         finally{
            if (con!=null){con.close();}
        }

    }

//--------------------------------------------------------------------------------------------//    
   
     // Modifica el sector valido y los fatores de un ensayo
     public void Update_SVA(int asunto,boolean[] editar,ArrayList<double[]> sector_valido,ArrayList<Double[]> factores)throws SQLException{
        
        Connection con=null;
              
        try{
            con = DriverManager.getConnection(url,user,pass); 
                        
            con.setAutoCommit(false);
            
            // Se modifica sector_valido
            if (editar[0]){
                // Se borra el sector_valido
                String sql_1="Delete from Sector_Valido where Asunto=?";
                PreparedStatement ps=con.prepareStatement(sql_1);
                ps.setInt(1, asunto);
                System.out.println("Delete from Sector_Valido where Asunto="+asunto);
                ps.execute();
                // Se inserta el nuevo
                String sql_2="Insert into Sector_Valido values (?,?,?,?)";
                PreparedStatement ps2=con.prepareStatement(sql_2);
                ps2.setInt(1, asunto);
                for (int i=0;i<sector_valido.size();i++){
                    ps2.setInt(2, (i+1));
                    ps2.setDouble(3, sector_valido.get(i)[0]);
                    ps2.setDouble(4, sector_valido.get(i)[1]);
                    System.out.println("Insert into Sector_Valido values ("+asunto+","+(i+1)+","+sector_valido.get(i)[0]+","+sector_valido.get(i)[1]+")");
                    ps2.execute();
                }
                ps.close();
                ps2.close();
            }
            
            // Se modifican los datosSC
            if (editar[1]){
                // Se modifican los datos a no_validos
                String sql_3="Update DatosSC"+asunto+" set sv=0 where Asunto=?";
                PreparedStatement ps3=con.prepareStatement(sql_3);
                ps3.setInt(1, asunto);
                System.out.println("Update DatosSC"+asunto+" set sv=0 where Asunto="+asunto);
                ps3.execute();
                int n=sector_valido.size();
                // Se modifican los datos con el nuevo sector_valido
                String sql_4="Update DatosSC"+asunto+" set sv=1 where Asunto=? and nulo=0 and (";
                for (int i=0;i<n;i++){ 
                   double li=sector_valido.get(i)[0];
                   double ls=sector_valido.get(i)[1];
                   if (li>ls){ // Paso por cero
                       sql_4=sql_4+" ((D1TRav>="+li+") and (D1TRav<=360)) or ((D1TRav>=0) and (D1TRav<"+ls+"))";
                   }  else { // No pasa por cero
                        sql_4=sql_4+" ((D1TRav>="+li+") and  (D1TRav<"+ls+"))";
                   }
                   if (i!=sector_valido.size()-1){
                    sql_4=sql_4+" or ";
                   }
                }
                sql_4=sql_4+")";
                PreparedStatement ps4=con.prepareStatement(sql_4);
                ps4.setInt(1, asunto);
                System.out.println(sql_4);
                ps4.execute();
                ps3.close();
                ps4.close();
            }
            
            // Se modifican los factores
            if (editar[2]){
                // Se borran los factores
                String sql_5="Delete from Factor where Asunto=?";
                PreparedStatement ps5=con.prepareStatement(sql_5);
                ps5.setInt(1, asunto);
                System.out.println("Delete from Factor where Asunto="+asunto);
                ps5.execute();
                int n=factores.size();
                // Se insertan los nuevos factores
                String sql_6="Insert into Factor(Asunto,Nfactor,Linf,Lsup,Factor,U,Slope,Offset,R2,Ur) values (?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement ps6=con.prepareStatement(sql_6);
                ps6.setInt(1, asunto);
                for (int i=0;i<n;i++){
                    ps6.setInt(2, (i+1));
                    ps6.setDouble(3, factores.get(i)[0]);
                    ps6.setDouble(4, factores.get(i)[1]);
                    for(int j=0;j<6;j++){
                        if (factores.get(i)[j+2]!=null){
                            ps6.setDouble(j+5, factores.get(i)[j+2]);
                        } else{
                            ps6.setNull(j+5, java.sql.Types.DOUBLE);
                        }
                    }
                   
                    System.out.println("Insert into Factor(Asunto,Nfactor,Linf,Lsup,Factor,U) values ("+asunto+","+(i+1)+","+factores.get(i)[0]+","+factores.get(i)[1]+","+factores.get(i)[2]+","+factores.get(i)[3]+","+factores.get(i)[4]+","+factores.get(i)[5]+","+factores.get(i)[6]+","+factores.get(i)[7]+")");
                    ps6.execute();
                }
                ps5.close();
                ps6.close();
            }
            
            // Se modifican los datosPC
            if (editar[3]){
                // Se modifican los datos a no_validos
                String sql_7="Update DatosPC"+asunto+" set sv=0 where Asunto=?";
                PreparedStatement ps7=con.prepareStatement(sql_7);
                ps7.setInt(1, asunto);
                System.out.println("Update DatosPC"+asunto+" set sv=0 where Asunto="+asunto);
                ps7.execute();
                int n=factores.size();
                // Se modifican ls datos con el nuevo sector_valido
                String sql_8="Update DatosPC"+asunto+" set sv=1 where Asunto=? and nulo=0 and (";
                for (int i=0;i<n;i++){ 
                   double li=factores.get(i)[0];
                   double ls=factores.get(i)[1];
                   if (li>ls){ // Paso por cero
                       sql_8=sql_8+" ((D1av>="+li+") and (D1av<=360)) or ((D1av>=0) and (D1av<"+ls+"))";
                   }  else { // No pasa por cero
                        sql_8=sql_8+" ((D1av>="+li+") and  (D1av<"+ls+"))";
                   }
                   if (i!=factores.size()-1){
                    sql_8=sql_8+" or ";
                   }
                }
                sql_8=sql_8+")";
                PreparedStatement ps8=con.prepareStatement(sql_8);
                ps8.setInt(1, asunto);
                System.out.println(sql_8);
                ps8.execute();
                ps7.close();
                ps8.close();
            }
            
            con.commit();
            con.close();
        } catch (SQLException e){
            e.printStackTrace();
            throw e;
        } 
        finally{
            if (con!=null){con.close();}
        }

    }

//--------------------------------------------------------------------------------------------//    
     
    // Devuelve un vector con los bines resultantes dado un centro y longitud
    public ArrayList<double []> getBinesA(double centro,double longitud){
        ArrayList <double []> bines=new ArrayList <double []>();
        int n=(int)(360/longitud);
        System.out.println("bines");
        for (int i=0;i<n;i++){
            double c=centro+i*longitud;
            double[] dato=new double[2];
            dato[0]=c-longitud/2;
            dato[1]=c+longitud/2;
            for (int j=0;j<2;j++){
                if (dato[j]<0){dato[j]=dato[j]+360;}
                if (dato[j]>360){dato[j]=dato[j]-360;}
            }
            if (dato[0]==360){dato[0]=0;}
            if (dato[1]==0){dato[1]=360;}
            System.out.println(dato[0]+" "+dato[1]);
            bines.add(dato);
        }
        return bines;
    }
       
//--------------------------------------------------------------------------------------------//    
    
}
