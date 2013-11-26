/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package general;

import RA.DatosRA2;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

/**
 *
 * @author Victor Fernandez
 */
public class ResultadoBandaCriticaFFT {
    private Double frecMax;
    private Double nivelFrecMax;
    private Double iniBandaCritica;
    private Double finBandaCritica;
    private Double nivelCriterio; //L_70% +6
    private Double nivelEnmascaramiento; //L_pn,avg
    private Double[][] bandaCriticaClasificada;
    private Double[][] bandaCriticaRF;
    
    public ResultadoBandaCriticaFFT(Double frecMax, Double nivelFrecMax, Double iniBandaCritica, Double finBandaCritica, Double nivelCriterio, Double nivelEnmascaramiento, Double[][] bandaCriticaClasificada, Double[][] bandaCriticaRF) {
        this.frecMax = frecMax;
        this.nivelFrecMax = nivelFrecMax;
        this.iniBandaCritica = iniBandaCritica;
        this.finBandaCritica = finBandaCritica;
        this.nivelCriterio = nivelCriterio;
        this.nivelEnmascaramiento = nivelEnmascaramiento;
        this.bandaCriticaClasificada = bandaCriticaClasificada;
        this.bandaCriticaRF = bandaCriticaRF;
    }
    
    public LinkedHashMap<Entry<Double, Double>, Integer> getMapBandaCriticaClasificada() {
        LinkedHashMap<Entry<Double, Double>, Integer> res = null;
        
        if (bandaCriticaClasificada != null) {
            res = new LinkedHashMap<Entry<Double, Double>, Integer>();
            LinkedHashMap<Double, Double> mapEntry;
            Entry<Double, Double> entry = null;
            Iterator it;
            
            int nDatos = bandaCriticaClasificada.length;
            
            for (int i = 0; i < nDatos; i++) {
                mapEntry = new LinkedHashMap<Double, Double>();
                mapEntry.put(bandaCriticaClasificada[i][0], bandaCriticaClasificada[i][1]);
                
                it = mapEntry.entrySet().iterator();
                while (it.hasNext()) {
                    entry = (Entry<Double, Double>) it.next();
                }
                
                res.put(entry, bandaCriticaClasificada[i][2].intValue());                
            }
        }
        
        return res;
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

    public Double getFinBandaCritica() {
        return finBandaCritica;
    }

    public void setFinBandaCritica(Double finBandaCritica) {
        this.finBandaCritica = finBandaCritica;
    }

    public Double getFrecMax() {
        return frecMax;
    }

    public void setFrecMax(Double frecMax) {
        this.frecMax = frecMax;
    }

    public Double getIniBandaCritica() {
        return iniBandaCritica;
    }

    public void setIniBandaCritica(Double iniBandaCritica) {
        this.iniBandaCritica = iniBandaCritica;
    }

    public Double getNivelCriterio() {
        return nivelCriterio;
    }
    
    public void setNivelCriterio(Double nivelCriterio) {
        this.nivelCriterio = nivelCriterio;
    }
    
    public Double getNivelEnmascaramiento() {
        return nivelEnmascaramiento;
    }
    
    public void setNivelEnmascaramiento(Double nivelEnmascaramiento) {
        this.nivelEnmascaramiento = nivelEnmascaramiento;
    }
    
    public Double getNivelFrecMax() {
        return nivelFrecMax;
    }
    
    public void setNivelFrecMax(Double nivelFrecMax) {
        this.nivelFrecMax = nivelFrecMax;
    }

    public Object[] toObject() {
        return new Object[]{this.frecMax, this.iniBandaCritica, this.finBandaCritica, DatosRA2.getNivelInc(this.nivelCriterio, -6.0), this.nivelCriterio, DatosRA2.getNivelInc(this.nivelEnmascaramiento, 6.0), DatosRA2.getNivelInc(this.nivelFrecMax, -10.0)};
    }
}
