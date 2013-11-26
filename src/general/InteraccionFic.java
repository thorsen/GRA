/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package general;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Victor Fernandez
 */
public class InteraccionFic {
    private String ruta;
    private FileWriter fileWriter;
    private FileReader fileReader;
    private BufferedReader buffReader;
    
    public static String LINEA_SEP = "--------------------------------------------------------------------------------";
    public static String SALTO_LINEA = "\r\n";
    
    public static String WRITE = "W";
    public static String READ = "R";
    
    public static final String FORMATO_FECHA = "dd/MM/yyyy  HH:mm:ss";

    public InteraccionFic(String ruta, String operacion) throws IOException {
        this.ruta = ruta;
        iniOpFichero(ruta, operacion);
    }
    
    //Función para iniciar las operaciones con el fichero  
    public void iniOpFichero(String ruta, String operacion) throws IOException {
        if (operacion.contains(WRITE))
            this.fileWriter = new FileWriter(ruta, true);
        if (operacion.contains(READ)) {
            this.fileReader = new FileReader(ruta);
            this.buffReader = new BufferedReader(this.fileReader);
        }
    }
    
    //Función para acabar las operaciones con el fichero  
    public void finOpFichero() throws IOException {
        if (this.fileWriter != null) {
            this.fileWriter.flush();
            this.fileWriter.close();

            //Liberamos el fichero, porque ya está cerrado
            this.fileWriter = null;
        }
        
        if (this.fileReader != null) {
            this.buffReader.close();
            this.fileReader.close();

            //Liberamos el fichero, porque ya está cerrado
            this.fileReader = null;
        }
    }

    public void escribeFic(String texto) throws IOException {
        this.fileWriter.write(texto);
    }
    
    public void escribeLineaFic(String texto) throws IOException {
        this.fileWriter.write(texto + SALTO_LINEA);
    }
    
    public static boolean creaDir(String ruta) {
        File dir = new File(ruta);
        
        return dir.mkdir();
    }
    
    public static boolean renombraDir(String rutaOri, String rutaFin) {
        File dirOri = new File(rutaOri);
        File dirFin = new File(rutaFin);
        
        return dirOri.renameTo(dirFin);
    }
    
    //Función que devuelve la lista de canales de un fichero de RA
    public ArrayList<String> leeCanales(String sepCampos) throws IOException {
        ArrayList<String> res = new ArrayList<String>();
        String[] campos;
        int nCampos;
        String linea;
        
        this.fileReader.close();
        this.fileReader = new FileReader(this.ruta);
        this.buffReader = new BufferedReader(this.fileReader);
        
        while ((linea = this.buffReader.readLine()) != null) {
            campos = linea.split(sepCampos);
            nCampos = campos.length;
            
            if (nCampos == 0 || !campos[0].contentEquals("Time"))
                continue;
            
            for (int i = 0; i < nCampos; i++) {
                res.add(campos[i].trim());
            }
            
            break;
        }

        return res; 
    }
    
    //Función que devuelve la fechaHora de recogida de inicio de recogida de datos de un fichero RA
    public long leeFechaInicio(String sepCampos) throws IOException {
        long res = 0;
        String[] campos;
        String linea;
        
        this.fileReader.close();
        this.fileReader = new FileReader(this.ruta);
        this.buffReader = new BufferedReader(this.fileReader);
        
        while ((linea = this.buffReader.readLine()) != null) {
            if (!linea.trim().startsWith("Time of run"))
                continue;
            
            campos = linea.split(sepCampos);
            
            //Cogemos la fecha hora
            res = TratFechas.millisFechaGen(campos[1], FORMATO_FECHA);
            
            break;
        }

        return res; 
    }
    
    //Función que devuelve el conjunto de datos de un fichero RA junto con la fechaHora de recogida
    public ArrayList<ArrayList<String>> leeDatos(long fechaMillis, String sepCampos) throws IOException {
        ArrayList<ArrayList<String>> res = new ArrayList<ArrayList<String>>();
        ArrayList<String> fila;
        String[] campos;
        int nCampos;
        String linea;
        long tiempoMillis, tiempoMillisAnt;
        
        tiempoMillis = tiempoMillisAnt = 0;
        
        this.fileReader.close();
        this.fileReader = new FileReader(this.ruta);
        this.buffReader = new BufferedReader(this.fileReader);
        //Nos posicionamos en el primer valor válido
        while ((linea = this.buffReader.readLine()) != null) {
            if (linea.isEmpty())
                continue;
            
            campos = linea.split(sepCampos);
            
            //Si comienza por número, estamos en la primera fila de datos
            try {
                Double.parseDouble(campos[0]);
                break;
            } catch (ArrayIndexOutOfBoundsException e) {
                continue;
            } catch (NumberFormatException e) {
                continue;
            }
        }
        
        do {
            if (linea.isEmpty())
                continue;
            
            fila = new ArrayList<String>();
            
            campos = linea.split(sepCampos);
            nCampos = campos.length;
            
            //Si no comienza por número, pasamos a la siguiente línea
            try {
                Double.parseDouble(campos[0]);
            } catch (ArrayIndexOutOfBoundsException e) {
                continue;
            } catch (NumberFormatException e) {
                continue;
            }
            
            for (int i = 0; i < nCampos; i++) {
                if (i == 0) { //Es la columan de tiempos
                    tiempoMillis = (long) (new Double(campos[i].trim()) * 1000);
                    fechaMillis += tiempoMillis - tiempoMillisAnt; 
                    
                    fila.add(String.valueOf(fechaMillis));
                } else { //Canales
                    fila.add(campos[i].trim());
                }
                
                tiempoMillisAnt = tiempoMillis;
            }
            
            res.add(fila);
        } while ((linea = this.buffReader.readLine()) != null);

        return res; 
    }
    
    //Función que devuelve los campos leído de un fichero, separados por <sep> y con un número de campos esperados <nCamposEsp>
    public ArrayList<ArrayList<String>> leeCamposSep(String sep, int nCamposEsp) throws IOException {
        ArrayList<ArrayList<String>> res = new ArrayList<ArrayList<String>>();
        ArrayList<String> fila;
        String[] campos;
        int nCampos;
        String linea;
        
        while ((linea = this.buffReader.readLine()) != null) {
            if (linea.isEmpty())
                continue;
            
            fila = new ArrayList<String>();
            
            campos = linea.split(sep);
            nCampos = campos.length;
            
            //Si no existen exactamente los campos buscados, se para el bucle y devuelve únicamente una fila de error
            if (nCampos != nCamposEsp) {
                res.clear();
                
                for (int i = 0; i < nCamposEsp; i++) {
                    fila.add("mal");
                }
                
                res.add(fila);
                break;
            }
            
            for (int i = 0; i < nCampos; i++) {
                    fila.add(campos[i].trim());
            }
            
            res.add(fila);
        }

        return res; 
    }

    //Función que devuelve la línea leída
    public String leeLinea() throws IOException {
        return this.buffReader.readLine();
    }
}
