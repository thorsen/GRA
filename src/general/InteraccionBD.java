/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package general;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import userinterfaces.GRA;

/**
 *
 * @author Victor Fernandez
 */
public class InteraccionBD {

    private Connection conexion;
    public static final String URL = "jdbc:sqlserver://192.168.1.165:1433";
    public static final String USER = "SQL_PwC";
    public static final String PASS = "Ru8865No";
    
    public static final String PREF_BD_GENERAL = "Power_Curve.dbo.";
    public static final String PREF_BD_RA = "Acoustic_Noise.dbo.";
    
//    public static final String URL = "jdbc:odbc:SqlServer";
//    public static final String USER = "sa";
//    public static final String PASS = "qwerty";
    public static final char TIPO_SELECT = 'S';
    public static final char TIPO_INSERT = 'I';
    public static final char TIPO_UPDATE = 'U';
    public static final char TIPO_DELETE = 'D';
    private static final String CLAUSULA_AND = "AND";
    private static final String CLAUSULA_OR = "OR";
    public static final String ASIGNACION_CAMPOS = "CampoBD";
    public static final String COND_SIN_OPERACION = "CondSinOpe";
    public static final String IGNORAR_PARAM_PS = "IGNORAR_PARAM_PS";
    
    public static final String TABLA_DATOS = "Datos";
    public static final String TABLA_DESC = "Descripcion";
    
    public static final String VISTA_DATOS = "Base";

    static {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            MensajeApp.muestraError(GRA.getFrames()[0], e, "No se pudo cargar el driver de la BD");
        }
    }

    public InteraccionBD(Connection conexion) {
        this.conexion = conexion;
    }

    public InteraccionBD() throws SQLException {
        this.conexion = null;
    }

    private Connection nuevaConexion() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
    
    //Función para comenzar la transacción    
    public void inicioTransaccion() throws SQLException {
        this.conexion = nuevaConexion();
        this.conexion.setAutoCommit(false);
    }
    
    //Función para acabar la transacción y cerrar la conexión    
    public void finTransaccion() throws SQLException {
        this.conexion.commit();
        this.conexion.close();

        //Liberamos la conexión, porque ya está cerrada
        this.conexion = null;
    }

    private Connection getConexion() throws SQLException {
        Connection con = null;

        if (this.conexion == null) {
            con = nuevaConexion();
        } else {
            con = this.conexion;
        }
        return con;
    }

    private void cierraConexionLocal(Connection con) throws SQLException {
        //Si es una conexión local, se puede cerrar
        if (con != this.conexion) {
            con.close();
        }
    }
    
    private void rollbackGlobal(Connection con) throws SQLException {
        //Si es la conexión de nivel superior, hacemos rollback
        if (con == this.conexion) {
            con.rollback();
        }
    }
    
    public void rollback() throws SQLException {
        rollbackGlobal(this.conexion);
    }

    //Función que devuelve el nombre de todos los campos de la tabla <tabla>
    //como un array de object
    public Object[] getCamposTabla(String tabla) throws SQLException {
        ArrayList<String> resAux = null;
        Object[] res = null;
        Connection con = null;

        try {
            String tablas[] = tabla.split(",");
            int nTablas = tablas.length;
            
            con = getConexion();

            String sql = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME=";
            
            for (int i = 0; i < nTablas; i++) {
                sql += "? OR TABLE_NAME=";
            }
            sql = sql.substring(0, sql.length() - "? OR TABLE_NAME=".length() + 1);
            
            PreparedStatement ps = con.prepareStatement(sql);

            for (int i = 0; i < nTablas; i++) {
                ps.setString(i+1, tablas[i].trim());
            }
            
            ResultSet rs = ps.executeQuery();

            resAux = new ArrayList<String>();

            while (rs.next()) {
                resAux.add(rs.getString(1));
            }
            
            rs.close();
            ps.close();
            cierraConexionLocal(con);

            res = resAux.toArray();
        } catch (SQLException e) {
            if (con != null)
                    rollbackGlobal(con);
            throw e;
        } finally {
            if (con != null) {
                cierraConexionLocal(con);
            }
            return res;
        }
    }

    //Función que devuelve la consulta SQL para una tabla <tabla> según los campos pasados <campos>
    //y la condición <condicion>
    //Si <campos> es vacío, devolverá todas las columnas
    //Si <condicion> es vacía o nula no habrá WHERE
    private static String construyeSelect(String tabla, ArrayList<String> campos, String condicion, String sqlExtra, Boolean distinct) {
        String sql = "SELECT ";

        if (distinct != null && distinct) {
            sql += "DISTINCT ";        //Añadimos los campos
        }
        if (campos != null && !campos.isEmpty()) {
            Iterator i = campos.iterator();

            while (i.hasNext()) {
                sql += i.next() + ", ";
            }
            sql = sql.substring(0, sql.length() - 2) + " "; //Sustituimos el último ", " por " "
        } else {
            sql += "* ";
        }

        //Añadimos la tabla
        if (tabla != null)
            sql += "FROM " + tabla;

        //Añadimos la condición
        if (condicion != null && condicion.length() > 0) {
            sql += " WHERE " + condicion;
        }

        //Añadimos la parte extra que se haya podido añadir (ORDER BY, GROUP BY...)
        if (sqlExtra != null && sqlExtra.length() > 0) {
            sql += " " + sqlExtra;
        }

        return (sql);
    }
    
    //Función que devuelve la consulta SQL para insertar valores en una tabla <tabla> según los campos pasados <campos>
    //y los valores <valores>
    private static String construyeInsert(String tabla, ArrayList<String> campos, String valores, String sqlExtra, ArrayList<String> autoInc, ArrayList<String> condAutoInc) {
        String sql = "";
        String campo, cond;
        int nAutoInc;
        
        //Si existe la tabla
        sql = dameSqlExisteTabla(tabla);
        
        //Si neesita autoincrementar, hay que declarar variables
        if (autoInc != null && !autoInc.isEmpty() && autoInc.size() == condAutoInc.size()) {
            sql += "DECLARE ";
            String sqlSelAutoInc = "BEGIN TRAN\n";

            nAutoInc = autoInc.size();

            for (int i = 0; i < nAutoInc; i++) {
                campo = autoInc.get(i);
                cond = condAutoInc.get(i);

                sql += "@" + campo + " int, ";
                sqlSelAutoInc += "SELECT @" + campo + " = ISNULL(MAX(" + campo + "), 0) + 1 FROM " + tabla + " WITH (UPDLOCK, HOLDLOCK)";

                if (cond != null && cond.length() != 0) {
                    sqlSelAutoInc += " WHERE " + cond;
                }
                
                sqlSelAutoInc += "\n";
            }

            sql = sql.substring(0, sql.length() - 2) + "\n"; //Sustituimos el último ", " por "\n"

            sql += sqlSelAutoInc;
        }

        //Inicializamos con la tabla
        sql += "INSERT INTO " + tabla + " ";

        //Añadimos los campos
        if (campos != null && !campos.isEmpty()) {
            Iterator i = campos.iterator();

            sql += "(";

            while (i.hasNext()) {
                sql += i.next() + ", ";
            }
            sql = sql.substring(0, sql.length() - 2) + ")\n"; //Sustituimos el último ", " por ")\n"
        }

        //Añadimos los valores
        sql += "VALUES (" + valores + ")\n";

        //Añadimos la parte extra que se haya podido añadir (ORDER BY, GROUP BY...)
        if (sqlExtra != null && sqlExtra.length() > 0) {
            sql += " " + sqlExtra + "\n";
        }
        
        //Cerramos la transaccion abierta
        if (autoInc != null && autoInc.size() > 0) {
            sql += "COMMIT TRAN\n";
        }

        return (sql);
    }
    //Función que devuelve la consulta SQL para actualizar valores en una tabla <tabla> según los campos pasados <campos>
    //con la condición <condicion>
    private static String construyeUpdateFrom(String tabla, ArrayList<String> tablas, ArrayList<String> campos, String condicion, String sqlExtra) {
        //Si existe la tabla
        String sql = dameSqlExisteTabla(tabla);
        String campo;
        
        //Inicializamos con la tabla
        sql += "UPDATE " + tabla + " SET ";

        //Añadimos los campos
        if (campos != null && !campos.isEmpty()) {
            Iterator i = campos.iterator();

            while (i.hasNext()) {
                campo = (String) i.next();
                
                if (campo.startsWith(IGNORAR_PARAM_PS))
                    sql += campo.substring(IGNORAR_PARAM_PS.length()) + ", ";
                else
                    sql += campo + "=?, ";
            }
            sql = sql.substring(0, sql.length() - 2) + " "; //Sustituimos el último ", " por " "
        }
        
        //Añadimos las tablas
        int nTablas;
        if (tablas != null && (nTablas = tablas.size()) > 0) {
            sql += "FROM ";
            
            for (int i = 0; i < nTablas; i++) {
                sql += tablas.get(i) + ", ";
            }
            sql = sql.substring(0, sql.length() - 2) + " "; //Sustituimos el último ", " por " "
        }

        //Añadimos la condición
        if (condicion != null && condicion.length() > 0) {
            sql += "WHERE " + condicion;
        }

        //Añadimos la parte extra que se haya podido añadir (ORDER BY, GROUP BY...)
        if (sqlExtra != null && sqlExtra.length() > 0) {
            sql += " " + sqlExtra;
        }

        return (sql);
    }
    //Función que devuelve la consulta SQL para actualizar valores en una tabla <tabla> según los campos pasados <campos>
    //con la condición <condicion>
    private static String construyeUpdate(String tabla, ArrayList<String> campos, String condicion, String sqlExtra) {
        return construyeUpdateFrom(tabla, null, campos, condicion, sqlExtra);
    }
    //Función que devuelve la consulta SQL para eliminar valores en una tabla <tabla> según la condición <condicion>
    private static String construyeDelete(String tabla, String condicion, String sqlExtra) {
        //Si existe la tabla
        String sql = dameSqlExisteTabla(tabla);
        
        //Inicializamos con la tabla
        sql += "DELETE FROM " + tabla + " ";

        //Añadimos la condición
        if (condicion != null && condicion.length() > 0) {
            sql += "WHERE " + condicion;
        }

        //Añadimos la parte extra que se haya podido añadir (ORDER BY, GROUP BY...)
        if (sqlExtra != null && sqlExtra.length() > 0) {
            sql += " " + sqlExtra;
        }

        return (sql);
    }
    //Función que carga los valores de los campos del <ps> según el tipo y valor de los <paramsPS>
    //Si falta algún tipo, añadir de lo comentado al final de la función
    private static void cargaParamsPS(PreparedStatement ps, ArrayList<Object[]> paramsPS, Connection con) throws SQLException {
        if (paramsPS != null && !paramsPS.isEmpty()) {
            Object[] fila;
            String tipo;
            int pos = 0;

            Iterator i = paramsPS.iterator();

            while (i.hasNext()) {
                pos++;
                fila = (Object[]) i.next();

                //Discriminamos los tipos
                tipo = (String) fila[0];
                if (tipo.compareTo("Integer") == 0) {
                    ps.setInt(pos, ((Integer) fila[1]).intValue());
                } else if (tipo.compareTo("int") == 0) {
                    ps.setInt(pos, ((Integer) fila[1]).intValue());
                } else if (tipo.compareTo("double") == 0) {
                    ps.setDouble(pos, ((Double) fila[1]).doubleValue());
                } else if (tipo.compareTo("float") == 0) {
                    ps.setFloat(pos, ((Float) fila[1]).floatValue());
                } else if (tipo.compareTo("long") == 0) {
                    ps.setLong(pos, ((Long) fila[1]).longValue());
                } else if (tipo.compareTo("boolean") == 0) {
                    ps.setBoolean(pos, (Boolean) fila[1]);
                } else if (tipo.compareTo("String") == 0) {
                    ps.setString(pos, (String) fila[1]);
                } else if (tipo.compareTo("Character") == 0) {
                    ps.setString(pos, ((Character) fila[1]).toString());
                } else if (tipo.compareTo("Date") == 0) {
                    ps.setDate(pos, (Date) fila[1]);
                } else if (tipo.compareTo("Timestamp") == 0) {
                    ps.setTimestamp(pos, (Timestamp) fila[1]);
                } else if (tipo.compareTo("BigDecimal") == 0) {
                    ps.setBigDecimal(pos, (BigDecimal) fila[1]);
                } else if (tipo.compareTo("SQLXML") == 0) {
                    SQLXML xmlVal = con.createSQLXML();
                    xmlVal.setString((String) fila[1]);
                    
                    ps.setSQLXML(pos, xmlVal);
                } else //Opción por defecto
                {
                    ps.setObject(pos, fila[1]);////////////////////////////////////////////////////////////////////////////////
/////////////////////////FUNCIONES POR AÑADIR///////////////////////////////////
////////////////////////////////////////////////////////////////////////////////                
//ps.setArray(pos, Array x) 
//ps.setAsciiStream(pos, InputStream x) 
//ps.setAsciiStream(pos, InputStream x, int length) 
//ps.setAsciiStream(pos, InputStream x, long length) 
//ps.setBinaryStream(pos, InputStream x) 
//ps.setBinaryStream(pos, InputStream x, int length) 
//ps.setBinaryStream(pos, InputStream x, long length)  setBlob(pos, Blob x) 
//ps.setBlob(pos, InputStream inputStream) 
//ps.setBlob(pos, InputStream inputStream, long length) 
//ps.setByte(pos, byte x) 
//ps.setBytes(pos, byte[] x) 
//ps.setCharacterStream(pos, Reader reader) 
//ps.setCharacterStream(pos, Reader reader, int length) 
//ps.setCharacterStream(pos, Reader reader, long length) 
//ps.setClob(pos, Clob x) 
//ps.setClob(pos, Reader reader) 
//ps.setClob(pos, Reader reader, long length) 
//ps.setNCharacterStream(pos, Reader value) 
//ps.setNCharacterStream(pos, Reader value, long length) 
//ps.setNClob(pos, NClob value) 
//ps.setNClob(pos, Reader reader) 
//ps.setNClob(pos, Reader reader, long length) 
//ps.setNString(pos, String value) 
//ps.setNull(pos, int sqlType) 
//ps.setNull(pos, int sqlType, String typeName) 
//ps.setObject(pos, Object x, int targetSqlType) 
//ps.setObject(pos, Object x, int targetSqlType, int scaleOrLength) 
//ps.setRef(pos, Ref x) 
//ps.setRowId(pos, RowId x) 
//ps.setShort(pos, short x) 
//ps.setSQLXML(pos, SQLXML xmlObject) 
//ps.setTime(pos, Time x, Calendar cal) 
//ps.setTime(pos, Time x, Calendar cal) 
//ps.setTimestamp(pos, Timestamp x) 
//ps.setUnicodeStream(pos, InputStream x, int length) 
//ps.setURL(pos, URL x)             
////////////////////////////////////////////////////////////////////////////////             
                }
            }
        }
    }

    //Función que devuelve los registros de una tabla <tabla> según los campos pasados <campos> y la condición <condicion>
    //En <paramsPS> se pasan pares de tipodeDato,valor para un PreparedStatement
    public ArrayList<Object[]> getDatosTablaIfExists(String tabla, ArrayList<String> campos, String condicion, ArrayList<Object[]> paramsPS, String sqlExtra, Boolean distinct, Boolean ifExists) throws SQLException {
        Connection con = null;
        ArrayList<Object[]> res = null;

        try {
            int numCols = 0;
            Object[] fila;

            con = getConexion();
            String sql = construyeSelect(tabla, campos, condicion, sqlExtra, distinct);
            
            if (campos != null && !campos.isEmpty()) {
                String campoAct;
                int nCampos = campos.size();
                
                for (int i = 0; i < nCampos; i++) {
                    campoAct = campos.get(i);
                    
                    if (campoAct.contains("*")) {
                        if (campoAct.contains("(*)")) //Hace referencia a una función sobre todos los campos
                            numCols++;
                        else if (campoAct.contains(".")) //Hace referencia a todos los campos de una tabla
                            numCols += getCamposTabla(campoAct.substring(0, campoAct.indexOf("."))).length;
                        else //hace referencia a todos los campos que vengan en <tabla>
                            numCols += getCamposTabla(tabla).length;
                    } else { //Es un campo normal
                        numCols++;
                    }
                }
            } else {
                numCols = getCamposTabla(tabla).length;
            }
            
            if (ifExists != null && ifExists) {
                sql = dameSqlExisteTabla(tabla) + sql;
                sql += dameElseSqlExisteTabla();
            }
            
            PreparedStatement ps = con.prepareStatement(sql);
            cargaParamsPS(ps, paramsPS, con);
            
            ResultSet rs = ps.executeQuery();

            res = new ArrayList<Object[]>();

            if (rs != null) {
                while (rs.next()) {
                    fila = new Object[numCols];

                    for (int i = 0; i < numCols; i++) {
                        fila[i] = rs.getObject(i + 1);
                    }
                    res.add(fila);
                }
            }

            if (res.size() == 0) {
                res = null;
            }
            rs.close();
            ps.close();
            cierraConexionLocal(con);
        } catch (SQLException e) {
            if (con != null)
                    rollbackGlobal(con);
            throw e;
        } finally {
            if (con != null) {
                cierraConexionLocal(con);
            }
        }

        return res;
    }
    
    public ArrayList<Object[]> getDatosTabla(String tabla, ArrayList<String> campos, String condicion, ArrayList<Object[]> paramsPS, String sqlExtra, Boolean distinct) throws SQLException {
        return getDatosTablaIfExists(tabla, campos, condicion, paramsPS, sqlExtra, distinct, null);
    }
    //Función que devuelve el número de registros insertados de una tabla <tabla> según los campos pasados <campos> y los 
    //valores <valores>. En <paramsPS> se pasan pares de tipodeDato,valor para un PreparedStatement
    //En <autoInc> vienen los campos que deberán ser autoincrementados
    public int insertDatosTabla(String tabla, ArrayList<String> campos, String valores, ArrayList<Object[]> paramsPS, String sqlExtra, ArrayList<String> autoInc, ArrayList<String> condAutoInc) throws SQLException {
        Connection con = null;
        int res = 0;

        if (valores != null) { //Si no, no hay nada que insertar
            try {
                con = getConexion();

                String sql = construyeInsert(tabla, campos, valores, sqlExtra, autoInc, condAutoInc);

                PreparedStatement ps = con.prepareStatement(sql);
                cargaParamsPS(ps, paramsPS, con);
                
                res = ps.executeUpdate();

                ps.close();
                cierraConexionLocal(con);
            } catch (SQLException e) {
                if (con != null)
                    rollbackGlobal(con);
                throw e;
            } finally {
                if (con != null) {
                    cierraConexionLocal(con);
                }
            }
        }

        return res;
    }
    
    //Función que devuelve el número de registros insertados de una tabla <tabla> según los campos pasados <campos> y los 
    //valores <valores>. En <paramsPS> se pasan pares de tipodeDato,valor para un PreparedStatement
    public int insertDatosTabla(String tabla, ArrayList<String> campos, String valores, ArrayList<Object[]> paramsPS, String sqlExtra) throws SQLException {
        return insertDatosTabla(tabla, campos, valores, paramsPS, sqlExtra, null, null);
    }
    
    //Función que devuelve el número de registros modificados de una tabla <tabla> según los campos pasados <campos> 
    //dada la condición <condicion>. En <paramsPS> se pasan pares de tipodeDato,valor para un PreparedStatement
    public int updateDatosTablaFrom(String tabla, ArrayList<String> tablas, ArrayList<String> campos, String condicion, ArrayList<Object[]> paramsPS, String sqlExtra) throws SQLException {
        Connection con = null;
        int res = 0;

        if (campos != null && !campos.isEmpty()) { //Si no, no hay nada que actualizar
            try {
                con = getConexion();

                String sql = construyeUpdateFrom(tabla, tablas, campos, condicion, sqlExtra);

                PreparedStatement ps = con.prepareStatement(sql);
                cargaParamsPS(ps, paramsPS, con);
                res = ps.executeUpdate();
                
                if (res == 0)
                    res = -1;   //Para discriminar el caso de no haber nada que actualizar del que no se ha podido hacer

                ps.close();
                cierraConexionLocal(con);
            } catch (SQLException e) {
                if (con != null)
                    rollbackGlobal(con);
                throw e;
            } finally {
                if (con != null) {
                    cierraConexionLocal(con);
                }
            }
        }

        return res;
    }
    
    //Función que devuelve el número de registros modificados de una tabla <tabla> según los campos pasados <campos> 
    //dada la condición <condicion>. En <paramsPS> se pasan pares de tipodeDato,valor para un PreparedStatement
    public int updateDatosTabla(String tabla, ArrayList<String> campos, String condicion, ArrayList<Object[]> paramsPS, String sqlExtra) throws SQLException {
        return updateDatosTablaFrom(tabla, null, campos, condicion, paramsPS, sqlExtra);
    }
    //Función que devuelve el número de registros eliminados de una tabla <tabla> según la condición <condicion>.
    //En <paramsPS> se pasan pares de tipodeDato,valor para un PreparedStatement
    public int deleteDatosTabla(String tabla, String condicion, ArrayList<Object[]> paramsPS, String sqlExtra) throws SQLException {
        Connection con = null;
        int res = 0;

        if (condicion != null && condicion.length() > 0) { //Debe haber una condición de borrado
            try {
                con = getConexion();

                String sql = construyeDelete(tabla, condicion, sqlExtra);

                PreparedStatement ps = con.prepareStatement(sql);
                cargaParamsPS(ps, paramsPS, con);
                res = ps.executeUpdate();

                ps.close();
                cierraConexionLocal(con);
            } catch (SQLException e) {
                if (con != null)
                    rollbackGlobal(con);
                throw e;
            } finally {
                if (con != null) {
                    cierraConexionLocal(con);
                }
            }
        }

        return res;
    }
    //PARA SELECT
    //Función auxiliar para ir rellenando la candena de condicion <condicion> y la lista de parámetros <paramsPS>
    //PARA UPDATE ==> Llamar anadeCampoValor y anadeCampoCondicion
    public static String anadeCampoCondicionClausula(String condicion, ArrayList<Object[]> paramsPS, String campo, String clausula, String operacion, Object valor) {
        if (condicion == null || condicion.length() == 0) {
            if (clausula.compareToIgnoreCase(CLAUSULA_AND) == 0) {
                condicion = "1=1";
            } else {
                condicion = "1=0";
            }
        }
        if (paramsPS == null && !operacion.contains(ASIGNACION_CAMPOS) && !operacion.contains(COND_SIN_OPERACION)) {
            paramsPS = new ArrayList<Object[]>();
        }
        
        if (operacion.contains(ASIGNACION_CAMPOS)) {
            operacion = operacion.substring(operacion.indexOf(ASIGNACION_CAMPOS) + ASIGNACION_CAMPOS.length());
            if (operacion.length() > 0)
                condicion += " " + clausula + " " + campo + " " + operacion + " " + valor;
            else
                condicion += " " + clausula + " " + campo + " = " + valor;
        } else if (operacion.contains(COND_SIN_OPERACION)) {
            condicion += " " + clausula + " " + campo;
        } else {
            condicion += " " + clausula + " " + campo + " " + operacion + " ?";
            paramsPS.add(paramsPS.size(), new Object[]{valor.getClass().getSimpleName(), valor});
        }

        return condicion;
    }

    public static String anadeCampoCondicionAnd(String condicion, ArrayList<Object[]> paramsPS, String campo, String operacion, Object valor) {
        return anadeCampoCondicionClausula(condicion, paramsPS, campo, CLAUSULA_AND, operacion, valor);
    }

    public static String anadeCampoCondicionOr(String condicion, ArrayList<Object[]> paramsPS, String campo, String operacion, Object valor) {
        return anadeCampoCondicionClausula(condicion, paramsPS, campo, CLAUSULA_OR, operacion, valor);
    }
    //Por defecto la condición es un And
    public static String anadeCampoCondicion(String condicion, ArrayList<Object[]> paramsPS, String campo, String operacion, Object valor) {
        return anadeCampoCondicionAnd(condicion, paramsPS, campo, operacion, valor);
    }
    //PARA INSERT
    //Función auxiliar para ir rellenando la candena de valores <valores> y la lista de parámetros <paramsPS>
    //PARA UPDATE ==> Llamar anadeCampoValor sin recoger la cadena y pasar campos que interesen
    public static String anadeCampoValor(String valores, ArrayList<Object[]> paramsPS, Object valor) {
        if (valores == null) {
            valores = "";
        }
        if (paramsPS == null) {
            paramsPS = new ArrayList<Object[]>();
        }
        if (valores.length() > 0) {
            valores += ", ";
        }
        valores += "?";

        paramsPS.add(paramsPS.size(), new Object[]{valor.getClass().getSimpleName(), valor});

        return valores;
    }
    
    public static String anadeCampoValorTipo(String valores, ArrayList<Object[]> paramsPS, Object valor, String tipo) {
        if (valores == null) {
            valores = "";
        }
        if (paramsPS == null) {
            paramsPS = new ArrayList<Object[]>();
        }
        if (valores.length() > 0) {
            valores += ", ";
        }
        valores += "?";

        paramsPS.add(paramsPS.size(), new Object[]{tipo, valor});

        return valores;
    }
    
    //PARA INSERT
    //Función auxiliar para ir rellenando la candena de valores <valores> y la lista de parámetros <paramsPS>
    //PARA UPDATE ==> Llamar anadeCampoValor sin recoger la cadena y pasar campos que interesen
    public static String anadeCampoValorAutoInc(String valores, String campo) {
        if (valores == null) {
            valores = "";
        }
        if (valores.length() > 0) {
            valores += ", ";
        }
        
        valores += "@" + campo;

        return valores;
    }

    public static String dameSentenciaSql(char Tipo, String tabla, ArrayList<String> campos, String condValor, ArrayList<Object[]> paramsPS, String sqlExtra, Boolean distinct, ArrayList<String> autoInc, ArrayList<String> condAutoInc) throws ClassNotFoundException {
        String res = null;

        switch (Tipo) {
            case TIPO_SELECT:
                res = construyeSelect(tabla, campos, condValor, sqlExtra, distinct);
                break;
            case TIPO_INSERT:
                res = construyeInsert(tabla, campos, condValor, sqlExtra, autoInc, condAutoInc);
                break;
            case TIPO_UPDATE:
                res = construyeUpdate(tabla, campos, condValor, sqlExtra);
                break;
            case TIPO_DELETE:
                res = construyeDelete(tabla, condValor, sqlExtra);
                break;
        }

        if (res != null && paramsPS != null && !paramsPS.isEmpty()) {
            Object[] fila;
            String tipo, valor;
            int pos;

            Iterator i = paramsPS.iterator();

            while (i.hasNext()) {
                fila = (Object[]) i.next();

                tipo = (String) fila[0];

                if (tipo.contentEquals("String")) {
                    valor = "'" + (String) fila[1] + "'";
                } else {
                    Class<?> clase = fila[1].getClass();
                    valor = clase.cast(fila[1]).toString();
                }

                pos = res.indexOf("?");

                res = res.substring(0, pos) + valor + res.substring(pos + 1);
            }
        }

        return res;
    }

    public static String anadeSqlExtra(String sqlExtra, String sqlNueva) {
        if (sqlExtra != null && sqlExtra.length() > 0) {
            sqlExtra = sqlExtra.trim();

            sqlExtra += " " + sqlNueva;
        } else {
            sqlExtra = sqlNueva;
        }
        return sqlExtra;
    }

    public static String anadeTabla(String tabla, String tablaNueva) {
        if (tabla != null && tabla.length() > 0) {
            tabla = tabla.trim();

            tabla += ", " + tablaNueva;
        } else {
            tabla = tablaNueva;
        }
        return tabla;
    }

    public static String anadeSubcondicionClausula(String condicion, String subCondicion, String clausula) {
        if (condicion != null && condicion.length() > 0) {
            condicion = condicion.trim();

            condicion += " " + clausula + " (" + subCondicion + ")";
        } else {
            condicion = subCondicion;
        }
        return condicion;
    }

    public static String anadeSubcondicionAnd(String condicion, String subCondicion) {
        return anadeSubcondicionClausula(condicion, subCondicion, CLAUSULA_AND);
    }

    public static String anadeSubcondicionOr(String condicion, String subCondicion) {
        return anadeSubcondicionClausula(condicion, subCondicion, CLAUSULA_OR);
    }
    
    public static String anadeSubcondicion(String condicion, String subCondicion) {
        return anadeSubcondicionAnd(condicion, subCondicion);
    }
    
    public static String anadeCampoOrderBy(String orderBy, String campo) {
        if (orderBy != null && orderBy.length() > 0) {
            orderBy = orderBy.trim();

            orderBy += ", " + campo;
        } else {
            orderBy = "ORDER BY " + campo;
        }
        return orderBy;
    }
    
    public int creaTabla(String tabla, ArrayList<String[]> columnas) throws SQLException {
        Connection con = null;
        int res = 0;

        if (columnas != null && columnas.size() > 0) { //La tabla debe contener al menos una columna
            try {
                con = getConexion();

                String sql = "CREATE TABLE " + tabla + "(";

                int nCols = columnas.size();

                for (int i = 0; i < nCols; i++) {
                    String[] columna = columnas.get(i);
                    int nCol = columna.length;
                    
                    for (int j = 0; j < nCol; j++) {
                        sql += columna[j].trim() + " ";
                    }
                    
                    sql = sql.trim() + ", ";
                }
                
                sql = sql.substring(0, sql.length() - 2) + ")"; //Sustituimos el último ", " por ")"

                Statement st = con.createStatement();
                res = st.executeUpdate(sql);

                st.close();
                cierraConexionLocal(con);
            } catch (SQLException e) {
                if (con != null)
                    rollbackGlobal(con);
                throw e;
            } finally {
                if (con != null) {
                    cierraConexionLocal(con);
                }
            }
        }

        return res;
    }

    public int creaVista(String vista, String tabla, ArrayList<String> campos, String condicion, ArrayList<Object[]> paramsPS, String sqlExtra, Boolean distinct) throws SQLException {
        Connection con = null;
        int res = 0;

        if (campos != null && campos.size() > 0) { //La vista debe contener al menos una columna
            try {
                con = getConexion();
                
                //Borramos la vista si existe
                borraVista(vista);

                String sql = "CREATE VIEW " + vista + " AS (";
                sql += construyeSelect(tabla, campos, condicion, sqlExtra, distinct);
                sql += ")";

                //Falla con preparedStatement
                //PreparedStatement ps = con.prepareStatement(sql);
                ///cargaParamsPS(ps, paramsPS);
                ///res = ps.executeUpdate();

                //ps.close();
                
                Statement st = con.createStatement();
                
                if (paramsPS != null) {
                    Iterator i = paramsPS.iterator();
                    Object[] param;

                    while (i.hasNext()) {
                        param = (Object[]) i.next();
                        if (((String) param[0]).compareToIgnoreCase("String") == 0)
                            sql = sql.replaceFirst("\\?", "'" + param[1].toString() + "'");
                        else
                            sql = sql.replaceFirst("\\?", param[1].toString());
                    }
                }
                
                res = st.executeUpdate(sql);
                
                st.close();
                cierraConexionLocal(con);
            } catch (SQLException e) {
                if (con != null)
                    rollbackGlobal(con);
                throw e;
            } finally {
                if (con != null) {
                    cierraConexionLocal(con);
                }
            }
        }

        return res;
    }
    
    public int borraTabla(String tabla) throws SQLException {
        Connection con = null;
        int res = 0;

       try {
            con = getConexion();

            String sql = dameSqlExisteTabla(tabla) + "DROP TABLE " + tabla;
            Statement st = con.createStatement();
            res = st.executeUpdate(sql);

            st.close();
            cierraConexionLocal(con);
        } catch (SQLException e) {
            if (con != null)
                rollbackGlobal(con);
            throw e;
        } finally {
            if (con != null) {
                cierraConexionLocal(con);
            }
        }

        return res;
    }
    
    public int borraVista(String vista) throws SQLException {
        Connection con = null;
        int res = 0;

       try {
            con = getConexion();

            String sql = dameSqlExisteVista(vista) + "DROP VIEW " + vista;
            Statement st = con.createStatement();
            res = st.executeUpdate(sql);

            st.close();
            cierraConexionLocal(con);
        } catch (SQLException e) {
            if (con != null)
                rollbackGlobal(con);
            throw e;
        } finally {
            if (con != null) {
                cierraConexionLocal(con);
            }
        }

        return res;
    }
    
    //Función que ordena los paramsPS. Para cuando hay AutoInc, primero deben ir los parámetros de condiciones de AutoInc
    public static void anadeParamsPS(ArrayList<Object[]> paramsPS, ArrayList<Object[]> paramsPSAutoInc) {
        if (paramsPS == null)
            paramsPS = new ArrayList<Object[]>();
        
        if (paramsPSAutoInc != null && !paramsPSAutoInc.isEmpty()) {
            int nParamsPSAutoInc = paramsPSAutoInc.size();
            
            for (int i = 0; i < nParamsPSAutoInc; i++) {
                paramsPS.add(i, paramsPSAutoInc.get(i));
                
            }
        }   
    }
    
    public static String dameSqlExisteTabla(String tabla) {
        String res = null;
        
        String tablas[] = tabla.split(",");
        int nTablas = tablas.length;

        if (nTablas > 0) {
            res = "IF EXISTS ";
            
            for (int i = 0; i < nTablas; i++) {
                res += "(SELECT 1 FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME='" + tablas[i].trim() +"') AND EXISTS ";
            }
            
            res = res.substring(0, res.length() - " AND EXISTS ".length()) + "\n";
        }
        
        return res;
    }
    
    public static String dameElseSqlExisteTabla() {
        String res = null;
        
        res = "\nELSE SELECT 1 WHERE 1=0";   //Para que o dé error en una select por no devolver nada
        
        return res;
    }
    
    public static String dameSqlExisteVista(String vista) {
        String res = null;
        
        res = "IF EXISTS (SELECT 1 FROM INFORMATION_SCHEMA.VIEWS WHERE TABLE_NAME='" + vista +"')\n";
        
        return res;
    }

    public int creaXmlSchema(String nombreSchema, String xmlSchema) throws SQLException {
        Connection con = null;
        int res = 0;

        try {
            con = getConexion();

            //Si existe no hace falta que continuemos
            String sql = dameSqlExisteXmlSchema(nombreSchema, false);

            sql += "CREATE XML SCHEMA COLLECTION " + nombreSchema + " AS N'";
            sql += xmlSchema;
            sql += "'";

            Statement st = con.createStatement();
            res = st.executeUpdate(sql);
            st.close();
            cierraConexionLocal(con);
        } catch (SQLException e) {
            if (con != null)
                rollbackGlobal(con);
            throw e;
        } finally {
            if (con != null) {
                cierraConexionLocal(con);
            }
        }

        return res;
    }
    
    public int borraXmlSchema(String nombreSchema) throws SQLException {
        Connection con = null;
        int res = 0;

       try {
            con = getConexion();

            String sql = dameSqlExisteXmlSchema(nombreSchema, true) + "DROP XML SCHEMA COLLECTION " + nombreSchema;
            Statement st = con.createStatement();
            res = st.executeUpdate(sql);

            st.close();
            cierraConexionLocal(con);
        } catch (SQLException e) {
            if (con != null)
                rollbackGlobal(con);
            throw e;
        } finally {
            if (con != null) {
                cierraConexionLocal(con);
            }
        }

        return res;
    }
    
    public static String dameSqlExisteXmlSchema(String nombreSchema, boolean existe) {
        String res = null;
        res = "IF ";
        
        if (!existe)
            res += "NOT ";
        
        res += "EXISTS (SELECT 1 FROM SYS.XML_SCHEMA_COLLECTIONS WHERE NAME='" + nombreSchema +"')\n";
        
        return res;
    }
    
    public boolean existeTabla(String tabla) throws SQLException {
        boolean res = false;
        Connection con = null;
        
        try {
            con = getConexion();
        
            String sql = dameSqlExisteTabla(tabla) + "SELECT 1 ELSE SELECT 0";
        
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            rs.next();
            res = rs.getBoolean(1);
            
            rs.close();
            st.close();
        } catch (SQLException e) {
            if (con != null)
                rollbackGlobal(con);
            throw e;
        } finally {
            if (con != null) {
                cierraConexionLocal(con);
            }
        }

        return res;
    }
    
    public int creaIndicesXml(String tabla, String campoXml) throws SQLException {
        Connection con = null;
        int res = 0;

        try {
            con = getConexion();

            //Creamos el índice primario
            String nomIndex = "PIdx_" + tabla + "_" + campoXml;
            String sql = "CREATE PRIMARY XML INDEX " + nomIndex + "\n";
            sql += "ON " + tabla + "(" + campoXml + ")";

            Statement st = con.createStatement();
            res = st.executeUpdate(sql);
            
            //Creamos los índices secuendarios
            //PATH
            sql = "CREATE XML INDEX " + nomIndex + "_PATH\n";
            sql += "ON " + tabla + "(" + campoXml + ")\n";
            sql += "USING XML INDEX " + nomIndex + "\n";
            sql += "FOR PATH";

            st = con.createStatement();
            res = st.executeUpdate(sql);
            
            //VALUE
            sql = "CREATE XML INDEX " + nomIndex + "_VALUE\n";
            sql += "ON " + tabla + "(" + campoXml + ")\n";
            sql += "USING XML INDEX " + nomIndex + "\n";
            sql += "FOR VALUE";

            st = con.createStatement();
            res = st.executeUpdate(sql);
            
            //PROPERTY
            sql = "CREATE XML INDEX " + nomIndex + "_PROPERTY\n";
            sql += "ON " + tabla + "(" + campoXml + ")\n";
            sql += "USING XML INDEX " + nomIndex + "\n";
            sql += "FOR PROPERTY";

            st = con.createStatement();
            res = st.executeUpdate(sql);

            st.close();
            cierraConexionLocal(con);
        } catch (SQLException e) {
            if (con != null)
                rollbackGlobal(con);
            throw e;
        } finally {
            if (con != null) {
                cierraConexionLocal(con);
            }
        }

        return res;
    }
    
    public Object getIdentidad(String tabla) throws SQLException {
        Object res = null;
        ArrayList<String> campos = new ArrayList<String>();
        
        campos.add("IDENT_CURRENT(\'" + tabla + "\')");
        
        ArrayList<Object[]> resAux = getDatosTabla(null, campos, null, null, null, null);
        
        if (resAux != null && resAux.size() > 0) {
            res = resAux.get(0)[0];
        }
        
        return res;
    }
}