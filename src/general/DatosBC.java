package general;

import java.util.ArrayList;
import java.util.Arrays;

public class DatosBC {
    private Integer valBin;
    private Integer valEsp;
    private Integer valBC;
    private String clave;
    private Double[][] bandaCriticaClasificada;
    private Double[][] bandaCriticaRF;
    
    public DatosBC(Integer valBin, Integer valEsp, Integer valBC, Double[][] bandaCriticaClasificada, Double[][] bandaCriticaRF) {
        this.valBin = valBin;
        this.valEsp = valEsp;
        this.valBC = valBC;
        this.clave = creaClave(valBin, valEsp, valBC);
        this.bandaCriticaClasificada = bandaCriticaClasificada;
        this.bandaCriticaRF = bandaCriticaRF;
    }
    
    public static DatosBC[] getDatosBC(ArrayList<Object[]> datosBC) {
        DatosBC[] res = null;
        Object[] datoBC;
        int nDatos = datosBC != null ? datosBC.size() : 0;
        
        if (nDatos > 0) {
            res = new DatosBC[nDatos];
            
            for (int i = 0; i < nDatos; i++) {
                datoBC = datosBC.get(i);
                
                res[i] = new DatosBC((Integer) datoBC[0], (Integer) datoBC[1], (Integer) datoBC[2], (Double[][]) datoBC[3], (Double[][]) datoBC[4]);
            }
        }
        
        return res;
    }
    
    private static String creaClave(Integer valBin, Integer valEsp, Integer valBC) {
        return String.format("%02d%02d%02d", valBin, valEsp, valBC);
    }
    
    public static int localizaDato(DatosBC[] datosBC, Integer valBin, Integer valEsp, Integer valBC) {
        int nDatos = datosBC.length;
        String[] claves = new String[nDatos];
        
        for (int i = 0; i < nDatos; i++) {
            claves[i] = datosBC[i].clave;
        }
        
        return Arrays.binarySearch(claves, creaClave(valBin, valEsp, valBC));
    }
    
    public Double[][] getBandaCriticaClasificada() {
        return bandaCriticaClasificada;
    }

    public void setBandaCriticaClasificada(Double[][] bandaCriticaClasificada) {
        this.bandaCriticaClasificada = bandaCriticaClasificada;
    }

    public Double[][] getBandaCriticaRF() {
        return bandaCriticaRF;
    }

    public void setBandaCriticaRF(Double[][] bandaCriticaRF) {
        this.bandaCriticaRF = bandaCriticaRF;
    }

    public Integer getValBC() {
        return valBC;
    }

    public void setValBC(Integer valBC) {
        this.valBC = valBC;
    }

    public Integer getValBin() {
        return valBin;
    }

    public void setValBin(Integer valBin) {
        this.valBin = valBin;
    }

    public Integer getValEsp() {
        return valEsp;
    }

    public void setValEsp(Integer valEsp) {
        this.valEsp = valEsp;
    }
}
