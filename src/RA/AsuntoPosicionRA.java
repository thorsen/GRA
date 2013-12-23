package RA;

import general.InteraccionBD;
import java.sql.SQLException;
import java.util.ArrayList;

public class AsuntoPosicionRA {
    private Integer idAsunto;
    private Integer idPosAero;
    private Integer idPosMicro1;
    private Integer idPosMicro2;
    private Integer idPosMicro3;
    private Integer idPosMicro4;
    
    public static final String BD = InteraccionBD.PREF_BD_RA;
    public static final String TABLA = BD + "AsuntoPosicionRA";
    public static final String CAMPO_ID_ASUNTO = "Id_asunto";
    public static final String CAMPO_ID_POS_AERO = "Id_posAero";
    public static final String CAMPO_ID_POS_MICRO1 = "Id_posMicro1";
    public static final String CAMPO_ID_POS_MICRO2 = "Id_posMicro2";
    public static final String CAMPO_ID_POS_MICRO3 = "Id_posMicro3";
    public static final String CAMPO_ID_POS_MICRO4 = "Id_posMicro4";
    
    public static final Integer ID_POS_AERO = 0;
    public static final Integer ID_POS_MICRO1 = 1;
    public static final Integer ID_POS_MICRO2 = 2;
    public static final Integer ID_POS_MICRO3 = 3;
    public static final Integer ID_POS_MICRO4 = 4;
    
    public static final String TXT_POS_AERO = "Aerogenerador";
    public static final String TXT_POS_MICRO1 = "Micrófono 1";
    public static final String TXT_POS_MICRO2 = "Micrófono 2";
    public static final String TXT_POS_MICRO3 = "Micrófono 3";
    public static final String TXT_POS_MICRO4 = "Micrófono 4";
    
    public AsuntoPosicionRA(Integer idAsunto, Integer idPosAero, Integer idPosMicro1, Integer idPosMicro2, Integer idPosMicro3, Integer idPosMicro4) {
        this.idAsunto = idAsunto;
        this.idPosAero = idPosAero;
        this.idPosMicro1 = idPosMicro1;
        this.idPosMicro2 = idPosMicro2;
        this.idPosMicro3 = idPosMicro3;
        this.idPosMicro4 = idPosMicro4;
    }
    
    public AsuntoPosicionRA(Object[] valores, ArrayList<String> campos) throws SQLException, NoSuchFieldException {
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

    public Integer getIdPosAero() {
        return idPosAero;
    }

    public void setIdPosAero(Integer idPosAero) {
        this.idPosAero = idPosAero;
    }

    public Integer getIdPosMicro1() {
        return idPosMicro1;
    }

    public void setIdPosMicro1(Integer idPosMicro1) {
        this.idPosMicro1 = idPosMicro1;
    }

    public Integer getIdPosMicro2() {
        return idPosMicro2;
    }

    public void setIdPosMicro2(Integer idPosMicro2) {
        this.idPosMicro2 = idPosMicro2;
    }

    public Integer getIdPosMicro3() {
        return idPosMicro3;
    }

    public void setIdPosMicro3(Integer idPosMicro3) {
        this.idPosMicro3 = idPosMicro3;
    }

    public Integer getIdPosMicro4() {
        return idPosMicro4;
    }

    public void setIdPosMicro4(Integer idPosMicro4) {
        this.idPosMicro4 = idPosMicro4;
    }

    //Función para asignar valores a campos de la clase según el campo de la BD
    private void asignaCampo(String campo, Object valor) throws NoSuchFieldException {
        if (campo.compareTo(CAMPO_ID_ASUNTO) == 0) {
            this.idAsunto = (Integer) valor;
        } else if (campo.compareTo(CAMPO_ID_POS_AERO) == 0) {
            this.idPosAero = (Integer) valor;
        } else if (campo.compareTo(CAMPO_ID_POS_MICRO1) == 0) {
            this.idPosMicro1 = (Integer) valor;
        } else if (campo.compareTo(CAMPO_ID_POS_MICRO2) == 0) {
            this.idPosMicro2 = (Integer) valor;
        } else if (campo.compareTo(CAMPO_ID_POS_MICRO3) == 0) {
            this.idPosMicro3 = (Integer) valor;
        } else if (campo.compareTo(CAMPO_ID_POS_MICRO4) == 0) {
            this.idPosMicro4 = (Integer) valor;
        } else {
            //throw new NoSuchFieldException("No existe el campo en la clase " + this.getClass().getSimpleName());
			System.out.println("No existe el campo <" + campo + "> en la clase " + this.getClass().getSimpleName());
        }
    }
    
    private static String getCondicion(Integer idAsunto, Integer idPosAero, Integer idPosMicro1, Integer idPosMicro2, Integer idPosMicro3, Integer idPosMicro4, ArrayList<Object[]> paramsPS) {
        String condicion = "";
        
        if (idAsunto != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_ID_ASUNTO, "=", idAsunto);
        }
        if (idPosAero != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_ID_POS_AERO, "=", idPosAero);
        }
        if (idPosMicro1 != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_ID_POS_MICRO1, "=", idPosMicro1);
        }
        if (idPosMicro2 != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_ID_POS_MICRO2, "=", idPosMicro2);
        }
        if (idPosMicro3 != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_ID_POS_MICRO3, "=", idPosMicro3);
        }
        if (idPosMicro4 != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_ID_POS_MICRO4, "=", idPosMicro4);
        }
        
        return condicion;
    }

    //Función para obtener la colección de PosicionesRA que se ajustan a los limites pasados
    public static ArrayList<AsuntoPosicionRA> getAsuntosPoscionRA(Integer idAsunto, Integer idPosAero, Integer idPosMicro1, Integer idPosMicro2, Integer idPosMicro3, Integer idPosMicro4, ArrayList<String> campos, String sqlExtra, Boolean distinct) throws SQLException, NoSuchFieldException {
        InteraccionBD interBD = new InteraccionBD();
        
        ArrayList<AsuntoPosicionRA> res = null;
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();

        condicion = getCondicion(idAsunto, idPosAero, idPosMicro1, idPosMicro2, idPosMicro3, idPosMicro4, paramsPS);
        
        //Por defecto lo devolvemos ordenado por site, tipo
        if (sqlExtra == null || sqlExtra.trim().length() == 0) {
            String sqlOrderBy = InteraccionBD.anadeCampoOrderBy(null, CAMPO_ID_ASUNTO);
            sqlOrderBy = InteraccionBD.anadeCampoOrderBy(sqlOrderBy, CAMPO_ID_POS_AERO);
            sqlExtra = InteraccionBD.anadeSqlExtra(null, sqlOrderBy);
        }
               
        ArrayList<Object[]> resAux = interBD.getDatosTabla(TABLA, campos, condicion, paramsPS, sqlExtra, distinct);

        if (resAux != null) {
            int n = resAux.size();
            res = new ArrayList<AsuntoPosicionRA>();
            
            for (int i = 0; i < n; i++) {
               res.add(new AsuntoPosicionRA(resAux.get(i), campos));
            }
        }

        return res;
    }
    
    public static AsuntoPosicionRA getAsuntoPosicionRA(Integer idAsunto) throws SQLException, NoSuchFieldException {
        AsuntoPosicionRA res = null;
        
        ArrayList<AsuntoPosicionRA> resAux = getAsuntosPoscionRA(idAsunto, null, null, null, null, null, null, null, null);
        
        if (resAux != null)
            res = resAux.get(0);
        
        return res;
    }

    //Función para añadir un AsuntoPosicionRA a la BD
    public static int insertAsuntoPosicionRA(Integer idAsunto, Integer idPosAero, Integer idPosMicro1, Integer idPosMicro2, Integer idPosMicro3, Integer idPosMicro4, String sqlExtra) throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        String valores = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        ArrayList<String> campos = new ArrayList<String>();
        int res = 0;
        
        if (idAsunto != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, idAsunto);
            campos.add(CAMPO_ID_ASUNTO);
        }
        if (idPosAero != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, idPosAero);
            campos.add(CAMPO_ID_POS_AERO);
        }
        if (idPosMicro1 != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, idPosMicro1);
            campos.add(CAMPO_ID_POS_MICRO1);
        }
        if (idPosMicro2 != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, idPosMicro2);
            campos.add(CAMPO_ID_POS_MICRO2);
        }
        if (idPosMicro3 != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, idPosMicro3);
            campos.add(CAMPO_ID_POS_MICRO3);
        }
        if (idPosMicro4 != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, idPosMicro4);
            campos.add(CAMPO_ID_POS_MICRO4);
        }
        
        interBD.inicioTransaccion();
        res = interBD.insertDatosTabla(TABLA, campos, valores, paramsPS, sqlExtra);
        interBD.finTransaccion();
        
        return res;
    }
    
    public static int insertAsuntoPosicionRA(AsuntoPosicionRA asuntoPosRA, String sqlExtra) throws SQLException {
        return insertAsuntoPosicionRA(asuntoPosRA.idAsunto, asuntoPosRA.idPosAero, asuntoPosRA.idPosMicro1, asuntoPosRA.idPosMicro2, asuntoPosRA.idPosMicro3, asuntoPosRA.idPosMicro4, sqlExtra);
    }
    
    //Función para añadir un TipoRA a la BD
    public static int updateAsuntoPosicionRA(Integer idAsunto, Integer idPosAero, Integer idPosMicro1, Integer idPosMicro2, Integer idPosMicro3, Integer idPosMicro4, Integer idAsuntoVal, Integer idPosAeroVal, Integer idPosMicro1Val, Integer idPosMicro2Val, Integer idPosMicro3Val, Integer idPosMicro4Val, String sqlExtra) throws SQLException { InteraccionBD interBD = new InteraccionBD();
        
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        ArrayList<String> campos = new ArrayList<String>();
        int res = 0;
        
        if (idAsuntoVal != null && !idAsuntoVal.equals(idAsunto)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, idAsuntoVal);
            campos.add(CAMPO_ID_ASUNTO);
        }
        if (idPosAeroVal != null && !idPosAeroVal.equals(idPosAero)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, idPosAeroVal);
            campos.add(CAMPO_ID_POS_AERO);
        }
        if (idPosMicro1Val != null && !idPosMicro1Val.equals(idPosMicro1)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, idPosMicro1Val);
            campos.add(CAMPO_ID_POS_MICRO1);
        }
        if (idPosMicro2Val != null && !idPosMicro2Val.equals(idPosMicro2)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, idPosMicro2Val);
            campos.add(CAMPO_ID_POS_MICRO2);
        }
        if (idPosMicro3Val != null && !idPosMicro3Val.equals(idPosMicro3)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, idPosMicro3Val);
            campos.add(CAMPO_ID_POS_MICRO3);
        }
        if (idPosMicro4Val != null && !idPosMicro4Val.equals(idPosMicro4)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, idPosMicro4Val);
            campos.add(CAMPO_ID_POS_MICRO4);
        }
        
        //Condiciones de actualización
        condicion = getCondicion(idAsunto, idPosAero, idPosMicro1, idPosMicro2, idPosMicro3, idPosMicro4, paramsPS);
                
        interBD.inicioTransaccion();
        res = interBD.updateDatosTabla(TABLA, campos, condicion, paramsPS, sqlExtra);
        interBD.finTransaccion();
        
        return res;
    }
    
    public static int updateAsuntoPosicionRA(AsuntoPosicionRA asuntoPosViejo, AsuntoPosicionRA asuntoPosNuevo, String sqlExtra) throws SQLException {
        return updateAsuntoPosicionRA(asuntoPosViejo.idAsunto, asuntoPosViejo.idPosAero, asuntoPosViejo.idPosMicro1, asuntoPosViejo.idPosMicro2, asuntoPosViejo.idPosMicro3, asuntoPosViejo.idPosMicro4, asuntoPosNuevo.idAsunto, asuntoPosNuevo.idPosAero, asuntoPosNuevo.idPosMicro1, asuntoPosNuevo.idPosMicro2, asuntoPosNuevo.idPosMicro3, asuntoPosNuevo.idPosMicro4, sqlExtra);
    }
    
    //Función para añadir/modificar un TipoRA a la BD
    public static int insertOrUpdateAsuntoPosicionRA(Integer idAsunto, Integer idPosAero, Integer idPosMicro1, Integer idPosMicro2, Integer idPosMicro3, Integer idPosMicro4, Integer idAsuntoVal, Integer idPosAeroVal, Integer idPosMicro1Val, Integer idPosMicro2Val, Integer idPosMicro3Val, Integer idPosMicro4Val, String sqlExtra) throws SQLException {
        int res;
        
        res = updateAsuntoPosicionRA(idAsunto, idPosAero, idPosMicro1, idPosMicro2, idPosMicro3, idPosMicro4, idAsuntoVal, idPosAeroVal, idPosMicro1Val, idPosMicro2Val, idPosMicro3Val, idPosMicro4Val, sqlExtra);
        
        if (res < 0)
            res = insertAsuntoPosicionRA(idAsuntoVal, idPosAeroVal, idPosMicro1Val, idPosMicro2Val, idPosMicro3Val, idPosMicro4Val, sqlExtra);

        return res;
    }
    
    public static int insertOrUpdateAsuntoPosicionRA(AsuntoPosicionRA asuntoPosViejo, AsuntoPosicionRA asuntoPosNuevo, String sqlExtra) throws SQLException {
        return insertOrUpdateAsuntoPosicionRA(asuntoPosViejo.idAsunto, asuntoPosViejo.idPosAero, asuntoPosViejo.idPosMicro1, asuntoPosViejo.idPosMicro2, asuntoPosViejo.idPosMicro3, asuntoPosViejo.idPosMicro4, asuntoPosNuevo.idAsunto, asuntoPosNuevo.idPosAero, asuntoPosNuevo.idPosMicro1, asuntoPosNuevo.idPosMicro2, asuntoPosNuevo.idPosMicro3, asuntoPosNuevo.idPosMicro4, sqlExtra);
    }
    
    //Función para eliminar TiposRA que se ajustan a los limites pasados
    public static int deletePosicionesRA(Integer idAsunto, Integer idPosAero, Integer idPosMicro1, Integer idPosMicro2, Integer idPosMicro3, Integer idPosMicro4, String sqlExtra) throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        int res = 0;

        condicion = getCondicion(idAsunto, idPosAero, idPosMicro1, idPosMicro2, idPosMicro3, idPosMicro4, paramsPS);
        
        interBD.inicioTransaccion();
        res  = interBD.deleteDatosTabla(TABLA, condicion, paramsPS, sqlExtra);
        interBD.finTransaccion();
        
        return res;
    }
    
    public static int deletePosicionesRA(AsuntoPosicionRA asuntoPosRA, String sqlExtra) throws SQLException {
        return deletePosicionesRA(asuntoPosRA.idAsunto, asuntoPosRA.idPosAero, asuntoPosRA.idPosMicro1, asuntoPosRA.idPosMicro2, asuntoPosRA.idPosMicro3, asuntoPosRA.idPosMicro4, sqlExtra);
    }
    
    public Object[] toObject() {
        return new Object[]{this.idAsunto, this.idPosAero, this.idPosMicro1, this.idPosMicro2, this.idPosMicro3, this.idPosMicro4};
    }
  
    public static Object[] getCamposTabla() throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        return interBD.getCamposTabla(TABLA);
    }
} 