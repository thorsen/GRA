package RA;

import general.InteraccionBD;
import java.sql.SQLException;
import java.util.ArrayList;

public class Interpolacion {
    private Integer idAsunto;
    private Double pNominal;
    private Double vCorte;
    
    public static final String BD = InteraccionBD.PREF_BD_GENERAL;
    public static final String TABLA = BD + "Interpolacion";
    public static final String CAMPO_ID_ASUNTO = "Asunto";
    public static final String CAMPO_P_NOMINAL = "Pnominal";
    public static final String CAMPO_V_CORTE = "Vcorte";

    public Interpolacion(Integer idAsunto, Double pNominal, Double vCorte) {
        this.idAsunto = idAsunto;
        this.pNominal = pNominal;
        this.vCorte = vCorte;
    }
    
    public Interpolacion(Object[] valores, ArrayList<String> campos) throws SQLException, NoSuchFieldException {
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

    public Double getPNominal() {
        return pNominal;
    }

    public void setPNominal(Double pNominal) {
        this.pNominal = pNominal;
    }

    public Double getVCorte() {
        return vCorte;
    }

    public void setVCorte(Double vCorte) {
        this.vCorte = vCorte;
    }
    
    //Función para asignar valores a campos de la clase según el campo de la BD
    private void asignaCampo(String campo, Object valor) throws NoSuchFieldException {
        if (campo.compareTo(CAMPO_ID_ASUNTO) == 0) {
            this.idAsunto = (Integer) valor;
        } else if (campo.compareTo(CAMPO_P_NOMINAL) == 0) {
            this.pNominal = (Double) valor;
        } else if (campo.compareTo(CAMPO_V_CORTE) == 0) {
            this.vCorte = (Double) valor;
        } else {
            throw new NoSuchFieldException("No existe el campo en la clase " + this.getClass().getSimpleName());
        }
    }
    
    private static String getCondicion(Integer idAsunto, Double pNominal, Double vCorte, ArrayList<Object[]> paramsPS) {
        String condicion = "";
        
        if (idAsunto != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_ID_ASUNTO, "=", idAsunto);
        }
        if (pNominal != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_P_NOMINAL, "=", pNominal);
        }
        if (vCorte != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_V_CORTE, "=", vCorte);
        }
        
        return condicion;
    }

    //Función para obtener la colección de Interpolaciones que se ajustan a los limites pasados
    public static ArrayList<Interpolacion> getInterpolaciones(Integer idAsunto, Double pNominal, Double vCorte,
            ArrayList<String> campos, String sqlExtra, Boolean distinct) throws SQLException, NoSuchFieldException {
        InteraccionBD interBD = new InteraccionBD();
        
        ArrayList<Interpolacion> res = null;
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();

        condicion = getCondicion(idAsunto, pNominal, vCorte, paramsPS);
        
        //Por defecto lo devolvemos ordenado por asunto
        if (sqlExtra == null || sqlExtra.trim().length() == 0) {
            String sqlOrderBy = InteraccionBD.anadeCampoOrderBy(null, CAMPO_ID_ASUNTO);
            sqlExtra = InteraccionBD.anadeSqlExtra(null, sqlOrderBy);
        }

        ArrayList<Object[]> resAux = interBD.getDatosTabla(TABLA, campos, condicion, paramsPS, sqlExtra, distinct);

        if (resAux != null) {
            int n = resAux.size();
            res = new ArrayList<Interpolacion>();
            
            for (int i = 0; i < n; i++) {
               res.add(new Interpolacion(resAux.get(i), campos));
            }
        }

        return res;
    }

    //Función para añadir una interpolacion a la BD
    public static int insertInterpolacion(Integer idAsunto, Double pNominal, Double vCorte, String sqlExtra) throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        String valores = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        ArrayList<String> campos = new ArrayList<String>();
        int res = 0;
        
        if (idAsunto != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, idAsunto);
            campos.add(CAMPO_ID_ASUNTO);
        }
        if (pNominal != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, pNominal);
            campos.add(CAMPO_P_NOMINAL);
        }
        if (vCorte != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, vCorte);
            campos.add(CAMPO_V_CORTE);
        }
        
        interBD.inicioTransaccion();
        res = interBD.insertDatosTabla(TABLA, campos, valores, paramsPS, sqlExtra);
        interBD.finTransaccion();
        
        return res;
    }
    
    public static int insertInterpolacion(Interpolacion interpolacion, String sqlExtra) throws SQLException {
        return insertInterpolacion(interpolacion.idAsunto, interpolacion.pNominal, interpolacion.vCorte, sqlExtra);
    }
    
    //Función para añadir una interpolacion a la BD
    public static int updateInterpolacion(Integer idAsunto, Double pNominal, Double vCorte,
            Integer idAsuntoVal, Double pNominalVal, Double vCorteVal, String sqlExtra) throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        ArrayList<String> campos = new ArrayList<String>();
        int res = 0;
        
        if (idAsuntoVal != null && !idAsuntoVal.equals(idAsunto)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, idAsuntoVal);
            campos.add(CAMPO_ID_ASUNTO);
        }
        if (pNominalVal != null && !pNominalVal.equals(pNominal)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, pNominalVal);
            campos.add(CAMPO_P_NOMINAL);        
        }
        if (vCorteVal != null && !vCorteVal.equals(vCorte)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, vCorteVal);
            campos.add(CAMPO_V_CORTE);        
        }

        //Condiciones de actualización
        condicion = getCondicion(idAsunto, pNominal, vCorte, paramsPS);
        
        interBD.inicioTransaccion();
        res = interBD.updateDatosTabla(TABLA, campos, condicion, paramsPS, sqlExtra);
        interBD.finTransaccion();
        
        return res;
    }
    
    public static int updateInterpolacion(Interpolacion interVieja, Interpolacion interNueva, String sqlExtra) throws SQLException {
        return updateInterpolacion(interVieja.idAsunto, interVieja.pNominal, interVieja.vCorte, 
                interNueva.idAsunto, interNueva.pNominal, interNueva.vCorte, sqlExtra);
    }
    
    //Función para añadir/modificar una interpolacion a la BD
    public static int insertOrUpdateInterpolacion(Integer idAsunto, Double pNominal, Double vCorte, 
            Integer idAsuntoVal, Double pNominalVal, Double vCorteVal, String sqlExtra) throws SQLException {
        int res;
        
        res = updateInterpolacion(idAsunto, pNominal, vCorte, idAsuntoVal, pNominalVal, vCorteVal, sqlExtra);
        
        if (res < 0)
            res = insertInterpolacion(idAsuntoVal, pNominalVal, vCorteVal, sqlExtra);

        return res;
    }
    
    public static int insertOrUpdateInterpolacion(Interpolacion interVieja, Interpolacion interNueva, String sqlExtra) throws SQLException {
        
        return insertOrUpdateInterpolacion(interVieja.idAsunto, interVieja.pNominal, interVieja.vCorte,
                interNueva.idAsunto, interNueva.pNominal, interNueva.vCorte, sqlExtra);
    }
    
    //Función para eliminar Interpolaciones que se ajustan a los limites pasados
    public static int deleteInterpolaciones(Integer idAsunto, Double pNominal, Double vCorte, String sqlExtra) throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        int res = 0;

        condicion = getCondicion(idAsunto, pNominal, vCorte, paramsPS);

        interBD.inicioTransaccion();
        res  = interBD.deleteDatosTabla(TABLA, condicion, paramsPS, sqlExtra);
        interBD.finTransaccion();
        
        return res;
    }
    
    public static int deleteInterpolaciones(Interpolacion interpolacion, String sqlExtra) throws SQLException {
        return deleteInterpolaciones(interpolacion.idAsunto, interpolacion.pNominal, interpolacion.vCorte, sqlExtra);
    }
    
    public Object[] toObject() {
        return new Object[]{this.idAsunto, this.pNominal, this.vCorte};
    }
  
    public static Object[] getCamposTabla() throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        return interBD.getCamposTabla(TABLA);
    }
} 