package RA;

import general.Auxiliares;
import general.InteraccionBD;
import general.TratFechas;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;

public class AsuntoIncert {
    private Integer idAsunto;
    private Long desdeFecha;
    private Integer idNorma;
    private Integer idTipoIncert;
    private Double valor;
    
    public static final String BD = InteraccionBD.PREF_BD_RA;
    public static final String TABLA = BD + "AsuntoIncert";
    public static final String CAMPO_ID_ASUNTO = "Id_asunto";
    public static final String CAMPO_DESDE_FECHA = "Desde_Fecha";
    public static final String CAMPO_ID_NORMA = "Id_Norma";
    public static final String CAMPO_ID_TIPO_INCERT = "Id_TipoIncert";
    public static final String CAMPO_VALOR = "Valor";
    
    public AsuntoIncert(Integer idAsunto, Long desdeFecha, Integer idNorma, Integer idTipoIncert, Double valor) {
        this.idAsunto = idAsunto;
        this.desdeFecha = desdeFecha;
        this.idNorma = idNorma;
        this.idTipoIncert = idTipoIncert;
        this.valor = valor;
    }
    
    public AsuntoIncert(Object[] valores, ArrayList<String> campos) throws SQLException, NoSuchFieldException {
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
    
    public Long getDesdeFecha() {
        return desdeFecha;
    }

    public void setDesdeFecha(Long desdeFecha) {
        this.desdeFecha = desdeFecha;
    }

    public Integer getIdAsunto() {
        return idAsunto;
    }

    public void setIdAsunto(Integer idAsunto) {
        this.idAsunto = idAsunto;
    }
    
    public Integer getIdNorma() {
        return idNorma;
    }

    public void setIdNorma(Integer idNorma) {
        this.idNorma = idNorma;
    }

    public Integer getIdTipoIncert() {
        return idTipoIncert;
    }

    public void setIdTipoIncert(Integer idTipoIncert) {
        this.idTipoIncert = idTipoIncert;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
        
    //Función para asignar valores a campos de la clase según el campo de la BD
    private void asignaCampo(String campo, Object valor) throws NoSuchFieldException {
        if (campo.compareTo(CAMPO_ID_ASUNTO) == 0) {
            this.idAsunto = (Integer) valor;
        } else if (campo.compareTo(CAMPO_DESDE_FECHA) == 0) {
            this.desdeFecha = (Long) valor;
        } else if (campo.compareTo(CAMPO_ID_NORMA) == 0) {
            this.idNorma = (Integer) valor;
        } else if (campo.compareTo(CAMPO_ID_TIPO_INCERT) == 0) {
            this.idTipoIncert = (Integer) valor;
        } else if (campo.compareTo(CAMPO_VALOR) == 0) {
            this.valor = valor != null ? ((BigDecimal) valor).doubleValue() : null;
        } else {
            //throw new NoSuchFieldException("No existe el campo en la clase " + this.getClass().getSimpleName());
			System.out.println("No existe el campo <" + campo + "> en la clase " + this.getClass().getSimpleName());
        }
    }
    
    private static String getCondicion(Integer idAsunto, Long desdeFecha, Integer idNorma, Integer idTipoIncert, Double valor, ArrayList<Object[]> paramsPS) {
        String condicion = "";
        
        if (idAsunto != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_ID_ASUNTO, "=", idAsunto);
        }
        if (desdeFecha != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_DESDE_FECHA, "=", desdeFecha);
        }
        if (idNorma != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_ID_NORMA, "=", idNorma);
        }
        if (idTipoIncert != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_ID_TIPO_INCERT, "=", idTipoIncert);
        }
        if (valor != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_VALOR, "=", valor);
        }
        
        return condicion;
    }

    //Función para obtener la colección de AsuntoIncerts que se ajustan a los limites pasados
    public static ArrayList<AsuntoIncert> getAsuntoIncerts(Integer idAsunto, Long desdeFecha, Integer idNorma, Integer idTipoIncert, Double valor, ArrayList<String> campos, String sqlExtra, Boolean distinct) throws SQLException, NoSuchFieldException {
        InteraccionBD interBD = new InteraccionBD();
        
        ArrayList<AsuntoIncert> res = null;
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();

        condicion = getCondicion(idAsunto, desdeFecha, idNorma, idTipoIncert, valor, paramsPS);
    
        //Por defecto lo devolvemos ordenado por Asunto, DesdeFecha
        if (sqlExtra == null || sqlExtra.trim().length() == 0) {
            String sqlOrderBy = InteraccionBD.anadeCampoOrderBy(null, CAMPO_ID_ASUNTO);
            sqlOrderBy = InteraccionBD.anadeCampoOrderBy(sqlOrderBy, CAMPO_DESDE_FECHA);
            sqlExtra = InteraccionBD.anadeSqlExtra(null, sqlOrderBy);
        }
               
        ArrayList<Object[]> resAux = interBD.getDatosTabla(TABLA, campos, condicion, paramsPS, sqlExtra, distinct);

        if (resAux != null) {
            int n = resAux.size();
            res = new ArrayList<AsuntoIncert>();
            
            for (int i = 0; i < n; i++) {
               res.add(new AsuntoIncert(resAux.get(i), campos));
            }
        }

        return res;
    }

    //Función para obtener la colección de AsuntoIncert.que se ajustan a los limites pasados (clave)
    public static AsuntoIncert getAsuntoIncertPorIdAsunto(Integer idAsunto) throws SQLException, NoSuchFieldException {
        AsuntoIncert res = null;
        ArrayList<AsuntoIncert> resAux = getAsuntoIncerts(idAsunto, null, null, null, null, null, null, null);
        
        if (resAux != null) {
            res = resAux.get(0);
        }
        
        return res;
    }

    //Función para obtener la colección de AsuntoIncert que se ajustan a los limites pasados (clave)
    public static ArrayList<AsuntoIncert> getAsuntoIncertPorRangoFechas(Integer idAsunto, Integer idNorma, Long desdeFecha, Long hastaFecha) throws SQLException, NoSuchFieldException {
        ArrayList<AsuntoIncert> res = null;
        ArrayList<AsuntoIncert> resAux = getAsuntoIncerts(idAsunto, null, idNorma, null, null, null, null, null);
        
        int nResAux =  resAux != null ? resAux.size() : 0;
        
        Long desdeFechaIncert;
        
        //Almacenamos la fecha míima y máxima válidas para nuestro rango
        Long fechaMinIncert = TratFechas.millisFecha(TratFechas.FECHA_MIN);
        Long fechaMaxIncert = TratFechas.millisFecha(TratFechas.FECHA_MAX);
        
        if (desdeFecha == null)
            desdeFecha = fechaMinIncert;
        if (hastaFecha == null)
            hastaFecha = fechaMaxIncert;
        
        for (int i = 0; i < nResAux; i++) {
            desdeFechaIncert = resAux.get(i).getDesdeFecha();
            
            if (desdeFechaIncert <= desdeFecha && desdeFechaIncert > fechaMinIncert)
                fechaMinIncert = desdeFechaIncert;
            if (desdeFechaIncert >= hastaFecha && desdeFechaIncert < fechaMaxIncert)
                fechaMaxIncert = desdeFechaIncert;
        }
        
        if (nResAux > 0) {
            res = new ArrayList<AsuntoIncert>();
            
            for (int i = 0; i < nResAux; i++) {
                desdeFechaIncert = resAux.get(i).getDesdeFecha();
                
                if (desdeFechaIncert >= fechaMinIncert && desdeFechaIncert <= fechaMaxIncert)
                        res.add(resAux.get(i));
                else if (desdeFechaIncert > fechaMaxIncert)
                    break;
            }
        }
        
        return res;
    }

    //Función para añadir un AsuntoIncertRA a la BD
    public static int insertAsuntoIncert(Integer idAsunto, Long desdeFecha, Integer idNorma, Integer idTipoIncert, Double valor, String sqlExtra) throws SQLException {
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
        if (desdeFecha != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, desdeFecha);
            campos.add(CAMPO_DESDE_FECHA);
        }
        if (idNorma != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, idNorma);
            campos.add(CAMPO_ID_NORMA);
        }
        if (idTipoIncert != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, idTipoIncert);
            campos.add(CAMPO_ID_TIPO_INCERT);
        }
        if (valor != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, valor);
            campos.add(CAMPO_VALOR);
        }
        
        interBD.inicioTransaccion();
        res = interBD.insertDatosTabla(TABLA, campos, valores, paramsPS, sqlExtra, autoInc, condAutoInc);
        interBD.finTransaccion();
        
        return res;
    }
    
    public static int insertAsuntoIncert(AsuntoIncert incert, String sqlExtra) throws SQLException {
        return insertAsuntoIncert(incert.idAsunto, incert.desdeFecha, incert.idNorma, incert.idTipoIncert, incert.valor, sqlExtra);
    }
    
    //Función para añadir un AsuntoIncertRA a la BD
    public static int updateAsuntoIncert(Integer idAsunto, Long desdeFecha, Integer idNorma, Integer idTipoIncert, Double valor, 
            Integer idAsuntoVal, Long desdeFechaVal, Integer idNormaVal, Integer idTipoIncertVal, Double valorVal, String sqlExtra) throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        ArrayList<String> campos = new ArrayList<String>();
        int res = 0;
        
        if (idAsuntoVal != null && !idAsuntoVal.equals(idAsunto)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, idAsuntoVal);
            campos.add(CAMPO_ID_ASUNTO);
        }
        if (desdeFechaVal != null && !desdeFechaVal.equals(desdeFecha)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, desdeFechaVal);
            campos.add(CAMPO_DESDE_FECHA);
        }
        if (idNormaVal != null && !idNormaVal.equals(idNorma)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, idNormaVal);
            campos.add(CAMPO_ID_NORMA);
        }
        if (idTipoIncertVal != null && !idTipoIncertVal.equals(idTipoIncert)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, idTipoIncertVal);
            campos.add(CAMPO_ID_TIPO_INCERT);
        }
        if (valorVal != null && !valorVal.equals(valor)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, valorVal);
            campos.add(CAMPO_VALOR);
        }
        
        //Condiciones de actualización
        condicion = getCondicion(idAsunto, desdeFecha, idNorma, idTipoIncert, valor, paramsPS);
                
        interBD.inicioTransaccion();
        res = interBD.updateDatosTabla(TABLA, campos, condicion, paramsPS, sqlExtra);
        interBD.finTransaccion();
        
        return res;
    }
    
    public static int updateAsuntoIncert(AsuntoIncert incertVieja, AsuntoIncert incertNueva, String sqlExtra) throws SQLException {
        return updateAsuntoIncert(incertVieja.idAsunto, incertVieja.desdeFecha, incertVieja.idNorma, incertVieja.idTipoIncert, incertVieja.valor, 
                incertNueva.idAsunto, incertNueva.desdeFecha, incertNueva.idNorma, incertNueva.idTipoIncert, incertNueva.valor, sqlExtra);
    }
    
    //Función para añadir/modificar un AsuntoIncertRA a la BD
    public static int insertOrUpdateAsuntoIncert(Integer idAsunto, Long desdeFecha, Integer idNorma, Integer idTipoIncert, Double valor, 
            Integer idAsuntoVal, Long desdeFechaVal, Integer idNormaVal, Integer idTipoIncertVal, Double valorVal, String sqlExtra) throws SQLException {
        int res;
        
        res = updateAsuntoIncert(idAsunto, desdeFecha, idNorma, idTipoIncert, valor, idAsuntoVal, desdeFechaVal, idNormaVal, idTipoIncertVal, valorVal, sqlExtra);
        
        if (res < 0)
            res = insertAsuntoIncert(idAsuntoVal, desdeFechaVal, idNormaVal, idTipoIncertVal, valorVal, sqlExtra);

        return res;
    }
    
    public static int insertOrUpdateAsuntoIncert(AsuntoIncert incertVieja, AsuntoIncert incertNueva, String sqlExtra) throws SQLException {
        int res = 0;
        
        if (incertVieja == null)
            res =  insertAsuntoIncert(incertNueva, sqlExtra);
        else
            res = insertOrUpdateAsuntoIncert(incertVieja.idAsunto, incertVieja.desdeFecha, incertVieja.idNorma, incertVieja.idTipoIncert, incertVieja.valor, 
                    incertNueva.idAsunto, incertNueva.desdeFecha, incertNueva.idNorma, incertNueva.idTipoIncert, incertNueva.valor, sqlExtra);
        
        return res;
    }
    
    //Función para eliminar AsuntoIncerts que se ajustan a los limites pasados
    public static int deleteAsuntoIncerts(Integer idAsunto, Long desdeFecha, Integer idNorma, Integer idTipoIncert, Double valor, String sqlExtra) throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        int res = 0;

        condicion = getCondicion(idAsunto, desdeFecha, idNorma, idTipoIncert, valor, paramsPS);
        
        interBD.inicioTransaccion();
        res  = interBD.deleteDatosTabla(TABLA, condicion, paramsPS, sqlExtra);
        interBD.finTransaccion();
        
        return res;
    }
    
    public static int deleteAsuntoIncerts(AsuntoIncert AsuntoIncert, String sqlExtra) throws SQLException {
        return deleteAsuntoIncerts(AsuntoIncert.idAsunto, AsuntoIncert.desdeFecha, AsuntoIncert.idNorma, AsuntoIncert.idTipoIncert, AsuntoIncert.valor, sqlExtra);
    }
    
    public Object[] toObject() {
        return new Object[]{this.idAsunto, this.desdeFecha, this.idNorma, this.idTipoIncert, this.valor};
    }
  
    public static Object[] getCamposTabla() throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        return interBD.getCamposTabla(TABLA);
    }

    //Función para eliminar del array de incertidumbres aquellas por cálculo de la velocidad que no se correspondan con el cálculo realizado
    public static void filtrarVelocidad(ArrayList<AsuntoIncert> incertidumbres, int calculoVel) {
        int nIncert = incertidumbres != null ? incertidumbres.size() : 0;
        
        if (nIncert > 0) {
            ArrayList<Integer> posEliminar = new ArrayList<Integer>();

            Integer idTipoIncert;
            for (int i = 0; i < nIncert; i++) {
                idTipoIncert = incertidumbres.get(i).getIdTipoIncert();
                
                if ((idTipoIncert.equals(TipoIncert.ID_TIPO_INCERT_VEL_DERIVADA) && calculoVel != DatosRA2.VEL_DERIVADA_CP) || (idTipoIncert.equals(TipoIncert.ID_TIPO_INCERT_VEL_MEDIDA) && calculoVel != DatosRA2.VEL_MEDIDA))
                    posEliminar.add(i);
            }
            
            int nPos = posEliminar.size();
            
            //Eliminamos de mayor a menor para no tener problemas
            for (int i = nPos - 1; i >= 0; i--)
                incertidumbres.remove(posEliminar.get(i).intValue());
        }
    }
    
    //Función para eliminar del array de incertidumbres aquellas por tipo de análisis no se correspondan con el cálculo realizado
    public static void filtrarTipoAnalisis(ArrayList<AsuntoIncert> incertidumbres, String tipoAnalisis, Integer idNorma) {
        if (!idNorma.equals(NormaRA.ID_NORMA_IEC_3_0)) {
            int nIncert = incertidumbres != null ? incertidumbres.size() : 0;

            if (nIncert > 0) {
                ArrayList<Integer> posEliminar = new ArrayList<Integer>();

                Integer idTipoIncert;
                for (int i = 0; i < nIncert; i++) {
                    idTipoIncert = incertidumbres.get(i).getIdTipoIncert();

                    if (    (idTipoIncert.equals(TipoIncert.ID_TIPO_INCERT_MESA_SPL) && !tipoAnalisis.equals(Auxiliares.TIPO_SPL)) || 
                            (idTipoIncert.equals(TipoIncert.ID_TIPO_INCERT_MESA_OCT_FFT) && !(tipoAnalisis.equals(Auxiliares.TIPO_OCT) || tipoAnalisis.equals(Auxiliares.TIPO_FFT))))
                        posEliminar.add(i);
                }

                int nPos = posEliminar.size();

                //Eliminamos de mayor a menor para no tener problemas
                for (int i = nPos - 1; i >= 0; i--)
                    incertidumbres.remove(posEliminar.get(i).intValue());
            }
        }
    }
    
    //Función que devuelve la fecha de inicio de incertidumbres que corresponde a una fecha dada
    //incertidumbres vienen ordenadas por asunto-fecha
    public static Long getFechaIniIncertParaFecha(ArrayList<AsuntoIncert> incertidumbres, Long fechaHora) {
        Long res = null;
        
        int nIncert = incertidumbres != null ? incertidumbres.size() : 0;
        
        Long fechaHoraAct;
        
        for (int i = 0; i < nIncert; i++) {
            fechaHoraAct = incertidumbres.get(i).getDesdeFecha();
            
            if (fechaHoraAct.equals(fechaHora)) {
                res = fechaHoraAct;
                break;
            } else if (fechaHoraAct > fechaHora)
                break;
            
            if (res == null || fechaHoraAct > res)
                res = fechaHoraAct;
        }        
        
        return res;
    }
} 