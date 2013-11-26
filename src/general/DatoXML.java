/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package general;

/**
 *
 * @author Victor Fernandez
 */
public class DatoXML {
        private Integer idDato;
        private Double frecuencia;
        private Double nivel;
    
        public DatoXML(Integer idDato, Double frecuencia, Double nivel) {
            this.idDato = idDato;
            this.frecuencia = frecuencia;
            this.nivel = nivel;
        }
        
        public Double getFrecuencia() {
            return frecuencia;
        }

        public void setFrecuencia(Double frecuencia) {
            this.frecuencia = frecuencia;
        }

        public Integer getIdDato() {
            return idDato;
        }

        public void setIdDato(Integer idDato) {
            this.idDato = idDato;
        }

        public Double getNivel() {
            return nivel;
        }

        public void setNivel(Double nivel) {
            this.nivel = nivel;
        }
    }