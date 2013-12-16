package userinterfaces;

import RA.Numero;
import general.Auxiliares;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.SpiderWebPlot;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;


public class SVGUIAG extends JDialog {
   
       
    public SVGUIAG(SVGUI2 D,int panel,int aero) {
        super(D, true);
        this.D0=D;
        this.panel=panel;
        this.aero=aero;
        initComponents();  
        RellenaTabla();
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

        jPanel1 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SV / AEROGENERADOR");
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setForeground(new java.awt.Color(255, 0, 0));
        setIconImage(new ImageIcon("\\\\B2Solar\\Datos\\Curva\\Imagenes\\GRA.png").getImage());
        setLocationByPlatform(true);
        setName("Cliente"); // NOI18N
        setResizable(false);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        this.jPanel6=createPanel(this.D0.D0.objetos.get((panel)*9+aero)[0],this.D0.D0.matrizaeros.get((panel)*9+aero).get(1));
        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 307, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 268, Short.MAX_VALUE)
        );

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel14.setText("SECTOR VÁLIDO SOBRE EL AEROGENERADOR");

        jTextArea1.setColumns(5);
        jTextArea1.setEditable(false);
        jTextArea1.setFont(new java.awt.Font("Arial", 1, 11));
        jTextArea1.setRows(3);
        jTextArea1.setTabSize(6);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel1.setText("GRÁFICO");

        jLabel2.setText("LÍMITES SV");

        jButton7.setBackground(new java.awt.Color(255, 255, 255));
        jButton7.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("\\\\B2Solar\\Datos\\Curva\\Imagenes\\camera.png" )));
        jButton7.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                GuardarImagen(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(jLabel2))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(67, 67, 67))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(145, 145, 145)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-464)/2, (screenSize.height-366)/2, 464, 366);
    }// </editor-fold>//GEN-END:initComponents

private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
    dispose();
}//GEN-LAST:event_formMouseClicked

private void GuardarImagen(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GuardarImagen
try{
    
    JFileChooser fc = new JFileChooser(Auxiliares.dameRutaDefecto(this));
    fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);     
    int seleccion = fc.showSaveDialog(this);              
    String ruta="";
    if (seleccion == JFileChooser.APPROVE_OPTION){
        File fichero = fc.getSelectedFile();
        ruta=fichero.getPath()+"\\"+this.D0.D0.objetos.get((panel)*9+aero)[0]+".png";
    }
    File f=new File(ruta);
    JComponent C=this.jPanel1;
    int width=(int)C.getSize().getWidth();
    int height=(int)C.getSize().getHeight();
    BufferedImage image = new BufferedImage(width,height, BufferedImage.TYPE_INT_ARGB);
    Graphics2D g2d = image.createGraphics();
    C.paint(g2d);
    g2d.dispose();
    RenderedImage rendImage=image;
    ImageIO.write(rendImage, "png", f);
}catch(IOException e ){
   e.printStackTrace();
}
}//GEN-LAST:event_GuardarImagen


 public JPanel createPanel(String objeto,ArrayList<String[]> limites) {
     JFreeChart chart = createChart(objeto,limites);
     ChartPanel chartPanel = new ChartPanel(chart);
     chartPanel.setPreferredSize(new java.awt.Dimension(300,275));
     return chartPanel;
 }
 
 public void RellenaTabla(){
    Numero N=new Numero();
    sv=getFinal(this.D0.D0.matrizaeros.get((panel)*9+aero).get(1));
    for (int i=0;i<sv.size();i++){
        this.jTextArea1.append("  ["+N.redondear(sv.get(i)[0],2)+"º - "+N.redondear(sv.get(i)[1],2)+"º] \n");
        this.jTextArea1.append("  \n");
    }    
 }
 
 // Devuelve el sector valido final a partir del sector no valido
 public ArrayList<double[]> getFinal(ArrayList<String[]> no_valido){
     ArrayList<double[]> valido=new ArrayList<double[]>();
     if(no_valido.size()!=0){
        
        for (int i=0;i<no_valido.size()-1;i++){
            double[] dato=new double[2];
            dato[0]=Double.parseDouble(no_valido.get(i)[1]);
            dato[1]=Double.parseDouble(no_valido.get(i+1)[0]);
            valido.add(i, dato);
        }
        double[] dato=new double[2];
        dato[0]=Double.parseDouble(no_valido.get(no_valido.size()-1)[1]);
        dato[1]=Double.parseDouble(no_valido.get(0)[0]);
        valido.add(no_valido.size()-1,dato);
     }
     return valido;
 }
 
 
private JFreeChart createChart(String objeto,ArrayList<String[]> limites_no_validos) {
    CategoryDataset dataset=createDataset(limites_no_validos);
    SpiderWebPlot plot = new SpiderWebPlot(dataset);
    plot.setInteriorGap(0.1);
    plot.setOutlineVisible(false);
    plot.setHeadPercent(0.0005);
    plot.setSeriesOutlinePaint(Color.BLUE);
    plot.setSeriesPaint(Color.BLUE);
    plot.setToolTipGenerator(new StandardCategoryToolTipGenerator());
    plot.setAxisLinePaint(Color.WHITE);
    plot.setLabelPaint(Color.WHITE);   
    JFreeChart chart = new JFreeChart(objeto, new Font("Arial", Font.BOLD, 16), plot, false);
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
    this.ordenary(limites,0, limites.size()-1);
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

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
    public SVGUI2 D0;
    private ArrayList<String[]> svnovalido=new ArrayList<String[]>();
    private ArrayList<double[]> sv=new ArrayList<double[]>();
    private int panel;
    private int aero;
}   
