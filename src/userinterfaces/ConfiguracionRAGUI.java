package userinterfaces;

import RA.AsuntoRA;
import RA.ConfiguracionRA2;
import RA.Declinacion;
import RA.LineaConfiguracion;
import RA.SerieRA2;
import RA.TipoRA;
import RA.Variable;
import general.Auxiliares;
import general.ComboBoxObject;
import general.IVExtendido;
import general.MensajeApp;
import general.TratFechas;
import java.awt.Toolkit;
import java.awt.print.PrinterException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author  Ruth Cordon
 */

public class ConfiguracionRAGUI extends JDialog {

    private final int CANALES_MAX = 40;

    private final String COL_SERIE = "Serie";
    private final String COL_EQUIPO = "Equipo";
    private final String COL_CANAL = "Canal";
    private final String COL_COTA = "Cota [m]";
    private final String COL_CERTIFICADO = "Certificado";
    private final String COL_SLOPE = "Slope";
    private final String COL_OFFSET = "Offset";
    private final String COL_SLOPE_P = "Slope [P]";
    private final String COL_OFFSET_P = "Offset [P]";
    private final String COL_ORIENTACION = "O [º]";


    public ConfiguracionRAGUI(java.awt.Frame parent) {
        super(parent, true);
        initComponents();
        this.setLocationRelativeTo(parent);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgDeclinacion = new javax.swing.ButtonGroup();
        jpPrincipal = new javax.swing.JPanel();
        jpCabecera = new javax.swing.JPanel();
        jlAsunto = new javax.swing.JLabel();
        jcbAsunto = new javax.swing.JComboBox();
        jlSite = new javax.swing.JLabel();
        jcbSite = new javax.swing.JComboBox();
        jpDatos = new javax.swing.JPanel();
        jspLineasConf = new javax.swing.JScrollPane();
        jtLineasConf = new javax.swing.JTable();
        jlSerie = new javax.swing.JLabel();
        jcbSerie = new javax.swing.JComboBox();
        jlCanal = new javax.swing.JLabel();
        jcbCanal = new javax.swing.JComboBox();
        jsSep = new javax.swing.JSeparator();
        jspHistorico = new javax.swing.JScrollPane();
        jtHistorico = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jTitConfiguraciones = new javax.swing.JLabel();
        jlFechaIniMed = new javax.swing.JLabel();
        jsFechaIniMed = new general.JSpinnerDate();
        jlTitHistorico = new javax.swing.JLabel();
        jlTitNueva = new javax.swing.JLabel();
        jlFechaIniNueva = new javax.swing.JLabel();
        jlDeclinacion = new javax.swing.JLabel();
        jlTitLineas = new javax.swing.JLabel();
        jbAnadir = new javax.swing.JButton();
        jbEliminar = new javax.swing.JButton();
        jlSepHistorico = new javax.swing.JLabel();
        jlSepNueva = new javax.swing.JLabel();
        jbImprimirTabla = new javax.swing.JButton();
        jtfDeclinacion = new javax.swing.JTextField();
        jrbDecEste = new javax.swing.JRadioButton();
        jrbDecOeste = new javax.swing.JRadioButton();
        jlGrados = new javax.swing.JLabel();
        jsFechaIniNueva = new general.JSpinnerDate();
        jbCargarLineas = new javax.swing.JButton();
        jbActualizar = new javax.swing.JButton();
        jbCancelar = new javax.swing.JButton();
        jbGuardar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("NUEVA CONFIGURACIÓN / ENSAYO de RUIDO / GENERAL");
        setLocationByPlatform(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jpPrincipal.setBackground(new java.awt.Color(255, 255, 255));

        jpCabecera.setBackground(new java.awt.Color(255, 255, 255));
        jpCabecera.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder()));

        jlAsunto.setFont(new java.awt.Font("Tahoma", 1, 12));
        jlAsunto.setText("Asunto:");

        jcbAsunto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cambioAsunto(evt);
            }
        });

        jlSite.setFont(new java.awt.Font("Tahoma", 1, 12));
        jlSite.setText("Site:");

        jcbSite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cambioSite(evt);
            }
        });

        javax.swing.GroupLayout jpCabeceraLayout = new javax.swing.GroupLayout(jpCabecera);
        jpCabecera.setLayout(jpCabeceraLayout);
        jpCabeceraLayout.setHorizontalGroup(
            jpCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpCabeceraLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlAsunto)
                .addGap(18, 18, 18)
                .addComponent(jcbAsunto, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69)
                .addComponent(jlSite)
                .addGap(18, 18, 18)
                .addComponent(jcbSite, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
        );
        jpCabeceraLayout.setVerticalGroup(
            jpCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpCabeceraLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlAsunto)
                    .addComponent(jlSite)
                    .addComponent(jcbAsunto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbSite))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jpDatos.setBackground(new java.awt.Color(255, 255, 255));
        jpDatos.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jtLineasConf.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jtLineasConf.getTableHeader().setReorderingAllowed(false);
        jspLineasConf.setViewportView(jtLineasConf);

        jlSerie.setFont(new java.awt.Font("Tahoma", 1, 11));
        jlSerie.setText("Serie:");

        jcbSerie.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));
        jcbSerie.setEnabled(false);

        jlCanal.setFont(new java.awt.Font("Tahoma", 1, 11));
        jlCanal.setText("Canal:");

        jcbCanal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));
        jcbCanal.setEnabled(false);

        jtHistorico.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jtHistorico.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nº", "Fecha Inicio"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtHistorico.setColumnSelectionAllowed(true);
        jtHistorico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cargarLineasConfHistorico(evt);
            }
        });
        jspHistorico.setViewportView(jtHistorico);
        jtHistorico.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel8.setText("         ");

        jTitConfiguraciones.setFont(new java.awt.Font("Tahoma", 1, 12));
        jTitConfiguraciones.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jTitConfiguraciones.setText("CONFIGURACIONES");
        jTitConfiguraciones.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jlFechaIniMed.setText("  Fecha Inicio Medidas:");

        jsFechaIniMed.setEnabled(false);

        jlTitHistorico.setFont(new java.awt.Font("Tahoma", 1, 12));
        jlTitHistorico.setText("Histórico:");

        jlTitNueva.setFont(new java.awt.Font("Tahoma", 1, 12));
        jlTitNueva.setText("Nueva:");

        jlFechaIniNueva.setText("  Fecha Inicio");

        jlDeclinacion.setText("  Declinación");

        jlTitLineas.setFont(new java.awt.Font("Tahoma", 1, 12));
        jlTitLineas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlTitLineas.setText("LÍNEAS DE CONFIGURACIÓN");
        jlTitLineas.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jbAnadir.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("\\\\Bsolar\\DatosSolar\\Curva\\Imagenes\\Down.gif" )));
        jbAnadir.setToolTipText("Añadir Línea de Configuración");
        jbAnadir.setAlignmentX(0.5F);
        jbAnadir.setEnabled(false);
        jbAnadir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbAnadir.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jbAnadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anadirLineaConf(evt);
            }
        });

        jbEliminar.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("\\\\Bsolar\\DatosSolar\\Curva\\Imagenes\\Up.gif" )));
        jbEliminar.setToolTipText("Eliminar Línea de Configuración");
        jbEliminar.setAlignmentX(0.5F);
        jbEliminar.setEnabled(false);
        jbEliminar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbEliminar.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jbEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarLineaConf(evt);
            }
        });

        jlSepHistorico.setText("__________________________________________________");

        jlSepNueva.setText("__________________________________________________");

        jbImprimirTabla.setBackground(new java.awt.Color(255, 255, 255));
        jbImprimirTabla.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("\\\\Bsolar\\DatosSolar\\Curva\\Imagenes\\printer.png" )));
        jbImprimirTabla.setToolTipText("Imprime la tabla de la configuracion seleccionada");
        jbImprimirTabla.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jbImprimirTabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imprimir(evt);
            }
        });

        jtfDeclinacion.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtfDeclinacion.setText("0");

        bgDeclinacion.add(jrbDecEste);
        jrbDecEste.setSelected(true);
        jrbDecEste.setText("E");

        bgDeclinacion.add(jrbDecOeste);
        jrbDecOeste.setText("W");

        jlGrados.setText("º");

        jbCargarLineas.setText("CARGAR LÍNEAS");
        jbCargarLineas.setToolTipText("Eliminar Línea de Configuración");
        jbCargarLineas.setAlignmentX(0.5F);
        jbCargarLineas.setEnabled(false);
        jbCargarLineas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbCargarLineas.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jbCargarLineas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargarLineasConf(evt);
            }
        });

        jbActualizar.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("\\\\Bsolar\\DatosSolar\\Curva\\Imagenes\\arrow_refresh.png" )));
        jbActualizar.setToolTipText("Actualizar Línea de Configuración");
        jbActualizar.setAlignmentX(0.5F);
        jbActualizar.setEnabled(false);
        jbActualizar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbActualizar.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jbActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actualizar(evt);
            }
        });

        javax.swing.GroupLayout jpDatosLayout = new javax.swing.GroupLayout(jpDatos);
        jpDatos.setLayout(jpDatosLayout);
        jpDatosLayout.setHorizontalGroup(
            jpDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDatosLayout.createSequentialGroup()
                .addGap(209, 209, 209)
                .addComponent(jTitConfiguraciones, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(228, Short.MAX_VALUE))
            .addGroup(jpDatosLayout.createSequentialGroup()
                .addGap(212, 212, 212)
                .addComponent(jlTitLineas, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(225, Short.MAX_VALUE))
            .addGroup(jpDatosLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jpDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpDatosLayout.createSequentialGroup()
                        .addGroup(jpDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpDatosLayout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jlSerie)
                                .addGap(18, 18, 18)
                                .addComponent(jcbSerie, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jlCanal)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jcbCanal, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 235, Short.MAX_VALUE)
                                .addComponent(jLabel8)
                                .addGap(8, 8, 8)
                                .addComponent(jbAnadir, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))
                            .addGroup(jpDatosLayout.createSequentialGroup()
                                .addGroup(jpDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jspHistorico, 0, 0, Short.MAX_VALUE)
                                    .addGroup(jpDatosLayout.createSequentialGroup()
                                        .addComponent(jlFechaIniMed)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                                        .addComponent(jsFechaIniMed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jlSepHistorico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jlTitHistorico))
                                .addGap(120, 120, 120)
                                .addGroup(jpDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlTitNueva)
                                    .addComponent(jlSepNueva)
                                    .addGroup(jpDatosLayout.createSequentialGroup()
                                        .addGroup(jpDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jlFechaIniNueva)
                                            .addComponent(jlDeclinacion))
                                        .addGroup(jpDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jpDatosLayout.createSequentialGroup()
                                                .addGap(104, 104, 104)
                                                .addComponent(jtfDeclinacion, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jlGrados, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jrbDecEste)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jrbDecOeste))
                                            .addGroup(jpDatosLayout.createSequentialGroup()
                                                .addGap(84, 84, 84)
                                                .addComponent(jsFechaIniNueva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addComponent(jbCargarLineas, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE))
                                .addGap(10, 10, 10)))
                        .addGap(142, 142, 142))
                    .addGroup(jpDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jspLineasConf, javax.swing.GroupLayout.PREFERRED_SIZE, 720, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jbImprimirTabla, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(152, 152, 152))
            .addGroup(jpDatosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jsSep, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jpDatosLayout.setVerticalGroup(
            jpDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDatosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTitConfiguraciones)
                .addGap(16, 16, 16)
                .addGroup(jpDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlTitHistorico)
                    .addComponent(jlTitNueva))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlSepHistorico, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlSepNueva, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jpDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpDatosLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlFechaIniMed))
                    .addGroup(jpDatosLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jpDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlFechaIniNueva)
                            .addComponent(jsFechaIniNueva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jpDatosLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jsFechaIniMed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jpDatosLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jpDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlDeclinacion)
                            .addComponent(jtfDeclinacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlGrados)
                            .addComponent(jrbDecEste)
                            .addComponent(jrbDecOeste))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbCargarLineas, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jspHistorico, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jsSep, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpDatosLayout.createSequentialGroup()
                        .addComponent(jlTitLineas)
                        .addGap(19, 19, 19)
                        .addGroup(jpDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jpDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jcbSerie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jlCanal)
                                .addComponent(jlSerie)
                                .addComponent(jcbCanal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)))
                    .addGroup(jpDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jbActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jbEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jbAnadir, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jspLineasConf, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbImprimirTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jbCancelar.setBackground(new java.awt.Color(255, 255, 255));
        jbCancelar.setText("CANCELAR");
        jbCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelar(evt);
            }
        });

        jbGuardar.setBackground(new java.awt.Color(255, 255, 255));
        jbGuardar.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("\\\\Bsolar\\DatosSolar\\Curva\\Imagenes\\Guardar.png" )));
        jbGuardar.setToolTipText("Guardar Configuración");
        jbGuardar.setEnabled(false);
        jbGuardar.setMaximumSize(new java.awt.Dimension(11, 9));
        jbGuardar.setMinimumSize(new java.awt.Dimension(11, 9));
        jbGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarConf(evt);
            }
        });

        javax.swing.GroupLayout jpPrincipalLayout = new javax.swing.GroupLayout(jpPrincipal);
        jpPrincipal.setLayout(jpPrincipalLayout);
        jpPrincipalLayout.setHorizontalGroup(
            jpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jpCabecera, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpPrincipalLayout.createSequentialGroup()
                            .addComponent(jbCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jbGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jpDatos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 774, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jpPrincipalLayout.setVerticalGroup(
            jpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpCabecera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                    .addComponent(jbGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void cargarLineasConfHistorico(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cargarLineasConfHistorico
    if (evt.getClickCount() == 2 && !evt.isConsumed()) {
        evt.consume();

        int fila = this.jtHistorico.rowAtPoint(evt.getPoint());

        if (fila != -1) {
            int numConfig = (Integer) this.jtHistorico.getValueAt(fila, 0);
            cargarLineas(numConfig);
        }
    }
}//GEN-LAST:event_cargarLineasConfHistorico

private void cancelar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelar
    dispose();
}//GEN-LAST:event_cancelar

private void cambioAsunto(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cambioAsunto
    try {
        Integer idAsunto = (Integer) ComboBoxObject.getClaveSelCombo(this.jcbAsunto);

        if (idAsunto != null)
            Auxiliares.cargaTodosSites(this.jcbSite);
        else {
            this.jcbSite.removeAllItems();
            this.jcbSite.addItem(new ComboBoxObject(null, ""));
        }
    } catch (SQLException e) {
        MensajeApp.muestraError(this, e, "Fallo al consultar la base de datos");
    } catch (NoSuchFieldException e) {
        MensajeApp.muestraError(this, e, "Fallo añadiendo campo");
    }
}//GEN-LAST:event_cambioAsunto

private void cambioSite(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cambioSite
    try {
        limpiarCampos();
        
        DefaultTableModel dtmHistorico = (DefaultTableModel) this.jtHistorico.getModel();
        
        Integer idAsunto = (Integer) ComboBoxObject.getClaveSelCombo(this.jcbAsunto);
        Integer idSite = (Integer) ComboBoxObject.getClaveSelCombo(this.jcbSite);

        if (idSite != null) {
            TipoRA tipo = TipoRA.getTipoRAPorIdSite(idSite);
            cargarCodigos(idAsunto, tipo.getIdTipoRA());

            AsuntoRA asunto = AsuntoRA.getAsuntoPorId(idAsunto);

            Long fechaIni = asunto.getFiniPC();
            if (fechaIni > 0) {
                this.jsFechaIniMed.setTimeInMillis(fechaIni);
            } else {
                this.jsFechaIniMed.setText(TratFechas.FECHA_MIN);
            }

            ArrayList<ConfiguracionRA2> configuraciones = ConfiguracionRA2.getConfiguracionesRA(idAsunto, idSite, null, null, null, null, null);
            int nConf = configuraciones != null ? configuraciones.size() : 0;

            ConfiguracionRA2 conf;
            for (int i = 0; i < nConf; i++) {
                conf = configuraciones.get(i);

                dtmHistorico.addRow(new Object[]{conf.getIdConfig(), TratFechas.toStringFecha(conf.getFechaIni())});
            }
            Auxiliares.centrarTabla(this.jtHistorico);

            cargarCanales();
            
            this.jbCargarLineas.setEnabled(true);
        }
    } catch (SQLException e) {
        MensajeApp.muestraError(this, e, "Fallo al consultar la base de datos");
    } catch (NoSuchFieldException e) {
        MensajeApp.muestraError(this, e, "Fallo añadiendo campo");
    }
}//GEN-LAST:event_cambioSite

private void anadirLineaConf(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anadirLineaConf
        Integer idSerie = (Integer) ComboBoxObject.getClaveSelCombo(this.jcbSerie);
        String codigo = null;
        Integer idCanal = (Integer) ComboBoxObject.getClaveSelCombo(this.jcbCanal);
        String canalTxt = null;

        if (idSerie != null || idCanal != null) {
            if (idSerie != null) {
                codigo = (String) ComboBoxObject.getDescSelCombo(this.jcbSerie);
                this.jcbSerie.removeItemAt(this.jcbSerie.getSelectedIndex());
            }
            
            if (idCanal != null) {
                canalTxt = (String) ComboBoxObject.getDescSelCombo(this.jcbCanal);
                this.jcbCanal.removeItemAt(this.jcbCanal.getSelectedIndex());
            }

            DefaultTableModel dtmLineasConf = (DefaultTableModel) this.jtLineasConf.getModel();

            dtmLineasConf.addRow(new Object[]{codigo, null, canalTxt});
            Auxiliares.muestraFila(this.jtLineasConf, dtmLineasConf.getRowCount() - 1);
            Auxiliares.centrarTabla(this.jtLineasConf);
        }
}//GEN-LAST:event_anadirLineaConf

private void eliminarLineaConf(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarLineaConf
    int nFilas = this.jtLineasConf.getSelectedRowCount();//GEN-LAST:event_eliminarLineaConf

    try {
        if (nFilas > 0) {
            DefaultTableModel dtmLineasConf = (DefaultTableModel) this.jtLineasConf.getModel();
            Integer codigo, canal;
            String codigoTxt, canalTxt;
            int[] filas = this.jtLineasConf.getSelectedRows();
            int fila;

            int colSerie = dtmLineasConf.findColumn(COL_SERIE);
            int colCanal = dtmLineasConf.findColumn(COL_CANAL);

            for (int i = 0; i < nFilas; i++) {
                fila = filas[nFilas - 1 - i];

                codigoTxt = (String) this.jtLineasConf.getValueAt(fila, colSerie);
                codigo = SerieRA2.getSerieRA2PorCodigo(codigoTxt).getIdSerie();
                canalTxt = (String) this.jtLineasConf.getValueAt(fila, colCanal);
                canal = Integer.parseInt(canalTxt.replaceAll("[^0-9]+", "").trim());

                anadirItemComboOrdenado(this.jcbSerie, new ComboBoxObject(codigo, codigoTxt));
                anadirItemComboOrdenado(this.jcbCanal, new ComboBoxObject(canal, canalTxt));

                dtmLineasConf.removeRow(fila);
            }
        }
    } catch (SQLException e) {
        MensajeApp.muestraError(this, e, "Fallo al consultar la base de datos");
    } catch (NoSuchFieldException e) {
        MensajeApp.muestraError(this, e, "Fallo añadiendo campo");
    }
}

private void guardarConf(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarConf
    DefaultTableModel dtmLineasConf = (DefaultTableModel) this.jtLineasConf.getModel();

    try {
        if (dtmLineasConf.getRowCount() == 0)
            JOptionPane.showMessageDialog(this, "Por favor, introduzca los campos obligatorios", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
        else {
            Long fechaIniNueva = this.jsFechaIniNueva.getTimeInMillis();

            Integer idAsunto = (Integer) ComboBoxObject.getClaveSelCombo(this.jcbAsunto);
            Integer idSite = (Integer) ComboBoxObject.getClaveSelCombo(this.jcbSite);
            Integer idConfig = ConfiguracionRA2.getUltimaConfiguracion(idAsunto, idSite);
            if (idConfig == null)
                idConfig = 0;
            
            idConfig++;

            Double angulo = Double.parseDouble(this.jtfDeclinacion.getText());
            Character posicion = this.jrbDecOeste.isSelected() ? Declinacion.CHAR_OESTE : Declinacion.CHAR_ESTE;

            Declinacion dec = new Declinacion(idAsunto, idSite, idConfig, angulo, posicion);

            ArrayList<LineaConfiguracion> lineasConf = new ArrayList<LineaConfiguracion>();
            // Relleno las líneas
            int nFilas = dtmLineasConf.getRowCount();

            int colCodigo = dtmLineasConf.findColumn(COL_SERIE);
            int colEquipo = dtmLineasConf.findColumn(COL_EQUIPO);
            int colCanal = dtmLineasConf.findColumn(COL_CANAL);
            int colCota = dtmLineasConf.findColumn(COL_COTA);
            int colCertificado = dtmLineasConf.findColumn(COL_CERTIFICADO);
            int colSlope = dtmLineasConf.findColumn(COL_SLOPE);
            int colOffset = dtmLineasConf.findColumn(COL_OFFSET);
            int colSlopeP = dtmLineasConf.findColumn(COL_SLOPE_P);
            int colOffsetP = dtmLineasConf.findColumn(COL_OFFSET_P);
            int colOrientacion = dtmLineasConf.findColumn(COL_ORIENTACION);

            SerieRA2 serie;
            Integer idSerie;
            Variable variable;
            String codigo, equipo, canal, certificado, nomVariable;
            Double cota, slope, offset, slopeP, offsetP, orientacion;
            String error = "";

            for (int i = 0; i < nFilas; i++) {
                codigo = dtmLineasConf.getValueAt(i, colCodigo) == null? null : (String) dtmLineasConf.getValueAt(i, colCodigo);
                equipo = dtmLineasConf.getValueAt(i, colEquipo) == null? null : (String) dtmLineasConf.getValueAt(i, colEquipo);
                canal = dtmLineasConf.getValueAt(i, colCanal) == null? null : (String) dtmLineasConf.getValueAt(i, colCanal);
                certificado = dtmLineasConf.getValueAt(i, colCertificado) == null? null : (String) dtmLineasConf.getValueAt(i, colCertificado);
                cota = dtmLineasConf.getValueAt(i, colCota) == null? null : (Double) dtmLineasConf.getValueAt(i, colCota);
                slope = dtmLineasConf.getValueAt(i, colSlope) == null? null : (Double) dtmLineasConf.getValueAt(i, colSlope);
                offset = dtmLineasConf.getValueAt(i, colOffset) == null? null : (Double) dtmLineasConf.getValueAt(i, colOffset);
                slopeP = dtmLineasConf.getValueAt(i, colSlopeP) == null? null : (Double) dtmLineasConf.getValueAt(i, colSlopeP);
                offsetP = dtmLineasConf.getValueAt(i, colOffsetP) == null? null : (Double) dtmLineasConf.getValueAt(i, colOffsetP);
                orientacion = dtmLineasConf.getValueAt(i, colOrientacion) == null? null : (Double) dtmLineasConf.getValueAt(i, colOrientacion);

                serie = SerieRA2.getSerieRA2PorCodigo(codigo);
                idSerie = serie.getIdSerie();

                variable = serie.getIdVariable() != null ? Variable.getVariablePorId(serie.getIdVariable()) : null;
                nomVariable = variable != null ? variable.getNomVariable() : null;

                lineasConf.add(new LineaConfiguracion(idAsunto, idSite, idConfig, idSerie, equipo, canal, cota, certificado, slope, offset, slopeP, offsetP, orientacion));

                //Comprobación de datos
                if (equipo == null)
                    error += "Faltan el código de equipo de la serie " + codigo  + "<br>";
                if (cota == null) {
                    if (idSerie.equals("11"))
                        error += "Falta la cota del sensor de presión principal de la serie " + codigo  + "<br>";
                    else if (nomVariable != null && nomVariable.equals("Velocidad"))
                        error += "Falta la cota del anemómetro de la serie " + codigo  + "<br>";
                }
                if (certificado != null && !certificado.isEmpty() && (slope == null || offset == null))
                    error += "Faltan datos del certificado de la serie " + codigo  + "<br>";
                if (slopeP == null || offsetP == null)
                    error += "Faltan datos en la programación de la serie " + codigo + "<br>";

                if (idSerie.equals("2") && orientacion == null)
                    error += "Faltan la orientación del anemómetro de la serie " + codigo + "<br>";
            }

            //Todo se ha rellenado correctamente
            if (error.isEmpty()) {
                ConfiguracionRA2 configuracion = new ConfiguracionRA2(idAsunto, idSite, idConfig, fechaIniNueva);
                ConfiguracionRA2.insertConfiguracionRA(configuracion, null);

                int nLineas = lineasConf.size();

                for (int i = 0; i < nLineas; i++) {
                    LineaConfiguracion.insertLineaConf(lineasConf.get(i), null);
                }

                Declinacion.insertDeclinacion(dec, null);

                MensajeApp.muestraInfo(this, "Configuración guardada correctamente");
            } else {
                MensajeApp.muestraWarning(this, error);
            }
        }
    } catch (SQLException e) {
        MensajeApp.muestraError(this, e, "Fallo al consultar la base de datos");
    } catch (NoSuchFieldException e) {
        MensajeApp.muestraError(this, e, "Fallo añadiendo campo");
    }
}//GEN-LAST:event_guardarConf

private void cargarLineasConf(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargarLineasConf
    try {
        if (this.jsFechaIniNueva.getTimeInMillis() != 0) {
            // Inicializo el combo de series
            this.jcbSerie.removeAllItems();
            this.jcbSerie.insertItemAt("", 0);

            Integer idAsunto = (Integer) ComboBoxObject.getClaveSelCombo(this.jcbAsunto);
            Integer idSite = (Integer) ComboBoxObject.getClaveSelCombo(this.jcbSite);
            Integer idTipoRA = TipoRA.getTipoRAPorIdSite(idSite).getIdTipoRA();
            
            cargarCodigos(idAsunto, idTipoRA);
            
            // Habilito
            this.jbGuardar.setEnabled(true);
            this.jbEliminar.setEnabled(true);
            this.jbAnadir.setEnabled(true);
            this.jbActualizar.setEnabled(true);
            this.jcbSerie.setEnabled(true);
            this.jcbCanal.setEnabled(true);

            int nLineas = this.jtHistorico.getRowCount();
            // se carga la última
            if (nLineas > 0) {
                int numConfig = (Integer) this.jtHistorico.getValueAt(nLineas - 1, 0);
                cargarLineas(numConfig);
            }

            this.jsFechaIniNueva.setEnabled(false);
        } else{
            JOptionPane.showMessageDialog(this, "Introduzca fecha de inicio de configuracion","ADVERTENCIA",JOptionPane.WARNING_MESSAGE);
        }
    } catch (SQLException e) {
        MensajeApp.muestraError(this, e, "Fallo al consultar la base de datos");
    } catch (NoSuchFieldException e) {
        MensajeApp.muestraError(this, e, "Fallo añadiendo campo");
    }
}//GEN-LAST:event_cargarLineasConf

private void imprimir(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imprimir
    try {
        if (this.jtLineasConf.print())
            MensajeApp.muestraInfo(this, "Impresión finalizada correctamente");
        else
            MensajeApp.muestraError(this, null, "Fallo realizando la impresión");
    } catch (PrinterException e) {
        MensajeApp.muestraError(this, e, "Fallo realizando impresión");
    }
}//GEN-LAST:event_imprimir

private void actualizar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizar
    try {
        int fila = jtLineasConf.getSelectedRow();

        if (fila != -1) {
            DefaultTableModel dtmLineasConf = (DefaultTableModel) this.jtLineasConf.getModel();
            int colCodigo = dtmLineasConf.findColumn(COL_SERIE);
            int colCanal = dtmLineasConf.findColumn(COL_CANAL);

            Integer canalNuevo = (Integer) ComboBoxObject.getClaveSelCombo(this.jcbCanal);
            if (canalNuevo != null) {
                String canalNuevoTxt = (String) ComboBoxObject.getDescSelCombo(this.jcbCanal);
                String canalViejoTxt = (String) dtmLineasConf.getValueAt(fila, colCanal);
                
                dtmLineasConf.setValueAt(canalNuevoTxt, fila, colCanal);

                eliminarItemCombo(this.jcbCanal, canalNuevo);
                if (canalViejoTxt != null) {
                    Integer canalViejo = Integer.parseInt(canalViejoTxt.replaceAll("[^0-9]+", "").trim());
                    anadirItemComboOrdenado(this.jcbCanal, new ComboBoxObject(canalViejo, canalViejoTxt));
                }
            }

            Integer serieNueva = (Integer) ComboBoxObject.getClaveSelCombo(this.jcbSerie);
            if (serieNueva != null){
                String serieNuevaTxt = (String) ComboBoxObject.getDescSelCombo(this.jcbSerie);
                String serieViejaTxt = (String) dtmLineasConf.getValueAt(fila, colCodigo);
                
                dtmLineasConf.setValueAt(serieNuevaTxt, fila, colCodigo);

                eliminarItemCombo(this.jcbSerie, serieNueva);
                if (serieViejaTxt != null) {
                    Integer serieVieja = SerieRA2.getSerieRA2PorCodigo(serieViejaTxt).getIdSerie();
                    anadirItemComboOrdenado(this.jcbSerie, new ComboBoxObject(serieVieja, serieViejaTxt));
                }
            }

            this.jcbSerie.setSelectedIndex(0);
            this.jcbCanal.setSelectedIndex(0);

            Auxiliares.centrarTabla(this.jtLineasConf);
        }
    } catch (SQLException e) {
        MensajeApp.muestraError(this, e, "Fallo al consultar la base de datos");
    } catch (NoSuchFieldException e) {
        MensajeApp.muestraError(this, e, "Fallo añadiendo campo");
    }
}//GEN-LAST:event_actualizar

private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
    try {
        //Validaciones de campos
        this.jsFechaIniMed.setInputVerifier(new IVExtendido(this, IVExtendido.TIPO_FECHA, false, true));
        this.jsFechaIniNueva.setInputVerifier(new IVExtendido(this, IVExtendido.TIPO_FECHA, false, true));

        //Traemos las cabeceras de la tabla
        final Object[] columnas = new Object[]{COL_SERIE, COL_EQUIPO, COL_CANAL, COL_COTA, COL_CERTIFICADO, COL_SLOPE, COL_OFFSET, COL_SLOPE_P, COL_OFFSET_P, COL_ORIENTACION};
        final boolean[] canEdit = new boolean[]{false, true, false, true, true, true, true, true, true, true};
        final Class[] types = new Class [] {java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class};
        
        this.jtLineasConf.setModel(new javax.swing.table.DefaultTableModel(new Object[][]{}, columnas) {
            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        this.jtLineasConf.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        this.jtLineasConf.getTableHeader().setReorderingAllowed(false);

        //Carga inicial de Asuntos
        Auxiliares.cargaAsuntos(this.jcbAsunto);
    } catch (SQLException e) {
        MensajeApp.muestraError(this, e, "Fallo al consultar la base de datos");
    } catch (NoSuchFieldException e) {
        MensajeApp.muestraError(this, e, "Fallo añadiendo campo");
    }
}//GEN-LAST:event_formWindowOpened

private void limpiarCampos() {
    DefaultTableModel dtmHistorico = (DefaultTableModel) this.jtHistorico.getModel();
    DefaultTableModel dtmLineasConf = (DefaultTableModel) this.jtLineasConf.getModel();
    
    // Fase historico
    this.jsFechaIniMed.setText(TratFechas.FECHA_MIN);
    this.jsFechaIniMed.setEnabled(false);
    dtmHistorico.setRowCount(0);

    // Fase Nueva
    this.jsFechaIniNueva.setText(TratFechas.FECHA_MIN);
    this.jsFechaIniNueva.setEnabled(true);
    this.jtfDeclinacion.setText("0");
    this.jrbDecEste.setSelected(true);
    this.jrbDecOeste.setSelected(false);
    this.jbCargarLineas.setEnabled(false);

    // Fase Lineas
    this.jbAnadir.setEnabled(false);
    this.jbEliminar.setEnabled(false);
    this.jbActualizar.setEnabled(false);
    this.jcbSerie.setEnabled(false);
    this.jcbSerie.removeAllItems();
    this.jcbCanal.setEnabled(false);
    this.jcbSerie.removeAllItems();

    dtmLineasConf.setRowCount(0);
}

private void cargarLineas(Integer numConfig){
    try {
        Integer idAsunto = (Integer) ComboBoxObject.getClaveSelCombo(this.jcbAsunto);
        Integer idSite = (Integer) ComboBoxObject.getClaveSelCombo(this.jcbSite);
        Integer idTipoRA = TipoRA.getTipoRAPorIdSite(idSite).getIdTipoRA();
        
        cargarCodigos(idAsunto, idTipoRA);
        cargarCanales();
        
        ArrayList<LineaConfiguracion> lineasConf = LineaConfiguracion.getLineasConfPorAsuntoSiteConf(idAsunto, idSite, numConfig);
        LineaConfiguracion lineaConf = null;
        int nLineas = lineasConf != null ? lineasConf.size() : 0;

        if (nLineas > 0) {
            DefaultTableModel dtmLineasConf = (DefaultTableModel) this.jtLineasConf.getModel();
            dtmLineasConf.setRowCount(0);

            for (int i = 0; i < nLineas; i++) {
                lineaConf = lineasConf.get(i);

                dtmLineasConf.addRow(new Object[]{SerieRA2.getSerieRA2PorId(lineaConf.getIdSerie()).getCodigo(), lineaConf.getEquipo(), lineaConf.getCanal(), lineaConf.getCota(), lineaConf.getCertificado(), lineaConf.getSlope(), lineaConf.getOffset(), lineaConf.getSlopeP(), lineaConf.getOffsetP(), lineaConf.getOrientacion()});
                
                eliminarItemCombo(this.jcbSerie, lineaConf.getIdSerie());
                eliminarItemCombo(this.jcbCanal, Integer.parseInt(lineaConf.getCanal().replaceAll("[^0-9]+", "").trim()));
            }
        }
        Auxiliares.centrarTabla(this.jtLineasConf);

        Declinacion dec = Declinacion.getDeclinacionPorAsuntoSiteConf(idAsunto, idSite, numConfig);
        if (dec != null) {
            this.jtfDeclinacion.setText(dec.getAngulo().toString());
            this.jrbDecEste.setSelected(dec.getPosicion().equals(Declinacion.CHAR_ESTE));
            this.jrbDecEste.setSelected(dec.getPosicion().equals(Declinacion.CHAR_OESTE));
        }
    } catch (SQLException e) {
        MensajeApp.muestraError(this, e, "Fallo al consultar la base de datos");
    } catch (NoSuchFieldException e) {
        MensajeApp.muestraError(this, e, "Fallo añadiendo campo");
    }
}

private void cargarCodigos(Integer idAsunto, Integer idTipoRA) {
    try {
        // Inicializo el combo series
        this.jcbSerie.removeAllItems();
        this.jcbSerie.insertItemAt(new ComboBoxObject(null, ""), 0);

        // Cargo el combo series
        if (idAsunto != null) {
            ArrayList<String> codigosGen = SerieRA2.getCodigosPorIdAsuntoTipo(idAsunto, 0);
            ArrayList<String> codigos = SerieRA2.getCodigosPorIdAsuntoTipo(idAsunto, idTipoRA);
            
            codigos.addAll(codigosGen);
            
            if (codigos != null) {
                int n = codigos.size();
                for (int i = 0; i < n; i++) {
                    ComboBoxObject dato = new ComboBoxObject(SerieRA2.getSerieRA2PorCodigo(codigos.get(i)).getIdSerie(), codigos.get(i));

                    this.jcbSerie.insertItemAt(dato, i + 1);
                }
            }
            this.jcbSerie.setSelectedIndex(0);
        }
    } catch (SQLException e) {
        MensajeApp.muestraError(this, e, "Fallo al consultar la base de datos");
    } catch (NoSuchFieldException e) {
        MensajeApp.muestraError(this, e, "Fallo añadiendo campo");
    }
}

private void cargarCanales() {
    this.jcbCanal.removeAllItems();
    this.jcbCanal.insertItemAt(new ComboBoxObject(null, ""), 0);

    for (int i = 1; i <= CANALES_MAX; i++) {
        ComboBoxObject dato = new ComboBoxObject(i, "Canal" + i);

        this.jcbCanal.insertItemAt(dato, i);
    }

    this.jcbCanal.setSelectedIndex(0);
}

private void eliminarItemCombo(JComboBox jcb, Object clave) {
    int nItems = jcb.getItemCount();

    for (int i = 0; i < nItems; i++) {
        if (ComboBoxObject.getClaveCombo(jcb, i) != null && clave.equals(ComboBoxObject.getClaveCombo(jcb, i))) {
            jcb.removeItemAt(i);
            break;
        }
    }
}
private void anadirItemComboOrdenado(JComboBox jcb, ComboBoxObject cbo) {
    JComboBox jcbAux = new JComboBox();
    int nItems = jcb.getItemCount();
    Comparable desc = (Comparable) cbo.getDescripcion();

    int pos;

    for (pos = 0; pos < nItems; pos++) {
        if (ComboBoxObject.getClaveCombo(jcb, pos) == null || desc.compareTo((Comparable) ComboBoxObject.getDescCombo(jcb, pos)) > 0) {
            jcbAux.addItem(jcb.getItemAt(pos));
            continue;
        }
        break;
    }

    jcbAux.addItem(cbo);

    for (int i = pos; i < nItems; i++)
        jcbAux.addItem(jcb.getItemAt(i));

    jcb.removeAllItems();
    for (int i = 0; i < nItems + 1; i++)
        jcb.addItem(jcbAux.getItemAt(i));
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgDeclinacion;
    public javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jTitConfiguraciones;
    private javax.swing.JButton jbActualizar;
    private javax.swing.JButton jbAnadir;
    private javax.swing.JButton jbCancelar;
    private javax.swing.JButton jbCargarLineas;
    private javax.swing.JButton jbEliminar;
    private javax.swing.JButton jbGuardar;
    private javax.swing.JButton jbImprimirTabla;
    public javax.swing.JComboBox jcbAsunto;
    private javax.swing.JComboBox jcbCanal;
    private javax.swing.JComboBox jcbSerie;
    private javax.swing.JComboBox jcbSite;
    private javax.swing.JLabel jlAsunto;
    private javax.swing.JLabel jlCanal;
    private javax.swing.JLabel jlDeclinacion;
    private javax.swing.JLabel jlFechaIniMed;
    private javax.swing.JLabel jlFechaIniNueva;
    private javax.swing.JLabel jlGrados;
    private javax.swing.JLabel jlSepHistorico;
    private javax.swing.JLabel jlSepNueva;
    private javax.swing.JLabel jlSerie;
    private javax.swing.JLabel jlSite;
    private javax.swing.JLabel jlTitHistorico;
    private javax.swing.JLabel jlTitLineas;
    private javax.swing.JLabel jlTitNueva;
    private javax.swing.JPanel jpCabecera;
    private javax.swing.JPanel jpDatos;
    private javax.swing.JPanel jpPrincipal;
    private javax.swing.JRadioButton jrbDecEste;
    private javax.swing.JRadioButton jrbDecOeste;
    private general.JSpinnerDate jsFechaIniMed;
    private general.JSpinnerDate jsFechaIniNueva;
    private javax.swing.JSeparator jsSep;
    private javax.swing.JScrollPane jspHistorico;
    private javax.swing.JScrollPane jspLineasConf;
    private javax.swing.JTable jtHistorico;
    private javax.swing.JTable jtLineasConf;
    private javax.swing.JTextField jtfDeclinacion;
    // End of variables declaration//GEN-END:variables
}
