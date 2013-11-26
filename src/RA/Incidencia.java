package RA;

import general.InteraccionBD;
import general.TratFechas;
import java.sql.SQLException;
import java.util.ArrayList;

public class Incidencia {

    private Integer idAsunto;
    private Integer idSite;
    private Long fechaD;
    private Long fechaH;
    private String observacion;
    
    public static final String TABLA = "IncidenciasRA";
    public static final String CAMPO_ID_ASUNTO = "Id_Asunto";
    public static final String CAMPO_ID_SITE = "Id_Site";
    public static final String CAMPO_FECHA_D = "Fecha_Desde";
    public static final String CAMPO_FECHA_H = "Fecha_Hasta";
    public static final String CAMPO_OBS = "Observacion";
    

    public Incidencia(Integer idAsunto, Integer idSite, Long fechaD, Long fechaH, String observacion) {
        this.idAsunto = idAsunto;
        this.idSite = idSite;
        this.fechaD = fechaD;
        this.fechaH = fechaH;
        this.observacion = observacion;
    }
    
    public Incidencia(Object[] valores, ArrayList<String> campos) throws SQLException, NoSuchFieldException {
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

    public Integer getIdSite() {
        return idSite;
    }

    public void setIdSite(Integer idSite) {
        this.idSite = idSite;
    }

    public Long getFechaD() {
        return fechaD;
    }

    public void setFechaD(Long fechaD) {
        this.fechaD = fechaD;
    }

    public Long getFechaH() {
        return fechaH;
    }

    public void setFechaH(Long fechaH) {
        this.fechaH = fechaH;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
    
    //Función para asignar valores a campos de la clase según el campo de la BD
    private void asignaCampo(String campo, Object valor) throws NoSuchFieldException {
        if (campo.compareTo(CAMPO_ID_ASUNTO) == 0) {
            this.idAsunto = (Integer) valor;
        } else if (campo.compareTo(CAMPO_ID_SITE) == 0) {
            this.idSite = (Integer) valor;
        } else if (campo.compareTo(CAMPO_FECHA_D) == 0) {
            this.fechaD = (Long) valor;
        } else if (campo.compareTo(CAMPO_FECHA_H) == 0) {
            this.fechaH = (Long) valor;
        } else if (campo.compareTo(CAMPO_OBS) == 0) {
            this.observacion = (String) valor;
        } else {
            throw new NoSuchFieldException("No existe el campo <" + campo + "> en la clase " + this.getClass().getSimpleName());
        }
    }

    private static String getCondicion(Integer idAsunto, Integer idSite, Long fechaD, Long fechaH, ArrayList<Object[]> paramsPS) {
        String condicion = "";
        
        if (idAsunto != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_ID_ASUNTO, "=", idAsunto);
        }
        if (idSite != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_ID_SITE, "=", idSite);
        }
        if (fechaD != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_FECHA_D, ">=", fechaD);
        }
        if (fechaH != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_FECHA_H, "<=", fechaH);
        }
        
        return condicion;
    }
    
    //Función para obtener la colección de Incidencias que se ajustan a los limites pasados
    public static ArrayList<Incidencia> getIncidencias(Integer idAsunto, Integer idSite, Long fechaD, Long fechaH, 
            ArrayList<String> campos, String sqlExtra, Boolean distinct) throws SQLException, NoSuchFieldException {
        InteraccionBD interBD = new InteraccionBD();
        
        ArrayList<Incidencia> res = null;
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();

        condicion = getCondicion(idAsunto, idSite, fechaD, fechaH, paramsPS);
        
        //Por defecto lo devolvemos ordenado por Asunto,Site
        if (sqlExtra == null || sqlExtra.trim().length() == 0) {
            String sqlOrderBy = InteraccionBD.anadeCampoOrderBy(null, CAMPO_ID_ASUNTO);
            sqlOrderBy = InteraccionBD.anadeCampoOrderBy(sqlOrderBy, CAMPO_ID_SITE);
            sqlExtra = InteraccionBD.anadeSqlExtra(null, sqlOrderBy);
        }

        ArrayList<Object[]> resAux = interBD.getDatosTabla(TABLA, campos, condicion, paramsPS, sqlExtra, distinct);

        if (resAux != null) {
            int n = resAux.size();
            res = new ArrayList<Incidencia>();
            
            for (int i = 0; i < n; i++) {
               res.add(new Incidencia(resAux.get(i), campos));
            }
        }

        return res;
    }

    //Función para añadir una incidencia a la BD
    public static int insertIncidencia(Integer idAsunto, Integer idSite, Long fechaD, Long fechaH, String observacion, String sqlExtra) throws SQLException {
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
        if (fechaD != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, fechaD);
            campos.add(CAMPO_FECHA_D);
        }
        if (fechaH != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, fechaH);
            campos.add(CAMPO_FECHA_H);
        }
        if (observacion != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, observacion);
            campos.add(CAMPO_OBS);
        }
        
        interBD.inicioTransaccion();
        res = interBD.insertDatosTabla(TABLA, campos, valores, paramsPS, sqlExtra);
        interBD.finTransaccion();
        
        return res;
    }
    
    public static int insertIncidencia(Incidencia incidencia, String sqlExtra) throws SQLException {
        return insertIncidencia(incidencia.idAsunto, incidencia.idSite, incidencia.fechaD, incidencia.fechaH, incidencia.observacion, sqlExtra);
    }
    
    //Función para añadir una incidencia a la BD
    public static int updateIncidencia(Integer idAsunto, Integer idSite, Long fechaD, Long fechaH,
            Integer idAsuntoVal, Integer idSiteVal, Long fechaDVal, Long fechaHVal, String observacionVal, String sqlExtra) throws SQLException {
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
        if (fechaDVal != null && !fechaDVal.equals(fechaD)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, fechaDVal);
            campos.add(CAMPO_FECHA_D);        
        }
        if (fechaHVal != null && !fechaHVal.equals(fechaH)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, fechaHVal);
            campos.add(CAMPO_FECHA_H);        
        }
        if (observacionVal != null) {
            InteraccionBD.anadeCampoValor(null, paramsPS, observacionVal);
            campos.add(CAMPO_OBS);        
        }

        //Condiciones de actualización
        condicion = getCondicion(idAsunto, idSite, fechaD, fechaH, paramsPS);
        
        interBD.inicioTransaccion();
        res = interBD.updateDatosTabla(TABLA, campos, condicion, paramsPS, sqlExtra);
        interBD.finTransaccion();
        
        return res;
    }
    
    public static int updateIncidencia(Incidencia inciVieja, Incidencia inciNueva, String sqlExtra) throws SQLException {
        return updateIncidencia(inciVieja.idAsunto, inciVieja.idSite, inciVieja.fechaD, inciVieja.fechaH, 
                inciNueva.idAsunto, inciNueva.idSite, inciNueva.fechaD, inciNueva.fechaH, inciNueva.observacion, sqlExtra);
    }
    
    //Función para añadir/modificar una incidencia a la BD
    public static int insertOrUpdateIncidencia(Integer idAsunto, Integer idSite, Long fechaD, Long fechaH, 
            Integer idAsuntoVal, Integer idSiteVal, Long fechaDVal, Long fechaHVal, String observacionVal, String sqlExtra) throws SQLException {
        int res;
        
        res = updateIncidencia(idAsunto, idSite, fechaD, fechaH, idAsuntoVal, idSiteVal, fechaDVal, fechaHVal, observacionVal, sqlExtra);
        
        if (res < 0)
            res = insertIncidencia(idAsuntoVal, idSiteVal, fechaDVal, fechaHVal, observacionVal, sqlExtra);

        return res;
    }
    
    public static int insertOrUpdateIncidencia(Incidencia inciVieja, Incidencia inciNueva, String sqlExtra) throws SQLException {
        
        return insertOrUpdateIncidencia(inciVieja.idAsunto, inciVieja.idSite, inciVieja.fechaD, inciVieja.fechaH,
                inciNueva.idAsunto, inciNueva.idSite, inciNueva.fechaD, inciNueva.fechaH, inciNueva.observacion, sqlExtra);
    }
    
    //Función para eliminar Incidencias que se ajustan a los limites pasados
    public static int deleteIncidencias(Integer idAsunto, Integer idSite, Long fechaD, Long fechaH, String sqlExtra) throws SQLException, NoSuchFieldException {
        InteraccionBD interBD = new InteraccionBD();
        
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        int res = 0;

        condicion = getCondicion(idAsunto, idSite, fechaD, fechaH, paramsPS);

        interBD.inicioTransaccion();
        //Validamos los datos que haya podido invalidar la incidencia
        DatosRA2.setTabla(TipoRA.getTipoRAPorIdSite(idSite).getSufijo(), idAsunto);
        DatosRA2.updateDatosIncidencias(DatosRA2.getTabla(), idAsunto, idSite, 1);
        
        res  = interBD.deleteDatosTabla(TABLA, condicion, paramsPS, sqlExtra);
        interBD.finTransaccion();
        
        return res;
    }
    
    public static int deleteIncidencias(Incidencia incidencia, String sqlExtra) throws SQLException, NoSuchFieldException {
        return deleteIncidencias(incidencia.idAsunto, incidencia.idSite, incidencia.fechaD, incidencia.fechaH, sqlExtra);
    }
    
    public Object[] toObject() {
        return new Object[]{this.idAsunto, this.idSite, TratFechas.toStringFecha(fechaD), TratFechas.toStringFecha(this.fechaH), this.observacion};
    }
  
    public static Object[] getCamposTabla() throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        return interBD.getCamposTabla(TABLA);
    }
} 