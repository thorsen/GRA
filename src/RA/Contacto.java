package RA;

import general.InteraccionBD;
import java.sql.SQLException;
import java.util.ArrayList;

public class Contacto {

    private Integer idContacto;
    private String nombre;
    private Integer idCliente;
    private String mail;
    private String telefono;
    private String usuario;
    private String password;
    private Boolean web;
    private Boolean interno;
    private Boolean activo;
    
    public static final String BD = InteraccionBD.PREF_BD_GENERAL;
    public static final String TABLA = BD + "Contacto";
    public static final String CAMPO_ID_CONTACTO = "Id_contacto";
    public static final String CAMPO_NOMBRE = "Nombre";
    public static final String CAMPO_ID_CLIENTE = "Cliente";
    public static final String CAMPO_MAIL = "Mail";
    public static final String CAMPO_TELEFONO = "Telefono";
    public static final String CAMPO_USUARIO = "Usuario";
    public static final String CAMPO_PASSWORD = "Contraseña";
    public static final String CAMPO_WEB = "Web";
    public static final String CAMPO_INTERNO = "Interno";
    public static final String CAMPO_ACTIVO = "Activo";

    public Contacto(Integer idContacto, String nombre, Integer idCliente, String mail, String telefono, String usuario, String password, Boolean web, Boolean interno, Boolean activo) {
        this.idContacto = idContacto;
        this.nombre = nombre;
        this.idCliente = idCliente;
        this.mail = mail;
        this.telefono = telefono;
        this.usuario = usuario;
        this.password = password;
        this.web = web;
        this.interno = interno;
        this.activo = activo;
    }

    public Contacto(Object[] valores, ArrayList<String> campos) throws SQLException, NoSuchFieldException {
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String Nombre) {
        this.nombre = Nombre;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getIdContacto() {
        return idContacto;
    }

    public void setIdContacto(Integer idContacto) {
        this.idContacto = idContacto;
    }

    public Boolean getInterno() {
        return interno;
    }

    public void setInterno(Boolean interno) {
        this.interno = interno;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Boolean getWeb() {
        return web;
    }

    public void setWeb(Boolean web) {
        this.web = web;
    }
    //Función para asignar valores a campos de la clase según el campo de la BD
    private void asignaCampo(String campo, Object valor) throws NoSuchFieldException {
        if (campo.compareTo(CAMPO_ID_CONTACTO) == 0) {
            this.idContacto = (Integer) valor;
        } else if (campo.compareTo(CAMPO_NOMBRE) == 0) {
            this.nombre = (String) valor;
        } else if (campo.compareTo(CAMPO_ID_CLIENTE) == 0) {
            this.idCliente = (Integer) valor;
        } else if (campo.compareTo(CAMPO_MAIL) == 0) {
            this.mail = (String) valor;
        } else if (campo.compareTo(CAMPO_TELEFONO) == 0) {
            this.telefono = (String) valor;
        } else if (campo.compareTo(CAMPO_USUARIO) == 0) {
            this.usuario = (String) valor;
        } else if (campo.compareTo(CAMPO_PASSWORD) == 0) {
            this.password = (String) valor;
        } else if (campo.compareTo(CAMPO_WEB) == 0) {
            this.web = (Boolean) valor;
        } else if (campo.compareTo(CAMPO_INTERNO) == 0) {
            this.interno = (Boolean) valor;
        } else if (campo.compareTo(CAMPO_ACTIVO) == 0) {
            this.activo = (Boolean) valor;
        } else {
            //throw new NoSuchFieldException("No existe el campo en la clase " + this.getClass().getSimpleName());
			System.out.println("No existe el campo <" + campo + "> en la clase " + this.getClass().getSimpleName());
        }
    }
    
    private static String getCondicion(Integer idContacto, String nombre, Integer idCliente, String mail, String telefono, String usuario, String password, Boolean web, Boolean interno, Boolean activo, ArrayList<Object[]> paramsPS) {
        String condicion = "";
        
        if (idContacto != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_ID_CONTACTO, "=", idContacto);
        }
        if (nombre != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_NOMBRE, "=", nombre);
        }
        if (idCliente != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_ID_CLIENTE, "=", idCliente);
        }
        if (mail != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_MAIL, "=", mail);
        }
        if (telefono != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_TELEFONO, "=", telefono);
        }
        if (usuario != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_USUARIO, "=", usuario);
        }
        if (password != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_PASSWORD, "=", password);
        }
        if (web != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_WEB, "=", web);
        }
        if (interno != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_INTERNO, "=", interno);
        }
        if (activo != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_ACTIVO, "=", activo);
        }
        
        return condicion;
    }

    //Función para obtener la colección de Contactos que se ajustan a los limites pasados
    public static ArrayList<Contacto> getContactos(Integer idContacto, String nombre, Integer idCliente, String mail, String telefono, String usuario, String password, Boolean web, Boolean interno, Boolean activo, ArrayList<String> campos, String sqlExtra, Boolean distinct) throws SQLException, NoSuchFieldException {
        InteraccionBD interBD = new InteraccionBD();

        ArrayList<Contacto> res = null;
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();

        condicion = getCondicion(idContacto, nombre, idCliente, mail, telefono, usuario, password, web, interno, activo, paramsPS);
        
        //Por defecto lo devolvemos ordenado por IdContacto
        if (sqlExtra == null || sqlExtra.trim().length() == 0) {
            String sqlOrderBy = InteraccionBD.anadeCampoOrderBy(null, CAMPO_ID_CONTACTO);
            sqlExtra = InteraccionBD.anadeSqlExtra(null, sqlOrderBy);
        }

        ArrayList<Object[]> resAux = interBD.getDatosTabla(TABLA, campos, condicion, paramsPS, sqlExtra, distinct);

        if (resAux != null) {
            int n = resAux.size();
            res = new ArrayList<Contacto>();

            for (int i = 0; i < n; i++) {
                res.add(new Contacto(resAux.get(i), campos));
            }
        }

        return res;
    }
    
    //Función para obtener la colección de Contactos que se ajustan a los limites pasados
    public static ArrayList<Contacto> getContactosPorIdCliente(Integer idCliente) throws SQLException, NoSuchFieldException {
        //Cogemos los distintos contactos activos del cliente
        return getContactos(null, null, idCliente, null, null, null, null, null, null, true, null, null, true);
    }
    
    public static Contacto getContactosPorId(Integer idContacto) throws SQLException, NoSuchFieldException {
        Contacto res = null;
        ArrayList<Contacto> resAux;
        
        resAux = getContactos(idContacto, null, null, null, null, null, null, null, null, null, null, null, null);
        
        if (resAux != null) {
            res = resAux.get(0);
        }

        return res;
    }

    public static ArrayList<Contacto> getConctactosExternos(Integer idAsunto, Boolean web, String sqlExtra, Boolean distinct) throws SQLException, NoSuchFieldException {
        InteraccionBD interBD = new InteraccionBD();

        ArrayList<Contacto> res = null;
        String condicion = "";
        String tabla = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        ArrayList<String> campos = new ArrayList<String>();
        
        //Cogemos todos los campos de Contacto
        campos.add(TABLA + ".*");

        //Añadimos la conectividad entre tablas
        condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, TABLA + "." + CAMPO_ID_CONTACTO, InteraccionBD.ASIGNACION_CAMPOS, AsuntoContacto.TABLA + "." + AsuntoContacto.CAMPO_ID_CONTACTO);
        
        if (idAsunto != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, AsuntoContacto.TABLA + "." + AsuntoContacto.CAMPO_ID_ASUNTO, "=", idAsunto);
        }
        if (web != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, TABLA + "." + CAMPO_WEB, "=", web);
        }
        
        tabla = InteraccionBD.anadeTabla(tabla, TABLA);
        tabla = InteraccionBD.anadeTabla(tabla, AsuntoContacto.TABLA);

        ArrayList<Object[]> resAux = interBD.getDatosTabla(tabla, campos, condicion, paramsPS, sqlExtra, distinct);

        if (resAux != null) {
            int n = resAux.size();
            res = new ArrayList<Contacto>();

            for (int i = 0; i < n; i++) {
                res.add(new Contacto(resAux.get(i), null));
            }
        }

        return res;
    }

    //Función para añadir un contacto a la BD
    public static int insertContacto(Integer idContacto, String nombre, Integer idCliente, String mail, String telefono, String usuario, String password, Boolean web, Boolean interno, Boolean activo, String sqlExtra) throws SQLException {
        InteraccionBD interBD = new InteraccionBD();

        String valores = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        ArrayList<String> campos = new ArrayList<String>();
        ArrayList<String> autoInc = new ArrayList<String>();
        ArrayList<String> condAutoInc = new ArrayList<String>();
        int res = 0;

        interBD.inicioTransaccion();

        if (idContacto != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, idContacto);
            campos.add(CAMPO_ID_CONTACTO);
        } else {    //Es campo clave, asignamos el incremento de la última
            valores = InteraccionBD.anadeCampoValorAutoInc(valores, CAMPO_ID_CONTACTO);
            campos.add(CAMPO_ID_CONTACTO);
            autoInc.add(CAMPO_ID_CONTACTO);
            condAutoInc.add(null);
        }
        if (nombre != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, nombre);
            campos.add(CAMPO_NOMBRE);
        }
        if (idCliente != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, idCliente);
            campos.add(CAMPO_ID_CLIENTE);
        }
        if (mail != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, mail);
            campos.add(CAMPO_MAIL);
        }
        if (telefono != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, telefono);
            campos.add(CAMPO_TELEFONO);
        }
        if (usuario != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, usuario);
            campos.add(CAMPO_USUARIO);
        }
        if (password != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, password);
            campos.add(CAMPO_PASSWORD);
        }
        if (web != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, web);
            campos.add(CAMPO_WEB);
        }
        if (interno != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, interno);
            campos.add(CAMPO_INTERNO);
        }
        if (activo != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, activo);
            campos.add(CAMPO_ACTIVO);
        }

        res = interBD.insertDatosTabla(TABLA, campos, valores, paramsPS, sqlExtra, autoInc, condAutoInc);
        interBD.finTransaccion();

        return res;
    }

    public static int insertContacto(Contacto contacto, String sqlExtra) throws SQLException {
        return insertContacto(contacto.idContacto, contacto.nombre, contacto.idCliente, contacto.mail, contacto.telefono, contacto.usuario, contacto.password, contacto.web, contacto.interno, contacto.activo, sqlExtra);
    }
    
    public static int insertContactoArray(Integer idCliente, ArrayList<String[]> contactoCliente) throws SQLException {
        int res = 0;
        
        String nombre, mail, telefono;
        String usuario, password;
        Boolean web, interno, activo;
        
        //Valores por defecto de campos no pasados en contactoCliente
        usuario = password = null;
        web = interno = false;
        activo = true;

        int nContactoCliente = contactoCliente.size();

        for (int i = 0; i < nContactoCliente; i++) {
            nombre = contactoCliente.get(i)[0];
            mail = contactoCliente.get(i)[1];
            telefono = contactoCliente.get(i)[2];
          
            //Pasamos idContacto como null para que nos dé el autonumérico
            res += insertContacto(null, nombre, idCliente, mail, telefono, usuario, password, web, interno, activo, null);
        }
        
        return res;
    }
    
    //Función para añadir un contacto a la BD
    public static int updateContacto(Integer idContacto, String nombre, Integer idCliente, String mail, String telefono, String usuario, String password, Boolean web, Boolean interno, Boolean activo, Integer idContactoVal, String nombreVal, Integer idClienteVal, String mailVal, String telefonoVal, String usuarioVal, String passwordVal, Boolean webVal, Boolean internoVal, Boolean activoVal, String sqlExtra) throws SQLException {
        InteraccionBD interBD = new InteraccionBD();

        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        ArrayList<String> campos = new ArrayList<String>();
        int res = 0;

        if (idContactoVal != null && !idContactoVal.equals(idContacto)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, idContactoVal);
            campos.add(CAMPO_ID_CONTACTO);
        }
        if (nombreVal != null && !nombreVal.equals(nombre)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, nombreVal);
            campos.add(CAMPO_NOMBRE);
        }
        if (idClienteVal != null && !idClienteVal.equals(idCliente)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, idClienteVal);
            campos.add(CAMPO_ID_CLIENTE);
        }
        if (mailVal != null && !mailVal.equals(mail)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, mailVal);
            campos.add(CAMPO_MAIL);
        }
        if (telefonoVal != null && !telefonoVal.equals(telefono)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, telefonoVal);
            campos.add(CAMPO_TELEFONO);
        }
        if (usuarioVal != null && !usuarioVal.equals(usuario)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, usuarioVal);
            campos.add(CAMPO_USUARIO);
        }
        if (passwordVal != null && !passwordVal.equals(password)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, passwordVal);
            campos.add(CAMPO_PASSWORD);
        }
        if (webVal != null && !webVal.equals(web)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, webVal);
            campos.add(CAMPO_WEB);
        }
        if (internoVal != null && !internoVal.equals(interno)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, internoVal);
            campos.add(CAMPO_INTERNO);
        }
        if (activoVal != null && !activoVal.equals(activo)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, activoVal);
            campos.add(CAMPO_ACTIVO);
        }

        //Condiciones de actualización
        condicion = getCondicion(idContacto, nombre, idCliente, mail, telefono, usuario, password, web, interno, activo, paramsPS);

        interBD.inicioTransaccion();
        res = interBD.updateDatosTabla(TABLA, campos, condicion, paramsPS, sqlExtra);
        interBD.finTransaccion();

        return res;
    }

    public static int updateContacto(Contacto contactoViejo, Contacto contactoNuevo, String sqlExtra) throws SQLException {
        return updateContacto(contactoViejo.idContacto, contactoViejo.nombre, contactoViejo.idCliente, contactoViejo.mail, contactoViejo.telefono, contactoViejo.usuario, contactoViejo.password, contactoViejo.web, contactoViejo.interno, contactoViejo.activo, contactoNuevo.idContacto, contactoNuevo.nombre, contactoNuevo.idCliente, contactoNuevo.mail, contactoNuevo.telefono, contactoNuevo.usuario, contactoNuevo.password, contactoNuevo.web, contactoNuevo.interno, contactoNuevo.activo, sqlExtra);
    }
    //Función para añadir/modificar un contacto a la BD
    public static int insertOrUpdateContacto(Integer idContacto, String nombre, Integer idCliente, String mail, String telefono, String usuario, String password, Boolean web, Boolean interno, Boolean activo, Integer idContactoVal, String nombreVal, Integer idClienteVal, String mailVal, String telefonoVal, String usuarioVal, String passwordVal, Boolean webVal, Boolean internoVal, Boolean activoVal, String sqlExtra) throws SQLException {
        int res;

        res = updateContacto(idContacto, nombre, idCliente, mail, telefono, usuario, password, web, interno, activo, idContactoVal, nombreVal, idClienteVal, mailVal, telefonoVal, usuarioVal, passwordVal, webVal, internoVal, activoVal, sqlExtra);

        if (res < 0) {
            res = insertContacto(idContactoVal, nombreVal, idClienteVal, mailVal, telefonoVal, usuarioVal, passwordVal, webVal, internoVal, activoVal, sqlExtra);
        }
        return res;
    }

    public static int insertOrUpdateContacto(Contacto contactoViejo, Contacto contactoNuevo, String sqlExtra) throws SQLException {

        return insertOrUpdateContacto(contactoViejo.idContacto, contactoViejo.nombre, contactoViejo.idCliente, contactoViejo.mail, contactoViejo.telefono, contactoViejo.usuario, contactoViejo.password, contactoViejo.web, contactoViejo.interno, contactoViejo.activo, contactoNuevo.idContacto, contactoNuevo.nombre, contactoNuevo.idCliente, contactoNuevo.mail, contactoNuevo.telefono, contactoNuevo.usuario, contactoNuevo.password, contactoNuevo.web, contactoNuevo.interno, contactoNuevo.activo, sqlExtra);
    }
    //Función para eliminar Contactos que se ajustan a los limites pasados
    public static int deleteContacto(Integer idContacto, String nombre, Integer idCliente, String mail, String telefono, String usuario, String password, Boolean web, Boolean interno, Boolean activo, String sqlExtra) throws SQLException {
        InteraccionBD interBD = new InteraccionBD();

        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        int res = 0;

        condicion = getCondicion(idContacto, nombre, idCliente, mail, telefono, usuario, password, web, interno, activo, paramsPS);

        interBD.inicioTransaccion();
        res = interBD.deleteDatosTabla(TABLA, condicion, paramsPS, sqlExtra);
        interBD.finTransaccion();

        return res;
    }

    public static int deleteContacto(Contacto contacto, String sqlExtra) throws SQLException {
        return deleteContacto(contacto.idContacto, contacto.nombre, contacto.idCliente, contacto.mail, contacto.telefono, contacto.usuario, contacto.password, contacto.web, contacto.interno, contacto.activo, sqlExtra);
    }

    public Object[] toObject() {
        return new Object[]{this.idContacto, this.nombre, this.idCliente, this.mail, this.telefono, this.usuario, this.password, this.web, this.interno, this.activo};
    }

    public static Object[] getCamposTabla() throws SQLException {
        InteraccionBD interBD = new InteraccionBD();

        return interBD.getCamposTabla(TABLA);
    }
    // Devuelve el identificador de contacto
    public static Integer getIdContacto(String nombre) throws SQLException, NoSuchFieldException {
        ArrayList<String> campos = new ArrayList<String>();
        ArrayList<Contacto> resAux = null;
        Integer res = null;

        //Solo nos interesa el campo de idContacto
        campos.add(CAMPO_ID_CONTACTO);

        resAux = Contacto.getContactos(null, nombre, null, null, null, null, null, null, null, null, campos, null, false);

        if (resAux != null && resAux.size() > 0) {
            res = resAux.get(0).getIdContacto();
        }

        return res;
    }
} 