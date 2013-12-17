package RA;

import general.InteraccionBD;
import java.sql.SQLException;
import java.util.ArrayList;

public class TipoSiteRA {
    private Integer idSite;
    private Integer idTipoRA;
    
    public static final String BD = InteraccionBD.PREF_BD_RA;
    public static final String TABLA = BD + "TipoSiteRA";
    public static final String CAMPO_ID_SITE = "Id_Site";
    public static final String CAMPO_ID_TIPO_RA = "TipoRA";

    public TipoSiteRA(Integer idSite, Integer idTipoRA) {
        this.idSite = idTipoRA;
        this.idTipoRA = idTipoRA;
    }
    
    public TipoSiteRA(Object[] valores, ArrayList<String> campos) throws SQLException, NoSuchFieldException {
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
    
    public Integer getIdSite() {
        return idSite;
    }

    public void setIdSite(Integer idTipoRA) {
        this.idSite = idTipoRA;
    }

    public Integer getIdTipoRA() {
        return idTipoRA;
    }

    public void setIdTipoRA(Integer idTipoRA) {
        this.idTipoRA = idTipoRA;
    }

    //Función para asignar valores a campos de la clase según el campo de la BD
    private void asignaCampo(String campo, Object valor) throws NoSuchFieldException {
        if (campo.compareTo(CAMPO_ID_SITE) == 0) {
            this.idSite = (Integer) valor;
        } else if (campo.compareTo(CAMPO_ID_TIPO_RA) == 0) {
            this.idTipoRA = (Integer) valor;
        } else {
            throw new NoSuchFieldException("No existe el campo en la clase " + this.getClass().getSimpleName());
        }
    }
    
    private static String getCondicion(Integer idSite, Integer idTipoRA, ArrayList<Object[]> paramsPS) {
        String condicion = "";
        
        if (idSite != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_ID_SITE, "=", idSite);
        }
        if (idTipoRA != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_ID_TIPO_RA, "=", idTipoRA);
        }
        
        return condicion;
    }

    //Función para obtener la colección de TiposSiteRA que se ajustan a los limites pasados
    public static ArrayList<TipoSiteRA> getTiposSiteRA(Integer idSite, Integer idTipoRA, ArrayList<String> campos, String sqlExtra, Boolean distinct) throws SQLException, NoSuchFieldException {
        InteraccionBD interBD = new InteraccionBD();
        
        ArrayList<TipoSiteRA> res = null;
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();

        condicion = getCondicion(idSite, idTipoRA, paramsPS);
        
        //Por defecto lo devolvemos ordenado por site, tipo
        if (sqlExtra == null || sqlExtra.trim().length() == 0) {
            String sqlOrderBy = InteraccionBD.anadeCampoOrderBy(null, CAMPO_ID_SITE);
            sqlOrderBy = InteraccionBD.anadeCampoOrderBy(sqlOrderBy, CAMPO_ID_TIPO_RA);
            sqlExtra = InteraccionBD.anadeSqlExtra(null, sqlOrderBy);
        }
               
        ArrayList<Object[]> resAux = interBD.getDatosTabla(TABLA, campos, condicion, paramsPS, sqlExtra, distinct);

        if (resAux != null) {
            int n = resAux.size();
            res = new ArrayList<TipoSiteRA>();
            
            for (int i = 0; i < n; i++) {
               res.add(new TipoSiteRA(resAux.get(i), campos));
            }
        }

        return res;
    }
    
    public static TipoSiteRA getTipoSiteRA(Integer idSite) throws SQLException, NoSuchFieldException {
        TipoSiteRA res = null;
        
        ArrayList<TipoSiteRA> resAux = getTiposSiteRA(idSite, null, null, null, null);
        
        if (resAux != null)
            res = resAux.get(0);
        
        return res;
    }

    //Función para añadir un TipoRA a la BD
    public static int insertTipoSiteRA(Integer idSite, Integer idTipoRA, String sqlExtra) throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        String valores = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        ArrayList<String> campos = new ArrayList<String>();
        int res = 0;
        
        if (idSite != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, idSite);
            campos.add(CAMPO_ID_SITE);
        }
        if (idTipoRA != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, idTipoRA);
            campos.add(CAMPO_ID_TIPO_RA);
        }
        
        interBD.inicioTransaccion();
        res = interBD.insertDatosTabla(TABLA, campos, valores, paramsPS, sqlExtra);
        interBD.finTransaccion();
        
        return res;
    }
    
    public static int insertTipoSiteRA(TipoSiteRA tipoRA, String sqlExtra) throws SQLException {
        return insertTipoSiteRA(tipoRA.idSite, tipoRA.idTipoRA, sqlExtra);
    }
    
    //Función para añadir un TipoRA a la BD
    public static int updateTipoSiteRA(Integer idSite, Integer idTipoRA,
            Integer idSiteVal, Integer idTipoRAVal, String sqlExtra) throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        ArrayList<String> campos = new ArrayList<String>();
        int res = 0;
        
        if (idSiteVal != null && !idSiteVal.equals(idSite)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, idSiteVal);
            campos.add(CAMPO_ID_SITE);
        }
        if (idTipoRAVal != null && !idTipoRAVal.equals(idTipoRA)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, idTipoRAVal);
            campos.add(CAMPO_ID_TIPO_RA);
        }
        
        //Condiciones de actualización
        condicion = getCondicion(idSite, idTipoRA, paramsPS);
                
        interBD.inicioTransaccion();
        res = interBD.updateDatosTabla(TABLA, campos, condicion, paramsPS, sqlExtra);
        interBD.finTransaccion();
        
        return res;
    }
    
    public static int updateTipoSiteRA(TipoSiteRA tipoViejo, TipoSiteRA tipoNuevo, String sqlExtra) throws SQLException {
        return updateTipoSiteRA(tipoViejo.idSite, tipoViejo.idTipoRA, tipoNuevo.idSite, tipoNuevo.idTipoRA, sqlExtra);
    }
    
    //Función para añadir/modificar un TipoRA a la BD
    public static int insertOrUpdateTipoRA(Integer idSite, Integer idTipoRA,
            Integer idSiteVal, Integer idTipoRAVal, String sqlExtra) throws SQLException {
        int res;
        
        res = updateTipoSiteRA(idSite, idTipoRA, idSiteVal, idTipoRAVal, sqlExtra);
        
        if (res < 0)
            res = insertTipoSiteRA(idSiteVal, idTipoRAVal, sqlExtra);

        return res;
    }
    
    public static int insertOrUpdateTipoRA(TipoSiteRA tipoViejo, TipoSiteRA tipoNuevo, String sqlExtra) throws SQLException {
        
        return insertOrUpdateTipoRA(tipoViejo.idSite, tipoViejo.idTipoRA, 
                tipoNuevo.idSite, tipoNuevo.idTipoRA, sqlExtra);
    }
    
    //Función para eliminar TiposRA que se ajustan a los limites pasados
    public static int deleteTiposSiteRA(Integer idSite, Integer idTipoRA, String sqlExtra) throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        int res = 0;

        condicion = getCondicion(idSite, idTipoRA, paramsPS);
        
        interBD.inicioTransaccion();
        res  = interBD.deleteDatosTabla(TABLA, condicion, paramsPS, sqlExtra);
        interBD.finTransaccion();
        
        return res;
    }
    
    public static int deleteTiposSiteRA(TipoSiteRA tipoRA, String sqlExtra) throws SQLException {
        return deleteTiposSiteRA(tipoRA.idSite, tipoRA.idTipoRA, sqlExtra);
    }
    
    public Object[] toObject() {
        return new Object[]{this.idSite, this.idTipoRA};
    }
  
    public static Object[] getCamposTabla() throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        return interBD.getCamposTabla(TABLA);
    }
} 