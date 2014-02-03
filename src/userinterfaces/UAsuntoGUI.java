package userinterfaces;

import RA.Login;
import RA.Aerogenerador;
import RA.AerogeneradorRA;
import RA.Asunto;
import RA.AsuntoPosicionRA;
import RA.AsuntoRA;
import RA.Cliente;
import RA.FechaRA;
import RA.Norma;
import RA.NormaRA;
import RA.Numero;
import RA.Parque;
import RA.PosicionRA;
import RA.Responsable;
import general.Auxiliares;
import general.CircleDrawer;
import general.ComboBoxObject;
import general.InteraccionFic;
import general.JComboBoxCellEditor;
import general.MensajeApp;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Shape;
import java.util.ArrayList;
import java.awt.Toolkit;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYDrawableAnnotation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.event.ChartChangeEvent;
import org.jfree.chart.event.ChartChangeListener;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.util.ShapeUtilities;



public class UAsuntoGUI extends JDialog {
    
  
    public UAsuntoGUI(java.awt.Frame parent, int id) {
       super(parent, true);
       this.Id=id;
       initComponents();
       this.dtm1=(DefaultTableModel)this.jTable1.getModel();
    }
   
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpInfoPosRA = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel65 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jcbAsunto = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jcbNorma = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox();
        jLabel30 = new javax.swing.JLabel();
        jComboBox12 = new javax.swing.JComboBox();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jComboBox13 = new javax.swing.JComboBox();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jComboBox7 = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        jComboBox10 = new javax.swing.JComboBox();
        jLabel19 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jComboBox8 = new javax.swing.JComboBox();
        jLabel16 = new javax.swing.JLabel();
        jComboBox11 = new javax.swing.JComboBox();
        jLabel20 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jTextField18 = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jTextField19 = new javax.swing.JTextField();
        jpPosicionesRA = new javax.swing.JPanel();
        jpDatosPosicionesRA = new javax.swing.JPanel();
        jspPosicionesRA = new javax.swing.JScrollPane();
        jtPosicionesRA = new javax.swing.JTable();
        jpBotonesPosRA = new javax.swing.JPanel();
        jbCargarFicPosRA = new javax.swing.JButton();
        jlCargarFicPosRA = new javax.swing.JLabel();
        jbVerFormatoPosRA = new javax.swing.JButton();
        jlVerFormatoPosRA = new javax.swing.JLabel();
        jbVisualizarPosRA = new javax.swing.JButton();
        jlVisualizarPosRA = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jpContGraficaPosRA = new javax.swing.JPanel();
        jpGraficaPosRA = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jLabel8.setText("-  El fichero de posiciones debe ser un fichero de texto plano.");

        jLabel43.setText("-  Cada línea contiene una posición del parque eólico.");

        jLabel50.setText("-  Para cada posición se introducen en este orden: ");

        jLabel61.setText("1. Coordenada X");

        jLabel62.setText("2. Coordenada Y");

        jLabel63.setText("3. Coordenada Z");

        jLabel64.setText("-  El separador de cada componente es el carácter   ");

        jTextField7.setEditable(false);
        jTextField7.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField7.setText(" ;");

        jLabel65.setText("-  Es obligatorio que aparezcan cinco separadores por linea");

        jLabel70.setText("-  Ejemplo:");

        jTextArea1.setColumns(15);
        jTextArea1.setEditable(false);
        jTextArea1.setRows(2);
        jTextArea1.setText("512080;4150999;250;0\n512080;4150099;185;3\n512083;4150929;282;-1");
        jScrollPane2.setViewportView(jTextArea1);

        jLabel66.setText("4. Tipo de posición (opcional)");

        jLabel67.setText("- 0.- Aerogenerador");

        jLabel68.setText("- n.- Micrófono n (n >= 1)");

        jLabel71.setText("- -1.- Sin definir");

        javax.swing.GroupLayout jpInfoPosRALayout = new javax.swing.GroupLayout(jpInfoPosRA);
        jpInfoPosRA.setLayout(jpInfoPosRALayout);
        jpInfoPosRALayout.setHorizontalGroup(
            jpInfoPosRALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpInfoPosRALayout.createSequentialGroup()
                .addGroup(jpInfoPosRALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpInfoPosRALayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jpInfoPosRALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel43)
                            .addComponent(jLabel50)))
                    .addGroup(jpInfoPosRALayout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addGroup(jpInfoPosRALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel61)
                            .addComponent(jLabel62)
                            .addComponent(jLabel63)
                            .addGroup(jpInfoPosRALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel66)
                                .addGroup(jpInfoPosRALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel68)
                                    .addComponent(jLabel67)
                                    .addComponent(jLabel71)))))
                    .addGroup(jpInfoPosRALayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jpInfoPosRALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpInfoPosRALayout.createSequentialGroup()
                                .addComponent(jLabel70)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel65)
                            .addGroup(jpInfoPosRALayout.createSequentialGroup()
                                .addComponent(jLabel64)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpInfoPosRALayout.setVerticalGroup(
            jpInfoPosRALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpInfoPosRALayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel43)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel50)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel61)
                .addGap(7, 7, 7)
                .addComponent(jLabel62)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpInfoPosRALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel64)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel65)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpInfoPosRALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel70)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("EDICIÓN de ASUNTO");
        setBackground(java.awt.Color.white);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setForeground(new java.awt.Color(255, 0, 0));
        setLocationByPlatform(true);
        setName("Cliente"); // NOI18N
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                Cerrar(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Asunto:");

        jcbAsunto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CambioAsunto(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(RA.Global.RUTA_IMAGENES + "pencil.png" )));
        jButton1.setToolTipText("Actualizar campos");
        jButton1.setEnabled(false);
        jButton1.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Editar(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jcbAsunto, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jcbAsunto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel21.setText(" Identificación:");

        jTextField1.setEditable(false);
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                ValidarCodigo(evt);
            }
        });

        jLabel22.setText("  Código:");

        jLabel23.setText("  Nombre:");

        jTextField5.setEditable(false);
        jTextField5.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTextField5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                Ncorto(evt);
            }
        });

        jLabel24.setText(" NC:");

        jTextField8.setEditable(false);
        jTextField8.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTextField8.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                ValidarNc(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel24)
                .addGap(10, 10, 10)
                .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jLabel24)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setText("  Norma:");

        jLabel3.setText("  Responsable:");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));
        jComboBox2.setEnabled(false);
        Responsable R=new Responsable();
        try{
            int n=(R.getResponsablesActivo()).size();
            for(int i=0;i<n;i++){
                String dato=(String)(R.getResponsablesActivo()).get(i);
                this.jComboBox2.insertItemAt(dato,i+1);
            }
            this.jComboBox2.setSelectedIndex(0);

        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }

        jLabel10.setText("  Periodicidad:");

        jTextField6.setEditable(false);
        jTextField6.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTextField6.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                ValidarPeriodicidad(evt);
            }
        });

        jLabel11.setText("[días]");

        jcbNorma.setEnabled(false);

        jLabel12.setText("  Estado:");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setText(" Descripción:");

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "ABIERTO", "CERRADO" }));
        jComboBox5.setEnabled(false);

        jLabel30.setText("  Idioma:");

        jComboBox12.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "Castellano", "Inglés" }));
        jComboBox12.setEnabled(false);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel10)
                            .addComponent(jLabel2)
                            .addComponent(jLabel12)
                            .addComponent(jLabel30))))
                .addGap(17, 17, 17)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbNorma, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jComboBox12, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBox5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jcbNorma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(12, 12, 12)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(jComboBox12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setText("  Empresa:");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));
        jComboBox3.setEnabled(false);
        Cliente C=new Cliente();
        try{
            int n=(C.getClientes()).size();
            for(int i=0;i<n;i++){
                String dato=(String)(C.getClientes()).get(i);
                this.jComboBox3.insertItemAt(dato,i+1);
            }
            this.jComboBox3.setSelectedIndex(0);

        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CargarContactos(evt);
            }
        });

        jLabel5.setText("  Persona / s de Contacto: ");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setText(" Cliente:");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "[ Contactos ]"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jComboBox13.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));
        jComboBox13.setEnabled(false);

        jButton4.setBackground(new java.awt.Color(255, 255, 255));
        jButton4.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(RA.Global.RUTA_IMAGENES + "Resultset_up.png" )));
        jButton4.setEnabled(false);
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BorrarContacto(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(255, 255, 255));
        jButton5.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(RA.Global.RUTA_IMAGENES + "Resultset_down.png" )));
        jButton5.setEnabled(false);
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                NuevoContacto(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(67, 67, 67)
                                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBox13, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(jComboBox13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel13.setText("  Modelo:");

        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));
        jComboBox7.setEnabled(false);
        Aerogenerador Aerog=new Aerogenerador();
        try{
            int n=(Aerog.getModelos()).size();
            for(int i=0;i<n;i++){
                String dato=(String)(Aerog.getModelos()).get(i);
                this.jComboBox7.insertItemAt(dato,i+1);
            }
            this.jComboBox7.setSelectedIndex(0);

        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        jComboBox7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CargarHb(evt);
            }
        });

        jLabel14.setText("  Hb (m):");

        jComboBox10.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));
        jComboBox10.setEnabled(false);

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel19.setText(" Aerogenerador:");

        jLabel33.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(RA.Global.RUTA_IMAGENES + "lock.png" )));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel13))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox10, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addGap(13, 13, 13)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(jLabel14))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel13)
                                    .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel15.setText("  Parque:");

        jComboBox8.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));
        jComboBox8.setEnabled(false);
        Parque Pa=new Parque();
        try{
            int n=(Pa.getParques()).size();
            for(int i=0;i<n;i++){
                String dato=(String)(Pa.getParques()).get(i);
                this.jComboBox8.insertItemAt(dato,i+1);
            }
            this.jComboBox8.setSelectedIndex(0);

        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        jComboBox8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CargarPosiciones(evt);
            }
        });

        jLabel16.setText("  Posicion: ");

        jComboBox11.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));
        jComboBox11.setEnabled(false);

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel20.setText(" Posición:");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox11, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jComboBox8, 0, 280, Short.MAX_VALUE)))
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel25.setText("  Fecha Incicio de Ensayo:");

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel27.setText(" Campaña :");

        jTextField9.setBackground(new java.awt.Color(204, 204, 204));
        jTextField9.setEditable(false);
        jTextField9.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField9.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                ValidarFecha(evt);
            }
        });

        jLabel26.setText("  Fecha Incicio de Calibración:");

        jTextField11.setBackground(new java.awt.Color(204, 204, 204));
        jTextField11.setEditable(false);
        jTextField11.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField11.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                ValidarFechaSC(evt);
            }
        });

        jLabel34.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(RA.Global.RUTA_IMAGENES + "lock.png" )));

        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("AG ESTÁNDAR");
        jLabel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("MINI AG");
        jLabel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel29.setText("Fecha Inicio de Curva:");

        jTextField18.setBackground(new java.awt.Color(204, 204, 204));
        jTextField18.setEditable(false);
        jTextField18.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel31.setText("Fecha Inicio de Duración:");

        jTextField19.setBackground(new java.awt.Color(204, 204, 204));
        jTextField19.setEditable(false);
        jTextField19.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel25)
                            .addComponent(jLabel26))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel31)
                            .addComponent(jLabel29))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextField19)
                            .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)))
                .addContainerGap())
            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel11Layout.createSequentialGroup()
                    .addGap(13, 13, 13)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(25, 25, 25)))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27)
                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26)
                            .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25)
                            .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel29)
                            .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel31)
                            .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel11Layout.createSequentialGroup()
                    .addGap(47, 47, 47)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(jLabel7))
                    .addContainerGap(84, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.LEADING, 0, 690, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 357, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(507, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("", jPanel4);

        jpPosicionesRA.setBackground(new java.awt.Color(255, 255, 255));

        jpDatosPosicionesRA.setBackground(new java.awt.Color(255, 255, 255));
        jpDatosPosicionesRA.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jtPosicionesRA.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "[x]", "[y]", "[z]", "Tipo", "Id"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtPosicionesRA.setEnabled(false);
        jtPosicionesRA.getTableHeader().setReorderingAllowed(false);
        jspPosicionesRA.setViewportView(jtPosicionesRA);
        if (jtPosicionesRA.getColumnModel().getColumnCount() > 0) {
            jtPosicionesRA.getColumnModel().getColumn(4).setResizable(false);
        }

        jpBotonesPosRA.setBackground(new java.awt.Color(255, 255, 255));
        jpBotonesPosRA.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jbCargarFicPosRA.setBackground(new java.awt.Color(255, 255, 255));
        jbCargarFicPosRA.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(RA.Global.RUTA_IMAGENES + "OpenFolder.gif" )));
        jbCargarFicPosRA.setToolTipText("Cargar Fichero");
        jbCargarFicPosRA.setEnabled(false);
        jbCargarFicPosRA.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jbCargarFicPosRA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargarPosicionesRA(evt);
            }
        });

        jlCargarFicPosRA.setText("CARGAR FICHERO");

        jbVerFormatoPosRA.setBackground(new java.awt.Color(255, 255, 255));
        jbVerFormatoPosRA.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(RA.Global.RUTA_IMAGENES + "Information.png" )));
        jbVerFormatoPosRA.setToolTipText("Ver Formato de Fichero\n");
        jbVerFormatoPosRA.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jbVerFormatoPosRA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verInfoPosRA(evt);
            }
        });

        jlVerFormatoPosRA.setText("VER FORMATO");

        jbVisualizarPosRA.setBackground(new java.awt.Color(255, 255, 255));
        jbVisualizarPosRA.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(RA.Global.RUTA_IMAGENES + "Information.png" )));
        jbVisualizarPosRA.setToolTipText("Ver Formato de Fichero\n");
        jbVisualizarPosRA.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jbVisualizarPosRA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                visualizarPosRA(evt);
            }
        });

        jlVisualizarPosRA.setText("VISUALIZAR POSICIONES");

        javax.swing.GroupLayout jpBotonesPosRALayout = new javax.swing.GroupLayout(jpBotonesPosRA);
        jpBotonesPosRA.setLayout(jpBotonesPosRALayout);
        jpBotonesPosRALayout.setHorizontalGroup(
            jpBotonesPosRALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpBotonesPosRALayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpBotonesPosRALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jbVisualizarPosRA, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbVerFormatoPosRA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbCargarFicPosRA, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
                .addGroup(jpBotonesPosRALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpBotonesPosRALayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jlCargarFicPosRA))
                    .addGroup(jpBotonesPosRALayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpBotonesPosRALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlVisualizarPosRA)
                            .addComponent(jlVerFormatoPosRA))))
                .addGap(42, 42, 42))
        );
        jpBotonesPosRALayout.setVerticalGroup(
            jpBotonesPosRALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpBotonesPosRALayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpBotonesPosRALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jbCargarFicPosRA, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlCargarFicPosRA))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpBotonesPosRALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jlVerFormatoPosRA)
                    .addComponent(jbVerFormatoPosRA, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpBotonesPosRALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jbVisualizarPosRA, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlVisualizarPosRA))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel69.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel69.setText("Posiciones:");

        jpContGraficaPosRA.setBackground(new java.awt.Color(255, 255, 255));

        jpGraficaPosRA.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        javax.swing.GroupLayout jpGraficaPosRALayout = new javax.swing.GroupLayout(jpGraficaPosRA);
        jpGraficaPosRA.setLayout(jpGraficaPosRALayout);
        jpGraficaPosRALayout.setHorizontalGroup(
            jpGraficaPosRALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 664, Short.MAX_VALUE)
        );
        jpGraficaPosRALayout.setVerticalGroup(
            jpGraficaPosRALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 375, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jpContGraficaPosRALayout = new javax.swing.GroupLayout(jpContGraficaPosRA);
        jpContGraficaPosRA.setLayout(jpContGraficaPosRALayout);
        jpContGraficaPosRALayout.setHorizontalGroup(
            jpContGraficaPosRALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpGraficaPosRA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpContGraficaPosRALayout.setVerticalGroup(
            jpContGraficaPosRALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpGraficaPosRA, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jpDatosPosicionesRALayout = new javax.swing.GroupLayout(jpDatosPosicionesRA);
        jpDatosPosicionesRA.setLayout(jpDatosPosicionesRALayout);
        jpDatosPosicionesRALayout.setHorizontalGroup(
            jpDatosPosicionesRALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDatosPosicionesRALayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpDatosPosicionesRALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpContGraficaPosRA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel69)
                    .addGroup(jpDatosPosicionesRALayout.createSequentialGroup()
                        .addComponent(jspPosicionesRA, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jpBotonesPosRA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jpDatosPosicionesRALayout.setVerticalGroup(
            jpDatosPosicionesRALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDatosPosicionesRALayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel69)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpDatosPosicionesRALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jspPosicionesRA, 0, 0, Short.MAX_VALUE)
                    .addComponent(jpBotonesPosRA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(jpContGraficaPosRA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jpPosicionesRALayout = new javax.swing.GroupLayout(jpPosicionesRA);
        jpPosicionesRA.setLayout(jpPosicionesRALayout);
        jpPosicionesRALayout.setHorizontalGroup(
            jpPosicionesRALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPosicionesRALayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpDatosPosicionesRA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpPosicionesRALayout.setVerticalGroup(
            jpPosicionesRALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPosicionesRALayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpDatosPosicionesRA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("", jpPosicionesRA);

        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(RA.Global.RUTA_IMAGENES + "lock.png" )));
        jLabel32.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel28.setText("Editable para asuntos sin datos insertados");

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setText("CERRAR");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Cancelar(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(RA.Global.RUTA_IMAGENES + "Guardar.png" )));
        jButton2.setToolTipText("Salvar cambios");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ModificarAsunto(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 592, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel32, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(751, 739));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
  
private void Cerrar(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_Cerrar
  dispose();
}//GEN-LAST:event_Cerrar

private void Habilitar(boolean h){
    //Identificadores
    this.jTextField1.setEditable(h);
    this.jTextField5.setEditable(h);
    this.jTextField8.setEditable(h);
    // Descripcion
    this.jcbNorma.setEnabled(h);
    this.jComboBox2.setEnabled(h);
    this.jComboBox5.setEnabled(h);
     this.jComboBox12.setEnabled(h);
    this.jTextField6.setEditable(h); 
    // Cliente
    this.jComboBox3.setEnabled(h);
    this.jComboBox13.setEnabled(h);
    this.jButton4.setEnabled(h);
    this.jButton5.setEnabled(h);
    // AG
    this.jComboBox7.setEnabled(h);
    this.jComboBox10.setEnabled(h);
    // Posicion
    this.jComboBox8.setEnabled(h);
    this.jComboBox11.setEnabled(h);
    // Campaña   
    this.jTextField9.setEditable(h);
    this.jTextField11.setEditable(h);
    this.jTextField19.setEditable(h);
    this.jTextField18.setEditable(h);
    
    //Posiciones RA
    this.jbCargarFicPosRA.setEnabled(h);
    this.jtPosicionesRA.setEnabled(h);
}



private void Editar(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Editar
    if (this.jButton1.isEnabled()) {
        Habilitar(true);
        cambio = true;

        try {
            int asunto = Asunto.getId(this.jcbAsunto.getSelectedItem().toString());

            this.jComboBox10.setEnabled(false);
            this.jComboBox7.setEnabled(false);
            this.jTextField9.setEditable(false);
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}//GEN-LAST:event_Editar

private void ModificarAsunto(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ModificarAsunto
try{
    boolean errorPosRA = false;
   if (cambio==true){
      if ((this.jcbAsunto.getSelectedIndex()!=0) && (this.jComboBox8.getSelectedIndex()!=0) && (this.jComboBox3.getSelectedIndex()!=0) && (this.jComboBox2.getSelectedIndex()!=0) && (this.jComboBox10.getSelectedIndex()!=0 && (this.jComboBox7.getSelectedIndex()!=0)) && (this.jComboBox5.getSelectedIndex()!=0) && (this.jComboBox11.getSelectedIndex()!=0) && (this.dtm1.getRowCount()!=0) && (this.jTextField1.getText().length()!=0) && (this.jTextField5.getText().length()!=0) && (this.jTextField8.getText().length()!=0) && (this.jComboBox12.getSelectedIndex()!=0) &&
          !(errorPosRA = hayErrorPosicionesRA())) {
                   
         int asunto= (Integer) ComboBoxObject.getClaveSelCombo(this.jcbAsunto);
         int parque=PE.getId((String)this.jComboBox8.getSelectedItem());
         Integer idNorma = (Integer) ComboBoxObject.getClaveSelCombo(this.jcbNorma);
         int periodicidad=Integer.parseInt(this.jTextField6.getText());
         int cliente=C.getId((String)this.jComboBox3.getSelectedItem());
         String nombre_responsable=(String)this.jComboBox2.getSelectedItem();
         int pos=nombre_responsable.indexOf(" ");
         String nombrer=nombre_responsable.substring(0, pos);
         int pos2=(nombre_responsable.substring(pos+1,nombre_responsable.length())).indexOf(" ");
         String ape1=(nombre_responsable.substring(pos+1,nombre_responsable.length())).substring(0, pos2);
         int responsable=R.getId(nombrer, ape1);
         String hb=this.jComboBox10.getSelectedItem().toString();
         int aero=AG.getId((String)this.jComboBox7.getSelectedItem(),Double.parseDouble(hb));
         boolean estado=true;
         if (this.jComboBox5.getSelectedIndex()==2){
             estado=false;
         }
         String idioma="C";
         if (this.jComboBox12.getSelectedIndex()==2){
             idioma="E";
         }
         String posicion=String.valueOf(this.jComboBox11.getSelectedItem());
         ArrayList<String> contactos=new ArrayList<String>();
         for (int i=0;i<dtm1.getRowCount();i++){
            contactos.add(i, dtm1.getValueAt(i, 0).toString());
         }
         String FiniPC=this.jTextField9.getText();
         String FiniSC=this.jTextField11.getText();
         String FiniME1=this.jTextField18.getText();
         String FiniME10=this.jTextField19.getText();
         String codigo=this.jTextField1.getText();
         String nombre=this.jTextField5.getText();
         String nc=this.jTextField8.getText();
         System.out.println("A.ModificarAsunto("+asunto+", "+codigo+","+nombre+","+parque+", norma, periodicidad,"+ cliente+", responsable, aero, posicion, pcontacto, mail, tel, estado,Fini,nc");
         if ((!estado) && (Login.rol==0)){ // Cerrar asunto
            int n = JOptionPane.showConfirmDialog(this, "Si cierra el asunto no podrá realizar modificaciones sobre el mismo. ¿Desea contiuar?"
                    ,"Información al usuario",JOptionPane.YES_NO_OPTION);
            if (n == JOptionPane.YES_OPTION) {
                if (A.ModificarAsunto(asunto, codigo,nombre,parque, idNorma, periodicidad, cliente, responsable, aero, posicion, contactos,  estado,nc,idioma,FiniPC,FiniSC,FiniME1,FiniME10)){
                    String errPosRA = guardarPosicionesRA(asunto);
                    if (errPosRA != null && !errPosRA.isEmpty()) 
                        MensajeApp.muestraError(this, null, errPosRA);
                    
                    JOptionPane.showMessageDialog(this,"Asunto modificado","INFORMACIÓN",JOptionPane.INFORMATION_MESSAGE);
                    this.jcbAsunto.setSelectedIndex(0);
                } else {
                    JOptionPane.showMessageDialog(this,"Falló el proceso de modificación","ERROR",JOptionPane.ERROR_MESSAGE);
                }
           }
         } else{
                if (A.ModificarAsunto(asunto, codigo,nombre,parque, idNorma, periodicidad, cliente, responsable, aero, posicion, contactos,  estado,nc,idioma,FiniPC,FiniSC,FiniME1,FiniME10)){
                    String errPosRA = guardarPosicionesRA(asunto);
                    if (errPosRA != null && !errPosRA.isEmpty()) 
                        MensajeApp.muestraError(this, null, errPosRA);
                    
                  JOptionPane.showMessageDialog(this,"Asunto modificado","INFORMACIÓN",JOptionPane.INFORMATION_MESSAGE);
                  this.jcbAsunto.setSelectedIndex(0);
                } else {
                    JOptionPane.showMessageDialog(this,"Falló el proceso de modificación","ERROR",JOptionPane.ERROR_MESSAGE);
                }
              }
     } else{
          if (errorPosRA)
              MensajeApp.muestraError(this, null, "Hay posiciones de ruido acústico duplicadas");
          else
              JOptionPane.showMessageDialog(this,"Rellene los campos obligatorios","ADVERTENCIA",JOptionPane.WARNING_MESSAGE);      
     }
     } else {
          JOptionPane.showMessageDialog(this,"Debe modificar antes de guardar","ADVERTENCIA",JOptionPane.WARNING_MESSAGE);
     } 
} catch (SQLException ex) {
     ex.printStackTrace();
     JOptionPane.showMessageDialog(this,"Falló el proceso de modificación","ERROR",JOptionPane.ERROR_MESSAGE);
} catch (Exception ex) {
     ex.printStackTrace();
     JOptionPane.showMessageDialog(this,"Falló al escribir en fichero","ERROR",JOptionPane.ERROR_MESSAGE);
}

             
}//GEN-LAST:event_ModificarAsunto

private void Cancelar(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Cancelar
    dispose();
}//GEN-LAST:event_Cancelar

private void CargarHb(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CargarHb
if ((this.jComboBox7.getSelectedIndex()!=0) && (this.jComboBox7.isEnabled())){
        // Se borran los campos de la anterior selección
        this.jComboBox10.removeAllItems();
        this.jComboBox10.insertItemAt("",0);
        // Se lee el modelo seleccionado
        String modelo=(String)this.jComboBox7.getSelectedItem();
        try{
            Aerogenerador Aer=new Aerogenerador();
            int n=(Aer.getHbs(modelo)).size();
            for(int i=0;i<n;i++){
                String dato=(String)(Aer.getHbs(modelo)).get(i);
                this.jComboBox10.insertItemAt(dato,i+1);
            }
            this.jComboBox10.setSelectedIndex(0);
        }catch (SQLException ex) {
            ex.printStackTrace();
         }
    }   
}//GEN-LAST:event_CargarHb

private void CargarPosiciones(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CargarPosiciones
if (((this.jComboBox8.getSelectedIndex()!=0) &&this.jComboBox8.isEnabled())){
    String parque=(String)this.jComboBox8.getSelectedItem();
    try{
       Parque Pa=new Parque();
       int n=(Pa.getPosiciones(Pa.getId(parque))).size();
       this.jComboBox11.removeAllItems();
       this.jComboBox11.insertItemAt("",0);
       for(int i=0;i<n;i++){
            String dato=(String)(Pa.getNPosiciones(Pa.getId(parque))).get(i);
            this.jComboBox11.insertItemAt(dato,i+1);
       }
       this.jComboBox11.setSelectedIndex(0);
    }catch (SQLException ex) {
        ex.printStackTrace();
   }
}
}//GEN-LAST:event_CargarPosiciones

private void ValidarFecha(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ValidarFecha
    FechaRA F=new FechaRA();
    if(!F.isFecha(this.jTextField9.getText())){
        this.jTextField9.setText(null);
    }
}//GEN-LAST:event_ValidarFecha

private void ValidarCodigo(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ValidarCodigo
    String campo=this.jTextField1.getText();
    if (campo.length()!=0){
        if (campo.length()!=8){
            this.jTextField1.setText(null);
            JOptionPane.showMessageDialog(this, "Formato xxxx-xxx","ADVERTENCIA",JOptionPane.WARNING_MESSAGE);
        }
    }
}//GEN-LAST:event_ValidarCodigo

private void Ncorto(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Ncorto
if (this.jTextField5.isEditable()){
    String campo=this.jTextField8.getText();
    if (campo.length()!=0){
        int npos=campo.indexOf(" ");
        String nc=campo.substring(0, 3);
        if (npos!=0){
            nc=campo.substring(npos+1, 3); 
        }
        this.jTextField8.setText(nc);
    } 
}
   
}//GEN-LAST:event_Ncorto

private void ValidarNc(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ValidarNc
    String campo=this.jTextField8.getText();
    if (campo.length()!=0){
       if (campo.length()>5) {
        this.jTextField8.setText(null);
        JOptionPane.showMessageDialog(this, "Máximo 5 caracteres","ADVERTENCIA",JOptionPane.WARNING_MESSAGE);
       }
    }
}//GEN-LAST:event_ValidarNc

private void ValidarPeriodicidad(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ValidarPeriodicidad

if (!N.isEntero(this.jTextField6.getText()) & (this.jTextField6.getText().length()!=0)){
    this.jTextField6.setText(null);
    JOptionPane.showMessageDialog(this, "Periodicidad debe ser un campo numérico y entero","ADVERTENCIA",JOptionPane.WARNING_MESSAGE);
}
}//GEN-LAST:event_ValidarPeriodicidad

private void CambioAsunto(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CambioAsunto
   Limpiar();
   Habilitar(false);
   cambio=false;
   if (this.jcbAsunto.getSelectedIndex()!=0){
   try {
        this.jButton1.setEnabled(true);
        
        Integer idAsunto = (Integer) ComboBoxObject.getClaveSelCombo(this.jcbAsunto);
        
        if (idAsunto != null) {
            cargarPosicionesRADeAsunto(idAsunto);
        }
        
        ArrayList <String> Datos=A.getAsunto(idAsunto);
        ArrayList <String> Contactos=A.getContactosExternos(idAsunto,false);
        
        this.jTextField1.setText(Datos.get(1)); // codigo
        this.jTextField5.setText(Datos.get(2)); // nombre
        this.jTextField8.setText(Datos.get(12)); // Nc
        
        //this.jcbNorma.setSelectedItem(NR.getNombre(Integer.parseInt(Datos.get(4))));  // norma
        Integer idNorma = Integer.parseInt(Datos.get(4));
        if (idNorma != null)
            this.jcbNorma.setSelectedItem(ComboBoxObject.getItemWithKey(this.jcbNorma, idNorma));
        else
            this.jcbNorma.setSelectedIndex(-1);
        
        this.jComboBox2.setSelectedItem(R.getNombre(Integer.parseInt(Datos.get(8))));  // responsable
        this.jTextField6.setText(Datos.get(5)); // Periodicidad
        if (Datos.get(10).equals("1")){ // ESTADO
            this.jComboBox5.setSelectedIndex(1);
        } else {
            this.jComboBox5.setSelectedIndex(2);
        }
        this.jComboBox3.setSelectedItem(C.getNombre(Integer.parseInt(Datos.get(7)))); // Cliente
        String modelo=AG.getModelo(Integer.parseInt(Datos.get(9)));
        this.jComboBox7.setSelectedItem(modelo); // AG
        // Cargo el combo Hb
        ArrayList <String> hb=AG.getHbs(modelo);
        this.jComboBox10.removeAllItems();
        this.jComboBox10.insertItemAt("",0);
        for (int i=0;i<hb.size();i++){
            this.jComboBox10.insertItemAt(hb.get(i).toString(),i+1);
        }
        this.jComboBox10.setSelectedItem(String.valueOf(AG.getHb(Integer.parseInt(Datos.get(9)))));
        this.jComboBox8.setSelectedItem(PE.getNombre(Integer.parseInt(Datos.get(3)))); // PE
        // Cargo el combo posiciones
        ArrayList <String []> posiciones=PE.getPosiciones(Integer.parseInt(Datos.get(3)));
        for (int j=0;j<posiciones.size();j++){
            this.jComboBox11.insertItemAt(posiciones.get(j)[0],j+1);
        }
        if (Datos.get(13).equals("C")){ // Idioma
            this.jComboBox12.setSelectedIndex(1);
        } else {
            this.jComboBox12.setSelectedIndex(2);
        }
        this.jComboBox11.setSelectedItem(Datos.get(11).toString());
        this.jTextField9.setText(Datos.get(14));
        this.jTextField11.setText(Datos.get(15));
        this.jTextField18.setText(Datos.get(16));
        this.jTextField19.setText(Datos.get(17));
        dtm1.setRowCount(Contactos.size());
        for (int i=0;i<Contactos.size();i++){
            dtm1.setValueAt(Contactos.get(i), i, 0);
        }
        Centrar(this.jTable1,0,0);
    } catch (SQLException e){
        e.printStackTrace();
    }
   }
}//GEN-LAST:event_CambioAsunto

private void ValidarFechaSC(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ValidarFechaSC
    FechaRA F=new FechaRA();
    if(!F.isFecha(this.jTextField11.getText())){
        this.jTextField11.setText(null);
    }
}//GEN-LAST:event_ValidarFechaSC

private void CargarContactos(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CargarContactos
LimpiarC();
if (((this.jComboBox3.getSelectedIndex()!=0) && this.jComboBox3.isEnabled())){
    String cliente=(String)this.jComboBox3.getSelectedItem();
    try{
       
       int n=(C.getContactos(C.getId(cliente),true)).size();
       this.jComboBox13.removeAllItems();
       this.jComboBox13.insertItemAt("",0);
       for(int i=0;i<n;i++){
            String dato=(String)(C.getContactos(C.getId(cliente),true)).get(i);
            this.jComboBox13.insertItemAt(dato,i+1);
       }
       this.jComboBox13.setSelectedIndex(0);
    }catch (SQLException ex) {
        ex.printStackTrace();
   }
}
}//GEN-LAST:event_CargarContactos

private void NuevoContacto(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NuevoContacto
if (this.jButton5.isEnabled()){
    if (this.jComboBox13.getSelectedIndex()!=0){
        String p=this.jComboBox13.getSelectedItem().toString();
        this.dtm1.setRowCount(this.dtm1.getRowCount()+1);
        this.dtm1.setValueAt(p, this.dtm1.getRowCount()-1, 0);
        Centrar(this.jTable1,0,0);
        this.jComboBox13.removeItem(p);
    }
}
}//GEN-LAST:event_NuevoContacto

private void BorrarContacto(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BorrarContacto
if (this.jButton4.isEnabled()){
    int fila=jTable1.getSelectedRow();
    int fila2=fila+jTable1.getSelectedRowCount()-1;
    if (fila!=-1){ 
         for (int i=fila;i<=fila2;i++){
             this.jComboBox13.addItem(dtm1.getValueAt(i, 0)); 
             dtm1.removeRow(fila);     
         }
    }
} 
}//GEN-LAST:event_BorrarContacto

private void cargarPosicionesRA(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargarPosicionesRA
    try {
        DefaultTableModel dtmPosicionesRA = (DefaultTableModel) this.jtPosicionesRA.getModel();
        JFileChooser fc = new JFileChooser(Auxiliares.dameRutaDefecto(this));
        
        int seleccion = fc.showOpenDialog(this);              

        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File fichero = fc.getSelectedFile();
            String ruta = fichero.getPath();
            InteraccionFic interFic = new InteraccionFic(ruta, InteraccionFic.READ);

            ArrayList<ArrayList<String>> campos = interFic.leeCamposSep(";", 4);
            ArrayList<String> camposFila;
            int nCamposFila;
            Object[] filaArray;
            Integer idPosCombo;
            JComboBox cbPosRA = getComboPosicionesRA();
            TableColumn tc = this.jtPosicionesRA.getColumnModel().getColumn(3);
            JComboBoxCellEditor jcbce = (JComboBoxCellEditor) tc.getCellEditor();
            
            int nFilas = campos != null ? campos.size() : 0;
            
            if (nFilas > 0) {
                if (!campos.get(0).get(0).equals("mal")) {
                    dtmPosicionesRA.setRowCount(0);
                    
                    for (int fila = 0; fila < nFilas; fila++) {
                        camposFila = campos.get(fila);
                        idPosCombo = Integer.parseInt(camposFila.get(camposFila.size() - 1));
                        camposFila.remove(camposFila.size() - 1);
                        nCamposFila = camposFila.size();
                        
                        filaArray = new Object[nCamposFila + 1];
                        
                        for (int i = 0; i < nCamposFila; i++) {
                            filaArray[i] = camposFila.get(i);
                        }
                        
                        filaArray[nCamposFila] = cbPosRA.getItemAt(idPosCombo);
                        
                        jcbce.setSelectedIndex(fila, idPosCombo);
                        dtmPosicionesRA.addRow(filaArray);
                    }

                    Auxiliares.centrarTabla(this.jtPosicionesRA);
                } else
                    MensajeApp.muestraWarning(this, "Formato de fichero no válido");
            } else
                MensajeApp.muestraWarning(this, "Fichero sin contenido");
        }
    } catch (Exception e) {
        MensajeApp.muestraError(this, e, "Error al leer las posiciones");
    }
}//GEN-LAST:event_cargarPosicionesRA

private void verInfoPosRA(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verInfoPosRA
    JOptionPane.showMessageDialog(this, this.jpInfoPosRA, "Información", JOptionPane.INFORMATION_MESSAGE);
}//GEN-LAST:event_verInfoPosRA

private void visualizarPosRA(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_visualizarPosRA
    XYSeriesCollection dataset = null;
    ComboBoxObject cbo;
    DefaultTableModel dtmPosRA = (DefaultTableModel) this.jtPosicionesRA.getModel();
    int nFilas = dtmPosRA.getRowCount();
    
    if (nFilas > 0) {
        dataset = new XYSeriesCollection();
        XYSeries serie;
                
        for (int i = 0; i < nFilas; i++) {
            cbo = (ComboBoxObject) dtmPosRA.getValueAt(i, 3);
            
            if (cbo == null)
                continue;
            
            serie = new XYSeries((String) cbo.getDescripcion());
            
            serie.add(Double.parseDouble((String) dtmPosRA.getValueAt(i, 0)), Double.parseDouble((String) dtmPosRA.getValueAt(i, 1)));

            if (!serie.isEmpty())
                dataset.addSeries(serie);
        }

        Auxiliares.asignaPanelGrafica(this, this.jpContGraficaPosRA, crearGraficaPosicionesRA(dataset), false, null);
        
        final ChartPanel cp = (ChartPanel) this.jpContGraficaPosRA.getComponent(0);
        
        ChartChangeListener changeListener = new ChartChangeListener() {

                public void chartChanged(ChartChangeEvent arg0) {
                    JFreeChart chart = arg0.getChart();
                    
                    if (chart != null) {
                        chart.removeChangeListener(this);
                        redibujaLimitesMicros(cp);
                        chart.addChangeListener(this);
                    }
                }
            };
        
        cp.getChart().addChangeListener(changeListener);
        
        this.update(this.getGraphics());
        redibujaLimitesMicros(cp);
    }
}//GEN-LAST:event_visualizarPosRA

private JComboBox getComboPosicionesRA() {
    JComboBox comboBox = new JComboBox();
    
    comboBox.addItem(new ComboBoxObject(AsuntoPosicionRA.ID_POS_AERO, AsuntoPosicionRA.TXT_POS_AERO));
    comboBox.addItem(new ComboBoxObject(AsuntoPosicionRA.ID_POS_MICRO1, AsuntoPosicionRA.TXT_POS_MICRO1));
    comboBox.addItem(new ComboBoxObject(AsuntoPosicionRA.ID_POS_MICRO2, AsuntoPosicionRA.TXT_POS_MICRO2));
    comboBox.addItem(new ComboBoxObject(AsuntoPosicionRA.ID_POS_MICRO3, AsuntoPosicionRA.TXT_POS_MICRO3));
    comboBox.addItem(new ComboBoxObject(AsuntoPosicionRA.ID_POS_MICRO4, AsuntoPosicionRA.TXT_POS_MICRO4));
    
    comboBox.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            actualizaValorCombo(evt);
        }
    });
    
    return comboBox;
}

private void actualizaValorCombo(java.awt.event.ActionEvent evt) {
    JComboBox jcb = (JComboBox) evt.getSource();
    
    TableColumn comboColumn = this.jtPosicionesRA.getColumnModel().getColumn(3);
    JComboBoxCellEditor jcbce = (JComboBoxCellEditor) comboColumn.getCellEditor(); 
    
    jcbce.setSelectedIndex(this.jtPosicionesRA.getSelectedRow(), jcb.getSelectedIndex());
}

private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
    try {
        //Establecemos las pestañas de JTabbedPane
        Auxiliares.setTitulosJTabbedPane(this.jTabbedPane1, new String[]{"GENERAL", "POSICIONES RA"});
        
        //Maximizamos las pestañas
        Auxiliares.maximizaTitulosJTabbedPane(this.jTabbedPane1);
        
        //Rellenamos combo de asuntos con los relacionados con el parque
        Auxiliares.cargaAsuntosTipo(this.jcbAsunto, AsuntoRA.TIPO_ASUNTO_RA);
        
        //Añadimos la columna Tipo como combo
        TableColumn comboColumn = this.jtPosicionesRA.getColumnModel().getColumn(3);

        JComboBox comboBox = getComboPosicionesRA();
    
        comboColumn.setCellEditor(new JComboBoxCellEditor(comboBox));
        
        //Cargamos el combo de normas
        Auxiliares.cargaNormas(this.jcbNorma);
    } catch (SQLException e) {
        MensajeApp.muestraError(this, e, "Fallo al consultar la base de datos");
        this.dispose();
    } catch (NoSuchFieldException e) {
        MensajeApp.muestraError(this, e, "Fallo añadiendo campo");
        this.dispose();
    }
}//GEN-LAST:event_formWindowOpened


private void redibujaLimitesMicros(ChartPanel chartPanel) {
    JFreeChart chart = chartPanel.getChart();
    XYPlot plot = (XYPlot) chart.getPlot();
    XYSeriesCollection dataset = (XYSeriesCollection) plot.getDataset();

    XYItemRenderer rend = plot.getRenderer();
    rend.removeAnnotations();
    
    int nSeries = plot.getSeriesCount();
    XYSeries serie;
    
    Double posXAero = null, posYAero = null;
    
    for (int i = 0; i < nSeries; i++) {
        serie = dataset.getSeries(i);
        
        if (((String) serie.getKey()).contains(AsuntoPosicionRA.TXT_POS_AERO)) {
            posXAero = (Double) serie.getX(0);
            posYAero = (Double) serie.getY(0);
        }
    }
    
    Double hB = null;
    Double dN = null;
    
    try {
        String modelo = (String) this.jComboBox7.getSelectedItem();
        String hBText = (String) this.jComboBox10.getSelectedItem();
        
        if (modelo != null && hBText != null) {
            AerogeneradorRA aero = AerogeneradorRA.getAeroPorModeloHb(modelo, Double.parseDouble(hBText));

            hB = aero.getHB();
            dN = aero.getDN();
        }
    } catch (NumberFormatException e) {
    } catch (SQLException e) {
        MensajeApp.muestraError(this, e, "Fallo al consultar la base de datos");
    } catch (NoSuchFieldException e) {
        MensajeApp.muestraError(this, e, "Fallo añadiendo campo");
    }
    
    if (posXAero != null && posYAero != null && hB != null && dN != null) {
        double r0 = hB  + dN / 2.0;
        double tolerancia = r0 * 0.2;
        
        Integer idNorma = (Integer) ComboBoxObject.getClaveSelCombo(this.jcbNorma);
        
        if (idNorma != null) {
            //Limitamos la tolerancia a un máximo de 30 metros si estamos en IEC 3.0
            if (idNorma.equals(NormaRA.ID_NORMA_IEC_3_0) && tolerancia > 30.0)
                tolerancia = 30.0;
        }
        
        double maxDom = ((NumberAxis)  plot.getDomainAxis()).getUpperBound();
        double minDom = ((NumberAxis)  plot.getDomainAxis()).getLowerBound();
        double maxRan = ((NumberAxis)  plot.getRangeAxis()).getUpperBound();
        double minRan = ((NumberAxis)  plot.getRangeAxis()).getLowerBound();
        
        Rectangle2D dataArea = chartPanel.getChartRenderingInfo().getPlotInfo().getDataArea();

        Double w = dataArea.getWidth();
        Double h = dataArea.getHeight();
        
        CircleDrawer circleDrawer = new CircleDrawer(Color.red, new BasicStroke(1.0F), null);
        XYDrawableAnnotation annotation = new XYDrawableAnnotation(posXAero, posYAero, 2.0*(r0 + tolerancia) * w / (maxDom - minDom), 2.0*(r0 + tolerancia) * h / (maxRan - minRan), circleDrawer);
        rend.addAnnotation(annotation);

        circleDrawer = new CircleDrawer(Color.green, new BasicStroke(1.0F), null);
        annotation = new XYDrawableAnnotation(posXAero, posYAero, 2.0*(r0 - tolerancia) * w / (maxDom - minDom), 2.0*(r0 - tolerancia) * h / (maxRan - minRan), circleDrawer);
        rend.addAnnotation(annotation);
        
        plot.setRenderer(rend);
    }    
}

private JFreeChart crearGraficaPosicionesRA(XYDataset dataset) {
    XYItemRenderer rendDatos = new XYLineAndShapeRenderer(false, true);

    JFreeChart chart = ChartFactory.createScatterPlot(
            "Posiciones de Ruido Acústico",
            "X",
            "Y",
            dataset,
            PlotOrientation.VERTICAL,
            true,
            true,
            false);

    TextTitle texto = chart.getTitle();
    texto.setFont(new Font("Arial", Font.BOLD, 13));
    chart.setBackgroundPaint(Color.WHITE);
    XYPlot plot = (XYPlot) chart.getPlot();
    Shape r = ShapeUtilities.createDiamond(4f);

    int nSeries = plot.getSeriesCount();

    for (int i = 0; i < nSeries; i++) {
        rendDatos.setSeriesShape(i, r);
        plot.setRenderer(i, rendDatos);
    }

    return chart;
}

private void cargarPosicionesRADeAsunto(Integer idAsunto) {
    try {
        AsuntoPosicionRA asuntoPos = AsuntoPosicionRA.getAsuntoPosicionRA(idAsunto);
        JComboBox jcbPosRA = getComboPosicionesRA();

        if (asuntoPos != null) {
            TableColumn tc = this.jtPosicionesRA.getColumnModel().getColumn(3);
            JComboBoxCellEditor jcbce = (JComboBoxCellEditor) tc.getCellEditor();
            DefaultTableModel dtmPosicionesRA = (DefaultTableModel) this.jtPosicionesRA.getModel();
            dtmPosicionesRA.setRowCount(0);

            PosicionRA posAero = asuntoPos.getIdPosAero() != null ? PosicionRA.getPosicion(asuntoPos.getIdPosAero()) : null;
            if (posAero != null) {
                dtmPosicionesRA.addRow(new Object[]{posAero.getPosX().toString(), posAero.getPosY().toString(), posAero.getPosZ().toString(), jcbPosRA.getItemAt(AsuntoPosicionRA.ID_POS_AERO), posAero.getId()});
                jcbce.setSelectedIndex(dtmPosicionesRA.getRowCount() - 1, AsuntoPosicionRA.ID_POS_AERO);
            }

            PosicionRA posMicro1 = asuntoPos.getIdPosMicro1() != null ? PosicionRA.getPosicion(asuntoPos.getIdPosMicro1()) : null;
            if (posMicro1 != null) {
                dtmPosicionesRA.addRow(new Object[]{posMicro1.getPosX().toString(), posMicro1.getPosY().toString(), posMicro1.getPosZ().toString(), jcbPosRA.getItemAt(AsuntoPosicionRA.ID_POS_MICRO1), posMicro1.getId()});
                jcbce.setSelectedIndex(dtmPosicionesRA.getRowCount() - 1, AsuntoPosicionRA.ID_POS_MICRO1);
            }

            PosicionRA posMicro2 = asuntoPos.getIdPosMicro2() != null ? PosicionRA.getPosicion(asuntoPos.getIdPosMicro2()) : null;
            if (posMicro2 != null) {
                dtmPosicionesRA.addRow(new Object[]{posMicro2.getPosX().toString(), posMicro2.getPosY().toString(), posMicro2.getPosZ().toString(), jcbPosRA.getItemAt(AsuntoPosicionRA.ID_POS_MICRO2), posMicro2.getId()});
                jcbce.setSelectedIndex(dtmPosicionesRA.getRowCount() - 1, AsuntoPosicionRA.ID_POS_MICRO2);
            }

            PosicionRA posMicro3 = asuntoPos.getIdPosMicro3() != null ? PosicionRA.getPosicion(asuntoPos.getIdPosMicro3()) : null;
            if (posMicro3 != null) {
                dtmPosicionesRA.addRow(new Object[]{posMicro3.getPosX().toString(), posMicro3.getPosY().toString(), posMicro3.getPosZ().toString(), jcbPosRA.getItemAt(AsuntoPosicionRA.ID_POS_MICRO3), posMicro3.getId()});
                jcbce.setSelectedIndex(dtmPosicionesRA.getRowCount() - 1, AsuntoPosicionRA.ID_POS_MICRO3);
            }

            PosicionRA posMicro4 = asuntoPos.getIdPosMicro4() != null ? PosicionRA.getPosicion(asuntoPos.getIdPosMicro4()) : null;
            if (posMicro4 != null) {
                dtmPosicionesRA.addRow(new Object[]{posMicro4.getPosX().toString(), posMicro4.getPosY().toString(), posMicro4.getPosZ().toString(), jcbPosRA.getItemAt(AsuntoPosicionRA.ID_POS_MICRO4), posMicro4.getId()});
                jcbce.setSelectedIndex(dtmPosicionesRA.getRowCount() - 1, AsuntoPosicionRA.ID_POS_MICRO4);
            }
        }
    } catch (SQLException e) {
        MensajeApp.muestraError(this, e, "Fallo al consultar la base de datos");
    } catch (NoSuchFieldException e) {
        MensajeApp.muestraError(this, e, "Fallo añadiendo campo");
    }
}


private boolean hayErrorPosicionesRA() {
    boolean res = false;
    ComboBoxObject cbo;
    ArrayList<Integer> selectedIndexes = new ArrayList<Integer>();
    Integer selectedIndex;
    DefaultTableModel dtmPosicionesRA = (DefaultTableModel) this.jtPosicionesRA.getModel();
    
    int nFilas = dtmPosicionesRA.getRowCount();
    for (int i = 0; i < nFilas; i++) {
        cbo = (ComboBoxObject) dtmPosicionesRA.getValueAt(i, 3);
        
        if (cbo == null)
            continue;
        
        selectedIndex = (Integer) cbo.getCodigo();
        
        if (selectedIndexes.contains(selectedIndex)) {
            res = true;
            break;
        }
        
        selectedIndexes.add(selectedIndex);
    }
    
    return res;
}

private String guardarPosicionesRA(Integer idAsunto) throws SQLException {
    String err = null;
    DefaultTableModel dtmPosicionesRA = (DefaultTableModel) this.jtPosicionesRA.getModel();
    
    int nFilas = dtmPosicionesRA.getRowCount();
    Integer idTipoPos;
    Integer idPos, idPosAero = null, idPosMicro1 = null, idPosMicro2 = null, idPosMicro3 = null, idPosMicro4 = null;
    Double posX, posY, posZ;
    
    for (int i = 0; i < nFilas; i++) {
        posX = Double.parseDouble(dtmPosicionesRA.getValueAt(i, 0).toString());
        posY = Double.parseDouble(dtmPosicionesRA.getValueAt(i, 1).toString());
        posZ = Double.parseDouble(dtmPosicionesRA.getValueAt(i, 2).toString());
        
        if (dtmPosicionesRA.getValueAt(i, 3) == null)
            continue;
        
        idTipoPos = (Integer) ((ComboBoxObject) dtmPosicionesRA.getValueAt(i, 3)).getCodigo();
        
        //Si tiene id asignado, sobreescribimos
        idPos = (Integer) dtmPosicionesRA.getValueAt(i, 4);
        
        if (idPos == null)
            idPos = PosicionRA.insertPosicionGetId(posX, posY, posZ, null);
        else
            PosicionRA.updatePosicion(idPos, null, null, null, idPos, posX, posY, posZ, null);
        
        if (idPos == null) {
            err = "Fallo insertando posiciones de ruido acústico";
            break;
        }
        
        if (idTipoPos == AsuntoPosicionRA.ID_POS_AERO)
            idPosAero = idPos;
        else if (idTipoPos == AsuntoPosicionRA.ID_POS_MICRO1)
            idPosMicro1 = idPos;
        else if (idTipoPos == AsuntoPosicionRA.ID_POS_MICRO2)
            idPosMicro2 = idPos;
        else if (idTipoPos == AsuntoPosicionRA.ID_POS_MICRO3)
            idPosMicro3 = idPos;
        else if (idTipoPos == AsuntoPosicionRA.ID_POS_MICRO4)
            idPosMicro4 = idPos;
    }
    
    if (err == null && (idPosAero != null || idPosMicro1 != null || idPosMicro2 != null || idPosMicro3 != null || idPosMicro4 != null)) {
        if (AsuntoPosicionRA.updateAsuntoPosicionRA(idAsunto, null, null, null, null, null, idAsunto, idPosAero, idPosMicro1, idPosMicro2, idPosMicro3, idPosMicro4, null) == 0)
            err = "Fallo insertando posiciones de ruido acústico";
    }
    
    return err;
}

// Centra los datos de una tabla
public void Centrar (JTable tabla,int i, int f){
    DefaultTableCellRenderer c = new DefaultTableCellRenderer();
    c.setHorizontalAlignment(SwingConstants.CENTER);
    for (int columna=i;columna<=f;columna++){
        tabla.getColumnModel().getColumn(columna).setCellRenderer(c);     
    }
} 

public void Limpiar(){
    // Identificacion
    this.jTextField1.setText(null);
    this.jTextField5.setText(null);
    this.jTextField8.setText(null);
    // Descripcion
    this.jcbNorma.setSelectedIndex(-1);
    this.jComboBox2.setSelectedIndex(0);
    this.jComboBox5.setSelectedIndex(0);
    this.jComboBox12.setSelectedIndex(0);
    this.jTextField6.setText(null);
    // Cliente
    dtm1.setRowCount(0);
    this.jComboBox13.setSelectedIndex(0);
    this.jComboBox3.setSelectedIndex(0);
    // AG
    this.jComboBox7.setSelectedIndex(0);
    this.jComboBox10.setSelectedIndex(0);
    // Posicion
    this.jComboBox11.setSelectedIndex(0);
    this.jComboBox8.setSelectedIndex(0);   
    // Campaña
    this.jTextField9.setText(null);
    this.jTextField11.setText(null);
    this.jTextField19.setText(null);
    this.jTextField18.setText(null);  
    
    this.jbCargarFicPosRA.setEnabled(false);
    this.jtPosicionesRA.setEnabled(false);
    
    DefaultTableModel dtmPosicionesRA = (DefaultTableModel) this.jtPosicionesRA.getModel();
    dtmPosicionesRA.setRowCount(0);
}

public void LimpiarC(){
    dtm1.setRowCount(0);
}
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox jComboBox10;
    private javax.swing.JComboBox jComboBox11;
    private javax.swing.JComboBox jComboBox12;
    private javax.swing.JComboBox jComboBox13;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JComboBox jComboBox5;
    private javax.swing.JComboBox jComboBox7;
    private javax.swing.JComboBox jComboBox8;
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
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JButton jbCargarFicPosRA;
    private javax.swing.JButton jbVerFormatoPosRA;
    private javax.swing.JButton jbVisualizarPosRA;
    private javax.swing.JComboBox jcbAsunto;
    private javax.swing.JComboBox jcbNorma;
    private javax.swing.JLabel jlCargarFicPosRA;
    private javax.swing.JLabel jlVerFormatoPosRA;
    private javax.swing.JLabel jlVisualizarPosRA;
    private javax.swing.JPanel jpBotonesPosRA;
    private javax.swing.JPanel jpContGraficaPosRA;
    private javax.swing.JPanel jpDatosPosicionesRA;
    private javax.swing.JPanel jpGraficaPosRA;
    private javax.swing.JPanel jpInfoPosRA;
    private javax.swing.JPanel jpPosicionesRA;
    private javax.swing.JScrollPane jspPosicionesRA;
    private javax.swing.JTable jtPosicionesRA;
    // End of variables declaration//GEN-END:variables
    // Objetos
    private Asunto A=new Asunto();
    private Norma NR=new Norma();
    private Responsable R=new Responsable();
    private Cliente C=new Cliente();
    private Aerogenerador AG=new Aerogenerador();
    private Parque PE=new Parque();
    private Numero N=new Numero();
    
    private boolean cambio;   
    private int Id;
    private DefaultTableModel dtm1; 
}
