package RA;

import general.InteraccionBD;
import general.TratDecimales;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;

public class PosicionRA {
    private Integer id;
    private Double posX;
    private Double posY;
    private Double posZ;
    
    public static final String BD = InteraccionBD.PREF_BD_RA;
    public static final String TABLA = BD + "PosicionRA";
    public static final String CAMPO_ID = "Id";
    public static final String CAMPO_POS_X = "PosX";
    public static final String CAMPO_POS_Y = "PosY";
    public static final String CAMPO_POS_Z = "PosZ";

    public PosicionRA(Integer id, Double posX, Double posY, Double posZ) {
        this.id = id;
        this.posX = posX;
        this.posY = posY;
        this.posZ = posZ;
    }
    
    public PosicionRA(Object[] valores, ArrayList<String> campos) throws SQLException, NoSuchFieldException {
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
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer idAsunto) {
        this.id = idAsunto;
    }

    public Double getPosX() {
        return posX;
    }

    public void setPosX(Double posX) {
        this.posX = posX;
    }
    
    public Double getPosY() {
        return posY;
    }

    public void setPosY(Double posY) {
        this.posY = posY;
    }

    public Double getPosZ() {
        return posZ;
    }

    public void setPosZ(Double posZ) {
        this.posZ = posZ;
    }

    //Función para asignar valores a campos de la clase según el campo de la BD
    private void asignaCampo(String campo, Object valor) throws NoSuchFieldException {
        if (campo.compareTo(CAMPO_ID) == 0) {
            this.id = (Integer) valor;
        } else if (campo.compareTo(CAMPO_POS_X) == 0) {
            if (valor != null) {
                this.posX = TratDecimales.round(((BigDecimal) valor).doubleValue(), TratDecimales.DEC_VARIABLE_RA);
            } else {
                this.posX = null;
            }
        } else if (campo.compareTo(CAMPO_POS_Y) == 0) {
            if (valor != null) {
                this.posY = TratDecimales.round(((BigDecimal) valor).doubleValue(), TratDecimales.DEC_VARIABLE_RA);
            } else {
                this.posY = null;
            }
        } else if (campo.compareTo(CAMPO_POS_Z) == 0) {
            if (valor != null) {
                this.posZ = TratDecimales.round(((BigDecimal) valor).doubleValue(), TratDecimales.DEC_VARIABLE_RA);
            } else {
                this.posZ = null;
            }
        } else {
            //throw new NoSuchFieldException("No existe el campo en la clase " + this.getClass().getSimpleName());
			System.out.println("No existe el campo <" + campo + "> en la clase " + this.getClass().getSimpleName());
        }
    }
    
    private static String getCondicion(Integer id, Double posX, Double posY, Double posZ, ArrayList<Object[]> paramsPS) {
        String condicion = "";
        
        if (id != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_ID, "=", id);
        }
        if (posX != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_POS_X, "=", posX);
        }
        if (posY != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_POS_Y, "=", posY);
        }
        if (posZ != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_POS_Z, "=", posZ);
        }
        
        return condicion;
    }

    //Función para obtener la colección de Posiciones que se ajustan a los limites pasados
    public static ArrayList<PosicionRA> getPosiciones(Integer id, Double posX, Double posY, Double posZ, ArrayList<String> campos, String sqlExtra, Boolean distinct) throws SQLException, NoSuchFieldException {
        InteraccionBD interBD = new InteraccionBD();
        
        ArrayList<PosicionRA> res = null;
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();

        condicion = getCondicion(id, posX, posY, posZ, paramsPS);
        
        //Por defecto lo devolvemos ordenado por site, posicion
        if (sqlExtra == null || sqlExtra.trim().length() == 0) {
            String sqlOrderBy = InteraccionBD.anadeCampoOrderBy(null, CAMPO_ID);
            sqlExtra = InteraccionBD.anadeSqlExtra(null, sqlOrderBy);
        }
               
        ArrayList<Object[]> resAux = interBD.getDatosTabla(TABLA, campos, condicion, paramsPS, sqlExtra, distinct);

        if (resAux != null) {
            int n = resAux.size();
            res = new ArrayList<PosicionRA>();
            
            for (int i = 0; i < n; i++) {
               res.add(new PosicionRA(resAux.get(i), campos));
            }
        }

        return res;
    }
    
    public static PosicionRA getPosicion(Integer id) throws SQLException, NoSuchFieldException {
        PosicionRA res = null;
        
        ArrayList<PosicionRA> resAux = getPosiciones(id, null, null, null, null, null, null);
        
        if (resAux != null)
            res = resAux.get(0);
        
        return res;
    }

    //Función para añadir una Posicion a la BD
    public static int insertPosicion(Integer id, Double posX, Double posY, Double posZ, String sqlExtra) throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        String valores = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        ArrayList<String> campos = new ArrayList<String>();
        int res = 0;
        
        if (id != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, id);
            campos.add(CAMPO_ID);
        }
        if (posX != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, posX);
            campos.add(CAMPO_POS_X);
        }
        if (posY != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, posY);
            campos.add(CAMPO_POS_Y);
        }
        if (posZ != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, posZ);
            campos.add(CAMPO_POS_Z);
        }
        
        interBD.inicioTransaccion();
        res = interBD.insertDatosTabla(TABLA, campos, valores, paramsPS, sqlExtra);
        interBD.finTransaccion();
        
        return res;
    }
    
    //Función para insertar posiciones con autoincremento)
    public static Integer insertPosicionGetId(Double posX, Double posY, Double posZ, String sqlExtra) throws SQLException {
        Integer res = null;
        InteraccionBD interBD = new InteraccionBD();
        
        interBD.inicioTransaccion();
        if (insertPosicion(null, posX, posY, posZ, sqlExtra) > 0) {
            res = ((BigDecimal) interBD.getIdentidad(TABLA)).intValue();
        }
        interBD.finTransaccion();
        
        return res;
    }
    
    public static int insertPosicion(PosicionRA posicionRA, String sqlExtra) throws SQLException {
        return insertPosicion(posicionRA.id, posicionRA.posX, posicionRA.posY, posicionRA.posZ, sqlExtra);
    }
    
    //Función para añadir una Posicion a la BD
    public static int updatePosicion(Integer id, Double posX, Double posY, Double posZ,
            Integer idVal, Double posXVal, Double posYVal, Double posZVal, String sqlExtra) throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        ArrayList<String> campos = new ArrayList<String>();
        int res = 0;
        
        if (idVal != null && !idVal.equals(id)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, idVal);
            campos.add(CAMPO_ID);
        }
        if (posXVal != null && !posXVal.equals(posX)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, posXVal);
            campos.add(CAMPO_POS_X);
        }
        if (posYVal != null && !posYVal.equals(posY)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, posYVal);
            campos.add(CAMPO_POS_Y);
        }
        if (posZVal != null && !posZVal.equals(posZ)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, posZVal);
            campos.add(CAMPO_POS_Z);
        }
        
        //Condiciones de actualización
        condicion = getCondicion(id, posX, posY, posZ, paramsPS);
                
        interBD.inicioTransaccion();
        res = interBD.updateDatosTabla(TABLA, campos, condicion, paramsPS, sqlExtra);
        interBD.finTransaccion();
        
        return res;
    }
    
    public static int updatePosicion(PosicionRA posicionViejo, PosicionRA posicionNuevo, String sqlExtra) throws SQLException {
        return updatePosicion(posicionViejo.id, posicionViejo.posX, posicionViejo.posY, posicionViejo.posZ,
                posicionNuevo.id, posicionNuevo.posX, posicionNuevo.posY, posicionNuevo.posZ, sqlExtra);
    }
    
    //Función para añadir/modificar una Posicion a la BD
    public static int insertOrUpdatePosicion(Integer id, Double posX, Double posY, Double posZ,
            Integer idVal, Double posXVal, Double posYVal, Double posZVal, String sqlExtra) throws SQLException {
        int res;
        
        res = updatePosicion(id, posX, posY, posZ, idVal, posXVal, posYVal, posZVal, sqlExtra);
        
        if (res < 0)
            res = insertPosicion(idVal, posXVal, posYVal, posZVal, sqlExtra);

        return res;
    }
    
    public static int insertOrUpdatePosicion(PosicionRA posicionViejo, PosicionRA posicionNuevo, String sqlExtra) throws SQLException {
        
        return insertOrUpdatePosicion(posicionViejo.id, posicionViejo.posX, posicionViejo.posY, posicionViejo.posZ,
                posicionNuevo.id, posicionNuevo.posX, posicionNuevo.posY, posicionNuevo.posZ, sqlExtra);
    }
    
    //Función para eliminar Posiciones que se ajustan a los limites pasados
    public static int deletePosiciones(Integer id, Double posX, Double posY, Double posZ, String sqlExtra) throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        int res = 0;

        condicion = getCondicion(id, posX, posY, posZ, paramsPS);
        
        interBD.inicioTransaccion();
        res  = interBD.deleteDatosTabla(TABLA, condicion, paramsPS, sqlExtra);
        interBD.finTransaccion();
        
        return res;
    }
    
    public static int deletePosiciones(PosicionRA posicionRA, String sqlExtra) throws SQLException {
        return deletePosiciones(posicionRA.id, posicionRA.posX, posicionRA.posY, posicionRA.posZ, sqlExtra);
    }
    
    public Object[] toObject() {
        return new Object[]{this.id, this.posX, this.posY, this.posZ};
    }
  
    public static Object[] getCamposTabla() throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        return interBD.getCamposTabla(TABLA);
    }
    
    @Override
    public String toString() {
        return TratDecimales.round(this.posX, 3) + "; " + TratDecimales.round(this.posY, 3) + "; " + TratDecimales.round(this.posZ, 3);
    }
} 