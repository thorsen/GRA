package calculos;

import RA.AsuntoIncert;
import RA.AsuntoRA;
import RA.DatosRA2;
import RA.NormaRA;
import general.AjusteTablasDinamico;
import general.Auxiliares;
import general.DatoVelocidadNivel;
import general.DatoXML;
import general.InteraccionXML;
import general.MensajeApp;
import general.TratFechas;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Shape;
import java.awt.event.WindowEvent;
import java.awt.geom.Rectangle2D;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.entity.XYItemEntity;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.function.Function2D;
import org.jfree.data.function.LineFunction2D;
import org.jfree.data.function.PolynomialFunction2D;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.util.ShapeUtilities;

public class DatosVisualizacionGUI extends JDialog implements ChartMouseListener {
    private String tipoTabla;
    private Integer idAsunto;
    private Integer idSite;
    private Integer valBinMin;
    private Integer valBinMax;
    private Double valorK;
    private double[] regNacelle;
    private ArrayList<Integer[]> modosFunc; //<[BinIniModo, BinFinModo], ..., [BinIniModo, BinFinModo]>
    private ArrayList<Integer> modoSalida;
    
    private HashMap<String, double[]> coeficientesPol;
    private HashMap<String, HashMap<Integer, Double[]>> coeficientesBin;
    private HashMap<String, HashMap<Integer, Double[]>> coeficientesModosFunc;
    private Integer idNorma;
    private ArrayList<AsuntoIncert> incertidumbres;
    
    private ArrayList<Integer[]> idValiDatosModif; //<idDato, ValidoAnt> En válido almacenamos el valor anterior
    //private ArrayList<Integer[]> idDatosXMLInvalidos; //<[idDato, idDatoXML], ..., [, ]>
    
    private int posAnGraOCT, posAnGraFFT;
    private int posAnGraEspOCT, posAnGraEspFFT;
    private LinkedHashMap<Integer, Integer> mapMaxPosAnGraEspOCT, mapMaxPosAnGraEspFFT;
    
    private XYSeriesCollection datasetOCT, datasetFFT;
    
    private static final int SERIE_AG = 0;
    private static final int SERIE_RF = 1;
    
    private static final Integer BIN_ESTUDIO_MODO_FUNC = 8;
    
    private final int NUM_MUESTRAS = 200;
    
    private final Shape SHAPE_DATO_IGNORADO = new Rectangle2D.Double(-4, -4, 4, 4);
    private final Shape SHAPE_DATO_VALIDO = ShapeUtilities.createDiagonalCross(2, (float) 0.5);
    private final Shape SHAPE_DATO_INGNORADO_INSUF = ShapeUtilities.createDiamond((float) 2.5);

	private final String PREF_POLINOMIO = "Polinomio ";
	private final String PREF_LINEAL = "Lineal ";
    
    public DatosVisualizacionGUI(java.awt.Frame parent, String tipoTabla, Integer idAsunto, Integer idSite, Integer valBinMin, Integer valBinMax, Double valorK, double[] regNacelle, ArrayList<Integer[]> modosFunc, ArrayList<Integer> modoSalida, Integer idNorma, ArrayList<AsuntoIncert> incertidumbres) {
        super(parent, true);

        initComponents();

        this.tipoTabla = tipoTabla;
        this.idAsunto = idAsunto;
        this.idSite = idSite;
        this.valBinMin = valBinMin;
        this.valBinMax = valBinMax;
        this.valorK = valorK;
        this.regNacelle = regNacelle;
        this.modosFunc = modosFunc;
        this.modoSalida = modoSalida; //variable para control de llamadas entre diálogos
        
        AsuntoIncert.filtrarTipoAnalisis(incertidumbres, tipoTabla, idNorma);
        this.idNorma = idNorma;
        this.incertidumbres = incertidumbres;
        
        this.idValiDatosModif = new ArrayList<Integer[]>(); //variable para controlar idDatos que han sido invalidados (por si hubiera que deshacer)
//        this.idDatosXMLInvalidos = new ArrayList<Integer[]>(); //variable para controlar idDatos que han sido invalidados (por si hubiera que deshacer)
        
        this.posAnGraOCT = 0;
        this.posAnGraFFT = 0;
        this.posAnGraEspOCT = 0;
        this.posAnGraEspFFT = 0;
        
        if (tipoTabla.contentEquals(Auxiliares.TIPO_SPL)) {
            this.coeficientesPol = new HashMap<String, double[]>();
            this.coeficientesBin = new HashMap<String, HashMap<Integer, Double[]>>();
            
            if (this.idNorma.equals(NormaRA.ID_NORMA_BWEA))
                this.coeficientesModosFunc = new HashMap<String, HashMap<Integer, Double[]>>();
            
        } else if (tipoTabla.contentEquals(Auxiliares.TIPO_OCT)) {
            this.mapMaxPosAnGraEspOCT = new LinkedHashMap<Integer, Integer>();
        } else if (tipoTabla.contentEquals(Auxiliares.TIPO_FFT)) {
            this.mapMaxPosAnGraEspFFT = new LinkedHashMap<Integer, Integer>();
        }
        
        Auxiliares.creaColores(this.valBinMin, this.valBinMax);

        try {
            this.jtfAsunto.setText(AsuntoRA.getAsuntoPorId(idAsunto).getNombreCompleto());
        } catch (SQLException e) {
            MensajeApp.muestraError(this, e, "Fallo al consultar la base de datos");
            Auxiliares.salirDialogo(this, null, this.modoSalida, Auxiliares.MODO_ERROR);
        } catch (NoSuchFieldException e) {
            MensajeApp.muestraError(this, e, "Fallo añadiendo campo");
            Auxiliares.salirDialogo(this, null, this.modoSalida, Auxiliares.MODO_ERROR);
        }
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpObs = new javax.swing.JPanel();
        jlPregunta = new javax.swing.JLabel();
        jlObs = new javax.swing.JLabel();
        jspObs = new javax.swing.JScrollPane();
        jtaObs = new javax.swing.JTextArea();
        bgTipoCalculoAG = new javax.swing.ButtonGroup();
        bgTipoCalculoRF = new javax.swing.ButtonGroup();
        jpTipoCalculoSPL = new javax.swing.JPanel();
        jlCoefCorrAG = new javax.swing.JLabel();
        jlPregTipoCalculoAG = new javax.swing.JLabel();
        jrbRegPolAG = new javax.swing.JRadioButton();
        jrbRegLinAG = new javax.swing.JRadioButton();
        jSeparator = new javax.swing.JSeparator();
        jlCoefCorrRF = new javax.swing.JLabel();
        jlPregTipoCalculoRF = new javax.swing.JLabel();
        jrbRegPolRF = new javax.swing.JRadioButton();
        jrbRegLinRF = new javax.swing.JRadioButton();
        jpTipoCalculoSPLModoFunc = new javax.swing.JPanel();
        jlBinEstudio = new javax.swing.JLabel();
        jtfBinEstudio = new javax.swing.JTextField();
        jpAjustes = new javax.swing.JPanel();
        jlTitAjustes = new javax.swing.JLabel();
        jspAjustes = new javax.swing.JScrollPane();
        jtAjustes = new general.JTableExport();
        jpContGraficaAjPolLin = new javax.swing.JPanel();
        jpGraficaAjPolLin = new javax.swing.JPanel();
        jpPrincipal = new javax.swing.JPanel();
        jpClave = new javax.swing.JPanel();
        jlAsunto = new javax.swing.JLabel();
        jtfAsunto = new javax.swing.JTextField();
        jpDatos = new javax.swing.JPanel();
        jtpDatos = new javax.swing.JTabbedPane();
        jpBaseNeta = new javax.swing.JPanel();
        jlTitBaseNeta = new javax.swing.JLabel();
        jspBaseNeta = new javax.swing.JScrollPane();
        jtBaseNeta = new general.JTableExport();
        jpCompletitud = new javax.swing.JPanel();
        jlTitCompletitud = new javax.swing.JLabel();
        jlCompAG = new javax.swing.JLabel();
        jspCompAG = new javax.swing.JScrollPane();
        jtCompAG = new general.JTableExport();
        jlCompRF = new javax.swing.JLabel();
        jspCompRF = new javax.swing.JScrollPane();
        jtCompRF = new general.JTableExport();
        jlTotalCompAG = new javax.swing.JLabel();
        jtfTotalCompAG = new javax.swing.JTextField();
        jlTotalCompRF = new javax.swing.JLabel();
        jtfTotalCompRF = new javax.swing.JTextField();
        jpContAjustes = new javax.swing.JPanel();
        jpAjustePol = new javax.swing.JPanel();
        jlTitAP = new javax.swing.JLabel();
        jspAjustePol = new javax.swing.JScrollPane();
        jtAjustePol = new general.JTableExport();
        jpContGraficaAjPol = new javax.swing.JPanel();
        jpGraficaAjPol = new javax.swing.JPanel();
        jpAjusteLin = new javax.swing.JPanel();
        jlTitAjLin = new javax.swing.JLabel();
        jlAjLinAG = new javax.swing.JLabel();
        jspAjLinAG = new javax.swing.JScrollPane();
        jtAjLinAG = new general.JTableExport();
        jlAjLinRF = new javax.swing.JLabel();
        jspAjLinRF = new javax.swing.JScrollPane();
        jtAjLinRF = new general.JTableExport();
        jpContGraficaAjLin = new javax.swing.JPanel();
        jpGraficaAjLin = new javax.swing.JPanel();
        jpAnalisisGraOCT = new javax.swing.JPanel();
        jlTitAnalisisGraOT = new javax.swing.JLabel();
        jbAnGraOCTAnt = new javax.swing.JButton();
        jbAnGraEspOCTAnt = new javax.swing.JButton();
        jbAnGraEspOCTSig = new javax.swing.JButton();
        jbAnGraOCTSig = new javax.swing.JButton();
        jpContGraficaOCT = new javax.swing.JPanel();
        jpGraficaAnGraOCT = new javax.swing.JPanel();
        jpAnalisisGraFFT = new javax.swing.JPanel();
        jlTitAnalisisGraFFT = new javax.swing.JLabel();
        jbAnGraFFTAnt = new javax.swing.JButton();
        jbAnGraFFTSig = new javax.swing.JButton();
        jbAnGraEspFFTAnt = new javax.swing.JButton();
        jbAnGraEspFFTSig = new javax.swing.JButton();
        jpContGraficaFFT = new javax.swing.JPanel();
        jpGraficaAnGraFFT = new javax.swing.JPanel();
        jbDeshacer = new javax.swing.JButton();
        jbSig = new javax.swing.JButton();

        jpObs.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jpObsAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        jlPregunta.setText("jLabel1");

        jlObs.setText("Observación:");

        jtaObs.setColumns(20);
        jtaObs.setRows(5);
        jspObs.setViewportView(jtaObs);

        javax.swing.GroupLayout jpObsLayout = new javax.swing.GroupLayout(jpObs);
        jpObs.setLayout(jpObsLayout);
        jpObsLayout.setHorizontalGroup(
            jpObsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpObsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpObsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlPregunta)
                    .addGroup(jpObsLayout.createSequentialGroup()
                        .addComponent(jlObs)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jspObs, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jpObsLayout.setVerticalGroup(
            jpObsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpObsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlPregunta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpObsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jspObs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlObs))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpTipoCalculoSPL.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jpTipoCalculoSPLAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        jlCoefCorrAG.setText("Coef. Corr. AG");

        jlPregTipoCalculoAG.setText("¿Qué tipo de cálculo desea realizar para hallar los niveles de energía?");

        bgTipoCalculoAG.add(jrbRegPolAG);
        jrbRegPolAG.setText("Regresión polinómica");

        bgTipoCalculoAG.add(jrbRegLinAG);
        jrbRegLinAG.setText("Regresiones lineales bin a bin");

        jlCoefCorrRF.setText("Coef. Corr. RF");

        jlPregTipoCalculoRF.setText("¿Qué tipo de cálculo desea realizar para hallar los niveles de energía?");

        bgTipoCalculoRF.add(jrbRegPolRF);
        jrbRegPolRF.setText("Regresión polinómica");

        bgTipoCalculoRF.add(jrbRegLinRF);
        jrbRegLinRF.setText("Regresiones lineales bin a bin");

        javax.swing.GroupLayout jpTipoCalculoSPLLayout = new javax.swing.GroupLayout(jpTipoCalculoSPL);
        jpTipoCalculoSPL.setLayout(jpTipoCalculoSPLLayout);
        jpTipoCalculoSPLLayout.setHorizontalGroup(
            jpTipoCalculoSPLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTipoCalculoSPLLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpTipoCalculoSPLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpTipoCalculoSPLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jrbRegPolRF, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jrbRegLinRF, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jlPregTipoCalculoRF, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jlCoefCorrRF, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jrbRegPolAG, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jrbRegLinAG, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jlPregTipoCalculoAG, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jlCoefCorrAG, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(jSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpTipoCalculoSPLLayout.setVerticalGroup(
            jpTipoCalculoSPLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTipoCalculoSPLLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlCoefCorrAG)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlPregTipoCalculoAG)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jrbRegPolAG)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jrbRegLinAG)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jlCoefCorrRF)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlPregTipoCalculoRF)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jrbRegPolRF)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jrbRegLinRF)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpTipoCalculoSPLModoFunc.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jpTipoCalculoSPLModoFuncAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        jlBinEstudio.setText("Bin a estudiar:");

        jtfBinEstudio.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtfBinEstudio.setName("Desde Velocidad"); // NOI18N

        javax.swing.GroupLayout jpTipoCalculoSPLModoFuncLayout = new javax.swing.GroupLayout(jpTipoCalculoSPLModoFunc);
        jpTipoCalculoSPLModoFunc.setLayout(jpTipoCalculoSPLModoFuncLayout);
        jpTipoCalculoSPLModoFuncLayout.setHorizontalGroup(
            jpTipoCalculoSPLModoFuncLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTipoCalculoSPLModoFuncLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlBinEstudio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtfBinEstudio, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpTipoCalculoSPLModoFuncLayout.setVerticalGroup(
            jpTipoCalculoSPLModoFuncLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTipoCalculoSPLModoFuncLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpTipoCalculoSPLModoFuncLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlBinEstudio)
                    .addComponent(jtfBinEstudio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpAjustes.setBackground(new java.awt.Color(255, 255, 255));

        jlTitAjustes.setBackground(new java.awt.Color(255, 255, 255));
        jlTitAjustes.setFont(new java.awt.Font("Tahoma", 1, 12));
        jlTitAjustes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlTitAjustes.setText("AJUSTES");

        jtAjustes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Ajuste", "Valor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jspAjustes.setViewportView(jtAjustes);
        jtAjustes.getColumnModel().getColumn(0).setMinWidth(120);
        jtAjustes.getColumnModel().getColumn(0).setPreferredWidth(120);
        jtAjustes.getColumnModel().getColumn(0).setMaxWidth(120);

        javax.swing.GroupLayout jpAjustesLayout = new javax.swing.GroupLayout(jpAjustes);
        jpAjustes.setLayout(jpAjustesLayout);
        jpAjustesLayout.setHorizontalGroup(
            jpAjustesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpAjustesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpAjustesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jspAjustes, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlTitAjustes, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpAjustesLayout.setVerticalGroup(
            jpAjustesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpAjustesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlTitAjustes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jspAjustes, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpContGraficaAjPolLin.setBackground(new java.awt.Color(255, 255, 255));

        jpGraficaAjPolLin.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        javax.swing.GroupLayout jpGraficaAjPolLinLayout = new javax.swing.GroupLayout(jpGraficaAjPolLin);
        jpGraficaAjPolLin.setLayout(jpGraficaAjPolLinLayout);
        jpGraficaAjPolLinLayout.setHorizontalGroup(
            jpGraficaAjPolLinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 771, Short.MAX_VALUE)
        );
        jpGraficaAjPolLinLayout.setVerticalGroup(
            jpGraficaAjPolLinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 578, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jpContGraficaAjPolLinLayout = new javax.swing.GroupLayout(jpContGraficaAjPolLin);
        jpContGraficaAjPolLin.setLayout(jpContGraficaAjPolLinLayout);
        jpContGraficaAjPolLinLayout.setHorizontalGroup(
            jpContGraficaAjPolLinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpGraficaAjPolLin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpContGraficaAjPolLinLayout.setVerticalGroup(
            jpContGraficaAjPolLinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpGraficaAjPolLin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setIconImage(null);
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 358, Short.MAX_VALUE)
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

        jpBaseNeta.setBackground(new java.awt.Color(255, 255, 255));
        jpBaseNeta.setName("GEN_BaseNeta"); // NOI18N

        jlTitBaseNeta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlTitBaseNeta.setText("BASE NETA");
        jlTitBaseNeta.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jtBaseNeta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jtBaseNeta.setToolTipText("Base Neta");
        jtBaseNeta.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jtBaseNeta.setRowSelectionAllowed(false);
        jspBaseNeta.setViewportView(jtBaseNeta);

        javax.swing.GroupLayout jpBaseNetaLayout = new javax.swing.GroupLayout(jpBaseNeta);
        jpBaseNeta.setLayout(jpBaseNetaLayout);
        jpBaseNetaLayout.setHorizontalGroup(
            jpBaseNetaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpBaseNetaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpBaseNetaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpBaseNetaLayout.createSequentialGroup()
                        .addComponent(jspBaseNeta, javax.swing.GroupLayout.DEFAULT_SIZE, 773, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jpBaseNetaLayout.createSequentialGroup()
                        .addComponent(jlTitBaseNeta, javax.swing.GroupLayout.DEFAULT_SIZE, 772, Short.MAX_VALUE)
                        .addGap(11, 11, 11))))
        );
        jpBaseNetaLayout.setVerticalGroup(
            jpBaseNetaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpBaseNetaLayout.createSequentialGroup()
                .addComponent(jlTitBaseNeta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jspBaseNeta, javax.swing.GroupLayout.DEFAULT_SIZE, 642, Short.MAX_VALUE)
                .addContainerGap())
        );

        jtpDatos.addTab("", jpBaseNeta);

        jpCompletitud.setBackground(new java.awt.Color(255, 255, 255));
        jpCompletitud.setName("GEN_Completitud"); // NOI18N

        jlTitCompletitud.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlTitCompletitud.setText("RESULTADOS POR BIN");
        jlTitCompletitud.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jlCompAG.setFont(new java.awt.Font("Tahoma", 1, 12));
        jlCompAG.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlCompAG.setText("AEROGENERADOR");

        jtCompAG.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Bin", "N", "Vs", "Pn", "Rpm", "Pitch"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtCompAG.setToolTipText("Resultados Bin Aerogenerador");
        jtCompAG.getTableHeader().setReorderingAllowed(false);
        jspCompAG.setViewportView(jtCompAG);

        jlCompRF.setFont(new java.awt.Font("Tahoma", 1, 12));
        jlCompRF.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlCompRF.setText("RUIDO FONDO");

        jtCompRF.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Bin", "N", "Vs", "Pn", "Rpm", "Pitch"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtCompRF.setToolTipText("Resultados Bin Ruido Fondo");
        jtCompRF.getTableHeader().setReorderingAllowed(false);
        //this.RellenarCompletitud();
        jspCompRF.setViewportView(jtCompRF);

        jlTotalCompAG.setText("TOTAL » ");

        jtfTotalCompAG.setBackground(new java.awt.Color(204, 204, 204));
        jtfTotalCompAG.setEditable(false);
        jtfTotalCompAG.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jlTotalCompRF.setText("TOTAL » ");

        jtfTotalCompRF.setBackground(new java.awt.Color(204, 204, 204));
        jtfTotalCompRF.setEditable(false);
        jtfTotalCompRF.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jpContAjustes.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jpContAjustesLayout = new javax.swing.GroupLayout(jpContAjustes);
        jpContAjustes.setLayout(jpContAjustesLayout);
        jpContAjustesLayout.setHorizontalGroup(
            jpContAjustesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 536, Short.MAX_VALUE)
        );
        jpContAjustesLayout.setVerticalGroup(
            jpContAjustesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jpCompletitudLayout = new javax.swing.GroupLayout(jpCompletitud);
        jpCompletitud.setLayout(jpCompletitudLayout);
        jpCompletitudLayout.setHorizontalGroup(
            jpCompletitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpCompletitudLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpCompletitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jlTitCompletitud, javax.swing.GroupLayout.DEFAULT_SIZE, 773, Short.MAX_VALUE)
                    .addComponent(jpContAjustes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(jpCompletitudLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jpCompletitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jpCompletitudLayout.createSequentialGroup()
                        .addComponent(jlTotalCompAG)
                        .addGap(18, 18, 18)
                        .addComponent(jtfTotalCompAG, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jlCompAG, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jspCompAG, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jpCompletitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpCompletitudLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(jpCompletitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpCompletitudLayout.createSequentialGroup()
                                .addComponent(jlTotalCompRF)
                                .addGap(18, 18, 18)
                                .addComponent(jtfTotalCompRF, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jlCompRF, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)))
                    .addGroup(jpCompletitudLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jspCompRF, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(10, Short.MAX_VALUE))
        );
        jpCompletitudLayout.setVerticalGroup(
            jpCompletitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpCompletitudLayout.createSequentialGroup()
                .addComponent(jlTitCompletitud)
                .addGap(107, 107, 107)
                .addGroup(jpCompletitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlCompRF)
                    .addComponent(jlCompAG))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpCompletitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jspCompRF, 0, 0, Short.MAX_VALUE)
                    .addComponent(jspCompAG, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpCompletitudLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlTotalCompAG)
                    .addComponent(jtfTotalCompAG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlTotalCompRF)
                    .addComponent(jtfTotalCompRF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpContAjustes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jtpDatos.addTab("", jpCompletitud);

        jpAjustePol.setBackground(new java.awt.Color(255, 255, 255));
        jpAjustePol.setName("SPL_AjustePol"); // NOI18N

        jlTitAP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlTitAP.setText("AJUSTE POLINOMIAL");
        jlTitAP.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jtAjustePol.setModel(new javax.swing.table.DefaultTableModel(
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
        jtAjustePol.setToolTipText("Coef. Ajuste Polinomial");
        jtAjustePol.getTableHeader().setReorderingAllowed(false);
        //this.RellenarCoeficientes();
        jspAjustePol.setViewportView(jtAjustePol);
        jtAjustePol.getColumnModel().getColumn(0).setMinWidth(50);
        jtAjustePol.getColumnModel().getColumn(0).setPreferredWidth(50);
        jtAjustePol.getColumnModel().getColumn(0).setMaxWidth(50);

        jpContGraficaAjPol.setBackground(new java.awt.Color(255, 255, 255));

        jpGraficaAjPol.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        javax.swing.GroupLayout jpGraficaAjPolLayout = new javax.swing.GroupLayout(jpGraficaAjPol);
        jpGraficaAjPol.setLayout(jpGraficaAjPolLayout);
        jpGraficaAjPolLayout.setHorizontalGroup(
            jpGraficaAjPolLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 771, Short.MAX_VALUE)
        );
        jpGraficaAjPolLayout.setVerticalGroup(
            jpGraficaAjPolLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 578, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jpContGraficaAjPolLayout = new javax.swing.GroupLayout(jpContGraficaAjPol);
        jpContGraficaAjPol.setLayout(jpContGraficaAjPolLayout);
        jpContGraficaAjPolLayout.setHorizontalGroup(
            jpContGraficaAjPolLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpGraficaAjPol, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpContGraficaAjPolLayout.setVerticalGroup(
            jpContGraficaAjPolLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpGraficaAjPol, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jpAjustePolLayout = new javax.swing.GroupLayout(jpAjustePol);
        jpAjustePol.setLayout(jpAjustePolLayout);
        jpAjustePolLayout.setHorizontalGroup(
            jpAjustePolLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpAjustePolLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpAjustePolLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jpContGraficaAjPol, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jspAjustePol, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlTitAP, javax.swing.GroupLayout.Alignment.CENTER, javax.swing.GroupLayout.DEFAULT_SIZE, 773, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpAjustePolLayout.setVerticalGroup(
            jpAjustePolLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpAjustePolLayout.createSequentialGroup()
                .addComponent(jlTitAP)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpContGraficaAjPol, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jspAjustePol, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jtpDatos.addTab("", jpAjustePol);

        jpAjusteLin.setBackground(new java.awt.Color(255, 255, 255));
        jpAjusteLin.setName("SPL_AjusteLin"); // NOI18N

        jlTitAjLin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlTitAjLin.setText("AJUSTE LINEAL");
        jlTitAjLin.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jlAjLinAG.setText("AG");

        jtAjLinAG.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Bin", "Recta", "R2"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtAjLinAG.setToolTipText("Rectas Ajuste Lineal AG");
        jspAjLinAG.setViewportView(jtAjLinAG);
        jtAjLinAG.getColumnModel().getColumn(0).setMinWidth(60);
        jtAjLinAG.getColumnModel().getColumn(0).setPreferredWidth(60);
        jtAjLinAG.getColumnModel().getColumn(0).setMaxWidth(60);
        jtAjLinAG.getColumnModel().getColumn(2).setMinWidth(60);
        jtAjLinAG.getColumnModel().getColumn(2).setPreferredWidth(60);
        jtAjLinAG.getColumnModel().getColumn(2).setMaxWidth(60);

        jlAjLinRF.setText("RF");

        jtAjLinRF.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Bin", "Recta", "R2"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtAjLinRF.setToolTipText("Rectas Ajuste Lineal RF");
        //this.RellenarCoeficientes2();
        jspAjLinRF.setViewportView(jtAjLinRF);
        jtAjLinRF.getColumnModel().getColumn(0).setMinWidth(60);
        jtAjLinRF.getColumnModel().getColumn(0).setPreferredWidth(60);
        jtAjLinRF.getColumnModel().getColumn(0).setMaxWidth(60);
        jtAjLinRF.getColumnModel().getColumn(2).setMinWidth(60);
        jtAjLinRF.getColumnModel().getColumn(2).setPreferredWidth(60);
        jtAjLinRF.getColumnModel().getColumn(2).setMaxWidth(60);

        jpContGraficaAjLin.setBackground(new java.awt.Color(255, 255, 255));

        jpGraficaAjLin.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        javax.swing.GroupLayout jpGraficaAjLinLayout = new javax.swing.GroupLayout(jpGraficaAjLin);
        jpGraficaAjLin.setLayout(jpGraficaAjLinLayout);
        jpGraficaAjLinLayout.setHorizontalGroup(
            jpGraficaAjLinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 771, Short.MAX_VALUE)
        );
        jpGraficaAjLinLayout.setVerticalGroup(
            jpGraficaAjLinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 509, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jpContGraficaAjLinLayout = new javax.swing.GroupLayout(jpContGraficaAjLin);
        jpContGraficaAjLin.setLayout(jpContGraficaAjLinLayout);
        jpContGraficaAjLinLayout.setHorizontalGroup(
            jpContGraficaAjLinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 773, Short.MAX_VALUE)
            .addGroup(jpContGraficaAjLinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jpGraficaAjLin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpContGraficaAjLinLayout.setVerticalGroup(
            jpContGraficaAjLinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 511, Short.MAX_VALUE)
            .addGroup(jpContGraficaAjLinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jpGraficaAjLin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jpAjusteLinLayout = new javax.swing.GroupLayout(jpAjusteLin);
        jpAjusteLin.setLayout(jpAjusteLinLayout);
        jpAjusteLinLayout.setHorizontalGroup(
            jpAjusteLinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpAjusteLinLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpAjusteLinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpAjusteLinLayout.createSequentialGroup()
                        .addGroup(jpAjusteLinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jlAjLinAG)
                            .addComponent(jspAjLinAG, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(99, 99, 99)
                        .addGroup(jpAjusteLinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jlAjLinRF)
                            .addComponent(jspAjLinRF, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)))
                    .addComponent(jlTitAjLin, javax.swing.GroupLayout.DEFAULT_SIZE, 773, Short.MAX_VALUE)
                    .addComponent(jpContGraficaAjLin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpAjusteLinLayout.setVerticalGroup(
            jpAjusteLinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpAjusteLinLayout.createSequentialGroup()
                .addComponent(jlTitAjLin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpContGraficaAjLin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpAjusteLinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jlAjLinAG)
                    .addComponent(jlAjLinRF))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpAjusteLinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jspAjLinAG, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jspAjLinRF, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jtpDatos.addTab("", jpAjusteLin);

        jpAnalisisGraOCT.setBackground(new java.awt.Color(255, 255, 255));
        jpAnalisisGraOCT.setName("OCT_AnalisisGra"); // NOI18N

        jlTitAnalisisGraOT.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlTitAnalisisGraOT.setText("GRÁFICA ESPECTRO TERCIO DE OCTAVA");
        jlTitAnalisisGraOT.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jbAnGraOCTAnt.setToolTipText("Bin anterior");
        jbAnGraOCTAnt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muestraAnGraOCTAnt(evt);
            }
        });

        jbAnGraEspOCTAnt.setToolTipText("Espectro anterior");
        jbAnGraEspOCTAnt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muestraAnGraEspOCTAnt(evt);
            }
        });

        jbAnGraEspOCTSig.setToolTipText("Espectro siguiente");
        jbAnGraEspOCTSig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muestraAnGraEspOCTSig(evt);
            }
        });

        jbAnGraOCTSig.setToolTipText("Bin siguiente");
        jbAnGraOCTSig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muestraAnGraOCTSig(evt);
            }
        });

        jpContGraficaOCT.setBackground(new java.awt.Color(255, 255, 255));

        jpGraficaAnGraOCT.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        javax.swing.GroupLayout jpGraficaAnGraOCTLayout = new javax.swing.GroupLayout(jpGraficaAnGraOCT);
        jpGraficaAnGraOCT.setLayout(jpGraficaAnGraOCTLayout);
        jpGraficaAnGraOCTLayout.setHorizontalGroup(
            jpGraficaAnGraOCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 771, Short.MAX_VALUE)
        );
        jpGraficaAnGraOCTLayout.setVerticalGroup(
            jpGraficaAnGraOCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 609, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jpContGraficaOCTLayout = new javax.swing.GroupLayout(jpContGraficaOCT);
        jpContGraficaOCT.setLayout(jpContGraficaOCTLayout);
        jpContGraficaOCTLayout.setHorizontalGroup(
            jpContGraficaOCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpGraficaAnGraOCT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpContGraficaOCTLayout.setVerticalGroup(
            jpContGraficaOCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpGraficaAnGraOCT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jpAnalisisGraOCTLayout = new javax.swing.GroupLayout(jpAnalisisGraOCT);
        jpAnalisisGraOCT.setLayout(jpAnalisisGraOCTLayout);
        jpAnalisisGraOCTLayout.setHorizontalGroup(
            jpAnalisisGraOCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpAnalisisGraOCTLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpAnalisisGraOCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jpContGraficaOCT, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlTitAnalisisGraOT, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 773, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpAnalisisGraOCTLayout.createSequentialGroup()
                        .addComponent(jbAnGraOCTAnt, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbAnGraEspOCTAnt, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 657, Short.MAX_VALUE)
                        .addComponent(jbAnGraEspOCTSig, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbAnGraOCTSig, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jpAnalisisGraOCTLayout.setVerticalGroup(
            jpAnalisisGraOCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpAnalisisGraOCTLayout.createSequentialGroup()
                .addComponent(jlTitAnalisisGraOT)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpContGraficaOCT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpAnalisisGraOCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jbAnGraOCTAnt, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbAnGraOCTSig, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbAnGraEspOCTAnt, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbAnGraEspOCTSig, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jtpDatos.addTab("", jpAnalisisGraOCT);

        jpAnalisisGraFFT.setBackground(new java.awt.Color(255, 255, 255));
        jpAnalisisGraFFT.setName("FFT_AnalisisGra"); // NOI18N

        jlTitAnalisisGraFFT.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlTitAnalisisGraFFT.setText("GRÁFICAS ESPECTROS FFT");
        jlTitAnalisisGraFFT.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jbAnGraFFTAnt.setToolTipText("Bin anterior");
        jbAnGraFFTAnt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muestraAnGraFFTAnt(evt);
            }
        });

        jbAnGraFFTSig.setToolTipText("Bin siguiente");
        jbAnGraFFTSig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muestraAnGraFFTSig(evt);
            }
        });

        jbAnGraEspFFTAnt.setToolTipText("Espectro anterior");
        jbAnGraEspFFTAnt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muestraAnGraEspFFTAnt(evt);
            }
        });

        jbAnGraEspFFTSig.setToolTipText("Espectro siguiente");
        jbAnGraEspFFTSig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muestraAnGraEspFFTSig(evt);
            }
        });

        jpContGraficaFFT.setBackground(new java.awt.Color(255, 255, 255));

        jpGraficaAnGraFFT.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        javax.swing.GroupLayout jpGraficaAnGraFFTLayout = new javax.swing.GroupLayout(jpGraficaAnGraFFT);
        jpGraficaAnGraFFT.setLayout(jpGraficaAnGraFFTLayout);
        jpGraficaAnGraFFTLayout.setHorizontalGroup(
            jpGraficaAnGraFFTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 771, Short.MAX_VALUE)
        );
        jpGraficaAnGraFFTLayout.setVerticalGroup(
            jpGraficaAnGraFFTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 598, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jpContGraficaFFTLayout = new javax.swing.GroupLayout(jpContGraficaFFT);
        jpContGraficaFFT.setLayout(jpContGraficaFFTLayout);
        jpContGraficaFFTLayout.setHorizontalGroup(
            jpContGraficaFFTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpGraficaAnGraFFT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpContGraficaFFTLayout.setVerticalGroup(
            jpContGraficaFFTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpGraficaAnGraFFT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jpAnalisisGraFFTLayout = new javax.swing.GroupLayout(jpAnalisisGraFFT);
        jpAnalisisGraFFT.setLayout(jpAnalisisGraFFTLayout);
        jpAnalisisGraFFTLayout.setHorizontalGroup(
            jpAnalisisGraFFTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpAnalisisGraFFTLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpAnalisisGraFFTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jpContGraficaFFT, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlTitAnalisisGraFFT, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 773, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpAnalisisGraFFTLayout.createSequentialGroup()
                        .addComponent(jbAnGraFFTAnt, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbAnGraEspFFTAnt, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 657, Short.MAX_VALUE)
                        .addComponent(jbAnGraEspFFTSig, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbAnGraFFTSig, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jpAnalisisGraFFTLayout.setVerticalGroup(
            jpAnalisisGraFFTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpAnalisisGraFFTLayout.createSequentialGroup()
                .addComponent(jlTitAnalisisGraFFT)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpContGraficaFFT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpAnalisisGraFFTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jbAnGraFFTAnt, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbAnGraFFTSig, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbAnGraEspFFTAnt, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbAnGraEspFFTSig, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jtpDatos.addTab("", jpAnalisisGraFFT);

        javax.swing.GroupLayout jpDatosLayout = new javax.swing.GroupLayout(jpDatos);
        jpDatos.setLayout(jpDatosLayout);
        jpDatosLayout.setHorizontalGroup(
            jpDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jtpDatos, javax.swing.GroupLayout.DEFAULT_SIZE, 798, Short.MAX_VALUE)
        );
        jpDatosLayout.setVerticalGroup(
            jpDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDatosLayout.createSequentialGroup()
                .addComponent(jtpDatos, javax.swing.GroupLayout.DEFAULT_SIZE, 705, Short.MAX_VALUE)
                .addContainerGap())
        );

        jbDeshacer.setBackground(new java.awt.Color(255, 255, 255));
        jbDeshacer.setText("Deshacer");
        jbDeshacer.setEnabled(false);
        jbDeshacer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deshacerModificacion(evt);
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
                    .addGroup(jpPrincipalLayout.createSequentialGroup()
                        .addComponent(jbDeshacer, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 602, Short.MAX_VALUE)
                        .addComponent(jbSig, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jpClave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpPrincipalLayout.setVerticalGroup(
            jpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpClave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpDatos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbSig)
                    .addComponent(jbDeshacer))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-830)/2, (screenSize.height-857)/2, 830, 857);
    }// </editor-fold>//GEN-END:initComponents

    private JFreeChart crearGraficaAjPolLin(XYDataset dataset) {
        JFreeChart chart = ChartFactory.createScatterPlot(
                "",
                "Velocidad de referencia Vs (m/s)",
                "Presión sonora equivalente (dBA)",
                null,
                PlotOrientation.VERTICAL,
                true,
                true,
                false);

        TextTitle texto = chart.getTitle();
        texto.setFont(new Font("Arial", Font.BOLD, 13));
        chart.setBackgroundPaint(Color.WHITE);

		XYPlot plot = chart.getXYPlot();

		int nSeries = dataset.getSeriesCount();
		String serieKey, numeroEnSerieKey;
		Integer valModoIni;
		XYLineAndShapeRenderer rend;
		Color color, colorOpuesto;
		Shape shape;
		XYSeriesCollection dsAux;
		XYSeries serie; 

        for (int i = 0; i < nSeries; i++) {
			serie = ((XYSeriesCollection) dataset).getSeries(i);
            serieKey = (String) serie.getKey();

		    rend = new XYLineAndShapeRenderer();

			numeroEnSerieKey = serieKey.replaceAll("[^0-9]+", "").trim();
			valModoIni = numeroEnSerieKey.isEmpty() ? this.valBinMin : Integer.parseInt(numeroEnSerieKey);

			if (serieKey.contains(Auxiliares.PREF_DATOS_AG)) {
			    color = Auxiliares.COLORES_SERIE0.get(valModoIni - this.valBinMin);
			    colorOpuesto = Auxiliares.colorOpuesto(Auxiliares.COLORES_SERIE0.get(valModoIni - this.valBinMin));
			} else {
			    color = Auxiliares.COLORES_SERIE1.get(valModoIni - this.valBinMin);
			    colorOpuesto = Auxiliares.colorOpuesto(Auxiliares.COLORES_SERIE1.get(valModoIni - this.valBinMin));
			}

			if (serieKey.contains(PREF_POLINOMIO) || serieKey.contains(PREF_LINEAL)) {
				rend.setSeriesLinesVisible(0, true);
				rend.setSeriesShapesVisible(0, false);
                rend.setSeriesStroke(0, new BasicStroke(2.0f));
                rend.setSeriesPaint(0, colorOpuesto);
			} else {
			    if (serieKey.contains(Auxiliares.PREF_DATOS_IGNORADOS))
					shape = SHAPE_DATO_IGNORADO;
			    else if (serie.getItemCount() > 1)
					shape = SHAPE_DATO_VALIDO;
			    else
					shape = SHAPE_DATO_INGNORADO_INSUF;

				rend.setSeriesLinesVisible(0, false);
				rend.setSeriesShapesVisible(0, true);
			    rend.setSeriesPaint(0, color);
			    rend.setSeriesShape(0, shape);
			}

			dsAux = new XYSeriesCollection();
			dsAux.addSeries(serie);

			plot.setDataset(i, dsAux);
			plot.setRenderer(i, rend);
        }

        return chart;
    }
    
    private JFreeChart crearGraficaAjPol(XYDataset dataset) {
        XYSeriesCollection datasetDatos;
        XYItemRenderer rendDatos = new XYLineAndShapeRenderer(false, true);

        JFreeChart chart = ChartFactory.createScatterPlot(
                "",
                "Velocidad de referencia Vs (m/s)",
                "Presión sonora equivalente (dBA)",
                null, //dataset
                PlotOrientation.VERTICAL,
                true,
                true,
                false);

        TextTitle texto = chart.getTitle();
        texto.setFont(new Font("Arial", Font.BOLD, 13));
        chart.setBackgroundPaint(Color.WHITE);
        XYPlot plot = (XYPlot) chart.getPlot();
        
        //DATOS AERO
        XYLineAndShapeRenderer rend;
        PolynomialFunction2D funPol;
        XYDataset dsGrafica;
        Color color;
        Shape shape;
        
        int nSeries = dataset.getSeriesCount();
        String serieKey;
        double[] coef, coefAux;
        int nCoefAux;
        int nSeriesPlot = 0;

        //Ajuste polinomial
        for (int i = 0; i < nSeries; i++) {
            serieKey = (String) dataset.getSeriesKey(i);
            
            if (!serieKey.contains(Auxiliares.PREF_DATOS_IGNORADOS) && dataset.getItemCount(i) >= DatosRA2.ORDEN_REG_POL) {
                if (serieKey.contains(Auxiliares.PREF_DATOS_AG)) {
                    coef = this.coeficientesPol.get(Auxiliares.PREF_DATOS_AG);

                    color = Auxiliares.colorOpuesto(Auxiliares.COLORES_SERIE0.get(0));
                } else {
                    coef = this.coeficientesPol.get(Auxiliares.PREF_DATOS_RF);

                    color = Auxiliares.colorOpuesto(Auxiliares.COLORES_SERIE1.get(0));
                }
                
                //No pasamos la correlación
                nCoefAux = coef.length - 1;
                coefAux = new double[nCoefAux];
                
                for (int j = 0; j < nCoefAux; j++) {
                    coefAux[j] = coef[j];
                }
                
                funPol = new PolynomialFunction2D(coefAux);
                dsGrafica = DatasetUtilities.sampleFunction2D(funPol, this.valBinMin - 0.5, this.valBinMax + 0.5, NUM_MUESTRAS, PREF_POLINOMIO + serieKey);
                plot.setDataset(nSeriesPlot, dsGrafica);
                
                rend = new XYLineAndShapeRenderer(true, false);
                rend.setSeriesStroke(0, new BasicStroke(2.0f));
                rend.setSeriesPaint(0, color);
                plot.setRenderer(nSeriesPlot, rend);
                nSeriesPlot++;
            }
        }

        //Datos brutos
        for (int i = 0; i < nSeries; i++) {
            serieKey = (String) dataset.getSeriesKey(i);
            
            if (serieKey.contains(Auxiliares.PREF_DATOS_AG)) {
                color = Auxiliares.COLORES_SERIE0.get(0);
            } else {
                color = Auxiliares.COLORES_SERIE1.get(0);
            }
            
            if (serieKey.contains(Auxiliares.PREF_DATOS_IGNORADOS))
                shape = SHAPE_DATO_IGNORADO;
            else if (dataset.getItemCount(i) >= DatosRA2.ORDEN_REG_POL)
                shape = SHAPE_DATO_VALIDO;
            else
                shape = SHAPE_DATO_INGNORADO_INSUF;

            datasetDatos = new XYSeriesCollection();
            datasetDatos.addSeries(((XYSeriesCollection)dataset).getSeries(i));
            plot.setDataset(nSeriesPlot, datasetDatos);
            
            rendDatos = new XYLineAndShapeRenderer(false, true);
            rendDatos.setSeriesPaint(0, color);
            rendDatos.setSeriesShape(0, shape);
            plot.setRenderer(nSeriesPlot, rendDatos);
            nSeriesPlot++;
        }

        return chart;
    }
    
    private JFreeChart crearGraficaAjLin(XYDataset dataset) {
        XYSeriesCollection datasetDatos;
        XYItemRenderer rendDatos;

        JFreeChart chart = ChartFactory.createScatterPlot(
                "",
                "Velocidad de referencia Vs (m/s)",
                "Presión sonora equivalente (dBA)",
                null, //dataset
                PlotOrientation.VERTICAL,
                true,
                true,
                false);

        TextTitle texto = chart.getTitle();
        texto.setFont(new Font("Arial", Font.BOLD, 13));
        chart.setBackgroundPaint(Color.WHITE);
        XYPlot plot = (XYPlot) chart.getPlot();
        
        //DATOS AERO
        XYLineAndShapeRenderer rend;
        Function2D funLin;
        XYDataset dsGrafica;
        Color color;
        Shape shape;
        
        int nSeries = dataset.getSeriesCount();
        String serieKey;
        Integer valModoIni, valModoFin;
        String modoString;

        Double[] pendOffset;
        int nSeriesPlot = 0;
        
        HashMap<String, HashMap<Integer, Double[]>> coeficientes;
        
        if (!this.idNorma.equals(NormaRA.ID_NORMA_BWEA)) { //Ajuste en modos de funcionamiento
            coeficientes = this.coeficientesBin;
        } else {
            coeficientes = this.coeficientesModosFunc;
        }
        
       
        //Ajuste lineal
        for (int i = 0; i < nSeries; i++) {
            serieKey = (String) dataset.getSeriesKey(i);

            if (!serieKey.contains(Auxiliares.PREF_DATOS_IGNORADOS) && dataset.getItemCount(i) > 1) {
                if (!this.idNorma.equals(NormaRA.ID_NORMA_BWEA)) { //Ajuste bin a bin
                    valModoIni = Integer.parseInt(serieKey.replaceAll("[^0-9]+", "").trim());
                    valModoFin = valModoIni;
                } else {
                    modoString = serieKey.replace(Auxiliares.PREF_DATOS_IGNORADOS, "").replace(Auxiliares.PREF_DATOS_AG, "").replace(Auxiliares.PREF_DATOS_RF, "").replace(Auxiliares.TXT_SERIE_BIN, "");
                    valModoIni = Integer.parseInt(modoString.substring(0, modoString.indexOf("-")).replaceAll("[^0-9]+", "").trim());
                    valModoFin = Integer.parseInt(modoString.substring(modoString.indexOf("-") + 1).replaceAll("[^0-9]+", "").trim());
                }

                if (serieKey.contains(Auxiliares.PREF_DATOS_AG)) {
                    pendOffset = coeficientes.get(Auxiliares.PREF_DATOS_AG).get(valModoIni);

                    funLin = new LineFunction2D(pendOffset[0], pendOffset[1]);

                    color = Auxiliares.colorOpuesto(Auxiliares.COLORES_SERIE0.get(valModoIni - this.valBinMin));
                } else {
                    pendOffset = coeficientes.get(Auxiliares.PREF_DATOS_RF).get(valModoIni);

                    funLin = new LineFunction2D(pendOffset[0], pendOffset[1]);

                    color = Auxiliares.colorOpuesto(Auxiliares.COLORES_SERIE1.get(valModoIni - this.valBinMin));
                }

                dsGrafica = DatasetUtilities.sampleFunction2D(funLin, valModoIni - 0.5, valModoFin + 0.5, NUM_MUESTRAS, PREF_LINEAL + serieKey);
                plot.setDataset(nSeriesPlot, dsGrafica);

                rend = new XYLineAndShapeRenderer(true, false);
                rend.setSeriesStroke(0, new BasicStroke(2.0f));
                rend.setSeriesPaint(0, color);
                plot.setRenderer(nSeriesPlot, rend);
                nSeriesPlot++;
            }
        }

        //Datos brutos
        for (int i = 0; i < nSeries; i++) {
            serieKey = (String) dataset.getSeriesKey(i);

            if (!this.idNorma.equals(NormaRA.ID_NORMA_BWEA)) { //Ajuste bin a bin
                valModoIni = Integer.parseInt(serieKey.replaceAll("[^0-9]+", "").trim());
            } else {
                modoString = serieKey.replace(Auxiliares.PREF_DATOS_IGNORADOS, "").replace(Auxiliares.PREF_DATOS_AG, "").replace(Auxiliares.PREF_DATOS_RF, "").replace(Auxiliares.TXT_SERIE_BIN, "");
                valModoIni = Integer.parseInt(modoString.substring(0, modoString.indexOf("-")).replaceAll("[^0-9]+", "").trim());
                valModoFin = Integer.parseInt(modoString.substring(modoString.indexOf("-") + 1).replaceAll("[^0-9]+", "").trim());
            }

            if (serieKey.contains(Auxiliares.PREF_DATOS_AG)) {
                color = Auxiliares.COLORES_SERIE0.get(valModoIni - this.valBinMin);
            } else {
                color = Auxiliares.COLORES_SERIE1.get(valModoIni - this.valBinMin);
            }

            if (!serieKey.contains(Auxiliares.PREF_DATOS_IGNORADOS))
                shape = SHAPE_DATO_IGNORADO;
            else if (dataset.getItemCount(i) > 1)
                shape = SHAPE_DATO_VALIDO;
            else
                shape = SHAPE_DATO_INGNORADO_INSUF;

            datasetDatos = new XYSeriesCollection();
            datasetDatos.addSeries(((XYSeriesCollection)dataset).getSeries(i));
            plot.setDataset(nSeriesPlot, datasetDatos);

            rendDatos = new XYLineAndShapeRenderer(false, true);
            rendDatos.setSeriesPaint(0, color);
            rendDatos.setSeriesShape(0, shape);
            plot.setRenderer(nSeriesPlot, rendDatos);
            nSeriesPlot++;
        }

        return chart;
    }
    
    private JFreeChart crearGraficaEspectros(XYDataset dataset) {
        XYItemRenderer rendDatos = new XYLineAndShapeRenderer(true, false);
        
        JFreeChart chart = ChartFactory.createScatterPlot(
                "",
                "Frecuencia (Hz)",
                "Presión sonora equivalente (dBA)",
                dataset,
                PlotOrientation.VERTICAL,
                false,
                true,
                false);

        TextTitle texto = chart.getTitle();
        texto.setFont(new Font("Arial", Font.BOLD, 13));
        chart.setBackgroundPaint(Color.WHITE);
        XYPlot plot = (XYPlot) chart.getPlot();
        Rectangle2D.Double r = new Rectangle2D.Double(-1, -1, 2, 2);

        int nSeries = plot.getSeriesCount();
        
        int contAG = 0, contRF = 0;
        
        for (int i = 0; i < nSeries; i++) {
            rendDatos.setSeriesShape(i, r);
            
            if (dataset.getSeriesKey(i).toString().contains(Auxiliares.PREF_DATOS_IGNORADOS))
                rendDatos.setSeriesStroke(i, new BasicStroke(1.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1.0f, new float[] {1.0f, 5.0f}, 0.0f));
            
            if (dataset.getSeriesKey(i).toString().contains(Auxiliares.PREF_DATOS_AG)) {
//                rendDatos.setSeriesPaint(i, Auxiliares.COLORES_SERIE0.get(contAG));
                contAG++;
            } else {
  //              rendDatos.setSeriesPaint(i, Auxiliares.COLORES_SERIE1.get(contRF));
                contRF++;
            }

            plot.setRenderer(i, rendDatos);
        }

        return chart;
    }
    
    private JFreeChart crearGraficaAnGraOCT(XYDataset dataset) {
        return crearGraficaEspectros(dataset);
    }
    
    private JFreeChart crearGraficaAnGraFFT(XYDataset dataset) {
        return crearGraficaEspectros(dataset);
    }
    
    private Object[] campoVistaNetaExceptoXML() throws SQLException {
        Object[] camposVistaNeta = DatosRA2.getCamposVistaNeta();
        
        int nCamposVistaNeta = camposVistaNeta.length;
        
        Object[] res;
        
        if (this.tipoTabla.compareTo(Auxiliares.TIPO_FFT) == 0) {
            res = new Object[nCamposVistaNeta - 1];
            int posSinXML = 0;

            for (int i = 0; i < nCamposVistaNeta; i++) {
                if (!((String) camposVistaNeta[i]).equals(DatosRA2.CAMPO_XML))
                    res[posSinXML++] = camposVistaNeta[i];
            }
        } else
            res = camposVistaNeta;
        
        return res;
    }

    private void crearColsBaseNeta() throws SQLException {
        //Traemos las cabeceras de la tabla
        this.jtBaseNeta.setModel(new javax.swing.table.DefaultTableModel(new Object[][]{}, campoVistaNetaExceptoXML()) {

            //boolean[] canEdit = new boolean[]{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, true, true, true};
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                //return canEdit[columnIndex];
                return false;
            }
        });
/*
        new AjusteTablasDinamico(this.jtBaseNeta, AjusteTablasDinamico.ESPACIADO_COL_MED);
        this.jtBaseNeta.getTableHeader().setReorderingAllowed(false);
 */
    }

    private void rellenarBaseNeta(JProgressBar jpb, boolean esFFT) throws SQLException {
        crearColsBaseNeta();

        DefaultTableModel dtmBaseNeta = (DefaultTableModel) this.jtBaseNeta.getModel();
        dtmBaseNeta.setRowCount(0);

        int colFecha = dtmBaseNeta.findColumn(DatosRA2.CAMPO_FECHA_HORA);
        
        ArrayList<String> campos = null;
        
        if (esFFT) {
            campos = new ArrayList<String>();
            Object[] camposVistaNeta = campoVistaNetaExceptoXML();
            int nCamposVistaNeta = camposVistaNeta.length;
            
            for (int i = 0; i < nCamposVistaNeta; i++) {
                campos.add((String) camposVistaNeta[i]);
            }
        }
            
        ArrayList<Object[]> datosBaseNeta = DatosRA2.getDatosVistaNeta(this.tipoTabla, this.idAsunto, this.idSite, this.valBinMin - 0.5, this.valBinMax + 0.5, campos, null, null);
        Auxiliares.incPorcentajeProgress(jpb, 0.1);

        // Relleno la tabla
        if (datosBaseNeta != null) {
            Object[] fila;
            int nDatosIncidencias = datosBaseNeta.size();

            for (int i = 0; i < nDatosIncidencias; i++) {
                fila = datosBaseNeta.get(i);
                //Ajustamos los datos para que se muestren correctamente
                fila[colFecha] = TratFechas.toStringFecha((Long) fila[colFecha]);
                        
                dtmBaseNeta.addRow(fila);

                Auxiliares.incPorcentajeProgress(jpb, 0.15 / nDatosIncidencias);
            }
        }

        Auxiliares.centrarTabla(this.jtBaseNeta);
    }

    private void rellenarCompletitud(JProgressBar jpb) throws SQLException {
        int nDatos;

        //DATOS AERO
        DefaultTableModel dtmComp = (DefaultTableModel) this.jtCompAG.getModel();
        dtmComp.setRowCount(0);

        ArrayList<Object[]> comp = DatosRA2.getResultadosBin(this.tipoTabla, this.idAsunto, this.idSite, 0, DatosRA2.CAMPO_V_S, this.valBinMin, this.valBinMax);
        Auxiliares.incPorcentajeProgress(jpb, 0.05);

        // Relleno la tabla
        AjusteTablasDinamico atdCompAG = new AjusteTablasDinamico(this.jtCompAG, AjusteTablasDinamico.ESPACIADO_COL_MED);
        if (comp != null) {
            int nDatosComp = comp.size();
            nDatos = 0;

            for (int i = 0; i < nDatosComp; i++) {
                if ((Integer) comp.get(i)[1] > 0) { // Si hay datos en el bin
                    dtmComp.addRow(comp.get(i));
                    nDatos += (Integer) comp.get(i)[1];
                }
                Auxiliares.incPorcentajeProgress(jpb, 0.075 / nDatosComp);
            }

            this.jtfTotalCompAG.setText("" + nDatos);
        }
        atdCompAG.maximizaSiEsPosible();
        Auxiliares.centrarTabla(this.jtCompAG);

        //DATOS RF
        dtmComp = (DefaultTableModel) this.jtCompRF.getModel();
        dtmComp.setRowCount(0);

        comp = DatosRA2.getResultadosBin(this.tipoTabla, this.idAsunto, this.idSite, 1, DatosRA2.CAMPO_V_S, this.valBinMin, this.valBinMax);
        Auxiliares.incPorcentajeProgress(jpb, 0.05);

        AjusteTablasDinamico atdCompRF = new AjusteTablasDinamico(this.jtCompRF, AjusteTablasDinamico.ESPACIADO_COL_MED);
        if (comp != null) {
            int nDatosComp = comp.size();
            nDatos = 0;

            for (int i = 0; i < nDatosComp; i++) {
                if ((Integer) comp.get(i)[1] > 0) { // Si hay datos en el bin
                    dtmComp.addRow(comp.get(i));
                    nDatos += (Integer) comp.get(i)[1];
                }
                Auxiliares.incPorcentajeProgress(jpb, 0.075 / nDatosComp);
            }

            this.jtfTotalCompRF.setText("" + nDatos);
        }
        atdCompRF.maximizaSiEsPosible();
        Auxiliares.centrarTabla(this.jtCompRF);
        
        
        //Ajustes
        if (this.valorK != null || this.regNacelle != null) {
            DefaultTableModel dtmAjustes = (DefaultTableModel) this.jtAjustes.getModel();
            dtmAjustes.setRowCount(0);

            if (this.valorK != null)
                dtmAjustes.addRow(new Object[]{"K Torre", this.valorK});
            if (this.regNacelle != null)
                dtmAjustes.addRow(new Object[]{"Reg. Nacelle", DatosRA2.getRectaCoef(this.regNacelle)});

            Auxiliares.centrarTabla(this.jtAjustes);
            
            this.jpContAjustes.setLayout(new BorderLayout());
            this.jpContAjustes.add(this.jpAjustes, BorderLayout.CENTER);
        } else
            this.jpContAjustes.removeAll();
    }

    private void rellenarCoefAjPol(JProgressBar jpb) {
        XYDataset dataset = crearDatasetAjPol();
        
        int nSeries = dataset.getSeriesCount();
        String serieKey;
        double[] coef;
        int nCoef;
        int fila;

        for (int i = 0; i < nSeries; i++) {
            serieKey = (String) dataset.getSeriesKey(i);
            
            if (serieKey.contains(Auxiliares.PREF_DATOS_IGNORADOS))
                continue;
            
            coef = DatosRA2.getCoefPol(dataset, i);
            
            Auxiliares.incPorcentajeProgress(jpb, 0.10 / nSeries);
            
            if (coef != null) {
                if (serieKey.contains(Auxiliares.PREF_DATOS_AG)) {
                    this.coeficientesPol.put(Auxiliares.PREF_DATOS_AG, coef);
                    
                    fila = SERIE_AG;
                } else {
                    this.coeficientesPol.put(Auxiliares.PREF_DATOS_RF, coef);
                    
                    fila = SERIE_RF;
                }
                
                nCoef = coef.length;

                for (int j = 0; j < nCoef; j++) {
                    this.jtAjustePol.setValueAt(coef[j], fila, j + 1);
                }
            }
            Auxiliares.incPorcentajeProgress(jpb, 0.15 / nSeries);
        }

        Auxiliares.centrarTabla(this.jtAjustePol);

        //Creamos la gráfica
        Auxiliares.asignaPanelGrafica(this, this.jpGraficaAjPol, crearGraficaAjPol(dataset), true, this);
    }

    private void rellenarCoefAjLin(JProgressBar jpb) throws SQLException {
        XYSeriesCollection datasetModosFunc = new XYSeriesCollection();
        XYDataset dataset = crearDatasetAjLin(datasetModosFunc);

        double[] coef;
        DefaultTableModel dtmAjLinAG = (DefaultTableModel) this.jtAjLinAG.getModel();
        DefaultTableModel dtmAjLinRF = (DefaultTableModel) this.jtAjLinRF.getModel();
        dtmAjLinAG.setRowCount(0);
        dtmAjLinRF.setRowCount(0);
        
        String serieKey, modoString;
        int nSeries = dataset.getSeriesCount();
        Integer valBin;
        HashMap<Integer, Double[]> coefBin;

        for (int i = 0; i < nSeries; i++) {
            serieKey = (String) dataset.getSeriesKey(i);
            
            if (serieKey.contains(Auxiliares.PREF_DATOS_IGNORADOS))
                continue;
            
            coef = DatosRA2.getCoefBin(dataset, i);
            Auxiliares.incPorcentajeProgress(jpb, 0.10 / nSeries);
            
            valBin = Integer.parseInt(serieKey.replaceAll("[^0-9]+", "").trim());
            
            if (coef != null) {
                if (serieKey.contains(Auxiliares.PREF_DATOS_AG)) {
                    dtmAjLinAG.addRow(new Object[]{serieKey, DatosRA2.getRectaCoef(coef), coef[2]});
                    
                    coefBin = this.coeficientesBin.get(Auxiliares.PREF_DATOS_AG);
                    if (coefBin == null)
                        coefBin = new HashMap<Integer, Double[]>();
                 
                    coefBin.put(valBin, new Double[]{coef[0], coef[1]});
                    
                    this.coeficientesBin.put(Auxiliares.PREF_DATOS_AG, coefBin);
                } else {
                    dtmAjLinRF.addRow(new Object[]{serieKey, DatosRA2.getRectaCoef(coef), coef[2]});
                    
                    coefBin = this.coeficientesBin.get(Auxiliares.PREF_DATOS_RF);
                    if (coefBin == null)
                        coefBin = new HashMap<Integer, Double[]>();
                 
                    coefBin.put(valBin, new Double[]{coef[0], coef[1]});
                    
                    this.coeficientesBin.put(Auxiliares.PREF_DATOS_RF, coefBin);
                }
            }
            Auxiliares.incPorcentajeProgress(jpb, 0.15 / nSeries);
        }
        
        
        if (datasetModosFunc != null) {
            nSeries = datasetModosFunc.getSeriesCount();
            
            if (nSeries > 0) {
                dtmAjLinAG.setRowCount(0);
                dtmAjLinRF.setRowCount(0);
            }
        } else
            nSeries = 0;
        
        for (int i = 0; i < nSeries; i++) {
            serieKey = (String) datasetModosFunc.getSeriesKey(i);
            
            if (serieKey.contains(Auxiliares.PREF_DATOS_IGNORADOS))
                continue;
            
            coef = DatosRA2.getCoefBin(datasetModosFunc, i);
            Auxiliares.incPorcentajeProgress(jpb, 0.10 / nSeries);
            
            modoString = serieKey.replace(Auxiliares.PREF_DATOS_AG, "").replace(Auxiliares.PREF_DATOS_RF, "").replace(Auxiliares.TXT_SERIE_BIN, "");
            valBin = Integer.parseInt(modoString.substring(0, modoString.indexOf("-")).replaceAll("[^0-9]+", "").trim());
            
            if (coef != null) {
                if (serieKey.contains(Auxiliares.PREF_DATOS_AG)) {
                    dtmAjLinAG.addRow(new Object[]{serieKey, DatosRA2.getRectaCoef(coef), coef[2]});
                    
                    coefBin = this.coeficientesModosFunc.get(Auxiliares.PREF_DATOS_AG);
                    if (coefBin == null)
                        coefBin = new HashMap<Integer, Double[]>();
                 
                    coefBin.put(valBin, new Double[]{coef[0], coef[1]});
                    
                    this.coeficientesModosFunc.put(Auxiliares.PREF_DATOS_AG, coefBin);
                } else {
                    dtmAjLinRF.addRow(new Object[]{serieKey, DatosRA2.getRectaCoef(coef), coef[2]});
                    
                    coefBin = this.coeficientesModosFunc.get(Auxiliares.PREF_DATOS_RF);
                    if (coefBin == null)
                        coefBin = new HashMap<Integer, Double[]>();
                 
                    coefBin.put(valBin, new Double[]{coef[0], coef[1]});
                    
                    this.coeficientesModosFunc.put(Auxiliares.PREF_DATOS_RF, coefBin);
                }
            }
            Auxiliares.incPorcentajeProgress(jpb, 0.15 / nSeries);
        }

        Auxiliares.centrarTabla(this.jtAjLinAG);
        Auxiliares.centrarTabla(this.jtAjLinRF);

        //Creamos la gráfica
        if (this.idNorma.equals(NormaRA.ID_NORMA_BWEA))
            dataset = datasetModosFunc;
        
        Auxiliares.asignaPanelGrafica(this, this.jpGraficaAjLin, crearGraficaAjLin(dataset), true, this);
    }

    private XYDataset crearDatasetAjPol() {
        XYSeries serieAG = new XYSeries(Auxiliares.PREF_DATOS_AG, false);
        XYSeries serieRF = new XYSeries(Auxiliares.PREF_DATOS_RF, false);
        XYSeries serieIgnorarAG = new XYSeries(Auxiliares.PREF_DATOS_IGNORADOS + " " + Auxiliares.PREF_DATOS_AG, false);
        XYSeries serieIgnorarRF = new XYSeries(Auxiliares.PREF_DATOS_IGNORADOS + " " + Auxiliares.PREF_DATOS_RF, false);
        XYSeries serie;
        
        XYSeriesCollection dataset = new XYSeriesCollection();

        int nDatos = this.jtBaseNeta.getRowCount();
        int colValido = this.jtBaseNeta.getColumnModel().getColumnIndex(DatosRA2.CAMPO_VALIDO);
        int colRF = this.jtBaseNeta.getColumnModel().getColumnIndex(DatosRA2.CAMPO_RF);
        int colVS = this.jtBaseNeta.getColumnModel().getColumnIndex(DatosRA2.CAMPO_V_S);
        int colLAeq1 = this.jtBaseNeta.getColumnModel().getColumnIndex(DatosRA2.CAMPO_L_A_EQ_1);

        for (int i = 0; i < nDatos; i++) {
            if (Integer.parseInt(this.jtBaseNeta.getValueAt(i, colValido).toString()) == 1) {
                if (Integer.parseInt(this.jtBaseNeta.getValueAt(i, colRF).toString()) == 0)
                    serie = serieAG;
                else
                    serie = serieRF;
            } else {
                if (Integer.parseInt(this.jtBaseNeta.getValueAt(i, colRF).toString()) == 0)
                    serie = serieIgnorarAG;
                else
                    serie = serieIgnorarRF;
            }
            
            serie.add(Double.parseDouble(this.jtBaseNeta.getValueAt(i, colVS).toString()), Double.parseDouble(this.jtBaseNeta.getValueAt(i, colLAeq1).toString()));
        }

        if (!serieAG.isEmpty())
            dataset.addSeries(serieAG);
        if (!serieIgnorarAG.isEmpty())
            dataset.addSeries(serieIgnorarAG);
        if (!serieRF.isEmpty())
            dataset.addSeries(serieRF);
        if (!serieIgnorarRF.isEmpty())
            dataset.addSeries(serieIgnorarRF);

        return dataset;
    }

    private XYDataset crearDatasetAjLin(XYSeriesCollection datasetModosFunc) throws SQLException {
        ArrayList<DatoVelocidadNivel> datos;
        int nDatos;
        XYSeries serie = null, serieIgnorar = null;
        XYSeriesCollection dataset = new XYSeriesCollection();

        for (int i = this.valBinMin; i <= this.valBinMax; i++) {
            serie = new XYSeries(Auxiliares.PREF_DATOS_AG + " " + i + Auxiliares.TXT_SERIE_BIN, false);
            serieIgnorar = new XYSeries(Auxiliares.PREF_DATOS_IGNORADOS + " " + Auxiliares.PREF_DATOS_AG + " " + i + Auxiliares.TXT_SERIE_BIN, false);

            datos = DatosRA2.getVelocidadNivelBin(this.idAsunto, this.idSite, 0, DatosRA2.CAMPO_V_S, i, i); //<Bin, VS, LAeq1>

            if (datos != null) {
                nDatos = datos.size();
                for (int j = 0; j < nDatos; j++) {
                    if (datos.get(j).getValido())
                        serie.add(datos.get(j).getVelocidad(), datos.get(j).getNivel()); //VS y LAeq1
                    else
                        serieIgnorar.add(datos.get(j).getVelocidad(), datos.get(j).getNivel()); //VS y LAeq1
                }
            }

            if (!serie.isEmpty())
                dataset.addSeries(serie);
            if (!serieIgnorar.isEmpty())
                dataset.addSeries(serieIgnorar);
        }

        //DATOS DE RF
        for (int i = this.valBinMin; i <= this.valBinMax; i++) {
            serie = new XYSeries(Auxiliares.PREF_DATOS_RF + " " + i + Auxiliares.TXT_SERIE_BIN, false);
            serieIgnorar = new XYSeries(Auxiliares.PREF_DATOS_IGNORADOS + " " + Auxiliares.PREF_DATOS_RF + " " + i + Auxiliares.TXT_SERIE_BIN, false);

            datos = DatosRA2.getVelocidadNivelBin(this.idAsunto, this.idSite, 1, DatosRA2.CAMPO_V_S, i, i); //<Bin, VS, LAeq1>

            if (datos != null) {
                nDatos = datos.size();
                for (int j = 0; j < nDatos; j++) {
                    if (datos.get(j).getValido())
                        serie.add(datos.get(j).getVelocidad(), datos.get(j).getNivel()); //VS y LAeq1
                    else
                        serieIgnorar.add(datos.get(j).getVelocidad(), datos.get(j).getNivel()); //VS y LAeq1
                }
            }

            if (!serie.isEmpty())
                dataset.addSeries(serie);
            if (!serieIgnorar.isEmpty())
                dataset.addSeries(serieIgnorar);
        }
                
        if (this.idNorma.equals(NormaRA.ID_NORMA_BWEA)) { //Agrupamos por modos de funcionamiento
//            datasetModosFunc = new XYSeriesCollection();
            XYSeries serieAG = null, serieIgnorarAG = null;
            XYSeries serieRF = null, serieIgnorarRF = null;
            
            int nModosFunc = this.modosFunc.size();
            Integer valBinModoFuncMin, valBinModoFuncMax;
            String serieKey;
            int valBin, nDatosSerie;
            
            int nSeries = dataset.getSeriesCount();
            
            for (int i = 0; i < nModosFunc; i++) {
                valBinModoFuncMin = this.modosFunc.get(i)[0];
                valBinModoFuncMax = this.modosFunc.get(i)[1];
                
                serieIgnorarAG = new XYSeries(Auxiliares.PREF_DATOS_IGNORADOS + " " + Auxiliares.PREF_DATOS_AG + " " + valBinModoFuncMin + "-" + valBinModoFuncMax + Auxiliares.TXT_SERIE_BIN, false);
                serieAG = new XYSeries(Auxiliares.PREF_DATOS_AG + " " + valBinModoFuncMin + "-" + valBinModoFuncMax + Auxiliares.TXT_SERIE_BIN, false);
                serieIgnorarRF = new XYSeries(Auxiliares.PREF_DATOS_IGNORADOS + " " + Auxiliares.PREF_DATOS_RF + " " + valBinModoFuncMin + "-" + valBinModoFuncMax + Auxiliares.TXT_SERIE_BIN, false);
                serieRF = new XYSeries(Auxiliares.PREF_DATOS_RF + " " + valBinModoFuncMin + "-" + valBinModoFuncMax + Auxiliares.TXT_SERIE_BIN, false);
                
                for (int j = 0; j < nSeries; j++) {
                    serieKey = (String) dataset.getSeriesKey(j);
            
                    valBin = Integer.parseInt(serieKey.replaceAll("[^0-9]+", "").trim());
                    
                    if (valBin < valBinModoFuncMin || valBin > valBinModoFuncMax)
                        continue;
                    
                    nDatosSerie = dataset.getSeries(j).getItemCount();
                    
                    if (serieKey.contains(Auxiliares.PREF_DATOS_AG))
                        if (serieKey.contains(Auxiliares.PREF_DATOS_IGNORADOS))
                            serie = serieIgnorarAG;
                        else
                            serie = serieAG;
                    else
                        if (serieKey.contains(Auxiliares.PREF_DATOS_IGNORADOS))
                            serie = serieIgnorarRF;
                        else
                            serie = serieRF;
                    
                    for (int k = 0; k < nDatosSerie; k++) {
                        serie.add(dataset.getSeries(j).getDataItem(k));
                    }
                }
                
                if (!serieAG.isEmpty())
                    datasetModosFunc.addSeries(serieAG);
                if (!serieIgnorarAG.isEmpty())
                    datasetModosFunc.addSeries(serieIgnorarAG);
                if (!serieRF.isEmpty())
                    datasetModosFunc.addSeries(serieRF);
                if (!serieIgnorarRF.isEmpty())
                    datasetModosFunc.addSeries(serieIgnorarRF);
            }
        }

        return dataset;
    }
    
    private void rellenarAnalisisOCT(JProgressBar jpb) throws SQLException, NoSuchFieldException {
        //Creamos la gráfica
        this.datasetOCT = crearDatasetAnGraOCT();
        muestraAnGraOCT(null, null);
    }
    
    private LinkedHashMap<Double, Double> getColsNivel(int fila) {
        LinkedHashMap<Double, Double> res = null;
        String nomCol;
        
        TableColumnModel tcm = this.jtBaseNeta.getColumnModel();
        int nCols = this.jtBaseNeta.getColumnCount();
        
        for (int i = 0; i < nCols; i++) {
            nomCol = ((String) tcm.getColumn(i).getHeaderValue()).replaceAll("[^0-9]+", "").trim();
            
            if (nomCol.length() > 0) { //Si la cebcera contiene números, será una de las columnas de niveles
                if (res == null)
                    res = new LinkedHashMap<Double, Double>();
                
                res.put(Double.parseDouble(nomCol), ((BigDecimal) this.jtBaseNeta.getValueAt(fila, i)).doubleValue());
            }
        }
        
        return res;
    }
    
    private XYSeriesCollection crearDatasetAnGraOCT() {
        XYSeriesCollection dataset = new XYSeriesCollection();

        XYSeries serie;

        int nDatos = this.jtBaseNeta.getRowCount();
        
        int colValido = this.jtBaseNeta.getColumnModel().getColumnIndex(DatosRA2.CAMPO_VALIDO);
        int colRF = this.jtBaseNeta.getColumnModel().getColumnIndex(DatosRA2.CAMPO_RF);
        int colVS = this.jtBaseNeta.getColumnModel().getColumnIndex(DatosRA2.CAMPO_V_S);
        LinkedHashMap<Double, Double> colsNivel;
                
        //HasMap<AG/RF, numEspectro>>>
        HashMap<String, Integer> contAG_RF = null;
        int cont, contAux;
        String key;
        Iterator it;
        Entry entry;
        
        for (int i = this.valBinMin; i <= this.valBinMax; i++) {
            contAG_RF = new HashMap<String, Integer>();
            
            contAG_RF.put(Auxiliares.PREF_DATOS_AG, 0);
            contAG_RF.put(Auxiliares.PREF_DATOS_RF, 0);
            contAG_RF.put(Auxiliares.PREF_DATOS_IGNORADOS + " " + Auxiliares.PREF_DATOS_AG, 0);
            contAG_RF.put(Auxiliares.PREF_DATOS_IGNORADOS + " " + Auxiliares.PREF_DATOS_RF, 0);

            for (int j = 0; j < nDatos; j++) {
                if (Math.round(((BigDecimal) this.jtBaseNeta.getValueAt(j, colVS)).doubleValue()) != i)
                    continue;
                
                key = "";
                if (Integer.parseInt(this.jtBaseNeta.getValueAt(j, colValido).toString()) == 0) {
                    key += Auxiliares.PREF_DATOS_IGNORADOS + " ";
                }
                
                if (Integer.parseInt(this.jtBaseNeta.getValueAt(j, colRF).toString()) == 0) {
                    key += Auxiliares.PREF_DATOS_AG;
                } else {
                    key += Auxiliares.PREF_DATOS_RF;
                }
                
                cont = contAG_RF.get(key) + 1;
                
                serie = new XYSeries(key + Auxiliares.TXT_SERIE_SEP + i + Auxiliares.TXT_SERIE_BIN + Auxiliares.TXT_SERIE_SEP + Auxiliares.TXT_SERIE_ESPECTRO + cont, false);

                colsNivel = getColsNivel(j);
                
                it = colsNivel.entrySet().iterator();
                
                while (it.hasNext()) {
                    entry = (Entry) it.next();
                    
                    serie.add((Double) entry.getKey(), (Double) entry.getValue());
                }

                if (!serie.isEmpty()) {
                    dataset.addSeries(serie);
                    contAG_RF.put(key, cont);

                    if (this.mapMaxPosAnGraEspOCT.containsKey(i))
                        contAux = this.mapMaxPosAnGraEspOCT.get(i);
                    else
                        contAux = 0;

                    if (cont > contAux)
                        this.mapMaxPosAnGraEspOCT.put(i, cont);
                }
            }
        }
        
        return dataset;
    }
    
    private void rellenarAnalisisFFT(JProgressBar jpb, boolean soloValidos) throws SQLException, NoSuchFieldException, Exception {
        //Creamos la gráfica
        this.datasetFFT = crearDatasetAnGraFFT(soloValidos);
        muestraAnGraFFT(null, null);
        Auxiliares.setPorcentajeProgress(jpb, 1.0);
    }
    
    private XYSeriesCollection crearDatasetAnGraFFT(boolean soloValidos) throws Exception {
        String xml;
        ArrayList<DatoXML> datosXml;
        int nDatosXml;
        DatoXML datoXml;
        
        XYSeriesCollection dataset = new XYSeriesCollection();

        XYSeries serie;

        int nDatos = this.jtBaseNeta.getRowCount();
        int colIdDato = this.jtBaseNeta.getColumnModel().getColumnIndex(DatosRA2.CAMPO_ID_DATO);
        int colValido = this.jtBaseNeta.getColumnModel().getColumnIndex(DatosRA2.CAMPO_VALIDO);
        int colRF = this.jtBaseNeta.getColumnModel().getColumnIndex(DatosRA2.CAMPO_RF);
        int colVS = this.jtBaseNeta.getColumnModel().getColumnIndex(DatosRA2.CAMPO_V_S);
        
        ArrayList<String> campoXML = new ArrayList<String>();
        campoXML.add(DatosRA2.CAMPO_XML);
        
        String key;
        //HasMap<AG/RF, numEspectro>>>
        HashMap<String, Integer> contAG_RF = null;
        int cont, contAux;
        
        for (int i = this.valBinMin; i <= this.valBinMax; i++) {
            contAG_RF = new HashMap<String, Integer>();
            
            contAG_RF.put(Auxiliares.PREF_DATOS_AG, 0);
            contAG_RF.put(Auxiliares.PREF_DATOS_RF, 0);
            contAG_RF.put(Auxiliares.PREF_DATOS_IGNORADOS + " " + Auxiliares.PREF_DATOS_AG, 0);
            contAG_RF.put(Auxiliares.PREF_DATOS_IGNORADOS + " " + Auxiliares.PREF_DATOS_RF, 0);

            for (int j = 0; j < nDatos; j++) {
                if (Math.round(((BigDecimal) this.jtBaseNeta.getValueAt(j, colVS)).doubleValue()) != i)
                    continue;
                
                if (soloValidos && Integer.parseInt(this.jtBaseNeta.getValueAt(j, colValido).toString()) == 0)
                    continue;

                xml = (String) DatosRA2.getDatosVistaNetaIdDato(this.tipoTabla, this.idAsunto, this.idSite, (Integer) this.jtBaseNeta.getValueAt(j, colIdDato), campoXML, null, null).get(0)[0];
                
                if (xml != null) {
                    datosXml = InteraccionXML.leeXml(xml);  //ArrayList<Object[idDato, frecuencia, nivel, valido]>
                    nDatosXml = datosXml.size();
                
                    key = "";
                    if (Integer.parseInt(this.jtBaseNeta.getValueAt(j, colValido).toString()) == 0) {
                        key += Auxiliares.PREF_DATOS_IGNORADOS + " ";
                    }

                    if (Integer.parseInt(this.jtBaseNeta.getValueAt(j, colRF).toString()) == 0) {
                        key += Auxiliares.PREF_DATOS_AG;
                    } else {
                        key += Auxiliares.PREF_DATOS_RF;
                    }

                    cont = contAG_RF.get(key) + 1;

                    serie = new XYSeries(key + Auxiliares.TXT_SERIE_SEP + i + Auxiliares.TXT_SERIE_BIN + Auxiliares.TXT_SERIE_SEP + Auxiliares.TXT_SERIE_ESPECTRO + cont, false);
            
                    for (int k = 0; k < nDatosXml; k++) {
                        datoXml = datosXml.get(k);
                        
                        serie.add(datoXml.getFrecuencia(), datoXml.getNivel());
                    }
                    
                    if (!serie.isEmpty()) {
                        dataset.addSeries(serie);
                        contAG_RF.put(key, cont);
                        
                        if (this.mapMaxPosAnGraEspFFT.containsKey(i))
                            contAux = this.mapMaxPosAnGraEspFFT.get(i);
                        else
                            contAux = 0;
                        
                        if (cont > contAux)
                            this.mapMaxPosAnGraEspFFT.put(i, cont);
                    }
                }
            }
        }
        
        return dataset;
    }
    
    private void cargaDatos() {
        boolean error = false;
        
        try {
            //Rellenamos los datos
            Auxiliares.bloqueaDialog(this, true);
            JProgressBar jpb = Auxiliares.muestraProgress(this, 100 * 1000);

            
            //OCT BWEA
            if (this.tipoTabla.compareTo(Auxiliares.TIPO_OCT) == 0) {
                if (this.idNorma.equals(NormaRA.ID_NORMA_BWEA)) {
                    Integer binEstudio;

                    //Establecemos el bin de estudios por defecto
                    this.jtfBinEstudio.setText(BIN_ESTUDIO_MODO_FUNC.toString());
                    if (JOptionPane.showConfirmDialog(this, this.jpTipoCalculoSPLModoFunc, "Seleccione bin a estudiar", JOptionPane.OK_OPTION) == JOptionPane.OK_OPTION) {
                        binEstudio = Integer.parseInt(this.jtfBinEstudio.getText());

                        this.valBinMin = Integer.parseInt(this.jtfBinEstudio.getText());
                        this.valBinMax = Integer.parseInt(this.jtfBinEstudio.getText());
                    }
                }
            }
            
            //Genéricas
            rellenarBaseNeta(jpb, this.tipoTabla.compareTo(Auxiliares.TIPO_FFT) == 0);
            rellenarCompletitud(jpb);
            
            //SPL
            if (this.tipoTabla.compareTo(Auxiliares.TIPO_SPL) == 0) {
                rellenarCoefAjPol(jpb);
                rellenarCoefAjLin(jpb);
            }
            
            //OCT
            if (this.tipoTabla.compareTo(Auxiliares.TIPO_OCT) == 0) {
                rellenarAnalisisOCT(jpb);
            }
            //FFT
            if (this.tipoTabla.compareTo(Auxiliares.TIPO_FFT) == 0) {
                int opcionGraficar = JOptionPane.showOptionDialog(this, "¿Qué espectros desea graficar? Esto podría incrementar el tiempo de proceso", "Aviso", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"Todos", "Solo los validos", "Ninguno"}, "Solo los validos");

                if (opcionGraficar != JOptionPane.CANCEL_OPTION)
                    rellenarAnalisisFFT(jpb, opcionGraficar == JOptionPane.NO_OPTION);
            }

            Auxiliares.ocultaProgress(jpb);
            Auxiliares.bloqueaDialog(this, false);

            this.update(this.getGraphics());
        } catch (SQLException e) {
            error = true;
            MensajeApp.muestraError(this, e, "Fallo al consultar la base de datos");
        } catch (NoSuchFieldException e) {
            error = true;
            MensajeApp.muestraError(this, e, "Fallo añadiendo campo");
        } catch (IllegalArgumentException e) {
            error = true;
            MensajeApp.muestraError(this, e, "Fallo realizando la operación");
        } catch (Exception e) {
            error = true;
            MensajeApp.muestraError(this, e, "Fallo realizando la operación");
        } finally {
            if (error) {
                Auxiliares.salirDialogo(this, null, this.modoSalida, Auxiliares.MODO_ERROR);
            }
        }
    }

private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
    //Establecemos el título
    this.setTitle("SEGUIMIENTO / " + this.tipoTabla.toUpperCase() + " - PREVISUALIZACIÓN");

    //Establecemos iconos de los botones
    this.jbAnGraOCTAnt.setIcon(Auxiliares.ICONO_PREV);
    this.jbAnGraOCTSig.setIcon(Auxiliares.ICONO_NEXT);
    this.jbAnGraEspOCTAnt.setIcon(Auxiliares.ICONO_PREV);
    this.jbAnGraEspOCTSig.setIcon(Auxiliares.ICONO_NEXT);
    this.jbAnGraFFTAnt.setIcon(Auxiliares.ICONO_PREV);
    this.jbAnGraFFTSig.setIcon(Auxiliares.ICONO_NEXT);
    this.jbAnGraEspFFTAnt.setIcon(Auxiliares.ICONO_PREV);
    this.jbAnGraEspFFTSig.setIcon(Auxiliares.ICONO_NEXT);

    //Establecemos las pestañas de JTabbedPane
    Auxiliares.setTitulosJTabbedPane(this.jtpDatos, new String[]{"DATOS NETOS", "RESULTADOS POR BIN", "AJUSTE POLINOMIAL", "AJUSTE LINEAL", "ESPECTRO OCT", "ESPECTROS FFT"});

    //Eliminamos las pestañas que no se corresponden con el tipo de análisis a realizar
    int nTabs = this.jtpDatos.getTabCount();
    for (int i = nTabs - 1; i >= 0; i--) {
        if (!this.jtpDatos.getComponentAt(i).getName().startsWith(Auxiliares.TIPO_GEN) && !this.jtpDatos.getComponentAt(i).getName().startsWith(this.tipoTabla))
            this.jtpDatos.remove(i);
    }
    
    //Maximizamos las pestañas
    Auxiliares.maximizaTitulosJTabbedPane(this.jtpDatos);

    cargaDatos();
}//GEN-LAST:event_formWindowOpened

private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
    int nDatos = this.idValiDatosModif.size();
    Integer idDato, validoAnt;
    
    try {
        if (Auxiliares.esSalidaUndefined(this.modoSalida)) { //Se ha cancelado desde la propia ventana
            if (nDatos > 0) {
                if (JOptionPane.showConfirmDialog(this, "Se ignorarán las modificaciones en los datos. ¿Desea continuar?", "Aviso", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.OK_OPTION) {
                    for (int i = 0; i < nDatos; i++) {
                        idDato = (Integer) this.idValiDatosModif.get(i)[0];
                        validoAnt = (Integer) this.idValiDatosModif.get(i)[1];
                        
                        DatosRA2.setDatoValido(this.tipoTabla, this.idAsunto, idDato, validoAnt);
                    }
                    
                    //Y salimos del diálogo
                    this.dispose();
                }
            } else
                Auxiliares.salirDialogo(this, Auxiliares.CANCEL_PROCESS, this.modoSalida, Auxiliares.MODO_CANCEL);
        } else {
            //Si es incorrecta, deshacemos los cambios
            if (Auxiliares.esSalidaIncorrecta(this.modoSalida)) {
                for (int i = 0; i < nDatos; i++) {
                    idDato = (Integer)this.idValiDatosModif.get(i)[0];
                    validoAnt = (Integer)this.idValiDatosModif.get(i)[1];

                    DatosRA2.setDatoValido(this.tipoTabla, this.idAsunto, idDato, validoAnt);
                }
            }
            
            //Y salimos del diálogo
            this.dispose();
        }
    } catch (SQLException e) {
        MensajeApp.muestraError(this, e, "Fallo al consultar la base de datos");
    }
}//GEN-LAST:event_formWindowClosing

private void deshacerModificacion(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deshacerModificacion
    int nDatos = this.idValiDatosModif.size();
    Integer idDato, validoAnt;
    
    try {
        if (nDatos > 0) {
            idDato = this.idValiDatosModif.get(nDatos - 1)[0];
            validoAnt = this.idValiDatosModif.get(nDatos - 1)[1];

            if (DatosRA2.setDatoValido(this.tipoTabla, this.idAsunto, idDato, validoAnt) > 0) {
                this.idValiDatosModif.remove(nDatos - 1);

                if (nDatos - 1 == 0)
                    this.jbDeshacer.setEnabled(false);

                //Refrescamos información
                cargaDatos();
            } else
                MensajeApp.muestraError(this, null, "Fallo al realizar acción");
        }
    } catch (SQLException e) {
        MensajeApp.muestraError(this, e, "Fallo al consultar la base de datos");
    }
}//GEN-LAST:event_deshacerModificacion

private void siguiente(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siguiente
    if (this.tipoTabla.equals(Auxiliares.TIPO_SPL)) {
        int tipoCalculoAG = DatosRA2.TIPO_CAL_REG_LIN;
        int tipoCalculoRF = DatosRA2.TIPO_CAL_REG_LIN;
        
        double[] coefPolAG = this.coeficientesPol.get(Auxiliares.PREF_DATOS_AG);
        double[] coefPolRF = this.coeficientesPol.get(Auxiliares.PREF_DATOS_RF);
        double coefCorrAG = coefPolAG[coefPolAG.length - 1];
        double coefCorrRF = coefPolRF[coefPolRF.length - 1];
        
        this.jlCoefCorrAG.setText("<html>Coeficiente de correlación AG calculado: <b>" + coefCorrAG + "</b></html>");
        this.jlCoefCorrRF.setText("<html>Coeficiente de correlación RF calculado: <b>" + coefCorrRF + "</b></html>");
        
        this.jrbRegPolAG.setSelected(coefCorrAG >= 0.8);
        this.jrbRegLinAG.setSelected(coefCorrAG < 0.8);
        this.jrbRegPolRF.setSelected(coefCorrRF >= 0.8);
        this.jrbRegLinRF.setSelected(coefCorrRF < 0.8);

        if (!this.idNorma.equals(NormaRA.ID_NORMA_BWEA)) {
            if (this.idNorma.equals(NormaRA.ID_NORMA_AWEA) || JOptionPane.showConfirmDialog(this, this.jpTipoCalculoSPL, "Seleccione tipo de cálculo a realizar", JOptionPane.OK_OPTION) == JOptionPane.OK_OPTION) {
                if (this.idNorma.equals(NormaRA.ID_NORMA_AWEA)) {
                    this.jrbRegLinAG.setSelected(true);
                    this.jrbRegPolAG.setSelected(false);
                    this.jrbRegLinRF.setSelected(true);
                    this.jrbRegPolRF.setSelected(false);                    
                }                
                
                if (this.jrbRegLinAG.isSelected()) {
                    tipoCalculoAG = DatosRA2.TIPO_CAL_REG_LIN;
                    
                    try {
                        String valiRegLin = DatosRA2.validaDatosRegLin(this.tipoTabla, this.idAsunto, this.idSite, this.valBinMin, this.valBinMax, 0);

                        if (valiRegLin != null && !valiRegLin.isEmpty())
                            MensajeApp.muestraWarning(this, valiRegLin);
                    } catch (SQLException e) {
                        MensajeApp.muestraError(this, e, "Fallo al consultar la base de datos");
                    }
                } else
                    tipoCalculoAG = DatosRA2.TIPO_CAL_REG_POL;
                
                if (this.jrbRegLinRF.isSelected()) {
                    tipoCalculoRF = DatosRA2.TIPO_CAL_REG_LIN;
                    
                    try {
                        String valiRegLin = DatosRA2.validaDatosRegLin(this.tipoTabla, this.idAsunto, this.idSite, this.valBinMin, this.valBinMax, 1);

                        if (valiRegLin != null && !valiRegLin.isEmpty())
                            MensajeApp.muestraWarning(this, valiRegLin);
                    } catch (SQLException e) {
                        MensajeApp.muestraError(this, e, "Fallo al consultar la base de datos");
                    }
                } else
                    tipoCalculoRF = DatosRA2.TIPO_CAL_REG_POL;

                this.setVisible(false);
                
                //Graficamos las regresiones con las que se obtendrán los resultados
                if (this.idNorma.equals(NormaRA.ID_NORMA_IEC_2_1)) {
                    XYPlot plotAjPol = ((ChartPanel) this.jpGraficaAjPol.getComponent(0)).getChart().getXYPlot();
                    XYPlot plotAjLin = ((ChartPanel) this.jpGraficaAjLin.getComponent(0)).getChart().getXYPlot();
                    XYSeriesCollection dsResultados = new XYSeriesCollection();
                    XYPlot plotAux;
                    String serieKey;
                    
                    //AG
                    plotAux = tipoCalculoAG == DatosRA2.TIPO_CAL_REG_POL ? plotAjPol : plotAjLin;
                    int nDatasets = plotAux.getDatasetCount();
                    
                    for (int i = 0; i < nDatasets; i++) {
                        for (int j = 0; j < plotAux.getDataset(i).getSeriesCount(); j++) {
                            serieKey = (String) plotAux.getDataset(i).getSeriesKey(j);
                            if (serieKey.contains(Auxiliares.PREF_DATOS_AG) && !serieKey.contains(Auxiliares.PREF_DATOS_IGNORADOS))
                                dsResultados.addSeries(((XYSeriesCollection) plotAux.getDataset(i)).getSeries(j));
                        }
                    }
                    
                    //RF
                    plotAux = tipoCalculoRF == DatosRA2.TIPO_CAL_REG_POL ? plotAjPol : plotAjLin;
                    nDatasets = plotAux.getDatasetCount();
                    
                    for (int i = 0; i < nDatasets; i++) {
                        for (int j = 0; j < plotAux.getDataset(i).getSeriesCount(); j++) {
                            serieKey = (String) plotAux.getDataset(i).getSeriesKey(j);
                            if (serieKey.contains(Auxiliares.PREF_DATOS_RF) && !serieKey.contains(Auxiliares.PREF_DATOS_IGNORADOS))
                                dsResultados.addSeries(((XYSeriesCollection) plotAux.getDataset(i)).getSeries(j));
                        }
                    }
                    
                    Auxiliares.asignaPanelGrafica(this, this.jpContGraficaAjPolLin, crearGraficaAjPolLin(dsResultados), false, null);
				    //JOptionPane.showMessageDialog(this, this.jpContGraficaAjPolLin, "Regresiones seleccionadas", JOptionPane.PLAIN_MESSAGE);
				    JDialog jd = new JDialog((Frame) this.getOwner(), "Regresiones seleccionadas", true);
				    jd.setLocationByPlatform(true);
				    jd.getContentPane().add(this.jpContGraficaAjPolLin, BorderLayout.CENTER);
				    jd.pack();
					jd.setSize(this.getSize());
					jd.setLocationRelativeTo(jd.getParent());
					jd.setVisible(true);
                }
                    
                DatosResultadosSPLGUI dR = new DatosResultadosSPLGUI((Frame) this.getParent(), this.tipoTabla, this.idAsunto, this.idSite, this.valBinMin, this.valBinMax, this.modosFunc, this.coeficientesPol, this.coeficientesBin, tipoCalculoAG, tipoCalculoRF, this.coeficientesModosFunc, this.modoSalida, this.idNorma, this.incertidumbres);
                dR.setVisible(true);
            } else
                return;
        } else if (this.idNorma.equals(NormaRA.ID_NORMA_BWEA)) {
            Integer binEstudio;
           
            //Establecemos el bin de estudios por defecto
            this.jtfBinEstudio.setText(BIN_ESTUDIO_MODO_FUNC.toString());
            if (JOptionPane.showConfirmDialog(this, this.jpTipoCalculoSPLModoFunc, "Seleccione bin a estudiar", JOptionPane.OK_OPTION) == JOptionPane.OK_OPTION) {
                binEstudio = Integer.parseInt(this.jtfBinEstudio.getText());
                
                this.setVisible(false);
                DatosResultadosSPLGUI dR = new DatosResultadosSPLGUI((Frame) this.getParent(), this.tipoTabla, this.idAsunto, this.idSite, binEstudio, binEstudio, this.modosFunc, this.coeficientesPol, this.coeficientesBin, tipoCalculoAG, tipoCalculoRF, this.coeficientesModosFunc, this.modoSalida, this.idNorma, this.incertidumbres);
                dR.setVisible(true);
            } else
                return;
        }
    } else if (this.tipoTabla.equals(Auxiliares.TIPO_OCT)) {
        this.setVisible(false);
        DatosResultadosOCTGUI dR = new DatosResultadosOCTGUI((Frame) this.getParent(), this.tipoTabla, this.idAsunto, this.idSite, this.valBinMin, this.valBinMax, this.modosFunc, this.modoSalida, this.idNorma, this.incertidumbres);
        dR.setVisible(true);
    } else if (this.tipoTabla.equals(Auxiliares.TIPO_FFT)) {
        this.setVisible(false);
        DatosResultadosFFTGUI dR = new DatosResultadosFFTGUI((Frame) this.getParent(), this.tipoTabla, this.idAsunto, this.idSite, this.valBinMin, this.valBinMax, this.modoSalida, this.idNorma, this.incertidumbres);
        dR.setVisible(true);
    }
    
    //Si volvemos para una modificación (atrás), mostramos de nuevo el diálogo
    if (Auxiliares.esSalidaAnt(this.modoSalida)) {
        Auxiliares.clearModoSalida(this.modoSalida);
        this.setVisible(true);
        return;
    }
    
    //Si no, pasamos el resultado a formWindowClosing
    formWindowClosing(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
}//GEN-LAST:event_siguiente

private void muestraAnGraFFTAnt(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muestraAnGraFFTAnt
    muestraAnGraFFT(false, null);
}//GEN-LAST:event_muestraAnGraFFTAnt

private void muestraAnGraFFTSig(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muestraAnGraFFTSig
    muestraAnGraFFT(true, null);
}//GEN-LAST:event_muestraAnGraFFTSig

private void muestraAnGraEspFFTAnt(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muestraAnGraEspFFTAnt
    muestraAnGraFFT(null, false);
}//GEN-LAST:event_muestraAnGraEspFFTAnt

private void muestraAnGraEspFFTSig(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muestraAnGraEspFFTSig
    muestraAnGraFFT(null, true);
}//GEN-LAST:event_muestraAnGraEspFFTSig

private void muestraAnGraOCTAnt(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muestraAnGraOCTAnt
    muestraAnGraOCT(false, null);
}//GEN-LAST:event_muestraAnGraOCTAnt

private void muestraAnGraEspOCTAnt(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muestraAnGraEspOCTAnt
    muestraAnGraOCT(null, false);
}//GEN-LAST:event_muestraAnGraEspOCTAnt

private void muestraAnGraEspOCTSig(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muestraAnGraEspOCTSig
    muestraAnGraOCT(null, true);
}//GEN-LAST:event_muestraAnGraEspOCTSig

private void muestraAnGraOCTSig(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muestraAnGraOCTSig
    muestraAnGraOCT(true, null);
}//GEN-LAST:event_muestraAnGraOCTSig

private void jpTipoCalculoSPLModoFuncAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jpTipoCalculoSPLModoFuncAncestorAdded
    this.jtfBinEstudio.requestFocus();
}//GEN-LAST:event_jpTipoCalculoSPLModoFuncAncestorAdded

private void jpObsAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jpObsAncestorAdded
    this.jtaObs.requestFocus();
}//GEN-LAST:event_jpObsAncestorAdded

private void jpTipoCalculoSPLAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jpTipoCalculoSPLAncestorAdded
// TODO add your handling code here:
    if (this.jrbRegLinAG.isSelected())
        this.jrbRegLinAG.requestFocus();
    else
        this.jrbRegPolAG.requestFocus();
}//GEN-LAST:event_jpTipoCalculoSPLAncestorAdded

private void muestraAnGraOCT(Boolean siguiente, Boolean siguienteEsp) {
    try {
        if (this.datasetOCT.getSeriesCount() != 0) {
            XYSeriesCollection dataset = new XYSeriesCollection();
            XYSeriesCollection datasetFinal = new XYSeriesCollection();

            if (siguiente != null) {
                if (siguiente)
                    this.posAnGraOCT++;
                else
                    this.posAnGraOCT--;

                this.posAnGraEspOCT = 0;
            }

            if (siguienteEsp != null) {
                if (siguienteEsp)
                    this.posAnGraEspOCT++;
                else
                    this.posAnGraEspOCT--;
            }

            int maxPosAnGraOCT = this.mapMaxPosAnGraEspOCT.keySet().size();
            
            if (maxPosAnGraOCT <= 1) {
                this.jbAnGraOCTAnt.setEnabled(false);
                this.jbAnGraOCTSig.setEnabled(false);
            }

            if (this.posAnGraOCT >= maxPosAnGraOCT)
                this.posAnGraOCT -= maxPosAnGraOCT;
            else if (this.posAnGraOCT < 0)
                this.posAnGraOCT += maxPosAnGraOCT;

            int nSeries = this.datasetOCT.getSeriesCount();

            int valBin = (Integer) this.mapMaxPosAnGraEspOCT.keySet().toArray()[this.posAnGraOCT];

            for (int i = 0; i < nSeries; i++) {
                if (((String) this.datasetOCT.getSeriesKey(i)).contains(Auxiliares.TXT_SERIE_SEP + valBin + Auxiliares.TXT_SERIE_BIN))
                    dataset.addSeries(this.datasetOCT.getSeries(i));
            }
            
            int maxPosAnGraEspOCT = this.mapMaxPosAnGraEspOCT.get(valBin);
            
            if (maxPosAnGraEspOCT <= 1) {
                this.jbAnGraEspOCTAnt.setEnabled(false);
                this.jbAnGraEspOCTSig.setEnabled(false);
            }
            
            maxPosAnGraEspOCT += 1; //Para ver todos

            if (this.posAnGraEspOCT >= maxPosAnGraEspOCT)
                this.posAnGraEspOCT -= maxPosAnGraEspOCT;
            else if (this.posAnGraEspOCT < 0)
                this.posAnGraEspOCT += maxPosAnGraEspOCT;
            
            int numEspSerie;

            String serieKey;
            for (int i = 0; i < dataset.getSeriesCount(); i++) {
                if (this.posAnGraEspOCT != 0) {
                    serieKey = (String) dataset.getSeriesKey(i);
                    numEspSerie = Integer.parseInt(serieKey.substring(serieKey.indexOf(Auxiliares.TXT_SERIE_ESPECTRO)).replaceAll("[^0-9]+", "").trim());
                    
                    if (numEspSerie != this.posAnGraEspOCT)
                        continue;
                }
                
                datasetFinal.addSeries(dataset.getSeries(i));
            }

            if (datasetFinal.getSeriesCount() == 0) //Si no hay nada que mostrar, mostramos el siguiente (anterior)
                muestraAnGraOCT(siguiente, null);
            else {
                Auxiliares.asignaPanelGrafica(this, this.jpGraficaAnGraOCT, crearGraficaAnGraOCT(datasetFinal), true, this);
                this.update(this.getGraphics());
            }
        }
    } catch (Exception e) {
        MensajeApp.muestraError(this, e, "Fallo realizando la operación");
    }
}

private void muestraAnGraFFT(Boolean siguiente, Boolean siguienteEsp) {
    try {
        if (this.datasetFFT.getSeriesCount() != 0) {
            XYSeriesCollection dataset = new XYSeriesCollection();
            XYSeriesCollection datasetFinal = new XYSeriesCollection();

            if (siguiente != null) {
                if (siguiente)
                    this.posAnGraFFT++;
                else
                    this.posAnGraFFT--;

                this.posAnGraEspFFT = 0;
            }

            if (siguienteEsp != null) {
                if (siguienteEsp)
                    this.posAnGraEspFFT++;
                else
                    this.posAnGraEspFFT--;
            }

            int maxPosAnGraFFT = this.mapMaxPosAnGraEspFFT.keySet().size();
            
            if (maxPosAnGraFFT <= 1) {
                this.jbAnGraFFTAnt.setEnabled(false);
                this.jbAnGraFFTSig.setEnabled(false);
            }

            if (this.posAnGraFFT >= maxPosAnGraFFT)
                this.posAnGraFFT -= maxPosAnGraFFT;
            else if (this.posAnGraFFT < 0)
                this.posAnGraFFT += maxPosAnGraFFT;

            int nSeries = this.datasetFFT.getSeriesCount();

            int valBin = (Integer) this.mapMaxPosAnGraEspFFT.keySet().toArray()[this.posAnGraFFT];

            for (int i = 0; i < nSeries; i++) {
                if (((String) this.datasetFFT.getSeriesKey(i)).contains(Auxiliares.TXT_SERIE_SEP + valBin + Auxiliares.TXT_SERIE_BIN))
                    dataset.addSeries(this.datasetFFT.getSeries(i));
            }
            
            int maxPosAnGraEspFFT = this.mapMaxPosAnGraEspFFT.get(valBin);
            
            if (maxPosAnGraEspFFT <= 1) {
                this.jbAnGraEspFFTAnt.setEnabled(false);
                this.jbAnGraEspFFTSig.setEnabled(false);
            }
            
            maxPosAnGraEspFFT += 1; //Para ver todos

            if (this.posAnGraEspFFT >= maxPosAnGraEspFFT)
                this.posAnGraEspFFT -= maxPosAnGraEspFFT;
            else if (this.posAnGraEspFFT < 0)
                this.posAnGraEspFFT += maxPosAnGraEspFFT;
            
            int numEspSerie;

            String serieKey;
            for (int i = 0; i < dataset.getSeriesCount(); i++) {
                if (this.posAnGraEspFFT != 0) {
                    serieKey = (String) dataset.getSeriesKey(i);
                    numEspSerie = Integer.parseInt(serieKey.substring(serieKey.indexOf(Auxiliares.TXT_SERIE_ESPECTRO)).replaceAll("[^0-9]+", "").trim());
                    
                    if (numEspSerie != this.posAnGraEspFFT)
                        continue;
                }
                
                datasetFinal.addSeries(dataset.getSeries(i));
            }

            if (datasetFinal.getSeriesCount() == 0) //Si no hay nada que mostrar, mostramos el siguiente (anterior)
                muestraAnGraFFT(siguiente, null);
            else {
                Auxiliares.asignaPanelGrafica(this, this.jpGraficaAnGraFFT, crearGraficaAnGraFFT(datasetFinal), true, this);
                this.update(this.getGraphics());
            }
        }
    } catch (Exception e) {
        MensajeApp.muestraError(this, e, "Fallo realizando la operación");
    }
}

private Integer localizaDatoSPL(XYItemEntity item) {
    Integer res = null;
    Integer rFFila;
    Double vS, vSFila;
    Double nivel, nivelFila;
    int idSerie = item.getSeriesIndex();
    
    String nomSerie = (String)item.getDataset().getSeriesKey(idSerie);

    Integer rF = null;

    if (nomSerie.contains(Auxiliares.PREF_DATOS_AG))
        rF = 0;
    else if (nomSerie.contains(Auxiliares.PREF_DATOS_RF))
        rF = 1;
    
    //Buscamos el datos que coincida
    DefaultTableModel dtmBaseNeta = (DefaultTableModel) this.jtBaseNeta.getModel();
    int nDatos = dtmBaseNeta.getRowCount();

    int colRF = dtmBaseNeta.findColumn(DatosRA2.CAMPO_RF);
    int colVS = dtmBaseNeta.findColumn(DatosRA2.CAMPO_V_S);
    int colNivel = dtmBaseNeta.findColumn(DatosRA2.CAMPO_L_A_EQ_1);
    
    int numItem = item.getItem();
    vS = item.getDataset().getXValue(idSerie, numItem);
    nivel = item.getDataset().getYValue(idSerie, numItem);

    for (int i = 0; i < nDatos; i++) {
        rFFila = (Integer) dtmBaseNeta.getValueAt(i, colRF);
        if (!rF.equals(rFFila))
            continue;

        vSFila = ((BigDecimal) dtmBaseNeta.getValueAt(i, colVS)).doubleValue();
        if (!vS.equals(vSFila))
            continue;

        nivelFila = ((BigDecimal) dtmBaseNeta.getValueAt(i, colNivel)).doubleValue();
        if (!nivel.equals(nivelFila))
            continue;

        //Hemos encontrado el punto
        res = (Integer) dtmBaseNeta.getValueAt(i, 0);
        break; //No necesitamos seguir
    }
    
    return res;
}

private Integer localizaDatoEspectro(XYItemEntity item) {
    Integer res = null;

    try {
        Integer rFFila, espFila, validoFila;
        int idSerie = item.getSeriesIndex();
        Integer numBin = null;
        Integer numEsp = null;

        String nomSerie = (String)item.getDataset().getSeriesKey(idSerie);

        Integer rF = null;
        Integer valido = null;

        if (nomSerie.contains(Auxiliares.PREF_DATOS_AG))
            rF = 0;
        else if (nomSerie.contains(Auxiliares.PREF_DATOS_RF))
            rF = 1;

        if (nomSerie.contains(Auxiliares.PREF_DATOS_IGNORADOS))
            valido = 0;
        else
            valido = 1;

        String nomSerieSplit[] = nomSerie.split(Auxiliares.TXT_SERIE_SEP);

        for (int i = 0; i < nomSerieSplit.length; i++) {
            if (nomSerieSplit[i].contains(Auxiliares.TXT_SERIE_BIN)) {
                numBin = Integer.parseInt(nomSerieSplit[i].replace(Auxiliares.TXT_SERIE_BIN, "").trim());
            } else if (nomSerieSplit[i].contains(Auxiliares.TXT_SERIE_ESPECTRO))
                numEsp = Integer.parseInt(nomSerieSplit[i].replace(Auxiliares.TXT_SERIE_ESPECTRO, "").trim());
            
            if (numBin != null && numEsp != null)
                break;
        }

        //Buscamos el datos que coincida
        DefaultTableModel dtmBaseNeta = (DefaultTableModel) this.jtBaseNeta.getModel();
        int nDatos = dtmBaseNeta.getRowCount();

        int colIdDato = dtmBaseNeta.findColumn(DatosRA2.CAMPO_ID_DATO);
        int colRF = dtmBaseNeta.findColumn(DatosRA2.CAMPO_RF);
        int colValido = dtmBaseNeta.findColumn(DatosRA2.CAMPO_VALIDO);
        int colVS = dtmBaseNeta.findColumn(DatosRA2.CAMPO_V_S);

        espFila = 1;
        for (int i = 0; i < nDatos; i++) {
            rFFila = (Integer) dtmBaseNeta.getValueAt(i, colRF);
            if (!rF.equals(rFFila))
                continue;

            //Si no pertence al bin, lo ignoramos
            if (Math.round(((BigDecimal) this.jtBaseNeta.getValueAt(i, colVS)).doubleValue()) != numBin)
                continue;
            
            validoFila = (Integer) dtmBaseNeta.getValueAt(i, colValido);
            if (!validoFila.equals(valido))
                continue;
            
            if (espFila.equals(numEsp)) {
                res = (Integer) this.jtBaseNeta.getValueAt(i, colIdDato); //Marcamos para invalidar la fila (serie) completa
                
                break; //No necesitamos seguir
            }
            
            espFila++;
        }
    } catch (Exception e) {
        MensajeApp.muestraError(this, e, "Fallo realizando la operación");
    } finally {
        return res;
    }
}

    public void chartMouseClicked(ChartMouseEvent evt) {
        JFreeChart chart = (JFreeChart) evt.getSource();

        ChartEntity ce = evt.getEntity();

        if (ce instanceof XYItemEntity) { //Es un punto de la gráfica
            XYItemEntity item = (XYItemEntity) ce;
            
            Integer idDato = null;

            if (this.jpGraficaAnGraFFT.getComponentCount() > 0 && chart.equals(((ChartPanel)this.jpGraficaAnGraFFT.getComponent(0)).getChart()))
                idDato = localizaDatoEspectro(item);
           else if (this.jpGraficaAnGraOCT.getComponentCount() > 0 && chart.equals(((ChartPanel)this.jpGraficaAnGraOCT.getComponent(0)).getChart()))
                idDato = localizaDatoEspectro(item);
            else if (item != null && this.jpGraficaAjLin.getComponentCount() > 0 && chart.equals(((ChartPanel)this.jpGraficaAjLin.getComponent(0)).getChart()))
                idDato = localizaDatoSPL(item);
            else if (item != null && this.jpGraficaAjPol.getComponentCount() > 0 && chart.equals(((ChartPanel)this.jpGraficaAjPol.getComponent(0)).getChart()))
                idDato = localizaDatoSPL(item);

            if (idDato != null) {
                cambiarDatoValido(idDato);
            }
        }
    }

    private Integer cambiarDatoValido(Integer idDato) {
        Integer valido = null, validoAct = null;
        this.jlPregunta.setText("¿Desea modificar la validez del dato <" + idDato + ">?");

        try {
            if (JOptionPane.showConfirmDialog(this, this.jpObs, "Modificar dato seleccionado", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                //Obtenemos la validez actual del dato
                DefaultTableModel dtm = (DefaultTableModel) this.jtBaseNeta.getModel();
                int nFilas = dtm.getRowCount();
                
                int colIdDato = dtm.findColumn(DatosRA2.CAMPO_ID_DATO);
                int colValido = dtm.findColumn(DatosRA2.CAMPO_VALIDO);
                
                for (int i = 0; i < nFilas; i++) {
                    if (((Integer) dtm.getValueAt(i, colIdDato)).equals(idDato)) {
                        validoAct = (Integer) dtm.getValueAt(i, colValido);
                        
                        if (validoAct == 1)
                            valido = 0;
                        else
                            valido = 1;
                        break;
                    }
                }
                
                if (DatosRA2.setDatoValido(this.tipoTabla, this.idAsunto, idDato, valido) > 0) {
                    //Añadimos a la lista temporal de datos modificados con el valor de antes de la modificación
                    this.idValiDatosModif.add(new Integer[]{idDato, validoAct});

                    if (!this.jbDeshacer.isEnabled())
                        this.jbDeshacer.setEnabled(true);

                    //Refrescamos información
                    cargaDatos();
                } else
                    MensajeApp.muestraError(this, null, "Fallo al realizar acción");
            }
        } catch (SQLException e) {
            MensajeApp.muestraError(this, e, "Fallo al consultar la base de datos");
        } finally {
            return valido;
        }
    }
    /*
    private void ignorarDatoXML(Integer idDato, Integer idDatoXML) {
        this.jlPregunta.setText("¿Desea ignorar el dato XML <" + idDatoXML + "> del dato <" + idDato + ">?");

        try {
            if (JOptionPane.showConfirmDialog(this, this.jpObs, "Ignorar dato seleccionado", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                if (DatosRA2.setDatoXMLValido(this.tipoTabla, this.idAsunto, idDato, idDatoXML, false) > 0) {
                    //Añadimos a la lista temporal de datos invalidados
                    this.idDatosXMLInvalidos.add(new Integer[]{idDato, idDatoXML});

                    if (!this.jbDeshacer.isEnabled())
                        this.jbDeshacer.setEnabled(true);

                    //Refrescamos información
                    cargaDatos();
                } else
                    MensajeApp.muestraError(this, null, "Fallo al realizar acción");
            }
        } catch (SQLException e) {
            MensajeApp.muestraError(this, e, "Fallo al consultar la base de datos");
        }
    }
*/
    public void chartMouseMoved(ChartMouseEvent arg0) {

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgTipoCalculoAG;
    private javax.swing.ButtonGroup bgTipoCalculoRF;
    private javax.swing.JSeparator jSeparator;
    private javax.swing.JButton jbAnGraEspFFTAnt;
    private javax.swing.JButton jbAnGraEspFFTSig;
    private javax.swing.JButton jbAnGraEspOCTAnt;
    private javax.swing.JButton jbAnGraEspOCTSig;
    private javax.swing.JButton jbAnGraFFTAnt;
    private javax.swing.JButton jbAnGraFFTSig;
    private javax.swing.JButton jbAnGraOCTAnt;
    private javax.swing.JButton jbAnGraOCTSig;
    private javax.swing.JButton jbDeshacer;
    private javax.swing.JButton jbSig;
    private javax.swing.JLabel jlAjLinAG;
    private javax.swing.JLabel jlAjLinRF;
    private javax.swing.JLabel jlAsunto;
    private javax.swing.JLabel jlBinEstudio;
    private javax.swing.JLabel jlCoefCorrAG;
    private javax.swing.JLabel jlCoefCorrRF;
    private javax.swing.JLabel jlCompAG;
    private javax.swing.JLabel jlCompRF;
    private javax.swing.JLabel jlObs;
    private javax.swing.JLabel jlPregTipoCalculoAG;
    private javax.swing.JLabel jlPregTipoCalculoRF;
    private javax.swing.JLabel jlPregunta;
    private javax.swing.JLabel jlTitAP;
    private javax.swing.JLabel jlTitAjLin;
    private javax.swing.JLabel jlTitAjustes;
    private javax.swing.JLabel jlTitAnalisisGraFFT;
    private javax.swing.JLabel jlTitAnalisisGraOT;
    private javax.swing.JLabel jlTitBaseNeta;
    private javax.swing.JLabel jlTitCompletitud;
    private javax.swing.JLabel jlTotalCompAG;
    private javax.swing.JLabel jlTotalCompRF;
    private javax.swing.JPanel jpAjusteLin;
    private javax.swing.JPanel jpAjustePol;
    private javax.swing.JPanel jpAjustes;
    private javax.swing.JPanel jpAnalisisGraFFT;
    private javax.swing.JPanel jpAnalisisGraOCT;
    private javax.swing.JPanel jpBaseNeta;
    private javax.swing.JPanel jpClave;
    private javax.swing.JPanel jpCompletitud;
    private javax.swing.JPanel jpContAjustes;
    private javax.swing.JPanel jpContGraficaAjLin;
    private javax.swing.JPanel jpContGraficaAjPol;
    private javax.swing.JPanel jpContGraficaAjPolLin;
    private javax.swing.JPanel jpContGraficaFFT;
    private javax.swing.JPanel jpContGraficaOCT;
    private javax.swing.JPanel jpDatos;
    private javax.swing.JPanel jpGraficaAjLin;
    private javax.swing.JPanel jpGraficaAjPol;
    private javax.swing.JPanel jpGraficaAjPolLin;
    private javax.swing.JPanel jpGraficaAnGraFFT;
    private javax.swing.JPanel jpGraficaAnGraOCT;
    private javax.swing.JPanel jpObs;
    private javax.swing.JPanel jpPrincipal;
    private javax.swing.JPanel jpTipoCalculoSPL;
    private javax.swing.JPanel jpTipoCalculoSPLModoFunc;
    private javax.swing.JRadioButton jrbRegLinAG;
    private javax.swing.JRadioButton jrbRegLinRF;
    private javax.swing.JRadioButton jrbRegPolAG;
    private javax.swing.JRadioButton jrbRegPolRF;
    private javax.swing.JScrollPane jspAjLinAG;
    private javax.swing.JScrollPane jspAjLinRF;
    private javax.swing.JScrollPane jspAjustePol;
    private javax.swing.JScrollPane jspAjustes;
    private javax.swing.JScrollPane jspBaseNeta;
    private javax.swing.JScrollPane jspCompAG;
    private javax.swing.JScrollPane jspCompRF;
    private javax.swing.JScrollPane jspObs;
    private general.JTableExport jtAjLinAG;
    private general.JTableExport jtAjLinRF;
    private general.JTableExport jtAjustePol;
    private general.JTableExport jtAjustes;
    private general.JTableExport jtBaseNeta;
    private general.JTableExport jtCompAG;
    private general.JTableExport jtCompRF;
    private javax.swing.JTextArea jtaObs;
    public javax.swing.JTextField jtfAsunto;
    private javax.swing.JTextField jtfBinEstudio;
    private javax.swing.JTextField jtfTotalCompAG;
    private javax.swing.JTextField jtfTotalCompRF;
    private javax.swing.JTabbedPane jtpDatos;
    // End of variables declaration//GEN-END:variables
} 

