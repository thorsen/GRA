package RA;

import general.InteraccionBD;
import java.sql.SQLException;
import java.util.ArrayList;

public class LineaConfiguracion {

    private Integer idAsunto;
    private Integer idSite;
    private Integer idConfig;
    private Integer idSerie;
    private String equipo;
    private String canal;
    private Double cota;
    private String certificado;
    private Double slope;
    private Double offset;
    private Double slopeP;
    private Double offsetP;
    private Double orientacion;
    
    public static final String TABLA = "LineaConfiguracionRA";
    public static final String CAMPO_ID_ASUNTO = "Asunto";
    public static final String CAMPO_ID_SITE = "Site";
    public static final String CAMPO_ID_CONFIG = "NConfig";
    public static final String CAMPO_ID_SERIE = "Serie";
    public static final String CAMPO_EQUIPO = "Equipo";
    public static final String CAMPO_CANAL = "Canal";
    public static final String CAMPO_COTA = "Cota";
    public static final String CAMPO_CERTIFICADO = "Certificado";
    public static final String CAMPO_SLOPE = "Slope";
    public static final String CAMPO_OFFSET = "Offset";
    public static final String CAMPO_SLOPE_P = "Slopep";
    public static final String CAMPO_OFFSET_P = "Offsetp";
    public static final String CAMPO_ORIENTACION = "Orientacion";
    
    public static final int ID_SERIE_T = 5;
    public static final int ID_SERIE_P = 6;
    public static final double SLOPE_P_DIRECCION = 72.0;

    public LineaConfiguracion(Integer idAsunto, Integer idSite, Integer idConfig, Integer idSerie, String equipo, String canal, Double cota,
            String certificado, Double slope, Double offset, Double slopeP, Double offsetP, Double orientacion) {
        this.idAsunto = idAsunto;
        this.idSite = idSite;
        this.idConfig = idConfig;
        this.idSerie = idSerie;
        this.equipo = equipo;
        this.canal = canal;
        this.cota = cota;
        this.certificado = certificado;
        this.slope = slope;
        this.offset = offset;
        this.slopeP = slopeP;
        this.offsetP = offsetP;
        this.orientacion = orientacion;
    }

    public LineaConfiguracion(Object[] valores, ArrayList<String> campos) throws SQLException, NoSuchFieldException {
        int nValores = valores.length;

        if (campos == null) { //Si se han pedido todos los campos sin indicarlos (SELECT * FROM ...)
            InteraccionBD interBD = new InteraccionBD();

            Object[] camposAux = interBD.getCamposTabla(TABLA);
            int nCamposAux = camposAux.length;

            campos = new ArrayList<String>();

            for (int i = 0; i < nCamposAux; i++) {
                campos.add((String) camposAux[i]);
            }

        }

        for (int i = 0; i < nValores; i++) {
            asignaCampo(campos.get(i), valores[i]);
        }
    }

    public String getCanal() {
        return canal;
    }

    public void setCanal(String canal) {
        this.canal = canal;
    }

    public String getCertificado() {
        return certificado;
    }

    public void setCertificado(String certificado) {
        this.certificado = certificado;
    }

    public Double getCota() {
        return cota;
    }

    public void setCota(Double cota) {
        this.cota = cota;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public Integer getIdAsunto() {
        return idAsunto;
    }

    public void setIdAsunto(Integer idAsunto) {
        this.idAsunto = idAsunto;
    }

    public Integer getIdConfig() {
        return idConfig;
    }

    public void setIdConfig(Integer idConfig) {
        this.idConfig = idConfig;
    }

    public Integer getIdSerie() {
        return idSerie;
    }

    public void setIdSerie(Integer idSerie) {
        this.idSerie = idSerie;
    }

    public Integer getIdSite() {
        return idSite;
    }

    public void setIdSite(Integer idSite) {
        this.idSite = idSite;
    }

    public Double getOffset() {
        return offset;
    }

    public void setOffset(Double offset) {
        this.offset = offset;
    }

    public Double getOffsetP() {
        return offsetP;
    }

    public void setOffsetP(Double offsetP) {
        this.offsetP = offsetP;
    }

    public Double getOrientacion() {
        return orientacion;
    }

    public void setOrientacion(Double orientacion) {
        this.orientacion = orientacion;
    }

    public Double getSlope() {
        return slope;
    }

    public void setSlope(Double slope) {
        this.slope = slope;
    }

    public Double getSlopeP() {
        return slopeP;
    }

    public void setSlopeP(Double slopeP) {
        this.slopeP = slopeP;
    }
    //Función para asignar valores a campos de la clase según el campo de la BD
    private void asignaCampo(String campo, Object valor) throws NoSuchFieldException {
        if (campo.compareTo(CAMPO_ID_ASUNTO) == 0) {
            this.idAsunto = (Integer) valor;
        } else if (campo.compareTo(CAMPO_ID_SITE) == 0) {
            this.idSite = (Integer) valor;
        } else if (campo.compareTo(CAMPO_ID_CONFIG) == 0) {
            this.idConfig = (Integer) valor;
        } else if (campo.compareTo(CAMPO_ID_SERIE) == 0) {
            this.idSerie = (Integer) valor;
        } else if (campo.compareTo(CAMPO_EQUIPO) == 0) {
            this.equipo = (String) valor;
        } else if (campo.compareTo(CAMPO_CANAL) == 0) {
            this.canal = (String) valor;
        } else if (campo.compareTo(CAMPO_COTA) == 0) {
            this.cota = (Double) valor;
        } else if (campo.compareTo(CAMPO_CERTIFICADO) == 0) {
            this.certificado = (String) valor;
        } else if (campo.compareTo(CAMPO_SLOPE) == 0) {
            this.slope = (Double) valor;
        } else if (campo.compareTo(CAMPO_OFFSET) == 0) {
            this.offset = (Double) valor;
        } else if (campo.compareTo(CAMPO_SLOPE_P) == 0) {
            this.slopeP = (Double) valor;
        } else if (campo.compareTo(CAMPO_OFFSET_P) == 0) {
            this.offsetP = (Double) valor;
        } else if (campo.compareTo(CAMPO_ORIENTACION) == 0) {
            this.orientacion = (Double) valor;
        } else {
            throw new NoSuchFieldException("No existe el campo en la clase " + this.getClass().getSimpleName());
        }
    }
    
    private static String getCondicion(Integer idAsunto, Integer idSite, Integer idConfig, Integer idSerie, String equipo, String canal, 
            Double cota, String certificado, Double slope, Double offset, Double slopeP, Double offsetP, Double orientacion, ArrayList<Object[]> paramsPS) {
        String condicion = "";
        
        if (idAsunto != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_ID_ASUNTO, "=", idAsunto);
        }
        if (idSite != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_ID_SITE, "=", idSite);
        }
        if (idConfig != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_ID_CONFIG, "=", idConfig);
        }
        if (idSerie != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_ID_SERIE, "=", idSerie);
        }
        if (equipo != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_EQUIPO, "=", equipo);
        }
        if (canal != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_CANAL, "=", canal);
        }
        if (cota != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_COTA, "=", cota);
        }
        if (certificado != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_CERTIFICADO, "=", certificado);
        }
        if (slope != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_SLOPE, "=", slope);
        }
        if (offset != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_OFFSET, "=", offset);
        }
        if (slopeP != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_SLOPE_P, "=", slopeP);
        }
        if (offsetP != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_OFFSET_P, "=", offsetP);
        }
        if (orientacion != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_ORIENTACION, "=", orientacion);
        }
        
        return condicion;
    }

    //Función para obtener la colección de ConfiguracionesRA.que se ajustan a los limites pasados
    public static ArrayList<LineaConfiguracion> getLineasConf(Integer idAsunto, Integer idSite, Integer idConfig, Integer idSerie,
            String equipo, String canal, Double cota, String certificado, Double slope, Double offset, Double slopeP, Double offsetP, Double orientacion,
            ArrayList<String> campos, String sqlExtra, Boolean distinct) throws SQLException, NoSuchFieldException {
        InteraccionBD interBD = new InteraccionBD();

        ArrayList<LineaConfiguracion> res = null;
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();

        condicion = getCondicion(idAsunto, idSite, idConfig, idSerie, equipo, canal, cota, certificado, slope, offset, slopeP, offsetP, orientacion, paramsPS);
        
        //Por defecto lo devolvemos ordenado por Serie
        if (sqlExtra == null || sqlExtra.trim().length() == 0) {
            String sqlOrderBy = InteraccionBD.anadeCampoOrderBy(null, CAMPO_ID_SERIE);
            sqlExtra = InteraccionBD.anadeSqlExtra(null, sqlOrderBy);
        }

        ArrayList<Object[]> resAux = interBD.getDatosTabla(TABLA, campos, condicion, paramsPS, sqlExtra, distinct);

        if (resAux != null) {
            int n = resAux.size();
            res = new ArrayList<LineaConfiguracion>();

            for (int i = 0; i < n; i++) {
                res.add(new LineaConfiguracion(resAux.get(i), campos));
            }
        }

        return res;
    }

    //Función para obtener la colección de ConfiguracionesRA.que se ajustan a los limites pasados
    public static ArrayList<LineaConfiguracion> getLineasConfPorAsuntoSiteConf(Integer idAsunto, Integer idSite, Integer idConfig) throws SQLException, NoSuchFieldException {
        return (getLineasConf(idAsunto, idSite, idConfig, null, null, null, null, null, null, null, null, null, null, null, null, null));
    }

    //Función para obtener la colección de ConfiguracionesRA.que se ajustan a los limites pasados
    public static LineaConfiguracion getLineaConfPorAsuntoSiteConfSerie(Integer idAsunto, Integer idSite, Integer idConfig, Integer idSerie) throws SQLException, NoSuchFieldException {
        LineaConfiguracion res = null;
        
        ArrayList<LineaConfiguracion> resAux = getLineasConf(idAsunto, idSite, idConfig, idSerie, null, null, null, null, null, null, null, null, null, null, null, null);
        
        if (resAux != null && resAux.size() > 0)
            res = resAux.get(0);
        
        return res;
    }
    
    //Función que devuelve las cotas para cada configuración <fechaIni, cota>
    public static ArrayList<Object[]> getCotasPorAsuntoSiteSerie(Integer idAsunto, Integer idSite, Integer idSerie) throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        ArrayList<Object[]> res = null;
                
        String condicion = "";
        String tabla = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        ArrayList<String> campos = new ArrayList<String>();
        
        //Solo nos interesa recoger las fechas y cotas
        campos.add(ConfiguracionRA2.TABLA + "." + ConfiguracionRA2.CAMPO_FECHA_INI);
        campos.add(CAMPO_COTA);

        //Añadimos la conectividad entre tablas
        condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, TABLA + "." + CAMPO_ID_ASUNTO, InteraccionBD.ASIGNACION_CAMPOS, ConfiguracionRA2.TABLA + "." + ConfiguracionRA2.CAMPO_ID_ASUNTO);
        condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, TABLA + "." + CAMPO_ID_SITE, InteraccionBD.ASIGNACION_CAMPOS, ConfiguracionRA2.TABLA + "." + ConfiguracionRA2.CAMPO_ID_SITE);
        condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, TABLA + "." + CAMPO_ID_CONFIG, InteraccionBD.ASIGNACION_CAMPOS, ConfiguracionRA2.TABLA + "." + ConfiguracionRA2.CAMPO_ID_CONFIG);
        
        if (idAsunto != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, TABLA + "." + CAMPO_ID_ASUNTO, "=", idAsunto);
        }
        if (idSite != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, TABLA + "." + CAMPO_ID_SITE, "=", idSite);
        }
        if (idSerie != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, TABLA + "." + CAMPO_ID_SERIE, "=", idSerie);
        }
                
        tabla = InteraccionBD.anadeTabla(tabla, TABLA);
        tabla = InteraccionBD.anadeTabla(tabla, ConfiguracionRA2.TABLA);

        ArrayList<Object[]> resAux = interBD.getDatosTabla(tabla, campos, condicion, paramsPS, null, null);

        if (resAux != null) {
            long fechaIni;
            
            int n = resAux.size();
            res = new ArrayList<Object[]>();

            for (int i = 0; i < n; i++) {
                if (resAux.get(i)[0] != null)
                    fechaIni = (Long) resAux.get(i)[0];
                else    
                    fechaIni = 0;
                
                res.add(new Object[]{fechaIni, (Double) resAux.get(i)[1]});
            }
        }

        return res;
    }

    //Función para añadir una lineaConf.a la BD
    public static int insertLineaConf(Integer idAsunto, Integer idSite, Integer idConfig, Integer idSerie,
            String equipo, String canal, Double cota, String certificado, Double slope, Double offset, Double slopeP, Double offsetP, Double orientacion, String sqlExtra) throws SQLException {
        InteraccionBD interBD = new InteraccionBD();

        String valores = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        ArrayList<String> campos = new ArrayList<String>();
        int res = 0;

        if (idAsunto != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, idAsunto);
            campos.add(CAMPO_ID_ASUNTO);
        }
        if (idSite != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, idSite);
            campos.add(CAMPO_ID_SITE);
        }
        if (idConfig != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, idConfig);
            campos.add(CAMPO_ID_CONFIG);
        }
        if (idSerie != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, idSerie);
            campos.add(CAMPO_ID_SERIE);
        }
        if (equipo != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, equipo);
            campos.add(CAMPO_EQUIPO);
        }
        if (canal != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, canal);
            campos.add(CAMPO_CANAL);
        }
        if (cota != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, cota);
            campos.add(CAMPO_COTA);
        }
        if (certificado != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, certificado);
            campos.add(CAMPO_CERTIFICADO);
        }
        if (slope != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, slope);
            campos.add(CAMPO_SLOPE);
        }
        if (offset != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, offset);
            campos.add(CAMPO_OFFSET);
        }
        if (slopeP != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, slopeP);
            campos.add(CAMPO_SLOPE_P);
        }
        if (offsetP != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, offsetP);
            campos.add(CAMPO_OFFSET_P);
        }
        if (orientacion != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, orientacion);
            campos.add(CAMPO_ORIENTACION);
        }

        interBD.inicioTransaccion();
        res = interBD.insertDatosTabla(TABLA, campos, valores, paramsPS, sqlExtra);
        interBD.finTransaccion();

        return res;
    }

    public static int insertLineaConf(LineaConfiguracion lineaConf, String sqlExtra) throws SQLException {
        return insertLineaConf(lineaConf.idAsunto, lineaConf.idSite, lineaConf.idConfig, lineaConf.idSerie, lineaConf.equipo, 
                lineaConf.canal, lineaConf.cota, lineaConf.certificado, lineaConf.slope, lineaConf.offset, lineaConf.slopeP, 
                lineaConf.offsetP, lineaConf.orientacion, sqlExtra);
    }
    //Función para añadir una lineaConf.a la BD
    public static int updateLineaConf(Integer idAsunto, Integer idSite, Integer idConfig, Integer idSerie, String equipo, String canal, 
            Double cota, String certificado, Double slope, Double offset, Double slopeP, Double offsetP, Double orientacion,
            Integer idAsuntoVal, Integer idSiteVal, Integer idConfigVal, Integer idSerieVal, String equipoVal, String canalVal, 
            Double cotaVal, String certificadoVal, Double slopeVal, Double offsetVal, Double slopePVal, Double offsetPVal, Double orientacionVal, String sqlExtra) throws SQLException {
        InteraccionBD interBD = new InteraccionBD();

        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        ArrayList<String> campos = new ArrayList<String>();
        int res = 0;

        if (idAsuntoVal != null && !idAsuntoVal.equals(idAsunto)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, idAsuntoVal);
            campos.add(CAMPO_ID_ASUNTO);
        }
        if (idSiteVal != null && !idSiteVal.equals(idSite)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, idSiteVal);
            campos.add(CAMPO_ID_SITE);
        }
        if (idConfigVal != null && !idConfigVal.equals(idConfig)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, idConfigVal);
            campos.add(CAMPO_ID_CONFIG);
        }
        if (idSerieVal != null && !idSerieVal.equals(idSerie)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, idSerieVal);
            campos.add(CAMPO_ID_SERIE);
        }
        if (equipoVal != null && !equipoVal.equals(equipo)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, equipoVal);
            campos.add(CAMPO_EQUIPO);
        }
        if (canalVal != null && !canalVal.equals(canal)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, canalVal);
            campos.add(CAMPO_CANAL);
        }
        if (cotaVal != null && !cotaVal.equals(cota)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, cotaVal);
            campos.add(CAMPO_COTA);
        }
        if (certificadoVal != null && !certificadoVal.equals(certificado)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, certificadoVal);
            campos.add(CAMPO_CERTIFICADO);
        }
        if (slopeVal != null && !slopeVal.equals(slope)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, slopeVal);
            campos.add(CAMPO_SLOPE);
        }
        if (offsetVal != null && !offsetVal.equals(offset)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, offsetVal);
            campos.add(CAMPO_OFFSET);
        }
        if (slopePVal != null && !slopePVal.equals(slopeP)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, slopePVal);
            campos.add(CAMPO_SLOPE_P);
        }
        if (offsetPVal != null && !offsetPVal.equals(offsetP)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, offsetPVal);
            campos.add(CAMPO_OFFSET_P);
        }
        if (orientacionVal != null && !orientacionVal.equals(orientacion)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, orientacionVal);
            campos.add(CAMPO_ORIENTACION);
        }

        //Condiciones de actualización
        condicion = getCondicion(idAsunto, idSite, idConfig, idSerie, equipo, canal, cota, certificado, slope, offset, slopeP, offsetP, orientacion, paramsPS);

        interBD.inicioTransaccion();
        res = interBD.updateDatosTabla(TABLA, campos, condicion, paramsPS, sqlExtra);
        interBD.finTransaccion();

        return res;
    }

    public static int updateLineaConf(LineaConfiguracion lineaConfVieja, LineaConfiguracion lineaConfNueva, String sqlExtra) throws SQLException {
        return updateLineaConf(lineaConfVieja.idAsunto, lineaConfVieja.idSite, lineaConfVieja.idConfig, lineaConfVieja.idSerie, lineaConfVieja.equipo, 
                lineaConfVieja.canal, lineaConfVieja.cota, lineaConfVieja.certificado, lineaConfVieja.slope, lineaConfVieja.offset, lineaConfVieja.slopeP, 
                lineaConfVieja.offsetP, lineaConfVieja.orientacion,
                lineaConfNueva.idAsunto, lineaConfNueva.idSite, lineaConfNueva.idConfig, lineaConfNueva.idSerie, lineaConfNueva.equipo, 
                lineaConfNueva.canal, lineaConfNueva.cota, lineaConfNueva.certificado, lineaConfNueva.slope, lineaConfNueva.offset, lineaConfNueva.slopeP, 
                lineaConfNueva.offsetP, lineaConfNueva.orientacion, sqlExtra);
    }
    //Función para añadir/modificar una ConfiguracionRA a la BD
    public static int insertOrUpdateLineaConf(Integer idAsunto, Integer idSite, Integer idConfig, Integer idSerie, String equipo, String canal, 
            Double cota, String certificado, Double slope, Double offset, Double slopeP, Double offsetP, Double orientacion,
            Integer idAsuntoVal, Integer idSiteVal, Integer idConfigVal, Integer idSerieVal, String equipoVal, String canalVal, 
            Double cotaVal, String certificadoVal, Double slopeVal, Double offsetVal, Double slopePVal, Double offsetPVal, Double orientacionVal, String sqlExtra) throws SQLException {
        int res;

        res = updateLineaConf(idAsunto, idSite, idConfig, idSerie, equipo, canal, cota, certificado, slope, offset, slopeP, offsetP, orientacion,
                idAsuntoVal, idSiteVal, idConfigVal, idSerieVal, equipoVal, canalVal, cotaVal, certificadoVal, slopeVal, offsetVal, slopePVal, offsetPVal, orientacionVal, sqlExtra);

        if (res < 0) {
            res = insertLineaConf(idAsuntoVal, idSiteVal, idConfigVal, idSerieVal, equipoVal, canalVal, cotaVal, certificadoVal, slopeVal, offsetVal, slopePVal, offsetPVal, orientacionVal, sqlExtra);
        }
        return res;
    }

    public static int insertOrUpdateLineaConf(LineaConfiguracion lineaConfVieja, LineaConfiguracion lineaConfNueva, String sqlExtra) throws SQLException {

        return insertOrUpdateLineaConf(lineaConfVieja.idAsunto, lineaConfVieja.idSite, lineaConfVieja.idConfig, lineaConfVieja.idSerie, lineaConfVieja.equipo, 
                lineaConfVieja.canal, lineaConfVieja.cota, lineaConfVieja.certificado, lineaConfVieja.slope, lineaConfVieja.offset, lineaConfVieja.slopeP, 
                lineaConfVieja.offsetP, lineaConfVieja.orientacion,
                lineaConfNueva.idAsunto, lineaConfNueva.idSite, lineaConfNueva.idConfig, lineaConfNueva.idSerie, lineaConfNueva.equipo, 
                lineaConfNueva.canal, lineaConfNueva.cota, lineaConfNueva.certificado, lineaConfNueva.slope, lineaConfNueva.offset, lineaConfNueva.slopeP, 
                lineaConfNueva.offsetP, lineaConfNueva.orientacion, sqlExtra);
    }
    //Función para eliminar ConfiguracionesRA.que se ajustan a los limites pasados
    public static int deleteLineaConf(Integer idAsunto, Integer idSite, Integer idConfig, Integer idSerie, String equipo, String canal, 
            Double cota, String certificado, Double slope, Double offset, Double slopeP, Double offsetP, Double orientacion, String sqlExtra) throws SQLException {
        InteraccionBD interBD = new InteraccionBD();

        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        int res = 0;

        condicion = getCondicion(idAsunto, idSite, idConfig, idSerie, equipo, canal, cota, certificado, slope, offset, slopeP, offsetP, orientacion, paramsPS);

        interBD.inicioTransaccion();
        res = interBD.deleteDatosTabla(TABLA, condicion, paramsPS, sqlExtra);
        interBD.finTransaccion();

        return res;
    }

    public static int deleteLineaConf(LineaConfiguracion lineaConf, String sqlExtra) throws SQLException {
        return deleteLineaConf(lineaConf.idAsunto, lineaConf.idSite, lineaConf.idConfig, lineaConf.idSerie, lineaConf.equipo, 
                lineaConf.canal, lineaConf.cota, lineaConf.certificado, lineaConf.slope, lineaConf.offset, lineaConf.slopeP, 
                lineaConf.offsetP, lineaConf.orientacion, sqlExtra);
    }

    public Object[] toObject() {
        return new Object[]{this.idAsunto, this.idSite, this.idConfig, this.idSerie, this.equipo,  this.canal, this.cota, this.certificado, this.slope,
        this.offset, this.slopeP, this.offsetP, this.orientacion};
    }

    public static Object[] getCamposTabla() throws SQLException {
        InteraccionBD interBD = new InteraccionBD();

        return interBD.getCamposTabla(TABLA);
    }
}