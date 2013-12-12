package calculos;

import RA.AerogeneradorRA;
import RA.AsuntoConfRA;
import RA.AsuntoConfRAModos;
import RA.AsuntoIncert;
import RA.AsuntoPosicionRA;
import RA.AsuntoRA;
import RA.ConfiguracionRA2;
import RA.DatosRA2;
import RA.DireccionRA;
import RA.NormaRA;
import RA.PosicionRA;
import RA.SiteRA;
import RA.TipoRA;
import general.ComboBoxObject;
import general.MensajeApp;
import general.TratFechas;
import java.sql.SQLException;
import javax.swing.*;
import general.Auxiliares;
import general.IVExtendido;
import java.awt.Frame;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class DatosLimitesGUI extends JDialog {

    public DatosLimitesGUI(java.awt.Frame parent) {
        super(parent, true);
        initComponents();
    }

    //Valores por defecto
    private final Double AMPLITUD = 15.0;
    private final Double Z_REF = 10.0;
    private final Double Z0_REF = 0.05;
    private final Integer DESDE_VELOCIDAD = 6;
    private final Integer HASTA_VELOCIDAD = 10;
    
    private final int ID_SERIE_DEF = 1;
    private final double DENSIDAD_DEF = 1.225;
    

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgMedidaPot = new javax.swing.ButtonGroup();
        bgOpVelK = new javax.swing.ButtonGroup();
        bgOpVelKRF = new javax.swing.ButtonGroup();
        bgOpVel = new javax.swing.ButtonGroup();
        bgPer = new javax.swing.ButtonGroup();
        bgOpVelDerTipo = new javax.swing.ButtonGroup();
        jpNuevoModoFunc = new javax.swing.JPanel();
        jtfVelDesModoFunc = new javax.swing.JTextField();
        jtfVelHasModoFunc = new javax.swing.JTextField();
        jlVelSepModoFunc = new javax.swing.JLabel();
        jlVelDesModoFunc = new javax.swing.JLabel();
        jpTipoCalculoOCTModoFunc = new javax.swing.JPanel();
        jlBinEstudio = new javax.swing.JLabel();
        jtfBinEstudio = new javax.swing.JTextField();
        bgResAltura = new javax.swing.ButtonGroup();
        jpGeneral = new javax.swing.JPanel();
        jpTipoAnalisis = new javax.swing.JPanel();
        jTitTipoAnalisis = new javax.swing.JLabel();
        jcbSPL = new javax.swing.JCheckBox();
        jcbOCT = new javax.swing.JCheckBox();
        jcbFFT = new javax.swing.JCheckBox();
        jpClave = new javax.swing.JPanel();
        jlAsunto = new javax.swing.JLabel();
        jcbAsunto = new javax.swing.JComboBox();
        jlNorma = new javax.swing.JLabel();
        jcbNorma = new javax.swing.JComboBox();
        jcbMiniAero = new javax.swing.JCheckBox();
        jlTitPerMed = new javax.swing.JLabel();
        jlFechaD = new javax.swing.JLabel();
        jtfFechaD = new javax.swing.JTextField();
        jlFechaH = new javax.swing.JLabel();
        jtfFechaH = new javax.swing.JTextField();
        jlTitPosiciones = new javax.swing.JLabel();
        jlPosAero = new javax.swing.JLabel();
        jtfPosAero = new javax.swing.JTextField();
        jlPosMicro = new javax.swing.JLabel();
        jtfPosMicro = new javax.swing.JTextField();
        jlPotNominal = new javax.swing.JLabel();
        jtfPotNominal = new javax.swing.JTextField();
        jlPotNominalEdit = new javax.swing.JLabel();
        jpPeriodo = new javax.swing.JPanel();
        jlTitPeriodo = new javax.swing.JLabel();
        jrbPerMed = new javax.swing.JRadioButton();
        jrbPerOtr = new javax.swing.JRadioButton();
        jlPerDes = new javax.swing.JLabel();
        jsPerDes = new general.JSpinnerDate();
        jlPerHas = new javax.swing.JLabel();
        jsPerHas = new general.JSpinnerDate();
        jpAlturaRug = new javax.swing.JPanel();
        jlTitAlturaRug = new javax.swing.JLabel();
        jlZref = new javax.swing.JLabel();
        jtfZref = new javax.swing.JTextField();
        jlmZred = new javax.swing.JLabel();
        jlZ0ref = new javax.swing.JLabel();
        jtfZ0ref = new javax.swing.JTextField();
        jlmZ0ref = new javax.swing.JLabel();
        jlZ0 = new javax.swing.JLabel();
        jtfZ0 = new javax.swing.JTextField();
        jpOpciones = new javax.swing.JPanel();
        jlTitOpciones = new javax.swing.JLabel();
        jrbVD = new javax.swing.JRadioButton();
        jlTipoK = new javax.swing.JLabel();
        jrbKTorre = new javax.swing.JRadioButton();
        jrbKNac = new javax.swing.JRadioButton();
        jlTipoCalculo = new javax.swing.JLabel();
        jrbPromedio = new javax.swing.JRadioButton();
        jrbPendiente = new javax.swing.JRadioButton();
        jlTipoKRF = new javax.swing.JLabel();
        jrbKRFTorre = new javax.swing.JRadioButton();
        jrbKRFNac = new javax.swing.JRadioButton();
        jrbVZ = new javax.swing.JRadioButton();
        jlMedPot = new javax.swing.JLabel();
        jrbTensionInt = new javax.swing.JRadioButton();
        jrbPotencia = new javax.swing.JRadioButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jlResAltura = new javax.swing.JLabel();
        jrbResAlturaZref = new javax.swing.JRadioButton();
        jrbResAlturaBuje = new javax.swing.JRadioButton();
        jbPrevisualizar = new javax.swing.JButton();
        jpVelocidad = new javax.swing.JPanel();
        jlTitVelocidad = new javax.swing.JLabel();
        jlVelDes = new javax.swing.JLabel();
        jtfVelDes = new javax.swing.JTextField();
        jlVelSep = new javax.swing.JLabel();
        jtfVelHas = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtModosFunc = new general.JTableExport();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jpDireccion = new javax.swing.JPanel();
        jlTitDireccion = new javax.swing.JLabel();
        jlDirEns = new javax.swing.JLabel();
        jtfDirEns = new javax.swing.JTextField();
        jlDirAmp = new javax.swing.JLabel();
        jtfDirAmp = new javax.swing.JTextField();
        jtfDirMin = new javax.swing.JTextField();
        jtfDirMax = new javax.swing.JTextField();
        jpIncertidumbre = new javax.swing.JPanel();
        jlTitIncertidumbre = new javax.swing.JLabel();
        jbIncertidumbres = new javax.swing.JButton();

        jpNuevoModoFunc.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jpNuevoModoFuncAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        jtfVelDesModoFunc.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtfVelDesModoFunc.setName("Desde Velocidad"); // NOI18N

        jtfVelHasModoFunc.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtfVelHasModoFunc.setName("Hasta Velocidad"); // NOI18N

        jlVelSepModoFunc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlVelSepModoFunc.setText("-");
        jlVelSepModoFunc.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jlVelDesModoFunc.setText("Rango de velocidad");

        javax.swing.GroupLayout jpNuevoModoFuncLayout = new javax.swing.GroupLayout(jpNuevoModoFunc);
        jpNuevoModoFunc.setLayout(jpNuevoModoFuncLayout);
        jpNuevoModoFuncLayout.setHorizontalGroup(
            jpNuevoModoFuncLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpNuevoModoFuncLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpNuevoModoFuncLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlVelDesModoFunc, javax.swing.GroupLayout.Alignment.CENTER)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpNuevoModoFuncLayout.createSequentialGroup()
                        .addComponent(jtfVelDesModoFunc, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(jlVelSepModoFunc, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(jtfVelHasModoFunc, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpNuevoModoFuncLayout.setVerticalGroup(
            jpNuevoModoFuncLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpNuevoModoFuncLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlVelDesModoFunc)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpNuevoModoFuncLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfVelDesModoFunc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfVelHasModoFunc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlVelSepModoFunc))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpTipoCalculoOCTModoFunc.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jpTipoCalculoOCTModoFuncAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        jlBinEstudio.setText("Bin a estudiar:");

        jtfBinEstudio.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtfBinEstudio.setName("Desde Velocidad"); // NOI18N

        javax.swing.GroupLayout jpTipoCalculoOCTModoFuncLayout = new javax.swing.GroupLayout(jpTipoCalculoOCTModoFunc);
        jpTipoCalculoOCTModoFunc.setLayout(jpTipoCalculoOCTModoFuncLayout);
        jpTipoCalculoOCTModoFuncLayout.setHorizontalGroup(
            jpTipoCalculoOCTModoFuncLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTipoCalculoOCTModoFuncLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlBinEstudio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtfBinEstudio, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpTipoCalculoOCTModoFuncLayout.setVerticalGroup(
            jpTipoCalculoOCTModoFuncLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTipoCalculoOCTModoFuncLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpTipoCalculoOCTModoFuncLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlBinEstudio)
                    .addComponent(jtfBinEstudio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(new ImageIcon("\\\\Bsolar\\DatosSolar\\Curva\\Imagenes\\GCPMini.jpg").getImage());
        setLocationByPlatform(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jpGeneral.setBackground(new java.awt.Color(255, 255, 255));
        jpGeneral.setPreferredSize(new java.awt.Dimension(706, 613));

        jpTipoAnalisis.setBackground(new java.awt.Color(255, 255, 255));
        jpTipoAnalisis.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder()));

        jTitTipoAnalisis.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTitTipoAnalisis.setText("Tipo Análisis:");

        jcbSPL.setBackground(new java.awt.Color(255, 255, 255));
        jcbSPL.setSelected(true);
        jcbSPL.setText("SPL");
        jcbSPL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbSPLActionPerformed(evt);
            }
        });

        jcbOCT.setBackground(new java.awt.Color(255, 255, 255));
        jcbOCT.setSelected(true);
        jcbOCT.setText("Tercios de Octava");
        jcbOCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbOCTActionPerformed(evt);
            }
        });

        jcbFFT.setBackground(new java.awt.Color(255, 255, 255));
        jcbFFT.setSelected(true);
        jcbFFT.setText("Banda Estrecha");
        jcbFFT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbFFTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpTipoAnalisisLayout = new javax.swing.GroupLayout(jpTipoAnalisis);
        jpTipoAnalisis.setLayout(jpTipoAnalisisLayout);
        jpTipoAnalisisLayout.setHorizontalGroup(
            jpTipoAnalisisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTipoAnalisisLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTitTipoAnalisis, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76)
                .addComponent(jcbSPL)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcbOCT)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcbFFT)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpTipoAnalisisLayout.setVerticalGroup(
            jpTipoAnalisisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTipoAnalisisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jcbSPL)
                .addComponent(jcbOCT)
                .addComponent(jcbFFT)
                .addComponent(jTitTipoAnalisis))
        );

        jpClave.setBackground(new java.awt.Color(255, 255, 255));
        jpClave.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder()));

        jlAsunto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlAsunto.setText("Asunto:");

        jcbAsunto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cambioAsunto(evt);
            }
        });

        jlNorma.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jlNorma.setText("Norma de Aplicación:");

        jcbNorma.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));
        jcbNorma.setEnabled(false);
        jcbNorma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cambioNorma(evt);
            }
        });

        jcbMiniAero.setBackground(new java.awt.Color(255, 255, 255));
        jcbMiniAero.setText("¿Mini Aero?");
        jcbMiniAero.setEnabled(false);

        jlTitPerMed.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jlTitPerMed.setText("Periodo Medido:");

        jlFechaD.setText("Fecha de Inicio:");

        jtfFechaD.setEditable(false);
        jtfFechaD.setBackground(new java.awt.Color(204, 204, 204));
        jtfFechaD.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtfFechaD.setFocusable(false);

        jlFechaH.setText("Fecha de Fin:");

        jtfFechaH.setEditable(false);
        jtfFechaH.setBackground(new java.awt.Color(204, 204, 204));
        jtfFechaH.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtfFechaH.setFocusable(false);

        jlTitPosiciones.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jlTitPosiciones.setText("Posiciones:");

        jlPosAero.setText("Aerogenerador:");

        jtfPosAero.setEditable(false);
        jtfPosAero.setBackground(new java.awt.Color(204, 204, 204));
        jtfPosAero.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtfPosAero.setFocusable(false);

        jlPosMicro.setText("Micrófono:");

        jtfPosMicro.setEditable(false);
        jtfPosMicro.setBackground(new java.awt.Color(204, 204, 204));
        jtfPosMicro.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtfPosMicro.setFocusable(false);

        jlPotNominal.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jlPotNominal.setText("Potencia Nominal:");

        jtfPotNominal.setEditable(false);
        jtfPotNominal.setBackground(new java.awt.Color(204, 204, 204));
        jtfPotNominal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtfPotNominal.setName("Potencia Nominal"); // NOI18N
        jtfPotNominal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtfPotNominalMouseClicked(evt);
            }
        });

        jlPotNominalEdit.setText("<html><i>(Click para editar)</i></html>");

        javax.swing.GroupLayout jpClaveLayout = new javax.swing.GroupLayout(jpClave);
        jpClave.setLayout(jpClaveLayout);
        jpClaveLayout.setHorizontalGroup(
            jpClaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpClaveLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpClaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpClaveLayout.createSequentialGroup()
                        .addComponent(jlTitPosiciones)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpClaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlPosAero)
                            .addComponent(jtfPosAero, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpClaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlPosMicro)
                            .addComponent(jtfPosMicro, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jpClaveLayout.createSequentialGroup()
                        .addGroup(jpClaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpClaveLayout.createSequentialGroup()
                                .addGroup(jpClaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlNorma)
                                    .addComponent(jcbNorma, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jcbMiniAero)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jlTitPerMed))
                            .addGroup(jpClaveLayout.createSequentialGroup()
                                .addComponent(jlAsunto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jcbAsunto, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpClaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpClaveLayout.createSequentialGroup()
                                .addGroup(jpClaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlFechaD)
                                    .addComponent(jtfFechaD, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jpClaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlFechaH)
                                    .addComponent(jtfFechaH, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jpClaveLayout.createSequentialGroup()
                                .addComponent(jlPotNominal)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtfPotNominal, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jlPotNominalEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jpClaveLayout.setVerticalGroup(
            jpClaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpClaveLayout.createSequentialGroup()
                .addGroup(jpClaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlAsunto)
                    .addComponent(jcbAsunto, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlPotNominal)
                    .addComponent(jtfPotNominal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlPotNominalEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpClaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpClaveLayout.createSequentialGroup()
                        .addComponent(jlNorma)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpClaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jcbNorma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcbMiniAero)))
                    .addGroup(jpClaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jpClaveLayout.createSequentialGroup()
                            .addComponent(jlTitPerMed)
                            .addGap(31, 31, 31))
                        .addGroup(jpClaveLayout.createSequentialGroup()
                            .addComponent(jlFechaH)
                            .addGap(26, 26, 26))
                        .addGroup(jpClaveLayout.createSequentialGroup()
                            .addComponent(jlFechaD)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jpClaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jtfFechaD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jtfFechaH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jpClaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpClaveLayout.createSequentialGroup()
                        .addComponent(jlTitPosiciones)
                        .addGap(31, 31, 31))
                    .addGroup(jpClaveLayout.createSequentialGroup()
                        .addComponent(jlPosMicro)
                        .addGap(26, 26, 26))
                    .addGroup(jpClaveLayout.createSequentialGroup()
                        .addComponent(jlPosAero)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpClaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtfPosMicro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfPosAero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        jpPeriodo.setBackground(new java.awt.Color(255, 255, 255));
        jpPeriodo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jlTitPeriodo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlTitPeriodo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlTitPeriodo.setText("PERIODO");
        jlTitPeriodo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jrbPerMed.setBackground(new java.awt.Color(255, 255, 255));
        bgPer.add(jrbPerMed);
        jrbPerMed.setSelected(true);
        jrbPerMed.setText("Medido");
        jrbPerMed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cambioPer(evt);
            }
        });

        jrbPerOtr.setBackground(new java.awt.Color(255, 255, 255));
        bgPer.add(jrbPerOtr);
        jrbPerOtr.setText("Otro");
        jrbPerOtr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cambioPer(evt);
            }
        });

        jlPerDes.setText("De:");

        jsPerDes.setBackground(new java.awt.Color(204, 204, 204));
        jsPerDes.setName("Desde Periodo"); // NOI18N

        jlPerHas.setText("A:");

        jsPerHas.setBackground(new java.awt.Color(204, 204, 204));
        jsPerHas.setName("Hasta Periodo"); // NOI18N

        javax.swing.GroupLayout jpPeriodoLayout = new javax.swing.GroupLayout(jpPeriodo);
        jpPeriodo.setLayout(jpPeriodoLayout);
        jpPeriodoLayout.setHorizontalGroup(
            jpPeriodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPeriodoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpPeriodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlTitPeriodo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jrbPerMed)
                    .addComponent(jrbPerOtr)
                    .addGroup(jpPeriodoLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jpPeriodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jpPeriodoLayout.createSequentialGroup()
                                .addComponent(jlPerDes)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jsPerDes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jpPeriodoLayout.createSequentialGroup()
                                .addComponent(jlPerHas)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jsPerHas, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jpPeriodoLayout.setVerticalGroup(
            jpPeriodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPeriodoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlTitPeriodo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jrbPerMed)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jrbPerOtr)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpPeriodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jsPerDes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlPerDes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpPeriodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlPerHas)
                    .addComponent(jsPerHas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpAlturaRug.setBackground(new java.awt.Color(255, 255, 255));
        jpAlturaRug.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jlTitAlturaRug.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlTitAlturaRug.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlTitAlturaRug.setText("ALTURA / RUGOSIDAD");
        jlTitAlturaRug.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jlZref.setText("<html>z<sub>ref</sub></html>");

        jtfZref.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtfZref.setName("Zref"); // NOI18N

        jlmZred.setText("m");

        jlZ0ref.setText("<html>z<sub>0<sub>ref</sub></sub></html>");

        jtfZ0ref.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtfZ0ref.setName("Z0_ref"); // NOI18N

        jlmZ0ref.setText("m");

        jlZ0.setText("<html>z<sub>0</sub></html>");

        jtfZ0.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtfZ0.setName("Z0"); // NOI18N

        javax.swing.GroupLayout jpAlturaRugLayout = new javax.swing.GroupLayout(jpAlturaRug);
        jpAlturaRug.setLayout(jpAlturaRugLayout);
        jpAlturaRugLayout.setHorizontalGroup(
            jpAlturaRugLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpAlturaRugLayout.createSequentialGroup()
                .addGroup(jpAlturaRugLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpAlturaRugLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jlTitAlturaRug, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jpAlturaRugLayout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addGroup(jpAlturaRugLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlZ0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jpAlturaRugLayout.createSequentialGroup()
                                .addComponent(jlZ0ref, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(23, 23, 23)
                                .addGroup(jpAlturaRugLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtfZ0, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtfZref, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jpAlturaRugLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jlmZred)
                                        .addGroup(jpAlturaRugLayout.createSequentialGroup()
                                            .addComponent(jtfZ0ref, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(12, 12, 12))
                                        .addComponent(jlmZ0ref))))
                            .addComponent(jlZref, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jpAlturaRugLayout.setVerticalGroup(
            jpAlturaRugLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpAlturaRugLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jlTitAlturaRug)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpAlturaRugLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlZref, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfZref, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlmZred))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpAlturaRugLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlZ0ref, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfZ0ref, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlmZ0ref))
                .addGap(8, 8, 8)
                .addGroup(jpAlturaRugLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfZ0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlZ0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpOpciones.setBackground(new java.awt.Color(255, 255, 255));
        jpOpciones.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jlTitOpciones.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlTitOpciones.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlTitOpciones.setText("OPCIONES");
        jlTitOpciones.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jrbVD.setBackground(new java.awt.Color(204, 204, 204));
        bgOpVel.add(jrbVD);
        jrbVD.setSelected(true);
        jrbVD.setText("<html>V<sub>D</sub> =  Velocidad derivada de CP</html>");
        jrbVD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cambioOpVel(evt);
            }
        });

        jlTipoK.setText("<html>Tipo k<sub>AG</sub></html>:");

        jrbKTorre.setBackground(new java.awt.Color(204, 204, 204));
        bgOpVelK.add(jrbKTorre);
        jrbKTorre.setSelected(true);
        jrbKTorre.setText("k - torre");

        jrbKNac.setBackground(new java.awt.Color(204, 204, 204));
        bgOpVelK.add(jrbKNac);
        jrbKNac.setText("k - nacelle");

        jlTipoCalculo.setText("<html>Tipo cálculo<sub>AG</sub><html>");

        jrbPromedio.setBackground(new java.awt.Color(204, 204, 204));
        bgOpVelDerTipo.add(jrbPromedio);
        jrbPromedio.setSelected(true);
        jrbPromedio.setText("Promedio");

        jrbPendiente.setBackground(new java.awt.Color(204, 204, 204));
        bgOpVelDerTipo.add(jrbPendiente);
        jrbPendiente.setText("Pendiente");

        jlTipoKRF.setText("<html>Tipo k<sub>RF</sub></html>");

        jrbKRFTorre.setBackground(new java.awt.Color(204, 204, 204));
        bgOpVelKRF.add(jrbKRFTorre);
        jrbKRFTorre.setSelected(true);
        jrbKRFTorre.setText("k - torre");

        jrbKRFNac.setBackground(new java.awt.Color(204, 204, 204));
        bgOpVelKRF.add(jrbKRFNac);
        jrbKRFNac.setText("k - nacelle");

        jrbVZ.setBackground(new java.awt.Color(204, 204, 204));
        bgOpVel.add(jrbVZ);
        jrbVZ.setText("<html>V<sub>Z</sub> =  Velocidad medida en Z</html>");
        jrbVZ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cambioOpVel(evt);
            }
        });

        jlMedPot.setText("Medida Pot");

        jrbTensionInt.setBackground(new java.awt.Color(204, 204, 204));
        bgMedidaPot.add(jrbTensionInt);
        jrbTensionInt.setSelected(true);
        jrbTensionInt.setText("V * I");

        jrbPotencia.setBackground(new java.awt.Color(204, 204, 204));
        bgMedidaPot.add(jrbPotencia);
        jrbPotencia.setText("Potencia");

        jlResAltura.setText("Altura resultados");

        jrbResAlturaZref.setBackground(new java.awt.Color(204, 204, 204));
        bgResAltura.add(jrbResAlturaZref);
        jrbResAlturaZref.setSelected(true);
        jrbResAlturaZref.setText("<html>z<sub>ref</sub></html>");
        jrbResAlturaZref.setEnabled(false);

        jrbResAlturaBuje.setBackground(new java.awt.Color(204, 204, 204));
        bgResAltura.add(jrbResAlturaBuje);
        jrbResAlturaBuje.setText("<html>H<sub>B</sub></html>");
        jrbResAlturaBuje.setEnabled(false);

        javax.swing.GroupLayout jpOpcionesLayout = new javax.swing.GroupLayout(jpOpciones);
        jpOpciones.setLayout(jpOpcionesLayout);
        jpOpcionesLayout.setHorizontalGroup(
            jpOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpOpcionesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpOpcionesLayout.createSequentialGroup()
                        .addComponent(jSeparator1)
                        .addContainerGap())
                    .addGroup(jpOpcionesLayout.createSequentialGroup()
                        .addComponent(jlTitOpciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jpOpcionesLayout.createSequentialGroup()
                        .addGroup(jpOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpOpcionesLayout.createSequentialGroup()
                                .addGap(142, 142, 142)
                                .addGroup(jpOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jrbKRFNac, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jrbPendiente, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jrbKNac, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jrbVD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpOpcionesLayout.createSequentialGroup()
                        .addGroup(jpOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jpOpcionesLayout.createSequentialGroup()
                                .addGroup(jpOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlMedPot)
                                    .addComponent(jlResAltura))
                                .addGroup(jpOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jrbTensionInt, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                                    .addComponent(jrbResAlturaZref))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jpOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jrbResAlturaBuje)
                                    .addComponent(jrbPotencia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jSeparator2)
                            .addGroup(jpOpcionesLayout.createSequentialGroup()
                                .addGroup(jpOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlTipoCalculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlTipoKRF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlTipoK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(4, 4, 4)
                                .addGroup(jpOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jrbKTorre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jrbPromedio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jrbKRFTorre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(73, 73, 73)))
                        .addGap(10, 10, 10))
                    .addGroup(jpOpcionesLayout.createSequentialGroup()
                        .addComponent(jrbVZ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jpOpcionesLayout.setVerticalGroup(
            jpOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpOpcionesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlTitOpciones)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jrbVD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlTipoK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jrbKTorre)
                    .addComponent(jrbKNac))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jrbPromedio)
                    .addComponent(jlTipoCalculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jrbPendiente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlTipoKRF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jrbKRFTorre)
                    .addComponent(jrbKRFNac))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jrbVZ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jrbPotencia)
                    .addComponent(jrbTensionInt)
                    .addComponent(jlMedPot))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jrbResAlturaBuje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jrbResAlturaZref, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlResAltura))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jbPrevisualizar.setBackground(new java.awt.Color(102, 102, 102));
        jbPrevisualizar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jbPrevisualizar.setForeground(new java.awt.Color(255, 255, 255));
        jbPrevisualizar.setText("PREVISUALIZAR");
        jbPrevisualizar.setEnabled(false);
        jbPrevisualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previsualizar(evt);
            }
        });

        jpVelocidad.setBackground(new java.awt.Color(255, 255, 255));
        jpVelocidad.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jlTitVelocidad.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlTitVelocidad.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlTitVelocidad.setText("VELOCIDAD");
        jlTitVelocidad.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jlVelDes.setText("Rango de velocidad");

        jtfVelDes.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtfVelDes.setName("Desde Velocidad"); // NOI18N

        jlVelSep.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlVelSep.setText("-");
        jlVelSep.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jtfVelHas.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtfVelHas.setName("Hasta Velocidad"); // NOI18N

        jtModosFunc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Desde", "Hasta"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtModosFunc.setColumnSelectionAllowed(true);
        jtModosFunc.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jtModosFunc);
        jtModosFunc.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (jtModosFunc.getColumnModel().getColumnCount() > 0) {
            jtModosFunc.getColumnModel().getColumn(0).setResizable(false);
            jtModosFunc.getColumnModel().getColumn(1).setResizable(false);
        }

        jButton1.setText("+");
        jButton1.setToolTipText("Añadir modo de funcionamiento");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anadirModoFunc(evt);
            }
        });

        jButton2.setText("-");
        jButton2.setToolTipText("Eliminar modo de funcionamiento");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarModoFunc(evt);
            }
        });

        javax.swing.GroupLayout jpVelocidadLayout = new javax.swing.GroupLayout(jpVelocidad);
        jpVelocidad.setLayout(jpVelocidadLayout);
        jpVelocidadLayout.setHorizontalGroup(
            jpVelocidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpVelocidadLayout.createSequentialGroup()
                .addGroup(jpVelocidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpVelocidadLayout.createSequentialGroup()
                        .addContainerGap(22, Short.MAX_VALUE)
                        .addGroup(jpVelocidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addGroup(jpVelocidadLayout.createSequentialGroup()
                                .addComponent(jtfVelDes, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(jlVelSep, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)
                                .addComponent(jtfVelHas, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                            .addComponent(jlVelDes))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpVelocidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jpVelocidadLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jlTitVelocidad, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jpVelocidadLayout.setVerticalGroup(
            jpVelocidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpVelocidadLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlTitVelocidad)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlVelDes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpVelocidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfVelDes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfVelHas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlVelSep))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpVelocidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpVelocidadLayout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        jpDireccion.setBackground(new java.awt.Color(255, 255, 255));
        jpDireccion.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jlTitDireccion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlTitDireccion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlTitDireccion.setText("DIRECCIÓN");
        jlTitDireccion.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jlDirEns.setText("Dirección Ensayo");

        jtfDirEns.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtfDirEns.setName("Dirección Ensayo"); // NOI18N
        jtfDirEns.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cambioDirEnsayo2(evt);
            }
        });
        jtfDirEns.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cambioDirEnsayo(evt);
            }
        });

        jlDirAmp.setText("Amplitud");

        jtfDirAmp.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtfDirAmp.setName("Amplitud"); // NOI18N
        jtfDirAmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cambioAmplitud2(evt);
            }
        });
        jtfDirAmp.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cambioAmplitud(evt);
            }
        });

        jtfDirMin.setEditable(false);
        jtfDirMin.setBackground(new java.awt.Color(204, 204, 204));
        jtfDirMin.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtfDirMin.setFocusable(false);
        jtfDirMin.setName("Desde Dirección"); // NOI18N

        jtfDirMax.setEditable(false);
        jtfDirMax.setBackground(new java.awt.Color(204, 204, 204));
        jtfDirMax.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtfDirMax.setFocusable(false);
        jtfDirMax.setName("Hasta Dirección"); // NOI18N

        javax.swing.GroupLayout jpDireccionLayout = new javax.swing.GroupLayout(jpDireccion);
        jpDireccion.setLayout(jpDireccionLayout);
        jpDireccionLayout.setHorizontalGroup(
            jpDireccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDireccionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpDireccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addGroup(jpDireccionLayout.createSequentialGroup()
                        .addComponent(jlDirEns)
                        .addGap(36, 36, 36)
                        .addComponent(jtfDirEns, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpDireccionLayout.createSequentialGroup()
                        .addComponent(jlDirAmp)
                        .addGap(76, 76, 76)
                        .addComponent(jtfDirAmp, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jlTitDireccion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.CENTER, jpDireccionLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jtfDirMin, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtfDirMax, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60))
        );
        jpDireccionLayout.setVerticalGroup(
            jpDireccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDireccionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlTitDireccion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpDireccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlDirEns)
                    .addComponent(jtfDirEns, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpDireccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlDirAmp)
                    .addComponent(jtfDirAmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpDireccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfDirMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfDirMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpIncertidumbre.setBackground(new java.awt.Color(255, 255, 255));
        jpIncertidumbre.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jlTitIncertidumbre.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlTitIncertidumbre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlTitIncertidumbre.setText("INCERTIDUMBRE");
        jlTitIncertidumbre.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jbIncertidumbres.setText("Establecer Incertidumbres");
        jbIncertidumbres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                establecerIncerts(evt);
            }
        });

        javax.swing.GroupLayout jpIncertidumbreLayout = new javax.swing.GroupLayout(jpIncertidumbre);
        jpIncertidumbre.setLayout(jpIncertidumbreLayout);
        jpIncertidumbreLayout.setHorizontalGroup(
            jpIncertidumbreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpIncertidumbreLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpIncertidumbreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jlTitIncertidumbre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbIncertidumbres))
                .addContainerGap())
        );
        jpIncertidumbreLayout.setVerticalGroup(
            jpIncertidumbreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpIncertidumbreLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlTitIncertidumbre)
                .addGap(58, 58, 58)
                .addComponent(jbIncertidumbres, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(92, 92, 92))
        );

        javax.swing.GroupLayout jpGeneralLayout = new javax.swing.GroupLayout(jpGeneral);
        jpGeneral.setLayout(jpGeneralLayout);
        jpGeneralLayout.setHorizontalGroup(
            jpGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpGeneralLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpClave, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jpGeneralLayout.createSequentialGroup()
                        .addGroup(jpGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jpOpciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jpPeriodo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(6, 6, 6)
                        .addGroup(jpGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jpAlturaRug, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jpVelocidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(jpGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jpDireccion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jpIncertidumbre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jbPrevisualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpTipoAnalisis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpGeneralLayout.setVerticalGroup(
            jpGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpGeneralLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jpTipoAnalisis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jpClave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(jpGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpPeriodo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpDireccion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpAlturaRug, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(6, 6, 6)
                .addGroup(jpGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jpOpciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpIncertidumbre, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpVelocidad, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(6, 6, 6)
                .addComponent(jbPrevisualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpGeneral, javax.swing.GroupLayout.DEFAULT_SIZE, 719, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpGeneral, javax.swing.GroupLayout.DEFAULT_SIZE, 623, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(735, 661));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

private void cambioPer(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cambioPer
    if (this.jrbPerOtr.isSelected()) {
        this.jsPerDes.setEnabled(true);
        this.jsPerDes.setFocusable(true);
        this.jsPerDes.setBackground(IVExtendido.FONDO_EDIT);
        this.jsPerDes.setInputVerifier(new IVExtendido(this, IVExtendido.TIPO_FECHA, false, false));
        
        this.jsPerHas.setEnabled(true);
        this.jsPerHas.setFocusable(true);
        this.jsPerHas.setBackground(IVExtendido.FONDO_EDIT);
        this.jsPerHas.setInputVerifier(new IVExtendido(this, IVExtendido.TIPO_FECHA, false, false));
    } else {
        if (!this.jtfFechaD.getText().isEmpty()) 
            this.jsPerDes.setText(this.jtfFechaD.getText());
        else
            this.jsPerDes.setText(TratFechas.FECHA_MIN);
        this.jsPerDes.setEnabled(false);
        this.jsPerDes.setFocusable(false);
        this.jsPerDes.setBackground(IVExtendido.FONDO_NO_EDIT);
        this.jsPerDes.setInputVerifier(null);
        
        if (!this.jtfFechaH.getText().isEmpty())
            this.jsPerHas.setText(this.jtfFechaH.getText());
        else
            this.jsPerHas.setText(TratFechas.FECHA_MAX);
        this.jsPerHas.setEnabled(false);
        this.jsPerHas.setFocusable(false);
        this.jsPerHas.setBackground(IVExtendido.FONDO_NO_EDIT);
        this.jsPerHas.setInputVerifier(null);
    }
}//GEN-LAST:event_cambioPer

private void actualizarSector()
{
    String dirText = this.jtfDirEns.getText();
    String ampText = this.jtfDirAmp.getText();
    
    if (    dirText.length() != 0 &&
            ampText.length() != 0
            ) {
        Double dir = Double.parseDouble(dirText);
        Double amp = Double.parseDouble(ampText);
        
        Double sector[] = DireccionRA.getSector(dir, amp);
        
        this.jtfDirMin.setText(sector[0].toString());
        this.jtfDirMax.setText(sector[1].toString());
    } else {
        this.jtfDirMin.setText("");
        this.jtfDirMax.setText("");
    }
}

private void cambioOpVel(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cambioOpVel
    this.jrbKTorre.setEnabled(this.jrbVD.isSelected());
    this.jrbKNac.setEnabled(this.jrbVD.isSelected());
    this.jrbKRFTorre.setEnabled(this.jrbVD.isSelected());
    this.jrbKRFNac.setEnabled(this.jrbVD.isSelected());
    this.jrbPromedio.setEnabled(this.jrbVD.isSelected());
    jrbPendiente.setEnabled(jrbVD.isSelected());//GEN-LAST:event_cambioOpVel
}                            

private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
    try {
        //Establecemos el título
        this.setTitle("SEGUIMIENTO - LÍMITES");
        
        //Añadimos los verificadores de los campos de entrada
        this.jtfPotNominal.setInputVerifier(new IVExtendido(this, IVExtendido.TIPO_DOUBLE, false, true));
        
        this.jtfZref.setInputVerifier(new IVExtendido(this, IVExtendido.TIPO_DOUBLE, false, true));
        this.jtfZ0ref.setInputVerifier(new IVExtendido(this, IVExtendido.TIPO_DOUBLE, false, true));
        this.jtfZ0.setInputVerifier(new IVExtendido(this, IVExtendido.TIPO_DOUBLE, false, true));
        this.jtfDirEns.setInputVerifier(new IVExtendido(this, IVExtendido.TIPO_DOUBLE, false, true));
        this.jtfDirAmp.setInputVerifier(new IVExtendido(this, IVExtendido.TIPO_DOUBLE, false, true));
        
        this.jtfVelDes.setInputVerifier(new IVExtendido(this, IVExtendido.TIPO_INT, false, true));
        this.jtfVelHas.setInputVerifier(new IVExtendido(this, IVExtendido.TIPO_INT, false, true));
        

        Auxiliares.cargaAsuntos(this.jcbAsunto);
        Auxiliares.cargaNormas(this.jcbNorma);

        limpiarCampos();
    } catch (SQLException e) {
        MensajeApp.muestraError(this, e, "Fallo al consultar la base de datos");
    } catch (NoSuchFieldException e) {
        MensajeApp.muestraError(this, e, "Fallo añadiendo campo");
    }
}//GEN-LAST:event_formWindowOpened

private void cargaPosiciones(Integer idAsunto) throws SQLException, NoSuchFieldException {
    AsuntoPosicionRA asuntoPos = AsuntoPosicionRA.getAsuntoPosicionRA(idAsunto);
    
    PosicionRA posAero = null;
    PosicionRA posMicro = null; 
    
    if (asuntoPos != null) { 
        posAero = asuntoPos.getIdPosAero() != null ? PosicionRA.getPosicion(asuntoPos.getIdPosAero()) : null;
        posMicro = asuntoPos.getIdPosMicro1() != null ? PosicionRA.getPosicion(asuntoPos.getIdPosMicro1()) : null; 
    }
    
    if (posAero != null)
        this.jtfPosAero.setText(posAero.toString());
    
    if (posMicro != null)
        this.jtfPosMicro.setText(posMicro.toString());
                
}

private void cargaConfiguracion(Integer idAsunto) throws SQLException, NoSuchFieldException {
    AsuntoConfRA conf = AsuntoConfRA.getAsuntoConfPorIdAsunto(idAsunto);
    ArrayList<AsuntoConfRAModos> confsModos = AsuntoConfRAModos.getAsuntoConfsPorIdAsunto(idAsunto);
    
    if (conf != null) {
        //Periodo
        Character perMedidoOtro;
        perMedidoOtro = conf.getPerMedidoOtro();
        if (perMedidoOtro.equals(AsuntoConfRA.PER_MEDIDO)) {
            this.jrbPerMed.setSelected(true);
            this.jrbPerOtr.setSelected(false);
            
            this.jsPerDes.setEnabled(false);
            this.jsPerDes.setFocusable(false);
            this.jsPerHas.setEnabled(false);
            this.jsPerHas.setFocusable(false);
            this.jsPerDes.setText(TratFechas.FECHA_MIN);
            this.jsPerHas.setText(TratFechas.FECHA_MAX);
        } else if (perMedidoOtro.equals(AsuntoConfRA.PER_OTRO)) {
            this.jrbPerMed.setSelected(false);
            this.jrbPerOtr.setSelected(true);
            
            this.jsPerDes.setEnabled(true);
            this.jsPerDes.setFocusable(true);
            this.jsPerHas.setEnabled(true);
            this.jsPerHas.setFocusable(true);
            this.jsPerDes.setTimeInMillis(conf.getDesdeFecha());
            this.jsPerHas.setTimeInMillis(conf.getHastaFecha());
        }
        cambioPer(null);
        
        //Altura y rugosidad
        this.jtfZref.setText(conf.getZRef().toString());
        this.jtfZ0ref.setText(conf.getZ0Ref().toString());
        this.jtfZ0.setText(conf.getZ0().toString());
                
        //Dirección
        this.jtfDirEns.setText(conf.getDirEnsayo().toString());
        this.jtfDirAmp.setText(conf.getAmplitud().toString());
        
        actualizarSector();
        
        //Opciones
        Character velDerMed, medidaPot, tipoK, tipoKRF, tipoCalculoVel;
        velDerMed = conf.getVelDerMed();
        medidaPot = conf.getMedidaPot();
        tipoK = conf.getTipoK();
        tipoKRF = conf.getTipoKRF();
        tipoCalculoVel = conf.getTipoCalculoVel();
        
        if (velDerMed.equals(AsuntoConfRA.VEL_DER)) {
            this.jrbVD.setSelected(true);
            this.jrbVZ.setSelected(false);
        } else if (velDerMed.equals(AsuntoConfRA.VEL_MED)) {
            this.jrbVD.setSelected(false);
            this.jrbVZ.setSelected(true);
        }
        
        if (medidaPot.equals(AsuntoConfRA.POT_MED)) {
            this.jrbPotencia.setSelected(true);
            this.jrbTensionInt.setSelected(false);
        } else if (velDerMed.equals(AsuntoConfRA.POT_V_I)) {
            this.jrbPotencia.setSelected(false);
            this.jrbTensionInt.setSelected(true);
        }
        
        if (tipoK.equals(AsuntoConfRA.K_TORRE)) {
            this.jrbKTorre.setSelected(true);
            this.jrbKNac.setSelected(false);
        } else if (tipoK.equals(AsuntoConfRA.K_NACELLE)) {
            this.jrbKTorre.setSelected(false);
            this.jrbKNac.setSelected(true);
        }

        if (tipoKRF.equals(AsuntoConfRA.K_TORRE)) {
            this.jrbKRFTorre.setSelected(true);
            this.jrbKRFNac.setSelected(false);
        } else if (tipoKRF.equals(AsuntoConfRA.K_NACELLE)) {
            this.jrbKRFTorre.setSelected(false);
            this.jrbKRFNac.setSelected(true);
        }
        
        if (tipoCalculoVel.equals(AsuntoConfRA.CALC_PROMEDIO)) {
            this.jrbPromedio.setSelected(true);
            this.jrbPendiente.setSelected(false);
        } else if (tipoCalculoVel.equals(AsuntoConfRA.CALC_PENDIENTE)) {
            this.jrbPromedio.setSelected(false);
            this.jrbPendiente.setSelected(true);
        }
        
        cambioOpVel(null);
        
        //Velocidad
        this.jtfVelDes.setText(conf.getDesdeVel().toString());
        this.jtfVelHas.setText(conf.getHastaVel().toString());

        //Incertidumbre
        /*
        this.jtfUB1.setText(conf.getUB1().toString());
        this.jtfUB2.setText(conf.getUB2().toString());
        this.jtfUB3_SPL.setText(conf.getUB3_SPL().toString());
        this.jtfUB3_OCT_FFT.setText(conf.getUB3_OCT_FFT().toString());
        this.jtfUB4.setText(conf.getUB4().toString());
        this.jtfUB5.setText(conf.getUB5().toString());
        this.jtfUB6.setText(conf.getUB6().toString());
        this.jtfUB7Medida.setText(conf.getUB7Med().toString());
        this.jtfUB7Derivada.setText(conf.getUB7Der().toString());
        this.jtfUB8.setText(conf.getUB8().toString());
        this.jtfUB9.setText(conf.getUB9().toString());
        
        this.jtfUB1.setCaretPosition(0);
        this.jtfUB2.setCaretPosition(0);
        this.jtfUB3_SPL.setCaretPosition(0);
        this.jtfUB3_OCT_FFT.setCaretPosition(0);
        this.jtfUB4.setCaretPosition(0);
        this.jtfUB5.setCaretPosition(0);
        this.jtfUB6.setCaretPosition(0);
        this.jtfUB7Medida.setCaretPosition(0);
        this.jtfUB7Derivada.setCaretPosition(0);
        this.jtfUB8.setCaretPosition(0);
        this.jtfUB9.setCaretPosition(0);
         * */
    } else {
        //Caragamos valores por defecto de la norma de aplicación
        AsuntoRA asunto = AsuntoRA.getAsuntoPorId(idAsunto);
        Integer idNorma = asunto != null ? asunto.getIdNorma() : null;
        
        if (idNorma != null) {
            if (idNorma.equals(NormaRA.ID_NORMA_IEC_2_1)) {
                this.jtfZref.setText(NormaRA.Z_REF_IEC.toString());
                this.jtfDirAmp.setText(NormaRA.AMPLITUD_IEC.toString());
                
                this.jtfVelDes.setText(NormaRA.DESDE_VEL_IEC_2_1.toString());
                this.jtfVelHas.setText(NormaRA.HASTA_VEL_IEC_2_1.toString());
            } else if (idNorma.equals(NormaRA.ID_NORMA_AWEA)) {
                this.jtfZref.setText(NormaRA.Z_REF_AWEA.toString());
                this.jtfDirAmp.setText(NormaRA.AMPLITUD_AWEA.toString());
                
                this.jtfVelDes.setText(NormaRA.DESDE_VEL_AWEA.toString());
                this.jtfVelHas.setText(NormaRA.HASTA_VEL_AWEA.toString());
            } else if (idNorma.equals(NormaRA.ID_NORMA_BWEA)) {
                this.jtfZref.setText(NormaRA.Z_REF_BWEA.toString());
                this.jtfDirAmp.setText(NormaRA.AMPLITUD_BWEA.toString());
                
                this.jtfVelDes.setText(NormaRA.DESDE_VEL_BWEA.toString());
                this.jtfVelHas.setText(NormaRA.HASTA_VEL_BWEA.toString());
            } else if (idNorma.equals(NormaRA.ID_NORMA_IEC_3_0)) {
                this.jtfZref.setText(NormaRA.Z_REF_IEC.toString());
                this.jtfDirAmp.setText(NormaRA.AMPLITUD_IEC.toString());
                
                AerogeneradorRA aero = asunto.getIdAero() != null ? AerogeneradorRA.getAeroPorId(asunto.getIdAero()) : null;
                
                if (aero != null) {
                    Integer[] limitesVel = DatosRA2.getLimitesBinesPorcPotencia(idAsunto, DENSIDAD_DEF, NormaRA.PORC_VEL_IEC_3_0, aero.getPNominal(), NormaRA.FACTOR_MIN_VEL_IEC_3_0, NormaRA.FACTOR_MAX_VEL_IEC_3_0);
                    
                    if (limitesVel != null) {
                        this.jtfVelDes.setText(limitesVel[0].toString());
                        this.jtfVelHas.setText(limitesVel[1].toString());
                    }
                }
            }
        }
    }
    
    if (confsModos != null) {
        int nConfs = confsModos.size();
        AsuntoConfRAModos confModos;
        DefaultTableModel dtmModos = (DefaultTableModel) this.jtModosFunc.getModel();
        
        for (int i = 0; i < nConfs; i++) {
            confModos = confsModos.get(i);
            
            dtmModos.addRow(new Object[]{confModos.getDesdeVel(), confModos.getHastaVel()});
        }
    }
}

private void guardaConfiguracion(Integer idAsunto) throws SQLException, NoSuchFieldException {
    AsuntoConfRA confVieja, confNueva;
    AsuntoConfRAModos confModos;
    Long desdeFecha, hastaFecha;
    
    confVieja = AsuntoConfRA.getAsuntoConfPorIdAsunto(idAsunto);
    
    Character perMedidoOtro = null, velDerMed = null, medidaPot = null, tipoK = null, tipoKRF = null, tipoCalculoVel = null;
    
    if (this.jrbPerMed.isSelected())
        perMedidoOtro = AsuntoConfRA.PER_MEDIDO;
    else if (this.jrbPerOtr.isSelected())
        perMedidoOtro = AsuntoConfRA.PER_OTRO;
        
    if (this.jrbVD.isSelected())
        velDerMed = AsuntoConfRA.VEL_DER;
    else if (this.jrbVZ.isSelected())
        velDerMed = AsuntoConfRA.VEL_MED;
    
    if (this.jrbPotencia.isSelected())
        medidaPot = AsuntoConfRA.POT_MED;
    else if (this.jrbTensionInt.isSelected())
        medidaPot = AsuntoConfRA.POT_V_I;
        
    if (this.jrbKNac.isSelected())
        tipoK = AsuntoConfRA.K_NACELLE;
    else if (this.jrbKTorre.isSelected())
        tipoK = AsuntoConfRA.K_TORRE;
        
    if (this.jrbKRFNac.isSelected())
        tipoKRF = AsuntoConfRA.K_NACELLE;
    else if (this.jrbKRFTorre.isSelected())
        tipoKRF = AsuntoConfRA.K_TORRE;
        
    if (this.jrbPendiente.isSelected())
        tipoCalculoVel = AsuntoConfRA.CALC_PENDIENTE;
    else if (this.jrbPromedio.isSelected())
        tipoCalculoVel = AsuntoConfRA.CALC_PROMEDIO;
        
    
    desdeFecha = this.jsPerDes.getValue() != null ? this.jsPerDes.getTimeInMillis() : null;
    hastaFecha = this.jsPerHas.getValue() != null ? this.jsPerHas.getTimeInMillis() : null;

    confNueva = new AsuntoConfRA(idAsunto, 
            perMedidoOtro, 
            desdeFecha, 
            hastaFecha, 
            Double.parseDouble(this.jtfZref.getText()), 
            Double.parseDouble(this.jtfZ0ref.getText()), 
            Double.parseDouble(this.jtfZ0.getText()), 
            Double.parseDouble(this.jtfDirEns.getText()), 
            Double.parseDouble(this.jtfDirAmp.getText()), 
            velDerMed,
            medidaPot,
            tipoK, 
			tipoKRF,
            tipoCalculoVel, 
            Integer.parseInt(this.jtfVelDes.getText()), 
            Integer.parseInt(this.jtfVelHas.getText()));
    AsuntoConfRA.insertOrUpdateAsuntoConf(confVieja, confNueva, null);
    
    //Borramos la configuración de modos anterior y establecemos la nueva;
    AsuntoConfRAModos.deleteAsuntoConfs(idAsunto, null, null, null);
    DefaultTableModel dtmModos = (DefaultTableModel) this.jtModosFunc.getModel();
    int nModos = dtmModos.getRowCount();
    
    for (int i = 0; i < nModos; i++) {
        AsuntoConfRAModos.insertAsuntoConf(idAsunto, (Integer) dtmModos.getValueAt(i, 0), (Integer) dtmModos.getValueAt(i, 1), null);
    }
}

private void actualizaFechas(Integer idAsunto) {
    Long fechaIni = null, fechaFin = null, fechaAux;
    
    try {
        if (idAsunto != null) {
            if (this.jcbSPL.isSelected()) {
                fechaIni = DatosRA2.getFechaIniDatos(Auxiliares.TIPO_SPL, idAsunto);
                fechaFin = DatosRA2.getFechaFinDatos(Auxiliares.TIPO_SPL, idAsunto);
            }
            if (this.jcbOCT.isSelected()) {
                if (fechaIni == null || fechaIni == TratFechas.millisFecha(TratFechas.FECHA_MIN))
                    fechaIni = DatosRA2.getFechaIniDatos(Auxiliares.TIPO_OCT, idAsunto);
                else {
                    fechaAux = DatosRA2.getFechaIniDatos(Auxiliares.TIPO_OCT, idAsunto);

                    if (fechaAux != TratFechas.millisFecha(TratFechas.FECHA_MIN))
                        fechaIni = Math.min(fechaIni, fechaAux);
                }    

                if (fechaFin == null || fechaFin == TratFechas.millisFecha(TratFechas.FECHA_MAX))
                    fechaFin = DatosRA2.getFechaFinDatos(Auxiliares.TIPO_OCT, idAsunto);
                else {
                    fechaAux = DatosRA2.getFechaFinDatos(Auxiliares.TIPO_OCT, idAsunto);

                    if (fechaAux != TratFechas.millisFecha(TratFechas.FECHA_MAX))
                        fechaFin = Math.max(fechaFin, fechaAux);
                }
            }
            if (this.jcbFFT.isSelected()) {
                if (fechaIni == null  || fechaIni == TratFechas.millisFecha(TratFechas.FECHA_MIN))
                    fechaIni = DatosRA2.getFechaIniDatos(Auxiliares.TIPO_FFT, idAsunto);
                else {
                    fechaAux = DatosRA2.getFechaIniDatos(Auxiliares.TIPO_FFT, idAsunto);

                    if (fechaAux != TratFechas.millisFecha(TratFechas.FECHA_MIN))
                        fechaIni = Math.min(fechaIni, fechaAux);
                }

                if (fechaFin == null || fechaFin == TratFechas.millisFecha(TratFechas.FECHA_MAX))
                    fechaFin = DatosRA2.getFechaFinDatos(Auxiliares.TIPO_FFT, idAsunto);
                else {
                    fechaAux = DatosRA2.getFechaFinDatos(Auxiliares.TIPO_FFT, idAsunto);

                    if (fechaAux != TratFechas.millisFecha(TratFechas.FECHA_MAX))
                        fechaFin = Math.max(fechaFin, fechaAux);
                }
            }

            if (fechaIni != null)
                this.jtfFechaD.setText(TratFechas.toStringFecha(fechaIni));
            else
                this.jtfFechaD.setText(null);
            if (fechaFin != null)
                this.jtfFechaH.setText(TratFechas.toStringFecha(fechaFin));
            else
                this.jtfFechaH.setText(null);
        }
    } catch (SQLException e) {
        MensajeApp.muestraError(this, e, "Fallo al consultar la base de datos");
    }
}

private void cambioAsunto(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cambioAsunto
    try {
        limpiarCampos();

        Integer idAsunto = (Integer) ComboBoxObject.getClaveSelCombo(this.jcbAsunto);

        if (idAsunto != null) {
            Integer idNorma = AsuntoRA.getAsuntoPorId(idAsunto).getIdNorma();
            DireccionRA direccion = DireccionRA.getDireccionPorIdAsunto(idAsunto);

            if (direccion != null) {
                Double dir = direccion.getDireccion();

                if (dir != null) {
                    this.jtfDirEns.setText(dir.toString());
                    actualizarSector();
                }
            }
            
            this.jcbNorma.setSelectedItem(ComboBoxObject.getItemWithKey(this.jcbNorma, idNorma));
            this.jcbNorma.setEnabled(true);
            
            actualizaFechas(idAsunto);
            
            Integer idAero = AsuntoRA.getAsuntoPorId(idAsunto).getIdAero();
            AerogeneradorRA aero = idAero != null ? AerogeneradorRA.getAeroPorId(idAero) : null;
            
            if (aero != null)
                this.jtfPotNominal.setText(aero.getPNominal().toString());
            
            //Carga posiciones
            cargaPosiciones(idAsunto);
            
            //Cargamos los valores almacenados como límites
            cargaConfiguracion(idAsunto);
        }
        actualizaBotones();
    } catch (SQLException e) {
        MensajeApp.muestraError(this, e, "Fallo al consultar la base de datos");
    } catch (NoSuchFieldException e) {
        MensajeApp.muestraError(this, e, "Fallo añadiendo campo");
    }
}//GEN-LAST:event_cambioAsunto

private void previsualizar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previsualizar
    DatosVisualizacionGUI dV;
    JProgressBar jpb = null;
    
    try {
        if (Auxiliares.validaCampos(this)) { //Todos los campos deben de ser válidos
			if (!this.jcbSPL.isSelected() && !this.jcbOCT.isSelected() && !this.jcbFFT.isSelected()) {
				MensajeApp.muestraWarning(this, "Debe seleccionar al menos un tipo de análisis a realizar");
				return;
			}
	    
            Integer idAsunto = (Integer) ComboBoxObject.getClaveSelCombo(this.jcbAsunto);
            
            if (idAsunto != null) {
                ArrayList<Integer> sites, sitesTipo;
                ArrayList<String> tiposTabla;
                long fechaIniPer, fechaFinPer;
                double zRef, z0Ref, z0, sector[], potNominal;
                int calculoVel = -1, ajuste = -1, ajusteRF = -1, calculoAjuste = -1, calculoPot = -1;
                
                //Variable para control de llamadas entre diálogos
                ArrayList<Integer> modoSalida = new ArrayList<Integer>();
                
                Integer idNorma = (Integer) ComboBoxObject.getClaveSelCombo(this.jcbNorma);

                //Cogemos los sites del asunto
                sites = ConfiguracionRA2.getIdSitesRA(idAsunto);

                //Nos quedamos con los que corresponden al tipo que nos interesa
                int nSites = sites.size();
                sitesTipo = new ArrayList<Integer>();
                tiposTabla = new ArrayList<String>();
                String sufijoTipoRA;
                for (int i = 0; i < nSites; i++) {
                    sufijoTipoRA = TipoRA.getTipoRAPorIdSite(sites.get(i)).getSufijo();
                    if (    !idNorma.equals(NormaRA.ID_NORMA_IEC_3_0) && sufijoTipoRA.contentEquals(Auxiliares.TIPO_SPL) && this.jcbSPL.isSelected() ||
                            sufijoTipoRA.contentEquals(Auxiliares.TIPO_OCT) && this.jcbOCT.isSelected() ||
                            sufijoTipoRA.contentEquals(Auxiliares.TIPO_FFT) && this.jcbFFT.isSelected() || //El site es del tipo del cálculo
                            idNorma.equals(NormaRA.ID_NORMA_IEC_3_0) && sufijoTipoRA.contentEquals(Auxiliares.TIPO_OCT) && this.jcbSPL.isSelected()) {//Caso especial IEC3
                        sitesTipo.add(sites.get(i));
                        if (idNorma.equals(NormaRA.ID_NORMA_IEC_3_0) && sufijoTipoRA.contentEquals(Auxiliares.TIPO_OCT) && this.jcbSPL.isSelected())
                            tiposTabla.add(Auxiliares.TIPO_SPL);
                        else
                            tiposTabla.add(sufijoTipoRA);
                    }
                }

                //Periodo de cálculo
                if (this.jrbPerMed.isSelected()) {  //Periodo medido
                    fechaIniPer = TratFechas.millisFecha(this.jtfFechaD.getText());
                    fechaFinPer = TratFechas.millisFecha(this.jtfFechaH.getText());
                } else { //Periodo definido por el usuario
                    fechaIniPer = this.jsPerDes.getTimeInMillis();
                    fechaFinPer = this.jsPerHas.getTimeInMillis();
                }
                
                //Sector a calcular
                sector = new double[]{Double.parseDouble(this.jtfDirMin.getText()), Double.parseDouble(this.jtfDirMax.getText())};

                //Variables de altura/rugosidad
                zRef = Double.parseDouble(this.jtfZref.getText());
                z0Ref = Double.parseDouble(this.jtfZ0ref.getText());
                z0 = Double.parseDouble(this.jtfZ0.getText());
                
                potNominal = Double.parseDouble(this.jtfPotNominal.getText());
                
                if (this.jrbTensionInt.isSelected())
                    calculoPot = DatosRA2.POT_DESDE_V_I;
                else
                    calculoPot = DatosRA2.POT_MEDIDA;
                
                if (this.jrbVD.isSelected()) { //Velocidad derivada de CP
                    calculoVel = DatosRA2.VEL_DERIVADA_CP;
                    
                    if (this.jrbKTorre.isSelected()) { //K-factor
                        ajuste = DatosRA2.AJUSTE_K_TORRE;
                    } else { //K-nacelle
                        ajuste = DatosRA2.AJUSTE_K_NACELLE;
                        /*
                        SerieRA2 serieVelNacelle = SerieRA2.getSerieRA2PorCodigo(DatosRA2.CAMPO_V_N);
                        Integer idSerieVelNacelle = serieVelNacelle != null ? serieVelNacelle.getIdSerie() : null;
                        if (Descripcion.getDescripcion(idAsunto, idSerieVelNacelle) == null) {    //No existe la descripción para velocidad de nacelle en el asunto
                            MensajeApp.muestraError(this, null, "No hay configurada una descripción para la velocidad de nacelle.\nNo se puede continuar");
                            return;
                        }
			*/
                    }

                    if (this.jrbKRFTorre.isSelected()) { //K-factor
                        ajusteRF = DatosRA2.AJUSTE_K_TORRE;
                    } else { //K-nacelle
                        ajusteRF = DatosRA2.AJUSTE_K_NACELLE;
					}
                    
                    if (this.jrbPromedio.isSelected()) { //Promedio
                        calculoAjuste = DatosRA2.CALCULO_K_PROMEDIO;
                    } else { //Pendiente
                        calculoAjuste = DatosRA2.CALCULO_K_PENDIENTE;
                        MensajeApp.muestraWarning(this, "Función <i>calculo k pendiente</i> no implementada");
                        return;
                    }
                } else if (this.jrbVZ.isSelected()) {  //Velocidad Medida en Z
                    calculoVel = DatosRA2.VEL_MEDIDA;
                    ajuste = -1;
                    ajusteRF = -1;
                }

				boolean resAlturaBuje = this.jrbResAlturaBuje.isSelected();
                
                //Modos de funcionamiento
                ArrayList<Integer[]> modosFunc = null;
                int nModosFunc = this.jtModosFunc.getRowCount();
                
                if (nModosFunc > 0) { 
                    modosFunc = new ArrayList<Integer[]>();
                    
                    for (int i = 0; i < nModosFunc; i++) {
                        modosFunc.add(new Integer[]{(Integer) this.jtModosFunc.getValueAt(i, 0), (Integer) this.jtModosFunc.getValueAt(i, 1)});
                    }
                }

				Boolean esMiniAero = this.jcbMiniAero.isSelected();
                
                if (idNorma.equals(NormaRA.ID_NORMA_BWEA) && nModosFunc == 0) {
                    MensajeApp.muestraWarning(this, "No hay modos de funcionamiento declarados para realizar el análisis bajo la norma BWEA.");
                } else {                
                    //Incertidumbre de componentes
                    ArrayList<AsuntoIncert> incertidumbres = AsuntoIncert.getAsuntoIncertPorRangoFechas(idAsunto, idNorma, fechaIniPer, fechaFinPer);
                    //Quitamos las incertidumbres por velocidad de viento que no correspondan
                    AsuntoIncert.filtrarVelocidad(incertidumbres, calculoVel);

                    if (incertidumbres == null || incertidumbres.isEmpty()) {
                        MensajeApp.muestraWarning(this, "No hay incertidumbres establecidas para el asunto, norma y fecha de análisis declarados.");
                    } else {
                        //Guardamos la configuración
                        guardaConfiguracion(idAsunto);

                        nSites = sitesTipo.size();
                        String tipoTabla;
                        String valiCrea;
                        for (int i = 0; i < nSites; i++) {
                            //tipoTabla = TipoRA.getTipoRAPorIdSite(sitesTipo.get(i)).getSufijo();
                            tipoTabla = tiposTabla.get(i);

							modoSalida = new ArrayList<Integer>();

                            //Mostramos una progress para indicar que se está trabajando en las bases
                            Auxiliares.bloqueaDialog(this, true);
                            jpb = Auxiliares.muestraProgress(this, 100 * 1000, "Creando vistas auxiliares...");
                            
                            valiCrea = DatosRA2.createVistaAux(idNorma, tipoTabla, idAsunto, sitesTipo.get(i), calculoVel, calculoPot, ID_SERIE_DEF, DENSIDAD_DEF, fechaIniPer, fechaFinPer, sector, zRef, z0Ref, z0, resAlturaBuje, esMiniAero);
                            if (valiCrea.length() == 0) {
                                Auxiliares.incPorcentajeProgress(jpb, 0.5);
                                ArrayList<Object> ajustes = new ArrayList<Object>(); //Variable para recoger los ajustes
                                valiCrea = DatosRA2.createVistaNeta(idNorma, tipoTabla, idAsunto, sitesTipo.get(i), potNominal, calculoVel, ajuste, ajusteRF, calculoAjuste, DENSIDAD_DEF, zRef, z0Ref, ajustes);
                                if (valiCrea.length() == 0) {
                                    Auxiliares.incPorcentajeProgress(jpb, 0.5);
                                    Auxiliares.ocultaProgress(jpb);
                                    Auxiliares.bloqueaDialog(this, false);
                                    jpb = null;

                                    //Mostramos la previsualización de datos
                                    if (i == 0)
                                        this.setVisible(false);

                                    dV = new DatosVisualizacionGUI((Frame) this.getParent(), tipoTabla, idAsunto, sitesTipo.get(i), Integer.parseInt(this.jtfVelDes.getText()), Integer.parseInt(this.jtfVelHas.getText()), (Double) ajustes.get(0), (Double) ajustes.get(1), (double[]) ajustes.get(2), (double[]) ajustes.get(3), modosFunc, modoSalida, idNorma, esMiniAero, incertidumbres, calculoVel, ajuste, ajusteRF);
                                    //dV = new DatosVisualizacionGUI((Frame) this.getParent(), tipoTabla, idAsunto, sitesTipo.get(i), Integer.parseInt(this.jtfVelDes.getText()), Integer.parseInt(this.jtfVelHas.getText()), modoSalida);
                                    dV.setVisible(true);

                                    if (!Auxiliares.esSalidaOk(modoSalida))
                                        break;
                                } else {
                                    MensajeApp.muestraError(this, null, "No se puede crear la base neta para el site &lt;" + SiteRA.getSitePorId(sitesTipo.get(i)).getNombre() + "&gt;." + valiCrea);
                                }
                            } else {
                                MensajeApp.muestraError(this, null, "No se puede crear la base auxiliar para el site &lt;" + SiteRA.getSitePorId(sitesTipo.get(i)).getNombre() + "&gt;." + valiCrea);
                            }
                        }

                        if (Auxiliares.esSalidaOk(modoSalida))
                            MensajeApp.muestraInfo(this, Auxiliares.PROCESS_OK);
                    }
                }
            }
        }
    } catch (SQLException e) {
        MensajeApp.muestraError(this, e, "Fallo al consultar la base de datos");
    } catch (NoSuchFieldException e) {
        MensajeApp.muestraError(this, e, "Fallo añadiendo campo");
    } finally {
        try {
            if (jpb != null) {
                Auxiliares.ocultaProgress(jpb);
				Auxiliares.bloqueaDialog(this, false);
            }
            
            DatosRA2.deleteVistas();
        } catch (SQLException e) {
            //Si ha fallado la consulta a la BD, ya se habrá mostrado el aviso con anterioridad
        }
        
        this.setVisible(true);
    }
}//GEN-LAST:event_previsualizar

private void cambioDirEnsayo2(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cambioDirEnsayo2
    //Actualizamos el sector
    actualizarSector();
}//GEN-LAST:event_cambioDirEnsayo2

private void cambioAmplitud2(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cambioAmplitud2
   //Actualizamos el sector
    actualizarSector();
}//GEN-LAST:event_cambioAmplitud2

private void cambioAmplitud(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cambioAmplitud
    //Actualizamos el sector
    actualizarSector();
}//GEN-LAST:event_cambioAmplitud

private void cambioDirEnsayo(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cambioDirEnsayo
    //Actualizamos el sector//GEN-LAST:event_cambioDirEnsayo
    actualizarSector();
}

private void anadirModoFunc(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anadirModoFunc
    this.jtfVelDesModoFunc.setText("");
    this.jtfVelHasModoFunc.setText("");
    
    if (JOptionPane.showConfirmDialog(this, this.jpNuevoModoFunc, "Añadir modo de funcionamiento", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
        try {
            Integer desdeVel = Integer.parseInt(this.jtfVelDesModoFunc.getText());
            Integer hastaVel = Integer.parseInt(this.jtfVelHasModoFunc.getText());

            DefaultTableModel dtmModosFunc = (DefaultTableModel) this.jtModosFunc.getModel();
            dtmModosFunc.addRow(new Object[]{desdeVel, hastaVel});
        } catch (NumberFormatException e) {
            MensajeApp.muestraError(this, e, "Se debe introducir un valor numérico");
        }
    }
}//GEN-LAST:event_anadirModoFunc

private void eliminarModoFunc(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarModoFunc
    DefaultTableModel dtmModosFunc = (DefaultTableModel) this.jtModosFunc.getModel();
    
    int filaEliminar = this.jtModosFunc.getSelectedRow();
    
    if (filaEliminar != -1)
        dtmModosFunc.removeRow(filaEliminar);
}//GEN-LAST:event_eliminarModoFunc

private void jcbSPLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbSPLActionPerformed
    actualizaFechas((Integer) ComboBoxObject.getClaveSelCombo(this.jcbAsunto));
}//GEN-LAST:event_jcbSPLActionPerformed

private void jcbOCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbOCTActionPerformed
    actualizaFechas((Integer) ComboBoxObject.getClaveSelCombo(this.jcbAsunto));
}//GEN-LAST:event_jcbOCTActionPerformed

private void jcbFFTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbFFTActionPerformed
    actualizaFechas((Integer) ComboBoxObject.getClaveSelCombo(this.jcbAsunto));
}//GEN-LAST:event_jcbFFTActionPerformed

private void cambioNorma(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cambioNorma
    try {
        Integer idNorma = (Integer) ComboBoxObject.getClaveSelCombo(this.jcbNorma);

        this.jcbMiniAero.setEnabled(false);
        this.jcbMiniAero.setSelected(false);

        if (idNorma != null) {
            if (idNorma.equals(NormaRA.ID_NORMA_IEC_3_0)) {
                this.jcbMiniAero.setEnabled(true);

                Integer idAsunto = (Integer) ComboBoxObject.getClaveSelCombo(this.jcbAsunto);
                AsuntoRA asunto = AsuntoRA.getAsuntoPorId(idAsunto);
                Integer idAero = asunto != null ? asunto.getIdAero() : null;
                AerogeneradorRA aero = idAero != null ? AerogeneradorRA.getAeroPorId(idAero) : null;

                /*****  No marcamos como miniAero porque no es estandar su definición todavía.
                this.jcbMiniAero.setSelected(aero != null && aero.getDN() != null && Math.PI * Math.pow(aero.getDN()/2.0, 2.0) <= 200);
                 *****/

				this.jrbResAlturaBuje.setEnabled(true);
				this.jrbResAlturaZref.setEnabled(true);
            } else {
				this.jrbResAlturaBuje.setEnabled(false);
				this.jrbResAlturaZref.setEnabled(false);
			}

			if (!idNorma.equals(NormaRA.ID_NORMA_BWEA))
				this.jtfZref.setText(Z_REF.toString());
			else {
                Integer idAsunto = (Integer) ComboBoxObject.getClaveSelCombo(this.jcbAsunto);
                AsuntoRA asunto = AsuntoRA.getAsuntoPorId(idAsunto);
                Integer idAero = asunto != null ? asunto.getIdAero() : null;
                AerogeneradorRA aero = idAero != null ? AerogeneradorRA.getAeroPorId(idAero) : null;
		
				this.jtfZref.setText(aero.getHB().toString());
			}
        }
        
        actualizaBotones();
    } catch (SQLException e) {
        MensajeApp.muestraError(this, e, "Fallo al consultar la base de datos");
    } catch (NoSuchFieldException e) {
        MensajeApp.muestraError(this, e, "Fallo añadiendo campo");
    }
}//GEN-LAST:event_cambioNorma

private void establecerIncerts(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_establecerIncerts
    Integer idAsunto = (Integer) ComboBoxObject.getClaveSelCombo(this.jcbAsunto);
    Long desdeFecha;
    if (this.jrbPerMed.isSelected())
        desdeFecha = TratFechas.millisFecha(this.jtfFechaD.getText());
    else
        desdeFecha = this.jsPerDes.getTimeInMillis();
    
    Integer idNorma = (Integer) ComboBoxObject.getClaveSelCombo(this.jcbNorma);
    new Incertidumbres((Frame) this.getParent(), idAsunto, desdeFecha, idNorma).setVisible(true);
}//GEN-LAST:event_establecerIncerts

private void jpNuevoModoFuncAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jpNuevoModoFuncAncestorAdded
    this.jtfVelDesModoFunc.requestFocus();
}//GEN-LAST:event_jpNuevoModoFuncAncestorAdded

private void jpTipoCalculoOCTModoFuncAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jpTipoCalculoOCTModoFuncAncestorAdded
    this.jtfBinEstudio.requestFocus();
}//GEN-LAST:event_jpTipoCalculoOCTModoFuncAncestorAdded

private void jtfPotNominalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtfPotNominalMouseClicked
    Integer idAsunto = (Integer) ComboBoxObject.getClaveSelCombo(this.jcbAsunto);
    
    if (idAsunto != null) {
        this.jtfPotNominal.setEditable(true);
        this.jtfPotNominal.setBackground(IVExtendido.FONDO_EDIT);
    }
}//GEN-LAST:event_jtfPotNominalMouseClicked

private void limpiarCampos(){
    //Potencia Nominal
    this.jtfPotNominal.setText(null);
    this.jtfPotNominal.setEditable(false);
    this.jtfPotNominal.setBackground(IVExtendido.FONDO_NO_EDIT);
    
    // Norma
    this.jcbNorma.setSelectedIndex(-1);
    this.jcbNorma.setEnabled(false);
        
    //Posiciones
    this.jtfPosAero.setText(null);
    this.jtfPosMicro.setText(null);
    
    // Periodo
    this.jtfFechaD.setText(null);
    this.jtfFechaH.setText(null);
    this.jsPerHas.setEnabled(false);
    this.jsPerHas.setText(TratFechas.FECHA_MAX);
    this.jsPerDes.setEnabled(false);
    this.jsPerDes.setText(TratFechas.FECHA_MIN);
    this.jrbPerMed.setSelected(true);
    this.jrbPerOtr.setSelected(false);
    cambioPer(null);

    // Dirección
    this.jtfDirMin.setText(null);
    this.jtfDirMax.setText(null);
    this.jtfDirEns.setText(null);
    this.jtfDirAmp.setText(AMPLITUD.toString());
    
    // Rugosidad
    this.jtfZref.setText(Z_REF.toString());
    this.jtfZ0ref.setText(Z0_REF.toString());
    this.jtfZ0.setText(null);
    
    // Velocidad
    this.jtfVelDes.setText(DESDE_VELOCIDAD.toString());
    this.jtfVelHas.setText(HASTA_VELOCIDAD.toString());
    
    // Opciones
    this.jrbVD.setSelected(true);
    this.jrbVZ.setSelected(false);
    this.jrbKTorre.setSelected(true);
    this.jrbKNac.setSelected(false);
    this.jrbKRFTorre.setSelected(true);
    this.jrbKRFNac.setSelected(false);
    this.jrbPromedio.setSelected(true);
    this.jrbPendiente.setSelected(false);
    cambioOpVel(null);
    
    this.jrbTensionInt.setSelected(true);
    this.jrbPotencia.setSelected(false);
    
    //Inicializamos variables por defecto
    this.jtfZ0ref.setText(Z0_REF.toString());
    this.jtfZref.setText(Z_REF.toString());
    this.jtfDirAmp.setText(AMPLITUD.toString());
    this.jtfVelDes.setText(DESDE_VELOCIDAD.toString());
    this.jtfVelHas.setText(HASTA_VELOCIDAD.toString());
    
    /*
    this.jtfUB1.setText("0.173205081");
    this.jtfUB2.setText("0.173205081");
    this.jtfUB3_SPL.setText("0.288675135");
    this.jtfUB3_OCT_FFT.setText("1.0"); //0.9815
    this.jtfUB4.setText("0.057735027");
    this.jtfUB5.setText("0.115470054");
    this.jtfUB6.setText("0.404145188");
    this.jtfUB7Medida.setText("0.866025404");
    this.jtfUB7Derivada.setText("0.173205081");
    this.jtfUB8.setText("0.288675135");
    this.jtfUB9.setText("0.1");
    
    this.jtfUB1.setCaretPosition(0);
    this.jtfUB2.setCaretPosition(0);
    this.jtfUB3_SPL.setCaretPosition(0);
    this.jtfUB3_OCT_FFT.setCaretPosition(0);
    this.jtfUB4.setCaretPosition(0);
    this.jtfUB5.setCaretPosition(0);
    this.jtfUB6.setCaretPosition(0);
    this.jtfUB7Medida.setCaretPosition(0);
    this.jtfUB7Derivada.setCaretPosition(0);
    this.jtfUB8.setCaretPosition(0);
    this.jtfUB9.setCaretPosition(0);
    */
    ((DefaultTableModel) this.jtModosFunc.getModel()).setRowCount(0);
    
    actualizarSector();
    
    this.jbIncertidumbres.setEnabled(false);
    
    this.jbPrevisualizar.setEnabled(false);
}

private void actualizaBotones() {
    Integer idAsunto = (Integer) ComboBoxObject.getClaveSelCombo(this.jcbAsunto);
    Integer idNorma = (Integer) ComboBoxObject.getClaveSelCombo(this.jcbNorma);
    
    Boolean activar = idAsunto != null && idNorma != null;
    
    this.jbIncertidumbres.setEnabled(activar);
    this.jbPrevisualizar.setEnabled(activar);
}
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgMedidaPot;
    private javax.swing.ButtonGroup bgOpVel;
    private javax.swing.ButtonGroup bgOpVelDerTipo;
    private javax.swing.ButtonGroup bgOpVelK;
    private javax.swing.ButtonGroup bgOpVelKRF;
    private javax.swing.ButtonGroup bgPer;
    private javax.swing.ButtonGroup bgResAltura;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel jTitTipoAnalisis;
    private javax.swing.JButton jbIncertidumbres;
    private javax.swing.JButton jbPrevisualizar;
    public javax.swing.JComboBox jcbAsunto;
    private javax.swing.JCheckBox jcbFFT;
    private javax.swing.JCheckBox jcbMiniAero;
    private javax.swing.JComboBox jcbNorma;
    private javax.swing.JCheckBox jcbOCT;
    private javax.swing.JCheckBox jcbSPL;
    private javax.swing.JLabel jlAsunto;
    private javax.swing.JLabel jlBinEstudio;
    private javax.swing.JLabel jlDirAmp;
    private javax.swing.JLabel jlDirEns;
    private javax.swing.JLabel jlFechaD;
    private javax.swing.JLabel jlFechaH;
    private javax.swing.JLabel jlMedPot;
    private javax.swing.JLabel jlNorma;
    private javax.swing.JLabel jlPerDes;
    private javax.swing.JLabel jlPerHas;
    private javax.swing.JLabel jlPosAero;
    private javax.swing.JLabel jlPosMicro;
    private javax.swing.JLabel jlPotNominal;
    private javax.swing.JLabel jlPotNominalEdit;
    private javax.swing.JLabel jlResAltura;
    private javax.swing.JLabel jlTipoCalculo;
    private javax.swing.JLabel jlTipoK;
    private javax.swing.JLabel jlTipoKRF;
    private javax.swing.JLabel jlTitAlturaRug;
    private javax.swing.JLabel jlTitDireccion;
    private javax.swing.JLabel jlTitIncertidumbre;
    private javax.swing.JLabel jlTitOpciones;
    private javax.swing.JLabel jlTitPerMed;
    private javax.swing.JLabel jlTitPeriodo;
    private javax.swing.JLabel jlTitPosiciones;
    private javax.swing.JLabel jlTitVelocidad;
    private javax.swing.JLabel jlVelDes;
    private javax.swing.JLabel jlVelDesModoFunc;
    private javax.swing.JLabel jlVelSep;
    private javax.swing.JLabel jlVelSepModoFunc;
    private javax.swing.JLabel jlZ0;
    private javax.swing.JLabel jlZ0ref;
    private javax.swing.JLabel jlZref;
    private javax.swing.JLabel jlmZ0ref;
    private javax.swing.JLabel jlmZred;
    private javax.swing.JPanel jpAlturaRug;
    private javax.swing.JPanel jpClave;
    private javax.swing.JPanel jpDireccion;
    private javax.swing.JPanel jpGeneral;
    private javax.swing.JPanel jpIncertidumbre;
    private javax.swing.JPanel jpNuevoModoFunc;
    private javax.swing.JPanel jpOpciones;
    private javax.swing.JPanel jpPeriodo;
    private javax.swing.JPanel jpTipoAnalisis;
    private javax.swing.JPanel jpTipoCalculoOCTModoFunc;
    private javax.swing.JPanel jpVelocidad;
    private javax.swing.JRadioButton jrbKNac;
    private javax.swing.JRadioButton jrbKRFNac;
    private javax.swing.JRadioButton jrbKRFTorre;
    private javax.swing.JRadioButton jrbKTorre;
    private javax.swing.JRadioButton jrbPendiente;
    private javax.swing.JRadioButton jrbPerMed;
    private javax.swing.JRadioButton jrbPerOtr;
    private javax.swing.JRadioButton jrbPotencia;
    private javax.swing.JRadioButton jrbPromedio;
    private javax.swing.JRadioButton jrbResAlturaBuje;
    private javax.swing.JRadioButton jrbResAlturaZref;
    private javax.swing.JRadioButton jrbTensionInt;
    private javax.swing.JRadioButton jrbVD;
    private javax.swing.JRadioButton jrbVZ;
    public general.JSpinnerDate jsPerDes;
    public general.JSpinnerDate jsPerHas;
    private general.JTableExport jtModosFunc;
    private javax.swing.JTextField jtfBinEstudio;
    private javax.swing.JTextField jtfDirAmp;
    private javax.swing.JTextField jtfDirEns;
    private javax.swing.JTextField jtfDirMax;
    private javax.swing.JTextField jtfDirMin;
    public javax.swing.JTextField jtfFechaD;
    public javax.swing.JTextField jtfFechaH;
    public javax.swing.JTextField jtfPosAero;
    public javax.swing.JTextField jtfPosMicro;
    private javax.swing.JTextField jtfPotNominal;
    private javax.swing.JTextField jtfVelDes;
    private javax.swing.JTextField jtfVelDesModoFunc;
    private javax.swing.JTextField jtfVelHas;
    private javax.swing.JTextField jtfVelHasModoFunc;
    private javax.swing.JTextField jtfZ0;
    private javax.swing.JTextField jtfZ0ref;
    private javax.swing.JTextField jtfZref;
    // End of variables declaration//GEN-END:variables
}