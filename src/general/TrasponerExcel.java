/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package general;

import java.io.File;
import java.io.FileWriter;
import jxl.Sheet;
import jxl.Workbook;

/**
 *
 * @author Victor Fernandez
 */
public class TrasponerExcel {
    public static void leerArchivoExcel(String fileExcel, String fileExport) {
        try {
            Workbook archivoExcel = Workbook.getWorkbook(new File(fileExcel));
            FileWriter fileWriter = new FileWriter(fileExport, true);

            for (int sheetNo = 0; sheetNo < archivoExcel.getNumberOfSheets(); sheetNo++) { // Recorre cada hoja                                                                                                                                                       
                Sheet hoja = archivoExcel.getSheet(sheetNo);
                int numColumnas = hoja.getColumns();
                int numFilas = hoja.getRows();
                String data;
                
                if (sheetNo == 0) {
                    for (int fila = 0; fila < numFilas; fila++) { // Recorre cada fila de la hoja 
                        for (int columna = 0; columna < numColumnas; columna++) { // Recorre cada columna de la hoja
                            data = hoja.getCell(columna, fila).getContents();
                            
                            if (data == null || data.length() == 0)
                                continue;
                            
                            fileWriter.write(data.replace(",", ".") + ";");
                        }
                        fileWriter.write("\r\n");
                    }
                } else if (sheetNo == 1) {
                    for (int columna = 0; columna < numColumnas; columna++) { // Recorre cada columna de la hoja
                        for (int fila = 0; fila < numFilas; fila++) { // Recorre cada fila de la hoja 
                            data = hoja.getCell(columna, fila).getContents();
                            
                            if (data == null || data.length() == 0)
                                continue;
                            
                            if (fila == 1)
                                fileWriter.write(";;;;;;;");
                            
                            fileWriter.write(data.replace(",", ".") + ";");
                        }
                        fileWriter.write("\r\n");
                    }
                }
            }
            
            fileWriter.flush();
            fileWriter.close();
        } catch (Exception ioe) {
            ioe.printStackTrace();
        }
    }
}

