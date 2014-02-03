package userinterfaces;

import RA.Numero;
import RA.Parque;
import RA.Sector;
import calculos.CalculoBeta;
import general.Auxiliares;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.print.PrinterException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class SVGUI1 extends JDialog {
       
    public SVGUI1(java.awt.Frame parent, int id) {
        super(parent, true);
        this.Id=id;
        initComponents(); 
        this.dtm1=(DefaultTableModel)this.jTable1.getModel();
        this.dtm2=(DefaultTableModel)this.jTable2.getModel();
        this.dtm3=(DefaultTableModel)this.jTable3.getModel();
        this.dtm5=(DefaultTableModel)this.jTable5.getModel();
        Centrar(this.jTable5,0,5);        
    } 
  
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton6 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel59 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jComboBox2 = new javax.swing.JComboBox();
        jButton2 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jbCalcularBeta = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jButton7 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        jButton6.setText("OK");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SECTOR VALIDO");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setForeground(new java.awt.Color(255, 0, 0));
        setIconImage(new ImageIcon(RA.Global.RUTA_IMAGENES + "GRA.png").getImage());
        setLocationByPlatform(true);
        setName("Cliente"); // NOI18N

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel1.setText("PARQUE EÓLICO");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));
        Parque PE=new Parque();
        try{
            ArrayList <String> parques=PE.getParques();
            int n=parques.size();
            for(int i=0;i<n;i++){
                String dato=parques.get(i);
                this.jComboBox1.insertItemAt(dato,i+1);
            }
            this.jComboBox1.setSelectedIndex(0);
        }catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Fallo al consultar la base de datos", "ERROR", JOptionPane.ERROR_MESSAGE);

        }
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                NuevoParque(evt);
            }
        });

        jLabel59.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(RA.Global.RUTA_IMAGENES + "parque.png" )));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 204, Short.MAX_VALUE)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel4.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(RA.Global.RUTA_IMAGENES + "database_gear.png" )));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel9.setText("AÑADIR");

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"TR1", null, null, null, null, null}
            },
            new String [] {
                "[ Id ]", "[ x]", "[ Y ]", "[ Z ]", "[ Ih ]", "[ Iw ]"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(jTable5);
        jTable5.getColumnModel().getColumn(0).setPreferredWidth(15);
        jTable5.getColumnModel().getColumn(4).setPreferredWidth(15);
        jTable5.getColumnModel().getColumn(5).setPreferredWidth(15);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Torre", "Obstáculo" }));
        jComboBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CambioObs(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(RA.Global.RUTA_IMAGENES + "resultset_down.png" )));
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Anadir(evt);
            }
        });

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(RA.Global.RUTA_IMAGENES + "map.png" )));
        jLabel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(RA.Global.RUTA_IMAGENES + "add.png" )));
        jLabel7.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jCheckBox1.setBackground(new java.awt.Color(204, 204, 204));
        jCheckBox1.setText("Incluir cálculos d<2D");

        jbCalcularBeta.setText("Calcular β");
        jbCalcularBeta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calcularBeta(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                            .addComponent(jComboBox2, 0, 169, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(160, 160, 160)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbCalcularBeta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jCheckBox1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jCheckBox1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addGap(38, 38, 38)))
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jbCalcularBeta, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38))))
        );

        jTabbedPane1.setBackground(new java.awt.Color(102, 102, 102));
        jTabbedPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jTabbedPane1.setForeground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 1, 11));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        this.dtm1=(DefaultTableModel)this.jTable1.getModel();
        this.dtm2=(DefaultTableModel)this.jTable2.getModel();
        this.dtm3=(DefaultTableModel)this.jTable3.getModel();
        this.jPanel8=this.createPanel();

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 712, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 509, Short.MAX_VALUE)
        );

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel8.setText("P.E.");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(340, 340, 340)
                        .addComponent(jLabel8)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addGap(11, 11, 11)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("                            ÁREA                            ", jPanel2);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "[ Id ]", "[ X ]", "[ Y ]", "[ Z ]", "[ Dn ]", "[ Hb ]"
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
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(100);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(100);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(50);
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(50);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel2.setText("AEROGENERADORES");

        jButton8.setBackground(new java.awt.Color(255, 255, 255));
        jButton8.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(RA.Global.RUTA_IMAGENES + "printer.png" )));
        jButton8.setToolTipText("Imprimir");
        jButton8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PrintPos(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(145, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(106, 106, 106))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(296, 296, 296)
                .addComponent(jLabel2)
                .addContainerGap(316, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel2)
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("                  AG's                       ", jPanel1);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel3.setText("TORRES DE REFERENCIA");

        jButton5.setBackground(new java.awt.Color(255, 255, 255));
        jButton5.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(RA.Global.RUTA_IMAGENES + "delete.png" )));
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BorrarTorre(evt);
            }
        });

        jButton9.setBackground(new java.awt.Color(255, 255, 255));
        jButton9.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(RA.Global.RUTA_IMAGENES + "printer.png" )));
        jButton9.setToolTipText("Imprimir");
        jButton9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PrintTR(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "[ Id ]", "[ X ]", "[ Y ]", "[ Z ]"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(141, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(699, Short.MAX_VALUE)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(264, 264, 264)
                .addComponent(jLabel3)
                .addContainerGap(324, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(jLabel3)
                .addGap(41, 41, 41)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(289, 289, 289)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("                     TORRES                         ", jPanel3);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel5.setText("OBSTÁCULOS");

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "[x]", "[y]", "[z]", "[Ih]", "[Iw]", "[De]"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTable3);
        jTable3.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTable3.getColumnModel().getColumn(1).setPreferredWidth(100);
        jTable3.getColumnModel().getColumn(2).setPreferredWidth(100);
        jTable3.getColumnModel().getColumn(3).setPreferredWidth(100);
        jTable3.getColumnModel().getColumn(4).setPreferredWidth(50);
        jTable3.getColumnModel().getColumn(5).setPreferredWidth(50);
        jTable3.getColumnModel().getColumn(6).setPreferredWidth(50);

        jButton7.setBackground(new java.awt.Color(255, 255, 255));
        jButton7.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(RA.Global.RUTA_IMAGENES + "delete.png" )));
        jButton7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BorrarObstaculo(evt);
            }
        });

        jButton10.setBackground(new java.awt.Color(255, 255, 255));
        jButton10.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(RA.Global.RUTA_IMAGENES + "printer.png" )));
        jButton10.setToolTipText("Imprimir");
        jButton10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PrintObs(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(699, Short.MAX_VALUE)
                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(321, 321, 321)
                .addComponent(jLabel5)
                .addContainerGap(334, Short.MAX_VALUE))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel5)
                        .addGap(50, 50, 50)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                        .addGap(331, 331, 331)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(121, 121, 121)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("                      OBS                           ", jPanel7);

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setText(">>");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Siguiente(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setText("<<");
        jButton3.setEnabled(false);

        jButton4.setBackground(new java.awt.Color(255, 255, 255));
        jButton4.setText("CANCELAR");
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Cancelar(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(725, 725, 725)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 747, Short.MAX_VALUE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 353, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(2008, 2008, 2008)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton4)
                            .addComponent(jButton1)
                            .addComponent(jButton3))
                        .addGap(1373, 1373, 1373)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 827, Short.MAX_VALUE)
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-775)/2, (screenSize.height-854)/2, 775, 854);
    }// </editor-fold>//GEN-END:initComponents

private void IniciarAñadir(String etiqueta){
    dtm5.setRowCount(0);
    dtm5.setRowCount(1);
    this.jTable5.setValueAt(etiqueta, 0, 0);    
    Centrar(this.jTable5,0,5); 
}

private void Anadir(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Anadir
    Numero N=new Numero();
    if (this.jComboBox2.getSelectedIndex()==0){ // Torre
        boolean ok=true;
        int i=0;
        while ((i<4) && (ok)){
            if ((this.jTable5.getValueAt(0,i)==null) || (this.jTable5.getValueAt(0,i).toString().equals(""))){
                ok=false;
            }
            i++;
        }
        if (ok){
             
             dtm2.setRowCount(dtm2.getRowCount()+1);
             for (int c=0;c<4;c++){
                this.jTable2.setValueAt(this.jTable5.getValueAt(0,c), n_torres, c); 
             }
             n_torres++;
             Centrar(this.jTable2,0,3);
             this.Graficar();
             this.IniciarAñadir("TR"+(n_torres+1));
        } else{
            JOptionPane.showMessageDialog(this, "Faltan datos", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);      
        }   
    } else{ // Obstáculo
        boolean ok=true;
        int i=0;
        while ((i<6) && (ok)){
            if ((this.jTable5.getValueAt(0,i)==null) || (this.jTable5.getValueAt(0,i).toString().equals(""))){
                ok=false;
            } 
            i++;
         }
         if (ok){
             dtm3.setRowCount(dtm3.getRowCount()+1);
             double Ih=0;
             double Iw=0;
             for (int c=0;c<6;c++){
                 if (c==4){Ih=Double.parseDouble(this.jTable5.getValueAt(0,c).toString());}
                 if (c==5){Iw=Double.parseDouble(this.jTable5.getValueAt(0,c).toString());}
                 this.jTable3.setValueAt(this.jTable5.getValueAt(0,c), n_obstaculos, c); 
             }
             // De
             double De=2*Ih*Iw/(Ih+Iw);
             this.jTable3.setValueAt(N.formato("0.0000", De), n_obstaculos, 6); 
             n_obstaculos++;
             Centrar(this.jTable3,0,6);
             this.Graficar();
             this.IniciarAñadir("OBS"+(n_obstaculos+1));
        } else{
            JOptionPane.showMessageDialog(this, "Faltan datos", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);      
        }   
    }
}//GEN-LAST:event_Anadir

private void Cancelar(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Cancelar
    dispose();
}//GEN-LAST:event_Cancelar

private void CambioObs(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CambioObs
     String etiqueta="";
     if (this.jComboBox2.getSelectedIndex()==0){
        etiqueta="TR"+(n_torres+1);
     } else{
        etiqueta="OBS"+(n_obstaculos+1);
     }
     this.IniciarAñadir(etiqueta);
}//GEN-LAST:event_CambioObs

private void Siguiente(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Siguiente
    // Inicializo
    
    objetos=new ArrayList<String []>(); // Ag+obs
    torres=new ArrayList<String []>();
   
    matrizaeros=new ArrayList<ArrayList<ArrayList<String[]>>>(); // SV de aeros
    matriztorres=new ArrayList<ArrayList<ArrayList<String[]>>>(); // SV de torres
    
    // Me creo la matriz de objetos (id,x,y,z,altura,anchura,de)
    
    int n=0;
    // AG
    for (int i=0;i<dtm1.getRowCount();i++){
        String[] dato=new String[7];
        dato[0]=dtm1.getValueAt(i, 0).toString(); // id
        dato[1]=dtm1.getValueAt(i, 1).toString(); // x
        dato[2]=dtm1.getValueAt(i, 2).toString(); // y
        dato[3]=dtm1.getValueAt(i, 3).toString(); // z
        dato[4]=dtm1.getValueAt(i, 5).toString(); // altura
        dato[5]=dtm1.getValueAt(i, 4).toString(); // anchura
        dato[6]=dtm1.getValueAt(i, 4).toString(); // dn
        objetos.add(n, dato);
        n++;
    }
    for (int i=0;i<dtm3.getRowCount();i++){
        String[] dato=new String[7];
        dato[0]=dtm3.getValueAt(i, 0).toString(); // id
        dato[1]=dtm3.getValueAt(i, 1).toString(); // x
        dato[2]=dtm3.getValueAt(i, 2).toString(); // y
        dato[3]=dtm3.getValueAt(i, 3).toString(); // z
        dato[4]=dtm3.getValueAt(i, 4).toString(); // altura
        dato[5]=dtm3.getValueAt(i, 5).toString(); // anchura
        dato[6]=dtm3.getValueAt(i, 6).toString(); // de
        objetos.add(n, dato);
        n++;
    }
    System.out.println("Objetos");
    for (int i=0;i<objetos.size();i++){
        System.out.println(objetos.get(i)[0]+" "+objetos.get(i)[1]+" "+objetos.get(i)[2]+" "+objetos.get(i)[3]);
    }
    n=0;
    // TR
    for (int i=0;i<dtm2.getRowCount();i++){
        String[] dato=new String[4];
        dato[0]=dtm2.getValueAt(i, 0).toString(); // id
        dato[1]=dtm2.getValueAt(i, 1).toString(); // x
        dato[2]=dtm2.getValueAt(i, 2).toString(); // y
        dato[3]=dtm2.getValueAt(i, 3).toString(); // z
        torres.add(n, dato);
        n++;
    }
    System.out.println("Torres");
    for (int i=0;i<torres.size();i++){
        System.out.println(torres.get(i)[0]+" "+torres.get(i)[1]+" "+torres.get(i)[2]);
    }
    try{
        Sector S=new Sector();
        boolean dosD=this.jCheckBox1.isSelected();
        // Para cada objeto se calculan los limites invalidados
        for (int i=0;i<objetos.size();i++){
            ArrayList<ArrayList<String[]>> sv=S.getSectorValido(objetos.get(i)[0], objetos, dosD, Auxiliares.NUM_D_FIN_PERTURBACION_RA);
            for (int j=0;j<sv.get(0).size();j++){
                System.out.println(sv.get(0).get(j)[0]+" "+sv.get(0).get(j)[1]+" "+sv.get(0).get(j)[2]+" "+sv.get(0).get(j)[3]+" "+sv.get(0).get(j)[4]+" "+sv.get(0).get(j)[5]+" "+sv.get(0).get(j)[6]);
            }
            for (int j=0;j<sv.get(1).size();j++){
                System.out.println(sv.get(1).get(j)[0]+" "+sv.get(1).get(j)[1]);
            }
            matrizaeros.add(i, sv);    
        } 
        for (int i=0;i<torres.size();i++){
            ArrayList<ArrayList<String[]>> sv=new ArrayList<ArrayList<String[]>>();
            sv=S.getSectorValido(torres.get(i), objetos,dosD);
            System.out.println("Tabla en torre");
            for (int j=0;j<sv.get(0).size();j++){
                System.out.println(sv.get(0).get(j)[0]+" "+sv.get(0).get(j)[1]+" "+sv.get(0).get(j)[2]+" "+sv.get(0).get(j)[3]+" "+sv.get(0).get(j)[4]+" "+sv.get(0).get(j)[5]+" "+sv.get(0).get(j)[6]);
            }
            System.out.println("limites en torre");
            for (int j=0;j<sv.get(1).size();j++){
                System.out.println(sv.get(1).get(j)[0]+" "+sv.get(1).get(j)[1]);
            }
            matriztorres.add(i, sv);    
        }
        if (torres.size()==0){
            int respuesta = JOptionPane.showConfirmDialog(this, "No ha introducido posición de torre. ¿Desea continuar?","Información al usuario",JOptionPane.YES_NO_OPTION);
            if (respuesta == JOptionPane.YES_OPTION) {
                 this.setVisible(false);
                 new SVGUI2(this,0).setVisible(true);
            }
        } else{
             this.setVisible(false);
             new SVGUI2(this,0).setVisible(true);
        }      
    } catch (SQLException e){
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Fallo en el proceso de cálculo", "ERROR", JOptionPane.ERROR_MESSAGE);      
    }
    
}//GEN-LAST:event_Siguiente

private void BorrarTorre(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BorrarTorre
   int fila=jTable2.getSelectedRow();
   int fila2=fila+jTable2.getSelectedRowCount()-1;
   if (fila!=-1){ 
      for (int i=fila;i<=fila2;i++){
         dtm2.removeRow(fila);
      }
   }
   n_torres--;
   this.Graficar();
   String etiqueta="";
   if (this.jComboBox2.getSelectedIndex()==0){
      etiqueta="TR"+(n_torres+1);
   } else{
      etiqueta="OBS"+(n_obstaculos+1);
   }
   this.IniciarAñadir(etiqueta);
}//GEN-LAST:event_BorrarTorre

private void BorrarObstaculo(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BorrarObstaculo
   int fila=jTable3.getSelectedRow();
   int fila2=fila+jTable3.getSelectedRowCount()-1;
   if (fila!=-1){ 
      for (int i=fila;i<=fila2;i++){
         dtm3.removeRow(fila);
      }
   }
   n_obstaculos--;
   this.Graficar();   
   String etiqueta="";
   if (this.jComboBox2.getSelectedIndex()==0){
      etiqueta="TR"+(n_torres+1);
   } else{
      etiqueta="OBS"+(n_obstaculos+1);
   }
   this.IniciarAñadir(etiqueta);
}//GEN-LAST:event_BorrarObstaculo

private void PrintPos(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PrintPos
try {
    boolean ok=this.jTable1.print();
    if (ok){
        JOptionPane.showMessageDialog(this, "Impresión Finalizada", "INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);      
    } else{
        JOptionPane.showMessageDialog(this, "Fallo en la impresión", "ERROR", JOptionPane.ERROR_MESSAGE);      
    }
} catch (PrinterException e) {
       e.printStackTrace();
}
}//GEN-LAST:event_PrintPos

private void PrintTR(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PrintTR
try {
    boolean ok=this.jTable2.print();
    if (ok){
        JOptionPane.showMessageDialog(this, "Impresión Finalizada", "INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);      
    } else{
        JOptionPane.showMessageDialog(this, "Fallo en la impresión", "ERROR", JOptionPane.ERROR_MESSAGE);      
    }
} catch (PrinterException e) {
       e.printStackTrace();
}
}//GEN-LAST:event_PrintTR

private void PrintObs(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PrintObs
try {
    boolean ok=this.jTable3.print();
    if (ok){
        JOptionPane.showMessageDialog(this, "Impresión Finalizada", "INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);      
    } else{
        JOptionPane.showMessageDialog(this, "Fallo en la impresión", "ERROR", JOptionPane.ERROR_MESSAGE);      
    }
} catch (PrinterException e) {
       e.printStackTrace();
}
}//GEN-LAST:event_PrintObs

private void NuevoParque(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_NuevoParque
    Limpiar();
    if (this.jComboBox1.getSelectedIndex()!=0){
        Parque PE=new Parque();
        try {
            parque=PE.getId(this.jComboBox1.getSelectedItem().toString());
            this.jLabel8.setText("P.E."+this.jComboBox1.getSelectedItem().toString());
            posiciones=PE.getPosiciones(parque);
            this.CargarAGs();
            this.Graficar();
        } catch (SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Fallo al consultar la base de datos", "ERROR", JOptionPane.ERROR_MESSAGE);    
        }
    }
}//GEN-LAST:event_NuevoParque

private void calcularBeta(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calcularBeta
    try {
        if (this.jComboBox1.getSelectedIndex() != 0) {
            Parque parqueEolico = new Parque();
            Integer idParque = parqueEolico.getId(this.jComboBox1.getSelectedItem().toString());

            new CalculoBeta((Frame) this.getParent(), idParque).setVisible(true);
        } else
            JOptionPane.showMessageDialog(this, "Debe seleccionar primero un parque", "Aviso", JOptionPane.WARNING_MESSAGE);
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Fallo al consultar la base de datos", "ERROR", JOptionPane.ERROR_MESSAGE);    
    }
}//GEN-LAST:event_calcularBeta

private void CargarAGs(){
    int n=posiciones.size();
    dtm1.setRowCount(n);
    for (int j=0;j<n;j++){
        for (int h=0;h<6;h++){
             if (posiciones.get(j)[h]!=null){
                 this.jTable1.setValueAt(posiciones.get(j)[h], j, h);
             }
        }                 
    }
    Centrar(this.jTable1,0,5); 
}

private void Graficar(){
    ChartPanel p=(ChartPanel)this.jPanel8;
    p.setChart(this.createChart());
}

public void Limpiar(){
    // Calculos obs < 2D
    this.jCheckBox1.setSelected(false);
    // Añadir
    this.jComboBox2.setSelectedIndex(0);
    // Área
    this.jLabel8.setText("P.E.");
    this.Graficar();
    // AG's
    dtm1.setRowCount(0);
    // Torres
    dtm2.setRowCount(0);
    // Obs
    dtm3.setRowCount(0);
    // Objetos
    posiciones=new ArrayList<String []>(); // Ag base de datos
    torres=new ArrayList<String []>();
    objetos=new ArrayList<String []>();
    n_torres=0;
    n_obstaculos=0;    
}

 // Crear el Panel
 public JPanel createPanel() {
     JFreeChart chart = createChart();
     ChartPanel chartPanel = new ChartPanel(chart);
     chartPanel.setPreferredSize(new java.awt.Dimension(300,450));
     return chartPanel;
 }
 
 private JFreeChart createChart() {
   
     XYDataset dataset = createDataset();
     JFreeChart chart = ChartFactory.createScatterPlot(
              "",
              "", 
              "", 
              dataset, 
              PlotOrientation.VERTICAL, 
              true, true, false);
 
     chart.setBackgroundPaint(Color.WHITE);
     XYPlot plot = (XYPlot) chart.getPlot();
     plot.setBackgroundPaint(Color.WHITE);
      
     plot.setDomainGridlinePaint(Color.LIGHT_GRAY);
     plot.setRangeGridlinePaint(Color.LIGHT_GRAY);
     
     XYItemRenderer renderer=plot.getRenderer();
     renderer.setBaseItemLabelGenerator(new StandardXYItemLabelGenerator() {
     
     @Override
     public String generateLabel(XYDataset dataset,int series,int item) {
            String label="";
            if (series==0){
                label=etiquetas_aerogeneradores.get(item);
            } 
            if (series==1){
                label=etiquetas_torres.get(item);
            } 
            if (series==2){
                label=etiquetas_obstaculos.get(item);
            } 
            return label;
     }
     });
        
     renderer.setBaseItemLabelsVisible(true);
     
     // AG's
     renderer.setSeriesOutlinePaint(0, Color.black);
     renderer.setSeriesPaint(0, Color.MAGENTA);
     renderer.setSeriesItemLabelPaint(0, Color.MAGENTA);
     
     // Torres
     renderer.setSeriesPaint(1, Color.BLUE);
     renderer.setSeriesItemLabelPaint(1, Color.BLUE);
     
     // Obs
     renderer.setSeriesPaint(2, Color.GREEN);
     renderer.setSeriesItemLabelPaint(2, Color.GREEN);
        
     return chart; 
   }
 
private XYDataset createDataset() {
       
    XYSeriesCollection dataset = new XYSeriesCollection();
       
    XYSeries serie_aerogeneradores = new XYSeries("AG's",false);
    XYSeries serie_torres = new XYSeries("TR's",false);
    XYSeries serie_obstaculos = new XYSeries("Obs's",false);
       
    etiquetas_aerogeneradores=new ArrayList<String>();
    etiquetas_torres=new ArrayList<String>();
    etiquetas_obstaculos=new ArrayList<String>();
   
    // AG's
    int n_aeros=dtm1.getRowCount();
    for (int j=0;j<n_aeros;j++){
        serie_aerogeneradores.add(Double.parseDouble(this.jTable1.getValueAt(j,1).toString()), Double.parseDouble(this.jTable1.getValueAt(j,2).toString()));                         
        etiquetas_aerogeneradores.add(this.jTable1.getValueAt(j,0).toString());
    } 
    dataset.addSeries(serie_aerogeneradores);
       
    // TR's
    System.out.println("n_torres="+n_torres);
    for (int j=0;j<n_torres;j++){
        serie_torres.add(Double.parseDouble(this.jTable2.getValueAt(j,1).toString()), Double.parseDouble(this.jTable2.getValueAt(j,2).toString()));                
        etiquetas_torres.add(this.jTable2.getValueAt(j,0).toString());        
    } 
    dataset.addSeries(serie_torres);
       
    // OBS's
    for (int j=0;j<n_obstaculos;j++){
        serie_obstaculos.add(Double.parseDouble(this.jTable3.getValueAt(j,1).toString()), Double.parseDouble(this.jTable3.getValueAt(j,2).toString()));                
        etiquetas_obstaculos.add(this.jTable3.getValueAt(j,0).toString());
    } 
    dataset.addSeries(serie_obstaculos);
       
    return dataset;
} 

// Centra los datos de una tabla
public void Centrar (JTable tabla,int i, int f){
    DefaultTableCellRenderer c = new DefaultTableCellRenderer();
    c.setHorizontalAlignment(SwingConstants.CENTER);
    for (int columna=i;columna<=f;columna++){
        tabla.getColumnModel().getColumn(columna).setCellRenderer(c);     
    }
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JCheckBox jCheckBox1;
    public javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
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
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable5;
    private javax.swing.JButton jbCalcularBeta;
    // End of variables declaration//GEN-END:variables
    private int Id;
    
    private DefaultTableModel dtm1;   
    private DefaultTableModel dtm2; 
    private DefaultTableModel dtm3; 
    private DefaultTableModel dtm5; 
    
    public int parque;
    public int n_torres;
    public int n_obstaculos;
    
    public ArrayList <String> etiquetas_aerogeneradores=new ArrayList<String>();
    public ArrayList <String> etiquetas_torres=new ArrayList<String>();
    public ArrayList <String> etiquetas_obstaculos=new ArrayList<String>();
    
    public ArrayList <String []> objetos=new ArrayList<String []>(); // Ag+obs
    public ArrayList <String []> torres=new ArrayList<String []>();    
    public ArrayList <String []> posiciones=new ArrayList<String []>(); // AG
    
    ArrayList<ArrayList<ArrayList<String[]>>> matrizaeros=new ArrayList<ArrayList<ArrayList<String[]>>>(); // SV de aeros
    ArrayList<ArrayList<ArrayList<String[]>>> matriztorres=new ArrayList<ArrayList<ArrayList<String[]>>>(); // SV de torres

}   
