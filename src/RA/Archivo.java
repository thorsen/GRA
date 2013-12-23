package RA;


import jxl.format.Colour;
import java.io.*;   
import java.sql.SQLException;
import java.util.ArrayList;
import jxl.*;   
import jxl.read.biff.BiffException;   
import jxl.write.*;    
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;


/**
 *
 * @author Ruth Cordon
 */

public class Archivo {

    private static String url = "jdbc:sqlserver://192.168.1.53:1433";
    private static String user = "SQL_PwC";
    private static String pass = "Ru8865No";
    
    static{
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        }catch(ClassNotFoundException e){
            System.out.println("No puedo cargar el driver de la BD");
        }
    }
   
// -----------------------------------------------------------------------------------------------//       
    
     // Ordena la matriz por el id (SV)
     public void ordenar(ArrayList<String[]> a, int izq, int der,int parque,boolean objetos) throws SQLException {
        int i = izq;
        int j = der;
        Parque PE=new Parque();
        int pivote=0;
        int n=7;
        if (PE.isPosicion(parque, a.get( (izq + der) / 2)[0])){
            pivote = PE.NPosicion(parque, a.get( (izq + der) / 2)[0]);
        }

        do {
            while (PE.NPosicion(parque,a.get(i)[0])<pivote) i++;
            while (PE.NPosicion(parque,a.get(j)[0])>pivote) j--;
            if (i <= j) {
                if (objetos){n=6;}
                String[] aux = new String[n];
                String[] pj = new String[n];
                for (int h=0;h<n;h++){
                    aux[h]=a.get(i)[h];
                }
                for (int h=0;h<n;h++){
                    pj[h]=a.get(j)[h];
                }
                a.set(i,pj);
                a.set(j, aux);
                i++;
                j--;
            }
        }
        while (i <= j);
        if (izq < j) {
          ordenar(a, izq, j,parque,objetos);
        }
        if (i < der) {
          ordenar(a, i, der,parque,objetos);
        }
      }
     
// -----------------------------------------------------------------------------------------------//      
 
      // Crea un archivo de SV
   public String getArchivoSV(int asunto,int parque,ArrayList<ArrayList<String[]>> aero,ArrayList<ArrayList<String[]>> torre,ArrayList<String[]> objetos,String[] tr,String[] ag,ArrayList<double[]> sv,ArrayList<double[]> sectoresr,String binado,String ruta) throws SQLException, Exception{
       String salida="";   
       try {
           Asunto A=new Asunto();
           Parque PE=new Parque();
           Torre T=new Torre();
           String a="";
           String p="";
           String datum=PE.getDatum(parque);
           p=PE.getNombre(parque);
           if (asunto!=-1){
                a=A.getNombreCompleto(asunto);
                p=PE.getNombre(A.getParque(asunto));
                
           }
           
           int[] validos=new int[360];
           for (int grado=0;grado<360;grado++){
                validos[grado]=0;
                for (int j=0;j<sv.size();j++){
                    double linf=sv.get(j)[0];
                    double lsup=sv.get(j)[1];
                    if (linf<lsup){
                        if ((grado>=linf) && (grado<=lsup)){
                            validos[grado]=1;
                        }
                    } else{ // paso por cero
                        if (((grado>=linf) && (grado<=360)) || ((grado>=0) && (grado<=lsup))){
                            validos[grado]=1;
                        }
                    }
                }
           }
           System.out.println("asunto "+asunto);
           String[] tc=T.getTorre(asunto, "TC");
          
           
           // Ordenos las matrices
           ArrayList<String[]> tablaagord=new ArrayList<String[]>();
           for (int i=0;i<aero.get(0).size();i++){
               String[] dato=new String[7]; 
               for (int j=0;j<7;j++){
                    dato[j]=aero.get(0).get(i)[j];
               }
               tablaagord.add(i, dato);
           }
           ordenar(tablaagord,0,tablaagord.size()-1,parque,false);
           aero.set(0, tablaagord);
           
           ArrayList<String[]> tablatrord=new ArrayList<String[]>();
           for (int i=0;i<torre.get(0).size();i++){
               String[] dato=new String[7]; 
               for (int j=0;j<7;j++){
                    dato[j]=torre.get(0).get(i)[j];
               }
               tablatrord.add(i, dato);
           }
           if (tablatrord.size()!=0){
                ordenar(tablatrord,0,tablatrord.size()-1,parque,false);
                torre.set(0, tablatrord);
           }
               
           
           ordenar(objetos,0,objetos.size()-1,parque,true);
           torre.set(0, tablatrord);
           
           salida=escribirExcelSV(asunto,aero,torre,objetos,tr,tc,ag,sv,sectoresr,a,p,validos,binado,datum,ruta);           
       } catch (SQLException e){    
            e.printStackTrace();
       }
       return salida;
  }
   
// -----------------------------------------------------------------------------------------------//         
   
    // Escribe en el Excel plantilla SV
    public String escribirExcelSV(int asunto,ArrayList<ArrayList<String[]>> aero,ArrayList<ArrayList<String[]>> torre,ArrayList<String[]> objetos,String[] tr,String[] tc,String[] ag,ArrayList<double[]> sv,ArrayList<double[]> sectoresr,String a, String p,int[] validos,String binado,String datum,String ruta) throws BiffException, SQLException, IOException, WriteException { 
        WritableWorkbook workbook=null;
        Workbook wbook=null;
        try {
            String archivo="";
            archivo="\\\\B2Solar\\Datos\\Curva\\Archivos\\SV.xls";
            InputStream in=new FileInputStream(archivo); // Plantilla
            OutputStream  out=new FileOutputStream(ruta);
            wbook = Workbook.getWorkbook(in);
            workbook =Workbook.createWorkbook(out,wbook);   
            
            WritableSheet hojatr = workbook.getSheet("NO-VÁLIDOS-TR"); 
            WritableSheet hojaag = workbook.getSheet("NO-VÁLIDOS-AG"); 
            WritableSheet IN = workbook.getSheet("GCP-IN"); 
            WritableSheet hoja2 = workbook.getSheet("VÁLIDOS"); 
            WritableSheet hojai = workbook.getSheet("IN"); 
            
            // Formatos
            NumberFormat twodps = new NumberFormat("0.00");
            WritableCellFormat f2 = new WritableCellFormat(twodps);
            f2.setBackground(Colour.WHITE);
            f2.setAlignment(Alignment.CENTRE);
            
            NumberFormat onedps = new NumberFormat("0.0");
            WritableCellFormat f1 = new WritableCellFormat(onedps);
            f1.setBackground(Colour.WHITE);
            f1.setAlignment(Alignment.CENTRE);
            
            NumberFormat fourdps = new NumberFormat("0.0000");
            WritableCellFormat f4 = new WritableCellFormat(fourdps);
            f4.setBackground(Colour.WHITE);
            f4.setAlignment(Alignment.CENTRE);
            
            WritableCellFormat f = new WritableCellFormat();
            f.setBackground(Colour.WHITE);
            f.setAlignment(Alignment.CENTRE);
            
            WritableCellFormat ff = new WritableCellFormat();
            ff.setBackground(Colour.WHITE);
            ff.setBorder(Border.ALL,BorderLineStyle.THIN);
            ff.setAlignment(Alignment.CENTRE);
            
            NumberFormat zerodps = new NumberFormat("0");
            WritableFont arial12 = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD, true); 
            WritableCellFormat f0 = new WritableCellFormat(arial12,zerodps); 
            f0.setBackground(Colour.WHITE);
            f0.setAlignment(Alignment.CENTRE);
            f0.setBorder(Border.TOP,BorderLineStyle.THIN);
            f0.setBorder(Border.RIGHT,BorderLineStyle.THIN);
            f0.setAlignment(Alignment.CENTRE);
           
            // Escribir resultados aero
            for (int j=0;j<aero.get(0).size();j++){
                hojaag.addCell(new jxl.write.Label(3, 10+j, aero.get(0).get(j)[0],f)); 
                hojaag.addCell(new jxl.write.Number(4, j+10, Double.parseDouble(aero.get(0).get(j)[2]),f2));
                hojaag.addCell(new jxl.write.Number(5, j+10, Double.parseDouble(aero.get(0).get(j)[1]),f2));
                for (int i=2;i<6;i++){
                    hojaag.addCell(new jxl.write.Number(4+i, j+10, Double.parseDouble(aero.get(0).get(j)[1+i]),f2));
                }
            }
            
            for (int j=0;j<aero.get(1).size();j++){
                hojaag.addCell(new jxl.write.Number(13, j+10, Double.parseDouble(aero.get(1).get(j)[0]),f2));
                hojaag.addCell(new jxl.write.Number(14, j+10, Double.parseDouble(aero.get(1).get(j)[1]),f2));
            }
            
            for (int i=0;i<4;i++){
                hojaag.addCell(new jxl.write.Label(i+7, 6, ag[i],f));
            }
            
            
            // Escribir resultados torre
            for (int j=0;j<torre.get(0).size();j++){
                hojatr.addCell(new jxl.write.Label(3, 10+j, torre.get(0).get(j)[0],f)); 
                hojatr.addCell(new jxl.write.Number(4, j+10, Double.parseDouble(torre.get(0).get(j)[2]),f2));
                hojatr.addCell(new jxl.write.Number(5, j+10, Double.parseDouble(torre.get(0).get(j)[1]),f2));
                for (int i=2;i<6;i++){
                    hojatr.addCell(new jxl.write.Number(4+i, j+10, Double.parseDouble(torre.get(0).get(j)[1+i]),f2));
                }
            } 
            
            for (int j=0;j<torre.get(1).size();j++){
                hojatr.addCell(new jxl.write.Number(13, j+10, Double.parseDouble(torre.get(1).get(j)[0]),f2));
                hojatr.addCell(new jxl.write.Number(14, j+10, Double.parseDouble(torre.get(1).get(j)[1]),f2));
            }
            double xag=Double.parseDouble(ag[1]);
            double yag=Double.parseDouble(ag[2]);
            for (int i=0;i<4;i++){
                hojatr.addCell(new jxl.write.Label(i+7, 6, tr[i],f));
            }
            
            double xtr=Double.parseDouble(tr[1]);
            double ytr=Double.parseDouble(tr[2]);
            hojatr.addCell(new jxl.write.Number(12, 6, java.lang.Math.sqrt(java.lang.Math.pow(xag-xtr, 2)+java.lang.Math.pow(yag-ytr, 2)),f2));
            
            // Escribir datum
            IN.addCell(new jxl.write.Label(8, 3, datum,f));
            // Escribir obstaculos
            for (int j=0;j<objetos.size();j++){
                for (int i=0;i<6;i++){
                   IN.addCell(new jxl.write.Label(4+i, 7+j, objetos.get(j)[i],f)); 
                }
            } 
            if ((!tc[0].equals("")) &&(!tc[1].equals("")) ){
                IN.addCell(new jxl.write.Label(5, 3, tc[0],f)); 
                IN.addCell(new jxl.write.Label(6, 3, tc[1],f)); 
                IN.addCell(new jxl.write.Label(7, 3, tc[2],f)); 
               
                double x=Double.parseDouble(tc[0]);
                double y=Double.parseDouble(tc[1]);
                
                IN.addCell(new jxl.write.Number(9, 3, java.lang.Math.sqrt(java.lang.Math.pow(x-xag, 2)+java.lang.Math.pow(y-yag, 2)),f2)); 
            }
            IN.addCell(new jxl.write.Label(2, 3, a,f)); 
            IN.addCell(new jxl.write.Label(11, 3, p,f)); 
            
            // Sector valido final
            for (int j=0;j<sv.size();j++){
                hoja2.addCell(new jxl.write.Number(7, j+7, sv.get(j)[0],f2));
                hoja2.addCell(new jxl.write.Number(8, j+7, sv.get(j)[1],f2));
            }
            // Sector valido final redondeado
            for (int j=0;j<sectoresr.size();j++){
                hoja2.addCell(new jxl.write.Number(10, j+8, sectoresr.get(j)[0],f1));
                hoja2.addCell(new jxl.write.Number(11, j+8, sectoresr.get(j)[1],f1));
            }
            hoja2.addCell(new jxl.write.Label(11, 6, binado,ff));
            for (int i=0;i<360;i++){
                hojai.addCell(new jxl.write.Number(1, 1+i, validos[i]));
            }
            
            hojai.addCell(new jxl.write.Label(3, 1, ag[0]));     
            
            workbook.write(); 
            workbook.close(); 
            wbook.close();
            in.close();
            out.close();
        } catch (IOException e)   {  
             System.out.println("Error al crear el fichero"); 
             return ("Error al crear el archivo");     
        } catch (WriteException e) {   
              System.out.println("Error al escribir el fichero.");
              return ("Error al escribir en archivo");
        } 
        return ("Archivo generado correctamente");
    }
}