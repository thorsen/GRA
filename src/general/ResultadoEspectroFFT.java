/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package general;

import java.util.ArrayList;

/**
 *
 * @author Victor Fernandez
 */
public class ResultadoEspectroFFT {
    private String xmlEspectro;
    private ArrayList<ResultadoBandaCriticaFFT> resBandaCritica;
    
    public ResultadoEspectroFFT(String xmlEspectro, ArrayList<ResultadoBandaCriticaFFT> resBandaCritica) {
        this.xmlEspectro = xmlEspectro;
        this.resBandaCritica = resBandaCritica;
    }

    public ArrayList<ResultadoBandaCriticaFFT> getResBandaCritica() {
        return resBandaCritica;
    }

    public void setResBandaCritica(ArrayList<ResultadoBandaCriticaFFT> resBandaCritica) {
        this.resBandaCritica = resBandaCritica;
    }

    public String getXmlEspectro() {
        return xmlEspectro;
    }

    public void setXmlEspectro(String xmlEspectro) {
        this.xmlEspectro = xmlEspectro;
    }    
}
