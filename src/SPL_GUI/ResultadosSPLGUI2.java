
package SPL_GUI;

import RA.Numero;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.geom.Rectangle2D;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.XYItemEntity;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.function.Function2D;
import org.jfree.data.function.LineFunction2D;
import org.jfree.data.function.PolynomialFunction2D;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.statistics.Regression;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


public class ResultadosSPLGUI2 extends JDialog implements ChartMouseListener {
    
    public ResultadosSPLGUI2(ResultadosSPLGUI1 D) {
       super(D,true);
       this.D0=D;
       
       // Creo los colores del gra´fcico lineal
       colores_AE[0]=new Color(0,0,153);
       colores_AE[1]=new Color(0,0,255);
       colores_AE[2]=new Color(51,153,255);
       colores_AE[3]=new Color(102,204,255);
       colores_AE[4]=new Color(153,255,255);
       
       colores_RF[0]=new Color(165,42,42);
       colores_RF[1]=new Color(255,140,0);
       colores_RF[2]=new Color(255,0,0);
       colores_RF[3]=new Color(255,215,0);
       colores_RF[4]=new Color(255,127,80);
       
       initComponents();   
       this.jTextField1.setText(this.D0.jcbAsunto.getSelectedItem().toString());    
              
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel9 = new javax.swing.JPanel();
        closeButton3 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        closeButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SEGUIMIENTO / SPL - 02");
        setBackground(new java.awt.Color(175, 30, 30));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setForeground(new java.awt.Color(255, 0, 0));
        setLocationByPlatform(true);
        setName("Cliente"); // NOI18N

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        closeButton3.setBackground(new java.awt.Color(255, 255, 255));
        closeButton3.setText("<< ");
        closeButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Anterior(evt);
            }
        });

        jButton13.setBackground(new java.awt.Color(255, 255, 255));
        jButton13.setText("  >>");
        jButton13.setAlignmentX(0.5F);
        jButton13.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton13.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Siguiente(evt);
            }
        });

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel33.setText("  Asunto: ");

        jTextField1.setBackground(new java.awt.Color(204, 204, 204));
        jTextField1.setEditable(false);
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel33)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 384, Short.MAX_VALUE)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTabbedPane1.setBackground(new java.awt.Color(102, 102, 102));
        jTabbedPane1.setForeground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Dato", "Fecha_Hora", "Valido", "RF", "S1", "S2", "S3", "S4", "S5", "S6", "S7", "S8", "S9", "S10", "S11", "S12", "S13", "S14", "S15", "S16", "S17", "S18", "S19", "S20"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable3.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable3.setColumnSelectionAllowed(true);
        this.RellenarTabla(); 
        this.CreaGraficos();
        jScrollPane3.setViewportView(jTable3);
        jTable3.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel1.setText("BASE NETA");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 734, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(350, 350, 350)
                        .addComponent(jLabel1)))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 521, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );

        jTabbedPane1.addTab("                DATOS NETOS                  ", jPanel1);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Bin", "N", "Vs", "Pn", "Rpm", "Pitch"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel4.setText("RESULTADOS POR BIN");

        jLabel17.setText("__________________________________________________________________________________________________________________________________");

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Bin", "N", "Vs", "Pn", "Rpm", "Pitch"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        this.RellenarCompletitud();
        jScrollPane4.setViewportView(jTable4);

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel18.setText("AEROGENERADOR");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel19.setText("RUIDO FONDO");

        jLabel6.setText("TOTAL » ");

        jTextField2.setBackground(new java.awt.Color(204, 204, 204));
        jTextField2.setEditable(false);
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel7.setText("TOTAL » ");

        jTextField3.setBackground(new java.awt.Color(204, 204, 204));
        jTextField3.setEditable(false);
        jTextField3.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 270, Short.MAX_VALUE)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(280, 280, 280)))))
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(304, 304, 304)
                .addComponent(jLabel4)
                .addContainerGap(359, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(148, 148, 148)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 274, Short.MAX_VALUE)
                .addComponent(jLabel19)
                .addGap(184, 184, 184))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17)
                .addGap(72, 72, 72)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4, 0, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addContainerGap(316, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("                COMPLETITUD / BIN                            ", jPanel4);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 678, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 397, Short.MAX_VALUE)
        );

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"AG", null, null, null, null, null, null},
                {"RF", null, null, null, null, null, null}
            },
            new String [] {
                "", "X0", "X1", "X2", "X3", "X4", "R2"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        this.RellenarCoeficientes();
        jScrollPane5.setViewportView(jTable5);
        jTable5.getColumnModel().getColumn(0).setMinWidth(50);
        jTable5.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTable5.getColumnModel().getColumn(0).setMaxWidth(50);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("AJUSTE POLINOMIAL");
        jLabel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(174, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(171, 171, 171))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(282, 282, 282)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(310, Short.MAX_VALUE))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(65, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel5)
                .addGap(33, 33, 33)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        jTabbedPane1.addTab("        AJUSTE POLINOMIAL             ", jPanel7);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 647, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 376, Short.MAX_VALUE)
        );

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("AJUSTE LINEAL");
        jLabel14.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Bin", "Recta"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getColumn(0).setMinWidth(50);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(50);

        jTable6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Bin", "Recta"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        this.RellenarCoeficientes2();
        jScrollPane6.setViewportView(jTable6);
        jTable6.getColumnModel().getColumn(0).setMinWidth(50);
        jTable6.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTable6.getColumnModel().getColumn(0).setMaxWidth(50);

        jLabel2.setText("AE");

        jLabel3.setText("RF");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(283, 283, 283)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(81, Short.MAX_VALUE))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(200, 200, 200)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 361, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(212, 212, 212))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel14)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("        AJUSTE LINEAL              ", jPanel8);

        jRadioButton1.setBackground(new java.awt.Color(204, 204, 204));
        jRadioButton1.setSelected(true);
        jRadioButton1.setText("Ajuste Lineal");

        jRadioButton2.setBackground(new java.awt.Color(204, 204, 204));
        jRadioButton2.setText("Ajuste Polinomial");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jRadioButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton2))
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 804, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 634, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2))
                .addContainerGap())
        );

        closeButton4.setBackground(new java.awt.Color(255, 255, 255));
        closeButton4.setText("CANCELAR");
        closeButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Cancelar(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel9Layout.createSequentialGroup()
                        .addComponent(closeButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 528, Short.MAX_VALUE)
                        .addComponent(closeButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(closeButton4)
                    .addComponent(jButton13)
                    .addComponent(closeButton3))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-857)/2, (screenSize.height-835)/2, 857, 835);
    }// </editor-fold>//GEN-END:initComponents

            
public void RellenarTabla(){
  
    this.dtm3=(DefaultTableModel)this.jTable3.getModel();
    DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
    tcr.setHorizontalAlignment(SwingConstants.CENTER);     
    
    int ncol=this.D0.columnas.size();
    
    // Se inicializa el tamaño de las columnas
    for (int k=0;k<24;k++){
        if (k==1){ // Fecha_Hora
             this.jTable3.getColumnModel().getColumn(k).setMaxWidth(120);
             this.jTable3.getColumnModel().getColumn(k).setMinWidth(120);
             this.jTable3.getColumnModel().getColumn(k).setPreferredWidth(120);     
        } else{ // Identificadores
            if (k<4){
                this.jTable3.getColumnModel().getColumn(k).setMaxWidth(45);
                this.jTable3.getColumnModel().getColumn(k).setMinWidth(45);
                this.jTable3.getColumnModel().getColumn(k).setPreferredWidth(45);     
            } else{ // Resto
                this.jTable3.getColumnModel().getColumn(k).setMaxWidth(65);
                this.jTable3.getColumnModel().getColumn(k).setMinWidth(65);
                this.jTable3.getColumnModel().getColumn(k).setPreferredWidth(65);     
            }  
        }
        
    } 
    
    // Relleno la tabla con la base 
    int ndatos=this.D0.datos.size();   
    dtm3.setRowCount(ndatos);
    // Columnas
    for (int j=4;j<ncol;j++){ 
          this.jTable3.getColumnModel().getColumn(j).setHeaderValue(this.D0.columnas.get(j));
     }
     for(int i=0;i<ndatos;i++){
            
            // Ndato + Fecha + Identificadores
            for (int j=0;j<4;j++){
                this.jTable3.setValueAt(this.D0.datos.get(i)[j], i, j); 
            }
            // Datos
            for (int j=4;j<ncol;j++){ 
                 double v=Double.parseDouble(this.D0.datos.get(i)[j]);
                 if (v!=8888.88){
                    this.jTable3.setValueAt(N.formato("0.0000", v), i, j); 
                 }     
            }
            
            
    }
    // Se ocultan las columnas sobrantes
    for (int j=ncol;j<24;j++){
         this.jTable3.getColumnModel().getColumn(j).setMaxWidth(0);
         this.jTable3.getColumnModel().getColumn(j).setMinWidth(0);
         this.jTable3.getColumnModel().getColumn(j).setPreferredWidth(0);    
    }
            
    this.Centrar(jTable3, 0, 23);
}


private void CreaGraficos(){
    this.jPanel2=this.createPanel();
    ((ChartPanel)jPanel2).addChartMouseListener(this);
    this.jPanel3=this.createPanel2();
    ((ChartPanel)jPanel3).addChartMouseListener(this);
}

 
public JPanel createPanel() {
     JFreeChart chart = createChart();
     ChartPanel chartPanel = new ChartPanel(chart);
     chartPanel.setPreferredSize(new java.awt.Dimension(700,450));
     return chartPanel;
}

public JPanel createPanel2() {
     JFreeChart chart = createChart2();
     ChartPanel chartPanel = new ChartPanel(chart);
     chartPanel.setPreferredSize(new java.awt.Dimension(650,400));
     return chartPanel;
}

   // Ajuste polinomial (grado 4)
   private JFreeChart createChart() {
       
       XYDataset dataset=this.createDataset();
       JFreeChart chart = ChartFactory.createScatterPlot(
               "",
               "Velocidad de referencia Vs (m/s)", 
               "Presión sonora equivalente (dBA)",
               dataset, 
               PlotOrientation.VERTICAL,
               true, 
               true, 
               false
       );
       
       TextTitle texto=chart.getTitle();
       texto.setFont(new Font("Arial", Font.BOLD, 13));
       chart.setBackgroundPaint(Color.WHITE);
       XYPlot plot = (XYPlot) chart.getPlot();
       Rectangle2D.Double R=new Rectangle2D.Double(-1,-1, 2, 2);
        
       // Se calcula la ecuacion de la curva AG    
       if (dataset.getItemCount(0)>1){
           double[] curva_ag=Regression.getPolynomialRegression(dataset, 0, 4);   
           coeficientes_polinomio_ag = curva_ag;
           double[] coeficientes_ag=new double[5];
           for (int j=0;j<5;j++){
                coeficientes_ag[j]=curva_ag[j];
           }
           PolynomialFunction2D polinomio_ag = new PolynomialFunction2D(coeficientes_ag) ;
           XYDataset regresion_ag = DatasetUtilities.sampleFunction2D(polinomio_ag,5.5, 10.5, 200, "Polinimio AG");
           plot.setDataset(0, regresion_ag);   
       }
       
       XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(true, false);
       renderer.setSeriesPaint(0, Color.RED); 
       renderer.setStroke(new BasicStroke(2.0f));
       plot.setRenderer(0, renderer);           
       
       // Se calcula la ecuacion de la curva RF  
       if (dataset.getItemCount(1)>1){
           double[] curva_rf=Regression.getPolynomialRegression(dataset, 1, 4);      
           coeficientes_polinomio_rf = curva_rf;
           double[] coeficientes_rf=new double[5];
           for (int j=0;j<5;j++){
                coeficientes_rf[j]=curva_rf[j];
           }
           PolynomialFunction2D polinomio_rf = new PolynomialFunction2D(coeficientes_rf) ;
           XYDataset regresion_rf = DatasetUtilities.sampleFunction2D(polinomio_rf,5.5, 10.5, 200, "Polinimio RF");
           plot.setDataset(1, regresion_rf);
       }
       
       XYLineAndShapeRenderer renderer2 = new XYLineAndShapeRenderer(true, false);
       renderer2.setSeriesPaint(0, Color.BLUE); 
       renderer2.setStroke(new BasicStroke(2.0f));
       plot.setRenderer(1, renderer2);     
       
       plot.setDataset(2, dataset);
       
       XYItemRenderer renderer3 = new XYLineAndShapeRenderer(false, true);   
       renderer3.setSeriesPaint(0, Color.BLUE);  
       renderer3.setSeriesShape(0, R);  
       renderer3.setSeriesPaint(1, Color.RED);  
       renderer3.setSeriesShape(1, R);  
       renderer3.setBaseToolTipGenerator(new StandardXYToolTipGenerator());
       plot.setRenderer(2, renderer3); 
     
       return chart;
   }
   
   // Ajuste lineal por bin
   private JFreeChart createChart2() {
       
     
       XYDataset dataset=this.createDataset2();
       JFreeChart chart = ChartFactory.createScatterPlot(
               "",
               "Velocidad de referencia Vs (m/s)", 
               "Presión sonora equivalente (dBA)",
               dataset, 
               PlotOrientation.VERTICAL,
               true, 
               true, 
               false
       );
       
       TextTitle texto=chart.getTitle();
       texto.setFont(new Font("Arial", Font.BOLD, 13));
       chart.setBackgroundPaint(Color.WHITE);
       XYPlot plot = (XYPlot) chart.getPlot();
       Rectangle2D.Double R=new Rectangle2D.Double(-1,-1, 2, 2);
   
       for (int i=0;i<5;i++){
           if (dataset.getItemCount(i)>1){
               coeficientes_bin_ag.add(Regression.getOLSRegression(dataset, i));
               Function2D recta = new LineFunction2D(coeficientes_bin_ag.get(i)[0], coeficientes_bin_ag.get(i)[1]);
               XYDataset regresion = DatasetUtilities.sampleFunction2D(recta,(i+6)-0.5, (i+6)+0.5, 150, "Lineal(AG_"+(i+6)+")");
               plot.setDataset(i, regresion);
           }

           XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(true, false);
           renderer.setStroke(new BasicStroke(2.0f));
           renderer.setSeriesPaint(0, colores_AE[i]);
           plot.setRenderer(i, renderer); 
           
       }

       for (int i=0;i<5;i++){
           if (dataset.getItemCount(i+5)>1){
               coeficientes_bin_rf.add(Regression.getOLSRegression(dataset, i+5));
               Function2D recta = new LineFunction2D(coeficientes_bin_rf.get(i)[0], coeficientes_bin_rf.get(i)[1]);
               XYDataset regresion = DatasetUtilities.sampleFunction2D(recta,(i+6)-0.5, (i+6)+0.5, 150, "Lineal(RF_"+(i+6)+")");
               plot.setDataset(i+5, regresion);
           }
         
           XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(true, false);
           renderer.setStroke(new BasicStroke(2.0f));
           renderer.setSeriesPaint(0, colores_RF[i]);
           plot.setRenderer(i+5, renderer); 
           
       }
       
       plot.setDataset(10, dataset);
       XYItemRenderer renderer2 = new XYLineAndShapeRenderer(false, true);  
       for (int i=0;i<5;i++){  
            renderer2.setSeriesShape(i, R);     
            renderer2.setSeriesPaint(i, colores_AE[i]);     
       }
       for (int i=0;i<5;i++){  
            renderer2.setSeriesShape(i+5, R);     
            renderer2.setSeriesPaint(i+5, colores_RF[i]);     
       }
       plot.setRenderer(10, renderer2); 
       return chart;
   }

   private void RellenarCompletitud(){
       this.dtm2=(DefaultTableModel)this.jTable2.getModel(); 
       this.dtm4=(DefaultTableModel)this.jTable4.getModel(); 
       dtm2.setRowCount(5);
       dtm4.setRowCount(5);
       for (int i=0;i<5;i++){
            // Bin
            this.jTable2.setValueAt(N.formato("0",this.D0.resultados_AE.get(i)[0]), i, 0); 
            this.jTable4.setValueAt(N.formato("0",this.D0.resultados_RF.get(i)[0]), i, 0); 
            // N
            this.jTable2.setValueAt(N.formato("0",this.D0.resultados_AE.get(i)[1]), i, 1); 
            this.jTable4.setValueAt(N.formato("0",this.D0.resultados_RF.get(i)[1]), i, 1); 
            // VS
            this.jTable2.setValueAt(N.formato("0.0000",this.D0.resultados_AE.get(i)[2]), i, 2); 
            this.jTable4.setValueAt(N.formato("0.0000",this.D0.resultados_RF.get(i)[2]), i, 2); 
            // P
            this.jTable2.setValueAt(N.formato("0.0000",this.D0.resultados_AE.get(i)[3]), i, 3); 
            this.jTable4.setValueAt(N.formato("0.0000",this.D0.resultados_RF.get(i)[3]), i, 3); 
       }
       this.Centrar(jTable2, 0, 3); 
       this.Centrar(jTable4, 0, 3); 
       this.jTextField2.setText(N.formato("0",this.D0.total[0]));
       this.jTextField3.setText(N.formato("0",this.D0.total[1]));
   }

   private void RellenarCoeficientes(){
        this.dtm5=(DefaultTableModel)this.jTable5.getModel();
   
        NumberFormat formato = new DecimalFormat("0.0000");
        for (int i=0;i<5;i++){
             this.jTable5.setValueAt(formato.format(coeficientes_polinomio_ag[i]), 0, i+1);
             this.jTable5.setValueAt(formato.format(coeficientes_polinomio_rf[i]), 1, i+1);
        }
        this.jTable5.setValueAt(formato.format(coeficientes_polinomio_ag[5]), 0, 6);
        this.jTable5.setValueAt(formato.format(coeficientes_polinomio_rf[5]), 1, 6);
        this.Centrar(jTable5, 0, 6); 
   }
   
   private void RellenarCoeficientes2(){
        this.dtm1=(DefaultTableModel)this.jTable1.getModel();
        this.dtm6=(DefaultTableModel)this.jTable6.getModel();
        dtm1.setRowCount(5); 
        dtm6.setRowCount(5);
        for (int i=0;i<5;i++){
             this.jTable1.setValueAt("AG_"+(i+6),i,0);
             if (coeficientes_bin_ag.size()>i){
                this.jTable1.setValueAt("y="+N.formato("0.0000", coeficientes_bin_ag.get(i)[0])+"x + "+N.formato("0.0000", coeficientes_bin_ag.get(i)[1]),i,1);
             }
             this.jTable6.setValueAt("RF_"+(i+6),i,0);
             if (coeficientes_bin_rf.size()>i){
                this.jTable6.setValueAt("y="+N.formato("0.0000", coeficientes_bin_rf.get(i)[0])+"x + "+N.formato("0.0000", coeficientes_bin_rf.get(i)[1]),i,1);
             }
        }
    
        this.Centrar(jTable1, 0, 1); 
        this.Centrar(jTable6, 0, 1); 
   }
 
 // Correlación Polinómica
 private XYDataset createDataset() {
    XYSeriesCollection dataset = new XYSeriesCollection();
    
    XYSeries serie_ag = new XYSeries("AG",false);
    XYSeries serie_rf = new XYSeries("RF",false);
    
    int columnax=this.jTable3.getColumnModel().getColumnIndex("VS");
    int columnay=this.jTable3.getColumnModel().getColumnIndex("LAeq1");
       
    for (int j=0;j<dtm3.getRowCount();j++){
        if (Double.parseDouble(this.jTable3.getValueAt(j, 3).toString())==0){
            serie_ag.add(Double.parseDouble(this.jTable3.getValueAt(j, columnax).toString()), Double.parseDouble(this.jTable3.getValueAt(j, columnay).toString())); 
//          fila.add(j);
        } else{
            serie_rf.add(Double.parseDouble(this.jTable3.getValueAt(j, columnax).toString()), Double.parseDouble(this.jTable3.getValueAt(j, columnay).toString())); 
       }            
    }      
    dataset.addSeries(serie_ag);
    dataset.addSeries(serie_rf);
    
    return dataset;
}
 
 // Correlación Lineal por Bin
 private XYDataset createDataset2() {
    
     XYSeriesCollection dataset = new XYSeriesCollection();
    
    for (int i=0;i<5;i++){
        XYSeries serie_ag = new XYSeries("AG_"+(i+6),false);
        int n=this.D0.datos_bin_AE.get(i).size();
        for (int j=0;j<n;j++){
             serie_ag.add(this.D0.datos_bin_AE.get(i).get(j)[0], this.D0.datos_bin_AE.get(i).get(j)[1]);              
        }      
        dataset.addSeries(serie_ag);       
    }
    
    for (int i=0;i<5;i++){
        XYSeries serie_rf = new XYSeries("RF_"+(i+6),false);
        int n=this.D0.datos_bin_RF.get(i).size();
        for (int j=0;j<n;j++){
             serie_rf.add(this.D0.datos_bin_RF.get(i).get(j)[0], this.D0.datos_bin_RF.get(i).get(j)[1]);              
        }      
        dataset.addSeries(serie_rf);       
    }
     
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


private void Siguiente(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Siguiente
    boolean lineal=true;
    if (this.jRadioButton2.isSelected()){
        lineal=false;
    } 
    System.out.println(coeficientes_polinomio_ag[0]+" "+coeficientes_polinomio_ag[1]+" "+coeficientes_polinomio_ag[2]+" "+coeficientes_polinomio_ag[3]+" "+coeficientes_polinomio_ag[4]);
    System.out.println(coeficientes_polinomio_rf[0]+" "+coeficientes_polinomio_rf[1]+" "+coeficientes_polinomio_rf[2]+" "+coeficientes_polinomio_rf[3]+" "+coeficientes_polinomio_rf[4]);
    SPL=this.D0.D.getSPL(this.D0.R1, lineal, coeficientes_polinomio_ag, coeficientes_polinomio_rf, coeficientes_bin_ag, coeficientes_bin_rf);
    this.setVisible(false);
    new ResultadosSPLGUI3(this).setVisible(true);   
}//GEN-LAST:event_Siguiente


private void Anterior(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Anterior
    this.dispose();
    this.D0.setVisible(true);
}//GEN-LAST:event_Anterior

private void Cancelar(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Cancelar
    this.dispose();
}//GEN-LAST:event_Cancelar

public void chartMouseClicked(ChartMouseEvent evento) {
        int panel=this.jTabbedPane1.getSelectedIndex();
        if ((panel==4)){
            System.out.println("Click ");
            ChartPanel p=(ChartPanel)this.jPanel2;
            JFreeChart c = p.getChart();
            if (c != null) {                    
               if (evento.getEntity()!=null){
                    XYItemEntity xyitem=(XYItemEntity) evento.getEntity(); 
                    String serie=(String)xyitem.getDataset().getSeriesKey(xyitem.getSeriesIndex());
                    if (serie.contains("Vn")){
                        int fila=xyitem.getItem();
                        int columna=this.jTable3.getColumnModel().getColumnIndex(serie.substring(1, 4));
                        this.jTable3.changeSelection(fila,columna,false,false);
                        this.jTabbedPane1.setSelectedIndex(0);
                    }      
               }
            }
        }      
    }

    public void chartMouseMoved(ChartMouseEvent arg0) {
       
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closeButton3;
    private javax.swing.JButton closeButton4;
    private javax.swing.JButton jButton13;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTable jTable6;
    public javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
    
    public ResultadosSPLGUI1 D0;
    
    // Objetos
    public Numero N=new Numero();
   
    // Tablas
    private DefaultTableModel dtm1;
    private DefaultTableModel dtm2;
    private DefaultTableModel dtm3;
    private DefaultTableModel dtm4;
    private DefaultTableModel dtm5;
    private DefaultTableModel dtm6;
    
    private double[] coeficientes_polinomio_ag=new double[6];
    private double[] coeficientes_polinomio_rf=new double[6];
    
    private ArrayList<double[]> coeficientes_bin_ag=new ArrayList<double[]>();
    private ArrayList<double[]> coeficientes_bin_rf=new ArrayList<double[]>();
    
    private Color[] colores_AE=new Color[5];
    private Color[] colores_RF=new Color[5];
    
    public double[][] SPL=new double[2][5];
} 

