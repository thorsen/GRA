/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package general;

import RA.AsuntoIncert;
import RA.NormaRA;
import RA.TipoIncert;
import java.util.ArrayList;

/**
 *
 * @author Victor Fernandez
 */
public class DatosIncertidumbre {
    private ArrayList<AsuntoIncert> incertidumbresB;
    private Double uA;
    private Double uC;
    
    
    public DatosIncertidumbre(ArrayList<AsuntoIncert> incertidumbres) {
        this.incertidumbresB = (ArrayList<AsuntoIncert>) incertidumbres.clone();
    }
    
    public Double getUA() {
        return uA;
    }

    public void setUA(Double uA) {
        this.uA = uA;
    }

    public Double getUB9() {
        Double res = null;
        
        int nIncerts = this.incertidumbresB != null ? this.incertidumbresB.size() : 0;
        
        if (nIncerts > 0) {
            Long fechaIni = this.incertidumbresB.get(0).getDesdeFecha();
            
            AsuntoIncert asuntoIncert;
            for (int i = 0; i < nIncerts; i++) {
                asuntoIncert = this.incertidumbresB.get(i);
                
        
                //Nos quedaremos solo con la primera configuración de incertidumbres establecida (excepto para IEC 3.0
                if (!asuntoIncert.getIdNorma().equals(NormaRA.ID_NORMA_IEC_3_0)) {
                    if (!fechaIni.equals(asuntoIncert.getDesdeFecha()))
                        break;

                    //Ignoramos la uB9 de IEC 2.1 porque la calculamos nosotros
                    if (asuntoIncert.getIdTipoIncert().equals(TipoIncert.ID_TIPO_INCERT_RF)) {
                        res = asuntoIncert.getValor();
                        break;
                    }
                }
            }
        }
        
        return res;
    }
    
    public void setUB9(Double uB9) {
        int nIncerts = this.incertidumbresB != null ? this.incertidumbresB.size() : 0;
        
        if (nIncerts > 0) {
            AsuntoIncert asuntoIncert = this.incertidumbresB.get(0);
            Integer idAsunto = asuntoIncert.getIdAsunto();
            Long desdeFecha = asuntoIncert.getDesdeFecha();
            Integer idNorma = asuntoIncert.getIdNorma();
            
            this.incertidumbresB.add(new AsuntoIncert(idAsunto, desdeFecha, idNorma, TipoIncert.ID_TIPO_INCERT_RF, uB9));
        }
    }
    
    public Double getUC() {
        return uC;
    }

    public void setUC(Double uC) {
        this.uC = uC;
    }
    
    public Double calculaUGenerico(String tipoTabla, ArrayList<Double> datosMedidos, ArrayList<Double> datosEstimados, Boolean fftEnMismaFrecuencia) {
        Double res = null;
        int nDatos = datosMedidos != null ? datosMedidos.size() : 0;
        Double numerador = 0.0;
        Integer denominador;
        
        if (nDatos > 0) {
            for (int i = 0; i < nDatos; i++) {
                numerador = numerador + Math.pow(datosMedidos.get(i) - datosEstimados.get(i), 2);
            }
            
            if (tipoTabla.contentEquals(Auxiliares.TIPO_OCT)) {
                denominador = nDatos - 1;
            } else if (tipoTabla.contentEquals(Auxiliares.TIPO_SPL) || tipoTabla.contentEquals(Auxiliares.TIPO_FFT)) {
                

                denominador = nDatos - 2;
                
                if (fftEnMismaFrecuencia != null && fftEnMismaFrecuencia) {
                    denominador = nDatos - 1;
                }
            } else
                denominador = 0;

            if (denominador != 0)
                res = Math.sqrt(numerador / denominador);
        }
        
        return res;
    }
    
    public Double calculaUC(Integer idNorma, String tipoTabla, ArrayList<Double> datosMedidos, ArrayList<Double> datosEstimados, ArrayList<Double> datosMedidosRF, ArrayList<Double> datosEstimadosRF, Boolean fftEnMismaFrecuencia) {
        this.uA = calculaUGenerico(tipoTabla, datosMedidos, datosEstimados, fftEnMismaFrecuencia);
        
        //Añadimos la incertidumbre calculada por RF
        if (!idNorma.equals(NormaRA.ID_NORMA_IEC_3_0)) {
            Double uB9 = calculaUGenerico(tipoTabla, datosMedidosRF, datosEstimadosRF, fftEnMismaFrecuencia);
            setUB9(uB9);
        }
        
        int nIncerts = this.incertidumbresB != null ? this.incertidumbresB.size() : 0;
        
        Double sumaCuad = null;
        
        if (nIncerts > 0) {
            Long fechaIni = idNorma.equals(NormaRA.ID_NORMA_IEC_3_0) ? null : this.incertidumbresB.get(0).getDesdeFecha();
            
            Double valor;
            sumaCuad = 0.0;
            AsuntoIncert asuntoIncert;
            for (int i = 0; i < nIncerts; i++) {
                asuntoIncert = this.incertidumbresB.get(i);
                
                //Nos quedaremos solo con la primera configuración de incertidumbres establecida (excepto para IEC 3.0
                if (fechaIni != null && !fechaIni.equals(asuntoIncert.getDesdeFecha()))
                        break;
               
                valor = asuntoIncert.getValor();
                if (valor != null)
                    sumaCuad += Math.pow(valor, 2.0);
            }
        }
        
        if (this.uA != null && sumaCuad != null)
            this.uC = Math.sqrt(Math.pow(this.uA, 2.0) + sumaCuad);
        
        return this.uC;
    }
    
    public Double getIncertidumbreEstandar(Double value) {
        return value / Math.sqrt(3);
    }
    
    public Double getRangoTipico(Double value) {
        return value * Math.sqrt(3);
    }
}