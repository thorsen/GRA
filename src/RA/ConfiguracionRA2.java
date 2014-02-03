package RA;

import general.InteraccionBD;
import java.sql.SQLException;
import java.util.ArrayList;

//////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////EQUIVALENCIAS///////////////////////////////////////////////
///////ConfiguracionRA2//////////////////////////////////////Configuracion////////////////////////
//LineaConfiguracionRA-> getLineasConfRA(params)        |   getLineas(asunto, site, config)
//LineaConfiguracionRA-> getLineasConfRA(params).getCota|   getCota(asunto, site, config, serie)
//Declinacion-> getDeclinaciones(params)                |   getDeclinacion(asunto, site, config)
//SerieRA2->getCodigosPor...                            |   getDescripcion
//Descripcion-> setTabla(suf) + insertDescripcion       |   NuevaDescripcion

public class ConfiguracionRA2 {
    private Integer idAsunto;
    private Integer idSite;
    private Integer idConfig;
    private Long fechaIni;
    
    public static final String BD = InteraccionBD.PREF_BD_RA;
    public static final String TABLA = BD + "ConfiguracionRA";
    public static final String CAMPO_ID_ASUNTO = "Asunto";
    public static final String CAMPO_ID_SITE = "Site";
    public static final String CAMPO_ID_CONFIG = "NConfig";
    public static final String CAMPO_FECHA_INI = "F_inicio";
    
    public ConfiguracionRA2(Integer idAsunto, Integer idSite, Integer idConfig, Long fechaIni) {
        this.idAsunto = idAsunto;
        this.idSite = idSite;
        this.idConfig = idConfig;
        this.fechaIni = fechaIni;
    }

    public ConfiguracionRA2(Object[] valores, ArrayList<String> campos) throws SQLException, NoSuchFieldException {
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
    
    public Long getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(Long fechaIni) {
        this.fechaIni = fechaIni;
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

    public Integer getIdSite() {
        return idSite;
    }

    public void setIdSite(Integer idSite) {
        this.idSite = idSite;
    }
    
    //Función para asignar valores a campos de la clase según el campo de la BD
    private void asignaCampo(String campo, Object valor) throws NoSuchFieldException {
        if (campo.compareTo(CAMPO_ID_ASUNTO) == 0) {
            this.idAsunto = (Integer) valor;
        } else if (campo.compareTo(CAMPO_ID_SITE) == 0) {
            this.idSite = (Integer) valor;
        } else if (campo.compareTo(CAMPO_ID_CONFIG) == 0) {
            this.idConfig = (Integer) valor;
        } else if (campo.compareTo(CAMPO_FECHA_INI) == 0) {
            this.fechaIni = (Long) valor;
        } else {
            //throw new NoSuchFieldException("No existe el campo en la clase " + this.getClass().getSimpleName());
			System.out.println("No existe el campo <" + campo + "> en la clase " + this.getClass().getSimpleName());
        }
    }
    
    private static String getCondicion(Integer idAsunto, Integer idSite, Integer idConfig, Long fechaIni, ArrayList<Object[]> paramsPS) {
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
        if (fechaIni != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_FECHA_INI, "=", fechaIni);
        }
        
        return condicion;
    }

    //Función para obtener la colección de ConfiguracionesRA.que se ajustan a los limites pasados
    public static ArrayList<ConfiguracionRA2> getConfiguracionesRA(Integer idAsunto, Integer idSite, Integer idConfig, Long fechaIni, 
            ArrayList<String> campos, String sqlExtra, Boolean distinct) throws SQLException, NoSuchFieldException {
        InteraccionBD interBD = new InteraccionBD();
        
        ArrayList<ConfiguracionRA2> res = null;
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();

        condicion = getCondicion(idAsunto, idSite, idConfig, fechaIni, paramsPS);
        
        //Por defecto lo devolvemos ordenado por asunto, site, configuración
        if (sqlExtra == null || sqlExtra.trim().length() == 0) {
            String sqlOrderBy = InteraccionBD.anadeCampoOrderBy(null, CAMPO_ID_ASUNTO);
            sqlOrderBy = InteraccionBD.anadeCampoOrderBy(sqlOrderBy, CAMPO_ID_SITE);
            sqlOrderBy = InteraccionBD.anadeCampoOrderBy(sqlOrderBy, CAMPO_ID_CONFIG);
            sqlExtra = InteraccionBD.anadeSqlExtra(null, sqlOrderBy);
        }

        ArrayList<Object[]> resAux = interBD.getDatosTabla(TABLA, campos, condicion, paramsPS, sqlExtra, distinct);

        if (resAux != null) {
            int n = resAux.size();
            res = new ArrayList<ConfiguracionRA2>();
            
            for (int i = 0; i < n; i++) {
               res.add(new ConfiguracionRA2(resAux.get(i), campos));
            }
        }

        return res;
    }

    //Función para añadir una configuracionRA.a la BD
    public static int insertConfiguracionRA(Integer idAsunto, Integer idSite, Integer idConfig, Long fechaIni, String sqlExtra) throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        String valores = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        ArrayList<String> campos = new ArrayList<String>();
        ArrayList<String> autoInc = new ArrayList<String>();
        ArrayList<String> condAutoInc = new ArrayList<String>();
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
        } else {    //Es campo clave, asignamos el incremento de la última
            valores = InteraccionBD.anadeCampoValorAutoInc(valores, CAMPO_ID_CONFIG);
            campos.add(CAMPO_ID_CONFIG);
            autoInc.add(CAMPO_ID_CONFIG);
            condAutoInc.add(null);
        }
        if (fechaIni != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, fechaIni);
            campos.add(CAMPO_FECHA_INI);
        }
        
        interBD.inicioTransaccion();
        res = interBD.insertDatosTabla(TABLA, campos, valores, paramsPS, sqlExtra, autoInc, condAutoInc);
        interBD.finTransaccion();
        
        return res;
    }
    
    public static int insertConfiguracionRA(ConfiguracionRA2 configuracionRA, String sqlExtra) throws SQLException {
        return insertConfiguracionRA(configuracionRA.idAsunto, configuracionRA.idSite, configuracionRA.idConfig, configuracionRA.fechaIni, sqlExtra);
    }
    
    //Función para añadir una configuracionRA.a la BD
    public static int updateConfiguracionRA(Integer idAsunto, Integer idSite, Integer idConfig, Long fechaIni, 
            Integer idAsuntoVal, Integer idSiteVal, Integer idConfigVal, Long fechaIniVal, String sqlExtra) throws SQLException {
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
        if (fechaIniVal != null && !fechaIniVal.equals(fechaIni)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, fechaIniVal);
            campos.add(CAMPO_FECHA_INI);
        }

        //Condiciones de actualización
        condicion = getCondicion(idAsunto, idSite, idConfig, fechaIni, paramsPS);
        
        interBD.inicioTransaccion();
        res = interBD.updateDatosTabla(TABLA, campos, condicion, paramsPS, sqlExtra);
        interBD.finTransaccion();
        
        return res;
    }
    
    public static int updateConfiguracionRA(ConfiguracionRA2 confRAVieja, ConfiguracionRA2 confRANueva, String sqlExtra) throws SQLException {
        return updateConfiguracionRA(confRAVieja.idAsunto, confRAVieja.idSite, confRAVieja.idConfig, confRAVieja.fechaIni, 
                confRANueva.idAsunto, confRANueva.idSite, confRANueva.idConfig, confRANueva.fechaIni, sqlExtra);
    }
    
    //Función para añadir/modificar una ConfiguracionRA a la BD
    public static int insertOrUpdateConfiguracionRA(Integer idAsunto, Integer idSite, Integer idConfig, Long fechaIni, 
            Integer idAsuntoVal, Integer idSiteVal, Integer idConfigVal, Long fechaIniVal, String sqlExtra) throws SQLException {
        int res;
        
        res = updateConfiguracionRA(idAsunto, idSite, idConfig, fechaIni, idAsuntoVal, idSiteVal, idConfigVal, fechaIniVal, sqlExtra);
        
        if (res < 0)
            res = insertConfiguracionRA(idAsuntoVal, idSiteVal, idConfigVal, fechaIniVal, sqlExtra);

        return res;
    }
    
    public static int insertOrUpdateConfiguracionRA(ConfiguracionRA2 confRAVieja, ConfiguracionRA2 confRANueva, String sqlExtra) throws SQLException {
        
        return insertOrUpdateConfiguracionRA(confRAVieja.idAsunto, confRAVieja.idSite, confRAVieja.idConfig, confRAVieja.fechaIni, 
                confRANueva.idAsunto, confRANueva.idSite, confRANueva.idConfig, confRANueva.fechaIni, sqlExtra);
    }
    
    //Función para eliminar ConfiguracionesRA.que se ajustan a los limites pasados
    public static int deleteConfiguracionesRA(Integer idAsunto, Integer idSite, Integer idConfig, Long fechaIni, String sqlExtra) throws SQLException, NoSuchFieldException {
        InteraccionBD interBD = new InteraccionBD();
        
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        int res = 0;

        condicion = getCondicion(idAsunto, idSite, idConfig, fechaIni, paramsPS);

        interBD.inicioTransaccion();
        res  = interBD.deleteDatosTabla(TABLA, condicion, paramsPS, sqlExtra);
        interBD.finTransaccion();
        
        return res;
    }
    
    public static int deleteConfiguracionesRA(ConfiguracionRA2 configuracionRA, String sqlExtra) throws SQLException, NoSuchFieldException {
        return deleteConfiguracionesRA(configuracionRA.idAsunto, configuracionRA.idSite, configuracionRA.idConfig, configuracionRA.fechaIni, sqlExtra);
    }
    
    public Object[] toObject() {
        return new Object[]{this.idAsunto, this.idSite, this.idConfig, this.fechaIni};
    }
  
    public static Object[] getCamposTabla() throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        return interBD.getCamposTabla(TABLA);
    }
    
    // Devuelve los sites de RA asociados a un asunto
    public static ArrayList<Integer> getIdSitesRA(Integer idAsunto) throws SQLException, NoSuchFieldException {
        ArrayList<String> campos = new ArrayList<String>();
        ArrayList<ConfiguracionRA2> resAux = null;
        ArrayList<Integer> res = null;
        
        //Solo nos interesa el campo de idSite
        campos.add(CAMPO_ID_SITE);
        
        String sqlOrderBy = InteraccionBD.anadeCampoOrderBy(null, CAMPO_ID_SITE);
        String sqlExtra = InteraccionBD.anadeSqlExtra(null, sqlOrderBy);
        
        resAux = getConfiguracionesRA(idAsunto, null, null, null, campos, sqlExtra, true);
        
        if (resAux != null) {
            int n = resAux.size();
            res = new ArrayList<Integer>();
            
            for (int i = 0; i < n; i++) {
               res.add(resAux.get(i).getIdSite());
            }
        }
        
        return res;
    }
    
    // Devuelve el numero de configuracion de un site válido para una fecha. Si no existe devuelve null
    public static Integer getNumeroConfiguracion(Integer idAsunto, Integer idSite, long fecha) throws SQLException, NoSuchFieldException{
        ArrayList<ConfiguracionRA2> resAux;
        Integer res = null;
        
        String sqlOrderBy = InteraccionBD.anadeCampoOrderBy(null, CAMPO_ID_CONFIG);
        String sqlExtra = InteraccionBD.anadeSqlExtra(null, sqlOrderBy);
        
        resAux = getConfiguracionesRA(idAsunto, idSite, null, null, null, sqlExtra, false);
        
        if (resAux != null) {
            int n = resAux.size();
            long fechaAux = 0;
            
            for (int i = 0; i < n; i++) {
                if (resAux.get(i).fechaIni <= fecha && resAux.get(i).fechaIni > fechaAux) {
                    res = resAux.get(i).idConfig;
                    fechaAux = resAux.get(i).fechaIni;
                }
            }
        }
        
        return res;
    }
    
    // Devuelve el máximo número de configuración para un asunto, site
    public static Integer getUltimaConfiguracion(Integer idAsunto, Integer idSite) throws SQLException, NoSuchFieldException{
        ArrayList<ConfiguracionRA2> resAux;
        Integer res = null;
        
        String sqlOrderBy = InteraccionBD.anadeCampoOrderBy(null, CAMPO_ID_CONFIG);
        String sqlExtra = InteraccionBD.anadeSqlExtra(null, sqlOrderBy);
        
        resAux = getConfiguracionesRA(idAsunto, idSite, null, null, null, sqlExtra, false);
        
        if (resAux != null) {
            int n = resAux.size();
            
            for (int i = 0; i < n; i++) {
                if (res == null || resAux.get(i).idConfig > res) {
                    res = resAux.get(i).idConfig;
                }
            }
        }
        
        return res;
    }
} 