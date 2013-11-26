package RA;

import general.InteraccionBD;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClienteRA {
   private Integer idCliente;
    private String nombre;
    private String cif;
    private String direccion;
    private String telefono;
    private String fax;
    private String web;

    public static final String TABLA = "Cliente";
    public static final String CAMPO_ID_CLIENTE = "Id_cliente";
    public static final String CAMPO_NOMBRE = "Nombre";
    public static final String CAMPO_CIF = "CIF";
    public static final String CAMPO_DIRECCION = "Direccion";
    public static final String CAMPO_TELF = "Telefono";
    public static final String CAMPO_FAX = "Fax";
    public static final String CAMPO_WEB = "P_web";

    public ClienteRA(Integer idCliente, String nombre, String cif, String direccion, String telefono, String fax, String web) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.cif = cif;
        this.direccion = direccion;
        this.telefono = telefono;
        this.fax = fax;
        this.web = web;
    }
        
    public ClienteRA(Object[] valores, ArrayList<String> campos) throws SQLException, NoSuchFieldException {
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
    
    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }
      
    //Función para asignar valores a campos de la clase según el campo de la BD
    private void asignaCampo(String campo, Object valor) throws NoSuchFieldException {
        if (campo.compareTo(CAMPO_ID_CLIENTE) == 0) {
            this.idCliente = (Integer) valor;
        } else if (campo.compareTo(CAMPO_NOMBRE) == 0) {
            this.nombre = (String) valor;
        } else if (campo.compareTo(CAMPO_CIF) == 0) {
            this.cif = (String) valor;
        } else if (campo.compareTo(CAMPO_DIRECCION) == 0) {
            this.direccion = (String) valor;
        } else if (campo.compareTo(CAMPO_TELF) == 0) {
            this.telefono = (String) valor;
        } else if (campo.compareTo(CAMPO_FAX) == 0) {
            this.fax = (String) valor;
        } else if (campo.compareTo(CAMPO_WEB) == 0) {
            this.web = (String) valor;
        } else {
            throw new NoSuchFieldException("No existe el campo en la clase " + this.getClass().getSimpleName());
        }
    }
    
    private static String getCondicion(Integer idCliente, String nombre, String cif, String direccion, String telefono, String fax, String web, ArrayList<Object[]> paramsPS) {
        String condicion = "";
        
        if (idCliente != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_ID_CLIENTE, "=", idCliente);
        }
        if (nombre != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_NOMBRE, "=", nombre);
        }
        if (cif != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_CIF, "=", cif);
        }
        if (direccion != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_DIRECCION, "=", direccion);
        }
        if (telefono != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_TELF, "=", telefono);
        }
        if (fax != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_FAX, "=", fax);
        }
        if (web != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_WEB, "=", web);
        }
        
        return condicion;
    }

    //Función para obtener la colección de Clientes que se ajustan a los limites pasados
    public static ArrayList<ClienteRA> getClientes(Integer idCliente, String nombre, String cif, String direccion, String telefono, String fax, String web,
            ArrayList<String> campos, String sqlExtra, Boolean distinct) throws SQLException, NoSuchFieldException {
        InteraccionBD interBD = new InteraccionBD();
        
        ArrayList<ClienteRA> res = null;
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();

        condicion = getCondicion(idCliente, nombre, cif, direccion, telefono, fax, web, paramsPS);
                
        //Por defecto lo devolvemos ordenado por cliente
        if (sqlExtra == null || sqlExtra.trim().length() == 0) {
            String sqlOrderBy = InteraccionBD.anadeCampoOrderBy(null, CAMPO_ID_CLIENTE);
            sqlExtra = InteraccionBD.anadeSqlExtra(null, sqlOrderBy);
        }

        ArrayList<Object[]> resAux = interBD.getDatosTabla(TABLA, campos, condicion, paramsPS, sqlExtra, distinct);

        if (resAux != null) {
            int n = resAux.size();
            res = new ArrayList<ClienteRA>();
            
            for (int i = 0; i < n; i++) {
               res.add(new ClienteRA(resAux.get(i), campos));
            }
        }

        return res;
    }
    
    //Función para obtener el cliente por Id
    public static ClienteRA getClientePorId (Integer idCliente) throws SQLException, NoSuchFieldException {
        ClienteRA res = null;
        
        ArrayList<ClienteRA> resAux = getClientes(idCliente, null, null, null, null, null, null, null, null, null);
        
        if (resAux != null) {
            res = resAux.get(0);
        }

        return res;
    }
    
    //Función para obtener el cliente por nombre
    public static ClienteRA getClientePorNombre(String nombre) throws SQLException, NoSuchFieldException {
        ClienteRA res = null;
        
        ArrayList<ClienteRA> resAux = getClientes(null, nombre, null, null, null, null, null, null, null, null);
        
        if (resAux != null) {
            res = resAux.get(0);
        }

        return res;
    }
    
    //Función para obtener los nombres
    public static ArrayList<String> getClientes() throws SQLException, NoSuchFieldException {
        ArrayList<String> res = null;
        
        ArrayList<String> campos = new ArrayList<String>();
        
        //Solo nos interesan los nombres
        campos.add(CAMPO_NOMBRE);
        
        //Ordenamos por nombre
        String sqlOrderBy = InteraccionBD.anadeCampoOrderBy(null, CAMPO_NOMBRE);
        String sqlExtra = InteraccionBD.anadeSqlExtra(null, sqlOrderBy);
        
        ArrayList<ClienteRA> resAux = getClientes(null, null, null, null, null, null, null, campos, sqlExtra, true);
        
        if (resAux != null) {
            int n = resAux.size();
            res = new ArrayList<String>();
            
            for (int i = 0; i < n; i++) {
                res.add(resAux.get(i).getNombre());
            }
        }

        return res;
    }
    
    //Función para añadir una cliente a la BD
    public static int insertClienteContactos(Integer idCliente, String nombre, String cif, String direccion, String telefono, String fax, String web, ArrayList<String[]> contactoCliente, String sqlExtra) throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        String valores = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        ArrayList<String> campos = new ArrayList<String>();
        ArrayList<String> autoInc = new ArrayList<String>();
        ArrayList<String> condAutoInc = new ArrayList<String>();
        int res = 0;
        
        if (idCliente != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, idCliente);
            campos.add(CAMPO_ID_CLIENTE);
        } else {    //Es campo clave, asignamos el incremento de la última
            valores = InteraccionBD.anadeCampoValorAutoInc(valores, CAMPO_ID_CLIENTE);
            campos.add(CAMPO_ID_CLIENTE);
            autoInc.add(CAMPO_ID_CLIENTE);
            condAutoInc.add(null);
        }
        if (nombre != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, nombre);
            campos.add(CAMPO_NOMBRE);
        }
        if (cif != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, cif);
            campos.add(CAMPO_CIF);
        }
        if (direccion != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, direccion);
            campos.add(CAMPO_DIRECCION);
        }
        if (telefono != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, telefono);
            campos.add(CAMPO_TELF);
        }
        if (fax != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, fax);
            campos.add(CAMPO_FAX);
        }
        if (web != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, web);
            campos.add(CAMPO_WEB);
        }
        
        interBD.inicioTransaccion();
        res = interBD.insertDatosTabla(TABLA, campos, valores, paramsPS, sqlExtra, autoInc, condAutoInc);

        //Insertamos los contactos del cliente
        Contacto.insertContactoArray(idCliente, contactoCliente);

        interBD.finTransaccion();
        
        return res;
    }
    
    //Función para añadir una cliente a la BD
    public static int insertCliente(Integer idCliente, String nombre, String cif, String direccion, String telefono, String fax, String web, String sqlExtra) throws SQLException {
        return insertClienteContactos(idCliente, nombre, cif, direccion, telefono, fax, web, null, sqlExtra);
    }
    
    public static int insertCliente(ClienteRA cliente, String sqlExtra) throws SQLException {
        return insertCliente(cliente.idCliente, cliente.nombre, cliente.cif, cliente.direccion, cliente.telefono, cliente.fax, cliente.web, sqlExtra);
    }
    
    //Función para añadir una cliente a la BD
    public static int updateCliente(Integer idCliente, String nombre, String cif, String direccion, String telefono, String fax, String web,
            Integer idClienteVal, String nombreVal, String cifVal, String direccionVal, String telefonoVal, String faxVal, String webVal, String sqlExtra) throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        ArrayList<String> campos = new ArrayList<String>();
        int res = 0;
        
        if (idClienteVal != null && !idClienteVal.equals(idCliente)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, idClienteVal);
            campos.add(CAMPO_ID_CLIENTE);
        }
        if (nombreVal != null && !nombreVal.equals(nombre)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, nombreVal);
            campos.add(CAMPO_NOMBRE);
        }
        if (cifVal != null && !cifVal.equals(cif)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, cifVal);
            campos.add(CAMPO_CIF);
        }
        if (direccionVal != null && !direccionVal.equals(direccion)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, direccionVal);
            campos.add(CAMPO_DIRECCION);
        }
        if (telefonoVal != null && !telefonoVal.equals(telefono)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, telefonoVal);
            campos.add(CAMPO_TELF);
        }
        if (faxVal != null && !faxVal.equals(fax)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, faxVal);
            campos.add(CAMPO_FAX);
        }
        if (webVal != null && !webVal.equals(web)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, webVal);
            campos.add(CAMPO_WEB);
        }
        
        //Condiciones de actualización
        condicion = getCondicion(idCliente, nombre, cif, direccion, telefono, fax, web, paramsPS);
         
        interBD.inicioTransaccion();
        res = interBD.updateDatosTabla(TABLA, campos, condicion, paramsPS, sqlExtra);
        interBD.finTransaccion();
        
        return res;
    }
    
    public static int updateCliente(ClienteRA clienteViejo, ClienteRA clienteNuevo, String sqlExtra) throws SQLException {
        return updateCliente(clienteViejo.idCliente, clienteViejo.nombre, clienteViejo.cif, clienteViejo.direccion, clienteViejo.telefono, clienteViejo.fax, clienteViejo.web,
            clienteNuevo.idCliente, clienteNuevo.nombre, clienteNuevo.cif, clienteNuevo.direccion, clienteNuevo.telefono, clienteNuevo.fax, clienteNuevo.web, sqlExtra);
    }
    
    //Función para añadir/modificar una cliente a la BD
    public static int insertOrUpdateCliente(Integer idCliente, String nombre, String cif, String direccion, String telefono, String fax, String web,
            Integer idClienteVal, String nombreVal, String cifVal, String direccionVal, String telefonoVal, String faxVal, String webVal, String sqlExtra) throws SQLException {
        int res;
        
        res = updateCliente(idCliente, nombre, cif, direccion, telefono, fax, web,
                idClienteVal, nombreVal, cifVal, direccionVal, telefonoVal, faxVal, webVal, sqlExtra);
        
        if (res < 0)
            res = insertCliente(idClienteVal, nombreVal, cifVal, direccionVal, telefonoVal, faxVal, webVal, sqlExtra);

        return res;
    }
    
    public static int insertOrUpdateCliente(ClienteRA clienteViejo, ClienteRA clienteNuevo, String sqlExtra) throws SQLException {
        
        return insertOrUpdateCliente(clienteViejo.idCliente, clienteViejo.nombre, clienteViejo.cif, clienteViejo.direccion, clienteViejo.telefono, clienteViejo.fax, clienteViejo.web,
            clienteNuevo.idCliente, clienteNuevo.nombre, clienteNuevo.cif, clienteNuevo.direccion, clienteNuevo.telefono, clienteNuevo.fax, clienteNuevo.web, sqlExtra);
    }
    
    //Función para eliminar Clientes que se ajustan a los limites pasados
    public static int deleteClientes(Integer idCliente, String nombre, String cif, String direccion, String telefono, String fax, String web, String sqlExtra) throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        int res = 0;

        condicion = getCondicion(idCliente, nombre, cif, direccion, telefono, fax, web, paramsPS);
        
        interBD.inicioTransaccion();
        res  = interBD.deleteDatosTabla(TABLA, condicion, paramsPS, sqlExtra);
        interBD.finTransaccion();
        
        return res;
    }
    
    public static int deleteClientes(ClienteRA cliente, String sqlExtra) throws SQLException {
        return deleteClientes(cliente.idCliente, cliente.nombre, cliente.cif, cliente.direccion, cliente.telefono, cliente.fax, cliente.web, sqlExtra);
    }
    
    public Object[] toObject() {
        return new Object[]{this.idCliente, this.nombre, this.cif, this.direccion, this.telefono, this.fax, this.web};
    }
  
    public static Object[] getCamposTabla() throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        return interBD.getCamposTabla(TABLA);
    }
} 