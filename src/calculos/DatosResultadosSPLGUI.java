package calculos;

import RA.AerogeneradorRA;
import RA.AsuntoIncert;
import RA.AsuntoRA;
import RA.DatosRA2;
import RA.NormaRA;
import general.Auxiliares;
import general.DatosIncertidumbre;
import general.MensajeApp;
import general.TratDecimales;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.WindowEvent;
import java.awt.font.TextAttribute;
import java.awt.geom.Rectangle2D;
import java.sql.SQLException;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.LegendItem;
import org.jfree.chart.LegendItemCollection;
import org.jfree.chart.LegendItemSource;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.xy.DeviationRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.data.xy.YIntervalSeriesCollection;


public class DatosResultadosSPLGUI extends JDialog {
    private String tipoTabla;
    private Integer idAsunto;
    private Integer idSite;
    private Integer valBinMin;
    private Integer valBinMax;
    private Integer tipoCalculoAG;
    private Integer tipoCalculoRF;
    private ArrayList<Integer[]> modosFunc;
    
    private HashMap<String, double[]> coeficientesPol;
    private HashMap<String, HashMap<Integer, Double[]>> coeficientesBin;
    private HashMap<String, HashMap<Integer, Double[]>> coeficientesModosFunc;
    private Integer idNorma;
    private Boolean esMiniAero;
    private ArrayList<AsuntoIncert> incertidumbres;

    private ArrayList<Double> resIncert_IEC_3_0;
    
    private ArrayList<Integer> modoSalida;
    
    private final String NIVEL_EQ_AG = "LAeq, WT";
    private final String NIVEL_EQ_RF = "LAeq, BN";
    private final String NIVEL_EQ_CO_RF = "LAeq, c";
    private final String NIVEL_APARENTE = "LWA";
    private final String NIVEL_APARENTE_DEC = "LWd";
    private final String NIVEL_INM_60 = "Lp, 60m";
    private final String NIVEL_INM_25 = "Lp, 25m";
    private final String PEND_RUIDO = "SdB";
    
    private final String HAY_TONO = "¿Hay tonos?";
    
    private final String HTML_NIVEL_EQ_AG = "<html>L<sub>Aeq, WT</sub></html>";
    private final String HTML_NIVEL_EQ_RF = "<html>L<sub>Aeq, BN</sub></html>";
    private final String HTML_NIVEL_EQ_CO_RF = "<html>L<sub>Aeq, c</sub></html>";
    private final String HTML_NIVEL_APARENTE = "<html>L<sub>WA</sub></html>";
    private final String HTML_NIVEL_APARENTE_DEC = "<html>L<sub>Wd</sub></html>";
    private final String HTML_NIVEL_INM_60 = "<html>L<sub>p, 60m</sub></html>";
    private final String HTML_NIVEL_INM_25 = "<html>L<sub>p, 25m</sub></html>";
    private final String HTML_PEND_RUIDO = "<html>S<sub>dB</sub></html>";
    
    private final Integer DESDE_BIN_DEF = 1;
    private final Integer HASTA_BIN_DEF = 25;
    private final Double RESOLUCION_BIN_DEF = 0.1;
    private final Double DESDE_DIST_DEF = 0.0;
    private final Double HASTA_DIST_DEF = 100.0;
    private final Double RESOLUCION_DIST_DEF = 0.1;

    
    private ArrayList<Double> valorApModos;
    
    public DatosResultadosSPLGUI(java.awt.Frame parent, String tipoTabla, Integer idAsunto, Integer idSite, Integer valBinMin, Integer valBinMax, ArrayList<Integer[]> modosFunc, HashMap<String, double[]> coeficientesPol, HashMap<String, HashMap<Integer, Double[]>> coeficientesBin, Integer tipoCalculoAG, Integer tipoCalculoRF, HashMap<String, HashMap<Integer, Double[]>> coeficientesModosFunc, ArrayList<Integer> modoSalida, Integer idNorma, Boolean esMiniAero, ArrayList<AsuntoIncert> incertidumbres) {
        super(parent, true);

        initComponents();

        this.tipoTabla = tipoTabla;
        this.idAsunto = idAsunto;
        this.idSite = idSite;
        this.valBinMin = valBinMin;
        this.valBinMax = valBinMax;

        this.coeficientesPol = coeficientesPol;
        this.coeficientesBin = coeficientesBin;
        this.coeficientesModosFunc = coeficientesModosFunc;
        
        this.tipoCalculoAG = tipoCalculoAG;
        this.tipoCalculoRF = tipoCalculoRF;
        
        this.modosFunc = modosFunc;
        this.modoSalida = modoSalida; //variable para control de llamadas entre diálogos
        
        this.idNorma = idNorma;
        this.esMiniAero = esMiniAero;
        this.incertidumbres = incertidumbres;

		this.resIncert_IEC_3_0 = null;
        
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
        jlTitDatos = new javax.swing.JLabel();
        jlSep = new javax.swing.JLabel();
        jspNivelesBin = new javax.swing.JScrollPane();
        jtNivelesBin = new general.JTableExport();
        jtpDatos = new javax.swing.JTabbedPane();
        jpCorregidaRF = new javax.swing.JPanel();
        jlTitCoRF = new javax.swing.JLabel();
        jpContGraficaCoRF = new javax.swing.JPanel();
        jpGraficaCoRF = new javax.swing.JPanel();
        jpAparente = new javax.swing.JPanel();
        jlTitAp = new javax.swing.JLabel();
        jpContGraficaAp = new javax.swing.JPanel();
        jpGraficaAp = new javax.swing.JPanel();
        jpMapaRuido = new javax.swing.JPanel();
        jlTitMapa = new javax.swing.JLabel();
        jspModosFunc = new javax.swing.JScrollPane();
        jtModosFunc = new general.JTableExport();
        jButton1 = new javax.swing.JButton();
        jlDesdeDist = new javax.swing.JLabel();
        jtfDesdeDist = new javax.swing.JTextField();
        jlHastaDist = new javax.swing.JLabel();
        jtfHastaDist = new javax.swing.JTextField();
        jlResDist = new javax.swing.JLabel();
        jtfResDist = new javax.swing.JTextField();
        jlDesdeBin = new javax.swing.JLabel();
        jtfDesdeBin = new javax.swing.JTextField();
        jlHastaBin = new javax.swing.JLabel();
        jtfHastaBin = new javax.swing.JTextField();
        jlResBin = new javax.swing.JLabel();
        jtfResBin = new javax.swing.JTextField();
        jpContGraficaMapa = new javax.swing.JPanel();
        jpGraficaMapa = new javax.swing.JPanel();
        jpMapaInmision = new javax.swing.JPanel();
        jlTitMapaInmision = new javax.swing.JLabel();
        jpContGraficaMapaInmision = new javax.swing.JPanel();
        jpGraficaMapaInmision = new javax.swing.JPanel();
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        jlTitDatos.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlTitDatos.setText("NIVEL DE PRESIÓN SONORA ");

        jlSep.setText("___________________________________________________________________________________________________________________________");

        jtNivelesBin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jtNivelesBin.setToolTipText("SPL Niveles");
        jtNivelesBin.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jtNivelesBin.getTableHeader().setReorderingAllowed(false);
        jspNivelesBin.setViewportView(jtNivelesBin);

        jpCorregidaRF.setBackground(new java.awt.Color(255, 255, 255));

        jlTitCoRF.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jlTitCoRF.setForeground(new java.awt.Color(102, 102, 102));
        jlTitCoRF.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlTitCoRF.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jpContGraficaCoRF.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jpGraficaCoRFLayout = new javax.swing.GroupLayout(jpGraficaCoRF);
        jpGraficaCoRF.setLayout(jpGraficaCoRFLayout);
        jpGraficaCoRFLayout.setHorizontalGroup(
            jpGraficaCoRFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 732, Short.MAX_VALUE)
        );
        jpGraficaCoRFLayout.setVerticalGroup(
            jpGraficaCoRFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 431, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jpContGraficaCoRFLayout = new javax.swing.GroupLayout(jpContGraficaCoRF);
        jpContGraficaCoRF.setLayout(jpContGraficaCoRFLayout);
        jpContGraficaCoRFLayout.setHorizontalGroup(
            jpContGraficaCoRFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jpContGraficaCoRFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jpGraficaCoRF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpContGraficaCoRFLayout.setVerticalGroup(
            jpContGraficaCoRFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 431, Short.MAX_VALUE)
            .addGroup(jpContGraficaCoRFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jpGraficaCoRF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jpCorregidaRFLayout = new javax.swing.GroupLayout(jpCorregidaRF);
        jpCorregidaRF.setLayout(jpCorregidaRFLayout);
        jpCorregidaRFLayout.setHorizontalGroup(
            jpCorregidaRFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpCorregidaRFLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpCorregidaRFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpContGraficaCoRF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlTitCoRF, javax.swing.GroupLayout.DEFAULT_SIZE, 732, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpCorregidaRFLayout.setVerticalGroup(
            jpCorregidaRFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpCorregidaRFLayout.createSequentialGroup()
                .addComponent(jlTitCoRF, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpContGraficaCoRF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jtpDatos.addTab("", jpCorregidaRF);

        jpAparente.setBackground(new java.awt.Color(255, 255, 255));

        jlTitAp.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jlTitAp.setForeground(new java.awt.Color(102, 102, 102));
        jlTitAp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlTitAp.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jpContGraficaAp.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jpGraficaApLayout = new javax.swing.GroupLayout(jpGraficaAp);
        jpGraficaAp.setLayout(jpGraficaApLayout);
        jpGraficaApLayout.setHorizontalGroup(
            jpGraficaApLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jpGraficaApLayout.setVerticalGroup(
            jpGraficaApLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 431, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jpContGraficaApLayout = new javax.swing.GroupLayout(jpContGraficaAp);
        jpContGraficaAp.setLayout(jpContGraficaApLayout);
        jpContGraficaApLayout.setHorizontalGroup(
            jpContGraficaApLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpGraficaAp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpContGraficaApLayout.setVerticalGroup(
            jpContGraficaApLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpGraficaAp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jpAparenteLayout = new javax.swing.GroupLayout(jpAparente);
        jpAparente.setLayout(jpAparenteLayout);
        jpAparenteLayout.setHorizontalGroup(
            jpAparenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpAparenteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpAparenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpContGraficaAp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlTitAp, javax.swing.GroupLayout.DEFAULT_SIZE, 732, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpAparenteLayout.setVerticalGroup(
            jpAparenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpAparenteLayout.createSequentialGroup()
                .addComponent(jlTitAp, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpContGraficaAp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jtpDatos.addTab("", jpAparente);

        jpMapaRuido.setBackground(new java.awt.Color(255, 255, 255));

        jlTitMapa.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jlTitMapa.setForeground(new java.awt.Color(102, 102, 102));
        jlTitMapa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlTitMapa.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jtModosFunc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Desde", "Hasta", "¿Hay tonos?"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtModosFunc.setColumnSelectionAllowed(true);
        jtModosFunc.getTableHeader().setReorderingAllowed(false);
        jspModosFunc.setViewportView(jtModosFunc);
        jtModosFunc.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        if (jtModosFunc.getColumnModel().getColumnCount() > 0) {
            jtModosFunc.getColumnModel().getColumn(2).setHeaderValue(HAY_TONO);
        }

        jButton1.setText("REDIBUJAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                redibujarMapaRuido(evt);
            }
        });

        jlDesdeDist.setText("Desde Distancia:");

        jlHastaDist.setText("Hasta Distancia:");

        jlResDist.setText("Reslución(m):");

        jlDesdeBin.setText("Desde Bin:");

        jlHastaBin.setText("Hasta Bin:");

        jlResBin.setText("Reslución(m/s):");

        jpContGraficaMapa.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jpGraficaMapaLayout = new javax.swing.GroupLayout(jpGraficaMapa);
        jpGraficaMapa.setLayout(jpGraficaMapaLayout);
        jpGraficaMapaLayout.setHorizontalGroup(
            jpGraficaMapaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jpGraficaMapaLayout.setVerticalGroup(
            jpGraficaMapaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 343, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jpContGraficaMapaLayout = new javax.swing.GroupLayout(jpContGraficaMapa);
        jpContGraficaMapa.setLayout(jpContGraficaMapaLayout);
        jpContGraficaMapaLayout.setHorizontalGroup(
            jpContGraficaMapaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpGraficaMapa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpContGraficaMapaLayout.setVerticalGroup(
            jpContGraficaMapaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpGraficaMapa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jpMapaRuidoLayout = new javax.swing.GroupLayout(jpMapaRuido);
        jpMapaRuido.setLayout(jpMapaRuidoLayout);
        jpMapaRuidoLayout.setHorizontalGroup(
            jpMapaRuidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpMapaRuidoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpMapaRuidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jpMapaRuidoLayout.createSequentialGroup()
                        .addComponent(jspModosFunc, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jpMapaRuidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpMapaRuidoLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1))
                            .addGroup(jpMapaRuidoLayout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addGroup(jpMapaRuidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlDesdeBin)
                                    .addComponent(jlDesdeDist))
                                .addGap(4, 4, 4)
                                .addGroup(jpMapaRuidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jtfDesdeBin)
                                    .addComponent(jtfDesdeDist, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jpMapaRuidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlHastaDist)
                                    .addComponent(jlHastaBin))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jpMapaRuidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtfHastaDist, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtfHastaBin, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jpMapaRuidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlResDist)
                                    .addComponent(jlResBin))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jpMapaRuidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtfResBin, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtfResDist, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(63, 63, 63))
                    .addComponent(jlTitMapa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpContGraficaMapa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpMapaRuidoLayout.setVerticalGroup(
            jpMapaRuidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpMapaRuidoLayout.createSequentialGroup()
                .addComponent(jlTitMapa, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(jpMapaRuidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpMapaRuidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jpMapaRuidoLayout.createSequentialGroup()
                            .addGroup(jpMapaRuidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jspModosFunc, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jpMapaRuidoLayout.createSequentialGroup()
                                    .addComponent(jButton1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jpMapaRuidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jlDesdeDist)
                                        .addComponent(jtfDesdeDist, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jpMapaRuidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jlDesdeBin)
                                        .addComponent(jtfDesdeBin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpMapaRuidoLayout.createSequentialGroup()
                            .addGroup(jpMapaRuidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jlHastaDist)
                                .addComponent(jtfHastaDist, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jpMapaRuidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jlHastaBin)
                                .addComponent(jtfHastaBin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(16, 16, 16)))
                    .addGroup(jpMapaRuidoLayout.createSequentialGroup()
                        .addGroup(jpMapaRuidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlResDist)
                            .addComponent(jtfResDist, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpMapaRuidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlResBin)
                            .addComponent(jtfResBin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)))
                .addComponent(jpContGraficaMapa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jtpDatos.addTab("", jpMapaRuido);

        jpMapaInmision.setBackground(new java.awt.Color(255, 255, 255));

        jlTitMapaInmision.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jlTitMapaInmision.setForeground(new java.awt.Color(102, 102, 102));
        jlTitMapaInmision.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlTitMapaInmision.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jpContGraficaMapaInmision.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jpGraficaMapaInmisionLayout = new javax.swing.GroupLayout(jpGraficaMapaInmision);
        jpGraficaMapaInmision.setLayout(jpGraficaMapaInmisionLayout);
        jpGraficaMapaInmisionLayout.setHorizontalGroup(
            jpGraficaMapaInmisionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jpGraficaMapaInmisionLayout.setVerticalGroup(
            jpGraficaMapaInmisionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 431, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jpContGraficaMapaInmisionLayout = new javax.swing.GroupLayout(jpContGraficaMapaInmision);
        jpContGraficaMapaInmision.setLayout(jpContGraficaMapaInmisionLayout);
        jpContGraficaMapaInmisionLayout.setHorizontalGroup(
            jpContGraficaMapaInmisionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpGraficaMapaInmision, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpContGraficaMapaInmisionLayout.setVerticalGroup(
            jpContGraficaMapaInmisionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpGraficaMapaInmision, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jpMapaInmisionLayout = new javax.swing.GroupLayout(jpMapaInmision);
        jpMapaInmision.setLayout(jpMapaInmisionLayout);
        jpMapaInmisionLayout.setHorizontalGroup(
            jpMapaInmisionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpMapaInmisionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpMapaInmisionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpContGraficaMapaInmision, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlTitMapaInmision, javax.swing.GroupLayout.DEFAULT_SIZE, 732, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpMapaInmisionLayout.setVerticalGroup(
            jpMapaInmisionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpMapaInmisionLayout.createSequentialGroup()
                .addComponent(jlTitMapaInmision, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpContGraficaMapaInmision, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jtpDatos.addTab("", jpMapaInmision);

        javax.swing.GroupLayout jpDatosLayout = new javax.swing.GroupLayout(jpDatos);
        jpDatos.setLayout(jpDatosLayout);
        jpDatosLayout.setHorizontalGroup(
            jpDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDatosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jspNivelesBin)
                    .addComponent(jlTitDatos))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.CENTER, jpDatosLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jtpDatos)
                .addGap(10, 10, 10))
            .addGroup(javax.swing.GroupLayout.Alignment.CENTER, jpDatosLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jlSep, javax.swing.GroupLayout.PREFERRED_SIZE, 757, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );
        jpDatosLayout.setVerticalGroup(
            jpDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDatosLayout.createSequentialGroup()
                .addGroup(jpDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpDatosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jlTitDatos)
                        .addGap(11, 11, 11)
                        .addComponent(jspNivelesBin, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpDatosLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jlSep, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jpDatos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpClave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jpPrincipalLayout.createSequentialGroup()
                        .addComponent(jbAnt, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbSig, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                    .addComponent(jbAnt))
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

        setSize(new java.awt.Dimension(817, 749));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

private void rellenarDatos(JProgressBar jpb) throws SQLException, NoSuchFieldException, NumberFormatException {
	ArrayList<ArrayList<Object>> filas = new ArrayList<ArrayList<Object>>();
	ArrayList<Object> filaAG = new ArrayList<Object>();
	ArrayList<Object> filaRF = new ArrayList<Object>();
	ArrayList<Object> filaCoRF = new ArrayList<Object>();
	ArrayList<Object> filaAp = new ArrayList<Object>();
	ArrayList<Object> filaApDec = null, filaInm60 = null, filaInm25 = null, filaSdb = null;
	int valModoIni = -1, valModoFin = -2;
	
	if (this.idNorma.equals(NormaRA.ID_NORMA_BWEA)) {
		//Aparente declarada
		filaApDec = new ArrayList<Object>();
		//Inmisión a 60m
		filaInm60 = new ArrayList<Object>();
		//Inmisión a 25m
		filaInm25 = new ArrayList<Object>();
		//Pendiente de ruido
		filaSdb = new ArrayList<Object>();
		
		filaApDec.add(HTML_NIVEL_APARENTE_DEC);
		filaInm60.add(HTML_NIVEL_INM_60);
		filaInm25.add(HTML_NIVEL_INM_25);
		filaSdb.add(HTML_PEND_RUIDO);
	}
	
	ArrayList<ArrayList<Entry<Double, Integer>>> datosCorregidos;
	ArrayList<Entry<Double, Integer>> datosCorregidosBin;
			
	if (this.idNorma.equals(NormaRA.ID_NORMA_BWEA)) {
		HashMap<String, HashMap<Integer, Double[]>> coeficientes = new HashMap<String, HashMap<Integer, Double[]>>();
		HashMap<Integer, Double[]> coefAG = new HashMap<Integer, Double[]>();
		HashMap<Integer, Double[]> coefRF = new HashMap<Integer, Double[]>();
		
		int nModosFunc = this.modosFunc.size();
		Double velMedia;
		Integer velMediaInf, velMediaSup;
		//Resultados para oberener el valos aparente de cada modo
		this.valorApModos = new ArrayList<Double>();

		for (int i = 0; i < nModosFunc; i++) {
			valModoIni = this.modosFunc.get(i)[0];
			valModoFin = this.modosFunc.get(i)[1];

			coeficientes = new HashMap<String, HashMap<Integer, Double[]>>();
			coefAG = new HashMap<Integer, Double[]>();
			coefRF = new HashMap<Integer, Double[]>();

			if (valModoIni <= 8.0 && valModoFin >= 8.0) {
				velMediaInf = 8;
				velMediaSup = 8;
			} else {
				velMedia = (valModoIni + valModoFin) / 2.0;
				velMediaInf = ((Double) Math.floor(velMedia)).intValue();
				velMediaSup = ((Double) Math.ceil(velMedia)).intValue();
			}
				
			coefAG.put(velMediaInf, this.coeficientesModosFunc.get(Auxiliares.PREF_DATOS_AG).get(valModoIni));
			coefRF.put(velMediaInf, this.coeficientesModosFunc.get(Auxiliares.PREF_DATOS_RF).get(valModoIni));

			if (!velMediaInf.equals(velMediaSup)) {
				coefAG.put(velMediaSup, this.coeficientesModosFunc.get(Auxiliares.PREF_DATOS_AG).get(valModoIni));
				coefRF.put(velMediaSup, this.coeficientesModosFunc.get(Auxiliares.PREF_DATOS_RF).get(valModoIni));
			}

			coeficientes.put(Auxiliares.PREF_DATOS_AG, coefAG);
			coeficientes.put(Auxiliares.PREF_DATOS_RF, coefRF);

			datosCorregidos = DatosRA2.getResultadosSPLCorregidoAparente(this.idAsunto, this.tipoCalculoAG, this.tipoCalculoRF, this.coeficientesPol, coeficientes, velMediaInf, velMediaSup);

			if (datosCorregidos.size() > 1) {
				if (datosCorregidos.get(0).get(3).getValue().equals(DatosRA2.CORREGIDO_RF_OK) && datosCorregidos.get(1).get(3).getValue().equals(DatosRA2.CORREGIDO_RF_OK))
					this.valorApModos.add((datosCorregidos.get(0).get(3).getKey() + datosCorregidos.get(1).get(3).getKey()) / 2.0);
			} else {
				if (datosCorregidos.get(0).get(3).getValue().equals(DatosRA2.CORREGIDO_RF_OK))
					this.valorApModos.add(datosCorregidos.get(0).get(3).getKey());
			}
		}
				
		//Resultados para el bin seleccionado
		for (int i = 0; i < nModosFunc; i++) {
			valModoIni = this.modosFunc.get(i)[0];
			valModoFin = this.modosFunc.get(i)[1];
			if (valModoIni > this.valBinMin || valModoFin < this.valBinMax)
				continue;
			
			coefAG.put(this.valBinMin, this.coeficientesModosFunc.get(Auxiliares.PREF_DATOS_AG).get(valModoIni));
			coeficientes.put(Auxiliares.PREF_DATOS_AG, coefAG);
			coefRF.put(this.valBinMin, this.coeficientesModosFunc.get(Auxiliares.PREF_DATOS_RF).get(valModoIni));
			coeficientes.put(Auxiliares.PREF_DATOS_RF, coefRF);
			
			break;
		}
	
		datosCorregidos = DatosRA2.getResultadosSPLCorregidoAparente(this.idAsunto, this.tipoCalculoAG, this.tipoCalculoRF, this.coeficientesPol, coeficientes, this.valBinMin, this.valBinMax);
	} else if (this.idNorma.equals(NormaRA.ID_NORMA_IEC_3_0)) {
		this.resIncert_IEC_3_0 = new ArrayList<Double>();
			datosCorregidos = DatosRA2.getResultadosSPLCorregidoAparente_IEC3(this.tipoTabla, this.idAsunto, this.idSite, this.valBinMin, this.valBinMax, this.incertidumbres, this.resIncert_IEC_3_0);
	} else
		datosCorregidos = DatosRA2.getResultadosSPLCorregidoAparente(this.idAsunto, this.tipoCalculoAG, this.tipoCalculoRF, this.coeficientesPol, this.coeficientesBin, this.valBinMin, this.valBinMax);


	Auxiliares.incPorcentajeProgress(jpb, 0.4);

	//Establecemos el modelo de la tabla
	this.jtNivelesBin.setModel(new javax.swing.table.DefaultTableModel(
		new Object [][] {

		},
		new String [] {
			"Presión"
		}) {
	
		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return false;
		}
	});
	
	DefaultTableModel dtm = (DefaultTableModel) this.jtNivelesBin.getModel();

	filaAG.add(HTML_NIVEL_EQ_AG);
	filaRF.add(HTML_NIVEL_EQ_RF);
	filaCoRF.add(HTML_NIVEL_EQ_CO_RF);
	filaAp.add(HTML_NIVEL_APARENTE);
	
	Entry<Double, Integer> entry;
	Double valor, valorAp, valorApDec;
	Integer tipo;

	for (int i = 0; i < (this.valBinMax - this.valBinMin + 1); i++) {
		datosCorregidosBin = datosCorregidos.get(i);
	
		dtm.addColumn(i + this.valBinMin);
		
		//Nivel Aero
		entry = datosCorregidosBin.get(0);
		if (entry != null && entry.getKey() != null) {
			valor = TratDecimales.round(entry.getKey(), TratDecimales.DEC_VARIABLE_RA);
			
			filaAG.add(valor);
		} else
			filaAG.add(null);
		
		//Nivel RF
		entry = datosCorregidosBin.get(1);
		if (entry != null && entry.getKey() != null) {
			valor = TratDecimales.round(entry.getKey(), TratDecimales.DEC_VARIABLE_RA);
			
			filaRF.add(valor);
		} else
			filaRF.add(null);

		//SPL Corregido
		entry = datosCorregidosBin.get(2);
		if (entry != null && entry.getKey() != null) {
			valor = TratDecimales.round(entry.getKey(), TratDecimales.DEC_VARIABLE_RA);
			tipo = entry.getValue();
			
			switch (tipo) {
				case DatosRA2.CORREGIDO_RF_OK:
					filaCoRF.add(valor);
					break;
				case DatosRA2.CORREGIDO_RF_AVISO:
					filaCoRF.add(valor + DatosRA2.MARCA_ASTERISCO);
					break;
				case DatosRA2.CORREGIDO_RF_AVISO_IEC3:
					filaCoRF.add(DatosRA2.MARCA_INI_BRACKETS + valor + DatosRA2.MARCA_FIN_BRACKETS);
					break;
				case DatosRA2.CORREGIDO_RF_ERROR:
					filaCoRF.add(DatosRA2.TXT_CORREGIDO_RF_ERROR);
					break;
			}
		} else
			filaCoRF.add(null);
		
		//SPL Aparente
		entry = datosCorregidosBin.get(3);
		valorAp = null;
		
		if (entry != null) {
			valor = entry.getKey();
			tipo = entry.getValue();
			
			switch (tipo) {
				case DatosRA2.CORREGIDO_RF_OK:
					valorAp = valor;
					break;
				case DatosRA2.CORREGIDO_RF_AVISO:
				case DatosRA2.CORREGIDO_RF_ERROR:
					valorAp = null;
					break;
			}
		}
		if (valorAp != null)
			filaAp.add(valorAp);
		else
			filaAp.add(DatosRA2.TXT_CORREGIDO_RF_ERROR);
		
		if (this.idNorma.equals(NormaRA.ID_NORMA_BWEA)) {
			if (valorAp != null) {
				//Pendiente ruido
				filaSdb.add(DatosRA2.getPendienteMapaRuido(this.idAsunto, this.tipoCalculoAG, this.tipoCalculoRF, this.coeficientesPol, this.coeficientesModosFunc, valModoIni, valModoFin));
				
				//Aparente declarada
				valorApDec = DatosRA2.getNivelAparenteDeclarado(this.tipoTabla, this.idAsunto, this.idSite, valModoIni, valModoFin, valorAp);
			  
				filaApDec.add(valorApDec);
				//Sacamos los niveles de inmsión sin penalty pero añadimo marca
				filaInm60.add(DatosRA2.getNivelInmision60(valorApDec, 0.0) + DatosRA2.MARCA_PENALIZACION);
				filaInm25.add(DatosRA2.getNivelInmision25(valorApDec, 0.0) + DatosRA2.MARCA_PENALIZACION);
			} else {
				filaSdb.add(null);
				filaApDec.add(null);
				filaInm60.add(null);
				filaInm25.add(null);
			}
		}

		Auxiliares.incPorcentajeProgress(jpb, 0.1/(this.valBinMax - this.valBinMin + 1));
	}

	filas.add(filaAG);
	filas.add(filaRF);
	filas.add(filaCoRF);
	filas.add(filaAp);
	if (this.idNorma.equals(NormaRA.ID_NORMA_BWEA)) {
		filas.add(filaApDec);
		filas.add(filaInm60);
		filas.add(filaInm25);
		filas.add(filaSdb);
	}

	//Añadimos las filas a la tabla
	int nDatos = filas.size();
	for (int i = 0; i < nDatos; i++) {
		dtm.addRow(filas.get(i).toArray());
	}
	
	//Seleccionamos la fila de corregida por RF para posicionar el scroll
	Auxiliares.muestraFila(this.jtNivelesBin, 2);
	
	Auxiliares.centrarTabla(this.jtNivelesBin);
	
	//Creamos las gráficas
//            asignaPanelGrafica(this.jpGraficaCoRF, crearGraficaBarras(NIVEL_EQ_CO_RF, crearDatasetBarras(NIVEL_EQ_CO_RF)));
//            asignaPanelGrafica(this.jpGraficaAp, crearGraficaBarras(NIVEL_APARENTE, crearDatasetBarras(NIVEL_APARENTE)));
	Auxiliares.asignaPanelGrafica(this, this.jpGraficaCoRF, crearGrafica(NIVEL_EQ_CO_RF, crearDataset(HTML_NIVEL_EQ_CO_RF)), false, null);
	Auxiliares.asignaPanelGrafica(this, this.jpGraficaAp, crearGrafica(NIVEL_APARENTE, crearDataset(HTML_NIVEL_APARENTE)), false, null);

	//Una vez renderizados los gráficos, hacemos los ajustes
	this.jtpDatos.setSelectedComponent(this.jpContGraficaAp.getParent());
	Auxiliares.escalarGrafica(this, this.jpGraficaAp, 2.0);
	this.jtpDatos.setSelectedComponent(this.jpContGraficaCoRF.getParent());
	Auxiliares.escalarGrafica(this, this.jpGraficaCoRF, 2.0);
	
	//Mapa de ruido
	if (this.idNorma.equals(NormaRA.ID_NORMA_BWEA)) {
		DefaultTableModel dtmModos = (DefaultTableModel) this.jtModosFunc.getModel();
		int nModosFunc = this.modosFunc != null ? this.modosFunc.size() : 0;

		for (int i = 0; i < nModosFunc; i++) {
			dtmModos.addRow(new Object[]{this.modosFunc.get(i)[0], this.modosFunc.get(i)[1], false});
		}
		
		this.jtfDesdeBin.setText(DESDE_BIN_DEF.toString());
		this.jtfHastaBin.setText(HASTA_BIN_DEF.toString());
		this.jtfResBin.setText(RESOLUCION_BIN_DEF.toString());
		this.jtfDesdeDist.setText(DESDE_DIST_DEF.toString());
		this.jtfHastaDist.setText(HASTA_DIST_DEF.toString());
		this.jtfResDist.setText(RESOLUCION_DIST_DEF.toString());

		redibujarMapaRuido(null);
	}

//Mapa de inmisión
	if (this.idNorma.equals(NormaRA.ID_NORMA_IEC_3_0) && this.esMiniAero) {
		Auxiliares.asignaPanelGrafica(this, this.jpGraficaMapaInmision, crearMapaInmision(datosCorregidos), false, null);
	}
}
    
private JFreeChart crearMapaRuido() throws NumberFormatException, SQLException, NoSuchFieldException {
	DefaultTableModel dtmModos = (DefaultTableModel) this.jtModosFunc.getModel();
	int nModosFunc = dtmModos.getRowCount();
	ArrayList<Boolean> tonosModosFunc = new ArrayList<Boolean>();
	Integer desdeBin, hastaBin;
	Double desdeDist, hastaDist, resolucionDist, resolucionBin;
	
	int colTonos = dtmModos.findColumn(HAY_TONO);

	for (int i = 0; i < nModosFunc; i++) {
		tonosModosFunc.add((Boolean) dtmModos.getValueAt(i, colTonos));
	}
	
	desdeBin = Integer.parseInt(this.jtfDesdeBin.getText());
	hastaBin = Integer.parseInt(this.jtfHastaBin.getText());
	resolucionBin = Double.parseDouble(this.jtfResBin.getText());
	desdeDist = Double.parseDouble(this.jtfDesdeDist.getText());
	hastaDist = Double.parseDouble(this.jtfHastaDist.getText());
	resolucionDist = Double.parseDouble(this.jtfResDist.getText());
		  
	YIntervalSeriesCollection dataset = DatosRA2.getDatasetMapaRuido(this.tipoTabla, this.idAsunto, this.idSite, this.tipoCalculoAG, this.tipoCalculoRF, this.coeficientesPol, this.coeficientesModosFunc, desdeBin, hastaBin, resolucionBin, desdeDist, hastaDist, resolucionDist, this.valorApModos, this.modosFunc, tonosModosFunc);

	//Creamos la gráfica
	JFreeChart chart = ChartFactory.createScatterPlot(
			"",
			"Velocidad de viento en el buje (m/s)",
			"Distancia al buje (m)",
			dataset,
			PlotOrientation.HORIZONTAL,
			true,
			true,
			false);
			
	TextTitle texto = chart.getTitle();
	texto.setFont(new Font("Arial", Font.BOLD, 13));
	chart.setBackgroundPaint(Color.WHITE);
	XYPlot plot = (XYPlot) chart.getPlot();
	
	XYItemRenderer rendLeyenda = new XYLineAndShapeRenderer(false, true);
	Rectangle2D.Double r = new Rectangle2D.Double(-2, -2, 4, 4);
	rendLeyenda.setSeriesShape(0, r);
	DeviationRenderer renderer = new DeviationRenderer(true, false);
	renderer.setAlpha(1.0F);
	
	int nSeries = dataset.getSeriesCount();
	String seriesKey;
	
	Color color = null;
	
	for (int i = 0; i < nSeries; i++) {
		seriesKey = (String) dataset.getSeriesKey(i);
		
		if (seriesKey.contains("> 45 dB(A)")) {
			color = Color.RED;
		} else if (seriesKey.contains("40 - 45 dB(A)")) {
			color = Color.YELLOW;
		} else if (seriesKey.contains("< 40 dB(A)")) {
			color = Color.GREEN;
		} else if (seriesKey.contains("Modo no declarado")) {
			color = Color.GRAY;
		}
		
		renderer.setSeriesPaint(i, color);
		renderer.setSeriesFillPaint(i, color);
		renderer.setSeriesStroke(i, new BasicStroke(0F, 1, 1));
	}
	plot.setRenderer(renderer);
	
	LegendTitle lt = chart.getLegend();
	LegendItemCollection lic = lt.getSources()[0].getLegendItems();
	final LegendItemCollection licNuevo = new LegendItemCollection();
	LegendItem li;
	int nItems = lic.getItemCount();
	
	for (int i = 0; i < nItems; i++) {
		li = lic.get(i);
		
		li.setLineVisible(false);
		li.setShapeVisible(true);
		li.setShape(r);
		li.setOutlineStroke(new BasicStroke(2F, 1, 1));
		
		licNuevo.add(li);
	}
	
	LegendItemSource lis = new LegendItemSource() {

		public LegendItemCollection getLegendItems() {
			return licNuevo;
		}
	};
	
	lt.setSources(new LegendItemSource[]{lis});
	
	return chart;
}

private JFreeChart crearMapaInmision(ArrayList<ArrayList<Entry<Double, Integer>>> datosCorregidos) throws NumberFormatException, SQLException, NoSuchFieldException {
ArrayList<Double> distNivelInf = new ArrayList<Double>();
	YIntervalSeriesCollection dataset = DatosRA2.getDatasetMapaRuido_IEC3(this.idAsunto, datosCorregidos, this.valBinMin, this.valBinMax, 5, 35, distNivelInf);
	
	//Creamos la gráfica
	JFreeChart chart = ChartFactory.createScatterPlot(
			"",
			"Distancia al buje (m)",
			"Velocidad de viento (m/s)",
			dataset,
			PlotOrientation.VERTICAL,
			true,
			true,
			false);
			
	TextTitle texto = chart.getTitle();
	texto.setFont(new Font("Arial", Font.BOLD, 13));
	chart.setBackgroundPaint(Color.WHITE);
	XYPlot plot = (XYPlot) chart.getPlot();
	
	XYItemRenderer rendLeyenda = new XYLineAndShapeRenderer(false, true);
	Rectangle2D.Double r = new Rectangle2D.Double(-2, -2, 4, 4);
	rendLeyenda.setSeriesShape(0, r);
	DeviationRenderer renderer = new DeviationRenderer(true, false);
	renderer.setAlpha(1.0F);
	
	int nSeries = dataset.getSeriesCount();

	for (int i = 0; i < nSeries; i++) {
		plot.getRendererCount();
		renderer.setSeriesPaint(i, plot.getRenderer(0).getItemPaint(i, 0));
		renderer.setSeriesFillPaint(i, plot.getRenderer(0).getItemPaint(i, 0));
		renderer.setSeriesStroke(i, new BasicStroke(0F, 1, 1));
	}

	plot.setRenderer(renderer);
	
	LegendTitle lt = chart.getLegend();
	LegendItemCollection lic = lt.getSources()[0].getLegendItems();
	final LegendItemCollection licNuevo = new LegendItemCollection();
	LegendItem li;
	int nItems = lic.getItemCount();
	
	for (int i = 0; i < nItems; i++) {
		li = lic.get(i);
		
		li.setLineVisible(false);
		li.setShapeVisible(true);
		li.setShape(r);
		li.setOutlineStroke(new BasicStroke(2F, 1, 1));
		
		licNuevo.add(li);
	}
	
	LegendItemSource lis = new LegendItemSource() {

		public LegendItemCollection getLegendItems() {
			return licNuevo;
		}
	};
	
	lt.setSources(new LegendItemSource[]{lis});

	//Solo mostramos el gráfico hasta una posición representativa de distNivelInf
	NumberAxis domAxis = (NumberAxis) plot.getDomainAxis();
	int nDistNivelInf = distNivelInf.size();

	AsuntoRA asunto = AsuntoRA.getAsuntoPorId(this.idAsunto);
	AerogeneradorRA aero = AerogeneradorRA.getAeroPorId(asunto.getIdAero());
	Double hB = aero.getHB();
	Double distNivelInfRep = nDistNivelInf != 0 ? distNivelInf.get(nDistNivelInf - 1) : domAxis.getUpperBound();
	int pasosRep = 0;

	for (int i = 0; i < nDistNivelInf; i++) {
		if (distNivelInf.get(i) != null && distNivelInf.get(i) > hB) {
		pasosRep++;
		distNivelInfRep = distNivelInf.get(i);

		if (pasosRep > 2)
			break;
		}
	}

	domAxis.setRange(hB, distNivelInfRep);
	
	return chart;
}

private JFreeChart crearGrafica(String tipoNivel, XYDataset dataset) {
	JFreeChart chart = ChartFactory.createScatterPlot(
			"",
			"Velocidad de referencia Vs (m/s)",
			"Presión sonora equivalente (dBA)",
			dataset,
			PlotOrientation.VERTICAL,
			true,
			true,
			false);
			
	TextTitle texto = chart.getTitle();
	texto.setFont(new Font("Arial", Font.BOLD, 13));
	chart.setBackgroundPaint(Color.WHITE);
	XYPlot plot = (XYPlot) chart.getPlot();
	
	Rectangle2D.Double r = new Rectangle2D.Double(-2, -2, 4, 4);

	XYItemRenderer rendNivel = new XYLineAndShapeRenderer(false, true);
	rendNivel.setSeriesShape(0, r);
	
   if (tipoNivel.compareTo(NIVEL_EQ_CO_RF) == 0)
		rendNivel.setSeriesPaint(0, Auxiliares.COLORES_SERIE0.get(0));
	else
		rendNivel.setSeriesPaint(0, Auxiliares.COLORES_SERIE1.get(0));
	
	rendNivel.setBaseToolTipGenerator(new StandardXYToolTipGenerator());
	plot.setRenderer(0, rendNivel); 

/* Código de prueba para cadenas con atributos en leyendas
	AttributedString serieKey = new AttributedString("ASDJLASDQWE");
serieKey.addAttribute(TextAttribute.SUPERSCRIPT, TextAttribute.SUPERSCRIPT_SUB, 3, 4);

LegendItemCollection lic = plot.getLegendItems();
LegendItemCollection licN = new LegendItemCollection();
Iterator it = lic.iterator();
LegendItem li, liN;
while (it.hasNext()) {
	li = (LegendItem) it.next();
	liN = new LegendItem((AttributedString) serieKey, li.getDescription(), li.getToolTipText(), li.getURLText(), li.isShapeVisible(), li.getShape(), li.isShapeFilled(), li.getFillPaint(), li.isShapeOutlineVisible(), li.getOutlinePaint(), li.getOutlineStroke(), li.isLineVisible(), li.getLine(), li.getLineStroke(), li.getLinePaint());

	licN.add(liN);
}

plot.setFixedLegendItems(licN);
*/

	AttributedString as = new AttributedString(tipoNivel + " (dBA)");

	as.addAttribute(TextAttribute.SUPERSCRIPT, TextAttribute.SUPERSCRIPT_SUB, 1, tipoNivel.length());
	as.addAttribute(TextAttribute.SUPERSCRIPT, TextAttribute.SUPERSCRIPT_SUB, tipoNivel.length() + 4, tipoNivel.length() + 5);

	ValueAxis ranAxis = plot.getRangeAxis();

	Font f = ranAxis.getLabelFont();

	as.addAttribute(TextAttribute.SIZE, f.getSize());
	as.addAttribute(TextAttribute.FAMILY, f.getFamily());
	if (f.isBold())
		as.addAttribute(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD);

	ranAxis.setAttributedLabel(as);
 
	return chart;
}
    
private XYDataset crearDataset(String tipoNivel) {
	XYSeriesCollection dataset = new XYSeriesCollection();
	XYSeries serie = new XYSeries("Nivel " + tipoNivel.replaceAll("<[^>]*>", ""), false);
	Object objeto;
	Double valor;

	int nDatos = this.jtNivelesBin.getRowCount();
	int nCols = this.jtNivelesBin.getColumnCount();

	for (int i = 0; i < nDatos; i++) {
		if (tipoNivel.compareTo((String) this.jtNivelesBin.getValueAt(i, 0)) == 0) { //Es la fila correcta
			for (int j = 1; j < nCols; j++) {
				objeto = this.jtNivelesBin.getValueAt(i, j);
				
				if (objeto == null)
					continue;
				
				if (objeto instanceof Number)
					valor = (Double) objeto;
				else {
					if (((String) objeto).contentEquals(DatosRA2.TXT_CORREGIDO_RF_ERROR))
						continue;
					
					valor = Double.valueOf(((String) objeto).replaceAll("[^0-9.]+", ""));
					//Qué habría que hacer??
					//continue;
					//valor = null;
				}
					
				serie.add(j + this.valBinMin - 1, valor);
			}
			break; //No necesitamos seguir mirando filas
		}
	}
	
	dataset.addSeries(serie);

	return dataset;
}
    
private JFreeChart crearGraficaBarras(String tipoNivel, CategoryDataset dataset) {
	JFreeChart chart = ChartFactory.createBarChart(
	"",
	"Velocidad de referencia Vs (m/s)",
	"Presión sonora equivalente (dBA)",
	dataset,
	PlotOrientation.HORIZONTAL,
	true,
	true,
	false);

	TextTitle texto = chart.getTitle();
	texto.setFont(new Font("Arial", Font.BOLD, 13));
	chart.setBackgroundPaint(Color.WHITE);
	CategoryPlot plot = (CategoryPlot) chart.getPlot();

	final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
	configureRangeAxis(rangeAxis);
	
	CategoryItemRenderer rendNivel = new BarRenderer(); 
	if (tipoNivel.compareTo(NIVEL_EQ_CO_RF) == 0)
		rendNivel.setSeriesPaint(0, Auxiliares.COLORES_SERIE0.get(0));
	else
		rendNivel.setSeriesPaint(0, Auxiliares.COLORES_SERIE1.get(0));

	rendNivel.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());
	plot.setRenderer(0, rendNivel); 
 
	return chart;
}
    
// configuramos el eje y de la gráfica (números enteros de dos en dos y rango entre 120 y 135)
private static void configureRangeAxis (NumberAxis rangeAxis) {
    rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
    rangeAxis.setTickUnit(new NumberTickUnit(10));
//    rangeAxis.setRange(0, 135);
}

// configuramos el eje X de la gráfica (se muestran números enteros y de uno en uno)
private static void configureDomainAxis (NumberAxis domainAxis) {
    domainAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
    domainAxis.setTickUnit(new NumberTickUnit(1));
//    domainAxis.setRange(0, 10);
}

private CategoryDataset crearDatasetBarras(String tipoNivel) {
	DefaultCategoryDataset dataset = new DefaultCategoryDataset();

	int nDatos = this.jtNivelesBin.getRowCount();
	int nCols = this.jtNivelesBin.getColumnCount();

	for (int i = 0; i < nDatos; i++) {
		if (tipoNivel.compareTo((String) this.jtNivelesBin.getValueAt(i, 0)) == 0) { //Es la fila correcta
			for (int j = 1; j < nCols; j++) {
				dataset.addValue(((Double) this.jtNivelesBin.getValueAt(i, j)).doubleValue(), "Nivel " + tipoNivel, String.valueOf(j + this.valBinMin - 1));
			}
			break; //No necesitamos seguir mirando filas
		}
	}

	return dataset;
}

private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
    boolean error = false;
    JProgressBar jpb = null;
    
    try {
        //Establecemos el título
        this.setTitle("SEGUIMIENTO / " + this.tipoTabla.toUpperCase() + " - RESULTADOS");

        //Establecemos las pestañas de JTabbedPane
        Auxiliares.setTitulosJTabbedPane(this.jtpDatos, new String[]{"CORREGIDA POR RUIDO DE FONDO", "APARENTE", "MAPA DE RUIDO", "MAPA DE INMISIÓN"});
        
        //Eliminamos las pestañas que no se corresponden con el tipo de análisis a realizar
        if (!this.idNorma.equals(NormaRA.ID_NORMA_BWEA))
            this.jtpDatos.remove(this.jpMapaRuido);

	 if (!this.idNorma.equals(NormaRA.ID_NORMA_IEC_3_0) || !this.esMiniAero)
            this.jtpDatos.remove(this.jpMapaInmision);
        
        //Maximizamos las pestañas
        Auxiliares.maximizaTitulosJTabbedPane(this.jtpDatos);
        
        this.jlTitCoRF.setText(HTML_NIVEL_EQ_CO_RF);
        this.jlTitAp.setText(HTML_NIVEL_APARENTE);

        //Rellenamos los datos
        Auxiliares.bloqueaDialog(this, true);
        jpb = Auxiliares.muestraProgress(this, 100 * 10000);
        
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
    } finally {
        if (error) {
            if (jpb != null)
                Auxiliares.ocultaProgress(jpb);
            
            Auxiliares.bloqueaDialog(this, false);
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
    DatosIncertidumbre datoIncertidumbre;
    
    try {
		if (this.resIncert_IEC_3_0 != null) {
			for (int i = this.valBinMin; i <= this.valBinMax; i++) {
				datoBinIncertidumbre = new LinkedHashMap<Object, DatosIncertidumbre>();

				datoIncertidumbre = new DatosIncertidumbre(this.resIncert_IEC_3_0.get(i - this.valBinMin));

				datoBinIncertidumbre.put("Incertidumbre", datoIncertidumbre);
				datosIncertidumbre.put(i, datoBinIncertidumbre);
			}
		} else {
			for (int i = this.valBinMin; i <= this.valBinMax; i++) {
				datoBinIncertidumbre = new LinkedHashMap<Object, DatosIncertidumbre>();

				datoIncertidumbre = DatosRA2.getIncertidumbreSPL(this.idNorma, this.incertidumbres, this.tipoTabla, this.idAsunto, this.idSite, i, this.tipoCalculoAG, this.coeficientesPol, this.coeficientesBin);

				datoBinIncertidumbre.put("Incertidumbre", datoIncertidumbre);
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

private void redibujarMapaRuido(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_redibujarMapaRuido
    try {
        Auxiliares.asignaPanelGrafica(this, this.jpGraficaMapa, crearMapaRuido(), false, null);
        this.update(this.getGraphics());
    } catch (SQLException e) {//GEN-LAST:event_redibujarMapaRuido
        MensajeApp.muestraError(this, e, "Fallo al consultar la base de datos");
    } catch (NoSuchFieldException e) {
        MensajeApp.muestraError(this, e, "Fallo añadiendo campo");
    } catch (NumberFormatException e) {
        MensajeApp.muestraError(this, e, "Fallo tratando número");
    }
}


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jbAnt;
    private javax.swing.JButton jbSig;
    private javax.swing.JLabel jlAsunto;
    private javax.swing.JLabel jlDesdeBin;
    private javax.swing.JLabel jlDesdeDist;
    private javax.swing.JLabel jlHastaBin;
    private javax.swing.JLabel jlHastaDist;
    private javax.swing.JLabel jlResBin;
    private javax.swing.JLabel jlResDist;
    private javax.swing.JLabel jlSep;
    private javax.swing.JLabel jlTitAp;
    private javax.swing.JLabel jlTitCoRF;
    private javax.swing.JLabel jlTitDatos;
    private javax.swing.JLabel jlTitMapa;
    private javax.swing.JLabel jlTitMapaInmision;
    private javax.swing.JPanel jpAparente;
    private javax.swing.JPanel jpClave;
    private javax.swing.JPanel jpContGraficaAp;
    private javax.swing.JPanel jpContGraficaCoRF;
    private javax.swing.JPanel jpContGraficaMapa;
    private javax.swing.JPanel jpContGraficaMapaInmision;
    private javax.swing.JPanel jpCorregidaRF;
    private javax.swing.JPanel jpDatos;
    private javax.swing.JPanel jpGraficaAp;
    private javax.swing.JPanel jpGraficaCoRF;
    private javax.swing.JPanel jpGraficaMapa;
    private javax.swing.JPanel jpGraficaMapaInmision;
    private javax.swing.JPanel jpMapaInmision;
    private javax.swing.JPanel jpMapaRuido;
    private javax.swing.JPanel jpPrincipal;
    private javax.swing.JScrollPane jspModosFunc;
    private javax.swing.JScrollPane jspNivelesBin;
    private general.JTableExport jtModosFunc;
    private general.JTableExport jtNivelesBin;
    public javax.swing.JTextField jtfAsunto;
    private javax.swing.JTextField jtfDesdeBin;
    private javax.swing.JTextField jtfDesdeDist;
    private javax.swing.JTextField jtfHastaBin;
    private javax.swing.JTextField jtfHastaDist;
    private javax.swing.JTextField jtfResBin;
    private javax.swing.JTextField jtfResDist;
    private javax.swing.JTabbedPane jtpDatos;
    // End of variables declaration//GEN-END:variables
}