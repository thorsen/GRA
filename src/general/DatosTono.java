/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package general;

/**
 *
 * @author Victor Fernandez
 */
public class DatosTono {
    private Integer espectro;
    private Integer bandaCritica;
    private Double frecMax;
    private Double tonalidad;
    
    public DatosTono(Integer espectro, Integer bandaCritica, Double frecMax) {
        this.espectro = espectro;
        this.bandaCritica = bandaCritica;
        this.frecMax = frecMax;
    }

    public Integer getBandaCritica() {
        return bandaCritica;
    }

    public void setBandaCritica(Integer bandaCritica) {
        this.bandaCritica = bandaCritica;
    }

    public Integer geEspectro() {
        return espectro;
    }

    public void setEspectro(Integer espectro) {
        this.espectro = espectro;
    }

    public Double getFrecMax() {
        return frecMax;
    }

    public void setFrecMax(Double frecMax) {
        this.frecMax = frecMax;
    }
    
    public Double getTonalidad() {
        return tonalidad;
    }

    public void setTonalidad(Double tonalidad) {
        this.tonalidad = tonalidad;
    }
}