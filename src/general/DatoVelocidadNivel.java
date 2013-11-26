/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package general;

/**
 *
 * @author Victor Fernandez
 */
public class DatoVelocidadNivel {
        private Integer bin;
        private Double velocidad;
        private Double nivel;
        private Boolean valido;
    
        public DatoVelocidadNivel(Integer bin, Double velocidad, Double nivel, Boolean valido) {
            this.bin = bin;
            this.velocidad = velocidad;
            this.nivel = nivel;
            this.valido = valido;
        }
        
        public Integer getBin() {
            return bin;
        }

        public void setBin(Integer bin) {
            this.bin = bin;
        }

        public Double getNivel() {
            return nivel;
        }

        public void setNivel(Double nivel) {
            this.nivel = nivel;
        }

        public Boolean getValido() {
            return valido;
        }

        public void setValido(Boolean valido) {
            this.valido = valido;
        }
        
        public Double getVelocidad() {
            return velocidad;
        }

        public void setVelocidad(Double velocidad) {
            this.velocidad = velocidad;
        }
    }