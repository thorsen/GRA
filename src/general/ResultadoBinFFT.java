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
public class ResultadoBinFFT {
    private Integer bin;
    private ArrayList<ResultadoEspectroFFT> resEspectro;
    
    public ResultadoBinFFT(Integer bin, ArrayList<ResultadoEspectroFFT> resEspectro) {
        this.bin = bin;
        this.resEspectro = resEspectro;
    }

    public Integer getBin() {
        return bin;
    }

    public void setBin(Integer bin) {
        this.bin = bin;
    }

    public ArrayList<ResultadoEspectroFFT> getResEspectro() {
        return resEspectro;
    }

    public void setResEspectro(ArrayList<ResultadoEspectroFFT> resEspectro) {
        this.resEspectro = resEspectro;
    }
}
