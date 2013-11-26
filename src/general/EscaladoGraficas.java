/*
 * EscaladoGraficas.java
 *
 * Created on 24 de septiembre de 2013, 12:36
 */

package general;

import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author  Victor Fernandez
 */
public class EscaladoGraficas extends javax.swing.JPanel {

    public JTextField getJtfIntervaloDom() {
        return jtfIntervaloDom;
    }

    public void setJtfIntervaloDom(JTextField jtfIntervaloDom) {
        this.jtfIntervaloDom = jtfIntervaloDom;
    }

    public JTextField getJtfIntervaloRango() {
        return jtfIntervaloRango;
    }

    public void setJtfIntervaloRango(JTextField jtfIntervaloRango) {
        this.jtfIntervaloRango = jtfIntervaloRango;
    }

    public JRadioButton getJrbMaximizar() {
        return jrbMaximizar;
    }

    public void setJrbMaximizar(JRadioButton jrbMaximizar) {
        this.jrbMaximizar = jrbMaximizar;
    }

    public JRadioButton getJrbRangoDom() {
        return jrbRangoDom;
    }

    public void setJrbRangoDom(JRadioButton jrbRangoDom) {
        this.jrbRangoDom = jrbRangoDom;
    }

    public JRadioButton getJrbRelacion() {
        return jrbRelacion;
    }

    public void setJrbRelacion(JRadioButton jrbRelacion) {
        this.jrbRelacion = jrbRelacion;
    }

    public JTextField getJtfDesdeDom() {
        return jtfDesdeDom;
    }

    public void setJtfDesdeDom(JTextField jtfDesdeDom) {
        this.jtfDesdeDom = jtfDesdeDom;
    }

    public JTextField getJtfDesdeRango() {
        return jtfDesdeRango;
    }

    public void setJtfDesdeRango(JTextField jtfDesdeRango) {
        this.jtfDesdeRango = jtfDesdeRango;
    }

    public JTextField getJtfHastaDom() {
        return jtfHastaDom;
    }

    public void setJtfHastaDom(JTextField jtfHastaDom) {
        this.jtfHastaDom = jtfHastaDom;
    }

    public JTextField getJtfHastaRango() {
        return jtfHastaRango;
    }

    public void setJtfHastaRango(JTextField jtfHastaRango) {
        this.jtfHastaRango = jtfHastaRango;
    }

    public JTextField getJtfRelX() {
        return jtfRelX;
    }

    public void setJtfRelX(JTextField jtfRelX) {
        this.jtfRelX = jtfRelX;
    }

    public JTextField getJtfRelY() {
        return jtfRelY;
    }

    public void setJtfRelY(JTextField jtfRelY) {
        this.jtfRelY = jtfRelY;
    }

    /** Creates new form EscaladoGraficas */
    public EscaladoGraficas() {
        initComponents();
    }
    
    public static EscaladoGraficas getEscaladoGraficas(Double relY, Double relX, Double desdeRan, Double hastaRan, Double desdeDom, Double hastaDom, Double intervaloRan, Double intervaloDom) {
        EscaladoGraficas eg = new EscaladoGraficas();
        
        eg.jtfRelY.setText(relY.toString());
        eg.jtfRelX.setText(relX.toString());
        
        eg.jtfDesdeRango.setText(desdeRan.toString());
        eg.jtfHastaRango.setText(hastaRan.toString());
        eg.jtfDesdeDom.setText(desdeDom.toString());
        eg.jtfHastaDom.setText(hastaDom.toString());
        
        eg.jtfIntervaloRango.setText(intervaloRan.toString());
        eg.jtfIntervaloDom.setText(intervaloDom.toString());
        
        eg.jtfRelY.setCaretPosition(0);
        eg.jtfRelX.setCaretPosition(0);
        
        eg.jtfDesdeRango.setCaretPosition(0);
        eg.jtfHastaRango.setCaretPosition(0);
        eg.jtfDesdeDom.setCaretPosition(0);
        eg.jtfHastaDom.setCaretPosition(0);
        
        eg.jtfIntervaloRango.setCaretPosition(0);
        eg.jtfIntervaloDom.setCaretPosition(0);
        
        return eg;
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gbEscala = new javax.swing.ButtonGroup();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        jlPregunta = new javax.swing.JLabel();
        jrbRelacion = new javax.swing.JRadioButton();
        jtfRelY = new javax.swing.JTextField();
        jlRelSep = new javax.swing.JLabel();
        jtfRelX = new javax.swing.JTextField();
        jrbRangoDom = new javax.swing.JRadioButton();
        jlRango = new javax.swing.JLabel();
        jtfDesdeRango = new javax.swing.JTextField();
        jlRangoSep = new javax.swing.JLabel();
        jtfHastaRango = new javax.swing.JTextField();
        jlDom = new javax.swing.JLabel();
        jtfDesdeDom = new javax.swing.JTextField();
        jlDomSep = new javax.swing.JLabel();
        jtfHastaDom = new javax.swing.JTextField();
        jrbMaximizar = new javax.swing.JRadioButton();
        jsSeparador = new javax.swing.JSeparator();
        jlIntervalo = new javax.swing.JLabel();
        jlIntervaloRango = new javax.swing.JLabel();
        jtfIntervaloRango = new javax.swing.JTextField();
        jlIntervaloDom = new javax.swing.JLabel();
        jtfIntervaloDom = new javax.swing.JTextField();

        jMenuItem1.setText("Item");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem1);
        jPopupMenu1.add(jSeparator1);

        jlPregunta.setText("¿Cómo desea escalar la gráfica?");

        gbEscala.add(jrbRelacion);
        jrbRelacion.setSelected(true);
        jrbRelacion.setText("Estableciendo relación entre ejes");

        jlRelSep.setText(":");

        gbEscala.add(jrbRangoDom);
        jrbRangoDom.setText("Definiendo rango y dominio");

        jlRango.setText("Rango:");

        jlRangoSep.setText("-");

        jlDom.setText("Dominio:");

        jlDomSep.setText("-");

        gbEscala.add(jrbMaximizar);
        jrbMaximizar.setText("Maximizar");

        jlIntervalo.setText("¿Qué intervalos desea establecer en los ejes?");

        jlIntervaloRango.setText("Rango:");

        jlIntervaloDom.setText("Dominio:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jsSeparador, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jrbMaximizar, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(21, 21, 21)
                            .addComponent(jtfRelY, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jlRelSep)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jtfRelX, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jlPregunta, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jrbRelacion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jrbRangoDom, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(21, 21, 21)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jlDom)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jtfDesdeDom, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jlDomSep)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jtfHastaDom, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jlRango)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jtfDesdeRango, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jlRangoSep)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jtfHastaRango, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addComponent(jlIntervalo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlIntervaloRango)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfIntervaloRango, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jlIntervaloDom)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfIntervaloDom, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlPregunta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jrbRelacion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfRelY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlRelSep)
                    .addComponent(jtfRelX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jrbRangoDom)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlRango)
                    .addComponent(jtfDesdeRango, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlRangoSep)
                    .addComponent(jtfHastaRango, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlDom)
                    .addComponent(jtfDesdeDom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlDomSep)
                    .addComponent(jtfHastaDom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jrbMaximizar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jsSeparador, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlIntervalo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlIntervaloRango)
                    .addComponent(jtfIntervaloRango, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlIntervaloDom)
                    .addComponent(jtfIntervaloDom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_jMenuItem1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup gbEscala;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel jlDom;
    private javax.swing.JLabel jlDomSep;
    private javax.swing.JLabel jlIntervalo;
    private javax.swing.JLabel jlIntervaloDom;
    private javax.swing.JLabel jlIntervaloRango;
    private javax.swing.JLabel jlPregunta;
    private javax.swing.JLabel jlRango;
    private javax.swing.JLabel jlRangoSep;
    private javax.swing.JLabel jlRelSep;
    private javax.swing.JRadioButton jrbMaximizar;
    private javax.swing.JRadioButton jrbRangoDom;
    private javax.swing.JRadioButton jrbRelacion;
    private javax.swing.JSeparator jsSeparador;
    private javax.swing.JTextField jtfDesdeDom;
    private javax.swing.JTextField jtfDesdeRango;
    private javax.swing.JTextField jtfHastaDom;
    private javax.swing.JTextField jtfHastaRango;
    private javax.swing.JTextField jtfIntervaloDom;
    private javax.swing.JTextField jtfIntervaloRango;
    private javax.swing.JTextField jtfRelX;
    private javax.swing.JTextField jtfRelY;
    // End of variables declaration//GEN-END:variables

}
