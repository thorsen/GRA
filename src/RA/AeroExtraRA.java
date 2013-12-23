package RA;

import general.InteraccionBD;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;

public class AeroExtraRA {
    private Integer idAero;
    private Double longBuje;
    
    public static final String BD = InteraccionBD.PREF_BD_RA;
    public static final String TABLA = BD + "AeroExtraRA";
    public static final String CAMPO_ID_AERO = TABLA + "." + "Id_aero";
    public static final String CAMPO_LONG_BUJE = TABLA + "." + "LongBuje";

    public AeroExtraRA(Integer idAero, Double longBuje) {
        this.idAero = idAero;
        this.longBuje = longBuje;
    }
    
    public AeroExtraRA(Object[] valores, ArrayList<String> campos) throws SQLException, NoSuchFieldException {
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
    
    public Integer getIdAero() {
        return idAero;
    }

    public void setIdAero(Integer idAero) {
        this.idAero = idAero;
    }

    public Double getLongBuje() {
        return longBuje;
    }

    public void setLongBuje(Double longBuje) {
        this.longBuje = longBuje;
    }

    //Función para asignar valores a campos de la clase según el campo de la BD
    private void asignaCampo(String campo, Object valor) throws NoSuchFieldException {
        if (campo.compareTo(CAMPO_ID_AERO) == 0) {
            this.idAero = (Integer) valor;
        } else if (campo.compareTo(CAMPO_LONG_BUJE) == 0) {
            if (valor != null)
                this.longBuje = ((BigDecimal) valor).doubleValue();
            else
                this.longBuje = null;
        } else {
            //throw new NoSuchFieldException("No existe el campo en la clase " + this.getClass().getSimpleName());
			System.out.println("No existe el campo <" + campo + "> en la clase " + this.getClass().getSimpleName());
        }
    }
    
    private static String getCondicion(Integer idAero, Double longBuje, ArrayList<Object[]> paramsPS) {
        String condicion = "";
        
        if (idAero != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_ID_AERO, "=", idAero);
        }
        if (longBuje != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_LONG_BUJE, "=", longBuje);
        }
        
        return condicion;
    }

    //Función para obtener la colección de AeroExtras que se ajustan a los limites pasados
    public static ArrayList<AeroExtraRA> getAeros(Integer idAero, Double longBuje, ArrayList<String> campos, String sqlExtra, Boolean distinct) throws SQLException, NoSuchFieldException {
        InteraccionBD interBD = new InteraccionBD();
        
        ArrayList<AeroExtraRA> res = null;
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();

        condicion = getCondicion(idAero, longBuje, paramsPS);
        
        //Por defecto lo devolvemos ordenado por aero
        if (sqlExtra == null || sqlExtra.trim().length() == 0) {
            String sqlOrderBy = InteraccionBD.anadeCampoOrderBy(null, CAMPO_ID_AERO);
            sqlExtra = InteraccionBD.anadeSqlExtra(null, sqlOrderBy);
        }

        ArrayList<Object[]> resAux = interBD.getDatosTabla(TABLA, campos, condicion, paramsPS, sqlExtra, distinct);

        if (resAux != null) {
            int n = resAux.size();
            res = new ArrayList<AeroExtraRA>();
            
            for (int i = 0; i < n; i++) {
               res.add(new AeroExtraRA(resAux.get(i), campos));
            }
        }

        return res;
    }
    
    //Función para obtener el aero por Id
    public static AeroExtraRA getAeroPorId (Integer idAero) throws SQLException, NoSuchFieldException {
        AeroExtraRA res = null;
        
        ArrayList<AeroExtraRA> resAux = getAeros(idAero, null, null, null, null);
        
        if (resAux != null) {
            res = resAux.get(0);
        }

        return res;
    }
    
    //Función para añadir una aero a la BD
    public static int insertAeroExtra(Integer idAero, Double longBuje, String sqlExtra) throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        String valores = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        ArrayList<String> campos = new ArrayList<String>();
        ArrayList<String> autoInc = new ArrayList<String>();
        ArrayList<String> condAutoInc = new ArrayList<String>();
        int res = 0;
        
        if (idAero != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, idAero);
            campos.add(CAMPO_ID_AERO);
        } else {    //Es campo clave, asignamos el incremento de la última
            valores = InteraccionBD.anadeCampoValorAutoInc(valores, CAMPO_ID_AERO);
            campos.add(CAMPO_ID_AERO);
            autoInc.add(CAMPO_ID_AERO);
        }
        if (longBuje != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, longBuje);
            campos.add(CAMPO_LONG_BUJE);
        }

        interBD.inicioTransaccion();
        res = interBD.insertDatosTabla(TABLA, campos, valores, paramsPS, sqlExtra, autoInc, condAutoInc);
        interBD.finTransaccion();
        
        return res;
    }
    
    public static int insertAeroExtra(AeroExtraRA aero, String sqlExtra) throws SQLException {
        return insertAeroExtra(aero.idAero, aero.longBuje, sqlExtra);
    }
    
    //Función para añadir una aero a la BD
    public static int updateAeroExtra(Integer idAero, Double longBuje, Integer idAeroVal, Double longBujeVal, String sqlExtra) throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        ArrayList<String> campos = new ArrayList<String>();
        int res = 0;
        
        if (idAeroVal != null && !idAeroVal.equals(idAero)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, idAeroVal);
            campos.add(CAMPO_ID_AERO);
        }
        if (longBujeVal != null && !longBujeVal.equals(longBuje)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, longBujeVal);
            campos.add(CAMPO_LONG_BUJE);
        }
        
        //Condiciones de actualización
        condicion = getCondicion(idAero, longBuje, paramsPS);
        
        interBD.inicioTransaccion();
        res = interBD.updateDatosTabla(TABLA, campos, condicion, paramsPS, sqlExtra);
        interBD.finTransaccion();
        
        return res;
    }
    
    public static int updateAeroExtra(AeroExtraRA aeroViejo, AeroExtraRA aeroNuevo, String sqlExtra) throws SQLException {
        return updateAeroExtra(aeroViejo.idAero, aeroViejo.longBuje, aeroNuevo.idAero, aeroNuevo.longBuje, sqlExtra);
    }
    
    //Función para añadir/modificar una aero a la BD
    public static int insertOrUpdateAeroExtra(Integer idAero, Double longBuje, Integer idAeroVal, Double longBujeVal, String sqlExtra) throws SQLException {
        int res;
        
        res = updateAeroExtra(idAero, longBuje, idAeroVal, longBujeVal, sqlExtra);
        
        if (res < 0)
            res = insertAeroExtra(idAeroVal, longBujeVal, sqlExtra);

        return res;
    }
    
    public static int insertOrUpdateAeroExtra(AeroExtraRA aeroViejo, AeroExtraRA aeroNuevo, String sqlExtra) throws SQLException {
        
        return insertOrUpdateAeroExtra(aeroViejo.idAero, aeroViejo.longBuje, aeroNuevo.idAero, aeroNuevo.longBuje, sqlExtra);
    }
    
    //Función para eliminar AeroExtras que se ajustan a los limites pasados
    public static int deleteAeroExtras(Integer idAero, Double longBuje, String sqlExtra) throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        int res = 0;

        condicion = getCondicion(idAero, longBuje, paramsPS);

        interBD.inicioTransaccion();
        res  = interBD.deleteDatosTabla(TABLA, condicion, paramsPS, sqlExtra);
        interBD.finTransaccion();
        
        return res;
    }
    
    public static int deleteAeroExtras(AeroExtraRA aero, String sqlExtra) throws SQLException {
        return deleteAeroExtras(aero.idAero, aero.longBuje, sqlExtra);
    }
    
    public Object[] toObject() {
        return new Object[]{this.idAero, this.longBuje};
    }
  
    public static Object[] getCamposTabla() throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        return interBD.getCamposTabla(TABLA);
    }
} 