package RA;

import general.InteraccionBD;
import java.sql.SQLException;
import java.util.ArrayList;

public class Variable {
    private Integer idVariable;
    private String nomVariable;
    private String unidad;
    private Double min;
    private Double max;
    private Double salto;
    private Double vCte;
    
    public static final String BD = InteraccionBD.PREF_BD_GENERAL;
    public static final String TABLA = BD + "Variable";
    public static final String CAMPO_ID_VAR = "Id_variable";
    public static final String CAMPO_NOM_VAR = "Variable";
    public static final String CAMPO_UNIDAD = "Unidad";
    public static final String CAMPO_MIN = "Min";
    public static final String CAMPO_MAX = "Max";
    public static final String CAMPO_SALTO = "Salto";
    public static final String CAMPO_V_CTE = "Vcte";
    
    public static final String TIPO_VELOCIDAD = "Velocidad";
    public static final String TIPO_DIRECCION = "Dirección";
    public static final String TIPO_HUMEDAD = "Humedad";

    public Variable(Integer idVariable, String nomVariable, String unidad, Double min, Double max, Double salto, Double vCte) {
        this.idVariable = idVariable;
        this.nomVariable = nomVariable;
        this.unidad = unidad;
        this.min = min;
        this.max = max;
        this.salto = salto;
        this.vCte = vCte;
    }
    
    public Variable(Object[] valores, ArrayList<String> campos) throws SQLException, NoSuchFieldException {
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
    
    public Integer getIdVariable() {
        return idVariable;
    }

    public void setIdVariable(Integer idVariable) {
        this.idVariable = idVariable;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public String getNomVariable() {
        return nomVariable;
    }

    public void setNomVariable(String nomVariable) {
        this.nomVariable = nomVariable;
    }

    public Double getSalto() {
        return salto;
    }

    public void setSalto(Double salto) {
        this.salto = salto;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public Double getVCte() {
        return vCte;
    }

    public void setVCte(Double vCte) {
        this.vCte = vCte;
    }
    
    //Función para asignar valores a campos de la clase según el campo de la BD
    private void asignaCampo(String campo, Object valor) throws NoSuchFieldException {
        if (campo.compareTo(CAMPO_ID_VAR) == 0) {
            this.idVariable = (Integer) valor;
        } else if (campo.compareTo(CAMPO_NOM_VAR) == 0) {
            this.nomVariable = (String) valor;
        } else if (campo.compareTo(CAMPO_UNIDAD) == 0) {
            this.unidad = (String) valor;
        } else if (campo.compareTo(CAMPO_MIN) == 0) {
            this.min = (Double) valor;
        } else if (campo.compareTo(CAMPO_MAX) == 0) {
            this.max = (Double) valor;
        } else if (campo.compareTo(CAMPO_SALTO) == 0) {
            this.salto = (Double) valor;
        } else if (campo.compareTo(CAMPO_V_CTE) == 0) {
            this.vCte = (Double) valor;
        } else {
            //throw new NoSuchFieldException("No existe el campo en la clase " + this.getClass().getSimpleName());
			System.out.println("No existe el campo <" + campo + "> en la clase " + this.getClass().getSimpleName());
        }
    }
    
    private static String getCondicion(Integer idVariable, String nomVariable, String unidad, Double min, Double max, Double salto, Double vCte, ArrayList<Object[]> paramsPS) {
        String condicion = "";
        
        if (idVariable != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_ID_VAR, "=", idVariable);
        }
        if (nomVariable != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_NOM_VAR, "=", nomVariable);
        }
        if (unidad != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_UNIDAD, "=", unidad);
        }
        if (min != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_MIN, "=", min);
        }
        if (max != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_MAX, "=", max);
        }
        if (salto != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_SALTO, "=", salto);
        }
        if (vCte != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_V_CTE, "=", vCte);
        }
        
        return condicion;
    }

    //Función para obtener la colección de Variables que se ajustan a los limites pasados
    public static ArrayList<Variable> getVariables(Integer idVariable, String nomVariable, String unidad, Double min, Double max, Double salto, Double vCte,
            ArrayList<String> campos, String sqlExtra, Boolean distinct) throws SQLException, NoSuchFieldException {
        InteraccionBD interBD = new InteraccionBD();
        
        ArrayList<Variable> res = null;
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();

        condicion = getCondicion(idVariable, nomVariable, unidad, min, max, salto, vCte, paramsPS);
        
        //Por defecto lo devolvemos ordenado por idVariable
        if (sqlExtra == null || sqlExtra.trim().length() == 0) {
            String sqlOrderBy = InteraccionBD.anadeCampoOrderBy(null, CAMPO_ID_VAR);
            sqlExtra = InteraccionBD.anadeSqlExtra(null, sqlOrderBy);
        }

        ArrayList<Object[]> resAux = interBD.getDatosTabla(TABLA, campos, condicion, paramsPS, sqlExtra, distinct);

        if (resAux != null) {
            int n = resAux.size();
            res = new ArrayList<Variable>();
            
            for (int i = 0; i < n; i++) {
               res.add(new Variable(resAux.get(i), campos));
            }
        }

        return res;
    }
    
    public static Variable getVariablePorId(Integer idVariable) throws SQLException, NoSuchFieldException {
        Variable res = null;
        ArrayList<Variable> resAux = getVariables(idVariable, null, null, null, null, null, null, null, null, null);

        if (resAux != null && resAux.size() > 0) {
            res = resAux.get(0);
        }
        return res;
    }

    //Función para añadir una variable a la BD
    public static int insertVariable(Integer idVariable, String nomVariable, String unidad, Double min, Double max, Double salto, Double vCte, String sqlExtra) throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        String valores = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        ArrayList<String> campos = new ArrayList<String>();
        ArrayList<String> autoInc = new ArrayList<String>();
        ArrayList<String> condAutoInc = new ArrayList<String>();
        int res = 0;
        
        if (idVariable != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, idVariable);
            campos.add(CAMPO_ID_VAR);
        } else {    //Es campo clave, asignamos el incremento de la última
            valores = InteraccionBD.anadeCampoValorAutoInc(valores, CAMPO_ID_VAR);
            campos.add(CAMPO_ID_VAR);
            autoInc.add(CAMPO_ID_VAR);
        }
        if (nomVariable != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, nomVariable);
            campos.add(CAMPO_NOM_VAR);
        }
        if (unidad != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, unidad);
            campos.add(CAMPO_UNIDAD);
        }
        if (min != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, min);
            campos.add(CAMPO_MIN);
        }
        if (max != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, max);
            campos.add(CAMPO_MAX);
        }
        if (salto != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, salto);
            campos.add(CAMPO_SALTO);
        }
        if (vCte != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, vCte);
            campos.add(CAMPO_V_CTE);
        }
        
        interBD.inicioTransaccion();
        res = interBD.insertDatosTabla(TABLA, campos, valores, paramsPS, sqlExtra, autoInc, condAutoInc);
        interBD.finTransaccion();
        
        return res;
    }
    
    public static int insertVariable(Variable variable, String sqlExtra) throws SQLException {
        return insertVariable(variable.idVariable, variable.nomVariable, variable.unidad, variable.min, variable.max, variable.salto, variable.vCte, sqlExtra);
    }
    
    //Función para añadir una variable a la BD
    public static int updateVariable(Integer idVariable, String nomVariable, String unidad, Double min, Double max, Double salto, Double vCte,
            Integer idVariableVal, String nomVariableVal, String unidadVal, Double minVal, Double maxVal, Double saltoVal, Double vCteVal, String sqlExtra) throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        ArrayList<String> campos = new ArrayList<String>();
        int res = 0;
        
        if (idVariableVal != null && !idVariableVal.equals(idVariable)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, idVariableVal);
            campos.add(CAMPO_ID_VAR);
        }
        if (nomVariableVal != null && !nomVariableVal.equals(nomVariable)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, nomVariableVal);
            campos.add(CAMPO_NOM_VAR);
        }
        if (unidadVal != null && !unidadVal.equals(unidad)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, unidadVal);
            campos.add(CAMPO_UNIDAD);
        }
        if (minVal != null && !minVal.equals(min)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, minVal);
            campos.add(CAMPO_MIN);
        }
        if (maxVal != null && !maxVal.equals(max)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, maxVal);
            campos.add(CAMPO_MAX);
        }
        if (saltoVal != null && !saltoVal.equals(salto)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, saltoVal);
            campos.add(CAMPO_SALTO);
        }
        if (vCteVal != null && !vCteVal.equals(vCte)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, vCteVal);
            campos.add(CAMPO_V_CTE);
        }

        //Condiciones de actualización
        condicion = getCondicion(idVariable, nomVariable, unidad, min, max, salto, vCte, paramsPS);
        
        interBD.inicioTransaccion();
        res = interBD.updateDatosTabla(TABLA, campos, condicion, paramsPS, sqlExtra);
        interBD.finTransaccion();
        
        return res;
    }
    
    public static int updateVariable(Variable varVieja, Variable varNueva, String sqlExtra) throws SQLException {
        return updateVariable(varVieja.idVariable, varVieja.nomVariable, varVieja.unidad, varVieja.min, varVieja.max, varVieja.salto, varVieja.vCte, 
                varNueva.idVariable, varNueva.nomVariable, varNueva.unidad, varNueva.min, varNueva.max, varNueva.salto, varNueva.vCte, sqlExtra);
    }
    
    //Función para añadir/modificar una variable a la BD
    public static int insertOrUpdateVariable(Integer idVariable, String nomVariable, String unidad, Double min, Double max, Double salto, Double vCte,
            Integer idVariableVal, String nomVariableVal, String unidadVal, Double minVal, Double maxVal, Double saltoVal, Double vCteVal, String sqlExtra) throws SQLException {
        int res;
        
        res = updateVariable(idVariable, nomVariable, unidad, min, max, salto, vCte, idVariableVal, nomVariableVal, unidadVal, minVal, maxVal, saltoVal, vCteVal, sqlExtra);
        
        if (res < 0)
            res = insertVariable(idVariableVal, nomVariableVal, unidadVal, minVal, maxVal, saltoVal, vCteVal, sqlExtra);

        return res;
    }
    
    public static int insertOrUpdateVariable(Variable varVieja, Variable varNueva, String sqlExtra) throws SQLException {
        
        return insertOrUpdateVariable(varVieja.idVariable, varVieja.nomVariable, varVieja.unidad, varVieja.min, varVieja.max, varVieja.salto, varVieja.vCte, 
                varNueva.idVariable, varNueva.nomVariable, varNueva.unidad, varNueva.min, varNueva.max, varNueva.salto, varNueva.vCte, sqlExtra);
    }
    
    //Función para eliminar Variables que se ajustan a los limites pasados
    public static int deleteVariables(Integer idVariable, String nomVariable, String unidad, Double min, Double max, Double salto, Double vCte, String sqlExtra) throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        int res = 0;

        condicion = getCondicion(idVariable, nomVariable, unidad, min, max, salto, vCte, paramsPS);

        interBD.inicioTransaccion();
        res  = interBD.deleteDatosTabla(TABLA, condicion, paramsPS, sqlExtra);
        interBD.finTransaccion();
        
        return res;
    }
    
    public static int deleteVariables(Variable variable, String sqlExtra) throws SQLException {
        return deleteVariables(variable.idVariable, variable.nomVariable, variable.unidad, variable.min, variable.max, variable.salto, variable.vCte, sqlExtra);
    }
    
    public Object[] toObject() {
        return new Object[]{this.idVariable, this.nomVariable, this.unidad, this.min, this.max, this.salto, this.vCte};
    }
  
    public static Object[] getCamposTabla() throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        return interBD.getCamposTabla(TABLA);
    }
} 