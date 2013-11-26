package SPL_GUI;

import RA.Aerogenerador;
import RA.Asunto;
import RA.Curva;
import RA.Direccion;
import RA.Login;
import RA.Norma;
import java.util.ArrayList;
import RA.ConfiguracionRA;
import RA.DatosRA;
import general.InteraccionBD;
import java.awt.Component;
import java.awt.Cursor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.*;

public class ResultadosSPLGUI1 extends JDialog {

    public ResultadosSPLGUI1(java.awt.Frame parent, int id) {
        super(parent, true);
        this.Id = id;
        initComponents();
    }
    
    //Valores por defecto
    private static final String AMPLITUD = ((Double)Double.parseDouble("15")).toString();
    private static final String Z_REF = "10";
    private static final String Z0_REF = "0.05";
    private static final String DESDE_VELOCIDAD = "6";
    private static final String HASTA_VELOCIDAD = "10";
    private static final String TABLA_DATOS = "DatosSPL";
    private static final String TABLA_DESC = "DescripcionSPL";

    static {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("No puedo cargar el driver de la BD");
        }
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgOpVelDer = new javax.swing.ButtonGroup();
        bgOpVel = new javax.swing.ButtonGroup();
        bgPer = new javax.swing.ButtonGroup();
        jPanel13 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jrbPerMed = new javax.swing.JRadioButton();
        jrbPerOtr = new javax.swing.JRadioButton();
        jLabel38 = new javax.swing.JLabel();
        jtfPerDes = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        jtfPerHas = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jcbAsunto = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jtfFechaD = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        jtfFechaH = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jtfNorma = new javax.swing.JTextField();
        jbCerrar = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel9 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jbNext = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jtfZ0ref = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jtfZ0 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jtfZref = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jtfDirEns = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jtfDirAmp = new javax.swing.JTextField();
        jtfDirMin = new javax.swing.JTextField();
        jtfDirMax = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jrbKFact = new javax.swing.JRadioButton();
        jrbRecta = new javax.swing.JRadioButton();
        jrbVD = new javax.swing.JRadioButton();
        jrbVZ = new javax.swing.JRadioButton();
        jrbKNac = new javax.swing.JRadioButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jtfVelDes = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jtfVelHas = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SEGUIMIENTO / SPL- 01");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setIconImage(new ImageIcon("\\\\Bsolar\\DatosSolar\\Curva\\Imagenes\\GCPMini.jpg").getImage());
        setLocationByPlatform(true);
        setName("Cliente"); // NOI18N
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                Cerrar(evt);
            }
        });

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("PERIODO");
        jLabel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

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

        jLabel38.setText("De:");

        jtfPerDes.setEditable(false);
        jtfPerDes.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtfPerDes.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                ValidarFecha1(evt);
            }
        });

        jLabel41.setText("A:");

        jtfPerHas.setEditable(false);
        jtfPerHas.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtfPerHas.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                ValidarFecha2(evt);
            }
        });

        jLabel14.setText("__________________________________");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jrbPerMed)
                            .addComponent(jrbPerOtr)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGap(48, 48, 48)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel38)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jtfPerDes, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel41)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jtfPerHas, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGap(61, 61, 61)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(229, 229, 229)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(4, 4, 4)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jrbPerMed)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jrbPerOtr)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(jtfPerDes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(jtfPerHas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder()));

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel31.setText("Asunto:");

        jcbAsunto.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));

        try {
            // Responsable=Id, Tipo=3, Activo=true
            ArrayList <String> asuntos = new ArrayList <String>();
            int rol = Login.getRol();
            if (rol == 1) { // admin
                asuntos = asunto.getAsuntos(0, 8, true);
            } else {
                asuntos = asunto.getAsuntos(Id, 8, true);
            }
            int n = asuntos.size();
            for(int i = 0; i < n; i++){
                String dato = asuntos.get(i);
                this.jcbAsunto.insertItemAt(dato, i+1);
            }
            this.jcbAsunto.setSelectedIndex(0);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Fallo al consultar la base de datos", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        jcbAsunto.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CambioAsunto(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel3.setText("Periodo Medido:");

        jLabel35.setText("Fecha de Inicio:");

        jtfFechaD.setBackground(new java.awt.Color(204, 204, 204));
        jtfFechaD.setEditable(false);
        jtfFechaD.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel39.setText("Fecha de Fin:");

        jtfFechaH.setBackground(new java.awt.Color(204, 204, 204));
        jtfFechaH.setEditable(false);
        jtfFechaH.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel32.setText("Norma de Aplicación:");

        jtfNorma.setBackground(new java.awt.Color(204, 204, 204));
        jtfNorma.setEditable(false);
        jtfNorma.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel31)
                    .addComponent(jLabel32)
                    .addComponent(jtfNorma, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(50, 50, 50)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel35, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtfFechaD, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel39)
                            .addComponent(jtfFechaH, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jcbAsunto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbAsunto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel39)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtfFechaH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel35)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtfFechaD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel32)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jtfNorma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jbCerrar.setBackground(new java.awt.Color(255, 255, 255));
        jbCerrar.setText("CERRAR");
        jbCerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Cancelar(evt);
            }
        });

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jProgressBar1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12));
        jLabel9.setText("Progreso del cálculo »");
        jLabel9.setFocusable(false);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 725, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 554, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jButton1.setBackground(new java.awt.Color(102, 102, 102));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("CALCULAR");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Calcular(evt);
            }
        });

        jbNext.setBackground(new java.awt.Color(255, 255, 255));
        jbNext.setText(">>");
        jbNext.setEnabled(false);
        jbNext.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Next(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setText("Zref =");

        jLabel4.setText("m");

        jLabel16.setText("______________________________________");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("ALTURA / RUGOSIDAD");
        jLabel5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jLabel26.setText("Z0_ref");

        jtfZ0ref.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtfZ0ref.setText(Z0_REF);

        jLabel23.setText("Z0");

        jtfZ0.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel6.setText("m");

        jtfZref.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtfZref.setText(Z_REF);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtfZref, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel26)
                                    .addComponent(jLabel23))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtfZ0, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jtfZ0ref, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel6))))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel16)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16)
                .addGap(23, 23, 23)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jtfZref, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(jtfZ0ref, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(8, 8, 8)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfZ0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel10.setText("Dirección Ensayo");

        jtfDirEns.setBackground(new java.awt.Color(204, 204, 204));
        jtfDirEns.setEditable(false);
        jtfDirEns.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtfDirEns.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                setDirEnsayo(evt);
            }
        });

        jLabel11.setText("Amplitud");

        jtfDirAmp.setBackground(new java.awt.Color(204, 204, 204));
        jtfDirAmp.setEditable(false);
        jtfDirAmp.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtfDirAmp.setText(AMPLITUD);
        jtfDirAmp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                setAmplitud(evt);
            }
        });

        jtfDirMin.setBackground(new java.awt.Color(204, 204, 204));
        jtfDirMin.setEditable(false);
        jtfDirMin.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jtfDirMax.setBackground(new java.awt.Color(204, 204, 204));
        jtfDirMax.setEditable(false);
        jtfDirMax.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("DIRECCIÓN");
        jLabel8.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jLabel17.setText("___________________________________");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jtfDirMin, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtfDirMax, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel10))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jtfDirAmp, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfDirEns, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel17)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17)
                .addGap(23, 23, 23)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jtfDirEns, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jtfDirAmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfDirMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfDirMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("OPCIONES");
        jLabel12.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jLabel15.setText("__________________________________");

        jrbKFact.setBackground(new java.awt.Color(204, 204, 204));
        bgOpVelDer.add(jrbKFact);
        jrbKFact.setSelected(true);
        jrbKFact.setText("k - factor");

        jrbRecta.setBackground(new java.awt.Color(204, 204, 204));
        bgOpVelDer.add(jrbRecta);
        jrbRecta.setText("recta - nacelle");

        jrbVD.setBackground(new java.awt.Color(204, 204, 204));
        bgOpVel.add(jrbVD);
        jrbVD.setSelected(true);
        jrbVD.setText("VD =  Velocidad derivada de CP");
        jrbVD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cambioOpVel(evt);
            }
        });

        jrbVZ.setBackground(new java.awt.Color(204, 204, 204));
        bgOpVel.add(jrbVZ);
        jrbVZ.setText("VZ =  Velocidad medida en Z");
        jrbVZ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cambioOpVel(evt);
            }
        });

        jrbKNac.setBackground(new java.awt.Color(204, 204, 204));
        bgOpVelDer.add(jrbKNac);
        jrbKNac.setText("k - nacelle");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jrbRecta, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jrbKNac, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 69, Short.MAX_VALUE)
                            .addComponent(jrbKFact, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jrbVZ, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jrbVD, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addGap(18, 18, 18)
                .addComponent(jrbVD)
                .addGap(18, 18, 18)
                .addComponent(jrbKFact)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jrbKNac)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jrbRecta)
                .addGap(19, 19, 19)
                .addComponent(jrbVZ)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel18.setText("__________________________________");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("VELOCIDAD");
        jLabel13.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jtfVelDes.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtfVelDes.setText(DESDE_VELOCIDAD);

        jLabel7.setText("Rango de velocidad");

        jtfVelHas.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtfVelHas.setText(HASTA_VELOCIDAD);

        jLabel19.setText("-");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jtfVelDes, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtfVelHas, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel7)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfVelDes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(jtfVelHas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(126, Short.MAX_VALUE))
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 246, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 237, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 752, Short.MAX_VALUE)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel13Layout.createSequentialGroup()
                            .addComponent(jbCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jbNext, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel13Layout.createSequentialGroup()
                            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jPanel2, 0, 238, Short.MAX_VALUE)
                                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbCerrar)
                    .addComponent(jbNext))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-780)/2, (screenSize.height-773)/2, 780, 773);
    }// </editor-fold>//GEN-END:initComponents

private void Cerrar(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_Cerrar
    dispose();
}//GEN-LAST:event_Cerrar

private void Calcular(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Calcular
    if (this.jcbAsunto.getSelectedItem().toString().length() != 0) {
        boolean ok = true;
        double[] sector = new double[2];
        Connection con = null;
        try {
            numAsunto = asunto.getId(this.jcbAsunto.getSelectedItem().toString());

            // Periodo      
            if ((this.jrbPerMed.isSelected()) && (this.jtfFechaD.getText().length() != 0) && (this.jtfFechaH.getText().length() != 0)) {
                inicio = D.getId(numAsunto, this.jtfFechaD.getText(), TABLA_DATOS);
                fin = D.getId(numAsunto, this.jtfFechaH.getText(), TABLA_DATOS);
            } else {
                if ((this.jrbPerOtr.isSelected()) && (this.jtfPerDes.getText().length() != 0) && (this.jtfPerHas.getText().length() != 0)) {
                    inicio = D.getId(numAsunto, this.jtfPerDes.getText(), TABLA_DATOS);
                    fin = D.getId(numAsunto, this.jtfPerHas.getText(), TABLA_DATOS);
                } else {
                    ok = false;
                }
            }
            if ((this.jtfZ0.getText().length() != 0) && (this.jtfZ0ref.getText().length() != 0) && (this.jtfZref.getText().length() != 0)) {
                Zref = Double.parseDouble(this.jtfZref.getText());
                z0ref = Double.parseDouble(this.jtfZ0ref.getText());
                z0 = Double.parseDouble(this.jtfZ0.getText());
            } else {
                ok = false;
            }
            VD = false;
            if (this.jrbVD.isSelected()) {
                VD = true;
                k_factor = true;
                if (this.jrbRecta.isSelected()) {
                    k_factor = false;
                }
            }
            if ((this.jtfDirMin.getText().length() != 0) && (this.jtfDirMax.getText().length() != 0)) {
                sector[0] = Double.parseDouble(this.jtfDirMin.getText());
                sector[1] = Double.parseDouble(this.jtfDirMax.getText());
            } else {
                ok = false;
            }
            if ((this.jtfVelDes.getText().length() != 0) && (this.jtfVelHas.getText().length() != 0)) {
                rango_v[0] = Double.parseDouble(this.jtfVelDes.getText()) - 0.5;
                rango_v[1] = Double.parseDouble(this.jtfVelHas.getText()) + 0.5;
            } else {
                ok = false;
            }
            if (ok) {
                Component root = SwingUtilities.getRoot(this);
                root.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

                long t0 = System.currentTimeMillis();

                // AG
                aero = asunto.getAero(numAsunto);

                double hb = AG.getHb(aero);
                double Pn = AG.getPn(aero);
                int lineas = AG.getLineas(aero);
                int regulacion = AG.getRegulacion(aero);
                double Vin = AG.getVin(aero);
                double Vcut = AG.getVcut(aero);
                double Dn = AG.getDn(aero);
                ArrayList<double[]> curva = CP.getCurva_CP(numAsunto, 1.225);

                ArrayList<double[]> regresion_cp = D.getRectas(curva);
                double potencia_limite_inf = 0.05 * Pn;
                double potencia_limite_sup = 0.95 * Pn;
                R0 = hb + Dn / 2.0;
                R1 = java.lang.Math.sqrt(java.lang.Math.pow(R0, 2) + java.lang.Math.pow(hb, 2));

                // Cota Z
                ArrayList<double[]> cotas = C.getCotas(numAsunto, 101, 1);

                // Conexion
                con = DriverManager.getConnection(InteraccionBD.URL, InteraccionBD.USER, InteraccionBD.PASS);

                // Creacion de base neta
                this.jProgressBar1.setMinimum(0);
                this.jProgressBar1.setMaximum(100);
                this.jProgressBar1.setValue(0);
                this.jProgressBar1.setStringPainted(true);

                // Metodo 1 (velocidad derivada de CP)
                if (VD) {
                    // Se calcula la base para calcular la k
                    D.getBaseAux(con, TABLA_DATOS, numAsunto, codigos, inicio, fin, Vin, Vcut, sector, Zref, z0ref, z0, hb, lineas, regulacion, cotas, regresion_cp);
                    if (k_factor) {
                        k = D.getK(con, numAsunto, potencia_limite_inf, potencia_limite_sup);
                    } else {
                        ArrayList<double[]> datos_nacelle = D.getNacelle(con, numAsunto, potencia_limite_inf, potencia_limite_sup);
                        nacelle = D.getRegresion(datos_nacelle);
                    }

                    this.jLabel9.setText("Generando la base de datos neta ....");
                    update(this.getGraphics());

                    D.getBaseNeta(con, numAsunto, codigos, potencia_limite_sup, k, nacelle, k_factor);

                    columnas = D.getColumnas("BaseNeta" + numAsunto);

                    this.jProgressBar1.setValue(25);
                    this.jProgressBar1.setStringPainted(true);

                    datos = D.getDatosBaseNeta(con, numAsunto, columnas, rango_v);

                    this.jLabel9.setText("Calculando completitud por bin ....");
                    update(this.getGraphics());

                    resultados_AE = D.getResultadosSPL(con, numAsunto, 0, rango_v);
                    resultados_RF = D.getResultadosSPL(con, numAsunto, 1, rango_v);

                    total[0] = D.getTotal(resultados_AE);
                    total[1] = D.getTotal(resultados_RF);

                    this.jProgressBar1.setValue(50);
                    this.jProgressBar1.setStringPainted(true);

                    this.jLabel9.setText("Calculando regresión lineal por bin ....");
                    update(this.getGraphics());

                    datos_bin_AE = D.getDatosBinSPL(con, numAsunto, 0, rango_v);
                    regresion_AG = D.getRegresionSPL(datos_bin_AE);
                    datos_bin_RF = D.getDatosBinSPL(con, numAsunto, 1, rango_v);
                    regresion_RF = D.getRegresionSPL(datos_bin_RF);


                } else {
                }

                // Se borran las vistas
                D.BorrarVista("BaseAux" + numAsunto);
                D.BorrarVista("BaseNeta" + numAsunto);
                // Se finaliza
                con.close();
                this.jLabel9.setText(" FIN DEL PROCESO DE CÁLCULO ");
                update(this.getGraphics());
                long t1 = System.currentTimeMillis();
                long tiempoTardadoEnMilisegundos = t1 - t0;
                System.out.println(tiempoTardadoEnMilisegundos);
                this.jbNext.setEnabled(true);
                root.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            } else {
                JOptionPane.showMessageDialog(this, "Faltan datos", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Fallo al consultar la base de datos", "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Fallo en el proceso de cálculo", "ERROR", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                D.BorrarVista("BaseAux" + numAsunto);
                D.BorrarVista("BaseNeta" + numAsunto);
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    } else {
        JOptionPane.showMessageDialog(this, "Faltan datos", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
    }
}//GEN-LAST:event_Calcular

private void CambioAsunto(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CambioAsunto
    Limpiar();
    if (this.jcbAsunto.getSelectedIndex() != 0) {
        try {
            numAsunto = asunto.getId((String) this.jcbAsunto.getSelectedItem());
            codigos = C.getDescripcion(numAsunto, TABLA_DESC);
            norma = asunto.getNorma(numAsunto);
            Double direccion = DR.getDireccion(numAsunto);
            
            //Si no tenemos una dirección, no podemos mostrarla ni calcular el sector.
            if (direccion != null)
                this.jtfDirEns.setText(direccion.toString());
            
            //Actualizamos el sector
            actualizaSector();

            this.jtfNorma.setText(NR.getNombre(norma));
            String Fini = D.getFprimera(numAsunto, TABLA_DATOS);
            String Ffin = D.getFultima(numAsunto, TABLA_DATOS);

            this.jtfFechaD.setText(Fini);
            this.jtfFechaH.setText(Ffin);

        } catch (SQLException e) {
            JLabel descError = new JLabel("<html>Fallo al consultar la base de datos<br>" + "<i><b>Error: </b>" + e.toString() + "</i></html>");
            JOptionPane.showMessageDialog(this, descError, "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JLabel descError = new JLabel("<html>Fallo realizando operación<br>" + "<i><b>Error: </b>" + e.toString() + "</i></html>");
            JOptionPane.showMessageDialog(this, descError, "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
}//GEN-LAST:event_CambioAsunto

private void ValidarFecha2(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ValidarFecha2
//Fecha F=new Fecha();
//   if (!F.isFecha(this.jTextField14.getText())){
//        this.jTextField14.setText(null);
//   } else {
//   if ((this.jTextField14.getText()!=null) && (!this.jTextField14.getText().equals(""))){
//        if (F.ComparaFechas(this.jTextField14.getText(), this.jTextField10.getText())>0){
//            JOptionPane.showMessageDialog(this, "El periodo de seguimiento debe pertenecer al periodo revisado","ADVERTENCIA",JOptionPane.WARNING_MESSAGE);
//            this.jTextField14.setText(null);
//        }
//   }
//   }
}//GEN-LAST:event_ValidarFecha2

private void ValidarFecha1(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ValidarFecha1
//Fecha F=new Fecha();
//   if (!F.isFecha(this.jTextField13.getText())){
//        this.jTextField13.setText(null);
//   } else {
//    if ((this.jTextField13.getText()!=null) && (!this.jTextField13.getText().equals(""))){
//      
//       if (F.ComparaFechas(this.jTextField13.getText(), this.jTextField9.getText())<0){
//            JOptionPane.showMessageDialog(this, "El periodo de seguimiento debe pertenecer al periodo revisado","ADVERTENCIA",JOptionPane.WARNING_MESSAGE);
//            this.jTextField13.setText(null);
//        }
//    }
//   }
}//GEN-LAST:event_ValidarFecha1

private void cambioPer(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cambioPer
    if (this.jrbPerOtr.isSelected()) {
        this.jtfPerHas.setEditable(true);
        this.jtfPerDes.setEditable(true);
    } else {
        this.jtfPerHas.setText("");
        this.jtfPerHas.setEditable(false);
        this.jtfPerDes.setText("");
        this.jtfPerDes.setEditable(false);
    }
}//GEN-LAST:event_cambioPer

private void Cancelar(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Cancelar
    dispose();
}//GEN-LAST:event_Cancelar

private void actualizaSector()
{
    if (    this.jtfDirEns.getText().length() != 0 &&
            this.jtfDirAmp.getText().length() != 0
            ) {
        double direccion = Double.parseDouble(this.jtfDirEns.getText());
        double amplitud = Double.parseDouble(this.jtfDirAmp.getText());
        double[] sector = new double[2];
        
        sector = DR.getSector(direccion, amplitud);
        
        this.jtfDirMin.setText(((Double)sector[0]).toString());
        this.jtfDirMax.setText(((Double)sector[1]).toString());
    } else {
        this.jtfDirMin.setText("");
        this.jtfDirMax.setText("");
    }
}

private void Next(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Next
    if (this.jbNext.isEnabled()) {
        this.setVisible(false);
        new ResultadosSPLGUI2(this).setVisible(true);
    }
}//GEN-LAST:event_Next

private void setAmplitud(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_setAmplitud
    JTextField etiqueta = new JTextField();
    JOptionPane.showMessageDialog(this, etiqueta, "Establecer Amplitud", JOptionPane.QUESTION_MESSAGE);
    
    if (etiqueta.getText().length() != 0) {
        try {
            double amplitud = Double.parseDouble(etiqueta.getText());
            this.jtfDirAmp.setText(((Double)amplitud).toString());
        } catch (NumberFormatException e) {
            JLabel descError = new JLabel("<html>Fallo convirtiendo número<br>" + "<i><b>Error: </b>" + e.toString() + "</i></html>");
            JOptionPane.showMessageDialog(this, descError, "ERROR", JOptionPane.ERROR_MESSAGE); 
            return;
        }
    } else
        this.jtfDirAmp.setText("");
        
    //Actualizamos el sector
    actualizaSector();
}//GEN-LAST:event_setAmplitud

private void cambioOpVel(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cambioOpVel
    jrbKFact.setEnabled(jrbVD.isSelected());
    jrbKNac.setEnabled(jrbVD.isSelected());
    jrbRecta.setEnabled(jrbVD.isSelected());
}//GEN-LAST:event_cambioOpVel

private void setDirEnsayo(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_setDirEnsayo
    JTextField etiqueta = new JTextField();
    JOptionPane.showMessageDialog(this, etiqueta, "Establecer Dirección", JOptionPane.QUESTION_MESSAGE);
    if (etiqueta.getText().length() != 0) {
        try {
            double direccion = Double.parseDouble(etiqueta.getText());
            this.jtfDirEns.setText(((Double)direccion).toString());
        } catch (NumberFormatException e) {
            JLabel descError = new JLabel("<html>Fallo convirtiendo número<br>" + "<i><b>Error: </b>" + e.toString() + "</i></html>");
            JOptionPane.showMessageDialog(this, descError, "ERROR", JOptionPane.ERROR_MESSAGE); 
            return;
        }
    } else
        this.jtfDirEns.setText("");
        
    //Actualizamos el sector
    actualizaSector();
}//GEN-LAST:event_setDirEnsayo

private void Limpiar(){
    // Norma
    this.jtfNorma.setText(null);
    
    // Periodo
    this.jtfFechaD.setText(null);
    this.jtfFechaH.setText(null);
    this.jtfPerHas.setEditable(false);
    this.jtfPerHas.setText("");
    this.jtfPerDes.setEditable(false);
    this.jtfPerDes.setText("");
    this.jrbPerMed.setSelected(true);
    this.jrbPerOtr.setSelected(false);
    
    // Dirección
    this.jtfDirMin.setText(null);
    this.jtfDirMax.setText(null);
    this.jtfDirEns.setText(null);
    this.jtfDirAmp.setText(AMPLITUD);
    
    // Rugosidad
    this.jtfZref.setText(Z_REF);
    this.jtfZ0ref.setText(Z0_REF);
    this.jtfZ0.setText(null);
    
    // Velocidad
    this.jtfVelDes.setText(DESDE_VELOCIDAD);
    this.jtfVelHas.setText(HASTA_VELOCIDAD);
    
    // Opciones
    this.jrbVD.setSelected(true);
    this.jrbVZ.setSelected(false);
    this.jrbKFact.setSelected(true);
    this.jrbRecta.setSelected(false);
    
    // Next
    this.jbNext.setEnabled(false);
}
   
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgOpVel;
    private javax.swing.ButtonGroup bgOpVelDer;
    private javax.swing.ButtonGroup bgPer;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JButton jbCerrar;
    private javax.swing.JButton jbNext;
    public javax.swing.JComboBox jcbAsunto;
    private javax.swing.JRadioButton jrbKFact;
    private javax.swing.JRadioButton jrbKNac;
    private javax.swing.JRadioButton jrbPerMed;
    private javax.swing.JRadioButton jrbPerOtr;
    private javax.swing.JRadioButton jrbRecta;
    private javax.swing.JRadioButton jrbVD;
    private javax.swing.JRadioButton jrbVZ;
    private javax.swing.JTextField jtfDirAmp;
    private javax.swing.JTextField jtfDirEns;
    private javax.swing.JTextField jtfDirMax;
    private javax.swing.JTextField jtfDirMin;
    public javax.swing.JTextField jtfFechaD;
    public javax.swing.JTextField jtfFechaH;
    private javax.swing.JTextField jtfNorma;
    public javax.swing.JTextField jtfPerDes;
    public javax.swing.JTextField jtfPerHas;
    private javax.swing.JTextField jtfVelDes;
    private javax.swing.JTextField jtfVelHas;
    private javax.swing.JTextField jtfZ0;
    private javax.swing.JTextField jtfZ0ref;
    private javax.swing.JTextField jtfZref;
    // End of variables declaration//GEN-END:variables
    
    // Objetos
    private Asunto asunto = new Asunto();
    public DatosRA D = new DatosRA();
    private Norma NR = new Norma();
    private ConfiguracionRA C = new ConfiguracionRA();
    private Aerogenerador AG = new Aerogenerador();
    private Direccion DR = new Direccion();
    private Curva CP = new Curva();
    
    // Variables IN
    private int Id;
    private int numAsunto;
    private int aero;
    private int inicio;
    private int fin;
    private int norma;
    private boolean k_factor;
    private boolean VD;
    private boolean VZ;
    private double Zref;
    private double z0ref;
    private double z0;
    private double[] rango_v = new double[2];
    private double k;
    private double[] nacelle;
    private double R0;
    public double R1;
    
    private ArrayList<String> codigos = new ArrayList<String>();
    public ArrayList<String> columnas = new ArrayList<String>();
    
    // Variables OUT 
    public ArrayList<String[]> datos = new ArrayList<String[]>();
    public ArrayList<ArrayList<double[]>> datos_bin_AE = new ArrayList<ArrayList<double[]>>();
    public ArrayList<ArrayList<double[]>> datos_bin_RF = new ArrayList<ArrayList<double[]>>();
   
    public ArrayList<double[]> resultados_AE = new ArrayList<double[]>();
    public ArrayList<double[]> resultados_RF = new ArrayList<double[]>();  
    
    public int[] total = new int[2];
    
    public ArrayList<double[]> regresion_AG = new ArrayList<double[]>();  
    public ArrayList<double[]> regresion_RF = new ArrayList<double[]>();  
}