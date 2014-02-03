package RA;

import general.InteraccionBD;
import java.sql.SQLException;
import java.util.ArrayList;

public class TipoIncert {
    private Integer idTipoIncert;
    private String descripcion;
    private Boolean esAcustica;
    
    public static final String BD = InteraccionBD.PREF_BD_RA;
    public static final String TABLA = BD + "TipoIncertidumbre";
    public static final String CAMPO_ID_TIPO_INCERT = "id";
    public static final String CAMPO_DESCRIPCION = "Descripcion";
    public static final String CAMPO_ES_ACUSTICA = "esAcustica";
    
    public static final Integer ID_TIPO_INCERT_MESA_SPL = 3;
    public static final Integer ID_TIPO_INCERT_MESA_OCT_FFT = 4;
    public static final Integer ID_TIPO_INCERT_VEL_MEDIDA = 8;
    public static final Integer ID_TIPO_INCERT_VEL_DERIVADA = 9;
    public static final Integer ID_TIPO_INCERT_RF = 11;
    public static final Integer ID_TIPO_INCERT_CURVA = 17;

    public TipoIncert(Integer idTipoIncert, String descripcion) {
        this.idTipoIncert = idTipoIncert;
        this.descripcion = descripcion;
    }
    
    public TipoIncert(Object[] valores, ArrayList<String> campos) throws SQLException, NoSuchFieldException {
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
    
    public Integer getIdTipoIncert() {
        return idTipoIncert;
    }

    public void setIdTipoIncert(Integer idTipoIncert) {
        this.idTipoIncert = idTipoIncert;
    }

    public Boolean getEsAcustica() {
        return esAcustica;
    }

    public void setEsAcustica(Boolean esAcustica) {
        this.esAcustica = esAcustica;
    }
    
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    //Función para asignar valores a campos de la clase según el campo de la BD
    private void asignaCampo(String campo, Object valor) throws NoSuchFieldException {
        if (campo.compareTo(CAMPO_ID_TIPO_INCERT) == 0) {
            this.idTipoIncert = (Integer) valor;
        } else if (campo.compareTo(CAMPO_DESCRIPCION) == 0) {
            this.descripcion = (String) valor;
        } else if (campo.compareTo(CAMPO_ES_ACUSTICA) == 0) {
            this.esAcustica = (Boolean) valor;
        } else {
            //throw new NoSuchFieldException("No existe el campo en la clase " + this.getClass().getSimpleName());
			System.out.println("No existe el campo <" + campo + "> en la clase " + this.getClass().getSimpleName());
        }
    }
    
    private static String getCondicion(Integer idTipoIncert, String descripcion, Boolean esAcustica, ArrayList<Object[]> paramsPS) {
        String condicion = "";
        
        if (idTipoIncert != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_ID_TIPO_INCERT, "=", idTipoIncert);
        }
        if (descripcion != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_DESCRIPCION, "=", descripcion);
        }
        if (esAcustica != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_ES_ACUSTICA, "=", esAcustica);
        }
        
        return condicion;
    }

    //Función para obtener la colección de TiposIncert que se ajustan a los limites pasados
    public static ArrayList<TipoIncert> getTiposIncert(Integer idTipoIncert, String descripcion, Boolean esAcustica, ArrayList<String> campos, String sqlExtra, Boolean distinct) throws SQLException, NoSuchFieldException {
        InteraccionBD interBD = new InteraccionBD();
        
        ArrayList<TipoIncert> res = null;
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();

        condicion = getCondicion(idTipoIncert, descripcion, esAcustica, paramsPS);
        
        //Por defecto lo devolvemos ordenado por tipo
        if (sqlExtra == null || sqlExtra.trim().length() == 0) {
            String sqlOrderBy = InteraccionBD.anadeCampoOrderBy(null, CAMPO_ID_TIPO_INCERT);
            sqlExtra = InteraccionBD.anadeSqlExtra(null, sqlOrderBy);
        }
               
        ArrayList<Object[]> resAux = interBD.getDatosTabla(TABLA, campos, condicion, paramsPS, sqlExtra, distinct);

        if (resAux != null) {
            int n = resAux.size();
            res = new ArrayList<TipoIncert>();
            
            for (int i = 0; i < n; i++) {
               res.add(new TipoIncert(resAux.get(i), campos));
            }
        }

        return res;
    }
    
    public static TipoIncert getTipoIntecertPorIdTipoIncert(Integer idTipoIncert) throws SQLException, NoSuchFieldException {
        TipoIncert res = null;
        
        ArrayList<TipoIncert> resAux = getTiposIncert(idTipoIncert, null, null, null, null, null);

        if (resAux != null && resAux.size() > 0) {
            res = resAux.get(0);
        }

        return res;
    }
    
    //Función para obtener la colección de TiposIncert.que se ajustan a los limites pasados (clave)
    public static ArrayList<TipoIncert> getTiposIncertPorIdNorma(Integer idNorma) throws SQLException, NoSuchFieldException {
        InteraccionBD interBD = new InteraccionBD();
        ArrayList<TipoIncert> res = null;
        ArrayList<String> campos = new ArrayList<String>();
                
        String condicion = "";
        String tabla = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        
        //Campos a recoger
        Object[] camposObj = getCamposTabla();
        
        int nCampos = camposObj != null ? camposObj.length : 0;
        
        for (int i = 0; i < nCampos; i++) {
            campos.add((String) camposObj[i]);            
        }
        
        //Añadimos la conectividad entre tablas
        condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, TABLA + "." + CAMPO_ID_TIPO_INCERT, InteraccionBD.ASIGNACION_CAMPOS, TipoIncertNorma.TABLA + "." + TipoIncertNorma.CAMPO_ID_TIPO_INCERT);
        
        if (idNorma != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, TipoIncertNorma.TABLA + "." + TipoIncertNorma.CAMPO_ID_NORMA, "=", idNorma);
        }
                
        tabla = InteraccionBD.anadeTabla(tabla, TABLA);
        tabla = InteraccionBD.anadeTabla(tabla, TipoIncertNorma.TABLA);

        ArrayList<Object[]> resAux = interBD.getDatosTabla(tabla, campos, condicion, paramsPS, null, null);
        
        if (resAux != null) {
            int n = resAux.size();
            res = new ArrayList<TipoIncert>();
            
            for (int i = 0; i < n; i++) {
               res.add(new TipoIncert(resAux.get(i), campos));
            }
        }
        
        return res;
    }

    //Función para añadir un TipoIncert a la BD
    public static int insertTipoIncert(Integer idTipoIncert, String descripcion, Boolean esAcustica, String sqlExtra) throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        String valores = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        ArrayList<String> campos = new ArrayList<String>();
        ArrayList<String> autoInc = new ArrayList<String>();
        ArrayList<String> condAutoInc = new ArrayList<String>();
        
        int res = 0;
        
        if (idTipoIncert != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, idTipoIncert);
            campos.add(CAMPO_ID_TIPO_INCERT);
        } else {    //Es campo clave, asignamos el incremento de la última
            valores = InteraccionBD.anadeCampoValorAutoInc(valores, CAMPO_ID_TIPO_INCERT);
            campos.add(CAMPO_ID_TIPO_INCERT);
            autoInc.add(CAMPO_ID_TIPO_INCERT);
            condAutoInc.add(null);
        }
        if (descripcion != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, descripcion);
            campos.add(CAMPO_DESCRIPCION);
        }
        if (esAcustica != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, esAcustica);
            campos.add(CAMPO_ES_ACUSTICA);
        }
        
        interBD.inicioTransaccion();
        res = interBD.insertDatosTabla(TABLA, campos, valores, paramsPS, sqlExtra, autoInc, condAutoInc);
        interBD.finTransaccion();
        
        return res;
    }
    
    public static int insertTipoIncert(TipoIncert tipoIncert, String sqlExtra) throws SQLException {
        return insertTipoIncert(tipoIncert.idTipoIncert, tipoIncert.descripcion, tipoIncert.esAcustica, sqlExtra);
    }
    
    //Función para añadir un TipoIncert a la BD
    public static int updateTipoIncert(Integer idTipoIncert, String descripcion, Boolean esAcustica, 
            Integer idTipoIncertVal, String descripcionVal, Boolean esAcusticaVal, String sqlExtra) throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        ArrayList<String> campos = new ArrayList<String>();
        int res = 0;
        
        if (idTipoIncertVal != null && !idTipoIncertVal.equals(idTipoIncert)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, idTipoIncertVal);
            campos.add(CAMPO_ID_TIPO_INCERT);
        }
        if (descripcionVal != null && !descripcionVal.equals(descripcion)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, descripcionVal);
            campos.add(CAMPO_DESCRIPCION);
        }
        if (esAcusticaVal != null && !esAcusticaVal.equals(esAcustica)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, esAcusticaVal);
            campos.add(CAMPO_ES_ACUSTICA);
        }
        
        //Condiciones de actualización
        condicion = getCondicion(idTipoIncert, descripcion, esAcustica, paramsPS);
                
        interBD.inicioTransaccion();
        res = interBD.updateDatosTabla(TABLA, campos, condicion, paramsPS, sqlExtra);
        interBD.finTransaccion();
        
        return res;
    }
    
    public static int updateTipoIncert(TipoIncert tipoViejo, TipoIncert tipoNuevo, String sqlExtra) throws SQLException {
        return updateTipoIncert(tipoViejo.idTipoIncert, tipoViejo.descripcion, tipoViejo.esAcustica, tipoNuevo.idTipoIncert, tipoNuevo.descripcion, tipoNuevo.esAcustica, sqlExtra);
    }
    
    //Función para añadir/modificar un TipoIncert a la BD
    public static int insertOrUpdateTipoIncert(Integer idTipoIncert, String descripcion, Boolean esAcustica, 
            Integer idTipoIncertVal, String descripcionVal, Boolean esAcusticaVal, String sqlExtra) throws SQLException {
        int res;
        
        res = updateTipoIncert(idTipoIncert, descripcion, esAcustica, idTipoIncertVal, descripcionVal, esAcusticaVal, sqlExtra);
        
        if (res < 0)
            res = insertTipoIncert(idTipoIncertVal, descripcionVal, esAcusticaVal, sqlExtra);

        return res;
    }
    
    public static int insertOrUpdateTipoIncert(TipoIncert tipoViejo, TipoIncert tipoNuevo, String sqlExtra) throws SQLException {
        
        return insertOrUpdateTipoIncert(tipoViejo.idTipoIncert, tipoViejo.descripcion, tipoViejo.esAcustica, 
                tipoNuevo.idTipoIncert, tipoNuevo.descripcion, tipoNuevo.esAcustica, sqlExtra);
    }
    
    //Función para eliminar TiposIncert que se ajustan a los limites pasados
    public static int deleteTiposIncert(Integer idTipoIncert, String descripcion, Boolean esAcustica, String sqlExtra) throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        int res = 0;

        condicion = getCondicion(idTipoIncert, descripcion, esAcustica, paramsPS);
        
        interBD.inicioTransaccion();
        res  = interBD.deleteDatosTabla(TABLA, condicion, paramsPS, sqlExtra);
        interBD.finTransaccion();
        
        return res;
    }
    
    public static int deleteTiposIncert(TipoIncert tipoIncert, String sqlExtra) throws SQLException {
        return deleteTiposIncert(tipoIncert.idTipoIncert, tipoIncert.descripcion, tipoIncert.esAcustica, sqlExtra);
    }
    
    public Object[] toObject() {
        return new Object[]{this.idTipoIncert, this.descripcion, this.esAcustica};
    }
  
    public static Object[] getCamposTabla() throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        return interBD.getCamposTabla(TABLA);
    }
} 