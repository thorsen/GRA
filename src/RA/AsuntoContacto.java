package RA;

import general.InteraccionBD;
import general.InteraccionFic;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class AsuntoContacto {
    private Integer idContacto;
    private Integer idAsunto;
    private Boolean notificacion;
    private Boolean adjunto;
    
    public  static final String TABLA = "AsuntoContacto";
    public static final String CAMPO_ID_CONTACTO = "Contacto";
    public static final String CAMPO_ID_ASUNTO = "Asunto";
    public static final String CAMPO_NOTIFICACION = "Notificacion";
    public static final String CAMPO_ADJUNTO = "Adjunto";
    
    private static final int ID_JESUS = 29;
    
    public AsuntoContacto(Integer idContacto, Integer idAsunto, Boolean notificacion, Boolean adjunto) {
        this.idContacto = idContacto;
        this.idAsunto = idAsunto;
        this.notificacion = notificacion;
        this.adjunto = adjunto;
    }
    
    public AsuntoContacto(Object[] valores, ArrayList<String> campos) throws SQLException, NoSuchFieldException {
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
    
    public Integer getIdContacto() {
        return idContacto;
    }

    public void setIdContacto(Integer idContacto) {
        this.idContacto = idContacto;
    }
    
    public Integer getIdAsunto() {
        return idAsunto;
    }

    public void setIdAsunto(Integer idAsunto) {
        this.idAsunto = idAsunto;
    }

    public Boolean getNotificacion() {
        return notificacion;
    }

    public void setNotificacion(Boolean notificacion) {
        this.notificacion = notificacion;
    }

    public Boolean getAdjunto() {
        return adjunto;
    }

    public void setAdjunto(Boolean adjunto) {
        this.adjunto = adjunto;
    }

    //Función para asignar valores a campos de la clase según el campo de la BD
    private void asignaCampo(String campo, Object valor) throws NoSuchFieldException {
        if (campo.compareTo(CAMPO_ID_CONTACTO) == 0) {
            this.idContacto = (Integer) valor;
        } else if (campo.compareTo(CAMPO_ID_ASUNTO) == 0) {
            this.idAsunto = (Integer) valor;
        } else if (campo.compareTo(CAMPO_NOTIFICACION) == 0) {
            this.notificacion = (Boolean) valor;
        } else if (campo.compareTo(CAMPO_ADJUNTO) == 0) {
            this.adjunto = (Boolean) valor;
        } else {
            throw new NoSuchFieldException("No existe el campo en la clase " + this.getClass().getSimpleName());
        }
    }
    
    private static String getCondicion(Integer idContacto, Integer idAsunto, Boolean notificacion, Boolean adjunto, ArrayList<Object[]> paramsPS){
        String condicion = "";
     
        if (idContacto != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_ID_CONTACTO, "=", idContacto);
        }
        if (idAsunto != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_ID_ASUNTO, "=", idAsunto);
        }
        if (notificacion != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_NOTIFICACION, "=", notificacion);
        }
        if (adjunto != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_ADJUNTO, "=", adjunto);
        }
        
        return condicion;
    }    

    //Función para obtener la colección de Contactos que se ajustan a los limites pasados
    public static ArrayList<AsuntoContacto> getAsuntoContas(Integer idContacto, Integer idAsunto, Boolean notificacion, Boolean adjunto, 
            ArrayList<String> campos, String sqlExtra, Boolean distinct) throws SQLException, NoSuchFieldException {
        InteraccionBD interBD = new InteraccionBD();
        
        ArrayList<AsuntoContacto> res = null;
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();

        condicion = getCondicion(idContacto, idAsunto, notificacion, adjunto, paramsPS);
        
        //Por defecto lo devolvemos ordenado por asunto, idContacto
        if (sqlExtra == null || sqlExtra.trim().length() == 0) {
            String sqlOrderBy = InteraccionBD.anadeCampoOrderBy(null, CAMPO_ID_ASUNTO);
            sqlOrderBy = InteraccionBD.anadeCampoOrderBy(sqlOrderBy, CAMPO_ID_CONTACTO);
            sqlExtra = InteraccionBD.anadeSqlExtra(null, sqlOrderBy);
        }

        ArrayList<Object[]> resAux = interBD.getDatosTabla(TABLA, campos, condicion, paramsPS, sqlExtra, distinct);

        if (resAux != null) {
            int n = resAux.size();
            res = new ArrayList<AsuntoContacto>();
            
            for (int i = 0; i < n; i++) {
               res.add(new AsuntoContacto(resAux.get(i), campos));
            }
        }

        return res;
    }

    //Función para añadir un asuntoConta a la BD
    public static int insertAsuntoContaFic(Integer idContacto, Integer idAsunto, Boolean notificacion, Boolean adjunto, String sqlExtra, InteraccionFic interFic) throws SQLException, IOException, ClassNotFoundException {
        InteraccionBD interBD = new InteraccionBD();
        
        String valores = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        ArrayList<String> campos = new ArrayList<String>();
        int res = 0;
        
        if (idContacto != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, idContacto);
            campos.add(CAMPO_ID_CONTACTO);
        }
        if (idAsunto != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, idAsunto);
            campos.add(CAMPO_ID_ASUNTO);
        }
        if (notificacion != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, notificacion);
            campos.add(CAMPO_NOTIFICACION);
        }
        if (adjunto != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, adjunto);
            campos.add(CAMPO_ADJUNTO);
        }
        
        interBD.inicioTransaccion();
        res = interBD.insertDatosTabla(TABLA, campos, valores, paramsPS, sqlExtra);
        interBD.finTransaccion();
        
        if (interFic != null)
            interFic.escribeLineaFic(InteraccionBD.dameSentenciaSql(InteraccionBD.TIPO_DELETE, TABLA, campos, valores, paramsPS, sqlExtra, false, null, null));
        
        return res;
    }
    
    public static int insertAsuntoContaFic(AsuntoContacto asuntoConta, String sqlExtra, InteraccionFic interFic) throws SQLException, IOException, ClassNotFoundException {
        return insertAsuntoContaFic(asuntoConta.idContacto, asuntoConta.idAsunto, asuntoConta.notificacion, asuntoConta.adjunto, sqlExtra, interFic);
    }
    
     //Función para añadir un asuntoConta a la BD
    public static int insertAsuntoConta(Integer idContacto, Integer idAsunto, Boolean notificacion, Boolean adjunto, String sqlExtra) throws SQLException, IOException, ClassNotFoundException {
        return insertAsuntoContaFic(idContacto, idAsunto, notificacion, adjunto, sqlExtra, null);
    }
    
    public static int insertAsuntoConta(AsuntoContacto asuntoConta, String sqlExtra) throws SQLException, IOException, ClassNotFoundException {
        return insertAsuntoContaFic(asuntoConta.idContacto, asuntoConta.idAsunto, asuntoConta.notificacion, asuntoConta.adjunto, sqlExtra, null);
    }
    
    public static int insertAsuntoContaArrayFic(Integer idAsunto, ArrayList<String> contactoAsunto, InteraccionFic interFic) throws SQLException, NoSuchFieldException, IOException, ClassNotFoundException {
        int res = 0;
        
        Boolean esJesus = false;
        //Valores por defecto de notificación y adjunto
        Boolean notificacion = true;
        Boolean adjunto = false;

        int nAsuntoConta = contactoAsunto.size();
        Integer idContacto;

        for (int i = 0; i < nAsuntoConta; i++) {
            idContacto = Contacto.getIdContacto(contactoAsunto.get(i));

            //Si el contacto no existe, se ignora
            if (idContacto == null) {
                continue;
            }
            if (idContacto.intValue() == ID_JESUS) {
                esJesus = true;
            }

            res += insertAsuntoContaFic(idContacto, idAsunto, notificacion, adjunto, null, interFic);
        }

        // Se inserta a Jesus como contacto si no es el cliente
        if (!esJesus) {
            //Valores por defecto de notificación y adjunto para Jesús
            notificacion = true;
            adjunto = true;

            res += insertAsuntoContaFic(ID_JESUS, idAsunto, notificacion, adjunto, null, interFic);
        }
        
        return res;
    }
    
    public static int insertAsuntoContaArray(Integer idAsunto, ArrayList<String> contactoAsunto) throws SQLException, NoSuchFieldException, IOException, ClassNotFoundException {
        return insertAsuntoContaArrayFic(idAsunto, contactoAsunto, null);
    }
    
    //Función para añadir un asuntoConta a la BD
    public static int updateAsuntoConta(Integer idContacto, Integer idAsunto, Boolean notificacion, Boolean adjunto,
            Integer idContactoVal, Integer idAsuntoVal, Boolean notificacionVal, Boolean adjuntoVal, String sqlExtra) throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        ArrayList<String> campos = new ArrayList<String>();
        int res = 0;
        
        if (idContactoVal != null && !idContactoVal.equals(idContacto)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, idContactoVal);
            campos.add(CAMPO_ID_CONTACTO);
        }
        if (idAsuntoVal != null && !idAsuntoVal.equals(idAsunto)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, idAsuntoVal);
            campos.add(CAMPO_ID_ASUNTO);
        }
        if (notificacionVal != null && !notificacionVal.equals(notificacion)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, notificacionVal);
            campos.add(CAMPO_NOTIFICACION);        
        }
        if (adjuntoVal != null && !adjuntoVal.equals(adjunto)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, adjuntoVal);
            campos.add(CAMPO_ADJUNTO);        
        }

        //Condiciones de actualización
        condicion = getCondicion(idContacto, idAsunto, notificacion, adjunto, paramsPS);
        
        interBD.inicioTransaccion();
        res = interBD.updateDatosTabla(TABLA, campos, condicion, paramsPS, sqlExtra);
        interBD.finTransaccion();
        
        return res;
    }
    
    public static int updateAsuntoConta(AsuntoContacto asuntoContaViejo, AsuntoContacto asuntoContaNuevo, String sqlExtra) throws SQLException {
        return updateAsuntoConta(asuntoContaViejo.idContacto, asuntoContaViejo.idAsunto, asuntoContaViejo.notificacion, asuntoContaViejo.adjunto, 
                asuntoContaNuevo.idContacto, asuntoContaNuevo.idAsunto, asuntoContaNuevo.notificacion, asuntoContaNuevo.adjunto, sqlExtra);
    }
    
    //Función para añadir/modificar un asuntoConta a la BD
    public static int insertOrUpdateAsuntoConta(Integer idContacto, Integer idAsunto, Boolean notificacion, Boolean adjunto, 
            Integer idContactoVal, Integer idAsuntoVal, Boolean notificacionVal, Boolean adjuntoVal, String sqlExtra) throws SQLException, IOException, ClassNotFoundException {
        int res;
        
        res = updateAsuntoConta(idContacto, idAsunto, notificacion, adjunto, idContactoVal, idAsuntoVal, notificacionVal, adjuntoVal, sqlExtra);
        
        if (res < 0)
            res = insertAsuntoConta(idContactoVal, idAsuntoVal, notificacionVal, adjuntoVal, sqlExtra);

        return res;
    }
    
    public static int insertOrUpdateAsuntoConta(AsuntoContacto asuntoContaViejo, AsuntoContacto asuntoContaNuevo, String sqlExtra) throws SQLException, IOException, ClassNotFoundException {
        
        return insertOrUpdateAsuntoConta(asuntoContaViejo.idContacto, asuntoContaViejo.idAsunto, asuntoContaViejo.notificacion, asuntoContaViejo.adjunto,
                asuntoContaNuevo.idContacto, asuntoContaNuevo.idAsunto, asuntoContaNuevo.notificacion, asuntoContaNuevo.adjunto, sqlExtra);
    }
    
    //Función para eliminar Contactos que se ajustan a los limites pasados
    public static int deleteAsuntoContaFic(Integer idContacto, Integer idAsunto, Boolean notificacion, Boolean adjunto, String sqlExtra, InteraccionFic interFic) throws SQLException, ClassNotFoundException, IOException {
        InteraccionBD interBD = new InteraccionBD();
        
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        int res = 0;

        condicion = getCondicion(idContacto, idAsunto, notificacion, adjunto, paramsPS);

        interBD.inicioTransaccion();
        res  = interBD.deleteDatosTabla(TABLA, condicion, paramsPS, sqlExtra);
        interBD.finTransaccion();
        
        if (interFic != null)
            interFic.escribeLineaFic(InteraccionBD.dameSentenciaSql(InteraccionBD.TIPO_DELETE, TABLA, null, condicion, paramsPS, sqlExtra, false, null, null));
        
        return res;
    }
    
    public static int deleteAsuntoContaFic(AsuntoContacto asuntoConta, String sqlExtra, InteraccionFic interFic) throws SQLException, ClassNotFoundException, IOException {
        return deleteAsuntoContaFic(asuntoConta.idContacto, asuntoConta.idAsunto, asuntoConta.notificacion, asuntoConta.adjunto, sqlExtra, interFic);
    }
    
    //Función para eliminar Contactos que se ajustan a los limites pasados
    public static int deleteAsuntoConta(Integer idContacto, Integer idAsunto, Boolean notificacion, Boolean adjunto, String sqlExtra) throws SQLException, ClassNotFoundException, IOException {
        return deleteAsuntoContaFic(idContacto, idAsunto, notificacion, adjunto, sqlExtra, null);
    }
    
    public static int deleteAsuntoConta(AsuntoContacto asuntoConta, String sqlExtra) throws SQLException, ClassNotFoundException, IOException {
        return deleteAsuntoContaFic(asuntoConta.idContacto, asuntoConta.idAsunto, asuntoConta.notificacion, asuntoConta.adjunto, sqlExtra, null);
    }
    
    public Object[] toObject() {
        return new Object[]{this.idContacto, this.idAsunto, this.notificacion, this.adjunto};
    }
  
    public static Object[] getCamposTabla() throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        return interBD.getCamposTabla(TABLA);
    }
} 