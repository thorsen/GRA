package RA;

import general.InteraccionBD;
import java.sql.SQLException;
import java.util.ArrayList;

public class NormaRA {
    private Integer idNorma;
    private String nombre;
    private Boolean vigente;
    
    public static final String BD = InteraccionBD.PREF_BD_GENERAL;
    public static final String TABLA = BD + "Norma";
    public static final String TABLA_RUIDO = InteraccionBD.PREF_BD_RA + "NormaRuido";
    public static final String CAMPO_ID_NORMA = TABLA + "." + "Id_norma";
    public static final String CAMPO_NOMBRE = TABLA + "." + "Nombre";
    public static final String CAMPO_VIGENTE = TABLA + "." + "Vigente";
    public static final String CAMPO_ID_NORMA_RUIDO = TABLA_RUIDO + "." + "Id_norma";
    
    public static final Integer ID_NORMA_IEC_2_1 = 4;
    public static final Integer ID_NORMA_IEC_3_0 = 5;
    public static final Integer ID_NORMA_BWEA = 6;
    public static final Integer ID_NORMA_AWEA = 7;
    
    public static final Integer DESDE_VEL_BWEA = 4;
    public static final Integer HASTA_VEL_BWEA = 18;
    public static final Integer DESDE_VEL_AWEA = 4;
    public static final Integer HASTA_VEL_AWEA = 18;
    public static final Integer DESDE_VEL_IEC_2_1 = 6;
    public static final Integer HASTA_VEL_IEC_2_1 = 10;
    public static final Double FACTOR_MIN_VEL_IEC_3_0 = 0.8;
    public static final Double FACTOR_MAX_VEL_IEC_3_0 = 1.3;
    public static final Double PORC_VEL_IEC_3_0 = 0.85;
    
    public static final Double Z_REF_IEC = 10.0;
    public static final Double Z_REF_AWEA = 10.0;
    public static final Double Z_REF_BWEA = 7.0;
    public static final Double AMPLITUD_IEC = 15.0;
    public static final Double AMPLITUD_AWEA = 15.0;
    public static final Double AMPLITUD_BWEA = 60.0;

    public NormaRA(Integer idNorma, String nombre, Boolean vigente) {
        this.idNorma = idNorma;
        this.nombre = nombre;
        this.vigente = vigente;
    }
    
    public NormaRA(Object[] valores, ArrayList<String> campos) throws SQLException, NoSuchFieldException {
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
    
    public Integer getIdNorma() {
        return idNorma;
    }

    public void setIdNorma(Integer idNorma) {
        this.idNorma = idNorma;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public Boolean getVigente() {
        return vigente;
    }

    public void setVigente(Boolean vigente) {
        this.vigente = vigente;
    }

    //Función para asignar valores a campos de la clase según el campo de la BD
    private void asignaCampo(String campo, Object valor) throws NoSuchFieldException {
        if (campo.compareTo(CAMPO_ID_NORMA) == 0) {
            this.idNorma = (Integer) valor;
        } else if (campo.compareTo(CAMPO_NOMBRE) == 0) {
            this.nombre = (String) valor;
        } else if (campo.compareTo(CAMPO_VIGENTE) == 0) {
            this.vigente = (Boolean) valor;
        } else {
            //throw new NoSuchFieldException("No existe el campo en la clase " + this.getClass().getSimpleName());
			System.out.println("No existe el campo <" + campo + "> en la clase " + this.getClass().getSimpleName());
        }
    }
    
    private static String getCondicion(Integer idNorma, String nombre, Boolean vigente, ArrayList<Object[]> paramsPS) {
        String condicion = "";
        
        if (idNorma != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_ID_NORMA, "=", idNorma);
        }
        if (nombre != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_NOMBRE, "=", nombre);
        }
        if (vigente != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_VIGENTE, "=", vigente);
        }
        
        return condicion;
    }

    //Función para obtener la colección de NormasRA que se ajustan a los limites pasados
    public static ArrayList<NormaRA> getNormasRA(Integer idNorma, String nombre, Boolean vigente, ArrayList<String> campos, String sqlExtra, Boolean distinct) throws SQLException, NoSuchFieldException {
        InteraccionBD interBD = new InteraccionBD();
        
        ArrayList<NormaRA> res = null;
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();

		if (campos == null || campos.isEmpty()) {
			Object[] camposNorma = interBD.getCamposTabla(TABLA);

			campos = new ArrayList<String>();

			int nCamposNorma = camposNorma.length;
			for (int i = 0; i < nCamposNorma; i++) {
				campos.add(TABLA + "." + (String) camposNorma[i]);
			}
		}

        condicion = getCondicion(idNorma, nombre, vigente, paramsPS);
        
        //Por defecto lo devolvemos ordenado por tipo
        if (sqlExtra == null || sqlExtra.trim().length() == 0) {
            String sqlOrderBy = InteraccionBD.anadeCampoOrderBy(null, CAMPO_ID_NORMA);
            sqlExtra = InteraccionBD.anadeSqlExtra(null, sqlOrderBy);
        }
               
        ArrayList<Object[]> resAux = interBD.getDatosTabla(TABLA, campos, condicion, paramsPS, sqlExtra, distinct);

        if (resAux != null) {
            int n = resAux.size();
            res = new ArrayList<NormaRA>();
            
            for (int i = 0; i < n; i++) {
               res.add(new NormaRA(resAux.get(i), campos));
            }
        }

        return res;
    }

	//Función para obtener la colección de NormasRA que se ajustan a los limites pasados
    public static ArrayList<NormaRA> getNormasRuido(Integer idNorma, String nombre, Boolean vigente, ArrayList<String> campos, String sqlExtra, Boolean distinct) throws SQLException, NoSuchFieldException {
        InteraccionBD interBD = new InteraccionBD();
        
        ArrayList<NormaRA> res = null;
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();

		if (campos == null || campos.isEmpty()) {
			Object[] camposNorma = interBD.getCamposTabla(TABLA);

			campos = new ArrayList<String>();

			int nCamposNorma = camposNorma.length;
			for (int i = 0; i < nCamposNorma; i++) {
				campos.add(TABLA + "." + (String) camposNorma[i]);
			}
		}

        condicion = getCondicion(idNorma, nombre, vigente, paramsPS);

		condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_ID_NORMA_RUIDO, InteraccionBD.ASIGNACION_CAMPOS, CAMPO_ID_NORMA);
        
        //Por defecto lo devolvemos ordenado por tipo
        if (sqlExtra == null || sqlExtra.trim().length() == 0) {
            String sqlOrderBy = InteraccionBD.anadeCampoOrderBy(null, CAMPO_ID_NORMA);
            sqlExtra = InteraccionBD.anadeSqlExtra(null, sqlOrderBy);
        }

		String tabla = InteraccionBD.anadeTabla(null, TABLA);
        tabla = InteraccionBD.anadeTabla(tabla, TABLA_RUIDO);
               
        ArrayList<Object[]> resAux = interBD.getDatosTabla(tabla, campos, condicion, paramsPS, sqlExtra, distinct);

        if (resAux != null) {
            int n = resAux.size();
            res = new ArrayList<NormaRA>();
            
            for (int i = 0; i < n; i++) {
               res.add(new NormaRA(resAux.get(i), campos));
            }
        }

        return res;
    }
    
    //Función para obtener la colección de NormasRA que se ajustan a los limites pasados
    public static NormaRA getNormaPorId(Integer idNorma) throws SQLException, NoSuchFieldException {
        NormaRA res = null;
        
        ArrayList<NormaRA> resAux = getNormasRA(idNorma, null, null, null, null, null);

        if (resAux != null) {
            res = resAux.get(0);
        }

        return res;
    }
    
    //Función para añadir un NormaRA a la BD
    public static int insertNormaRA(Integer idNorma, String nombre, Boolean vigente, String sqlExtra) throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        String valores = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        ArrayList<String> campos = new ArrayList<String>();
        ArrayList<String> autoInc = new ArrayList<String>();
        ArrayList<String> condAutoInc = new ArrayList<String>();
        
        int res = 0;
        
        if (idNorma != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, idNorma);
            campos.add(CAMPO_ID_NORMA);
        } else {    //Es campo clave, asignamos el incremento de la última
            valores = InteraccionBD.anadeCampoValorAutoInc(valores, CAMPO_ID_NORMA);
            campos.add(CAMPO_ID_NORMA);
            autoInc.add(CAMPO_ID_NORMA);
            condAutoInc.add(null);
        }
        if (nombre != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, nombre);
            campos.add(CAMPO_NOMBRE);
        }
        if (vigente != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, vigente);
            campos.add(CAMPO_VIGENTE);
        }
        
        interBD.inicioTransaccion();
        res = interBD.insertDatosTabla(TABLA, campos, valores, paramsPS, sqlExtra, autoInc, condAutoInc);
        interBD.finTransaccion();
        
        return res;
    }
    
    public static int insertNormaRA(NormaRA normaRA, String sqlExtra) throws SQLException {
        return insertNormaRA(normaRA.idNorma, normaRA.nombre, normaRA.vigente, sqlExtra);
    }
    
    //Función para añadir un NormaRA a la BD
    public static int updateNormaRA(Integer idNorma, String nombre, Boolean vigente, 
            Integer idNormaVal, String nombreVal, Boolean vigenteVal, String sqlExtra) throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        ArrayList<String> campos = new ArrayList<String>();
        int res = 0;
        
        if (idNormaVal != null && !idNormaVal.equals(idNorma)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, idNormaVal);
            campos.add(CAMPO_ID_NORMA);
        }
        if (nombreVal != null && !nombreVal.equals(nombre)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, nombreVal);
            campos.add(CAMPO_NOMBRE);
        }
        if (vigenteVal != null && !vigenteVal.equals(vigente)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, vigenteVal);
            campos.add(CAMPO_VIGENTE);
        }
        
        //Condiciones de actualización
        condicion = getCondicion(idNorma, nombre, vigente, paramsPS);
                
        interBD.inicioTransaccion();
        res = interBD.updateDatosTabla(TABLA, campos, condicion, paramsPS, sqlExtra);
        interBD.finTransaccion();
        
        return res;
    }
    
    public static int updateNormaRA(NormaRA normaVieja, NormaRA normaNueva, String sqlExtra) throws SQLException {
        return updateNormaRA(normaVieja.idNorma, normaVieja.nombre, normaVieja.vigente, normaNueva.idNorma, normaNueva.nombre, normaVieja.vigente, sqlExtra);
    }
    
    //Función para añadir/modificar un NormaRA a la BD
    public static int insertOrUpdateNormaRA(Integer idNorma, String nombre, Boolean vigente, 
            Integer idNormaVal, String nombreVal, Boolean vigenteVal, String sqlExtra) throws SQLException {
        int res;
        
        res = updateNormaRA(idNorma, nombre, vigente, idNormaVal, nombreVal, vigenteVal, sqlExtra);
        
        if (res < 0)
            res = insertNormaRA(idNormaVal, nombreVal, vigenteVal, sqlExtra);

        return res;
    }
    
    public static int insertOrUpdateNormaRA(NormaRA normaVieja, NormaRA normaNueva, String sqlExtra) throws SQLException {
        
        return insertOrUpdateNormaRA(normaVieja.idNorma, normaVieja.nombre, normaVieja.vigente,
                normaNueva.idNorma, normaNueva.nombre, normaNueva.vigente, sqlExtra);
    }
    
    //Función para eliminar NormasRA que se ajustan a los limites pasados
    public static int deleteNormasRA(Integer idNorma, String nombre, Boolean vigente, String sqlExtra) throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        int res = 0;

        condicion = getCondicion(idNorma, nombre, vigente, paramsPS);
        
        interBD.inicioTransaccion();
        res  = interBD.deleteDatosTabla(TABLA, condicion, paramsPS, sqlExtra);
        interBD.finTransaccion();
        
        return res;
    }
    
    public static int deleteNormasRA(NormaRA normaRA, String sqlExtra) throws SQLException {
        return deleteNormasRA(normaRA.idNorma, normaRA.nombre, normaRA.vigente, sqlExtra);
    }
    
    public Object[] toObject() {
        return new Object[]{this.idNorma, this.nombre, this.vigente};
    }
  
    public static Object[] getCamposTabla() throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        return interBD.getCamposTabla(TABLA);
    }
} 