/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package general;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Victor Fernandez
 */
public class ConfInsercionDatos {

    private Integer idConfig;
    private ArrayList<String> codigos;
    private ArrayList<String> nomVariables;
    private ArrayList<Integer> canales;
    private ArrayList<Double> slopes;
    private ArrayList<Double> offsets;
    private Double cotaT;
    private Double cotaP;
    private Double dec;
    private Double hB;
    private HashMap<String, Double> pondDbZtoDbA;
    
    public ConfInsercionDatos(Integer idConfig, ArrayList<String> codigos, ArrayList<String> nomVariables, ArrayList<Integer> canales, ArrayList<Double> slopes, ArrayList<Double> offsets, Double cotaT, Double cotaP, Double dec, Double hB, HashMap<String, Double> pondDbZtoDbA) {
        this.idConfig = idConfig;
        this.codigos = codigos;
        this.nomVariables = nomVariables;
        this.canales = canales;
        this.slopes = slopes;
        this.offsets = offsets;
        this.cotaT = cotaT;
        this.cotaP = cotaP;
        this.dec = dec;
        this.hB = hB;
        this.pondDbZtoDbA = pondDbZtoDbA;
    }
    
    public ArrayList<Integer> getCanales() {
        return canales;
    }

    public void setCanales(ArrayList<Integer> canales) {
        this.canales = canales;
    }

    public ArrayList<String> getCodigos() {
        return codigos;
    }

    public void setCodigos(ArrayList<String> codigos) {
        this.codigos = codigos;
    }

    public Double getCotaP() {
        return cotaP;
    }

    public void setCotaP(Double cotaP) {
        this.cotaP = cotaP;
    }

    public Double getCotaT() {
        return cotaT;
    }

    public void setCotaT(Double cotaT) {
        this.cotaT = cotaT;
    }

    public Double getDec() {
        return dec;
    }

    public void setDec(Double dec) {
        this.dec = dec;
    }

    public Double getHB() {
        return hB;
    }

    public void setHB(Double hB) {
        this.hB = hB;
    }

    public Integer getIdConfig() {
        return idConfig;
    }

    public void setIdConfig(Integer idConfig) {
        this.idConfig = idConfig;
    }

    public ArrayList<String> getNomVariables() {
        return nomVariables;
    }

    public void setNomVariables(ArrayList<String> nomVariables) {
        this.nomVariables = nomVariables;
    }

    public ArrayList<Double> getOffsets() {
        return offsets;
    }

    public void setOffsets(ArrayList<Double> offsets) {
        this.offsets = offsets;
    }

    public HashMap<String, Double> getPondDbZtoDbA() {
        return pondDbZtoDbA;
    }

    public void setPondDbZtoDbA(HashMap<String, Double> pondDbZtoDbA) {
        this.pondDbZtoDbA = pondDbZtoDbA;
    }

    public ArrayList<Double> getSlopes() {
        return slopes;
    }

    public void setSlopes(ArrayList<Double> slopes) {
        this.slopes = slopes;
    }
}
