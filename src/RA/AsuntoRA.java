package RA;

import general.InteraccionBD;
import general.InteraccionFic;
import general.TratCadenas;
import general.TratFechas;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
//////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////EQUIVALENCIAS///////////////////////////////////////////////
///////AsuntoRA//////////////////////////////////////////////Asunto///////////////////////////////
//insertasunto                                          |   NuevoAsunto
//AsuntoRA.getAsuntos(<cond>) + asunto.getIdAsunto()    |   getIdAsunto(String nombreCompleto)
//getSites                                              |   -->Pasa a ConfiguracionRA
//updateAsunto                                          |   ModificarAsunto
//getAsuntos filtrado un asunto y tienes el objeto      |   getAsunto 
//esCodigoValido                                        |   isCodigo
//ordenarAsuntos(ArrayList<Asunto>)                     |   ordenar(ArryList<String>)
//asunto1.getCodigo().compareTo(asunto2.getCodigo())    |   esMayor / esMenor
// Contactos --> getContactosExternos                   |   getContactosExternos
public class AsuntoRA {

    private Integer idAsunto;
    private String codigo;
    private String nombre;
    private Integer pE;
    private Integer idNorma;
    private Integer periodicidad;
    private Boolean clave;
    private Integer idCliente;
    private Integer idResponsable;
    private Integer idAero;
    private Boolean abierto;
    private String posicion;
    private String nCorto;
    private Character idioma;
    private Long finiPC;
    private Long finiSC;
    private Long finiME1;
    private Long finiME10;
    private Integer tipo;  
    
    //BD
    public static final String BD = InteraccionBD.PREF_BD_GENERAL;
    public static final String TABLA = BD + "Asunto";
    public static final String CAMPO_ID_ASUNTO = "Id_asunto";
    public static final String CAMPO_CODIGO = "Codigo";
    public static final String CAMPO_NOMBRE = "Nombre";
    public static final String CAMPO_PE = "PE";
    public static final String CAMPO_ID_NORMA = "Norma";
    public static final String CAMPO_PERIOD = "Periodicidad";
    public static final String CAMPO_CLAVE = "Clave";
    public static final String CAMPO_ID_CLIENTE = "Cliente";
    public static final String CAMPO_ID_RESP = "Responsable";
    public static final String CAMPO_ID_AERO = "Aerogenerador";
    public static final String CAMPO_ABIERTO = "Abierto";
    public static final String CAMPO_POS = "Posicion";
    public static final String CAMPO_N_CORTO = "Ncorto";
    public static final String CAMPO_IDIOMA = "Idioma";
    public static final String CAMPO_F_INI_PC = "FiniPC";
    public static final String CAMPO_F_INI_SC = "FiniSC";
    public static final String CAMPO_F_INI_ME_1 = "FiniME1";
    public static final String CAMPO_F_INI_ME_10 = "FiniME10";
    public static final String CAMPO_TIPO = "Tipo";    
  
    
    //Otras constantes
    private static final String RUTA_ASUNTOS = "\\\\B2solar\\Datos\\Curva\\Asuntos\\";
    private static final String SUB_INSERCION = "\\Insercion";
    private static final String SUB_EDICION = "\\Edicion";
    private static final String SUB_REVISION = "\\Revision";
    private static final String SUB_INFORMES = "\\Informes";
    private static final String SUB_CORRECION = "\\Correccion";
    
    public static final String FORMATO_FECHA = "ddMMyyyy HHmm";
    public static final Integer TIPO_ASUNTO_RA = 3;

    public AsuntoRA(Integer idAsunto, String codigo, String nombre, Integer pE, Integer idNorma, Integer periodicidad, Boolean clave,
            Integer idCliente, Integer idResponsable, Integer idAero, Boolean abierto, String posicion, String nCorto, Character idioma,
            Long finiPC, Long finiSC, Long finiME1, Long finiME10, Integer tipo) {
        this.idAsunto = idAsunto;
        this.codigo = codigo;
        this.nombre = nombre;
        this.pE = pE;
        this.idNorma = idNorma;
        this.periodicidad = periodicidad;
        this.clave = clave;
        this.idCliente = idCliente;
        this.idResponsable = idResponsable;
        this.idAero = idAero;
        this.abierto = abierto;
        this.posicion = posicion;
        this.nCorto = nCorto;
        this.idioma = idioma;
        this.finiPC = finiPC;
        this.finiSC = finiSC;
        this.finiME1 = finiME1;
        this.finiME10 = finiME10;
        this.tipo = tipo;
    }

    public AsuntoRA(Object[] valores, ArrayList<String> campos) throws SQLException, NoSuchFieldException {
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

    public Boolean getAbierto() {
        return abierto;
    }

    public void setAbierto(Boolean abierto) {
        this.abierto = abierto;
    }

    public Boolean getClave() {
        return clave;
    }

    public void setClave(Boolean clave) {
        this.clave = clave;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Long getFiniME1() {
        return finiME1;
    }

    public void setFiniME1(Long finiME1) {
        this.finiME1 = finiME1;
    }

    public Long getFiniME10() {
        return finiME10;
    }

    public void setFiniME10(Long finiME10) {
        this.finiME10 = finiME10;
    }

    public Long getFiniPC() {
        return finiPC;
    }

    public void setFiniPC(Long finiPC) {
        this.finiPC = finiPC;
    }

    public Long getFiniSC() {
        return finiSC;
    }

    public void setFiniSC(Long finiSC) {
        this.finiSC = finiSC;
    }

    public Integer getIdAero() {
        return idAero;
    }

    public void setIdAero(Integer idAero) {
        this.idAero = idAero;
    }

    public Integer getIdAsunto() {
        return idAsunto;
    }

    public void setIdAsunto(Integer idAsunto) {
        this.idAsunto = idAsunto;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getIdNorma() {
        return idNorma;
    }

    public void setIdNorma(Integer idNorma) {
        this.idNorma = idNorma;
    }

    public Integer getIdResponsable() {
        return idResponsable;
    }

    public void setIdResponsable(Integer idResponsable) {
        this.idResponsable = idResponsable;
    }

    public Character getIdioma() {
        return idioma;
    }

    public void setIdioma(Character idioma) {
        this.idioma = idioma;
    }

    public String getNCorto() {
        return nCorto;
    }

    public void setNCorto(String nCorto) {
        this.nCorto = nCorto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getPE() {
        return pE;
    }

    public void setPE(Integer pE) {
        this.pE = pE;
    }

    public Integer getPeriodicidad() {
        return periodicidad;
    }

    public void setPeriodicidad(Integer periodicidad) {
        this.periodicidad = periodicidad;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }
    //Función para asignar valores a campos de la clase según el campo de la BD
    private void asignaCampo(String campo, Object valor) throws NoSuchFieldException {
        if (campo.compareTo(CAMPO_ID_ASUNTO) == 0) {
            this.idAsunto = (Integer) valor;
        } else if (campo.compareTo(CAMPO_CODIGO) == 0) {
            this.codigo = (String) valor;
        } else if (campo.compareTo(CAMPO_NOMBRE) == 0) {
            this.nombre = (String) valor;
        } else if (campo.compareTo(CAMPO_PE) == 0) {
            this.pE = (Integer) valor;
        } else if (campo.compareTo(CAMPO_ID_NORMA) == 0) {
            this.idNorma = (Integer) valor;
        } else if (campo.compareTo(CAMPO_PERIOD) == 0) {
            this.periodicidad = (Integer) valor;
        } else if (campo.compareTo(CAMPO_CLAVE) == 0) {
            this.clave = (Boolean) valor;
        } else if (campo.compareTo(CAMPO_ID_CLIENTE) == 0) {
            this.idCliente = (Integer) valor;
        } else if (campo.compareTo(CAMPO_ID_RESP) == 0) {
            this.idResponsable = (Integer) valor;
        } else if (campo.compareTo(CAMPO_ID_AERO) == 0) {
            this.idAero = (Integer) valor;
        } else if (campo.compareTo(CAMPO_ABIERTO) == 0) {
            this.abierto = (Boolean) valor;
        } else if (campo.compareTo(CAMPO_POS) == 0) {
            this.posicion = (String) valor;
        } else if (campo.compareTo(CAMPO_N_CORTO) == 0) {
            this.nCorto = (String) valor;
        } else if (campo.compareTo(CAMPO_IDIOMA) == 0) {
            if (valor.getClass().getSimpleName().compareTo("String") == 0) {
                if (((String) valor).length() > 0) {
                    this.idioma = ((String) valor).charAt(0);
                }
            } else {
                this.idioma = (Character) valor;
            }
        } else if (campo.compareTo(CAMPO_F_INI_PC) == 0) {
            this.finiPC = TratFechas.millisFechaGen((String) valor, FORMATO_FECHA);
        } else if (campo.compareTo(CAMPO_F_INI_SC) == 0) {
            this.finiSC = TratFechas.millisFechaGen((String) valor, FORMATO_FECHA);
        } else if (campo.compareTo(CAMPO_F_INI_ME_1) == 0) {
            this.finiME1 = TratFechas.millisFechaGen((String) valor, FORMATO_FECHA);
        } else if (campo.compareTo(CAMPO_F_INI_ME_10) == 0) {
            this.finiME10 = TratFechas.millisFechaGen((String) valor, FORMATO_FECHA);
        } else if (campo.compareTo(CAMPO_TIPO) == 0) {
            this.tipo = (Integer) valor;
        } else {
            throw new NoSuchFieldException("No existe el campo en la clase " + this.getClass().getSimpleName());
        }
    }

    @Override
    public String toString() {
        String res = "";

        res += CAMPO_ID_ASUNTO + ": " + this.idAsunto + InteraccionFic.SALTO_LINEA;
        res += CAMPO_CODIGO + ": " + this.codigo + InteraccionFic.SALTO_LINEA;
        res += CAMPO_NOMBRE + ": " + this.nombre + InteraccionFic.SALTO_LINEA;
        res += CAMPO_PE + ": " + this.pE + InteraccionFic.SALTO_LINEA;
        res += CAMPO_ID_NORMA + ": " + this.idNorma + InteraccionFic.SALTO_LINEA;
        res += CAMPO_PERIOD + ": " + this.periodicidad + InteraccionFic.SALTO_LINEA;
        res += CAMPO_CLAVE + ": " + this.clave + InteraccionFic.SALTO_LINEA;
        res += CAMPO_ID_CLIENTE + ": " + this.idCliente + InteraccionFic.SALTO_LINEA;
        res += CAMPO_ID_RESP + ": " + this.idResponsable + InteraccionFic.SALTO_LINEA;
        res += CAMPO_ID_AERO + ": " + this.idAero + InteraccionFic.SALTO_LINEA;
        res += CAMPO_ABIERTO + ": " + this.abierto + InteraccionFic.SALTO_LINEA;
        res += CAMPO_POS + ": " + this.posicion + InteraccionFic.SALTO_LINEA;
        res += CAMPO_N_CORTO + ": " + this.nCorto + InteraccionFic.SALTO_LINEA;
        res += CAMPO_IDIOMA + ": " + this.idioma + InteraccionFic.SALTO_LINEA;
        res += CAMPO_F_INI_PC + ": " + TratFechas.toStringFechaGen(this.finiPC, FORMATO_FECHA) + InteraccionFic.SALTO_LINEA;
        res += CAMPO_F_INI_SC + ": " + TratFechas.toStringFechaGen(this.finiSC, FORMATO_FECHA) + InteraccionFic.SALTO_LINEA;
        res += CAMPO_F_INI_ME_1 + ": " + TratFechas.toStringFechaGen(this.finiME1, FORMATO_FECHA) + InteraccionFic.SALTO_LINEA;
        res += CAMPO_F_INI_ME_10 + ": " + TratFechas.toStringFechaGen(this.finiME10, FORMATO_FECHA) + InteraccionFic.SALTO_LINEA;
        res += CAMPO_TIPO + ": " + this.tipo + InteraccionFic.SALTO_LINEA;

        return res;
    }
    
    private static String getCondicion(Integer idAsunto, String codigo, String nombre, Integer pE, Integer idNorma, Integer periodicidad, Boolean clave, Integer idCliente, Integer idResponsable, Integer idAero, Boolean abierto, String posicion, String nCorto, Character idioma, Long finiPC, Long finiSC, Long finiME1, Long finiME10, Integer tipo, ArrayList<Object[]> paramsPS) {
        String condicion = "";
        
        if (idAsunto != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_ID_ASUNTO, "=", idAsunto);
        }
        if (codigo != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_CODIGO, "=", codigo);
        }
        if (nombre != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_NOMBRE, "=", nombre);
        }
        if (pE != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_PE, "=", pE);
        }
        if (idNorma != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_ID_NORMA, "=", idNorma);
        }
        if (periodicidad != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_PERIOD, "=", periodicidad);
        }
        if (clave != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_CLAVE, "=", clave);
        }
        if (idCliente != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_ID_CLIENTE, "=", idCliente);
        }
        if (idResponsable != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_ID_RESP, "=", idResponsable);
        }
        if (idAero != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_ID_AERO, "=", idAero);
        }
        if (abierto != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_ABIERTO, "=", abierto);
        }
        if (posicion != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_POS, "=", posicion);
        }
        if (nCorto != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_N_CORTO, "=", nCorto);
        }
        if (idioma != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_IDIOMA, "=", idioma);
        }
        if (finiPC != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_F_INI_PC, "=", TratFechas.toStringFechaGen(finiPC, FORMATO_FECHA));
        }
        if (finiSC != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_F_INI_SC, "=", TratFechas.toStringFechaGen(finiSC, FORMATO_FECHA));
        }
        if (finiME1 != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_F_INI_ME_1, "=", TratFechas.toStringFechaGen(finiME1, FORMATO_FECHA));
        }
        if (finiME10 != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_F_INI_ME_10, "=", TratFechas.toStringFechaGen(finiME10, FORMATO_FECHA));
        }
        if (tipo != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_TIPO, "=", tipo);
        }
        
        return condicion;
    }
    
    //Función para obtener la colección de Asuntos que se ajustan a los limites pasados
    public static ArrayList<AsuntoRA> getAsuntos(Integer idAsunto, String codigo, String nombre, Integer pE, Integer idNorma, Integer periodicidad, Boolean clave, Integer idCliente, Integer idResponsable, Integer idAero, Boolean abierto, String posicion, String nCorto, Character idioma, Long finiPC, Long finiSC, Long finiME1, Long finiME10, Integer tipo,  ArrayList<String> campos, String sqlExtra, Boolean distinct) throws SQLException, NoSuchFieldException {
        InteraccionBD interBD = new InteraccionBD();

        ArrayList<AsuntoRA> res = null;
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();

        condicion = getCondicion(idAsunto, codigo, nombre, pE, idNorma, periodicidad, clave, idCliente, idResponsable, idAero, abierto, posicion, nCorto, idioma, finiPC, finiSC, finiME1, finiME10, tipo, paramsPS);
        
        //Por defecto lo devolvemos ordenado por idAsunto
        if (sqlExtra == null || sqlExtra.trim().length() == 0) {
            String sqlOrderBy = InteraccionBD.anadeCampoOrderBy(null, CAMPO_ID_ASUNTO);
            sqlExtra = InteraccionBD.anadeSqlExtra(null, sqlOrderBy);
        }

        ArrayList<Object[]> resAux = interBD.getDatosTabla(TABLA, campos, condicion, paramsPS, sqlExtra, distinct);

        if (resAux != null) {
            int n = resAux.size();
            res = new ArrayList<AsuntoRA>();

            for (int i = 0; i < n; i++) {
                res.add(new AsuntoRA(resAux.get(i), campos));
            }
        }

        return res;
    }
    
    //Función para obtener la colección de Asunos que se ajustan a los limites pasados
    public static ArrayList<AsuntoRA> getAsuntosPorId(Integer desdeIdAsunto, Integer hastaIdAsunto, ArrayList<String> campos, String sqlExtra, Boolean distinct) throws SQLException, NoSuchFieldException {
        InteraccionBD interBD = new InteraccionBD();

        ArrayList<AsuntoRA> res = null;
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();

        if (desdeIdAsunto != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_ID_ASUNTO, ">=", desdeIdAsunto);
        }
        if (hastaIdAsunto != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_ID_ASUNTO, "<=", hastaIdAsunto);
        }

        ArrayList<Object[]> resAux = interBD.getDatosTabla(TABLA, campos, condicion, paramsPS, sqlExtra, distinct);

        if (resAux != null) {
            int n = resAux.size();
            res = new ArrayList<AsuntoRA>();

            for (int i = 0; i < n; i++) {
                res.add(new AsuntoRA(resAux.get(i), campos));
            }
        }

        return res;
    }
    
    //Función para obtener el Asunto correspondiente a un Id determinado
    public static AsuntoRA getAsuntoPorId(Integer idAsunto) throws SQLException, NoSuchFieldException {
        AsuntoRA res = null;

        ArrayList<AsuntoRA> resAux = getAsuntosPorId(idAsunto, idAsunto, null, null, null);
        
        if (resAux != null) {
            res = resAux.get(0);
        }

        return res;
    }
    
    // Inserta un nuevo asunto en la bd 
    // Crea el directorio en el servidor. 
    // Inserta una linea en interpolacion (si tipo=0,1)
    // Inserta en la tabla contactos (si jesus no es contatco de cliente, se inserta tb como contacto)
    // Devuelve true si el codigo del asunto ya existe
    public boolean insertAsunto(String codigo, String nombre, Integer pe, Integer norma, Integer periodicidad, Integer cliente, Integer responsable, Integer aero, String nPos, ArrayList<String> contactoAsunto, String nCorto, String idioma, Integer tipo, String sqlExtra) throws SQLException, NoSuchFieldException, IOException, ClassNotFoundException {

        InteraccionBD interBD = new InteraccionBD();
        boolean res = false;
        File folder = null;

        try {
            Aerogenerador AG = new Aerogenerador();

            // Se comprueba si existe y el último insertado
            ArrayList<String> campos = new ArrayList<String>();
            ArrayList<String> autoInc = new ArrayList<String>();
            ArrayList<String> condAutoInc = new ArrayList<String>();
            Integer idAsuntoNuevo;

            campos.add(CAMPO_ID_ASUNTO);
            campos.add(CAMPO_CODIGO);

            String sqlOrderBy = InteraccionBD.anadeCampoOrderBy(null, CAMPO_ID_ASUNTO);
            sqlExtra = InteraccionBD.anadeSqlExtra(sqlExtra, sqlOrderBy);

            //Iniciamos la transacción
            interBD.inicioTransaccion();

            ArrayList<AsuntoRA> asuntos = getAsuntosPorId(null, null, campos, sqlExtra, false);

            //Si existe en la BD un asunto con ese código, no podemos insertar
            int nAsuntos = asuntos.size();
            for (int i = 0; i < nAsuntos; i++) {
                if (asuntos.get(i).getCodigo().compareToIgnoreCase(codigo) == 0) {
                    return res;
                }
            }

            //No existe en la BD, procedemos a su inserción
            String valores = "";
            ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
            campos.clear();

            //El nuevo Id será el del último más uno (están ordenados en orden descendente)
            if (nAsuntos > 0) {
                idAsuntoNuevo = asuntos.get(0).getIdAsunto() + 1;
            } else {
                idAsuntoNuevo = 0;
            }
//            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, idAsunto);
//            campos.add(CAMPO_ID_ASUNTO);
            
            valores = InteraccionBD.anadeCampoValorAutoInc(valores, CAMPO_ID_ASUNTO);
            campos.add(CAMPO_ID_ASUNTO);
            autoInc.add(CAMPO_ID_ASUNTO);
            condAutoInc.add(null);
            if (codigo != null) {
                valores = InteraccionBD.anadeCampoValor(valores, paramsPS, codigo);
                campos.add(CAMPO_CODIGO);
            }
            if (nombre != null) {
                valores = InteraccionBD.anadeCampoValor(valores, paramsPS, nombre);
                campos.add(CAMPO_NOMBRE);
            }
            if (pe != null) {
                valores = InteraccionBD.anadeCampoValor(valores, paramsPS, pe);
                campos.add(CAMPO_PE);
            }
            if (norma != null) {
                valores = InteraccionBD.anadeCampoValor(valores, paramsPS, norma);
                campos.add(CAMPO_ID_NORMA);
            }
            if (periodicidad != null) {
                valores = InteraccionBD.anadeCampoValor(valores, paramsPS, periodicidad);
                campos.add(CAMPO_PERIOD);
            }
            //Clave se inserta siempre como false
            Boolean claveVal = false;
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, claveVal);
            campos.add(CAMPO_CLAVE);

            if (cliente != null) {
                valores = InteraccionBD.anadeCampoValor(valores, paramsPS, cliente);
                campos.add(CAMPO_ID_CLIENTE);
            }
            if (responsable != null) {
                valores = InteraccionBD.anadeCampoValor(valores, paramsPS, responsable);
                campos.add(CAMPO_ID_RESP);
            }
            if (aero != null) {
                valores = InteraccionBD.anadeCampoValor(valores, paramsPS, aero);
                campos.add(CAMPO_ID_AERO);
            }
            //Abierto se inserta siempre como true
            Boolean abiertoVal = true;
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, abiertoVal);
            campos.add(CAMPO_ABIERTO);

            if (nPos != null) {
                valores = InteraccionBD.anadeCampoValor(valores, paramsPS, nPos);
                campos.add(CAMPO_POS);
            }
            if (this.nCorto != null) {
                valores = InteraccionBD.anadeCampoValor(valores, paramsPS, this.nCorto);
                campos.add(CAMPO_N_CORTO);
            }
            if (idioma != null) {
                valores = InteraccionBD.anadeCampoValor(valores, paramsPS, idioma);
                campos.add(CAMPO_IDIOMA);
            }
            if (tipo != null) {
                valores = InteraccionBD.anadeCampoValor(valores, paramsPS, tipo);
                campos.add(CAMPO_TIPO);
            }

            int nFilas = interBD.insertDatosTabla(TABLA, campos, valores, paramsPS, null, autoInc, condAutoInc);

            // Se inserta la linea de interpolacion
            Double vCut = AG.getVcut(aero);
            Double pn = AG.getPn(aero);
            if ((tipo == 0) || (tipo == 1)) {
                Interpolacion.insertInterpolacion(idAsuntoNuevo, pn, vCut, null);
            }
            
            // Se insertan los contactos
            AsuntoContacto.insertAsuntoContaArray(idAsuntoNuevo, contactoAsunto);

            interBD.finTransaccion();

            // Se crea el directorio asociado
            String rutaAsunto = RUTA_ASUNTOS + codigo + "." + nombre;
            InteraccionFic.creaDir(rutaAsunto);
            InteraccionFic.creaDir(rutaAsunto + SUB_INSERCION);
            InteraccionFic.creaDir(rutaAsunto + SUB_EDICION);
            InteraccionFic.creaDir(rutaAsunto + SUB_REVISION);
            InteraccionFic.creaDir(rutaAsunto + SUB_INFORMES);
            InteraccionFic.creaDir(rutaAsunto + SUB_CORRECION);
        } catch (SQLException e) {
            if (folder != null) {
                folder.delete();
            }

            throw e;
        }

        return res;
    }
    //Función para añadir una incidencia a la BD
    public static int updateAsunto(Integer idAsunto, String codigo, String nombre, Integer pE, Integer idNorma, Integer periodicidad, Boolean clave, Integer idCliente, Integer idResponsable, Integer idAero, Boolean abierto, String posicion, String nCorto, Character idioma, Long finiPC, Long finiSC, Long finiME1, Long finiME10, Integer tipo, Integer idAsuntoVal, String codigoVal, String nombreVal, Integer pEVal, Integer idNormaVal, Integer periodicidadVal, Boolean claveVal, Integer idClienteVal, Integer idResponsableVal, Integer idAeroVal, Boolean abiertoVal, String posicionVal, String nCortoVal, Character idiomaVal, Long finiPCVal, Long finiSCVal, Long finiME1Val, Long finiME10Val, Integer tipoVal, ArrayList<String> contactoAsuntoVal, String sqlExtra) throws SQLException, IOException, ClassNotFoundException, NoSuchFieldException {
        InteraccionBD interBD = new InteraccionBD();

        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        ArrayList<String> campos = new ArrayList<String>();
        int res = 0;

        if (idAsuntoVal != null && !idAsuntoVal.equals(idAsunto)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, idAsuntoVal);
            campos.add(CAMPO_ID_ASUNTO);
        }
        if (codigoVal != null && !codigoVal.equals(codigo)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, codigoVal);
            campos.add(CAMPO_CODIGO);
        }
        if (nombreVal != null && !nombreVal.equals(nombre)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, nombreVal);
            campos.add(CAMPO_NOMBRE);
        }
        if (pEVal != null && !pEVal.equals(pE)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, pEVal);
            campos.add(CAMPO_PE);
        }
        if (idNormaVal != null && !idNormaVal.equals(idNorma)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, idNormaVal);
            campos.add(CAMPO_ID_NORMA);
        }
        if (periodicidadVal != null && !periodicidadVal.equals(periodicidad)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, periodicidadVal);
            campos.add(CAMPO_PERIOD);
        }
        if (claveVal != null && !claveVal.equals(clave)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, claveVal);
            campos.add(CAMPO_CLAVE);
        }
        if (idClienteVal != null && !idClienteVal.equals(idCliente)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, idClienteVal);
            campos.add(CAMPO_ID_CLIENTE);
        }
        if (idResponsableVal != null && !idResponsableVal.equals(idResponsable)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, idResponsableVal);
            campos.add(CAMPO_ID_RESP);
        }
        if (idAeroVal != null && !idAeroVal.equals(idAero)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, idAeroVal);
            campos.add(CAMPO_ID_AERO);
        }
        if (abiertoVal != null && !abiertoVal.equals(abierto)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, abiertoVal);
            campos.add(CAMPO_ABIERTO);
        }
        if (posicionVal != null && !posicionVal.equals(posicion)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, posicionVal);
            campos.add(CAMPO_POS);
        }
        if (nCortoVal != null && !nCortoVal.equals(nCorto)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, nCortoVal);
            campos.add(CAMPO_N_CORTO);
        }
        if (idiomaVal != null && !idiomaVal.equals(idioma)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, idiomaVal);
            campos.add(CAMPO_IDIOMA);
        }
        if (finiPCVal != null && !finiPCVal.equals(finiPC)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, TratFechas.toStringFechaGen(finiPCVal, FORMATO_FECHA));
            campos.add(CAMPO_F_INI_PC);
        }
        if (finiSCVal != null && !finiSCVal.equals(finiSC)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, TratFechas.toStringFechaGen(finiSCVal, FORMATO_FECHA));
            campos.add(CAMPO_F_INI_SC);
        }
        if (finiME1Val != null && !finiME1Val.equals(finiME1)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, TratFechas.toStringFechaGen(finiME1Val, FORMATO_FECHA));
            campos.add(CAMPO_F_INI_ME_1);
        }
        if (finiME10Val != null && !finiME10Val.equals(finiME10)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, TratFechas.toStringFechaGen(finiME10Val, FORMATO_FECHA));
            campos.add(CAMPO_F_INI_ME_10);
        }
        if (tipoVal != null && !tipoVal.equals(tipo)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, tipoVal);
            campos.add(CAMPO_TIPO);
        }

        //Condiciones de actualización
        condicion = getCondicion(idAsunto, codigo, nombre, pE, idNorma, periodicidad, clave, idCliente, idResponsable, idAero, abierto, posicion, nCorto, idioma, finiPC, finiSC, finiME1, finiME10, tipo, paramsPS);

        String rutaUpdate = "\\update-" + idAsunto + ".log";
        String rutaAsunto = RUTA_ASUNTOS + getNombreCompleto(codigo, nombre);
        String rutaAsuntoVal = RUTA_ASUNTOS + getNombreCompleto(codigoVal, nombreVal);
        String rutaFic = rutaAsunto + SUB_EDICION + rutaUpdate;
        InteraccionFic interFic = new InteraccionFic(rutaFic, InteraccionFic.WRITE);

        interFic.escribeLineaFic(InteraccionFic.LINEA_SEP);
        interFic.escribeLineaFic("");

        SimpleDateFormat formatoFechaHora = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        interFic.escribeLineaFic("Proceso de Modificación (Asunto) a: " + formatoFechaHora.format(new Date()));
        interFic.escribeLineaFic("Estado previo:");

        AsuntoRA asunto = new AsuntoRA(idAsunto, codigo, nombre, pE, idNorma, periodicidad, clave, idCliente, idResponsable, idAero, abierto, posicion, nCorto, idioma, finiPC, finiSC, finiME1, finiME10, tipo);
        interFic.escribeLineaFic(asunto.toString());

        interBD.inicioTransaccion();
        //Actualizamos la tabla Asunto
        res = interBD.updateDatosTabla(TABLA, campos, condicion, paramsPS, sqlExtra);

        //Reflejamos la operación de actualización en el fichero
        interFic.escribeLineaFic("Instrucción:");
        interFic.escribeLineaFic(InteraccionBD.dameSentenciaSql(InteraccionBD.TIPO_UPDATE, TABLA, campos, condicion, paramsPS, sqlExtra, false, null, null));

        // Se borran los contactos anteriores del asunto (dejando registro en el fichero)
        AsuntoContacto.deleteAsuntoContaFic(null, idAsunto, null, null, null, interFic);

        // Se insertan los contactos (dejando registro en el fichero)
        AsuntoContacto.insertAsuntoContaArrayFic(idAsuntoVal, contactoAsuntoVal, interFic);

        interFic.escribeLineaFic("Fin de modificación");
        interFic.finOpFichero();

        //Renombramos el directorio si es necesario
        if (rutaAsuntoVal.compareTo(rutaAsunto) != 0) {
            InteraccionFic.renombraDir(rutaAsunto, rutaAsuntoVal);
        }

        interBD.finTransaccion();

        return res;
    }

    // Devuelve el Asunto dado su nombre completo (codigo.nombre). Si no existe devuelve null
    public static AsuntoRA getAsuntoPorNombreCompleto(String nombreCompleto) throws SQLException, NoSuchFieldException {
        ArrayList<String> campos = new ArrayList<String>();
        ArrayList<AsuntoRA> resAux = null;
        AsuntoRA res = null;

        //Solo nos interesa el campo de idContacto
        campos.add(CAMPO_ID_ASUNTO);

        String codigo = nombreCompleto.substring(0, nombreCompleto.indexOf("."));

        resAux = AsuntoRA.getAsuntos(null, codigo, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, campos, null, false);

        if (resAux != null && resAux.size() > 0) {
            res = resAux.get(0);
        }

        return res;
    }
    // Devuelve el nombre completo de un asunto (Codigo.Nombre)
    public String getNombreCompleto() {
        return getNombreCompleto(this.getCodigo(), this.getNombre());
    }
    // Devuelve el nombre completo de un asunto (Codigo.Nombre)
    public static String getNombreCompleto(String codigo, String nombre) {
        return codigo + "." + nombre;
    }
    //Obtiene el último Código introducido
    public static String getNuevoCodigo() throws SQLException {
        InteraccionBD interBD = new InteraccionBD();

        ArrayList<String> campos = new ArrayList<String>();
        GregorianCalendar cal = new GregorianCalendar();
        Integer ultNumCodigo = -1;  //Si no existe ningún código
        final Integer maxNumCodigo = 999;
        String res = null;

        //Solo nos interesa del campo de codigo [yyyy-nnn] el máximo de nnn
        //SELECT MAX(SUBSTRING(Codigo, 6, 3))
        //FROM ASUNTO
        //WHERE SUBSTRING(Codigo, 1, 4) = <año_actual>
        String campoSqlCompleja = "MAX(SUBSTRING(" + CAMPO_CODIGO + ", 6, 3))";
        campos.add(campoSqlCompleja);

        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        String condicion = null;

        String campoCondComplejo = "SUBSTRING(" + CAMPO_CODIGO + ", 1, 4)";

        condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, campoCondComplejo, "=", cal.get(GregorianCalendar.YEAR));

        ArrayList<Object[]> resAux = interBD.getDatosTabla(TABLA, campos, condicion, paramsPS, null, false);

        if (resAux != null && resAux.size() > 0) {
            ultNumCodigo = Integer.parseInt((String) resAux.get(0)[0]);
        }

        if (ultNumCodigo < maxNumCodigo) {
            res = String.format("%04d-%03d", cal.get(GregorianCalendar.YEAR), ++ultNumCodigo);
        }
        return res;
    }
    //Función que devuelve los asuntos, ordenados por código, según filtro de tipo, responsable y activo
    //idResponsable=0 || null --> Todos | idResponsable!=0 --> Responsable
    public static ArrayList<AsuntoRA> getAsuntos(Integer idResponsable, Boolean[] filtroTipo, Boolean abierto) throws SQLException, NoSuchFieldException {
        InteraccionBD interBD = new InteraccionBD();

        ArrayList<AsuntoRA> res = null;
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();

        if (idResponsable != null && idResponsable != 0) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_ID_RESP, "=", idResponsable);
        }
        if (abierto != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_ABIERTO, "=", abierto);
        }
        if (filtroTipo != null) {
            int nTipos = filtroTipo.length;
            String subcondicion = "";

            for (int i = nTipos-1; i >= 0; i--) {
                if (filtroTipo[i])
                    subcondicion = InteraccionBD.anadeCampoCondicionOr(subcondicion, paramsPS, CAMPO_TIPO, "=", nTipos-i-1);
            }
            
            condicion = InteraccionBD.anadeSubcondicion(condicion, subcondicion);
        }

        String sqlOrderBy = InteraccionBD.anadeCampoOrderBy(null, CAMPO_CODIGO);
        String sqlExtra = InteraccionBD.anadeSqlExtra(null, sqlOrderBy);

        ArrayList<Object[]> resAux = interBD.getDatosTabla(TABLA, null, condicion, paramsPS, sqlExtra, false);

        if (resAux != null) {
            int n = resAux.size();
            res = new ArrayList<AsuntoRA>();

            for (int i = 0; i < n; i++) {
                res.add(new AsuntoRA(resAux.get(i), null));
            }
        }

        return res;
    }

    public static boolean esCodigoValido(String codigo) {
        return (codigo.length() != 8 ||
                codigo.charAt(4) != '-' ||
                !TratCadenas.esAnoValido(codigo.substring(0, 4)) ||
                !TratCadenas.esEnteroValido(codigo.substring(5)));
    }

    public static boolean existeCodigo(String codigo) throws SQLException, NoSuchFieldException {
        ArrayList<AsuntoRA> resAux = getAsuntos(null, codigo, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);

        return (resAux != null && resAux.size() > 0);
    }

    private static class ComparatorAsuntoRA implements Comparator<AsuntoRA> {

        public ComparatorAsuntoRA() {
        }

        public int compare(AsuntoRA asunto1, AsuntoRA asunto2) {
            return (asunto1.getCodigo().compareTo(asunto2.getCodigo()));
        }
    }

    public static void ordenarAsuntos(ArrayList<AsuntoRA> asuntos) {
        Collections.sort(asuntos, new ComparatorAsuntoRA());
    }
    
    //Función para eliminar Asuntos que se ajustan a los limites pasados
    public static int deleteAsuntos(Integer idAsunto, String codigo, String nombre, Integer pE, Integer idNorma, Integer periodicidad, Boolean clave, Integer idCliente, Integer idResponsable, Integer idAero, Boolean abierto, String posicion, String nCorto, Character idioma, Long finiPC, Long finiSC, Long finiME1, Long finiME10, Integer tipo, String sqlExtra) throws SQLException, ClassNotFoundException, IOException {
        InteraccionBD interBD = new InteraccionBD();
        
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        int res = 0;

        condicion = getCondicion(idAsunto, codigo, nombre, pE, idNorma, periodicidad, clave, idCliente, idResponsable, idAero, abierto, posicion, nCorto, idioma, finiPC, finiSC, finiME1, finiME10, tipo, paramsPS);
        
        interBD.inicioTransaccion();
        Interpolacion.deleteInterpolaciones(idAsunto, null, null, null);
        AsuntoContacto.deleteAsuntoConta(null, idAsunto, null, null, null);
        AsuntoPosicionRA.deletePosicionesRA(idAsunto, null, null, null, null, null, null);
        AsuntoConfRA.deleteAsuntoConfs(idAsunto, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
        AsuntoConfRAModos.deleteAsuntoConfs(idAsunto, null, null, null);
        AsuntoIncert.deleteAsuntoIncerts(idAsunto, null, null, null, null, null);
        
        res  = interBD.deleteDatosTabla(TABLA, condicion, paramsPS, sqlExtra);
        interBD.finTransaccion();
        
        return res;
    }
    
    public static int deleteAsuntos(AsuntoRA asuntoRA, String sqlExtra) throws SQLException, ClassNotFoundException, IOException {
        return deleteAsuntos(asuntoRA.idAsunto, asuntoRA.codigo, asuntoRA.nombre, asuntoRA.pE, asuntoRA.idNorma, asuntoRA.periodicidad, asuntoRA.clave, asuntoRA.idCliente, asuntoRA.idResponsable, asuntoRA.idAero, asuntoRA.abierto, asuntoRA.posicion, asuntoRA.nCorto, asuntoRA.idioma, asuntoRA.finiPC, asuntoRA.finiSC, asuntoRA.finiME1, asuntoRA.finiME10, asuntoRA.tipo, sqlExtra);
    }
} 