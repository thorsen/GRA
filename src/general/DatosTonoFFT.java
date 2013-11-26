package general;

import RA.DatosRA2;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import javax.swing.JProgressBar;

public class DatosTonoFFT implements Comparable<DatosTonoFFT> {
    private Integer valBin;
    private Integer valEsp;
    private Integer valBC;
    private String clave;
    private Double frecMax;
    private Integer posClasificacion;
    private Double tonalidad;
    
    private static Integer modoOrdenacion;
    public static final Integer ORD_BIN_TONO = 1;
    public static final Integer ORD_FREC_MAX = 2;
    
    public DatosTonoFFT(Integer valBin, Integer valEsp, Integer valBC, Double frecMax, Integer posClasificacion) {
        this.valBin = valBin;
        this.valEsp = valEsp;
        this.valBC = valBC;
        this.clave = creaClave(valBin, valEsp, valBC);
        this.frecMax = frecMax;
        this.posClasificacion = posClasificacion;
    }
    
    private static String creaClave(Integer valBin, Integer valEsp, Integer valBC) {
        String clave = "";
        
        if (valBin != null)
            clave += String.format("%02d", valBin);
        if (valEsp != null)
            clave += String.format("%02d", valEsp);
        if (valBC != null)
            clave += String.format("%02d", valBC);
        
        return clave;
    }
    
    public static String[] getClaves(DatosTonoFFT[] datos, Integer largo) {
        int nDatos = datos.length;
        String[] claves = new String[nDatos];
        
        if (largo != null) {
            for (int i = 0; i < nDatos; i++) {
                claves[i] = datos[i].clave.substring(0, largo);
            }
        } else{
            for (int i = 0; i < nDatos; i++) {
                claves[i] = datos[i].clave;
            }
        }
        
        return claves;
    }
    
    public static Integer[] getBines(DatosTonoFFT[] datos) {
        Integer[] bines = null;
        
        int nDatos = datos != null ? datos.length : 0;
        
        if (nDatos > 0) {
            ArrayList<Integer> arrayBines = new ArrayList<Integer>();

            for (int i = 0; i < nDatos; i++) {
                if (!arrayBines.contains(datos[i].valBin))
                    arrayBines.add(datos[i].valBin);
            }
            
            if (arrayBines.size() > 0)
                bines = Auxiliares.arrayObjToInteger(arrayBines.toArray());
        }
        
        return bines;
    }
    
    public static Object[][] getFrecPosDeBinEsp(DatosTonoFFT[] datos, Integer valBin, Integer valEsp) {
        int nDatos = datos.length;
        
        ArrayList<Object[]> resAux = new ArrayList<Object[]>();
        
        for (int i = 0; i < nDatos; i++) {
            if (valBin.equals(datos[i].valBin) && valEsp.equals(datos[i].valEsp))
                resAux.add(new Object[]{datos[i].frecMax, datos[i].posClasificacion});
        }
        
        return Auxiliares.arrayObjToObjObj(resAux.toArray());
    }
    
    public static int localizaDato(DatosTonoFFT[] datos, Integer valBin, Integer valEsp, Integer valBC) {
        return Arrays.binarySearch(getClaves(datos, null), creaClave(valBin, valEsp, valBC));
    }
    
    public static int localizaDatoBinEspFrecMax(DatosTonoFFT[] datos, Integer valBin, Integer valEsp, Double frecMax) {
        int res = -1;
        int nDatos = datos.length;
        
        String[] claves = getClaves(datos, null);
        String clave = creaClave(valBin, valEsp, 1);
        
        //Buscamos la posición de inicio del Bin - Espectro para ahorrar tiempo
        int posIniBusqueda = Arrays.binarySearch(claves, clave);
        
        if (posIniBusqueda < 0)
            posIniBusqueda = 0;
        
        for (int i = posIniBusqueda; i < nDatos; i++) {
            if (datos[i].valBin < valBin)
                continue;
            else if (datos[i].valBin > valBin)
                break;
            if (datos[i].valEsp < valEsp)
                continue;
            else if (datos[i].valEsp > valEsp)
                break;
            
            if (frecMax.equals(datos[i].frecMax))
                res = i;
        }
        
        return res;
    }
    
    public static boolean hayVariosBines(DatosTonoFFT[] datos) {
        boolean res = false;
        int nBines = 0;
        Integer binAnt = null;
         
        Integer[] bines = getBines(datos);
        
        int nDatos = bines != null ? bines.length : 0;
        
        for (int i = 0; i < nDatos; i++) {
            if (binAnt == null || !binAnt.equals(bines[i])) {
                binAnt = bines[i];
                
                nBines++;
                
                if (nBines > 1)
                    res = true;
            }
        }
        
        return res;
    }
    
    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Double getFrecMax() {
        return frecMax;
    }

    public void setFrecMax(Double frecMax) {
        this.frecMax = frecMax;
    }
    
    public static Integer getModoOrdenacion() {
        return modoOrdenacion;
    }

    public static void setModoOrdenacion(Integer modoOrd) {
        modoOrdenacion = modoOrd;
    }

    public Integer getPosClasificacion() {
        return posClasificacion;
    }

    public void setPosClasificacion(Integer posClasificacion) {
        this.posClasificacion = posClasificacion;
    }

    public Double getTonalidad() {
        return tonalidad;
    }

    public void setTonalidad(Double tonalidad) {
        this.tonalidad = tonalidad;
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
    
    public static DatosTonoFFT[] eliminaTono(DatosTonoFFT[] datos, Integer valBin, int posCol) {
        DatosTonoFFT[] res = null;
        
        int nDatos = datos.length;
        
        if (nDatos > 0) {
            ArrayList<DatosTonoFFT> resAux = new ArrayList<DatosTonoFFT>();
            DatosTonoFFT dato;
            Integer posClasificacion;

            for (int i = 0; i < nDatos; i++) {
                dato = datos[i];
                if (valBin.equals(dato.valBin)) {
                    posClasificacion = dato.posClasificacion;
                    
                    if (posClasificacion != null) {
                        if (posClasificacion == posCol)
                            dato.setPosClasificacion(null);
                        else if (posClasificacion > posCol)
                            dato.setPosClasificacion(posClasificacion - 1);
                    }
                }
                
                resAux.add(dato);
            }
            
            int nRes = resAux.size();
            res = new DatosTonoFFT[nRes];
            
            for (int i = 0; i < nRes; i++) {
                res[i] = resAux.get(i);
            }
        }
        
        return res;
    }
    
    public static int getNumTonosBin(DatosTonoFFT[] datos, Integer valBin) {
        int nTonos = 0;
        Integer posClasificacion; 
        
        int nDatos = datos.length;
        
        for (int i = 0; i < nDatos; i++) {
            if (datos[i].valBin < valBin)
                continue;
            if (datos[i].valBin > valBin)
                break;
            
            posClasificacion = datos[i].posClasificacion;
            
            if (posClasificacion != null && posClasificacion > nTonos)
                nTonos = posClasificacion;
        }
        
        return nTonos;
    }
    
    public static Integer[] getEspectrosBin(DatosTonoFFT[] datos, Integer valBin) {
        Integer[] espectros = null;
        
        int nDatos = datos != null ? datos.length : 0;
        
        if (nDatos > 0) {
            ArrayList<Integer> arrayEspectros = new ArrayList<Integer>();

            for (int i = 0; i < nDatos; i++) {
                if (datos[i].valBin < valBin)
                    continue;
                if (datos[i].valBin > valBin)
                    break;
                
                if (!arrayEspectros.contains(datos[i].valEsp))
                    arrayEspectros.add(datos[i].valEsp);
            }
            
            if (arrayEspectros.size() > 0)
                espectros = Auxiliares.arrayObjToInteger(arrayEspectros.toArray());
        }
        
        return espectros;
    }

    public int compareTo(DatosTonoFFT dato) {
        int res = 0;
        
        String clv1 = null;
        String clv2 = null;
        
        if (modoOrdenacion.equals(ORD_BIN_TONO)) {
            int posClasificacion1 = this.posClasificacion != null ? this.posClasificacion : -1;
            int posClasificacion2 = dato.posClasificacion != null ? dato.posClasificacion : -1;
                            
            clv1 = String.format("%02d%02d", this.valBin, posClasificacion1);
            clv2 = String.format("%02d%02d", dato.valBin, posClasificacion2);
        } else if (modoOrdenacion.equals(ORD_FREC_MAX)) {
            clv1 = String.format("%18.5f", this.frecMax);
            clv2 = String.format("%18.5f", dato.frecMax);
        }
        
        if (clv1 != null && clv2 != null)
            res =  clv1.compareTo(clv2);
        
        return res;
    }
    
    public static Comparator<DatosTonoFFT> DatosFrecMaxComparator = new Comparator<DatosTonoFFT>() {
        public int compare(DatosTonoFFT dato1, DatosTonoFFT dato2) {
            return dato1.compareTo(dato2);
        }
    };
    
    public static DatosTonoFFT[] ordenarPor(DatosTonoFFT[] datos, Integer modo) {
        setModoOrdenacion(modo);
        
        Arrays.sort(datos, DatosTonoFFT.DatosFrecMaxComparator);
        
        return datos;
    }
            
    public static DatosTonoFFT[] getArrayFromArrayList(ArrayList<DatosTonoFFT> datos) {
        DatosTonoFFT[] res = null;
        int nDatos = datos.size();
        
        if (nDatos > 0) {
            res = new DatosTonoFFT[nDatos];

            for (int i = 0; i < nDatos; i++) {
                res[i] = datos.get(i);
            }
        }
        
        return res;
    }
        
    public static DatosTonoFFT[] getDatosMaximoBCBin(DatosTonoFFT[] datos, Integer valBin) {
        DatosTonoFFT[] res = null;
        
        int nDatos = datos.length;
        
        String[] claves = getClaves(datos, null);
        String clave = creaClave(valBin, 1, 1);
        
        //Buscamos la posición de inicio del Bin - Espectro para ahorrar tiempo
        int posIniBusqueda = Arrays.binarySearch(claves, clave);
        
        if (posIniBusqueda < 0)
            posIniBusqueda = 0;
        
        ArrayList<DatosTonoFFT> resAux = new ArrayList<DatosTonoFFT>();
        int nDatosArray;
        boolean haySimilar;
        
        for (int i = posIniBusqueda; i < nDatos; i++) {
            if (datos[i].valBin < valBin)
                continue;
            else if (datos[i].valBin > valBin)
                break;
            
            //No tenemos en cuenta aquellos que difieran menos de un 10% con los ya guardados
            nDatosArray = resAux.size();
            haySimilar = false;
            
            for (int j = 0; j < nDatosArray; j++) {
                if (Math.abs(datos[i].frecMax - resAux.get(j).getFrecMax()) <= datos[i].frecMax * 0.1)
                    haySimilar = true;
            }
            
            if (!haySimilar)
                resAux.add(datos[i]);
        }
            
        nDatosArray = resAux.size();
        if (nDatosArray > 0){
            res = new DatosTonoFFT[nDatosArray];

            //Lo recorremos al revés para devolverlo ordenado
            for (int i = 0; i < nDatosArray; i++) {
                res[i] = resAux.get(i);
            }
            
            //Reordenamos
            res = ordenarPor(res, ORD_FREC_MAX);
        }
        
        return res;
    }
    
    public static DatosTonoFFT[] getDatosCalcIncertRF(Integer idAsunto, Integer idSite, Integer valBinMin, Integer valBinMax, DatosTonoFFT[] datos, JProgressBar jpb) throws SQLException, NoSuchFieldException, Exception { //Datos viene ordenado por Bin-Tono
        DatosTonoFFT[] res = null;
        ArrayList<DatosTonoFFT> resAux = null;
        DatosTonoFFT dato, datoAux;
        int nDatos = datos != null ? datos.length : 0;
        
        if (nDatos > 0) {
            resAux = new ArrayList<DatosTonoFFT>();
            
            Integer valBin = null, valBinAnt = null;

            ArrayList<Double[][]> espectrosRF = null;
            int nEspectrosRF = -1;

            ArrayList<Object[]> resultadosFFT_RF = DatosRA2.getResultadosFFT(idAsunto, idSite, 1, valBinMin, valBinMax);

            for (int i = 0; i < nDatos; i++) {
                dato = datos[i];

                valBin = dato.valBin;
                
                if (dato.getPosClasificacion() == null)
                        continue;
                
                if (valBinAnt == null || !valBin.equals(valBinAnt)) {
                    valBinAnt = valBin;
                    
                    espectrosRF = DatosRA2.getEspectrosFFTBin(resultadosFFT_RF, valBin);
                    nEspectrosRF = espectrosRF.size();
                }
                
                for (int j = 0; j < nEspectrosRF; j++) {
                    datoAux = new DatosTonoFFT(dato.valBin, dato.valEsp, dato.valBC, dato.frecMax, dato.posClasificacion);
                    datoAux.tonalidad =  DatosRA2.getNivelPromedioEnmascaramiento(DatosRA2.getBandaCritica(espectrosRF.get(j), DatosRA2.getLimBandaCritica(datoAux.frecMax)), null);
                    resAux.add(datoAux);
                }
                
                Auxiliares.incPorcentajeProgress(jpb, 0.75 * (i + 1) / nDatos);
            }
            
            int nRes = resAux.size();
            
            if (nRes > 0) {
                res = new DatosTonoFFT[nRes];
                for (int i = 0; i < nRes; i++) {
                    res[i] = resAux.get(i);
                }
            }
        }

        return res;
    }
}
