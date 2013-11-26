package calculos;

import RA.AsuntoIncert;
import RA.AsuntoRA;
import RA.DatosRA2;
import general.Auxiliares;
import general.DatosBC;
import general.DatosTonoFFT;
import general.DatosIncertidumbre;
import general.MensajeApp;
import general.ResultadoBandaCriticaFFT;
import general.ResultadoBinFFT;
import general.ResultadoEspectroFFT;
import general.RowsTableCellRenderer;
import general.TratDecimales;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.CategoryTextAnnotation;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.entity.CategoryItemEntity;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.plot.CategoryMarker;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.Layer;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.TextAnchor;


public class DatosResultadosFFTGUI extends JDialog implements ChartMouseListener {
    private String tipoTabla;
    private Integer idAsunto;
    private Integer idSite;
    private Integer valBinMin;
    private Integer valBinMax;
    
    private Double desdeFrec;
    private Double hastaFrec;
    
    private int posGraDatos;
    private int posGraDatosEsp;
    private int posGraDatosEspBC;
    private LinkedHashMap<Integer, LinkedHashMap<Integer, Integer>> mapMaxPosDatos; //<Bin, <Espectro, BC>>
    
    private DefaultCategoryDataset datasetDatos;
    private HashMap<Integer, HashMap<Integer, HashMap<Integer, HashMap<String, Double>>>> markersDatos; //<Bin, <Espectro, <BC, <marker, valor>>>>;
    
    private DatosBC[] datosBC; //[Bin, Espectro, numBC, BCClasificada, BCdeRF]
    
    
    private final String COL_BIN = "Bin";
    private final String COL_ESP = "Esp";
    private final String COL_BC = "BC";
    private final String COL_FREC_MAX = "Frec. Máximo";
    private final String COL_INI_BC = "Ini B. Crítica";
    private final String COL_FIN_BC = "Fin B. Crítica";
    private final String COL_L_70 = "<html>L<sub>70%</sub></html>";
    private final String COL_L_70_MAS_6 = "<html>L<sub>70%</sub> + 6 dB</html>";
    private final String COL_L_PN_AVG_MAS_6 = "<html>L<sub>pn, avg</sub> + 6 dB</html>";
    private final String COL_L_PT_MAX_MENOS_10 = "<html>L<sub>pt, max</sub> - 10 dB</html>";
    
    private final String TXT_SEP_ESPECTRO = " - Espectro ";
    private final String TXT_SEP_PROMEDIO = " - Promedio";
    
    private DatosTonoFFT[] frecMaxDatos; //[[Bin, Espectro, BC, Clave, Frecuencia, PosClasificacion]]; -- Ordenado por Bin-Espectro-BC
    private DatosTonoFFT[] tonoBinDatos; //[[Bin, Espectro, BC, Clave, Frecuencia, PosClasificacion]]; -- Ordenado por Bin-PosClasificacion
    //private HashMap<Integer, HashMap<Integer, ArrayList<DatosTono>>> mapDatosTonoBin; //<Bin, <Tono, <DatosTono>>>
    
    private ArrayList<Integer> modoSalida;
    private Integer idNorma;
    private ArrayList<AsuntoIncert> incertidumbres;
    
    private Integer valBinClasificacion;
    
    //Ver nuevas versiones de Jfreechart, están implementado el soporte a subindices/superíndices
    private final String NIVEL_CRITERIO = "L70% + 6 dB"; //<html>L<sub>70%</sub> + 6 dB</html>
    private final String NIVEL_ENMASCARAMIENTO_MAS_6 = "Lpn, avg + 6 dB"; //<html>L<sub>pn, avg</sub> + 6 dB</html>
    private final String NIVEL_FREC_MAX_MENOS_10 = "Lpt, max - 10 dB"; //<html>L<sub>pt, max</sub> - 10 dB</html>
    
    private final Color COLOR_NIVEL = new Color(255, 255, 0);
    private final Color COLOR_TONO = new Color(255, 0, 0);
    private final Color COLOR_NADA = new Color(0, 255, 0);
    private final Color COLOR_ENMASCARAMIENTO = new Color(0, 0, 255);
    
    public DatosResultadosFFTGUI(java.awt.Frame parent, String tipoTabla, Integer idAsunto, Integer idSite, Integer valBinMin, Integer valBinMax, ArrayList<Integer> modoSalida, Integer idNorma, ArrayList<AsuntoIncert> incertidumbres) {
        super(parent, true);

        initComponents();

        this.tipoTabla = tipoTabla;
        this.idAsunto = idAsunto;
        this.idSite = idSite;
        this.valBinMin = valBinMin;
        this.valBinMax = valBinMax;

        this.modoSalida = modoSalida; //variable para control de llamadas entre diálogos
        
        this.idNorma = idNorma;
        this.incertidumbres = incertidumbres;
        
        this.posGraDatos = 0;
        this.posGraDatosEsp = 0;
        this.posGraDatosEspBC = 0;
        this.mapMaxPosDatos = new LinkedHashMap<Integer, LinkedHashMap<Integer, Integer>>();
        
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

        jpModificarTipo = new javax.swing.JPanel();
        jlPregModificarTipo = new javax.swing.JLabel();
        jrbTono = new javax.swing.JRadioButton();
        jrbNada = new javax.swing.JRadioButton();
        bgModificarTipo = new javax.swing.ButtonGroup();
        jpRangoFrec = new javax.swing.JPanel();
        jtfFrecDesde = new javax.swing.JTextField();
        jtfFrecHasta = new javax.swing.JTextField();
        jlSepFrec = new javax.swing.JLabel();
        jlRangoFrec = new javax.swing.JLabel();
        jpPrincipal = new javax.swing.JPanel();
        jpClave = new javax.swing.JPanel();
        jlAsunto = new javax.swing.JLabel();
        jtfAsunto = new javax.swing.JTextField();
        jpDatos = new javax.swing.JPanel();
        jtpDatos = new javax.swing.JTabbedPane();
        jpDatosFFT = new javax.swing.JPanel();
        jlTitDatos = new javax.swing.JLabel();
        jspDatos = new javax.swing.JScrollPane();
        jtDatos = new general.JTableExport();
        jpAnalisis = new javax.swing.JPanel();
        jlTitAnalisis = new javax.swing.JLabel();
        jbGraDatosAnt = new javax.swing.JButton();
        jbGraDatosEspAnt = new javax.swing.JButton();
        jbGraDatosEspBCAnt = new javax.swing.JButton();
        jbGraDatosEspBCSig = new javax.swing.JButton();
        jbGraDatosEspSig = new javax.swing.JButton();
        jbGraDatosSig = new javax.swing.JButton();
        jpContGrafica = new javax.swing.JPanel();
        jpGraficaAnalisis = new javax.swing.JPanel();
        jpClasificacion = new javax.swing.JPanel();
        jlTitClasificacion = new javax.swing.JLabel();
        jlClasificacion = new javax.swing.JLabel();
        jspClasificacion = new javax.swing.JScrollPane();
        jtClasificacion = new general.JTableExport();
        jbAnadirTono = new javax.swing.JButton();
        jbEliminarTono = new javax.swing.JButton();
        jbSetValor = new javax.swing.JButton();
        jbClearValor = new javax.swing.JButton();
        jlTonosEsp = new javax.swing.JLabel();
        jspTonosEsp = new javax.swing.JScrollPane();
        jtTonosEsp = new general.JTableExport();
        jbBinAnt = new javax.swing.JButton();
        jbBinSig = new javax.swing.JButton();
        jbAutocompletar = new javax.swing.JButton();
        jlBinEstudio = new javax.swing.JLabel();
        jpTonAud = new javax.swing.JPanel();
        jlTitTonAud = new javax.swing.JLabel();
        jspTonAud = new javax.swing.JScrollPane();
        jtTonAud = new general.JTableExport();
        jbRecalcular = new javax.swing.JButton();
        jbAnt = new javax.swing.JButton();
        jbSig = new javax.swing.JButton();

        jpModificarTipo.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jpModificarTipoAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        jlPregModificarTipo.setText("¿Qué tipo de cálculo desea realizar para hallar los niveles de energía?");

        bgModificarTipo.add(jrbTono);
        jrbTono.setText("Tono");

        bgModificarTipo.add(jrbNada);
        jrbNada.setText("Nada");

        javax.swing.GroupLayout jpModificarTipoLayout = new javax.swing.GroupLayout(jpModificarTipo);
        jpModificarTipo.setLayout(jpModificarTipoLayout);
        jpModificarTipoLayout.setHorizontalGroup(
            jpModificarTipoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpModificarTipoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpModificarTipoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jrbNada)
                    .addComponent(jrbTono)
                    .addComponent(jlPregModificarTipo))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpModificarTipoLayout.setVerticalGroup(
            jpModificarTipoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpModificarTipoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlPregModificarTipo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jrbTono)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jrbNada)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpRangoFrec.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jpRangoFrecAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        jtfFrecDesde.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtfFrecDesde.setName("Desde Velocidad"); // NOI18N

        jtfFrecHasta.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtfFrecHasta.setName("Hasta Velocidad"); // NOI18N

        jlSepFrec.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlSepFrec.setText("-");
        jlSepFrec.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jlRangoFrec.setText("Rango de frecuencias");

        javax.swing.GroupLayout jpRangoFrecLayout = new javax.swing.GroupLayout(jpRangoFrec);
        jpRangoFrec.setLayout(jpRangoFrecLayout);
        jpRangoFrecLayout.setHorizontalGroup(
            jpRangoFrecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpRangoFrecLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpRangoFrecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlRangoFrec, javax.swing.GroupLayout.Alignment.CENTER)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpRangoFrecLayout.createSequentialGroup()
                        .addComponent(jtfFrecDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(jlSepFrec, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(jtfFrecHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpRangoFrecLayout.setVerticalGroup(
            jpRangoFrecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpRangoFrecLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlRangoFrec)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpRangoFrecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfFrecDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfFrecHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlSepFrec))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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

        jlAsunto.setFont(new java.awt.Font("Tahoma", 1, 12));
        jlAsunto.setText("  Asunto: ");

        jtfAsunto.setBackground(new java.awt.Color(204, 204, 204));
        jtfAsunto.setEditable(false);
        jtfAsunto.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jpClaveLayout = new javax.swing.GroupLayout(jpClave);
        jpClave.setLayout(jpClaveLayout);
        jpClaveLayout.setHorizontalGroup(
            jpClaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpClaveLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlAsunto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 338, Short.MAX_VALUE)
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

        jpDatosFFT.setBackground(new java.awt.Color(255, 255, 255));

        jlTitDatos.setFont(new java.awt.Font("Tahoma", 0, 12));
        jlTitDatos.setForeground(new java.awt.Color(102, 102, 102));
        jlTitDatos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlTitDatos.setText("DATOS");
        jlTitDatos.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jtDatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jtDatos.setToolTipText("Datos FFT");
        jtDatos.getTableHeader().setReorderingAllowed(false);
        jspDatos.setViewportView(jtDatos);

        javax.swing.GroupLayout jpDatosFFTLayout = new javax.swing.GroupLayout(jpDatosFFT);
        jpDatosFFT.setLayout(jpDatosFFTLayout);
        jpDatosFFTLayout.setHorizontalGroup(
            jpDatosFFTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpDatosFFTLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpDatosFFTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jspDatos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 733, Short.MAX_VALUE)
                    .addComponent(jlTitDatos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 733, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpDatosFFTLayout.setVerticalGroup(
            jpDatosFFTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDatosFFTLayout.createSequentialGroup()
                .addComponent(jlTitDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jspDatos, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                .addContainerGap())
        );

        jtpDatos.addTab("", jpDatosFFT);

        jpAnalisis.setBackground(new java.awt.Color(255, 255, 255));

        jlTitAnalisis.setFont(new java.awt.Font("Tahoma", 0, 12));
        jlTitAnalisis.setForeground(new java.awt.Color(102, 102, 102));
        jlTitAnalisis.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlTitAnalisis.setText("ANÁLISIS DE TONALIDAD");
        jlTitAnalisis.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jbGraDatosAnt.setToolTipText("Bin anterior");
        jbGraDatosAnt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muestraGraDatosAnt(evt);
            }
        });

        jbGraDatosEspAnt.setToolTipText("Espectro anterior");
        jbGraDatosEspAnt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muestraGraDatosEspAnt(evt);
            }
        });

        jbGraDatosEspBCAnt.setToolTipText("Banda crítica anterior");
        jbGraDatosEspBCAnt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muestraGraDatosEspBCAnt(evt);
            }
        });

        jbGraDatosEspBCSig.setToolTipText("Banda crítica siguiente");
        jbGraDatosEspBCSig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muestraGraDatosEspBCSig(evt);
            }
        });

        jbGraDatosEspSig.setToolTipText("Espectro siguiente");
        jbGraDatosEspSig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muestraGraDatosEspSig(evt);
            }
        });

        jbGraDatosSig.setToolTipText("Bin siguiente");
        jbGraDatosSig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muestraGraDatosSig(evt);
            }
        });

        jpContGrafica.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jpGraficaAnalisisLayout = new javax.swing.GroupLayout(jpGraficaAnalisis);
        jpGraficaAnalisis.setLayout(jpGraficaAnalisisLayout);
        jpGraficaAnalisisLayout.setHorizontalGroup(
            jpGraficaAnalisisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 733, Short.MAX_VALUE)
        );
        jpGraficaAnalisisLayout.setVerticalGroup(
            jpGraficaAnalisisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 469, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jpContGraficaLayout = new javax.swing.GroupLayout(jpContGrafica);
        jpContGrafica.setLayout(jpContGraficaLayout);
        jpContGraficaLayout.setHorizontalGroup(
            jpContGraficaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpGraficaAnalisis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpContGraficaLayout.setVerticalGroup(
            jpContGraficaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpGraficaAnalisis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jpAnalisisLayout = new javax.swing.GroupLayout(jpAnalisis);
        jpAnalisis.setLayout(jpAnalisisLayout);
        jpAnalisisLayout.setHorizontalGroup(
            jpAnalisisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpAnalisisLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpAnalisisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jpContGrafica, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlTitAnalisis, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 733, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpAnalisisLayout.createSequentialGroup()
                        .addComponent(jbGraDatosAnt, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbGraDatosEspAnt, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbGraDatosEspBCAnt, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 553, Short.MAX_VALUE)
                        .addComponent(jbGraDatosEspBCSig, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbGraDatosEspSig, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbGraDatosSig, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jpAnalisisLayout.setVerticalGroup(
            jpAnalisisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpAnalisisLayout.createSequentialGroup()
                .addComponent(jlTitAnalisis)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpContGrafica, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpAnalisisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jbGraDatosAnt, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbGraDatosEspAnt, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbGraDatosSig, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbGraDatosEspSig, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbGraDatosEspBCAnt, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbGraDatosEspBCSig, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jtpDatos.addTab("", jpAnalisis);

        jpClasificacion.setBackground(new java.awt.Color(255, 255, 255));
        jpClasificacion.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jlTitClasificacion.setFont(new java.awt.Font("Tahoma", 0, 12));
        jlTitClasificacion.setForeground(new java.awt.Color(102, 102, 102));
        jlTitClasificacion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlTitClasificacion.setText("CLASIFICACIÓN DE TONALIDAD");
        jlTitClasificacion.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jlClasificacion.setFont(new java.awt.Font("Tahoma", 1, 12));
        jlClasificacion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlClasificacion.setText("CLASIFICACIÓN DE TONOS");

        jtClasificacion.getTableHeader().setReorderingAllowed(false);
        jspClasificacion.setViewportView(jtClasificacion);

        jbAnadirTono.setToolTipText("Añadir columna");
        jbAnadirTono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anadirTono(evt);
            }
        });

        jbEliminarTono.setToolTipText("Eliminar columna seleccionada");
        jbEliminarTono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarTono(evt);
            }
        });

        jbSetValor.setToolTipText("Traer valor seleccionado");
        jbSetValor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setValor(evt);
            }
        });

        jbClearValor.setToolTipText("Limpiar valor");
        jbClearValor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearValor(evt);
            }
        });

        jlTonosEsp.setFont(new java.awt.Font("Tahoma", 1, 12));
        jlTonosEsp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlTonosEsp.setText("TONOS DEL ESPECTRO");

        jtTonosEsp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Frecuencias"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtTonosEsp.getTableHeader().setReorderingAllowed(false);
        jtTonosEsp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtTonosEspMouseClicked(evt);
            }
        });
        jspTonosEsp.setViewportView(jtTonosEsp);

        jbBinAnt.setToolTipText("Bin anterior");
        jbBinAnt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muestraClasBinAnt(evt);
            }
        });

        jbBinSig.setToolTipText("Bin siguiente");
        jbBinSig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muestraClasBinSig(evt);
            }
        });

        jbAutocompletar.setText("AUTOCOMPLETAR");
        jbAutocompletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                autocompletarClasificacion(evt);
            }
        });

        jlBinEstudio.setFont(new java.awt.Font("Tahoma", 1, 11));
        jlBinEstudio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jpClasificacionLayout = new javax.swing.GroupLayout(jpClasificacion);
        jpClasificacion.setLayout(jpClasificacionLayout);
        jpClasificacionLayout.setHorizontalGroup(
            jpClasificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpClasificacionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpClasificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlTitClasificacion, javax.swing.GroupLayout.DEFAULT_SIZE, 729, Short.MAX_VALUE)
                    .addGroup(jpClasificacionLayout.createSequentialGroup()
                        .addGroup(jpClasificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpClasificacionLayout.createSequentialGroup()
                                .addGroup(jpClasificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jpClasificacionLayout.createSequentialGroup()
                                        .addComponent(jbBinAnt, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jlBinEstudio, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jbBinSig, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jlClasificacion, javax.swing.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE))
                                .addGap(6, 6, 6))
                            .addGroup(jpClasificacionLayout.createSequentialGroup()
                                .addComponent(jspClasificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jpClasificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpClasificacionLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbAnadirTono, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpClasificacionLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 4, Short.MAX_VALUE)
                                .addGroup(jpClasificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jbSetValor, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jbClearValor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)))
                            .addGroup(jpClasificacionLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbEliminarTono, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpClasificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpClasificacionLayout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jlTonosEsp, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpClasificacionLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jpClasificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jbAutocompletar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jspTonosEsp, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE))))))
                .addContainerGap())
        );
        jpClasificacionLayout.setVerticalGroup(
            jpClasificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpClasificacionLayout.createSequentialGroup()
                .addComponent(jlTitClasificacion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpClasificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlTonosEsp)
                    .addComponent(jlClasificacion))
                .addGap(6, 6, 6)
                .addGroup(jpClasificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jspClasificacion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpClasificacionLayout.createSequentialGroup()
                        .addGroup(jpClasificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jpClasificacionLayout.createSequentialGroup()
                                .addComponent(jbAnadirTono, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbEliminarTono, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jbSetValor, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbClearValor, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jspTonosEsp, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(82, 82, 82)
                        .addComponent(jbAutocompletar, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpClasificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jbBinSig, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(jbBinAnt, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(jlBinEstudio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jtpDatos.addTab("", jpClasificacion);

        jpTonAud.setBackground(new java.awt.Color(255, 255, 255));
        jpTonAud.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jlTitTonAud.setFont(new java.awt.Font("Tahoma", 0, 12));
        jlTitTonAud.setForeground(new java.awt.Color(102, 102, 102));
        jlTitTonAud.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlTitTonAud.setText("TONALIDAD / AUDIBILIDAD");
        jlTitTonAud.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jtTonAud.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Bin", "Tono", "Tonalidad", "Audibilidad"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtTonAud.getTableHeader().setReorderingAllowed(false);
        jspTonAud.setViewportView(jtTonAud);

        jbRecalcular.setBackground(new java.awt.Color(102, 102, 102));
        jbRecalcular.setFont(new java.awt.Font("Tahoma", 1, 11));
        jbRecalcular.setForeground(new java.awt.Color(255, 255, 255));
        jbRecalcular.setText("RECALCULAR");
        jbRecalcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recalcularTonAud(evt);
            }
        });

        javax.swing.GroupLayout jpTonAudLayout = new javax.swing.GroupLayout(jpTonAud);
        jpTonAud.setLayout(jpTonAudLayout);
        jpTonAudLayout.setHorizontalGroup(
            jpTonAudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpTonAudLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpTonAudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jspTonAud, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 729, Short.MAX_VALUE)
                    .addComponent(jbRecalcular, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 729, Short.MAX_VALUE)
                    .addComponent(jlTitTonAud, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 729, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpTonAudLayout.setVerticalGroup(
            jpTonAudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTonAudLayout.createSequentialGroup()
                .addComponent(jlTitTonAud)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jspTonAud, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbRecalcular, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jtpDatos.addTab("", jpTonAud);

        javax.swing.GroupLayout jpDatosLayout = new javax.swing.GroupLayout(jpDatos);
        jpDatos.setLayout(jpDatosLayout);
        jpDatosLayout.setHorizontalGroup(
            jpDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDatosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jtpDatos, javax.swing.GroupLayout.DEFAULT_SIZE, 758, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpDatosLayout.setVerticalGroup(
            jpDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpDatosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jtpDatos, javax.swing.GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE)
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
        jbSig.setText(">>");
        jbSig.setAlignmentX(0.5F);
        jbSig.setEnabled(false);
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 582, Short.MAX_VALUE)
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
            .addComponent(jpPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-810)/2, (screenSize.height-734)/2, 810, 734);
    }// </editor-fold>//GEN-END:initComponents

    private void crearColsDatos() throws SQLException {
        ArrayList<String> columnas = new ArrayList<String>();
        
        columnas.add(COL_BIN);
        columnas.add(COL_ESP);
        columnas.add(COL_BC);
        columnas.add(COL_FREC_MAX);
        columnas.add(COL_INI_BC);
        columnas.add(COL_FIN_BC);
        columnas.add(COL_L_70);
        columnas.add(COL_L_70_MAS_6);
        columnas.add(COL_L_PN_AVG_MAS_6);
        columnas.add(COL_L_PT_MAX_MENOS_10);

        this.jtDatos.setModel(new javax.swing.table.DefaultTableModel(new Object[][]{}, columnas.toArray()) {

            //boolean[] canEdit = new boolean[]{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, true, true, true};
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                //return canEdit[columnIndex];
                return false;
            }
        });
    }
    
    private void rellenarDatos(JProgressBar jpb, boolean graficar) throws SQLException, NoSuchFieldException, Exception {
        Object[] cols, colsBC;
        crearColsDatos();
        
        ArrayList<ResultadoBinFFT> resultados = DatosRA2.getResultadosFFTClasificados(this.idAsunto, this.idSite, this.valBinMin, this.valBinMax, this.desdeFrec, this.hastaFrec, jpb, 0.75, true);
        ResultadoBinFFT resultadoBin;
        ArrayList<ResultadoEspectroFFT> resultadosEsp;
        ResultadoEspectroFFT resultadoEsp;
        ArrayList<ResultadoBandaCriticaFFT> resultadosBC;
        ResultadoBandaCriticaFFT resultadoBC;
        
        int nResultadosBin = resultados.size();
        int nResultadosEsp;
        int nResultadosBC;
        int nColsBC;
        
        DefaultTableModel dtmDatosCoRF = (DefaultTableModel) this.jtDatos.getModel();
        
        ArrayList<Object[]> datosBCAux = new ArrayList<Object[]>();
        
        for (int i = 0; i < nResultadosBin; i++) {
            resultadoBin = resultados.get(i);
            
            resultadosEsp = resultadoBin.getResEspectro();
            nResultadosEsp = resultadosEsp.size();
            
            for (int j = 0; j < nResultadosEsp; j++) {
                resultadoEsp = resultadosEsp.get(j);
            
                resultadosBC = resultadoEsp.getResBandaCritica();
                nResultadosBC = resultadosBC.size();
                
                for (int k = 0; k < nResultadosBC; k++) {
                    resultadoBC = resultadosBC.get(k);
                    
                    colsBC = resultadoBC.toObject();
                    nColsBC = colsBC.length;
                    
                    cols = new Object[4 + nColsBC];
                    
                    cols[0] = resultadoBin.getBin();
                    cols[1] = j + 1;
                    cols[2] = k + 1;
                    //cols[3] = resultadoEsp.getXmlEspectro();
                    
                    for (int l = 0; l < colsBC.length; l++) {
                        cols[l+3] = colsBC[l];
                    }
                    
                    dtmDatosCoRF.addRow(cols);
                    
                    //Guardamos la BC Clasifcada y la de RF en un array temporal
                    datosBCAux.add(new Object[]{cols[0], cols[1], cols[2], resultadoBC.getBandaCriticaClasificada(), resultadoBC.getBandaCriticaRF()});
                    
                }
            }
            Auxiliares.incPorcentajeProgress(jpb, ((i+1) * 0.15) / nResultadosBin);
        }
        
        //Guardamos definitivamente la BC Clasifcada y la de RF
        this.datosBC = DatosBC.getDatosBC(datosBCAux);
        
        //AjusteTablasDinamico atdCoRF = new AjusteTablasDinamico(this.jtDatos, AjusteTablasDinamico.ESPACIADO_COL_MED);
        //atdCoRF.maximizaSiEsPosible();
        Auxiliares.centrarTabla(this.jtDatos);
        this.jtDatos.getTableHeader().setReorderingAllowed(false);
        
        //Creamos la gráfica
        this.datasetDatos = crearDatasetDatos(resultados);
        if (graficar) {
            muestraGraDatos(null, null, null);
        }
        
        //Establecemos el modelo de la tabla de clasificación
        this.jtClasificacion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Espectro"
            }) {
        
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        });
        
        //Rellenamos los datos de clasificación para el primer Bin
        muestraClasificacion(true);
        
        //Deshabilitamos los botones si tenemos como mucho un bin
        if (!DatosTonoFFT.hayVariosBines(this.frecMaxDatos)) {
            this.jbBinSig.setEnabled(false);
            this.jbBinAnt.setEnabled(false);
        }
        
        //Establecemos los listeners
        ListSelectionModel cellSelectionModel = this.jtClasificacion.getSelectionModel();
        cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                int selectedRow = jtClasificacion.getSelectedRow();

                if (selectedRow != -1 && valBinClasificacion != null) {
                    DefaultTableModel dtmTonosEsp = (DefaultTableModel) jtTonosEsp.getModel();
                    
                    //Limpiamos la tabla antes de rellenar
                    dtmTonosEsp.setRowCount(0);

                    Object[][] frecsPos = DatosTonoFFT.getFrecPosDeBinEsp(frecMaxDatos, valBinClasificacion, selectedRow + 1);
                    int nFrecsPos = frecsPos != null ? frecsPos.length : 0;
                    Double frec;
                    
                    for (int i = 0; i < nFrecsPos; i++) {
                        frec = (Double) frecsPos[i][0];
                        dtmTonosEsp.addRow(new Object[]{frec});
                        updateValorUsado(selectedRow, dtmTonosEsp.getRowCount() - 1, frec);
                    }
                }
            }
        });
    }
    
    private JFreeChart crearGraficaDatos(DefaultCategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createBarChart(
                "",
                "Frecuencia (Hz)",
                "Presión sonora equivalente (dBA)",
                null, //dataset
                PlotOrientation.VERTICAL,
                true,
                true,
                false);

        TextTitle texto = chart.getTitle();
        texto.setFont(new Font("Arial", Font.BOLD, 13));
        chart.setBackgroundPaint(Color.WHITE);
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        
        plot.getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 4.0));
        
        DefaultCategoryDataset datasetAux = new DefaultCategoryDataset();

        int nRows = dataset.getRowCount();
        int nCols = dataset.getColumnCount();
        String rowKey, tipoRowKey = "";
        Double valor;
             
        //Unimos las filas de dataset en una única almacenando el color por defecto
        HashMap<Double, String> rendCategories = new HashMap<Double, String>(); 
        
        //Recorremos 1º por categorías para que venga ordenado por frecuencias
        Object[] colsKeys = dataset.getColumnKeys().toArray();
        Arrays.sort(colsKeys);
        
        for (int i = 0; i < nCols; i++) {
            for (int j = 0; j < nRows; j++) {
                rowKey = (String) dataset.getRowKey(j);
            
                if (rowKey.contains(Auxiliares.TXT_SERIE_TONO))
                    tipoRowKey = Auxiliares.TXT_SERIE_TONO;
                else if (rowKey.contains(Auxiliares.TXT_SERIE_ENMASCARAMIENTO))
                    tipoRowKey = Auxiliares.TXT_SERIE_ENMASCARAMIENTO;
                else if (rowKey.contains(Auxiliares.TXT_SERIE_NADA))
                    tipoRowKey = Auxiliares.TXT_SERIE_NADA;

                rowKey = rowKey.substring(0, rowKey.lastIndexOf(Auxiliares.TXT_SERIE_SEP));
                
                valor = (Double) dataset.getValue(dataset.getRowKey(j), (Comparable) colsKeys[i]);
                
                if (valor != null) {
                    datasetAux.addValue(valor, rowKey, (Comparable) colsKeys[i]);
                    rendCategories.put((Double) colsKeys[i], tipoRowKey);
                }
            }
        }
        
        plot.setDataset(datasetAux);
        
        Iterator it = this.mapMaxPosDatos.keySet().iterator();
            
        int count = 0;
        int valBin = -1;

        while (it.hasNext() && count <= this.posGraDatos) {
            valBin = (Integer) it.next();
            count++;
        }
    
        //int valEsp = this.posGraDatosEsp + 1;
        int valEsp = (Integer) this.mapMaxPosDatos.get(valBin).keySet().toArray()[this.posGraDatosEsp];
        int valBC = this.posGraDatosEspBC + 1;

        HashMap<String, Double> markersMap = this.markersDatos.get(valBin).get(valEsp).get(valBC);
                
        Iterator itMarker = markersMap.entrySet().iterator();
        Entry<String, Double> entry;
        
        while (itMarker.hasNext()) {
            entry = (Entry<String, Double>) itMarker.next();
        
            ValueMarker marker = new ValueMarker(entry.getValue());
            marker.setLabel(entry.getKey());
            marker.setLabelAnchor(RectangleAnchor.RIGHT);
            marker.setLabelTextAnchor(TextAnchor.BASELINE_RIGHT);
            
            if (entry.getKey().contentEquals(NIVEL_CRITERIO)) {
                marker.setPaint(COLOR_ENMASCARAMIENTO);
                marker.setLabelPaint(COLOR_ENMASCARAMIENTO);
            } else if (entry.getKey().contentEquals(NIVEL_ENMASCARAMIENTO_MAS_6)) {
                marker.setPaint(COLOR_TONO);
                marker.setLabelPaint(COLOR_TONO);
            } else if (entry.getKey().contentEquals(NIVEL_FREC_MAX_MENOS_10)) {
                marker.setPaint(COLOR_NADA);
                marker.setLabelPaint(COLOR_NADA);
                marker.setStroke(new BasicStroke(1.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1.0f, new float[] {1.0f, 5.0f}, 0.0f));
            }

            plot.addRangeMarker(marker);
        }
        
        //Damos color a las barras
        nCols = datasetAux.getColumnCount();
        
        CategoryItemRenderer rendNivel = new BarRenderer();
        rendNivel.setSeriesPaint(0, COLOR_NIVEL);
        plot.setRenderer(rendNivel);
        
        //Establecemos markers para identificar si son tonos, enmascaramiento o nada
        CategoryMarker marker;
        
        for (int i = 0; i < nCols; i++) {
            marker = new CategoryMarker(datasetAux.getColumnKey(i));
            
            if (rendCategories.get(datasetAux.getColumnKey(i)).equals(Auxiliares.TXT_SERIE_TONO))
                marker.setPaint(COLOR_TONO);
            else if (rendCategories.get(datasetAux.getColumnKey(i)).equals(Auxiliares.TXT_SERIE_ENMASCARAMIENTO))
                marker.setPaint(COLOR_ENMASCARAMIENTO);
            else if (rendCategories.get(datasetAux.getColumnKey(i)).equals(Auxiliares.TXT_SERIE_NADA))
                marker.setPaint(COLOR_NADA);

            marker.setAlpha(0.25f);
//            marker.setLabel("***");
//            marker.setLabelAnchor(RectangleAnchor.TOP);
//            marker.setLabelTextAnchor(TextAnchor.TOP_CENTER);
//            marker.setLabelOffsetType(LengthAdjustmentType.CONTRACT);

            plot.addDomainMarker(marker, Layer.BACKGROUND);
        }
        
        //Indicamos la frecuencia sobre la que se ha calculado la banda crítica
        Double frecMax = this.frecMaxDatos[DatosTonoFFT.localizaDato(frecMaxDatos, valBin, valEsp, valBC)].getFrecMax();
        Double nivelMax = (Double) datasetAux.getValue(datasetAux.getRowKey(0), frecMax);
        //CategoryPointerAnnotation annotation = new CategoryPointerAnnotation("Frec. Centro: " + frecMax, frecMax, nivelMax, 0);
        CategoryTextAnnotation annotation = new CategoryTextAnnotation("Frec. Centro: " + frecMax, frecMax, nivelMax);
        annotation.setTextAnchor(TextAnchor.BASELINE_CENTER);
        plot.addAnnotation(annotation);
         
        //Pintamos las etiquetas del eje
        CategoryAxis domainAxis = plot.getDomainAxis();
        
        //Reducimos el tamaño de las etiquetas
        Font fontLabel = domainAxis.getTickLabelFont();
        fontLabel = new Font(fontLabel.getName(), fontLabel.getStyle(), fontLabel.getSize() - 2);
        domainAxis.setTickLabelFont(fontLabel);
        
        //Creamos una etiqueta vacía para no mostrar demasiadas
        fontLabel = new Font(fontLabel.getName(), fontLabel.getStyle(), 0);
        
        for (int i = 0; i < nCols; i++) {
            if (rendCategories.get(datasetAux.getColumnKey(i)).equals(Auxiliares.TXT_SERIE_TONO))
                domainAxis.setTickLabelPaint(datasetAux.getColumnKey(i), COLOR_TONO);
            else if (rendCategories.get(datasetAux.getColumnKey(i)).equals(Auxiliares.TXT_SERIE_NADA))
                domainAxis.setTickLabelPaint(datasetAux.getColumnKey(i), COLOR_NADA);
            else {
                if (i > 1 && i < nCols - 2) //Siempre pintaremos los dos primeros y últimos valores para ver la escala
                    domainAxis.setTickLabelFont(datasetAux.getColumnKey(i), fontLabel);
            }
        }

        return chart;
    }
    
    private DefaultCategoryDataset crearDatasetDatos(ArrayList<ResultadoBinFFT> resBin) throws Exception {
        LinkedHashMap<Integer, Integer> mapMaxPosDatosEsp;
        Double[][] bandaCriticaClasificada = null;
        Double nivel, frecuencia;
        Integer tipo;
        String rowKey;
        int nDatosBCC, nBin, nEsp, nEspAux, nBC, nDatos;
        
        //Markers
        HashMap<Integer, HashMap<Integer, HashMap<String, Double>>> markersDatosEsp;
        HashMap<Integer, HashMap<String, Double>> markersDatosEspBC;
        HashMap<String, Double> markers;
        Double nivelCriterio, nivelEnmascaramiento, nivelFrecMax;
        
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        int nResBin = resBin.size();
        ArrayList<ResultadoEspectroFFT> resEsp;
        int nResEsp;
        ArrayList<ResultadoBandaCriticaFFT> resBC;
        int nResBC;
        
        this.markersDatos = new HashMap<Integer, HashMap<Integer, HashMap<Integer, HashMap<String, Double>>>>();
        
        ArrayList<DatosTonoFFT> arrayFrecMaxDatos = new ArrayList<DatosTonoFFT>();
        
        //Recorremos los resultados por Bin
        for (int i = 0; i < nResBin; i++) {
            nBin = resBin.get(i).getBin();
            nEsp = 0;
            nEspAux = 0;
            
            mapMaxPosDatosEsp = new LinkedHashMap<Integer, Integer>();
            markersDatosEsp = new HashMap<Integer, HashMap<Integer, HashMap<String, Double>>>();
            
            resEsp = resBin.get(i).getResEspectro();
            nResEsp = resEsp.size();
            
            //Recorremos los resultados del bin --> Espectros
            for (int j = 0; j < nResEsp; j++) {
                nEsp++;
                nEspAux++;
                nBC = 0;
                
                markersDatosEspBC =  new HashMap<Integer, HashMap<String, Double>>();
                
                resBC = resEsp.get(j).getResBandaCritica();
                nResBC = resBC.size();
                
                //Recorremos los resultados del espectro del bin --> Bandas críticas
                for (int k = 0; k < nResBC; k++) {
                    if ((bandaCriticaClasificada = resBC.get(k).getBandaCriticaClasificada()) != null) {
                        nDatosBCC = bandaCriticaClasificada.length;
                        
                        rowKey = nBin + Auxiliares.TXT_SERIE_BIN + Auxiliares.TXT_SERIE_SEP + Auxiliares.TXT_SERIE_ESPECTRO + nEsp + Auxiliares.TXT_SERIE_SEP + Auxiliares.TXT_SERIE_BC + (++nBC) + Auxiliares.TXT_SERIE_SEP;

                        nDatos = 0;

                        for (int l = 0; l < nDatosBCC; l++) {
                            frecuencia = bandaCriticaClasificada[l][0];
                            nivel = bandaCriticaClasificada[l][1];
                            tipo = bandaCriticaClasificada[l][2].intValue();

                            if (tipo.equals(DatosRA2.TIPO_TONO)) {
                                dataset.addValue(nivel, rowKey + Auxiliares.TXT_SERIE_TONO, frecuencia);
                                nDatos++;
                            } else if (tipo.equals(DatosRA2.TIPO_ENMASCARAMIENTO)) {
                                dataset.addValue(nivel, rowKey + Auxiliares.TXT_SERIE_ENMASCARAMIENTO, frecuencia);
                                nDatos++;
                            } else if (tipo.equals(DatosRA2.TIPO_NADA)) {
                                dataset.addValue(nivel, rowKey + Auxiliares.TXT_SERIE_NADA, frecuencia);
                                nDatos++;
                            }
                        }
                        
                        markers = new HashMap<String, Double>();
                        
                        nivelCriterio = resBC.get(k).getNivelCriterio();
                        nivelEnmascaramiento = resBC.get(k).getNivelEnmascaramiento();
                        nivelFrecMax = resBC.get(k).getNivelFrecMax();
                        
                        markers.put(NIVEL_CRITERIO, nivelCriterio);
                        markers.put(NIVEL_ENMASCARAMIENTO_MAS_6, DatosRA2.getNivelInc(nivelEnmascaramiento, 6.0));
                        markers.put(NIVEL_FREC_MAX_MENOS_10, DatosRA2.getNivelInc(nivelFrecMax, -10.0));
                        
                        if (nDatos == 0) { //No se insertado nada
                          nBC--;
                        } else {
                            markersDatosEspBC.put(nBC, markers);
                            arrayFrecMaxDatos.add(new DatosTonoFFT(nBin, nEsp, nBC, resBC.get(k).getFrecMax(), null));
                        }
                    }
                }
                
                if (nBC == 0) { //No se ha insertado nada
                    nEspAux--;
                } else {
                    mapMaxPosDatosEsp.put(nEsp, nBC);
                    
                    if (!markersDatosEspBC.isEmpty())
                        markersDatosEsp.put(nEsp, markersDatosEspBC);
                }
            }
            this.mapMaxPosDatos.put(nBin, mapMaxPosDatosEsp);
            
            if (!markersDatosEsp.isEmpty())
                this.markersDatos.put(nBin, markersDatosEsp);
        }
        
        this.frecMaxDatos = DatosTonoFFT.getArrayFromArrayList(arrayFrecMaxDatos);
        
        return dataset;
    }
    
    private void muestraGraDatos(Boolean siguiente, Boolean siguienteEsp, Boolean siguienteBC) {
    try {
        if (this.datasetDatos.getRowCount() != 0 && this.datasetDatos.getColumnCount() != 0) {
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            DefaultCategoryDataset datasetEsp = new DefaultCategoryDataset();
            DefaultCategoryDataset datasetBC = new DefaultCategoryDataset();

            if (siguiente != null) {
                if (siguiente)
                    this.posGraDatos++;
                else
                    this.posGraDatos--;

                this.posGraDatosEsp = 0;
                this.posGraDatosEspBC = 0;
            }

            if (siguienteEsp != null) {
                if (siguienteEsp)
                    this.posGraDatosEsp++;
                else
                    this.posGraDatosEsp--;

                this.posGraDatosEspBC = 0;
            }

            if (siguienteBC != null) {
                if (siguienteBC)
                    this.posGraDatosEspBC++;
                else
                    this.posGraDatosEspBC--;
            }

            int maxPosGraDatos = this.mapMaxPosDatos.keySet().size();
            
            this.jbGraDatosAnt.setEnabled(maxPosGraDatos > 1);
            this.jbGraDatosSig.setEnabled(maxPosGraDatos > 1);

            if (this.posGraDatos >= maxPosGraDatos)
                this.posGraDatos -= maxPosGraDatos;
            else if (this.posGraDatos < 0)
                this.posGraDatos += maxPosGraDatos;
            
            Iterator it = this.mapMaxPosDatos.keySet().iterator();
            
            int count = 0;
            int valBin = -1;
            
            while (it.hasNext() && count <= this.posGraDatos) {
                valBin = (Integer) it.next();
                count++;
            }
            
            if (this.mapMaxPosDatos.keySet().contains(valBin)) { //Si no, no hay datos para el bin, pasamos al siguiente
                LinkedHashMap<Integer, Integer> mapMaxPosDatosEsp = this.mapMaxPosDatos.get(valBin);
                
                int maxPosDatosEsp = mapMaxPosDatosEsp.keySet().size();

                if (maxPosDatosEsp != 0) { //Si no, no hay datos para el espectro, pasamos al siguiente
                    Comparable rowKey;

                    dataset = (DefaultCategoryDataset) this.datasetDatos.clone();
                    int nDatos = this.datasetDatos.getRowCount();

                    for (int i = 0; i < nDatos; i++) {
                        rowKey = this.datasetDatos.getRowKey(i);
                    
                        if (!((String) rowKey).startsWith(valBin + Auxiliares.TXT_SERIE_BIN))
                            dataset.removeRow(rowKey);
                    }
                    
                    this.jbGraDatosEspAnt.setEnabled(maxPosDatosEsp > 1);
                    this.jbGraDatosEspSig.setEnabled(maxPosDatosEsp > 1);

                    if (this.posGraDatosEsp >= maxPosDatosEsp)
                        this.posGraDatosEsp -= maxPosDatosEsp;
                    else if (this.posGraDatosEsp < 0)
                        this.posGraDatosEsp += maxPosDatosEsp;

                    //int valEsp = this.posGraDatosEsp + 1;
                    int valEsp = (Integer) mapMaxPosDatosEsp.keySet().toArray()[this.posGraDatosEsp];
                    
                    int maxPosDatosEspBC = mapMaxPosDatosEsp.get(valEsp);

                    if (maxPosDatosEspBC != 0) { //Si no, no hay datos para la Banda crítica, pasamos a la siguiente
                        datasetEsp = (DefaultCategoryDataset) dataset.clone();
                        int nDatosEsp = dataset.getRowCount();

                        for (int i = 0; i < nDatosEsp; i++) {
                            rowKey = dataset.getRowKey(i);

                            if (!((String) rowKey).contains(Auxiliares.TXT_SERIE_ESPECTRO + valEsp + Auxiliares.TXT_SERIE_SEP))
                                datasetEsp.removeRow(rowKey);
                        }
                        
                        this.jbGraDatosEspBCAnt.setEnabled(maxPosDatosEspBC > 1);
                        this.jbGraDatosEspBCSig.setEnabled(maxPosDatosEspBC > 1);

                        if (this.posGraDatosEspBC >= maxPosDatosEspBC)
                            this.posGraDatosEspBC -= maxPosDatosEspBC;
                        else if (this.posGraDatosEspBC < 0)
                            this.posGraDatosEspBC += maxPosDatosEspBC;

                        int valEspBC = this.posGraDatosEspBC + 1;
                    
                        datasetBC = (DefaultCategoryDataset) datasetEsp.clone();
                        int nDatosBC = datasetEsp.getRowCount();

                        for (int i = 0; i < nDatosBC; i++) {
                            rowKey = datasetEsp.getRowKey(i);

                            if (!((String) rowKey).contains(Auxiliares.TXT_SERIE_BC + valEspBC + Auxiliares.TXT_SERIE_SEP))
                                datasetBC.removeRow(rowKey);
                        }
                    } else {
                        if (siguienteBC == null)
                            siguienteBC = true;
                    }
                } else {
                    if (siguienteEsp == null)
                        siguienteEsp = true;
                }
            } else {
                if (siguiente == null)
                    siguiente = true;
            }

            if (datasetBC.getRowCount() == 0 || datasetBC.getColumnCount() == 0) //Si no hay nada que mostrar, mostramos el siguiente (anterior)
                muestraGraDatos(siguiente, siguienteEsp, siguienteBC);
            else {
                Auxiliares.asignaPanelGrafica(this, this.jpGraficaAnalisis, crearGraficaDatos(datasetBC), true, this);
                this.update(this.getGraphics());
            }
        }
    } catch (Exception e) {
        MensajeApp.muestraError(this, e, "Fallo realizando la operación");
    }
}
    
private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
    boolean error = false;
    boolean graficar = false;
    
    try {
        //Establecemos el título
        this.setTitle("SEGUIMIENTO / " + this.tipoTabla.toUpperCase() + " - RESULTADOS");

        //Establecemos iconos de los botones
        this.jbGraDatosAnt.setIcon(Auxiliares.ICONO_PREV);
        this.jbGraDatosSig.setIcon(Auxiliares.ICONO_NEXT);
        this.jbGraDatosEspAnt.setIcon(Auxiliares.ICONO_PREV);
        this.jbGraDatosEspSig.setIcon(Auxiliares.ICONO_NEXT);
        this.jbGraDatosEspBCAnt.setIcon(Auxiliares.ICONO_PREV);
        this.jbGraDatosEspBCSig.setIcon(Auxiliares.ICONO_NEXT);
        
        //Establecemos las pestañas de JTabbedPane
        Auxiliares.setTitulosJTabbedPane(this.jtpDatos, new String[]{"DATOS", "GRÁFICAS", "CLASIFICACIÓN", "TONALIDAD / AUDIBILIDAD"});
        
        //Maximizamos las pestañas
        Auxiliares.maximizaTitulosJTabbedPane(this.jtpDatos);
        
        this.jtfFrecDesde.setText(((Double) 0.0).toString());
        this.jtfFrecHasta.setText(((Double) 20000.0).toString());
        
        if (JOptionPane.showConfirmDialog(this, this.jpRangoFrec, "Selección de rango de frecuencias", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
            this.desdeFrec = Double.parseDouble(this.jtfFrecDesde.getText());
            this.hastaFrec = Double.parseDouble(this.jtfFrecHasta.getText());
        
            if (JOptionPane.showConfirmDialog(this, "¿Desea graficar los resultados? Esto podría incrementar el tiempo de espera", "", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                graficar = true;
            }

            //Rellenamos los datos
            Auxiliares.bloqueaDialog(this, true);
            JProgressBar jpb = Auxiliares.muestraProgress(this, 100 * 100000);

            this.rellenarDatos(jpb, graficar);

            Auxiliares.ocultaProgress(jpb);
            Auxiliares.bloqueaDialog(this, false);

            this.update(this.getGraphics());
        } else
            anterior(null);
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
    LinkedHashMap<Object, DatosIncertidumbre> datoBinIncertidumbre = null;
    DatosIncertidumbre datoIncertidumbre = null;
    
    Integer valBin = null, valBinAnt = null, valEsp = null, posClasificacion = null, posClasificacionAnt = null;
    Double valorTonalidadBin;
    Double valorTonalidadBinRF;
    int nFilas = this.jtTonAud.getRowCount();
    
    int colBin = this.jtTonAud.getColumnModel().getColumnIndex("Bin");
    int colTono = this.jtTonAud.getColumnModel().getColumnIndex("Tono");
    int colTonalidad = this.jtTonAud.getColumnModel().getColumnIndex("Tonalidad");
    
    try {
        Auxiliares.bloqueaDialog(this, true);
        JProgressBar jpb = Auxiliares.muestraProgress(this, 100 * 100000);
        
        DatosTonoFFT[] tonoBinDatosRF = DatosTonoFFT.getDatosCalcIncertRF(this.idAsunto, this.idSite, this.valBinMin, this.valBinMax, this.tonoBinDatos, jpb);

        int nDatos = this.tonoBinDatos.length;
        int nDatosRF = tonoBinDatosRF.length;
        DatosTonoFFT tonoBinDato;
        DatosTonoFFT tonoBinDatoRF;
        ArrayList<Double> tonalidadesEsp = null;
        ArrayList<Double> tonalidadesEspRF = null;
        Boolean fftEnMismaFrecuencia = null;
        Double frecTono = null;
        int posIniBinTonoRF = -1;
        Integer valTono = null;
        String txtColTono;
        
        for (int i = 0; i < nDatos; i++) {
            tonoBinDato = this.tonoBinDatos[i];
            
            valBin = tonoBinDato.getValBin();
            valEsp = tonoBinDato.getValEsp();
            posClasificacion = tonoBinDato.getPosClasificacion();
            tonoBinDato.getTonalidad();
            
            if (valBinAnt == null || !valBin.equals(valBinAnt) || posClasificacionAnt == null || !posClasificacion.equals(posClasificacionAnt)) {
                if (tonalidadesEsp != null && !tonalidadesEsp.isEmpty()) {
                    valorTonalidadBin = null;
                    valorTonalidadBinRF = null;
                
                    for (int j = 0; j < nFilas; j++) {
                        txtColTono = (String) this.jtTonAud.getValueAt(j, colTono);
                        if (txtColTono.contains(TXT_SEP_ESPECTRO))
                            continue;
                            
                        if (!valBinAnt.equals((Integer) this.jtTonAud.getValueAt(j, colBin)))
                            continue;
                        valTono = Integer.parseInt(txtColTono.replaceAll("[^0-9]+", "").trim());
                        if (!posClasificacionAnt.equals(valTono))
                            continue;

                        valorTonalidadBin = (Double) this.jtTonAud.getValueAt(j, colTonalidad);
                        break;
                    }
                    
                    valorTonalidadBinRF = DatosRA2.getNivelPromedioEnergetico(Auxiliares.arrayObjToDouble(tonalidadesEspRF.toArray()));
                    
                    datoIncertidumbre = DatosRA2.getIncertidumbreFFT(this.idNorma, this.incertidumbres, this.tipoTabla, tonalidadesEsp, valorTonalidadBin, tonalidadesEspRF, valorTonalidadBinRF, fftEnMismaFrecuencia);

                    datoBinIncertidumbre.put(posClasificacionAnt, datoIncertidumbre);
                }
                
                if (valBinAnt == null || !valBin.equals(valBinAnt)) {
                    if (datoBinIncertidumbre != null && !datoBinIncertidumbre.isEmpty())
                        datosIncertidumbre.put(valBinAnt, datoBinIncertidumbre);
                        
                    valBinAnt = valBin;
                    datoBinIncertidumbre = new LinkedHashMap<Object, DatosIncertidumbre>();
                }
                
                tonalidadesEsp = new ArrayList<Double>();
                tonalidadesEspRF = new ArrayList<Double>();
                posClasificacionAnt = posClasificacion;
                fftEnMismaFrecuencia = true;
                frecTono = tonoBinDato.getFrecMax();
                
                posIniBinTonoRF = -1;
                
                if (posClasificacion == null) {
                    posClasificacionAnt = null;
                    continue;
                } else {
                    for (int j = 0; j < nDatosRF; j++) {
                        tonoBinDatoRF = tonoBinDatosRF[j];

                        if (tonoBinDatoRF.getValBin() < valBin)
                            continue;
                        if (tonoBinDatoRF.getValBin() > valBin)
                            break;
                        if (tonoBinDatoRF.getPosClasificacion() == null || !posClasificacion.equals(tonoBinDatoRF.getPosClasificacion()))
                            continue;

                        //Estamos al comienzo de los datos que buscamos
                        posIniBinTonoRF = j;
                        break;
                    }
                }
            }
            
            tonalidadesEsp.add(tonoBinDato.getTonalidad());
            
            if (posIniBinTonoRF >= 0) {
                tonoBinDatoRF = tonoBinDatosRF[posIniBinTonoRF];

                for (int j = posIniBinTonoRF; j < nDatosRF; j++) {
                    tonoBinDatoRF = tonoBinDatosRF[j];

                    if (!valBin.equals(tonoBinDatoRF.getValBin()) || !posClasificacion.equals(tonoBinDatoRF.getPosClasificacion()))
                        break;
                    if (tonoBinDatoRF.getValEsp() < valEsp)
                        continue;
                    if (tonoBinDatoRF.getValEsp() > valEsp)
                        break;
                    
                    tonalidadesEspRF.add(tonoBinDatoRF.getTonalidad());
                }
            }
            
            if (frecTono != tonoBinDato.getFrecMax())
                fftEnMismaFrecuencia = false;
            
            Auxiliares.incPorcentajeProgress(jpb, 0.25 * (i+1) / nDatos);
        }
        
        //Caso de salida
        if (nDatos > 0) {
            if (tonalidadesEsp != null && !tonalidadesEsp.isEmpty()) {
                    valorTonalidadBin = null;
                    valorTonalidadBinRF = null;
                
                    for (int j = 0; j < nFilas; j++) {
                        txtColTono = (String) this.jtTonAud.getValueAt(j, colTono);
                        if (txtColTono.contains(TXT_SEP_ESPECTRO))
                            continue;
                        if (valBin != (Integer) this.jtTonAud.getValueAt(j, colBin))
                            continue;
                        valTono = Integer.parseInt(((String) this.jtTonAud.getValueAt(j, colTono)).replaceAll("[^0-9]+", "").trim());
                        if (!posClasificacion.equals(valTono))
                            continue;

                        valorTonalidadBin = (Double) this.jtTonAud.getValueAt(j, colTonalidad);
                        break;
                    }
                    
                    valorTonalidadBinRF = DatosRA2.getNivelPromedioEnergetico(Auxiliares.arrayObjToDouble(tonalidadesEspRF.toArray()));

                    datoIncertidumbre = DatosRA2.getIncertidumbreFFT(this.idNorma, this.incertidumbres, this.tipoTabla, tonalidadesEsp, valorTonalidadBin, tonalidadesEspRF, valorTonalidadBinRF, fftEnMismaFrecuencia);

                    datoBinIncertidumbre.put(posClasificacionAnt, datoIncertidumbre);
                }
            
            if (datoBinIncertidumbre != null && !datoBinIncertidumbre.isEmpty())
                datosIncertidumbre.put(valBin, datoBinIncertidumbre);
            
            Auxiliares.incPorcentajeProgress(jpb, 0.25 / nDatos);
        }

        Auxiliares.ocultaProgress(jpb);
        Auxiliares.bloqueaDialog(this, false);

        this.setVisible(false);
        DatosIncertidumbreGUI dI = new DatosIncertidumbreGUI((Frame) this.getParent(), this.tipoTabla, this.idAsunto, datosIncertidumbre, this.modoSalida);
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
    } catch (NoSuchFieldException e) {
        MensajeApp.muestraError(this, e, "Fallo buscando campo");
    } catch (Exception e) {
        MensajeApp.muestraError(this, e, "Fallo realizando la operación");
    }
}//GEN-LAST:event_siguiente

private void muestraGraDatosAnt(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muestraGraDatosAnt
    muestraGraDatos(false, null, null);
}//GEN-LAST:event_muestraGraDatosAnt

private void muestraGraDatosSig(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muestraGraDatosSig
    muestraGraDatos(true, null, null);
}//GEN-LAST:event_muestraGraDatosSig

private void muestraGraDatosEspAnt(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muestraGraDatosEspAnt
    muestraGraDatos(null, false, null);
}//GEN-LAST:event_muestraGraDatosEspAnt

private void muestraGraDatosEspSig(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muestraGraDatosEspSig
    muestraGraDatos(null, true, null);
}//GEN-LAST:event_muestraGraDatosEspSig

private void muestraGraDatosEspBCAnt(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muestraGraDatosEspBCAnt
    muestraGraDatos(null, null, false);
}//GEN-LAST:event_muestraGraDatosEspBCAnt

private void muestraGraDatosEspBCSig(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muestraGraDatosEspBCSig
    muestraGraDatos(null, null, true);
}//GEN-LAST:event_muestraGraDatosEspBCSig

private void anadirTono(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anadirTono
    DefaultTableModel dtmClasificacion = (DefaultTableModel) this.jtClasificacion.getModel();
    
    int ultTono = 0;
    int nCols = dtmClasificacion.getColumnCount();
    
    if (nCols > 1) {
        ultTono = Integer.parseInt(((String) this.jtClasificacion.getColumnName(nCols - 1)).replace("Tono ", ""));
    }
    dtmClasificacion.addColumn("Tono " + (ultTono + 1));
}//GEN-LAST:event_anadirTono

private void eliminarTono(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarTono
    int posCol = this.jtClasificacion.getSelectedColumn();
    
    if (posCol > 0) {
        //Eliminamos las posiciones de la columna borrada
        //y actualizamos las posiciones de la clasificación para las columnas por encima de la columna borrada
        this.frecMaxDatos = DatosTonoFFT.eliminaTono(this.frecMaxDatos, this.valBinClasificacion, posCol);
        
        //Mostramos los cambios realizados
        muestraClasificacion(null);
    }
}//GEN-LAST:event_eliminarTono

private void muestraClasificacion(Boolean siguiente) {
    Integer[] bines = DatosTonoFFT.getBines(this.frecMaxDatos);
    int nBines = bines != null ? bines.length : 0;
    
    if (nBines > 0) {
        //Ordenamos los bines
        Arrays.sort(bines);

        if (siguiente != null) {
            if (valBinClasificacion != null) {
                int valBinAnt = bines[0], valBinSig = bines[0];
                for (int i = 0; i < nBines; i++) {
                    valBinAnt = bines[(i-1+nBines)%nBines];
                    valBinSig = bines[(i+1)%nBines];

                    //Hemos encontrado el valor
                    if (bines[i] == valBinClasificacion)
                        break;
                }

                if (siguiente)
                    valBinClasificacion = valBinSig;
                else
                    valBinClasificacion = valBinAnt;

            } else
                valBinClasificacion = bines[0];
        }
        
        this.jlBinEstudio.setText("BIN " + valBinClasificacion);

        DefaultTableModel dtmClasificacion = (DefaultTableModel) this.jtClasificacion.getModel();
        DefaultTableModel dtmTonosEsp = (DefaultTableModel) this.jtTonosEsp.getModel();
        
        //Vaciamos las tablas
        dtmClasificacion.setRowCount(0);
        dtmTonosEsp.setRowCount(0);
        
        //Dejamos la columna de Espectro
        TableColumnModel tcm = this.jtClasificacion.getColumnModel();
        dtmClasificacion.setColumnIdentifiers(new Object[]{tcm.getColumn(0).getHeaderValue()});
        
        //Recorremos las posiciones de clasificación para ver si tenemos que añadir columnas
        int nTonos = DatosTonoFFT.getNumTonosBin(this.frecMaxDatos, valBinClasificacion);
        
        for (int i = 0; i < nTonos; i++) { //Tenemos que añadir columnas para los tonos
            anadirTono(null);
        }
        
        //Rellenamos la tabla
        Object[] fila;
        
        //Integer[] espectros = DatosTonoFFT.getEspectrosBin(this.frecMaxDatos, valBinClasificacion);
        //int nEspectros = espectros.length;
        int nEspectros = 12;
        Object[][] datosEsp;
        int nDatosEsp;
        Integer posClasificacion;
        
        for (int i = 0; i < nEspectros; i++) {
            fila = new Object[1 + nTonos];//Nº espectro + posibles tonos
            
            datosEsp = DatosTonoFFT.getFrecPosDeBinEsp(this.frecMaxDatos, valBinClasificacion, i + 1);
            nDatosEsp = datosEsp != null ? datosEsp.length : 0;
            
            //fila[0] = espectros[i];
            fila[0] = i + 1;
            
            for (int j = 0; j < nDatosEsp; j++) {
                posClasificacion = (Integer) datosEsp[j][1];
                if (posClasificacion != null)
                    fila[posClasificacion] = datosEsp[j][0];
            }
            
            dtmClasificacion.addRow(fila);
        }
    }
    
    //Deshabilitamos el botón de siguiente hasta que se recalcule la tonalidad
    this.jbSig.setEnabled(false);
}

private void muestraClasBinAnt(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muestraClasBinAnt
    muestraClasificacion(false);
}//GEN-LAST:event_muestraClasBinAnt

private void muestraClasBinSig(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muestraClasBinSig
    muestraClasificacion(true);
}//GEN-LAST:event_muestraClasBinSig

private void actualizarPosClasificacion(int rowSelClas, Integer colSelClas, Double frecMax) {
    int posDato = DatosTonoFFT.localizaDatoBinEspFrecMax(this.frecMaxDatos, valBinClasificacion, rowSelClas + 1, frecMax);
    
    if (posDato >= 0)
        this.frecMaxDatos[posDato].setPosClasificacion(colSelClas);
}

private void updateValorUsado(int rowIndexClasificacion, int rowIndexTonosEsp, Double frecMax) {
    Double valorFrec;
    
    if (rowIndexClasificacion != -1 && rowIndexTonosEsp != -1) {
        DefaultTableModel dtmTonosEsp = (DefaultTableModel) this.jtTonosEsp.getModel();
        int nCols = this.jtClasificacion.getColumnCount();
        int i;

        for (i = 1; i < nCols; i++) {
            //Se ha utilizado el valor
            if ((valorFrec = (Double) jtClasificacion.getValueAt(rowIndexClasificacion, i)) != null && valorFrec.equals(frecMax)) {
                //jtTonosEsp.updateCeldasColor(jtTonosEsp.getRowCount() - 1, 0, COLOR_TONO, true);
                dtmTonosEsp.setValueAt("**** " + frecMax + " ****", rowIndexTonosEsp, 0);

                //Encontrado, No hace falta seguir mirando
                break;
            }
        }
            
        //No se ha encontrado
        if (i == nCols) {
            dtmTonosEsp.setValueAt(frecMax, rowIndexTonosEsp, 0);
        }
    }
}

private void setValor(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setValor
    int rowSelClas = this.jtClasificacion.getSelectedRow();
    int colSelClas = this.jtClasificacion.getSelectedColumn();
    int rowSelTonos = this.jtTonosEsp.getSelectedRow();
    int colSelTonos = this.jtTonosEsp.getSelectedColumn();
    Object valor;
    Double frecMax = null;
    
    if (rowSelClas != -1 && colSelClas > 0 && rowSelTonos != -1 && colSelTonos != -1) {
        valor = this.jtTonosEsp.getValueAt(rowSelTonos, colSelTonos);
        
        if (valor instanceof Double)
            frecMax = (Double) valor;
        else if (valor instanceof String)
            frecMax = Double.parseDouble(((String) valor).replaceAll("\\*", "").trim());
        
        this.jtClasificacion.setValueAt(frecMax, rowSelClas, colSelClas);
        
        //Guardamos la información en memoria
        actualizarPosClasificacion(rowSelClas, colSelClas, frecMax);
        
        //Refrescamos la tabla de frecuencias
        updateValorUsado(rowSelClas, rowSelTonos, frecMax);
        
        //Deshabilitamos el botón de siguiente hasta que se recalcule la tonalidad
        this.jbSig.setEnabled(false);
    }
}//GEN-LAST:event_setValor

private void clearValor(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearValor
    int rowSelClas = this.jtClasificacion.getSelectedRow();
    int colSelClas = this.jtClasificacion.getSelectedColumn();
    int nTonos = this.jtTonosEsp.getRowCount();
    int rowSelTonos = -1;
    Object valor;
    Double frecMax;
    Double valorTono = null;
    
    if (rowSelClas != -1 && colSelClas > 0) {
        frecMax = (Double) this.jtClasificacion.getValueAt(rowSelClas, colSelClas);
        
        if (frecMax != null) {
            for (int i = 0; i < nTonos; i++) {
                valor = this.jtTonosEsp.getValueAt(i, 0);

                if (valor != null) {
                    if (valor instanceof Double)
                        valorTono = (Double) valor;
                    else if (valor instanceof String)
                        valorTono = Double.parseDouble(((String) valor).replaceAll("\\*", "").trim());
                }

                if (valorTono != null && valorTono.equals(frecMax)) {
                    rowSelTonos = i;
                    break;
                }
            }

            this.jtClasificacion.setValueAt(null, rowSelClas, colSelClas);

            //Guardamos la información en memoria
            actualizarPosClasificacion(rowSelClas, null, frecMax);

            //Refrescamos la tabla de frecuencias
            updateValorUsado(rowSelClas, rowSelTonos, frecMax);
                                
            //Deshabilitamos el botón de siguiente hasta que se recalcule la tonalidad
            this.jbSig.setEnabled(false);
        }
    }
}//GEN-LAST:event_clearValor

private int getFilaDatos(Integer valBin, Integer valEsp, Integer valBC, DefaultTableModel dtmDatos, TableColumnModel tcmDatos) {
    int fila = -1;
    
    int nFilasDatos = dtmDatos.getRowCount();
    
    int colBin = tcmDatos.getColumnIndex(COL_BIN);
    int colEsp = tcmDatos.getColumnIndex(COL_ESP);
    int colBC = tcmDatos.getColumnIndex(COL_BC);
    
    for (int i = 0; i < nFilasDatos; i++) {
        if (!valBin.equals((Integer) dtmDatos.getValueAt(i, colBin)))
            continue;
        if (!valEsp.equals((Integer) dtmDatos.getValueAt(i, colEsp)))
            continue;
        if (!valBC.equals((Integer) dtmDatos.getValueAt(i, colBC)))
            continue;

        //Hemos encontrado la fila, no hace falta continuar
        fila = i;
        break;
    }
    
    return fila;
}

private void recalcularTonAud(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recalcularTonAud
    DefaultTableModel dtmDatos = (DefaultTableModel) this.jtDatos.getModel();
    TableColumnModel tcmDatos = this.jtDatos.getColumnModel();
    
    DefaultTableModel dtmTonAud = (DefaultTableModel) this.jtTonAud.getModel();
    
    dtmTonAud.setRowCount(0);
    
    //Recolocamos los datos de forma que nos vengan ordenados por Bin, tono
    this.tonoBinDatos = this.frecMaxDatos.clone();
    this.tonoBinDatos = DatosTonoFFT.ordenarPor(this.tonoBinDatos, DatosTonoFFT.ORD_BIN_TONO);
    DatosTonoFFT tonoBinDato;
    
    //En las primeras posiciones tendremos los que no están asignados
    
    Double[][] bandaCriticaClasAG;
    Double[][] bandaCriticaRF;
    Double nivelCriterio, tonalidadEsp, tonalidad, audibilidad, frecSum = null;
    ArrayList<Double> tonalidadesEsp = null;

    DatosBC datoBC;
    
    int colNivelCriterio = tcmDatos.getColumnIndex(COL_L_70_MAS_6);
    
    Integer valBin = null, valBinAnt = null;
    Integer posClasificacion = null, posClasificacionAnt = null;
    
    RowsTableCellRenderer rtcr = new RowsTableCellRenderer();
    
    int nDatosTonoBin = this.tonoBinDatos.length;
    for (int i = 0; i < nDatosTonoBin; i++) {
        tonoBinDato = this.tonoBinDatos[i];
        
        valBin = tonoBinDato.getValBin();
        posClasificacion = tonoBinDato.getPosClasificacion();
        
        //Condiciones de cambio de tono
        if (valBinAnt == null || !valBin.equals(valBinAnt) || posClasificacionAnt == null || posClasificacion == null || !posClasificacion.equals(posClasificacionAnt)) {
            if (tonalidadesEsp != null && !tonalidadesEsp.isEmpty()) {
                tonalidad = DatosRA2.getTonalidadPromedioEspectros(tonalidadesEsp);
                audibilidad = DatosRA2.getAudibilidadTonal(tonalidad, frecSum/tonalidadesEsp.size());
                
                dtmTonAud.addRow(new Object[]{valBinAnt, posClasificacionAnt + TXT_SEP_PROMEDIO, tonalidad, audibilidad >= -3.0 ? audibilidad + " *** " : audibilidad});
                rtcr.addColoredRow(dtmTonAud.getRowCount() - 1, Color.LIGHT_GRAY, Color.BLACK);
            }
            
            if (valBinAnt == null || !valBin.equals(valBinAnt))
                valBinAnt = valBin;
            
            posClasificacionAnt = posClasificacion;
            frecSum = 0.0;
            tonalidadesEsp = new ArrayList<Double>();
            
            if (posClasificacion == null) {
                posClasificacionAnt = null;
                continue;
            }
        }
        
        frecSum = TratDecimales.round(frecSum + tonoBinDato.getFrecMax(), TratDecimales.DEC_VARIABLE_RA);
        
        datoBC = this.datosBC[DatosBC.localizaDato(this.datosBC, valBin, tonoBinDato.getValEsp(), tonoBinDato.getValBC())];
        bandaCriticaClasAG = datoBC.getBandaCriticaClasificada();
        bandaCriticaRF = datoBC.getBandaCriticaRF();
        nivelCriterio = (Double) dtmDatos.getValueAt(getFilaDatos(valBin, tonoBinDato.getValEsp(), tonoBinDato.getValBC(), dtmDatos, tcmDatos), colNivelCriterio);

        tonalidadEsp = DatosRA2.getTonalidadEspectro(bandaCriticaClasAG, tonoBinDato.getFrecMax(), bandaCriticaRF, nivelCriterio, true, true);
        
        //Lo guardamos para posibles futuros usos
        this.tonoBinDatos[i].setTonalidad(tonalidadEsp);

        tonalidadesEsp.add(tonalidadEsp);
        
        dtmTonAud.addRow(new Object[]{valBin, posClasificacion + TXT_SEP_ESPECTRO + tonoBinDato.getValEsp(), tonalidadEsp});
    }
    
    //Condicion de salida
    if (nDatosTonoBin > 0) {
        if (tonalidadesEsp != null && !tonalidadesEsp.isEmpty()) {
            tonalidad = DatosRA2.getTonalidadPromedioEspectros(tonalidadesEsp);
            audibilidad = DatosRA2.getAudibilidadTonal(tonalidad, frecSum/tonalidadesEsp.size());
            
            dtmTonAud.addRow(new Object[]{valBin, posClasificacion + TXT_SEP_PROMEDIO, tonalidad, audibilidad >= -3.0 ? audibilidad + " *** " : audibilidad});
            rtcr.addColoredRow(dtmTonAud.getRowCount() - 1, Color.LIGHT_GRAY, Color.BLACK);
        }
    }
    
    this.jtTonAud.setDefaultRenderer(Object.class, rtcr);

    //Si todo ha ido bien, activamos el botón de siguiente
    this.jbSig.setEnabled(true);
}//GEN-LAST:event_recalcularTonAud

private void jtTonosEspMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtTonosEspMouseClicked
    if (evt.getClickCount() == 2 && !evt.isConsumed()) {
        evt.consume();
        
        //Limpiamos el valor anterior
        clearValor(null);
        
        //Establecemos el nuevo
        setValor(null);
    }
}//GEN-LAST:event_jtTonosEspMouseClicked

    @SuppressWarnings("empty-statement")
private void autocompletarClasificacion(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_autocompletarClasificacion
    if (JOptionPane.showConfirmDialog(this, "¿Desea autocompletar la clasificación? Se perderá la clasificación actual", "", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
        int nDatos = this.frecMaxDatos.length;
        DatosTonoFFT[] datoMaxBin = null;
        Integer valBin = null, valBinAnt = null, valEsp = null, valEspAnt = null;;
        Double frecMax = null;
        Double anchoBandaCritica = null;
        int nDatosMaxBin = -1, ultPosOcup = -1;
                
        for (int i = 0; i < nDatos; i++) {
            valBin = this.frecMaxDatos[i].getValBin();
            valEsp = this.frecMaxDatos[i].getValEsp();
            frecMax = this.frecMaxDatos[i].getFrecMax();
            anchoBandaCritica = DatosRA2.getAnchoBandaCritica(frecMax);
            
            if (valBinAnt == null || !valBin.equals(valBinAnt)) { //Cambio de bin, actualizamos los datos del máximo
                datoMaxBin = DatosTonoFFT.getDatosMaximoBCBin(this.frecMaxDatos, valBin);
                nDatosMaxBin = datoMaxBin.length;
                
                valBinAnt = valBin;
                valEspAnt = null;
            }
            
            if (valEspAnt == null || !valEsp.equals(valEspAnt)) {
                ultPosOcup = -1;
                
                valEspAnt = valEsp;
            }
            
            //Por defecto quedan todos sin asignar
            this.frecMaxDatos[i].setPosClasificacion(null);
            
            for (int j = 0; j < nDatosMaxBin; j++) {
                if (ultPosOcup != (j + 1) && Math.abs(frecMax - datoMaxBin[j].getFrecMax()) <= anchoBandaCritica * 0.1) {  //Lo consideramos el mismo tono
                    this.frecMaxDatos[i].setPosClasificacion(j + 1);
                    ultPosOcup = j + 1;
                    break;
                }
            }
        }
       
        //Refrescamos la tabla para visualizar los cambios
        muestraClasificacion(null);
    }
}//GEN-LAST:event_autocompletarClasificacion

private void jpRangoFrecAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jpRangoFrecAncestorAdded
    this.jtfFrecDesde.requestFocus();
}//GEN-LAST:event_jpRangoFrecAncestorAdded

private void jpModificarTipoAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jpModificarTipoAncestorAdded
    if (this.jrbNada.isSelected())
        this.jrbNada.requestFocus();
    else
        this.jrbTono.requestFocus();
}//GEN-LAST:event_jpModificarTipoAncestorAdded

private void modificarTipoDato(CategoryItemEntity item) {
    DefaultTableModel dtmDatos = (DefaultTableModel) this.jtDatos.getModel();
    TableColumnModel tcmDatos = this.jtDatos.getColumnModel();
    
    String rowKey = (String) item.getRowKey();
    Double frecMax = (Double) item.getColumnKey();
    Double nivelFrecMax = (Double) item.getDataset().getValue(rowKey, frecMax);
    
    Integer valBin = null, valEsp = null, valBC = null;
    
    String[] rowKeySplit = rowKey.split(Auxiliares.TXT_SERIE_SEP);
    String split;
    int nSplit = rowKeySplit.length;
    int posFrecMax = -1;
    
    for (int i = 0; i < nSplit; i++) {
        split = rowKeySplit[i];
        
        if (split.contains(Auxiliares.TXT_SERIE_BIN))
            valBin = Integer.parseInt(split.replace(Auxiliares.TXT_SERIE_BIN, "").trim());
        else if (split.contains(Auxiliares.TXT_SERIE_ESPECTRO))
            valEsp = Integer.parseInt(split.replace(Auxiliares.TXT_SERIE_ESPECTRO, "").trim());
        else if (split.contains(Auxiliares.TXT_SERIE_BC))
            valBC = Integer.parseInt(split.replace(Auxiliares.TXT_SERIE_BC, "").trim());
    }
    
    int fila = getFilaDatos(valBin, valEsp, valBC, dtmDatos, tcmDatos);
    
    int posDatoBC = -1;
    
    if (fila != -1) {
        posDatoBC = DatosBC.localizaDato(this.datosBC, valBin, valEsp, valBC);
        Double[][] bandaCriticaClasAG = this.datosBC[posDatoBC].getBandaCriticaClasificada();
        
        posFrecMax = DatosRA2.getPosFrecMax(bandaCriticaClasAG, frecMax);
        Integer tipoFrec = bandaCriticaClasAG[posFrecMax][2].intValue();
        String txtSufijo = Auxiliares.TXT_SERIE_SEP;
        Integer tipoFrecNuevo = null;
        String txtSufijoNuevo = Auxiliares.TXT_SERIE_SEP;
        
        if (tipoFrec.equals(DatosRA2.TIPO_TONO))
            txtSufijo += Auxiliares.TXT_SERIE_TONO;
        else if (tipoFrec.equals(DatosRA2.TIPO_NADA))
            txtSufijo += Auxiliares.TXT_SERIE_NADA;
        
        this.jrbTono.setSelected(tipoFrec.equals(DatosRA2.TIPO_TONO));
        this.jrbNada.setSelected(tipoFrec.equals(DatosRA2.TIPO_NADA));
    
        if (JOptionPane.showConfirmDialog(this, this.jpModificarTipo, "Modificación de Banda Clasificada", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
            if (this.jrbTono.isSelected()) {
                tipoFrecNuevo = DatosRA2.TIPO_TONO;
                txtSufijoNuevo += Auxiliares.TXT_SERIE_TONO;
            } else if (this.jrbNada.isSelected()) {
                tipoFrecNuevo = DatosRA2.TIPO_NADA;
                txtSufijoNuevo += Auxiliares.TXT_SERIE_NADA;
            }
            
            if (!tipoFrec.equals(tipoFrecNuevo)) {
                //Actualizamos la banda crítica clasificada
                bandaCriticaClasAG[posFrecMax][2] = 1.0*tipoFrecNuevo;
                this.datosBC[posDatoBC].setBandaCriticaClasificada(bandaCriticaClasAG);

                //Actualizamos las gráficas para visualizar el cambio
                if (this.datasetDatos != null)
                this.datasetDatos.setValue(null, rowKey + txtSufijo, frecMax);
                this.datasetDatos.setValue(nivelFrecMax, rowKey + txtSufijoNuevo, frecMax);
                
                muestraGraDatos(null, null, null);
            }
        }
    }
}

public void chartMouseClicked(ChartMouseEvent evt) {
    ChartEntity ce = evt.getEntity();

    if (ce instanceof CategoryItemEntity) { //Es un punto de la gráfica
        CategoryItemEntity item = (CategoryItemEntity) ce;
        
        modificarTipoDato(item);
    }
}

public void chartMouseMoved(ChartMouseEvent arg0) {
}


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgModificarTipo;
    private javax.swing.JButton jbAnadirTono;
    private javax.swing.JButton jbAnt;
    private javax.swing.JButton jbAutocompletar;
    private javax.swing.JButton jbBinAnt;
    private javax.swing.JButton jbBinSig;
    private javax.swing.JButton jbClearValor;
    private javax.swing.JButton jbEliminarTono;
    private javax.swing.JButton jbGraDatosAnt;
    private javax.swing.JButton jbGraDatosEspAnt;
    private javax.swing.JButton jbGraDatosEspBCAnt;
    private javax.swing.JButton jbGraDatosEspBCSig;
    private javax.swing.JButton jbGraDatosEspSig;
    private javax.swing.JButton jbGraDatosSig;
    private javax.swing.JButton jbRecalcular;
    private javax.swing.JButton jbSetValor;
    private javax.swing.JButton jbSig;
    private javax.swing.JLabel jlAsunto;
    private javax.swing.JLabel jlBinEstudio;
    private javax.swing.JLabel jlClasificacion;
    private javax.swing.JLabel jlPregModificarTipo;
    private javax.swing.JLabel jlRangoFrec;
    private javax.swing.JLabel jlSepFrec;
    private javax.swing.JLabel jlTitAnalisis;
    private javax.swing.JLabel jlTitClasificacion;
    private javax.swing.JLabel jlTitDatos;
    private javax.swing.JLabel jlTitTonAud;
    private javax.swing.JLabel jlTonosEsp;
    private javax.swing.JPanel jpAnalisis;
    private javax.swing.JPanel jpClasificacion;
    private javax.swing.JPanel jpClave;
    private javax.swing.JPanel jpContGrafica;
    private javax.swing.JPanel jpDatos;
    private javax.swing.JPanel jpDatosFFT;
    private javax.swing.JPanel jpGraficaAnalisis;
    private javax.swing.JPanel jpModificarTipo;
    private javax.swing.JPanel jpPrincipal;
    private javax.swing.JPanel jpRangoFrec;
    private javax.swing.JPanel jpTonAud;
    private javax.swing.JRadioButton jrbNada;
    private javax.swing.JRadioButton jrbTono;
    private javax.swing.JScrollPane jspClasificacion;
    private javax.swing.JScrollPane jspDatos;
    private javax.swing.JScrollPane jspTonAud;
    private javax.swing.JScrollPane jspTonosEsp;
    private general.JTableExport jtClasificacion;
    private general.JTableExport jtDatos;
    private general.JTableExport jtTonAud;
    private general.JTableExport jtTonosEsp;
    public javax.swing.JTextField jtfAsunto;
    private javax.swing.JTextField jtfFrecDesde;
    private javax.swing.JTextField jtfFrecHasta;
    private javax.swing.JTabbedPane jtpDatos;
    // End of variables declaration//GEN-END:variables

}