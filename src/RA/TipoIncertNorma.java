package RA;

import general.InteraccionBD;
import java.sql.SQLException;
import java.util.ArrayList;

public class TipoIncertNorma {
    private Integer idNorma;
    private Integer idTipoIncert;
    
    public static final String BD = InteraccionBD.PREF_BD_RA;
    public static final String TABLA = BD + "TipoIncertNorma";
    public static final String CAMPO_ID_NORMA = "id_norma";
    public static final String CAMPO_ID_TIPO_INCERT = "id_tipo_incert";

    public TipoIncertNorma(Integer idNorma, Integer idTipoIncert) {
        this.idNorma = idTipoIncert;
        this.idTipoIncert = idTipoIncert;
    }
    
    public TipoIncertNorma(Object[] valores, ArrayList<String> campos) throws SQLException, NoSuchFieldException {
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
    
    public Integer getIdNorma() {
        return idNorma;
    }

    public void setIdNorma(Integer idTipoIncert) {
        this.idNorma = idTipoIncert;
    }

    public Integer getIdTipoIncert() {
        return idTipoIncert;
    }

    public void setIdTipoIncert(Integer idTipoIncert) {
        this.idTipoIncert = idTipoIncert;
    }

    //Función para asignar valores a campos de la clase según el campo de la BD
    private void asignaCampo(String campo, Object valor) throws NoSuchFieldException {
        if (campo.compareTo(CAMPO_ID_NORMA) == 0) {
            this.idNorma = (Integer) valor;
        } else if (campo.compareTo(CAMPO_ID_TIPO_INCERT) == 0) {
            this.idTipoIncert = (Integer) valor;
        } else {
            throw new NoSuchFieldException("No existe el campo en la clase " + this.getClass().getSimpleName());
        }
    }
    
    private static String getCondicion(Integer idNorma, Integer idTipoIncert, ArrayList<Object[]> paramsPS) {
        String condicion = "";
        
        if (idNorma != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_ID_NORMA, "=", idNorma);
        }
        if (idTipoIncert != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_ID_TIPO_INCERT, "=", idTipoIncert);
        }
        
        return condicion;
    }

    //Función para obtener la colección de TiposSiteRA que se ajustan a los limites pasados
    public static ArrayList<TipoIncertNorma> getTiposSiteRA(Integer idNorma, Integer idTipoIncert, ArrayList<String> campos, String sqlExtra, Boolean distinct) throws SQLException, NoSuchFieldException {
        InteraccionBD interBD = new InteraccionBD();
        
        ArrayList<TipoIncertNorma> res = null;
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();

        condicion = getCondicion(idNorma, idTipoIncert, paramsPS);
        
        //Por defecto lo devolvemos ordenado por site, tipo
        if (sqlExtra == null || sqlExtra.trim().length() == 0) {
            String sqlOrderBy = InteraccionBD.anadeCampoOrderBy(null, CAMPO_ID_NORMA);
            sqlOrderBy = InteraccionBD.anadeCampoOrderBy(sqlOrderBy, CAMPO_ID_TIPO_INCERT);
            sqlExtra = InteraccionBD.anadeSqlExtra(null, sqlOrderBy);
        }
               
        ArrayList<Object[]> resAux = interBD.getDatosTabla(TABLA, campos, condicion, paramsPS, sqlExtra, distinct);

        if (resAux != null) {
            int n = resAux.size();
            res = new ArrayList<TipoIncertNorma>();
            
            for (int i = 0; i < n; i++) {
               res.add(new TipoIncertNorma(resAux.get(i), campos));
            }
        }

        return res;
    }
    
    public static TipoIncertNorma getTipoSiteRA(Integer idNorma) throws SQLException, NoSuchFieldException {
        TipoIncertNorma res = null;
        
        ArrayList<TipoIncertNorma> resAux = getTiposSiteRA(idNorma, null, null, null, null);
        
        if (resAux != null)
            res = resAux.get(0);
        
        return res;
    }

    //Función para añadir un TipoRA a la BD
    public static int insertTipoSiteRA(Integer idNorma, Integer idTipoIncert, String sqlExtra) throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        String valores = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        ArrayList<String> campos = new ArrayList<String>();
        int res = 0;
        
        if (idNorma != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, idNorma);
            campos.add(CAMPO_ID_NORMA);
        }
        if (idTipoIncert != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, idTipoIncert);
            campos.add(CAMPO_ID_TIPO_INCERT);
        }
        
        interBD.inicioTransaccion();
        res = interBD.insertDatosTabla(TABLA, campos, valores, paramsPS, sqlExtra);
        interBD.finTransaccion();
        
        return res;
    }
    
    public static int insertTipoSiteRA(TipoIncertNorma tipoRA, String sqlExtra) throws SQLException {
        return insertTipoSiteRA(tipoRA.idNorma, tipoRA.idTipoIncert, sqlExtra);
    }
    
    //Función para añadir un TipoRA a la BD
    public static int updateTipoSiteRA(Integer idNorma, Integer idTipoIncert,
            Integer idNormaVal, Integer idTipoIncertVal, String sqlExtra) throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        ArrayList<String> campos = new ArrayList<String>();
        int res = 0;
        
        if (idNormaVal != null && !idNormaVal.equals(idNorma)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, idNormaVal);
            campos.add(CAMPO_ID_NORMA);
        }
        if (idTipoIncertVal != null && !idTipoIncertVal.equals(idTipoIncert)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, idTipoIncertVal);
            campos.add(CAMPO_ID_TIPO_INCERT);
        }
        
        //Condiciones de actualización
        condicion = getCondicion(idNorma, idTipoIncert, paramsPS);
                
        interBD.inicioTransaccion();
        res = interBD.updateDatosTabla(TABLA, campos, condicion, paramsPS, sqlExtra);
        interBD.finTransaccion();
        
        return res;
    }
    
    public static int updateTipoSiteRA(TipoIncertNorma tipoViejo, TipoIncertNorma tipoNuevo, String sqlExtra) throws SQLException {
        return updateTipoSiteRA(tipoViejo.idNorma, tipoViejo.idTipoIncert, tipoNuevo.idNorma, tipoNuevo.idTipoIncert, sqlExtra);
    }
    
    //Función para añadir/modificar un TipoRA a la BD
    public static int insertOrUpdateTipoRA(Integer idNorma, Integer idTipoIncert,
            Integer idNormaVal, Integer idTipoIncertVal, String sqlExtra) throws SQLException {
        int res;
        
        res = updateTipoSiteRA(idNorma, idTipoIncert, idNormaVal, idTipoIncertVal, sqlExtra);
        
        if (res < 0)
            res = insertTipoSiteRA(idNormaVal, idTipoIncertVal, sqlExtra);

        return res;
    }
    
    public static int insertOrUpdateTipoRA(TipoIncertNorma tipoViejo, TipoIncertNorma tipoNuevo, String sqlExtra) throws SQLException {
        
        return insertOrUpdateTipoRA(tipoViejo.idNorma, tipoViejo.idTipoIncert, 
                tipoNuevo.idNorma, tipoNuevo.idTipoIncert, sqlExtra);
    }
    
    //Función para eliminar TiposRA que se ajustan a los limites pasados
    public static int deleteTiposSiteRA(Integer idNorma, Integer idTipoIncert, String sqlExtra) throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        int res = 0;

        condicion = getCondicion(idNorma, idTipoIncert, paramsPS);
        
        interBD.inicioTransaccion();
        res  = interBD.deleteDatosTabla(TABLA, condicion, paramsPS, sqlExtra);
        interBD.finTransaccion();
        
        return res;
    }
    
    public static int deleteTiposSiteRA(TipoIncertNorma tipoRA, String sqlExtra) throws SQLException {
        return deleteTiposSiteRA(tipoRA.idNorma, tipoRA.idTipoIncert, sqlExtra);
    }
    
    public Object[] toObject() {
        return new Object[]{this.idNorma, this.idTipoIncert};
    }
  
    public static Object[] getCamposTabla() throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        return interBD.getCamposTabla(TABLA);
    }
} 