
package userinterfaces;

import RA.Asunto;
import RA.Login;
import RA.Numero;
import RA.Site;
import RA.ConfiguracionRA;
import RA.DatosRA;
import RA.Etiqueta;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.CombinedDomainCategoryPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;


public class VDatosGUI extends JDialog implements ChartMouseListener{
    
    public VDatosGUI(Frame parent,int id) {
       super(parent,true);
       this.Id=id;
       this.inicio=1;
       this.fin=inicio+4319;
       initComponents();      
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jTextField4 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jComboBox2 = new javax.swing.JComboBox();
        jLabel34 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox();
        jPanel11 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jPanel17 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("VER DATOS / BANCAL");
        setBackground(new java.awt.Color(175, 30, 30));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setForeground(new java.awt.Color(255, 0, 0));
        setLocationByPlatform(true);
        setName("Cliente"); // NOI18N

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel33.setText("  Asunto: ");

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setText("REFRESH");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Refresh(evt);
            }
        });

        jTextField4.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel3.setText("Inicio");

        jLabel4.setText("Fin");

        jTextField5.setBackground(new java.awt.Color(204, 204, 204));
        jTextField5.setEditable(false);
        jTextField5.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));
        try{
            // Responsable=Id, Tipo=3, Activo=true
            ArrayList <String> asuntos=new ArrayList <String>();
            int rol=Login.getRol();
            if (rol==1){ // admin
                asuntos=A.getAsuntos(0, 8, true);
            } else {
                asuntos=A.getAsuntos(Id, 8, true);
            }
            int n=asuntos.size();
            for(int i=0;i<n;i++){
                String dato=asuntos.get(i);
                this.jComboBox2.insertItemAt(dato,i+1);
            }
            this.jComboBox2.setSelectedIndex(0);
        }catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Fallo al leer la base de datos", "ERROR", JOptionPane.ERROR_MESSAGE);    
        }
        jComboBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CambioAsunto(evt);
            }
        });

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel34.setText("  Site: ");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));
        jComboBox3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CambioSite(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(492, 492, 492)
                        .addComponent(jButton1))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel33)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel34)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap())
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
                "Id", "Fecha_Hora", "valido", "RF", "S1", "S2", "S3", "S4", "S5", "S6", "S7", "S8", "S9", "S10", "S11", "S12", "S13", "S14", "S15", "S16", "S17", "S18", "S19", "S20", "S21", "null", "null", "null", "null", "null", "null", "null", "null", "null", "null", "null", "null", "null", "null", "null", "null", "null", "null", "null", "null", "null", "null", "null", "null", "null", "Title 51", "Title 52", "Title 53", "Title 54"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable3.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable3.setColumnSelectionAllowed(true);
        this.dtm3=(DefaultTableModel)this.jTable3.getModel();
        this.Graficar();
        jScrollPane3.setViewportView(jTable3);
        jTable3.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jTable3.getColumnModel().getColumn(1).setMinWidth(150);
        jTable3.getColumnModel().getColumn(1).setPreferredWidth(150);
        jTable3.getColumnModel().getColumn(1).setMaxWidth(150);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel1.setText("DATOS BRUTOS");

        jButton9.setText(">>");
        jButton9.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                NextTabla(evt);
            }
        });

        jButton10.setText("<<");
        jButton10.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PrevTabla(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 894, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 836, Short.MAX_VALUE)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 561, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton10)
                    .addComponent(jButton9))
                .addContainerGap())
        );

        jTabbedPane1.addTab("DATOS                                               ", jPanel1);

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel8.setText("Superior");

        jTextField2.setBackground(new java.awt.Color(204, 204, 204));
        jTextField2.setEditable(false);
        jTextField2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Superior(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Poner(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Quitar(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel9.setText("Inferior");

        jTextField3.setBackground(new java.awt.Color(204, 204, 204));
        jTextField3.setEditable(false);
        jTextField3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Inferior(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Poner2(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Quitar2(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(53, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 902, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 536, Short.MAX_VALUE)
        );

        jButton7.setText("<<");
        jButton7.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PrevCombinado(evt);
            }
        });

        jButton8.setText(">>");
        jButton8.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                NextCombinado(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 848, Short.MAX_VALUE)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton8)
                    .addComponent(jButton7))
                .addContainerGap())
        );

        jTabbedPane1.addTab("COMBINADO                                 ", jPanel15);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 931, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 668, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jButton18.setBackground(new java.awt.Color(255, 255, 255));
        jButton18.setText("CANCELAR");
        jButton18.setAlignmentX(0.5F);
        jButton18.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton18.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Cerrar(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 955, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-983)/2, (screenSize.height-890)/2, 983, 890);
    }// </editor-fold>//GEN-END:initComponents

            
public void RellenarTabla(){
     
    Numero N=new Numero();
      
    DefaultTableCellRenderer c = new DefaultTableCellRenderer();
    c.setHorizontalAlignment(SwingConstants.CENTER);
    
    // Relleno la tabla
    int ndatos=datos.size();   
    int nequipos=codigos.size();
     
    dtm3.setRowCount(ndatos);
     
    // Columnas
    for (int j=0;j<nequipos;j++){ 
        this.jTable3.getColumnModel().getColumn(j+4).setHeaderValue(codigos.get(j));
    }
     
    for(int i=0;i<ndatos;i++){
        // Id+Fecha_Hora
        this.jTable3.setValueAt(datos.get(i)[0], i, 0);  
        String fecha_hora=datos.get(i)[1];
        this.jTable3.setValueAt(fecha_hora.substring(0,8)+" "+fecha_hora.substring(8), i, 1);  
        
        // Identificadores
        for (int j=2;j<4;j++){ 
             this.jTable3.setValueAt(datos.get(i)[j], i, j);    
        }
        // Series
        for (int j=0;j<nequipos;j++){ 
             double v=Double.parseDouble(datos.get(i)[j+4]);
             this.jTable3.setValueAt(N.formato("0.00000", v), i, j+4);           
        }  
    }
    
    // Se ocultan las columnas sobrantes
    for (int j=nequipos+4;j<54;j++){
         this.jTable3.getColumnModel().getColumn(j).setMaxWidth(0);
         this.jTable3.getColumnModel().getColumn(j).setMinWidth(0);
         this.jTable3.getColumnModel().getColumn(j).setPreferredWidth(0);    
    } 
    for (int i=0;i<54;i++){
        this.jTable3.getColumnModel().getColumn(i).setCellRenderer(c);   
    }
}





private void NextCombinado(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NextCombinado
if (this.jComboBox2.getSelectedIndex()!=0){

    try{
        inicio=fin+1;
        fin=inicio+999;
        datos=D.getDatos(asunto,inicio, fin,codigos,tabla_datos);      
        this.RellenarTabla();
        this.ActualizarGraficoCombinado();     
    } catch(SQLException e){
        e.printStackTrace();
    }
}
}//GEN-LAST:event_NextCombinado

private void PrevCombinado(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PrevCombinado
if (this.jComboBox2.getSelectedIndex()!=0){
 
    try{
        fin=inicio-1;
        inicio=fin-999;
        datos=D.getDatos(asunto,inicio, fin,codigos,tabla_datos);   
        this.RellenarTabla();
        this.ActualizarGraficoCombinado();     
    } catch(SQLException e){
        e.printStackTrace();
    }
}
}//GEN-LAST:event_PrevCombinado

private void Quitar2(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Quitar2
    Component root = SwingUtilities.getRoot(this);
    root.setCursor( Cursor.getPredefinedCursor( Cursor.DEFAULT_CURSOR ) );
}//GEN-LAST:event_Quitar2

private void Poner2(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Poner2
    Component root = SwingUtilities.getRoot(this);
    root.setCursor( Cursor.getPredefinedCursor( Cursor.HAND_CURSOR ) );
}//GEN-LAST:event_Poner2

private void Inferior(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Inferior
        javax.swing.JPanel P = new javax.swing.JPanel();
        P.setBackground(new java.awt.Color(255,255,255));
       
        // Se añaden las series
        int n=codigos.size();
        for (int j=0;j<n;j++){
               javax.swing.JCheckBox jC=new javax.swing.JCheckBox(codigos.get(j), false);
               P.add(jC);  
        }      
        P.setLayout(new GridLayout(n, 1));
        P.setVisible(true);
        int respuesta=JOptionPane.showConfirmDialog(this, P, "POSIBLES SERIES A GRAFICAR", JOptionPane.OK_CANCEL_OPTION,javax.swing.JOptionPane.PLAIN_MESSAGE,new ImageIcon(Toolkit.getDefaultToolkit().getImage("\\\\B2solar\\Datos\\Curva\\Imagenes\\Grafico.png" )));
        if (respuesta==0){
             inferior=new ArrayList<String>();
             Component c[]=P.getComponents();
             String ginferior="";
             for (int i=0;i<c.length;i++){
                if (((javax.swing.JCheckBox)c[i]).isSelected()){
                     String s=((javax.swing.JCheckBox)c[i]).getText();
                     if (ginferior.length()!=0){ginferior=ginferior+" / ";}
                     ginferior=ginferior+s;
                     inferior.add(s);
                }
             } 
             this.jTextField3.setText(ginferior);  
             ChartPanel p=(ChartPanel)this.jPanel17;
             p.setChart(this.createChartCombinado(superior,inferior));                        
         }
}//GEN-LAST:event_Inferior

private void Quitar(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Quitar
    Component root = SwingUtilities.getRoot(this);
    root.setCursor( Cursor.getPredefinedCursor( Cursor.DEFAULT_CURSOR ) );
}//GEN-LAST:event_Quitar

private void Poner(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Poner
    Component root = SwingUtilities.getRoot(this);
    root.setCursor( Cursor.getPredefinedCursor( Cursor.HAND_CURSOR ) );
}//GEN-LAST:event_Poner

private void Superior(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Superior
        javax.swing.JPanel P = new javax.swing.JPanel();
        P.setBackground(new java.awt.Color(255,255,255));
       
        // Se añaden las series
        int n=codigos.size();
        for (int j=0;j<n;j++){
               javax.swing.JCheckBox jC=new javax.swing.JCheckBox(codigos.get(j), false);
               P.add(jC);  
        }      
        P.setLayout(new GridLayout(n, 1));
        P.setVisible(true);
        int respuesta=JOptionPane.showConfirmDialog(this, P, "POSIBLES SERIES A GRAFICAR", JOptionPane.OK_CANCEL_OPTION,javax.swing.JOptionPane.PLAIN_MESSAGE,new ImageIcon(Toolkit.getDefaultToolkit().getImage("\\\\B2solar\\Datos\\Curva\\Imagenes\\Grafico.png" )));
        if (respuesta==0){
             superior=new ArrayList<String>();
             Component c[]=P.getComponents();
             String gsuperior="";
             for (int i=0;i<c.length;i++){
                if (((javax.swing.JCheckBox)c[i]).isSelected()){
                     String s=((javax.swing.JCheckBox)c[i]).getText();
                     if (gsuperior.length()!=0){gsuperior=gsuperior+" / ";}
                     gsuperior=gsuperior+s;
                     superior.add(s);
                }
             } 
             this.jTextField2.setText(gsuperior);  
             ChartPanel p=(ChartPanel)this.jPanel17;
             p.setChart(this.createChartCombinado(superior,inferior));
         }
}//GEN-LAST:event_Superior

private void Cerrar(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Cerrar
    dispose();
}//GEN-LAST:event_Cerrar

private void Refresh(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Refresh
if ((this.jComboBox2.getSelectedIndex()!=0) && (this.jComboBox3.getSelectedIndex()!=0) && (this.jTextField4.getText().length()!=0)&& (this.jTextField5.getText().length()!=0)){
    try{
       
        codigos=C.getDescripcion(asunto,tabla_descripcion);
        String fecha_hora="%"+this.jTextField4.getText()+"%";    
        inicio=D.getId(asunto,fecha_hora,tabla_datos);
        fin=inicio+999;
        datos=D.getDatos(asunto,inicio,fin,codigos,tabla_datos);        
    } catch(SQLException e){
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Fallo al consultar la base de datos","ERROR",JOptionPane.ERROR_MESSAGE);
    }
    this.RellenarTabla();
    this.ActualizarGraficoCombinado();
}
}//GEN-LAST:event_Refresh

private void NextTabla(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NextTabla
if (this.jComboBox2.getSelectedIndex()!=0){

    try{
        inicio=fin+1;
        fin=inicio+4319;
        datos=D.getDatos(asunto,inicio, fin,codigos,tabla_datos);      
        this.RellenarTabla();
        this.ActualizarGraficoCombinado();     
    } catch(SQLException e){
        e.printStackTrace();
    }
}
}//GEN-LAST:event_NextTabla

private void PrevTabla(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PrevTabla
if (this.jComboBox2.getSelectedIndex()!=0){
 
    try{
        fin=inicio-1;
        inicio=fin-4319;
        datos=D.getDatos(asunto,inicio, fin,codigos,tabla_datos);      
        this.RellenarTabla();
        this.ActualizarGraficoCombinado();     
    } catch(SQLException e){
        e.printStackTrace();
    }
}
}//GEN-LAST:event_PrevTabla

private void CambioAsunto(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CambioAsunto
    Limpiar();   
    if (this.jComboBox2.getSelectedIndex()!=0){
        try{
            // Calculo el numero de sites para ese asunto y relleno el combobox con ellos
            asunto=A.getId(this.jComboBox2.getSelectedItem().toString());
            
            // Inicializo el combo sites
            this.jComboBox3.removeAllItems();
            this.jComboBox3.insertItemAt("",0);
            
            // Cargo el combo sites
            ArrayList <Integer> sites=A.getSitesRA(asunto);
            int n=sites.size();
            for(int i=0;i<n;i++){
                String dato=S.getNombre(sites.get(i));
                this.jComboBox3.insertItemAt(dato,i+1);
            }
            this.jComboBox3.setSelectedIndex(0);
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Fallo al consultar la base de datos", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
   }      
  
    
}//GEN-LAST:event_CambioAsunto

private void CambioSite(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CambioSite
  
    if ((this.jComboBox3.getSelectedIndex()!=0) && (this.jComboBox3.getSelectedItem()!=null)){
       try{ 
           site=S.getId(this.jComboBox3.getSelectedItem().toString());
           
           tabla_datos="DatosSPL";
           tabla_descripcion="DescripcionSPL";
           if (site==105) {
               tabla_datos="DatosOCT";
               tabla_descripcion="DescripcionOCT";
           }
           
           // Relleno las fechas
           String Fprimera=D.getFprimera(asunto,tabla_datos);
           String Fultima=D.getFultima(asunto,tabla_datos);
           if ((Fprimera!=null) && (Fultima!=null)){
                this.jTextField4.setText(Fprimera);
                this.jTextField5.setText(Fultima);
           }
           
       } catch(SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Fallo al consultar la base de datos", "ERROR", JOptionPane.ERROR_MESSAGE);            
       }
    }
    
}//GEN-LAST:event_CambioSite

private void Limpiar(){
    this.jTextField4.setText(null);
    this.jTextField5.setText(null);
    this.jTextField5.setEditable(false);
  //  this.RellenarTabla();
  //  this.ActualizarGraficoCombinado();
}

private void Graficar(){
   superior=new ArrayList<String>();
   inferior=new ArrayList<String>();
   this.jPanel17=this.createPanelCombinado(superior,inferior);  
   
}


private void ActualizarGraficoCombinado(){
   if ((superior.size()!=0) && (inferior.size()!=0)){
       ChartPanel p=(ChartPanel)this.jPanel17;
       p.setChart(this.createChartCombinado(superior,inferior));    
    }   
}



// Crear el Panel Combinado
private JPanel createPanelCombinado(ArrayList superior,ArrayList inferior) {
     JFreeChart chart = createChartCombinado(superior,inferior);
     ChartPanel chartPanel = new ChartPanel(chart);
     chartPanel.setPreferredSize(new java.awt.Dimension(700, 500));
     chartPanel.addChartMouseListener(this);
     return chartPanel;
}

// Crear Grafico Combinado
public JFreeChart createChartCombinado(ArrayList superior,ArrayList inferior)   {  
        
        data1c=this.createDatasetCombinado(superior);
        data2c=this.createDatasetCombinado(inferior);
     
        NumberAxis rango1 = new NumberAxis("");
        rango1.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        LineAndShapeRenderer renderer1 = new LineAndShapeRenderer();
        renderer1.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());      
        renderer1.setBaseShapesVisible(false);
        renderer1.setBaseShapesFilled(true);
        
        CategoryPlot plot1 = new CategoryPlot(data1c, null, rango1, renderer1);
               
        NumberAxis rango2 = new NumberAxis("");
        rango2.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        LineAndShapeRenderer renderer2 = new LineAndShapeRenderer();
        renderer2.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());      
        renderer2.setBaseShapesVisible(false);
        renderer2.setBaseShapesFilled(true);
        
        CategoryPlot plot2 = new CategoryPlot(data2c, null, rango2, renderer2);
         
        NumberAxis rango3 = new NumberAxis("");
        rango3.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rango3.setTickUnit(new NumberTickUnit(10)); 
        rango3.setRange(-1.5, 2.5); 
       
                        
        Etiqueta E=new Etiqueta("Fecha",180);
        CombinedDomainCategoryPlot plot = new CombinedDomainCategoryPlot((CategoryAxis)E);
        plot.setBackgroundPaint(Color.WHITE);
        
        plot.setDomainGridlinePaint(Color.BLACK);
        plot.setRangeGridlinePaint(Color.BLACK);
        
        plot.add(plot1, 5);
        plot.add(plot2, 5); 
           
        CategoryAxis DomainAxis=plot.getDomainAxis();
        DomainAxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_45); 
        DomainAxis.setTickMarksVisible(true);
        DomainAxis.setLowerMargin(0.01);
        DomainAxis.setUpperMargin(0.01);        
       
        JFreeChart chart = new JFreeChart(
            " "+"\n",
            null,
            plot,
            true
        );
        
        chart.setBackgroundPaint(Color.WHITE);
        return chart;
} 


// Creo el dataset combinado
public CategoryDataset createDatasetCombinado(ArrayList<String> lista)   {
    
    DefaultCategoryDataset data = new DefaultCategoryDataset(); 
    for (int j=0;j<this.jTable3.getRowCount();j++){
        String fecha=this.jTable3.getValueAt(j, 1).toString();
        for (int s=0;s<lista.size();s++){
            String serie=lista.get(s);
            int columna=this.jTable3.getColumnModel().getColumnIndex(serie);
            if ((this.jTable3.getValueAt(j, columna)!=null)&&(!this.jTable3.getValueAt(j, columna).toString().contains("NAN"))){
                double dato=Double.parseDouble(this.jTable3.getValueAt(j, columna).toString());
                data.addValue(dato,serie,fecha);
            } else{
                data.addValue(null, serie,fecha);
            }  
        }
    }    
    return data;   
} 



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    // End of variables declaration//GEN-END:variables
    
    // Objetos
    private Asunto A=new Asunto();
    private DatosRA D=new DatosRA();
    private ConfiguracionRA C=new ConfiguracionRA();
    private Site S=new Site();
    
    // Variables
    private int asunto;
    private int site;
    private int Id;
    private String tabla_datos;
    private String tabla_descripcion;
    private ArrayList <String> codigos=new ArrayList<String>();
    private ArrayList <String []> datos=new ArrayList<String[]>();
    private ArrayList <String> superior;
    private ArrayList <String> inferior;
    
    public int inicio;
    public int fin;
 
    private CategoryDataset data1c=new DefaultCategoryDataset();
    private CategoryDataset data2c=new DefaultCategoryDataset();
   
    // Tablas
    private DefaultTableModel dtm3;

    public void chartMouseClicked(ChartMouseEvent evento) {
         ChartPanel p=(ChartPanel)this.jPanel17;
         JFreeChart c = p.getChart();
         if (c != null) {                    
               if (evento.getEntity()!=null){
                   int i=evento.getEntity().getToolTipText().indexOf(" ");
                   String fecha=evento.getEntity().getToolTipText().substring(i+1,i+16);
                  
                   System.out.println(fecha);
                   String s=evento.getEntity().getToolTipText().substring(1,i-1);                    
                   Comparable click=(Comparable)fecha; 
                   int fila=data1c.getColumnIndex(click);
                   int col=this.jTable3.getColumnModel().getColumnIndex(s);
                   System.out.println("Fila "+fila+" Columna "+col);
                   this.jTable3.changeSelection(fila,col,false,false);
                   this.jTabbedPane1.setSelectedIndex(0);
               }
          }
    }

    public void chartMouseMoved(ChartMouseEvent arg0) {
       
    }  
    
} 

