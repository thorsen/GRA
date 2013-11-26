package userinterfaces;

import RA.Asunto;
import RA.Login;
import RA.Site;
import RA.ConfiguracionRA;
import RA.DatosRA;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class DatosGUI extends JDialog {
    
    public DatosGUI(java.awt.Frame parent, int id) {
        super(parent, true);
        this.Id=id;
        initComponents();  
        this.dtm1=(DefaultTableModel)this.jTable1.getModel();
    }
   
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel8 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel13 = new javax.swing.JLabel();
        closeButton1 = new javax.swing.JButton();
        closeButton = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("DATOS ENSAYO RUIDO");
        setBackground(new java.awt.Color(175, 30, 30));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setForeground(new java.awt.Color(255, 0, 0));
        setIconImage(new ImageIcon("\\\\Bsolar\\DatosSolar\\Curva\\Imagenes\\GCPMini.jpg").getImage());
        setLocationByPlatform(true);
        setName("Cliente"); // NOI18N

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 51, 255))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12));
        jLabel3.setText("Selector de Mediciones ");

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("\\\\Bsolar\\DatosSolar\\Curva\\Imagenes\\OpenFolder.gif" )));
        jButton1.setToolTipText("Seleccionar fichero de datos");
        jButton1.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton1.setPreferredSize(new java.awt.Dimension(65, 20));
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Ruta(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Fichero", "RF?", "Ok?"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Boolean.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(jTable1);
        jTable1.getColumnModel().getColumn(0).setMinWidth(200);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(200);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(500);
        jTable1.getColumnModel().getColumn(1).setMinWidth(50);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(50);
        jTable1.getColumnModel().getColumn(1).setMaxWidth(50);
        jTable1.getColumnModel().getColumn(2).setMinWidth(50);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(50);
        jTable1.getColumnModel().getColumn(2).setMaxWidth(50);

        jLabel5.setText("  Fecha de Inicio:");

        jTextField1.setBackground(new java.awt.Color(204, 204, 204));
        jTextField1.setEditable(false);
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel6.setText("  Último dato:");

        jTextField2.setBackground(new java.awt.Color(204, 204, 204));
        jTextField2.setEditable(false);
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jProgressBar1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12));
        jLabel13.setText("Progreso de la inserción »");
        jLabel13.setFocusable(false);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 377, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(55, 55, 55)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 537, Short.MAX_VALUE))))
                .addGap(26, 26, 26))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        closeButton1.setBackground(new java.awt.Color(255, 255, 255));
        closeButton1.setText("CANCELAR");
        closeButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cancelar(evt);
            }
        });

        closeButton.setBackground(new java.awt.Color(255, 255, 255));
        closeButton.setText("INSERTAR");
        closeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ok(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel7.setText("MEDICIONES DEL ENSAYO DE RUIDO ACÚSTICO");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder()));

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
                NuevoAsunto(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel2.setText("Site:");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CambioSite(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 224, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(185, 185, 185)
                        .addComponent(jLabel7))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                                .addComponent(closeButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(closeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(11, 11, 11)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(closeButton1)
                    .addComponent(closeButton))
                .addContainerGap())
        );

        jPanel1.getAccessibleContext().setAccessibleName("DATOS_CP");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-639)/2, (screenSize.height-650)/2, 639, 650);
    }// </editor-fold>//GEN-END:initComponents

    private void Cancelar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cancelar
        dispose();
}//GEN-LAST:event_Cancelar
    
    private void ok(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ok
    
        boolean ok=true;
        Component root=null;
        try{
            if (this.jComboBox1.getSelectedIndex()!=0){
                asunto=A.getId(this.jComboBox1.getSelectedItem().toString());
            } else{
                ok=false;
            }
            if (this.jComboBox2.getSelectedIndex()!=0){
                site=S.getId(this.jComboBox2.getSelectedItem().toString());
            } else{
                ok=false;
            }
            if (medidas.size()!=0){
                for (int i=0;i<medidas.size();i++){
                    System.out.println(dtm1.getValueAt(i, 1));
                    if (Boolean.parseBoolean(dtm1.getValueAt(i, 1).toString())){ // RF=1
                        String[] medida=medidas.get(i);
                        medida[1]="1";
                        medidas.set(i, medida);
                    }
                }
            } else{
                ok=false;
            }
            if (ok){
                
                 root = SwingUtilities.getRoot(this);
                 
                 // Se busca la descripcion del punto seleccionado
                 String tabla="DescripcionSPL";
                 if (site==105){tabla="DescripcionOCT";}
                 if (site==106){tabla="DescripcionFFT";}
                 tabla="DescripcionRA";
                 ArrayList <String> descripcion=C.getDescripcion(asunto,tabla);
                 
                 if (descripcion.size()==0){
                    JOptionPane.showMessageDialog(this, "Antes de insertar datos, insertar una descripción al ensayo", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);   
                    dispose();
                } else{
                     // Se insertan las medidas
                    root.setCursor( Cursor.getPredefinedCursor( Cursor.WAIT_CURSOR ) );
                    
                    this.jLabel13.setText("Inicio de inserción ...");
                    update(this.getGraphics());
                    
                    int n=medidas.size();
                    int i=0;
                    
                    String[] salida=new String[3];
                    while (i<n){
                        
                        String fichero=dtm1.getValueAt(i, 0).toString();
                        this.jLabel13.setText("Insertando medida "+fichero+" ...");
                        update(this.getGraphics());
                        
                        salida[0]=D.Nuevosdatos(asunto, site,medidas.get(i),Id);
                        System.out.println(salida);
                        
                        if (salida[0].equals("Datos insertados")){
                            dtm1.setValueAt(new Boolean (true), i, 2);
                        } 
                        
                        this.jProgressBar1.setValue(100*(i+1)/n);  
                        this.jProgressBar1.setStringPainted(true);         
                        update(this.getGraphics());  
                        i++;
                    }
                    // Se rellena la fecha fin
                    String fecha_ultima=D.getFultima(asunto,tabla_datos);
                    if (fecha_ultima!=null){
                        this.jTextField2.setText(fecha_ultima);
                        update(this.getGraphics());
                    }                 
                    
                    // Se finaliza
                    JOptionPane.showMessageDialog(this, "Proceso de inserción finalizado", "INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
                    root.setCursor( Cursor.getPredefinedCursor( Cursor.DEFAULT_CURSOR ) );  
                                   
                }
            } else{
                JOptionPane.showMessageDialog(this, "Faltan datos de entrada", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
            }
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Fallo en el proceso de inserción ", "ERROR", JOptionPane.ERROR_MESSAGE);
            root.setCursor( Cursor.getPredefinedCursor( Cursor.DEFAULT_CURSOR ) );  
        }catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Fallo al leer fichero", "ERROR", JOptionPane.ERROR_MESSAGE);
            root.setCursor( Cursor.getPredefinedCursor( Cursor.DEFAULT_CURSOR ) );  
        }
 
   
}//GEN-LAST:event_ok
    
    private void Ruta(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Ruta
        String u=Login.getUsuario();
        JFileChooser fileChooser = new JFileChooser("Z:\\"+u+"\\PRIVADA\\CALIDAD\\ASUNTOS"); 
        fileChooser.setMultiSelectionEnabled(true);
        int seleccion =fileChooser.showOpenDialog(this);              
        if (seleccion == JFileChooser.APPROVE_OPTION){
            File[] ficheros = fileChooser.getSelectedFiles();           
            int n=ficheros.length;
            dtm1.setRowCount(0);
            for (int i=0;i<n;i++){
                 Object[] fila=new Object[2];
                 fila[0]=null;
                 fila[1]=new Boolean(false);
                 dtm1.addRow(fila);
                 String medida[]=new String[2];
                 medida[0]=ficheros[i].getPath();
                 ficheros[i].getPath();
                 medida[1]="0";
                 medidas.add(medida);
                 this.jTable1.setValueAt(ficheros[i].getName(), i, 0);
                 if (ficheros[i].getName().contains("RF")){
                    this.jTable1.setValueAt(new Boolean(true), i, 1);
                 }                
            }
        }
        
}//GEN-LAST:event_Ruta

private void Limpiar(){
    // Fechas
    this.jTextField1.setText(null);
    // Ndatos
    this.jTextField2.setText(null);
    // Mediciones
    dtm1.setRowCount(0);
    for (int i=0;i<10;i++){
        Object[] fila=new Object[2];
        fila[0]=null;
        fila[1]=new Boolean(false);
        dtm1.addRow(fila);
    }
    medidas=new ArrayList<String[]>();
    
}


private void NuevoAsunto(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NuevoAsunto
    Limpiar();   
    if (this.jComboBox1.getSelectedIndex()!=0){
        try{
            // Calculo el numero de sites para ese asunto y relleno el combobox con ellos
            asunto=A.getId(this.jComboBox1.getSelectedItem().toString());
            
            // Inicializo el combo sites
            this.jComboBox2.removeAllItems();
            this.jComboBox2.insertItemAt("",0);
            
            // Cargo el combo sites
            ArrayList <Integer> sites=A.getSitesRA(asunto);
            int n=sites.size();
            for(int i=0;i<n;i++){
                String dato=S.getNombre(sites.get(i));
                this.jComboBox2.insertItemAt(dato,i+1);
            }
            this.jComboBox2.setSelectedIndex(0);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Fallo al consultar la base de datos", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
   }      
}//GEN-LAST:event_NuevoAsunto

private void CambioSite(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CambioSite
    Limpiar();
    if ((this.jComboBox2.getSelectedIndex()!=0) && (this.jComboBox2.getSelectedItem()!=null) && (this.jComboBox2.getSelectedItem()!="") ){
        try {
            
            String nombre_site=(String)this.jComboBox2.getSelectedItem();
            site=S.getId(nombre_site);
                       
            tabla_datos="DatosSPL";
            tabla_descripcion="DescripcionSPL";
            if (site==105) {
               tabla_datos="DatosOCT";
               tabla_descripcion="DescripcionOCT";
            }
           
            // Relleno Fecha de inicio y fin de las medidas del site seleccionado
            String Fini=A.getFiniRA(asunto,site);
            String Fultima=D.getFultima(asunto,tabla_datos);
                    
            if (Fini!=null){
                this.jTextField1.setText(Fini);
            }
            if (Fultima!=null){
                this.jTextField2.setText(Fultima);
            }
   
        } catch(SQLException e){
             e.printStackTrace();
             JOptionPane.showMessageDialog(this, "Fallo al consultar la base de datos", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
     } 
}//GEN-LAST:event_CambioSite
   
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closeButton;
    private javax.swing.JButton closeButton1;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
    
    // Objetos
    private Asunto A=new Asunto();
    private Site S=new Site();
    private DatosRA D=new DatosRA();
    private ConfiguracionRA C=new ConfiguracionRA();
    
    // Tablas
    private DefaultTableModel dtm1;
    
    // Variables
    private int Id;
    private int asunto;
    private int site;
    private ArrayList<String[]> medidas=new ArrayList<String[]>();
    private String tabla_descripcion;
    private String tabla_datos;
            
            
}
