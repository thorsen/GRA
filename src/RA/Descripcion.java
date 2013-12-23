package RA;

import general.Auxiliares;
import general.InteraccionBD;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class Descripcion {
    //Campos de la tabla
    private Integer idAsunto;
    private Integer idSerie;
    private Double valor;
    
    public static final String BD = InteraccionBD.PREF_BD_RA;
    public static final String TABLA = BD + "DescripcionRA";
    public static final String CAMPO_ID_ASUNTO = "Asunto";
    public static final String CAMPO_ID_SERIE = "Serie";
    public static final String CAMPO_VALOR = "Valor";

    public Descripcion(Integer idAsunto, Integer idSerie, Double valor) {
        this.idAsunto = idAsunto;
        this.idSerie = idSerie;
        this.valor = valor;
    }
    
    public Descripcion(Object[] valores, ArrayList<String> campos) throws SQLException, NoSuchFieldException {
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

    public Integer getIdSerie() {
        return idSerie;
    }

    public void setIdSerie(Integer idSerie) {
        this.idSerie = idSerie;
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
        } else if (campo.compareTo(CAMPO_ID_SERIE) == 0) {
            this.idSerie = (Integer) valor;
        } else if (campo.compareTo(CAMPO_VALOR) == 0) {
            this.valor = (Double) valor;
        } else {
            //throw new NoSuchFieldException("No existe el campo en la clase " + this.getClass().getSimpleName());
			System.out.println("No existe el campo <" + campo + "> en la clase " + this.getClass().getSimpleName());
        }
    }

    private static String getCondicion(Integer idAsunto, Integer idSerie, Double valor, ArrayList<Object[]> paramsPS) {
        String condicion = "";
        
        if (idAsunto != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_ID_ASUNTO, "=", idAsunto);
        }
        if (idSerie != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_ID_SERIE, "=", idSerie);
        }
        if (valor != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_VALOR, "=", valor);
        }
        
        return condicion;
    }
    
    //Función para obtener la colección de Descripciones que se ajustan a los limites pasados
    public static ArrayList<Descripcion> getDescripciones(Integer idAsunto, Integer idSerie, Double valor, ArrayList<String> campos, String sqlExtra, Boolean distinct) throws SQLException, NoSuchFieldException {
        InteraccionBD interBD = new InteraccionBD();
        
        ArrayList<Descripcion> res = null;
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();

        condicion = getCondicion(idAsunto, idSerie, valor, paramsPS);

        //Por defecto lo devolvemos ordenado por asunto, serie
        if (sqlExtra == null || sqlExtra.trim().length() == 0) {
            String sqlOrderBy = InteraccionBD.anadeCampoOrderBy(null, CAMPO_ID_ASUNTO);
            sqlOrderBy = InteraccionBD.anadeCampoOrderBy(sqlOrderBy, CAMPO_ID_SERIE);
            sqlExtra = InteraccionBD.anadeSqlExtra(null, sqlOrderBy);
        }

        ArrayList<Object[]> resAux = interBD.getDatosTabla(TABLA, campos, condicion, paramsPS, sqlExtra, distinct);

        if (resAux != null) {
            int n = resAux.size();
            res = new ArrayList<Descripcion>();

            for (int i = 0; i < n; i++) {
               res.add(new Descripcion(resAux.get(i), campos));
            }
        }

        return res;
    }
    
    public static Descripcion getDescripcion(Integer idAsunto, Integer idSerie) throws SQLException, NoSuchFieldException {
        Descripcion res = null;
        
        ArrayList<Descripcion> resAux = getDescripciones(idAsunto, idSerie, null, null, null, null);
        
        if (resAux != null)
            res = resAux.get(0);
        
        return res;
    }
    
    //Función para añadir una descripcion a la BD
    public static int insertDescripcion(Integer idAsunto, Integer idSerie, Double valor, String sqlExtra) throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        String valores = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        ArrayList<String> campos = new ArrayList<String>();
        int res = 0;
        
        if (idAsunto != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, idAsunto);
            campos.add(CAMPO_ID_ASUNTO);
        }
        if (idSerie != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, idSerie);
            campos.add(CAMPO_ID_SERIE);
        }
        if (valor != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, valor);
            campos.add(CAMPO_ID_SERIE);
        }

        interBD.inicioTransaccion();
        res = interBD.insertDatosTabla(TABLA, campos, valores, paramsPS, sqlExtra);
        interBD.finTransaccion();
            
        return res;
    }
    
    public static int insertDescripcion(Descripcion descripcion, String sqlExtra) throws SQLException {
        return insertDescripcion(descripcion.idAsunto, descripcion.idSerie, descripcion.valor, sqlExtra);
    }
    
    //Función para añadir una descripcion a la BD
    public static int insertDescripcionCreateDatos(Integer idAsunto, LinkedHashMap<String, Double> nomVarNoAcusticas, LinkedHashMap<String, Double> nomVarAcusticas, String sqlExtra) throws SQLException, NoSuchFieldException, IOException {
        InteraccionBD interBD = new InteraccionBD();
        
        interBD.inicioTransaccion();
        //Borramos las descripciones para añadir de 0
        deleteDescripciones(idAsunto, null, null, null);
        
        int res = 0;
                    
        SerieRA2 serie;
        Integer idSerie, idTipo;
        ArrayList<String> codVarNoAcus = null, codVarSPL = null, codVarOCT = null, codVarFFT = null;
        
        Iterator itNoAcus = nomVarNoAcusticas.entrySet().iterator();
        Iterator itAcus = nomVarAcusticas.entrySet().iterator();
        Entry entry;
        
        if (itNoAcus.hasNext())
            codVarNoAcus = new ArrayList<String>();
        
        while (itNoAcus.hasNext()) {
            entry = (Entry) itNoAcus.next();
            
            serie = SerieRA2.getSerieRA2PorDescripcion((String) entry.getKey());
            idSerie = serie.getIdSerie();
            
            codVarNoAcus.add(serie.getCodigo());

            insertOrUpdateDescripcion(idAsunto, idSerie, (Double) entry.getValue(), idAsunto, idSerie, (Double) entry.getValue(), null);
        }
        
        while (itAcus.hasNext()) {
            entry = (Entry) itAcus.next();
            
            serie = SerieRA2.getSerieRA2PorDescripcion((String) entry.getKey());
            idSerie = serie.getIdSerie();
            idTipo = serie.getIdTipoRA();
            
            if (idTipo.equals(TipoRA.ID_TIPO_SPL)) {
                if (codVarSPL == null)
                    codVarSPL = new ArrayList<String>();
                
                codVarSPL.add(serie.getCodigo());
            } else if (idTipo.equals(TipoRA.ID_TIPO_OCT)) {
                if (codVarOCT == null)
                    codVarOCT = new ArrayList<String>();
                
                codVarOCT.add(serie.getCodigo());
            } else if (idTipo.equals(TipoRA.ID_TIPO_FFT)) {
                if (codVarFFT == null)
                    codVarFFT = new ArrayList<String>();
                
                codVarFFT.add(serie.getCodigo());
            }

            insertOrUpdateDescripcion(idAsunto, idSerie, (Double) entry.getValue(), idAsunto, idSerie, (Double) entry.getValue(), null);
        }

        if (codVarSPL != null)
            DatosRA2.createDatos(Auxiliares.TIPO_SPL, idAsunto, codVarNoAcus, codVarSPL, false, sqlExtra);
        if (codVarOCT != null)
            DatosRA2.createDatos(Auxiliares.TIPO_OCT, idAsunto, codVarNoAcus, codVarOCT, false, sqlExtra);
        if (codVarFFT != null)
            DatosRA2.createDatos(Auxiliares.TIPO_FFT, idAsunto, codVarNoAcus, codVarFFT, false, sqlExtra); //No creamos índices XML porque, en principio, no nos harán falta -- Mejora eficiencia
            //DatosRA2.createDatos(Auxiliares.TIPO_FFT, idAsunto, codVarNoAcus, codVarFFT, true, sqlExtra);

        interBD.finTransaccion();
            
        return res;
    }
    
    //Función para añadir una descripcion a la BD
    public static int updateDescripcion(Integer idAsunto, Integer idSerie, Double valor, Integer idAsuntoVal, Integer idSerieVal, Double valorVal, String sqlExtra) throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        ArrayList<String> campos = new ArrayList<String>();
        int res = 0;
             
        if (idAsuntoVal != null) {
            InteraccionBD.anadeCampoValor(null, paramsPS, idAsuntoVal);
            campos.add(CAMPO_ID_ASUNTO);
        }
        if (idSerieVal != null) {
            InteraccionBD.anadeCampoValor(null, paramsPS, idSerieVal);
            campos.add(CAMPO_ID_SERIE);        
        }
        if (valorVal != null) {
            InteraccionBD.anadeCampoValor(null, paramsPS, valorVal);
            campos.add(CAMPO_VALOR);        
        }

        //Condiciones de actualización
        condicion = getCondicion(idAsunto, idSerie, valor, paramsPS);

        interBD.inicioTransaccion();
        res = interBD.updateDatosTabla(TABLA, campos, condicion, paramsPS, sqlExtra);
        interBD.finTransaccion();
        
        return res;
    }
    
    public static int updateDescripcion(Descripcion descVieja, Descripcion descNueva, String sqlExtra) throws SQLException {
        return updateDescripcion(descVieja.idAsunto, descVieja.idSerie, descVieja.valor, descNueva.idAsunto, descNueva.idSerie, descNueva.valor, sqlExtra);
    }
    
    //Función para añadir/modificar una descripcion a la BD
    public static int insertOrUpdateDescripcion(Integer idAsunto, Integer idSerie, Double valor, Integer idAsuntoVal, Integer idSerieVal, Double valorVal, String sqlExtra) throws SQLException {
        int res;
        
        res = updateDescripcion(idAsunto, idSerie, valor, idAsuntoVal, idSerieVal, valorVal, sqlExtra);
        
        if (res < 0)
            res = insertDescripcion(idAsuntoVal, idSerieVal, valorVal, sqlExtra);

        return res;
    }
    
    public static int insertOrUpdateDescripcion(Descripcion descVieja, Descripcion descNueva, String sqlExtra) throws SQLException {
        return insertOrUpdateDescripcion(descVieja.idAsunto, descVieja.idSerie, descVieja.valor, descNueva.idAsunto, descNueva.idSerie, descNueva.valor, sqlExtra);
    }
    
    //Función para eliminar Descripciones que se ajustan a los limites pasados
    public static int deleteDescripciones(Integer idAsunto, Integer idSerie, Double valor, String sqlExtra) throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        int res = 0;
    
        condicion = getCondicion(idAsunto, idSerie, valor, paramsPS);

        interBD.inicioTransaccion();
        res  = interBD.deleteDatosTabla(TABLA, condicion, paramsPS, sqlExtra);
        interBD.finTransaccion();
        
        return res;
    }
    
    public static int deleteDescripciones(Descripcion descripcion, String sqlExtra) throws SQLException {
        return deleteDescripciones(descripcion.idAsunto, descripcion.idSerie, descripcion.valor, sqlExtra);
    }
    
    public Object[] toObject() {
        return new Object[]{this.idAsunto, this.idSerie, this.valor};
    }
  
    public static Object[] getCamposTabla() throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        return interBD.getCamposTabla(TABLA);
    }
} 