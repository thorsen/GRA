package RA;

import general.InteraccionBD;
import java.sql.SQLException;
import java.util.ArrayList;

public class Declinacion {
    private Integer idAsunto;
    private Integer idSite;
    private Integer idConfig;
    private Double angulo;
    private Character posicion;
    
    public static final String BD = InteraccionBD.PREF_BD_RA;
    public static final String TABLA = BD + "DeclinacionRA";
    public static final String CAMPO_ID_ASUNTO = "Asunto";
    public static final String CAMPO_ID_SITE = "Site";
    public static final String CAMPO_ID_CONFIG = "NConfig";
    public static final String CAMPO_ANGULO = "Angulo";
    public static final String CAMPO_POSICION = "Posicion";
    
    public static final Character CHAR_OESTE = 'W';
    public static final Character CHAR_ESTE = 'E';

    public Declinacion(Integer idAsunto, Integer idSite, Integer idConfig, Double angulo, Character posicion) {
        this.idAsunto = idAsunto;
        this.idSite = idSite;
        this.idConfig = idConfig;
        this.angulo = angulo;
        this.posicion = posicion;
    }
    
    public Declinacion(Object[] valores, ArrayList<String> campos) throws SQLException, NoSuchFieldException {
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
    
    public Character getPosicion() {
        return posicion;
    }

    public void setPosicion(Character posicion) {
        this.posicion = posicion;
    }

    public Double getAngulo() {
        return angulo;
    }

    public void setAngulo(Double angulo) {
        this.angulo = angulo;
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
    
    public Double getDeclinacion() {
        Double res = null;
        
        if (this.angulo != null && this.posicion != null) {
            res = angulo;
            
            if (this.posicion.equals(CHAR_OESTE))
                res *= -1;
        }
        
        return res;
    }
    
    //Función para asignar valores a campos de la clase según el campo de la BD
    private void asignaCampo(String campo, Object valor) throws NoSuchFieldException {
        if (campo.compareTo(CAMPO_ID_ASUNTO) == 0) {
            this.idAsunto = (Integer) valor;
        } else if (campo.compareTo(CAMPO_ID_SITE) == 0) {
            this.idSite = (Integer) valor;
        } else if (campo.compareTo(CAMPO_ID_CONFIG) == 0) {
            this.idConfig = (Integer) valor;
        } else if (campo.compareTo(CAMPO_ANGULO) == 0) {
            this.angulo = (Double) valor;
        } else if (campo.compareTo(CAMPO_POSICION) == 0) {
            if (valor != null && ((String) valor).length() > 0)
                this.posicion = ((String) valor).charAt(0);
            else
                this.posicion = ' ';
        } else {
            throw new NoSuchFieldException("No existe el campo en la clase " + this.getClass().getSimpleName());
        }
    }
    
    private static String getCondicion(Integer idAsunto, Integer idSite, Integer idConfig, Double angulo, Character posicion, ArrayList<Object[]> paramsPS) {
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
        if (angulo != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_ANGULO, "=", angulo);
        }
        if (posicion != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_POSICION, "=", posicion);
        }
        
        return condicion;
    }

    //Función para obtener la colección de Declinaciones.que se ajustan a los limites pasados
    public static ArrayList<Declinacion> getDeclinaciones(Integer idAsunto, Integer idSite, Integer idConfig, Double angulo, Character posicion, 
            ArrayList<String> campos, String sqlExtra, Boolean distinct) throws SQLException, NoSuchFieldException {
        InteraccionBD interBD = new InteraccionBD();
        
        ArrayList<Declinacion> res = null;
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();

        condicion = getCondicion(idAsunto, idSite, idConfig, angulo, posicion, paramsPS);
        
        //Por defecto lo devolvemos ordenado por asunto site, config
        if (sqlExtra == null || sqlExtra.trim().length() == 0) {
            String sqlOrderBy = InteraccionBD.anadeCampoOrderBy(null, CAMPO_ID_ASUNTO);
            sqlOrderBy = InteraccionBD.anadeCampoOrderBy(sqlOrderBy, CAMPO_ID_SITE);
            sqlOrderBy = InteraccionBD.anadeCampoOrderBy(sqlOrderBy, CAMPO_ID_CONFIG);
            sqlExtra = InteraccionBD.anadeSqlExtra(null, sqlOrderBy);
        }

        ArrayList<Object[]> resAux = interBD.getDatosTabla(TABLA, campos, condicion, paramsPS, sqlExtra, distinct);

        if (resAux != null) {
            int n = resAux.size();
            res = new ArrayList<Declinacion>();
            
            for (int i = 0; i < n; i++) {
               res.add(new Declinacion(resAux.get(i), campos));
            }
        }

        return res;
    }
    
   //Función para obtener la colección de Declinaciones.que se ajustan a los limites pasados
    public static Declinacion getDeclinacionPorAsuntoSiteConf(Integer idAsunto, Integer idSite, Integer idConfig) throws SQLException, NoSuchFieldException {
        Declinacion res = null;
        ArrayList<Declinacion> resAux = getDeclinaciones(idAsunto, idSite, idConfig, null, null, null, null, null);

        if (resAux != null && resAux.size() > 0) {
            res = resAux.get(0);
        }
        return res;
    }

    //Función para añadir una declinacion.a la BD
    public static int insertDeclinacion(Integer idAsunto, Integer idSite, Integer idConfig, Double angulo, Character posicion, String sqlExtra) throws SQLException {
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
        if (angulo != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, angulo);
            campos.add(CAMPO_ANGULO);
        }
        if (posicion != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, posicion);
            campos.add(CAMPO_POSICION);
        }
        
        interBD.inicioTransaccion();
        res = interBD.insertDatosTabla(TABLA, campos, valores, paramsPS, sqlExtra);
        interBD.finTransaccion();
        
        return res;
    }
    
    public static int insertDeclinacion(Declinacion declinacion, String sqlExtra) throws SQLException {
        return insertDeclinacion(declinacion.idAsunto, declinacion.idSite, declinacion.idConfig, declinacion.angulo, declinacion.posicion, sqlExtra);
    }
    
    //Función para añadir una declinacion.a la BD
    public static int updateDeclinacion(Integer idAsunto, Integer idSite, Integer idConfig, Double angulo, Character posicion,
            Integer idAsuntoVal, Integer idSiteVal, Integer idConfigVal, Double anguloVal, Character posicionVal, String sqlExtra) throws SQLException {
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
        if (anguloVal != null && !anguloVal.equals(angulo)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, anguloVal);
            campos.add(CAMPO_ANGULO);
        }
        if (posicionVal != null && !posicionVal.equals(posicion)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, posicionVal);
            campos.add(CAMPO_POSICION);
        }

        //Condiciones de actualización
        condicion = getCondicion(idAsunto, idSite, idConfig, angulo, posicion, paramsPS);
        
        interBD.inicioTransaccion();
        res = interBD.updateDatosTabla(TABLA, campos, condicion, paramsPS, sqlExtra);
        interBD.finTransaccion();
        
        return res;
    }
    
    public static int updateDeclinacion(Declinacion decliVieja, Declinacion decliNueva, String sqlExtra) throws SQLException {
        return updateDeclinacion(decliVieja.idAsunto, decliVieja.idSite, decliVieja.idConfig, decliVieja.angulo, decliVieja.posicion, 
                decliNueva.idAsunto, decliNueva.idSite, decliNueva.idConfig, decliNueva.angulo, decliNueva.posicion, sqlExtra);
    }
    
    //Función para añadir/modificar una Declinacion a la BD
    public static int insertOrUpdateDeclinacion(Integer idAsunto, Integer idSite, Integer idConfig, Double angulo, Character posicion,
            Integer idAsuntoVal, Integer idSiteVal, Integer idConfigVal, Double anguloVal, Character posicionVal, String sqlExtra) throws SQLException {
        int res;
        
        res = updateDeclinacion(idAsunto, idSite, idConfig, angulo, posicion,
            idAsuntoVal, idSiteVal, idConfigVal, anguloVal, posicionVal, sqlExtra);
        
        if (res < 0)
            res = insertDeclinacion(idAsuntoVal, idSiteVal, idConfigVal, anguloVal, posicionVal, sqlExtra);

        return res;
    }
    
    public static int insertOrUpdateDeclinacion(Declinacion decliVieja, Declinacion decliNueva, String sqlExtra) throws SQLException {
        
        return insertOrUpdateDeclinacion(decliVieja.idAsunto, decliVieja.idSite, decliVieja.idConfig, decliVieja.angulo, decliVieja.posicion, 
                decliNueva.idAsunto, decliNueva.idSite, decliNueva.idConfig, decliNueva.angulo, decliNueva.posicion, sqlExtra);
    }
    
    //Función para eliminar Declinaciones.que se ajustan a los limites pasados
    public static int deleteDeclinaciones(Integer idAsunto, Integer idSite, Integer idConfig, Double angulo, Character posicion, String sqlExtra) throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        int res = 0;

        condicion = getCondicion(idAsunto, idSite, idConfig, angulo, posicion, paramsPS);

        interBD.inicioTransaccion();
        res  = interBD.deleteDatosTabla(TABLA, condicion, paramsPS, sqlExtra);
        interBD.finTransaccion();
        
        return res;
    }
    
    public static int deleteDeclinaciones(Declinacion declinacion, String sqlExtra) throws SQLException {
        return deleteDeclinaciones(declinacion.idAsunto, declinacion.idSite, declinacion.idConfig, declinacion.angulo, declinacion.posicion, sqlExtra);
    }
    
    public Object[] toObject() {
        return new Object[]{this.idAsunto, this.idSite, this.idConfig, this.angulo, this.posicion};
    }
  
    public static Object[] getCamposTabla() throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        return interBD.getCamposTabla(TABLA);
    }
} 