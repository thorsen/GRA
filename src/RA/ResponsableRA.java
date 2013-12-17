package RA;

import general.InteraccionBD;
import java.sql.SQLException;
import java.util.ArrayList;

public class ResponsableRA {
    private Integer idResponsable;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String usuario;
    private String password;
    private String mail;
    private Boolean activo;
    private Integer rol;

    public static final String BD = InteraccionBD.PREF_BD_GENERAL;
    public static final String TABLA = BD + "Responsable";
    public static final String CAMPO_ID_RESPONSABLE = "Id_responsable";
    public static final String CAMPO_NOMBRE = "Nombre";
    public static final String CAMPO_APE_1 = "Apellido1";
    public static final String CAMPO_APE_2 = "Apellido2";
    public static final String CAMPO_USUARIO = "Usuario";
    public static final String CAMPO_PASS = "Contraseña";
    public static final String CAMPO_MAIL = "Mail";
    public static final String CAMPO_ACTIVO = "Activo";
    public static final String CAMPO_ROL = "Rol";

    public ResponsableRA(Integer idResponsable, String nombre, String apellido1, String apellido2, String usuario, String password, String mail, Boolean activo, Integer rol) {
        this.idResponsable = idResponsable;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.usuario = usuario;
        this.password = password;
        this.mail = mail;
        this.activo = activo;
        this.rol = rol;
    }
        
    public ResponsableRA(Object[] valores, ArrayList<String> campos) throws SQLException, NoSuchFieldException {
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
    
    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public Integer getIdResponsable() {
        return idResponsable;
    }

    public void setIdResponsable(Integer idResponsable) {
        this.idResponsable = idResponsable;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRol() {
        return rol;
    }

    public void setRol(Integer rol) {
        this.rol = rol;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
      
    //Función para asignar valores a campos de la clase según el campo de la BD
    private void asignaCampo(String campo, Object valor) throws NoSuchFieldException {
        if (campo.compareTo(CAMPO_ID_RESPONSABLE) == 0) {
            this.idResponsable = (Integer) valor;
        } else if (campo.compareTo(CAMPO_NOMBRE) == 0) {
            this.nombre = (String) valor;
        } else if (campo.compareTo(CAMPO_APE_1) == 0) {
            this.apellido1 = (String) valor;
        } else if (campo.compareTo(CAMPO_APE_2) == 0) {
            this.apellido2 = (String) valor;
        } else if (campo.compareTo(CAMPO_USUARIO) == 0) {
            this.usuario = (String) valor;
        } else if (campo.compareTo(CAMPO_PASS) == 0) {
            this.password = (String) valor;
        } else if (campo.compareTo(CAMPO_MAIL) == 0) {
            this.mail = (String) valor;
        } else if (campo.compareTo(CAMPO_ACTIVO) == 0) {
            this.activo = (Boolean) valor;
        } else if (campo.compareTo(CAMPO_ROL) == 0) {
            this.rol = (Integer) valor;
        } else {
            throw new NoSuchFieldException("No existe el campo en la clase " + this.getClass().getSimpleName());
        }
    }
    
    private static String getCondicion(Integer idResponsable, String nombre, String apellido1, String apellido2, String usuario, String password, String mail, Boolean activo, Integer rol, ArrayList<Object[]> paramsPS) {
        String condicion = "";
        
        if (idResponsable != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_ID_RESPONSABLE, "=", idResponsable);
        }
        if (nombre != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_NOMBRE, "=", nombre);
        }
        if (apellido1 != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_APE_1, "=", apellido1);
        }
        if (apellido2 != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_APE_2, "=", apellido2);
        }
        if (usuario != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_USUARIO, "=", usuario);
        }
        if (password != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_PASS, "=", password);
        }
        if (mail != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_MAIL, "=", mail);
        }
        if (activo != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_ACTIVO, "=", activo);
        }
        if (rol != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_ROL, "=", rol);
        }
        
        return condicion;
    }

    //Función para obtener la colección de Responsables que se ajustan a los limites pasados
    public static ArrayList<ResponsableRA> getResponsables(Integer idResponsable, String nombre, String apellido1, String apellido2, String usuario, String password, String mail, Boolean activo, Integer rol, ArrayList<String> campos, String sqlExtra, Boolean distinct) throws SQLException, NoSuchFieldException {
        InteraccionBD interBD = new InteraccionBD();
        
        ArrayList<ResponsableRA> res = null;
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();

        condicion = getCondicion(idResponsable, nombre, apellido1, apellido2, usuario, password, mail, activo, rol, paramsPS);
                
        //Por defecto lo devolvemos ordenado por responsable
        if (sqlExtra == null || sqlExtra.trim().length() == 0) {
            String sqlOrderBy = InteraccionBD.anadeCampoOrderBy(null, CAMPO_ID_RESPONSABLE);
            sqlExtra = InteraccionBD.anadeSqlExtra(null, sqlOrderBy);
        }

        ArrayList<Object[]> resAux = interBD.getDatosTabla(TABLA, campos, condicion, paramsPS, sqlExtra, distinct);

        if (resAux != null) {
            int n = resAux.size();
            res = new ArrayList<ResponsableRA>();
            
            for (int i = 0; i < n; i++) {
               res.add(new ResponsableRA(resAux.get(i), campos));
            }
        }

        return res;
    }
    
    //Función para obtener el responsable por Id
    public static ResponsableRA getResponsablePorId (Integer idResponsable) throws SQLException, NoSuchFieldException {
        ResponsableRA res = null;
        
        ArrayList<ResponsableRA> resAux = getResponsables(idResponsable, null, null, null, null, null, null, null, null, null, null, null);
        
        if (resAux != null) {
            res = resAux.get(0);
        }

        return res;
    }
    
    //Función para obtener el responsable por usuario
    public static ResponsableRA getResponsablePorUsuario(String usuario) throws SQLException, NoSuchFieldException {
        ResponsableRA res = null;
        
        ArrayList<ResponsableRA> resAux = getResponsables(null, null, null, null, usuario, null, null, null, null, null, null, null);
        
        if (resAux != null) {
            res = resAux.get(0);
        }

        return res;
    }
    
    //Función para obtener el responsable por nombre
    public static ResponsableRA getResponsablePorNombreAp1Ap2(String nombre, String apellido1, String apellido2) throws SQLException, NoSuchFieldException {
        ResponsableRA res = null;
        
        ArrayList<ResponsableRA> resAux = getResponsables(null, nombre, apellido1, apellido2, null, null, null, null, null, null, null, null);
        
        if (resAux != null) {
            res = resAux.get(0);
        }

        return res;
    }
    
    //Función para obtener los nombres
    public static ArrayList<ArrayList<String>> getResponsablesGen(Boolean activo) throws SQLException, NoSuchFieldException {
        ArrayList<ArrayList<String>> res = null;
        
        ArrayList<String> campos = new ArrayList<String>();
        
        //Solo nos interesan los nombres
        campos.add(CAMPO_NOMBRE);
        campos.add(CAMPO_APE_1);
        campos.add(CAMPO_APE_2);
        
        //Ordenamos por nombre
        String sqlOrderBy = InteraccionBD.anadeCampoOrderBy(null, CAMPO_NOMBRE);
        sqlOrderBy = InteraccionBD.anadeCampoOrderBy(sqlOrderBy, CAMPO_APE_1);
        sqlOrderBy = InteraccionBD.anadeCampoOrderBy(sqlOrderBy, CAMPO_APE_2);
        String sqlExtra = InteraccionBD.anadeSqlExtra(null, sqlOrderBy);
        
        ArrayList<ResponsableRA> resAux = getResponsables(null, null, null, null, null, null, null, activo, null, campos, sqlExtra, true);
        
        if (resAux != null) {
            ArrayList<String> nomCompleto;
            
            int n = resAux.size();
            res = new ArrayList<ArrayList<String>>();
            
            for (int i = 0; i < n; i++) {
                nomCompleto = new ArrayList<String>();
                
                nomCompleto.add(resAux.get(0).getNombre());
                nomCompleto.add(resAux.get(0).getApellido1());
                nomCompleto.add(resAux.get(0).getApellido2());
                
                res.add(nomCompleto);
            }
        }

        return res;
    }
    
    public static ArrayList<ArrayList<String>> getResponsables() throws SQLException, NoSuchFieldException {
        return getResponsablesGen(null);
    }
    
    public static ArrayList<ArrayList<String>> getResponsablesActivos() throws SQLException, NoSuchFieldException {
        return getResponsablesGen(true);
    }
    
    //Función para añadir una responsable a la BD
    public static int insertResponsable(Integer idResponsable, String nombre, String apellido1, String apellido2, String usuario, String password, String mail, Boolean activo, Integer rol, String sqlExtra) throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        String valores = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        ArrayList<String> campos = new ArrayList<String>();
        ArrayList<String> autoInc = new ArrayList<String>();
        ArrayList<String> condAutoInc = new ArrayList<String>();
        int res = 0;
        
        if (idResponsable != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, idResponsable);
            campos.add(CAMPO_ID_RESPONSABLE);
        } else {    //Es campo clave, asignamos el incremento de la última
            valores = InteraccionBD.anadeCampoValorAutoInc(valores, CAMPO_ID_RESPONSABLE);
            campos.add(CAMPO_ID_RESPONSABLE);
            autoInc.add(CAMPO_ID_RESPONSABLE);
            condAutoInc.add(null);
        }
        if (nombre != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, nombre);
            campos.add(CAMPO_NOMBRE);
        }
        if (apellido1 != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, apellido1);
            campos.add(CAMPO_APE_1);
        }
        if (apellido2 != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, apellido2);
            campos.add(CAMPO_APE_2);
        }
        if (usuario != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, usuario);
            campos.add(CAMPO_USUARIO);
        }
        if (password != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, password);
            campos.add(CAMPO_PASS);
        }
        if (mail != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, mail);
            campos.add(CAMPO_MAIL);
        }
        if (activo != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, activo);
            campos.add(CAMPO_ACTIVO);
        }
        if (rol != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, rol);
            campos.add(CAMPO_ROL);
        }
        
        interBD.inicioTransaccion();
        res = interBD.insertDatosTabla(TABLA, campos, valores, paramsPS, sqlExtra, autoInc, condAutoInc);
        interBD.finTransaccion();
        
        return res;
    }
    
    public static int insertResponsable(ResponsableRA responsable, String sqlExtra) throws SQLException {
        return insertResponsable(responsable.idResponsable, responsable.nombre, responsable.apellido1, responsable.apellido2, responsable.usuario, responsable.password, responsable.mail, responsable.activo, responsable.rol, sqlExtra);
    }
    
    //Función para añadir una responsable a la BD
    public static int updateResponsable(Integer idResponsable, String nombre, String apellido1, String apellido2, String usuario, String password, String mail, Boolean activo, Integer rol,
            Integer idResponsableVal, String nombreVal, String apellido1Val, String apellido2Val, String usuarioVal, String passwordVal, String mailVal, Boolean activoVal, Integer rolVal, String sqlExtra) throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        ArrayList<String> campos = new ArrayList<String>();
        int res = 0;
        
        if (idResponsableVal != null && !idResponsableVal.equals(idResponsable)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, idResponsableVal);
            campos.add(CAMPO_ID_RESPONSABLE);
        }
        if (nombreVal != null && !nombreVal.equals(nombre)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, nombreVal);
            campos.add(CAMPO_NOMBRE);
        }
        if (apellido1Val != null && !apellido1Val.equals(apellido1)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, apellido1Val);
            campos.add(CAMPO_APE_1);
        }
        if (apellido2Val != null && !apellido2Val.equals(apellido2)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, apellido2Val);
            campos.add(CAMPO_APE_2);
        }
        if (usuarioVal != null && !usuarioVal.equals(usuario)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, usuarioVal);
            campos.add(CAMPO_USUARIO);
        }
        if (passwordVal != null && !passwordVal.equals(password)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, passwordVal);
            campos.add(CAMPO_PASS);
        }
        if (mailVal != null && !mailVal.equals(mail)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, mailVal);
            campos.add(CAMPO_MAIL);
        }
        if (activoVal != null && !activoVal.equals(activo)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, activoVal);
            campos.add(CAMPO_ACTIVO);
        }
        if (rolVal != null && !rolVal.equals(rol)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, rolVal);
            campos.add(CAMPO_ROL);
        }
        
        //Condiciones de actualización
        condicion = getCondicion(idResponsable, nombre, apellido1, apellido2, usuario, password, mail, activo, rol, paramsPS);
         
        interBD.inicioTransaccion();
        res = interBD.updateDatosTabla(TABLA, campos, condicion, paramsPS, sqlExtra);
        interBD.finTransaccion();
        
        return res;
    }
    
    public static int updateResponsable(ResponsableRA responsableViejo, ResponsableRA responsableNuevo, String sqlExtra) throws SQLException {
        return updateResponsable(responsableViejo.idResponsable, responsableViejo.nombre, responsableViejo.apellido1, responsableViejo.apellido2, responsableViejo.usuario, responsableViejo.password, responsableViejo.mail, responsableViejo.activo, responsableViejo.rol,
            responsableNuevo.idResponsable, responsableNuevo.nombre, responsableNuevo.apellido1, responsableNuevo.apellido2, responsableNuevo.usuario, responsableNuevo.password, responsableNuevo.mail, responsableNuevo.activo, responsableNuevo.rol, sqlExtra);
    }
    
    //Función para añadir/modificar una responsable a la BD
    public static int insertOrUpdateResponsable(Integer idResponsable, String nombre, String apellido1, String apellido2, String usuario, String password, String mail, Boolean activo, Integer rol,
            Integer idResponsableVal, String nombreVal, String apellido1Val, String apellido2Val, String usuarioVal, String passwordVal, String mailVal, Boolean activoVal, Integer rolVal, String sqlExtra) throws SQLException {
        int res;
        
        res = updateResponsable(idResponsable, nombre, apellido1, apellido2, usuario, password, mail, activo, rol,
                idResponsableVal, nombreVal, apellido1Val, apellido2Val, usuarioVal, passwordVal, mailVal, activoVal, rolVal, sqlExtra);
        
        if (res < 0)
            res = insertResponsable(idResponsableVal, nombreVal, apellido1Val, apellido2Val, usuarioVal, passwordVal, mailVal, activoVal, rolVal, sqlExtra);

        return res;
    }
    
    public static int insertOrUpdateResponsable(ResponsableRA responsableViejo, ResponsableRA responsableNuevo, String sqlExtra) throws SQLException {
        
        return insertOrUpdateResponsable(responsableViejo.idResponsable, responsableViejo.nombre, responsableViejo.apellido1, responsableViejo.apellido2, responsableViejo.usuario, responsableViejo.password, responsableViejo.mail, responsableViejo.activo, responsableViejo.rol,
            responsableNuevo.idResponsable, responsableNuevo.nombre, responsableNuevo.apellido1, responsableNuevo.apellido2, responsableNuevo.usuario, responsableNuevo.password, responsableNuevo.mail, responsableNuevo.activo, responsableNuevo.rol, sqlExtra);
    }
    
    //Función para eliminar Responsables que se ajustan a los limites pasados
    public static int deleteResponsables(Integer idResponsable, String nombre, String apellido1, String apellido2, String usuario, String password, String mail, Boolean activo, Integer rol, String sqlExtra) throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        int res = 0;

        condicion = getCondicion(idResponsable, nombre, apellido1, apellido2, usuario, password, mail, activo, rol, paramsPS);
        
        interBD.inicioTransaccion();
        res  = interBD.deleteDatosTabla(TABLA, condicion, paramsPS, sqlExtra);
        interBD.finTransaccion();
        
        return res;
    }
    
    public static int deleteResponsables(ResponsableRA responsable, String sqlExtra) throws SQLException {
        return deleteResponsables(responsable.idResponsable, responsable.nombre, responsable.apellido1, responsable.apellido2, responsable.usuario, responsable.password, responsable.mail, responsable.activo, responsable.rol, sqlExtra);
    }
    
    public Object[] toObject() {
        return new Object[]{this.idResponsable, this.nombre, this.apellido1, this.apellido2, this.usuario, this.password, this.mail, this.activo, this.rol};
    }
  
    public static Object[] getCamposTabla() throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        return interBD.getCamposTabla(TABLA);
    }
} 