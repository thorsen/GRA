package calculos;

import RA.AerogeneradorRA;
import RA.AsuntoPosicionRA;
import RA.AsuntoRA;
import RA.DatosRA2;
import RA.NormaRA;
import RA.PosicionRA;
import general.Auxiliares;
import general.ComboBoxObject;
import general.IVExtendido;
import general.MensajeApp;
import java.sql.SQLException;

public class CalculoBeta extends javax.swing.JDialog {
    private Integer parqueEolico;
    
    private final Double BETA_MAX = 90.0;
    private final Double BETA_MIN = 30.0;
    
    public CalculoBeta(java.awt.Frame parent, Integer parqueEolico) {
        super(parent, true);
        initComponents();
        
        this.parqueEolico = parqueEolico;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpPrincipal = new javax.swing.JPanel();
        jpClave = new javax.swing.JPanel();
        jlAsunto = new javax.swing.JLabel();
        jcbAsunto = new javax.swing.JComboBox();
        jlNorma = new javax.swing.JLabel();
        jtfNorma = new javax.swing.JTextField();
        jlPosAero = new javax.swing.JLabel();
        jtfPosAero = new javax.swing.JTextField();
        jlHB = new javax.swing.JLabel();
        jtfHB = new javax.swing.JTextField();
        jpCalcBeta = new javax.swing.JPanel();
        jlZ = new javax.swing.JLabel();
        jtfZ = new javax.swing.JTextField();
        jlZSufijo = new javax.swing.JLabel();
        jlZref = new javax.swing.JLabel();
        jtfZref = new javax.swing.JTextField();
        jlZrefSufijo = new javax.swing.JLabel();
        jlBetaMax = new javax.swing.JLabel();
        jtfBetaMax = new javax.swing.JTextField();
        jlBetaMaxSufijo = new javax.swing.JLabel();
        jlBetaMin = new javax.swing.JLabel();
        jtfBetaMin = new javax.swing.JTextField();
        jlBetaMinSufijo = new javax.swing.JLabel();
        jlBetaRes = new javax.swing.JLabel();
        jtfBetaRes = new javax.swing.JTextField();
        jbCalcBeta = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("CÁLCULO β");
        setLocationByPlatform(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jpPrincipal.setBackground(new java.awt.Color(255, 255, 255));

        jpClave.setBackground(new java.awt.Color(255, 255, 255));
        jpClave.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder()));

        jlAsunto.setFont(new java.awt.Font("Tahoma", 1, 12));
        jlAsunto.setText("Asunto:");

        jcbAsunto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cambioAsunto(evt);
            }
        });

        jlNorma.setFont(new java.awt.Font("Tahoma", 1, 11));
        jlNorma.setText("Norma de Aplicación:");

        jtfNorma.setBackground(new java.awt.Color(204, 204, 204));
        jtfNorma.setEditable(false);
        jtfNorma.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtfNorma.setFocusable(false);

        jlPosAero.setFont(new java.awt.Font("Tahoma", 1, 11));
        jlPosAero.setText("Posición Aerogenerador:");

        jtfPosAero.setBackground(new java.awt.Color(204, 204, 204));
        jtfPosAero.setEditable(false);
        jtfPosAero.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtfPosAero.setFocusable(false);

        jlHB.setFont(new java.awt.Font("Tahoma", 1, 11));
        jlHB.setText("Altura Buje:");

        jtfHB.setBackground(new java.awt.Color(204, 204, 204));
        jtfHB.setEditable(false);
        jtfHB.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtfHB.setFocusable(false);

        javax.swing.GroupLayout jpClaveLayout = new javax.swing.GroupLayout(jpClave);
        jpClave.setLayout(jpClaveLayout);
        jpClaveLayout.setHorizontalGroup(
            jpClaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpClaveLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpClaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpClaveLayout.createSequentialGroup()
                        .addGroup(jpClaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlPosAero)
                            .addComponent(jlHB))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpClaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtfHB, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                            .addComponent(jtfPosAero, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)))
                    .addGroup(jpClaveLayout.createSequentialGroup()
                        .addComponent(jlAsunto)
                        .addGap(18, 18, 18)
                        .addComponent(jcbAsunto, 0, 280, Short.MAX_VALUE))
                    .addGroup(jpClaveLayout.createSequentialGroup()
                        .addComponent(jlNorma)
                        .addGap(25, 25, 25)
                        .addComponent(jtfNorma, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jpClaveLayout.setVerticalGroup(
            jpClaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpClaveLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jpClaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlAsunto)
                    .addComponent(jcbAsunto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jpClaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlNorma)
                    .addComponent(jtfNorma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpClaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlPosAero)
                    .addComponent(jtfPosAero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpClaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlHB)
                    .addComponent(jtfHB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpCalcBeta.setBackground(new java.awt.Color(255, 255, 255));
        jpCalcBeta.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jlZ.setText("z");

        jtfZ.setEditable(false);
        jtfZ.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtfZ.setName("Zref"); // NOI18N

        jlZSufijo.setText("m");

        jlZref.setText("<html>z<sub>ref</sub></html>");

        jtfZref.setEditable(false);
        jtfZref.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtfZref.setName("Z0_ref"); // NOI18N

        jlZrefSufijo.setText("m");

        jlBetaMax.setText("<html>β<sub>max</sub></html>");

        jtfBetaMax.setEditable(false);
        jtfBetaMax.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtfBetaMax.setName("Z0"); // NOI18N

        jlBetaMaxSufijo.setText("º");

        jlBetaMin.setText("<html>β<sub>min</sub></html>");

        jtfBetaMin.setEditable(false);
        jtfBetaMin.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtfBetaMin.setName("Z0"); // NOI18N

        jlBetaMinSufijo.setText("º");

        jlBetaRes.setText("β");

        jtfBetaRes.setEditable(false);
        jtfBetaRes.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtfBetaRes.setName("Z0"); // NOI18N

        jbCalcBeta.setText("Calcular");
        jbCalcBeta.setEnabled(false);
        jbCalcBeta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calcularBeta(evt);
            }
        });

        javax.swing.GroupLayout jpCalcBetaLayout = new javax.swing.GroupLayout(jpCalcBeta);
        jpCalcBeta.setLayout(jpCalcBetaLayout);
        jpCalcBetaLayout.setHorizontalGroup(
            jpCalcBetaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpCalcBetaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpCalcBetaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpCalcBetaLayout.createSequentialGroup()
                        .addComponent(jlZ)
                        .addGap(15, 15, 15)
                        .addComponent(jtfZ, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlZSufijo))
                    .addGroup(jpCalcBetaLayout.createSequentialGroup()
                        .addComponent(jlZref)
                        .addGap(4, 4, 4)
                        .addComponent(jtfZref, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlZrefSufijo)))
                .addGap(18, 18, 18)
                .addGroup(jpCalcBetaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlBetaMin)
                    .addComponent(jlBetaMax))
                .addGap(4, 4, 4)
                .addGroup(jpCalcBetaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtfBetaMin, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfBetaMax, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpCalcBetaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlBetaMaxSufijo)
                    .addComponent(jlBetaMinSufijo))
                .addGap(18, 18, 18)
                .addComponent(jbCalcBeta)
                .addGap(18, 18, 18)
                .addComponent(jlBetaRes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtfBetaRes, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpCalcBetaLayout.setVerticalGroup(
            jpCalcBetaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpCalcBetaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpCalcBetaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpCalcBetaLayout.createSequentialGroup()
                        .addGroup(jpCalcBetaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlBetaMinSufijo)
                            .addComponent(jtfBetaMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpCalcBetaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlBetaMaxSufijo)
                            .addComponent(jlBetaMax)
                            .addComponent(jtfBetaMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jlBetaMin)
                    .addComponent(jlZ)
                    .addGroup(jpCalcBetaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jtfZ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jlZSufijo))
                    .addGroup(jpCalcBetaLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jpCalcBetaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlZref)
                            .addComponent(jtfZref, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlZrefSufijo)))
                    .addGroup(jpCalcBetaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jbCalcBeta, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jlBetaRes)
                        .addComponent(jtfBetaRes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jpPrincipalLayout = new javax.swing.GroupLayout(jpPrincipal);
        jpPrincipal.setLayout(jpPrincipalLayout);
        jpPrincipalLayout.setHorizontalGroup(
            jpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpClave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpCalcBeta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jpPrincipalLayout.setVerticalGroup(
            jpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpClave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpCalcBeta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-407)/2, (screenSize.height-273)/2, 407, 273);
    }// </editor-fold>//GEN-END:initComponents

private void cambioAsunto(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cambioAsunto
    try {
        AsuntoRA asunto;
        
        limpiarCampos();

        Integer idAsunto = (Integer) ComboBoxObject.getClaveSelCombo(this.jcbAsunto);
        asunto = idAsunto != null ? AsuntoRA.getAsuntoPorId(idAsunto) : null;

        if (asunto != null) {
            AerogeneradorRA aero = asunto.getIdAero() != null ? AerogeneradorRA.getAeroPorId(asunto.getIdAero()) : null;
            this.jtfHB.setText(aero != null && aero.getHB() != null ? aero.getHB().toString() : null);
            
            AsuntoPosicionRA asuntoPos = AsuntoPosicionRA.getAsuntoPosicionRA(idAsunto);
            PosicionRA posAero = asuntoPos != null && asuntoPos.getIdPosAero() != null ? PosicionRA.getPosicion(asuntoPos.getIdPosAero()) : null;
            this.jtfPosAero.setText(posAero != null ? posAero.toString() : null);
            
            NormaRA norma = asunto.getIdNorma() != null ? NormaRA.getNormaPorId(asunto.getIdNorma()) : null;
            this.jtfNorma.setText(norma != null ? norma.getNombre() : null);
            
            if (norma != null) {
                Integer idNorma = norma.getIdNorma();
                
                if (idNorma.equals(NormaRA.ID_NORMA_IEC_2_1))
                    this.jtfZref.setText(NormaRA.Z_REF_IEC.toString());
                else if (idNorma.equals(NormaRA.ID_NORMA_AWEA))
                    this.jtfZref.setText(NormaRA.Z_REF_AWEA.toString());
                else if (idNorma.equals(NormaRA.ID_NORMA_BWEA))
                    this.jtfZref.setText(NormaRA.Z_REF_BWEA.toString());
            }
            
            this.jtfBetaMax.setText(BETA_MAX.toString());
            this.jtfBetaMin.setText(BETA_MIN.toString());
            
            this.jtfZ.setEditable(true);
            this.jtfZref.setEditable(true);
            this.jtfBetaMax.setEditable(true);
            this.jtfBetaMin.setEditable(true);
            this.jbCalcBeta.setEnabled(true);
        }
    } catch (SQLException e) {
        MensajeApp.muestraError(this, e, "Fallo al consultar la base de datos");
    } catch (NoSuchFieldException e) {
        MensajeApp.muestraError(this, e, "Fallo añadiendo campo");
    }
}//GEN-LAST:event_cambioAsunto

private void calcularBeta(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calcularBeta
    Double z, zRef, hB, betaMin, betaMax, beta = null;
    
    this.jtfBetaRes.setText(null);
    
    if (Auxiliares.validaCampos(this)) {
        z = Double.parseDouble(this.jtfZ.getText());
        zRef = Double.parseDouble(this.jtfZref.getText());
        betaMin = Double.parseDouble(this.jtfBetaMin.getText());
        betaMax = Double.parseDouble(this.jtfBetaMax.getText());

        hB = Double.parseDouble(this.jtfHB.getText());

        beta = DatosRA2.getBeta(z, zRef, hB, betaMin, betaMax);
        
        if (beta == null)
            MensajeApp.muestraError(this, null, "La altura de referencia y de buje no pueden ser iguales");
        else
            this.jtfBetaRes.setText(beta.toString());
    }
}//GEN-LAST:event_calcularBeta

private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
    try {
        //Rellenamos combo de asuntos con los relacionados con el parque
        Auxiliares.cargaAsuntosGen(this.jcbAsunto, false, null, null, null, this.parqueEolico, null, null, null, null, null, null, null, null, null, null, null, null, null, AsuntoRA.TIPO_ASUNTO_RA);

        if (this.jcbAsunto.getItemCount() != 0)
            this.jcbAsunto.setSelectedIndex(0);
        else {
            MensajeApp.muestraError(this, null, "No se ha encontrado ningún asunto abierto para el parque");
            this.dispose();
        }

        //Añadimos los verificadores de los campos de entrada
        this.jtfZ.setInputVerifier(new IVExtendido(this, IVExtendido.TIPO_DOUBLE, false, true));
        this.jtfZref.setInputVerifier(new IVExtendido(this, IVExtendido.TIPO_DOUBLE, false, true));
        this.jtfBetaMax.setInputVerifier(new IVExtendido(this, IVExtendido.TIPO_DOUBLE, false, true));
        this.jtfBetaMin.setInputVerifier(new IVExtendido(this, IVExtendido.TIPO_DOUBLE, false, true));
        this.jtfHB.setInputVerifier(new IVExtendido(this, IVExtendido.TIPO_DOUBLE, false, true));
    } catch (SQLException e) {
        MensajeApp.muestraError(this, e, "Fallo al consultar la base de datos");
        this.dispose();
    } catch (NoSuchFieldException e) {
        MensajeApp.muestraError(this, e, "Fallo añadiendo campo");
        this.dispose();
    }
}//GEN-LAST:event_formWindowOpened

private void limpiarCampos() {
    //Campos de cabecera
    this.jtfNorma.setText(null);
    this.jtfPosAero.setText(null);
    
    //Campos de datos
    this.jtfZ.setText(null);
    this.jtfZref.setText(null);
    this.jtfBetaMin.setText(null);
    this.jtfBetaMax.setText(null);
    this.jtfBetaRes.setText(null);

    this.jbCalcBeta.setEnabled(false);
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbCalcBeta;
    public javax.swing.JComboBox jcbAsunto;
    private javax.swing.JLabel jlAsunto;
    private javax.swing.JLabel jlBetaMax;
    private javax.swing.JLabel jlBetaMaxSufijo;
    private javax.swing.JLabel jlBetaMin;
    private javax.swing.JLabel jlBetaMinSufijo;
    private javax.swing.JLabel jlBetaRes;
    private javax.swing.JLabel jlHB;
    private javax.swing.JLabel jlNorma;
    private javax.swing.JLabel jlPosAero;
    private javax.swing.JLabel jlZ;
    private javax.swing.JLabel jlZSufijo;
    private javax.swing.JLabel jlZref;
    private javax.swing.JLabel jlZrefSufijo;
    private javax.swing.JPanel jpCalcBeta;
    private javax.swing.JPanel jpClave;
    private javax.swing.JPanel jpPrincipal;
    private javax.swing.JTextField jtfBetaMax;
    private javax.swing.JTextField jtfBetaMin;
    private javax.swing.JTextField jtfBetaRes;
    public javax.swing.JTextField jtfHB;
    private javax.swing.JTextField jtfNorma;
    public javax.swing.JTextField jtfPosAero;
    private javax.swing.JTextField jtfZ;
    private javax.swing.JTextField jtfZref;
    // End of variables declaration//GEN-END:variables

}
