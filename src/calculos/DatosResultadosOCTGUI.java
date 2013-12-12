package calculos;

import RA.AsuntoIncert;
import RA.AsuntoRA;
import RA.DatosRA2;
import RA.NormaRA;
import RA.SerieRA2;
import RA.TipoRA;
import general.AjusteTablasDinamico;
import general.Auxiliares;
import general.DatosIncertidumbre;
import general.MensajeApp;
import general.TratDecimales;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.WindowEvent;
import java.awt.font.TextAttribute;
import java.sql.SQLException;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;


public class DatosResultadosOCTGUI extends JDialog {
    private String tipoTabla;
    private Integer idAsunto;
    private Integer idSite;
    private Integer valBinMin;
    private Integer valBinMax;
    
    private int posGraDatos;
    
    private DefaultCategoryDataset datasetDatos;
    
    private ArrayList<Integer[]> modosFunc; //<[BinIniModo, BinFinModo], ..., [BinIniModo, BinFinModo]>
    private ArrayList<Integer> modoSalida;
    private Integer idNorma;
    private ArrayList<AsuntoIncert> incertidumbres;

    private ArrayList<ArrayList<Double>> resIncert_IEC_3_0;
    
    public DatosResultadosOCTGUI(java.awt.Frame parent, String tipoTabla, Integer idAsunto, Integer idSite, Integer valBinMin, Integer valBinMax, ArrayList<Integer[]> modosFunc, ArrayList<Integer> modoSalida, Integer idNorma, ArrayList<AsuntoIncert> incertidumbres) {
        super(parent, true);

        initComponents();

        this.tipoTabla = tipoTabla;
        this.idAsunto = idAsunto;
        this.idSite = idSite;
        this.valBinMin = valBinMin;
        this.valBinMax = valBinMax;
        
        this.modosFunc = modosFunc;

        this.modoSalida = modoSalida; //variable para control de llamadas entre diálogos
        
        this.idNorma = idNorma;
        this.incertidumbres = incertidumbres;

		this.resIncert_IEC_3_0 = null;
        
        this.posGraDatos = 0;
        
        try {
            this.jtfAsunto.setText(AsuntoRA.getAsuntoPorId(idAsunto).getNombreCompleto());
        } catch (SQLException e) {
            MensajeApp.muestraError(this, e, "Fallo al consultar la base de datos");
        } catch (NoSuchFieldException e) {
            MensajeApp.muestraError(this, e, "Fallo añadiendo campo");
        }           
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpPrincipal = new javax.swing.JPanel();
        jpClave = new javax.swing.JPanel();
        jlAsunto = new javax.swing.JLabel();
        jtfAsunto = new javax.swing.JTextField();
        jpDatos = new javax.swing.JPanel();
        jtpDatos = new javax.swing.JTabbedPane();
        jpAnalisis = new javax.swing.JPanel();
        jlTitAnalisis = new javax.swing.JLabel();
        jspDatosAnalisis = new javax.swing.JScrollPane();
        jtDatosAnalisis = new general.JTableExport();
        jpGrafica = new javax.swing.JPanel();
        jlTitGrafica = new javax.swing.JLabel();
        jpContGrafica = new javax.swing.JPanel();
        jpGraficaDatos = new javax.swing.JPanel();
        jbGraDatosAnt = new javax.swing.JButton();
        jbGraDatosSig = new javax.swing.JButton();
        jbAnt = new javax.swing.JButton();
        jbSig = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setLocationByPlatform(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jpPrincipal.setBackground(new java.awt.Color(255, 255, 255));

        jpClave.setBackground(new java.awt.Color(255, 255, 255));
        jpClave.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jlAsunto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlAsunto.setText("  Asunto: ");

        jtfAsunto.setEditable(false);
        jtfAsunto.setBackground(new java.awt.Color(204, 204, 204));
        jtfAsunto.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jpClaveLayout = new javax.swing.GroupLayout(jpClave);
        jpClave.setLayout(jpClaveLayout);
        jpClaveLayout.setHorizontalGroup(
            jpClaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpClaveLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlAsunto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 332, Short.MAX_VALUE)
                .addComponent(jtfAsunto, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jpClaveLayout.setVerticalGroup(
            jpClaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpClaveLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpClaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlAsunto)
                    .addComponent(jtfAsunto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpDatos.setBackground(new java.awt.Color(255, 255, 255));
        jpDatos.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jpAnalisis.setBackground(new java.awt.Color(255, 255, 255));

        jlTitAnalisis.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jlTitAnalisis.setForeground(new java.awt.Color(102, 102, 102));
        jlTitAnalisis.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlTitAnalisis.setText("ANÁLISIS TERCIO DE OCTAVA");
        jlTitAnalisis.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jtDatosAnalisis.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jtDatosAnalisis.setToolTipText("OCT Análisis");
        jtDatosAnalisis.getTableHeader().setReorderingAllowed(false);
        jspDatosAnalisis.setViewportView(jtDatosAnalisis);

        javax.swing.GroupLayout jpAnalisisLayout = new javax.swing.GroupLayout(jpAnalisis);
        jpAnalisis.setLayout(jpAnalisisLayout);
        jpAnalisisLayout.setHorizontalGroup(
            jpAnalisisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpAnalisisLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpAnalisisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jspDatosAnalisis, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 727, Short.MAX_VALUE)
                    .addComponent(jlTitAnalisis, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 727, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpAnalisisLayout.setVerticalGroup(
            jpAnalisisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpAnalisisLayout.createSequentialGroup()
                .addComponent(jlTitAnalisis, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jspDatosAnalisis, javax.swing.GroupLayout.DEFAULT_SIZE, 495, Short.MAX_VALUE)
                .addContainerGap())
        );

        jtpDatos.addTab("", jpAnalisis);

        jpGrafica.setBackground(new java.awt.Color(255, 255, 255));

        jlTitGrafica.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jlTitGrafica.setForeground(new java.awt.Color(102, 102, 102));
        jlTitGrafica.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlTitGrafica.setText("GRÁFICAS TERCIO DE OCTAVA");
        jlTitGrafica.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jpContGrafica.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jpGraficaDatosLayout = new javax.swing.GroupLayout(jpGraficaDatos);
        jpGraficaDatos.setLayout(jpGraficaDatosLayout);
        jpGraficaDatosLayout.setHorizontalGroup(
            jpGraficaDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 727, Short.MAX_VALUE)
        );
        jpGraficaDatosLayout.setVerticalGroup(
            jpGraficaDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 464, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jpContGraficaLayout = new javax.swing.GroupLayout(jpContGrafica);
        jpContGrafica.setLayout(jpContGraficaLayout);
        jpContGraficaLayout.setHorizontalGroup(
            jpContGraficaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpGraficaDatos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpContGraficaLayout.setVerticalGroup(
            jpContGraficaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpGraficaDatos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jbGraDatosAnt.setToolTipText("Bin anterior");
        jbGraDatosAnt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muestraGraDatosAnt(evt);
            }
        });

        jbGraDatosSig.setToolTipText("Bin siguiente");
        jbGraDatosSig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muestraGraDatosSig(evt);
            }
        });

        javax.swing.GroupLayout jpGraficaLayout = new javax.swing.GroupLayout(jpGrafica);
        jpGrafica.setLayout(jpGraficaLayout);
        jpGraficaLayout.setHorizontalGroup(
            jpGraficaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpGraficaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpGraficaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jpContGrafica, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlTitGrafica, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 727, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpGraficaLayout.createSequentialGroup()
                        .addComponent(jbGraDatosAnt, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 675, Short.MAX_VALUE)
                        .addComponent(jbGraDatosSig, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jpGraficaLayout.setVerticalGroup(
            jpGraficaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpGraficaLayout.createSequentialGroup()
                .addComponent(jlTitGrafica)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpContGrafica, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpGraficaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbGraDatosAnt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbGraDatosSig, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jtpDatos.addTab("", jpGrafica);

        javax.swing.GroupLayout jpDatosLayout = new javax.swing.GroupLayout(jpDatos);
        jpDatos.setLayout(jpDatosLayout);
        jpDatosLayout.setHorizontalGroup(
            jpDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDatosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jtpDatos, javax.swing.GroupLayout.DEFAULT_SIZE, 752, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpDatosLayout.setVerticalGroup(
            jpDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpDatosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jtpDatos)
                .addContainerGap())
        );

        jbAnt.setBackground(new java.awt.Color(255, 255, 255));
        jbAnt.setText("<<");
        jbAnt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anterior(evt);
            }
        });

        jbSig.setBackground(new java.awt.Color(255, 255, 255));
        jbSig.setText("  >>");
        jbSig.setAlignmentX(0.5F);
        jbSig.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbSig.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jbSig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siguiente(evt);
            }
        });

        javax.swing.GroupLayout jpPrincipalLayout = new javax.swing.GroupLayout(jpPrincipal);
        jpPrincipal.setLayout(jpPrincipalLayout);
        jpPrincipalLayout.setHorizontalGroup(
            jpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpDatos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpClave, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpPrincipalLayout.createSequentialGroup()
                        .addComponent(jbAnt, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 576, Short.MAX_VALUE)
                        .addComponent(jbSig, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jpPrincipalLayout.setVerticalGroup(
            jpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpClave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbSig)
                    .addComponent(jbAnt))
                .addGap(45, 45, 45))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 688, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(804, 722));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void crearColsDatos() throws SQLException {
        this.jtDatosAnalisis.setModel(new javax.swing.table.DefaultTableModel(new Object[][]{}, new Object[]{"Bandas"}) {

            //boolean[] canEdit = new boolean[]{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, true, true, true};
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                //return canEdit[columnIndex];
                return false;
            }
        });

        this.jtDatosAnalisis.getTableHeader().setReorderingAllowed(false);
    }
    /*
    private void rellenarDatos(JProgressBar jpb) throws SQLException, NoSuchFieldException, Exception {
        crearColsDatos();
        
        DefaultTableModel dtmAnalisis = (DefaultTableModel) this.jtDatosAnalisis.getModel();
        dtmAnalisis.setRowCount(0);

        //<bin, valBanda1, valBanda2, ...>
        ArrayList<Object[]> resOCTCorregidos = null;
        ArrayList<ArrayList<Double>> resIncert = null;
        if (!this.idNorma.equals(NormaRA.ID_NORMA_IEC_3_0))
            resOCTCorregidos = DatosRA2.getResultadosOCTCorregidos(this.idAsunto, this.idSite, this.valBinMin, this.valBinMax);
        //else {
        //    resIncert = new ArrayList<ArrayList<Double>>();
        //    resOCTCorregidos = DatosRA2.getResultadosOCTCorregidos_IEC3(idAsunto, idSite, valBinMin, valBinMax, incertidumbres, resIncert);
        //}
        
        Auxiliares.incPorcentajeProgress(jpb, 0.1);

        ArrayList<String> bandas = SerieRA2.getCodigosPorIdAsuntoTipo(idAsunto, TipoRA.getTipoRAPorIdSite(idSite).getIdTipoRA());

        //Añadimos una fila por cada banda
        int nBandas = bandas.size();
        for (int i = 0; i < nBandas; i++) {
            dtmAnalisis.addRow(new Object[]{bandas.get(i)});
        }
        
        //Insertamos los valores de cada bin
        Entry<Double, Integer> entry;
        String valorTxt;
        Integer tipo;
        int nRes = 0;
        
        int nDatosRes, nColBin;
        if (resOCTCorregidos != null) {
            nRes = resOCTCorregidos.size();
            for (int i = 0; i < nRes; i++) {
                nDatosRes = resOCTCorregidos.get(i).length;
                dtmAnalisis.addColumn(resOCTCorregidos.get(i)[0] + Auxiliares.TXT_SERIE_BIN);
                nColBin = dtmAnalisis.getColumnCount() - 1;
                for (int j = 1; j < nDatosRes; j++) { //Ignoramos el primer dato, valor del Bin
                    entry = (Entry<Double, Integer>) resOCTCorregidos.get(i)[j];
                    tipo = entry.getValue();

                    if (entry.getKey() != null)
                        valorTxt = TratDecimales.round(entry.getKey(), TratDecimales.DEC_VARIABLE_RA).toString();
                    else
                        valorTxt = null;

                    switch (tipo) {
                        case DatosRA2.CORREGIDO_RF_OK:
                            //Dejamos el valor como está
                            break;
                        case DatosRA2.CORREGIDO_RF_AVISO:
                            valorTxt += DatosRA2.MARCA_ASTERISCO;
                            break;
                        case DatosRA2.CORREGIDO_RF_ERROR:
                            valorTxt = null;
                            break;
                    }

                    dtmAnalisis.setValueAt(valorTxt, j-1, nColBin); //Nos colocamos a partir de la 2ª columna
                    Auxiliares.incPorcentajeProgress(jpb, 0.15 / nRes);
                }
            }
        }
        
        //Creamos la gráfica
        this.datasetDatos = crearDatasetDatos();
        muestraGraDatos(null);
        
        if (idNorma.equals(NormaRA.ID_NORMA_BWEA)) {
            String frec;
            Double nivel;
            String valorTxtZ = null, valorTxtC = null;
            
            dtmAnalisis.addColumn("Pond. dB Z");
            dtmAnalisis.addColumn("Pond. dB C");
           
            int nColFrecs = 0;
            int nColDbZ = 2;
            int nColDbC = 3;
            
            for (int i = 0; i < nRes; i++) {
                nDatosRes = resOCTCorregidos.get(i).length;
                for (int j = 1; j < nDatosRes; j++) { //Ignoramos el primer dato, valor del Bin
                    entry = (Entry<Double, Integer>) resOCTCorregidos.get(i)[j];
                    tipo = entry.getValue();
                    
                    frec = (String) dtmAnalisis.getValueAt(j-1, nColFrecs);

                    if (entry.getKey() != null) {
                        nivel = entry.getKey();
                        
                        valorTxt = TratDecimales.round(nivel, TratDecimales.DEC_VARIABLE_RA).toString();
                        //Pasamos el nivel a dB Z
                        nivel -= DatosRA2.POND_OCT_Z_TO_A.get(frec);
                        valorTxtZ = TratDecimales.round(nivel, TratDecimales.DEC_VARIABLE_RA).toString();
                        
                        //Pasamos el nivel a dB C
                        nivel += DatosRA2.POND_OCT_Z_TO_C.get(frec);
                        valorTxtC = TratDecimales.round(nivel, TratDecimales.DEC_VARIABLE_RA).toString();
                    } else {
                        valorTxt = null;
                    }

                    switch (tipo) {
                        case DatosRA2.CORREGIDO_RF_OK:
                            //Dejamos el valor como está
                            break;
                        case DatosRA2.CORREGIDO_RF_AVISO:
                            valorTxt += DatosRA2.MARCA_ASTERISCO;
                            valorTxtZ += DatosRA2.MARCA_ASTERISCO;
                            valorTxtC += DatosRA2.MARCA_ASTERISCO;
                            break;
                        case DatosRA2.CORREGIDO_RF_ERROR:
                            valorTxt = null;
                            valorTxtZ = null;
                            valorTxtC = null;
                            break;
                    }

                    dtmAnalisis.setValueAt(valorTxtZ, j-1, nColDbZ); //Nos colocamos a partir de la 2ª columna
                    dtmAnalisis.setValueAt(valorTxtC, j-1, nColDbC); //Nos colocamos a partir de la 2ª columna
                }
            }
        }
        
        AjusteTablasDinamico atdAnalisis = new AjusteTablasDinamico(this.jtDatosAnalisis, AjusteTablasDinamico.ESPACIADO_COL_MED);
        atdAnalisis.maximizaSiEsPosible();
        Auxiliares.centrarTabla(this.jtDatosAnalisis);
    }
    */
    
    
    private void rellenarDatos(JProgressBar jpb) throws SQLException, NoSuchFieldException, Exception {
        crearColsDatos();
        
        DefaultTableModel dtmAnalisis = (DefaultTableModel) this.jtDatosAnalisis.getModel();
        dtmAnalisis.setRowCount(0);

        //<bin, valBanda1, valBanda2, ...>
        ArrayList<ArrayList<Entry<Double, Integer>>> resOCTCorregidos = null;
        if (!this.idNorma.equals(NormaRA.ID_NORMA_IEC_3_0))
            resOCTCorregidos = DatosRA2.getResultadosOCTCorregidos(this.idAsunto, this.idSite, this.valBinMin, this.valBinMax);
        else {
            this.resIncert_IEC_3_0 = new ArrayList<ArrayList<Double>>();
            resOCTCorregidos = DatosRA2.getResultadosOCTCorregidos_IEC3(this.tipoTabla, this.idAsunto, this.idSite, this.valBinMin, this.valBinMax, this.incertidumbres, this.resIncert_IEC_3_0);
        }
        
        Auxiliares.incPorcentajeProgress(jpb, 0.1);

        ArrayList<String> bandas = SerieRA2.getCodigosPorIdAsuntoTipo(idAsunto, TipoRA.getTipoRAPorIdSite(idSite).getIdTipoRA());

        //Añadimos una fila por cada banda
        int nBandas = bandas.size();
        for (int i = 0; i < nBandas; i++) {
            dtmAnalisis.addRow(new Object[]{bandas.get(i)});
        }
        
        //Insertamos los valores de cada bin
        Entry<Double, Integer> entry;
        String valorTxt;
        Integer tipo;
        int nRes = 0;
        
        int nDatosRes, nColBin;
        if (resOCTCorregidos != null) {
            nRes = resOCTCorregidos.size();
            for (int i = 0; i < nRes; i++) {
                nDatosRes = resOCTCorregidos.get(i).size();
                dtmAnalisis.addColumn((this.valBinMin + i) + Auxiliares.TXT_SERIE_BIN);
                nColBin = dtmAnalisis.getColumnCount() - 1;
                for (int j = 0; j < nDatosRes; j++) {
                    entry = resOCTCorregidos.get(i).get(j);
                    tipo = entry.getValue();

                    if (entry.getKey() != null)
                        valorTxt = TratDecimales.round(entry.getKey(), TratDecimales.DEC_VARIABLE_RA).toString();
                    else
                        valorTxt = null;

                    switch (tipo) {
                        case DatosRA2.CORREGIDO_RF_OK:
                            //Dejamos el valor como está
                            break;
                        case DatosRA2.CORREGIDO_RF_AVISO:
                            valorTxt += DatosRA2.MARCA_ASTERISCO;
                            break;
                        case DatosRA2.CORREGIDO_RF_ERROR:
                            valorTxt = DatosRA2.TXT_CORREGIDO_RF_ERROR;
                            break;
                    }

                    dtmAnalisis.setValueAt(valorTxt, j, nColBin);
                    Auxiliares.incPorcentajeProgress(jpb, 0.15 / nRes);
                }
            }
        }
        
        //Creamos la gráfica
        this.datasetDatos = crearDatasetDatos();
        muestraGraDatos(null);
        
        if (idNorma.equals(NormaRA.ID_NORMA_BWEA)) {
            String frec;
            Double nivel;
            String valorTxtZ = null, valorTxtC = null;
            
            dtmAnalisis.addColumn("Pond. dB Z");
            dtmAnalisis.addColumn("Pond. dB C");
           
            int nColFrecs = 0;
            int nColDbZ = 2;
            int nColDbC = 3;
            
            for (int i = 0; i < nRes; i++) {
                nDatosRes = resOCTCorregidos.get(i).size();
                for (int j = 0; j < nDatosRes; j++) { //Ignoramos el primer dato, valor del Bin
                    entry = resOCTCorregidos.get(i).get(j);
                    tipo = entry.getValue();
                    
                    frec = (String) dtmAnalisis.getValueAt(j, nColFrecs);

                    if (entry.getKey() != null) {
                        nivel = entry.getKey();
                        
                        valorTxt = TratDecimales.round(nivel, TratDecimales.DEC_VARIABLE_RA).toString();
                        //Pasamos el nivel a dB Z
                        nivel -= DatosRA2.POND_OCT_Z_TO_A.get(frec);
                        valorTxtZ = TratDecimales.round(nivel, TratDecimales.DEC_VARIABLE_RA).toString();
                        
                        //Pasamos el nivel a dB C
                        nivel += DatosRA2.POND_OCT_Z_TO_C.get(frec);
                        valorTxtC = TratDecimales.round(nivel, TratDecimales.DEC_VARIABLE_RA).toString();
                    } else {
                        valorTxt = null;
                    }

                    switch (tipo) {
                        case DatosRA2.CORREGIDO_RF_OK:
                            //Dejamos el valor como está
                            break;
                        case DatosRA2.CORREGIDO_RF_AVISO:
                            valorTxt += DatosRA2.MARCA_ASTERISCO;
                            valorTxtZ += DatosRA2.MARCA_ASTERISCO;
                            valorTxtC += DatosRA2.MARCA_ASTERISCO;
                            break;
                        case DatosRA2.CORREGIDO_RF_ERROR:
                            valorTxt = DatosRA2.TXT_CORREGIDO_RF_ERROR;
                            valorTxtZ = DatosRA2.TXT_CORREGIDO_RF_ERROR;
                            valorTxtC = DatosRA2.TXT_CORREGIDO_RF_ERROR;
                            break;
                    }

                    dtmAnalisis.setValueAt(valorTxtZ, j, nColDbZ); //Nos colocamos a partir de la 2ª columna
                    dtmAnalisis.setValueAt(valorTxtC, j, nColDbC); //Nos colocamos a partir de la 2ª columna
                }
            }
        }
        
        AjusteTablasDinamico atdAnalisis = new AjusteTablasDinamico(this.jtDatosAnalisis, AjusteTablasDinamico.ESPACIADO_COL_MED);
        atdAnalisis.maximizaSiEsPosible();
        Auxiliares.centrarTabla(this.jtDatosAnalisis);
    }
    
    private JFreeChart crearGraficaDatos(DefaultCategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createBarChart(
        "1/3 Octava",
        "Fecuencia (Hz)", 
        "Presión sonora (dBA)",
        dataset,
        PlotOrientation.VERTICAL,
        true,
        true,
        false);

        TextTitle texto = chart.getTitle();
        texto.setFont(new Font("Arial", Font.BOLD, 13));
        chart.setBackgroundPaint(Color.WHITE);
        CategoryPlot plot = (CategoryPlot) chart.getPlot();

		ValueAxis ranAxis = plot.getRangeAxis();
		String labelAxis = ranAxis.getLabel();
		Font f = ranAxis.getLabelFont();

		AttributedString as = new AttributedString(labelAxis);

		as.addAttribute(TextAttribute.SUPERSCRIPT, TextAttribute.SUPERSCRIPT_SUB, labelAxis.indexOf("A)"), labelAxis.indexOf("A)") + 1);
		as.addAttribute(TextAttribute.SIZE, f.getSize());
		as.addAttribute(TextAttribute.FAMILY, f.getFamily());
		if (f.isBold())
			as.addAttribute(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD);

		ranAxis.setAttributedLabel(as);

        plot.getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(Math.PI * 0.25));
      
        CategoryItemRenderer rendNivel = new BarRenderer();

        for (int i = 0; i <= this.valBinMax - this.valBinMin; i++) {
            rendNivel.setSeriesPaint(i, Auxiliares.COLORES_SERIE0.get(i));
            rendNivel.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());
            plot.setRenderer(i, rendNivel); 
        }
     
        return chart;
    }
    
    private DefaultCategoryDataset crearDatasetDatos() throws Exception {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        int nDatos = this.jtDatosAnalisis.getRowCount();
        int nCols = this.jtDatosAnalisis.getColumnCount();
        
        Object objeto;
        Double valor;

        for (int i = 0; i < nDatos; i++) {
            for (int j = 1; j < nCols; j++) {
                objeto = this.jtDatosAnalisis.getValueAt(i, j);

                if (objeto == null)
                    continue;

                if (objeto instanceof Number)
                    valor = (Double) objeto;
                else {
                    if (((String) objeto).contentEquals(DatosRA2.TXT_CORREGIDO_RF_ERROR))
                        continue;
                    
                    valor = Double.valueOf(((String) objeto).replace(DatosRA2.MARCA_ASTERISCO, ""));
                    //Qué habría que hacer??
                    //continue;
                    //valor = null;
                }

                dataset.addValue(valor, this.jtDatosAnalisis.getColumnName(j), (String) this.jtDatosAnalisis.getValueAt(i, 0));
            }
        }

        return dataset;
    }
    
private void muestraGraDatos(Boolean siguiente) {
    try {
        if (this.datasetDatos.getRowCount() != 0 && this.datasetDatos.getColumnCount() != 0) {
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();

            if (siguiente != null) {
                if (siguiente)
                    this.posGraDatos++;
                else
                    this.posGraDatos--;
            }

            int nRows = this.datasetDatos.getRowCount();
            
            int maxposGraDatos = nRows;
            
            if (maxposGraDatos <= 1) {
                this.jbGraDatosAnt.setEnabled(false);
                this.jbGraDatosSig.setEnabled(false);
            } else
                maxposGraDatos++; //Para poder visualizar todos a la vez

            if (this.posGraDatos >= maxposGraDatos)
                this.posGraDatos -= maxposGraDatos;
            else if (this.posGraDatos < 0)
                this.posGraDatos += maxposGraDatos;

            dataset = (DefaultCategoryDataset) this.datasetDatos.clone();
            
            if (this.posGraDatos != 0) {
                int valBin = this.posGraDatos - 1 + this.valBinMin;
                Comparable rowKey;
                
                for (int i = 0; i < nRows; i++) {
                    rowKey = this.datasetDatos.getRowKey(i);
                    
                    if (!((String) rowKey).startsWith(valBin + Auxiliares.TXT_SERIE_BIN))
                        dataset.removeRow(rowKey);
                }
            }
            
            if (dataset.getRowCount() == 0 || dataset.getColumnCount() == 0) //Si no hay nada que mostrar, mostramos el siguiente (anterior)
                muestraGraDatos(siguiente);
            else {
                Auxiliares.asignaPanelGrafica(this, this.jpGraficaDatos, crearGraficaDatos(dataset), false, null);
                this.update(this.getGraphics());
            }
        }
    } catch (CloneNotSupportedException e) {
        MensajeApp.muestraError(this, e, "Fallo realizando la operación");
    }
}
    
private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
    boolean error = false;
    
    try {
        //Establecemos el título
        this.setTitle("SEGUIMIENTO / " + this.tipoTabla.toUpperCase() + " - RESULTADOS");

        //Establecemos iconos de los botones
        this.jbGraDatosAnt.setIcon(Auxiliares.ICONO_PREV);
        this.jbGraDatosSig.setIcon(Auxiliares.ICONO_NEXT);
    
        //Establecemos las pestañas de JTabbedPane
        Auxiliares.setTitulosJTabbedPane(this.jtpDatos, new String[]{"ANÁLISIS TERCIO DE OCTAVA", "GRÁFICA TERCIO DE OCTAVA"});
        
        //Maximizamos las pestañas
        Auxiliares.maximizaTitulosJTabbedPane(this.jtpDatos);

        //Rellenamos los datos
        Auxiliares.bloqueaDialog(this, true);
        JProgressBar jpb = Auxiliares.muestraProgress(this, 100 * 1000);

        this.rellenarDatos(jpb);

        Auxiliares.ocultaProgress(jpb);
        Auxiliares.bloqueaDialog(this, false);

        this.update(this.getGraphics());
    } catch (SQLException e) {
        error = true;
        MensajeApp.muestraError(this, e, "Fallo al consultar la base de datos");
    } catch (NoSuchFieldException e) {
        error = true;
        MensajeApp.muestraError(this, e, "Fallo añadiendo campo");
    } catch (NumberFormatException e) {
        error = true;
        MensajeApp.muestraError(this, e, "Fallo tratando número");
    } catch (Exception e) {
        error = true;
        MensajeApp.muestraError(this, e, "Fallo realizando acción");
    } finally {
        if (error) {
            Auxiliares.salirDialogo(this, null, this.modoSalida, Auxiliares.MODO_ERROR);
        }
    }
}//GEN-LAST:event_formWindowOpened

private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
    if (Auxiliares.esSalidaUndefined(this.modoSalida)) //Se cierra desde la propia ventana
        Auxiliares.salirDialogo(this, Auxiliares.CANCEL_PROCESS, this.modoSalida, Auxiliares.MODO_CANCEL);
    else
        this.dispose();
}//GEN-LAST:event_formWindowClosing

private void anterior(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anterior
    Auxiliares.setModoSalida(this.modoSalida, Auxiliares.MODO_ANT);
    formWindowClosing(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
}//GEN-LAST:event_anterior

private void siguiente(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siguiente
    LinkedHashMap<Integer, LinkedHashMap<Object, DatosIncertidumbre>> datosIncertidumbre = new LinkedHashMap<Integer, LinkedHashMap<Object, DatosIncertidumbre>>();
    LinkedHashMap<Object, DatosIncertidumbre> datoBinIncertidumbre;
    DatosIncertidumbre datoIncertidumbre = null;
    String campoFrecuencia;
    int nFilas = this.jtDatosAnalisis.getRowCount();
    
    try {
		if (this.resIncert_IEC_3_0 != null) {
			ArrayList<Double> resIncert_IEC_3_0_Bin;
			for (int i = this.valBinMin; i <= this.valBinMax; i++) {
				datoBinIncertidumbre = new LinkedHashMap<Object, DatosIncertidumbre>();

				resIncert_IEC_3_0_Bin = this.resIncert_IEC_3_0.get(i - this.valBinMin);
				
				for (int j = 0; j < nFilas; j++) {
					campoFrecuencia = (String) this.jtDatosAnalisis.getValueAt(j, 0);

					datoIncertidumbre = new DatosIncertidumbre(resIncert_IEC_3_0_Bin.get(j));

					datoBinIncertidumbre.put(campoFrecuencia, datoIncertidumbre);
				}
				
				datosIncertidumbre.put(i, datoBinIncertidumbre);
			}
		} else {
			for (int i = this.valBinMin; i <= this.valBinMax; i++) {
				datoBinIncertidumbre = new LinkedHashMap<Object, DatosIncertidumbre>();
				
				for (int j = 0; j < nFilas; j++) {
					campoFrecuencia = (String) this.jtDatosAnalisis.getValueAt(j, 0);

					datoIncertidumbre = DatosRA2.getIncertidumbreOCT(this.idNorma, this.incertidumbres, this.tipoTabla, this.idAsunto, this.idSite, i, campoFrecuencia);

					datoBinIncertidumbre.put(campoFrecuencia, datoIncertidumbre);
				}
				
				datosIncertidumbre.put(i, datoBinIncertidumbre);
			}
		}

        this.setVisible(false);
        DatosIncertidumbreGUI dI = new DatosIncertidumbreGUI((Frame) this.getParent(), this.tipoTabla, this.idAsunto, datosIncertidumbre, this.idNorma, this.modoSalida);
        dI.setVisible(true);

        //Si volvemos para una modificación (atrás), mostramos de nuevo el diálogo
        if (Auxiliares.esSalidaAnt(this.modoSalida)) {
            Auxiliares.clearModoSalida(this.modoSalida);
            this.setVisible(true);
            return;
        }

        //Si no, pasamos el resultado a formWindowClosing
        formWindowClosing(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    } catch (SQLException e) {
        MensajeApp.muestraError(this, e, "Fallo al consultar la base de datos");
    }
}//GEN-LAST:event_siguiente

private void muestraGraDatosAnt(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muestraGraDatosAnt
    muestraGraDatos(false);
}//GEN-LAST:event_muestraGraDatosAnt

private void muestraGraDatosSig(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muestraGraDatosSig
    muestraGraDatos(true);
}//GEN-LAST:event_muestraGraDatosSig


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbAnt;
    private javax.swing.JButton jbGraDatosAnt;
    private javax.swing.JButton jbGraDatosSig;
    private javax.swing.JButton jbSig;
    private javax.swing.JLabel jlAsunto;
    private javax.swing.JLabel jlTitAnalisis;
    private javax.swing.JLabel jlTitGrafica;
    private javax.swing.JPanel jpAnalisis;
    private javax.swing.JPanel jpClave;
    private javax.swing.JPanel jpContGrafica;
    private javax.swing.JPanel jpDatos;
    private javax.swing.JPanel jpGrafica;
    private javax.swing.JPanel jpGraficaDatos;
    private javax.swing.JPanel jpPrincipal;
    private javax.swing.JScrollPane jspDatosAnalisis;
    private general.JTableExport jtDatosAnalisis;
    public javax.swing.JTextField jtfAsunto;
    private javax.swing.JTabbedPane jtpDatos;
    // End of variables declaration//GEN-END:variables
}