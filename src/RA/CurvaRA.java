package RA;

import general.InteraccionBD;
import java.sql.SQLException;
import java.util.ArrayList;

public class CurvaRA {
    private Integer idAsunto;
    private Double densidad;
    private Double vel;
    private Double pot;
    
    public static final String BD = InteraccionBD.PREF_BD_GENERAL;
    public static final String TABLA = BD + "Curva";
    public static final String CAMPO_ID_ASUNTO = "Asunto";
    public static final String CAMPO_DENSIDAD = "Densidad";
    public static final String CAMPO_VEL = "V";
    public static final String CAMPO_POT = "P";

    public CurvaRA(Integer idAsunto, Double densidad, Double vel, Double pot) {
        this.idAsunto = idAsunto;
        this.densidad = densidad;
        this.vel = vel;
        this.pot = pot;
    }
    
    public CurvaRA(Object[] valores, ArrayList<String> campos) throws SQLException, NoSuchFieldException {
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
    
    public Double getDensidad() {
        return densidad;
    }

    public void setDensidad(Double densidad) {
        this.densidad = densidad;
    }

    public Integer getIdAsunto() {
        return idAsunto;
    }

    public void setIdAsunto(Integer idAsunto) {
        this.idAsunto = idAsunto;
    }

    public Double getPot() {
        return pot;
    }

    public void setPot(Double pot) {
        this.pot = pot;
    }

    public Double getVel() {
        return vel;
    }

    public void setVel(Double vel) {
        this.vel = vel;
    }
    
    //Función que devuelve pendiente y offset de la recta obtenida entre los puntos p1 y p2
    public double[] Recta(double[] p1, double[] p2){
        double [] recta=new double[2];
        double pendiente=(p2[0]-p1[0])/(p2[1]-p1[1]);
        double offset=pendiente*(-1)*p1[1]+p1[0];
        recta[0]=pendiente;
        recta[1]=offset;
        return recta;
    }

    //Función que devuelve pendiente entre dos curvas
    private static Double getPendiente(CurvaRA curva1, CurvaRA curva2) {
        return (curva2.pot - curva1.pot) != 0 ? ((curva2.vel - curva1.vel) / (curva2.pot - curva1.pot)) : 0;
    }
    
    //Función que devuelve offset entre dos curvas
    private static Double getOffset(CurvaRA curva1, CurvaRA curva2) {
        return (getPendiente(curva1, curva2) * (-1) * curva1.pot + curva1.vel);
    }
    
    public Double getCoeficientePot() throws SQLException, NoSuchFieldException {
        Double res = null;
        
        Double dN = AerogeneradorRA.getAeroPorId(idAsunto).getDN();
        
        if (vel != null && dN != null && vel != 0 && dN != 0) {
            res = 1000 * pot / (0.5 * densidad * (java.lang.Math.PI * java.lang.Math.pow(dN / 2, 2)) * java.lang.Math.pow(vel, 3));
        }

        return res;
    }
    
    //Función para asignar valores a campos de la clase según el campo de la BD
    private void asignaCampo(String campo, Object valor) throws NoSuchFieldException {
        if (campo.compareTo(CAMPO_ID_ASUNTO) == 0) {
            this.idAsunto = (Integer) valor;
        } else if (campo.compareTo(CAMPO_DENSIDAD) == 0) {
            this.densidad = (Double) valor;
        } else if (campo.compareTo(CAMPO_VEL) == 0) {
            this.vel = (Double) valor;
        } else if (campo.compareTo(CAMPO_POT) == 0) {
            this.pot = (Double) valor;
        } else {
            throw new NoSuchFieldException("No existe el campo en la clase " + this.getClass().getSimpleName());
        }
    }
    
    private static String getCondicion(Integer idAsunto, Double densidad, Double vel, Double pot, ArrayList<Object[]> paramsPS) {
        String condicion = "";
        
        if (idAsunto != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_ID_ASUNTO, "=", idAsunto);
        }
        if (densidad != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_DENSIDAD, "=", densidad);
        }
        if (vel != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_VEL, "=", vel);
        }
        if (pot != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_POT, "=", pot);
        }
        
        return condicion;
    }

    //Función para obtener la colección de Curvas que se ajustan a los limites pasados
    public static ArrayList<CurvaRA> getCurvas(Integer idAsunto, Double densidad, Double vel, Double pot,
            ArrayList<String> campos, String sqlExtra, Boolean distinct) throws SQLException, NoSuchFieldException {
        InteraccionBD interBD = new InteraccionBD();
        
        ArrayList<CurvaRA> res = null;
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();

        condicion = getCondicion(idAsunto, densidad, vel, pot, paramsPS);
                
        //Por defecto lo devolvemos ordenado por asunto, velocidad
        if (sqlExtra == null || sqlExtra.trim().length() == 0) {
            String sqlOrderBy = InteraccionBD.anadeCampoOrderBy(null, CAMPO_ID_ASUNTO);
            sqlOrderBy = InteraccionBD.anadeCampoOrderBy(sqlOrderBy, CAMPO_VEL);
            sqlExtra = InteraccionBD.anadeSqlExtra(null, sqlOrderBy);
        }

        ArrayList<Object[]> resAux = interBD.getDatosTabla(TABLA, campos, condicion, paramsPS, sqlExtra, distinct);

        if (resAux != null) {
            int n = resAux.size();
            res = new ArrayList<CurvaRA>();
            
            for (int i = 0; i < n; i++) {
               res.add(new CurvaRA(resAux.get(i), campos));
            }
        }

        return res;
    }
    
    //Función para obtener la colección de Curvas que se ajustan a los limites pasados
    public static ArrayList<CurvaRA> getCurvasPorIdAsuntoDensidad(Integer idAsunto, Double densidad) throws SQLException, NoSuchFieldException {
        return getCurvas(idAsunto, densidad, null, null, null, null, null);
    }
    
    //Función para obtener las densidades
    public static ArrayList<Double> getDensidadesPorIdAsunto(Integer idAsunto) throws SQLException, NoSuchFieldException {
        ArrayList<Double> res = null;
        
        ArrayList<String> campos = new ArrayList<String>();
        
        //Solo nos interesan las densidades
        campos.add(CAMPO_DENSIDAD);
        
        //Ordenamos por modelo
        String sqlOrderBy = InteraccionBD.anadeCampoOrderBy(null, CAMPO_DENSIDAD);
        String sqlExtra = InteraccionBD.anadeSqlExtra(null, sqlOrderBy);
        
        ArrayList<CurvaRA> resAux = getCurvas(idAsunto, null, null, null, campos, sqlExtra, true);
        
        if (resAux != null) {
            int n = resAux.size();
            res = new ArrayList<Double>();
            
            for (int i = 0; i < n; i++) {
                res.add(resAux.get(i).getDensidad());
            }
        }

        return res;
    }
    
    //Función que devuelve una matriz con las rectas entre puntos consecutivos de una curva Double[pendiente, offset]
    public static ArrayList<Object[]> getRectas(ArrayList<CurvaRA> curvas) {
        ArrayList<Object[]> res = null;
        
        if (curvas != null) {
            CurvaRA curva1, curva2;
            int nCurvas = curvas.size();
            
            res = new ArrayList<Object[]>();
            
            for (int i = 0; i < nCurvas-1; i++) {
                curva1 = curvas.get(i);
                curva2 = curvas.get(i+1);
                
                if (curva1.pot != curva2.pot) {
                    res.add(new Object[]{curva1, getPendiente(curva1, curva2), getOffset(curva1, curva2)});
                }
            }
        }

       return res;
    }

    //Función para añadir una curva a la BD
    public static int insertCurva(Integer idAsunto, Double densidad, Double vel, Double pot, String sqlExtra) throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        String valores = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        ArrayList<String> campos = new ArrayList<String>();
        int res = 0;
        
        if (idAsunto != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, idAsunto);
            campos.add(CAMPO_ID_ASUNTO);
        }
        if (densidad != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, densidad);
            campos.add(CAMPO_DENSIDAD);
        }
        if (vel != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, vel);
            campos.add(CAMPO_VEL);
        }
        if (pot != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, pot);
            campos.add(CAMPO_POT);
        }
        
        interBD.inicioTransaccion();
        res = interBD.insertDatosTabla(TABLA, campos, valores, paramsPS, sqlExtra);
        interBD.finTransaccion();
        
        return res;
    }
    
    public static int insertCurva(CurvaRA curva, String sqlExtra) throws SQLException {
        return insertCurva(curva.idAsunto, curva.densidad, curva.vel, curva.pot, sqlExtra);
    }
    
    //Función para añadir una curva a la BD
    public static int insertCurvaValores(Integer idAsunto, Double densidad, ArrayList<Double[]> velPot, String sqlExtra) throws SQLException {
        int res = 0;
        
        if (velPot != null) {
            CurvaRA curvaAux;
            int nVelPot = velPot.size();
            
            for (int i = 0; i < nVelPot; i++) {
                curvaAux = new CurvaRA(idAsunto, densidad, velPot.get(i)[0], velPot.get(i)[1]);
                
                res += insertCurva(curvaAux, sqlExtra);
            }
        }
        
        return res;
    }
    
    //Función para añadir una curva a la BD
    public static int updateCurva(Integer idAsunto, Double densidad, Double vel, Double pot,
            Integer idAsuntoVal, Double densidadVal, Double velVal, Double potVal, String sqlExtra) throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        ArrayList<String> campos = new ArrayList<String>();
        int res = 0;
        
        if (idAsuntoVal != null && !idAsuntoVal.equals(idAsunto)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, idAsuntoVal);
            campos.add(CAMPO_ID_ASUNTO);
        }
        if (densidadVal != null && !densidadVal.equals(densidad)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, densidadVal);
            campos.add(CAMPO_DENSIDAD);
        }
        if (velVal != null && !velVal.equals(vel)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, velVal);
            campos.add(CAMPO_VEL);
        }
        if (potVal != null && !potVal.equals(pot)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, potVal);
            campos.add(CAMPO_POT);
        }
        
        //Condiciones de actualización
        condicion = getCondicion(idAsunto, densidad, vel, pot, paramsPS);
        
        interBD.inicioTransaccion();
        res = interBD.updateDatosTabla(TABLA, campos, condicion, paramsPS, sqlExtra);
        interBD.finTransaccion();
        
        return res;
    }
    
    public static int updateCurva(CurvaRA curvaVieja, CurvaRA curvaNueva, String sqlExtra) throws SQLException {
        return updateCurva(curvaVieja.idAsunto, curvaVieja.densidad, curvaVieja.vel, curvaVieja.pot, 
            curvaNueva.idAsunto, curvaNueva.densidad, curvaNueva.vel, curvaNueva.pot, sqlExtra);
    }
    
    //Función para añadir/modificar una curva a la BD
    public static int insertOrUpdateCurva(Integer idAsunto, Double densidad, Double vel, Double pot,
            Integer idAsuntoVal, Double densidadVal, Double velVal, Double potVal, String sqlExtra) throws SQLException {
        int res;
        
        res = updateCurva(idAsunto, densidad, vel, pot, idAsuntoVal, densidadVal, velVal, potVal, sqlExtra);
        
        if (res < 0)
            res = insertCurva(idAsuntoVal, densidadVal, velVal, potVal, sqlExtra);

        return res;
    }
    
    public static int insertOrUpdateCurva(CurvaRA curvaVieja, CurvaRA curvaNueva, String sqlExtra) throws SQLException {
        
        return insertOrUpdateCurva(curvaVieja.idAsunto, curvaVieja.densidad, curvaVieja.vel, curvaVieja.pot, 
            curvaNueva.idAsunto, curvaNueva.densidad, curvaNueva.vel, curvaNueva.pot, sqlExtra);
    }
    
    //Función para eliminar Curvas que se ajustan a los limites pasados
    public static int deleteCurvas(Integer idAsunto, Double densidad, Double vel, Double pot, String sqlExtra) throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        int res = 0;

        condicion = getCondicion(idAsunto, densidad, vel, pot, paramsPS);
        
        interBD.inicioTransaccion();
        res  = interBD.deleteDatosTabla(TABLA, condicion, paramsPS, sqlExtra);
        interBD.finTransaccion();
        
        return res;
    }
    
    public static int deleteCurvas(CurvaRA curva, String sqlExtra) throws SQLException {
        return deleteCurvas(curva.idAsunto, curva.densidad, curva.vel, curva.pot, sqlExtra);
    }
    
    public Object[] toObject() {
        return new Object[]{this.idAsunto, this.densidad, this.vel, this.pot};
    }
  
    public static Object[] getCamposTabla() throws SQLException {
        InteraccionBD interBD = new InteraccionBD();
        
        return interBD.getCamposTabla(TABLA);
    }
    
    //Devuelve una curva de potencia a densidad 1 convertida a densidad 2
     public static ArrayList<CurvaRA> convierteCP(ArrayList<CurvaRA> curva, Double densidad1, Double densidad2) {
         ArrayList<CurvaRA> res = new ArrayList<CurvaRA>();

         if (curva != null) {
             double x1, x2, y1, y2;
             double v, vAjustada, origen, pendiente, pAjustada;

             
             int nCurva = curva.size();
             
             double vIni = curva.get(0).getVel();
             double vFin = curva.get(nCurva - 1).getVel();

             for (int i = 0; i < nCurva; i++) {
                 v = curva.get(i).getVel();
                 x1 = x2 = y1 = y2 = 0;
                 
                 //double vAjustada = v * java.lang.Math.pow((densidad2 / densidad1) , 0.333333333333333);
                 vAjustada = v * java.lang.Math.pow((densidad2 / densidad1), 1 / 3);

                 if (vAjustada < vIni) {
                     x1 = curva.get(0).getVel();
                     x2 = curva.get(1).getVel();
                     y1 = curva.get(0).getPot();
                     y2 = curva.get(1).getPot();
                 } else {
                     if (vAjustada > vFin) {
                         x1 = curva.get(nCurva - 2).getVel();
                         x2 = curva.get(nCurva - 1).getVel();
                         y1 = curva.get(nCurva - 2).getPot();
                         y2 = curva.get(nCurva - 1).getPot();
                     } else {
                         for (int j = 1; j < nCurva; j++) {
                             if (curva.get(j).getVel() > vAjustada) {
                                 x1 = curva.get(j - 1).getVel();
                                 x2 = curva.get(j).getVel();
                                 y1 = curva.get(j - 1).getPot();
                                 y2 = curva.get(j).getPot();
                                 
                                 break;
                             }
                             
                         }
                     }
                 }

                 pendiente = (y2 - y1) / (x2 - x1);
                 origen = ((y1 + y2) - (pendiente * (x1 + x2))) / 2;
                 pAjustada = vAjustada * pendiente + origen;
                 
                 //res.add(new double[]{v, pAjustada});
                 res.add(new CurvaRA(curva.get(i).getIdAsunto(), densidad2, v, pAjustada));
             }
         }
        
        return res;
    }
     
     public static Double getVelocidadPorcentajePot(ArrayList<CurvaRA> curva, double porcentaje) {
        int i, nBines;
        Double velPorcentaje, potN, potPorcentaje;
        Double vel0, vel1, pot0, pot1;
        
        //Inicializaciones
        velPorcentaje = potN = potPorcentaje = vel0 = vel1 = pot0 = pot1 = 0.0;
        
        if (curva != null) {
            nBines = curva.size();

            if (nBines != 0) {
                potN = curva.get(nBines - 1).getPot();
            }
            potPorcentaje = potN * porcentaje;

            // Se busca el bin del 85%Pn
            for (i = 0; i < nBines; i++) {
                if (curva.get(i).getPot() >= potPorcentaje) {
                    vel1 = curva.get(i).getVel();
                    vel0 = curva.get(i - 1).getVel();
                    pot1 = curva.get(i).getPot();
                    pot0 = curva.get(i - 1).getPot();

                    break;
                }
            }

            if (i < nBines) { //Encontrado en el for anterior
                velPorcentaje = ((vel1 - vel0) * (potPorcentaje - pot0) / (pot1 - pot0) + vel0);
            }
        }

        return (velPorcentaje);
    }
     
    //Devuelve la velocidad de completitud para una curva (1.5*velocidad al 85%Pn)
    public static Double getVelCompletitud(ArrayList<CurvaRA> curva) {
        return 1.5 * getVelocidadPorcentajePot(curva, 85 / 100);
    }
    
    //Devuelve la velocidad de completitud de fgw para una curva (1.3*velocidad al 85%Pn)
    public static double getVelFgw(ArrayList<CurvaRA> curva) {
        return 1.3 * getVelocidadPorcentajePot(curva, 85 / 100);
     }
} 