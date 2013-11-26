
package RA;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


/**
 *
 * @author Ruth Cordon
 */

public class FicheroRA {
    
//--------------------------------------------------------------------------------------------//  

    // Lee los datos de un fichero SPL
    public ArrayList <ArrayList <String>> LeerSPL_OCT(String fichero,String fecha,int ncanales) throws Exception{
        
        FechaRA F=new FechaRA();
        ArrayList <String> fila=new ArrayList <String>();
        ArrayList<ArrayList <String>> datos=new ArrayList<ArrayList <String>>();
        
        File archivo = new File (fichero);	
        FileReader fr = new FileReader (archivo);
        BufferedReader br = new BufferedReader(fr);
        String linea;
        int i=1;
        try {
           String time_ant="0";
           String fecha_ant=fecha;
            while ((linea=br.readLine())!=null){
                fila=new ArrayList <String>();
                
                //Leemos los datos
                if (i>=3){
                   
                    int indice=linea.indexOf("\t");
                    String time=linea.substring(0,indice); 
                    double d=Double.parseDouble(time)-Double.parseDouble(time_ant);
                    String fecha_dato=F.sumaFecha(fecha_ant, d);
                    fila.add(fecha_dato); //fecha
                    
                    linea=linea.substring(indice+1);                    
                    
                    int canal=0;
                    while (canal<ncanales){  // Para cada canal del fichero        
                        String valor="8888.88";
                        indice=linea.indexOf("\t");
                        if (indice!=-1){
                            valor=linea.substring(0, indice);
                        } else{
                            valor=linea.trim();
                        }    
                        linea=linea.substring(indice+1);
                        fila.add(valor); 
                        canal++;  
                    } 
                    String cadena="";
                    for (int j=0;j<fila.size();j++){
                        cadena=cadena+" "+fila.get(j);                        
                    } 
                    System.out.println(cadena);
                    time_ant=time;
                    fecha_ant=fecha_dato;
                    datos.add(fila);
                }               
                i++;
            }
            br.close();   
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }finally{
               if( fr != null){fr.close();}
        }
        return datos; 
    }
    
//--------------------------------------------------------------------------------------------//     

    // Lee los canales de un fichero de RA
    public ArrayList<String> LeerCanales(String fichero) throws Exception{
        
      
        ArrayList<String> canales=new ArrayList<String>();
        
        File archivo = new File (fichero);	
        FileReader fr = new FileReader (archivo);
        BufferedReader br = new BufferedReader(fr);
        String linea;
        boolean encontrado=false;
        int i=1;
        try {
          
           while ((linea=br.readLine())!=null && !encontrado){

                //Leemos los datos
                if (linea.contains("Time")){
                    encontrado=true;
   
                    int indice=linea.indexOf("\t");
                    linea=linea.substring(indice+1);
                    indice=linea.indexOf("\t");
                    while(indice!=-1){
                        String canal=linea.substring(0, indice);
                        canales.add(canal);

                        linea=linea.substring(indice+1);
                        indice=linea.indexOf("\t");
                    }
                    String canal=linea.trim();
                    canales.add(canal);
                   
                    String cadena="";
                    for (int j=0;j<canales.size();j++){
                        cadena=cadena+" "+canales.get(j);                        
                    } 
                    System.out.println(cadena);
                   
                }               
                i++;
            }
            br.close();   
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }finally{
               if( fr != null){fr.close();}
        }
        return canales; 
    }
 
//--------------------------------------------------------------------------------------------//
    
    // Lee la fecha de inicio de un fichero de un fichero de RA
    public String LeerFecha(String fichero) throws Exception{
     
        String fecha=null;
        
        FechaRA F=new FechaRA();
        
        File archivo = new File (fichero);
        FileReader fr = new FileReader (archivo);
        BufferedReader br = new BufferedReader(fr);
        String linea;
        boolean encontrado=false;
       
        try {
          
           while ((linea=br.readLine())!=null && !encontrado){
           
                // Leemos la fecha
                if (linea.contains("Time of run")){
                    encontrado=true;
   
                    int indice=linea.indexOf("\t");
                    linea=linea.substring(indice+1);
                    indice=linea.indexOf("\t");
                    String fecha_fichero=linea.substring(0,indice);
                    fecha=F.format(fecha_fichero);
                    System.out.println(fecha);
                   
                }               

            }
            br.close();   
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }finally{
               if( fr != null){fr.close();}
        }
        return fecha; 
    }

//--------------------------------------------------------------------------------------------//
    
 // Lee las posiciones de un fichero de texto. Devuelve una unica posicion con tupla ("mal", "mal", "mal", "mal", "mal","mal") si el formato del fichero no es válido
 public ArrayList <String[]> loadPosiciones(String fichero) throws Exception{
    ArrayList <String []> pos=new ArrayList <String []>();
    File archivo = new File (fichero);
    FileReader fr = new FileReader (archivo);
    BufferedReader br = new BufferedReader(fr);
    String linea;
    int i=0;
    boolean existe=true;
    try {            
         while(((linea=br.readLine())!=null) && (existe)){ 
             String [] datos=new String [6];
             String aux=linea;
             // Se comprueba que existen los 5 separadores mientrs se rellena datos
             int s1=aux.indexOf(";"); // separa aero de x
             if (s1!=-1) {
                String naero=aux.substring(0,s1).trim(); //naero
                datos[0]=naero;
                aux=aux.substring(s1+1);
                int s2=aux.indexOf(";"); // separa x de y
                if (s2!=-1){
                    String x=aux.substring(0,s2).trim(); // x
                    datos[1]=x; 
                    aux=aux.substring(s2+1);
                    int s3=aux.indexOf(";"); // separa y de z
                    if (s3!=-1){
                        String y=aux.substring(0,s3).trim(); // y
                        datos[2]=y; 
                        aux=aux.substring(s3+1);
                        int s4=aux.indexOf(";"); // separa z de dn
                        if (s4!=-1){
                            String z=aux.substring(0,s4).trim(); // z
                            datos[3]=z; 
                            aux=aux.substring(s4+1);
                            int s5=aux.indexOf(";"); // separa dn de hb
                            if (s5!=-1){
                                String dn=aux.substring(0,s5).trim(); // dn
                                datos[4]=dn; 
                                aux=aux.substring(s5+1);
                                String hb=aux.trim();
                                datos[5]=hb; 
                                pos.add(i, datos);
                                i=i+1;
                            } else{
                                existe=false;
                            }
                        } else{
                          existe=false;
                        } 
                    } else{
                        existe=false;
                    }
                } else{
                    existe=false;
                }
             } else {
                existe=false;
             }
        }
        br.close();  
        if (!existe){
            pos.clear();
            String [] datos=new String [6];
            datos[0]=datos[1]=datos[2]=datos[3]=datos[4]=datos[5]="mal";
            pos.add(0, datos);
        }
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }finally{
            try{
                if( fr != null){
                    fr.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return pos;
    }    

//--------------------------------------------------------------------------------------------//

    // Lee la cp de un fichero de texto (V,P) 
    public ArrayList <double[]> LeerCP(String fichero) throws Exception{
        
        ArrayList <double []> curva=new ArrayList <double []>();
        
        File archivo = new File (fichero);	
        FileReader fr = new FileReader (archivo);
        BufferedReader br = new BufferedReader(fr);
       
        try {  
            
            String linea;
            while((linea=br.readLine())!=null){  
               
               double [] dato=new double [2];
               int p=linea.indexOf(";");
               if (p!=-1){
                    dato[0]=Double.parseDouble(linea.substring(0,p).trim()); //v
                    dato[1]=Double.parseDouble(linea.substring(p+1).trim()); // p
                    curva.add(dato);
                    System.out.println(dato[0]+" "+dato[1]);
               }
              
            }
            br.close();   
           
        }catch(Exception e){
                e.printStackTrace();
                throw e;
        }
        return curva;
    } 
//--------------------------------------------------------------------------------------------//         
    
    // Lee la rosa energetica de un emplazamiento. Devuelve tupla ("mal","mal","mal","mal") si el formato no es valido
    public ArrayList <String []> loadRosa(String fichero) throws FileNotFoundException{
        
        ArrayList <String []> R=new ArrayList <String []>();
        File archivo = new File (fichero);	
	FileReader fr = new FileReader (archivo);
	BufferedReader br = new BufferedReader(fr);
        String linea;
        int i=0;
        boolean existe=true;
        try {            
            while(((linea=br.readLine())!=null) && (existe)){ 
                 String [] datos=new String [2];
                 String aux=linea;
                 // Se comprueba que existe el separador mientras se rellena datos
                 int s1=aux.indexOf(";"); // separa D de E
                 if (s1!=-1) {
                    String D=aux.substring(0,s1).trim(); // Límite inferior
                    datos[0]=D;
                    aux=aux.substring(s1+1);
                    String E=aux.trim();
                    datos[1]=E;
                    R.add(i, datos);
                    i=i+1;
                } else{
                     existe=false;
                }    
            }
            br.close(); 
            if (!existe){
                R.clear();
                String [] datos=new String [2];
                datos[0]=datos[1]="mal";
                R.add(0, datos);
            }
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            try{
                if( fr != null){
                    fr.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return R;
    } 
  
//--------------------------------------------------------------------------------------------//     
    // Devuelve si el fichero existe en un directorio
    public boolean Existe(String fichero,String Directorio){       
        File dir = new File(Directorio);
        boolean existe=false;     
        if (dir.list()!=null){            
            String[] ficheros = dir.list();
            int i=0;
            while ((i<ficheros.length) && (!existe)){
                if (ficheros[i].equals(fichero)){
                    existe=true;
                }          
                i=i+1;
            }        
        }
        return existe; 
    }
    //--------------------------------------------------------------------------------------------//     
}