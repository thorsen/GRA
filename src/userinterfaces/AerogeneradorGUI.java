
package userinterfaces;


import RA.AerogeneradorRA;
import RA.Global;
import RA.Numero;
import general.MensajeApp;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;


public class AerogeneradorGUI extends JDialog {
    
 
    public AerogeneradorGUI(java.awt.Frame parent) {
        super(parent,true);
        initComponents();
    }
   
   
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel6 = new javax.swing.JPanel();
        closeButton3 = new javax.swing.JButton();
        closeButton2 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jComboBox6 = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jTextField15 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        jComboBox7 = new javax.swing.JComboBox();
        jLabel23 = new javax.swing.JLabel();
        jlLongBuje = new javax.swing.JLabel();
        jtfLongBuje = new javax.swing.JTextField();
        jcbMiniAero = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jTextField13 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jTextField14 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("AEROGENERADOR");
        setBackground(java.awt.Color.white);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setForeground(new java.awt.Color(255, 0, 0));
        setIconImage(new ImageIcon(RA.Global.RUTA_IMAGENES + "GCPMini.jpg").getImage());
        setLocationByPlatform(true);
        setName("Cliente"); // NOI18N

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        closeButton3.setText("CANCELAR");
        closeButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cancelar(evt);
            }
        });

        closeButton2.setText("OK");
        closeButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarAero(evt);
            }
        });

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("VALORES NOMINALES / CARACTERÍSTICAS");

        jLabel15.setText("  Dn [m]: ");

        jTextField11.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField11.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                ValidarDn(evt);
            }
        });

        jLabel19.setText("   Lineas Evacuación:");

        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "1", "2" }));

        jLabel14.setText("   Fabricante:");

        jTextField8.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel16.setText("   Potencia Nominal   [kW] : ");

        jTextField10.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField10.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                ValidarPn(evt);
            }
        });

        jLabel22.setText("   Velocidad Nominal   (m/s) : ");

        jLabel17.setText("  Arranque (Vin m/s) : ");

        jLabel18.setText("  Corte (Vcut m/s) :");

        jTextField15.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField15.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                ValidarVn(evt);
            }
        });

        jTextField9.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField9.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                ValidarVin(evt);
            }
        });

        jTextField12.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField12.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                ValidarVcut(evt);
            }
        });

        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "Cambio de Paso", "Pérdida Aerodinámica" }));

        jLabel23.setText("  Control de potencia :");

        jlLongBuje.setText("  Longitud de Buje (m) :");

        jtfLongBuje.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtfLongBuje.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfLongBujeValidarVcut(evt);
            }
        });

        jcbMiniAero.setText("Mini Aerogenerador");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(143, 143, 143)
                        .addComponent(jLabel1))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jcbMiniAero)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel7Layout.createSequentialGroup()
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel14)
                                        .addComponent(jLabel15)
                                        .addComponent(jLabel16)
                                        .addComponent(jLabel22)
                                        .addComponent(jLabel17)
                                        .addComponent(jLabel18)
                                        .addComponent(jLabel23)
                                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(56, 56, 56)
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel7Layout.createSequentialGroup()
                                    .addComponent(jlLongBuje)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jtfLongBuje, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(58, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(11, 11, 11)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel17)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel18)
                    .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel23)
                    .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel19)
                    .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlLongBuje)
                    .addComponent(jtfLongBuje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcbMiniAero)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel20.setText("Modelo: ");

        jTextField13.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel21.setText("Hb [m]:");

        jTextField14.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField14.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                ValidarHb(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 175, Short.MAX_VALUE)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(closeButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 319, Short.MAX_VALUE)
                        .addComponent(closeButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(closeButton2)
                    .addComponent(closeButton3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(566, 455));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void Cancelar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cancelar
        dispose();
}//GEN-LAST:event_Cancelar

private void ValidarHb(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ValidarHb
Numero N=new Numero();
if (!N.isNumeric(this.jTextField14.getText()) & (this.jTextField14.getText().length()!=0)){
   this.jTextField14.setText(null);
   JOptionPane.showMessageDialog(this, "Hb debe ser un campo numérico","ADVERTENCIA",JOptionPane.WARNING_MESSAGE);
}
}//GEN-LAST:event_ValidarHb

// Centra los datos de una tabla
public void Centrar (JTable tabla,int i, int f){
    DefaultTableCellRenderer c = new DefaultTableCellRenderer();
    c.setHorizontalAlignment(SwingConstants.CENTER);
    for (int columna=i;columna<=f;columna++){
        tabla.getColumnModel().getColumn(columna).setCellRenderer(c);     
    }
}   


private void ValidarVcut(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ValidarVcut
Numero N=new Numero();
if (!N.isNumeric(this.jTextField12.getText()) & (this.jTextField12.getText().length()!=0)){
    this.jTextField12.setText(null);
    JOptionPane.showMessageDialog(this, "Vcut debe ser un campo numérico","ADVERTENCIA",JOptionPane.WARNING_MESSAGE);
}
}//GEN-LAST:event_ValidarVcut

private void ValidarVin(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ValidarVin
Numero N=new Numero();
if (!N.isNumeric(this.jTextField9.getText()) & (this.jTextField9.getText().length()!=0)){
    this.jTextField9.setText(null);
    JOptionPane.showMessageDialog(this, "Vin debe ser un campo numérico","ADVERTENCIA",JOptionPane.WARNING_MESSAGE);
}
}//GEN-LAST:event_ValidarVin

private void ValidarVn(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ValidarVn
Numero N=new Numero();
if (!N.isNumeric(this.jTextField15.getText()) & (this.jTextField15.getText().length()!=0)){
    this.jTextField15.setText(null);
    JOptionPane.showMessageDialog(this, "Vn debe ser un campo numérico","ADVERTENCIA",JOptionPane.WARNING_MESSAGE);
}
}//GEN-LAST:event_ValidarVn

private void ValidarPn(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ValidarPn
Numero N=new Numero();
if (!N.isNumeric(this.jTextField10.getText()) & (this.jTextField10.getText().length()!=0)){
    this.jTextField10.setText(null);
    JOptionPane.showMessageDialog(this, "Pn debe ser un campo numérico","ADVERTENCIA",JOptionPane.WARNING_MESSAGE);
}
}//GEN-LAST:event_ValidarPn

private void ValidarDn(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ValidarDn
Numero N=new Numero();
if (!N.isNumeric(this.jTextField11.getText()) & (this.jTextField11.getText().length()!=0)){
    this.jTextField11.setText(null);
    JOptionPane.showMessageDialog(this, "Dn debe ser un campo numérico","ADVERTENCIA",JOptionPane.WARNING_MESSAGE);
}
}//GEN-LAST:event_ValidarDn

    private void jtfLongBujeValidarVcut(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfLongBujeValidarVcut
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfLongBujeValidarVcut

    private void guardarAero(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarAero
        // TODO add your handling code here:
		if ((this.jTextField13.getText().length()==0) |(this.jTextField10.getText().length()==0) |(this.jTextField9.getText().length()==0)|(this.jTextField12.getText().length()==0)|(this.jTextField14.getText().length()==0)| (this.jTextField11.getText().length()==0)| (this.jComboBox6.getSelectedIndex()==0) | (this.jComboBox7.getSelectedIndex()==0)){
			JOptionPane.showMessageDialog(this,"Por favor, introduzca los campos obligatorios (*)","ADVERTENCIA",JOptionPane.WARNING_MESSAGE);
		} else {
			try {
				String modelo=this.jTextField13.getText();
				double hb=Double.parseDouble(this.jTextField14.getText());
				int lineas=Integer.parseInt((String)this.jComboBox6.getSelectedItem());
				String fabricante=null;
				if (this.jTextField8.getText().length()!=0){
					fabricante=this.jTextField8.getText();
				}
				double Vn=0;
				if (this.jTextField15.getText().length()!=0){
					Vn=Double.parseDouble(this.jTextField15.getText());
				}
				double vin=Double.parseDouble(this.jTextField9.getText());
				double vcut=Double.parseDouble(this.jTextField12.getText());
				double pn=Double.parseDouble(this.jTextField10.getText());
				double dn=Double.parseDouble(this.jTextField11.getText());
				int reg=1;
				if (this.jComboBox7.getSelectedIndex()==2){
					reg=0;
				}

				//Modificaicones Longitud de buje
				/*
				try {
					Aerogenerador Ae=new Aerogenerador();
					boolean salida=Ae.NuevoAerogenerador(modelo, fabricante, hb, dn, pn, vin, vcut, lineas, reg,Vn);
					if (!salida){
						JOptionPane.showMessageDialog(this, "Aerogenerador insertado correctamente","INFORMACIÓN",JOptionPane.INFORMATION_MESSAGE);
					}else{
						JOptionPane.showMessageDialog(this, "El aerogenerador ya existe","MENSAJE DE ERROR",JOptionPane.ERROR_MESSAGE);  
					}
				} catch (SQLException e) {
						e.printStackTrace();
				}
				*/
				Double longBuje = Double.parseDouble(this.jtfLongBuje.getText());
				Boolean pequeno = this.jcbMiniAero.isSelected();
				Boolean regulacion = (reg == 1);

				int res = AerogeneradorRA.insertAerogenerador(null, modelo, hb, fabricante, dn, pn, vin, vcut, lineas, regulacion, Vn, pequeno, longBuje, null);

				if (res > 0)
					MensajeApp.muestraInfo(this, "Aerogenerador añadido correctamente");
				else
					MensajeApp.muestraError(this, null, "Fallo añadiendo aerogenerador");
			} catch (NoSuchFieldException ex) {
				MensajeApp.muestraError(this, ex, "Error añadiendo aerogenerador");
			} catch (NumberFormatException ex) {
				MensajeApp.muestraError(this, ex, "Error parseando número");
			} catch (SQLException ex) {
				MensajeApp.muestraError(this, ex, "Error añadiendo aerogenerador");
			}
        }   
    }//GEN-LAST:event_guardarAero
   
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closeButton2;
    private javax.swing.JButton closeButton3;
    private javax.swing.JComboBox jComboBox6;
    private javax.swing.JComboBox jComboBox7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JCheckBox jcbMiniAero;
    private javax.swing.JLabel jlLongBuje;
    private javax.swing.JTextField jtfLongBuje;
    // End of variables declaration//GEN-END:variables
 
    
}  
