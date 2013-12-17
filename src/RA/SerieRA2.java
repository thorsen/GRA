
package RA;

import general.InteraccionBD;
import java.sql.SQLException;
import java.util.ArrayList;


//////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////EQUIVALENCIAS///////////////////////////////////////////////
///////SerieRA2//////////////////////////////////////////////SerieRA//////////////////////////////
//Varibale --> getVariablePorId(<Id>).getNomVariable()   |   getVariable
//Ponderacion--> getPonderacionPorId(<IdSerie>)          |   getPonderaciones

public class SerieRA2 {
    private Integer idSerie;
    private String descripcion;
    private Integer idVariable;
    private String codigo;
    private Integer idTipoRA;
    
    public static final String BD = InteraccionBD.PREF_BD_RA;
    public static final String TABLA = BD + "SerieRA";
    public static final String CAMPO_ID_SERIE = "Id_serie";
    public static final String CAMPO_DESC = "Descripción";
    public static final String CAMPO_VAR = "Variable";
    public static final String CAMPO_CODIGO = "Codigo";
    public static final String CAMPO_TIPO = "Tipo";
    
    public SerieRA2(Integer idSerie, String descripcion, Integer idVariable, String codigo, Integer idTipoRA) {
        this.idSerie = idSerie;
        this.descripcion = descripcion;
        this.idVariable = idVariable;
        this.codigo = codigo;
        this.idTipoRA = idTipoRA;
    }
    
    public SerieRA2(Object[] valores, ArrayList<String> campos) throws SQLException, NoSuchFieldException {
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
    
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getIdSerie() {
        return idSerie;
    }

    public void setIdSerie(Integer idSerie) {
        this.idSerie = idSerie;
    }

    public Integer getIdTipoRA() {
        return idTipoRA;
    }

    public void setIdTipoRA(Integer idTipoRA) {
        this.idTipoRA = idTipoRA;
    }

    public Integer getIdVariable() {
        return idVariable;
    }

    public void setIdVariable(Integer idVariable) {
        this.idVariable = idVariable;
    }
    
    //Función para asignar valores a campos de la clase según el campo de la BD
    private void asignaCampo(String campo, Object valor) throws NoSuchFieldException {
         if (campo.compareTo(CAMPO_ID_SERIE) == 0) {
            this.idSerie = (Integer) valor;
        } else if (campo.compareTo(CAMPO_DESC) == 0) {
            this.descripcion = (String) valor;
        } else if (campo.compareTo(CAMPO_VAR) == 0) {
            this.idVariable = (Integer) valor;
        } else if (campo.compareTo(CAMPO_CODIGO) == 0) {
            this.codigo = (String) valor;
        } else if (campo.compareTo(CAMPO_TIPO) == 0) {
            this.idTipoRA = (Integer) valor;
        } else {
            throw new NoSuchFieldException("No existe el campo <" + campo + "> en la clase " + this.getClass().getSimpleName());
        }
    }
    
    private static String getCondicion(Integer idSerie, String descripcion, Integer idVariable, String codigo, Integer idTipoRA, ArrayList<Object[]> paramsPS) {
        String condicion = "";
        
        if (idSerie != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_ID_SERIE, "=", idSerie);
        }
        if (descripcion != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_DESC, "=", descripcion);
        }
        if (idVariable != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_VAR, "=", idVariable);
        }
        if (codigo != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_CODIGO, "=", codigo);
        }
        if (idTipoRA != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_TIPO, "=", idTipoRA);
        }
        
        return condicion;
    }
    
    //Función para obtener la colección de SeriesRA2 que se ajustan a los limites pasados
    public static ArrayList<SerieRA2> getSeriesRA2(Integer idSerie, String descripcion, Integer idVariable, String codigo, Integer idTipoRA, 
            ArrayList<String> campos, String sqlExtra, Boolean distinct) throws SQLException, NoSuchFieldException {
        InteraccionBD interBD = new InteraccionBD();
        
        ArrayList<SerieRA2> res = null;
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();

        condicion = getCondicion(idSerie, descripcion, idVariable, codigo, idTipoRA, paramsPS);
        
        //Por defecto lo devolvemos ordenado por Serie
        if (sqlExtra == null || sqlExtra.trim().length() == 0) {
            String sqlOrderBy = InteraccionBD.anadeCampoOrderBy(null, CAMPO_ID_SERIE);
            sqlExtra = InteraccionBD.anadeSqlExtra(null, sqlOrderBy);
        }

        ArrayList<Object[]> resAux = interBD.getDatosTabla(TABLA, campos, condicion, paramsPS, sqlExtra, distinct);

        if (resAux != null) {
            int n = resAux.size();
            res = new ArrayList<SerieRA2>();
            
            for (int i = 0; i < n; i++) {
               res.add(new SerieRA2(resAux.get(i), campos));
            }
        }

        return res;
    }
    
    public static SerieRA2 getSerieRA2PorId(Integer idSerie) throws SQLException, NoSuchFieldException {
        SerieRA2 res = null;
        ArrayList<SerieRA2> resAux = getSeriesRA2(idSerie, null, null, null, null, null, null, null);

        if (resAux != null && resAux.size() > 0) {
            res = resAux.get(0);
        }
        return res;
    }
    
    public static SerieRA2 getSerieRA2PorDescripcion(String descripcion) throws SQLException, NoSuchFieldException {
        SerieRA2 res = null;
        ArrayList<SerieRA2> resAux = getSeriesRA2(null, descripcion, null, null, null, null, null, null);

        if (resAux != null && resAux.size() > 0) {
            res = resAux.get(0);
        }
        return res;
    }
    
    public static SerieRA2 getSerieRA2PorCodigo(String codigo) throws SQLException, NoSuchFieldException {
        SerieRA2 res = null;
        ArrayList<SerieRA2> resAux = getSeriesRA2(null, null, null, codigo, null, null, null, null);

        if (resAux != null && resAux.size() > 0) {
            res = resAux.get(0);
        }
        return res;
    }
    
    public static ArrayList<SerieRA2> getSeriesRA2PorTipo(Integer idTipoRA) throws SQLException, NoSuchFieldException {
        return getSeriesRA2(null, null, null, null, idTipoRA, null, null, null);
    }
    
    public static ArrayList<String> getDescCodigosPorTipo(Integer idTipoRA) throws SQLException, NoSuchFieldException {
        ArrayList<String> res = null;
        ArrayList<SerieRA2> resAux = getSeriesRA2(null, null, null, null, idTipoRA, null, null, null);
        
        if (resAux != null) {
            int nResAux = resAux.size();
            
            if (nResAux > 0) {
                res = new ArrayList<String>();
            
                for (int i = 0; i < nResAux; i++) {
                    res.add(resAux.get(i).getDescripcion());
                }
            }
        }
        
        return res;
    }
    
    // Devuelve la descripcion de tabla de un asunto (codigos)
    public static ArrayList<String> getCodigosPorIdAsuntoTipo(Integer idAsunto, Integer idTipoRA, String sqlExtra, Boolean distinct) throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        ArrayList<String> res = null;
                
        String condicion = "";
        String tabla = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        ArrayList<String> campos = new ArrayList<String>();
        
        //Solo nos interesa recoger los códigos
        campos.add(CAMPO_CODIGO);

        //Añadimos la conectividad entre tablas
        condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, TABLA + "." + CAMPO_ID_SERIE, InteraccionBD.ASIGNACION_CAMPOS, Descripcion.TABLA + "." + Descripcion.CAMPO_ID_SERIE);
        
        if (idAsunto != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, Descripcion.TABLA + "." + Descripcion.CAMPO_ID_ASUNTO, "=", idAsunto);
        }
        if (idTipoRA != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, TABLA + "." + CAMPO_TIPO, "=", idTipoRA);
        }
        
        tabla = InteraccionBD.anadeTabla(tabla, TABLA);
        tabla = InteraccionBD.anadeTabla(tabla, Descripcion.TABLA);

        ArrayList<Object[]> resAux = interBD.getDatosTabla(tabla, campos, condicion, paramsPS, sqlExtra, distinct);

        if (resAux != null) {
            int n = resAux.size();
            res = new ArrayList<String>();

            for (int i = 0; i < n; i++) {
                res.add((String) resAux.get(i)[0]);
            }
        }

        return res;
    }
    
    // Devuelve la descripcion de tabla de un asunto (codigos)
    public static ArrayList<String> getCodigosPorIdAsuntoTipo(Integer idAsunto, Integer idTipoRA) throws SQLException {
        return getCodigosPorIdAsuntoTipo(idAsunto, idTipoRA, null, null);
    }
    
    // Devuelve la descripcion de tabla de un asunto (codigos)
    public static ArrayList<String> getDescCodigosPorIdAsuntoTipo(Integer idAsunto, Integer idTipoRA) throws SQLException, NoSuchFieldException {
        ArrayList<String> res = null;
        ArrayList<String> resAux = getCodigosPorIdAsuntoTipo(idAsunto, idTipoRA, null, null);
        
        if (resAux != null) {
            int nResAux = resAux.size();
            
            if (nResAux > 0) {
                res = new ArrayList<String>();
            
                for (int i = 0; i < nResAux; i++) {
                    res.add(getSerieRA2PorCodigo(resAux.get(i)).getDescripcion());
                }
            }
        }
        
        return res;
    }
    
    //Función para añadir una SerieRA2 a la BD
    public static int insertSerieRA2(Integer idSerie, String descripcion, Integer idVariable, String codigo, Integer idTipoRA, String sqlExtra) throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        String valores = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        ArrayList<String> campos = new ArrayList<String>();
        ArrayList<String> autoInc = new ArrayList<String>();
        ArrayList<String> condAutoInc = new ArrayList<String>();
        int res = 0;
        
        if (idSerie != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, idSerie);
            campos.add(CAMPO_ID_SERIE);
        } else {    //Es campo clave, asignamos el incremento de la última
            valores = InteraccionBD.anadeCampoValorAutoInc(valores, CAMPO_ID_SERIE);
            campos.add(CAMPO_ID_SERIE);
            autoInc.add(CAMPO_ID_SERIE);
            condAutoInc.add(null);
        }
        if (descripcion != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, descripcion);
            campos.add(CAMPO_DESC);
        }
        if (idVariable != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, idVariable);
            campos.add(CAMPO_VAR);
        }
        if (codigo != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, codigo);
            campos.add(CAMPO_CODIGO);
        }
        if (idTipoRA != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, idTipoRA);
            campos.add(CAMPO_TIPO);
        }
        
        interBD.inicioTransaccion();
        res = interBD.insertDatosTabla(TABLA, campos, valores, paramsPS, sqlExtra, autoInc, condAutoInc);
        interBD.finTransaccion();
        
        return res;
    }
    
    public static int insertSerieRA2(SerieRA2 serieRA2, String sqlExtra) throws SQLException {
        return insertSerieRA2(serieRA2.idSerie, serieRA2.descripcion, serieRA2.idVariable, serieRA2.codigo, serieRA2.idTipoRA, sqlExtra);
    }
    
    //Función para añadir una SerieRA2 a la BD
    public static int updateSerieRA2(Integer idSerie, String descripcion, Integer idVariable, String codigo, Integer idTipoRA,
            Integer idSerieVal, String descripcionVal, Integer idVariableVal, String codigoVal, Integer idTipoRAVal, String sqlExtra) throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        ArrayList<String> campos = new ArrayList<String>();
        int res = 0;
        
        if (idSerieVal != null && !idSerieVal.equals(idSerie)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, idSerieVal);
            campos.add(CAMPO_ID_SERIE);
        }
        if (descripcionVal != null && !descripcionVal.equals(descripcion)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, descripcionVal);
            campos.add(CAMPO_DESC);
        }
        if (idVariableVal != null && !idVariableVal.equals(idVariable)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, idVariableVal);
            campos.add(CAMPO_VAR);        
        }
        if (codigoVal != null && !codigoVal.equals(codigo)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, codigoVal);
            campos.add(CAMPO_CODIGO);        
        }
        if (idTipoRAVal != null) {
            InteraccionBD.anadeCampoValor(null, paramsPS, idTipoRAVal);
            campos.add(CAMPO_TIPO);        
        }

        //Condiciones de actualización
        condicion = getCondicion(idSerie, descripcion, idVariable, codigo, idTipoRA, paramsPS);
        
        interBD.inicioTransaccion();
        res = interBD.updateDatosTabla(TABLA, campos, condicion, paramsPS, sqlExtra);
        interBD.finTransaccion();
        
        return res;
    }
    
    public static int updateSerieRA2(SerieRA2 serieVieja, SerieRA2 serieNueva, String sqlExtra) throws SQLException {
        return updateSerieRA2(serieVieja.idSerie, serieVieja.descripcion, serieVieja.idVariable, serieVieja.codigo, serieVieja.idTipoRA,
                serieNueva.idSerie, serieNueva.descripcion, serieNueva.idVariable, serieNueva.codigo, serieNueva.idTipoRA, sqlExtra);
    }
    
    //Función para añadir/modificar una SerieRA2 a la BD
    public static int insertOrUpdateSerieRA2(Integer idSerie, String descripcion, Integer idVariable, String codigo, Integer idTipoRA,
            Integer idSerieVal, String descripcionVal, Integer idVariableVal, String codigoVal, Integer idTipoRAVal, String sqlExtra) throws SQLException {
        int res;
        
        res = updateSerieRA2(idSerie, descripcion, idVariable, codigo, idTipoRA, idSerieVal, descripcionVal, idVariableVal, codigoVal, idTipoRAVal, sqlExtra);
        
        if (res < 0)
            res = insertSerieRA2(idSerieVal, descripcionVal, idVariableVal, codigoVal, idTipoRAVal, sqlExtra);

        return res;
    }
    
    public static int insertOrUpdateSerieRA2(SerieRA2 serieVieja, SerieRA2 serieNueva, String sqlExtra) throws SQLException {
        
        return insertOrUpdateSerieRA2(serieVieja.idSerie, serieVieja.descripcion, serieVieja.idVariable, serieVieja.codigo, serieVieja.idTipoRA,
                serieNueva.idSerie, serieNueva.descripcion, serieNueva.idVariable, serieNueva.codigo, serieNueva.idTipoRA, sqlExtra);
    }
    
    //Función para eliminar SeriesRA2 que se ajustan a los limites pasados
    public static int deleteSeriesRA2(Integer idSerie, String descripcion, Integer idVariable, String codigo, Integer idTipoRA, String sqlExtra) throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        int res = 0;

        condicion = getCondicion(idSerie, descripcion, idVariable, codigo, idTipoRA, paramsPS);

        interBD.inicioTransaccion();
        res  = interBD.deleteDatosTabla(TABLA, condicion, paramsPS, sqlExtra);
        interBD.finTransaccion();
        
        return res;
    }
    
    public static int deleteSeriesRA2(SerieRA2 SerieRA2, String sqlExtra) throws SQLException {
        return deleteSeriesRA2(SerieRA2.idSerie, SerieRA2.descripcion, SerieRA2.idVariable, SerieRA2.codigo, SerieRA2.idTipoRA, sqlExtra);
    }
    
    public Object[] toObject() {
        return new Object[]{this.idSerie, this.descripcion, this.idVariable, this.codigo, this.idTipoRA};
    }
  
    public static Object[] getCamposTabla() throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        return interBD.getCamposTabla(TABLA);
    }
    
    // Devuelve las bandas de octava de un conjunto de codigos
    public ArrayList<String> getBandasOctava(ArrayList<String> codigos) throws SQLException {
        ArrayList<String> res = new ArrayList<String>();
        
        int nCod = codigos.size();
        
        for (int i = 0; i < nCod; i++){
            if (codigos.get(i).contains("Hz")){
                res.add(codigos.get(i));
            }
        }     

        return res;
    }
}
