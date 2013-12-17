package RA;

import general.InteraccionBD;
import general.TratFechas;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class InsercionRA {
    private Integer idAsunto;
    private Integer idSite;
    private Integer numInser;
    private Integer fase;
    private String ruta;
    private Long fechaIni;
    private Long fechaFin;
    private Long fecha;
    private Integer responsable;
    private Integer rf;
    
    public static final String BD = InteraccionBD.PREF_BD_GENERAL;
    public static final String TABLA = BD + "Insercion";
    public static final String CAMPO_ID_ASUNTO = "Asunto";
    public static final String CAMPO_ID_SITE = "Site";
    public static final String CAMPO_NUM_INSER = "Nº";
    public static final String CAMPO_FASE = "Fase";
    public static final String CAMPO_RUTA = "Ruta";
    public static final String CAMPO_F_INI = "Fini";
    public static final String CAMPO_F_FIN = "Ffin";
    public static final String CAMPO_FECHA = "Fecha";
    public static final String CAMPO_RESP = "Responsable";
    public static final String CAMPO_RF = "RF";
    
    public static final int FASE_SC = 1;
    public static final int FASE_PC = 2;
    public static final int FASE_PC_ME = 3;
    public static final int FASE_DR_ME = 4;
    public static final int FASE_RA = 5;
    
    private static final String NOM_FASE_SC = "SC";
    private static final String NOM_FASE_PC = "PC";
    private static final String NOM_FASE_PC_ME = "PC_ME";
    private static final String NOM_FASE_DR_ME = "DR_ME";
    private static final String NOM_FASE_RA = "RA";
    
    private static final String FORMATO_FECHA = "ddMMyyyy HHmm";
    private static final String FORMATO_FECHA_2 = "dd-MM-yyyy HH:mm";

    public InsercionRA(Integer idAsunto, Integer idSite, Integer numInser, Integer fase, String ruta, Long fechaIni, Long fechaFin, Long fecha, Integer responsable, Integer rf) {
        this.idAsunto = idAsunto;
        this.idSite = idSite;
        this.numInser = numInser;
        this.fase = fase;
        this.ruta = ruta;
        this.fechaIni = fechaIni;
        this.fechaFin = fechaFin;
        this.fecha = fecha;
        this.responsable = responsable;
        this.rf = rf;
    }
    
    public InsercionRA(Object[] valores, ArrayList<String> campos) throws SQLException, NoSuchFieldException {
        int nValores = valores.length;
        
        if (campos == null) { //Si se han pedido todos los campos sin indicarlos (SELECT * FROM ...)
            InteraccionBD inserBD = new InteraccionBD();
            
            Object[] camposAux = inserBD.getCamposTabla(TABLA);
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
    
    public Integer getFase() {
        return fase;
    }

    public void setFase(Integer fase) {
        this.fase = fase;
    }

    public Long getFecha() {
        return fecha;
    }

    public void setFecha(Long fecha) {
        this.fecha = fecha;
    }

    public Long getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Long fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Long getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(Long fechaIni) {
        this.fechaIni = fechaIni;
    }

    public Integer getIdAsunto() {
        return idAsunto;
    }

    public void setIdAsunto(Integer idAsunto) {
        this.idAsunto = idAsunto;
    }

    public Integer getIdSite() {
        return idSite;
    }

    public void setIdSite(Integer idSite) {
        this.idSite = idSite;
    }

    public Integer getNumInser() {
        return numInser;
    }

    public void setNumInser(Integer numInser) {
        this.numInser = numInser;
    }

    public Integer getResponsable() {
        return responsable;
    }

    public void setResponsable(Integer responsable) {
        this.responsable = responsable;
    }

    public Integer getRf() {
        return rf;
    }

    public void setRf(Integer rf) {
        this.rf = rf;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
    
    public String getNomFase() {
        String res = null;
        
        switch (this.fase) {
            case FASE_SC:
                res = NOM_FASE_SC;
                break;
            case FASE_PC:
                res = NOM_FASE_PC;
                break;
            case FASE_PC_ME:
                res = NOM_FASE_PC_ME;
                break;
            case FASE_DR_ME:
                res = NOM_FASE_DR_ME;
                break;
            case FASE_RA:
            default:
                res = NOM_FASE_RA;
        }
        
        return res;
    }
    
    //Función para asignar valores a campos de la clase según el campo de la BD
    private void asignaCampo(String campo, Object valor) throws NoSuchFieldException {
        if (campo.compareTo(CAMPO_ID_ASUNTO) == 0) {
            this.idAsunto = (Integer) valor;
        } else if (campo.compareTo(CAMPO_ID_SITE) == 0) {
            this.idSite = (Integer) valor;
        } else if (campo.compareTo(CAMPO_NUM_INSER) == 0) {
            this.numInser = (Integer) valor;
        } else if (campo.compareTo(CAMPO_FASE) == 0) {
            this.fase = (Integer) valor;
        } else if (campo.compareTo(CAMPO_RUTA) == 0) {
            this.ruta = (String) valor;
        } else if (campo.compareTo(CAMPO_F_INI) == 0) {
            this.fechaIni = TratFechas.millisFechaGen((String) valor, FORMATO_FECHA);
        } else if (campo.compareTo(CAMPO_F_FIN) == 0) {
            this.fechaFin = TratFechas.millisFechaGen((String) valor, FORMATO_FECHA);
        } else if (campo.compareTo(CAMPO_FECHA) == 0) {
            this.fecha = TratFechas.millisFechaGen((String) valor, FORMATO_FECHA_2);
        } else if (campo.compareTo(CAMPO_RESP) == 0) {
            this.responsable = (Integer) valor;
        } else if (campo.compareTo(CAMPO_RF) == 0) {
            this.rf = (Integer) valor;
        } else {
            throw new NoSuchFieldException("No existe el campo en la clase " + this.getClass().getSimpleName());
        }
    }
    
    private static String getCondicion(Integer idAsunto, Integer idSite, Integer numInser, Integer fase, String ruta, 
            Long fechaIni, Long fechaFin, Long fecha, Integer responsable, Integer rf, ArrayList<Object[]> paramsPS) {
        String condicion = "";
        
        if (idAsunto != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_ID_ASUNTO, "=", idAsunto);
        }
        if (idSite != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_ID_SITE, "=", idSite);
        }
        if (numInser != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_NUM_INSER, "=", numInser);
        }
        if (fase != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_FASE, "=", fase);
        }
        if (ruta != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_RUTA, "=", ruta);
        }
        if (fechaIni != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_F_INI, "=", TratFechas.toStringFechaGen(fechaIni, FORMATO_FECHA));
        }
        if (fechaFin != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_F_FIN, "=", TratFechas.toStringFechaGen(fechaFin, FORMATO_FECHA));
        }
        if (fecha != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_FECHA, "=", TratFechas.toStringFechaGen(fecha, FORMATO_FECHA_2));
        }
        if (responsable != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_RESP, "=", responsable);
        }
        if (rf != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_RF, "=", rf);
        }
        
        return condicion;
    }

    //Función para obtener la colección de Inserciones que se ajustan a los limites pasados
    public static ArrayList<InsercionRA> getInserciones(Integer idAsunto, Integer idSite, Integer numInser, Integer fase, String ruta, 
            Long fechaIni, Long fechaFin, Long fecha, Integer responsable, Integer rf,
            ArrayList<String> campos, String sqlExtra, Boolean distinct) throws SQLException, NoSuchFieldException {
        InteraccionBD inserBD = new InteraccionBD();
        
        ArrayList<InsercionRA> res = null;
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();

        condicion = getCondicion(idAsunto, idSite, numInser, fase, ruta, fechaIni, fechaFin, fecha, responsable, rf, paramsPS);
        
        //Por defecto lo devolvemos ordenado por asunto, site, insercion
        if (sqlExtra == null || sqlExtra.trim().length() == 0) {
            String sqlOrderBy = InteraccionBD.anadeCampoOrderBy(null, CAMPO_ID_ASUNTO);
            sqlOrderBy = InteraccionBD.anadeCampoOrderBy(sqlOrderBy, CAMPO_ID_SITE);
            sqlOrderBy = InteraccionBD.anadeCampoOrderBy(sqlOrderBy, CAMPO_NUM_INSER);
            sqlExtra = InteraccionBD.anadeSqlExtra(null, sqlOrderBy);
        }

        ArrayList<Object[]> resAux = inserBD.getDatosTabla(TABLA, campos, condicion, paramsPS, sqlExtra, distinct);

        if (resAux != null) {
            int n = resAux.size();
            res = new ArrayList<InsercionRA>();
            
            for (int i = 0; i < n; i++) {
               res.add(new InsercionRA(resAux.get(i), campos));
            }
        }

        return res;
    }
    
    //Función para obtener la colección de Inserciones que se ajustan a los limites pasados
    public static ArrayList<InsercionRA> getInsercionesAsuntoSite(Integer idAsunto, Integer idSite) throws SQLException, NoSuchFieldException {
        return getInserciones(idAsunto, idSite, null, null, null, null, null, null, null, null, null, null, null);
    }

    //Función para añadir una insercion a la BD
    public static int insertInsercion(Integer idAsunto, Integer idSite, Integer numInser, Integer fase, String ruta, 
            Long fechaIni, Long fechaFin, Long fecha, Integer responsable, Integer rf, String sqlExtra) throws SQLException {
        InteraccionBD inserBD = new InteraccionBD();
        
        String valores = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        ArrayList<Object[]> paramsPSAutoInc = new ArrayList<Object[]>();
        ArrayList<String> campos = new ArrayList<String>();
        ArrayList<String> autoInc = new ArrayList<String>();
        ArrayList<String> condAutoInc = new ArrayList<String>();
        String cond = null;
        int res = 0;
        
        if (idAsunto != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, idAsunto);
            campos.add(CAMPO_ID_ASUNTO);
        }
        if (idSite != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, idSite);
            campos.add(CAMPO_ID_SITE);
        }
        if (numInser != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, numInser);
            campos.add(CAMPO_NUM_INSER);
        } else {    //Es campo clave, asignamos el incremento de la última
            if (idAsunto != null && idSite != null) {
                valores = InteraccionBD.anadeCampoValorAutoInc(valores, CAMPO_NUM_INSER);
                campos.add(CAMPO_NUM_INSER);
                autoInc.add(CAMPO_NUM_INSER);

                //Se incrementa donde coincidan asunto y site
                cond = InteraccionBD.anadeCampoCondicion(cond, paramsPSAutoInc, CAMPO_ID_ASUNTO, "=", idAsunto);
                cond = InteraccionBD.anadeCampoCondicion(cond, paramsPSAutoInc, CAMPO_ID_SITE, "=", idSite);
                condAutoInc.add(cond);
            }
        }
        if (fase != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, fase);
            campos.add(CAMPO_FASE);
        }
        if (ruta != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, ruta);
            campos.add(CAMPO_RUTA);
        }
        if (fechaIni != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, TratFechas.toStringFechaGen(fechaIni, FORMATO_FECHA));
            campos.add(CAMPO_F_INI);
        }
        if (fechaFin != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, TratFechas.toStringFechaGen(fechaFin, FORMATO_FECHA));
            campos.add(CAMPO_F_FIN);
        }
        if (fecha != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, TratFechas.toStringFechaGen(fecha, FORMATO_FECHA_2));
            campos.add(CAMPO_FECHA);
        } else { //Si no se pasa fecha, será la actual
            Date d = new Date();
            
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, TratFechas.toStringFechaGen(d.getTime(), FORMATO_FECHA_2));
            campos.add(CAMPO_FECHA);
        }
        if (responsable != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, responsable);
            campos.add(CAMPO_RESP);
        }
        if (rf != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, rf);
            campos.add(CAMPO_RF);
        }

        InteraccionBD.anadeParamsPS(paramsPS, paramsPSAutoInc);
        
        inserBD.inicioTransaccion();
        res = inserBD.insertDatosTabla(TABLA, campos, valores, paramsPS, sqlExtra, autoInc, condAutoInc);
        inserBD.finTransaccion();
        
        return res;
    }
    
    public static int insertInsercion(InsercionRA insercion, String sqlExtra) throws SQLException {
        return insertInsercion(insercion.idAsunto, insercion.idSite, insercion.numInser, insercion.fase, insercion.ruta, 
            insercion.fechaIni, insercion.fechaFin, insercion.fecha, insercion.responsable, insercion.rf, sqlExtra);
    }
    
    //Función para añadir una insercion a la BD
    public static int updateInsercion(Integer idAsunto, Integer idSite, Integer numInser, Integer fase, String ruta, 
            Long fechaIni, Long fechaFin, Long fecha, Integer responsable, Integer rf,
            Integer idAsuntoVal, Integer idSiteVal, Integer numInserVal, Integer faseVal, String rutaVal, 
            Long fechaIniVal, Long fechaFinVal, Long fechaVal, Integer responsableVal, Integer rfVal, String sqlExtra) throws SQLException {
        InteraccionBD inserBD = new InteraccionBD();
        
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        ArrayList<String> campos = new ArrayList<String>();
        int res = 0;
        
        if (idAsuntoVal != null && !idAsuntoVal.equals(idAsunto)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, idAsuntoVal);
            campos.add(CAMPO_ID_ASUNTO);
        }
        if (idSiteVal != null && !idSiteVal.equals(idSite)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, idSiteVal);
            campos.add(CAMPO_ID_SITE);        
        }
        if (numInserVal != null && !numInserVal.equals(numInser)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, numInserVal);
            campos.add(CAMPO_NUM_INSER);        
        }
        if (faseVal != null&& !faseVal.equals(fase)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, faseVal);
            campos.add(CAMPO_FASE);
        }
        if (rutaVal != null&& !rutaVal.equals(ruta)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, rutaVal);
            campos.add(CAMPO_RUTA);
        }
        if (fechaIniVal != null&& !fechaIniVal.equals(fechaIni)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, TratFechas.toStringFechaGen(fechaIniVal, FORMATO_FECHA));
            campos.add(CAMPO_F_INI);
        }
        if (fechaFinVal != null&& !fechaFinVal.equals(fechaFin)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, TratFechas.toStringFechaGen(fechaFinVal, FORMATO_FECHA));
            campos.add(CAMPO_F_FIN);
        }
        if (fechaVal != null&& !fechaVal.equals(fecha)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, TratFechas.toStringFechaGen(fechaVal, FORMATO_FECHA_2));
            campos.add(CAMPO_FECHA);
        }
        if (responsableVal != null&& !responsableVal.equals(responsable)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, responsableVal);
            campos.add(CAMPO_RESP);
        }
        if (rfVal != null&& !rfVal.equals(rf)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, rfVal);
            campos.add(CAMPO_RF);
        }

        //Condiciones de actualización
        condicion = getCondicion(idAsunto, idSite, numInser, fase, ruta, fechaIni, fechaFin, fecha, responsable, rf, paramsPS);
        
        inserBD.inicioTransaccion();
        res = inserBD.updateDatosTabla(TABLA, campos, condicion, paramsPS, sqlExtra);
        inserBD.finTransaccion();
        
        return res;
    }
    
    public static int updateInsercion(InsercionRA inserVieja, InsercionRA inserNueva, String sqlExtra) throws SQLException {
        return updateInsercion(inserVieja.idAsunto, inserVieja.idSite, inserVieja.numInser, inserVieja.fase, inserVieja.ruta, 
            inserVieja.fechaIni, inserVieja.fechaFin, inserVieja.fecha, inserVieja.responsable, inserVieja.rf,
            inserNueva.idAsunto, inserNueva.idSite, inserNueva.numInser, inserNueva.fase, inserNueva.ruta, 
            inserNueva.fechaIni, inserNueva.fechaFin, inserNueva.fecha, inserNueva.responsable, inserNueva.rf, sqlExtra);
    }
    
    //Función para añadir/modificar una insercion a la BD
    public static int insertOrUpdateInsercion(Integer idAsunto, Integer idSite, Integer numInser, Integer fase, String ruta, 
            Long fechaIni, Long fechaFin, Long fecha, Integer responsable, Integer rf,
            Integer idAsuntoVal, Integer idSiteVal, Integer numInserVal, Integer faseVal, String rutaVal, 
            Long fechaIniVal, Long fechaFinVal, Long fechaVal, Integer responsableVal, Integer rfVal, String sqlExtra) throws SQLException {
        int res;
        
        res = updateInsercion(idAsunto, idSite, numInser, fase, ruta, fechaIni, fechaFin, fecha, responsable, rf, 
                idAsuntoVal, idSiteVal, numInserVal, faseVal, rutaVal, fechaIniVal, fechaFinVal, fechaVal, responsableVal, rfVal, sqlExtra);
        
        if (res < 0)
            res = insertInsercion(idAsuntoVal, idSiteVal, numInserVal, faseVal, rutaVal, fechaIniVal, fechaFinVal, fechaVal, responsableVal, rfVal, sqlExtra);

        return res;
    }
    
    public static int insertOrUpdateInsercion(InsercionRA inserVieja, InsercionRA inserNueva, String sqlExtra) throws SQLException {
        
        return insertOrUpdateInsercion(inserVieja.idAsunto, inserVieja.idSite, inserVieja.numInser, inserVieja.fase, inserVieja.ruta, 
            inserVieja.fechaIni, inserVieja.fechaFin, inserVieja.fecha, inserVieja.responsable, inserVieja.rf,
            inserNueva.idAsunto, inserNueva.idSite, inserNueva.numInser, inserNueva.fase, inserNueva.ruta, 
            inserNueva.fechaIni, inserNueva.fechaFin, inserNueva.fecha, inserNueva.responsable, inserNueva.rf, sqlExtra);
    }
    
    //Función para eliminar Inserciones que se ajustan a los limites pasados
    public static int deleteInserciones(Integer idAsunto, Integer idSite, Integer numInser, Integer fase, String ruta, 
            Long fechaIni, Long fechaFin, Long fecha, Integer responsable, Integer rf, String sqlExtra) throws SQLException {
        InteraccionBD inserBD = new InteraccionBD();
        
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        int res = 0;

        condicion = getCondicion(idAsunto, idSite, numInser, fase, ruta, fechaIni, fechaFin, fecha, responsable, rf, paramsPS);

        inserBD.inicioTransaccion();
        res  = inserBD.deleteDatosTabla(TABLA, condicion, paramsPS, sqlExtra);
        inserBD.finTransaccion();
        
        return res;
    }
    
    public static int deleteInserciones(InsercionRA insercion, String sqlExtra) throws SQLException {
        return deleteInserciones(insercion.idAsunto, insercion.idSite, insercion.numInser, insercion.fase, insercion.ruta, 
            insercion.fechaIni, insercion.fechaFin, insercion.fecha, insercion.responsable, insercion.rf, sqlExtra);
    }
    
    public Object[] toObject() {
        return new Object[]{this.idAsunto, this.idSite, this.numInser, this.fase, this.ruta,
        this.fechaIni, this.fechaFin, this.fecha, this.responsable, this.rf};
    }
  
    public static Object[] getCamposTabla() throws SQLException {
        InteraccionBD inserBD = new InteraccionBD();
        
        return inserBD.getCamposTabla(TABLA);
    }
} 