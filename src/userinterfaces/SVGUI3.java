package userinterfaces;

import RA.DatosRA2;
import RA.FicheroRA;
import RA.Login;
import RA.Numero;
import RA.Sector;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.PolarPlot;
import org.jfree.chart.plot.SpiderWebPlot;
import org.jfree.chart.renderer.DefaultPolarItemRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class SVGUI3 extends JDialog {
       
    public SVGUI3(SVGUI2 D) {
        super(D, true);
        this.D0=D;
        initComponents();  
        this.dtm1=(DefaultTableModel)this.jTable1.getModel();
        this.dtm2=(DefaultTableModel)this.jTable2.getModel();
        this.dtm3=(DefaultTableModel)this.jTable3.getModel();
        this.dtm4=(DefaultTableModel)this.jTable4.getModel();
        this.dtm5=(DefaultTableModel)this.jTable5.getModel();
        this.dtm6=(DefaultTableModel)this.jTable6.getModel();
        this.dtm7=(DefaultTableModel)this.jTable7.getModel();
        this.dtm8=(DefaultTableModel)this.jTable8.getModel();
        this.dtm9=(DefaultTableModel)this.jTable9.getModel();
        this.jTextField1.setText(this.D0.jTextField1.getText()); 
      
        RellenaTablas();
    } 
 
    
    // Centra los datos de una tabla
    public void Centrar (JTable tabla,int i, int f){
        DefaultTableCellRenderer c = new DefaultTableCellRenderer();
        c.setHorizontalAlignment(SwingConstants.CENTER);
        for (int columna=i;columna<=f;columna++){
            tabla.getColumnModel().getColumn(columna).setCellRenderer(c);     
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton6 = new javax.swing.JButton();
        jpInfoRosas = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel5 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jComboBox3 = new javax.swing.JComboBox();
        jTextField1 = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTable9 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable7 = new javax.swing.JTable();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTable8 = new javax.swing.JTable();
        jButton10 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();

        jButton6.setText("OK");

        jLabel18.setText("-  Los ficheros de rosas de viento deben ser ficheros de texto plano.");

        jLabel19.setText("-  Cada línea del fichero representa una dirección.");

        jLabel20.setText("-  Para cada direccion se introduce: ");

        jLabel21.setText("1. Direccion [º]");

        jLabel22.setText("2. Frecuencia / Energia para esa dirección [%]");

        jLabel23.setText("-  El separador de cada componente es el carácter   ");

        jTextField3.setEditable(false);
        jTextField3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField3.setText(" ;");

        jLabel24.setText("-  Es obligatorio que aparezca 1 separador por linea");

        jLabel25.setText("-  Ejemplo:");

        jTextArea1.setColumns(12);
        jTextArea1.setEditable(false);
        jTextArea1.setRows(2);
        jTextArea1.setText("000.0 ; 03.7\n022.5 ; 04.3\n045.0 ; 06.4\n067.5 ; 03.8\n090.0 ; 04.1\n112.5 ; 10.4\n135.0 ; 17.7\n157.5 ; 04.1\n180.0 ; 04.1\n202.5 ; 04.7\n225.0 ; 05.2\n247.5 ; 06.7\n270.0 ; 09.6\n292.5 ; 06.5\n315.0 ; 05.2\n337.5 ; 03.5");
        jScrollPane10.setViewportView(jTextArea1);

        javax.swing.GroupLayout jpInfoRosasLayout = new javax.swing.GroupLayout(jpInfoRosas);
        jpInfoRosas.setLayout(jpInfoRosasLayout);
        jpInfoRosasLayout.setHorizontalGroup(
            jpInfoRosasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpInfoRosasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpInfoRosasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19)
                    .addGroup(jpInfoRosasLayout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addGroup(jpInfoRosasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21)
                            .addComponent(jLabel22)))
                    .addComponent(jLabel20)
                    .addGroup(jpInfoRosasLayout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane10))
                    .addComponent(jLabel24)
                    .addGroup(jpInfoRosasLayout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18))
        );
        jpInfoRosasLayout.setVerticalGroup(
            jpInfoRosasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpInfoRosasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpInfoRosasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpInfoRosasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SECTOR VALIDO");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setForeground(new java.awt.Color(255, 0, 0));
        setIconImage(new ImageIcon("\\\\B2solar\\Datos\\Curva\\Imagenes\\GRA.png").getImage());
        setLocationByPlatform(true);
        setName("Cliente"); // NOI18N
        setResizable(false);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel1.setText("PARQUE");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel3.setText("AG");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel5.setText("TORRE");

        int n_objetos=this.D0.D0.objetos.size();
        for(int i=0;i<n_objetos;i++){
            String dato=this.D0.D0.objetos.get(i)[0];
            this.jComboBox2.insertItemAt(dato,i);
        }
        this.jComboBox2.setSelectedIndex(0);

        int n_torres=this.D0.D0.torres.size();
        for(int i=0;i<n_torres;i++){     
            String dato=this.D0.D0.torres.get(i)[0];     
            this.jComboBox3.insertItemAt(dato,i); 
        } 
        this.jComboBox3.setSelectedIndex(0);

        jTextField1.setBackground(new java.awt.Color(204, 204, 204));
        jTextField1.setEditable(false);
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jButton7.setBackground(new java.awt.Color(255, 255, 255));
        jButton7.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("\\\\B2solar\\Datos\\Curva\\Imagenes\\rosa.png" )));
        jButton7.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Graficar(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE))
                .addGap(41, 41, 41)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel3)
                        .addGap(71, 71, 71)
                        .addComponent(jLabel5)))
                .addGap(18, 18, 18)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(12, 12, 12)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setText(">>");
        jButton3.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Siguiente(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(255, 255, 255));
        jButton4.setText("<<");
        jButton4.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Atras(evt);
            }
        });

        jTabbedPane1.setBackground(new java.awt.Color(102, 102, 102));
        jTabbedPane1.setForeground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 1, 11));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        Sector S=new Sector();
        o=this.jComboBox2.getSelectedIndex();
        t=this.jComboBox3.getSelectedIndex();
        objeto=this.jComboBox2.getSelectedItem().toString();
        torre=this.jComboBox3.getSelectedItem().toString();
        ArrayList<String[]> sectoraero=this.D0.D0.matrizaeros.get(o).get(1);
        ArrayList<String[]> sectortorre=this.D0.D0.matriztorres.get(t).get(1);
        svnovalido=S.getSVFinal(sectoraero, sectortorre);
        this.jPanel6=createPanel(objeto,torre,svnovalido);
        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 443, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 383, Short.MAX_VALUE)
        );

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "[ º", "º ]"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTable3);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel4.setText("SECTORES VÁLIDOS");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel14.setText("  SECTOR VÁLIDO LIBRE DE ESTELAS EN TR Y AG");

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTable9.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "[ º", "º ]"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane9.setViewportView(jTable9);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12));
        jLabel2.setText("Site Calibration");

        jTextField2.setEditable(false);
        jTextField2.setText(" 0 : 10 ");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addContainerGap(20, Short.MAX_VALUE)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel2))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(jLabel4))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(14, 14, 14))
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addComponent(jLabel14)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("GRÁFICO            ", jPanel1);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Id", "[x]", "[y]", "[z]", "[Hb]", "[Dn]"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(jTable4);
        jTable4.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTable4.getColumnModel().getColumn(1).setPreferredWidth(100);
        jTable4.getColumnModel().getColumn(2).setPreferredWidth(100);
        jTable4.getColumnModel().getColumn(3).setPreferredWidth(100);
        jTable4.getColumnModel().getColumn(4).setPreferredWidth(50);
        jTable4.getColumnModel().getColumn(5).setPreferredWidth(50);

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel15.setText("  OBSTÁCULOS CONSIDERADOS");

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("\\\\B2solar\\Datos\\Curva\\Imagenes\\printer.png" )));
        jButton2.setToolTipText("Imprimir");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PrintObs(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(53, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(171, Short.MAX_VALUE)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(245, 245, 245))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(55, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("OBSTÁCULOS                          ", jPanel9);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Anchura", "Altura", "Distancia", "Dirección", "[ º", "º ]"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "[", "]"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(jTable5);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel11.setText("  SECTORES INVALIDADOS EN TORRE DE REFERENCIA");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel12.setText("SECTORES");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel13.setText("NO VALIDOS");

        jButton8.setBackground(new java.awt.Color(255, 255, 255));
        jButton8.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("\\\\B2solar\\Datos\\Curva\\Imagenes\\printer.png" )));
        jButton8.setToolTipText("Imprimir");
        jButton8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PrintTR(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 462, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel13)
                                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel12)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(10, 10, 10)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel11)
                .addGap(41, 41, 41)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );

        jTabbedPane1.addTab("TABLA TR                 ", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Anchura", "Altura", "Distancia", "Dirección", "[ º", "º ]"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jTable6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "[", "]"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(jTable6);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel8.setText("  SECTORES INVALIDADOS EN AEROGENERADOR DE ENSAYO");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel9.setText("SECTORES");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel10.setText("NO VALIDOS");

        jButton9.setBackground(new java.awt.Color(255, 255, 255));
        jButton9.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("\\\\B2solar\\Datos\\Curva\\Imagenes\\printer.png" )));
        jButton9.setToolTipText("Imprimir");
        jButton9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PrintAG(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 462, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel10)
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel8)
                .addGap(41, 41, 41)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(57, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("TABLA AERO             ", jPanel3);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        for (int i=0;i<this.jTable7.getRowCount();i++){ 
            String[] dato=new String[2];          
            dato[0]=this.jTable7.getValueAt(i, 0).toString();
            dato[1]=this.jTable7.getValueAt(i, 1).toString();
            rosa.add(dato);      
        }       
        for (int i=0;i<this.jTable8.getRowCount();i++){  
            String[] dato=new String[2];           
            dato[0]=this.jTable8.getValueAt(i, 0).toString();
            dato[1]=this.jTable8.getValueAt(i, 1).toString();       
            rosa2.add(dato);    
        }         
        this.jPanel8=createPanelII(rosa,rosa2,svnovalido);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 350, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 342, Short.MAX_VALUE)
        );

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setText("  SECTOR VÁLIDO DEFINITIVO / ROSA");

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setText("+");
        jButton1.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Rosa(evt);
            }
        });

        jTable7.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "D [º]", "E [%]"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane7.setViewportView(jTable7);

        jTable8.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "D [º]", "F [%]"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane8.setViewportView(jTable8);

        jButton10.setBackground(new java.awt.Color(255, 255, 255));
        jButton10.setText("+");
        jButton10.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Rosa2(evt);
            }
        });

        jLabel6.setText("ENERGÍA");

        jLabel7.setText("FREC.");

        jLabel17.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("\\\\B2solar\\Datos\\Curva\\Imagenes\\Information.png" )));
        jLabel17.setToolTipText("Información de Formato");
        jLabel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Formato(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ManoSi(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ManoNO(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(222, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                                    .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))
                                .addGap(36, 36, 36))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jLabel6)
                                .addGap(53, 53, 53)
                                .addComponent(jLabel7)
                                .addGap(70, 70, 70)))
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1)
                            .addComponent(jButton10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane7, 0, 0, Short.MAX_VALUE)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)))
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );

        jTabbedPane1.addTab("GRÁFICO ROSA               ", jPanel7);

        jButton5.setBackground(new java.awt.Color(255, 255, 255));
        jButton5.setText("CANCELAR");
        jButton5.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Salir(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 431, Short.MAX_VALUE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 495, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jButton4)
                    .addComponent(jButton3))
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
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-668)/2, (screenSize.height-658)/2, 668, 658);
    }// </editor-fold>//GEN-END:initComponents

private void Siguiente(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Siguiente
    if (sv.size()!=0){
        this.setVisible(false);
        new SVGUI4(this).setVisible(true);    
    } else{
    
    }    
}//GEN-LAST:event_Siguiente

private void Atras(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Atras
    dispose();
    this.D0.setVisible(true);
}//GEN-LAST:event_Atras

private void Salir(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Salir
    dispose();
}//GEN-LAST:event_Salir

private void Graficar(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Graficar
   try{
        Sector S=new Sector();
        o=this.jComboBox2.getSelectedIndex();
        t=this.jComboBox3.getSelectedIndex();
        System.out.println("o="+o);
        System.out.println("t="+t);
        objeto=this.jComboBox2.getSelectedItem().toString();
        torre=this.jComboBox3.getSelectedItem().toString();
        ArrayList<String[]> sectoraero=this.D0.D0.matrizaeros.get(o).get(1);
        ArrayList<String[]> sectortorre=this.D0.D0.matriztorres.get(t).get(1);
        svnovalido=S.getSVFinal(sectoraero, sectortorre);
        ChartPanel p=(ChartPanel)this.jPanel6;
        p.setChart(this.createChart(objeto,torre,svnovalido));
        ChartPanel p2=(ChartPanel)this.jPanel8;
        p2.setChart(this.createChartII(rosa,rosa2,svnovalido));
        RellenaTablas();
       
   } catch (Exception e){
        e.printStackTrace();
   }   
}//GEN-LAST:event_Graficar

private void PrintObs(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PrintObs
try {
    boolean ok=this.jTable4.print();
    if (ok){
        JOptionPane.showMessageDialog(this, "Impresión Finalizada", "INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);      
    } else{
        JOptionPane.showMessageDialog(this, "Fallo en la impresión", "ERROR", JOptionPane.ERROR_MESSAGE);      
    }
} catch (PrinterException e) {
       e.printStackTrace();
}
}//GEN-LAST:event_PrintObs

private void PrintTR(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PrintTR
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
}//GEN-LAST:event_PrintTR

private void PrintAG(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PrintAG
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
}//GEN-LAST:event_PrintAG

private void Rosa(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Rosa
String u=Login.getUsuario();
try {
    FicheroRA F=new FicheroRA();
    JFileChooser fileChooser = new JFileChooser("Z:\\"+u+"\\PRIVADA\\CALIDAD\\ASUNTOS");
    int seleccion = fileChooser.showOpenDialog(this);
    if (seleccion == JFileChooser.APPROVE_OPTION){
        File fichero = fileChooser.getSelectedFile();
        String ruta=fichero.getPath();
        if (ruta!=null){
            ArrayList <String []> rosaf = F.loadRosa(ruta);
            if (!rosaf.get(0)[0].equals("mal")){
                 rosa=new ArrayList<String[]>();
                 dtm7.setRowCount(rosaf.size());
                 for (int i=0;i<rosaf.size();i++){
                     this.jTable7.setValueAt(rosaf.get(i)[0], i, 0);
                     this.jTable7.setValueAt(rosaf.get(i)[1], i, 1);
                 }
                 Centrar(jTable7,0,1);
                 for (int i=0;i<dtm7.getRowCount();i++){
                    String[] dato=new String[2];
                    dato[0]=dtm7.getValueAt(i, 0).toString();
                    dato[1]=dtm7.getValueAt(i, 1).toString();
                    rosa.add(dato);
                 }
                 ChartPanel p=(ChartPanel)this.jPanel8;
                 p.setChart(this.createChartII(rosa,rosa2,svnovalido));
            } else {
                JOptionPane.showMessageDialog(this, "Formato de fichero no válido","ADVERTENCIA",JOptionPane.WARNING_MESSAGE);
            }     
       }
   }  
} catch (FileNotFoundException ex){
        ex.printStackTrace();
}
}//GEN-LAST:event_Rosa

private void Rosa2(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Rosa2
String u=Login.getUsuario();
try {
    FicheroRA F=new FicheroRA();
    JFileChooser fileChooser = new JFileChooser("Z:\\"+u+"\\PRIVADA\\CALIDAD\\ASUNTOS");
    int seleccion = fileChooser.showOpenDialog(this);
    if (seleccion == JFileChooser.APPROVE_OPTION){
        File fichero = fileChooser.getSelectedFile();
        String ruta=fichero.getPath();
        if (ruta!=null){
            ArrayList <String []> rosaf = F.loadRosa(ruta);
            if (!rosaf.get(0)[0].equals("mal")){
                 dtm8.setRowCount(rosaf.size());
                 rosa2=new ArrayList<String[]>();
                 for (int i=0;i<rosaf.size();i++){
                     this.jTable8.setValueAt(rosaf.get(i)[0], i, 0);
                     this.jTable8.setValueAt(rosaf.get(i)[1], i, 1);
                 }
                 Centrar(jTable8,0,1);
                 for (int i=0;i<dtm8.getRowCount();i++){
                    String[] dato=new String[2];
                    dato[0]=dtm8.getValueAt(i, 0).toString();
                    dato[1]=dtm8.getValueAt(i, 1).toString();
                    rosa2.add(dato);
                 }
                 ChartPanel p=(ChartPanel)this.jPanel8;
                 p.setChart(this.createChartII(rosa,rosa2,svnovalido));
            } else {
                JOptionPane.showMessageDialog(this, "Formato de fichero no válido","ADVERTENCIA",JOptionPane.WARNING_MESSAGE);
            }     
       }
   }  
} catch (FileNotFoundException ex){
        ex.printStackTrace();
}
}//GEN-LAST:event_Rosa2

private void Formato(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Formato
   JOptionPane.showMessageDialog(this, this.jpInfoRosas, "Información", JOptionPane.INFORMATION_MESSAGE);
}//GEN-LAST:event_Formato

private void ManoSi(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ManoSi
    Component root = SwingUtilities.getRoot(this);  
    root.setCursor( Cursor.getPredefinedCursor( Cursor.HAND_CURSOR ) );
}//GEN-LAST:event_ManoSi

private void ManoNO(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ManoNO
    Component root = SwingUtilities.getRoot(this);  
    root.setCursor( Cursor.getPredefinedCursor( Cursor.DEFAULT_CURSOR ) );
}//GEN-LAST:event_ManoNO

 public JPanel createPanel(String objeto,String torre,ArrayList<String[]> limites) {
     JFreeChart chart = createChart(objeto,torre,limites);
     ChartPanel chartPanel = new ChartPanel(chart);
     chartPanel.setPreferredSize(new java.awt.Dimension(450,400));
     return chartPanel;
 }
 
 public void RellenaTablas(){
 Numero N=new Numero();
 Sector S=new Sector();
  
 try{
    // Se inicializan las tablas
    this.dtm1.setRowCount(0);
    this.dtm2.setRowCount(0);
    this.dtm3.setRowCount(0);
    this.dtm4.setRowCount(0);
    this.dtm5.setRowCount(0);
    this.dtm6.setRowCount(0);
    
    ordenar(svnovalido,0,svnovalido.size()-1);
    sv=getFinal(svnovalido);
    for (int i=0;i<sv.size();i++){
        System.out.println(sv.get(i)[0]+" "+sv.get(i)[1]);
              
    }
    // Sector valido
    this.dtm3.setRowCount(sv.size());
    for (int i=0;i<sv.size();i++){
        this.jTable3.setValueAt(N.redondear(sv.get(i)[0],2), i, 0);
        this.jTable3.setValueAt(N.redondear(sv.get(i)[1],2), i, 1); 
    }
    Centrar(this.jTable3,0,1);
    
    // Site Calibration
    if (this.jTextField2.getText().length()!=0){
        
        binado=this.jTextField2.getText();
        double centro=Double.parseDouble(binado.substring(0,binado.indexOf(":")));
        double longitud=Double.parseDouble(binado.substring(binado.indexOf(":")+1));
        
        // Se escriben los sectores redondeados
        sectores_redondeados = S.getSectorRedondeado(sv, DatosRA2.getSector(centro, longitud));
        this.dtm9.setRowCount(sectores_redondeados.size());
        for (int i=0;i<sectores_redondeados.size();i++){
            this.jTable9.setValueAt(sectores_redondeados.get(i)[0], i, 0);
            this.jTable9.setValueAt(sectores_redondeados.get(i)[1], i, 1); 
        }
        Centrar(this.jTable9,0,1);
    }
    
    // Sectores invalidados AG
    int n_ag=this.D0.D0.matrizaeros.get(o).get(0).size();
    dtm2.setRowCount(n_ag);  
    for (int k=0;k<n_ag;k++){
         for (int i=0;i<7;i++){
             if (i>=3){
                dtm2.setValueAt(N.formato("0.00",Double.parseDouble(this.D0.D0.matrizaeros.get(o).get(0).get(k)[i])), k, i);
             } else{
                dtm2.setValueAt(this.D0.D0.matrizaeros.get(o).get(0).get(k)[i], k, i);
             }     
         }
    } 
    Centrar(this.jTable2,0,6);
    
    // Limites AG
    int n_ag2=this.D0.D0.matrizaeros.get(o).get(1).size();
    this.dtm6.setRowCount(n_ag2);
    for (int i=0;i<n_ag2;i++){
        this.jTable6.setValueAt(N.redondear(Double.parseDouble(this.D0.D0.matrizaeros.get(o).get(1).get(i)[0]),2), i, 0);
        this.jTable6.setValueAt(N.redondear(Double.parseDouble(this.D0.D0.matrizaeros.get(o).get(1).get(i)[1]),2), i, 1);     
    }
    Centrar(this.jTable6,0,1);
    
    // Sectores invalidados TR
    int n_tr=this.D0.D0.matriztorres.get(t).get(0).size();
    dtm1.setRowCount(n_tr);  
    for (int k=0;k<n_tr;k++){
         for (int i=0;i<7;i++){
              if (i>=3){
                dtm1.setValueAt(N.formato("0.00",Double.parseDouble(this.D0.D0.matriztorres.get(t).get(0).get(k)[i])), k, i);
             } else{
                dtm1.setValueAt(this.D0.D0.matriztorres.get(t).get(0).get(k)[i], k, i);
             }
         }
    } 
    Centrar(this.jTable1,0,6);
    
    // Limites TR
    int n_tr2=this.D0.D0.matriztorres.get(t).get(1).size();
    this.dtm5.setRowCount(n_tr2);
    for (int i=0;i<n_tr2;i++){
        this.jTable5.setValueAt(N.formato("0.00",Double.parseDouble(this.D0.D0.matriztorres.get(t).get(1).get(i)[0])), i, 0);
        this.jTable5.setValueAt(N.formato("0.00",Double.parseDouble(this.D0.D0.matriztorres.get(t).get(1).get(i)[1])), i, 1);     
    }
    Centrar(this.jTable5,0,1);
    
    // Obstaculos
    int n_objetos=this.D0.D0.objetos.size();
    this.dtm4.setRowCount(n_objetos);
    for (int i=0;i<n_objetos;i++){
        for (int j=0;j<6;j++){
            this.jTable4.setValueAt(this.D0.D0.objetos.get(i)[j], i, j);
        }
    }
    Centrar(this.jTable4,0,5);
 } catch (SQLException e){
    e.printStackTrace();
    
 }   
 }
 
// Ordena la matriz por la componente x
 public void ordenar(ArrayList<String[]> a, int izq, int der) {
    int i = izq;
    int j = der;
    double pivote = Double.parseDouble(a.get( (izq + der) / 2)[0]);
    do {
        while (Double.parseDouble(a.get(i)[0])<pivote) i++;
        while (Double.parseDouble(a.get(j)[0])>pivote) j--;
        if (i <= j) {
            String[] aux = new String[2];
            String[] pj = new String[2];
            for (int h=0;h<2;h++){
                aux[h]=a.get(i)[h];
            }
            for (int h=0;h<2;h++){
                pj[h]=a.get(j)[h];
            }
            a.set(i,pj);
            a.set(j, aux);
            i++;
            j--;
        }
    }
    while (i <= j);
    if (izq < j) {
      ordenar(a, izq, j);
    }
    if (i < der) {
      ordenar(a, i, der);
    }
  }
 
 // Ordena los sectores no validos por la componente y
 public void ordenary(ArrayList<String[]> novalido, int izq, int der) {
    int i = izq;
    int j = der;
    double pivote = Double.parseDouble(novalido.get( (izq + der) / 2)[1]);
    do {
        while (Double.parseDouble(novalido.get(i)[1])<pivote) i++;
        while (Double.parseDouble(novalido.get(j)[1])>pivote) j--;
        if (i <= j) {
            String[] aux = new String[2];
            String[] pj = new String[2];
            for (int h=0;h<2;h++){
                aux[h]=novalido.get(i)[h];
            }
            for (int h=0;h<2;h++){
                pj[h]=novalido.get(j)[h];
            }
            novalido.set(i,pj);
            novalido.set(j, aux);
            i++;
            j--;
        }
    }
    while (i <= j);
    if (izq < j) {
      ordenary(novalido, izq, j);
    }
    if (i < der) {
      ordenary(novalido, i, der);
    }
  }
 
 public ArrayList<double[]> getFinal(ArrayList<String[]> novalido){
     ArrayList<double[]> valido=new ArrayList<double[]>();
     this.ordenary(novalido, 0, novalido.size()-1);
     if(novalido.size()!=0){
        double[] dato=new double[2];
        for (int i=0;i<novalido.size()-1;i++){
            dato=new double[2];
            dato[0]=Double.parseDouble(novalido.get(i)[1]);
            dato[1]=Double.parseDouble(novalido.get(i+1)[0]);
            valido.add(i, dato);
        }
        dato=new double[2];
        dato[0]=Double.parseDouble(novalido.get(novalido.size()-1)[1]);
        dato[1]=Double.parseDouble(novalido.get(0)[0]);
        valido.add(novalido.size()-1,dato);
     }
     return valido;
   
 }
 
private JFreeChart createChart(String objeto,String torre,ArrayList<String[]> limites) {
    CategoryDataset dataset=createDataset(limites);
    SpiderWebPlot plot = new SpiderWebPlot(dataset);
    plot.setInteriorGap(0.1);
    plot.setOutlineVisible(false);
    plot.setHeadPercent(0.0005);
    plot.setSeriesOutlinePaint(Color.BLUE);
    plot.setSeriesPaint(Color.BLUE);
    plot.setToolTipGenerator(new StandardCategoryToolTipGenerator());
    plot.setAxisLinePaint(Color.WHITE);
    plot.setLabelPaint(Color.WHITE);   
    JFreeChart chart = new JFreeChart(objeto+" / "+torre, new Font("Arial", Font.BOLD, 16), plot, false);
    chart.setBackgroundPaint(Color.WHITE);
    return chart;
}

   private CategoryDataset createDataset(ArrayList<String[]> limites) {
        // Creo una categoria para cada sector de 0 a 360
        String[] category = new String[360];
        for (int i=0;i<360;i++){
            category[i]="C"+i;
        }
        // Me creo el vector de validos de cada grado
        int[] valido=Valido(limites);
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i=0;i<360;i++){
            dataset.addValue(valido[i], "", category[i]);
        }
        return dataset;
   }

public int[] Valido(ArrayList<String[]> limites){
    int[] valido=new int[360];
    for (int grado=0;grado<360;grado++){
        valido[grado]=1;
        for (int j=0;j<limites.size();j++){
            double linf=Double.parseDouble(limites.get(j)[0]);
            double lsup=Double.parseDouble(limites.get(j)[1]);
            if (linf<lsup){
                if ((grado>=linf) && (grado<=lsup)){
                    valido[grado]=0;
                }
            } else{ // paso por cero
                if (((grado>=linf) && (grado<=360)) || ((grado>=0) && (grado<=lsup))){
                    valido[grado]=0;
                }
            }
        }
    }
    return valido;
}

public JPanel createPanelII(ArrayList<String[]> rosa,ArrayList<String[]> rosa2,ArrayList<String[]> limites) {
     JFreeChart chart = createChartII(rosa,rosa2,limites);
     ChartPanel chartPanel = new ChartPanel(chart);
     chartPanel.setPreferredSize(new java.awt.Dimension(380,340));
     return chartPanel;
 }

private JFreeChart createChartII(ArrayList<String[]> rosa,ArrayList<String[]> rosa2,ArrayList<String[]> limites) {
   
    XYDataset dataset=createDatasetII(rosa,rosa2,limites); 
   
    JFreeChart chart = ChartFactory.createPolarChart("", dataset, true, true, false);
    chart.setBackgroundPaint(Color.white);
    PolarPlot plot = (PolarPlot) chart.getPlot();
    plot.setBackgroundPaint(Color.white);

    DefaultPolarItemRenderer renderer=(DefaultPolarItemRenderer) plot.getRenderer();
    renderer.setShapesVisible(false);

    plot.setAngleGridlinePaint(Color.lightGray);
    plot.setRadiusGridlinePaint(Color.lightGray);
   
    NumberAxis rango = (NumberAxis) plot.getAxis();
    rango.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
    rango.setTickUnit(new NumberTickUnit(10));
    
    return chart;
}

private XYDataset createDatasetII(ArrayList<String[]> rosa,ArrayList<String[]> rosa2,ArrayList<String[]> limites) {
        XYSeriesCollection result = new XYSeriesCollection();
        XYSeries s1 = new XYSeries("Rosa de Energias");
        double max=0;
        System.out.println("Tamaño de rosa1 "+rosa.size());
        for (int i=0;i<rosa.size();i++){
            if (Double.parseDouble(rosa.get(i)[1])>max){max=Double.parseDouble(rosa.get(i)[1]);}
            s1.add(Double.parseDouble(rosa.get(i)[0]), Double.parseDouble(rosa.get(i)[1]));
        }
        if (rosa.size()!=0){
            result.addSeries(s1);
        }
        
        XYSeries s2 = new XYSeries("Rosa de Vientos");
        System.out.println("Tamaño de rosa2 "+rosa2.size());
        for (int i=0;i<rosa2.size();i++){
            if (Double.parseDouble(rosa2.get(i)[1])>max){max=Double.parseDouble(rosa2.get(i)[1]);}
            s2.add(Double.parseDouble(rosa2.get(i)[0]), Double.parseDouble(rosa2.get(i)[1]));
        }
        if (rosa2.size()!=0){
            result.addSeries(s2);
        }
        
        // Me creo el vector de validos de cada grado
        int[] valido=Valido(limites);
        XYSeries s3 = new XYSeries("Sector Válido");
        for (int i=0;i<360;i++){
            if ((rosa.size()!=0) || (rosa2.size()!=0)){
                s3.add(i, valido[i]*max);
            } else{
                s3.add(i, valido[i]);
                System.out.println("Añado "+valido[i]);
            }    
        }
        System.out.println("Tamaño de sv "+s3.getItemCount());
        result.addSeries(s3);
        
        return result;
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
    public javax.swing.JComboBox jComboBox2;
    public javax.swing.JComboBox jComboBox3;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTable jTable6;
    private javax.swing.JTable jTable7;
    private javax.swing.JTable jTable8;
    private javax.swing.JTable jTable9;
    private javax.swing.JTextArea jTextArea1;
    public javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JPanel jpInfoRosas;
    // End of variables declaration//GEN-END:variables
    public SVGUI2 D0;
    private String torre="";
    private String objeto="";
    public String binado="";
    private DefaultTableModel dtm1;   
    private DefaultTableModel dtm2; 
    private DefaultTableModel dtm3; 
    private DefaultTableModel dtm4; 
    private DefaultTableModel dtm5; 
    private DefaultTableModel dtm6; 
    private DefaultTableModel dtm7; 
    private DefaultTableModel dtm8; 
    private DefaultTableModel dtm9; 

    public ArrayList<String[]> svnovalido=new ArrayList<String[]>();
    public ArrayList<double[]> sv=new ArrayList<double[]>();
    public ArrayList<double[]> sectores_redondeados=new ArrayList<double[]>();
    
    public int o;
    public int t;    

    public ArrayList<String> etiquetasobs=new ArrayList<String>();
    public ArrayList<String[]> rosa=new ArrayList<String[]>();
    public ArrayList<String[]> rosa2=new ArrayList<String[]>();
    
}   
