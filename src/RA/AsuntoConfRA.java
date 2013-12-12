package RA;

import general.InteraccionBD;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;

public class AsuntoConfRA {
    private Integer idAsunto;
    private Character perMedidoOtro;
    private Long desdeFecha;
    private Long hastaFecha;
    private Double zRef;
    private Double z0Ref;
    private Double z0;
    private Double dirEnsayo;
    private Double amplitud;
    private Character velDerMed;
    private Character medidaPot;
    private Character tipoK;
    private Character tipoKRF;
    private Character tipoCalculoVel;
    private Integer desdeVel;
    private Integer hastaVel;
    
    public static final String TABLA = "AsuntoConfRA";
    public static final String CAMPO_ID_ASUNTO = "Id_asunto";
    public static final String CAMPO_PER_MEDIDO_OTRO = "PerMedidoOtro";
    public static final String CAMPO_DESDE_FECHA = "DesdeFecha";
    public static final String CAMPO_HASTA_FECHA = "HastaFecha";
    public static final String CAMPO_Z_REF = "Zref";
    public static final String CAMPO_Z0_REF = "Z0_ref";
    public static final String CAMPO_Z0 = "Z0";
    public static final String CAMPO_DIR_ENSAYO = "DirEnsayo";
    public static final String CAMPO_AMPLITUD = "Amplitud";
    public static final String CAMPO_VEL_DER_MED = "VelDerMed";
    public static final String CAMPO_MEDIDA_POT = "MedidaPot";
    public static final String CAMPO_TIPO_K = "TipoK";
    public static final String CAMPO_TIPO_K_RF = "TipoKRF";
    public static final String CAMPO_TIPO_CALCULO_VEL = "TipoCalculoVel";
    public static final String CAMPO_DESDE_VEL = "DesdeVel";
    public static final String CAMPO_HASTA_VEL = "HastaVel";
    
    public static final Character PER_MEDIDO = 'M';
    public static final Character PER_OTRO = 'O';
    public static final Character VEL_DER = 'D';
    public static final Character VEL_MED = 'M';
    public static final Character POT_MED = 'M';
    public static final Character POT_V_I = 'V';
    public static final Character K_TORRE = 'T';
    public static final Character K_NACELLE = 'N';
    public static final Character CALC_PROMEDIO = 'A';
    public static final Character CALC_PENDIENTE = 'S';
    
    public AsuntoConfRA(Integer idAsunto, Character perMedidoOtro, Long desdeFecha, Long hastaFecha, Double zRef, Double z0Ref, Double z0, Double dirEnsayo, Double amplitud, Character velDerMed, Character medidaPot, Character tipoK, Character tipoKRF, Character tipoCalculoVel, Integer desdeVel, Integer hastaVel) {
        this.idAsunto = idAsunto;
        this.perMedidoOtro = perMedidoOtro;
        this.desdeFecha = desdeFecha;
        this.hastaFecha = hastaFecha;
        this.zRef = zRef;
        this.z0Ref = z0Ref;
        this.z0 = z0;
        this.dirEnsayo = dirEnsayo;
        this.amplitud = amplitud;
        this.medidaPot = medidaPot;
        this.velDerMed = velDerMed;
        this.tipoK = tipoK;
        this.tipoKRF = tipoKRF;
        this.tipoCalculoVel = tipoCalculoVel;
        this.desdeVel = desdeVel;
        this.hastaVel = hastaVel;
    }
    
    public AsuntoConfRA(Object[] valores, ArrayList<String> campos) throws SQLException, NoSuchFieldException {
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
    
    public Double getAmplitud() {
        return amplitud;
    }

    public void setAmplitud(Double amplitud) {
        this.amplitud = amplitud;
    }

    public Long getDesdeFecha() {
        return desdeFecha;
    }

    public void setDesdeFecha(Long desdeFecha) {
        this.desdeFecha = desdeFecha;
    }

    public Integer getDesdeVel() {
        return desdeVel;
    }

    public void setDesdeVel(Integer desdeVel) {
        this.desdeVel = desdeVel;
    }

    public Double getDirEnsayo() {
        return dirEnsayo;
    }

    public void setDirEnsayo(Double dirEnsayo) {
        this.dirEnsayo = dirEnsayo;
    }

    public Long getHastaFecha() {
        return hastaFecha;
    }

    public void setHastaFecha(Long hastaFecha) {
        this.hastaFecha = hastaFecha;
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
    
    public Character getMedidaPot() {
        return medidaPot;
    }

    public void setMedidaPot(Character medidaPot) {
        this.medidaPot = medidaPot;
    }

    public Character getPerMedidoOtro() {
        return perMedidoOtro;
    }

    public void setPerMedidoOtro(Character perMedidoOtro) {
        this.perMedidoOtro = perMedidoOtro;
    }

    public Character getTipoCalculoVel() {
        return tipoCalculoVel;
    }

    public void setTipoCalculoVel(Character tipoCalculoVel) {
        this.tipoCalculoVel = tipoCalculoVel;
    }

    public Character getTipoK() {
        return tipoK;
    }

    public void setTipoK(Character tipoK) {
        this.tipoK = tipoK;
    }

    public Character getTipoKRF() {
        return tipoKRF;
    }

    public void setTipoKRF(Character tipoKRF) {
        this.tipoKRF = tipoKRF;
    }

    public Character getVelDerMed() {
        return velDerMed;
    }

    public void setVelDerMed(Character velDerMed) {
        this.velDerMed = velDerMed;
    }

    public Double getZ0() {
        return z0;
    }

    public void setZ0(Double z0) {
        this.z0 = z0;
    }

    public Double getZ0Ref() {
        return z0Ref;
    }

    public void setZ0Ref(Double z0Ref) {
        this.z0Ref = z0Ref;
    }

    public Double getZRef() {
        return zRef;
    }

    public void setZRef(Double zRef) {
        this.zRef = zRef;
    }

    //Función para asignar valores a campos de la clase según el campo de la BD
    private void asignaCampo(String campo, Object valor) throws NoSuchFieldException {
        if (campo.compareTo(CAMPO_ID_ASUNTO) == 0) {
            this.idAsunto = (Integer) valor;
        } else if (campo.compareTo(CAMPO_PER_MEDIDO_OTRO) == 0) {
            this.perMedidoOtro = ((String) valor).charAt(0);
        } else if (campo.compareTo(CAMPO_DESDE_FECHA) == 0) {
            this.desdeFecha = (Long) valor;
        } else if (campo.compareTo(CAMPO_HASTA_FECHA) == 0) {
            this.hastaFecha = (Long) valor;
        } else if (campo.compareTo(CAMPO_Z_REF) == 0) {
            this.zRef = valor != null ? ((BigDecimal) valor).doubleValue() : null;
        } else if (campo.compareTo(CAMPO_Z0_REF) == 0) {
            this.z0Ref = valor != null ? ((BigDecimal) valor).doubleValue() : null;
        } else if (campo.compareTo(CAMPO_Z0) == 0) {
            this.z0 = valor != null ? ((BigDecimal) valor).doubleValue() : null;
        } else if (campo.compareTo(CAMPO_DIR_ENSAYO) == 0) {
            this.dirEnsayo = valor != null ? ((BigDecimal) valor).doubleValue() : null;
        } else if (campo.compareTo(CAMPO_AMPLITUD) == 0) {
            this.amplitud = valor != null ? ((BigDecimal) valor).doubleValue() : null;
        } else if (campo.compareTo(CAMPO_VEL_DER_MED) == 0) {
            this.velDerMed = ((String) valor).charAt(0);
        } else if (campo.compareTo(CAMPO_MEDIDA_POT) == 0) {
            this.medidaPot = ((String) valor).charAt(0);
        } else if (campo.compareTo(CAMPO_TIPO_K) == 0) {
            this.tipoK = ((String) valor).charAt(0);
        } else if (campo.compareTo(CAMPO_TIPO_K_RF) == 0) {
            this.tipoKRF = ((String) valor).charAt(0);
        } else if (campo.compareTo(CAMPO_TIPO_CALCULO_VEL) == 0) {
            this.tipoCalculoVel = ((String) valor).charAt(0);
        } else if (campo.compareTo(CAMPO_DESDE_VEL) == 0) {
            this.desdeVel = (Integer) valor;
        } else if (campo.compareTo(CAMPO_HASTA_VEL) == 0) {
            this.hastaVel = (Integer) valor;
        } else {
            throw new NoSuchFieldException("No existe el campo en la clase " + this.getClass().getSimpleName());
        }
    }
    
    private static String getCondicion(Integer idAsunto, Character perMedidoOtro, Long desdeFecha, Long hastaFecha, Double zRef, Double z0Ref, Double z0, Double dirEnsayo, Double amplitud, Character velDerMed, Character medidaPot, Character tipoK, Character tipoKRF, Character tipoCalculoVel, Integer desdeVel, Integer hastaVel, ArrayList<Object[]> paramsPS) {
        String condicion = "";
        
        if (idAsunto != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_ID_ASUNTO, "=", idAsunto);
        }
        if (perMedidoOtro != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_PER_MEDIDO_OTRO, "=", perMedidoOtro);
        }
        if (desdeFecha != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_DESDE_FECHA, "=", desdeFecha);
        }
        if (hastaFecha != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_HASTA_FECHA, "=", hastaFecha);
        }
        if (zRef != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_Z_REF, "=", zRef);
        }
        if (z0Ref != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_Z0_REF, "=", z0Ref);
        }
        if (z0 != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_Z0, "=", z0);
        }
        if (dirEnsayo != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_DIR_ENSAYO, "=", dirEnsayo);
        }
        if (amplitud != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_AMPLITUD, "=", amplitud);
        }
        if (velDerMed != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_VEL_DER_MED, "=", velDerMed);
        }
        if (medidaPot != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_MEDIDA_POT, "=", medidaPot);
        }
        if (tipoK != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_TIPO_K, "=", tipoK);
        }
        if (tipoKRF != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_TIPO_K_RF, "=", tipoKRF);
        }
        if (tipoCalculoVel != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_TIPO_CALCULO_VEL, "=", tipoCalculoVel);
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
    public static ArrayList<AsuntoConfRA> getAsuntoConfs(Integer idAsunto, Character perMedidoOtro, Long desdeFecha, Long hastaFecha, Double zRef, Double z0Ref, Double z0, Double dirEnsayo, Double amplitud, Character velDerMed, Character medidaPot, Character tipoK, Character tipoKRF, Character tipoCalculoVel, Integer desdeVel, Integer hastaVel, ArrayList<String> campos, String sqlExtra, Boolean distinct) throws SQLException, NoSuchFieldException {
        InteraccionBD interBD = new InteraccionBD();
        
        ArrayList<AsuntoConfRA> res = null;
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();

        condicion = getCondicion(idAsunto, perMedidoOtro, desdeFecha, hastaFecha, zRef, z0Ref, z0, dirEnsayo, amplitud, velDerMed, medidaPot, tipoK, tipoKRF, tipoCalculoVel, desdeVel, hastaVel, paramsPS);
    
    //Por defecto lo devolvemos ordenado por asuntoConf
        if (sqlExtra == null || sqlExtra.trim().length() == 0) {
            String sqlOrderBy = InteraccionBD.anadeCampoOrderBy(null, CAMPO_ID_ASUNTO);
            sqlExtra = InteraccionBD.anadeSqlExtra(null, sqlOrderBy);
        }
               
        ArrayList<Object[]> resAux = interBD.getDatosTabla(TABLA, campos, condicion, paramsPS, sqlExtra, distinct);

        if (resAux != null) {
            int n = resAux.size();
            res = new ArrayList<AsuntoConfRA>();
            
            for (int i = 0; i < n; i++) {
               res.add(new AsuntoConfRA(resAux.get(i), campos));
            }
        }

        return res;
    }
    
    //Función para obtener la colección de AsuntoConf.que se ajustan a los limites pasados (clave)
    public static AsuntoConfRA getAsuntoConfPorIdAsunto(Integer idAsunto) throws SQLException, NoSuchFieldException {
        AsuntoConfRA res = null;
        ArrayList<AsuntoConfRA> resAux = getAsuntoConfs(idAsunto, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
        
        if (resAux != null) {
            res = resAux.get(0);
        }
        
        return res;
    }

    //Función para añadir un AsuntoConfRA a la BD
    public static int insertAsuntoConf(Integer idAsunto, Character perMedidoOtro, Long desdeFecha, Long hastaFecha, Double zRef, Double z0Ref, Double z0, Double dirEnsayo, Double amplitud, Character velDerMed, Character medidaPot, Character tipoK, Character tipoKRF, Character tipoCalculoVel, Integer desdeVel, Integer hastaVel, String sqlExtra) throws SQLException {
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
        if (perMedidoOtro != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, perMedidoOtro);
            campos.add(CAMPO_PER_MEDIDO_OTRO);
        }
        if (desdeFecha != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, desdeFecha);
            campos.add(CAMPO_DESDE_FECHA);
        }
        if (hastaFecha != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, hastaFecha);
            campos.add(CAMPO_HASTA_FECHA);
        }
        if (zRef != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, zRef);
            campos.add(CAMPO_Z_REF);
        }
        if (z0Ref != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, z0Ref);
            campos.add(CAMPO_Z0_REF);
        }
        if (z0 != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, z0);
            campos.add(CAMPO_Z0);
        }
        if (dirEnsayo != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, dirEnsayo);
            campos.add(CAMPO_DIR_ENSAYO);
        }
        if (amplitud != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, amplitud);
            campos.add(CAMPO_AMPLITUD);
        }
        if (velDerMed != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, velDerMed);
            campos.add(CAMPO_VEL_DER_MED);
        }
        if (medidaPot != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, medidaPot);
            campos.add(CAMPO_MEDIDA_POT);
        }
        if (tipoK != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, tipoK);
            campos.add(CAMPO_TIPO_K);
        }
        if (tipoKRF != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, tipoKRF);
            campos.add(CAMPO_TIPO_K_RF);
        }
        if (tipoCalculoVel != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, tipoCalculoVel);
            campos.add(CAMPO_TIPO_CALCULO_VEL);
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
    
    public static int insertAsuntoConf(AsuntoConfRA conf, String sqlExtra) throws SQLException {
        return insertAsuntoConf(conf.idAsunto, conf.perMedidoOtro, conf.desdeFecha, conf.hastaFecha, conf.zRef, conf.z0Ref, conf.z0, conf.dirEnsayo, conf.amplitud, conf.velDerMed, conf.medidaPot, conf.tipoK, conf.tipoKRF, conf.tipoCalculoVel, conf.desdeVel, conf.hastaVel, sqlExtra);
    }
    
    //Función para añadir un AsuntoConfRA a la BD
    public static int updateAsuntoConf(Integer idAsunto, Character perMedidoOtro, Long desdeFecha, Long hastaFecha, Double zRef, Double z0Ref, Double z0, Double dirEnsayo, Double amplitud, Character velDerMed, Character medidaPot, Character tipoK, Character tipoKRF, Character tipoCalculoVel, Integer desdeVel, Integer hastaVel, Integer idAsuntoVal, Character perMedidoOtroVal, Long desdeFechaVal, Long hastaFechaVal, Double zRefVal, Double z0RefVal, Double z0Val, Double dirEnsayoVal, Double amplitudVal, Character velDerMedVal, Character medidaPotVal, Character tipoKVal, Character tipoKRFVal, Character tipoCalculoVelVal, Integer desdeVelVal, Integer hastaVelVal, String sqlExtra) throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        ArrayList<String> campos = new ArrayList<String>();
        int res = 0;
        
        if (idAsuntoVal != null && !idAsuntoVal.equals(idAsunto)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, idAsuntoVal);
            campos.add(CAMPO_ID_ASUNTO);
        }
        if (perMedidoOtroVal != null && !perMedidoOtroVal.equals(perMedidoOtro)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, perMedidoOtroVal);
            campos.add(CAMPO_PER_MEDIDO_OTRO);
        }
        if (desdeFechaVal != null && !desdeFechaVal.equals(desdeFecha)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, desdeFechaVal);
            campos.add(CAMPO_DESDE_FECHA);
        }
        if (hastaFechaVal != null && !hastaFechaVal.equals(hastaFecha)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, hastaFechaVal);
            campos.add(CAMPO_HASTA_FECHA);
        }
        if (zRefVal != null && !zRefVal.equals(zRef)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, zRefVal);
            campos.add(CAMPO_Z_REF);
        }
        if (z0RefVal != null && !z0RefVal.equals(z0Ref)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, z0RefVal);
            campos.add(CAMPO_Z0_REF);
        }
        if (z0Val != null && !z0Val.equals(z0)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, z0Val);
            campos.add(CAMPO_Z0);
        }
        if (dirEnsayoVal != null && !dirEnsayoVal.equals(dirEnsayo)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, dirEnsayoVal);
            campos.add(CAMPO_DIR_ENSAYO);
        }
        if (amplitudVal != null && !amplitudVal.equals(amplitud)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, amplitudVal);
            campos.add(CAMPO_AMPLITUD);
        }
        if (velDerMedVal != null && !velDerMedVal.equals(velDerMed)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, velDerMedVal);
            campos.add(CAMPO_VEL_DER_MED);
        }
        if (medidaPotVal != null && !medidaPotVal.equals(medidaPot)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, medidaPotVal);
            campos.add(CAMPO_MEDIDA_POT);
        }
        if (tipoKVal != null && !tipoKVal.equals(tipoK)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, tipoKVal);
            campos.add(CAMPO_TIPO_K);
        }
        if (tipoKRFVal != null && !tipoKRFVal.equals(tipoKRF)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, tipoKRFVal);
            campos.add(CAMPO_TIPO_K_RF);
        }
        if (tipoCalculoVelVal != null && !tipoCalculoVelVal.equals(tipoCalculoVel)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, tipoCalculoVelVal);
            campos.add(CAMPO_TIPO_CALCULO_VEL);
        }
        if (desdeVelVal != null && !desdeVelVal.equals(desdeVel)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, desdeVelVal);
            campos.add(CAMPO_DESDE_VEL);
        }
        if (hastaVelVal != null && !hastaVelVal.equals(hastaVel)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, hastaVelVal);
            campos.add(CAMPO_HASTA_VEL);
        }
        
        //Condiciones de actualización
        condicion = getCondicion(idAsunto, perMedidoOtro, desdeFecha, hastaFecha, zRef, z0Ref, z0, dirEnsayo, amplitud, velDerMed, medidaPot, tipoK, tipoKRF, tipoCalculoVel, desdeVel, hastaVel, paramsPS);
                
        interBD.inicioTransaccion();
        res = interBD.updateDatosTabla(TABLA, campos, condicion, paramsPS, sqlExtra);
        interBD.finTransaccion();
        
        return res;
    }
    
    public static int updateAsuntoConf(AsuntoConfRA confVieja, AsuntoConfRA confNueva, String sqlExtra) throws SQLException {
        return updateAsuntoConf(confVieja.idAsunto, confVieja.perMedidoOtro, confVieja.desdeFecha, confVieja.hastaFecha, confVieja.zRef, confVieja.z0Ref, confVieja.z0, confVieja.dirEnsayo, confVieja.amplitud, confVieja.velDerMed, confVieja.medidaPot, confVieja.tipoK, confVieja.tipoKRF, confVieja.tipoCalculoVel, confVieja.desdeVel, confVieja.hastaVel, confNueva.idAsunto, confNueva.perMedidoOtro, confNueva.desdeFecha, confNueva.hastaFecha, confNueva.zRef, confNueva.z0Ref, confNueva.z0, confNueva.dirEnsayo, confNueva.amplitud, confNueva.velDerMed, confNueva.medidaPot, confNueva.tipoK, confNueva.tipoKRF, confNueva.tipoCalculoVel, confNueva.desdeVel, confNueva.hastaVel, sqlExtra);
    }
    
    //Función para añadir/modificar un AsuntoConfRA a la BD
    public static int insertOrUpdateAsuntoConf(Integer idAsunto, Character perMedidoOtro, Long desdeFecha, Long hastaFecha, Double zRef, Double z0Ref, Double z0, Double dirEnsayo, Double amplitud, Character velDerMed, Character medidaPot, Character tipoK, Character tipoKRF, Character tipoCalculoVel, Integer desdeVel, Integer hastaVel, Integer idAsuntoVal, Character perMedidoOtroVal, Long desdeFechaVal, Long hastaFechaVal, Double zRefVal, Double z0RefVal, Double z0Val, Double dirEnsayoVal, Double amplitudVal, Character velDerMedVal, Character medidaPotVal, Character tipoKVal, Character tipoKRFVal, Character tipoCalculoVelVal, Integer desdeVelVal, Integer hastaVelVal, String sqlExtra) throws SQLException {
        int res;
        
        res = updateAsuntoConf(idAsunto, perMedidoOtro, desdeFecha, hastaFecha, zRef, z0Ref, z0, dirEnsayo, amplitud, velDerMed, medidaPot, tipoK, tipoKRF, tipoCalculoVel, desdeVel, hastaVel, idAsuntoVal, perMedidoOtroVal, desdeFechaVal, hastaFechaVal, zRefVal, z0RefVal, z0Val, dirEnsayoVal, amplitudVal, velDerMedVal, medidaPotVal, tipoKVal, tipoKRFVal, tipoCalculoVelVal, desdeVelVal, hastaVelVal, sqlExtra);
        
        if (res < 0)
            res = insertAsuntoConf(idAsuntoVal, perMedidoOtroVal, desdeFechaVal, hastaFechaVal, zRefVal, z0RefVal, z0Val, dirEnsayoVal, amplitudVal, velDerMedVal, medidaPotVal, tipoKVal, tipoKRFVal, tipoCalculoVelVal, desdeVelVal, hastaVelVal, sqlExtra);

        return res;
    }
    
    public static int insertOrUpdateAsuntoConf(AsuntoConfRA confVieja, AsuntoConfRA confNueva, String sqlExtra) throws SQLException {
        int res = 0;
        
        if (confVieja == null)
            res =  insertAsuntoConf(confNueva, sqlExtra);
        else
            res = insertOrUpdateAsuntoConf(confVieja.idAsunto, confVieja.perMedidoOtro, confVieja.desdeFecha, confVieja.hastaFecha, confVieja.zRef, confVieja.z0Ref, confVieja.z0, confVieja.dirEnsayo, confVieja.amplitud, confVieja.velDerMed, confVieja.medidaPot, confVieja.tipoK, confVieja.tipoKRF, confVieja.tipoCalculoVel, confVieja.desdeVel, confVieja.hastaVel, confNueva.idAsunto, confNueva.perMedidoOtro, confNueva.desdeFecha, confNueva.hastaFecha, confNueva.zRef, confNueva.z0Ref, confNueva.z0, confNueva.dirEnsayo, confNueva.amplitud, confNueva.velDerMed, confNueva.medidaPot, confNueva.tipoK, confNueva.tipoKRF, confNueva.tipoCalculoVel, confNueva.desdeVel, confNueva.hastaVel, sqlExtra);
        
        return res;
    }
    
    //Función para eliminar AsuntoConfs que se ajustan a los limites pasados
    public static int deleteAsuntoConfs(Integer idAsunto, Character perMedidoOtro, Long desdeFecha, Long hastaFecha, Double zRef, Double z0Ref, Double z0, Double dirEnsayo, Double amplitud, 
            Character velDerMed, Character medidaPot, Character tipoK, Character tipoKRF, Character tipoCalculoVel, Integer desdeVel, Integer hastaVel, String sqlExtra) throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        int res = 0;

        condicion = getCondicion(idAsunto, perMedidoOtro, desdeFecha, hastaFecha, zRef, z0Ref, z0, dirEnsayo, amplitud, velDerMed, medidaPot, tipoK, tipoKRF, tipoCalculoVel, desdeVel, hastaVel, paramsPS);
        
        interBD.inicioTransaccion();
        res  = interBD.deleteDatosTabla(TABLA, condicion, paramsPS, sqlExtra);
        interBD.finTransaccion();
        
        return res;
    }
    
    public static int deleteAsuntoConfs(AsuntoConfRA asuntoConf, String sqlExtra) throws SQLException {
        return deleteAsuntoConfs(asuntoConf.idAsunto, asuntoConf.perMedidoOtro, asuntoConf.desdeFecha, asuntoConf.hastaFecha, asuntoConf.zRef, asuntoConf.z0Ref, asuntoConf.z0, asuntoConf.dirEnsayo, asuntoConf.amplitud, asuntoConf.velDerMed, asuntoConf.medidaPot, asuntoConf.tipoK, asuntoConf.tipoKRF, asuntoConf.tipoCalculoVel, asuntoConf.desdeVel, asuntoConf.hastaVel, sqlExtra);
    }
    
    public Object[] toObject() {
        return new Object[]{this.idAsunto, this.perMedidoOtro, this.desdeFecha, this.hastaFecha, this.zRef, this.z0Ref, this.z0, this.dirEnsayo, this.amplitud, this.velDerMed, this.medidaPot, this.tipoK, this.tipoKRF, this.tipoCalculoVel, this.desdeVel, this.hastaVel};
    }
  
    public static Object[] getCamposTabla() throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        return interBD.getCamposTabla(TABLA);
    }
} 