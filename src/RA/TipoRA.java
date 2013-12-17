package RA;

import general.Auxiliares;
import general.InteraccionBD;
import general.MensajeApp;
import java.sql.SQLException;
import java.util.ArrayList;
import userinterfaces.GRA;

public class TipoRA {
    private Integer idTipoRA;
    private String sufijo;
    
    public static final String BD = InteraccionBD.PREF_BD_RA;
    public static final String TABLA = BD + "TipoRA";
    public static final String CAMPO_ID_TIPO_RA = "TipoRA";
    public static final String CAMPO_SUFIJO = "SufijoTabla";
    
    public static Integer ID_TIPO_GEN = null;
    public static Integer ID_TIPO_SPL = null;
    public static Integer ID_TIPO_OCT = null;
    public static Integer ID_TIPO_FFT = null;
    
    static {
        try {
            ID_TIPO_GEN = getTiposRA(null, Auxiliares.TIPO_GEN, null, null, null).get(0).getIdTipoRA();
            ID_TIPO_SPL = getTiposRA(null, Auxiliares.TIPO_SPL, null, null, null).get(0).getIdTipoRA();
            ID_TIPO_OCT = getTiposRA(null, Auxiliares.TIPO_OCT, null, null, null).get(0).getIdTipoRA();
            ID_TIPO_FFT = getTiposRA(null, Auxiliares.TIPO_FFT, null, null, null).get(0).getIdTipoRA();
        } catch (SQLException e) {
            MensajeApp.muestraError(GRA.getFrames()[0], e, "No se pudieron cargar los tipos por defecto");
        } catch (NoSuchFieldException e) {
            MensajeApp.muestraError(GRA.getFrames()[0], e, "No se pudieron cargar los tipos por defecto");
        }
    }

    public TipoRA(Integer idTipoRA, String sufijo) {
        this.idTipoRA = idTipoRA;
        this.sufijo = sufijo;
    }
    
    public TipoRA(Object[] valores, ArrayList<String> campos) throws SQLException, NoSuchFieldException {
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
    
    public Integer getIdTipoRA() {
        return idTipoRA;
    }

    public void setIdTipoRA(Integer idTipoRA) {
        this.idTipoRA = idTipoRA;
    }

    public String getSufijo() {
        return sufijo;
    }

    public void setSufijo(String sufijo) {
        this.sufijo = sufijo;
    }

    //Función para asignar valores a campos de la clase según el campo de la BD
    private void asignaCampo(String campo, Object valor) throws NoSuchFieldException {
        if (campo.compareTo(CAMPO_ID_TIPO_RA) == 0) {
            this.idTipoRA = (Integer) valor;
        } else if (campo.compareTo(CAMPO_SUFIJO) == 0) {
            this.sufijo = (String) valor;
        } else {
            throw new NoSuchFieldException("No existe el campo en la clase " + this.getClass().getSimpleName());
        }
    }
    
    private static String getCondicion(Integer idTipoRA, String sufijo, ArrayList<Object[]> paramsPS) {
        String condicion = "";
        
        if (idTipoRA != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_ID_TIPO_RA, "=", idTipoRA);
        }
        if (sufijo != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_SUFIJO, "=", sufijo);
        }
        
        return condicion;
    }

    //Función para obtener la colección de TiposRA que se ajustan a los limites pasados
    public static ArrayList<TipoRA> getTiposRA(Integer idTipoRA, String sufijo, ArrayList<String> campos, String sqlExtra, Boolean distinct) throws SQLException, NoSuchFieldException {
        InteraccionBD interBD = new InteraccionBD();
        
        ArrayList<TipoRA> res = null;
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();

        condicion = getCondicion(idTipoRA, sufijo, paramsPS);
        
        //Por defecto lo devolvemos ordenado por tipo
        if (sqlExtra == null || sqlExtra.trim().length() == 0) {
            String sqlOrderBy = InteraccionBD.anadeCampoOrderBy(null, CAMPO_ID_TIPO_RA);
            sqlExtra = InteraccionBD.anadeSqlExtra(null, sqlOrderBy);
        }
               
        ArrayList<Object[]> resAux = interBD.getDatosTabla(TABLA, campos, condicion, paramsPS, sqlExtra, distinct);

        if (resAux != null) {
            int n = resAux.size();
            res = new ArrayList<TipoRA>();
            
            for (int i = 0; i < n; i++) {
               res.add(new TipoRA(resAux.get(i), campos));
            }
        }

        return res;
    }
    
    //Función para obtener la colección de TiposRA.que se ajustan a los limites pasados (clave)
    public static TipoRA getTipoRAPorIdSite(Integer idSite) throws SQLException, NoSuchFieldException {
        InteraccionBD interBD = new InteraccionBD();
        TipoRA res = null;
        ArrayList<String> campos = new ArrayList<String>();
                
        String condicion = "";
        String tabla = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        
        //Campos a recoger
        campos.add(TABLA + ".*");
        
        //Añadimos la conectividad entre tablas
        condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, TABLA + "." + CAMPO_ID_TIPO_RA, InteraccionBD.ASIGNACION_CAMPOS, TipoSiteRA.TABLA + "." + TipoSiteRA.CAMPO_ID_TIPO_RA);
        
        if (idSite != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, TipoSiteRA.TABLA + "." + TipoSiteRA.CAMPO_ID_SITE, "=", idSite);
        }
                
        tabla = InteraccionBD.anadeTabla(tabla, TABLA);
        tabla = InteraccionBD.anadeTabla(tabla, TipoSiteRA.TABLA);

        ArrayList<Object[]> resAux = interBD.getDatosTabla(tabla, campos, condicion, paramsPS, null, null);
        
        if (resAux != null) {
            res = new TipoRA(resAux.get(0), null);
        }
        
        return res;
    }

    //Función para añadir un TipoRA a la BD
    public static int insertTipoRA(Integer idTipoRA, String sufijo, String sqlExtra) throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        String valores = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        ArrayList<String> campos = new ArrayList<String>();
        ArrayList<String> autoInc = new ArrayList<String>();
        ArrayList<String> condAutoInc = new ArrayList<String>();
        
        int res = 0;
        
        if (idTipoRA != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, idTipoRA);
            campos.add(CAMPO_ID_TIPO_RA);
        } else {    //Es campo clave, asignamos el incremento de la última
            valores = InteraccionBD.anadeCampoValorAutoInc(valores, CAMPO_ID_TIPO_RA);
            campos.add(CAMPO_ID_TIPO_RA);
            autoInc.add(CAMPO_ID_TIPO_RA);
            condAutoInc.add(null);
        }
        if (sufijo != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, sufijo);
            campos.add(CAMPO_SUFIJO);
        }
        
        interBD.inicioTransaccion();
        res = interBD.insertDatosTabla(TABLA, campos, valores, paramsPS, sqlExtra, autoInc, condAutoInc);
        interBD.finTransaccion();
        
        return res;
    }
    
    public static int insertTipoRA(TipoRA tipoRA, String sqlExtra) throws SQLException {
        return insertTipoRA(tipoRA.idTipoRA, tipoRA.sufijo, sqlExtra);
    }
    
    //Función para añadir un TipoRA a la BD
    public static int updateTipoRA(Integer idTipoRA, String descripcion,
            Integer idTipoRAVal, String descripcionVal, String sqlExtra) throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        ArrayList<String> campos = new ArrayList<String>();
        int res = 0;
        
        if (idTipoRAVal != null && !idTipoRAVal.equals(idTipoRA)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, idTipoRAVal);
            campos.add(CAMPO_ID_TIPO_RA);
        }
        if (descripcionVal != null && !descripcionVal.equals(descripcion)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, descripcionVal);
            campos.add(CAMPO_SUFIJO);
        }
        
        //Condiciones de actualización
        condicion = getCondicion(idTipoRA, descripcion, paramsPS);
                
        interBD.inicioTransaccion();
        res = interBD.updateDatosTabla(TABLA, campos, condicion, paramsPS, sqlExtra);
        interBD.finTransaccion();
        
        return res;
    }
    
    public static int updateTipoRA(TipoRA tipoViejo, TipoRA tipoNuevo, String sqlExtra) throws SQLException {
        return updateTipoRA(tipoViejo.idTipoRA, tipoViejo.sufijo, tipoNuevo.idTipoRA, tipoNuevo.sufijo, sqlExtra);
    }
    
    //Función para añadir/modificar un TipoRA a la BD
    public static int insertOrUpdateTipoRA(Integer idTipoRA, String sufijo,
            Integer idTipoRAVal, String descripcionVal, String sqlExtra) throws SQLException {
        int res;
        
        res = updateTipoRA(idTipoRA, sufijo, idTipoRAVal, descripcionVal, sqlExtra);
        
        if (res < 0)
            res = insertTipoRA(idTipoRAVal, descripcionVal, sqlExtra);

        return res;
    }
    
    public static int insertOrUpdateTipoRA(TipoRA tipoViejo, TipoRA tipoNuevo, String sqlExtra) throws SQLException {
        
        return insertOrUpdateTipoRA(tipoViejo.idTipoRA, tipoViejo.sufijo, 
                tipoNuevo.idTipoRA, tipoNuevo.sufijo, sqlExtra);
    }
    
    //Función para eliminar TiposRA que se ajustan a los limites pasados
    public static int deleteTiposRA(Integer idTipoRA, String sufijo, String sqlExtra) throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        int res = 0;

        condicion = getCondicion(idTipoRA, sufijo, paramsPS);
        
        interBD.inicioTransaccion();
        res  = interBD.deleteDatosTabla(TABLA, condicion, paramsPS, sqlExtra);
        interBD.finTransaccion();
        
        return res;
    }
    
    public static int deleteTiposRA(TipoRA tipoRA, String sqlExtra) throws SQLException {
        return deleteTiposRA(tipoRA.idTipoRA, tipoRA.sufijo, sqlExtra);
    }
    
    public Object[] toObject() {
        return new Object[]{this.idTipoRA, this.sufijo};
    }
  
    public static Object[] getCamposTabla() throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        return interBD.getCamposTabla(TABLA);
    }
}