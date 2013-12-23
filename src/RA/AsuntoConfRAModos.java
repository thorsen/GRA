package RA;

import general.InteraccionBD;
import java.sql.SQLException;
import java.util.ArrayList;

public class AsuntoConfRAModos {
    private Integer idAsunto;
    private Integer desdeVel;
    private Integer hastaVel;
    
    public static final String BD = InteraccionBD.PREF_BD_RA;
    public static final String TABLA = BD + "AsuntoConfRAModos";
    public static final String CAMPO_ID_ASUNTO = "Id_asunto";
    public static final String CAMPO_DESDE_VEL = "ModoFuncDesde";
    public static final String CAMPO_HASTA_VEL = "ModoFuncHasta";

    public AsuntoConfRAModos(Integer idAsunto, Integer desdeVel, Integer hastaVel) {
        this.idAsunto = idAsunto;
        this.desdeVel = desdeVel;
        this.hastaVel = hastaVel;
    }
    
    public AsuntoConfRAModos(Object[] valores, ArrayList<String> campos) throws SQLException, NoSuchFieldException {
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
    
    public Integer getDesdeVel() {
        return desdeVel;
    }

    public void setDesdeVel(Integer desdeVel) {
        this.desdeVel = desdeVel;
    }

    public Integer getHastaVel() {
        return hastaVel;
    }

    public void setHastaVel(Integer hastaVel) {
        this.hastaVel = hastaVel;
    }

    public Integer getIdAsunto() {
        return idAsunto;
    }

    public void setIdAsunto(Integer idAsunto) {
        this.idAsunto = idAsunto;
    }

    //Función para asignar valores a campos de la clase según el campo de la BD
    private void asignaCampo(String campo, Object valor) throws NoSuchFieldException {
        if (campo.compareTo(CAMPO_ID_ASUNTO) == 0) {
            this.idAsunto = (Integer) valor;
        } else if (campo.compareTo(CAMPO_DESDE_VEL) == 0) {
            this.desdeVel = (Integer) valor;
        } else if (campo.compareTo(CAMPO_HASTA_VEL) == 0) {
            this.hastaVel = (Integer) valor;
        } else {
            //throw new NoSuchFieldException("No existe el campo en la clase " + this.getClass().getSimpleName());
			System.out.println("No existe el campo <" + campo + "> en la clase " + this.getClass().getSimpleName());
        }
    }
    
    private static String getCondicion(Integer idAsunto, Integer desdeVel, Integer hastaVel, ArrayList<Object[]> paramsPS) {
        String condicion = "";
        
        if (idAsunto != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_ID_ASUNTO, "=", idAsunto);
        }
        if (desdeVel != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_DESDE_VEL, "=", desdeVel);
        }
        if (hastaVel != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_HASTA_VEL, "=", hastaVel);
        }
        
        return condicion;
    }

    //Función para obtener la colección de AsuntoConfs que se ajustan a los limites pasados
    public static ArrayList<AsuntoConfRAModos> getAsuntoConfs(Integer idAsunto, Integer desdeVel, Integer hastaVel, ArrayList<String> campos, String sqlExtra, Boolean distinct) throws SQLException, NoSuchFieldException {
        InteraccionBD interBD = new InteraccionBD();
        
        ArrayList<AsuntoConfRAModos> res = null;
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();

        condicion = getCondicion(idAsunto, desdeVel, hastaVel, paramsPS);
    
    //Por defecto lo devolvemos ordenado por asuntoConf
        if (sqlExtra == null || sqlExtra.trim().length() == 0) {
            String sqlOrderBy = InteraccionBD.anadeCampoOrderBy(null, CAMPO_ID_ASUNTO);
            sqlExtra = InteraccionBD.anadeSqlExtra(null, sqlOrderBy);
        }
               
        ArrayList<Object[]> resAux = interBD.getDatosTabla(TABLA, campos, condicion, paramsPS, sqlExtra, distinct);

        if (resAux != null) {
            int n = resAux.size();
            res = new ArrayList<AsuntoConfRAModos>();
            
            for (int i = 0; i < n; i++) {
               res.add(new AsuntoConfRAModos(resAux.get(i), campos));
            }
        }

        return res;
    }
    
    //Función para obtener la colección de AsuntoConf.que se ajustan a los limites pasados (clave)
    public static ArrayList<AsuntoConfRAModos> getAsuntoConfsPorIdAsunto(Integer idAsunto) throws SQLException, NoSuchFieldException {
        return getAsuntoConfs(idAsunto, null, null, null, null, null);
    }

    //Función para añadir un AsuntoConfRA a la BD
    public static int insertAsuntoConf(Integer idAsunto, Integer desdeVel, Integer hastaVel, String sqlExtra) throws SQLException {
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
        } else {    //Es campo clave, asignamos el incremento de la última
            valores = InteraccionBD.anadeCampoValorAutoInc(valores, CAMPO_ID_ASUNTO);
            campos.add(CAMPO_ID_ASUNTO);
            autoInc.add(CAMPO_ID_ASUNTO);
            condAutoInc.add(null);
        }
        if (desdeVel != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, desdeVel);
            campos.add(CAMPO_DESDE_VEL);
        }
        if (hastaVel != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, hastaVel);
            campos.add(CAMPO_HASTA_VEL);
        }
        
        interBD.inicioTransaccion();
        res = interBD.insertDatosTabla(TABLA, campos, valores, paramsPS, sqlExtra, autoInc, condAutoInc);
        interBD.finTransaccion();
        
        return res;
    }
    
    public static int insertAsuntoConf(AsuntoConfRAModos asuntoConf, String sqlExtra) throws SQLException {
        return insertAsuntoConf(asuntoConf.idAsunto, asuntoConf.desdeVel, asuntoConf.hastaVel, sqlExtra);
    }
    
    //Función para añadir un AsuntoConfRA a la BD
    public static int updateAsuntoConf(Integer idAsunto, Integer desdeVel, Integer hastaVel, Integer idAsuntoVal, Integer desdeVelVal, Integer hastaVelVal, String sqlExtra) throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        ArrayList<String> campos = new ArrayList<String>();
        int res = 0;
        
        if (idAsuntoVal != null && !idAsuntoVal.equals(idAsunto)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, idAsuntoVal);
            campos.add(CAMPO_ID_ASUNTO);
        }
        if (desdeVelVal != null && !desdeVelVal.equals(desdeVelVal)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, desdeVelVal);
            campos.add(CAMPO_DESDE_VEL);
        }
        if (hastaVelVal != null && !hastaVelVal.equals(hastaVelVal)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, hastaVelVal);
            campos.add(CAMPO_HASTA_VEL);
        }
        
        //Condiciones de actualización
        condicion = getCondicion(idAsunto, desdeVel, hastaVel, paramsPS);
                
        interBD.inicioTransaccion();
        res = interBD.updateDatosTabla(TABLA, campos, condicion, paramsPS, sqlExtra);
        interBD.finTransaccion();
        
        return res;
    }
    
    public static int updateAsuntoConf(AsuntoConfRAModos confVieja, AsuntoConfRAModos confNueva, String sqlExtra) throws SQLException {
        return updateAsuntoConf(confVieja.idAsunto, confVieja.desdeVel, confVieja.hastaVel, confNueva.idAsunto, confNueva.desdeVel, confNueva.hastaVel, sqlExtra);
    }
    
    //Función para añadir/modificar un AsuntoConfRA a la BD
    public static int insertOrUpdateAsuntoConf(Integer idAsunto, Integer desdeVel, Integer hastaVel, Integer idAsuntoVal, Integer desdeVelVal, Integer hastaVelVal, String sqlExtra) throws SQLException {
        int res;
        
        res = updateAsuntoConf(idAsunto, desdeVel, hastaVel, idAsuntoVal, desdeVelVal, hastaVelVal, sqlExtra);
        
        if (res < 0)
            res = insertAsuntoConf(idAsuntoVal, desdeVelVal, hastaVelVal, sqlExtra);

        return res;
    }
    
    public static int insertOrUpdateAsuntoConf(AsuntoConfRAModos confVieja, AsuntoConfRAModos confNueva, String sqlExtra) throws SQLException {
        int res = 0;
        
        if (confVieja == null)
            res =  insertAsuntoConf(confNueva, sqlExtra);
        else
            res = insertOrUpdateAsuntoConf(confVieja.idAsunto, confVieja.desdeVel, confVieja.hastaVel, confNueva.idAsunto, confNueva.desdeVel, confNueva.hastaVel, sqlExtra);
        
        return res;
    }
    
    //Función para eliminar AsuntoConfs que se ajustan a los limites pasados
    public static int deleteAsuntoConfs(Integer idAsunto, Integer desdeVel, Integer hastaVel, String sqlExtra) throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        int res = 0;

        condicion = getCondicion(idAsunto, desdeVel, hastaVel, paramsPS);
        
        interBD.inicioTransaccion();
        res  = interBD.deleteDatosTabla(TABLA, condicion, paramsPS, sqlExtra);
        interBD.finTransaccion();
        
        return res;
    }
    
    public static int deleteAsuntoConfs(AsuntoConfRAModos asuntoConf, String sqlExtra) throws SQLException {
        return deleteAsuntoConfs(asuntoConf.idAsunto, asuntoConf.desdeVel, asuntoConf.hastaVel, sqlExtra);
    }
    
    public Object[] toObject() {
        return new Object[]{this.idAsunto, this.desdeVel, this.hastaVel};
    }
  
    public static Object[] getCamposTabla() throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        return interBD.getCamposTabla(TABLA);
    }
} 