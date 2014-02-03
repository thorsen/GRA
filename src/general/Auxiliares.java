package general;

import RA.AsuntoRA;
import RA.ConfiguracionRA2;
import RA.NormaRA;
import RA.PosicionRA;
import RA.SiteRA;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.XYPlot;
import userinterfaces.GRA;
/**
 *
 * @author Victor Fernandez
 */
public class Auxiliares {
    public static final String TIPO_SPL = "SPL";
    public static final String TIPO_OCT = "OCT";
    public static final String TIPO_FFT = "FFT";
    public static final String TIPO_GEN = "GEN";
    public static final String TIPO_IEC_3_0 = "IEC3";
    
    public static final String CANCEL_PROCESS = "Se cancelará el proceso. ¿Desea continuar?";
    public static final String PROCESS_CANCELED = "Proceso cancelado por el usuario";
    public static final String PROCESS_OK = "Proceso finalizado correctamente";
    
    public static final int MODO_UNDEFINED = -99;
    public static final int MODO_CANCEL = -2;
    public static final int MODO_ERROR = -1;
    public static final int MODO_OK = 0;
    public static final int MODO_ANT = 1;
    
    public static final String RUTA_RAIZ = "Z:";
    public static final String RUTA_DIR = "\\PRIVADA\\CALIDAD\\ASUNTOS";
    
    public static final ImageIcon ICONO_CAPTURA = new ImageIcon(Toolkit.getDefaultToolkit().getImage(RA.Global.RUTA_IMAGENES + "camera.png"));
    public static final ImageIcon ICONO_PREV = new ImageIcon(Toolkit.getDefaultToolkit().getImage(RA.Global.RUTA_IMAGENES + "resultset_previous.png"));
    public static final ImageIcon ICONO_NEXT = new ImageIcon(Toolkit.getDefaultToolkit().getImage(RA.Global.RUTA_IMAGENES + "resultset_next.png"));
    
    public static final String TXT_SERIE_BC = "BC ";
    public static final String TXT_SERIE_ESPECTRO = "Esp. ";
    public static final String TXT_SERIE_BIN = " m/s";
    public static final String TXT_SERIE_SEP = " - ";
    public static final String TXT_SERIE_TONO = "Tono";
    public static final String TXT_SERIE_ENMASCARAMIENTO = "Enmascaramiento";
    public static final String TXT_SERIE_NADA = "Nada";
    
    public static final String PREF_DATOS_AG = "AG";
    public static final String PREF_DATOS_RF = "RF";
    public static final String PREF_DATOS_IGNORADOS = "Ignorados";
    
    //Colores para gráficos
    public static ArrayList<Color> COLORES_SERIE0;
    public static ArrayList<Color> COLORES_SERIE1;
    
    private static final Color COLOR_SERIE0_DEF = new Color(63, 63, 127);
    private static final Color COLOR_SERIE1_DEF = new Color(127, 63, 63);
    
    //Colores para JTabbedPane
    public static final Color JTP_TIT_TXT = new Color(255, 255, 255);
    public static final Color JTP_TIT_FONDO = new Color(102, 102, 102);
    
    public static final Integer NUM_D_FIN_PERTURBACION_RA = 10; //La pertubación de sector por un obstáculo desaparece a 10D
    
    public static String dameRutaDefecto(Component c) {
        LoginRA login = obtenerLogin(c);
        
        return RUTA_RAIZ + "\\" + login.getUsuario() + RUTA_DIR;
    }
    
    public static Component getParentSup(Component c) {
        Container cont, contAux;
        
        cont = c.getParent();
        do {
            contAux = cont;
            cont = contAux.getParent();
        } while (cont != null);
        
        cont = contAux;
        
        return cont;
    }
    
    public static LoginRA obtenerLogin(Component c) {
        return ((GRA) getParentSup(c)).getLogin();
    }
    
    //Función para cargar en un combo los asuntos
    public static void cargaAsuntosGen(JComboBox jcb, Boolean incluirVacio, Integer idAsunto, String codigo, String nombre, Integer pE, Integer idNorma, Integer periodicidad, Boolean clave, Integer idCliente,  Integer idAero, Boolean abierto, String posicion, String nCorto, Character idioma, Long finiPC, Long finiSC, Long finiME1, Long finiME10, Integer tipo) throws SQLException, NoSuchFieldException {
        LoginRA login = obtenerLogin(jcb);
        
        ArrayList<AsuntoRA> asuntos = new ArrayList<AsuntoRA>();
        
        Integer idResponsable = null;
        
        if (login.getRol() != 1)  // No es admin
            idResponsable = login.getIdResponsable();
        
        asuntos = AsuntoRA.getAsuntos(idAsunto, codigo, nombre, pE, idNorma, periodicidad, clave, idCliente, idResponsable, idAero, abierto, posicion, nCorto, idioma, finiPC, finiSC, finiME1, finiME10, tipo, null, null, null);

        if (incluirVacio != null && incluirVacio) {
            //Cargamos el valor vacío
            jcb.insertItemAt(new ComboBoxObject(null, ""), 0);
        }

        if (asuntos != null) {
            int n = asuntos.size();
            int posIni = jcb.getItemCount();
            for (int i = 0; i < n; i++) {
                ComboBoxObject dato = new ComboBoxObject(asuntos.get(i).getIdAsunto(), asuntos.get(i).getNombreCompleto());

                jcb.insertItemAt(dato, i + posIni);
            }
        }
        
        if (jcb.getItemCount() > 0)
            jcb.setSelectedIndex(0);
    }
    //Función para cargar en un combo los asuntos
    public static void cargaAsuntosTipo(JComboBox jcb, Integer tipo) throws SQLException, NoSuchFieldException {
        cargaAsuntosGen(jcb, true, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, tipo);
    }
    
    //Función para cargar en un combo los sites dado un asunto
    public static void cargaSites(Integer idAsunto, JComboBox jcb) throws SQLException, NoSuchFieldException {
        // Inicializo el combo sites
        jcb.removeAllItems();
        jcb.insertItemAt(new ComboBoxObject(null, ""), 0);

        // Cargo el combo sites
        if (idAsunto != null) {
            ArrayList<Integer> sites = ConfiguracionRA2.getIdSitesRA(idAsunto);
           
            if (sites != null) {
                int n = sites.size();
                for (int i = 0; i < n; i++) {
                    ComboBoxObject dato = new ComboBoxObject(sites.get(i), SiteRA.getSitePorId(sites.get(i)).getNombre());

                    jcb.insertItemAt(dato, i + 1);
                }
            }
            
            if (jcb.getItemCount() > 0)
                jcb.setSelectedIndex(0);
        }
    }
    
    //Función para cargar en un combo los sites dado un asunto
    public static void cargaTodosSites(JComboBox jcb) throws SQLException, NoSuchFieldException {
        // Inicializo el combo sites
        jcb.removeAllItems();
        jcb.insertItemAt(new ComboBoxObject(null, ""), 0);

        // Cargo el combo sites
        ArrayList<SiteRA> sites = SiteRA.getSites(null, null, 10, null, null, null, null, null, null, true);

        if (sites != null) {
            int n = sites.size();
            for (int i = 0; i < n; i++) {
                ComboBoxObject dato = new ComboBoxObject(sites.get(i).getIdSite(), sites.get(i).getNombre());

                jcb.insertItemAt(dato, i + 1);
            }
        }
        jcb.setSelectedIndex(0);
    }
    
    //Función para cargar en un combo las normas de aplicación posibles
    public static void cargaNormas(JComboBox jcb) throws SQLException, NoSuchFieldException {
        // Inicializo el combo sites
        jcb.removeAllItems();
        jcb.insertItemAt(new ComboBoxObject(null, ""), 0);

        // Cargo el combo sites
        ArrayList<NormaRA> normas = NormaRA.getNormasRA(null, null, null, null, null, true);

        if (normas != null) {
            int n = normas.size();
            for (int i = 0; i < n; i++) {
                ComboBoxObject dato = new ComboBoxObject(normas.get(i).getIdNorma(), normas.get(i).getNombre());

                jcb.insertItemAt(dato, i + 1);
            }
        }
		
        jcb.setSelectedIndex(0);
    }

    //Función para cargar en un combo las normas de ruido de aplicación posibles
    public static void cargaNormasRuido(JComboBox jcb) throws SQLException, NoSuchFieldException {
        // Inicializo el combo sites
        jcb.removeAllItems();
        jcb.insertItemAt(new ComboBoxObject(null, ""), 0);

        // Cargo el combo sites
        ArrayList<NormaRA> normas = NormaRA.getNormasRuido(null, null, null, null, null, true);

        if (normas != null) {
            int n = normas.size();
            for (int i = 0; i < n; i++) {
                ComboBoxObject dato = new ComboBoxObject(normas.get(i).getIdNorma(), normas.get(i).getNombre());

                jcb.insertItemAt(dato, i + 1);
            }
        }
        jcb.setSelectedIndex(0);
    }
    
    //Función recursiva para habilitar/deshabilitar todos los componentes de un contenedor
    public static void setEnabledCamposPanel(Container jp, boolean enabled) {
        Component[] com;

        com = jp.getComponents();
		for (Component com1 : com) {
			if (com1 instanceof Container) {
				setEnabledCamposPanel((Container) com1, enabled);
			}
			com1.setEnabled(enabled);
		}
    }
    
    //Función para bloquear/desbloquear un diálogo para que el usuario no pueda interactuar con él hasta que se le vuelva a dar permiso
    public static void bloqueaDialog(JDialog jd, boolean enabled) {
        if (enabled) {
            jd.setModalityType(ModalityType.DOCUMENT_MODAL);
            jd.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        } else {
            jd.setModalityType(JDialog.DEFAULT_MODALITY_TYPE); 
            jd.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
    }
    
    //Función recursiva para validar todos los campos que tengan que validarse en un contenedor
    public static boolean validaCampos(Container jp) {
        boolean res = true;
        Component[] com;
        IVExtendido iv;

        com = jp.getComponents();
		for (Component com1 : com) {
			if (com1 instanceof Container) {
				res = validaCampos((Container) com1);
				if (!res)
					return res;
			}
			try {
				iv = (IVExtendido) ((JComponent) com1).getInputVerifier();
				if (iv != null) {
					if (!iv.verifyFinal((JComponent) com1)) {
						res = false;
						return res;
					}
				}
			}catch (ClassCastException e) {  //Si no se puede pareser a JComponent, no tiene InputVerfier
				//Seguimos validando con el siguiente campo
			}
		}
        
        return res;
    }
    
    //Función que establece el valor a un campo de solo lectura (verificándolo si es necesario) y devulve si se ha cambiado o no
    public static boolean setValCampo(JDialog jd, JTextField jtf, boolean validar) {
        boolean res = true;
        JTextField jtfRecoger = new JTextField();
        String valorAnt = jtf.getText();
   
        if (JOptionPane.showConfirmDialog(jd, jtfRecoger, "Establecer " + jtf.getName(), JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
            jtf.setText(jtfRecoger.getText());

            IVExtendido iv = (IVExtendido) jtf.getInputVerifier();
            if (iv != null) {
                if (!iv.verifyFinal(jtf)) {
                    jtf.setText(valorAnt);
                    res = false;
                }
            }
        } else 
            res = false;
        
        return res;
    }
    
    //Función que muestra una progress embebida en un nuevo diálogo
    public static JProgressBar muestraProgress(JDialog parent, int maxValue, String texto) {
        JProgressBar jpb = new JProgressBar(0, maxValue);
        JDialog jd = new JDialog(parent, "Procesando...");
        JPanel jp = new JPanel();
        
        if (texto != null)
            jd.setTitle(texto);
        
        jpb.setPreferredSize(new Dimension(500, 50));
        jpb.setStringPainted(true);

        jp.add(jpb);
        
        jd.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        jd.setUndecorated(true);
        jd.getContentPane().add(jp, BorderLayout.CENTER);
        jd.pack();
        jd.setLocationRelativeTo(parent);
        jd.setVisible(true);
        jd.toFront();
        
        jd.update(jd.getGraphics());
        
        return jpb;
    }
    
    public static JProgressBar muestraProgress(JDialog parent, int maxValue) {
        return muestraProgress(parent, maxValue, null);
    }
    
    public static void ocultaProgress(JProgressBar jpb) {
        ((JDialog) jpb.getRootPane().getParent()).dispose();
    }
    
    //Porc será de 0 a 1
    public static void setPorcentajeProgress(JProgressBar jpb, double porc) {
        JDialog jd;
        
        jpb.setValue((int) Math.ceil(jpb.getMaximum() * porc));
        
        jd = ((JDialog) jpb.getRootPane().getParent());
        jd.update(jd.getGraphics());
    }
    
    //Porc será de 0 a 1
    public static void incPorcentajeProgress(JProgressBar jpb, double porc) {
        JDialog jd;
        
        int value = jpb.getValue();
        
        jpb.setValue((int) Math.ceil(value + jpb.getMaximum() * porc));
        
        //Si hay cambios
        if (value != jpb.getValue()) {
            jd = ((JDialog) jpb.getRootPane().getParent());
            jd.update(jd.getGraphics());
        }
    }

    //Función para centrar el contenido de tablas
    public static void centrarTabla(JTable tabla) {
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        TableColumn tc;
        int nCols;

        tcr.setHorizontalAlignment(SwingConstants.CENTER);

        nCols = tabla.getColumnCount();

        for (int i = 0; i < nCols; i++) {
            tc = tabla.getColumnModel().getColumn(i);

            tc.setCellRenderer(tcr);
        }
    }
    
    public static void clearModoSalida(ArrayList<Integer> modoSalida) {
        if (modoSalida != null) {
                modoSalida.clear();
            }
    }
    
    public static void setModoSalida(ArrayList<Integer> modoSalida, Integer modo) {
        if (modoSalida != null && modo != null) {
                modoSalida.clear();
                modoSalida.add(modo);
            }
    }

    //Función para salir de un diálogo mostrando o no solicitud de confirmación y almacenando o no la salida del proceso
    public static void salirDialogo(JDialog jd, String aviso, ArrayList<Integer> modoSalida, Integer modo) {
        
        if (    (modoSalida != null && modoSalida.size() > 0 && modoSalida.get(0) == MODO_CANCEL) || //Ya viene cancelado, no hace falta mostar aviso
                (aviso != null  && JOptionPane.showConfirmDialog(jd, aviso, "Aviso", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.OK_OPTION) || //Hay aviso, se muestra diálogo y este es aceptado
                (aviso == null)) { //Cierre sin aviso
            setModoSalida(modoSalida, modo);
            jd.dispose();
        }
    }
    
    public static boolean esSalidaTipo(ArrayList<Integer> modoSalida, Integer modo) {
        boolean res = false;
        
        switch (modo) {
            case MODO_ERROR:
                res = (modoSalida != null && !modoSalida.isEmpty() && modoSalida.get(0) == MODO_ERROR);
                break;
            case MODO_CANCEL:
                res = (modoSalida != null && modoSalida.isEmpty() && modoSalida.get(0) == MODO_CANCEL);
                break;
            case MODO_OK:
                res = (modoSalida != null && !modoSalida.isEmpty() && modoSalida.get(0) == MODO_OK);
                break;
            case MODO_ANT:
                res = (modoSalida != null && !modoSalida.isEmpty() && modoSalida.get(0) == MODO_ANT);
                break;
            case MODO_UNDEFINED:
            default:
                res = (modoSalida == null || modoSalida.isEmpty());
                break;
        }
                
        return res;
    }
    
    public static boolean esSalidaUndefined(ArrayList<Integer> modoSalida) {
        return esSalidaTipo(modoSalida, MODO_UNDEFINED);
    }
    
    public static boolean esSalidaCancel(ArrayList<Integer> modoSalida) {
        return esSalidaTipo(modoSalida, MODO_CANCEL);
    }
    
    public static boolean esSalidaError(ArrayList<Integer> modoSalida) {
        return esSalidaTipo(modoSalida, MODO_ERROR);
    }
    
    public static boolean esSalidaOk(ArrayList<Integer> modoSalida) {
        return esSalidaTipo(modoSalida, MODO_OK);
    }
    
    public static boolean esSalidaAnt(ArrayList<Integer> modoSalida) {
        return esSalidaTipo(modoSalida, MODO_ANT);
    }
    
    public static boolean esSalidaIncorrecta(ArrayList<Integer> modoSalida) {
        return (esSalidaCancel(modoSalida) || esSalidaError(modoSalida));
    }
    
    public static void creaColores(Integer valBinMin, Integer valBinMax) {
        //Creamos tantos colores como bines tengamos

        COLORES_SERIE0 = new ArrayList<Color>();
        COLORES_SERIE1 = new ArrayList<Color>();

        double inc = 128.0 / (valBinMax - valBinMin + 1); //Sobre 128, porque sobre 256 sería muy oscuro
        int colorMaxAG = Math.max(Math.max(COLOR_SERIE0_DEF.getRed(), COLOR_SERIE0_DEF.getGreen()), COLOR_SERIE0_DEF.getBlue());
        int colorMaxRF = Math.max(Math.max(COLOR_SERIE1_DEF.getRed(), COLOR_SERIE1_DEF.getGreen()), COLOR_SERIE1_DEF.getBlue());
        for (int i = 0; i <= valBinMax - valBinMin; i++) {
            COLORES_SERIE0.add(new Color(
                    (int) Math.round(COLOR_SERIE0_DEF.getRed() * (1 + (inc / colorMaxAG * (i+1)))) % 256,
                    (int) Math.round(COLOR_SERIE0_DEF.getGreen() * (1 + (inc / colorMaxAG * (i+1)))) % 256,
                    (int) Math.round(COLOR_SERIE0_DEF.getBlue() * (1 + (inc / colorMaxAG * (i+1)))) % 256));
            COLORES_SERIE1.add(new Color(
                    (int) Math.round(COLOR_SERIE1_DEF.getRed() * (1 + (inc / colorMaxRF * (i+1)))) % 256,
                    (int) Math.round(COLOR_SERIE1_DEF.getGreen() * (1 + (inc / colorMaxRF * (i+1)))) % 256,
                    (int) Math.round(COLOR_SERIE1_DEF.getBlue() * (1 + (inc / colorMaxRF * (i+1)))) % 256));
        }
    }
    
    public static void setTitulosJTabbedPane(JTabbedPane jtp, String[] listaTitulos) {
        int nTit;
        JLabel jl;
        
        jtp.setBackground(JTP_TIT_FONDO);
        
        nTit = listaTitulos.length;
        if (nTit > 0) {
			for (int i = 0; i < nTit; i++) {
				jl = new JLabel(listaTitulos[i], JLabel.CENTER);
				jl.setForeground(JTP_TIT_TXT);
				jtp.setTabComponentAt(i, jl);
			}
        }
        
        jtp.update(jtp.getGraphics());
    }
    
    public static void maximizaTitulosJTabbedPane(JTabbedPane jtp) {
        int nTabs, anchoJtp, anchoJl, anchoAcum;
        JLabel jl;
      
        nTabs = jtp.getTabCount();
        if (nTabs > 0) {
            anchoJtp = jtp.getWidth()
                    - (nTabs - 1) //Separadores entre pestañas no seleccionadas
                    - (nTabs - 1) * 10 //Bordes de pestañas no seleccionadas
                    - 13 //Borde de pestaña seleccionada
                    - 2; //Borde del jtp
            
            anchoAcum = 0;
            for (int i = 0; i < nTabs; i++) {
                if (i != nTabs - 1)
                    anchoJl = (int) (Math.round((i + 1) * anchoJtp / (nTabs * 1.0)) - Math.round((i) * anchoJtp / (nTabs * 1.0)));
                else
                    anchoJl = anchoJtp - anchoAcum;

                jl = (JLabel) jtp.getTabComponentAt(i);
                jl.setPreferredSize(new Dimension(anchoJl, jl.getPreferredSize().height));
                
                anchoAcum += anchoJl;
            }
        }
        
        jtp.update(jtp.getGraphics());
    }
    
    public static void asignaPanelGrafica(final JDialog jd, final JPanel jp, JFreeChart chart, boolean addChartMouseListener, ChartMouseListener cml) {
        jp.removeAll();

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(jp.getSize().width - 2, jp.getSize().height - 2));

        chartPanel.setDomainZoomable(true);
        chartPanel.setRangeZoomable(true);
        
        if (chart.getPlot() instanceof XYPlot) {
            JPopupMenu jpmMenu = chartPanel.getPopupMenu();

            jpmMenu.add(new JSeparator());

            JMenuItem jmiEscalar = new JMenuItem("Escalar gráfica");
            jmiEscalar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    escalarGrafica(jd, jp, null);
                }
            });
            jpmMenu.add(jmiEscalar);
        }

        jp.setLayout(new BorderLayout());
        jp.add(chartPanel, BorderLayout.CENTER);
        
        if (addChartMouseListener)
            chartPanel.addChartMouseListener(cml);
    }

    public static void muestraFila(JTable table, int rowIndex) {
        if (!(table.getParent() instanceof JViewport)) {
            return;
        }
        
        if (rowIndex < 0)
            rowIndex = 0;
        else if (rowIndex > table.getRowCount() - 1)
            rowIndex = table.getRowCount() - 1;
        
        JViewport viewport = (JViewport) table.getParent();
        Rectangle rect = table.getCellRect(rowIndex, 0, true);
        Rectangle r2 = viewport.getVisibleRect();
        
        table.scrollRectToVisible(new Rectangle(rect.x, rect.y, (int) r2.getWidth(), (int) r2.getHeight()));
    }
    
    public static Double[] arrayObjToDouble(Object[] arrayObj) {
        Double[] res = null;
        
        int nArrayObj = arrayObj != null ? arrayObj.length : 0;
        
        if (nArrayObj > 0) {
            res = new Double[nArrayObj];
            
            for (int i = 0; i < nArrayObj; i++) {
                res[i] = (Double) arrayObj[i];
            }
        }
        
        return res;
    }
    
    public static Double[][] arrayObjToDoubleDouble(Object[] arrayObj) {
        Double[][] res = null;
        
        int nArrayObj = arrayObj != null ? arrayObj.length : 0;
        
        if (nArrayObj > 0) {
            res = new Double[nArrayObj][];
            
            for (int i = 0; i < nArrayObj; i++) {
                res[i] = (Double[]) arrayObj[i];
            }
        }
        
        return res;
    }
    
    public static Object[][] arrayObjToObjObj(Object[] arrayObj) {
        Object[][] res = null;
        
        int nArrayObj = arrayObj != null ? arrayObj.length : 0;
        
        if (nArrayObj > 0) {
            res = new Object[nArrayObj][];
            
            for (int i = 0; i < nArrayObj; i++) {
                res[i] = (Object[]) arrayObj[i];
            }
        }
        
        return res;
    }
    
    public static double[] arrayObjTodouble(Object[] arrayObj) {
        double[] res = null;
        
        int nArrayObj = arrayObj != null ? arrayObj.length : 0;
        
        if (nArrayObj > 0) {
            res = new double[nArrayObj];
            
            for (int i = 0; i < nArrayObj; i++) {
                res[i] = (Double) arrayObj[i];
            }
        }
        
        return res;
    }
    
    public static int[] arrayObjToint(Object[] arrayObj) {
        int[] res = null;
        
        int nArrayObj = arrayObj != null ? arrayObj.length : 0;
        
        if (nArrayObj > 0) {
            res = new int[nArrayObj];
            
            for (int i = 0; i < nArrayObj; i++) {
                res[i] = (Integer) arrayObj[i];
            }
        }
        
        return res;
    }
    
    public static Integer[] arrayObjToInteger(Object[] arrayObj) {
        Integer[] res = null;
        
        int nArrayObj = arrayObj != null ? arrayObj.length : 0;
        
        if (nArrayObj > 0) {
            res = new Integer[nArrayObj];
            
            for (int i = 0; i < nArrayObj; i++) {
                res[i] = (Integer) arrayObj[i];
            }
        }
        
        return res;
    }
    
    public static Color colorOpuesto(Color color) {
        return new Color(255 - color.getRed(), 255 - color.getGreen(), 255 - color.getBlue());
    }
    
    public static void escalarGrafica(JDialog jd, JPanel jp, Double relacion) {
        Double relY, relX, desdeDom, hastaDom, desdeRan, hastaRan, intervaloRan, intervaloDom;
        EscaladoGraficas eg;
        
        XYPlot plot = ((ChartPanel)jp.getComponent(0)).getChart().getXYPlot();

        NumberAxis domAxis = (NumberAxis) plot.getDomainAxis();
        NumberAxis ranAxis = (NumberAxis) plot.getRangeAxis();

        double maxDom = domAxis.getUpperBound();
        double minDom = domAxis.getLowerBound();
        double maxRan = ranAxis.getUpperBound();
        double minRan = ranAxis.getLowerBound();
        
        desdeRan = minRan;
        hastaRan = maxRan;
        desdeDom = minDom;
        hastaDom = maxDom;
        
        intervaloRan = ranAxis.getTickUnit().getSize();
        intervaloDom = domAxis.getTickUnit().getSize();
        
        eg = EscaladoGraficas.getEscaladoGraficas(1.0, 1.0, minRan, maxRan, minDom, maxDom, intervaloRan, intervaloDom);
        
        if (relacion != null || JOptionPane.showConfirmDialog(jd, eg, "Escalar gráfica", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
            //Redibujamos el original para partir de él
            ((ChartPanel)jp.getComponent(0)).restoreAutoBounds();
            maxDom = domAxis.getUpperBound();
            minDom = domAxis.getLowerBound();
            maxRan = ranAxis.getUpperBound();
            minRan = ranAxis.getLowerBound();
            
            if (relacion == null) {
                relY = Double.parseDouble(eg.getJtfRelY().getText());
                relX = Double.parseDouble(eg.getJtfRelX().getText());
                
                desdeRan = Double.parseDouble(eg.getJtfDesdeRango().getText());
                hastaRan = Double.parseDouble(eg.getJtfHastaRango().getText());
                desdeDom = Double.parseDouble(eg.getJtfDesdeDom().getText());
                hastaDom = Double.parseDouble(eg.getJtfHastaDom().getText());
                
                intervaloRan = Double.parseDouble(eg.getJtfIntervaloRango().getText());
                intervaloDom = Double.parseDouble(eg.getJtfIntervaloDom().getText());
            } else {
                if (relacion >= 1.0) {
                    relY = relacion;
                    relX = 1.0;
                } else {
                    relY = 1.0;
                    relX = 1.0 / relacion;
                }
            }
                
            
            //Escalado
            if (relacion != null || eg.getJrbRelacion().isSelected()) {
                double ajusteY, ajusteX;
                if ((relY/relX)*(maxDom - minDom) > (maxRan - minRan)) {
                    ajusteY = (relY/relX)*(maxDom - minDom) - (maxRan - minRan);
                    ranAxis.setRange(minRan - ajusteY/2.0, maxRan + ajusteY/2.0);
                    domAxis.setRange(minDom, maxDom);
                } else {
                    ajusteX = (relX/relY)*(maxRan - minRan) - (maxDom - minDom);
                    ranAxis.setRange(minRan, maxRan);
                    domAxis.setRange(minDom - ajusteX/2.0, maxDom + ajusteX/2.0);
                }
            } else if (eg.getJrbRangoDom().isSelected()) {
                ranAxis.setRange(desdeRan, hastaRan);
                domAxis.setRange(desdeDom, hastaDom);
            } else if (eg.getJrbMaximizar().isSelected()) {
                
            }

            ranAxis.setTickUnit(new NumberTickUnit(intervaloRan));
            domAxis.setTickUnit(new NumberTickUnit(intervaloDom));

            if (relacion != null)
                jd.update(jd.getGraphics());

            Rectangle2D dataArea = ((ChartPanel)jp.getComponent(0)).getChartRenderingInfo().getPlotInfo().getDataArea();
            double anchoDA = dataArea.getWidth();
            
            jp.getParent().getParent().setLayout(null);
            jp.getParent().setLayout(null);

            if (!eg.getJrbMaximizar().isSelected()) {
                dataArea.setRect(dataArea.getX(), dataArea.getY(), dataArea.getHeight(), dataArea.getHeight());
                jp.setSize(new Dimension(((Double)(jp.getWidth() - anchoDA + dataArea.getWidth())).intValue(), jp.getHeight()));
                jp.setLocation(((Double) (jp.getLocation().x + (anchoDA - dataArea.getWidth())/2.0)).intValue(), jp.getLocation().y);    
            } else {
                dataArea.setRect(jp.getParent().getX(), jp.getParent().getY(), jp.getParent().getWidth(), jp.getParent().getHeight());
                jp.setSize(jp.getParent().getSize());
                jp.setLocation(0, 0);
            }
            
            jd.update(jd.getGraphics());
        }
    }
    
    public static LineString getRecta2D(Double posX1, Double posY1, Double posX2, Double posY2) {
        GeometryFactory gf = new GeometryFactory();
        Coordinate[] vertices = new Coordinate[]{new Coordinate(posX1, posY1), new Coordinate(posX2, posY2)};
        
        LineString res = gf.createLineString(vertices);
        
        return res;
    }
    
    public static LineString getRecta3D(Double posX1, Double posY1, Double posZ1, Double posX2, Double posY2, Double posZ2) {
        GeometryFactory gf = new GeometryFactory();
        Coordinate[] vertices = new Coordinate[]{new Coordinate(posX1, posY1, posZ1), new Coordinate(posX2, posY2, posZ2)};
        
        LineString res = gf.createLineString(vertices);
        
        return res;
    }
    
    //Devuelve en radianes el ángulo que forma un recta en el espacio con el plano XY
    public static Double getAnguloRectaPlano(LineString recta) {
        Double res = null;
        
        Coordinate vectorNormalPlano = new Coordinate(0, 0, 1);
        
        Coordinate iniRecta = recta.getCoordinateN(0);
        Coordinate finRecta = recta.getCoordinateN(recta.getCoordinates().length - 1);
        Coordinate vectorDirectorRecta = new Coordinate(finRecta.x - iniRecta.x, finRecta.y - iniRecta.y, finRecta.z - iniRecta.z);
        
        res = Math.asin((vectorNormalPlano.x * vectorDirectorRecta.x + vectorNormalPlano.y * vectorDirectorRecta.y + vectorNormalPlano.z * vectorDirectorRecta.z) / 
			(Math.sqrt(Math.pow(vectorNormalPlano.x, 2.0) + Math.pow(vectorNormalPlano.y, 2.0) + Math.pow(vectorNormalPlano.z, 2.0)) * 
			Math.sqrt(Math.pow(vectorDirectorRecta.x, 2.0) + Math.pow(vectorDirectorRecta.y, 2.0) + Math.pow(vectorDirectorRecta.z, 2.0))));
        
        return res;
    }
    
    //Función que devuelve el ángulo máximo en radianes que forma la recta micro aero con la curva de nivel (en caso de que haya intersección
    public static Double getAnguloMaxMicroAeroConCurvaNivel(LineString rectaMicroAero2D, LineString curvaNivel, Coordinate posMicro, Double nivel) {
        Double res = null, resAux = null;
        GeometryFactory gf = new GeometryFactory();
        
        //Hay intersección
        if (rectaMicroAero2D.intersects(curvaNivel)) {
            Coordinate[] intersecciones = rectaMicroAero2D.intersection(curvaNivel).getCoordinates();
            Coordinate interseccion = null;
            
            int nInter = intersecciones.length;
            for (int i = 0; i < nInter; i++) {
                interseccion = intersecciones[i];
                //Añadimos la z
                interseccion.z = nivel;

                //Creamos una recta del micro al punto de intersección
                Coordinate[] vertices = new Coordinate[]{posMicro, interseccion};

                LineString rectaMicroCurvaNivel = gf.createLineString(vertices);
                
                resAux = getAnguloRectaPlano(rectaMicroCurvaNivel);
                
                if (resAux != null) {
                    if (res == null || resAux > res)
                        res = resAux;
                }
            }
        }
        
        return res;
    }
    
    //Función que devuelve el ángulo máximo en radianes que forma la recta micro aero con las diferentes curvas de nivel (en caso de que haya intersección)
    public static Double getAnguloMaxMicroAeroConCurvasNivel(LineString rectaMicroAero, ArrayList<LineString> curvasNivel) {
        Double res = null, resAux = null;
        Double nivel = null;
        
        if (rectaMicroAero != null && rectaMicroAero.getCoordinates().length != 0 && curvasNivel != null) {
            Coordinate posMicro = rectaMicroAero.getCoordinateN(0);

            //Creamos la recta 2D
            Coordinate iniRecta = rectaMicroAero.getCoordinateN(0);
            Coordinate finRecta = rectaMicroAero.getCoordinateN(rectaMicroAero.getCoordinates().length - 1);
            
            LineString rectaMicroAero2D = getRecta2D(iniRecta.x, iniRecta.y, finRecta.x, finRecta.y);

            int nCurvas = curvasNivel.size();
            for (int i = 0; i < nCurvas; i++) {
                resAux = getAnguloMaxMicroAeroConCurvaNivel(rectaMicroAero2D, curvasNivel.get(i), posMicro, nivel);

                if (resAux != null) {
                    if (res == null || resAux > res)
                        res = resAux;
                }
            }
        }
        
        return res;
    }
    
    public static Double getAlturaPunto(Double posXInterseccion, Double posYInterseccion, Double[][] mallado, Double desdePosX, Double desdePosY, Double hastaPosX, Double hastaPosY) {
        Double res = null;
        Double[] elem;
        Double posX, posY, posZ;
        Double posXInf = null, posYInf = null, posXSup = null, posYSup = null;
        Double posZInf = null, posZSup = null;
        
        int nElem = mallado != null ? mallado.length : 0;
        for (int i = 0; i < nElem; i++) {
            elem = mallado[i];
            posX = elem[0];
            posY = elem[1];
            
            if (posX < desdePosX || posX > hastaPosX || posY < desdePosY || posY > hastaPosY)
                continue;
            
            if (posX.equals(posXInterseccion))
                posXInf = posXSup = posX;
            else {
                if (posX < posXInterseccion)
                    posXInf = posXInf == null || posX > posXInf? posX : posXInf;
                else
                    posXSup = posXSup == null || posX < posXSup? posX : posXSup;
            }

            if (posY.equals(posYInterseccion))
                posYInf = posYSup = posY;
            else {  
                if (posY < posYInterseccion)
                    posYInf = posYInf == null || posY > posYInf? posY : posYInf;
                else
                    posYSup = posYSup == null || posY < posYSup? posY : posYSup;
            }
        }
        
        for (int i = 0; i < nElem; i++) {
            elem = mallado[i];
            posX = elem[0];
            posY = elem[1];
            posZ = elem[2];
            
            //Nos quedamos con las mayores altitudes
            if ((posX.equals(posXInf) && posY.equals(posYInf)) || (posX.equals(posXSup) && posY.equals(posYSup))) {
                if (posX.equals(posXInf) && posY.equals(posYInf))
                    posZInf = posZInf == null || posZ > posZInf? posZ : posZInf;
                else
                    posZSup = posZSup == null || posZ > posZSup? posZ : posZSup;
            }
        }

        if (posXInterseccion.equals(posXInf) && posYInterseccion.equals(posYInf)) //Si el punto corresponde con el inferior
            res = posZInf;
        else if (posXInterseccion.equals(posXSup) && posYInterseccion.equals(posYSup)) //Si el punto corresponde con el superior
            res = posZSup;
        else { //Si no coincide, extrapolamos
            Double distPuntosMallado = Math.sqrt(Math.pow(posXSup - posXInf, 2.0) + Math.pow(posYSup - posYInf, 2.0));
            Double distPosInfInterseccion = Math.sqrt(Math.pow(posXInterseccion - posXInf, 2.0) + Math.pow(posYInterseccion - posYInf, 2.0));
            
            res = posZInf + ((posZSup - posZInf) * distPosInfInterseccion / distPuntosMallado);
        }
        
        return res;
    }
    
    //Mallado [[x1, y1, z1]....[xn, yn, zn]], ordenado por y,x
    public static Double[][] getPuntosCorteRectaMallado(LineString rectaMicroAero2D, Double[][] mallado) {
        Double[][] res = null;
        ArrayList<Double[]> resAux = new ArrayList<Double[]>();

        Double posY = null, posYAnt = null, posX = null, posXMin = null, posXMax = null, posYMin = null, posYMax = null;
        Double altura;
        LineString rectaMallado;
        Coordinate interseccion;
        Double[] elem = null;
        
        int nElem = mallado != null ? mallado.length : 0;
        //Buscamos los puntos de corte con X
        for (int i = 0; i < nElem; i++) {
            elem = mallado[i];
            
            posY = elem[1];
            if (posYAnt == null || !posY.equals(posYAnt)) { //Cambio de Y
                if (i != 0) { //Si no es la condición de entrada
                    posYMin = posYMin == null ? posY : (posY < posYMin ? posY : posYMin);
                    posYMax = posYMax == null ? posY : (posY > posYMax ? posY : posYMax);
                    
                    rectaMallado = getRecta2D(posXMin, posY, posXMax, posY);
                    
                    //Si hay interseccion, nos vamos quedando con la máxima altura
                    if (rectaMicroAero2D.intersects(rectaMallado)) {
                        interseccion = rectaMicroAero2D.intersection(rectaMallado).getCoordinates()[0];
                        altura = getAlturaPunto(interseccion.x, interseccion.y, mallado, posXMin, posY, posXMax, posY);
                        resAux.add(new Double[]{interseccion.x, interseccion.y, altura});
                    }
                }
                
                //Inicialización para siguientes casos
                posXMin = elem[0];
                posXMax = posXMin;
                posYAnt = posY;
                continue;
            }
            
            if (elem[0] > posXMax)
                posXMax = elem[0];
        }
        //Caso de salida
        if (nElem > 0) {
            posYMin = posYMin == null ? posY : (posY < posYMin ? posY : posYMin);
            posYMax = posYMax == null ? posY : (posY > posYMax ? posY : posYMax);
            
            rectaMallado = getRecta2D(posXMin, posY, posXMax, posY);
            
            //Si hay interseccion, nos vamos quedando con la máxima altura
            if (rectaMicroAero2D.intersects(rectaMallado)) {
                interseccion = rectaMicroAero2D.intersection(rectaMallado).getCoordinates()[0];
                altura = getAlturaPunto(interseccion.x, interseccion.y, mallado, posXMin, posY, posXMax, posY);
                resAux.add(new Double[]{interseccion.x, interseccion.y, altura});
            }
        }
        
        //Buscamos los puntos de corte con Y
        for (int i = 0; i < nElem; i++) {
            elem = mallado[i];
            
            posX = elem[0];
            
            rectaMallado = getRecta2D(posX, posYMin, posX, posYMax);
            
            //Si hay interseccion, nos vamos quedando con la máxima altura
            if (rectaMicroAero2D.intersects(rectaMallado)) {
                interseccion = rectaMicroAero2D.intersection(rectaMallado).getCoordinates()[0];
                altura = getAlturaPunto(interseccion.x, interseccion.y, mallado, posX, posYMin, posX, posYMax);
                resAux.add(new Double[]{interseccion.x, interseccion.y, altura});
            }
            
            posY = elem[1];
            if (posYAnt == null || !posY.equals(posYAnt)) { //Cambio de Y
                if (i != 0) { //Ya hemos pasado por todas las X
                    break;
                }
                posYAnt = posY;
            }
        }

        //Tenemos en resAux todos los puntos de interseccion de la recta Micro-Aero con las rectas XY del mallado
        if (resAux.size() > 0)
            res = arrayObjToDoubleDouble(resAux.toArray());
        
        return res;
    }
    
    //Función que devuelve el ángulo máximo en radianes que forma la recta micro - punto de corte
    public static Double getAnguloMaxMicroPuntoCorte(Coordinate posMicro, Coordinate posCorte) {
        Double res = null;
        GeometryFactory gf = new GeometryFactory();
        
        //Creamos una recta del micro al punto de intersección
        Coordinate[] vertices = new Coordinate[]{posMicro, posCorte};

        LineString rectaMicroPosCorte = gf.createLineString(vertices);
                
        res = getAnguloRectaPlano(rectaMicroPosCorte);
                
        return res;
    }
    
    //Función que devuelve el ángulo máximo en radianes que forma la recta micro aero con las diferentes curvas de nivel (en caso de que haya intersección)
    public static Double getAnguloMaxConMallado(LineString rectaMicroAero, Double[][] mallado, ArrayList<PosicionRA> puntoAngMax) {
        Double res = -Math.PI/2.0, resAux = null;

		int nCoordinates = rectaMicroAero.getCoordinates().length;
        
        if (rectaMicroAero != null && nCoordinates != 0 && mallado != null) {
            Coordinate posMicro = rectaMicroAero.getCoordinateN(0);
            Coordinate posAero = rectaMicroAero.getCoordinateN(nCoordinates - 1);
            Coordinate puntoCorte;

            //Creamos la recta 2D
            Coordinate iniRecta = rectaMicroAero.getCoordinateN(0);
            Coordinate finRecta = rectaMicroAero.getCoordinateN(rectaMicroAero.getCoordinates().length - 1);
            
            LineString rectaMicroAero2D = getRecta2D(iniRecta.x, iniRecta.y, finRecta.x, finRecta.y);
            
            Double[][] puntosCorte = getPuntosCorteRectaMallado(rectaMicroAero2D, mallado);

			res = getAnguloMaxMicroPuntoCorte(posMicro, posAero);

            int nPuntos = puntosCorte != null ? puntosCorte.length : 0;
            for (int i = 0; i < nPuntos; i++) {
                puntoCorte = new Coordinate(puntosCorte[i][0], puntosCorte[i][1], puntosCorte[i][2]);
                resAux = getAnguloMaxMicroPuntoCorte(posMicro, puntoCorte);

                if (resAux != null && resAux > res) {
                    res = resAux;

					puntoAngMax.clear();
					puntoAngMax.add(new PosicionRA(null, puntosCorte[i][0], puntosCorte[i][1], puntosCorte[i][2]));
				}
            }
        }
        
        return res;
    }
    
    public static Double[][] leeMalladoFichero(String ruta) throws IOException {
        Double[][] res = null;
        ArrayList<Double[]> resAux = new ArrayList<Double[]>();
        
        InteraccionFic interFic = new InteraccionFic(ruta, InteraccionFic.READ);
        ArrayList<ArrayList<String>> posiciones = interFic.leeCamposSep(";", 3);
        ArrayList<String> posicion;
        
        int nPosciones = posiciones.size();
        for (int i = 0; i < nPosciones; i++) {
            posicion = posiciones.get(i);
            resAux.add(new Double[]{Double.parseDouble(posicion.get(0)), Double.parseDouble(posicion.get(1)), Double.parseDouble(posicion.get(2))});
        }

        res = Auxiliares.arrayObjToDoubleDouble(resAux.toArray());
        
        interFic.finOpFichero();
        
        return res;
    }
    
    public static Double getAnguloDecimal(Double anguloRadianes) {
        return anguloRadianes != null ? TratDecimales.round(anguloRadianes * 180/Math.PI, TratDecimales.DEC_SECTOR) : null;
    }
}