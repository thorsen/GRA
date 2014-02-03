package userinterfaces;

import RA.Numero;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.SpiderWebPlot;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;


public class SVGUITR extends JDialog {
       
    public SVGUITR(SVGUI2 D) {
        super(D, true);
        this.D0=D;
        initComponents();  
        this.torre=this.jComboBox3.getSelectedIndex();
        this.RellenaTabla();
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
        jComboBox3 = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SV / TORRE");
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setForeground(new java.awt.Color(255, 0, 0));
        setIconImage(new ImageIcon(RA.Global.RUTA_IMAGENES + "GRA.png").getImage());
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

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        this.jPanel6=createPanel(this.D0.D0.torres.get(torre)[0],this.D0.D0.matriztorres.get(torre).get(1));
        this.jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 307, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 274, Short.MAX_VALUE)
        );

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(102, 102, 102));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("SECTOR VÁLIDO SOBRE LA TORRE");
        jLabel14.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextArea1.setColumns(5);
        jTextArea1.setEditable(false);
        jTextArea1.setFont(new java.awt.Font("Arial", 1, 11));
        jTextArea1.setRows(3);
        jTextArea1.setTabSize(6);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel1.setText("GRÁFICO :");

        jLabel2.setText("LÍMITES SV :");

        int n_torres=this.D0.D0.torres.size();
        for(int i=0;i<n_torres;i++){     
            String dato=this.D0.D0.torres.get(i)[0];     
            this.jComboBox3.insertItemAt(dato,i); 
        } 
        this.jComboBox3.setSelectedIndex(0);
        jComboBox3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CambioTorre(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel4.setText("Torre");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(141, 141, 141)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-478)/2, (screenSize.height-421)/2, 478, 421);
    }// </editor-fold>//GEN-END:initComponents

private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
    dispose();
}//GEN-LAST:event_formMouseClicked

private void CambioTorre(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CambioTorre
    // Se borra limites
    this.jTextArea1.setText(null);
    // Se lee la torre
    torre=this.jComboBox3.getSelectedIndex();
    this.Graficar();
    this.RellenaTabla();
}//GEN-LAST:event_CambioTorre

 public JPanel createPanel(String objeto,ArrayList<String[]> limites) {
     JFreeChart chart = createChart(objeto,limites);
     ChartPanel chartPanel = new ChartPanel(chart);
     chartPanel.setPreferredSize(new java.awt.Dimension(300,275));
     return chartPanel;
 }
 
 private void Graficar(){
    ChartPanel p=(ChartPanel)this.jPanel6;
    p.setChart(this.createChart(this.D0.D0.torres.get(torre)[0],this.D0.D0.matriztorres.get(torre).get(1)));
    p.setBorder(javax.swing.BorderFactory.createEtchedBorder());
 }
 
 public void RellenaTabla(){
    
    Numero N=new Numero();
    sv=getFinal(this.D0.D0.matriztorres.get((torre)).get(1));
    for (int i=0;i<sv.size();i++){
        this.jTextArea1.append("  ["+N.redondear(sv.get(i)[0],2)+"º - "+N.redondear(sv.get(i)[1],2)+"º] \n");
        this.jTextArea1.append("  \n");
    }
    
 }
 
 // Calcula el sector valido final a partir del sector no valido
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

 // Ordena los sectores no validos por la componente y
public void ordenary(ArrayList<String[]> novalido, int izq, int der) {
    if (novalido.size() > 1) {
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
        } while (i <= j);
        
        if (izq < j) {
            ordenary(novalido, izq, j);
        }
        if (i < der) {
            ordenary(novalido, i, der);
        }
    }
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

   private CategoryDataset createDataset(ArrayList<String[]> limites_no_validos) {
        // Creo una categoria para cada sector de 0 a 360
        String[] category = new String[360];
        for (int i=0;i<360;i++){
            category[i]="C"+i;
        }
        // Me creo el vector de validos de cada grado
        int[] valido=Valido(limites_no_validos);
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i=0;i<360;i++){
            dataset.addValue(valido[i], "", category[i]);
        }
        return dataset;
   }

   // Devuelve un vector con los 360º indicando 1 si está dentro del sector valido y cero si esta fuera
public int[] Valido(ArrayList<String[]> limites_no_validos){
    int[] valido=new int[360];
    this.ordenary(limites_no_validos,0, limites_no_validos.size()-1);
    for (int grado=0;grado<360;grado++){
        valido[grado]=1;
        for (int j=0;j<limites_no_validos.size();j++){
            double linf=Double.parseDouble(limites_no_validos.get(j)[0]);
            double lsup=Double.parseDouble(limites_no_validos.get(j)[1]);
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

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JComboBox jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
    public SVGUI2 D0;
    private int torre;
    private ArrayList<double[]> sv=new ArrayList<double[]>();

}   
