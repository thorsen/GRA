package RA;

import general.InteraccionBD;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;

public class CurvaExtraRA {
    private Integer idAsunto;
    private Double densidad;
    private Double vel;
    private Double incert;
    
    public static final String BD = InteraccionBD.PREF_BD_RA;
    public static final String TABLA = BD + "CurvaExtraRA";
    public static final String CAMPO_ID_ASUNTO = TABLA + "." + "Asunto";
    public static final String CAMPO_DENSIDAD = TABLA + "." + "Densidad";
    public static final String CAMPO_VEL = TABLA + "." + "V";
    public static final String CAMPO_INCERT = TABLA + "." + "Incert";

    public CurvaExtraRA(Integer idAsunto, Double densidad, Double vel, Double incert) {
        this.idAsunto = idAsunto;
        this.densidad = densidad;
        this.vel = vel;
        this.incert = incert;
    }
    
    public CurvaExtraRA(Object[] valores, ArrayList<String> campos) throws SQLException, NoSuchFieldException {
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
    
    public Double getDensidad() {
        return densidad;
    }

    public void setDensidad(Double densidad) {
        this.densidad = densidad;
    }

    public Integer getIdAsunto() {
        return idAsunto;
    }

    public void setIdAsunto(Integer idAsunto) {
        this.idAsunto = idAsunto;
    }

    public Double getIncert() {
        return incert;
    }

    public void setIncert(Double incert) {
        this.incert = incert;
    }

	public Double getVel() {
        return vel;
    }

    public void setVel(Double vel) {
        this.vel = vel;
    }

    //Función para asignar valores a campos de la clase según el campo de la BD
    private void asignaCampo(String campo, Object valor) throws NoSuchFieldException {
        if (campo.compareTo(CAMPO_ID_ASUNTO) == 0) {
            this.idAsunto = (Integer) valor;
		} else if (campo.compareTo(CAMPO_DENSIDAD) == 0) {
            this.densidad = (Double) valor;
		} else if (campo.compareTo(CAMPO_VEL) == 0) {
            this.vel = (Double) valor;
        } else if (campo.compareTo(CAMPO_INCERT) == 0) {
            if (valor != null)
                this.incert = ((BigDecimal) valor).doubleValue();
            else
                this.incert = null;
        } else {
            //throw new NoSuchFieldException("No existe el campo en la clase " + this.getClass().getSimpleName());
			System.out.println("No existe el campo <" + campo + "> en la clase " + this.getClass().getSimpleName());
        }
    }
    
    private static String getCondicion(Integer idAsunto, Double densidad, Double vel, Double incert, ArrayList<Object[]> paramsPS) {
        String condicion = "";
        
        if (idAsunto != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_ID_ASUNTO, "=", idAsunto);
        }
        if (densidad != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_DENSIDAD, "=", densidad);
        }
        if (vel != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_VEL, "=", vel);
        }
        if (incert != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_INCERT, "=", incert);
        }
        
        return condicion;
    }

    //Función para obtener la colección de CurvaExtras que se ajustan a los limites pasados
    public static ArrayList<CurvaExtraRA> getCurvas(Integer idAsunto, Double densidad, Double vel, Double incert, ArrayList<String> campos, String sqlExtra, Boolean distinct) throws SQLException, NoSuchFieldException {
        InteraccionBD interBD = new InteraccionBD();
        
        ArrayList<CurvaExtraRA> res = null;
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();

        condicion = getCondicion(idAsunto, densidad, vel, incert, paramsPS);

		if (campos == null) {
			Object[] camposCurva = interBD.getCamposTabla(TABLA);

			campos = new ArrayList<String>();

			int nCamposCurva = camposCurva.length;
			for (int i = 0; i < nCamposCurva; i++) {
				campos.add(TABLA + "." + (String) camposCurva[i]);
			}
		}
        
        //Por defecto lo devolvemos ordenado por curva
        if (sqlExtra == null || sqlExtra.trim().length() == 0) {
            String sqlOrderBy = InteraccionBD.anadeCampoOrderBy(null, CAMPO_ID_ASUNTO);
            sqlOrderBy = InteraccionBD.anadeCampoOrderBy(sqlOrderBy, CAMPO_DENSIDAD);
            sqlOrderBy = InteraccionBD.anadeCampoOrderBy(sqlOrderBy, CAMPO_VEL);
            sqlExtra = InteraccionBD.anadeSqlExtra(null, sqlOrderBy);
        }

        ArrayList<Object[]> resAux = interBD.getDatosTabla(TABLA, campos, condicion, paramsPS, sqlExtra, distinct);

        if (resAux != null) {
            int n = resAux.size();
            res = new ArrayList<CurvaExtraRA>();
            
            for (int i = 0; i < n; i++) {
               res.add(new CurvaExtraRA(resAux.get(i), campos));
            }
        }

        return res;
    }
    
    //Función para añadir una curva a la BD
    public static int insertCurvaExtra(Integer idAsunto, Double densidad, Double vel, Double incert, String sqlExtra) throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        String valores = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        ArrayList<String> campos = new ArrayList<String>();
        ArrayList<String> autoInc = new ArrayList<String>();
        ArrayList<String> condAutoInc = new ArrayList<String>();
        int res = 0;
        
        if (idAsunto != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, idAsunto);
            campos.add(CAMPO_ID_ASUNTO);
        }
        if (densidad != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, densidad);
            campos.add(CAMPO_DENSIDAD);
        }
        if (vel != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, vel);
            campos.add(CAMPO_VEL);
        }
        if (incert != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, incert);
            campos.add(CAMPO_INCERT);
        }

        interBD.inicioTransaccion();
        res = interBD.insertDatosTabla(TABLA, campos, valores, paramsPS, sqlExtra, autoInc, condAutoInc);
        interBD.finTransaccion();
        
        return res;
    }
    
    public static int insertCurvaExtra(CurvaExtraRA curva, String sqlExtra) throws SQLException {
        return insertCurvaExtra(curva.idAsunto, curva.densidad, curva.vel, curva.incert, sqlExtra);
    }
    
    //Función para añadir una curva a la BD
    public static int updateCurvaExtra(Integer idAsunto, Double densidad, Double vel, Double incert, Integer idAsuntoVal, Double densidadVal, Double velVal, Double incertVal, String sqlExtra) throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        ArrayList<String> campos = new ArrayList<String>();
        int res = 0;
        
        if (idAsuntoVal != null && !idAsuntoVal.equals(idAsunto)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, idAsuntoVal);
            campos.add(CAMPO_ID_ASUNTO);
        }
        if (densidadVal != null && !densidadVal.equals(densidad)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, densidadVal);
            campos.add(CAMPO_DENSIDAD);
        }
        if (velVal != null && !velVal.equals(vel)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, velVal);
            campos.add(CAMPO_VEL);
        }
        if (incertVal != null && !incertVal.equals(incert)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, incertVal);
            campos.add(CAMPO_INCERT);
        }
        
        //Condiciones de actualización
        condicion = getCondicion(idAsunto, densidad, vel, incert, paramsPS);
        
        interBD.inicioTransaccion();
        res = interBD.updateDatosTabla(TABLA, campos, condicion, paramsPS, sqlExtra);
        interBD.finTransaccion();
        
        return res;
    }
    
    public static int updateCurvaExtra(CurvaExtraRA curvaVieja, CurvaExtraRA curvaNueva, String sqlExtra) throws SQLException {
        return updateCurvaExtra(curvaVieja.idAsunto, curvaVieja.densidad, curvaVieja.vel, curvaVieja.incert, curvaNueva.idAsunto, curvaNueva.densidad, curvaNueva.vel, curvaNueva.incert, sqlExtra);
    }
    
    //Función para añadir/modificar una curva a la BD
    public static int insertOrUpdateCurvaExtra(Integer idAsunto, Double densidad, Double vel, Double incert, Integer idAsuntoVal, Double densidadVal, Double velVal, Double incertVal, String sqlExtra) throws SQLException {
        int res;
        
        res = updateCurvaExtra(idAsunto, densidad, vel, incert, idAsuntoVal, densidadVal, velVal, incertVal, sqlExtra);
        
        if (res < 0)
            res = insertCurvaExtra(idAsuntoVal, densidadVal, velVal, incertVal, sqlExtra);

        return res;
    }
    
    public static int insertOrUpdateCurvaExtra(CurvaExtraRA curvaVieja, CurvaExtraRA curvaNueva, String sqlExtra) throws SQLException {
        
        return insertOrUpdateCurvaExtra(curvaVieja.idAsunto, curvaVieja.densidad, curvaVieja.vel, curvaVieja.incert, curvaNueva.idAsunto, curvaNueva.densidad, curvaNueva.vel, curvaNueva.incert, sqlExtra);
    }
    
    //Función para eliminar CurvaExtras que se ajustan a los limites pasados
    public static int deleteCurvaExtras(Integer idAsunto, Double densidad, Double vel, Double incert, String sqlExtra) throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        int res = 0;

        condicion = getCondicion(idAsunto, densidad, vel, incert, paramsPS);

        interBD.inicioTransaccion();
        res  = interBD.deleteDatosTabla(TABLA, condicion, paramsPS, sqlExtra);
        interBD.finTransaccion();
        
        return res;
    }
    
    public static int deleteCurvaExtras(CurvaExtraRA curva, String sqlExtra) throws SQLException {
        return deleteCurvaExtras(curva.idAsunto, curva.densidad, curva.vel, curva.incert, sqlExtra);
    }
    
    public Object[] toObject() {
        return new Object[]{this.idAsunto, this.incert, this.vel, this.densidad};
    }
  
    public static Object[] getCamposTabla() throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        return interBD.getCamposTabla(TABLA);
    }
} 