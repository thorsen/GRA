
package userinterfaces;

import RA.Asunto;
import RA.Login;
import RA.SerieRA;
import RA.ConfiguracionRA;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


public class DescripcionRAGUI extends JDialog {
    
  
    public DescripcionRAGUI(java.awt.Frame parent, int id) {
        super(parent, true);
        this.Id=id;
        initComponents();       
        this.dtm3=(DefaultTableModel)this.jTable3.getModel();
        this.dtm4=(DefaultTableModel)this.jTable4.getModel();
        this.dtm5=(DefaultTableModel)this.jTable5.getModel();
        this.dtm6=(DefaultTableModel)this.jTable6.getModel();
        this.dtm7=(DefaultTableModel)this.jTable7.getModel();
        this.dtm8=(DefaultTableModel)this.jTable8.getModel();
    }
  
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable7 = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTable8 = new javax.swing.JTable();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTable9 = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTable10 = new javax.swing.JTable();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("DESCRIPCION / SPL");
        setBackground(new java.awt.Color(175, 30, 30));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setForeground(new java.awt.Color(255, 0, 0));
        setLocationByPlatform(true);
        setName("Cliente"); // NOI18N

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder()));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel1.setText("Asunto:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));

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
                this.jComboBox1.insertItemAt(dato,i+1);
            }
            this.jComboBox1.setSelectedIndex(0);
        }catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Fallo al consultar la base de datos", "ERROR", JOptionPane.ERROR_MESSAGE);    
        }
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CambioAsunto(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 240, Short.MAX_VALUE)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setText("CERRAR");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Cerrar(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(255, 255, 255));
        jButton4.setText("AÑADIR");
        jButton4.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                GuardarDescripcion(evt);
            }
        });

        jTabbedPane1.setBackground(new java.awt.Color(102, 102, 102));
        jTabbedPane1.setForeground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel4.setText("SERIES POSIBLES");

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DESCRIPCIÓN"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        //this.CargarSeriesPC();
        jScrollPane3.setViewportView(jTable3);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel5.setText("SERIES A INSERTAR");

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DESCRIPCIÓN"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(jTable4);

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(RA.Global.RUTA_IMAGENES + "resultset_next.png")));
        jButton2.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PonerNA(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(RA.Global.RUTA_IMAGENES + "resultset_previous.png")));
        jButton1.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                QuitarNA(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 158, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addGap(40, 40, 40)))
                .addGap(82, 82, 82))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 604, Short.MAX_VALUE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 604, Short.MAX_VALUE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(136, 136, 136)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("             NO ACÚSTICAS               ", jPanel4);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel6.setText("SERIES POSIBLES");

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DESCRIPCIÓN"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        //this.CargarSeriesPC();
        jScrollPane5.setViewportView(jTable5);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel7.setText("SERIES A INSERTAR");

        jTable6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DESCRIPCIÓN"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(jTable6);

        jButton5.setBackground(new java.awt.Color(255, 255, 255));
        jButton5.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(RA.Global.RUTA_IMAGENES + "resultset_next.png")));
        jButton5.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PonerSPL(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(255, 255, 255));
        jButton6.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(RA.Global.RUTA_IMAGENES + "resultset_previous.png")));
        jButton6.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                QuitarSPL(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 158, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addGap(40, 40, 40)))
                .addGap(70, 70, 70))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane5)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 578, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(136, 136, 136)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("                      SPL                        ", jPanel5);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel8.setText("SERIES POSIBLES");

        jTable7.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DESCRIPCIÓN"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        //this.CargarSeriesPC();
        jScrollPane7.setViewportView(jTable7);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel9.setText("SERIES A INSERTAR");

        jTable8.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DESCRIPCIÓN"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane8.setViewportView(jTable8);

        jButton7.setBackground(new java.awt.Color(255, 255, 255));
        jButton7.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(RA.Global.RUTA_IMAGENES + "resultset_next.png")));
        jButton7.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PonerOCT(evt);
            }
        });

        jButton8.setBackground(new java.awt.Color(255, 255, 255));
        jButton8.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(RA.Global.RUTA_IMAGENES + "resultset_previous.png")));
        jButton8.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                QuitarOCT(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 158, Short.MAX_VALUE)
                        .addComponent(jLabel9)
                        .addGap(40, 40, 40)))
                .addGap(81, 81, 81))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane7)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(136, 136, 136)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("                  1/3 OCTAVA                ", jPanel6);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel10.setText("SERIES POSIBLES");

        jTable9.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DESCRIPCIÓN"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        //this.CargarSeriesPC();
        jScrollPane9.setViewportView(jTable9);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel11.setText("SERIES A INSERTAR");

        jTable10.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DESCRIPCIÓN"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane10.setViewportView(jTable10);

        jButton9.setBackground(new java.awt.Color(255, 255, 255));
        jButton9.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(RA.Global.RUTA_IMAGENES + "resultset_next.png")));
        jButton9.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton9Poner(evt);
            }
        });

        jButton10.setBackground(new java.awt.Color(255, 255, 255));
        jButton10.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(RA.Global.RUTA_IMAGENES + "resultset_previous.png")));
        jButton10.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton10Quitar(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 162, Short.MAX_VALUE)
                        .addComponent(jLabel11)
                        .addGap(40, 40, 40)))
                .addGap(81, 81, 81))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane9)
                            .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(136, 136, 136)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("                  FFT                    ", jPanel1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 661, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 200, Short.MAX_VALUE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 684, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-689)/2, (screenSize.height-825)/2, 689, 825);
    }// </editor-fold>//GEN-END:initComponents

private void GuardarDescripcion(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GuardarDescripcion
if (this.jButton4.isEnabled()){
    if ((this.jComboBox1.getSelectedIndex()!=0) && (codigosNOTAC.size()!=0) && (codigosOCT.size()!=0) && (codigosSPL.size()!=0) ){
   
        try {
            asunto=A.getId(this.jComboBox1.getSelectedItem().toString());
            if (C.NuevaDescripcion(asunto, codigosNOTAC,codigosSPL,codigosOCT)){
                    JOptionPane.showMessageDialog(this, "Descripción insertada correctamente.","INFORMACIÓN",JOptionPane.INFORMATION_MESSAGE);  
            }        
        } catch (SQLException e){
            JOptionPane.showMessageDialog(this, "Fallo durante la inserción","ERROR",JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    } else{
        JOptionPane.showMessageDialog(this, "Faltan datos de entrada","ADVERTENCIA",JOptionPane.WARNING_MESSAGE);
    }
}
}//GEN-LAST:event_GuardarDescripcion

private void Limpiar(){
    
    dtm3.setRowCount(0);
    dtm4.setRowCount(0);
    dtm5.setRowCount(0);
    dtm6.setRowCount(0);
    dtm7.setRowCount(0);
    dtm8.setRowCount(0);
    codigosNOTAC=new ArrayList <String>();
    codigosSPL=new ArrayList <String>();
    codigosOCT=new ArrayList <String>();
    // Cargar series en tablas
    this.CargarSeriesRA();
    // Boton guardar
    this.jButton4.setEnabled(true);
    // Botones actualizar
    this.jButton2.setEnabled(true);
    this.jButton1.setEnabled(true);
    this.jButton5.setEnabled(true);
    this.jButton6.setEnabled(true);
    this.jButton7.setEnabled(true);
    this.jButton8.setEnabled(true);
    
}

public void Actualizar1(DefaultTableModel tabla1,DefaultTableModel tabla2){
    for (int fila=0;fila<tabla2.getRowCount();fila++){
        String serie=tabla2.getValueAt(fila, 0).toString();
        int i=0;
        boolean encontrado=false;
        while ((i<tabla1.getRowCount()) && (!encontrado)){
            if (tabla1.getValueAt(i, 0).toString().equals(serie)){
                encontrado=true;
                tabla1.removeRow(i);
            }
            i=i+1;
        }
    }
}

public void Actualizar2(ArrayList <String> series,DefaultTableModel tabla){
    for (int s=0;s<series.size();s++){
        int i=0;
        boolean encontrado=false;
        while ((i<tabla.getRowCount()) && (!encontrado)){
            if (tabla.getValueAt(i, 0).toString().equals(series.get(s))){
                encontrado=true;
                tabla.removeRow(i);
            }
            i=i+1;
        }  
    }   
}

private void Cerrar(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Cerrar
    dispose();
}//GEN-LAST:event_Cerrar

private void CambioAsunto(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CambioAsunto
    Limpiar();
 
    if (this.jComboBox1.getSelectedIndex()!=0){
        try{
           asunto=A.getId(this.jComboBox1.getSelectedItem().toString());
           codigosNOTAC=C.getDescripcion(asunto, "DescripcionSPL",0);
           codigosSPL=C.getDescripcion(asunto, "DescripcionSPL",1);
           codigosOCT=C.getDescripcion(asunto, "DescripcionOCT",2);
           // Se cargan tablas
           if (codigosNOTAC.size()!=0){
               int n=codigosNOTAC.size();
               dtm4.setRowCount(n);
               this.jButton4.setEnabled(false);
               this.jButton2.setEnabled(false);
               this.jButton1.setEnabled(false);
                
               for (int i=0;i<n;i++){
                     String codigo=codigosNOTAC.get(i);
                     String descripcion=S.getDescripcion(S.getSerie(codigo));
                     this.jTable4.setValueAt(descripcion, i, 0);   
                     Centrar(this.jTable4,0,0);
               }
               this.Actualizar1(dtm3,dtm4);
           } 
           if (codigosSPL.size()!=0){
               int n=codigosSPL.size();
               dtm6.setRowCount(n);
               this.jButton4.setEnabled(false);
               this.jButton5.setEnabled(false);
               this.jButton6.setEnabled(false);
                
               for (int i=0;i<n;i++){
                     String codigo=codigosSPL.get(i);
                     String descripcion=S.getDescripcion(S.getSerie(codigo));
                     this.jTable6.setValueAt(descripcion, i, 0);   
                     Centrar(this.jTable6,0,0);
               }
               this.Actualizar1(dtm5,dtm6);
           } 
           if (codigosOCT.size()!=0){
               int n=codigosOCT.size();
               dtm8.setRowCount(n);
               this.jButton4.setEnabled(false);
               this.jButton7.setEnabled(false);
               this.jButton8.setEnabled(false);
                
               for (int i=0;i<n;i++){
                     String codigo=codigosOCT.get(i);
                     String descripcion=S.getDescripcion(S.getSerie(codigo));
                     this.jTable8.setValueAt(descripcion, i, 0);   
                     Centrar(this.jTable8,0,0);
               }
               this.Actualizar1(dtm7,dtm8);
           } 
        } catch(SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al consultar la base de datos","ERROR",JOptionPane.ERROR_MESSAGE);
        }    
    }
}//GEN-LAST:event_CambioAsunto

private void CargarSeriesRA(){
 
    try{
        /*
        // No acústicas
        ArrayList<String> series=S.getSeriesRA(0);
        int n=series.size();
        dtm3.setRowCount(n);
        for (int i=0;i<n;i++){
            this.jTable3.setValueAt(series.get(i), i,0);
            Centrar(this.jTable3,0,0);
        }
        // SPL
        series=S.getSeriesRA(1);
        n=series.size();
        dtm5.setRowCount(n);
        for (int i=0;i<n;i++){
            this.jTable5.setValueAt(series.get(i), i,0);
            Centrar(this.jTable5,0,0);
        }
        // OCT
        series=S.getSeriesRA(2);
        n=series.size();
        dtm7.setRowCount(n);
        for (int i=0;i<n;i++){
            this.jTable7.setValueAt(series.get(i), i,0);
            Centrar(this.jTable7,0,0);
        }
        */
        
        //Método generalizado para no repetir código
        
        int                 i, n, iSeries, nSeries;
        DefaultTableModel   dtmAux;
        JTable              jtAux;
        
        ArrayList<DefaultTableModel> dtms = new ArrayList<DefaultTableModel>();
        dtms.add(dtm3); //No acústicas
        dtms.add(dtm5); //SPL
        dtms.add(dtm7); //OCT
        
        ArrayList<JTable> jts = new ArrayList<JTable>();
        jts.add(jTable3); //No acústicas
        jts.add(jTable5); //SPL
        jts.add(jTable7); //OCT
        
        n = dtms.size();
                 
        for (i = 0; i < n; i++) {
            dtmAux = dtms.get(i);
            jtAux = jts.get(i);
            
            ArrayList<String> series = S.getSeriesRA(i);
            
            nSeries = series.size();
            dtmAux.setRowCount(nSeries);

            for (iSeries = 0; iSeries < nSeries; iSeries++) {
                jtAux.setValueAt(series.get(iSeries), iSeries, 0);
                Centrar(jtAux, 0, 0);
            }   
        }
    } catch (SQLException e){
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error al cargar las series", "ERROR", JOptionPane.ERROR_MESSAGE);
    }
}

private void PonerNA(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PonerNA
if (this.jButton2.isEnabled()){
  
    try{
        int [] filas=this.jTable3.getSelectedRows();
        if (filas.length!=0){
            ArrayList <String> s=new ArrayList <String>();
            for (int i=0;i<filas.length;i++){
                 String serie=this.jTable3.getValueAt(filas[i], 0).toString();
                 String codigo=S.getCodigo(serie);
                 s.add(serie);
                 dtm4.setRowCount(dtm4.getRowCount()+1);
                 dtm4.setValueAt(serie, dtm4.getRowCount()-1, 0);
                 Centrar(this.jTable4,0,0);
                 codigosNOTAC.add(codigo);
            }
            Actualizar1(dtm3,dtm4);       
        }
    } catch (SQLException e){
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error al leer la serie","ERROR",JOptionPane.ERROR_MESSAGE);
    }
}
}//GEN-LAST:event_PonerNA

private void QuitarNA(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QuitarNA
if (this.jButton1.isEnabled()){

    try{
        ArrayList <String> series=new ArrayList <String>();
        int[] filas=jTable4.getSelectedRows();
       
        if (filas.length!=0){
            for (int i=0;i<filas.length;i++){
                 String serie=this.jTable4.getValueAt(filas[i], 0).toString();
                 String codigo=S.getCodigo(serie);
                 series.add(serie);
                 dtm3.setRowCount(dtm3.getRowCount()+1);
                 dtm3.setValueAt(serie, dtm3.getRowCount()-1, 0);
                 Centrar(this.jTable3,0,0);
                 codigosNOTAC.remove(codigo);
            }
        }   
        Actualizar2(series,dtm4);
    } catch (SQLException e){
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error al leer la serie","ERROR",JOptionPane.ERROR_MESSAGE);
    }
}
}//GEN-LAST:event_QuitarNA

private void PonerSPL(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PonerSPL
if (this.jButton5.isEnabled()){
  
    try{
        int [] filas=this.jTable5.getSelectedRows();
        if (filas.length!=0){
            ArrayList <String> s=new ArrayList <String>();
            for (int i=0;i<filas.length;i++){
                 String serie=this.jTable5.getValueAt(filas[i], 0).toString();
                 String codigo=S.getCodigo(serie);
                 s.add(serie);
                 dtm6.setRowCount(dtm6.getRowCount()+1);
                 dtm6.setValueAt(serie, dtm6.getRowCount()-1, 0);
                 Centrar(this.jTable6,0,0);
                 codigosSPL.add(codigo);
            }
            Actualizar1(dtm5,dtm6);       
        }
    } catch (SQLException e){
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error al leer la serie","ERROR",JOptionPane.ERROR_MESSAGE);
    }
}
}//GEN-LAST:event_PonerSPL

private void QuitarSPL(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QuitarSPL
if (this.jButton6.isEnabled()){

    try{
        ArrayList <String> series=new ArrayList <String>();
        int[] filas=jTable6.getSelectedRows();
       
        if (filas.length!=0){
            for (int i=0;i<filas.length;i++){
                 String serie=this.jTable6.getValueAt(filas[i], 0).toString();
                 String codigo=S.getCodigo(serie);
                 series.add(serie);
                 dtm5.setRowCount(dtm5.getRowCount()+1);
                 dtm5.setValueAt(serie, dtm5.getRowCount()-1, 0);
                 Centrar(this.jTable5,0,0);
                 codigosSPL.remove(codigo);
            }
        }   
        Actualizar2(series,dtm6);
    } catch (SQLException e){
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error al leer la serie","ERROR",JOptionPane.ERROR_MESSAGE);
    }
}
}//GEN-LAST:event_QuitarSPL

private void PonerOCT(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PonerOCT
if (this.jButton7.isEnabled()){
  
    try{
        int [] filas=this.jTable7.getSelectedRows();
        if (filas.length!=0){
            ArrayList <String> s=new ArrayList <String>();
            for (int i=0;i<filas.length;i++){
                 String serie=this.jTable7.getValueAt(filas[i], 0).toString();
                 String codigo=S.getCodigo(serie);
                 s.add(serie);
                 dtm8.setRowCount(dtm8.getRowCount()+1);
                 dtm8.setValueAt(serie, dtm8.getRowCount()-1, 0);
                 Centrar(this.jTable8,0,0);
                 codigosOCT.add(codigo);
            }
            Actualizar1(dtm7,dtm8);       
        }
    } catch (SQLException e){
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error al leer la serie","ERROR",JOptionPane.ERROR_MESSAGE);
    }
}
}//GEN-LAST:event_PonerOCT

private void QuitarOCT(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QuitarOCT
if (this.jButton8.isEnabled()){

    try{
        ArrayList <String> series=new ArrayList <String>();
        int[] filas=jTable8.getSelectedRows();
       
        if (filas.length!=0){
            for (int i=0;i<filas.length;i++){
                 String serie=this.jTable8.getValueAt(filas[i], 0).toString();
                 String codigo=S.getCodigo(serie);
                 series.add(serie);
                 dtm7.setRowCount(dtm7.getRowCount()+1);
                 dtm5.setValueAt(serie, dtm7.getRowCount()-1, 0);
                 Centrar(this.jTable7,0,0);
                 codigosOCT.remove(codigo);
            }
        }   
        Actualizar2(series,dtm8);
    } catch (SQLException e){
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error al leer la serie","ERROR",JOptionPane.ERROR_MESSAGE);
    }
}
}//GEN-LAST:event_QuitarOCT

private void jButton9Poner(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton9Poner
// TODO add your handling code here:
}//GEN-LAST:event_jButton9Poner

private void jButton10Quitar(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton10Quitar
// TODO add your handling code here:
}//GEN-LAST:event_jButton10Quitar


    
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
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
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
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable10;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTable jTable6;
    private javax.swing.JTable jTable7;
    private javax.swing.JTable jTable8;
    private javax.swing.JTable jTable9;
    // End of variables declaration//GEN-END:variables
    
    // Objetos
    private Asunto A=new Asunto();
    private ConfiguracionRA C=new ConfiguracionRA();
    private SerieRA S=new SerieRA();
    
    // Tablas
    private DefaultTableModel dtm3;
    private DefaultTableModel dtm4;
    private DefaultTableModel dtm5;
    private DefaultTableModel dtm6;
    private DefaultTableModel dtm7;
    private DefaultTableModel dtm8;
    
    // Variables
    private int asunto;
    private int Id;
    private ArrayList <String> codigosNOTAC = new ArrayList <String>();
    private ArrayList <String> codigosSPL = new ArrayList <String>();
    private ArrayList <String> codigosOCT = new ArrayList <String>();
  
}
