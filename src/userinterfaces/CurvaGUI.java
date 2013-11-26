
package userinterfaces;

import RA.Asunto;
import RA.Curva;
import RA.FicheroRA;
import RA.Login;
import RA.Numero;
import Informacion.Formato_CPGUI;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


public class CurvaGUI extends JDialog {
    
 
    public CurvaGUI(java.awt.Frame parent,int id) {
        super(parent,true);
        this.Id=id;
        initComponents();
        this.dtm1=(DefaultTableModel)this.jTable1.getModel();
    }
   
   
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel6 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("CURVA DE POTENCIA");
        setBackground(java.awt.Color.white);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setForeground(new java.awt.Color(255, 0, 0));
        setIconImage(new ImageIcon("\\\\Bsolar\\DatosSolar\\Curva\\Imagenes\\GCPMini.jpg").getImage());
        setLocationByPlatform(true);
        setName("Cliente"); // NOI18N

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("  CURVA DE POTENCIA   ");

        jLabel11.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("\\\\Bsolar\\DatosSolar\\Curva\\Imagenes\\Information.png" )));
        jLabel11.setToolTipText("Información de Formato");
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Poner(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Quitar(evt);
            }
        });

        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("____________________________________________________________________________________________________");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "V [m/s]", "P [kW]"
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

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setText("+");
        jButton3.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                NuevaCurva(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel4.setText("Densidad:");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CambioDensidad(evt);
            }
        });

        this.jPanel1=this.createPanel();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 455, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 401, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addComponent(jLabel4)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGap(28, 28, 28)))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(216, 216, 216)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(16, 16, 16)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jButton3)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setText("GUARDAR");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Guardar(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setText("CANCELAR");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Cancelar(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder()));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel3.setText("Asunto:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));
        Asunto A=new Asunto();
        try{
            // Responsable=Id, Tipo=3 Activo=true
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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 255, Short.MAX_VALUE)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, 0, 646, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-674)/2, (screenSize.height-680)/2, 674, 680);
    }// </editor-fold>//GEN-END:initComponents

private void Limpiar(){
    dtm1.setRowCount(0);
    curva_nueva=new ArrayList <double[]>();
    this.Graficar(curva_nueva);
}



private void Poner(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Poner
    L=new Formato_CPGUI(this);
    L.setVisible(true);
}//GEN-LAST:event_Poner

private void Quitar(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Quitar
    L.dispose();
}//GEN-LAST:event_Quitar

private void NuevaCurva(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NuevaCurva
if (this.jButton3.isEnabled()) {   
    this.jComboBox2.setSelectedIndex(0);
    try {
       JTextField jtfS = new JTextField();
       JOptionPane.showMessageDialog(this, jtfS,"Seleccione la densidad", JOptionPane.QUESTION_MESSAGE);
       double densidad=Double.parseDouble(jtfS.getText());
       if (densidades.indexOf(densidad)==-1){
            // Se lee la curva
           String u=Login.getUsuario();
           JFileChooser fileChooser = new JFileChooser("Z:\\"+u+"\\PRIVADA\\CALIDAD\\ASUNTOS");
           int seleccion = fileChooser.showOpenDialog(this);
           if (seleccion == JFileChooser.APPROVE_OPTION){
               File fichero = fileChooser.getSelectedFile();
               String ruta=fichero.getPath();
               curva_nueva = F.LeerCP(ruta);
               this.jComboBox2.insertItemAt(densidad,densidades.size()+1);      
               this.jComboBox2.setSelectedIndex(densidades.size()+1);
               this.CargarCurva(curva_nueva);
               this.Graficar(curva_nueva);    
               // Se deshabilita
               this.jComboBox2.setEnabled(false);
               this.jButton3.setEnabled(false);
           }
       } else{
            JOptionPane.showMessageDialog(this, "La densidad introducida ya existe","ADVERTENCIA",JOptionPane.WARNING_MESSAGE);
       }
    
    } catch (Exception e){
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Fallo al leer la nurva curva","ERROR",JOptionPane.ERROR_MESSAGE);
    }    
}
}//GEN-LAST:event_NuevaCurva

private void CargarCurva(ArrayList<double[]> curva){
    int n=curva.size();
    dtm1.setRowCount(n);
    for (int i=0;i<n;i++){
        this.jTable1.setValueAt(N.formato("0.0000",curva.get(i)[0]), i, 0);
        this.jTable1.setValueAt(N.formato("0.0000",curva.get(i)[1]), i, 1);
    }
    this.Centrar(jTable1, 0, 1);
}

private void Guardar(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Guardar
boolean ok=true;
try{
    if (this.jComboBox1.getSelectedIndex()!=0){
        asunto=A.getId(this.jComboBox1.getSelectedItem().toString());
    }else{
        ok=false;
    }
    if (this.jComboBox2.getSelectedIndex()!=0){
        densidad_nueva=Double.parseDouble(this.jComboBox2.getSelectedItem().toString());
    }else{
        ok=false;
    }
    if ((curva_nueva.size()==0) || (dtm1.getRowCount()==0)){
        ok=false;
    }
    if (ok){      
        boolean existe=CP.NuevaCP(asunto, densidad_nueva, curva_nueva);         
        if (!existe){
            JOptionPane.showMessageDialog(this, "Inserción realizada correctamente","INFORMACIÓN",JOptionPane.INFORMATION_MESSAGE);
            this.jButton3.setEnabled(true);
            this.jComboBox2.setEnabled(true);
        } else{
            JOptionPane.showMessageDialog(this, "La curva ya existe","ADVERTENCIA",JOptionPane.WARNING_MESSAGE);
        }
    }else{
        JOptionPane.showMessageDialog(this, "Faltan datos","ADVERTENCIA",JOptionPane.WARNING_MESSAGE);
    }
}catch (SQLException e){
    e.printStackTrace();
    JOptionPane.showMessageDialog(this, "Fallo en el proceso de inserción","ERROR",JOptionPane.ERROR_MESSAGE);
}
}//GEN-LAST:event_Guardar

private void Cancelar(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Cancelar
    dispose();
}//GEN-LAST:event_Cancelar

private void NuevoAsunto(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NuevoAsunto

    Limpiar();
    if (this.jComboBox1.getSelectedIndex()!=0){
        try{
            
            asunto=A.getId(this.jComboBox1.getSelectedItem().toString());
            
            // Inicializo el combo densidad
            this.jComboBox2.removeAllItems();
            this.jComboBox2.insertItemAt("",0);
            
            // Cargo el combo densidades
            densidades=CP.getDensidades(asunto);
            int n=densidades.size();
            for(int i=0;i<n;i++){
                String dato=""+densidades.get(i);
                this.jComboBox2.insertItemAt(dato,i+1);
            }
            this.jComboBox2.setSelectedIndex(0);

        }catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Fallo al consultar la base de datos","ERROR",JOptionPane.ERROR_MESSAGE);
        }
   }
}//GEN-LAST:event_NuevoAsunto

private void CambioDensidad(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CambioDensidad
if (this.jButton2.isEnabled()){
    dtm1.setRowCount(0);
    ArrayList<double[]> curva=new ArrayList<double[]>();
    this.Graficar(curva);
    if ((this.jComboBox2.getSelectedIndex()!=0) && (this.jComboBox2.getSelectedItem()!=null) && (this.jComboBox2.getSelectedItem()!="") ){
        try{
            double densidad=Double.parseDouble(this.jComboBox2.getSelectedItem().toString());
            curva=CP.getCurva(asunto, densidad);
            this.CargarCurva(curva);
            this.Graficar(curva);
        } catch (SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Fallo al consultar la base de datos","ERROR",JOptionPane.ERROR_MESSAGE);
        }
    }
}
}//GEN-LAST:event_CambioDensidad

public JPanel createPanel() {
     ArrayList<double[]> curva=new ArrayList<double[]>();
     JFreeChart chart = createChart(curva);
     ChartPanel chartPanel = new ChartPanel(chart);
     chartPanel.setPreferredSize(new java.awt.Dimension(425,400));
     return chartPanel;
}

private void Graficar(ArrayList<double[]> curva){    
    ChartPanel p=(ChartPanel)this.jPanel1;
    p.setChart(this.createChart(curva));
}

// Gráfico 
   private JFreeChart createChart(ArrayList<double[]> curva) {
       XYDataset dataset=this.createDataset(curva);
       JFreeChart chart = ChartFactory.createScatterPlot(
               "Curva de Potencia", 
               "Velocidad (m/s)", 
               "Potencia  (kW)",
               dataset, 
               PlotOrientation.VERTICAL,
               false, 
               false, 
               false
       );
       TextTitle texto=chart.getTitle();
       texto.setFont(new Font("Arial", Font.PLAIN, 13));        
       chart.setBackgroundPaint(Color.white);
       
       XYPlot plot = (XYPlot) chart.getPlot();
       plot.getDomainAxis().setLabelFont(new Font("Arial", Font.PLAIN, 12));
       plot.getRangeAxis().setLabelFont(new Font("Arial", Font.PLAIN, 12));
       Rectangle2D.Double R=new Rectangle2D.Double(-1,-1, 2, 2);
       XYItemRenderer renderer = new XYLineAndShapeRenderer(true, true); 
       renderer.setBaseToolTipGenerator(new StandardXYToolTipGenerator());
       renderer.setSeriesShape(0, R);  
       plot.setRenderer(0, renderer); 
       
       return chart;
   }

private XYDataset createDataset(ArrayList<double[]> curva) {
       
       XYSeriesCollection dataset = new XYSeriesCollection();   
       XYSeries serie=new XYSeries("Teórica");  
       int n=curva.size();
       for (int i=0;i<n;i++){
           serie.add(curva.get(i)[0],curva.get(i)[1]);
       } 
       dataset.addSeries(serie);      
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
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
    
    // Objetos
    private Asunto A=new Asunto();
    private Curva CP=new Curva();
    private Numero N=new Numero();
    private FicheroRA F=new FicheroRA();
    
    // Variables
    private int Id;
    private int asunto;
    private ArrayList<double[]> curva_nueva=new ArrayList <double[]>();
    private ArrayList<Double> densidades=new ArrayList <Double>();
    private DefaultTableModel dtm1;
    private Formato_CPGUI L;
    private double densidad_nueva;
}  
