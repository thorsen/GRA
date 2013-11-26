package RA;

import general.InteraccionBD;
import java.sql.SQLException;
import java.util.ArrayList;

public class Ponderacion {
    private Integer idSerie;
    private Double valor;
    
    public static final String TABLA = "Ponderacion";
    public static final String CAMPO_ID_SERIE = "Serie";
    public static final String CAMPO_VALOR = "Valor";

    public Ponderacion(Integer idSerie, Double valor) {
        this.idSerie = idSerie;
        this.valor = valor;
    }
    
    public Ponderacion(Object[] valores, ArrayList<String> campos) throws SQLException, NoSuchFieldException {
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
    
    public Integer getIdSerie() {
        return idSerie;
    }

    public void setIdSerie(Integer idSerie) {
        this.idSerie = idSerie;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    //Función para asignar valores a campos de la clase según el campo de la BD
    private void asignaCampo(String campo, Object valor) throws NoSuchFieldException {
        if (campo.compareTo(CAMPO_ID_SERIE) == 0) {
            this.idSerie = (Integer) valor;
        } else if (campo.compareTo(CAMPO_VALOR) == 0) {
            this.valor = (Double) valor;
        } else {
            throw new NoSuchFieldException("No existe el campo en la clase " + this.getClass().getSimpleName());
        }
    }
    
    private static String getCondicion(Integer idSerie, Double valor, ArrayList<Object[]> paramsPS) {
        String condicion = "";
        
        if (idSerie != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_ID_SERIE, "=", idSerie);
        }
        if (valor != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_VALOR, "=", valor);
        }
        
        return condicion;
    }

    //Función para obtener la colección de Ponderaciones que se ajustan a los limites pasados
    public static ArrayList<Ponderacion> getPonderaciones(Integer idSerie, Double valor, ArrayList<String> campos, String sqlExtra, Boolean distinct) throws SQLException, NoSuchFieldException {
        InteraccionBD interBD = new InteraccionBD();
        
        ArrayList<Ponderacion> res = null;
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();

        condicion = getCondicion(idSerie, valor, paramsPS);
        
        //Por defecto lo devolvemos ordenado por serie
        if (sqlExtra == null || sqlExtra.trim().length() == 0) {
            String sqlOrderBy = InteraccionBD.anadeCampoOrderBy(null, CAMPO_ID_SERIE);
            sqlExtra = InteraccionBD.anadeSqlExtra(null, sqlOrderBy);
        }
               
        ArrayList<Object[]> resAux = interBD.getDatosTabla(TABLA, campos, condicion, paramsPS, sqlExtra, distinct);

        if (resAux != null) {
            int n = resAux.size();
            res = new ArrayList<Ponderacion>();
            
            for (int i = 0; i < n; i++) {
               res.add(new Ponderacion(resAux.get(i), campos));
            }
        }

        return res;
    }
    
    public static Ponderacion getPonderacionPorId(Integer idSerie) throws SQLException, NoSuchFieldException {
        Ponderacion res = null;
        ArrayList<Ponderacion> resAux = getPonderaciones(idSerie, null, null, null, null);

        if (resAux != null && resAux.size() > 0) {
            res = resAux.get(0);
        }
        return res;
    }

    //Función para añadir una ponderacion a la BD
    public static int insertPonderacion(Integer idSerie, Double valor, String sqlExtra) throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        String valores = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        ArrayList<String> campos = new ArrayList<String>();
        int res = 0;
        
        if (idSerie != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, idSerie);
            campos.add(CAMPO_ID_SERIE);
        }
        if (valor != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, valor);
            campos.add(CAMPO_VALOR);
        }
        
        interBD.inicioTransaccion();
        res = interBD.insertDatosTabla(TABLA, campos, valores, paramsPS, sqlExtra);
        interBD.finTransaccion();
        
        return res;
    }
    
    public static int insertPonderacion(Ponderacion ponderacion, String sqlExtra) throws SQLException {
        return insertPonderacion(ponderacion.idSerie, ponderacion.valor, sqlExtra);
    }
    
    //Función para añadir una ponderacion a la BD
    public static int updatePonderacion(Integer idSerie, Double valor,
            Integer idSerieVal, Double valorVal, String sqlExtra) throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        ArrayList<String> campos = new ArrayList<String>();
        int res = 0;
        
        if (idSerieVal != null && !idSerieVal.equals(idSerie)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, idSerieVal);
            campos.add(CAMPO_ID_SERIE);
        }
        if (valorVal != null && !valorVal.equals(valor)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, valorVal);
            campos.add(CAMPO_VALOR);
        }
        
        //Condiciones de actualización
        condicion = getCondicion(idSerie, valor, paramsPS);
                
        interBD.inicioTransaccion();
        res = interBD.updateDatosTabla(TABLA, campos, condicion, paramsPS, sqlExtra);
        interBD.finTransaccion();
        
        return res;
    }
    
    public static int updatePonderacion(Ponderacion pondVieja, Ponderacion pondNueva, String sqlExtra) throws SQLException {
        return updatePonderacion(pondVieja.idSerie, pondVieja.valor, pondNueva.idSerie, pondNueva.valor, sqlExtra);
    }
    
    //Función para añadir/modificar una ponderacion a la BD
    public static int insertOrUpdatePonderacion(Integer idSerie, Double valor,
            Integer idSerieVal, Double valorVal, String sqlExtra) throws SQLException {
        int res;
        
        res = updatePonderacion(idSerie, valor, idSerieVal, valorVal, sqlExtra);
        
        if (res < 0)
            res = insertPonderacion(idSerieVal, valorVal, sqlExtra);

        return res;
    }
    
    public static int insertOrUpdatePonderacion(Ponderacion pondVieja, Ponderacion pondNueva, String sqlExtra) throws SQLException {
        
        return insertOrUpdatePonderacion(pondVieja.idSerie, pondVieja.valor, 
                pondNueva.idSerie, pondNueva.valor, sqlExtra);
    }
    
    //Función para elivalorar Ponderaciones que se ajustan a los limites pasados
    public static int deletePonderaciones(Integer idSerie, Double valor, String sqlExtra) throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        int res = 0;

        condicion = getCondicion(idSerie, valor, paramsPS);
        
        interBD.inicioTransaccion();
        res  = interBD.deleteDatosTabla(TABLA, condicion, paramsPS, sqlExtra);
        interBD.finTransaccion();
        
        return res;
    }
    
    public static int deletePonderaciones(Ponderacion ponderacion, String sqlExtra) throws SQLException {
        return deletePonderaciones(ponderacion.idSerie, ponderacion.valor, sqlExtra);
    }
    
    public Object[] toObject() {
        return new Object[]{this.idSerie, this.valor};
    }
  
    public static Object[] getCamposTabla() throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        return interBD.getCamposTabla(TABLA);
    }
} 