package RA;

import general.Auxiliares;
import general.ConfInsercionDatos;
import general.DatoVelocidadNivel;
import general.DatoXML;
import general.DatosIncertidumbre;
import general.InteraccionBD;
import general.InteraccionFic;
import general.InteraccionXML;
import general.MensajeApp;
import general.PolynomialRegression;
import general.RecogidaDatos;
import general.ResultadoBandaCriticaFFT;
import general.ResultadoBinFFT;
import general.ResultadoEspectroFFT;
import general.ResultadoIncert;
import general.TratDecimales;
import general.TratFechas;
import java.awt.Component;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.apache.commons.math.stat.StatUtils;
import org.jfree.data.function.PolynomialFunction2D;
import org.jfree.data.statistics.Regression;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.data.xy.YIntervalSeries;
import org.jfree.data.xy.YIntervalSeriesCollection;

public class DatosRA2 {
    //Campos identificativos de tabla
    private String tipoTabla;
    private Integer idAsunto;
    //Campos genéricos
    private Integer idDato;
    private Long fechaHora;
    private Integer valido;
    private Integer rF;
    private Double tBuje;
    private Double pBuje;    
    //Campos variables
    private HashMap<String, Object> variables;
    
    private static String BD = InteraccionBD.PREF_BD_RA;
    private static String TABLA;
    
    public static final String CAMPO_ID_DATO = "Id";
    public static final String CAMPO_ID_DATO_SYS = "IdSys";
    private static final String TIPO_ID_DATO = "int";
    private static final String EXTRA_ID_DATO = "Primary key";
    public static final String CAMPO_FECHA_HORA = "Fecha_Hora";
    private static final String TIPO_FECHA_HORA = "bigint";
    private static final String EXTRA_FECHA_HORA = "not NULL";
    public static final String CAMPO_VALIDO = "Valido";
    public static final String CAMPO_VALIDO_SYS = "ValidoSys";
    private static final String TIPO_VALIDO = "int";
    private static final String EXTRA_VALIDO = "default 1";
    public static final String CAMPO_RF = "RF";
    private static final String TIPO_RF = "int";
    private static final String EXTRA_RF = "default 0";
    public static final String CAMPO_T_BUJE = "Tbuje";
    private static final String TIPO_T_BUJE = "decimal(18,5)";
    private static final String EXTRA_T_BUJE = "default NULL";
    public static final String CAMPO_P_BUJE = "Pbuje";
    private static final String TIPO_P_BUJE = "decimal(18,5)";
    private static final String EXTRA_P_BUJE = "default NULL";
    public static final String CAMPO_XML = "XmlEspectro";
    private static final String TIPO_XML = "xml";
    private static final String EXTRA_XML = "(" + InteraccionXML.NOM_FFT_SCHEMA + ")default NULL";    //Campos para Vistas
    
    private static final String CAMPO_DESDE_POT = "DesdePot";
    private static final String CAMPO_HASTA_POT = "HastaPot";
    private static final String CAMPO_PENDIENTE = "Pendiente";
    private static final String CAMPO_OFFSET = "Offset";
    private static final String CAMPO_RANGO_PERMITIDO = "RangoPermitido";
    private static final String TIPO_INT = "int";
    private static final String TIPO_DECIMAL = "decimal(18,5)";
    private static final String EXTRA_NULO = "default NULL";
    private static final String EXTRA_NO_NULO = "not NULL";
    
    public static final String CURVA_AUX = "CurvaAux";
    public static final String VALI_SYS_AUX = "ValiSysAux";
    public static String VISTA_AUX;
    public static String VISTA_NETA;
    
    public static final String CAMPO_TENSION = "V";
    public static final String CAMPO_INTENSIDAD = "I";
    public static final String CAMPO_P_ACTIVA = "PA";
    public static final String CAMPO_P_ACT_ROTOR = "PAR";
    public static final String CAMPO_P_ACT_ESTATOR = "PAE";
    public static final String CAMPO_P_NETA = "Pneta";
    public static final String CAMPO_V_Z = "VZ";
    public static final String CAMPO_V_Z_REF = "VZref";
    public static final String CAMPO_P_N = "Pn";
    public static final String CAMPO_V_N = "Vn";
    public static final String CAMPO_V_D = "VD";
    public static final String CAMPO_V_H = "VH";
    public static final String CAMPO_V_S = "VS";
    public static final String CAMPO_L_A_EQ_1 = "LAeq1";    //Valores por defecto
    public static final String CAMPO_DIRECCION = "D";    //Valores por defecto
    private static final String TIPO_DECIMAL_DEF = "decimal(18,5)";
    private static final String VALOR_DECIMAL_DEF = "NULL";
    private static final String EXTRA_DECIMAL_DEF = "default " + VALOR_DECIMAL_DEF;
    private static final int PRESION_DEF = 1013;
    private static final int TEMP_DEF = 288;

    private static final Double PORC_TOLERANCIA = 0.05; //Entre 1-5%
    
    public static final int VEL_DERIVADA_CP = 0;
    public static final int VEL_MEDIDA = 1;
    
    public static final int POT_DESDE_V_I = 0;
    public static final int POT_MEDIDA = 1;
    
    public static final int AJUSTE_K_TORRE = 0;
    public static final int AJUSTE_K_NACELLE = 1;
    
    public static final int CALCULO_K_PROMEDIO = 0;
    public static final int CALCULO_K_PENDIENTE = 1;
    
    public static final int TIPO_NADA = 0;
    public static final int TIPO_ENMASCARAMIENTO = -1;
    public static final int TIPO_TONO = 1;
    
    public static final int ORDEN_REG_POL = 4;
    
    public static final int VEL_M_S = 0;
    public static final int VEL_M_S_SB = 1;
    public static final int VEL_RPM = 2;
    
    public static final int CORREGIDO_RF_OK = 0;
    public static final int CORREGIDO_RF_AVISO = 1;
    public static final int CORREGIDO_RF_ERROR = 2;
    public static final int CORREGIDO_RF_AVISO_IEC3 = 3;
    
    public static final String TXT_CORREGIDO_RF_ERROR = "---";
    
    public static final int TIPO_CAL_REG_LIN = 0;
    public static final int TIPO_CAL_REG_POL = 1;
    
    private static final String SEP_CAMPOS_OCT = ";"; //Priemras pruebas, era "\t"
    private static final String SEP_CAMPOS_SPL = ";"; //Priemras pruebas, era "\t"
    private static final String SEP_CAMPOS_FFT = ";";
    
    public static final String MARCA_INI_BRACKETS = "[";
    public static final String MARCA_FIN_BRACKETS = "]";
    public static final String MARCA_ASTERISCO = " (*)";
    public static final String MARCA_PENALIZACION = " + P";
    
    public static final String UB1 = "UB1";
    public static final String UB2 = "UB2";
    public static final String UB3_SPL = "UB3_SPL";
    public static final String UB3_OCT_FFT = "UB3_OCT_FFT";
    public static final String UB4 = "UB4";
    public static final String UB5 = "UB5";
    public static final String UB6 = "UB6";
    public static final String UB7 = "UB7";
    public static final String UB8 = "UB8";
    public static final String UB9 = "UB9";

    
    public static final String FORMATO_FECHA = "ddMMyyyy HHmmss.SS";    
    
    //Ponderación dbZ a dbA
    public static final int POND_DECIMALES_VARIABLES = 8;
    public static final Double POND_CTE_A_1000 = -2.0;
    public static final Double POND_CTE_D = TratDecimales.round(Math.sqrt(0.5), POND_DECIMALES_VARIABLES);
    public static final Double POND_CTE_F_A = Math.pow(10.0, 2.45);
    public static final Double POND_CTE_F_L = Math.pow(10.0, 1.5);
    public static final Double POND_CTE_F_H = Math.pow(10.0, 3.9);
    public static final Double POND_CTE_F_R = 1000.0;
    public static final Double POND_CTE_C = Math.pow(POND_CTE_F_L, 2) * Math.pow(POND_CTE_F_H, 2);
    public static final Double POND_CTE_B = TratDecimales.round((1 / (1 - POND_CTE_D)) * (Math.pow(POND_CTE_F_R, 2) + (POND_CTE_C / Math.pow(POND_CTE_F_R, 2)) - POND_CTE_D * (Math.pow(POND_CTE_F_L, 2) + Math.pow(POND_CTE_F_H, 2))), POND_DECIMALES_VARIABLES);
    public static final Double POND_CTE_F1 = TratDecimales.round(Math.pow((-POND_CTE_B - Math.sqrt(Math.pow(POND_CTE_B, 2) - 4 * POND_CTE_C)) / 2, 0.5), POND_DECIMALES_VARIABLES);
    public static final Double POND_CTE_F2 = TratDecimales.round(((3 - Math.sqrt(5)) / 2) * POND_CTE_F_A, POND_DECIMALES_VARIABLES);
    public static final Double POND_CTE_F3 = TratDecimales.round(((3 + Math.sqrt(5)) / 2) * POND_CTE_F_A, POND_DECIMALES_VARIABLES);
    public static final Double POND_CTE_F4 = TratDecimales.round(Math.pow((-POND_CTE_B + Math.sqrt(Math.pow(POND_CTE_B, 2) - 4 * POND_CTE_C)) / 2, 0.5), POND_DECIMALES_VARIABLES);
    public static final HashMap<String, Double> POND_OCT_Z_TO_A = new HashMap<String, Double>() {
        {
            put("Hz10", -70.4);
            put("Hz12_5", -63.4);
            put("Hz16", -56.7);
            put("Hz20", -50.5);
            put("Hz25", -44.7);
            put("Hz31_5", -39.4);
            put("Hz40", -34.6);
            put("Hz50", -30.2);
            put("Hz63", -26.2);
            put("Hz80", -22.5);
            put("Hz100", -19.1);
            put("Hz125", -16.1);
            put("Hz160", -13.4);
            put("Hz200", -10.9);
            put("Hz250", -8.6);
            put("Hz315", -6.6);
            put("Hz400", -4.8);
            put("Hz500", -3.2);
            put("Hz630", -1.9);
            put("Hz800", -0.8);
            put("Hz1000", 0.0);
            put("Hz1250", 0.6);
            put("Hz1600", 1.0);
            put("Hz2000", 1.2);
            put("Hz2500", 1.3);
            put("Hz3150", 1.2);
            put("Hz4000", 1.0);
            put("Hz5000", 0.5);
            put("Hz6300", -0.1);
            put("Hz8000", -1.1);
            put("Hz10000", -2.5);
            put("Hz12500", -4.3);
            put("Hz16000", -6.6);
            put("Hz20000", -9.3);
        }
    };
    public static final HashMap<String, Double> POND_OCT_Z_TO_C = new HashMap<String, Double>() {
        {
            put("Hz10", -14.3);
            put("Hz12_5", -11.2);
            put("Hz16", -8.5);
            put("Hz20", -6.2);
            put("Hz25", -4.4);
            put("Hz31_5", -3.0);
            put("Hz40", -2.0);
            put("Hz50", -1.3);
            put("Hz63", -0.8);
            put("Hz80", -0.5);
            put("Hz100", -0.3);
            put("Hz125", -0.2);
            put("Hz160", -0.1);
            put("Hz200", 0.0);
            put("Hz250", 0.0);
            put("Hz315", 0.0);
            put("Hz400", 0.0);
            put("Hz500", 0.0);
            put("Hz630", 0.0);
            put("Hz800", 0.0);
            put("Hz1000", 0.0);
            put("Hz1250", 0.0);
            put("Hz1600", -0.1);
            put("Hz2000", -0.2);
            put("Hz2500", -0.3);
            put("Hz3150", -0.5);
            put("Hz4000", -0.8);
            put("Hz5000", -1.3);
            put("Hz6300", -2.0);
            put("Hz8000", -3.0);
            put("Hz10000", -4.4);
            put("Hz12500", -6.2);
            put("Hz16000", -8.5);
            put("Hz20000", -11.2);
        }
    };
    
    private static final Double TOLERANCIA_DIFF_VZ_VD = 0.75;
	public static final Double RES_FREC_ORI_DEF = 1.5625;
	public static final Double RES_FREC_DES_DEF = 2.0;
    
    public DatosRA2(String tipoTabla, Integer idAsunto, Integer idDato, Long fechaHora, Integer valido, Integer rF, Double tBuje, Double pBuje) {
        this.tipoTabla = tipoTabla;
        this.idAsunto = idAsunto;

        this.idDato = idDato;
        this.fechaHora = fechaHora;
        this.valido = valido;
        this.rF = rF;
        this.tBuje = tBuje;
        this.pBuje = pBuje;
    }

    public DatosRA2(String tipoTabla, Integer idAsunto, Object[] valores, ArrayList<String> campos) throws SQLException, NoSuchFieldException {
        int nValores = valores.length;

        this.tipoTabla = tipoTabla;
        this.idAsunto = idAsunto;

        if (campos == null) { //Si se han pedido todos los campos sin indicarlos (SELECT * FROM ...)
            InteraccionBD interBD = new InteraccionBD();

            Object[] camposAux = interBD.getCamposTabla(TABLA);
            int nCamposAux = camposAux.length;

            campos = new ArrayList<String>();

            for (int i = 0; i < nCamposAux; i++) {
                campos.add((String) camposAux[i]);
            }

        }

        this.variables = new HashMap<String, Object>();

        for (int i = 0; i < nValores; i++) {
            asignaCampo(campos.get(i), valores[i]);
        }
    }

    public Long getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Long fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Integer getIdAsunto() {
        return idAsunto;
    }

    public void setIdAsunto(Integer idAsunto) {
        this.idAsunto = idAsunto;
    }

    public Integer getIdDato() {
        return idDato;
    }

    public void setIdDato(Integer idDato) {
        this.idDato = idDato;
    }

    public Double getPBuje() {
        return pBuje;
    }

    public void setPBuje(Double pBuje) {
        this.pBuje = pBuje;
    }

    public Integer getRf() {
        return rF;
    }

    public void setRf(Integer rF) {
        this.rF = rF;
    }

    public Double getTBuje() {
        return tBuje;
    }

    public void setTBuje(Double tBuje) {
        this.tBuje = tBuje;
    }

    public String getTipo() {
        return tipoTabla;
    }

    public void setTipo(String tipoTabla) {
        this.tipoTabla = tipoTabla;
    }

    public Integer getValido() {
        return valido;
    }

    public void setValido(Integer valido) {
        this.valido = valido;
    }

    public static String getTabla() {
        return TABLA;
    }

    public static void setTabla(String tipoTabla, Integer idAsunto) {
        TABLA = BD + InteraccionBD.TABLA_DATOS + tipoTabla + idAsunto;
    }

    public static String getVistaAux() {
        return VISTA_AUX;
    }

    public static void setVistaAux(String tipoTabla, Integer idAsunto, Integer idSite) {
        VISTA_AUX = InteraccionBD.VISTA_DATOS + "Aux" + tipoTabla + idAsunto + "_" + idSite;
    }

    public static String getVistaNeta() {
        return VISTA_NETA;
    }

    public static void setVistaNeta(String tipoTabla, Integer idAsunto, Integer idSite) {
        VISTA_NETA = InteraccionBD.VISTA_DATOS + "Neta" + tipoTabla + idAsunto + "_" + idSite;
    }
    //Función para asignar valores a campos de la clase según el campo de la BD
    private void asignaCampo(String campo, Object valor) throws NoSuchFieldException {
        if (campo.compareTo(CAMPO_ID_DATO) == 0) {
            this.idDato = (Integer) valor;
        } else if (campo.compareTo(CAMPO_FECHA_HORA) == 0) {
            this.fechaHora = (Long) valor;
        } else if (campo.compareTo(CAMPO_VALIDO) == 0) {
            this.valido = (Integer) valor;
        } else if (campo.compareTo(CAMPO_RF) == 0) {
            this.rF = (Integer) valor;
        } else if (campo.compareTo(CAMPO_T_BUJE) == 0) {
            if (valor != null) {
                this.tBuje = TratDecimales.round(((BigDecimal) valor).doubleValue(), TratDecimales.DEC_VARIABLE_RA);
            } else {
                this.tBuje = null;
            }
        } else if (campo.compareTo(CAMPO_P_BUJE) == 0) {
            if (valor != null) {
                this.pBuje = TratDecimales.round(((BigDecimal) valor).doubleValue(), TratDecimales.DEC_VARIABLE_RA);
            } else {
                this.pBuje = null;
            }
        } else if (campo.compareTo(CAMPO_XML) == 0) {
            this.variables.put(campo, (String) valor);
        } else { //Será uno los campos variables
            if (valor != null) {
                this.variables.put(campo, TratDecimales.round(((BigDecimal) valor).doubleValue(), TratDecimales.DEC_VARIABLE_RA));
            } else {
                this.variables.put(campo, null);
            }
        }
    }

    private static String getCondicion(Integer idDato, Long fechaHora, Integer valido, Integer rF, Double tBuje, Double pBuje, ArrayList<Object[]> paramsPS) {
        String condicion = "";

        if (idDato != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_ID_DATO, "=", idDato);
        }
        if (fechaHora != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_FECHA_HORA, "=", fechaHora);
        }
        if (valido != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_VALIDO, "=", valido);
        }
        if (rF != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_RF, "=", rF);
        }
        if (tBuje != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_T_BUJE, "=", tBuje);
        }
        if (pBuje != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_P_BUJE, "=", pBuje);
        }

        return condicion;
    }

    //Función para obtener la colección de Datos que se ajustan a los limites pasados
    public static ArrayList<DatosRA2> getDatos(String tipoTabla, Integer idAsunto, Integer idDato, Long fechaHora, Integer valido, Integer rF, Double tBuje, Double pBuje, ArrayList<String> campos, String sqlExtra, Boolean distinct) throws SQLException, NoSuchFieldException {
        InteraccionBD interBD = new InteraccionBD();

        ArrayList<DatosRA2> res = null;
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();

        if (tipoTabla != null && idAsunto != null) {
            setTabla(tipoTabla, idAsunto);

            condicion = getCondicion(idDato, fechaHora, valido, rF, tBuje, pBuje, paramsPS);

            //Por defecto lo devolvemos ordenado por fecha_hora
            if (sqlExtra == null || sqlExtra.trim().length() == 0) {
                String sqlOrderBy = InteraccionBD.anadeCampoOrderBy(null, CAMPO_FECHA_HORA);
                sqlExtra = InteraccionBD.anadeSqlExtra(null, sqlOrderBy);
            }

            ArrayList<Object[]> resAux = interBD.getDatosTabla(TABLA, campos, condicion, paramsPS, sqlExtra, distinct);

            if (resAux != null) {
                int n = resAux.size();
                res = new ArrayList<DatosRA2>();

                for (int i = 0; i < n; i++) {
                    res.add(new DatosRA2(tipoTabla, idAsunto, resAux.get(i), campos));
                }
            }
        }

        return res;
    }
    //Función para obtener el Datos que se ajustan a los limites pasados
    public static DatosRA2 getDatoPorFecha(String tipoTabla, Integer idAsunto, long fechaHora) throws SQLException, NoSuchFieldException {
        DatosRA2 res = null;

        ArrayList<DatosRA2> resAux = getDatos(tipoTabla, idAsunto, null, fechaHora, null, null, null, null, null, null, null);

        if (resAux != null) {
            res = resAux.get(0);
        }

        return res;
    }
    
    //Función para obtener el Datos que se ajustan a los limites pasados
    public static Long getFechaPorFechaProximo(String tipoTabla, Integer idAsunto, long fechaHora, String operacion) throws SQLException, NoSuchFieldException {
        Long res = null;
        
        InteraccionBD interBD = new InteraccionBD();

        String condicion = "";
        ArrayList<String> campos = new ArrayList<String>();
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();

        if (tipoTabla != null && idAsunto != null) {
            setTabla(tipoTabla, idAsunto);

            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_FECHA_HORA, operacion, fechaHora);
            
            if (operacion.contains(">="))
                campos.add("MIN(" + CAMPO_FECHA_HORA + ")");
            else if (operacion.contains("<="))
                campos.add("MAX(" + CAMPO_FECHA_HORA + ")");
            else
                campos.add(CAMPO_FECHA_HORA);

            ArrayList<Object[]> resAux = interBD.getDatosTabla(TABLA, campos, condicion, paramsPS, null, null);

            if (resAux != null) {
                int n = resAux.size();
                
                res = (Long) resAux.get(0)[0];
            }
        }

        return res;
    }
    //Función para obtener el Dato que se ajustan a los limites pasados
    public static DatosRA2 getDatoPorId(String tipoTabla, Integer idAsunto, Integer idDato) throws SQLException, NoSuchFieldException {
        DatosRA2 res = null;

        ArrayList<DatosRA2> resAux = getDatos(tipoTabla, idAsunto, idDato, null, null, null, null, null, null, null, null);

        if (resAux != null) {
            res = resAux.get(0);
        }

        return res;
    }
    //Función para obtener la fecha de inicio/fin de datos para los límites pasado
    public static Long getFechaGenDatos(String tipoTabla, Integer idAsunto, String minMax) throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        Long res = null;
        ArrayList<String> campos = new ArrayList<String>();

        if (tipoTabla != null && idAsunto != null) {
            setTabla(tipoTabla, idAsunto);

            campos.add(minMax + "(" + CAMPO_FECHA_HORA + ")");

            ArrayList<Object[]> resAux = interBD.getDatosTablaIfExists(TABLA, campos, null, null, null, null, true);

            if (resAux != null && resAux.get(0)[0] != null) {
                res = (Long) resAux.get(0)[0];
            }
        }

        return res;
    }
    //Función para obtener la fecha de inicio de datos para los límites pasado
    public static Long getFechaIniDatos(String tipoTabla, Integer idAsunto) throws SQLException {
        Long res = getFechaGenDatos(tipoTabla, idAsunto, "MIN");

        if (res == null) //Fecha no encontrada, devolvemos el mínimo posible
        {
            res = TratFechas.millisFecha(TratFechas.FECHA_MIN);
        }

        return res;
    }
    //Función para obtener la fecha de fin de datos para los límites pasado
    public static Long getFechaFinDatos(String tipoTabla, Integer idAsunto) throws SQLException {
        Long res = getFechaGenDatos(tipoTabla, idAsunto, "MAX");

        if (res == null) //Fecha no encontrada, devolvemos el máximo posible
        {
            res = TratFechas.millisFecha(TratFechas.FECHA_MAX);
        }

        return res;
    }

    //Función para añadir una dato a la BD
    public static int insertDatoBasico(String tipoTabla, Integer idAsunto, Integer idDato, Long fechaHora, Integer valido, Integer rF, Double tBuje, Double pBuje, String sqlExtra) throws SQLException {
        InteraccionBD interBD = new InteraccionBD();

        String valores = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        ArrayList<String> campos = new ArrayList<String>();
        ArrayList<String> autoInc = new ArrayList<String>();
        ArrayList<String> condAutoInc = new ArrayList<String>();
        int res = 0;

        if (tipoTabla != null && idAsunto != null) {
            setTabla(tipoTabla, idAsunto);

            if (idDato != null) {
                valores = InteraccionBD.anadeCampoValor(valores, paramsPS, idDato);
                campos.add(CAMPO_ID_DATO);
            } else {    //Es campo clave, asignamos el incremento de la última
                valores = InteraccionBD.anadeCampoValorAutoInc(valores, CAMPO_ID_DATO);
                campos.add(CAMPO_ID_DATO);
                autoInc.add(CAMPO_ID_DATO);
                condAutoInc.add(null);
            }
            if (fechaHora != null) {
                valores = InteraccionBD.anadeCampoValor(valores, paramsPS, fechaHora);
                campos.add(CAMPO_FECHA_HORA);
            }
            if (valido != null) {
                valores = InteraccionBD.anadeCampoValor(valores, paramsPS, valido);
                campos.add(CAMPO_VALIDO);
            }
            if (rF != null) {
                valores = InteraccionBD.anadeCampoValor(valores, paramsPS, rF);
                campos.add(CAMPO_RF);
            }
            if (tBuje != null) {
                valores = InteraccionBD.anadeCampoValor(valores, paramsPS, tBuje);
                campos.add(CAMPO_T_BUJE);
            }
            if (pBuje != null) {
                valores = InteraccionBD.anadeCampoValor(valores, paramsPS, pBuje);
                campos.add(CAMPO_P_BUJE);
            }

            interBD.inicioTransaccion();
            res = interBD.insertDatosTabla(TABLA, campos, valores, paramsPS, sqlExtra, autoInc, condAutoInc);
            interBD.finTransaccion();
        }

        return res;
    }

    public static int insertDatoBasico(String tipoTabla, Integer idAsunto, DatosRA2 dato, String sqlExtra) throws SQLException {
        return insertDatoBasico(tipoTabla, idAsunto, dato.idDato, dato.fechaHora, dato.valido, dato.rF, dato.tBuje, dato.pBuje, sqlExtra);
    }
    
    //Función que devuelve la ponderación de dbZ a dbA basándose en la función dada por AENOR, redondeandoa un decimal como indica la norma
    private static Double getPondDbZtoDbA(Double frecuencia) {
        Double res = null;

        if (frecuencia != 0.0) {
            res = TratDecimales.round(20 * Math.log10(Math.pow(POND_CTE_F4, 2) * Math.pow(frecuencia, 4) / ((Math.pow(frecuencia, 2) + Math.pow(POND_CTE_F1, 2)) * Math.pow(Math.pow(frecuencia, 2) + Math.pow(POND_CTE_F2, 2), 0.5) * Math.pow(Math.pow(frecuencia, 2) + Math.pow(POND_CTE_F3, 2), 0.5) * (Math.pow(frecuencia, 2) + Math.pow(POND_CTE_F4, 2)))) - POND_CTE_A_1000, 1);
        }

        return res;
    }

    private static void cargaPondDbZtoDbA(HashMap<String, Double> pondDbZtoDbA, ArrayList<String> codigos, ArrayList<String> nomVariables, Boolean ponderadoA) throws SQLException, NoSuchFieldException {
        String codigo;
        Double codigoVal;

        int nCodigos = codigos.size();

        for (int i = 0; i < nCodigos; i++) {
            if (!nomVariables.get(i).contentEquals(CAMPO_XML))
                continue;
            
            codigo = codigos.get(i);
            codigoVal = Double.parseDouble(codigo);
            
            if (!ponderadoA)
                pondDbZtoDbA.put(codigo, getPondDbZtoDbA(codigoVal));
            else
                pondDbZtoDbA.put(codigo, 0.0);
        }
    }

    private static ConfInsercionDatos preparaDatosInsertUpdate(long desdeFecha, Integer idAsunto, Integer idSite, ArrayList<String> canalesFic, ArrayList<SerieRA2> seriesDesc, ArrayList<String> nomVariablesDesc, ArrayList<LineaConfiguracion> lineasConfTot, Boolean ponderadoA) throws SQLException, NoSuchFieldException {
        SerieRA2 serie;
        Integer idTipoRA;
        Integer idConfig;
        Double cotaT, cotaP, dec, hB, resFrecOri = null, resFrecDes = null;
        ArrayList<String> codigos = new ArrayList<String>();
        ArrayList<String> nomVariables = new ArrayList<String>();
        ArrayList<Integer> canales = new ArrayList<Integer>();
        ArrayList<Double> slopes = new ArrayList<Double>();
        ArrayList<Double> offsets = new ArrayList<Double>();
        ArrayList<Double> varInOut = new ArrayList<Double>(); //<cotaT, cotaP, dec, hB>
        HashMap<String, Double> pondDbZtoDbA = new HashMap<String, Double>();

        cotaT = cotaP = dec = hB = null;

        //Obtenemos altura de buje para corregir T y P
        hB = AerogeneradorRA.getAeroPorId(AsuntoRA.getAsuntoPorId(idAsunto).getIdAero()).getHB();

        idConfig = ConfiguracionRA2.getNumeroConfiguracion(idAsunto, idSite, desdeFecha);

        if (idConfig != null) {
            String nomVariable, certificado;
            Double slope, offset, slopeC, offsetC, slopeP, offsetP;
            LineaConfiguracion lineaConf;

            ArrayList<LineaConfiguracion> lineasConf = new ArrayList<LineaConfiguracion>();
            int nLineasConfTot = lineasConfTot.size();

            for (int i = 0; i < nLineasConfTot; i++) {
                if (lineasConfTot.get(i).getIdConfig().equals(idConfig)) {
                    lineasConf.add(lineasConfTot.get(i));
                }
            }

            if (lineasConf != null) {
                int nLineas = lineasConf.size();

                int nSeriesDesc = seriesDesc.size();
                int nPosLinea;

                for (int i = 0; i < nSeriesDesc; i++) {
                    lineaConf = null;
                    serie = seriesDesc.get(i);
                    nomVariable = nomVariablesDesc.get(i);

                    //Ignoramos las descripciones para FFT, llevan un tratamiento al márgen
                    if (serie.getIdTipoRA().equals(TipoRA.ID_TIPO_FFT)) {
                        continue;                    //Ignoramos las series que no sean genéricas o del tipo del site
                    }
                    if (!serie.getIdTipoRA().equals(TipoRA.ID_TIPO_GEN) && !serie.getIdTipoRA().equals(TipoSiteRA.getTipoSiteRA(idSite).getIdTipoRA())) {
                        continue;
                    }
                    for (nPosLinea = 0; nPosLinea < nLineas; nPosLinea++) {
                        lineaConf = lineasConf.get(nPosLinea);

                        if (serie.getIdSerie().equals(lineaConf.getIdSerie())) {
                            break;
                        }
                    }

                    if (nPosLinea == nLineas) { //No hay configuración para la serie
                        System.out.println("No se encuentra una configuración para la serie \"" + serie.getDescripcion() + "\"");
                        continue;
                    }

                    slope = 1.0;
                    offset = 0.0;
                    slopeP = lineaConf.getSlopeP();
                    offsetP = lineaConf.getOffsetP();
                    slopeC = slopeP;
                    offsetC = offsetP;

                    certificado = lineaConf.getCertificado();

                    if (certificado != null && !certificado.trim().isEmpty() && !certificado.trim().contentEquals("-")) {
                        slopeC = lineaConf.getSlope();
                        offsetC = lineaConf.getOffset();
                    }

                    if (!slopeC.equals(slopeP) || !offsetC.equals(offsetP)) {
                        if (nomVariable.trim().contentEquals(Variable.TIPO_DIRECCION)) {
                            slopeP = LineaConfiguracion.SLOPE_P_DIRECCION;
                        }
                        slope = slopeC / slopeP;
                        offset = offsetC - slopeC * offsetP / slopeP;
                    }

                    codigos.add(serie.getCodigo());
                    canales.add(Integer.parseInt(lineaConf.getCanal().trim().replaceAll("Canal", ""))); //Canal<num_canal> --> num_canal
                    slopes.add(slope);
                    offsets.add(offset);
                    nomVariables.add(nomVariable);
                }

                lineaConf = LineaConfiguracion.getLineaConfPorAsuntoSiteConfSerie(idAsunto, idSite, idConfig, LineaConfiguracion.ID_SERIE_T);
                if (lineaConf != null) {
                    cotaT = lineaConf.getCota();
                }
                lineaConf = LineaConfiguracion.getLineaConfPorAsuntoSiteConfSerie(idAsunto, idSite, idConfig, LineaConfiguracion.ID_SERIE_P);
                if (lineaConf != null) {
                    cotaP = lineaConf.getCota();
                }
                Declinacion declinacion = Declinacion.getDeclinacionPorAsuntoSiteConf(idAsunto, idSite, idConfig);
                if (declinacion != null) {
                    dec = declinacion.getDeclinacion();
                }
            }
        }

        idTipoRA = TipoSiteRA.getTipoSiteRA(idSite).getIdTipoRA();

        if (idTipoRA.equals(TipoRA.ID_TIPO_FFT)) {
            //Tratamiento especial para FFT
            Double frecIni = null, frecFin = null, valor;

            ArrayList<SerieRA2> seriesFFT = SerieRA2.getSeriesRA2PorTipo(TipoRA.ID_TIPO_FFT);
            int nSeriesFFT = seriesFFT != null ? seriesFFT.size() : 0;

            Descripcion descFFT;
            for (int i = 0; i < nSeriesFFT; i++) {
                serie = seriesFFT.get(i);

                descFFT = Descripcion.getDescripcion(idAsunto, serie.getIdSerie());
                valor = descFFT != null ? descFFT.getValor() : null;

                if (serie.getDescripcion().contains("Ini")) {
                    frecIni = valor;
                } else if (serie.getDescripcion().contains("Fin")) {
                    frecFin = valor;
                } else if (serie.getDescripcion().contains("Ori")) {
                    resFrecOri = valor;
                } else if (serie.getDescripcion().contains("Des")) {
                    resFrecDes = valor;
                }
            }

            int nCanalesFic = canalesFic.size();
            //Recorremos los canales del fichero para añadir aquellos que nos interesen
            String canalFic;
            Double valorCanalFic, valorCanalFicAnt = null;
            for (int i = 0; i < nCanalesFic; i++) {
                canalFic = canalesFic.get(i);
                valorCanalFic = null;

                try {
                    valorCanalFic = Double.parseDouble(canalFic);
                } catch (NumberFormatException e) {
                    continue;
                }

                //Si se ha podido convertir en Double es que es uno de los canales de frecuencias
                if (frecIni != null && frecIni > valorCanalFic) {
                    continue;
                }
                if (frecFin != null && frecFin < valorCanalFic) {
                    continue;
                }

                //Si ha pasado las validaciones, añadimos a la lista de códigos
                codigos.add(canalFic);
                canales.add(i);
                slopes.add(1.0);
                offsets.add(0.0);
                nomVariables.add(CAMPO_XML);

                valorCanalFicAnt = valorCanalFic;
            }

            //Cargamos la ponderación para FFT
            cargaPondDbZtoDbA(pondDbZtoDbA, codigos, nomVariables, ponderadoA);

        //Fin de tratamiento especial FFT
        } else if (idTipoRA.equals(TipoRA.ID_TIPO_OCT)) {
            pondDbZtoDbA = POND_OCT_Z_TO_A;
            
            if (ponderadoA) {
                Iterator it = pondDbZtoDbA.keySet().iterator();
                while (it.hasNext()) {
                    pondDbZtoDbA.put((String) it.next(), 0.0);
                }
            }
        }

        //Añadimos cotaT, cotaP, dec, hB
        varInOut.add(cotaT);
        varInOut.add(cotaP);
        varInOut.add(dec);
        varInOut.add(hB);

        return new ConfInsercionDatos(idConfig, codigos, nomVariables, canales, slopes, offsets, cotaT, cotaP, dec, hB, pondDbZtoDbA, resFrecOri, resFrecDes);
    }

	//Función para pasar de una resolución de origen a otra de menor resolución (datos cada más Hz, resoluciónDes >= resolucionOri).
	private static LinkedHashMap<Double, Double> cambiarResolucion(LinkedHashMap<Double, Double> espectroFFT, Double resolucionOri, Double resolucionDes) {
		LinkedHashMap<Double, Double> res = null;
		
		if (resolucionOri == null)
			resolucionOri = RES_FREC_ORI_DEF;
		if (resolucionDes == null)
			resolucionDes = RES_FREC_DES_DEF;

		if (espectroFFT != null && !resolucionOri.equals(resolucionDes)) {
			Iterator it = espectroFFT.entrySet().iterator();
			Entry<Double, Double> entry;

			Double nivelOri, frecOri, nivelDes, frecDes, nivelAcum;
			Double iniFrecOri, finFrecOri, iniFrecDes, finFrecDes;
			res = new LinkedHashMap<Double, Double>();

			iniFrecDes = -resolucionDes / 2.0;
			while (it.hasNext()) {
				entry = (Entry<Double, Double>) it.next();
				frecOri = entry.getKey();
				nivelOri = entry.getValue();

				iniFrecOri = frecOri - resolucionOri / 2.0;
				finFrecOri = frecOri + resolucionOri / 2.0;

				while (iniFrecDes + resolucionDes <= iniFrecOri)
					iniFrecDes += resolucionDes;

				finFrecDes = iniFrecDes + resolucionDes;

				frecDes = iniFrecDes + resolucionDes / 2.0;
				nivelAcum = res.get(frecDes);

				nivelDes = (nivelAcum != null ? nivelAcum : 0.0) + ((finFrecDes - iniFrecOri) / resolucionOri) * nivelOri;

				if (nivelDes != null)
					res.put(frecDes, TratDecimales.round(nivelDes, TratDecimales.DEC_VARIABLE_RA));

				if (finFrecOri - finFrecDes < 0) {
					frecDes = iniFrecDes + resolucionDes / 2.0;
					nivelAcum = res.get(frecDes);
				} else {
					frecDes = finFrecDes + resolucionDes / 2.0;
					nivelAcum = res.get(frecDes);
				}

				nivelDes = (nivelAcum != null ? nivelAcum : 0.0) + ((finFrecOri - finFrecDes) / resolucionOri) * nivelOri;

				if (nivelDes != null && !finFrecOri.equals(finFrecDes))
					res.put(frecDes, TratDecimales.round(nivelDes, TratDecimales.DEC_VARIABLE_RA));
			}
		} else
			res = espectroFFT;
		
		return res;
	}

    public static int insertDatos(String tipoTabla, Integer idAsunto, Integer idSite, Integer rF, Integer tipoVel, ArrayList<ArrayList<String>> datos, ConfInsercionDatos conf) throws SQLException, NoSuchFieldException, ParserConfigurationException, TransformerException {
        int res = 0;

        int nDatos = datos.size();

        if (nDatos > 0 && tipoTabla != null && idAsunto != null) { //Si no, no hay nada que insertar o no se puede
            Integer idConfig = conf.getIdConfig();
            ArrayList<String> codigos = conf.getCodigos();
            ArrayList<String> nomVariables = conf.getNomVariables();
            ArrayList<Integer> canales = conf.getCanales();
            ArrayList<Double> slopes = conf.getSlopes();
            ArrayList<Double> offsets = conf.getOffsets();
            Double cotaT = conf.getCotaT();
            Double cotaP = conf.getCotaP();
            Double dec = conf.getDec();
            Double hB = conf.getHB();
            HashMap<String, Double> pondDbZtoDbA = conf.getPondDbZtoDbA();
			Double resolucionOri = conf.getResolucionOri();
			Double resolucionDes = conf.getResolucionDes();

            setTabla(tipoTabla, idAsunto);

            if (idConfig != 0) {
                InteraccionBD interBD = new InteraccionBD();

                String valores = "";
                ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
                ArrayList<String> campos = new ArrayList<String>();
                ArrayList<String> autoInc = new ArrayList<String>();
                ArrayList<String> condAutoInc = new ArrayList<String>();
                ArrayList<String> dato;
                Double valorDato, tDato, pDato, tBuje, pBuje;
                long fechaMillis;
                String codigo;
                DatosRA2 datoRA;

                tDato = pDato = null;

                int nCodigos = codigos.size();

                LinkedHashMap<Double, Double> espectroFFT;

                //Vamos insertando los datos uno a uno
                for (int i = 0; i < nDatos; i++) {
                    valores = "";
                    paramsPS = new ArrayList<Object[]>();
                    campos = new ArrayList<String>();
                    autoInc = new ArrayList<String>();
                    condAutoInc = new ArrayList<String>();

                    espectroFFT = null;

                    dato = datos.get(i);

                    fechaMillis = Long.parseLong(dato.get(0));

                    ////////////////////////////////////////////////////////////
                    //DATOS BÁSICOS/////////////////////////////////////////////
                    ////////////////////////////////////////////////////////////

                    //Comprobamos que no exista ya un campo en la BD con el mismo idDato
                    datoRA = DatosRA2.getDatoPorFecha(tipoTabla, idAsunto, fechaMillis);

                    if (datoRA != null) //Dato encontrado, no se puede inserta
                    {
                        continue;                    //idDato --> Se tiene que autoincrementar
                    }
                    valores = InteraccionBD.anadeCampoValorAutoInc(valores, CAMPO_ID_DATO);
                    campos.add(CAMPO_ID_DATO);
                    autoInc.add(CAMPO_ID_DATO);
                    condAutoInc.add(null);

                    //FechaHora
                    valores = InteraccionBD.anadeCampoValor(valores, paramsPS, fechaMillis);
                    campos.add(CAMPO_FECHA_HORA);

                    //Válido --> Por defecto será válido
                    valores = InteraccionBD.anadeCampoValor(valores, paramsPS, 1);
                    campos.add(CAMPO_VALIDO);

                    //Ruido de Fondo
                    valores = InteraccionBD.anadeCampoValor(valores, paramsPS, rF);
                    campos.add(CAMPO_RF);

                    ////////////////////////////////////////////////////////////
                    //DATOS PARTIULARES/////////////////////////////////////////
                    ////////////////////////////////////////////////////////////

                    tBuje = pBuje = null;

                    for (int j = 0; j < nCodigos; j++) {
                        codigo = (String) codigos.get(j);
                        
                        if (dato.get(canales.get(j)).isEmpty())
                            continue;
                        
                        valorDato = Double.parseDouble(dato.get(canales.get(j)));
                        
                        if (nomVariables.get(j).contentEquals(Variable.TIPO_VELOCIDAD)) {
                            if (tipoVel.equals(VEL_M_S_SB)) { //Aplicamos corrección si viene en m/s porque trunca decimales el soundbook
                                valorDato = valorDato * 0.05 / 0.048;
                            } else if (tipoVel.equals(VEL_RPM)) { //Modificamos la velocidad si viene en rpm para pasarla a m/s
                                valorDato = valorDato / 60.0;
                            }
                        }

                        //Realizamos corrección
                        valorDato = valorDato * slopes.get(j) + offsets.get(j);

                        //Recogemos datos para tBuje
                        if (codigo.contentEquals("T")) {
                            tDato = valorDato;
                        }

                        //Recogemos datos para pBuje
                        if (codigo.contentEquals("P")) {
                            pDato = valorDato;
                        }

                        //Corrección por declinación si es veleta y cierre de rango
                        if (nomVariables.get(j).contentEquals(Variable.TIPO_DIRECCION)) {
                            if (valorDato != null && dec != null) {
                                valorDato += dec;

                                while (valorDato >= 360) {
                                    valorDato -= 360;
                                }
                                while (valorDato < 0) {
                                    valorDato += 360;
                                }
                            }
                        }

                        //Cierre de rango para humedad
                        if (nomVariables.get(j).contentEquals(Variable.TIPO_HUMEDAD)) {
                            if (valorDato > 100.0 && valorDato <= 105.0) {
                                valorDato = 100.0;
                            }
                        }

                        valorDato = TratDecimales.round(valorDato, TratDecimales.DEC_VARIABLE_RA);

                        //Convertimos dbZ a dbA
                        if (pondDbZtoDbA.containsKey(codigo)) {
                            valorDato = pondDbZtoDbA.get(codigo) != null ? TratDecimales.round(valorDato + pondDbZtoDbA.get(codigo), TratDecimales.DEC_VARIABLE_RA) : null;
                        }
                        
                        //Es una parte de un campo xml
                        if (nomVariables.get(j).contentEquals(CAMPO_XML)) {
                            if (valorDato != null) {
                                if (espectroFFT == null) {
                                    espectroFFT = new LinkedHashMap<Double, Double>();
                                }
                                espectroFFT.put(Double.parseDouble(codigo), valorDato);
                            }
                            continue;
                        }

                        //Campo particular
                        valores = InteraccionBD.anadeCampoValor(valores, paramsPS, valorDato);
                        campos.add(codigo);
                    }

                    if (espectroFFT != null) {//Hay datos de tipo FFT
                        valores = InteraccionBD.anadeCampoValorTipo(valores, paramsPS, InteraccionXML.creaXml(cambiarResolucion(espectroFFT, resolucionOri, resolucionDes)), SQLXML.class.getSimpleName());
                        campos.add(CAMPO_XML);
                    }

                    ////////////////////////////////////////////////////////////
                    //RESTO DATOS BÁSICOS///////////////////////////////////////
                    ////////////////////////////////////////////////////////////

                    //tBuje
                    //Corregimos tBuje
                    if (tDato != null && hB != null && cotaT != null) {
                        tBuje = TratDecimales.round(tDato - (0.65 * (hB - cotaT) / 100), TratDecimales.DEC_VARIABLE_RA);
                    }
                    if (tBuje != null) {
                        valores = InteraccionBD.anadeCampoValor(valores, paramsPS, tBuje);
                        campos.add(CAMPO_T_BUJE);
                    }

                    //pBuje
                    //Corregimos pBuje
                    if (pDato != null && hB != null && cotaP != null) {
                        pBuje = TratDecimales.round(pDato * Math.exp(-(9.80665 / 287.05287 / (273.15 + tBuje)) * (hB - cotaP)), TratDecimales.DEC_VARIABLE_RA);
                    }
                    if (pBuje != null) {
                        valores = InteraccionBD.anadeCampoValor(valores, paramsPS, pBuje);
                        campos.add(CAMPO_P_BUJE);
                    }

                    //Insrtamos en la BD
                    res += interBD.insertDatosTabla(TABLA, campos, valores, paramsPS, null, autoInc, condAutoInc);
                }
            }
        }

        return res;
    }
    //Función para añadir una dato a la BD
    public static int updateDatoBasico(String tipoTabla, Integer idAsunto, Integer idDato, Long fechaHora, Integer valido, Integer rF, Double tBuje, Double pBuje, Integer idDatoVal, Long fechaHoraVal, Integer validoVal, Integer rFVal, Double tBujeVal, Double pBujeVal, String sqlExtra) throws SQLException {
        InteraccionBD interBD = new InteraccionBD();

        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        ArrayList<String> campos = new ArrayList<String>();
        int res = 0;
        if (tipoTabla != null && idAsunto != null) {
            setTabla(tipoTabla, idAsunto);

            if (idDatoVal != null && !idDatoVal.equals(idDato)) {
                InteraccionBD.anadeCampoValor(null, paramsPS, idDatoVal);
                campos.add(CAMPO_ID_DATO);
            }
            if (fechaHoraVal != null && !fechaHoraVal.equals(fechaHora)) {
                InteraccionBD.anadeCampoValor(null, paramsPS, fechaHoraVal);
                campos.add(CAMPO_FECHA_HORA);
            }
            if (validoVal != null && !validoVal.equals(valido)) {
                InteraccionBD.anadeCampoValor(null, paramsPS, validoVal);
                campos.add(CAMPO_VALIDO);
            }
            if (rFVal != null && !rFVal.equals(rF)) {
                InteraccionBD.anadeCampoValor(null, paramsPS, rFVal);
                campos.add(CAMPO_RF);
            }
            if (tBujeVal != null && !tBujeVal.equals(tBuje)) {
                InteraccionBD.anadeCampoValor(null, paramsPS, tBujeVal);
                campos.add(CAMPO_T_BUJE);
            }
            if (pBujeVal != null && !pBujeVal.equals(pBuje)) {
                InteraccionBD.anadeCampoValor(null, paramsPS, pBujeVal);
                campos.add(CAMPO_P_BUJE);
            }

            //Condiciones de actualización
            condicion = getCondicion(idDato, fechaHora, valido, rF, tBuje, pBuje, paramsPS);

            interBD.inicioTransaccion();
            res = interBD.updateDatosTabla(TABLA, campos, condicion, paramsPS, sqlExtra);
            interBD.finTransaccion();
        }

        return res;
    }

    public static int updateDatoBasico(String tipoTabla, Integer idAsunto, DatosRA2 datoViejo, DatosRA2 datoNuevo, String sqlExtra) throws SQLException {
        return updateDatoBasico(tipoTabla, idAsunto, datoViejo.idDato, datoViejo.fechaHora, datoViejo.valido, datoViejo.rF, datoViejo.tBuje, datoViejo.pBuje, datoNuevo.idDato, datoNuevo.fechaHora, datoNuevo.valido, datoNuevo.rF, datoNuevo.tBuje, datoNuevo.pBuje, sqlExtra);
    }

    public static int setDatoValido(String tipoTabla, Integer idAsunto, Integer idSite, Integer idDato, Integer valido, Integer validoSys) throws SQLException {
		if (tipoTabla != null && idAsunto != null) {
            setVistaAux(tipoTabla, idAsunto, idSite);
	
			InteraccionBD interBD = new InteraccionBD();
			ArrayList<String> campos = new ArrayList<String>();
			ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();

			InteraccionBD.anadeCampoValor(null, paramsPS, validoSys);
			campos.add(CAMPO_VALIDO_SYS);
			String condicion = InteraccionBD.anadeCampoCondicion(null, paramsPS, CAMPO_ID_DATO_SYS, "=", idDato);
		    
			interBD.updateDatosTabla(VALI_SYS_AUX + VISTA_AUX, campos, condicion, paramsPS, null);
		}
		
        return updateDatoBasico(tipoTabla, idAsunto, idDato, null, null, null, null, null, null, null, valido, null, null, null, null);
    }

    public static int setDatoValidoNorma(Integer idNorma, String tipoTabla, Integer idAsunto, Integer idSite, Integer idDato, Integer valido, Integer validoSys) throws SQLException, NoSuchFieldException {
		int res = setDatoValido(tipoTabla, idAsunto, idSite, idDato, valido, validoSys);

		if (res > 0 && idNorma.equals(NormaRA.ID_NORMA_IEC_3_0)) {
			ArrayList<TipoSiteRA> tiposSite = null;
			TipoSiteRA tipoSite;
			
			if (tipoTabla.contentEquals(Auxiliares.TIPO_SPL))
				tiposSite = TipoSiteRA.getTiposSiteRA(null, TipoRA.ID_TIPO_OCT, null, null, Boolean.TRUE);
			else if (tipoTabla.contentEquals(Auxiliares.TIPO_OCT))
				tiposSite = TipoSiteRA.getTiposSiteRA(null, TipoRA.ID_TIPO_SPL, null, null, Boolean.TRUE);
			
			int nTipos = tiposSite != null ? tiposSite.size() : 0;
			
			if (nTipos > 0) {
				DatosRA2 datoOrig, dato;
				String tipoTablaAux;
				datoOrig = DatosRA2.getDatoPorId(tipoTabla, idAsunto, idDato);
				
				for (int i = 0; i < nTipos; i++) {
					tipoSite = tiposSite.get(i);

					tipoTablaAux = TipoRA.getTipoRAPorIdSite(tipoSite.getIdSite()).getSufijo();

					dato = DatosRA2.getDatoPorFecha(tipoTablaAux, idAsunto, datoOrig.getFechaHora());

					if (DatosRA2.setDatoValido(tipoTablaAux, idAsunto, tipoSite.getIdSite(), dato.getIdDato(), valido, validoSys) <= 0) {
						res = 0;
						break;
					}
				}
			}
		}

		return res;
    }

    public static int setDatoXMLValido(String tipoTabla, Integer idAsunto, Integer idDato, Integer idDatoXML, Boolean valido) throws SQLException {
        InteraccionBD interBD = new InteraccionBD();

        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        ArrayList<String> campos = new ArrayList<String>();
        int res = 0;

        String campoComplejo = InteraccionBD.IGNORAR_PARAM_PS + CAMPO_XML + ".modify('declare namespace " + InteraccionXML.TAG_NAMESPACE + "=\"" + InteraccionXML.RUTA_NAMESPACE + "\"; ";
        campoComplejo += "replace value of (/" + InteraccionXML.TAG_ESPECTRO;
        campoComplejo += "/" + InteraccionXML.TAG_DATOS + "[@" + InteraccionXML.TAG_ID_DATO + "=" + idDatoXML + "]";
//        campoComplejo += "/" + InteraccionXML.TAG_VALIDO + ")[1] ";
        campoComplejo += "with " + valido + "()')";

        campos.add(campoComplejo);

        //Condiciones de actualización
        condicion = getCondicion(idDato, null, null, null, null, null, paramsPS);

        interBD.inicioTransaccion();
        res = interBD.updateDatosTabla(TABLA, campos, condicion, paramsPS, null);
        interBD.finTransaccion();

        return res;
    }

    public static int updateDatos(String tipoTabla, Integer idAsunto, Integer idSite, Integer rF, Integer tipoVel, ArrayList<ArrayList<String>> datos, ConfInsercionDatos conf) throws SQLException, NoSuchFieldException, ParserConfigurationException, TransformerException {
        int res = 0;

        int nDatos = datos.size();

        if (nDatos > 0 && tipoTabla != null && idAsunto != null) { //Si no, no hay nada que insertar o no se puede
            Integer idConfig = conf.getIdConfig();
            ArrayList<String> codigos = conf.getCodigos();
            ArrayList<String> nomVariables = conf.getNomVariables();
            ArrayList<Integer> canales = conf.getCanales();
            ArrayList<Double> slopes = conf.getSlopes();
            ArrayList<Double> offsets = conf.getOffsets();
            Double cotaT = conf.getCotaT();
            Double cotaP = conf.getCotaP();
            Double dec = conf.getDec();
            Double hB = conf.getHB();
            HashMap<String, Double> pondDbZtoDbA = conf.getPondDbZtoDbA();
			Double resolucionOri = conf.getResolucionOri();
			Double resolucionDes = conf.getResolucionDes();

            setTabla(tipoTabla, idAsunto);

            if (idConfig != 0) {
                InteraccionBD interBD = new InteraccionBD();

                String condicion;
                ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
                ArrayList<String> campos = new ArrayList<String>();
                ArrayList<String> dato;
                Double valorDato, tDato, pDato, tBuje, pBuje;
                long fechaMillis;
                String codigo;
                DatosRA2 datoRA;
                Integer idDato;

                tDato = pDato = null;

                int nCodigos = codigos.size();

                LinkedHashMap<Double, Double> espectroFFT;

                //Vamos actulizando los datos uno auno
                for (int i = 0; i < nDatos; i++) {
                    paramsPS = new ArrayList<Object[]>();
                    campos = new ArrayList<String>();
                    condicion = "";

                    espectroFFT = null;

                    dato = datos.get(i);

                    fechaMillis = Long.parseLong(dato.get(0));

                    ////////////////////////////////////////////////////////////
                    //DATOS BÁSICOS/////////////////////////////////////////////
                    ////////////////////////////////////////////////////////////

                    //idDato --> Es campo clave para la actualización
                    datoRA = DatosRA2.getDatoPorFecha(tipoTabla, idAsunto, fechaMillis);

                    if (datoRA == null) //Dato no encontrado, no se puede actualizar
                    {
                        continue;                    //FechaHora
                    }
                    InteraccionBD.anadeCampoValor(null, paramsPS, fechaMillis);
                    campos.add(CAMPO_FECHA_HORA);

                    //Válido --> Por defecto será válido
                    InteraccionBD.anadeCampoValor(null, paramsPS, 1);
                    campos.add(CAMPO_VALIDO);

                    //Ruido de Fondo
                    InteraccionBD.anadeCampoValor(null, paramsPS, rF);
                    campos.add(CAMPO_RF);

                    ////////////////////////////////////////////////////////////
                    //DATOS PARTIULARES/////////////////////////////////////////
                    ////////////////////////////////////////////////////////////

                    tBuje = pBuje = null;

                    for (int j = 0; j < nCodigos; j++) {
                        codigo = (String) codigos.get(j);
                        
                        if (dato.get(canales.get(j)).isEmpty())
                            continue;
                        
                        valorDato = Double.parseDouble(dato.get(canales.get(j)));
                        
                        if (nomVariables.get(j).contentEquals(Variable.TIPO_VELOCIDAD)) {
                            if (tipoVel.equals(VEL_M_S_SB)) { //Aplicamos corrección si viene en m/s porque trunca decimales el soundbook
                                valorDato = valorDato * 0.05 / 0.048;
                            } else if (tipoVel.equals(VEL_RPM)) { //Modificamos la velocidad si viene en rpm para pasarla a m/s
                                valorDato = valorDato / 60.0;
                            }
                        }

                        //Realizamos corrección
                        valorDato = valorDato * slopes.get(j) + offsets.get(j);

                        //Recogemos datos para tBuje
                        if (codigo.contentEquals("T")) {
                            tDato = valorDato;
                        }

                        //Recogemos datos para pBuje
                        if (codigo.contentEquals("P")) {
                            pDato = valorDato;
                        }

                        //Corrección por declinación si es veleta y cierre de rango
                        if (nomVariables.get(j).contentEquals(Variable.TIPO_DIRECCION)) {
                            if (valorDato != null && dec != null) {
                                valorDato += dec;

                                while (valorDato >= 360) {
                                    valorDato -= 360;
                                }
                                while (valorDato < 0) {
                                    valorDato += 360;
                                }
                            }
                        }

                        //Cierre de rango para humedad
                        if (nomVariables.get(j).contentEquals(Variable.TIPO_HUMEDAD)) {
                            if (valorDato > 100.0 && valorDato <= 105.0) {
                                valorDato = 100.0;
                            }
                        }

                        valorDato = TratDecimales.round(valorDato, TratDecimales.DEC_VARIABLE_RA);

                        //Convertimos dbZ a dbA
                        if (pondDbZtoDbA.containsKey(codigo)) {
                            valorDato = pondDbZtoDbA.get(codigo) != null ? TratDecimales.round(valorDato + pondDbZtoDbA.get(codigo), TratDecimales.DEC_VARIABLE_RA) : 0.0;
                        }
                        //Es una parte de un campo xml
                        if (nomVariables.get(j).contentEquals(CAMPO_XML)) {
                            if (valorDato != null) {
                                if (espectroFFT == null) {
                                    espectroFFT = new LinkedHashMap<Double, Double>();
                                }
                                espectroFFT.put(Double.parseDouble(codigo), valorDato);
                            }
                            continue;
                        }

                        //Campo particular
                        InteraccionBD.anadeCampoValor(null, paramsPS, valorDato);
                        campos.add(codigo);
                    }

                    if (espectroFFT != null) {//Hay datos de tipo FFT
                        InteraccionBD.anadeCampoValorTipo(null, paramsPS, InteraccionXML.creaXml(cambiarResolucion(espectroFFT, resolucionOri, resolucionDes)), SQLXML.class.getSimpleName());
                        campos.add(CAMPO_XML);
                    }

                    ////////////////////////////////////////////////////////////
                    //RESTO DATOS BÁSICOS///////////////////////////////////////
                    ////////////////////////////////////////////////////////////
                    //tBuje
                    //Corregimos tBuje
                    if (tDato != null && hB != null && cotaT != null) {
                        tBuje = TratDecimales.round(tDato - (0.65 * (hB - cotaT) / 100), TratDecimales.DEC_VARIABLE_RA);
                    }
                    if (tBuje != null) {
                        InteraccionBD.anadeCampoValor(null, paramsPS, tBuje);
                        campos.add(CAMPO_T_BUJE);
                    }

                    //pBuje
                    //Corregimos pBuje
                    if (pDato != null && hB != null && cotaP != null) {
                        pBuje = TratDecimales.round(pDato * Math.exp(-(9.80665 / 287.05287 / (273.15 + tBuje)) * (hB - cotaP)), TratDecimales.DEC_VARIABLE_RA);
                    }
                    if (pBuje != null) {
                        InteraccionBD.anadeCampoValor(null, paramsPS, pBuje);
                        campos.add(CAMPO_P_BUJE);
                    }

                    //Añadimos la condición de actualización
                    idDato = datoRA.getIdDato();
                    condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_ID_DATO, "=", idDato);

                    //Insrtamos en la BD
                    res += interBD.updateDatosTabla(TABLA, campos, condicion, paramsPS, null);
                }
            }
        }

        return res;
    }
    //Función para añadir/modificar una dato a la BD
    public static int insertOrUpdateDatoBasico(String tipoTabla, Integer idAsunto, Integer idDato, Long fechaHora, Integer valido, Integer rF, Double tBuje, Double pBuje,
            Integer idDatoVal, Long fechaHoraVal, Integer validoVal, Integer rFVal, Double tBujeVal, Double pBujeVal, String sqlExtra) throws SQLException {
        int res;

        res = updateDatoBasico(tipoTabla, idAsunto, idDato, fechaHora, valido, rF, tBuje, pBuje, idDatoVal, fechaHoraVal, validoVal, rFVal, tBujeVal, pBujeVal, sqlExtra);

        if (res < 0) {
            res = insertDatoBasico(tipoTabla, idAsunto, idDatoVal, fechaHoraVal, validoVal, rFVal, tBujeVal, pBujeVal, sqlExtra);
        }
        return res;
    }

    public static int insertOrUpdateDatoBasico(String tipoTabla, Integer idAsunto, DatosRA2 datoViejo, DatosRA2 datoNuevo, String sqlExtra) throws SQLException {
        return insertOrUpdateDatoBasico(tipoTabla, idAsunto, datoViejo.idDato, datoViejo.fechaHora, datoViejo.valido, datoViejo.rF, datoViejo.tBuje, datoViejo.pBuje, datoNuevo.idDato, datoNuevo.fechaHora, datoNuevo.valido, datoNuevo.rF, datoNuevo.tBuje, datoNuevo.pBuje, sqlExtra);
    }

    //Función para añadir/modificar una dato a la BD
    public static int insertOrUpdateDatos(String tipoTabla, Integer idAsunto, Integer idSite, Integer rF, Integer tipoVel, ArrayList<ArrayList<String>> datos, ConfInsercionDatos conf) throws SQLException, NoSuchFieldException, ParserConfigurationException, TransformerException {
        int res, resUpdate = 0, resInsert = 0, nDatos;
        InteraccionBD interBD = new InteraccionBD();

        nDatos = datos.size();

        interBD.inicioTransaccion();

        resUpdate = updateDatos(tipoTabla, idAsunto, idSite, rF, tipoVel, datos, conf);

        if (resUpdate != nDatos) //No se han actualizado todos los campos, habrá que añadir
        {
            resInsert = insertDatos(tipoTabla, idAsunto, idSite, rF, tipoVel, datos, conf);
        }
        res = resUpdate + resInsert;

        if (res != nDatos) { //Si no están insertados o actualizados todos los datos en la BD, hacemos rollback
            interBD.rollback();

            res = 0;
        }

        interBD.finTransaccion();

        return res;
    }
    //Función para insertar datos desde fichero
    public static boolean anadeDatosFic(String tipoTabla, Integer idAsunto, Integer idSite, Boolean ponderadoA, Boolean rF, Integer tipoVel, String rutaFic, Integer idUsuario, ArrayList<SerieRA2> seriesDesc, ArrayList<String> nomVariablesDesc, ArrayList<LineaConfiguracion> lineasConfTot) throws SQLException, NoSuchFieldException, IOException, ParserConfigurationException, TransformerException {
        boolean res = false;
        int resAux, nDatos;
        Integer rFAux;
        long fechaIniMillis, fechaIniInser, fechaFinInser;
        ArrayList<ArrayList<String>> datos;
        InteraccionFic interFic = new InteraccionFic(rutaFic, InteraccionFic.READ);
        String sepCampos = "";

        if (tipoTabla.contentEquals(Auxiliares.TIPO_SPL)) {
            sepCampos = SEP_CAMPOS_SPL;
        } else if (tipoTabla.contentEquals(Auxiliares.TIPO_OCT)) {
            sepCampos = SEP_CAMPOS_OCT;
        } else if (tipoTabla.contentEquals(Auxiliares.TIPO_FFT)) {
            sepCampos = SEP_CAMPOS_FFT;
        }
        fechaIniMillis = interFic.leeFechaInicio(sepCampos);
        datos = interFic.leeDatos(fechaIniMillis, sepCampos);
        ArrayList<String> canales = interFic.leeCanales(sepCampos);

        interFic.finOpFichero();

        //Obtenemos la configuración del punto de medida para la primera fecha del fichero           
        long desdeFecha = Long.parseLong(datos.get(0).get(0));
        ConfInsercionDatos conf = preparaDatosInsertUpdate(desdeFecha, idAsunto, idSite, canales, seriesDesc, nomVariablesDesc, lineasConfTot, ponderadoA);

        if (rF != null && rF.booleanValue() == true) {
            rFAux = 1;
        } else {
            rFAux = 0;
        }
        resAux = insertOrUpdateDatos(tipoTabla, idAsunto, idSite, rFAux, tipoVel, datos, conf);

        if (resAux > 0 && datos != null) {
            res = true;

            nDatos = datos.size();

            fechaIniInser = Long.parseLong(datos.get(0).get(0));
            fechaFinInser = Long.parseLong(datos.get(nDatos - 1).get(0));

            InsercionRA.insertInsercion(idAsunto, idSite, null, InsercionRA.FASE_RA, rutaFic, fechaIniInser, fechaFinInser, null, idUsuario, rFAux, null);
        }

        return res;
    }
    //Función para eliminar Datos que se ajustan a los limites pasados
    public static int deleteDatos(String tipoTabla, Integer idAsunto, Integer idDato, Long fechaHora, Integer valido, Integer rF, Double tBuje, Double pBuje, String sqlExtra) throws SQLException {
        InteraccionBD interBD = new InteraccionBD();

        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        int res = 0;

        if (tipoTabla != null && idAsunto != null) {
            setTabla(tipoTabla, idAsunto);

            condicion = getCondicion(idDato, fechaHora, valido, rF, tBuje, pBuje, paramsPS);

            interBD.inicioTransaccion();
            res = interBD.deleteDatosTabla(TABLA, condicion, paramsPS, sqlExtra);
            interBD.finTransaccion();
        }

        return res;
    }

    public static int deleteDatos(String tipoTabla, Integer idAsunto, DatosRA2 dato, String sqlExtra) throws SQLException {
        return deleteDatos(tipoTabla, idAsunto, dato.idDato, dato.fechaHora, dato.valido, dato.rF, dato.tBuje, dato.pBuje, sqlExtra);
    }
    //Función para añadir una descripcion a la BD
    public static int createDatos(String tipoTabla, Integer idAsunto, ArrayList<String> codVarNoAcus, ArrayList<String> codVarAcus, Boolean crearIndicesXml, String sqlExtra) throws SQLException, NoSuchFieldException, IOException {
        InteraccionBD interBD = new InteraccionBD();

        int res = 0;
        if (tipoTabla != null && idAsunto != null) {
            setTabla(tipoTabla, idAsunto);

            int nVarNoAcus = codVarNoAcus != null ? codVarNoAcus.size() : 0;
            int nVarAcus = codVarAcus != null ? codVarAcus.size() : 0;

            ArrayList<String[]> columnas = new ArrayList<String[]>();

            //Anadimos campos genéricos
            columnas.add(new String[]{CAMPO_ID_DATO, TIPO_ID_DATO, EXTRA_ID_DATO});
            columnas.add(new String[]{CAMPO_FECHA_HORA, TIPO_FECHA_HORA, EXTRA_FECHA_HORA});
            columnas.add(new String[]{CAMPO_VALIDO, TIPO_VALIDO, EXTRA_VALIDO});
            columnas.add(new String[]{CAMPO_RF, TIPO_RF, EXTRA_RF});

            //Variables no acústicas
            for (int i = 0; i < nVarNoAcus; i++) {
                columnas.add(new String[]{codVarNoAcus.get(i), TIPO_DECIMAL_DEF, EXTRA_DECIMAL_DEF});
            }

            //Variables acústicas
            if (tipoTabla.contentEquals(Auxiliares.TIPO_FFT)) { //Caso especial FFT
                columnas.add(new String[]{CAMPO_XML, TIPO_XML, EXTRA_XML});
            } else {
                for (int i = 0; i < nVarAcus; i++) {
                    columnas.add(new String[]{codVarAcus.get(i), TIPO_DECIMAL_DEF, EXTRA_DECIMAL_DEF});
                }
            }

            //Añadimos Tbuje y Pbuje
            columnas.add(new String[]{CAMPO_T_BUJE, TIPO_T_BUJE, EXTRA_T_BUJE});
            columnas.add(new String[]{CAMPO_P_BUJE, TIPO_P_BUJE, EXTRA_P_BUJE});

            if (interBD.existeTabla(TABLA)) {
                interBD.borraTabla(TABLA);
            }
            if (tipoTabla.contentEquals(Auxiliares.TIPO_FFT)) //Caso especial FFT, hay que crear si no existe el schema xml
            {
                InteraccionXML.creaFftXmlSchema();
            }
            res = interBD.creaTabla(TABLA, columnas);

            if (crearIndicesXml) {
                interBD.creaIndicesXml(TABLA, CAMPO_XML);
            }
        }

        return res;
    }

    public Object[] toObject() {
        return new Object[]{this.idDato, this.valido};
    }

    public static Object[] getCamposTabla() throws SQLException {
        InteraccionBD interBD = new InteraccionBD();

        return interBD.getCamposTabla(TABLA);
    }
    public static Object[] getCamposVistaAux() throws SQLException {
        InteraccionBD interBD = new InteraccionBD();

        return interBD.getCamposTabla(VISTA_AUX);
    }
    public static Object[] getCamposVistaNeta() throws SQLException {
        InteraccionBD interBD = new InteraccionBD();

        return interBD.getCamposTabla(VISTA_NETA);
    }
    //Función que devulve el sumatorio de una columna de Datos
    public static Double getSumatorioVar(ArrayList<DatosRA2> datos, String nomVariable) {
        Double res = null;
        Double valorVar;

        if (datos != null && !datos.isEmpty()) {
            DatosRA2 dato;
            int nDatos = datos.size();

            res = new Double(0);

            for (int i = 0; i < nDatos; i++) {
                dato = datos.get(i);

                if ((valorVar = (Double) dato.variables.get(nomVariable)) == null) {
                    valorVar = new Double(0);
                }
                res = TratDecimales.round(res + valorVar, TratDecimales.DEC_VARIABLE_RA);
            }
        }

        return res;
    }
    //Función que devulve el sumatorio de todas las columnas de Datos
    public static HashMap<String, Double> getSumatorioVar(ArrayList<DatosRA2> datos) {
        HashMap<String, Double> res = null;

        if (datos != null && !datos.isEmpty()) {
            Iterator it = null;
            Entry entry;
            Double valorVar, valorAcum;
            String nomVar;
            int nDatos = datos.size();


            res = new HashMap<String, Double>();

            if (nDatos > 0) {
                it = datos.get(0).variables.entrySet().iterator();
            }
            for (int i = 0; i < nDatos; i++) {
                while (it.hasNext()) {
                    entry = (Entry) it.next();

                    nomVar = (String) entry.getKey();
                    valorVar = (Double) entry.getValue();


                    if ((valorAcum = res.get(nomVar)) == null) {
                        valorAcum = new Double(0);
                    }
                    res.put(nomVar, TratDecimales.round(valorAcum + valorVar, TratDecimales.DEC_VARIABLE_RA));
                }
            }
        }

        return res;
    }

    //Función para actualizar la validez de los datos con respecto a las incidencias actuales
    public static int updateDatosIncidencias(String tabla, Integer idAsunto, Integer idSite, Integer valido) throws SQLException {
        int res = 0;
        if (idAsunto != null) {
            InteraccionBD interBD = new InteraccionBD();
            String condicion = "";
            ArrayList<String> campos = new ArrayList<String>();
            ArrayList<String> tablas = new ArrayList<String>();
            ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();

            InteraccionBD.anadeCampoValor(null, paramsPS, valido);
            campos.add(CAMPO_VALIDO);

            //Condicion de invalidacion
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, Incidencia.TABLA + "." + Incidencia.CAMPO_ID_ASUNTO, "=", idAsunto);
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, Incidencia.TABLA + "." + Incidencia.CAMPO_ID_SITE, "=", idSite);
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, tabla + "." + CAMPO_FECHA_HORA, InteraccionBD.ASIGNACION_CAMPOS + ">=", Incidencia.TABLA + "." + Incidencia.CAMPO_FECHA_D);
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, tabla + "." + CAMPO_FECHA_HORA, InteraccionBD.ASIGNACION_CAMPOS + "<=", Incidencia.TABLA + "." + Incidencia.CAMPO_FECHA_H);

            tablas.add(tabla);
            tablas.add(Incidencia.TABLA);

            res = interBD.updateDatosTablaFrom(tabla, tablas, campos, condicion, paramsPS, null);
        }
        return res;
    }
    
    //Función para obtener la velocidad de viento necesaria para obtener una potencia determinada
    public static Double getVelocidadPotencia(Integer idAsunto, Double densidad, Double potencia) throws SQLException, NoSuchFieldException {
        Double res = null;
        CurvaRA curva, curvaSig;
        
        if (idAsunto != null && densidad != null && potencia != null) {
            ArrayList<Object[]> regCP = CurvaRA.getRectas(CurvaRA.getCurvasPorIdAsuntoDensidad(idAsunto, densidad));
            int nRegCP = regCP != null ? regCP.size() : 0;

            for (int i = 0; i < nRegCP - 1; i++) {
                curva = (CurvaRA) regCP.get(i)[0];
                curvaSig = (CurvaRA) regCP.get(i + 1)[0];

                if (curva.getPot() <= potencia && curvaSig.getPot() > potencia) {
                    curvaSig = (CurvaRA) regCP.get(i + 1)[0];

                    res = potencia * (Double) regCP.get(i)[1] + (Double) regCP.get(i)[2];
                    break;
                }
            }
        }
        
        return res;
    }
    
    //Función para obtener la velocidad de viento necesaria para obtener un porcentaje de la potencia nominal
    public static Double getVelocidadPorcentajePotNominal(Integer idAsunto, Double densidad, Double porcentaje, Double potNominal) throws SQLException, NoSuchFieldException {
        return getVelocidadPotencia(idAsunto, densidad, potNominal*porcentaje);
    }
    
    //Función para actualizar la validez de sistema de los datos con respecto a la velocidad del límite de potencia superior
    public static int updateDatosVelocidadLimPotSup(String tabla, Integer idAsunto, Double densidad, Double potLimSup, Integer valido, double zRef, Double hB, double z0Ref) throws SQLException, NoSuchFieldException {
        InteraccionBD interBD = new InteraccionBD();
        String condicion = "";
        ArrayList<String> campos = new ArrayList<String>();
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        Double v_d_95 = getVelocidadPotencia(idAsunto, densidad, potLimSup); //Velocidad en condiciones de referencia y a altura de buje

        InteraccionBD.anadeCampoValor(null, paramsPS, valido);
        campos.add(CAMPO_VALIDO_SYS);

        //Condicion de invalidacion
        condicion = InteraccionBD.anadeCampoCondicion("", paramsPS, CAMPO_RF, "=", 0);
        condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_P_N, ">=", potLimSup);
		//Llevamos v_d_95 a altura de referencia
		v_d_95 = TratDecimales.round(v_d_95 * Math.log(zRef / z0Ref) / Math.log(hB / z0Ref), TratDecimales.DEC_VARIABLE_RA);
        condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_V_S, "<", v_d_95);

        return interBD.updateDatosTabla(tabla, campos, condicion, paramsPS, null);
    }
    
    //Función para obtener el límite inferior y superior de bines de velocidad para un asunto de forma que cubra el rango de [factormin * getVelocidadPorcentajePotNominal, factorMax * getVelocidadPorcentajePotNominal]
    public static Integer[] getLimitesBinesPorcPotencia(Integer idAsunto, Double densidad, Double porcentaje, Double potNominal, Double factorMin, double factorMax) throws SQLException, NoSuchFieldException {
        Integer[] res = null;

        Double velPorcentaje = getVelocidadPorcentajePotNominal(idAsunto, densidad, porcentaje, potNominal);
        
        if (velPorcentaje != null) {
            res = new Integer[]{((Double) Math.floor(factorMin * velPorcentaje)).intValue(), ((Double) Math.ceil(factorMax * velPorcentaje)).intValue()};
        }
        
        return res;
    }
    
    public static String getCte1() {
        String res;
        
        res = "((" + PRESION_DEF + " * (" + CAMPO_T_BUJE + " + 273))";
        res += " / ";
        res += "(" + CAMPO_P_BUJE + " * " + TEMP_DEF + "))";
        
        return res;
    }
    
    public static String getCte3(double zRef, Double hB, double z0Ref, double z0) {
        String res;

        res = "((LOG(" + zRef + " / " + z0Ref + ") * LOG(" + hB + " / " + z0 + "))";
        res += " / ";
        res += "(LOG(" + hB + " / " + z0Ref + ") * LOG(" + hB + " / " + z0 + ")))";

        return res;
    }

    public static String getCteAltOriAltDes(Double altOri, Double altDest, double z0Ref) {
        String res;

        res = "(LOG(" + altDest + " / " + z0Ref + ")";
        res += " / ";
        res += "LOG(" + altOri + " / " + z0Ref + "))";

        return res;
    }
    
    //Valida si puede crear y crea en caso afirmativola vista base Auxiliar de RA utilizada para el cálculo de la k
    public static String createVistaAux(Integer idNorma, String tipoTabla, Integer idAsunto, Integer idSite, int tipoCalculoVelocidad, int tipoCalculoPot, Integer idSerie, Double densidad, long fechaIni, long fechaFin, double[] sector, double zRef, double z0Ref, double z0, boolean resAlturaBuje, boolean esMiniAero, JDialog jd) throws SQLException, NoSuchFieldException {
		String res = "";
		InteraccionBD interBD = new InteraccionBD();

		if (tipoTabla != null && idAsunto != null) {
			setTabla(tipoTabla, idAsunto);
			setVistaAux(tipoTabla, idAsunto, idSite);

			//Obtenemos los campos de la tabla
			Object[] camposTabla = interBD.getCamposTabla(getTabla());

			//Guardamos los campos de la tabla en un hash para tener un rápida localización
			HashMap<String, String> mapCamposTabla = new HashMap<String, String>();

			int nCamposTabla = camposTabla.length;

			for (int i = 0; i < nCamposTabla; i++) {
				mapCamposTabla.put((String) camposTabla[i], null);
			}

			AerogeneradorRA aero;
			ArrayList<Object[]> regCP; //ArrayList<CurvaRA curva, Double pendiente, Double offset>
			ArrayList<Object[]> cotas; //ArrayList<long fechaIni, long fechaFin, Double cota>
			Integer idDatoIni, idDatoFin, lineas;
			Boolean regulacion;
			Double vIn, vCut, hB;
			String cte1, cte2, potNeta, pN, vD, vH, vHAux, vS;
			ArrayList<String> ctes2;
			String sqlCase, sqlCaseVD, sqlCaseVH, sqlCaseVS;
			Double pendiente, offset;
			int nCotas, nRegCP;
			CurvaRA curva1, curva2, curvaAux;
			DatosRA2 datoAux;
			long fechaAux, fechaIniCota, fechaFinCota;

			aero = AerogeneradorRA.getAeroPorId(AsuntoRA.getAsuntoPorId(idAsunto).getIdAero());

			fechaAux = getFechaIniDatos(tipoTabla, idAsunto);
			if (fechaAux > fechaIni) {
				fechaIni = fechaAux;
			}
			fechaAux = getFechaFinDatos(tipoTabla, idAsunto);
			if (fechaAux < fechaFin) {
				fechaFin = fechaAux;
			}
			idDatoIni = getDatoPorFecha(tipoTabla, idAsunto, getFechaPorFechaProximo(tipoTabla, idAsunto, fechaIni, ">=")).getIdDato();
			idDatoFin = getDatoPorFecha(tipoTabla, idAsunto, getFechaPorFechaProximo(tipoTabla, idAsunto, fechaFin, "<=")).getIdDato();

			vIn = aero.getVIn();
			vCut = aero.getVCut();
			hB = aero.getHB();

			regulacion = aero.getRegulacion();
			lineas = aero.getLineas();

			cotas = LineaConfiguracion.getCotasPorAsuntoSiteSerie(idAsunto, idSite, idSerie);

			regCP = CurvaRA.getRectas(CurvaRA.getCurvasPorIdAsuntoDensidad(idAsunto, densidad));

			//Constante1
			cte1 = getCte1();
			if (!res.contains(CAMPO_T_BUJE) && !mapCamposTabla.containsKey(CAMPO_T_BUJE)) {
				res += "<br>Falta el campo &lt;" + CAMPO_T_BUJE + "&gt; en  la tabla.";
			}
			if (!res.contains(CAMPO_P_BUJE) && !mapCamposTabla.containsKey(CAMPO_P_BUJE)) {
				res += "<br>Falta el campo &lt;" + CAMPO_P_BUJE + "&gt; en  la tabla.";
			}

			//Constante2
			ctes2 = new ArrayList<String>();
			
			nCotas = cotas != null ? cotas.size(): 0;

			for (int i = 0; i < nCotas; i++) {
				cte2 = "((LOG(" + zRef + " / " + z0Ref + ") * LOG(" + hB + " / " + z0 + "))";
				cte2 += " / ";
				cte2 += "(LOG(" + hB + " / " + z0Ref + ") * LOG(" + (Double) cotas.get(i)[1] + " / " + z0 + ")))";

				ctes2.add(cte2);
			}
		
			if (nCotas > 0) {
				if (zRef < 0.0) {
					res += "<br>El valor de zref no puede ser menor que 0.";
				}
				if (z0Ref <= 0.0) {
					res += "<br>El valor de z0ref no puede ser menor o igual que 0.";
				}
				if (hB <= 0.0) {
					res += "<br>El valor de hB no puede ser menor que 0.";
				}
				if (z0 <= 0.0) {
					res += "<br>El valor de z0 no puede ser menor o igual que 0.";
				}
				
				Double cota;
				for (int i = 0; i < nCotas; i++) {
					cota = (Double) cotas.get(i)[1];
					
					if (cota != null && cota <= 0.0) {
						res += "<br>El valor de la cota " + i + " no puede ser menor o igual que 0.";
					}
				}
			}

			ArrayList<String> campos = new ArrayList<String>();
			ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();

			//Nos interesan todos los campos de la tabla
			campos.add("*");
			if (idNorma.equals(NormaRA.ID_NORMA_IEC_3_0) && (tipoTabla.contentEquals(Auxiliares.TIPO_OCT) || tipoTabla.contentEquals(Auxiliares.TIPO_SPL))) {
				String tablaAuxOCT = getTabla().replace(tipoTabla, Auxiliares.TIPO_OCT);
				String tablaAuxSPL = getTabla().replace(tipoTabla, Auxiliares.TIPO_SPL);
				campos.add("(SELECT " + CAMPO_L_A_EQ_1 + " FROM " + tablaAuxSPL + " WHERE " + tablaAuxOCT + "." + CAMPO_FECHA_HORA + " = " + tablaAuxSPL + "." + CAMPO_FECHA_HORA + ") AS " + CAMPO_L_A_EQ_1);
			}

			////////////////////////////////////////////////////////////////////////
			////RESTO DE CAMPOS PARA VISTA////////////////////////////////////////
			////////////////////////////////////////////////////////////////////////

			pN = vD = vH = vS = "";

			//Potencia Neta
			if (lineas == 1) {
				if (tipoCalculoPot == POT_MEDIDA) {
					potNeta = CAMPO_P_ACTIVA;

					if (!res.contains(CAMPO_P_ACTIVA) && !mapCamposTabla.containsKey(CAMPO_P_ACTIVA)) {
						res += "<br>Falta el campo &lt;" + CAMPO_P_ACTIVA + "&gt; en  la tabla.";
					}
				} else {
					potNeta = "(" + CAMPO_TENSION  + " * " + CAMPO_INTENSIDAD + ")";

					if (!res.contains(CAMPO_TENSION) && !mapCamposTabla.containsKey(CAMPO_TENSION)) {
						res += "<br>Falta el campo &lt;" + CAMPO_TENSION + "&gt; en  la tabla.";
					}
					if (!res.contains(CAMPO_INTENSIDAD) && !mapCamposTabla.containsKey(CAMPO_INTENSIDAD)) {
						res += "<br>Falta el campo &lt;" + CAMPO_INTENSIDAD + "&gt; en  la tabla.";
					}
				}
			} else {
				potNeta = "(" + CAMPO_P_ACT_ROTOR + " + " + CAMPO_P_ACT_ESTATOR + ")";

				if (!res.contains(CAMPO_P_ACT_ROTOR) && !mapCamposTabla.containsKey(CAMPO_P_ACT_ROTOR)) {
					res += "<br>Falta el campo &lt;" + CAMPO_P_ACT_ROTOR + "&gt; en  la tabla.";
				}
				if (!res.contains(CAMPO_P_ACT_ESTATOR) && !mapCamposTabla.containsKey(CAMPO_P_ACT_ESTATOR)) {
					res += "<br>Falta el campo &lt;" + CAMPO_P_ACT_ESTATOR + "&gt; en  la tabla.";
				}
			}

			campos.add("CONVERT(" + TIPO_DECIMAL_DEF + ", " + potNeta + ") AS " + CAMPO_P_NETA);

			//VZref - Velocidad a altura de referencia
			String sqlCaseVZRef = "CONVERT(" + TIPO_DECIMAL_DEF + ", " + "CASE\n";
			for (int i = 0; i < nCotas; i++) {
				fechaIniCota = ((Long) cotas.get(i)[0]).longValue();
				fechaFinCota = getFechaFinDatos(tipoTabla, idAsunto);
				
				fechaAux = getFechaIniDatos(tipoTabla, idAsunto);
				if (fechaAux > fechaIniCota) {
					fechaIniCota = fechaAux;
				}
				
				sqlCaseVZRef += "\tWHEN " + CAMPO_FECHA_HORA + " >= " + fechaIniCota + " ";
				sqlCaseVZRef += "AND " + CAMPO_FECHA_HORA + " <= " + fechaFinCota + " THEN ";
				sqlCaseVZRef += CAMPO_V_Z + " * " + ctes2.get(i) + "\n";

				if (i == nCotas - 1) { //Última
					sqlCaseVZRef += "\tELSE " + VALOR_DECIMAL_DEF + "\n";
					sqlCaseVZRef += "END) AS " + CAMPO_V_Z_REF;

					campos.add(sqlCaseVZRef);
				}
			}

			if (nCotas == 0) {
				sqlCaseVZRef = "CONVERT(" + TIPO_DECIMAL_DEF + ", " + CAMPO_V_Z + ") AS " + CAMPO_V_Z_REF;
		
				campos.add(sqlCaseVZRef);
			}
			if (!res.contains(CAMPO_FECHA_HORA) && !mapCamposTabla.containsKey(CAMPO_FECHA_HORA)) {
				res += "<br>Falta el campo &lt;" + CAMPO_FECHA_HORA + "&gt; en  la tabla.";
			}
			if (!res.contains(CAMPO_V_Z) && !mapCamposTabla.containsKey(CAMPO_V_Z)) {
				res += "<br>Falta el campo &lt;" + CAMPO_V_Z + "&gt; en  la tabla.";
			}

			//Pn - Potencia normalizada
			if (!idNorma.equals(NormaRA.ID_NORMA_IEC_3_0)) {
				if (!regulacion) {
					pN = "(" + potNeta + " * " + cte1 + ")";
					vHAux = "";
				} else {
					pN = potNeta;
					vHAux = " * POWER(" + cte1 + ", " + TratDecimales.round(1 / 3.0, TratDecimales.DEC_POTENCIA) + ")";
				}
			} else {
				pN = potNeta;
				if (!regulacion)
					vHAux = "";
				else
					vHAux = " * POWER(" + cte1 + ", " + TratDecimales.round(1 / 3.0, TratDecimales.DEC_POTENCIA) + ")";
			}
			campos.add("CONVERT(" + TIPO_DECIMAL_DEF + ", " + pN + ") AS " + CAMPO_P_N);


			//VD - Velocidad derivada de curva de pontencia
			//VH - Velocidad a altura de buje
			//VS  - Velocidad estandarizada

			String velAlturaRef = sqlCaseVZRef.substring(0, sqlCaseVZRef.indexOf(" AS " + CAMPO_V_Z_REF));
			String velAlturaBuje = velAlturaRef + " * " + getCteAltOriAltDes(zRef, hB, z0Ref);

			switch (tipoCalculoVelocidad) {
				case VEL_DERIVADA_CP:
					nRegCP = regCP != null ? regCP.size() : 0;
					if (nRegCP > 1) {
						String vZaCP = velAlturaBuje + " * " + cte1; // Para llevar a condicones de referencia
			
						//Creamos tabla temporal para almacenar las pendientes y offsets
						//Borramos la tabla si ya existe
						interBD.borraTabla(CURVA_AUX + VISTA_AUX);
                        
                        ArrayList<String[]> columnas = new ArrayList<String[]>();
                        columnas.add(new String[]{CAMPO_DESDE_POT, TIPO_DECIMAL, EXTRA_NULO});
                        columnas.add(new String[]{CAMPO_HASTA_POT, TIPO_DECIMAL, EXTRA_NULO});
                        columnas.add(new String[]{CAMPO_PENDIENTE, TIPO_DECIMAL, EXTRA_NULO});
                        columnas.add(new String[]{CAMPO_OFFSET, TIPO_DECIMAL, EXTRA_NULO});
                        columnas.add(new String[]{CAMPO_RANGO_PERMITIDO, TIPO_INT, EXTRA_NULO});
                        interBD.creaTabla(CURVA_AUX + VISTA_AUX, columnas);

						Double tolerancia = null;
						Boolean esRangoPermitido;

						curva1 = (CurvaRA) regCP.get(0)[0];
						curva2 = (CurvaRA) regCP.get(1)[0];

						if (idNorma.equals(NormaRA.ID_NORMA_IEC_3_0) && (curva1.getIncert() == null || curva2.getIncert() == null)) {
							ArrayList<AsuntoIncert> incertidumbres = AsuntoIncert.getAsuntoIncerts(idAsunto, null, idNorma, TipoIncert.ID_TIPO_INCERT_VEL_DERIVADA, null, null, null, Boolean.TRUE);
							int nIncert = incertidumbres != null ? incertidumbres.size() : 0;
							Double clase = null;

							for (int j = 0; j < nIncert; j++) {
								if (incertidumbres.get(j).getDesdeFecha() <= fechaIni) {
									clase = incertidumbres.get(j).getValor();
									break;
								}
							}
							
							if (clase != null) {
								Double rango = null;
								do {
									RecogidaDatos rd = new RecogidaDatos("<html><center>La curva de pontencia correspondiente no contiene<br>"
											+ "datos de incertidumbres. Introduzca el rango de<br>"
											+ "medida de calibración de la CPA para calcular la<br>"
											+ "tolerancia como clase * rango / 100</center>", "Rango medida cal. CPA:");
									if (JOptionPane.showConfirmDialog(jd, rd, "Introduzca rango de medida de calibración de la CPA", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
										try {
										rango = Double.parseDouble(rd.getCampoRecoger());
										} catch (NumberFormatException ex) {
											MensajeApp.muestraError(rd, null, "Debe introducir un número");
										}
									} else
										return "<br>Proceso cancelado por el usuario.";
								} while (rango == null);

								tolerancia = clase * rango / 100.0;
							}

						}
                        
                        for (int i = 0; i < nRegCP - 1; i++) {
                            String valores = "";
                            ArrayList<String> camposAux = new ArrayList<String>();
                            ArrayList<Object[]> paramsPSAux = new ArrayList<Object[]>();

                            curva1 = (CurvaRA) regCP.get(i)[0];
                            curva2 = (CurvaRA) regCP.get(i + 1)[0];
                            pendiente = (Double) regCP.get(i)[1];
                            offset = (Double) regCP.get(i)[2];

							//tolerancia = PORC_TOLERANCIA * Math.max(curva1.getPot(), curva2.getPot());
							esRangoPermitido = esRangoPendientePositiva(curva1, curva2, tolerancia);

                            if (curva2.getPot() < curva1.getPot()) {
                                curvaAux = curva1;
                                curva1 = curva2;
                                curva2 = curvaAux;
                            }

                            valores = InteraccionBD.anadeCampoValor(valores, paramsPSAux, curva1.getPot());
                            camposAux.add(CAMPO_DESDE_POT);
                            valores = InteraccionBD.anadeCampoValor(valores, paramsPSAux, curva2.getPot());
                            camposAux.add(CAMPO_HASTA_POT);
                            valores = InteraccionBD.anadeCampoValor(valores, paramsPSAux, pendiente);
                            camposAux.add(CAMPO_PENDIENTE);
                            valores = InteraccionBD.anadeCampoValor(valores, paramsPSAux, offset);
                            camposAux.add(CAMPO_OFFSET);
                            valores = InteraccionBD.anadeCampoValor(valores, paramsPSAux, esRangoPermitido ? 1 : 0);
                            camposAux.add(CAMPO_RANGO_PERMITIDO);

                            try {
                                interBD.insertDatosTabla(CURVA_AUX + VISTA_AUX, camposAux, valores, paramsPSAux, null);
                            } catch (SQLException e) {}
                        }
                        
                        vD = "(SELECT TOP 1 CASE WHEN 1 = 1" + (idNorma.equals(NormaRA.ID_NORMA_IEC_3_0) ? " AND " + CAMPO_RANGO_PERMITIDO + " = 1 ": "") + " THEN ABS(" + pN + " * " + CAMPO_PENDIENTE + " + " + CAMPO_OFFSET + ") ELSE NULL END FROM " + CURVA_AUX + VISTA_AUX + " WHERE " + pN + " >= " + CAMPO_DESDE_POT + " AND " + pN + " < " + CAMPO_HASTA_POT + " ORDER BY ABS(" + vZaCP + " - ABS(" + pN + " * " + CAMPO_PENDIENTE + " + " + CAMPO_OFFSET + ")))";

						vH = "(" + vD + vHAux + ")";

						//Llevamos el resultado a altura de referencia
						if (!idNorma.equals(NormaRA.ID_NORMA_IEC_3_0)) {
							vS = "(" + vH + " * " + getCteAltOriAltDes(hB, zRef, z0Ref) + ")";
						} else {
							if (resAlturaBuje)
								vS = vH;
							else
								vS = "(" + vH + " * " + getCteAltOriAltDes(hB, zRef, z0Ref) + ")";
						}

                        sqlCaseVD = "CONVERT(" + TIPO_DECIMAL_DEF + ", " + vD + ") AS " + CAMPO_V_D;
                        sqlCaseVH = "CONVERT(" + TIPO_DECIMAL_DEF + ", " + vH + ") AS " + CAMPO_V_H;
                        sqlCaseVS = "CONVERT(" + TIPO_DECIMAL_DEF + ", " + vS + ") AS " + CAMPO_V_S;

                        campos.add(sqlCaseVD);
						campos.add(sqlCaseVH);
						campos.add(sqlCaseVS);
                    } else {
						res += "<br>No hay datos suficientes de CP para derivar.";
					}

                    break;
                case VEL_MEDIDA:
                    //sqlCaseVH = "CONVERT(" + TIPO_DECIMAL_DEF + ", ";
                    sqlCaseVS = "CONVERT(" + TIPO_DECIMAL_DEF + ", ";

					//Tomamos la velocidad a altura de referencia
					if (!idNorma.equals(NormaRA.ID_NORMA_IEC_3_0))
						vS = velAlturaRef;
					else {
						//Llevamos el resultado a altura de Buje
						if (resAlturaBuje)
							vS = velAlturaBuje;
						else
							vS = velAlturaRef;

						if (esMiniAero)
							vS = "(" + vS + " * POWER(" + cte1 + ", " + TratDecimales.round(1 / 3.0, TratDecimales.DEC_POTENCIA) + "))";
					}

					//sqlCaseVH += vH + ") AS " + CAMPO_V_H;
					sqlCaseVS += vS + ") AS " + CAMPO_V_S;

					//campos.add(sqlCaseVH);
					campos.add(sqlCaseVS);
					if (!res.contains(CAMPO_V_Z) && !mapCamposTabla.containsKey(CAMPO_V_Z)) {
						res += "<br>Falta el campo &lt;" + CAMPO_V_Z + "&gt; en  la tabla.";
					}
					break;
            }


            ////////////////////////////////////////////////////////////////////////
            ////FIN RESTO DE CAMPOS PARA VISTA//////////////////////////////////////
            ////////////////////////////////////////////////////////////////////////

            //Añadimos las condiciones de la vista
            String condicion = InteraccionBD.anadeCampoCondicion(null, paramsPS, CAMPO_FECHA_HORA, ">=", fechaIni);
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_FECHA_HORA, "<=", fechaFin);

            //Nos quedamos con todos los campos, no solo los válidos, para poder mostrarlos despúes
            //condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_VALIDO, "=", 1);
            
            if (sector[1] <= sector[0]) {
                String condicionAND = "";
                String condicionAND2 = "";
                String condicionOR = "";
                
                condicionAND = InteraccionBD.anadeCampoCondicion(condicionAND, paramsPS, CAMPO_DIRECCION, ">=", sector[0]);
                condicionAND = InteraccionBD.anadeCampoCondicion(condicionAND, paramsPS, CAMPO_DIRECCION, "<=", 360.0);
                condicionAND = "(" + condicionAND + ")";
                
                condicionAND2 = InteraccionBD.anadeCampoCondicion(condicionAND2, paramsPS, CAMPO_DIRECCION, "<=", sector[1]);
                condicionAND2 = InteraccionBD.anadeCampoCondicion(condicionAND2, paramsPS, CAMPO_DIRECCION, ">=", 0.0);
                condicionAND2 = "(" + condicionAND2 + ")";
                
                condicionOR = InteraccionBD.anadeCampoCondicionOr(condicionOR, null, condicionAND, InteraccionBD.COND_SIN_OPERACION, null);
                condicionOR = InteraccionBD.anadeCampoCondicionOr(condicionOR, null, condicionAND2, InteraccionBD.COND_SIN_OPERACION, null);
                condicionOR = "(" + condicionOR + ")";
                
                condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, condicionOR, InteraccionBD.COND_SIN_OPERACION, null);
            } else {
                condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_DIRECCION, ">=", sector[0]);
                condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_DIRECCION, "<=", sector[1]);
            }
            if (!res.contains(CAMPO_DIRECCION) && !mapCamposTabla.containsKey(CAMPO_DIRECCION)) {
                res += "<br>Falta el campo &lt;" + CAMPO_DIRECCION + "&gt; en  la tabla.";
            }

            //Si no ha habido incidencias
            if (res.length() == 0) {
                if (idNorma.equals(NormaRA.ID_NORMA_IEC_3_0) && (tipoTabla.contentEquals(Auxiliares.TIPO_OCT) || tipoTabla.contentEquals(Auxiliares.TIPO_SPL))) {
                    String tablaAuxOCT = getTabla().replace(tipoTabla, Auxiliares.TIPO_OCT);

                    interBD.creaVista(VISTA_AUX, tablaAuxOCT, campos, condicion, paramsPS, null, null);
                } else
                     interBD.creaVista(VISTA_AUX, TABLA, campos, condicion, paramsPS, null, null);
                
                //Actualizamos la vista para que nos marque como no válidos los datos que entren dentro de las incidencias
                updateDatosIncidencias(VISTA_AUX, idAsunto, idSite, 0);
            }
        } else {
            res += "Debe definirse un tipo de tabla y un asunto";
        }
        return res;
    }
    
    //Función que devuelve la K promedio de la base Aux (VS/VZ)
    public static Double getVistaAuxK(String tipoTabla, Integer idAsunto, Integer idSite, Double potLimSup, Double potLimInf, String campoVelMedida) throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        Double res = null;

        if (tipoTabla != null && idAsunto != null && idSite != null) {
            setVistaAux(tipoTabla, idAsunto, idSite);

            ArrayList<String> campos = new ArrayList<String>();
            ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();

			campos.add("AVG(" + CAMPO_V_S + " / " + campoVelMedida + ")");
            
            String condicion = "";
            if (potLimInf != null)
                condicion = InteraccionBD.anadeCampoCondicion(null, paramsPS, CAMPO_P_NETA, ">", potLimInf);
            if (potLimSup != null)
				condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_P_NETA, "<", potLimSup);

			//Solo nos interesan aquellas que no sean RF
			condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_RF, "=", 0);
            
            //Solo para los valores válidos
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_VALIDO, "=", 1);

            ArrayList<Object[]> resAux = interBD.getDatosTabla(VISTA_AUX, campos, condicion, paramsPS, null, null);

            if (resAux != null && resAux.get(0)[0] != null) {
                res = TratDecimales.round(((BigDecimal) resAux.get(0)[0]).doubleValue(), TratDecimales.DEC_VARIABLE_RA);
            }
        }

        return res;
    }
    
    public static double[] getRegVelMedida(String tipoTabla, Integer idAsunto, Integer idSite, Double potLimSup, Double potLimInf, String campoVelMedida) throws SQLException {
        ArrayList<Object[]> velMedida; // <{Vn, Vs}, ...>
        
        velMedida = getVistaAuxVelMedidaVs(tipoTabla, idAsunto, idSite, potLimSup, potLimInf, campoVelMedida);
                            
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries serie = new XYSeries("Regresion");

        int nVelMedida = velMedida.size();

        for (int i = 0; i < nVelMedida; i++) 
            serie.add(((BigDecimal) velMedida.get(i)[0]).doubleValue(), ((BigDecimal) velMedida.get(i)[1]).doubleValue());

        dataset.addSeries(serie);
        
        double[] resAux = Regression.getPolynomialRegression(dataset, 0, 1);
        int nRes = resAux.length;
        double[] res = new double[nRes];
        
        for (int i = 0; i < nRes; i++) {
            res[i] = TratDecimales.round(resAux[i], TratDecimales.DEC_VARIABLE_RA);
        }
        
        return res;
    }
    //Función que devuelve los datos de Nacelle para hacer regresión
    public static ArrayList<Object[]> getVistaAuxVelMedidaVs(String tipoTabla, Integer idAsunto, Integer idSite, Double potLimSup, Double potLimInf, String campoVelMedida) throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        ArrayList<Object[]> res = null;

        if (tipoTabla != null && idAsunto != null && idSite != null) {
            setVistaAux(tipoTabla, idAsunto, idSite);

            ArrayList<String> campos = new ArrayList<String>();
            ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();

            campos.add(campoVelMedida);
            campos.add(CAMPO_V_S);

			String condicion = "";
            if (potLimInf != null)
                condicion = InteraccionBD.anadeCampoCondicion(null, paramsPS, CAMPO_P_NETA, ">", potLimInf);
            if (potLimSup != null)
				condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_P_NETA, "<", potLimSup);

            res = interBD.getDatosTabla(VISTA_AUX, campos, condicion, paramsPS, null, null);
        }

        return res;
    }
    //Función que devuelve el pendiente, offset y R2 de la regresion de una matriz (x,y)
    public static Double[] getRegresion(ArrayList<Object[]> datos) {
        Double var1 = 0.0;
        Double var2 = 0.0;

        Double sumVar1 = 0.0;
        Double sumVar1Sqr = 0.0;
        Double sumVar2 = 0.0;
        Double sumVar1PorVar2 = 0.0;
        Double numerador = 0.0;
        Double denomVar1 = 0.0;
        Double denomVar2 = 0.0;

        Double pendiente = 0.0;
        Double offset = 0.0;
        Double r2 = 0.0;

        int n = (datos.size());

        for (int i = 0; i < n; i++) {
            var1 = TratDecimales.round(((BigDecimal) datos.get(i)[0]).doubleValue(), TratDecimales.DEC_VARIABLE_RA);
            var2 = TratDecimales.round(((BigDecimal) datos.get(i)[1]).doubleValue(), TratDecimales.DEC_VARIABLE_RA);

            sumVar1 = sumVar1 + var1;
            sumVar1Sqr = TratDecimales.round(sumVar1Sqr + Math.pow(var1, 2), TratDecimales.DEC_VARIABLE_RA);
            sumVar2 = sumVar2 + var2;
            sumVar1PorVar2 = TratDecimales.round(sumVar1PorVar2 + (var1 * var2), TratDecimales.DEC_VARIABLE_RA);
        }

        Double aux = n * sumVar1Sqr - Math.pow(sumVar1, 2);

        if (n > 0 && aux != 0.0) {
            pendiente = TratDecimales.round((sumVar1PorVar2 * n - sumVar1 * sumVar2) / aux, TratDecimales.DEC_VARIABLE_RA);

            offset = TratDecimales.round((sumVar2 - pendiente * sumVar1) / n, TratDecimales.DEC_VARIABLE_RA);

            for (int i = 0; i < n; i++) {
                numerador = TratDecimales.round(numerador + (var1 - sumVar1 / n) * (var2 - sumVar2 / n), TratDecimales.DEC_VARIABLE_RA);
                denomVar1 = TratDecimales.round(denomVar1 + Math.pow(var1 - sumVar1 / n, 2), TratDecimales.DEC_VARIABLE_RA);
                denomVar1 = TratDecimales.round(+Math.pow(var2 - sumVar2 / n, 2), TratDecimales.DEC_VARIABLE_RA);
            }

            Double aux2 = TratDecimales.round(Math.pow(denomVar1, 0.5) * Math.pow(denomVar2, 0.5), TratDecimales.DEC_VARIABLE_RA);

            if (aux2 != 0.0) {
                r2 = TratDecimales.round(numerador / aux2, TratDecimales.DEC_VARIABLE_RA);
            }
        }

        return new Double[]{pendiente, offset, r2};
    }
    //Valida y crea la vista base neta de RA utilizada para el cálculo de la k
    public static String createVistaNeta(Integer idNorma, String tipoTabla, Integer idAsunto, Integer idSite, Double potNominal, int tipoCalculoVelocidad, int tipoAjuste, int tipoAjusteRF, int calculoAjuste, Double densidad, double zRef, double z0Ref, ArrayList<Object> ajustes) throws SQLException, NoSuchFieldException {
        String res = "";
        InteraccionBD interBD = new InteraccionBD();

        if (tipoTabla != null && idAsunto != null && idSite != null) {
            setVistaAux(tipoTabla, idAsunto, idSite);
            setVistaNeta(tipoTabla, idAsunto, idSite);

            Double hB = AerogeneradorRA.getAeroPorId(AsuntoRA.getAsuntoPorId(idAsunto).getIdAero()).getHB();

            //Obtenemos los campos de la tabla
            Object[] camposTabla = interBD.getCamposTabla(getVistaAux());

            //Guardamos los campos de la tabla en un hash para tener un rápida localización
            HashMap<String, String> mapCamposTabla = new HashMap<String, String>();

            int nCamposTabla = camposTabla.length;

            for (int i = 0; i < nCamposTabla; i++) {
                mapCamposTabla.put((String) camposTabla[i], null);
            }

            Double potLimSup = null, potLimInf = null;
            Double k = null, kRF = null;
            double[] regVelMedida = null, regVelMedidaRF = null;
            String sqlCase;
            
            ArrayList<String> campos = new ArrayList<String>();
			//Creamos tabla temporal para almacenar la validez
			//Borramos la tabla si ya existe
			interBD.borraTabla(VALI_SYS_AUX + VISTA_AUX);
			
			campos.add(CAMPO_ID_DATO + " AS " + CAMPO_ID_DATO_SYS);
			campos.add(CAMPO_VALIDO + " AS " + CAMPO_VALIDO_SYS + " INTO " + VALI_SYS_AUX + VISTA_AUX);

			interBD.getDatosTabla(VISTA_AUX, campos, null, null, null, null);

            //Nos interesan todos los campos de la tabla
            Object camposAux[] = getCamposVistaAux();
            int nCamposAux = camposAux.length;

			campos = new ArrayList<String>();

            for (int i = 0; i < nCamposAux; i++) {
                campos.add((String) camposAux[i]);

				if (((String) camposAux[i]).contentEquals(CAMPO_VALIDO))
					campos.add(CAMPO_VALIDO_SYS); //Campo para validaciones de sistema
            }
            //Quitamos la VS para añadirla ajustada
            campos.remove(CAMPO_V_S);

            ////////////////////////////////////////////////////////////////////////
            ////RESTO DE CAMPOS PARA VISTA////////////////////////////////////////
            ////////////////////////////////////////////////////////////////////////

            //VS
            switch (tipoCalculoVelocidad) {
                case VEL_DERIVADA_CP:
					String campoVelMedida = null, campoVelMedidaRF = null;

					switch (tipoAjuste) {
					case AJUSTE_K_TORRE:
						potLimSup = 0.95 * potNominal;
						potLimInf = null;

						campoVelMedida = CAMPO_V_Z;
						break;
					case AJUSTE_K_NACELLE:
						potLimSup = 0.95 * potNominal;
						potLimInf = 0.05 * potNominal;

						campoVelMedida = CAMPO_V_N;
						break;
					}

					switch (tipoAjusteRF) {
					case AJUSTE_K_TORRE:
						campoVelMedidaRF = CAMPO_V_Z;
						break;
					case AJUSTE_K_NACELLE:
						campoVelMedidaRF = CAMPO_V_N;
						break;
					}

					sqlCase = "CONVERT(" + TIPO_DECIMAL_DEF + ", " + "CASE";

					if (!idNorma.equals(NormaRA.ID_NORMA_IEC_3_0))
						sqlCase += "\n\tWHEN (" + CAMPO_RF + " = 0 " + (potLimInf != null ? "AND " + CAMPO_P_N + " > " + potLimInf : "") + " AND " + CAMPO_P_N + " < " + potLimSup + ") THEN ";
					else
						sqlCase += "\n\tWHEN (" + CAMPO_RF + " = 0 AND " + CAMPO_V_D + " is not null) THEN ";

					sqlCase += CAMPO_V_S;

					if (mapCamposTabla.containsKey(CAMPO_V_S) && mapCamposTabla.containsKey(campoVelMedida) && mapCamposTabla.containsKey(campoVelMedidaRF)) {
						switch (calculoAjuste) {
							case CALCULO_K_PROMEDIO:
								k = getVistaAuxK(tipoTabla, idAsunto, idSite, potLimSup, potLimInf, campoVelMedida);
								kRF = getVistaAuxK(tipoTabla, idAsunto, idSite, potLimSup, potLimInf, campoVelMedidaRF);

								if (!idNorma.equals(NormaRA.ID_NORMA_IEC_3_0)) {
									sqlCase += "\n\tWHEN (" + CAMPO_RF + " = 0";
									if (potLimInf != null)
										sqlCase += " AND (" + CAMPO_P_N + " <= " + potLimInf + " OR " + (potLimSup != null ? CAMPO_P_N + " >= " + potLimSup : " 0") + ")";
									else
										sqlCase += (potLimSup != null ? " AND " + CAMPO_P_N + " >= " + potLimSup : "");
									sqlCase += ") THEN ";
									
									sqlCase += k + " * " + campoVelMedida;
									
									sqlCase += "\n\tWHEN (" + CAMPO_RF + " = 1) THEN ";
									sqlCase += kRF + " * " + campoVelMedidaRF;
								} else {
									sqlCase += "\n\tWHEN (" + CAMPO_RF + " = 0 AND " + CAMPO_V_D + " is null) THEN ";
									sqlCase += k + " * " + campoVelMedida;
									
									sqlCase += "\n\tWHEN (" + CAMPO_RF + " = 1) THEN ";
									sqlCase += kRF + " * " + campoVelMedidaRF;
								}
								break;
								
							case CALCULO_K_PENDIENTE:
								regVelMedida = getRegVelMedida(tipoTabla, idAsunto, idSite, potLimSup, potLimInf, campoVelMedida);
								kRF = getVistaAuxK(tipoTabla, idAsunto, idSite, potLimSup, potLimInf, campoVelMedidaRF);

								sqlCase += "\n\tWHEN (" + CAMPO_RF + " = 0";
								if (potLimInf != null)
									sqlCase += " AND (" + CAMPO_P_N + " <= " + potLimInf + " OR " + (potLimSup != null ? CAMPO_P_N + " >= " + potLimSup : " 0") + ")";
								else
									sqlCase += (potLimSup != null ? " AND " + CAMPO_P_N + " >= " + potLimSup : "");
								sqlCase += ") THEN ";
							
								sqlCase += regVelMedida[1] + " * " + campoVelMedida  + " + " + regVelMedida[0];
								
								sqlCase += "\n\tWHEN (" + CAMPO_RF + " = 1) THEN ";
								sqlCase += kRF + " * " + campoVelMedidaRF;

								break;
						}

						sqlCase += "\nEND) AS " + CAMPO_V_S;

						campos.add(sqlCase);
					} else {
						if (!res.contains(CAMPO_V_S) && !mapCamposTabla.containsKey(CAMPO_V_S)) {
							res += "<br>Falta el campo &lt;" + CAMPO_V_S + "&gt; en  la tabla.";
						}
						if (!res.contains(campoVelMedida) && !mapCamposTabla.containsKey(campoVelMedida)) {
							res += "<br>Falta el campo &lt;" + campoVelMedida + "&gt; en  la tabla.";
						}
						if (!campoVelMedida.contentEquals(campoVelMedidaRF) && !res.contains(campoVelMedidaRF) && !mapCamposTabla.containsKey(campoVelMedidaRF)) {
							res += "<br>Falta el campo &lt;" + campoVelMedidaRF + "&gt; en  la tabla.";
						}
					}

                    if (!res.contains(CAMPO_RF) && !mapCamposTabla.containsKey(CAMPO_RF)) {
                        res += "<br>Falta el campo &lt;" + CAMPO_RF + "&gt; en  la tabla.";
                    }
                    if (!res.contains(CAMPO_P_N) && !mapCamposTabla.containsKey(CAMPO_P_N)) {
                        res += "<br>Falta el campo &lt;" + CAMPO_P_N + "&gt; en  la tabla.";
                    }
                    if (!res.contains(CAMPO_V_S) && !mapCamposTabla.containsKey(CAMPO_V_S)) {
                        res += "<br>Falta el campo &lt;" + CAMPO_V_S + "&gt; en  la tabla.";
                    }

                    break;
                case VEL_MEDIDA:
                    campos.add(CAMPO_V_S);

                    if (!res.contains(CAMPO_V_S) && !mapCamposTabla.containsKey(CAMPO_V_S)) {
                        res += "<br>Falta el campo &lt;" + CAMPO_V_S + "&gt; en  la tabla.";
                    }
                    break;
            }
            
            ajustes.add(k);
            ajustes.add(kRF);
            ajustes.add(regVelMedida);
            ajustes.add(regVelMedidaRF);

            ////////////////////////////////////////////////////////////////////////
            ////FIN RESTO DE CAMPOS PARA VISTA//////////////////////////////////////
            ////////////////////////////////////////////////////////////////////////

            if (res.length() == 0) { //Si no hay error
				String condicion = null;
				ArrayList<Object[]> paramsPS = null;
				
				if (idNorma.equals(NormaRA.ID_NORMA_IEC_3_0) && !tipoTabla.contentEquals(Auxiliares.TIPO_FFT)) {
					paramsPS = new ArrayList<Object[]>();
					condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_L_A_EQ_1, "is not", null);
				}

				String tablaFrom = VISTA_AUX;
				tablaFrom = InteraccionBD.anadeTabla(tablaFrom, VALI_SYS_AUX + VISTA_AUX);

				condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_ID_DATO, InteraccionBD.ASIGNACION_CAMPOS, CAMPO_ID_DATO_SYS);
                    
                interBD.creaVista(VISTA_NETA, tablaFrom, campos, condicion, paramsPS, null, null);

                //Actualizamos la vista para que nos marque como no válidos los datos conseguido por encima de potLimSup y que no superen la velocidad para ese punto.
                if (!idNorma.equals(NormaRA.ID_NORMA_IEC_3_0) && tipoCalculoVelocidad == VEL_DERIVADA_CP && tipoAjuste == AJUSTE_K_TORRE && calculoAjuste == CALCULO_K_PROMEDIO) {
					updateDatosVelocidadLimPotSup(VISTA_NETA, idAsunto, densidad, potLimSup, 0, zRef, hB, z0Ref);
                }
            }
        } else {
            res += "Debe definirse un tipo de tabla, un asunto y un site";
        }
        return res;
    }
    //Función que devuelve los datos de la vista neta
    public static ArrayList<Object[]> getDatosVistaNeta(String tipoTabla, Integer idAsunto, Integer idSite, Double desdeVS, Double hastaVS, ArrayList<String> campos, String sqlExtra, Boolean distinct) throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        ArrayList<Object[]> res = null;

        if (tipoTabla != null && idAsunto != null && idSite != null) {
            setVistaNeta(tipoTabla, idAsunto, idSite);

            ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
            String condicion = "";

            if (desdeVS != null) {
                condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_V_S, ">=", desdeVS);
            }
            if (hastaVS != null) {
                condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_V_S, "<", hastaVS);
            }

            res = interBD.getDatosTabla(VISTA_NETA, campos, condicion, paramsPS, sqlExtra, distinct);
        }

        return res;
    }
    //Función que devuelve los datos de la vista neta
    public static ArrayList<Object[]> getDatosVistaNetaIdDato(String tipoTabla, Integer idAsunto, Integer idSite, Integer idDato, ArrayList<String> campos, String sqlExtra, Boolean distinct) throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        ArrayList<Object[]> res = null;

        if (tipoTabla != null && idAsunto != null && idSite != null) {
            setVistaNeta(tipoTabla, idAsunto, idSite);

            ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
            String condicion = "";

            if (idDato != null) {
                condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_ID_DATO, "=", idDato);
            }
            
            res = interBD.getDatosTabla(VISTA_NETA, campos, condicion, paramsPS, sqlExtra, distinct);
        }

        return res;
    }
    //Función que devuelve los <campos> de la <tabla> según las <condicion>es, respecto al bin
    //del campo <campoBin> entre sus null <valBinMin> y <valBinMax>
    public static ArrayList<Object[]> getDatosBin(String tipoTabla, Integer idAsunto, Integer idSite, ArrayList<String> campos, String condicion, ArrayList<Object[]> paramsPS, String campoBin, Integer valBinMin, Integer valBinMax, String sqlExtra, Boolean distinct) throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        ArrayList<Object[]> res = null;

        if (tipoTabla != null && idAsunto != null && idSite != null && campoBin != null) {
            String condicionBin;
            ArrayList<Object[]> resAux;
            ArrayList<Object[]> paramsPSBin;
            ArrayList<String> camposBin;

            setVistaNeta(tipoTabla, idAsunto, idSite);

            String campoComplejoBin = "ROUND(" + campoBin + ", 0, 0)";

            for (int i = valBinMin; i <= valBinMax; i++) {
                paramsPSBin = (ArrayList<Object[]>) paramsPS.clone();
                camposBin = (ArrayList<String>) campos.clone();

                //Queremos que nos devuelva en primer lugar el bin al que hace referencia
                camposBin.add(0, Integer.toString(i));

                condicionBin = InteraccionBD.anadeCampoCondicion(condicion, paramsPSBin, campoComplejoBin, "=", i);

                resAux = interBD.getDatosTabla(VISTA_NETA, camposBin, condicionBin, paramsPSBin, sqlExtra, distinct);

                if (resAux != null) {
                    if (res == null) {
                        res = new ArrayList<Object[]>();
                    }
                    int n = resAux.size();

                    for (int j = 0; j < n; j++) {
                        res.add(resAux.get(j));
                    }
                }
            }
        }

        return res;
    }

    //Función que devuelve Vs y LAeq1 de los bines entre minVin y maxBin
    public static ArrayList<DatoVelocidadNivel> getVelocidadNivelBin(Integer idAsunto, Integer idSite, Integer rF, String campoBin, Integer valBinMin, Integer valBinMax) throws SQLException {
        ArrayList<DatoVelocidadNivel> res = null;
        ArrayList<String> campos = new ArrayList<String>();
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        String condicion = null;

        if (rF != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_RF, "=", rF);
        }

        //Solo nos interesa Vs y LAeq1
        campos.add(CAMPO_V_S);
        campos.add(CAMPO_L_A_EQ_1);
        campos.add(CAMPO_VALIDO);
        campos.add(CAMPO_VALIDO_SYS);

        ArrayList<Object[]> resAux = getDatosBin(Auxiliares.TIPO_SPL, idAsunto, idSite, campos, condicion, paramsPS, campoBin, valBinMin, valBinMax, null, null);

        int nResAux;
        if (resAux != null && (nResAux = resAux.size()) > 0) {
            res = new ArrayList<DatoVelocidadNivel>();

            Object[] fila;

            for (int i = 0; i < nResAux; i++) {
                fila = resAux.get(i);

                //Redondeamos los valores
                if (fila[1] != null) {
                    fila[1] = TratDecimales.round(((BigDecimal) fila[1]).doubleValue(), TratDecimales.DEC_VARIABLE_RA);
                }
                if (fila[2] != null) {
                    fila[2] = TratDecimales.round(((BigDecimal) fila[2]).doubleValue(), TratDecimales.DEC_VARIABLE_RA);
                }
                res.add(new DatoVelocidadNivel((Integer) fila[0], (Double) fila[1], (Double) fila[2], ((Integer) fila[3] * (Integer) fila[4] == 1)));
            }
        }

        return res;
    }
    //Función que devuelve los resultados de los bines entre minVin y maxBin <Bin, numDatosBin, Vs, Pn>
    public static ArrayList<Object[]> getResultadosBin(String tipoTabla, Integer idAsunto, Integer idSite, Integer rF, String campoBin, Integer valBinMin, Integer valBinMax) throws SQLException {
        ArrayList<Object[]> res;
        ArrayList<String> campos = new ArrayList<String>();
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
         String  condicion = null, campoComplejo = null;

        if (rF != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_RF, "=", rF);
        }

        campoComplejo = "COUNT(*)";
        campos.add(campoComplejo);
        campoComplejo = "AVG(" + CAMPO_V_S + ")";
        campos.add(campoComplejo);
        campoComplejo = "AVG(" + CAMPO_P_N + ")";
        campos.add(campoComplejo);

        //Nos quedamos solo con los datos válidos
        condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_VALIDO, "=", 1);
        condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_VALIDO_SYS, "=", 1);

        res = getDatosBin(tipoTabla, idAsunto, idSite, campos, condicion, paramsPS, campoBin, valBinMin, valBinMax, null, null);

        if (res != null) {
            Object[] fila;
            int nRes = res.size();

            for (int i = 0; i < nRes; i++) {
                fila = res.get(i);

                //Redondeamos las medias
                if (fila[2] != null) {
                    fila[2] = TratDecimales.round(((BigDecimal) fila[2]).doubleValue(), TratDecimales.DEC_VARIABLE_RA);
                }
                if (fila[3] != null) {
                    fila[3] = TratDecimales.round(((BigDecimal) fila[3]).doubleValue(), TratDecimales.DEC_VARIABLE_RA);
                }
                res.set(i, fila);
            }
        }

        return res;
    }
    //Función que devuelve el XYDataset para pol
    public static XYSeriesCollection getXYDatasetPol(String tipoTabla, Integer idAsunto, Integer idSite, Double desdeVS, Double hastaVS) throws SQLException {
        XYSeriesCollection res = new XYSeriesCollection();

        XYSeries xysAG = new XYSeries("AG", false);
        XYSeries xysRF = new XYSeries("RF", false);

        ArrayList<String> campos = new ArrayList<String>();

        campos.add(CAMPO_RF);
        campos.add(CAMPO_V_S);
        campos.add(CAMPO_L_A_EQ_1);

        ArrayList<Object[]> resAux = getDatosVistaNeta(tipoTabla, idAsunto, idSite, desdeVS, hastaVS, campos, null, null);

        int n = resAux.size();

        Object[] dato;
        for (int i = 0; i < n; i++) {
            dato = resAux.get(i);

            if ((Integer) dato[0] == 0) { //No es ruido de fondo
                xysAG.add(((Double) dato[1]).doubleValue(), ((Double) dato[2]).doubleValue());
            } else {    //Es ruido de fondo
                xysRF.add(((Double) dato[1]).doubleValue(), ((Double) dato[2]).doubleValue());
            }
        }

        res.addSeries(xysAG);
        res.addSeries(xysRF);

        return res;
    }

    /*
    //Función que devuelve el XYDataset para bines
    public static XYSeriesCollection getXYDatasetBin(String tipoTabla, Integer idAsunto, Integer idSite, String campoBin, Integer valBinMin, Integer valBinMax) throws SQLException {
    XYSeriesCollection res = new XYSeriesCollection();
    
    XYSeries xysAG;
    XYSeries xysRF;
    ArrayList<Object[]> resAuxAG;
    ArrayList<Object[]> resAuxRF;
    Object[] dato;
    int nDatos;
    
    for (int i = valBinMin; i <= valBinMax; i++) {
    xysAG = new XYSeries("AG_Bin_" + i, false);
    xysRF = new XYSeries("RF_Bin_" + i, false);
    
    resAuxAG = getVelocidadNivelBin(idAsunto, idSite, 0, campoBin, i, i); //<bin, VS, LAeq1>
    resAuxRF = getVelocidadNivelBin(idAsunto, idSite, 1, campoBin, i, i); //<bin, VS, LAeq1>
    
    //Datos AG ara el bin i
    nDatos = resAuxAG.size();
    for (int j = 0; j < nDatos; j++) {
    dato = resAuxAG.get(j);
    xysAG.add(((Double) dato[1]).doubleValue(), ((Double) dato[2]).doubleValue());
    }
    
    //DAtos RF para el bin i
    nDatos = resAuxRF.size();
    for (int j = 0; j < nDatos; j++) {
    dato = resAuxRF.get(j);
    xysAG.add(((Double) dato[1]).doubleValue(), ((Double) dato[2]).doubleValue());
    }
    
    res.addSeries(xysAG);
    res.addSeries(xysRF);
    }
    
    return res;
    }
     */    //Función que devuelve los coeficientes pol
    public static double[] getCoefGen(XYDataset dataset, int serie, int orden) throws IllegalArgumentException {
        XYSeriesCollection ds = (XYSeriesCollection) dataset;
        XYSeries s = ds.getSeries(serie);
        int nDatos = s.getItemCount();
        double[] x = new double[nDatos];
        double[] y = new double[nDatos];
        
        for (int i = 0; i < nDatos; i++) {
            x[i] = (Double) s.getX(i);
            y[i] = (Double) s.getY(i);
        }
        
        double res[] = null;
        
        if (nDatos > orden) {
            res = new double[orden + 2];
            PolynomialRegression pr = new PolynomialRegression(x, y, orden);
            
            //Redondeamos y almacenamos los coeficientes
            for (int i = 0; i <= orden; i++) {
                res[i] = TratDecimales.round(pr.beta(i), TratDecimales.DEC_VARIABLE_RA);
            }
            
            res[orden + 1] = TratDecimales.round(pr.R2(), TratDecimales.DEC_VARIABLE_RA);
        }

        return res;
    }
    
    public static double[] getCoefPol(XYDataset dataset, int serie) throws IllegalArgumentException {
        return getCoefGen(dataset, serie, ORDEN_REG_POL);
    }
    //Función que devuelve los coeficientes bin
    public static double[] getCoefBin(XYDataset dataset, int serie) {
        return getCoefGen(dataset, serie, 1);
    }
    //Función para validad que al menos hay un dato por encima y otro por debajo del valor centrado del bin para regresiones lineales bin a bin
    public static String validaDatosRegLin(String tipoTabla, Integer idAsunto, Integer idSite, Integer valBinMin, Integer valBinMax, Integer rF) throws SQLException {
        String res = "";
        
        ArrayList<String> campos = new ArrayList<String>();
        String condicion = "";
        String condicionAux = "";
        String condicionAux2 = "";
        ArrayList<Object[]> paramsPS = null;
        ArrayList<Object[]> paramsPSAux = null;
        ArrayList<Object[]> paramsPSAux2 = null;
        String aeroRF = "";
        String operacion = "";
        String operacionTxt = "";
        
        campos.add("COUNT(*)");
        
        ArrayList<Object[]> resAux;
        int nResAux;
        
        paramsPS = new ArrayList<Object[]>();
        condicion = InteraccionBD.anadeCampoCondicion(null, paramsPS, CAMPO_VALIDO, "=", 1);
        condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_VALIDO_SYS, "=", 1);
        
        if (rF == 0)
            aeroRF = "aerogenerador";
        else    
            aeroRF = "fondo";
        
        for (int i = valBinMin; i <= valBinMax; i++) {
            paramsPSAux = (ArrayList<Object[]>) paramsPS.clone();
            condicionAux = InteraccionBD.anadeCampoCondicion(condicion, paramsPSAux, CAMPO_RF, "=", rF);

            for (int j = 0; j <= 1; j++) {
                if (j == 0) {
                    operacion = ">";
                    operacionTxt = "Superior";
                } else {
                    operacion = "<";
                    operacionTxt = "Inferior";
                }

                paramsPSAux2 = (ArrayList<Object[]>) paramsPSAux.clone();
                condicionAux2 = InteraccionBD.anadeCampoCondicion(condicionAux, paramsPSAux2, CAMPO_V_S, operacion, i);

                resAux = getDatosBin(tipoTabla, idAsunto, idSite, campos, condicionAux2, paramsPSAux2, CAMPO_V_S, i, i, null, null);

                nResAux = resAux != null ? resAux.size() : 0;

                if (nResAux == 0 || (Integer) resAux.get(0)[1] == 0) {
                    res += "El bin <b>" + i + "</b> no contiene al menos un dato <b>" + operacionTxt + "</b> al valor centrado para <b>ruido de " + aeroRF + "</b>.<br>";
                }
            }
        }
        
        return res;
    }
    //Función que devuelve el nivel SPL del aerogenerador corregido con respecto al RF
    //Bajo la fórmula: Ls = 10 log (10 ^ (0.1 Lsn) - 10 ^ (0.1 Ln) con:
    //Ls = Nivel del aero
    //Lsn = Nivel del aero + RF
    //Ln = Nivel RF
    //Devolveremos el valor corregido y el tipo de correccción realizado
    public static Entry<Double, Integer> getCorreccionRF(Double valorAG_RF, Double valorRF) throws NumberFormatException {
        HashMap<Double, Integer> resAux = new HashMap<Double, Integer>();
        Double valorAG = null;
        Integer tipo = null;

		if (valorAG_RF != null && valorRF != null) {
			Double diff = valorAG_RF - valorRF;

			if (diff >= 6.0) {
				valorAG = TratDecimales.round(10 * java.lang.Math.log10(java.lang.Math.pow(10, 0.1 * valorAG_RF) - java.lang.Math.pow(10, 0.1 * valorRF)), TratDecimales.DEC_VARIABLE_RA);
				tipo = CORREGIDO_RF_OK;
			} else if (diff > 3.0) {
				valorAG = TratDecimales.round(valorAG_RF - 1.3, TratDecimales.DEC_VARIABLE_RA);
				tipo = CORREGIDO_RF_AVISO;
			} else {
				tipo = CORREGIDO_RF_ERROR;
			}
		} else
			tipo = CORREGIDO_RF_ERROR;

        resAux.put(valorAG, tipo);

        Iterator it = resAux.entrySet().iterator();

        return (Entry<Double, Integer>) it.next();
    }
    //Función que devuelve el nivel aparente SPL del aerogenerador
    //Bajo la fórmula: L_WA,k = L_A_eq,c,k - 6 +10 log(4*pi*R1/S0) con:
    //L_WA,k = Nivel aparente para el bin k
    //L_A_eq,c,k = Nivel corregido para RF para el bin k bajo condiciones de referencia en dBA
    //R1 = Distancia del centro del buje al micrófono en metros
    //S0 = Are de referencia = 1 m2
    public static Entry<Double, Integer> getNivelAparente(Entry<Double, Integer> nivelCorregido, Double distMicro) throws NumberFormatException {
        HashMap<Double, Integer> resAux = new HashMap<Double, Integer>();
        Double nivelAparente = null;

        if (distMicro >= 0.0) {
            if (nivelCorregido.getValue() == CORREGIDO_RF_OK || nivelCorregido.getValue() == CORREGIDO_RF_AVISO_IEC3) {
                nivelAparente = TratDecimales.round(nivelCorregido.getKey() - 6 + 10 * java.lang.Math.log10(4 * java.lang.Math.PI * java.lang.Math.pow(distMicro, 2) / 1), TratDecimales.DEC_VARIABLE_RA);
            }
        }

        resAux.put(nivelAparente, nivelCorregido.getValue());

        Iterator it = resAux.entrySet().iterator();

        return (Entry<Double, Integer>) it.next();
    }
    
    public static PosicionRA getPosicionBuje(PosicionRA posAero, PosicionRA posMicro, Double hB, Double longBuje) {
        //Alejamos la posición del aero la distancia del buje con respecto al micrófono
        PosicionRA posBuje = new PosicionRA(null, posAero.getPosX(), posAero.getPosY(), posAero.getPosZ());
        
        Double posAeroX = posAero.getPosX();
        Double posAeroY = posAero.getPosY();
        Double posMicroX = posMicro.getPosX();
        Double posMicroY = posMicro.getPosY();
        
        Double desp, despBujeX, despBujeY;
        
        if (posAeroX == posMicroX || posAeroY == posMicroY)
            desp = longBuje;
        else
            desp = longBuje / Math.sqrt(2);
        
        if (posAeroX > posMicroX)
            despBujeX = desp;
        else if (posAeroX < posMicroX)
            despBujeX = -desp;
        else
            despBujeX = 0.0;
        
        if (posAeroY > posMicroY)
            despBujeY = desp;
        else if (posAeroY < posMicroY)
            despBujeY = -desp;
        else
            despBujeY = 0.0;
        
        posBuje.setPosX(TratDecimales.round(posBuje.getPosX() + despBujeX, TratDecimales.DEC_VARIABLE_RA));
        posBuje.setPosY(TratDecimales.round(posBuje.getPosY() + despBujeY, TratDecimales.DEC_VARIABLE_RA));
        posBuje.setPosZ(TratDecimales.round(posBuje.getPosZ() + hB, TratDecimales.DEC_VARIABLE_RA));
        
        return posBuje;
    }
    
    //Función que devulve la distancia existente entre el micrófono y el centro del buje
    public static Double getDistanciaMicro(Integer idAsunto) throws SQLException, NoSuchFieldException {
        Double res = null;
        
        AsuntoRA asunto = AsuntoRA.getAsuntoPorId(idAsunto);
        Integer idAero = asunto.getIdAero();
        AerogeneradorRA aero = AerogeneradorRA.getAeroPorId(idAero);
        
        AsuntoPosicionRA asuntoPos = AsuntoPosicionRA.getAsuntoPosicionRA(idAsunto);
        
        PosicionRA posAero = PosicionRA.getPosicion(asuntoPos.getIdPosAero());
        PosicionRA posMicro = PosicionRA.getPosicion(asuntoPos.getIdPosMicro1());
        PosicionRA posBuje;
        
        if (posAero == null)
            posAero = new PosicionRA(null, 0.0, 0.0, 0.0);
        
        if (posMicro == null)
            posMicro = new PosicionRA(null, 0.0, 0.0, 0.0);
        
        /* Cálculo teórico
        Double hB = aero.getHB();
        Double dN = aero.getDN();

        Double r0 = hB + dN / 2.0;  //Para turbinas horizontales
        //Double r0 = hB + dN;  //Para turbinas verticales
        Double r1 = Math.pow(Math.pow(r0, 2) + Math.pow(hB, 2), 0.5);
        */
        
        //Establecemos la posición del Buje
        Double hB = aero.getHB() != null ? aero.getHB() : 0.0;
        Double longBuje = aero.getLongBuje() != null ? aero.getLongBuje() : 0.0;
        
        posBuje = getPosicionBuje(posAero, posMicro, hB, longBuje);
        
        //Calculamos la distancia del micrófono
        res = Math.sqrt(Math.pow(posBuje.getPosX() - posMicro.getPosX(), 2) + Math.pow(posBuje.getPosY() - posMicro.getPosY(), 2) + Math.pow(posBuje.getPosZ() - posMicro.getPosZ(), 2));
        
        return res;
    }
    
    //Función que devuelve los resultados SPL <(L_A_eq,c,k, L_WA, k)> para cada bin k
    //L_WA,k = Nivel aparente para el bin k
    //L_A_eq,c,k = Nivel corregido para RF para el bin k bajo condiciones de referencia
    public static ArrayList<ArrayList<Entry<Double, Integer>>> getResultadosSPLCorregidoAparente(Integer idAsunto, Integer tipoCalculoAG, Integer tipoCalculoRF, HashMap<String, double[]> coeficientesPol, HashMap<String, HashMap<Integer, Double[]>> coeficientesBin, Integer valBinMin, Integer valBinMax) throws NumberFormatException, SQLException, NoSuchFieldException {
        ArrayList<ArrayList<Entry<Double, Integer>>> res = new ArrayList<ArrayList<Entry<Double, Integer>>>();
	ArrayList<Entry<Double, Integer>> resBin;

        Double splAG_RF,splRF;
        Entry <Double, Integer> splCorregido, splAparente;
        
        //Calculamos la distancia del micrófono
        Double r1 = getDistanciaMicro(idAsunto);
        
        int nBin;

        PolynomialFunction2D funcionPolAG = null, funcionPolRF = null;
        
        if (tipoCalculoAG.equals(TIPO_CAL_REG_POL)) { // y = a0 + a1 * x + a2 * x^2 + ... + an * x^n
            double[] coeficientes;
            double[] coefPol;
            int nCoefPol;
            
            //AG
            coefPol = coeficientesPol.get(Auxiliares.PREF_DATOS_AG);
            nCoefPol = coefPol.length;
            coeficientes = new double[nCoefPol - 1];
			System.arraycopy(coefPol, 0, coeficientes, 0, nCoefPol - 1); //Descartamos el último coeficiente (coef. de correlación)
            
            funcionPolAG = new PolynomialFunction2D(coeficientes);
        }
        
        if (tipoCalculoRF.equals(TIPO_CAL_REG_POL)) { // y = a0 + a1 * x + a2 * x^2 + ... + an * x^n
            double[] coeficientes;
            double[] coefPol;
            int nCoefPol;
            
            //RF
            coefPol = coeficientesPol.get(Auxiliares.PREF_DATOS_RF);
            nCoefPol = coefPol.length;
            coeficientes = new double[nCoefPol - 1];
			System.arraycopy(coefPol, 0, coeficientes, 0, nCoefPol - 1); //Descartamos el último coeficiente (coef. de correlación)
            
            funcionPolRF = new PolynomialFunction2D(coeficientes);
        }
        
        Double[] coefBin;
        
        for (int i = 0; i < valBinMax - valBinMin + 1; i++) {
			resBin = new ArrayList<Entry<Double, Integer>>();
            nBin = valBinMin + i;

            splAG_RF = 0.0;
            splRF = 0.0;

            if (tipoCalculoAG.equals(TIPO_CAL_REG_LIN)) { // y = a0 + a1 * x
                //Aero
                coefBin = coeficientesBin.get(Auxiliares.PREF_DATOS_AG).get(nBin);
                if (coefBin != null) {
                    splAG_RF = coefBin[0] + coefBin[1] * nBin; // AE + RF
                }
            } else { // y = a0 + a1 * x + a2 * x^2 + ... + an * x^n
               splAG_RF = funcionPolAG.getValue(nBin);
            }
            
            if (tipoCalculoRF.equals(TIPO_CAL_REG_LIN)) { // y = a0 + a1 * x
                //Ruido de Fondo
                coefBin = coeficientesBin.get(Auxiliares.PREF_DATOS_RF).get(nBin);
                if (coefBin != null) {
                    splRF = coefBin[0] + coefBin[1] * nBin; // RF
                }
            } else { // y = a0 + a1 * x + a2 * x^2 + ... + an * x^n
               splRF = funcionPolRF.getValue(nBin);
            }

            splCorregido = null;
            splAparente = null;

            //Corregimos RF
            if (splAG_RF != null && splRF != null) {
                splCorregido = getCorreccionRF(splAG_RF, splRF);            //Obtenemos nivel aparente
            }
            if (r1 != null) {
                splAparente = getNivelAparente(splCorregido, r1);
            }
            
            HashMap<Double, Integer> mapSplAG_RF = new HashMap<Double, Integer>();
            mapSplAG_RF.put(splAG_RF, null);
            HashMap<Double, Integer> mapSplRF = new HashMap<Double, Integer>();
            mapSplRF.put(splRF, null);
            
            resBin.add(mapSplAG_RF.entrySet().iterator().next());
            resBin.add(mapSplRF.entrySet().iterator().next());

            resBin.add(splCorregido);
            resBin.add(splAparente);

			res.add(resBin);
        }

        return res;
    }
    //Devuelve la completitud OCT (Hz1_6..Hzn_6,Hz1_7..Hzn_7,)
    public static ArrayList<Object[]> getResultadosOCTPond(Integer idAsunto, Integer idSite, Integer rF, Integer valBinMin, Integer valBinMax) throws SQLException, NoSuchFieldException {
        ArrayList<String> bandas = SerieRA2.getCodigosPorIdAsuntoTipo(idAsunto, TipoRA.getTipoRAPorIdSite(idSite).getIdTipoRA());

        ArrayList<String> campos = new ArrayList<String>();
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        String condicion = "";

        //Los datos ya se importan ponderadosa dbA. No hace falta la ponderación
        /*
        Integer idSerie;
        Ponderacion pond;
        Double valorPond;
        
        int nBandas = bandas.size();
        for (int i = 0; i < nBandas; i++) {
            idSerie = SerieRA2.getSerieRA2PorCodigo(bandas.get(i)).getIdSerie();
            pond = Ponderacion.getPonderacionPorId(idSerie);
            valorPond = pond != null ? pond.getValor() : 0.0;

            campos.add("10 * LOG10(SUM(POWER(10, 0.1 * (" + bandas.get(i) + " + " + valorPond + "))) / (1.0 * COUNT(*)))");
        }

        String sqlExtra = InteraccionBD.anadeSqlExtra(null, "GROUP BY ROUND(" + CAMPO_V_S + ", 0, 0)");
        */
        
        int nBandas = bandas.size();
        for (int i = 0; i < nBandas; i++) {
            campos.add(bandas.get(i));
        }
        
        String sqlExtra = null;

        if (rF != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_RF, "=", rF);
        }
        
        //Queremos los datos válidos
        condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_VALIDO, "=", 1);
        condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_VALIDO_SYS, "=", 1);

        return getDatosBin(Auxiliares.TIPO_OCT, idAsunto, idSite, campos, condicion, paramsPS, CAMPO_V_S, valBinMin, valBinMax, sqlExtra, null);
    }
/*
    public static ArrayList<Object[]> getResultadosOCTCorregidos(Integer idAsunto, Integer idSite, Integer valBinMin, Integer valBinMax) throws SQLException, NoSuchFieldException {
        ArrayList<Object[]> res = new ArrayList<Object[]>();
        Object[] resAux;

        int posAG = 0, posRF = 0;
        Object[] resAGBin, resRFBin;
        Double valAG, valRF;
        int nDatosBin, nFrecs;
        Double nivelesFrec[];
        
        for (int i = valBinMin; i <= valBinMax; i++) { //Por cada bin
            //Datos de Aero + RF
            ArrayList<Object[]> resOCTPondAG = getResultadosOCTPond(idAsunto, idSite, 0, i, i);
            
            nDatosBin = resOCTPondAG != null ? resOCTPondAG.size() : 0;
            nFrecs = resOCTPondAG != null && resOCTPondAG.get(0) != null ? resOCTPondAG.get(0).length - 1: 0;
            
            resAGBin = nFrecs > 0 ? new Object[nFrecs] : null;
            
            if (resAGBin != null) {
                for (int j = 0; j < nFrecs; j++) {
                    nivelesFrec = new Double[nDatosBin];

                    for (int k = 0; k < nDatosBin; k++) { //Cargamos los niveles para esa frecuencia en cada uno de los datos del bin
                        nivelesFrec[k] = ((BigDecimal) resOCTPondAG.get(k)[j + 1]).doubleValue();
                    }

                    //Obtenemos el nivel promedio para la frecuencia
                    resAGBin[j] = getNivelPromedioEnergetico(nivelesFrec);
                }
            }
            
            //Datos de RF
            ArrayList<Object[]> resOCTPondRF = getResultadosOCTPond(idAsunto, idSite, 1, i, i);
            
            nDatosBin = resOCTPondRF != null ? resOCTPondRF.size() : 0;
            nFrecs = resOCTPondRF != null && resOCTPondRF.get(0) != null ? resOCTPondRF.get(0).length - 1: 0;
            
            resRFBin = nFrecs > 0 ? new Object[nFrecs] : null;

            if (resRFBin != null) {
                for (int j = 0; j < nFrecs; j++) {
                    nivelesFrec = new Double[nDatosBin];

                    for (int k = 0; k < nDatosBin; k++) { //Cargamos los niveles para esa frecuencia en cada uno de los datos del bin
                        nivelesFrec[k] = ((BigDecimal) resOCTPondRF.get(k)[j + 1]).doubleValue();
                    }

                    //Obtenemos el nivel promedio para la frecuencia
                    resRFBin[j] = getNivelPromedioEnergetico(nivelesFrec);
                }
            }
            
            //Realizamos la corrección para cada frecuencia
            if (resAGBin != null || resRFBin != null) {
                resAux = resAGBin != null ? new Object[resAGBin.length + 1] : new Object[resRFBin.length + 1];

                resAux[0] = i;
                int nResAux = resAux.length - 1;
                for (int j = 0; j < nResAux; j++) {
                    valAG = resAGBin != null ? (Double) resAGBin[j] : 0.0;
                    valRF = resRFBin != null ? (Double) resRFBin[j] : 0.0;

                    if (!valAG.equals(valRF)) {
                        resAux[j + 1] = corrigeRF(valAG, valRF);
                    } else
                        resAux[j + 1] = null;
                }

                res.add(resAux);
            }

        }

        return res;
    }
*/
    
    public static ArrayList<ArrayList<Entry<Double, Integer>>> getResultadosOCTCorregidos(Integer idAsunto, Integer idSite, Integer valBinMin, Integer valBinMax) throws SQLException, NoSuchFieldException {
        ArrayList<ArrayList<Entry<Double, Integer>>> res = new ArrayList<ArrayList<Entry<Double, Integer>>>();
        ArrayList<Entry<Double, Integer>> resAux;

        Object[] resAGBin, resRFBin;
        Double valAG, valRF;
        int nDatosBin, nFrecs;
        Double nivelesFrec[];
        
        for (int i = valBinMin; i <= valBinMax; i++) { //Por cada bin
            //Datos de Aero + RF
            ArrayList<Object[]> resOCTPondAG = getResultadosOCTPond(idAsunto, idSite, 0, i, i);
            
            nDatosBin = resOCTPondAG != null ? resOCTPondAG.size() : 0;
            nFrecs = resOCTPondAG != null && resOCTPondAG.get(0) != null ? resOCTPondAG.get(0).length - 1: 0;
            
            resAGBin = nFrecs > 0 ? new Object[nFrecs] : null;
            
            if (resAGBin != null) {
                for (int j = 0; j < nFrecs; j++) {
                    nivelesFrec = new Double[nDatosBin];

                    for (int k = 0; k < nDatosBin; k++) { //Cargamos los niveles para esa frecuencia en cada uno de los datos del bin
                        nivelesFrec[k] = ((BigDecimal) resOCTPondAG.get(k)[j + 1]).doubleValue();
                    }

                    //Obtenemos el nivel promedio para la frecuencia
                    resAGBin[j] = getNivelPromedioEnergetico(nivelesFrec);
                }
            }
            
            //Datos de RF
            ArrayList<Object[]> resOCTPondRF = getResultadosOCTPond(idAsunto, idSite, 1, i, i);
            
            nDatosBin = resOCTPondRF != null ? resOCTPondRF.size() : 0;
            nFrecs = resOCTPondRF != null && resOCTPondRF.get(0) != null ? resOCTPondRF.get(0).length - 1: 0;
            
            resRFBin = nFrecs > 0 ? new Object[nFrecs] : null;

            if (resRFBin != null) {
                for (int j = 0; j < nFrecs; j++) {
                    nivelesFrec = new Double[nDatosBin];

                    for (int k = 0; k < nDatosBin; k++) { //Cargamos los niveles para esa frecuencia en cada uno de los datos del bin
                        nivelesFrec[k] = ((BigDecimal) resOCTPondRF.get(k)[j + 1]).doubleValue();
                    }

                    //Obtenemos el nivel promedio para la frecuencia
                    resRFBin[j] = getNivelPromedioEnergetico(nivelesFrec);
                }
            }
            
            //Realizamos la corrección para cada frecuencia
            resAux = new ArrayList<Entry<Double, Integer>>();
            if (resAGBin != null || resRFBin != null) {
                int nResAux = resAGBin != null ? resAGBin.length : resRFBin.length;
                for (int j = 0; j < nResAux; j++) {
                    valAG = resAGBin != null ? (Double) resAGBin[j] : 0.0;
                    valRF = resRFBin != null ? (Double) resRFBin[j] : 0.0;

                    resAux.add(getCorreccionRF(valAG, valRF));
                }
            }

            res.add(resAux);
        }

        return res;
    }

    public static ArrayList<Object[]> getResultadosFFT(Integer idAsunto, Integer idSite, Integer rF, Integer valBinMin, Integer valBinMax) throws SQLException, NoSuchFieldException {
        ArrayList<String> campos = new ArrayList<String>();
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        String condicion = "";

        campos.add(CAMPO_XML);

        if (rF != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_RF, "=", rF);
        }

        //Solo queremos los datos válidos
        condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_VALIDO, "=", 1);
        condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_VALIDO_SYS, "=", 1);

        return getDatosBin(Auxiliares.TIPO_FFT, idAsunto, idSite, campos, condicion, paramsPS, CAMPO_V_S, valBinMin, valBinMax, null, null);
    }
    
    public static ArrayList<Double[][]> getEspectrosFFTBin(ArrayList<Object[]> resultadosFFT, Integer valBin) throws Exception {
        ArrayList<Double[][]> res = new ArrayList<Double[][]>();
        int nResFFT = resultadosFFT.size();
        Integer bin;
        String xml;
        ArrayList<DatoXML> datosXML;
        int nDatosXML;
        DatoXML datoXML;
        Double[][] resAux;
        
        for (int i = 0; i < nResFFT; i++) {
            bin = (Integer) resultadosFFT.get(i)[0];
            
            if (bin < valBin)
                continue;
            if (bin > valBin)
                break;
            
            xml = (String) resultadosFFT.get(i)[1];
            datosXML = InteraccionXML.leeXml(xml);
            nDatosXML = datosXML.size();
            
            if (nDatosXML > 0) { 
                resAux = new Double[nDatosXML][2];

                for (int j = 0; j < nDatosXML; j++) {
                    datoXML = datosXML.get(j);

                    resAux[j] = new Double[]{datoXML.getFrecuencia(), datoXML.getNivel()};
                }

                res.add(resAux);
            }
        }
        
        if (res.isEmpty())
            res = null;
        
        return res;
    }

    public static ArrayList<ResultadoBinFFT> getResultadosFFTClasificados(Integer idAsunto, Integer idSite, Integer valBinMin, Integer valBinMax, Double desdeFrec, Double hastaFrec, JProgressBar jpb, Double porc, Boolean corregirRF) throws SQLException, NoSuchFieldException, Exception {
        ArrayList<ResultadoBinFFT> res = new ArrayList<ResultadoBinFFT>();
        ArrayList<ResultadoEspectroFFT> resEspectro = null;
        ArrayList<ResultadoBandaCriticaFFT> resBandaCritica;

        ArrayList<Object[]> resFFT_AG = getResultadosFFT(idAsunto, idSite, 0, valBinMin, valBinMax);
        ArrayList<Object[]> resFFT_RF = getResultadosFFT(idAsunto, idSite, 1, valBinMin, valBinMax);

        ArrayList<Double[]> arrayEspectroAG, arrayEspectroRF;
        Double[][] espectroAG, espectroRF, bandaCritica, bandaCriticaRF, bandaCriticaClasificada;
        ArrayList<Double[][]> espectrosRFBin = null;
        LinkedHashMap<Integer, Double[][]> binEspectroRF = new LinkedHashMap<Integer, Double[][]>();
        Double[] frecsMax;
        int nFrecsMax;
        Double frecMax, nivelFrecMax, nivelMedioBC, nivelCriterio, nivelEnmascaramiento;
        Double[] limitesBC;
        
        int nResFFT;
        String xml;
        ArrayList<DatoXML> datosXML;
        int nDatosXML;
        DatoXML datoXML;
        Double frec;
        
        int bin = -1, binAnt = -1;
        
        //Preparamos los datos de RF
		nResFFT = resFFT_RF.size();
		espectroRF = null;
		int nEspectrosRFBin;

		ArrayList<Double> frecEspRF;
		int nFrecsRF;

		for (int i = 0; i < nResFFT; i++) {
			bin = (Integer) resFFT_RF.get(i)[0];

			if (bin != binAnt) {
				//Guardamos los cambios
				if (i != 0) {
					//Calculamos el promedio energético de los espectros del bin
					nEspectrosRFBin = espectrosRFBin.size();
					if (nEspectrosRFBin > 0) {
						nFrecsRF = espectrosRFBin.get(0).length;
						espectroRF = new Double[nFrecsRF][2];

						for (int j = 0; j < nFrecsRF; j++) {
							frecEspRF = new ArrayList<Double>();
							for (int k = 0; k < nEspectrosRFBin; k++) {
								frecEspRF.add(espectrosRFBin.get(k)[j][1]);
							}
							espectroRF[j] = new Double[]{espectrosRFBin.get(0)[j][0], getNivelPromedioEnergetico(Auxiliares.arrayObjToDouble(frecEspRF.toArray()))};
						}

						binEspectroRF.put(binAnt, espectroRF);
					}
				}

				binAnt = bin;
				espectrosRFBin = new ArrayList<Double[][]>();
			}

			arrayEspectroRF = new ArrayList<Double[]>();

			xml = (String) resFFT_RF.get(i)[1];
			datosXML = InteraccionXML.leeXml(xml);
			nDatosXML = datosXML.size();

			for (int j = 0; j < nDatosXML; j++) {
				datoXML = datosXML.get(j);

				frec = datoXML.getFrecuencia();
				if (frec >= desdeFrec && frec <= hastaFrec) {  //Si es válido
					arrayEspectroRF.add(new Double[]{frec, datoXML.getNivel()}); //Frecuencia, nivel
				}
			}

			espectroRF = Auxiliares.arrayObjToDoubleDouble(arrayEspectroRF.toArray());

			espectrosRFBin.add(espectroRF);
		}
		//Caso de salida
		if (nResFFT > 0) {
			//Calculamos el promedio energético de los espectros del bin
			nEspectrosRFBin = espectrosRFBin.size();
			if (nEspectrosRFBin > 0) {
				nFrecsRF = espectrosRFBin.get(0).length;
				espectroRF = new Double[nFrecsRF][2];

				for (int j = 0; j < nFrecsRF; j++) {
					frecEspRF = new ArrayList<Double>();
					for (int k = 0; k < nEspectrosRFBin; k++) {
						frecEspRF.add(espectrosRFBin.get(k)[j][1]);
					}

					espectroRF[j] = new Double[]{espectrosRFBin.get(0)[j][0], getNivelPromedioEnergetico(Auxiliares.arrayObjToDouble(frecEspRF.toArray()))};
				}

				binEspectroRF.put(binAnt, espectroRF);
			}
		}
        
        nResFFT = resFFT_AG.size();
        bin = -1;
        binAnt = -1;
        for (int i = 0; i < nResFFT; i++) {
            bin = (Integer) resFFT_AG.get(i)[0];

            if (bin != binAnt) {
                //Guardamos los cambios
                if (i != 0) {
                    res.add(new ResultadoBinFFT(binAnt, resEspectro));
                }

                binAnt = bin;
                resEspectro = new ArrayList<ResultadoEspectroFFT>();
            }

            arrayEspectroAG = new ArrayList<Double[]>();

            xml = (String) resFFT_AG.get(i)[1];
            datosXML = InteraccionXML.leeXml(xml);
            nDatosXML = datosXML.size();

            for (int j = 0; j < nDatosXML; j++) {
                datoXML = datosXML.get(j);

                frec = datoXML.getFrecuencia();
                if (frec >= desdeFrec && frec <= hastaFrec) {  //Si es válido
                    arrayEspectroAG.add(new Double[]{datoXML.getFrecuencia(), datoXML.getNivel()}); //Frecuencia, nivel
                }
            }

            espectroAG = Auxiliares.arrayObjToDoubleDouble(arrayEspectroAG.toArray());

            frecsMax = getFrecuenciasMaxLocalesEspectro(espectroAG);

            nFrecsMax = frecsMax.length;

            if (nFrecsMax > 0) {
                resBandaCritica = new ArrayList<ResultadoBandaCriticaFFT>();
                for (int j = 0; j < nFrecsMax; j++) {
                    frecMax = frecsMax[j];

                    limitesBC = getLimBandaCritica(frecMax);
                    bandaCritica = getBandaCritica(espectroAG, limitesBC);
					bandaCriticaRF = getBandaCritica(binEspectroRF.get(bin), limitesBC);
                    nivelMedioBC = getNivelPromedioBandaCritica(frecMax, bandaCritica);

                    nivelCriterio = null;
                    nivelEnmascaramiento = null;
                    bandaCriticaClasificada = null;
                    
                    nivelFrecMax = getNivelFrecMax(bandaCritica, frecMax);

                    if (esFrecPosibleTono(nivelFrecMax, nivelMedioBC)) {
                        nivelCriterio = getNivelCriterio(bandaCritica); //L_70% + 6
                        nivelEnmascaramiento = getNivelPromedioEnmascaramiento(bandaCritica, nivelCriterio); //L_pn, avg
                        bandaCriticaClasificada = getBandaCriticaClasificada(bandaCritica, nivelFrecMax, nivelCriterio, nivelEnmascaramiento);

                        //Si no hay tono, no nos interesa
                        if (getTipoFrecMax(bandaCriticaClasificada, frecMax).equals(TIPO_TONO)) {
                            resBandaCritica.add(new ResultadoBandaCriticaFFT(frecMax, nivelFrecMax, limitesBC[0], limitesBC[1], nivelCriterio, nivelEnmascaramiento, bandaCriticaClasificada, bandaCriticaRF, corregirRF));
                        }
                    }
                    Auxiliares.incPorcentajeProgress(jpb, porc / (nResFFT * nFrecsMax));
                }
                resEspectro.add(new ResultadoEspectroFFT(xml, resBandaCritica));
            } else {
                Auxiliares.incPorcentajeProgress(jpb, porc / nResFFT);
            }
        }

        //Caso de salida
        if (nResFFT > 0) {
            res.add(new ResultadoBinFFT(binAnt, resEspectro));
        }

        return res;
    }

    public static void deleteVistas() throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
		if (VISTA_NETA != null)
			interBD.borraVista(VISTA_NETA);

		if (VISTA_AUX != null) {
			interBD.borraVista(VISTA_AUX);
        
			interBD.borraTabla(CURVA_AUX + VISTA_AUX);
			interBD.borraTabla(VALI_SYS_AUX + VISTA_AUX);
		}
    }

    //Devuelve la cadena de la función dados los parámetros pasados y = coefBin_0 + coefBin_1*x + coefBin_2*x^2 + ... + coefBin_n*x^n
    public static String getRectaCoef(double[] coefBin) {
        String res = "y = ";

        if (coefBin != null) {
            int nCoefBin = coefBin.length - 1; //Por el coeficiente de correlación

            for (int i = 0; i < nCoefBin; i++) {
                if (coefBin[i] == 0.0) {
                    continue;
                //Añadimos el signo
                }
                if (res.compareTo("y = ") == 0) { //Todavía no se a añadido nada
                    if (coefBin[i] < 0) {
                        res += "-";
                    }
                } else { //Resto de coeficientes
                    if (coefBin[i] < 0) {
                        res += " - ";
                    } else {
                        res += " + ";
                    }
                }

                //Añadimos el coeficiente
                res += Math.abs(coefBin[i]);

                //Añadimos la x
                if (i > 0) {
                    res += " x";
                    if (i > 1) {
                        res += "^" + i;
                    }
                }
            }
        }

        return res;
    }
    //Función que devuelve el ancho de la banda crítica
    public static Double getAnchoBandaCritica(Double frecuencia) {
        return 25 + 75 * Math.pow(1 + 1.4 * Math.pow(frecuencia / 1000.0, 2.0), 0.69);
    }
    //Función que devuelve las frecuencias <desdeFrec, hastaFrec> que delimitan la banda crítica centrada en la frecuencia <frecuencia>
    //Si la frecuencia está entre 20 y 70 Hz, la Banda crítica será entre 20 y 120 Hz
    public static Double[] getLimBandaCritica(Double frecuencia) {
        Double[] res;

        if (frecuencia >= 20 && frecuencia <= 70) {
            res = new Double[]{20.0, 120.0};
        } else {
            Double anchoBC = getAnchoBandaCritica(frecuencia);
            Double iniBC = TratDecimales.round(frecuencia - anchoBC / 2.0, TratDecimales.DEC_VARIABLE_RA);
            if (iniBC < 0.0) {
                iniBC = 0.0;
            }
            Double finBC = TratDecimales.round(frecuencia + anchoBC / 2.0, TratDecimales.DEC_VARIABLE_RA);
            res = new Double[]{iniBC, finBC};
        }

        return res;
    }
    //Función que devuelve la lista de frecuencias <frecMax1, ..., frecMaxn> que son máximos locales en el espectro <espectro> = <<frec1, nivel1>, ..., <frecn, niveln>>
    public static Double[] getFrecuenciasMaxLocalesEspectro(Double[][] espectro) {
        ArrayList<Double> res = new ArrayList<Double>();
        Double valAnt, val, valSig;

        valAnt = Double.MIN_VALUE;
        val = Double.MIN_VALUE;
        valSig = Double.MIN_VALUE;
        
        int nDatos = espectro.length;
        
        for (int i = 0; i < nDatos; i++) {
            if (i >= 1)
                valAnt = val;
            
            val = espectro[i][1];
            
            if (i < nDatos - 1)
                valSig = espectro[i + 1][1];
            else
                valSig = Double.MIN_VALUE;
                
            if (val > valAnt && val > valSig) { //Es un máximo local
                res.add(espectro[i][0]);
            }
        }

        return Auxiliares.arrayObjToDouble(res.toArray());
    }
    
    //Función que devuelve la banda crítica de un espectro, pasados unos límites de banda crítica
    public static Double[][] getBandaCritica(Double[][] espectro, Double[] limitesBC) {
        int nDatosEspectro = espectro.length;
        int posIni = -1, posFin = nDatosEspectro;
        
        for (int i = 0; i < nDatosEspectro; i++) {
            if (posIni == -1 && espectro[i][0] >= limitesBC[0])
                posIni = i;
            
            if (posFin == nDatosEspectro && espectro[i][0] > limitesBC[1]) //Nos quedamos con la superior para hacer la copia
                posFin = i;
            
            if (posIni != -1 && posFin != nDatosEspectro)
                break;
        }
        
        Double[][] res = Arrays.copyOfRange(espectro, posIni, posFin);

        return res;
    }
    //Función que elimina de una banda crítica su máximo y sus dos frecuencias adyacentes
    public static Double[][] quitaMaxAdyacentes(Double[][] bandaCritica, Double frecMax) {
        Double[][] res;
        Double  frec = null;

        int nFrecs = bandaCritica.length;
        int posFrecMax = 0;

        for (posFrecMax = 0; posFrecMax < nFrecs; posFrecMax++) {
            frec = bandaCritica[posFrecMax][0];

            if (frec.equals(frecMax))  {
                break;
            }
        }
        
        if (posFrecMax == 0 || posFrecMax == nFrecs - 1)
            res = new Double[nFrecs - 2][2];
        else if (posFrecMax == nFrecs)
            res = new Double[nFrecs][2];
        else
            res = new Double[nFrecs - 3][2];
        
        int posRes = 0;
        for (int i = 0; i < nFrecs; i++) {
            if (i-1 <= posFrecMax && i+1 >= posFrecMax)
                continue;
            
            res[posRes++] = new Double[]{bandaCritica[i][0], bandaCritica[i][1]};
        }

        return res;
    }
    //Función que devuelve el promedio energétio de una serie de niveles hasta un tope de niveles <nNiveles>. Si null, entonces todos
    //<L> = 10 * log (1/n SUM(10^(0.1*nivel), 1, nDatos)
    public static Double getNivelPromedioEnergetico(Double[] niveles, Integer nNiveles) {
         Double  res = 0.0, sumatorio = 0.0;

        if (nNiveles == null) {
            nNiveles = niveles != null ? niveles.length : 0; 
        }
        for (int i = 0; i < nNiveles; i++) {
            sumatorio += Math.pow(10, 0.1 * niveles[i]);
        }

        if (nNiveles > 0 && sumatorio > 0) {
            res = 10 * Math.log10(sumatorio / nNiveles);
        }
        return TratDecimales.round(res, TratDecimales.DEC_VARIABLE_RA);
    }
    //Función que devuelve el promedio energétio de una serie de niveles
    //<L> = 10 * log (1/n SUM(10^(0.1*nivel), 1, nDatos)
    public static Double getNivelPromedioEnergetico(Double[] niveles) {
        return getNivelPromedioEnergetico(niveles, null);
    }
    
    private static Double[] getFrecuenciasEspectro(Double[][] espectro) {
        int nDatos = espectro.length;
        Double res[] = new Double[nDatos];
        
        for (int i = 0; i < nDatos; i++) {
            res[i] = espectro[i][0];
        }
        
        return res;
    }
    
    private static Double[] getNivelesEspectro(Double[][] espectro) {
        int nDatos = espectro.length;
        Double res[] = new Double[nDatos];
        
        for (int i = 0; i < nDatos; i++) {
            res[i] = espectro[i][1];
        }
        
        return res;
    }
    
    private static int[] getTiposBcClasificada(Double[][] bcClasificada) {
        int nDatos = bcClasificada.length;
        int res[] = new int[nDatos];
        
        for (int i = 0; i < nDatos; i++) {
            res[i] = bcClasificada[i][2].intValue();
        }
        
        return res;
    }
    
    private static Double[] getNivelesOrdenadosEspectro(Double[][] espectro) {
        Double res[] = getNivelesEspectro(espectro);
        
        Arrays.sort(res);
        
        return res;
    }
    
    //Función que devuelve el nivel promedio de una banda crítica centrada en <frecMax>, una vez eliminada esta frecuencia y sus adyacentes
    public static Double getNivelPromedioBandaCritica(Double frecMax, Double[][] bandaCritica) {
        Double[][] bandaCriticaSinMaxAdy = quitaMaxAdyacentes(bandaCritica, frecMax);

        return getNivelPromedioEnergetico(getNivelesEspectro(bandaCriticaSinMaxAdy));
    }
    //Función que indica si una frecuencia es un posible tono dado un nivel medio de banda crítica
    public static boolean esFrecPosibleTono(Double nivelFrec, Double nivelMedioBC) {
        return (nivelFrec - nivelMedioBC > 6);
    }
    //Función que devuelve el nivel promedio del porcentaje <porc> inferior de una <bandaCritica>
    public static Double getNivelPromedioPorcInferior(Double porc, Double[][] bandaCritica) {
        Double[] valoresOrdenados = getNivelesOrdenadosEspectro(bandaCritica);

        //Nos quedamos con los valores que caigan dentro del porcentaje <porc>
        int nValores = (int) Math.round(valoresOrdenados.length * porc);

        return getNivelPromedioEnergetico(valoresOrdenados, nValores);
    }

    //Función que devuelve el nivel de criterio de una banda crítica para discernie qué es ruido de enmascaramiento de qué no
    //L_70%
    public static Double getNivelCriterio(Double[][] bandaCritica) {
        return getNivelPromedioPorcInferior(0.7, bandaCritica) + 6.0;
    }
    //Función que devuelve el nivel promedio del ruido de enmascaramiento de una <bandaCritica>, siendo éste el de aquellos niveles que queden por debajo del <nivelCriterio>
    //L_pn,avg
    public static Double getNivelPromedioEnmascaramiento(Double[][] bandaCritica, Double nivelCriterio) {
        Double[] valoresOrdenados = getNivelesOrdenadosEspectro(bandaCritica);
        int nValores = valoresOrdenados.length;
        
        if (nivelCriterio != null) {
            for (int i = 0; i < nValores; i++) {
                //En el momento en que el nivel del valor sea >= al del cirterio, no necesitamos seguir mirando
                if (valoresOrdenados[i] >= nivelCriterio) {
                    nValores = i;
                    break;
                }
            }
        }

        return getNivelPromedioEnergetico(valoresOrdenados, nValores);
    }
    
    public static int getPosFrecMax(Double[][] bandaCritica, Double frecMax) {
        return Arrays.binarySearch(getFrecuenciasEspectro(bandaCritica), frecMax);
    }
    
    public static Double getNivelFrecMax(Double[][] bandaCritica, Double frecMax) {
        Double res = null;
       
        int posFrecMax = getPosFrecMax(bandaCritica, frecMax);
        
        if (posFrecMax >= 0)
            res = bandaCritica[posFrecMax][1];
       
        return res;
    }
    
    public static Integer getTipoFrecMax(Double[][] bandaCritica, Double frecMax) {
        Integer res = null;
       
        int posFrecMax = getPosFrecMax(bandaCritica, frecMax);
        
        if (posFrecMax >= 0)
            res = bandaCritica[posFrecMax][2].intValue();
       
        return res;
    } 
    
    //Función que devuelve las bandas críticas con sus frecuencias clasificadas en tono, ruido de enmascaramiento o nada para cada uno de los posibles tonos de un <espectro>
    public static ArrayList<Double[][]> getBandasCriticasClasificadas(Double[][] espectro) {
        ArrayList<Double[][]> res = new ArrayList<Double[][]>();
        Double frecMax, nivelFrecMax, nivelCriterio, nivelEnmascaramiento;
        Double[][] bandaCritica;
        
        Double[] frecsMax = getFrecuenciasMaxLocalesEspectro(espectro);
        int nFrecsMax = frecsMax.length;
        
        for (int i = 0; i < nFrecsMax; i++) {
            frecMax = frecsMax[i];
            bandaCritica = getBandaCritica(espectro, getLimBandaCritica(frecMax));
            
            nivelFrecMax = getNivelFrecMax(bandaCritica, frecMax);
            
            if (esFrecPosibleTono(nivelFrecMax, getNivelPromedioBandaCritica(frecMax, bandaCritica))) {
                nivelCriterio = getNivelCriterio(bandaCritica);
                nivelEnmascaramiento = getNivelPromedioEnmascaramiento(bandaCritica, nivelCriterio);

                //Clasificamos la banda crítica
                res.add(getBandaCriticaClasificada(bandaCritica, nivelFrecMax, nivelCriterio, nivelEnmascaramiento));
            }
        }

        return res;
    }
    //Función para calisificar las frecuencias de una <bandaCrítica>, dado sus niveles de frec. máxima, de criterio de discriminación para ruido de enmascaramiento y del nivel de este ruido de enmascaramiento
    public static Double[][] getBandaCriticaClasificada(Double[][] bandaCritica, Double nivelFrecMax, Double nivelCriterio, Double nivelEnmascaramiento) {
        Double nivelFrec, nivelFrecAnt = null, nivelFrecSig = null;
        Integer tipoFrec;
        Double[] frecuencias = getFrecuenciasEspectro(bandaCritica);
        Double[] niveles = getNivelesEspectro(bandaCritica);
        int nFrecs = frecuencias.length;
        
        Double[][] res = new Double[nFrecs][3];

        for (int i = 0; i < nFrecs; i++) {
            nivelFrec = niveles[i];

            if (i + 1 < nFrecs) {
                nivelFrecSig = niveles[i + 1];
            } else {
                nivelFrecSig = null;
            }
            if (nivelFrec < nivelCriterio) {
                tipoFrec = TIPO_ENMASCARAMIENTO;
            } else if (nivelFrec > nivelEnmascaramiento + 6) {
                //Si los adyacentes son posibles tonos, habrá que ver si está a menos de 10dB del máximo
                if ((nivelFrecAnt != null && nivelFrecAnt > nivelEnmascaramiento + 6) || (nivelFrecSig != null && nivelFrecSig > nivelEnmascaramiento + 6)) {
					if (nivelFrecMax - nivelFrec < 10) {
                        tipoFrec = TIPO_TONO;
                    } else {
                        tipoFrec = TIPO_NADA;
                    }
                } else {
                    tipoFrec = TIPO_TONO;
                }
            } else {
                tipoFrec = TIPO_NADA;
            }
            
            res[i] = new Double[]{frecuencias[i], niveles[i], 1.0*tipoFrec};

            nivelFrecAnt = nivelFrec;
        }

        return res;
    }
    //Función que indica si hay o no frecuencias que sean consideradas tonos en una <bandaCriticaClasificada>
    public static boolean hayTonosBandaCritica(Double[][] bandaCriticaClasificada) {
        boolean res = false;

        int[] tipos = getTiposBcClasificada(bandaCriticaClasificada);
        int nTipos = tipos.length;

        for (int i = 0; i < nTipos; i++) {
            if (tipos[i] == TIPO_TONO) {
                res = true;
                break;
            }
        }

        return res;
    }
    //Función que indica si hay o no frecuencias adyacentes que sean consideradas tonos en una <bandaCriticaClasificada>
    public static boolean hayTonosAdyacentesBandaCritica(Double[][] bandaCriticaClasificada) {
        boolean res = false;
		Integer tipoFrec, tipoFrecAnt = null, tipoFrecSig = null;

        int[] tipos = getTiposBcClasificada(bandaCriticaClasificada);
        int nTipos = tipos.length;

        for (int i = 0; i < nTipos; i++) {
            tipoFrec = tipos[i];

            if (i + 1 < nTipos) {
                tipoFrecSig = tipos[i + 1];
            } else {
                tipoFrecSig = null;
            }
            if (tipoFrec == TIPO_TONO && ((tipoFrecAnt != null && tipoFrecAnt == TIPO_TONO) || (tipoFrecSig != null && tipoFrecSig == TIPO_TONO))) {
                res = true;
                break;
            }

            tipoFrecAnt = tipoFrec;
        }

        return res;
    }

    //Función que devuelve el nivel del tono de la <bandaCriticaClasificada>, obtenido como la suma de los niveles de las frecuencias consideradas como tonos
    //L_pt,j,k - <j> espectro, <k> bin
    public static Double getNivelTonoBandaCritica(Double[][] bandaCriticaClasificada) {
        Double res = 0.0;
        
        int nDatos = bandaCriticaClasificada.length;
        
        for (int i = 0; i < nDatos; i++) {
            if (bandaCriticaClasificada[i][2].intValue() == TIPO_TONO) {
                res += Math.pow(10.0, bandaCriticaClasificada[i][1] * 0.1);
            }
        }
        
        //Si hay líneas adyacentes, se aplica corrección por uso de la ventana Hannning
        if (hayTonosAdyacentesBandaCritica(bandaCriticaClasificada)) {
            res = res / 1.5;
        }
        
        if (res > 0.0)
            res = 10 * Math.log10(res);
        
        return res;
    }
    
    //Función que devuelve el nivel de una <bandaCritica>, obtenido como la suma de los niveles de las frecuencias
    //Se usará para corregir que un tono venga generado por ruido de fondo
    public static Double getNivelSumBandaCritica(Double[][] bandaCritica) {
        Double res = 0.0;
        
        Double[] niveles = getNivelesEspectro(bandaCritica);
        int nNiveles = niveles.length;
        
        for (int i = 0; i < nNiveles; i++) {
            res += Math.pow(10.0, niveles[i] * 0.1);
        }
        
        if (res > 0.0 && nNiveles > 0)
            res = 10 * Math.log10(res / nNiveles);
        
        return res;
    }
    //Función que devuelve el ancho efectivo de ruido, que es 1.5 veces la resolución de la frecuencia
    public static Double getAnchoEfectivoRuido(Double[][] bandaCritica) {
        Double res = 0.0;

        Double[] frecuencias = getFrecuenciasEspectro(bandaCritica);

        if (frecuencias.length >= 2) {
            res = 1.5 * (frecuencias[1] - frecuencias[0]);
        }
        return res;
    }

    //Función que devuelve el nivel de enmascaramiento para una <bandaCritica>
    //L_pn,j,k = L_pn,avg,j,k + 10 ln(anchoBandaCritica / anchoEfectivoRuido), con <L_pn,avg,j,k> como el nivel promedio de líneas de enmascaramiento corregidas por RF
    public static Double getNivelEnmascaramiento(Double[][] bandaCriticaAG, Double frecMax, Double[][] bandaCriticaRF, Double nivelCriterio, Boolean corregirRF) {
        Double res = null;

        //Debe ser > 6.0 dB
        Double nivelPromEnmCorregido = getNivelPromedioEnmascaramiento(bandaCriticaAG, nivelCriterio);
        Boolean correccionOk = true;
        
        if (corregirRF) {
            Entry<Double, Integer> nivelPromEnmCorregidoTipo = getCorreccionRF(nivelPromEnmCorregido, getNivelSumBandaCritica(bandaCriticaRF));
            nivelPromEnmCorregido = nivelPromEnmCorregidoTipo.getKey();
            
            correccionOk = !nivelPromEnmCorregidoTipo.getValue().equals(CORREGIDO_RF_ERROR);
        }

        if (correccionOk) {
            Double anchoEfectivoRuido = getAnchoEfectivoRuido(bandaCriticaAG);
            Double anchoBandaCritica = getAnchoBandaCritica(frecMax);

            if (anchoEfectivoRuido != 0.0 && anchoBandaCritica != 0.0) {
                res = nivelPromEnmCorregido + 10 * Math.log10(anchoBandaCritica / anchoEfectivoRuido);
            }
        }
        
        return res;
    }
    //Función que devuelve la tonalidad de un espectro
    //ΔL_tn, j,k = L_pt,j,k − L_pn,j,k -> Tonalidad_Espectro = Nivel_Tono - Nivel_Enmascaramientoç
    public static Double getTonalidadEspectro(Double[][] bandaCriticaAG, Double nivelfrecMax, Double[][] bandaCriticaRF, Double nivelCriterio, Double nivelEnmascaramiento, Boolean anadirTonalidadSinTonos, Boolean corregirRF) {
        Double res = null;
        Double nivelEnmascCorregido = null;

        Double[][] bandaCriticaClasificada = getBandaCriticaClasificada(bandaCriticaAG, nivelfrecMax, nivelCriterio, nivelEnmascaramiento);

        if (hayTonosBandaCritica(bandaCriticaClasificada)) {
            nivelEnmascCorregido = getNivelEnmascaramiento(bandaCriticaAG, nivelfrecMax, bandaCriticaRF, nivelCriterio, corregirRF);
            
            if (nivelEnmascCorregido != null)
                res = getNivelTonoBandaCritica(bandaCriticaClasificada) - nivelEnmascCorregido;
        } else { //Si no hay tonos en la banda crítica
			if (anadirTonalidadSinTonos) {
				Double anchoEfectivoRuido = getAnchoEfectivoRuido(bandaCriticaAG);
				Double anchoBandaCritica = getAnchoBandaCritica(nivelfrecMax);

				if (anchoEfectivoRuido != 0.0 && anchoBandaCritica != 0.0)
					res = -10 * Math.log(anchoBandaCritica / anchoEfectivoRuido);
			}
        }

        return res;
    }
    //Función que devuelve la tonalidad de un espectro
    //ΔL_tn, j,k = L_pt,j,k − L_pn,j,k -> Tonalidad_Espectro = Nivel_Tono - Nivel_Enmascaramientoç
    public static Double getTonalidadEspectro(Double[][] bandaCriticaClasificada, Double frecMax, Double[][] bandaCriticaRF, Double nivelCriterio, Boolean anadirTonalidadSinTonos, Boolean corregirRF) {
        Double res = null;
        Double nivelEnmascCorregido = null;
        
        int nDatos = bandaCriticaClasificada.length;
        Double[][] bandaCriticaAG = new Double[nDatos][2];
        
        for (int i = 0; i < nDatos; i++) {
            bandaCriticaAG[i] = new Double[]{bandaCriticaClasificada[i][0], bandaCriticaClasificada[i][1]};
            
        }
        
        if (hayTonosBandaCritica(bandaCriticaClasificada)) {
            nivelEnmascCorregido = getNivelEnmascaramiento(bandaCriticaAG, frecMax, bandaCriticaRF, nivelCriterio, corregirRF);
            
            if (nivelEnmascCorregido != null)
                res = getNivelTonoBandaCritica(bandaCriticaClasificada) - nivelEnmascCorregido;
        } else { //Si no hay tonos en la banda crítica
            if (anadirTonalidadSinTonos) {
                Double anchoEfectivoRuido = getAnchoEfectivoRuido(bandaCriticaAG);
                Double anchoBandaCritica = getAnchoBandaCritica(frecMax);

                if (anchoEfectivoRuido != 0.0 && anchoBandaCritica != 0.0)
                    res = -10 * Math.log(anchoBandaCritica / anchoEfectivoRuido);
            }
        }
        
        if (res != null)
            res = TratDecimales.round(res, TratDecimales.DEC_VARIABLE_RA);

        return res;
    }
    //Función que devuleve la tonalidad promedio para el bin como el promedio energético de las tonalidades de cada bin
    //ΔL_k
    public static Double getTonalidadPromedioEspectros(ArrayList<Double> tonalidadesBinEspectro) {
        ArrayList<Double> tonalidadesAux = new ArrayList<Double>();
        Double valor = null;
        int nTonalidades = tonalidadesBinEspectro != null ? tonalidadesBinEspectro.size() : 0;

        //Nos quedamos solo con los valores válidos para que el promedio se realice solo sobre ellos
        for (int i = 0; i < nTonalidades; i++) {
            valor = tonalidadesBinEspectro.get(i);

            if (valor != null)
                tonalidadesAux.add(valor);
        }
        
        return getNivelPromedioEnergetico(Auxiliares.arrayObjToDouble(tonalidadesAux.toArray()));
    }
    //Función que devuelve el criterio de audibilidad para la frecuencia del tono <frecTono>
    public static Double getCriterioAudibilidadDependienteFrecuencia(Double frecTono) {
        return -2 - Math.log10(1 + Math.pow(frecTono / 502, 2.5));
    }
    //ΔL_a,k = ΔL_k − L_a
    public static Double getAudibilidadTonal(Double tonalidadPromedio, Double frecTono) {
        return TratDecimales.round(tonalidadPromedio - getCriterioAudibilidadDependienteFrecuencia(frecTono), TratDecimales.DEC_VARIABLE_RA);
    }

    public static Double getNivelInc(Double nivel, double inc) {
        Double res = null;

        if (nivel != null) {
            res = nivel + inc;
        }

        return res;
    }
    
    public static ArrayList<Double> getDatosMedidosSPL(String tipoTabla, Integer idAsunto, Integer idSite, Integer bin, Integer rF) throws SQLException {
        ArrayList<Double> res = null;
        
        ArrayList<String> campos = new ArrayList<String>();
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        
        campos.add(CAMPO_L_A_EQ_1);
        
        condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_VALIDO, "=", 1);
        condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_VALIDO_SYS, "=", 1);
        
        if (rF != null)
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_RF, "=", rF);
        
        ArrayList<Object[]> resAux = getDatosBin(tipoTabla, idAsunto, idSite, campos, condicion, paramsPS, CAMPO_V_S, bin, bin, null, null);
        
        int nResAux = resAux != null ? resAux.size() : 0;
        
        if (nResAux > 0) {
            Object[] fila;
            
            res = new ArrayList<Double>();
            for (int i = 0; i < nResAux; i++) {
                fila = resAux.get(i);
                
                res.add(((BigDecimal) fila[1]).doubleValue());
            }
        }
        
        return res;
    }
    
    public static ArrayList<Double> getDatosEstimadosSPL(String tipoTabla, Integer idAsunto, Integer idSite, Integer bin, Integer rF, Integer tipoCalculo, HashMap<String, double[]> coeficientesPol, HashMap<String, HashMap<Integer, Double[]>> coeficientesBin) throws SQLException {
        ArrayList<Double> res = null;
        
        ArrayList<String> campos = new ArrayList<String>();
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        String prefRF = "";
        
        campos.add(CAMPO_V_S);
        
        condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_VALIDO, "=", 1);
        condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_VALIDO_SYS, "=", 1);
        
        if (rF != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_RF, "=", rF);
            
            if (rF == 0)
                prefRF = Auxiliares.PREF_DATOS_AG;
            else
                prefRF = Auxiliares.PREF_DATOS_RF;
        }
        
        ArrayList<Object[]> resAux = getDatosBin(tipoTabla, idAsunto, idSite, campos, condicion, paramsPS, CAMPO_V_S, bin, bin, null, null);
        
        int nResAux = resAux != null ? resAux.size() : 0;
        
        if (nResAux > 0) {
            Object[] fila;
            Double velocidad;
            double coef[] = null;
            PolynomialFunction2D funcion;
            
            if (tipoCalculo.equals(TIPO_CAL_REG_LIN)) {
                coef = new double[] {coeficientesBin.get(prefRF).get(bin)[0].doubleValue(), coeficientesBin.get(prefRF).get(bin)[1].doubleValue()};
            } else if (tipoCalculo.equals(TIPO_CAL_REG_POL)) {
                int nCoef;
                double coefAux[];

                coefAux = coeficientesPol.get(prefRF);
                
                //Para quitar el coeficiente de correlación
                nCoef = coefAux.length - 1;
                coef = new double[nCoef];
				System.arraycopy(coefAux, 0, coef, 0, nCoef);
            }
            
            funcion = new PolynomialFunction2D(coef);
            
            res = new ArrayList<Double>();
            for (int i = 0; i < nResAux; i++) {
                fila = resAux.get(i);
                
                velocidad = ((BigDecimal) fila[1]).doubleValue();
                
                //Aplicacmos los coeficientes
                //Jesús comenta que el dato estimado es sobre la velocidad central del bin, no sobre cada una de las velocidades de los puntos
                //res.add(funcion.getValue(velocidad));
                res.add(funcion.getValue(Math.round(velocidad)));
            }
        }
        
        return res;
    }
    
    public static DatosIncertidumbre getIncertidumbreSPL(Integer idNorma, ArrayList<AsuntoIncert> incertidumbres, String tipoTabla, Integer idAsunto, Integer idSite, Integer bin, Integer tipoCalculo, HashMap<String, double[]> coeficientesPol,
            HashMap<String, HashMap<Integer, Double[]>> coeficientesBin) throws SQLException {
        DatosIncertidumbre res = null;
        
        res = new DatosIncertidumbre(incertidumbres);
        ArrayList<Double> datosMedidos = DatosRA2.getDatosMedidosSPL(tipoTabla, idAsunto, idSite, bin, 0);
        ArrayList<Double> datosEstimados = DatosRA2.getDatosEstimadosSPL(tipoTabla, idAsunto, idSite, bin, 0, tipoCalculo, coeficientesPol, coeficientesBin);
        ArrayList<Double> datosMedidosRF = DatosRA2.getDatosMedidosSPL(tipoTabla, idAsunto, idSite, bin, 1);
        ArrayList<Double> datosEstimadosRF = DatosRA2.getDatosEstimadosSPL(tipoTabla, idAsunto, idSite, bin, 1, tipoCalculo, coeficientesPol, coeficientesBin);
        
        res.calculaUC(idNorma, tipoTabla, datosMedidos, datosEstimados, datosMedidosRF, datosEstimadosRF, null);
        
        return res;
    }
    
    public static ArrayList<Double> getDatosMedidosOCT(String tipoTabla, Integer idAsunto, Integer idSite, Integer bin, Integer rF, String campoFrecuencia) throws SQLException {
        ArrayList<Double> res = null;
        
        ArrayList<String> campos = new ArrayList<String>();
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        
        campos.add(campoFrecuencia);
        
        condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_VALIDO, "=", 1);
        condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_VALIDO_SYS, "=", 1);
        
        if (rF != null)
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_RF, "=", rF);
        
        ArrayList<Object[]> resAux = getDatosBin(tipoTabla, idAsunto, idSite, campos, condicion, paramsPS, CAMPO_V_S, bin, bin, null, null);
        
        int nResAux = resAux != null ? resAux.size() : 0;
        
        if (nResAux > 0) {
            Object[] fila;
            
            res = new ArrayList<Double>();
            for (int i = 0; i < nResAux; i++) {
                fila = resAux.get(i);
                
                res.add(((BigDecimal) fila[1]).doubleValue());
            }
        }
        
        return res;
    }
    
    public static DatosIncertidumbre getIncertidumbreOCT(Integer idNorma, ArrayList<AsuntoIncert> incertidumbres, String tipoTabla, Integer idAsunto, Integer idSite, Integer bin, String campoFrecuencia) throws SQLException {
        DatosIncertidumbre res = null;
        
        res = new DatosIncertidumbre(incertidumbres);
        ArrayList<Double> datosMedidos = DatosRA2.getDatosMedidosOCT(tipoTabla, idAsunto, idSite, bin, 0, campoFrecuencia);
        ArrayList<Double> datosEstimados = null;
        Double datoEstimado;
        int nDatos;
        ArrayList<Double> datosMedidosRF = DatosRA2.getDatosMedidosOCT(tipoTabla, idAsunto, idSite, bin, 1, campoFrecuencia);
        ArrayList<Double> datosEstimadosRF = null;
        
        
        nDatos = datosMedidos != null ? datosMedidos.size() : 0;
        if (nDatos > 0) {
            datoEstimado = getNivelPromedioEnergetico(Auxiliares.arrayObjToDouble(datosMedidos.toArray()));
            datosEstimados = new ArrayList<Double>();
        
            for (int i = 0; i < nDatos; i++) {
                datosEstimados.add(datoEstimado);
            }
        }
        

        nDatos = datosMedidosRF != null ? datosMedidosRF.size() : 0;
        if (nDatos > 0) {
            datoEstimado = getNivelPromedioEnergetico(Auxiliares.arrayObjToDouble(datosMedidosRF.toArray()));
            datosEstimadosRF = new ArrayList<Double>();
            
            for (int i = 0; i < nDatos; i++) {
                datosEstimadosRF.add(datoEstimado);
            }
        }
                
        res.calculaUC(idNorma, tipoTabla, datosMedidos, datosEstimados, datosMedidosRF, datosEstimadosRF, null);
        
        return res;
    }
    
    public static DatosIncertidumbre getIncertidumbreFFT(Integer idNorma, ArrayList<AsuntoIncert> incertidumbres, String tipoTabla, ArrayList<Double> tonalidades, Double valorTonalidadBin, ArrayList<Double> tonalidadesRF, Double valorTonalidadBinRF, Boolean fftEnMismaFrecuencia) throws SQLException {
        DatosIncertidumbre res = null;
        
        res = new DatosIncertidumbre(incertidumbres);
        ArrayList<Double> datosMedidos = new ArrayList<Double>();
        ArrayList<Double> datosEstimados = new ArrayList<Double>();
        int nTonalidades = tonalidades != null ? tonalidades.size() : 0;
        
        for (int i = 0; i < nTonalidades; i++) {
            datosMedidos.add(tonalidades.get(i));
            datosEstimados.add(valorTonalidadBin);
        }
        
        ArrayList<Double> datosMedidosRF = new ArrayList<Double>();
        ArrayList<Double> datosEstimadosRF = new ArrayList<Double>();
        int nTonalidadesRF = tonalidadesRF != null ? tonalidadesRF.size() : 0;
        
        for (int i = 0; i < nTonalidadesRF; i++) {
            datosMedidosRF.add(tonalidadesRF.get(i));
            datosEstimadosRF.add(valorTonalidadBinRF);
        }
        
        res.calculaUC(idNorma, tipoTabla, datosMedidos, datosEstimados, datosMedidosRF, datosEstimadosRF, fftEnMismaFrecuencia);
        
        return res;
    }
                
    //Función para calcular la pendiente de un mapa de ruido BWEA
    //S_dB
    public static Double getPendienteMapaRuido(Integer idAsunto, Integer tipoCalculoAG, Integer tipoCalculoRF, HashMap<String, double[]> coeficientesPol, HashMap<String, HashMap<Integer, Double[]>> coeficientesModosFunc, Integer valModoIni, Integer valModoFin) throws NumberFormatException, SQLException, NoSuchFieldException {
        HashMap<String, HashMap<Integer, Double[]>> coeficientesModosFuncAux = new HashMap<String, HashMap<Integer, Double[]>>();
        HashMap<Integer, Double[]> coefBinModosFuncAuxAG = new HashMap<Integer, Double[]>();
        HashMap<Integer, Double[]> coefBinModosFuncAuxRF = new HashMap<Integer, Double[]>();
        Double[] valBinAG = coeficientesModosFunc.get(Auxiliares.PREF_DATOS_AG).get(valModoIni);
        Double[] valBinRF = coeficientesModosFunc.get(Auxiliares.PREF_DATOS_RF).get(valModoIni);
        XYSeriesCollection datasetModoFunc = new XYSeriesCollection();
        XYSeries serieModoFunc = new XYSeries("modoFunc");
        Entry<Double, Integer> entryModoFunc;

        for (int k = 0; k < valModoFin - valModoIni + 1; k++) {
            //AG
            coefBinModosFuncAuxAG.put(k + valModoIni, valBinAG);

            //RF
            coefBinModosFuncAuxRF.put(k + valModoIni, valBinRF);
        }

        coeficientesModosFuncAux.put(Auxiliares.PREF_DATOS_AG, coefBinModosFuncAuxAG);
        coeficientesModosFuncAux.put(Auxiliares.PREF_DATOS_RF, coefBinModosFuncAuxRF);

        ArrayList<ArrayList<Entry<Double, Integer>>> datosCorregidosModoFunc = DatosRA2.getResultadosSPLCorregidoAparente(idAsunto, tipoCalculoAG, tipoCalculoRF, coeficientesPol, coeficientesModosFuncAux, valModoIni, valModoFin);
        for (int k = 0; k < valModoFin - valModoIni + 1; k++) {
            entryModoFunc = datosCorregidosModoFunc.get(k).get(2); //Cogemos el nivel corregido por ruido de fondo
            serieModoFunc.add(k + valModoIni, entryModoFunc.getKey());
        }

        datasetModoFunc.addSeries(serieModoFunc);
        
        return TratDecimales.round(getCoefBin(datasetModoFunc, 0)[1], TratDecimales.DEC_VARIABLE_RA);
    }
    
    //Función para calcular el nivel aparente declarado
    //L_Wd, 8m/s
    public static Double getNivelAparenteDeclarado(String tipoTabla, Integer idAsunto, Integer idSite, Integer valModoIni, Integer valModoFin, Double valorAp) throws SQLException {
        Double res = null;
        
        ArrayList<String> campos = new ArrayList<String>();
        campos.add(DatosRA2.CAMPO_L_A_EQ_1);
        String sqlExtra = "AND " + DatosRA2.CAMPO_VALIDO + " = 1 AND " + DatosRA2.CAMPO_VALIDO_SYS + " = 1 AND " + DatosRA2.CAMPO_RF + " = 0";

        ArrayList<Object[]> datosNivelesNeta = DatosRA2.getDatosVistaNeta(tipoTabla, idAsunto, idSite, valModoIni - 0.5, valModoFin + 0.5, campos, sqlExtra, null);
        int nNiveles = datosNivelesNeta != null ? datosNivelesNeta.size() : 0;
        if (nNiveles > 0) {
            double niveles[] = new double[nNiveles];

            for (int j = 0; j < nNiveles; j++) {
                niveles[j] = ((BigDecimal) datosNivelesNeta.get(j)[0]).doubleValue();
            }

            Double stdDev = Math.sqrt(StatUtils.variance(niveles));

            res = TratDecimales.round(valorAp + 1.645 * stdDev, TratDecimales.DEC_VARIABLE_RA);

        }
        
        return res;
    }
    
    //Función para calcular el nivel de inmisión a 60 metros
    //L_p, 60m
    public static Double getNivelInmision60(Double valorApDec, Double penalty) {
        Double res = valorApDec != null ?  TratDecimales.round(valorApDec - 43.5 + penalty, TratDecimales.DEC_VARIABLE_RA) : null;
        
        return res;
    }
    
    //Función para calcular el nivel de inmisión a 25 metros
    //L_p, 25m
    public static Double getNivelInmision25(Double valorApDec, Double penalty) {
        Double res = valorApDec != null ?  TratDecimales.round(valorApDec - 36.0 + penalty, TratDecimales.DEC_VARIABLE_RA) : null;
        
        return res;
    }
    
    //Funcion para calcular la distancia X para obtener un nivel de ruido de Y decibelios a la altura de centro de rotor
    //X_YdB
    public static Double getDistanciaParaNivelDeRuido(Double valorApDecModo, Double valBin, Double pendModo, Double velMediaModo, Double penalty, Double nivelObjetivo) {
        Double res = null;
        
        if (valorApDecModo != null && pendModo != null) {
            if (pendModo.isInfinite())
                res = -1.0;
            else
                res = TratDecimales.round(Math.pow(10.0, (valorApDecModo + pendModo * (valBin - velMediaModo) + penalty - 8.0 - nivelObjetivo)/20.0), 5);
                //res = TratDecimales.round(Math.pow(10.0, (valorApDec + pendModo * (valBin - 8.0) + penalty - 8.0 - nivelObjetivo)/20.0), 5);
		//Jesús comenta que aunque sea esto lo que dice la norma, no tiene mucho sentido, así que en el modo que no contenga el 8 se pasará el valor aparente declarado
	    	//del valor medio del modo así como la velocidad media del modo.
        }
        
        return res;
    }
    
    public static Integer getModoBin(Integer valBin, ArrayList<Integer[]> modosFunc) {
        Integer res = null;
        
        int nModos = modosFunc.size();
        for (int j = 0; j < nModos; j++) {
            if (valBin >= modosFunc.get(j)[0] && valBin <= modosFunc.get(j)[1]) {
                res = j;
                break;
            }
        }
        
        return res;
    }

    //Función para obtener el dataset para dibujar un mapa de ruido
    public static YIntervalSeriesCollection getDatasetMapaRuido(String tipoTabla, Integer idAsunto, Integer idSite, Integer tipoCalculoAG, Integer tipoCalculoRF, HashMap<String, double[]> coeficientesPol, HashMap<String, HashMap<Integer, Double[]>> coeficientesModosFunc, Integer desdeValBin, Integer hastaValBin, Double pasoBin, Double desdeDist, Double hastaDist, Double pasoDist, ArrayList<Double> valorApModos, ArrayList<Integer[]> modosFunc, ArrayList<Boolean> tonosModosFunc) throws NumberFormatException, SQLException, NoSuchFieldException {
        YIntervalSeriesCollection dataset = new YIntervalSeriesCollection();
        YIntervalSeries serie45 = new YIntervalSeries("> 45 dB(A)");
        YIntervalSeries serie40 = new YIntervalSeries("40 - 45 dB(A)");
        YIntervalSeries serieMenos40 = new YIntervalSeries("< 40 dB(A)");
        YIntervalSeries serieSinModo = new YIntervalSeries("Modo no declarado");
        
        Double distNivel45 = null, distNivel40 = null;
        Integer modo, modoAnt = null;
        Double pendModo = null, valorApDecModo = null, velMediaModo = null;
        Boolean tonosModoFunc;
        Double penalty = null, vIn, vCut;
        
        Double pos45Low, pos45High, pos40Low, pos40High, posMenos40Low, posMenos40High, posSinModoLow, posSinModoHigh;
        
        AerogeneradorRA aero = AerogeneradorRA.getAeroPorId(AsuntoRA.getAsuntoPorId(idAsunto).getIdAero());
        vIn = aero.getVIn();
        vCut = aero.getVCut();
        
        //Calculamos las pendientes de los modos
        ArrayList<Double> pendModos =  new ArrayList<Double>();
        ArrayList<Double> valorApDecModos =  new ArrayList<Double>();
        ArrayList<Double> velMediaModos =  new ArrayList<Double>();
		
        int nModos = modosFunc.size();
        for (int i = 0; i < nModos; i++) {
            pendModos.add(getPendienteMapaRuido(idAsunto, tipoCalculoAG, tipoCalculoRF, coeficientesPol, coeficientesModosFunc, modosFunc.get(i)[0], modosFunc.get(i)[1]));
			valorApDecModos.add(getNivelAparenteDeclarado(tipoTabla, idAsunto, idSite, modosFunc.get(i)[0], modosFunc.get(i)[1], valorApModos.get(i)));

			if (modosFunc.get(i)[0] <= 8.0 && modosFunc.get(i)[1] >= 8.0)
				velMediaModo = 8.0;
			else
				velMediaModo = (modosFunc.get(i)[0] + modosFunc.get(i)[1]) / 2.0;
		    
			velMediaModos.add(velMediaModo);
        }

        for (Double posBin = desdeValBin.doubleValue(); posBin <= hastaValBin.doubleValue(); posBin += pasoBin) {
            modo = getModoBin((int) Math.round(posBin), modosFunc);

            pos45Low = pos45High = pos40Low = pos40High = posMenos40Low = posMenos40High = posSinModoLow = posSinModoHigh = null;

            //Cogemos la pendiente del modo
            if (modo != null) {
                if (modoAnt == null || !modo.equals(modoAnt)) {
                    if (modo >= 0) {
                        pendModo = pendModos.get(modo);
                        valorApDecModo = valorApDecModos.get(modo);
                        velMediaModo = velMediaModos.get(modo);

                        tonosModoFunc = tonosModosFunc.get(modo);

                        penalty = tonosModoFunc != null && tonosModoFunc ? 5.0 : 0.0;
                    } else if (modo == -1) {
                        pendModo = Double.NEGATIVE_INFINITY;
						valorApDecModo = null;
						velMediaModo = null;
                        penalty = 0.0;
                    } else if (modo == -2) {
                        pendModo = null;
						valorApDecModo = null;
						velMediaModo = null;
                        penalty = 0.0;
                    }

                    modoAnt = modo;
                }
            } else {
                if (posBin < vIn || posBin > vCut) {
                    posMenos40Low = desdeDist;
                    posMenos40High = hastaDist;
                } else {
                    posSinModoLow = desdeDist;
                    posSinModoHigh = hastaDist;
                }
            }
            
            if (modo != null) {
                distNivel45 = getDistanciaParaNivelDeRuido(valorApDecModo, posBin, pendModo, velMediaModo, penalty, 45.0);
                distNivel40 = getDistanciaParaNivelDeRuido(valorApDecModo, posBin, pendModo, velMediaModo, penalty, 40.0);

				if (distNivel40 != null && distNivel45 != null) {
					for (Double posDist = desdeDist; posDist <= hastaDist; posDist += pasoDist) {
						if (distNivel45 > posDist) {
							pos45Low = pos45Low != null && posDist > pos45Low ? pos45Low : posDist;
							pos45High = pos45High != null && posDist < pos45High ? pos45High : posDist;
						} else if (distNivel40 >= posDist) {
							pos40Low = pos40Low != null && posDist > pos40Low ? pos40Low : posDist;
							pos40High = pos40High != null && posDist < pos40High ? pos40High : posDist;
						} else {
							posMenos40Low = posMenos40Low != null && posDist > posMenos40Low ? posMenos40Low : posDist;
							posMenos40High = posMenos40High != null && posDist < posMenos40High ? posMenos40High : posDist;
						}
					}
                }
            }
            
            //< 40 dB
            serieMenos40.add(posBin, posMenos40Low != null ? posMenos40Low : hastaDist, posMenos40Low != null ? posMenos40Low : hastaDist, posMenos40High != null ? posMenos40High : hastaDist);
            //>= 40 dB <= 45
            serie40.add(posBin, pos40Low != null ? pos40Low : hastaDist, pos40Low != null ? pos40Low : hastaDist, pos40High != null ? pos40High : hastaDist);
            //> 45 dB
            serie45.add(posBin, pos45Low != null ? pos45Low : hastaDist, pos45Low != null ? pos45Low : hastaDist, pos45High != null ? pos45High : hastaDist);
            //Sin Modo
            serieSinModo.add(posBin, posSinModoLow != null ? posSinModoLow : hastaDist, posSinModoLow != null ? posSinModoLow : hastaDist, posSinModoHigh != null ? posSinModoHigh : hastaDist);
        }

        if (!serie45.isEmpty())
            dataset.addSeries(serie45);
        if (!serie40.isEmpty())
            dataset.addSeries(serie40);
        if (!serieMenos40.isEmpty())
            dataset.addSeries(serieMenos40);
        if (!serieSinModo.isEmpty())
            dataset.addSeries(serieSinModo);
        
  
        if (dataset.getSeriesCount() == 0)
            dataset = null;

        return dataset;
    }
    
    // Devuelve un vector con los sectores resultantes dado un centro y longitud
    public static ArrayList<double[]> getSector(double centro, double longitud){
        ArrayList<double[]> sectores = new ArrayList<double[]>();
        int n = ((Double) (360 /longitud)).intValue();
        
        for (int i = 0; i < n; i++) {
            double c = centro + i*longitud;
            double[] dato = new double[2];
            dato[0] = c - longitud/2.0;
            dato[1] = c + longitud/2.0;
            
            for (int j = 0; j < 2; j++) {
                if (dato[j] < 0)
                    dato[j] = TratDecimales.round(dato[j] + 360.0, TratDecimales.DEC_SECTOR);
                if (dato[j] >= 360)
                    dato[j] = TratDecimales.round(dato[j] - 360.0, TratDecimales.DEC_SECTOR);
            }
            
            sectores.add(i, dato);
        }
        
        return sectores;
    }

	// Devuelve el sector dada una dirección y una amplitud.
    public static Double[] getSectorDirAmp(Double direccion, Double amplitud) {
        Double min, max;
        
        min = TratDecimales.round(direccion - amplitud, TratDecimales.DEC_DIRECCION);
        if (min < 0)
            min = TratDecimales.round(min + 360, TratDecimales.DEC_DIRECCION);
        
        max = TratDecimales.round(direccion + amplitud, TratDecimales.DEC_DIRECCION);
        if (max > 360)
            max = TratDecimales.round(max - 360, TratDecimales.DEC_DIRECCION);
        
        return new Double[]{min.doubleValue(), max.doubleValue()};
    }
    
    //Función que devuelve el ángulo beta para validar el sector
    public static Double getBeta(Double z, Double zRef, Double hB, Double betaMin, Double betaMax) {
        Double res = null;
        
        if (!hB.equals(zRef))
            res = TratDecimales.round((z - zRef) * (betaMax - betaMin) / (hB - zRef) + betaMin, TratDecimales.DEC_SECTOR);
        
        return res;
    }
    
    public static Double getSumaEnergetica(ArrayList<Double> valores) {
        Double res = null;

		if (valores != null)
			while (valores.contains(null))
				valores.remove(null);
        
        int nValores = valores != null ? valores.size() : 0;
        
        if (nValores > 0) {
            res = 0.0;
        
			for (int i = 0; i < nValores; i++)
				res += Math.pow(10.0, valores.get(i) * 0.1);
			
			if (res > 0.0 && nValores > 0)
				res = 10 * Math.log10(res);
		}
        
        return res;
    }
    
    public static Double getPromedioEnergetico(ArrayList<Double> valores) {
        Double res = null;
        
        Double sumaEnergetica = getSumaEnergetica(valores);
        
        if (sumaEnergetica != null) {
            int nValores = valores != null ? valores.size() : 0;
        
            if (nValores > 0)
                res = sumaEnergetica + (10 * Math.log10(1.0 / nValores));
        }
        
        return res;
    }
        
    //Función que devuelve los niveles de tercios de octava normalizados de un espectro dados los niveles medidos en tercios y SPL
    //L_Aeq, n, i, j
    public static Double[] getNivelesNormalizadosEspectro(ArrayList<Double> nivelesMedidosTercios, Double nivelSPL) {
        Double[] res = null;
        
        int nNiveles = nivelesMedidosTercios != null ? nivelesMedidosTercios.size() : 0;
        
        if (nNiveles > 0 && nivelSPL != null) {
            Double nivelSPLCalculado = getSumaEnergetica(nivelesMedidosTercios);
            Double incremento = nivelSPL - nivelSPLCalculado;
            
            res = new Double[nNiveles];
            
            for (int i = 0; i < nNiveles; i++)
                res[i] = nivelesMedidosTercios.get(i) + incremento;
        }
       
        return res;
    }
    
    //Función que devuelve la incertidumbre tipo A
    public static Double getIncertidumbreTipoA(ArrayList<Double> valoresMedidos, Double valorPromedio) {
        Double res = null;
        
        int nDatos = valoresMedidos != null ? valoresMedidos.size() : 0;
        
        if (nDatos > 1) { //Si no, sería dicisión por 0
            Double sumatorio = 0.0;
            
            for (int i = 0; i < nDatos; i++)
                sumatorio += Math.pow(valoresMedidos.get(i) - valorPromedio, 2.0);
            
            res = Math.sqrt(sumatorio / (nDatos * (nDatos - 1)));
        }
        
        return res;        
    }
    
    //Función que devuelve la incertidumbre de una serie de medidas
    public static Double getIncertidumbreTipoBMedidas(ArrayList<AsuntoIncert> incertidumbres, Long fechaHora) throws SQLException, NoSuchFieldException {
        Double res = null;
        
        int nIncert = incertidumbres != null ? incertidumbres.size() : 0;
        Long fechaIniIncert = AsuntoIncert.getFechaIniIncertParaFecha(incertidumbres, fechaHora);
        
        if (nIncert > 0 && fechaHora != null) {
            res = 0.0;
        
            Integer idTipoIncert;
            Long fechaHoraIncert;
            for (int i = 0; i < nIncert; i++) {
                idTipoIncert = incertidumbres.get(i).getIdTipoIncert();
                fechaHoraIncert = incertidumbres.get(i).getDesdeFecha();
                
                if (fechaHoraIncert > fechaIniIncert) //Si hemos sobrepasado la hora de inicio no hace falta seguir mirando
                    break;
                else if (!fechaHoraIncert.equals(fechaIniIncert)) //Si no se correponde con la fecha de inicio que buscamos, ignoramos
                    continue;
                
                res += Math.pow(incertidumbres.get(i).getValor(), 2.0);
            }
            
            res = Math.sqrt(res);
        }
        
        return res;
    }

    //Función que devuelve la incertidumbre de tipo B promedio
    public static Double getIncertidumbreTipoBPromedio(ArrayList<Double> incertBBandaMedidas) {
        Double res = null;
        
        int nDatos = incertBBandaMedidas != null ? incertBBandaMedidas.size() : 0;
        
        if (nDatos > 0) {
            res = 0.0;
            
            for (int i = 0; i < nDatos; i++)
                res += Math.pow(incertBBandaMedidas.get(i), 2.0);
            
            res = Math.sqrt(res / nDatos);
        }
        
        return res;
    }

    //Función que devuelve el promedio aritmético
    public static Double getPromedioAritmetico(ArrayList<Double> valores) {
        Double res = null;
        
        int nDatos = valores != null ? valores.size() : 0;
        
        if (nDatos > 0) {
            res = 0.0;
            
            for (int i = 0; i < nDatos; i++)
                res += valores.get(i);
            
            res = res / nDatos;
        }
        
        return res;
    }
    
    //Función que devuleve la covarianza entre dos conjuntos de valores medidos y sus valores promedio
    public static Double getCovarianza(ArrayList<Double> valores1Medidos, Double valor1Promedio, ArrayList<Double> valores2Medidos, Double valor2Promedio) {
        Double res = null;
        
        if (valor1Promedio != null && valor2Promedio != null && valores1Medidos != null && valores2Medidos != null && valores1Medidos.size() == valores2Medidos.size()) {
            int nDatos = valores1Medidos.size();
            
            if (nDatos > 1) {
                res = 0.0;

                for (int i = 0; i < nDatos; i++)
                    res += (valores1Medidos.get(i) - valor1Promedio) * (valores2Medidos.get(i) - valor2Promedio);
                
                res = res / (nDatos - 1);
            }
        }
        
        return res;
    }
    
    private static Integer[] getPosPromedioInfSup(Double valorBuscado, ArrayList<Double> valores, Integer nDatos) {
        Integer[] res = null;
        
        int nValores = valores != null ? valores.size() : 0;
        if (nValores > 0) {
            Double valorInf = null, valorSup = null, valor, valorMin = null, valorMax = null;
            Integer posInf = null, posSup = null, posMin = null, posMinSig = null, posMax = null, posMaxAnt = null;
            
            for (int i = 0; i < nValores; i++) {
                valor = valores.get(i);

				if (valor == null)
					continue;

				if (posMax != null)
					posMaxAnt =  posMax;

				if (posMinSig == null && posMin != null)
					posMinSig = i;

				if (valorMin == null ) {
					valorMin = valor;
					posMin = i;
				}
            
                if (valor <= valorBuscado && (valorInf == null || valor > valorInf)) {
                    valorInf = valor;
                    posInf = i;
                } if (valor > valorBuscado && (valorSup == null || valor < valorSup)) {
                    valorSup = valor;
                    posSup = i;                             
                }

				valorMax = valor;
				posMax = i;
            }
            
            if (posInf == null && valorBuscado < valorMin && nDatos >= 10) {
                posInf = posMin;
                posSup = posMinSig;
            }
                
            if (posSup == null && valorBuscado > valorMax && nDatos >= 10) {
                posInf = posMaxAnt;
                posSup = posMax;
            }
            
            if (posInf != null && posSup != null) {
                res = new Integer[]{posInf, posSup};
            }
        }
        
        return res;
    }
    
    //Función para el cálculo de t a partir de una velocidad, su promedio inferior (<=) y superior (>)
    //t
    public static Double getT(Double velocidad, Double velPromedioInf, Double velPromedioSup) {
        Double res = null;
        
        if (velocidad != null && velPromedioInf != null && velPromedioSup != null)
            res = (velocidad - velPromedioInf) / (velPromedioSup - velPromedioInf);
        
        return res;
    }
    
    //Función que devueve el nivel estimado a una velocidad V dada sus velocidades y niveles SPL promedio inferior (<=) y superior (>) 
    //L_v(t)
    public static Double getNivelEstimadoBin(Double velocidad, Double velPromedioInf, Double velPromedioSup, Double nivelPromedioInf, Double nivelPromedioSup) {
        Double res = null;
        
        Double t = getT(velocidad, velPromedioInf, velPromedioSup);
        
        if (t != null && nivelPromedioInf != null && nivelPromedioSup != null)
            res = (1 - t) * nivelPromedioInf + t * nivelPromedioSup;
        
        return res;
    }
    
    //Función que devuelve la incertidumbre para un nivel calculado a una velocidad determinada
    //U_L_v(t)
    public static Double getIncertidumbreNivelCalculado(Double velocidad, Double velPromedioInf, Double velPromedioSup, Double incertVelocidadCombInf, Double incertVelocidadCombSup, Double incertNivelCombInf, Double incertNivelCombSup, Double covarianzaInf, Integer nDatosInf, Double covarianzaSup, Integer nDatosSup) {
        Double res = null;
        
        if (velocidad != null && incertNivelCombInf != null && incertNivelCombSup != null && incertVelocidadCombInf != null && incertVelocidadCombSup != null && covarianzaInf != null && nDatosInf != null && covarianzaSup != null && nDatosSup != null && nDatosInf != 0 && nDatosSup != 0) {
            Double t = getT(velocidad, velPromedioInf, velPromedioSup);
            Double u_L_cuadrado = Math.pow(1 - t, 2.0) * Math.pow(incertNivelCombInf, 2.0) + Math.pow(t, 2.0) * Math.pow(incertNivelCombSup, 2.0);
            Double cov_LV = Math.pow(1 - t, 2.0) * covarianzaInf / nDatosInf + Math.pow(t, 2.0) * covarianzaSup / nDatosSup;
            Double u_V_cuadrado = Math.pow(1 - t, 2.0) * Math.pow(incertVelocidadCombInf, 2.0) + Math.pow(t, 2.0) * Math.pow(incertVelocidadCombSup, 2.0);

            res = Math.sqrt(u_L_cuadrado - Math.pow(cov_LV, 2.0) / u_V_cuadrado);
        }
        
        return res;
    }
    
    //Función que devuelve el nivel vorregido por ruido de fondo según IEC 3.0
    //L_V, c, i, k
    public static Entry<Double, Integer> getCorrecionRF_IEC3(Double valorTotal, Double valorRF) {
        HashMap<Double, Integer> resAux = new HashMap<Double, Integer>();
        Double valorCorregido = null;
        Integer tipo = null;
        
        if (valorTotal != null) {
            if (valorRF == null) {
                valorCorregido = 10 * Math.log10(Math.pow(10, 0.1*valorTotal));
                tipo = CORREGIDO_RF_OK;
            } else if (valorTotal - valorRF >= 3.0) {
                valorCorregido = 10 * Math.log10(Math.pow(10, 0.1*valorTotal) - Math.pow(10, 0.1 * valorRF));
                tipo = CORREGIDO_RF_OK;
            } else {
                valorCorregido = valorTotal - 3.0;
                tipo = CORREGIDO_RF_AVISO_IEC3;
            }
        } else
            tipo = CORREGIDO_RF_ERROR;
        
        resAux.put(valorCorregido, tipo);

        Iterator it = resAux.entrySet().iterator();

        return (Entry<Double, Integer>) it.next();
    }
    
    //Función que devuelve la incertidumbre por realizar la corrección por ruido de fondo
    //u_c, i, k
    public static Double getIncertidumbreCorreccionRF(Double valorTotal, Double valorRF, Double incertCalcTotal, Double incertCalcRF) {
        Double res = null;
        
	if (valorTotal != null && valorRF != null && incertCalcTotal != null && incertCalcRF != null) {
	    if (valorTotal - valorRF < 3.0)
			valorRF = valorTotal - 3.0;
	    
	    res = Math.sqrt(Math.pow(incertCalcTotal * Math.pow(10, 0.1 * valorTotal), 2.0) + Math.pow(incertCalcRF * Math.pow(10, 0.1 * valorRF), 2.0)) / (Math.pow(10, 0.1 * valorTotal) - Math.pow(10, 0.1 * valorRF));
	}
        
        return res;
    }
    
    //Función que devuelve la incertidumbre de obtener la suma energética de las bandas de tercios de octava
    //u_L_WA, k
    public static Double getIncertidumbreSumarTercios(ArrayList<Double> nivelesTercios, ArrayList<Double> incertCorreccion) {
        Double res = null;
        
        int nDatos = nivelesTercios != null ? nivelesTercios.size() : 0;
        
        if (nDatos > 0 && incertCorreccion != null && !incertCorreccion.contains(null) && nDatos == incertCorreccion.size()) {
            Double numerador = 0.0;
            Double denominador = 0.0;
            
            for (int i = 0; i < nDatos; i++) {
                numerador += incertCorreccion.get(i) * Math.pow(10, 0.1 * nivelesTercios.get(i));
                denominador += Math.pow(10, 0.1 * nivelesTercios.get(i));
            }
            
            if (!denominador.equals(0.0))
                res = numerador / denominador;
        }
        
        return res;
    }
    
    //Función para obtener la velocidad correspondiente en altura de buje a una velocidad a 10 m
    public static Double getVelocidadEnBuje(Double velocidad10, Double hB, Double z0Ref) {
        Double res = null;
        
        if (velocidad10 != null && hB != null && z0Ref != null)
            res = velocidad10 * Math.log(hB / z0Ref)  / Math.log(10.0 / z0Ref);
                    
        return res;
    }
    
    public static ArrayList<Object[]> getResultadosOCTVelocidad(String tipoTabla, Integer idAsunto, Integer idSite, Integer rF, Integer valBinMin, Integer valBinMax) throws SQLException, NoSuchFieldException {
        ArrayList<String> bandas = SerieRA2.getCodigosPorIdAsuntoTipo(idAsunto, TipoRA.getTipoRAPorIdSite(idSite).getIdTipoRA());

        ArrayList<String> campos = new ArrayList<String>();
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        String condicion = "";

        campos.add(CAMPO_FECHA_HORA);
        campos.add(CAMPO_V_S);
        campos.add(CAMPO_L_A_EQ_1);
        
        int nBandas = bandas.size();
        for (int i = 0; i < nBandas; i++) {
            campos.add(bandas.get(i));
        }

        //Traemos los datos ordenados por fecha hora
        String sqlOrderBy = InteraccionBD.anadeCampoOrderBy(null, CAMPO_FECHA_HORA);
        String sqlExtra = InteraccionBD.anadeSqlExtra(null, sqlOrderBy);

        if (rF != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_RF, "=", rF);
        }
        
        //Queremos los datos válidos
        condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_VALIDO, "=", 1);
        condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_VALIDO_SYS, "=", 1);

        return getDatosBin(tipoTabla, idAsunto, idSite, campos, condicion, paramsPS, CAMPO_V_S, valBinMin, valBinMax, sqlExtra, null);
    }
    
    //Función que obtiene los resultados de tercios de octava para la norma IEC 3.0 para RF o AG y sus incertidumbres asociadas (como parámetro en resIncert)
    public static ArrayList<ArrayList<Double>> getResultadosOCT_IEC3_AG_RF(String tipoTabla, Integer idAsunto, Integer idSite, Integer rF, Integer valBinMin, Integer valBinMax, ArrayList<AsuntoIncert> incertAcus, ArrayList<AsuntoIncert> incertNoAcus, ArrayList<ArrayList<Double>> resIncert) throws SQLException, NoSuchFieldException {
        int nDatosBin, nFrecs = 0;
        Double nivelesNormalizados[];
        ArrayList<Object[]> resOCT;
        ArrayList<ArrayList<Double>> res;
        ArrayList<Double> resBin;
        ArrayList<Double> resIncertBin;
        
        Double velMedia, nivelMedio, covarianza;
        Double incertAVel, incertBVel;
        Double incertANivelBanda, incertBNivelBanda, incertBMedidasNivelBanda;
        ArrayList<Double> nivelesMedidos, incertBMedidasVel, velocidades, nivelesBanda, velocidadesMedias, nivelesMediosBin, covarianzasBin;
        ArrayList<ArrayList<Double>> nivelesMedios, covarianzas;
        ArrayList<Double[]> nivelesNormalizadosBin;
        ArrayList<Integer> nDatos = new ArrayList<Integer>();
        
        ResultadoIncert resultadoIncertVel, resultadoIncertNivel;
        //Se sigue la nomenclatura IEC 3.0
        //i -> Banda
        //j -> Medida
        //k -> Bin
        
        if (resIncert == null)
            resIncert = new ArrayList<ArrayList<Double>>();
        
        //Inicializamos estructuras
        velocidadesMedias = new ArrayList<Double>();
        nivelesMedios = new ArrayList<ArrayList<Double>>();
        covarianzas = new ArrayList<ArrayList<Double>>();
        
        ArrayList<ResultadoIncert> resIncertVel = new ArrayList<ResultadoIncert>();
        ArrayList<ArrayList<ResultadoIncert>> resIncertNivel = new ArrayList<ArrayList<ResultadoIncert>>();
        
        ArrayList<ResultadoIncert> resIncertNivelBin;
        
        for (int k = valBinMin; k <= valBinMax; k++) { //Por cada bin
            resOCT = getResultadosOCTVelocidad(tipoTabla, idAsunto, idSite, rF, k, k);
            
            nDatosBin = resOCT != null ? resOCT.size() : 0;
            nFrecs = resOCT != null && resOCT.get(0) != null ? resOCT.get(0).length - 4: 0; //En i = 0 está el número de Bin; en i = 1 la fechaHora; en i = 2 está la velocidad;  en i = 3 está el nivel SPL
            
			nDatos.add(nDatosBin);
			nivelesMediosBin = null;
			resIncertNivelBin = null;
			covarianzasBin = null;

			velMedia = null;
			resultadoIncertVel = null;

            if (nFrecs > 0 && nDatosBin > 0) {
                velocidades = new ArrayList<Double>();
                nivelesNormalizadosBin = new ArrayList<Double[]>();
                incertBMedidasVel = new ArrayList<Double>();
				nivelesMediosBin = new ArrayList<Double>();
				resIncertNivelBin = new ArrayList<ResultadoIncert>();
				covarianzasBin = new ArrayList<Double>();

                for (int j = 0; j < nDatosBin; j++) {
                    nivelesMedidos = new ArrayList<Double>();

                    for (int i = 0; i < nFrecs; i++) {
                        nivelesMedidos.add(((BigDecimal) resOCT.get(j)[i + 4]).doubleValue()); //En i = 0 está el número de Bin; en i = 1 la fechaHora; en i = 2 esá la velocidad, en i = 3 está el nivel SPL
                    }
                    
                    nivelesNormalizados = getNivelesNormalizadosEspectro(nivelesMedidos, ((BigDecimal) resOCT.get(j)[3]).doubleValue()); //L_Aeq,n,i,j
                    nivelesNormalizadosBin.add(nivelesNormalizados); //L_Aeq,n,i,j
                    
                    velocidades.add(((BigDecimal) resOCT.get(j)[2]).doubleValue()); //V_j,k
                    incertBMedidasVel.add(getIncertidumbreTipoBMedidas(incertNoAcus, (Long) resOCT.get(j)[1])); //u_V_j
                }
                
                //VELOCIDADES
                velMedia = getPromedioAritmetico(velocidades); //V_prom k
                incertAVel = getIncertidumbreTipoA(velocidades, velMedia); //s_V k
                incertBVel = getIncertidumbreTipoBPromedio(incertBMedidasVel); //u_V,k

				resultadoIncertVel = new ResultadoIncert(k, null, incertAVel, incertBVel, ResultadoIncert.TIPO_VELOCIDAD);
                
                //NIVELES
                for (int i = 0; i < nFrecs; i++) {
                    nivelesBanda = new ArrayList<Double>();
                    for (int j = 0; j < nDatosBin; j++) {
                        nivelesBanda.add(nivelesNormalizadosBin.get(j)[i]);
                    }

                    nivelMedio = getPromedioEnergetico(nivelesBanda); //L_prom i,k
                    incertANivelBanda = getIncertidumbreTipoA(nivelesBanda, nivelMedio); //s_L i,k
                    
                    //Cogemos la fechaHora del 1º porque debería ser igual para todas las medidas
                    incertBMedidasNivelBanda = getIncertidumbreTipoBMedidas(incertAcus, (Long) resOCT.get(0)[1]); //u_L i,j
                    //incertBNivelBanda = getIncertidumbreTipoBPromedio(incertBMedidasNivelBanda); //u_L i,k
                    incertBNivelBanda = incertBMedidasNivelBanda; //u_L i,k
                    
                    //Almacenamos el resultado de las incertidumbres
                    resultadoIncertNivel = new ResultadoIncert(k, i, incertANivelBanda, incertBNivelBanda, ResultadoIncert.TIPO_SPL);
                    resIncertNivelBin.add(resultadoIncertNivel);
                    
                    nivelesMediosBin.add(nivelMedio);
                    
                    covarianza = getCovarianza(nivelesBanda, nivelMedio, velocidades, velMedia); //cov_LV,i,k
                    covarianzasBin.add(covarianza);
                }
            }
			//Almacenamos el resultado de las incertidumbres
			resIncertVel.add(resultadoIncertVel);
					
			//Almacenamos los valores medios
			velocidadesMedias.add(velMedia);
			nivelesMedios.add(nivelesMediosBin);
			covarianzas.add(covarianzasBin);
			resIncertNivel.add(resIncertNivelBin);
        }
        
        Integer[] posPromedioInfSup;
        Double velPromedioInf, velPromedioSup;
        Double incertVelocidadCombInf, incertVelocidadCombSup;
        Double nivelPromedioInf, nivelPromedioSup;
        Double incertNivelCombInf, incertNivelCombSup;
        Double covarianzaInf, covarianzaSup;
        Integer nDatosInf, nDatosSup;
        
        res = new ArrayList<ArrayList<Double>>();
        
        for (int k = valBinMin; k <= valBinMax; k++) { //Por cada bin
			nFrecs = nivelesMedios != null && nivelesMedios.size() > 0 && nivelesMedios.get(k - valBinMin) != null ? nivelesMedios.get(k - valBinMin).size() : 0;
            resBin = null;
            resIncertBin = null;
            
			if (nDatos.get(k - valBinMin) > 0) {
				posPromedioInfSup = getPosPromedioInfSup(k * 1.0, velocidadesMedias, nDatos.get(k - valBinMin));
				if (posPromedioInfSup != null) {
					resBin = new ArrayList<Double>();
					resIncertBin = new ArrayList<Double>();
	    
					velPromedioInf = velocidadesMedias.get(posPromedioInfSup[0]);
					velPromedioSup = velocidadesMedias.get(posPromedioInfSup[1]);
					
					incertVelocidadCombInf = resIncertVel.get(posPromedioInfSup[0]).getIncertCombinada();
					incertVelocidadCombSup = resIncertVel.get(posPromedioInfSup[1]).getIncertCombinada();
					
					nDatosInf = nDatos.get(posPromedioInfSup[0]);
					nDatosSup = nDatos.get(posPromedioInfSup[1]);
		    
					for (int i = 0; i < nFrecs; i++) {
						nivelPromedioInf = nivelesMedios.get(posPromedioInfSup[0]).get(i);
						nivelPromedioSup = nivelesMedios.get(posPromedioInfSup[1]).get(i);
			
						incertNivelCombInf = resIncertNivel.get(posPromedioInfSup[0]).get(i).getIncertCombinada();
						incertNivelCombSup = resIncertNivel.get(posPromedioInfSup[1]).get(i).getIncertCombinada();
			
						covarianzaInf = covarianzas.get(posPromedioInfSup[0]).get(i);
						covarianzaSup = covarianzas.get(posPromedioInfSup[1]).get(i);
			
						resBin.add(getNivelEstimadoBin(k * 1.0, velPromedioInf, velPromedioSup, nivelPromedioInf, nivelPromedioSup)); //L_v(k,i)
			
						resIncertBin.add(getIncertidumbreNivelCalculado(k * 1.0, velPromedioInf, velPromedioSup, incertVelocidadCombInf, incertVelocidadCombSup, incertNivelCombInf, incertNivelCombSup, covarianzaInf, nDatosInf, covarianzaSup, nDatosSup));
					}
				}
			}
            
            res.add(resBin);
            resIncert.add(resIncertBin);
        }
        
        return res;
    }
    
    //Función que obtiene los resultados corregidos por RF de tercios de octava para la norma IEC 3.0
    public static ArrayList<ArrayList<Entry<Double, Integer>>> getResultadosOCTCorregidos_IEC3(String tipoTabla, Integer idAsunto, Integer idSite, Integer valBinMin, Integer valBinMax, ArrayList<AsuntoIncert> incertidumbres, ArrayList<ArrayList<Double>> resIncert) throws SQLException, NoSuchFieldException {
        ArrayList<ArrayList<Entry<Double, Integer>>> res = new ArrayList<ArrayList<Entry<Double, Integer>>>();
        
        ArrayList<ArrayList<Double>> resAG, resRF;
        ArrayList<ArrayList<Double>> resIncertAG, resIncertRF;
        
        //Separamos las incertiumbres en acústicas y no acústicas
        ArrayList<AsuntoIncert> incertAcus = new ArrayList<AsuntoIncert>();
        ArrayList<AsuntoIncert> incertNoAcus = new ArrayList<AsuntoIncert>();
        
        int nIncert = incertidumbres != null ? incertidumbres.size() : 0;
        
        for (int i = 0; i < nIncert; i++) {
            if (TipoIncert.getTipoIntecertPorIdTipoIncert(incertidumbres.get(i).getIdTipoIncert()).getEsAcustica())
                incertAcus.add(incertidumbres.get(i));
            else
                incertNoAcus.add(incertidumbres.get(i));
        }
        
        //Datos AG + RF
        resIncertAG = new ArrayList<ArrayList<Double>>();
        resAG = getResultadosOCT_IEC3_AG_RF(tipoTabla, idAsunto, idSite, 0, valBinMin, valBinMax, incertAcus, incertNoAcus, resIncertAG);
        //Datos RF
        resIncertRF = new ArrayList<ArrayList<Double>>();
        resRF = getResultadosOCT_IEC3_AG_RF(tipoTabla, idAsunto, idSite, 1, valBinMin, valBinMax, incertAcus, incertNoAcus, resIncertRF);
        
        if (resIncert == null)
            resIncert = new ArrayList<ArrayList<Double>>();
        
        //Corregimos por RF
        Double valorTotal, valorRF;
        Double incertCalcTotal, incertCalcRF;
        ArrayList<Entry<Double, Integer>> nivelCorregidoTercios;
        ArrayList<Double> incertTercios;

        int nFrecs = 0;

		int nResAG = resAG != null ? resAG.size() : 0;
	
		for (int i = 0; i < nResAG; i++) {
			int nResAGBin = resAG.get(i) != null ? resAG.get(i).size() : 0;
	    
			if (nResAGBin > nFrecs)
				nFrecs = nResAGBin;
		}
        
        for (int k = valBinMin; k <= valBinMax; k++) {
            nivelCorregidoTercios = new ArrayList<Entry<Double, Integer>>();
            incertTercios = new ArrayList<Double>();

            for (int i = 0; i < nFrecs; i++) {
                valorTotal = resAG.get(k - valBinMin) != null && resAG.get(k - valBinMin).size() > i ? resAG.get(k - valBinMin).get(i) : null;
                valorRF = resRF.get(k - valBinMin) != null && resRF.get(k - valBinMin).size() > i ? resRF.get(k - valBinMin).get(i) : null;
                
                incertCalcTotal = valorTotal != null ? resIncertAG.get(k - valBinMin).get(i) : null;
                incertCalcRF = valorRF != null ? resIncertRF.get(k - valBinMin).get(i) : null;
                
                nivelCorregidoTercios.add(getCorrecionRF_IEC3(valorTotal, valorRF)); //L_V,c,i,k
				incertTercios.add(getIncertidumbreCorreccionRF(valorTotal, valorRF, incertCalcTotal, incertCalcRF)); //u_c,i,k
            }
            
            res.add(nivelCorregidoTercios);
            resIncert.add(incertTercios);
        }
        
        return res;
    }

    //Función que obtiene los resultados de tercios de octava para la norma IEC 3.0
    public static ArrayList<ArrayList<Entry<Double, Integer>>> getResultadosSPLCorregidoAparente_IEC3(String tipoTabla, Integer idAsunto, Integer idSite, Integer valBinMin, Integer valBinMax, ArrayList<AsuntoIncert> incertidumbres, ArrayList<Double> resIncert) throws SQLException, NoSuchFieldException {
        ArrayList<ArrayList<Entry<Double, Integer>>> res = new ArrayList<ArrayList<Entry<Double, Integer>>>();
        ArrayList<Entry<Double, Integer>> resBin;
        
        ArrayList<ArrayList<Double>> resAG, resRF;
        ArrayList<ArrayList<Double>> resIncertAG, resIncertRF;
        
        //Separamos las incertiumbres en acústicas y no acústicas
        ArrayList<AsuntoIncert> incertAcus = new ArrayList<AsuntoIncert>();
        ArrayList<AsuntoIncert> incertNoAcus = new ArrayList<AsuntoIncert>();
        
        int nIncert = incertidumbres != null ? incertidumbres.size() : 0;
        
        for (int i = 0; i < nIncert; i++) {
            if (TipoIncert.getTipoIntecertPorIdTipoIncert(incertidumbres.get(i).getIdTipoIncert()).getEsAcustica())
                incertAcus.add(incertidumbres.get(i));
            else
                incertNoAcus.add(incertidumbres.get(i));
        }        
        
        //Datos AG + RF
        resIncertAG = new ArrayList<ArrayList<Double>>();
        resAG = getResultadosOCT_IEC3_AG_RF(tipoTabla, idAsunto, idSite, 0, valBinMin, valBinMax, incertAcus, incertNoAcus, resIncertAG);
        //Datos RF
        resIncertRF = new ArrayList<ArrayList<Double>>();
        resRF = getResultadosOCT_IEC3_AG_RF(tipoTabla, idAsunto, idSite, 1, valBinMin, valBinMax, incertAcus, incertNoAcus, resIncertRF);

        ArrayList<ArrayList<Double>> incertTercios = new ArrayList<ArrayList<Double>>();
        ArrayList<ArrayList<Entry<Double, Integer>>> nivelesTercios = getResultadosOCTCorregidos_IEC3(tipoTabla, idAsunto, idSite, valBinMin, valBinMax, incertidumbres, incertTercios);
        
        //Corregimos por RF
        Entry<Double, Integer> nivelAparenteBanda, nivelCorregidoBanda;
        ArrayList<Entry<Double, Integer>> nivelAparente;
        ArrayList<Double> nivelAparenteNiveles, nivelCorregidoNiveles;
        Double nivelBinAG, nivelBinRF, nivelCorregido;
		Integer tipoNivelCorregido;
        LinkedHashMap<Double, Integer> mapNivel;
        Iterator it;

		if (resIncert == null)
            resIncert = new ArrayList<Double>();
        
        Double distMicro = getDistanciaMicro(idAsunto);
        
        int nFrecs = nivelesTercios != null && nivelesTercios.size() > 0 && nivelesTercios.get(0) != null ? nivelesTercios.get(0).size() : 0;
        
        for (int k = valBinMin; k <= valBinMax; k++) {
			resBin = new ArrayList<Entry<Double, Integer>>();
	    
            nivelAparente = new ArrayList<Entry<Double, Integer>>();
            nivelAparenteNiveles = new ArrayList<Double>();
            nivelCorregidoNiveles = new ArrayList<Double>();
            
			tipoNivelCorregido = DatosRA2.CORREGIDO_RF_OK;
	    
            for (int i = 0; i < nFrecs; i++) {
                nivelCorregidoBanda = nivelesTercios.get(k - valBinMin).get(i);

                nivelCorregidoNiveles.add(nivelCorregidoBanda.getKey());

				//Nos quedamos con el mayor problema en la corrección por RF
				if (	(tipoNivelCorregido.equals(DatosRA2.CORREGIDO_RF_OK) && !nivelCorregidoBanda.getValue().equals(DatosRA2.CORREGIDO_RF_OK))	||
						(tipoNivelCorregido.equals(DatosRA2.CORREGIDO_RF_AVISO_IEC3) && nivelCorregidoBanda.getValue().equals(DatosRA2.CORREGIDO_RF_ERROR)))
					tipoNivelCorregido = nivelCorregidoBanda.getValue();

				nivelAparenteBanda = getNivelAparente(nivelCorregidoBanda, distMicro); //L_WA,i,k
				nivelAparente.add(nivelAparenteBanda);
				nivelAparenteNiveles.add(nivelAparenteBanda.getKey());
			}
            
            nivelBinAG = getSumaEnergetica(resAG.get(k - valBinMin));
            nivelBinRF = getSumaEnergetica(resRF.get(k - valBinMin));
			nivelCorregido = getSumaEnergetica(nivelCorregidoNiveles);
	    
            mapNivel = new LinkedHashMap<Double, Integer>();
			mapNivel.put(nivelBinAG, null);
            it = mapNivel.entrySet().iterator();
			while (it.hasNext())
				resBin.add((Entry<Double, Integer>) it.next()); 
	    
            mapNivel = new LinkedHashMap<Double, Integer>();
			mapNivel.put(nivelBinRF, null);
            it = mapNivel.entrySet().iterator();
			while (it.hasNext())
				resBin.add((Entry<Double, Integer>) it.next()); 
	    
			//Marcamos la corrección por RF según IEC 3
            mapNivel = new LinkedHashMap<Double, Integer>();
			mapNivel.put(nivelCorregido, tipoNivelCorregido);
            it = mapNivel.entrySet().iterator();
			while (it.hasNext())
				resBin.add((Entry<Double, Integer>) it.next()); 
	    
			//Marcamos la corrección por RF  según IEC 2.1
            mapNivel = new LinkedHashMap<Double, Integer>();
            mapNivel.put(getSumaEnergetica(nivelAparenteNiveles), getCorreccionRF(nivelBinAG, nivelBinRF).getValue()); //L_WA,k
            it = mapNivel.entrySet().iterator();
			while (it.hasNext())
				resBin.add((Entry<Double, Integer>) it.next()); 
	    
            resIncert.add(getIncertidumbreSumarTercios(nivelAparenteNiveles, incertTercios.get(k - valBinMin))); //u_L_WA, k
			res.add(resBin);
        }
        
        return res;
    }
    
    
    /*
    //Función que obtiene los resultados de tercios de octava para la norma IEC 3.0
    public static ArrayList<Object[]> getResultadosOCT_IEC3(Integer idAsunto, Integer idSite, Integer valBinMin, Integer valBinMax, ArrayList<AsuntoIncert> incertidumbres) throws SQLException, NoSuchFieldException {
        ArrayList<Object[]> res = new ArrayList<Object[]>();

        int nDatosBin, nFrecs = 0;
        Double nivelesNormalizados[];
        ArrayList<Object[]> resOCT;
        ArrayList<ArrayList<Double>> resAG, resRF;
        ArrayList<Double> resAGBin, resRFBin;
        ArrayList<ArrayList<Double>> resIncertAG, resIncertRF;
        ArrayList<Double> resIncertAGBin, resIncertRFBin;
        
        Double velMedia, nivelMedio, covarianza;
        Double incertAVel, incertBVel;
        Double incertANivelBanda, incertBNivelBanda, incertBMedidasNivelBanda;
        ArrayList<Double> nivelesMedidos, incertBMedidasVel, velocidades, nivelesBanda, velocidadesMedias, nivelesMediosBin, covarianzasBin;
        ArrayList<ArrayList<Double>> nivelesMedios, covarianzas;
        ArrayList<Double[]> nivelesNormalizadosBin;
        ArrayList<Integer> nDatos = new ArrayList<Integer>();
        
        ResultadoIncert resultadoIncert;
        //Se sigue la nomenclatura IEC 3.0
        //i -> Banda
        //j -> Medida
        //k -> Bin
        
        //Datos de Aero + RF
        //Inicializamos estructuras
        velocidadesMedias = new ArrayList<Double>();
        nivelesMedios = new ArrayList<ArrayList<Double>>();
        covarianzas = new ArrayList<ArrayList<Double>>();
        
        ArrayList<ResultadoIncert> resIncertVelAG = new ArrayList<ResultadoIncert>();
        ArrayList<ArrayList<ResultadoIncert>> resIncertNivelAG = new ArrayList<ArrayList<ResultadoIncert>>();
        ArrayList<ResultadoIncert> resIncertVelRF = new ArrayList<ResultadoIncert>();
        ArrayList<ArrayList<ResultadoIncert>> resIncertNivelRF = new ArrayList<ArrayList<ResultadoIncert>>();
        
        ArrayList<ResultadoIncert> resIncertNivelAGBin;
        ArrayList<ResultadoIncert> resIncertNivelRFBin;
        
        for (int k = valBinMin; k <= valBinMax; k++) { //Por cada bin
            resOCT = getResultadosOCTVelocidad(idAsunto, idSite, 0, k, k);
            
            nDatosBin = resOCT != null ? resOCT.size() : 0;
            nFrecs = resOCT != null && resOCT.get(0) != null ? resOCT.get(0).length - 3: 0; //En i = 0 está el número de Bin; en i = 1 la fechaHora; en i = 2 esá la velocidad
            
            if (nFrecs > 0 && nDatosBin > 0) {
                velocidades = new ArrayList<Double>();
                nivelesNormalizadosBin = new ArrayList<Double[]>();
                incertBMedidasVel = new ArrayList<Double>();
                nivelesMediosBin = new ArrayList<Double>();
                resIncertNivelAGBin = new ArrayList<ResultadoIncert>();
                covarianzasBin = new ArrayList<Double>();
                nDatos.add(nDatosBin);

                for (int j = 0; j < nDatosBin; j++) {
                    nivelesMedidos = new ArrayList<Double>();

                    for (int i = 0; i < nFrecs; i++) {
                        nivelesMedidos.add(((BigDecimal) resOCT.get(j)[i + 3]).doubleValue()); //En i = 0 está el número de Bin; en i = 1 la fechaHora; en i = 2 esá la velocidad
                    }

                    nivelesNormalizados = getNivelesNormalizadosEspectro(nivelesMedidos, getNivelPromedioEnergetico(Auxiliares.arrayObjToDouble(nivelesMedidos.toArray()))); //L_Aeq,n,i,j
                    nivelesNormalizadosBin.add(nivelesNormalizados); //L_Aeq,n,i,j

                    velocidades.add(((BigDecimal) resOCT.get(j)[2]).doubleValue()); //V_j,k
                    incertBMedidasVel.add(getIncertidumbreTipoBMedidas(incertidumbres, false, (Long) resOCT.get(j)[1])); //u_V_j
                }
                
                //VELOCIDADES
                velMedia = getPromedioAritmetico(velocidades); //V_prom k
                incertAVel = getIncertidumbreTipoA(velocidades, velMedia); //s_V k
                incertBVel = getIncertidumbreTipoBPromedio(incertBMedidasVel); //u_V,k

                //Almacenamos el resultado de las incertidumbres
                resultadoIncert = new ResultadoIncert(k, null, incertAVel, incertBVel, ResultadoIncert.TIPO_VELOCIDAD);
                resIncertVelAG.add(resultadoIncert);
                
                //NIVELES
                for (int i = 0; i < nFrecs; i++) {
                    nivelesBanda = new ArrayList<Double>();
                    for (int j = 0; j < nDatosBin; j++) {
                        nivelesBanda.add(nivelesNormalizadosBin.get(j)[i]);
                    }
                    
                    nivelMedio = getPromedioEnergetico(nivelesBanda); //L_prom i,k
                    incertANivelBanda = getIncertidumbreTipoA(nivelesBanda, nivelMedio); //s_L i,k
                    
                    //Cogemos la fechaHora del 1º porque debería ser igual para todas las medidas
                    incertBMedidasNivelBanda = getIncertidumbreTipoBMedidas(incertidumbres, true, (Long) resOCT.get(0)[1]); //u_L i,j
                    //incertBNivelBanda = getIncertidumbreTipoBPromedio(incertBMedidasNivelBanda); //u_L i,k
                    incertBNivelBanda = incertBMedidasNivelBanda; //u_L i,k
                    
                    //Almacenamos el resultado de las incertidumbres
                    resultadoIncert = new ResultadoIncert(k, i, incertANivelBanda, incertBNivelBanda, ResultadoIncert.TIPO_SPL);
                    resIncertNivelAGBin.add(resultadoIncert);
                    
                    nivelesMediosBin.add(nivelMedio);
                    
                    covarianza = getCovarianza(nivelesBanda, nivelMedio, velocidades, velMedia); //cov_LV,i,k
                    covarianzasBin.add(covarianza);
                }
                
                //Almacenamos los valores medios
                velocidadesMedias.add(velMedia);
                nivelesMedios.add(nivelesMediosBin);
                covarianzas.add(covarianzasBin);
                resIncertNivelAG.add(resIncertNivelAGBin);
            }
        }
        
        nFrecs = nivelesMedios != null ? nivelesMedios.size() : 0;
        
        Integer[] posPromedioInfSup;
        Double velPromedioInf, velPromedioSup;
        Double incertVelocidadCombInf, incertVelocidadCombSup;
        Double nivelPromedioInf, nivelPromedioSup;
        Double incertNivelCombInf, incertNivelCombSup;
        Double covarianzaInf, covarianzaSup;
        Integer nDatosInf, nDatosSup;
        
        resAG = new ArrayList<ArrayList<Double>>();
        resIncertAG = new ArrayList<ArrayList<Double>>();
        
        for (int k = valBinMin; k <= valBinMax; k++) { //Por cada bin
            resAGBin = null;
            resIncertAGBin = null;
            
            posPromedioInfSup = getPosPromedioInfSup(k * 1.0, velocidadesMedias);
            if (posPromedioInfSup != null) {
                resAGBin = new ArrayList<Double>();
                resIncertAGBin = new ArrayList<Double>();
                
                velPromedioInf = velocidadesMedias.get(posPromedioInfSup[0]);
                velPromedioSup = velocidadesMedias.get(posPromedioInfSup[1]);
                
                incertVelocidadCombInf = resIncertVelAG.get(posPromedioInfSup[0]).getIncertCombinada();
                incertVelocidadCombSup = resIncertVelAG.get(posPromedioInfSup[1]).getIncertCombinada();
                
                nDatosInf = nDatos.get(posPromedioInfSup[0]);
                nDatosSup = nDatos.get(posPromedioInfSup[1]);
                
                for (int i = 0; i < nFrecs; i++) {
                    nivelPromedioInf = nivelesMedios.get(posPromedioInfSup[0]).get(i);
                    nivelPromedioSup = nivelesMedios.get(posPromedioInfSup[1]).get(i);
                    
                    incertNivelCombInf = resIncertNivelAG.get(posPromedioInfSup[0]).get(i).getIncertCombinada();
                    incertNivelCombSup = resIncertNivelAG.get(posPromedioInfSup[1]).get(i).getIncertCombinada();
                    
                    covarianzaInf = covarianzas.get(posPromedioInfSup[0]).get(i);
                    covarianzaSup = covarianzas.get(posPromedioInfSup[1]).get(i);
                    
                    resAGBin.add(getNivelEstimadoBin(k * 1.0, velPromedioInf, velPromedioSup, nivelPromedioInf, nivelPromedioSup)); //L_v(k,i)

                    resIncertAGBin.add(getIncertidumbreNivelCalculado(k * 1.0, velPromedioInf, velPromedioSup, incertVelocidadCombInf, incertVelocidadCombSup, incertNivelCombInf, incertNivelCombSup, covarianzaInf, nDatosInf, covarianzaSup, nDatosSup));
                }
            }
            
            resAG.add(resAGBin);
            resIncertAG.add(resIncertAGBin);
        }
        
        //Datos de RF
        //Inicializamos estructuras
        velocidadesMedias = new ArrayList<Double>();
        nivelesMedios = new ArrayList<ArrayList<Double>>();
        covarianzas = new ArrayList<ArrayList<Double>>();
        
        for (int k = valBinMin; k <= valBinMax; k++) { //Por cada bin
            resOCT = getResultadosOCTVelocidad(idAsunto, idSite, 1, k, k);
            
            nDatosBin = resOCT != null ? resOCT.size() : 0;
            nFrecs = resOCT != null && resOCT.get(0) != null ? resOCT.get(0).length - 3: 0; //En i = 0 está el número de Bin; en i = 1 la fechaHora; en i = 2 esá la velocidad
            
            if (nFrecs > 0 && nDatosBin > 0) {
                velocidades = new ArrayList<Double>();
                nivelesNormalizadosBin = new ArrayList<Double[]>();
                incertBMedidasVel = new ArrayList<Double>();
                nivelesMediosBin = new ArrayList<Double>();
                resIncertNivelRFBin = new ArrayList<ResultadoIncert>();
                covarianzasBin = new ArrayList<Double>();
                nDatos.add(nDatosBin);

                for (int j = 0; j < nDatosBin; j++) {
                    nivelesMedidos = new ArrayList<Double>();

                    for (int i = 0; i < nFrecs; i++) {
                        nivelesMedidos.add(((BigDecimal) resOCT.get(j)[i + 3]).doubleValue()); //En i = 0 está el número de Bin; en i = 1 la fechaHora; en i = 2 esá la velocidad
                    }

                    nivelesNormalizados = getNivelesNormalizadosEspectro(nivelesMedidos, getNivelPromedioEnergetico(Auxiliares.arrayObjToDouble(nivelesMedidos.toArray()))); //L_Aeq,n,i,j
                    nivelesNormalizadosBin.add(nivelesNormalizados); //L_Aeq,n,i,j

                    velocidades.add(((BigDecimal) resOCT.get(j)[2]).doubleValue()); //V_j,k
                    incertBMedidasVel.add(getIncertidumbreTipoBMedidas(incertidumbres, false, (Long) resOCT.get(j)[1])); //u_V_j
                }
                
                //VELOCIDADES
                velMedia = getPromedioAritmetico(velocidades); //V_prom k
                incertAVel = getIncertidumbreTipoA(velocidades, velMedia); //s_V k
                incertBVel = getIncertidumbreTipoBPromedio(incertBMedidasVel); //u_V,k

                //Almacenamos el resultado de las incertidumbres
                resultadoIncert = new ResultadoIncert(k, null, incertAVel, incertBVel, ResultadoIncert.TIPO_VELOCIDAD);
                resIncertVelRF.add(resultadoIncert);
                
                //NIVELES
                for (int i = 0; i < nFrecs; i++) {
                    nivelesBanda = new ArrayList<Double>();
                    for (int j = 0; j < nDatosBin; j++) {
                        nivelesBanda.add(nivelesNormalizadosBin.get(j)[i]);
                    }
                    
                    nivelMedio = getPromedioEnergetico(nivelesBanda); //L_prom i,k
                    incertANivelBanda = getIncertidumbreTipoA(nivelesBanda, nivelMedio); //s_L i,k
                    
                    //Cogemos la fechaHora del 1º porque debería ser igual para todas las medidas
                    incertBMedidasNivelBanda = getIncertidumbreTipoBMedidas(incertidumbres, true, (Long) resOCT.get(0)[1]); //u_L i,j
                    //incertBNivelBanda = getIncertidumbreTipoBPromedio(incertBMedidasNivelBanda); //u_L i,k
                    incertBNivelBanda = incertBMedidasNivelBanda; //u_L i,k
                    
                    //Almacenamos el resultado de las incertidumbres
                    resultadoIncert = new ResultadoIncert(k, i, incertANivelBanda, incertBNivelBanda, ResultadoIncert.TIPO_SPL);
                    resIncertNivelRFBin.add(resultadoIncert);
                    
                    nivelesMediosBin.add(nivelMedio);
                    
                    covarianza = getCovarianza(nivelesBanda, nivelMedio, velocidades, velMedia); //cov_LV,i,k
                    covarianzasBin.add(covarianza);
                }
                
                //Almacenamos los valores medios
                velocidadesMedias.add(velMedia);
                nivelesMedios.add(nivelesMediosBin);
                covarianzas.add(covarianzasBin);
                resIncertNivelRF.add(resIncertNivelRFBin);
            }
        }
        
        nFrecs = nivelesMedios != null ? nivelesMedios.size() : 0;
        
        resRF = new ArrayList<ArrayList<Double>>();
        resIncertRF = new ArrayList<ArrayList<Double>>();
        
        for (int k = valBinMin; k <= valBinMax; k++) { //Por cada bin
            resRFBin = null;
            resIncertRFBin = null;
            
            posPromedioInfSup = getPosPromedioInfSup(k * 1.0, velocidadesMedias);
            if (posPromedioInfSup != null) {
                resRFBin = new ArrayList<Double>();
                resIncertRFBin = new ArrayList<Double>();
                
                velPromedioInf = velocidadesMedias.get(posPromedioInfSup[0]);
                velPromedioSup = velocidadesMedias.get(posPromedioInfSup[1]);
                
                incertVelocidadCombInf = resIncertVelRF.get(posPromedioInfSup[0]).getIncertCombinada();
                incertVelocidadCombSup = resIncertVelRF.get(posPromedioInfSup[1]).getIncertCombinada();
                
                nDatosInf = nDatos.get(posPromedioInfSup[0]);
                nDatosSup = nDatos.get(posPromedioInfSup[1]);
                
                for (int i = 0; i < nFrecs; i++) {
                    nivelPromedioInf = nivelesMedios.get(posPromedioInfSup[0]).get(i);
                    nivelPromedioSup = nivelesMedios.get(posPromedioInfSup[1]).get(i);
                    
                    incertNivelCombInf = resIncertNivelRF.get(posPromedioInfSup[0]).get(i).getIncertCombinada();
                    incertNivelCombSup = resIncertNivelRF.get(posPromedioInfSup[1]).get(i).getIncertCombinada();
                    
                    covarianzaInf = covarianzas.get(posPromedioInfSup[0]).get(i);
                    covarianzaSup = covarianzas.get(posPromedioInfSup[1]).get(i);
                    
                    resRFBin.add(getNivelEstimadoBin(k * 1.0, velPromedioInf, velPromedioSup, nivelPromedioInf, nivelPromedioSup)); //L_v(k,i)

                    resIncertRFBin.add(getIncertidumbreNivelCalculado(k * 1.0, velPromedioInf, velPromedioSup, incertVelocidadCombInf, incertVelocidadCombSup, incertNivelCombInf, incertNivelCombSup, covarianzaInf, nDatosInf, covarianzaSup, nDatosSup));
                }
            }
            
            resRF.add(resRFBin);
            resIncertRF.add(resIncertRFBin);
        }
        
        //Corregimos por RF
        Double valorTotal, valorRF;
        Double incertCalcTotal, incertCalcRF;
        Entry<Double, Integer> splCorregido;
        Entry<Double, Integer> nivelAparenteBanda;
        ArrayList<Entry<Double, Integer>> nivelAparente, resNivelesAparentes;
        ArrayList<Double> nivelesTercios, incertTercios;
        Double nivelBinAG, nivelBinRF;
        HashMap<Double, Integer> nivelAparenteBin;
        Iterator it;
        
        Double distMicro = getDistanciaMicro(idAsunto);
        
        resNivelesAparentes = new ArrayList<Entry<Double, Integer>>();
        for (int k = valBinMin; k <= valBinMax; k++) {
            nivelAparente = new ArrayList<Entry<Double, Integer>>();
            nivelesTercios = new ArrayList<Double>();
            incertTercios = new ArrayList<Double>();
            
            nivelAparenteBin = new HashMap<Double, Integer>();
            
            for (int i = 0; i < nFrecs; i++) {
                valorTotal = resAG.get(k - valBinMin).get(i);
                valorRF = resRF.get(k - valBinMin).get(i);
                
                incertCalcTotal = resIncertAG.get(k - valBinMin).get(i);
                incertCalcRF = resIncertRF.get(k - valBinMin).get(i);
                
                splCorregido = getCorrecionRF_IEC3(valorTotal, valorRF); //L_V,c,i,k
                
                nivelAparenteBanda = getNivelAparente(splCorregido, distMicro); //L_WA,i,k
                nivelAparente.add(nivelAparenteBanda);
                
                if (nivelAparenteBanda.getValue() != CORREGIDO_RF_ERROR) {
                    nivelesTercios.add(nivelAparenteBanda.getKey());
                    incertTercios.add(getIncertidumbreCorreccionRF(valorTotal, valorRF, incertCalcTotal, incertCalcRF)); //u_c,i,k
                }
            }
            
            nivelBinAG = getSumaEnergetica(resAG.get(k - valBinMin));
            nivelBinRF = getSumaEnergetica(resRF.get(k - valBinMin));
            nivelAparenteBin.put(getSumaEnergetica(nivelesTercios), corrigeRF(nivelBinAG, nivelBinRF).getValue()); //L_WA,k
            
            getIncertidumbreSumarTercios(nivelesTercios, incertTercios);
            
            it = nivelAparenteBin.entrySet().iterator();
            
            resNivelesAparentes.add((Entry<Double, Integer>) it.next()); 
        }
        
        return res;
    }
    */
    /*
    public static ArrayList<Object[]> getResultadosSPL_IEC3(Integer idAsunto, Integer idSite, Integer valBinMin, Integer valBinMax, ArrayList<AsuntoIncert> incertidumbres) throws SQLException, NoSuchFieldException {
        ArrayList<Object[]> res = new ArrayList<Object[]>();

        //Corregimos por RF
        Double valorTotal, valorRF;
        Double incertCalcTotal, incertCalcRF;
        Entry<Double, Integer> splCorregido;
        Entry<Double, Integer> nivelAparenteBanda;
        ArrayList<Entry<Double, Integer>> nivelAparente, resNivelesAparentes;
        ArrayList<Double> nivelesTercios, incertTercios;
        Double nivelBinAG, nivelBinRF;
        HashMap<Double, Integer> nivelAparenteBin;
        Iterator it;
        
        Double distMicro = getDistanciaMicro(idAsunto);
        
        resNivelesAparentes = new ArrayList<Entry<Double, Integer>>();
        for (int k = valBinMin; k <= valBinMax; k++) {
            nivelAparente = new ArrayList<Entry<Double, Integer>>();
            nivelesTercios = new ArrayList<Double>();
            incertTercios = new ArrayList<Double>();
            
            nivelAparenteBin = new HashMap<Double, Integer>();
            
            for (int i = 0; i < nFrecs; i++) {
                valorTotal = resAG.get(k - valBinMin).get(i);
                valorRF = resRF.get(k - valBinMin).get(i);
                
                incertCalcTotal = resIncertAG.get(k - valBinMin).get(i);
                incertCalcRF = resIncertRF.get(k - valBinMin).get(i);
                
                splCorregido = getCorrecionRF_IEC3(valorTotal, valorRF); //L_V,c,i,k
                
                nivelAparenteBanda = getNivelAparente(splCorregido, distMicro); //L_WA,i,k
                nivelAparente.add(nivelAparenteBanda);
                
                if (nivelAparenteBanda.getValue() != CORREGIDO_RF_ERROR) {
                    nivelesTercios.add(nivelAparenteBanda.getKey());
                    incertTercios.add(getIncertidumbreCorreccionRF(valorTotal, valorRF, incertCalcTotal, incertCalcRF)); //u_c,i,k
                }
            }
            
            nivelBinAG = getSumaEnergetica(resAG.get(k - valBinMin));
            nivelBinRF = getSumaEnergetica(resRF.get(k - valBinMin));
            nivelAparenteBin.put(getSumaEnergetica(nivelesTercios), corrigeRF(nivelBinAG, nivelBinRF).getValue()); //L_WA,k
            
            getIncertidumbreSumarTercios(nivelesTercios, incertTercios);
            
            it = nivelAparenteBin.entrySet().iterator();
            
            resNivelesAparentes.add((Entry<Double, Integer>) it.next()); 
        }
        
        return res;
    }
     * */

    //Función que devuelve los rangos en que la curva de potencia mantiene una pendiente positiva
    public static boolean esRangoPendientePositiva(CurvaRA curva1, CurvaRA curva2, Double tolerancia) {
		boolean res = false;
		
		if (curva1.getIncert() != null && curva2.getIncert() != null) {
			res = (curva2.getPot() - curva2.getIncert()) - (curva1.getPot() + curva1.getIncert()) > 0;
		} else {
			if (tolerancia != null) {
				res = (curva2.getPot() - tolerancia) - (curva1.getPot() + tolerancia) > 0;
			} else 
				res = false;
		}

        return res;
    }

    //Función para obtener la velocidad a <altura> metros de altura
    public static Double getVelocidadAltura(Double velocidadBuje, Double hB, Double altura, Double z0ref) {
		Double res = null;

		if (velocidadBuje != null && hB != null && altura != null && z0ref != null)
			res = velocidadBuje * Math.log(altura / z0ref) / Math.log(hB / z0ref);

		return res;
    }

    //Función que devuelve la distancia a la que se consigue el <nivelBuscado> dado un <nivelConocido> a una <distConocida>
    public static Double getDistanciaNivel(Double distConocida, Double nivelConocido, Double nivelBuscado) {
		Double res = null;

		if (distConocida != null && nivelConocido != null && nivelBuscado != null)
			res = distConocida * Math.pow(10.0, (nivelConocido - nivelBuscado) / 20.0);

		return res;
	}

    public static Comparator<Double[]> distNivel = new Comparator<Double[]>() {
		public int compare(Double[] dato1, Double[] dato2) {
			return (dato1[0]).compareTo(dato2[0]);
		}
    };
    
    //Función para obtener el dataset para dibujar un mapa de ruido
    public static YIntervalSeriesCollection getDatasetMapaRuido_IEC3(Integer idAsunto, ArrayList<ArrayList<Entry<Double, Integer>>> datosCorregidosSPL, Integer valBinMin, Integer valBinMax, Integer pasoNivel, Integer nivelInf, ArrayList<Double> distNivelInf) throws SQLException, NoSuchFieldException {
        YIntervalSeriesCollection dataset = new YIntervalSeriesCollection();
        YIntervalSeries serie = null;
		HashMap<Integer, ArrayList<Double[]>> nivelesPos = new HashMap<Integer, ArrayList<Double[]>>();
		ArrayList<Double[]> posicionesNivel;
		ArrayList<Double> distNivelInfAux = new ArrayList<Double>();

		Entry<Double, Integer> nivelAparente;
		Double nivel, dist = null;
		Integer nivelSup;

		AerogeneradorRA aero = AerogeneradorRA.getAeroPorId(AsuntoRA.getAsuntoPorId(idAsunto).getIdAero());
		Double hB = aero.getHB();

		//Obtenemos el máximo nivel aparente
		for (int i = 0; i < valBinMax - valBinMin + 1; i++) {
			nivelAparente = datosCorregidosSPL.get(i).get(3);

			if (nivelAparente.getValue().equals(DatosRA2.CORREGIDO_RF_OK)) {
				nivel = nivelAparente.getKey();
				nivelSup = nivel.intValue() - (nivel.intValue() % pasoNivel) + pasoNivel;

				while (nivelSup >= nivelInf) {
					if (nivelesPos.containsKey(nivelSup))
						posicionesNivel = nivelesPos.get(nivelSup);
					else
						posicionesNivel = new ArrayList<Double[]>();

					dist = getDistanciaNivel(hB, nivel, nivelSup * 1.0);

					posicionesNivel.add(new Double[]{dist, 1.0 * (i + valBinMin)});
					nivelesPos.put(nivelSup, posicionesNivel);

					nivelSup -= pasoNivel;
				}
				//Acabamos de añadir el último nivel, guardamos su ditancia;
				distNivelInfAux.add(dist);
			}
		} 

		Integer nDatosSerie;
		Double[][] posicionesNivelArray;
		Integer[] clavesArray;

		clavesArray = Auxiliares.arrayObjToInteger(nivelesPos.keySet().toArray());
		Arrays.sort(clavesArray);

		Double[] distNivelArray = Auxiliares.arrayObjToDouble(distNivelInfAux.toArray()).clone();
		Arrays.sort(distNivelArray);
		int nDistNivelArray = distNivelArray.length;

		for (int i = 0; i < nDistNivelArray; i++) {
			distNivelInf.add(distNivelArray[i]);
		}

		int nClaves = clavesArray.length;

		for (int i = 0; i < nClaves; i++) {
			serie = new YIntervalSeries((clavesArray[i] - pasoNivel) + " - " + clavesArray[i]);

			posicionesNivel = nivelesPos.get(clavesArray[i]);
			posicionesNivelArray = Auxiliares.arrayObjToDoubleDouble(posicionesNivel.toArray());
			Arrays.sort(posicionesNivelArray, distNivel);
			nDatosSerie = posicionesNivelArray.length;

			for (int j = 0; j < nDatosSerie; j++) {
				//serie.add(posicionesNivelArray[i][0], posicionesNivelArray[i][1], entry.getKey() - pasoNivel, entry.getKey());
				serie.add(posicionesNivelArray[j][0], posicionesNivelArray[j][1], valBinMin * 1.0, posicionesNivelArray[j][1]);
			}
		   
			if (!serie.isEmpty()) {
				serie.add(distNivelInf.get(distNivelInf.size() - 1), posicionesNivelArray[nDatosSerie - 1][1], valBinMin * 1.0, posicionesNivelArray[nDatosSerie - 1][1]);
				dataset.addSeries(serie);
			}
		}

        if (dataset.getSeriesCount() == 0)
            dataset = null;

        return dataset;
    }

	private static Double getCoefSensibilidadCP(CurvaRA curvaAnt, CurvaRA curvaAct) {
		Double res = null;

		if (curvaAnt != null && curvaAct != null && curvaAct.getPot() - curvaAnt.getPot() != 0.0) {
			res = Math.abs((curvaAct.getVel() - curvaAnt.getVel()) / (curvaAct.getPot() - curvaAnt.getPot()));
		}

		return res;
	}

	private static ArrayList<CurvaRA> getRangosPermitidos(ArrayList<CurvaRA> curvas) {
		ArrayList<CurvaRA> res = null;
		int nCurvas = curvas != null ? curvas.size() : 0;

		if (nCurvas != 0) {
			res = new ArrayList<CurvaRA>();

			for (int i = 0; i < nCurvas - 1; i++) {
				if (esRangoPendientePositiva(curvas.get(i), curvas.get(i + 1), null))
					res.add(curvas.get(i));
			}
		}

		return res;
	}

	public static Double calculaIncertidumbre(Component c, Integer idAsunto, Double densidad, Integer idIncert) throws SQLException, NoSuchFieldException {
		Double res = null, coef = null;

		if (idIncert.equals(TipoIncert.ID_TIPO_INCERT_VEL_DERIVADA)) {
			Double valorRecogido = null;
			RecogidaDatos rd;

			do {
				rd = new RecogidaDatos("Introduzca la incertidumbre de calibración de la CPA", "Incertidumbre:");
				if (JOptionPane.showConfirmDialog(c, rd, "Recogida de datos", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
					try {
						valorRecogido = Double.parseDouble(rd.getCampoRecoger());
					} catch (NumberFormatException ex) {
						MensajeApp.muestraError(rd, null, "Debe introducir un número");
					}
				} else {
					MensajeApp.muestraInfo(c, "Proceso cancelado por el usuario.");
					return res;
				}
			} while (valorRecogido == null);

			if (valorRecogido != null) {
				Double coefAux = null;
				res = null;
				
				ArrayList<CurvaRA> curvas = getRangosPermitidos(CurvaRA.getCurvas(idAsunto, densidad, null, null, null, null, null, Boolean.TRUE));
				int nCurvas = curvas != null ? curvas.size() : 0;

				for (int i = 1; i < nCurvas; i++) {
					coefAux = getCoefSensibilidadCP(curvas.get(i - 1), curvas.get(i));
					
					coef = coef == null ? coefAux : (coefAux != null ? Math.max(coef, coefAux) : null);
				}

				res = coef * valorRecogido;
			}
		} else if (idIncert.equals(TipoIncert.ID_TIPO_INCERT_CURVA)) {
			Double resAux = null;
			ArrayList<CurvaRA> curvas = getRangosPermitidos(CurvaRA.getCurvas(idAsunto, densidad, null, null, null, null, null, Boolean.TRUE));
			int nCurvas = curvas != null ? curvas.size() : 0;

			for (int i = 1; i < nCurvas; i++) {
				coef = getCoefSensibilidadCP(curvas.get(i - 1), curvas.get(i));

				resAux = coef * curvas.get(i).getIncert();

				res = res == null ? resAux : (resAux != null ? Math.max(res, resAux) : null);
			}
		}

		if (res != null)
			res = TratDecimales.round(res, TratDecimales.DEC_VARIABLE_RA);
		else
			MensajeApp.muestraError(c, null, "No se puede realizar el cálculo automático. No se han establecido incertidumbres para la curva de potencia.<br>Debrá introducir la incertidumbre manualmente.");

		return res;
	}
}