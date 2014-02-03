package userinterfaces;

import Informacion.Formato_PEGUI;
import RA.Aerogenerador;
import RA.Asunto;
import RA.AsuntoPosicionRA;
import RA.AsuntoRA;
import RA.Cliente;
import RA.FicheroRA;
import RA.Global;
import RA.Norma;
import RA.Numero;
import RA.Parque;
import RA.Responsable;
import RA.Login;
import RA.NormaRA;
import RA.PosicionRA;
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
import java.awt.Toolkit;
import java.awt.geom.Rectangle2D;
import java.awt.print.PrinterException;
import java.sql.SQLException;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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


public class AsuntoGUI extends JDialog {
    public AsuntoGUI(java.awt.Frame parent,int tipo) {
       super(parent, true);
       initComponents();
       this.tipo_asunto=tipo;
       this.dtm3=(DefaultTableModel)this.jTable3.getModel();
       this.dtm1=(DefaultTableModel)this.jTable1.getModel();
       this.jPanel11.setEnabled(false);
       this.jPanel7.setEnabled(false);
    }

    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpInfoPosRA = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
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
        jPanel14 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jcbNorma = new javax.swing.JComboBox();
        jTextField1 = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        jTextField20 = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jTextField22 = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jComboBox10 = new javax.swing.JComboBox();
        jLabel57 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox();
        jPanel13 = new javax.swing.JPanel();
        jTextField4 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jLabel58 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jComboBox11 = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jComboBox9 = new javax.swing.JComboBox();
        jButton9 = new javax.swing.JButton();
        jLabel60 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jTextField13 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jTextField14 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        jComboBox6 = new javax.swing.JComboBox();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jComboBox7 = new javax.swing.JComboBox();
        jLabel52 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jTextField18 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox();
        jLabel13 = new javax.swing.JLabel();
        jComboBox8 = new javax.swing.JComboBox();
        jButton7 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jLabel59 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jTextField15 = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jTextField16 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jTextField17 = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        jTextField21 = new javax.swing.JTextField();
        jPanel16 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel12 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jLabel31 = new javax.swing.JLabel();
        jButton12 = new javax.swing.JButton();
        jLabel53 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jLabel29 = new javax.swing.JLabel();
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
        closeButton3 = new javax.swing.JButton();
        closeButton2 = new javax.swing.JButton();
        jLabel55 = new javax.swing.JLabel();

        jLabel6.setText("-  El fichero de posiciones debe ser un fichero de texto plano.");

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
                            .addComponent(jLabel6)
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
                .addComponent(jLabel6)
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
        setTitle("ASUNTO");
        setBackground(new java.awt.Color(204, 204, 204));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setIconImage(new ImageIcon(RA.Global.RUTA_IMAGENES + "GRA.png").getImage());
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

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTabbedPane1.setBackground(new java.awt.Color(102, 102, 102));
        jTabbedPane1.setForeground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("  Codigo:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("  Norma:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("  Responsable:");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));
        try{
            ArrayList<String> resposables=R.getResponsablesActivo();
            int n=resposables.size();
            for(int i=0;i<n;i++){
                String dato=resposables.get(i);
                this.jComboBox2.insertItemAt(dato,i+1);
            }
            this.jComboBox2.setSelectedIndex(0);
        }catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Fallo al consultar la base de datos", "ERROR", JOptionPane.ERROR_MESSAGE);
        }

        jLabel10.setText("  Periodicidad [dias]:");

        jTextField6.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                ValidarPeriodocidad(evt);
            }
        });

        Asunto A=new Asunto();
        try{
            this.jTextField1.setText(A.getNuevoCodigo());
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(204, 204, 204));
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EditarCodigo(evt);
            }
        });
        jTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                ValidarCodigo(evt);
            }
        });

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel38.setText("  Nombre:");

        jTextField20.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField20.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                Limpiar_Ncorto(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                NombreCorto(evt);
            }
        });

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(153, 153, 153));
        jLabel36.setText("*");

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(153, 153, 153));
        jLabel40.setText("*");

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel41.setText("  Nombre corto:");

        jTextField22.setEditable(false);
        jTextField22.setBackground(new java.awt.Color(204, 204, 204));
        jTextField22.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EditarNC(evt);
            }
        });
        jTextField22.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                ValidarNCorto(evt);
            }
        });

        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(153, 153, 153));
        jLabel48.setText("*");

        jLabel49.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(153, 153, 153));
        jLabel49.setText("*");

        jLabel56.setText("  Idioma:");

        jComboBox10.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jComboBox10.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Castellano", "Inglés" }));

        jLabel57.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(RA.Global.RUTA_IMAGENES + "user.png" )));

        jLabel5.setForeground(new java.awt.Color(153, 153, 153));
        jLabel5.setText("*");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(97, 97, 97)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel56)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jcbNorma, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jComboBox10, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel49, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel40, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel48, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 9, Short.MAX_VALUE))
                    .addComponent(jLabel36)
                    .addComponent(jLabel5))
                .addContainerGap(162, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel36))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel40)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel49)
                            .addComponent(jLabel41))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel48)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3)))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jcbNorma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel2))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBox10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel10))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(159, 159, 159)
                        .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(213, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("  Empresa:");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));
        try{
            ArrayList<String> clientes=C.getClientes();
            int n=clientes.size();
            for(int i=0;i<n;i++){
                String dato=clientes.get(i);
                this.jComboBox3.insertItemAt(dato,i+1);
            }
            this.jComboBox3.setSelectedIndex(0);
        }catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Fallo al consultar la base de datos", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        jComboBox3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CambioCliente(evt);
            }
        });

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextField4.setEditable(false);
        jTextField4.setBackground(new java.awt.Color(204, 204, 204));

        jTextField2.setEditable(false);
        jTextField2.setBackground(new java.awt.Color(204, 204, 204));

        jTextField3.setEditable(false);
        jTextField3.setBackground(new java.awt.Color(204, 204, 204));

        jLabel58.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(RA.Global.RUTA_IMAGENES + "user_suit.png" )));

        jLabel42.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(RA.Global.RUTA_IMAGENES + "telephone.png" )));

        jLabel7.setText("  Teléfono: ");

        jLabel11.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(RA.Global.RUTA_IMAGENES + "email.png" )));

        jLabel45.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(RA.Global.RUTA_IMAGENES + "vcard.png" )));

        jLabel46.setText("  Nombre: ");

        jLabel47.setText("   Mail: ");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel46)
                            .addComponent(jLabel47))
                        .addGap(121, 121, 121)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)))
                    .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel58, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(39, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel44.setText("  Contacto:");

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Personas de Contacto"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CargarPersona(evt);
            }
        });
        jScrollPane3.setViewportView(jTable3);

        jComboBox11.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));
        jComboBox11.setEnabled(false);

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(RA.Global.RUTA_IMAGENES + "resultset_down.png" )));
        jButton1.setEnabled(false);
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AddContacto(evt);
            }
        });

        jButton13.setBackground(new java.awt.Color(255, 255, 255));
        jButton13.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(RA.Global.RUTA_IMAGENES + "resultset_up.png" )));
        jButton13.setEnabled(false);
        jButton13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BorrarContacto(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addGap(50, 50, 50)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPanel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(192, 192, 192)
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jComboBox3, 0, 280, Short.MAX_VALUE)
                                        .addComponent(jComboBox11, 0, 280, Short.MAX_VALUE)
                                        .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addGap(75, 75, 75))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44)
                    .addComponent(jComboBox11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 483, Short.MAX_VALUE)
                .addGap(214, 214, 214))
        );

        jTabbedPane1.addTab("", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Modelo:");

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));
        try{
            ArrayList<String> modelos=AG.getModelos();
            int n=modelos.size();
            for(int i=0;i<n;i++){
                String dato=modelos.get(i);
                this.jComboBox4.insertItemAt(dato,i+1);
            }
            this.jComboBox4.setSelectedIndex(0);
        }catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Fallo al consultar la base de datos", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        jComboBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NuevoModeloAero(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Hb [m]:");

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setText("+");
        jButton2.setToolTipText("Nuevo modelo");
        jButton2.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                HabilitarDatos(evt);
            }
        });

        jComboBox9.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));
        jComboBox9.setEnabled(false);
        jComboBox9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CargarDatosAero(evt);
            }
        });

        jButton9.setBackground(new java.awt.Color(255, 255, 255));
        jButton9.setText("-");
        jButton9.setToolTipText("Volver");
        jButton9.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LimpiarAero(evt);
            }
        });

        jLabel60.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(RA.Global.RUTA_IMAGENES + "aero.png" )));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox9, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(82, 82, 82)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jButton9)
                    .addComponent(jButton2)
                    .addComponent(jLabel9)
                    .addComponent(jComboBox9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel20.setText("  Modelo:");

        jTextField13.setEditable(false);
        jTextField13.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel21.setText("  Hb (m):");

        jTextField14.setEditable(false);
        jTextField14.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField14.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                ValidarHb(evt);
            }
        });

        jLabel16.setText("  Potencia Nominal   [kW] :");

        jLabel17.setText("  Velocidad de arranque / Vin [m/s] :");

        jLabel18.setText("  Velocidad de corte / Vcut [m/s] :");

        jLabel19.setText("  Lineas Evacuación: ");

        jLabel15.setText("  Dn (m):");

        jTextField11.setEditable(false);
        jTextField11.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField11.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                ValidarDn(evt);
            }
        });

        jTextField8.setEditable(false);
        jTextField8.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel14.setText("  Fabricante:");

        jTextField10.setEditable(false);
        jTextField10.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField10.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                ValidarPotencia(evt);
            }
        });

        jTextField9.setEditable(false);
        jTextField9.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField9.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                ValidarVin(evt);
            }
        });

        jTextField12.setEditable(false);
        jTextField12.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField12.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                ValidarVcut(evt);
            }
        });

        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "1", "2" }));
        jComboBox6.setEnabled(false);

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(153, 153, 153));
        jLabel26.setText("*");

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(153, 153, 153));
        jLabel27.setText("*");

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(153, 153, 153));
        jLabel28.setText("*");

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(153, 153, 153));
        jLabel37.setText("*");

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(RA.Global.RUTA_IMAGENES + "Guardar.png" )));
        jButton3.setToolTipText("Guardar Aerogenerador");
        jButton3.setEnabled(false);
        jButton3.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                GuardarAero(evt);
            }
        });

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(153, 153, 153));
        jLabel32.setText("*");

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(153, 153, 153));
        jLabel33.setText("*");

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(153, 153, 153));
        jLabel34.setText("*");

        jLabel35.setText("  Control de Potencia: ");

        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "Cambio de Paso", "Pérdida Aerodinámica" }));
        jComboBox7.setEnabled(false);

        jLabel52.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(153, 153, 153));
        jLabel52.setText("*");

        jLabel54.setText("  Velocidad Nominal   [m/s] :");

        jTextField18.setEditable(false);
        jTextField18.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField18.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                ValidarVn(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel15)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(jLabel16))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 369, Short.MAX_VALUE)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jTextField13, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                                    .addComponent(jTextField8)
                                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField14, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jTextField11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel28)
                                .addComponent(jLabel26)
                                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addContainerGap())
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                            .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 374, Short.MAX_VALUE)
                            .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(8, 8, 8))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                            .addComponent(jLabel19)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 475, Short.MAX_VALUE)
                            .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(14, 14, 14))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel18)
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel54))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 388, Short.MAX_VALUE)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel34, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jTextField12, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jTextField9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(11, 11, 11))
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addComponent(jLabel14)
                            .addContainerGap()))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26)
                            .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21)))
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(jLabel15)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(jLabel16)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel54)
                    .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addGap(11, 11, 11)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(jLabel18)
                    .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel52)
                    .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel35))
                .addGap(81, 81, 81)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("", jPanel3);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("Parque:");

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));
        try{
            ArrayList<String> parques=PE.getParques();
            int n=parques.size();
            for(int i=0;i<n;i++){
                String dato=parques.get(i);
                this.jComboBox5.insertItemAt(dato,i+1);
            }
            this.jComboBox5.setSelectedIndex(0);
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Fallo al consultar la base de datos", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        jComboBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NuevoParque(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("Posicion: ");

        jComboBox8.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));
        jComboBox8.setEnabled(false);

        jButton7.setBackground(new java.awt.Color(255, 255, 255));
        jButton7.setText("+");
        jButton7.setToolTipText("Nuevo Parque");
        jButton7.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EditarParque(evt);
            }
        });

        jButton10.setBackground(new java.awt.Color(255, 255, 255));
        jButton10.setText("-");
        jButton10.setToolTipText("Volver");
        jButton10.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LimpiarParque(evt);
            }
        });

        jLabel59.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(RA.Global.RUTA_IMAGENES + "parque.png" )));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox5, 0, 272, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton10)
                            .addComponent(jButton7)
                            .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel22.setText("  Nombre:");

        jTextField5.setEditable(false);

        jLabel23.setText("  Población:");

        jTextField15.setEditable(false);

        jLabel24.setText("  Provincia:");

        jTextField16.setEditable(false);

        jLabel25.setText("  Pais:");

        jTextField17.setEditable(false);

        jLabel39.setText("  Datum:");

        jTextField21.setEditable(false);

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "[x]", "[y]", "[z]", "[Dn]", "[Hb]"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(30);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(50);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(50);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(50);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(20);
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(20);
        }

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton5.setBackground(new java.awt.Color(255, 255, 255));
        jButton5.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(RA.Global.RUTA_IMAGENES + "OpenFolder.gif" )));
        jButton5.setToolTipText("Cargar Fichero");
        jButton5.setEnabled(false);
        jButton5.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CargarPosiciones(evt);
            }
        });

        jLabel30.setText("LOAD FICHERO");

        jButton8.setBackground(new java.awt.Color(255, 255, 255));
        jButton8.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(RA.Global.RUTA_IMAGENES + "Information.png" )));
        jButton8.setToolTipText("Ver Formato de Fichero\n");
        jButton8.setEnabled(false);
        jButton8.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                VerInfo(evt);
            }
        });

        jLabel31.setText("VER FORMATO");

        jButton12.setBackground(new java.awt.Color(255, 255, 255));
        jButton12.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(RA.Global.RUTA_IMAGENES + "printer.png" )));
        jButton12.setToolTipText("Imprimir posiciones");
        jButton12.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Print(evt);
            }
        });

        jLabel53.setText("IMPRIMIR POSICIONES");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jButton8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)))
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel30))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel53)
                            .addComponent(jLabel31))))
                .addGap(18, 18, 18))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                    .addComponent(jLabel53))
                .addContainerGap())
        );

        jButton4.setBackground(new java.awt.Color(255, 255, 255));
        jButton4.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(RA.Global.RUTA_IMAGENES + "Guardar.png" )));
        jButton4.setToolTipText("Guardar Parque");
        jButton4.setEnabled(false);
        jButton4.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                GuardarParque(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel29.setText("Aerogeneradores :");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel29)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 102, Short.MAX_VALUE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(jLabel23)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(jLabel24)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(42, 42, 42)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel39)
                            .addComponent(jLabel25))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25)
                    .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel39)
                    .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(229, 229, 229))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("", jPanel4);

        jpPosicionesRA.setBackground(new java.awt.Color(255, 255, 255));

        jpDatosPosicionesRA.setBackground(new java.awt.Color(255, 255, 255));
        jpDatosPosicionesRA.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jtPosicionesRA.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "[x]", "[y]", "[z]", "Tipo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jspPosicionesRA.setViewportView(jtPosicionesRA);
        if (jtPosicionesRA.getColumnModel().getColumnCount() > 0) {
            jtPosicionesRA.getColumnModel().getColumn(0).setPreferredWidth(50);
            jtPosicionesRA.getColumnModel().getColumn(1).setPreferredWidth(50);
            jtPosicionesRA.getColumnModel().getColumn(2).setPreferredWidth(50);
            jtPosicionesRA.getColumnModel().getColumn(3).setPreferredWidth(40);
        }

        jpBotonesPosRA.setBackground(new java.awt.Color(255, 255, 255));
        jpBotonesPosRA.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jbCargarFicPosRA.setBackground(new java.awt.Color(255, 255, 255));
        jbCargarFicPosRA.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(RA.Global.RUTA_IMAGENES + "OpenFolder.gif" )));
        jbCargarFicPosRA.setToolTipText("Cargar Fichero");
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
            .addGap(0, 649, Short.MAX_VALUE)
        );
        jpGraficaPosRALayout.setVerticalGroup(
            jpGraficaPosRALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 307, Short.MAX_VALUE)
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
                .addGroup(jpDatosPosicionesRALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpBotonesPosRA, 0, 0, Short.MAX_VALUE)
                    .addComponent(jspPosicionesRA, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
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

        closeButton3.setBackground(new java.awt.Color(255, 255, 255));
        closeButton3.setText("CANCELAR");
        closeButton3.setMargin(new java.awt.Insets(2, 2, 2, 2));
        closeButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cancelar(evt);
            }
        });

        closeButton2.setBackground(new java.awt.Color(255, 255, 255));
        closeButton2.setText("GUARDAR");
        closeButton2.setToolTipText("Finalizar");
        closeButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                GuardarAsunto(evt);
            }
        });
        closeButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarAsunto(evt);
            }
        });

        jLabel55.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(102, 102, 102));
        jLabel55.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel55.setText("INFORMACIÓN DE UN NUEVO PROYECTO");
        jLabel55.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addComponent(closeButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(closeButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(194, 194, 194)
                        .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel55)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 528, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(closeButton2)
                    .addComponent(closeButton3))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(743, 635));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void GuardarAsunto(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GuardarAsunto
    
}//GEN-LAST:event_GuardarAsunto

    private void Cancelar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cancelar
        dispose();
}//GEN-LAST:event_Cancelar

private void Cerrar(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_Cerrar
    dispose();
}//GEN-LAST:event_Cerrar

private void CargarPosiciones(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CargarPosiciones
String u=Login.getUsuario();
if (this.jButton5.isEnabled()){
   JFileChooser fileChooser = new JFileChooser("Z:\\"+u+"\\PRIVADA\\CALIDAD\\ASUNTOS\\");
   int seleccion = fileChooser.showOpenDialog(this);
   if (seleccion == JFileChooser.APPROVE_OPTION){
        File fichero = fileChooser.getSelectedFile();
        String Ruta=fichero.getPath();
        try {
            FicheroRA F=new FicheroRA();
            ArrayList <String []> pos=F.loadPosiciones(Ruta);
            if (!pos.get(0)[0].equals("mal")){
                dtm1.setRowCount(pos.size());
                for (int f=0;f<pos.size();f++){
                    for (int c=0;c<6;c++){
                        this.jTable1.setValueAt(pos.get(f)[c], f, c);
                    }
                }
                Centrar(this.jTable1,0,5);
            } else {
                JOptionPane.showMessageDialog(this, "Formato de fichero no válido","ADVERTENCIA",JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e){
            JOptionPane.showMessageDialog(this, "Error al leer las posiciones","ERROR",JOptionPane.ERROR_MESSAGE);
        }
  }
}
}//GEN-LAST:event_CargarPosiciones

private void EditarParque(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EditarParque
    this.jPanel11.setEnabled(true);
    this.jComboBox5.setSelectedIndex(0);
    this.jComboBox5.setEnabled(false);
    this.jComboBox8.setEnabled(false);
    HabilitarDatosParque(true);
    this.jButton4.setEnabled(true); 
    this.jButton5.setEnabled(true); 
    this.jButton8.setEnabled(true);
}//GEN-LAST:event_EditarParque

private void NuevoParque(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NuevoParque
    this.jComboBox8.removeAllItems();
    LimpiarDatosParque();
    if (this.jComboBox5.getSelectedIndex()!=0){
        String parque=(String)this.jComboBox5.getSelectedItem();
        try{
          
            int idp=PE.getId(parque);
            ArrayList <String []> posiciones=PE.getPosiciones(idp);
            int n=posiciones.size();
            for(int i=0;i<n;i++){
                String dato=posiciones.get(i)[0];
                this.jComboBox8.insertItemAt(dato,i);
            }
            this.jComboBox8.setSelectedIndex(0);
            this.jComboBox8.setEnabled(true);
            ArrayList <String> DatosP=PE.getParque(idp);
            this.jTextField5.setText(parque);
            if (DatosP.size()!=0){
                this.jTextField15.setText(DatosP.get(2));
                this.jTextField16.setText(DatosP.get(3));
                this.jTextField17.setText(DatosP.get(4));
                this.jTextField21.setText(DatosP.get(5));
            }
            dtm1.setRowCount(n);
            for (int j=0;j<n;j++){
                for (int h=0;h<6;h++){
                    if (posiciones.get(j)[h]!=null){
                        this.jTable1.setValueAt(posiciones.get(j)[h], j, h);
                    }
                }
                
            }
            Centrar(this.jTable1,0,5);
   }catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Error al consultar parque","ERROR",JOptionPane.ERROR_MESSAGE);
   }
}
}//GEN-LAST:event_NuevoParque

// Centra los datos de una tabla
public void Centrar (JTable tabla,int i, int f){
    DefaultTableCellRenderer c = new DefaultTableCellRenderer();
    c.setHorizontalAlignment(SwingConstants.CENTER);
    for (int columna=i;columna<=f;columna++){
        tabla.getColumnModel().getColumn(columna).setCellRenderer(c);     
    }
}

private void HabilitarDatosAero(boolean accion){
    this.jTextField13.setEditable(accion);
    this.jTextField14.setEditable(accion);
    this.jTextField8.setEditable(accion);
    this.jTextField18.setEditable(accion);
    this.jTextField11.setEditable(accion);
    this.jTextField10.setEditable(accion);
    this.jTextField9.setEditable(accion);
    this.jTextField12.setEditable(accion);
    this.jComboBox6.setEnabled(accion);
    this.jComboBox7.setEnabled(accion);
}

private void LimpiarDatosAero(){
    
    this.jTextField13.setText("");
    this.jTextField14.setText("");
    this.jTextField11.setText("");
    this.jTextField8.setText("");
    this.jTextField18.setText("");
    this.jTextField10.setText("");
    this.jTextField9.setText("");
    this.jTextField12.setText("");
    this.jComboBox6.setSelectedIndex(0);
    this.jComboBox7.setSelectedIndex(0);
    HabilitarDatosAero(false);
}



private void CargarDatosAero(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CargarDatosAero
   if ((this.jComboBox9.isEnabled()) && (this.jComboBox9.getSelectedIndex()!=0) && (this.jComboBox9.getSelectedItem()!=null)){
       String modelo=this.jComboBox4.getSelectedItem().toString();
       double hb=Double.parseDouble(this.jComboBox9.getSelectedItem().toString());
       try {
          
           int aerogenerador=AG.getId(modelo, hb);
           ArrayList <String> Datos=AG.getAero(aerogenerador);
           this.jTextField13.setText(modelo);
           this.jTextField14.setText(""+hb);
           this.jTextField8.setText(Datos.get(3));
           this.jTextField11.setText(Datos.get(4));
           this.jTextField10.setText(Datos.get(5));
           this.jTextField9.setText(Datos.get(6));
           this.jTextField12.setText(Datos.get(7));
           if (Integer.parseInt(Datos.get(8))==1){
                this.jComboBox6.setSelectedIndex(1);
           } else {
                this.jComboBox6.setSelectedIndex(2);
           }
           if (Integer.parseInt(Datos.get(9))==1){
                this.jComboBox7.setSelectedIndex(1);
           } else {
                this.jComboBox7.setSelectedIndex(2);
           }
           if (Datos.get(10)!=null){
                this.jTextField18.setText(Datos.get(10));
           } 
         
          
      }catch (SQLException e){
        JOptionPane.showMessageDialog(this, "Error al consultar el aerogenerador","ERROR",JOptionPane.ERROR_MESSAGE);
      }
} else {
     LimpiarDatosAero();
}

}//GEN-LAST:event_CargarDatosAero

private void LimpiarDatosParque(){
    
    this.jTextField5.setText("");
    this.jTextField15.setText("");
    this.jTextField16.setText("");
    this.jTextField17.setText("");
    this.jTextField21.setText("");
    HabilitarDatosParque(false);
    dtm1.setRowCount(0);
}

private void HabilitarDatosParque(boolean accion){
    this.jTextField5.setEditable(accion);
    this.jTextField15.setEditable(accion);
    this.jTextField16.setEditable(accion);
    this.jTextField17.setEditable(accion);
    this.jTextField21.setEditable(accion);
}

private void NuevoModeloAero(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NuevoModeloAero
    this.jComboBox9.removeAllItems();
    this.jComboBox9.insertItemAt("", 0);
    LimpiarDatosAero();
    if (this.jComboBox4.getSelectedIndex()!=0){ // Se cargan las alturas de buje
       String modelo=(String)this.jComboBox4.getSelectedItem();
       try{
        
          int n=(AG.getHbs(modelo)).size();
          for(int i=0;i<n;i++){
             String dato=(String)(AG.getHbs(modelo)).get(i);
             this.jComboBox9.insertItemAt(dato,i+1);
         }
         this.jComboBox9.setSelectedIndex(0);
         this.jComboBox9.setEnabled(true);
      }catch (SQLException ex) {
         JOptionPane.showMessageDialog(this, "Error al consultar el aerogenerador","ERROR",JOptionPane.ERROR_MESSAGE);
      }
      this.jComboBox9.setEnabled(true);
    } else {
      this.jComboBox9.setEnabled(false);
    }
}//GEN-LAST:event_NuevoModeloAero

private void HabilitarDatos(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HabilitarDatos
    this.jPanel7.setEnabled(true);
    this.jComboBox4.setSelectedIndex(0);
    this.jComboBox4.setEnabled(false);
    this.jComboBox9.setSelectedIndex(0);
    this.jComboBox9.setEnabled(false);
    HabilitarDatosAero(true);
    this.jButton3.setEnabled(true);
    
}//GEN-LAST:event_HabilitarDatos

private void GuardarAero(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GuardarAero
if (this.jButton3.isEnabled()){
    if ((this.jTextField13.getText().length()==0) |(this.jTextField14.getText().length()==0)|(this.jTextField10.getText().length()==0)|(this.jTextField9.getText().length()==0)|(this.jTextField12.getText().length()==0)| (this.jTextField11.getText().length()==0)| (this.jComboBox6.getSelectedIndex()==0) | (this.jComboBox7.getSelectedIndex()==0)){
        JOptionPane.showMessageDialog(this,"Por favor, introduzca los campos obligatorios (*)","ADVERTENCIA",JOptionPane.WARNING_MESSAGE);
    } else {
        
            String modelo=this.jTextField13.getText();
            double hb=Double.parseDouble(this.jTextField14.getText());
            int lineas=Integer.parseInt((String)this.jComboBox6.getSelectedItem());
            int reg=1;
            if (this.jComboBox7.getSelectedIndex()==2){
                reg=0;
            }
            String fabricante=null;
            double Vn=0;
            if (this.jTextField18.getText().length()!=0){
                Vn=Double.parseDouble(this.jTextField18.getText());
            }
            double dn=Double.parseDouble(this.jTextField11.getText());
            double pn=Double.parseDouble(this.jTextField10.getText());
            double vin=Double.parseDouble(this.jTextField9.getText());
            double vcut=Double.parseDouble(this.jTextField12.getText());
            if (this.jTextField8.getText().length()!=0){
                fabricante=this.jTextField8.getText();
            }
            try {
    
                boolean salida=AG.NuevoAerogenerador(modelo, fabricante, hb,dn, pn,  vin, vcut, lineas, reg,Vn);
                if (!salida){
                    this.jComboBox4.setEnabled(true);
                    this.jButton3.setEnabled(false);
                    JOptionPane.showMessageDialog(this, "Aerogenerador insertado correctamente","INFORMACIÓN",JOptionPane.INFORMATION_MESSAGE);
                    this.jComboBox4.addItem(modelo);
                    this.jComboBox4.setSelectedItem(modelo);
                    this.jComboBox9.setSelectedItem(String.valueOf(hb));
                    this.jButton2.setEnabled(false);
                    this.jButton9.setEnabled(false);
                }else{
                    JOptionPane.showMessageDialog(this, "El aerogenerador ya existe","MENSAJE DE ERROR",JOptionPane.ERROR_MESSAGE);   
                }
            } catch (SQLException e) {
                    JOptionPane.showMessageDialog(this, "Fallo en la inserción","ERROR",JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}//GEN-LAST:event_GuardarAero

private void ValidarHb(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ValidarHb

if (!N.isNumeric(this.jTextField14.getText()) & (this.jTextField14.getText().length()!=0)){
   this.jTextField14.setText(null);
   JOptionPane.showMessageDialog(this, "Hb debe ser un campo numérico","ADVERTENCIA",JOptionPane.WARNING_MESSAGE);
}
}//GEN-LAST:event_ValidarHb

private void ValidarDn(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ValidarDn

if (!N.isNumeric(this.jTextField11.getText()) & (this.jTextField11.getText().length()!=0)){
    this.jTextField11.setText(null);
    JOptionPane.showMessageDialog(this, "Dn debe ser un campo numérico","ADVERTENCIA",JOptionPane.WARNING_MESSAGE);
}
}//GEN-LAST:event_ValidarDn

private void GuardarParque(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GuardarParque
if (this.jButton4.isEnabled()){
    boolean ok=true;
    boolean repetidos=false;
    
    String nombre_parque=null;
    String poblacion=null;
    String provincia=null;
    String pais=null;
    String datum=null;
    ArrayList <String []> posiciones_pe=new ArrayList<String []>(); 
    
    if (this.jTextField5.getText().length()==0){
         nombre_parque=this.jTextField5.getText();
    } else{
        ok=false;
    }
    if (this.jTextField15.getText().length()!=0){
         poblacion=this.jTextField15.getText();
    } else{
        ok=false;
    }
    if (this.jTextField16.getText().length()!=0){
        provincia=this.jTextField16.getText();
    } else{
        ok=false;
    }
    if (this.jTextField17.getText().length()!=0){
        pais=this.jTextField17.getText();
    } else{
        ok=false;
    }
    if (this.jTextField21.getText().length()!=0){
       datum=this.jTextField21.getText();
    }
    if (dtm1.getRowCount()!=0) {
       
        // Se cargan las posiciones
        
        int n=dtm1.getRowCount();
        for (int f=0;f<n;f++){
             String[] aux=new String [6];
             for (int dato=0;dato<6;dato++){
                aux[dato]=null;
             }
             if ((this.jTable1.getValueAt(f, 0)!=null) && (this.jTable1.getValueAt(f, 1)!=null)&& (this.jTable1.getValueAt(f, 2)!=null) ){
                aux[0]=this.jTable1.getValueAt(f, 0).toString();  
                for (int i=0;i<f;i++){
                    if (aux[0].equals(this.jTable1.getValueAt(i, 0).toString())){
                        repetidos=true;
                    }
                }
                aux[1]=this.jTable1.getValueAt(f, 1).toString();  
                aux[2]=this.jTable1.getValueAt(f, 2).toString();
                aux[3]=this.jTable1.getValueAt(f, 3).toString(); 
                aux[4]=this.jTable1.getValueAt(f, 4).toString(); 
                aux[5]=this.jTable1.getValueAt(f, 5).toString(); 
             } else {
                ok=false;
             }
             posiciones_pe.add(aux);
        }
    } else{
        ok=false;
    }
    if ((ok) && (!repetidos)){
        try{
            boolean salida=PE.NuevoParque(nombre_parque,poblacion,provincia,pais,datum,posiciones_pe);
            if (!salida){
                this.jComboBox5.setEnabled(true);
                JOptionPane.showMessageDialog(this, "Parque insertado correctamente","INFORMACIÓN",JOptionPane.INFORMATION_MESSAGE);
                System.out.println("Añado al parque "+nombre_parque);
                this.jComboBox5.addItem(nombre_parque);
                this.jComboBox5.setSelectedItem(nombre_parque);
                System.out.println("Selecciono "+nombre_parque);
                this.jButton4.setEnabled(false);
                this.jButton7.setEnabled(false);
                this.jButton10.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(this, "Fallo en la inserción. El parque ya existe","ERROR",JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Fallo en la inserción","ERROR",JOptionPane.ERROR_MESSAGE);
        }
    } else{
        if (!ok){
            JOptionPane.showMessageDialog(this, "Faltan datos","ADVERTENCIA",JOptionPane.WARNING_MESSAGE);
        } else{
            JOptionPane.showMessageDialog(this, "Identificador de posición repetido","ADVERTENCIA",JOptionPane.WARNING_MESSAGE);
        }
    }
     
 }
   
}//GEN-LAST:event_GuardarParque

private void ValidarPotencia(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ValidarPotencia
if (this.jTextField10.getText()!=null){
    if (!N.isNumeric(this.jTextField10.getText()) & (this.jTextField10.getText().length()!=0)){
        this.jTextField10.setText(null);
        JOptionPane.showMessageDialog(this, "Potencia debe ser un campo numérico","ADVERTENCIA",JOptionPane.WARNING_MESSAGE);
    }
}
}//GEN-LAST:event_ValidarPotencia

private void ValidarVin(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ValidarVin
if (this.jTextField9.getText()!=null){
    if (!N.isNumeric(this.jTextField9.getText()) & (this.jTextField9.getText().length()!=0)){
        this.jTextField9.setText(null);
        JOptionPane.showMessageDialog(this, "Velocidad de arranque debe ser un campo numérico","ADVERTENCIA",JOptionPane.WARNING_MESSAGE);
    }
}
}//GEN-LAST:event_ValidarVin

private void ValidarVcut(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ValidarVcut
if (this.jTextField12.getText()!=null){
    if (!N.isNumeric(this.jTextField12.getText()) & (this.jTextField12.getText().length()!=0)){
        this.jTextField12.setText(null);
        JOptionPane.showMessageDialog(this, "Velocidad de corte debe ser un campo numérico","ADVERTENCIA",JOptionPane.WARNING_MESSAGE);
    }
}
}//GEN-LAST:event_ValidarVcut

private void VerInfo(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_VerInfo
    if (this.jButton8.isEnabled()){
        new Formato_PEGUI(this).setVisible(true);
    }
}//GEN-LAST:event_VerInfo

private void LimpiarAero(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LimpiarAero
    this.jPanel7.setEnabled(false);
    this.jComboBox4.setSelectedIndex(0);
    this.jComboBox4.setEnabled(true);
    this.jComboBox9.setSelectedIndex(0);
    this.jComboBox9.setEnabled(true);
    LimpiarDatosAero();
    HabilitarDatosAero(false);
    this.jButton3.setEnabled(false);
}//GEN-LAST:event_LimpiarAero

private void LimpiarParque(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LimpiarParque
    this.jComboBox5.setSelectedIndex(0);
    this.jComboBox5.setEnabled(true);
    LimpiarDatosParque();
    HabilitarDatosParque(false);
    this.jButton4.setEnabled(false); 
    this.jButton5.setEnabled(false); 
    this.jButton8.setEnabled(false);
    this.jPanel11.setEnabled(false);
}//GEN-LAST:event_LimpiarParque

private void Print(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Print
try {
    boolean ok=this.jTable1.print();
    if (ok){
        JOptionPane.showMessageDialog(this, "Impresión Finalizada", "INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);      
    } else{
        JOptionPane.showMessageDialog(this, "Fallo en la impresión", "ERROR", JOptionPane.ERROR_MESSAGE);      
    }
} catch (PrinterException e) {
	JOptionPane.showMessageDialog(this, "Fallo en la impresión", "ERROR", JOptionPane.ERROR_MESSAGE);      
}
}//GEN-LAST:event_Print

private void ValidarVn(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ValidarVn
if (this.jTextField18.getText()!=null){
   
    if (!N.isNumeric(this.jTextField18.getText()) & (this.jTextField18.getText().length()!=0)){
        this.jTextField18.setText(null);
        JOptionPane.showMessageDialog(this, "Vn debe ser un campo numérico","ADVERTENCIA",JOptionPane.WARNING_MESSAGE);
    }
}
}//GEN-LAST:event_ValidarVn

private void ValidarNCorto(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ValidarNCorto
    String campo=this.jTextField22.getText();
    if (campo.length()!=0){
       if (campo.length()>5) {
        this.jTextField22.setText(null);
        JOptionPane.showMessageDialog(this, "Máximo 5 caracteres","ADVERTENCIA",JOptionPane.WARNING_MESSAGE);
       }
    }
}//GEN-LAST:event_ValidarNCorto

private void EditarNC(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EditarNC
    this.jTextField22.setEditable(true);
}//GEN-LAST:event_EditarNC

private void NombreCorto(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_NombreCorto
    String campo=this.jTextField20.getText();
    if (campo.length()!=0){
        campo=campo.replace(" ", "-");
        this.jTextField20.setText(campo);
        String nc=campo.substring(0, 3);
        this.jTextField22.setText(nc);
    }
}//GEN-LAST:event_NombreCorto

private void Limpiar_Ncorto(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Limpiar_Ncorto
    this.jTextField22.setText(null);
    this.jTextField22.setEditable(false);
}//GEN-LAST:event_Limpiar_Ncorto

private void ValidarCodigo(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ValidarCodigo
   
    try {
        String campo=this.jTextField1.getText();
        if (AsuntoRA.existeCodigo(campo)) {
            this.jTextField1.setText(null);
            JOptionPane.showMessageDialog(this, "El código ya existe","ADVERTENCIA",JOptionPane.WARNING_MESSAGE);
        }else{
            if (!A.isCodigo(campo)){
                this.jTextField1.setText(null);
                JOptionPane.showMessageDialog(this, "Formato xxxx-xxx","ADVERTENCIA",JOptionPane.WARNING_MESSAGE);
            }
        }
        
    } catch (SQLException e){
        JOptionPane.showMessageDialog(this, "Fallo al consultar la base de datos","ERROR",JOptionPane.ERROR_MESSAGE);
    }	catch (NoSuchFieldException e) {
        JOptionPane.showMessageDialog(this, "Fallo al consultar la base de datos","ERROR",JOptionPane.ERROR_MESSAGE);
	}
    
}//GEN-LAST:event_ValidarCodigo

private void EditarCodigo(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EditarCodigo
    this.jTextField1.setEditable(true);
}//GEN-LAST:event_EditarCodigo

private void ValidarPeriodocidad(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ValidarPeriodocidad
if (!N.isEntero(this.jTextField6.getText()) & (this.jTextField6.getText().length()!=0)){
    this.jTextField6.setText(null);
    JOptionPane.showMessageDialog(this, "Periodicidad debe ser un campo numérico y entero","ADVERTENCIA",JOptionPane.WARNING_MESSAGE);
}
}//GEN-LAST:event_ValidarPeriodocidad

private void CambioCliente(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CambioCliente
LimpiarContacto();
if (this.jComboBox3.getSelectedIndex()!=0){
   try{
        int id_cliente=C.getId(this.jComboBox3.getSelectedItem().toString());
        ArrayList<String> contactos=C.getContactos(id_cliente,true);
        for (int i=0;i<contactos.size();i++){
            this.jComboBox11.insertItemAt(contactos.get(i),i+1);
        }
        this.jComboBox11.setSelectedIndex(0);
        this.jComboBox11.setEnabled(true);
        this.jButton1.setEnabled(true);
        this.jButton13.setEnabled(true);
    } catch (SQLException e){
        JOptionPane.showMessageDialog(this, "Error al consultar el cliente","ERROR",JOptionPane.ERROR_MESSAGE);
    }    
} 
}//GEN-LAST:event_CambioCliente

private void AddContacto(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddContacto
if (this.jButton1.isEnabled()){
    if (this.jComboBox11.getSelectedIndex()!=0){
        String persona=this.jComboBox11.getSelectedItem().toString();
        this.dtm3.setRowCount(this.dtm3.getRowCount()+1);
        this.dtm3.setValueAt(persona, this.dtm3.getRowCount()-1, 0);
        Centrar(this.jTable3,0,0);
        this.jComboBox11.removeItem(persona);
    }
}
}//GEN-LAST:event_AddContacto

private void CargarPersona(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CargarPersona
   try{
       int fila = this.jTable3.rowAtPoint(evt.getPoint());
       if ((fila > -1)){ 
           String persona=this.jTable3.getValueAt(fila,0).toString();
           int id_persona=C.getIdContacto(persona);
           ArrayList<String> datos=C.getContacto(id_persona);
           this.jTextField2.setText(datos.get(1));
           this.jTextField3.setText(datos.get(3));
           this.jTextField4.setText(datos.get(4));
       }
    } catch (SQLException e){
        JOptionPane.showMessageDialog(this, "Error al consultar el cliente","ERROR",JOptionPane.ERROR_MESSAGE);
    }
}//GEN-LAST:event_CargarPersona

private void BorrarContacto(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BorrarContacto
if (this.jButton13.isEnabled()){
    int fila=jTable3.getSelectedRow();
    int fila2=fila+jTable3.getSelectedRowCount()-1;
    if (fila!=-1){ 
         for (int i=fila;i<=fila2;i++){
             this.jComboBox11.addItem(dtm3.getValueAt(i, 0)); 
             dtm3.removeRow(fila);     
         }
    }
}    
}//GEN-LAST:event_BorrarContacto

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
        Auxiliares.setTitulosJTabbedPane(this.jTabbedPane1, new String[]{"GENERAL", "CLIENTE", "AEROGENERADOR", "PARQUE EÓLICO", "POSICIONES RA"});

        //Maximizamos las pestañas
        Auxiliares.maximizaTitulosJTabbedPane(this.jTabbedPane1);

        //Añadimos la columna Tipo como combo
        TableColumn comboColumn = this.jtPosicionesRA.getColumnModel().getColumn(3);

        JComboBox comboBox = getComboPosicionesRA();

        comboColumn.setCellEditor(new JComboBoxCellEditor(comboBox));

        //Cargamos el combo de normas
        Auxiliares.cargaNormas(this.jcbNorma);
    } catch (SQLException e) {
        MensajeApp.muestraError(this, e, "Fallo al consultar la base de datos");
    } catch (NoSuchFieldException e) {
        MensajeApp.muestraError(this, e, "Fallo añadiendo campo");
    }
}//GEN-LAST:event_formWindowOpened

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
    }	catch (IOException e) {
		MensajeApp.muestraError(this, e, "Error al leer las posiciones");
	} catch (NumberFormatException e) {
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
        
        ChartChangeListener chartListener = new ChartChangeListener() {

                public void chartChanged(ChartChangeEvent arg0) {
                    JFreeChart chart = arg0.getChart();
                    
                    if (chart != null) {
                        chart.removeChangeListener(this);
                        redibujaLimitesMicros(cp);
                        chart.addChangeListener(this);
                    }
                }
            };
        
        cp.getChart().addChangeListener(chartListener);
        
        this.update(this.getGraphics());
        redibujaLimitesMicros(cp);
    }
}//GEN-LAST:event_visualizarPosRA

private void guardarAsunto(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarAsunto
    boolean errorPosRA = false;
    try {
        Integer idNorma = (Integer) ComboBoxObject.getClaveSelCombo(this.jcbNorma);
        
        if (    !this.jTextField1.getText().isEmpty()                       &&
                !this.jTextField20.getText().isEmpty()                      &&
                !this.jTextField22.getText().isEmpty()                      &&
                idNorma != null                                             &&
                this.jComboBox2.getSelectedIndex() != 0                     &&
                this.jComboBox3.getSelectedIndex() != 0                     &&
                this.dtm3.getRowCount() != 0                                &&
                this.jComboBox4.getSelectedIndex() != 0                     &&
                this.jComboBox9.getSelectedIndex() != 0                     &&
                this.jComboBox5.getSelectedIndex() != 0                     &&
                this.jComboBox8.getSelectedItem().toString().length() != 0  &&
                !(errorPosRA = hayErrorPosicionesRA()))
        {
                
                
                codigo = this.jTextField1.getText(); // codigo
                nombre = this.jTextField20.getText(); // nombre
                Nc = this.jTextField22.getText(); // norbre corto
                
                String nombre_responsable = this.jComboBox2.getSelectedItem().toString();
                int p1 = nombre_responsable.indexOf(" ");
                int p2 = nombre_responsable.lastIndexOf(" ");
                if (p2 == 0)
                    responsable = R.getId(nombre_responsable.substring(0, p1), nombre_responsable.substring(p1 + 1));
                else
                    responsable = R.getId(nombre_responsable.substring(0, p1), nombre_responsable.substring(p1 + 1, p2));
                
                cliente = C.getId((String)this.jComboBox3.getSelectedItem()); // cliente
        
                contactosasunto.clear();
                for (int i = 0;i < dtm3.getRowCount(); i++)
                    contactosasunto.add(i, dtm3.getValueAt(i, 0).toString());
        
                String modelo = this.jComboBox4.getSelectedItem().toString();
                double hb = Double.parseDouble(this.jComboBox9.getSelectedItem().toString());
                aero = AG.getId(modelo, hb); // aero
        
                pe = PE.getId((String)this.jComboBox5.getSelectedItem()); // parque

                posicion = (String)this.jComboBox8.getSelectedItem(); // posicion

                idioma = "C";
                if (this.jComboBox10.getSelectedIndex() != 0)
                    idioma="E";

                periodicidad = 0;
                if (!this.jTextField6.getText().isEmpty())
                    periodicidad=Integer.parseInt(this.jTextField6.getText()); // periodicidad
                
                if (A.NuevoAsunto(codigo, nombre, pe, idNorma, periodicidad, cliente, responsable, aero, posicion, contactosasunto, Nc, idioma, tipo_asunto) == false) {
                    Integer idAsunto = Asunto.getId(codigo + "." + nombre);
                    String errPosRA = guardarPosicionesRA(idAsunto);
                    if (errPosRA == null || errPosRA.isEmpty())
                        JOptionPane.showMessageDialog(this, "Asunto insertado correctamente", "INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
                    else {
                        JOptionPane.showMessageDialog(this, errPosRA, "INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
                        if (idAsunto != null)
                            AsuntoRA.deleteAsuntos(idAsunto, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
                    }
                }else
                    JOptionPane.showMessageDialog(this, "El asunto ya existe", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            if (errorPosRA)
                MensajeApp.muestraError(this, null, "Hay posiciones de ruido acústico duplicadas");
            else
                JOptionPane.showMessageDialog(this,"Por favor, introduzca los campos obligatorios antes de guardar", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Fallo en la inserción", "ERROR", JOptionPane.ERROR_MESSAGE);
    } catch (ClassNotFoundException e) {//GEN-LAST:event_guardarAsunto
        JOptionPane.showMessageDialog(this, "Fallo en la inserción", "ERROR", JOptionPane.ERROR_MESSAGE);
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "Fallo en la inserción", "ERROR", JOptionPane.ERROR_MESSAGE);
    }	catch (NumberFormatException e) {
		JOptionPane.showMessageDialog(this, "Fallo en la inserción", "ERROR", JOptionPane.ERROR_MESSAGE);
	} catch (NoSuchFieldException e) {
		JOptionPane.showMessageDialog(this, "Fallo en la inserción", "ERROR", JOptionPane.ERROR_MESSAGE);
	}
}

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
        hB = Double.parseDouble(this.jTextField14.getText());
        dN = Double.parseDouble(this.jTextField11.getText());
    } catch (NumberFormatException e) {
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
        
        //Tiene autoincremento, no pasamos id.
        idPos = PosicionRA.insertPosicionGetId(posX, posY, posZ, null);
        
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
        if (AsuntoPosicionRA.insertAsuntoPosicionRA(idAsunto, idPosAero, idPosMicro1, idPosMicro2, idPosMicro3, idPosMicro4, null) == 0)
            err = "Fallo insertando posiciones de ruido acústico";
    }
    
    return err;
}

public void LimpiarContacto(){
    this.jComboBox11.setEnabled(false);
    this.jButton1.setEnabled(false);
    this.jButton3.setEnabled(false);
    this.jComboBox11.removeAllItems();
    this.jComboBox11.insertItemAt("", 0);
    this.dtm3.setRowCount(0);
    this.jTextField2.setText(null);
    this.jTextField3.setText(null);
    this.jTextField4.setText(null);
}


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closeButton2;
    private javax.swing.JButton closeButton3;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox jComboBox10;
    private javax.swing.JComboBox jComboBox11;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JComboBox jComboBox4;
    private javax.swing.JComboBox jComboBox5;
    private javax.swing.JComboBox jComboBox6;
    private javax.swing.JComboBox jComboBox7;
    private javax.swing.JComboBox jComboBox8;
    private javax.swing.JComboBox jComboBox9;
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
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
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
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JButton jbCargarFicPosRA;
    private javax.swing.JButton jbVerFormatoPosRA;
    private javax.swing.JButton jbVisualizarPosRA;
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
   private Responsable R=new Responsable();
   private Norma NR=new Norma();
   private Numero N=new Numero();
   private Cliente C=new Cliente();
   private Aerogenerador AG=new Aerogenerador();
   private Parque PE=new Parque();
   
   // Tablas
   private DefaultTableModel dtm1;
   private DefaultTableModel dtm3;
   
   // Variables   
   private String codigo;
   private String nombre;
   private int pe;
   private int periodicidad;
   private int cliente;
   private int responsable;
   private int aero;
   private String posicion; 
   private String idioma;
   private String Nc;
   private ArrayList<String> contactosasunto=new ArrayList<String>();
   private int tipo_asunto;
}
