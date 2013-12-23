package RA;

import general.InteraccionBD;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;

public class AerogeneradorRA {
    private Integer idAero;
    private String modelo;
    private Double hB;
    private String fabricante;
    private Double dN;
    private Double pNominal;
    private Double vIn;
    private Double vCut;
    private Integer lineas;
    private Boolean regulacion;
    private Double vNominal;
    private Boolean pequeno;
    private Double longBuje;
    
    public static final String BD = InteraccionBD.PREF_BD_GENERAL;
    public static final String TABLA = BD + "Aerogenerador";
    public static final String CAMPO_ID_AERO = TABLA + "." + "Id_aero";
    public static final String CAMPO_MODELO = TABLA + "." + "Modelo";
    public static final String CAMPO_H_B = TABLA + "." + "Hb";
    public static final String CAMPO_FAB = TABLA + "." + "Fabricante";
    public static final String CAMPO_D_N = TABLA + "." + "Dn";
    public static final String CAMPO_P_NOM = TABLA + "." + "Pnominal";
    public static final String CAMPO_V_IN = TABLA + "." + "Vin";
    public static final String CAMPO_V_CUT = TABLA + "." + "Vcut";
    public static final String CAMPO_LINEAS = TABLA + "." + "Lineas";
    public static final String CAMPO_REG = TABLA + "." + "Regulacion";
    public static final String CAMPO_V_NOM = TABLA + "." + "Vnominal";
    public static final String CAMPO_PEQ = TABLA + "." + "Pequeño";

    public AerogeneradorRA(Integer idAero, String modelo, Double hB, String fabricante, Double dN, Double pNominal, Double vIn, Double vCut, Integer lineas, Boolean regulacion, Double vNominal, Boolean pequeno, Double longBuje) {
        this.idAero = idAero;
        this.modelo = modelo;
        this.hB = hB;
        this.fabricante = fabricante;
        this.dN = dN;
        this.pNominal = pNominal;
        this.vIn = vIn;
        this.vCut = vCut;
        this.lineas = lineas;
        this.regulacion = regulacion;
        this.vNominal = vNominal;
        this.pequeno = pequeno;
        this.longBuje = longBuje;
    }
    
    public AerogeneradorRA(Object[] valores, ArrayList<String> campos) throws SQLException, NoSuchFieldException {
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
    
    public Double getDN() {
        return dN;
    }

    public void setDN(Double dN) {
        this.dN = dN;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public Double getHB() {
        return hB;
    }

    public void setHB(Double hB) {
        this.hB = hB;
    }

    public Integer getIdAero() {
        return idAero;
    }

    public void setIdAero(Integer idAero) {
        this.idAero = idAero;
    }

    public Integer getLineas() {
        return lineas;
    }

    public void setLineas(Integer lineas) {
        this.lineas = lineas;
    }

    public Double getLongBuje() {
        return longBuje;
    }

    public void setLongBuje(Double longBuje) {
        this.longBuje = longBuje;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Double getPNominal() {
        return pNominal;
    }

    public void setPNominal(Double pNominal) {
        this.pNominal = pNominal;
    }

    public Boolean getPequeno() {
        return pequeno;
    }

    public void setPequeno(Boolean pequeno) {
        this.pequeno = pequeno;
    }

    public Boolean getRegulacion() {
        return regulacion;
    }

    public void setRegulacion(Boolean regulacion) {
        this.regulacion = regulacion;
    }

    public Double getVCut() {
        return vCut;
    }

    public void setVCut(Double vCut) {
        this.vCut = vCut;
    }

    public Double getVIn() {
        return vIn;
    }

    public void setVIn(Double vIn) {
        this.vIn = vIn;
    }

    public Double getVNominal() {
        return vNominal;
    }

    public void setVNominal(Double vNominal) {
        this.vNominal = vNominal;
    }
    
    //Función para asignar valores a campos de la clase según el campo de la BD
    private void asignaCampo(String campo, Object valor) throws NoSuchFieldException {
        if (campo.compareTo(CAMPO_ID_AERO) == 0) {
            this.idAero = (Integer) valor;
        } else if (campo.compareTo(CAMPO_MODELO) == 0) {
            this.modelo = (String) valor;
        } else if (campo.compareTo(CAMPO_H_B) == 0) {
            this.hB = (Double) valor;
        } else if (campo.compareTo(CAMPO_FAB) == 0) {
            this.fabricante = (String) valor;
        } else if (campo.compareTo(CAMPO_D_N) == 0) {
            this.dN = (Double) valor;
        } else if (campo.compareTo(CAMPO_P_NOM) == 0) {
            this.pNominal = (Double) valor;
        } else if (campo.compareTo(CAMPO_V_IN) == 0) {
            this.vIn = (Double) valor;
        } else if (campo.compareTo(CAMPO_V_CUT) == 0) {
            this.vCut = (Double) valor;
        } else if (campo.compareTo(CAMPO_LINEAS) == 0) {
            this.lineas = (Integer) valor;
        } else if (campo.compareTo(CAMPO_REG) == 0) {
            if ((Integer) valor == 1)
                this.regulacion = true;
            else
                this.regulacion = true;
        } else if (campo.compareTo(CAMPO_V_NOM) == 0) {
            this.vNominal = (Double) valor;
        } else if (campo.compareTo(CAMPO_PEQ) == 0) {
            this.pequeno = (Boolean) valor;
        } else if (campo.compareTo(AeroExtraRA.CAMPO_LONG_BUJE) == 0) {
            if (valor != null)
                this.longBuje = ((BigDecimal) valor).doubleValue();
            else
                this.longBuje = null;
        } else {
            //throw new NoSuchFieldException("No existe el campo en la clase " + this.getClass().getSimpleName());
			System.out.println("No existe el campo <" + campo + "> en la clase " + this.getClass().getSimpleName());
        }
    }
    
    private static String getCondicion(Integer idAero, String modelo, Double hB, String fabricante, Double dN, Double pNominal, Double vIn, Double vCut, Integer lineas, Boolean regulacion, Double vNominal, Boolean pequeno, Double longBuje, ArrayList<Object[]> paramsPS) {
        String condicion = "";
        
        if (idAero != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_ID_AERO, "=", idAero);
        }
        if (modelo != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_MODELO, "=", modelo);
        }
        if (hB != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_H_B, "=", hB);
        }
        if (fabricante != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_FAB, "=", fabricante);
        }
        if (dN != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_D_N, "=", dN);
        }
        if (pNominal != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_P_NOM, "=", pNominal);
        }
        if (vIn != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_V_IN, "=", vIn);
        }
        if (vCut != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_V_CUT, "=", vCut);
        }
        if (lineas != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_LINEAS, "=", lineas);
        }
        if (regulacion != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_REG, "=", regulacion);
        }
        if (vNominal != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_V_NOM, "=", vNominal);
        }
        if (pequeno != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_PEQ, "=", pequeno);
        }

		//Campos externos
        if (longBuje != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, AeroExtraRA.CAMPO_LONG_BUJE, "=", longBuje);
        }
        
        return condicion;
    }

    //Función para obtener la colección de Aerogeneradores que se ajustan a los limites pasados
    public static ArrayList<AerogeneradorRA> getAeros(Integer idAero, String modelo, Double hB, String fabricante, Double dN, Double pNominal, Double vIn, Double vCut, Integer lineas, Boolean regulacion, Double vNominal, Boolean pequeno, Double longBuje, ArrayList<String> campos, String sqlExtra, Boolean distinct) throws SQLException, NoSuchFieldException {
        InteraccionBD interBD = new InteraccionBD();
        
        ArrayList<AerogeneradorRA> res = null;
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();

        condicion = getCondicion(idAero, modelo, hB, fabricante, dN, pNominal, vIn, vCut, lineas, regulacion, vNominal, pequeno, longBuje, paramsPS);

		//Añadimos la conectividad entre tablas
        if (longBuje != null) {
			condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_ID_AERO, InteraccionBD.ASIGNACION_CAMPOS, AeroExtraRA.CAMPO_ID_AERO);
        }

		if (campos == null || campos.isEmpty()) {
			Object[] camposAero = interBD.getCamposTabla(TABLA);
			Object[] camposAeroExtra = interBD.getCamposTabla(AeroExtraRA.TABLA);

			campos = new ArrayList<String>();

			int nCamposAero = camposAero.length;
			for (int i = 0; i < nCamposAero; i++) {
				campos.add(TABLA + "." + (String) camposAero[i]);
			}

			int nCamposAeroExtra = camposAeroExtra.length;
			for (int i = 0; i < nCamposAeroExtra; i++) {
				if (!campos.contains(TABLA + "." + (String) camposAeroExtra[i])) //Si el campo ya está es porque es de interrelación
					campos.add(AeroExtraRA.TABLA + "." + (String) camposAeroExtra[i]);
			}
		}

        String tabla = InteraccionBD.anadeTabla(null, TABLA);
        tabla = InteraccionBD.anadeTabla(tabla, AeroExtraRA.TABLA);

        //Por defecto lo devolvemos ordenado por aero
        if (sqlExtra == null || sqlExtra.trim().length() == 0) {
            String sqlOrderBy = InteraccionBD.anadeCampoOrderBy(null, CAMPO_ID_AERO);
            sqlExtra = InteraccionBD.anadeSqlExtra(null, sqlOrderBy);
        }

        ArrayList<Object[]> resAux = interBD.getDatosTabla(tabla, campos, condicion, paramsPS, sqlExtra, distinct);

        if (resAux != null) {
            int n = resAux.size();
            res = new ArrayList<AerogeneradorRA>();
            
            for (int i = 0; i < n; i++) {
               res.add(new AerogeneradorRA(resAux.get(i), campos));
            }
        }

        return res;
    }
    
    //Función para obtener el aero por Id
    public static AerogeneradorRA getAeroPorId (Integer idAero) throws SQLException, NoSuchFieldException {
        AerogeneradorRA res = null;
        
        ArrayList<AerogeneradorRA> resAux = getAeros(idAero, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
        
        if (resAux != null) {
            res = resAux.get(0);
        }

        return res;
    }
    
    //Función para obtener el aero por modelo y hB
    public static AerogeneradorRA getAeroPorModeloHb(String modelo, Double hB) throws SQLException, NoSuchFieldException {
        AerogeneradorRA res = null;
        
        ArrayList<AerogeneradorRA> resAux = getAeros(null, modelo, hB, null, null, null, null, null, null, null, null, null, null, null, null, null);
        
        if (resAux != null) {
            res = resAux.get(0);
        }

        return res;
    }
    
    //Función para obtener los modelos
    public static ArrayList<String> getModelos() throws SQLException, NoSuchFieldException {
        ArrayList<String> res = null;
        
        ArrayList<String> campos = new ArrayList<String>();
        
        //Solo nos interesan los modelos
        campos.add(CAMPO_MODELO);
        
        //Ordenamos por modelo
        String sqlOrderBy = InteraccionBD.anadeCampoOrderBy(null, CAMPO_MODELO);
        String sqlExtra = InteraccionBD.anadeSqlExtra(null, sqlOrderBy);
        
        ArrayList<AerogeneradorRA> resAux = getAeros(null, null, null, null, null, null, null, null, null, null, null, null, null, campos, sqlExtra, true);
        
        if (resAux != null) {
            int n = resAux.size();
            res = new ArrayList<String>();
            
            for (int i = 0; i < n; i++) {
                res.add(resAux.get(i).getModelo());
            }
        }

        return res;
    }
    
    //Función para obtener los modelos
    public static ArrayList<String> getHbsModelo(String modelo) throws SQLException, NoSuchFieldException {
        ArrayList<String> res = null;
        
        ArrayList<String> campos = new ArrayList<String>();
        
        //Solo nos interesan las alturas
        campos.add(CAMPO_H_B);
        
        //Ordenamos por altura
        String sqlOrderBy = InteraccionBD.anadeCampoOrderBy(null, CAMPO_H_B);
        String sqlExtra = InteraccionBD.anadeSqlExtra(null, sqlOrderBy);
        
        ArrayList<AerogeneradorRA> resAux = getAeros(null, modelo, null, null, null, null, null, null, null, null, null, null, null, campos, sqlExtra, true);
        
        if (resAux != null) {
            int n = resAux.size();
            res = new ArrayList<String>();
            
            for (int i = 0; i < n; i++) {
                res.add(resAux.get(i).getModelo());
            }
        }

        return res;
    }

    //Función para añadir una aero a la BD
    public static int insertAerogenerador(Integer idAero, String modelo, Double hB, String fabricante, Double dN, Double pNominal, Double vIn, Double vCut, Integer lineas, Boolean regulacion, Double vNominal, Boolean pequeno, Double longBuje, String sqlExtra) throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        String valores = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        ArrayList<String> campos = new ArrayList<String>();
        ArrayList<String> autoInc = new ArrayList<String>();
        ArrayList<String> condAutoInc = new ArrayList<String>();
        int res = 0, resExtra = 0;
        
        if (idAero != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, idAero);
            campos.add(CAMPO_ID_AERO);
        } else {    //Es campo clave, asignamos el incremento de la última
            valores = InteraccionBD.anadeCampoValorAutoInc(valores, CAMPO_ID_AERO);
            campos.add(CAMPO_ID_AERO);
            autoInc.add(CAMPO_ID_AERO);
        }
        if (modelo != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, modelo);
            campos.add(CAMPO_MODELO);
            condAutoInc.add(null);
        }
        if (hB != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, hB);
            campos.add(CAMPO_H_B);
        }
        if (fabricante != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, fabricante);
            campos.add(CAMPO_FAB);
        }
        if (dN != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, dN);
            campos.add(CAMPO_D_N);
        }
        if (pNominal != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, pNominal);
            campos.add(CAMPO_P_NOM);
        }
        if (vIn != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, vIn);
            campos.add(CAMPO_V_IN);
        }
        if (vCut != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, vCut);
            campos.add(CAMPO_V_CUT);
        }
        if (lineas != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, lineas);
            campos.add(CAMPO_LINEAS);
        }
        if (regulacion != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, regulacion);
            campos.add(CAMPO_REG);
        }
        if (vNominal != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, vNominal);
            campos.add(CAMPO_V_NOM);
        }
        if (pequeno != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, pequeno);
            campos.add(CAMPO_PEQ);
        }

        interBD.inicioTransaccion();
        res = interBD.insertDatosTabla(TABLA, campos, valores, paramsPS, sqlExtra, autoInc, condAutoInc);

		//Campos externos
        if (longBuje != null) {
			resExtra = AeroExtraRA.insertAeroExtra(idAero, longBuje, sqlExtra);

			if (res != resExtra) {
				interBD.rollback();

				res = res * resExtra;
			}
        }

        interBD.finTransaccion();
        
        return res;
    }
    
    public static int insertAerogenerador(AerogeneradorRA aero, String sqlExtra) throws SQLException {
        return insertAerogenerador(aero.idAero, aero.modelo, aero.hB, aero.fabricante, aero.dN, aero.pNominal, aero.vIn, aero.vCut, aero.lineas, aero.regulacion, aero.vNominal, aero.pequeno, aero.longBuje, sqlExtra);
    }
    
    //Función para añadir una aero a la BD
    public static int updateAerogenerador(Integer idAero, String modelo, Double hB, String fabricante, Double dN, Double pNominal, Double vIn, Double vCut, Integer lineas, Boolean regulacion, Double vNominal, Boolean pequeno, Double longBuje, Integer idAeroVal, String modeloVal, Double hBVal, String fabricanteVal, Double dNVal, Double pNominalVal, Double vInVal, Double vCutVal, Integer lineasVal, Boolean regulacionVal, Double vNominalVal, Boolean pequenoVal, Double longBujeVal, String sqlExtra) throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        ArrayList<String> campos = new ArrayList<String>();
        int res = 0, resExtra = 0;
        
        if (idAeroVal != null && !idAeroVal.equals(idAero)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, idAeroVal);
            campos.add(CAMPO_ID_AERO);
        }
        if (modeloVal != null && !modeloVal.equals(modelo)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, modeloVal);
            campos.add(CAMPO_MODELO);
        }
        if (hBVal != null && !hBVal.equals(hB)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, hBVal);
            campos.add(CAMPO_H_B);
        }
        if (fabricanteVal != null && !fabricanteVal.equals(fabricante)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, fabricanteVal);
            campos.add(CAMPO_FAB);
        }
        if (dNVal != null && !dNVal.equals(dN)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, dNVal);
            campos.add(CAMPO_D_N);
        }
        if (pNominalVal != null && !pNominalVal.equals(pNominal)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, pNominalVal);
            campos.add(CAMPO_P_NOM);
        }
        if (vInVal != null && !vInVal.equals(vIn)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, vInVal);
            campos.add(CAMPO_V_IN);
        }
        if (vCutVal != null && !vCutVal.equals(vCut)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, vCutVal);
            campos.add(CAMPO_V_CUT);
        }
        if (lineasVal != null && !lineasVal.equals(lineas)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, lineasVal);
            campos.add(CAMPO_LINEAS);
        }
        if (regulacionVal != null && !regulacionVal.equals(regulacion)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, regulacionVal);
            campos.add(CAMPO_REG);
        }
        if (vNominalVal != null && !vNominalVal.equals(vNominal)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, vNominalVal);
            campos.add(CAMPO_V_NOM);
        }
        if (pequenoVal != null && !pequenoVal.equals(pequeno)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, pequenoVal);
            campos.add(CAMPO_PEQ);
        }

        //Condiciones de actualización
        condicion = getCondicion(idAero, modelo, hB, fabricante, dN, pNominal, vIn, vCut, lineas, regulacion, vNominal, pequeno, null, paramsPS);

		ArrayList<String> tablas = new ArrayList<String>();
		tablas.add(TABLA);
		tablas.add(AeroExtraRA.TABLA);
        
        interBD.inicioTransaccion();
		res = interBD.updateDatosTablaFrom(TABLA, tablas, campos, condicion, paramsPS, sqlExtra);

		//Campos extermos
        if (longBujeVal != null && !longBujeVal.equals(longBuje)) {
			resExtra = AeroExtraRA.updateAeroExtra(idAero, longBuje, idAeroVal, longBujeVal, sqlExtra);

			if (res != resExtra) {
				interBD.rollback();

				res = res * resExtra;
			}
        }
        interBD.finTransaccion();
        
        return res;
    }
    
    public static int updateAerogenerador(AerogeneradorRA aeroViejo, AerogeneradorRA aeroNuevo, String sqlExtra) throws SQLException {
        return updateAerogenerador(aeroViejo.idAero, aeroViejo.modelo, aeroViejo.hB, aeroViejo.fabricante, aeroViejo.dN, aeroViejo.pNominal, aeroViejo.vIn, aeroViejo.vCut, aeroViejo.lineas, aeroViejo.regulacion, aeroViejo.vNominal, aeroViejo.pequeno, aeroViejo.longBuje, aeroNuevo.idAero, aeroNuevo.modelo, aeroNuevo.hB, aeroNuevo.fabricante, aeroNuevo.dN, aeroNuevo.pNominal, aeroNuevo.vIn, aeroNuevo.vCut, aeroNuevo.lineas, aeroNuevo.regulacion, aeroNuevo.vNominal, aeroNuevo.pequeno, aeroNuevo.longBuje, sqlExtra);
    }
    
    //Función para añadir/modificar una aero a la BD
    public static int insertOrUpdateAerogenerador(Integer idAero, String modelo, Double hB, String fabricante, Double dN, Double pNominal, Double vIn, Double vCut, Integer lineas, Boolean regulacion, Double vNominal, Boolean pequeno, Double longBuje, Integer idAeroVal, String modeloVal, Double hBVal, String fabricanteVal, Double dNVal, Double pNominalVal, Double vInVal, Double vCutVal, Integer lineasVal, Boolean regulacionVal, Double vNominalVal, Boolean pequenoVal, Double longBujeVal, String sqlExtra) throws SQLException {
        int res;
        
        res = updateAerogenerador(idAero, modelo, hB, fabricante, dN, pNominal, vIn, vCut, lineas, regulacion, vNominal, pequeno, longBuje, idAeroVal, modeloVal, hBVal, fabricanteVal, dNVal, pNominalVal, vInVal, vCutVal, lineasVal, regulacionVal, vNominalVal, pequenoVal, longBujeVal, sqlExtra);
        
        if (res < 0)
            res = insertAerogenerador(idAeroVal, modeloVal, hBVal, fabricanteVal, dNVal, pNominalVal, vInVal, vCutVal, lineasVal, regulacionVal, vNominalVal, pequenoVal, longBujeVal, sqlExtra);

        return res;
    }
    
    public static int insertOrUpdateAerogenerador(AerogeneradorRA aeroViejo, AerogeneradorRA aeroNuevo, String sqlExtra) throws SQLException {
        
        return insertOrUpdateAerogenerador(aeroViejo.idAero, aeroViejo.modelo, aeroViejo.hB, aeroViejo.fabricante, aeroViejo.dN, aeroViejo.pNominal, aeroViejo.vIn, aeroViejo.vCut, aeroViejo.lineas, aeroViejo.regulacion, aeroViejo.vNominal, aeroViejo.pequeno, aeroViejo.longBuje, aeroNuevo.idAero, aeroNuevo.modelo, aeroNuevo.hB, aeroNuevo.fabricante, aeroNuevo.dN, aeroNuevo.pNominal, aeroNuevo.vIn, aeroNuevo.vCut, aeroNuevo.lineas, aeroNuevo.regulacion, aeroNuevo.vNominal, aeroNuevo.pequeno, aeroNuevo.longBuje, sqlExtra);
    }
    
    //Función para eliminar Aerogeneradores que se ajustan a los limites pasados
    public static int deleteAerogeneradores(Integer idAero, String modelo, Double hB, String fabricante, Double dN, Double pNominal, Double vIn, Double vCut, Integer lineas, Boolean regulacion, Double vNominal, Boolean pequeno, Double longBuje, String sqlExtra) throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        int res = 0, resExtra = 0;

        condicion = getCondicion(idAero, modelo, hB, fabricante, dN, pNominal, vIn, vCut, lineas, regulacion, vNominal, pequeno, longBuje, paramsPS);

        interBD.inicioTransaccion();
        res  = interBD.deleteDatosTabla(TABLA, condicion, paramsPS, sqlExtra);

		if (res > 0) {
			resExtra = AeroExtraRA.deleteAeroExtras(idAero, longBuje, sqlExtra);
		}
        interBD.finTransaccion();
        
        return res;
    }
    
    public static int deleteAerogeneradores(AerogeneradorRA aero, String sqlExtra) throws SQLException {
        return deleteAerogeneradores(aero.idAero, aero.modelo, aero.hB, aero.fabricante, aero.dN, aero.pNominal, aero.vIn, aero.vCut, aero.lineas, aero.regulacion, aero.vNominal, aero.pequeno, aero.longBuje, sqlExtra);
    }
    
    public Object[] toObject() {
        return new Object[]{this.idAero, this.modelo, this.hB, this.fabricante, this.dN, this.pNominal, this.vIn, this.vCut, this.lineas, this.regulacion, this.vNominal, this.pequeno, this.longBuje};
    }
  
    public static Object[] getCamposTabla() throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        return interBD.getCamposTabla(TABLA);
    }
} 