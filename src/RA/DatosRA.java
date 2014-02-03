package RA;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/*
 * @author Ruth_Cordon
 */
import java.util.Date;


public class DatosRA {
    
     private static String url = "jdbc:sqlserver://" + Global.IP_SERVER_GCP;
    //private static String url = "jdbc:sqlserver://localhost";
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
    
    // Devuelve la primera fecha introducida en tabla de un asunto. Si no hay, devuelve null
    public String getFprimera(int asunto,String tabla)throws SQLException{
        Connection con = null;
        String Fprimera=null;
        try{            
            con = DriverManager.getConnection(url, user, pass);
            PreparedStatement ps = con.prepareStatement("IF EXISTS (SELECT 1 FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME='" + tabla + asunto + "') Select Fecha_Hora from " + tabla + asunto +" where Id=1 ELSE SELECT * FROM Asunto WHERE 1=0");   
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Fprimera=rs.getString(1);
                if (rs.wasNull()){
                    Fprimera=null;
                }
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (con != null)
                con.close();
        }
        return (Fprimera);
    }
    
//--------------------------------------------------------------------------------------------//
    
    // Devuelve la última fecha introducida en tabla de un asunto. Si no hay, devuelva null
    public String getFultima(int asunto,String tabla)throws SQLException{
        Connection con = null;
        String Fultima=null;
        try{            
            con = DriverManager.getConnection(url, user, pass);
            PreparedStatement ps = con.prepareStatement("IF EXISTS (SELECT 1 FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME='" + tabla + asunto + "') Select Fecha_Hora from " + tabla + asunto +" where Id=(Select max(Id) from " + tabla + asunto + ") ELSE SELECT * FROM Asunto WHERE 1=0");   
            ResultSet rs=ps.executeQuery();
            if (rs.next()) {
                Fultima=rs.getString(1);
                if (rs.wasNull()){
                    Fultima=null;
                }
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e) {
                e.printStackTrace();
                throw e;
        } finally {
            if (con!=null)
                con.close();
        }
        return (Fultima);
    }

//--------------------------------------------------------------------------------------------//        
   
    // Devuelve el Id de una fecha de SPL
    public int getId(int asunto,String fecha_hora,String tabla) throws SQLException{
        Connection con = null;
        int id=0;
        try {
            con = DriverManager.getConnection(url,user,pass); 
          
            String sql="Select min(Id) from "+tabla+asunto+" where Fecha_hora like ?";
            PreparedStatement ps = con.prepareStatement(sql);  
            ps.setString(1, fecha_hora); 
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
             if (con!=null) con.close();
       }
       return id;   
    }
    
//--------------------------------------------------------------------------------------------//     
    
    // Devuelve el Id de una fecha de SPL
    public int getId(Connection con,int asunto,String fecha_hora,String tabla) throws SQLException{
        
        int id=0;
        try {
           
            String sql="Select min(Id) from "+tabla+asunto+" where Fecha_hora like ?";
            PreparedStatement ps = con.prepareStatement(sql);  
            ps.setString(1, fecha_hora); 
            ResultSet rs=ps.executeQuery(); 
           
            if (rs.next()){
               id=rs.getInt(1);
            }
            rs.close();
            ps.close();
  
       }catch(SQLException e){
             e.printStackTrace();
             throw e;
       }
       return id;   
    }
    
//--------------------------------------------------------------------------------------------//     
    
    // Devuelve el total de datos de una matriz de resultados
    public int getTotal(ArrayList <double[]> resultados) throws SQLException{
        
       int total=0;
       int n=resultados.size();
       for (int i=0;i<n;i++){
            total=total+(int)resultados.get(i)[1];
       }
       return total;   
    }
    
//--------------------------------------------------------------------------------------------//     
    
    // Devuelve una matriz con los datos de la tabla de un asunto entre inicio y fin 
    public ArrayList <String[]> getDatos(int asunto,int inicio, int fin,ArrayList<String> codigos,String tabla) throws SQLException{
        
        Connection con = null;
        ArrayList<String[]> datos=new ArrayList <String []>(); // Matriz con datos Brutos
        
        try {
            con = DriverManager.getConnection(url,user,pass); 
            int nequipos=codigos.size();
            String sql="Select Id,Fecha_Hora,valido,RF";
            for (int j=0;j<nequipos;j++){ 
                sql=sql+","+codigos.get(j);
            }

            sql=sql+" from "+tabla+asunto+" where Id>=? and Id<=?";
            System.out.println(sql);
            PreparedStatement ps = con.prepareStatement(sql);  
            ps.setInt(1, inicio);  ps.setInt(2, fin); 
            String[] linea=new String[nequipos+4];
            ResultSet rs=ps.executeQuery(); 
           
            while (rs.next()){
                linea=new String[nequipos+4];
                // Identificadores
                linea [0]=String.valueOf(rs.getInt(1)); // Id
                linea [1]=rs.getString(2); // Fecha Hora         
                linea [2]=""+rs.getInt(3); // valido
                linea [3]=""+rs.getInt(4); // RF
              
                // Equipos
                for (int j=0;j<nequipos;j++){ 
                    linea [j+4]=""+rs.getDouble(j+5);   
                }
              
                datos.add(linea);  
                
                String cadena="";
                for(int h=0;h<(nequipos+4);h++){
                    cadena=cadena+" "+linea[h];
                }
                System.out.println(cadena);
            }
            rs.close();
            ps.close();
            con.close();
           
       }catch(SQLException e){
             e.printStackTrace();
             throw e;
       }finally{
            if (con!=null) con.close();
       }
       return datos;
    }
    
//--------------------------------------------------------------------------------------------//     
    
    // Crea la base Auxiliar de RA utilizada para el cálculo de la k
    public void getBaseAux(Connection con,String tabla,int asunto,ArrayList<String> codigos,int inicio, int fin,double Vin,double Vcut,double[] sector,double Zref,double z0ref,double z0,double hb,int lineas,int regulacion,ArrayList<double[]> cotas,ArrayList<double[]> regresion_cp) throws SQLException{
        
        try {
                       
            int n=regresion_cp.size();
                      
            // Creamos el sql
            int nequipos=codigos.size();
            
            String constante1="1013*(Tbuje+273)/(Pbuje*288)";
            ArrayList<String> constante2=new ArrayList<String>();
            for (int i=0;i<cotas.size();i++){
                constante2.add("((LOG("+Zref+"/"+z0ref+")*LOG("+hb+"/"+z0+"))/(LOG("+hb+"/"+z0ref+")*LOG("+cotas.get(i)[2]+"/"+z0+")))");
            }
            String constante3="((LOG("+Zref+"/"+z0ref+")*LOG("+hb+"/"+z0+"))/(LOG("+hb+"/"+z0ref+")*LOG("+hb+"/"+z0+")))";
            
            String sql="Create view BaseAux"+asunto+" as Select Id,Fecha_Hora,valido,RF";
            for (int j=0;j<nequipos;j++){ 
                sql=sql+","+codigos.get(j);
            }
            sql=sql+", Tbuje, Pbuje,";
            String Pneta="";
            if (lineas==1){
                Pneta="PA";
            } else{
                Pneta="(PAR+PAE)";
            }
            sql=sql+" "+Pneta+" as Pneta,";             
            for (int i=0;i<cotas.size();i++){
                if (i==0){ // Primera
                    sql=sql+" case ";
                }
                sql=sql+" WHEN Id>="+cotas.get(i)[0]+" and Id<="+cotas.get(i)[1]+" THEN VZ*"+constante2.get(i);
                if (i==cotas.size()-1){ // Ultima
                    sql=sql+" else 8888.88 end as VZref,";
                }
            }
            
            if (regulacion==1){
                
                // Pn
                String Pn=Pneta;
                sql=sql+" "+Pn+" as Pn,";  
                
                // VD
                String VD=Pn+"*"+regresion_cp.get(0)[2]+"+"+regresion_cp.get(0)[3];
                sql=sql+" case WHEN "+Pn+"<"+regresion_cp.get(1)[1]+" THEN "+VD;
                for (int i=1;i<n-2;i++){
                    VD=Pn+"*"+regresion_cp.get(i)[2]+"+"+regresion_cp.get(i)[3];
                    sql=sql+" WHEN ("+Pn+">="+regresion_cp.get(i)[1]+" and "+Pn+"<"+regresion_cp.get(i+1)[1]+") THEN "+VD;    
                }
                VD=Pn+"*"+regresion_cp.get(n-2)[2]+"+"+regresion_cp.get(n-2)[3];
                sql=sql+" else "+VD;
                sql=sql+" end as VD,";
                
                // VH
                String VH="("+Pn+"*"+regresion_cp.get(0)[2]+"+"+regresion_cp.get(0)[3]+")*Power("+constante1+",0.333333333333333)";
                sql=sql+" case WHEN "+Pn+"<"+regresion_cp.get(1)[1]+" THEN "+VH;
                for (int i=1;i<n-2;i++){
                    VH="("+Pn+"*"+regresion_cp.get(i)[2]+"+"+regresion_cp.get(i)[3]+")*Power("+constante1+",0.333333333333333)";
                    sql=sql+" WHEN ("+Pn+">="+regresion_cp.get(i)[1]+" and "+Pn+"<"+regresion_cp.get(i+1)[1]+") THEN "+VH;    
                }
                VH="("+Pn+"*"+regresion_cp.get(n-2)[2]+"+"+regresion_cp.get(n-2)[3]+")*Power("+constante1+",0.333333333333333)";
                sql=sql+" else "+VH;
                sql=sql+" end as VH,";
                
                // VS
                String VS="(("+Pn+"*"+regresion_cp.get(0)[2]+"+"+regresion_cp.get(0)[3]+")*Power("+constante1+",0.333333333333333))*"+constante3;
                sql=sql+" case WHEN "+Pn+"<"+regresion_cp.get(1)[1]+" THEN "+VS;
                for (int i=1;i<n-2;i++){
                    VS="(("+Pn+"*"+regresion_cp.get(i)[2]+"+"+regresion_cp.get(i)[3]+")*Power("+constante1+",0.333333333333333))*"+constante3;
                    sql=sql+" WHEN ("+Pn+">="+regresion_cp.get(i)[1]+" and "+Pn+"<"+regresion_cp.get(i+1)[1]+") THEN "+VS;    
                }
                VS="(("+Pn+"*"+regresion_cp.get(n-2)[2]+"+"+regresion_cp.get(n-2)[3]+")*Power("+constante1+",0.333333333333333))*"+constante3;
                sql=sql+" else "+VS;
                sql=sql+" end as VS";
                
            } else{
                
                // Pn
                String Pn=Pneta+"*"+constante1;
                sql=sql+" "+Pn+" as Pn,";  
                
                // VD
                String VD=Pn+"*"+regresion_cp.get(0)[2]+"+"+regresion_cp.get(0)[3];
                sql=sql+" case WHEN "+Pn+"<"+regresion_cp.get(1)[1]+" THEN "+VD;
                for (int i=1;i<n-2;i++){
                    VD=Pn+"*"+regresion_cp.get(i)[2]+"+"+regresion_cp.get(i)[3];
                    sql=sql+" WHEN ("+Pn+">="+regresion_cp.get(i)[1]+" and "+Pn+"<"+regresion_cp.get(i+1)[1]+") THEN "+VD;    
                }
                VD=Pn+"*"+regresion_cp.get(n-2)[2]+"+"+regresion_cp.get(n-2)[3];
                sql=sql+" else "+VD;
                sql=sql+" end as VD,";
                
                // VH
                String VH=Pn+"*"+regresion_cp.get(0)[2]+"+"+regresion_cp.get(0)[3];
                sql=sql+" case WHEN "+Pn+"<"+regresion_cp.get(1)[1]+" THEN "+VH;
                for (int i=1;i<n-2;i++){
                    VH=Pn+"*"+regresion_cp.get(i)[2]+"+"+regresion_cp.get(i)[3];
                    sql=sql+" WHEN ("+Pn+">="+regresion_cp.get(i)[1]+" and "+Pn+"<"+regresion_cp.get(i+1)[1]+") THEN "+VH;    
                }
                VH=Pn+"*"+regresion_cp.get(n-2)[2]+"+"+regresion_cp.get(n-2)[3];
                sql=sql+" else "+VH;
                sql=sql+" end as VH,";
                
                // VS
                String VS=Pn+"*"+regresion_cp.get(0)[2]+"+"+regresion_cp.get(0)[3]+"*"+constante3;
                sql=sql+" case WHEN "+Pn+"<"+regresion_cp.get(1)[1]+" THEN "+VS;
                for (int i=1;i<n-2;i++){
                    VS=Pn+"*"+regresion_cp.get(i)[2]+"+"+regresion_cp.get(i)[3]+"*"+constante3;
                    sql=sql+" WHEN ("+Pn+">="+regresion_cp.get(i)[1]+" and "+Pn+"<"+regresion_cp.get(i+1)[1]+") THEN "+VS;    
                }
                VS=Pn+"*"+regresion_cp.get(n-2)[2]+"+"+regresion_cp.get(n-2)[3]+"*"+constante3;
                sql=sql+" else "+VS;
                sql=sql+" end as VS";
                
            }           
            
            sql=sql+" from "+tabla+asunto; 
            sql=sql+" where Id>="+inicio+" and Id<="+fin;
            sql=sql+" and valido=1";
            
            if (sector[0]<=sector[1]){
                sql=sql+" and (D>="+sector[0]+" AND D<"+sector[1]+")";
            } else{
                sql=sql+" and ((D>="+sector[0]+" AND D<360) OR (D>=0 AND D<"+sector[1]+"))";
            }         
            System.out.println(sql);
            Statement stmt = con.createStatement();
            stmt.execute(sql);
            
            stmt.close();
           
       }catch(SQLException e){
             e.printStackTrace();
             throw e;
       }

    }
    
//--------------------------------------------------------------------------------------------//     
    
   //  Devuelve una matriz con las rectas entre puntos consecutivos de una curva
    public ArrayList <double []> getRectas(ArrayList<double[]> CP) throws Exception{
        
        ArrayList <double []> rectas=new ArrayList <double []>(); 
        try {
            System.out.println("Rectas");
            int n=CP.size();
            for (int i=0;i<n-1;i++){
                double[] p1=new double[2];
                double[] p2=new double[2];
                p1[0]=CP.get(i)[0];
                p1[1]=CP.get(i)[1];
                p2[0]=CP.get(i+1)[0];
                p2[1]=CP.get(i+1)[1];
                if (p1[1]!=p2[1]){
                    double[] recta=this.Recta(p1, p2);
                    double[] dato=new double[4];
                    dato[0]=CP.get(i)[0];
                    dato[1]=CP.get(i)[1];
                    dato[2]=recta[0];
                    dato[3]=recta[1];

                    rectas.add(dato);
                    System.out.println(dato[0]+" "+dato[1]+" "+dato[2]+" "+dato[3]);
                }
               
            }
          
       }catch(Exception e){
             e.printStackTrace();
             throw e;
       }
       return rectas;
    }
    
//--------------------------------------------------------------------------------------------//      

    // Devuelve pendiente y offset de la recta obtenida entre los puntos p1 y p2
    public double[] Recta(double[] p1, double[] p2){
        double [] recta=new double[2];
        double pendiente=(p2[0]-p1[0])/(p2[1]-p1[1]);
        double offset=pendiente*(-1)*p1[1]+p1[0];
        recta[0]=pendiente;
        recta[1]=offset;
        return recta;
    }

//--------------------------------------------------------------------------------------------//       
    
    //  Devuelve el slope, offset y R2 de la regresion de una matriz (x,y)
    public double [] getRegresion(ArrayList<double[]> matriz) throws Exception{
        double [] MC=new double [3]; 
        try{
            double suma_x=0;
            double suma_x2=0;
            double suma_y=0;
            double suma_xy=0;
            double numerador = 0;
            double denominador_x=0;
            double denominador_y=0;
            double r=0;
            int n=matriz.size();
            for (int i=0;i<3;i++){MC[i]=0;}
            for( int i = 0;i<n;i++){
                suma_x = suma_x + matriz.get(i)[0];
                suma_x2 = suma_x2 + (Math.pow(matriz.get(i)[0] ,2));
                suma_y = suma_y + matriz.get(i)[1];
                suma_xy = suma_xy + (matriz.get(i)[0] * matriz.get(i)[1]);
            }
            if (n>1){
                double x_media = suma_x / matriz.size();
                double y_media = suma_y / matriz.size();
                //pendiente

                MC[0]=((n * suma_xy) - (suma_x * suma_y)) / ((n * suma_x2) - (Math.pow(suma_x ,2)));
                //origen
                MC[1]=(suma_y - (MC[0] * suma_x)) / n;
                for (int i = 0;i<n;i++){
                    numerador = numerador + ((matriz.get(i)[0] - x_media) * (matriz.get(i)[1] - y_media));
                    denominador_x = denominador_x + (Math.pow((matriz.get(i)[0] - x_media),2));
                    denominador_y = denominador_y + (Math.pow((matriz.get(i)[1] - y_media),2));
                }
                r = numerador / (Math.sqrt(denominador_x) * Math.sqrt(denominador_y));
                //r2
                MC[2]=Math.pow(r, 2);
            }
        } catch (Exception e){
            e.printStackTrace();
            throw e;
        }
        return MC;
    } 
    
//--------------------------------------------------------------------------------------------//     
    
    // Devuelve la K promedio de la base Aux (VS/VZ)
    public double getK(Connection con,int asunto,double potencia_limite_inf,double potencia_limite_sup) throws SQLException{
        double k=0;
        try{
            
            String sql="Select avg(VS/VZ) from BaseAux"+asunto+" where";
            sql=sql+" RF=0";
            sql=sql+" and Pneta>"+potencia_limite_inf+" and Pneta<"+potencia_limite_sup;
            PreparedStatement ps = con.prepareStatement(sql);  
            ResultSet rs=ps.executeQuery(); 
            
            if (rs.next()){
                k=rs.getDouble(1);
                System.out.println("k="+k);
            }
            rs.close();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }   
        return k;
    }

//--------------------------------------------------------------------------------------------//  
    
    // Crea la base neta 
    public void getBaseNeta(Connection con,int asunto,ArrayList<String> codigos,double potencia_limite_sup,double k,double[] regresion,boolean k_factor) throws SQLException{
        
        try {
           
            // Creamos el sql
            int nequipos=codigos.size();
            String sql="Create view BaseNeta"+asunto+" as Select Id,Fecha_Hora,valido,RF";
            for (int j=0;j<nequipos;j++){ 
                sql=sql+","+codigos.get(j);
            }
            sql=sql+", Tbuje, Pbuje,Pneta,VZref,Pn,VD,VH,";
            sql=sql+" case";
            sql=sql+" WHEN (RF=0 AND Pn<"+potencia_limite_sup+") THEN VS ";
            if (k_factor){
                sql=sql+" WHEN (RF=0 AND Pn>="+potencia_limite_sup+") THEN "+k+"*VZref ";
            } else{
                sql=sql+" WHEN (RF=0 AND Pn>="+potencia_limite_sup+") THEN Vn*"+regresion[0]+"+"+regresion[1];
            }
                    
            sql=sql+" WHEN RF=1 THEN "+k+"*VZref end as VS";
            sql=sql+" from BaseAux"+asunto; 
          
            System.out.println(sql);
            Statement stmt = con.createStatement();
            stmt.execute(sql);
            
            stmt.close();
           
       }catch(SQLException e){
             e.printStackTrace();
             throw e;
       }

    }
    
//--------------------------------------------------------------------------------------------//     
    
    // Devuelve la completitud SPL (Bin,N,VS,Pn)
    public ArrayList<double[]> getResultadosSPL(Connection con,int asunto,int RF,double[] rango_v) throws SQLException{
        ArrayList<double[]> resultados=new ArrayList<double[]>();
        try{
            String sql="Select count(*),avg(VS),avg(Pn) from BaseNeta"+asunto+" where round(Vs,0,0)=? and RF="+RF+" and VS>="+rango_v[0]+" and VS<"+rango_v[1];
            PreparedStatement ps=con.prepareStatement(sql);
            System.out.println("Completitud SPL");
            for (int i=0;i<5;i++){
                double[] dato=new double[4];
                ps.setInt(1, i+6);
                ResultSet rs=ps.executeQuery();
                if (rs.next()){
                    dato[0]=(i+6);
                    dato[1]=rs.getInt(1);
                    dato[2]=rs.getDouble(2);
                    dato[3]=rs.getDouble(3);
                }
                rs.close();
                System.out.println(dato[0]+" "+dato[1]+" "+dato[2]+" "+dato[3]);
                resultados.add(dato);
            }
            ps.close();
        } catch (SQLException e){
            e.printStackTrace();
            throw e;
        }
        return resultados;
    }
    
//--------------------------------------------------------------------------------------------//       
    
    // Devuleve slope, offset y R por bin de velocidad
     public ArrayList<double[]> getRegresionSPL(ArrayList<ArrayList<double[]>> datos_bin) throws SQLException, Exception{
        ArrayList<double[]> resultados=new ArrayList<double[]>();
        try{ 
            System.out.println("Regresion Lineal SPL");
            for (int i=0;i<5;i++){
                double[] bin=new double[3];
                bin=this.getRegresion(datos_bin.get(i));
                resultados.add(bin);
                System.out.println(bin[0]+" "+bin[1]+" "+bin[2]);
            }          
        } catch (Exception e){
            e.printStackTrace();
            throw e;
        }
        return resultados;
    }
    
//--------------------------------------------------------------------------------------------//    
    
     // Devuelve los resultados SPL (LAeq,LAW)
    public double[][] getSPL(double R1,boolean lineal,double[] coeficientes_polinomio_AE,double[] coeficientes_polinomio_RF,ArrayList<double[]> coeficientes_bin_AE,ArrayList<double[]> coeficientes_bin_RF) {
        double[][] resultados=new double[2][5];
        System.out.println("SPL");
        
        String salida1="";
        String salida2="";
        for (int i=0;i<5;i++){
            System.out.println("Coeficientes bin AE");
            double v=(i+6);
            double LAeq_AE=0;
            double LAeq_RF=0;
            double LAeq_AEC=0;
            double LAeq_A=0;
            if (lineal){                
                LAeq_AE=coeficientes_bin_AE.get(i)[0]*v+coeficientes_bin_AE.get(i)[1]; // AE +RF                
                LAeq_RF=coeficientes_bin_RF.get(i)[0]*v+coeficientes_bin_RF.get(i)[1]; // RF                                
            } else{
                LAeq_AE=coeficientes_polinomio_AE[0]+coeficientes_polinomio_AE[1]*v+coeficientes_polinomio_AE[2]*java.lang.Math.pow(v, 2)+coeficientes_polinomio_AE[3]*java.lang.Math.pow(v, 3)+coeficientes_polinomio_AE[4]*java.lang.Math.pow(v, 4); // AE +RF                
                LAeq_RF=coeficientes_polinomio_RF[0]+coeficientes_polinomio_RF[1]*v+coeficientes_polinomio_RF[2]*java.lang.Math.pow(v, 2)+coeficientes_polinomio_RF[3]*java.lang.Math.pow(v, 3)+coeficientes_polinomio_RF[4]*java.lang.Math.pow(v, 4); // RF                           
            }
            LAeq_AEC=10*java.lang.Math.log10(java.lang.Math.pow(10, 0.1*LAeq_AE)-java.lang.Math.pow(10, 0.1*LAeq_RF)); // AE corregido               
            LAeq_A=LAeq_AEC-6+10*java.lang.Math.log10(4*java.lang.Math.PI*java.lang.Math.pow(R1, 2)/1); // Aparente               
            resultados[0][i]=LAeq_AEC;
            resultados[1][i]=LAeq_A;   
            salida1=salida1+" "+resultados[0][i];
            salida2=salida2+" "+resultados[1][i];
        }
        System.out.println(salida1);
        System.out.println(salida2);
        return resultados;
    }
    
//--------------------------------------------------------------------------------------------//      
     
    // Devuelve la completitud OCT (Bin,N,VS,Pn)
    public ArrayList<double[]> getResultadosOCT(Connection con,int asunto,int RF,double[] rango_v) throws SQLException{
        ArrayList<double[]> resultados=new ArrayList<double[]>();
        try{
            String sql="Select count(*),avg(VS),avg(Pn) from BaseNeta"+asunto+" where round(Vs,0,0)=? and RF="+RF+" and VS>="+rango_v[0]+" and VS<"+rango_v[1];
            PreparedStatement ps=con.prepareStatement(sql);
            System.out.println("Completitud OCT");
            for (int i=0;i<5;i++){
                double[] dato=new double[4];
                ps.setInt(1, i+6);
                ResultSet rs=ps.executeQuery();
                if (rs.next()){
                    dato[0]=(i+6);
                    dato[1]=rs.getInt(1);
                    dato[2]=rs.getDouble(2);
                    dato[3]=rs.getDouble(3);
                }
                rs.close();
                System.out.println(dato[0]+" "+dato[1]+" "+dato[2]+" "+dato[3]);
                resultados.add(dato);
            }
            ps.close();
        } catch (SQLException e){
            e.printStackTrace();
            throw e;
        }
        return resultados;
    }
    
//--------------------------------------------------------------------------------------------//       
    
    // Devuelve la completitud OCT (Hz1_6..Hzn_6,Hz1_7..Hzn_7,)
    public ArrayList<double[]> getOCT(Connection con,int asunto,ArrayList<String> bandas_octava,ArrayList<Double> ponderaciones,int RF,double[] rango_v) throws SQLException{
        ArrayList<double[]> resultados=new ArrayList<double[]>();
        try{
            String sql="Select ";
            int n=bandas_octava.size();
            System.out.println(n+" "+ponderaciones.size());
            for (int i=0;i<n;i++){           
                 if (i!=0){
                     sql=sql+",";
                 }
                 sql=sql+"Power(10,0.1*("+bandas_octava.get(i)+"+"+ponderaciones.get(i)+"))";                
            }
            sql=sql+" from BaseNeta"+asunto+" where round(Vs,0,0)=? and RF="+RF+" and VS>="+rango_v[0]+" and VS<"+rango_v[1];
            PreparedStatement ps=con.prepareStatement(sql);
            System.out.println(sql);
            for (int i=0;i<n;i++){ // Para cada banda
                double[] dato=new double[5];     
                double[] suma=new double[5];               
               
                for (int j=0;j<5;j++){
                    suma[j]=0;
                    ps.setInt(1, j+6);
                    ResultSet rs=ps.executeQuery();
                    int n_datos_bin=0;
                    while (rs.next()){
                         suma[j]=suma[j]+rs.getDouble(i+1);
                         n_datos_bin++;
                    }
                    rs.close();
                    dato[j]=8888.88;
                    if (n_datos_bin!=0){
                        dato[j]=10*java.lang.Math.log10(suma[j]/n_datos_bin);
                    }
                }
                resultados.add(dato);
                System.out.println(dato[0]+" "+dato[1]+" "+" "+dato[2]+" "+dato[3]+" "+dato[4]);
            }
            ps.close();
        } catch (SQLException e){
            e.printStackTrace();
            throw e;
        }
        return resultados;
    }
    
//--------------------------------------------------------------------------------------------//       
    
    // Devuelve los resultados OCT corregidos con RF 
    public ArrayList<double[]> getOCT(ArrayList<double[]> OCT_AE,ArrayList<double[]> OCT_RF) throws Exception{
        ArrayList<double[]> resultados=new ArrayList<double[]>();
        try{
            for (int i=0;i<OCT_AE.size();i++){
                double[] dato=new double[5];
                for (int j=0;j<5;j++){
                    double AE=OCT_AE.get(i)[j];
                    double RF=OCT_RF.get(i)[j];
                    if (AE==8888.88){
                        AE=0;
                    }
                    if (RF==8888.88){
                        RF=0;
                    }
                    dato[j]=8888.88;
                    if (AE!=RF){
                        dato[j]=10*java.lang.Math.log10(java.lang.Math.pow(10, 0.1*AE)-java.lang.Math.pow(10, 0.1*RF));   
                    }
                }
                System.out.println(dato[0]+" "+dato[1]+" "+" "+dato[2]+" "+dato[3]+" "+dato[4]);
                resultados.add(dato);
                
            }
        } catch (Exception e){
            e.printStackTrace();
            throw e;
        }
        return resultados;
    }
    
//--------------------------------------------------------------------------------------------//       
    
     // Devuleve VS,LAeq1 por bin de velocidad
     public ArrayList<ArrayList<double[]>> getDatosBinSPL(Connection con,int asunto,int RF,double[] rango_v) throws SQLException, Exception{
        ArrayList<ArrayList<double[]>> datos=new ArrayList<ArrayList<double[]>>();
        try{
            String sql="Select VS,LAeq1 from BaseNeta"+asunto+" where round(Vs,0,0)=? and RF="+RF;
            PreparedStatement ps=con.prepareStatement(sql);
            for (int i=0;i<5;i++){
                ps.setInt(1, i+6);
                ResultSet rs=ps.executeQuery();
                ArrayList<double[]> bin=new ArrayList<double[]>();
                while (rs.next()){
                    double[] dato=new double[2];
                    dato[0]=rs.getDouble(1);
                    dato[1]=rs.getDouble(2);
                    bin.add(dato);
                }
                rs.close();
                datos.add(bin);
            }
            ps.close();
        } catch (SQLException e){
            e.printStackTrace();
            throw e;
        } catch (Exception e){
            e.printStackTrace();
            throw e;
        }
        return datos;
    }
    
//--------------------------------------------------------------------------------------------//    
     
    // Obtiene los datos de nacelle para hacer la regresión lineal
    public ArrayList<double[]> getNacelle(Connection con,int asunto,double potencia_limite_inf,double potencia_limite_sup) throws SQLException{
        ArrayList<double[]> nacelle=new ArrayList<double[]>();
        try{
            
            String sql="Select Vn,Vs from BaseAux"+asunto+" where";
            sql=sql+" RF=0";
            sql=sql+" and Pneta>"+potencia_limite_inf+" and Pneta<"+potencia_limite_sup;
            PreparedStatement ps = con.prepareStatement(sql);  
            ResultSet rs=ps.executeQuery(); 
            
            while (rs.next()){
                double[] dato=new double[2];
                dato[0]=rs.getDouble(1);
                dato[1]=rs.getDouble(2);
                nacelle.add(dato);System.out.println(dato[0]+" "+dato[1]);
            }
            rs.close();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }   
        return nacelle;
    }

//--------------------------------------------------------------------------------------------//       
    
    // Devuelve las columnas de una tabla
    public ArrayList <String> getColumnas(String tabla) throws SQLException{
        Connection con = null;
        ArrayList<String> columnas=new ArrayList <String>(); 
        System.out.println(tabla);
        try {
            con = DriverManager.getConnection(url,user,pass); 
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("Select * from "+tabla);
            ResultSetMetaData mdata = rs.getMetaData();  
            int num_columnas = mdata.getColumnCount();  
	    for ( int i = 0; i < num_columnas; i++ ) {
		 columnas.add(mdata.getColumnLabel( i+1 ) ); 
	    }
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException e){
            e.printStackTrace();    
            throw e;
        } finally{
            if (con!=null){
                con.close();
            }    
        }
        return columnas;
    }


//--------------------------------------------------------------------------------------------//
 
    // Devuelve una matriz con los datos de la base neta de un asunto 
    public ArrayList <String[]> getDatosBaseNeta(Connection con,int asunto,ArrayList<String> columnas,double[] rango_v) throws SQLException{
       
        ArrayList<String[]> datos=new ArrayList <String []>(); 
        try {
          
            int ncol=columnas.size();
           
            String sql="Select ";
            for (int j=0;j<ncol;j++){ 
                sql=sql+columnas.get(j);
                if (j!=ncol-1){
                    sql=sql+",";
                }
            }

            sql=sql+" from BaseNeta"+asunto;
            sql=sql+" where (VS>="+rango_v[0]+" and VS<"+rango_v[1]+")";
            System.out.println(sql);
            PreparedStatement ps = con.prepareStatement(sql);  
            
            String[] linea=new String[ncol];
            ResultSet rs=ps.executeQuery(); 
           
            while (rs.next()){
                linea=new String[ncol];
                
                for (int j=0;j<ncol;j++){ 
                    linea [j]=rs.getString(j+1);   
                }
                datos.add(linea);  
                
                String cadena="";
                for(int h=0;h<(ncol);h++){
                    cadena=cadena+" "+linea[h];
                }
                System.out.println(cadena);
            }
            rs.close();
            ps.close();
 
       }catch(SQLException e){
             e.printStackTrace();
             throw e;
       }
       return datos;
    }
    
//--------------------------------------------------------------------------------------------//        
 
    // Borra una vista dada
    public void BorrarVista(String vista)throws SQLException{
        Connection con = null;   
        try{
            con = DriverManager.getConnection(url,user,pass);            
            Statement stmt = con.createStatement();
            // Se comprueba si existe
            boolean existe=false;
            String sql1="select * from Power_Curve.Information_Schema.Views where table_name='"+vista+"'";
            ResultSet rs=stmt.executeQuery(sql1);
            if (rs.next()){
                existe=true;
            }
            if (existe){
                // Se borra
                String sql="drop view "+vista;            
                stmt.execute(sql);
            }
            stmt.close();
            con.close();
        }catch(SQLException e){           
            e.printStackTrace();
            throw e;             
        }finally{
            if (con!=null){con.close();}
        }
    }

//--------------------------------------------------------------------------------------------//    
   
    // Inserta datos en SPL
    public String[] insercionSPL(int asunto,int site,int ruido_fondo,ArrayList<ArrayList<String>> Datos)throws SQLException, Exception {   
        
        Connection con = null;      
        String[] salida=new String[3];
       
        try{
            con = DriverManager.getConnection(url,user,pass); 
            
            // Inicio de la transacción
            
            con.setAutoCommit(false);
                       
            // Instancias de clases
         
            Asunto A=new Asunto();
            Aerogenerador AG=new Aerogenerador();
            ConfiguracionRA C=new ConfiguracionRA();
            SerieRA S=new SerieRA();
            Numero N=new Numero();
           
            // Variables de estado
            boolean error=false; // estado de la inserción
            String codigo_salida="Fallo en la inserción"; // código del error de salida
            
            // Obtengo la altura de buje del AG (para corregir T y P)
            double buje=AG.getHb(A.getAero(asunto));
                        
            // Obtenemos la configuración del punto de medida para la primera y ultima fecha del fichero           
            String primera_fecha=Datos.get(0).get(0); 
            String ultima_fecha=Datos.get(Datos.size()-1).get(0); 
            int numero_configuracion=C.getNumeroConfiguracion(asunto, site, primera_fecha);

            if (numero_configuracion==-1){
                
                // La configuración no existe            
                error=true;
                codigo_salida="No existe configuración en ese perido";
            
            } else { 
                
                ArrayList<String> variables=new ArrayList <String>();
                ArrayList<String> codigos=new ArrayList <String>();
                ArrayList<String> canales=new ArrayList <String>();
                ArrayList<String> pendientes=new ArrayList <String>();
                ArrayList<String> origenes=new ArrayList <String>();
                
                // Obtengo las lineas de configuración
                ArrayList<String[]> configuracion=C.getLineas(asunto, site, numero_configuracion);
                
                int ncanales=configuracion.size();
                                
                // Leo la informacion requerida de las lineas y calculo las rectas de transformacion
                for (int i=0;i<ncanales;i++){
                    
                    int serie=Integer.parseInt(configuracion.get(i)[0]);
                    String variable="";
                    if (S.getVariable(serie)!=null){
                        variable=S.getVariable(serie);
                    }
                    // Calibraciones
                    double p=1;
                    double o=0;
                    
                    double sp=Double.parseDouble(configuracion.get(i)[7]); 
                    double op=Double.parseDouble(configuracion.get(i)[8]);
                    
                    double sc=sp;
                    double oc=op;
                   
                    if ((configuracion.get(i)[4]!=null) && (!configuracion.get(i)[4].equals("-"))&& (!configuracion.get(i)[4].equals(""))){ // Certificado="-"
                        sc=Double.parseDouble(configuracion.get(i)[5]); 
                        oc=Double.parseDouble(configuracion.get(i)[6]); 
                        
                    } 
                    if ((sc!=sp) || (op!=oc)){
                        if (variable.equals("Dirección")){
                            sp=72;
                        }
                        p=sc/sp;
                        o=oc-(sc*(op/sp));
                    }
                    codigos.add(S.getCodigo(serie));
                    canales.add(configuracion.get(i)[2]);
                    pendientes.add(p+"");
                    origenes.add(o+"");
                    variables.add(variable);
                }
                
                // Obtengo la declinación magnética de la configuración               
                double declinacion=C.getDeclinacion(asunto, site, numero_configuracion);
                
                // Se busca la cota del sensor de presión para esta configuración
                double cota_presion=C.getcota(asunto,site,numero_configuracion,6);
                
                 // Se busca la cota del sensor de temperatuta para esta configuración
                double cota_temperatura=C.getcota(asunto,site,numero_configuracion,5);
                
                //Creamos el Prepared para la inserción en datospc    
                String insercion_datos="INSERT INTO DatosSPL"+asunto+" (Id,Fecha_Hora,RF";
                for (int j=0;j<codigos.size();j++){
                    String Codigo=codigos.get(j);
                    insercion_datos=insercion_datos+","+Codigo;
                }
                insercion_datos=insercion_datos+",Tbuje,Pbuje";
                insercion_datos=insercion_datos+") VALUES(?,?,?";
                for (int j=0;j<ncanales;j++){                
                   insercion_datos=insercion_datos+",?";
                }
                insercion_datos=insercion_datos+",?,?)";            
                System.out.println(insercion_datos);
                PreparedStatement ps_datos=con.prepareStatement(insercion_datos);

                //Creamos el Prepared para la modificación en datospc                        
                String modificación_datos="UPDATE DatosSPL"+asunto+" set ";
                for (int j=0;j<codigos.size();j++){
                    String Codigo=codigos.get(j);
                    modificación_datos=modificación_datos+Codigo+"=?";
                    if (j!=codigos.size()-1){
                         modificación_datos= modificación_datos+",";
                    }
                }  
                modificación_datos=modificación_datos+", Valido=1";
                modificación_datos=modificación_datos+", RF=?";
     
                int indice_id=codigos.size()+2;
                int indice_p=codigos.size()+2;
                
                if (codigos.indexOf("T")!=-1){
                    if (codigos.indexOf("P")!=-1){
                        modificación_datos=modificación_datos+", Tbuje=?";
                        modificación_datos=modificación_datos+", Pbuje=?";
                        indice_p=indice_p+1;
                        indice_id=indice_p+1;
                    } else{
                        indice_id=indice_id+1;
                    }
                } else{
                    if (codigos.indexOf("P")!=-1){
                        modificación_datos=modificación_datos+", Pbuje=?";
                        indice_id=indice_id+1;
                    }
                }
                
                
                modificación_datos=modificación_datos+" where Id=?";
                System.out.println(modificación_datos);
                PreparedStatement ps_moddatos=con.prepareStatement(modificación_datos);

                // Buscamos la ultimo dato introducido antes de la inserción
                int id=0;
                int ultimo_id=0;
                String fecha_ultima=getFultima(asunto,"DatosSPL"); // Última fecha introducida en datoSPL 
                ultimo_id=this.getId(asunto,fecha_ultima,"DatosSPL");
               
                // Para cada dato del fichero
                for (int dato=0;dato<Datos.size();dato++){ 
                  
                    double pb=8888.88;
                    double tb=8888.88;
                    double t=8888.88;
                    double p=8888.88;
                    String fecha=Datos.get(dato).get(0);
                   
                    System.out.println("Fecha a insertar "+fecha);
                       
                    // Comprobamos si existe el dato 
                    boolean existe=false; // Existe el dato en la base de datos
                    if (getId(con,asunto,fecha,"DatosSPL")!=0){
                         existe=true;
                    }
                    if (!existe){ 
                       // No existe el dato en la tabla DatosSPL--> Inserción al final (id=ultimo_id+1)
                       System.out.println(fecha+" no existe");
                       id=ultimo_id+1;
                       System.out.println("Inserto "+fecha+" n dato "+id);
                                 
                       // Se inserta en datos
                       ps_datos.setInt(1, id);
                       ps_datos.setString(2, fecha);
                       ps_datos.setInt(3, ruido_fondo);
                       String sql=""+id+" "+fecha+" "+ruido_fondo;
                       for (int j=0;j<codigos.size();j++){
                                     
                            // Se busca la posicion del codigo en el fichero (canal)
                            String canal=canales.get(j);
                            int posicion=Integer.parseInt(canal.substring(5));
                                     
                            // Se obtienen los valores brutos de la matriz datos
                            double valor=Double.parseDouble(Datos.get(dato).get(posicion));
                                                                    
                            // Se corrigen por las rectas de calibracion                                 
                            valor=valor*Double.parseDouble(pendientes.get(j))+Double.parseDouble(origenes.get(j));
                             
                            // Se corrige por declinación si es veleta y se cierra el rango 360
                            if (variables.get(j).equals("Dirección")){                                
                                
                                if (valor!=8888.88){
                                     valor=valor+declinacion;
                                     if (valor>=360){valor=valor-360;}
                                     if (valor<0){valor=valor+360;}
                                }
                            }
                                     
                            // Se cierra el rango de humedad
                            if (variables.get(j).equals("Humedad")){                                   
                                if ((valor>100) && (valor<=105)){ valor=100;}      
                            }
                                     
                            // Se redondea el valor
                            valor=N.redondear(valor, 5);
                            
                            // Se corrige P y T
                            if (codigos.get(j).equals("P")){ //  Presión
                                p=valor;
                            }
                            if (codigos.get(j).equals("T")){ // Temperatura
                                t=valor;
                            }
                            
                            // Se asigna el valor
                            ps_datos.setDouble(j+4, valor);     
                            sql=sql+" "+valor;
                       } // Fin codigos
                                 
                       // Se añade tb + pb
                       if ((t!=8888.88) && (buje!=0) && (cota_temperatura!=-1)) {
                           tb=N.redondear(t-(0.65*(buje-cota_temperatura)/100),5);
                       }
                       ps_datos.setDouble(codigos.size()+4, tb);  
                       sql=sql+" "+tb;
                       
                       if ((t!=8888.88) && (p!=8888.88) && (buje!=0) && (cota_presion!=-1)) {
                           pb=N.redondear(p*(java.lang.Math.exp(-(9.80665/287.05287/(273.15+tb))*(buje-cota_presion))),5);
                       }
                       ps_datos.setDouble(codigos.size()+5, pb);  
                       sql=sql+" "+pb;
                       System.out.println(sql);
                                 
                       ps_datos.executeUpdate(); 
                       
                       // saltar Id
                       ultimo_id++;
                    
                    } else{
                        
                        // Existe el dato --> Modificación (Id=Id)
                        System.out.println("Existe el dato");
                        
                        id=getId(con,asunto,fecha,"DatosSPL");
                        String sql=""+id+" "+fecha+" "+ruido_fondo; 
                        // Se modifica datos
                        for (int j=0;j<codigos.size();j++){
                                
                            // Se busca la posicion del codigo en el fichero (canal)
                            String canal=canales.get(j);
                            int posicion=Integer.parseInt(canal.substring(5));
                                     
                            // Se obtienen los valores brutos de la matriz datos
                            double valor=Double.parseDouble(Datos.get(dato).get(posicion));
                         
                            // Se corrigen por las rectas de calibracion
                            valor=valor*Double.parseDouble(pendientes.get(j))+Double.parseDouble(origenes.get(j));
                               
                            // Se corrige por declinación si en veleta y se cierra el rango 360
                            if (variables.get(j).equals("Dirección")){
                                if (valor!=8888.88){
                                    valor=valor+declinacion;
                                    if (valor>=360){valor=valor-360;}
                                    if (valor<0){valor=valor+360;}
                                }
                            }
                                     
                            // Se cierra el rango de humedad
                            if (variables.get(j).equals("Humedad")){
                                if ((valor>100) && (valor<=105)){ valor=100;}      
                            }
                                
                           // Se redondea el valor
                           valor=N.redondear(valor, 5);
                            
                           if (codigos.get(j).equals("P")){ //  Presion
                               p=valor;
                           }
                           if (codigos.get(j).equals("T")){ // Temperatura
                               t=valor;
                           }
                          
                           ps_moddatos.setDouble(j+1, valor);
                           sql=sql+" "+valor; 
                    
                      } // Fin codigos
                      
                      ps_moddatos.setInt(codigos.size()+1, ruido_fondo);
                     
                      // tb + pb
                      if ((t!=8888.88) && (buje!=0) && (cota_temperatura!=-1)) {
                           tb=N.redondear(t-(0.65*(buje-cota_temperatura)/100),5);
                           ps_moddatos.setDouble(codigos.size()+2, tb);  
                           sql=sql+" "+tb; 
                      }
                      
                      if ((t!=8888.88) && (p!=8888.88) && (buje!=0) && (cota_presion!=-1)) {
                          pb=N.redondear(p*(java.lang.Math.exp(-(9.80665/287.05287/(273.15+tb))*(buje-cota_presion))),5);
                          ps_moddatos.setDouble(indice_p, pb);  
                          sql=sql+" "+tb; 
                      }
                      
                      ps_moddatos.setInt(indice_id, id);
                      ps_moddatos.executeUpdate(); 
                      System.out.println(sql);
                    }
                }
                // Fin de insercción
                error=false; 
                codigo_salida="Datos insertados";
                System.out.println("Fin de inserción de datos");
            }
            if (!error){
                con.commit();
                salida[0]=codigo_salida;
                salida[1]=primera_fecha;
                salida[2]=ultima_fecha;
            } else {
                con.rollback();
            }          
            con.close();     
      }catch (SQLException e){
          e.printStackTrace();
          throw e; 
      } catch (Exception e){
          e.printStackTrace();
          throw e;       
      }
      finally{
         try {
             if (con!=null) con.close();
         }catch (SQLException e){
             e.printStackTrace();
         }
      }
      return (salida);
}
    
//--------------------------------------------------------------------------------------------//          

    // Inserta datos en OCT
    public String[] insercionOCT(int asunto,int site,int ruido_fondo,ArrayList<ArrayList<String>> Datos)throws SQLException, Exception {   
        
        Connection con = null;
        String[] salida=new String[3];
              
        try{
            con = DriverManager.getConnection(url,user,pass); 
            
            // Inicio de la transacción
            
            con.setAutoCommit(false);
                       
            // Instancias de clases
         
            Asunto A=new Asunto();
            Aerogenerador AG=new Aerogenerador();
            ConfiguracionRA C=new ConfiguracionRA();
            SerieRA S=new SerieRA();
            Numero N=new Numero();
           
            // Variables de estado
            boolean error=false; // estado de la inserción
            String codigo_salida="Fallo en la inserción"; // código del error de salida
            
            // Obtengo la altura de buje del AG (para corregir T y P)
            double buje=AG.getHb(A.getAero(asunto));
                        
            // Obtenemos la configuración del punto de medida para la primera fecha del fichero           
            String primera_fecha=Datos.get(0).get(0); 
            String ultima_fecha=Datos.get(Datos.size()-1).get(0); 
            int numero_configuracion=C.getNumeroConfiguracion(asunto, site, primera_fecha);

            if (numero_configuracion==-1){
                
                // La configuración no existe            
                error=true;
                codigo_salida="No existe configuración en ese perido";
            
            } else { 
                
                ArrayList<String> variables=new ArrayList <String>();
                ArrayList<String> codigos=new ArrayList <String>();
                ArrayList<String> canales=new ArrayList <String>();
                ArrayList<String> pendientes=new ArrayList <String>();
                ArrayList<String> origenes=new ArrayList <String>();
                
                // Obtengo las lineas de configuración
                ArrayList<String[]> configuracion=C.getLineas(asunto, site, numero_configuracion);
                
                int ncanales=configuracion.size();
                                
                // Leo la informacion requerida de las lineas y calculo las rectas de transformacion
                for (int i=0;i<ncanales;i++){
                    
                    int serie=Integer.parseInt(configuracion.get(i)[0]);
                    String variable="";
                    if (S.getVariable(serie)!=null){
                        variable=S.getVariable(serie);
                    }
                    // Calibraciones
                    double p=1;
                    double o=0;
                    
                    double sp=Double.parseDouble(configuracion.get(i)[7]); 
                    double op=Double.parseDouble(configuracion.get(i)[8]);
                    
                    double sc=sp;
                    double oc=op;
                   
                    if ((configuracion.get(i)[4]!=null) && (!configuracion.get(i)[4].equals("-"))&& (!configuracion.get(i)[4].equals(""))){ // Certificado="-"
                        sc=Double.parseDouble(configuracion.get(i)[5]); 
                        oc=Double.parseDouble(configuracion.get(i)[6]); 
                        
                    } 
                    if ((sc!=sp) || (op!=oc)){
                        if (variable.equals("Dirección")){
                            sp=72;
                        }
                        p=sc/sp;
                        o=oc-(sc*(op/sp));
                    }
                    codigos.add(S.getCodigo(serie));
                    canales.add(configuracion.get(i)[2]);
                    pendientes.add(p+"");
                    origenes.add(o+"");
                    variables.add(variable);
                }
                
                // Obtengo la declinación magnética de la configuración               
                double declinacion=C.getDeclinacion(asunto, site, numero_configuracion);
                
                // Se busca la cota del sensor de presión para esta configuración
                double cota_presion=C.getcota(asunto,site,numero_configuracion,6);
                
                 // Se busca la cota del sensor de temperatuta para esta configuración
                double cota_temperatura=C.getcota(asunto,site,numero_configuracion,5);
                
                //Creamos el Prepared para la inserción en datos  
                String insercion_datos="INSERT INTO DatosOCT"+asunto+" (Id,Fecha_Hora,RF";
                for (int j=0;j<codigos.size();j++){
                    String Codigo=codigos.get(j);
                    insercion_datos=insercion_datos+","+Codigo;
                }
                insercion_datos=insercion_datos+",Tbuje,Pbuje";
                insercion_datos=insercion_datos+") VALUES(?,?,?";
                for (int j=0;j<ncanales;j++){                
                   insercion_datos=insercion_datos+",?";
                }
                insercion_datos=insercion_datos+",?,?)";            
                System.out.println(insercion_datos);
                PreparedStatement ps_datos=con.prepareStatement(insercion_datos);

                //Creamos el Prepared para la modificación en datos                     
                String modificación_datos="UPDATE DatosOCT"+asunto+" set ";
                for (int j=0;j<codigos.size();j++){
                    String Codigo=codigos.get(j);
                    modificación_datos=modificación_datos+Codigo+"=?";
                    if (j!=codigos.size()-1){
                         modificación_datos= modificación_datos+",";
                    }
                }  
                modificación_datos=modificación_datos+", Valido=1";
                modificación_datos=modificación_datos+", RF=?";
     
                int indice_id=codigos.size()+2;
                int indice_p=codigos.size()+2;
                
                if (codigos.indexOf("T")!=-1){
                    if (codigos.indexOf("P")!=-1){
                        modificación_datos=modificación_datos+", Tbuje=?";
                        modificación_datos=modificación_datos+", Pbuje=?";
                        indice_p=indice_p+1;
                        indice_id=indice_p+1;
                    } else{
                        indice_id=indice_id+1;
                    }
                } else{
                    if (codigos.indexOf("P")!=-1){
                        modificación_datos=modificación_datos+", Pbuje=?";
                        indice_id=indice_id+1;
                    }
                }
                
                
                modificación_datos=modificación_datos+" where Id=?";
                System.out.println(modificación_datos);
                PreparedStatement ps_moddatos=con.prepareStatement(modificación_datos);

                // Buscamos la ultimo dato introducido antes de la inserción
                int id=0;
                int ultimo_id=0;
                String fecha_ultima=getFultima(asunto,"DatosOCT"); // Última fecha introducida en datoOCT 
                ultimo_id=this.getId(asunto,fecha_ultima,"DatosOCT");
               
                // Para cada dato del fichero
                for (int dato=0;dato<Datos.size();dato++){ 
                  
                    double pb=8888.88;
                    double tb=8888.88;
                    double t=8888.88;
                    double p=8888.88;
                    String fecha=Datos.get(dato).get(0);
                   
                    System.out.println("Fecha a insertar "+fecha);
                       
                    // Comprobamos si existe el dato 
                    boolean existe=false; // Existe el dato en la base de datos
                    if (getId(con,asunto,fecha,"DatosOCT")!=0){
                         existe=true;
                    }
                    if (!existe){ 
                       // No existe el dato en la tabla DatosSPL--> Inserción al final (id=ultimo_id+1)
                       System.out.println(fecha+" no existe");
                       id=ultimo_id+1;
                       System.out.println("Inserto "+fecha+" n dato "+id);
                                 
                       // Se inserta en datos
                       ps_datos.setInt(1, id);
                       ps_datos.setString(2, fecha);
                       ps_datos.setInt(3, ruido_fondo);
                       String sql=""+id+" "+fecha+" "+ruido_fondo;
                       for (int j=0;j<codigos.size();j++){
                                     
                            // Se busca la posicion del codigo en el fichero (canal)
                            String canal=canales.get(j);
                            int posicion=Integer.parseInt(canal.substring(5));
                                     
                            // Se obtienen los valores brutos de la matriz datos
                            double valor=Double.parseDouble(Datos.get(dato).get(posicion));
                                                                    
                            // Se corrigen por las rectas de calibracion                                 
                            valor=valor*Double.parseDouble(pendientes.get(j))+Double.parseDouble(origenes.get(j));
                             
                            // Se corrige por declinación si es veleta y se cierra el rango 360
                            if (variables.get(j).equals("Dirección")){                                
                                
                                if (valor!=8888.88){
                                     valor=valor+declinacion;
                                     if (valor>=360){valor=valor-360;}
                                     if (valor<0){valor=valor+360;}
                                }
                            }
                                     
                            // Se cierra el rango de humedad
                            if (variables.get(j).equals("Humedad")){                                   
                                if ((valor>100) && (valor<=105)){ valor=100;}      
                            }
                                     
                            // Se redondea el valor
                            valor=N.redondear(valor, 5);
                            
                            // Se corrige P y T
                            if (codigos.get(j).equals("P")){ //  Presión
                                p=valor;
                            }
                            if (codigos.get(j).equals("T")){ // Temperatura
                                t=valor;
                            }
                            
                            // Se asigna el valor
                            ps_datos.setDouble(j+4, valor);     
                            sql=sql+" "+valor;
                       } // Fin codigos
                                 
                       // Se añade tb + pb
                       if ((t!=8888.88) && (buje!=0) && (cota_temperatura!=-1)) {
                           tb=N.redondear(t-(0.65*(buje-cota_temperatura)/100),5);
                       }
                       ps_datos.setDouble(codigos.size()+4, tb);  
                       sql=sql+" "+tb;
                       
                       if ((t!=8888.88) && (p!=8888.88) && (buje!=0) && (cota_presion!=-1)) {
                           pb=N.redondear(p*(java.lang.Math.exp(-(9.80665/287.05287/(273.15+tb))*(buje-cota_presion))),5);
                       }
                       ps_datos.setDouble(codigos.size()+5, pb);  
                       sql=sql+" "+pb;
                       System.out.println(sql);
                                 
                       ps_datos.executeUpdate(); 
                       
                       // saltar Id
                       ultimo_id++;
                    
                    } else{
                        
                        // Existe el dato --> Modificación (Id=Id)
                        System.out.println("Existe el dato");
                        
                        id=getId(con,asunto,fecha,"DatosOCT");
                        String sql=""+id+" "+fecha+" "+ruido_fondo; 
                        // Se modifica datos
                        for (int j=0;j<codigos.size();j++){
                                
                            // Se busca la posicion del codigo en el fichero (canal)
                            String canal=canales.get(j);
                            int posicion=Integer.parseInt(canal.substring(5));
                                     
                            // Se obtienen los valores brutos de la matriz datos
                            double valor=Double.parseDouble(Datos.get(dato).get(posicion));
                         
                            // Se corrigen por las rectas de calibracion
                            valor=valor*Double.parseDouble(pendientes.get(j))+Double.parseDouble(origenes.get(j));
                               
                            // Se corrige por declinación si en veleta y se cierra el rango 360
                            if (variables.get(j).equals("Dirección")){
                                if (valor!=8888.88){
                                    valor=valor+declinacion;
                                    if (valor>=360){valor=valor-360;}
                                    if (valor<0){valor=valor+360;}
                                }
                            }
                                     
                            // Se cierra el rango de humedad
                            if (variables.get(j).equals("Humedad")){
                                if ((valor>100) && (valor<=105)){ valor=100;}      
                            }
                                
                           // Se redondea el valor
                           valor=N.redondear(valor, 5);
                            
                           if (codigos.get(j).equals("P")){ //  Presion
                               p=valor;
                           }
                           if (codigos.get(j).equals("T")){ // Temperatura
                               t=valor;
                           }
                          
                           ps_moddatos.setDouble(j+1, valor);
                           sql=sql+" "+valor; 
                    
                      } // Fin codigos
                      
                      ps_moddatos.setInt(codigos.size()+1, ruido_fondo);
                     
                      // tb + pb
                      if ((t!=8888.88) && (buje!=0) && (cota_temperatura!=-1)) {
                           tb=N.redondear(t-(0.65*(buje-cota_temperatura)/100),5);
                           ps_moddatos.setDouble(codigos.size()+2, tb);  
                           sql=sql+" "+tb; 
                      }
                      
                      if ((t!=8888.88) && (p!=8888.88) && (buje!=0) && (cota_presion!=-1)) {
                          pb=N.redondear(p*(java.lang.Math.exp(-(9.80665/287.05287/(273.15+tb))*(buje-cota_presion))),5);
                          ps_moddatos.setDouble(indice_p, pb);  
                          sql=sql+" "+tb; 
                      }
                      
                      ps_moddatos.setInt(indice_id, id);
                      ps_moddatos.executeUpdate(); 
                      System.out.println(sql);
                    }
                }
                // Fin de insercción
                error=false; 
                codigo_salida="Datos insertados";
                System.out.println("Fin de inserción de datos");
            }
            if (!error){
                con.commit();
                salida[0]=codigo_salida;
                salida[1]=primera_fecha;
                salida[2]=ultima_fecha;
            } else {
                con.rollback();
            }          
            con.close();     
      }catch (SQLException e){
          e.printStackTrace();
          throw e; 
      } catch (Exception e){
          e.printStackTrace();
          throw e;       
      }
      finally{
         try {
             if (con!=null) con.close();
         }catch (SQLException e){
             e.printStackTrace();
         }
      }
      return (salida);
}
    
//--------------------------------------------------------------------------------------------//          
    
    // Proceso de inserción 
    public String Nuevosdatos(int asunto, int site,String[] medida,int usuario) throws SQLException,Exception{ 
     
     String[] salida=new String[3];
     FicheroRA FI=new FicheroRA();
     Insercion I=new Insercion();
     
     ArrayList<ArrayList<String>> datos=new ArrayList <ArrayList<String>>(); // matriz con los datos
     ArrayList<String> cabecera=new ArrayList<String>(); // vector con la cabecera     
     String Finicio=null; // Fecha de inicio del fichero del fichero
     
     try {
         // Fecha actual
         
        Date hoy= new Date();
        SimpleDateFormat formato=new SimpleDateFormat("dd-MM-yyyy hh:mm");
        String formatohoy=formato.format(hoy);
     
         if ((site==101) || (site==102) || (site==103) || (site==104)){ // SPL
             
                 System.out.println("Se inserta "+medida[0]);
                 cabecera=FI.LeerCanales(medida[0]);
                 Finicio=FI.LeerFecha(medida[0]);
                 datos=FI.LeerSPL_OCT(medida[0], Finicio, cabecera.size());
                 salida=insercionSPL(asunto, site, Integer.parseInt(medida[1]), datos);
             
         } else{
            if (site==105){ // OCT
                 System.out.println("Se inserta "+medida[0]);
                 cabecera=FI.LeerCanales(medida[0]);
                 Finicio=FI.LeerFecha(medida[0]);
                 datos=FI.LeerSPL_OCT(medida[0], Finicio, cabecera.size());
                 salida=insercionOCT(asunto, site, Integer.parseInt(medida[1]), datos);
            } else{ // FFT
                
            }
         }
         if (salida[0].equals("Datos insertados")){
             I.NuevaInsercion(asunto, site, 5, medida[0], salida[1],salida[2],formatohoy, usuario,Integer.parseInt(medida[1]));
         }
         System.out.println("Salida de insercion: "+salida[0]);
     } catch(SQLException e) {
         e.printStackTrace();
         throw e;        
     } catch(Exception e) {
         e.printStackTrace();
         throw e;
     } 
     return salida[0];     
    }

//--------------------------------------------------------------------------------------------//          
    
//    // Proceso de borrado
//    public String Borradatos(int asunto, int site,String[] medida,int usuario) throws SQLException,Exception{ 
//     
//     String[] salida=new String[3];
//     Fichero FI=new Fichero();
//     Site S=new Site();
//     Insercion I=new Insercion();
//     
//     ArrayList<ArrayList<String>> datos=new ArrayList <ArrayList<String>>(); // matriz con los datos
//     ArrayList<String> cabecera=new ArrayList<String>(); // vector con la cabecera     
//     String Finicio=null; // Fecha de inicio del fichero del fichero
//     
//     try {
//         
//        // Fecha actual
//        Date hoy= new Date();
//        SimpleDateFormat formato=new SimpleDateFormat("dd-MM-yyyy hh:mm");
//        String formatohoy=formato.format(hoy);
//     
//        if ((site==101) || (site==102) || (sad==103) || (sad==104)){ // SPL
//             
//                 System.out.println("Se borra "+medida[0]);
//                 cabecera=FI.LeerCanales(medida[0]);
//                 Finicio=FI.LeerFecha(medida[0]);
//                 datos=FI.LeerSPL_OCT(medida[0], Finicio, cabecera.size());
//                 salida=insercionSPL(asunto, site, Integer.parseInt(medida[1]), datos);
//             
//         } else{
//            if (site==105){ // OCT
//                 System.out.println("Se inserta "+medida[0]);
//                 cabecera=FI.LeerCanales(medida[0]);
//                 Finicio=FI.LeerFecha(medida[0]);
//                 datos=FI.LeerSPL_OCT(medida[0], Finicio, cabecera.size());
//                 salida=insercionOCT(asunto, site, Integer.parseInt(medida[1]), datos);
//            }
//         }
//         if (salida[0].equals("Datos insertados")){
//             I.NuevaInsercion(asunto, site, 5, medida[0], salida[1],salida[2],formatohoy, usuario,Integer.parseInt(medida[1]));
//         }
//         System.out.println("Salida de insercion: "+salida[0]);
//     } catch(SQLException e) {
//         e.printStackTrace();
//         throw e;        
//     } catch(Exception e) {
//         e.printStackTrace();
//         throw e;
//     } 
//     return salida[0];     
//    }
}