package RA;

import general.InteraccionBD;
import general.TratDecimales;
import java.sql.SQLException;
import java.util.ArrayList;

public class DireccionRA {
    private Integer idAsunto;
    private Double direccion;
    
    private static String TABLA = "Direccion";
    
    public static final String CAMPO_ID_ASUNTO = "Asunto";
    public static final String CAMPO_DIRECCION = "Dirección";

    public DireccionRA(Integer idAsunto, Double direccion) {
        this.idAsunto = idAsunto;
        this.direccion = direccion;
    }
    
    public DireccionRA(Object[] valores, ArrayList<String> campos) throws SQLException, NoSuchFieldException {
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
    
    public Integer getIdAsunto() {
        return idAsunto;
    }

    public void setIdAsunto(Integer idAsunto) {
        this.idAsunto = idAsunto;
    }

    public Double getDireccion() {
        return direccion;
    }

    public void setDireccion(Double direccion) {
        this.direccion = direccion;
    }
    
    //Función para asignar valores a campos de la clase según el campo de la BD
    private void asignaCampo(String campo, Object valor) throws NoSuchFieldException {
        if (campo.compareTo(CAMPO_ID_ASUNTO) == 0) {
            this.idAsunto = (Integer) valor;
        } else if (campo.compareTo(CAMPO_DIRECCION) == 0) {
            this.direccion = (Double) valor;
        } else {
            throw new NoSuchFieldException("No existe el campo en la clase " + this.getClass().getSimpleName());
        }
    }
    
    private static String getCondicion(Integer idAsunto, Double direccion, ArrayList<Object[]> paramsPS) {
        String condicion = "";
        
        if (idAsunto != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_ID_ASUNTO, "=", idAsunto);
        }
        if (direccion != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_DIRECCION, "=", direccion);
        }
        
        return condicion;
    }

    //Función para obtener la colección de Direcciones que se ajustan a los limites pasados
    public static ArrayList<DireccionRA> getDirecciones(Integer idAsunto, Double direccion,
            ArrayList<String> campos, String sqlExtra, Boolean distinct) throws SQLException, NoSuchFieldException {
        InteraccionBD interBD = new InteraccionBD();
        
        ArrayList<DireccionRA> res = null;
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();

        condicion = getCondicion(idAsunto, direccion, paramsPS);
        
        //Por defecto lo devolvemos ordenado por asunto
        if (sqlExtra == null || sqlExtra.trim().length() == 0) {
            String sqlOrderBy = InteraccionBD.anadeCampoOrderBy(null, CAMPO_ID_ASUNTO);
            sqlExtra = InteraccionBD.anadeSqlExtra(null, sqlOrderBy);
        }

        ArrayList<Object[]> resAux = interBD.getDatosTabla(TABLA, campos, condicion, paramsPS, sqlExtra, distinct);

        if (resAux != null) {
            int n = resAux.size();
            res = new ArrayList<DireccionRA>();
            
            for (int i = 0; i < n; i++) {
               res.add(new DireccionRA(resAux.get(i), campos));
            }
        }

        return res;
    }
    
    //Función para obtener la colección de Direcciones que se ajustan a los limites pasados
    public static DireccionRA getDireccionPorIdAsunto(Integer idAsunto) throws SQLException, NoSuchFieldException {
        DireccionRA res = null;
        
        ArrayList<DireccionRA> resAux = getDirecciones(idAsunto, null, null, null, null);

        if (resAux != null) {
            res = resAux.get(0);
        }

        return res;
    }

    //Función para añadir una direccion a la BD
    public static int insertDireccion(Integer idAsunto, Double direccion, String sqlExtra) throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        String valores = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        ArrayList<String> campos = new ArrayList<String>();
        int res = 0;
        
        if (idAsunto != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, idAsunto);
            campos.add(CAMPO_ID_ASUNTO);
        }
        if (direccion != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, direccion);
            campos.add(CAMPO_DIRECCION);
        }
        
        interBD.inicioTransaccion();
        res = interBD.insertDatosTabla(TABLA, campos, valores, paramsPS, sqlExtra);
        interBD.finTransaccion();
        
        return res;
    }
    
    public static int insertDireccion(DireccionRA direccion, String sqlExtra) throws SQLException {
        return insertDireccion(direccion.idAsunto, direccion.direccion, sqlExtra);
    }
    
    //Función para añadir una direccion a la BD
    public static int updateDireccion(Integer idAsunto, Double direccion,
            Integer idAsuntoVal, Double direccionVal, String sqlExtra) throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        ArrayList<String> campos = new ArrayList<String>();
        int res = 0;
        
        if (idAsuntoVal != null && !idAsuntoVal.equals(idAsunto)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, idAsuntoVal);
            campos.add(CAMPO_ID_ASUNTO);
        }
        if (direccionVal != null && !direccionVal.equals(direccion)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, direccionVal);
            campos.add(CAMPO_DIRECCION);        
        }

        //Condiciones de actualización
        condicion = getCondicion(idAsunto, direccion, paramsPS);
        
        interBD.inicioTransaccion();
        res = interBD.updateDatosTabla(TABLA, campos, condicion, paramsPS, sqlExtra);
        interBD.finTransaccion();
        
        return res;
    }
    
    public static int updateDireccion(DireccionRA dirVieja, DireccionRA dirNueva, String sqlExtra) throws SQLException {
        return updateDireccion(dirVieja.idAsunto, dirVieja.direccion, 
                dirNueva.idAsunto, dirNueva.direccion, sqlExtra);
    }
    
    //Función para añadir/modificar una direccion a la BD
    public static int insertOrUpdateDireccion(Integer idAsunto, Double direccion, 
            Integer idAsuntoVal, Double direccionVal, String sqlExtra) throws SQLException {
        int res;
        
        res = updateDireccion(idAsunto, direccion, idAsuntoVal, direccionVal, sqlExtra);
        
        if (res < 0)
            res = insertDireccion(idAsuntoVal, direccionVal, sqlExtra);

        return res;
    }
    
    public static int insertOrUpdateDireccion(DireccionRA dirVieja, DireccionRA dirNueva, String sqlExtra) throws SQLException {
        
        return insertOrUpdateDireccion(dirVieja.idAsunto, dirVieja.direccion,
                dirNueva.idAsunto, dirNueva.direccion, sqlExtra);
    }
    
    //Función para eliminar Direcciones que se ajustan a los limites pasados
    public static int deleteDirecciones(Integer idAsunto, Double direccion, String sqlExtra) throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        int res = 0;

        condicion = getCondicion(idAsunto, direccion, paramsPS);

        interBD.inicioTransaccion();
        res  = interBD.deleteDatosTabla(TABLA, condicion, paramsPS, sqlExtra);
        interBD.finTransaccion();
        
        return res;
    }
    
    public static int deleteDirecciones(DireccionRA direccion, String sqlExtra) throws SQLException {
        return deleteDirecciones(direccion.idAsunto, direccion.direccion, sqlExtra);
    }
    
    public Object[] toObject() {
        return new Object[]{this.idAsunto, this.direccion};
    }
  
    public static Object[] getCamposTabla() throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        return interBD.getCamposTabla(TABLA);
    }
    
    // Devuelve el sector dada una dirección y una amplitud.
    public static Double[] getSector(Double direccion, Double amplitud) {
        Double min, max;
        
        min = TratDecimales.round(direccion - amplitud, TratDecimales.DEC_DIRECCION);
        if (min < 0)
            min = TratDecimales.round(min + 360, TratDecimales.DEC_DIRECCION);
        
        max = TratDecimales.round(direccion + amplitud, TratDecimales.DEC_DIRECCION);
        if (max > 360)
            max = TratDecimales.round(max - 360, TratDecimales.DEC_DIRECCION);
        
        return new Double[]{min.doubleValue(), max.doubleValue()};
    }
} 