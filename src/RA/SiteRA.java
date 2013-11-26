/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RA;

import general.InteraccionBD;
import java.sql.SQLException;
import java.util.ArrayList;

public class SiteRA {

    private Integer idSite;
    private String nombre;
    private Integer sad;
    private String fechaDesc;
    private String horaDesc;
    private Boolean activo;
    private Integer fase;
    public static final String TABLA = "Site";
    public static final String CAMPO_ID_SITE = "Id_site";
    public static final String CAMPO_NOMBRE = "Nombre";
    public static final String CAMPO_SAD = "Sad";
    public static final String CAMPO_FECHA_DESC = "Fecha_Descarga";
    public static final String CAMPO_HORA_DESC = "Hora_Descarga";
    public static final String CAMPO_ACTIVO = "Activo";
    public static final String CAMPO_FASE = "Fase";

    public SiteRA(Integer idSite, String nombre, Integer sad, String fechaDesc, String horaDesc, Boolean activo, Integer fase) {
        this.idSite = idSite;
        this.nombre = nombre;
        this.sad = sad;
        this.fechaDesc = fechaDesc;
        this.horaDesc = horaDesc;
        this.activo = activo;
        this.fase = fase;
    }

    public SiteRA(Object[] valores, ArrayList<String> campos) throws SQLException, NoSuchFieldException {
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

    public Integer getFase() {
        return fase;
    }

    public void setFase(Integer fase) {
        this.fase = fase;
    }

    public String getFechaDesc() {
        return fechaDesc;
    }

    public void setFechaDesc(String fechaDesc) {
        this.fechaDesc = fechaDesc;
    }

    public String getHoraDesc() {
        return horaDesc;
    }

    public void setHoraDesc(String horaDesc) {
        this.horaDesc = horaDesc;
    }

    public Integer getIdSite() {
        return idSite;
    }

    public void setIdSite(Integer idSite) {
        this.idSite = idSite;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getSad() {
        return sad;
    }

    public void setSad(Integer sad) {
        this.sad = sad;
    }
    //Función para asignar valores a campos de la clase según el campo de la BD
    private void asignaCampo(String campo, Object valor) throws NoSuchFieldException {
        if (campo.compareTo(CAMPO_ID_SITE) == 0) {
            this.idSite = (Integer) valor;
        } else if (campo.compareTo(CAMPO_NOMBRE) == 0) {
            this.nombre = (String) valor;
        } else if (campo.compareTo(CAMPO_SAD) == 0) {
            this.sad = (Integer) valor;
        } else if (campo.compareTo(CAMPO_FECHA_DESC) == 0) {
            this.fechaDesc = (String) valor;
        } else if (campo.compareTo(CAMPO_HORA_DESC) == 0) {
            this.horaDesc = (String) valor;
        } else if (campo.compareTo(CAMPO_ACTIVO) == 0) {
            this.activo = (Boolean) valor;
        } else if (campo.compareTo(CAMPO_FASE) == 0) {
            this.fase = (Integer) valor;
        } else {
            throw new NoSuchFieldException("No existe el campo <" + campo + "> en la clase " + this.getClass().getSimpleName());
        }
    }
    
    private static String getCondicion(Integer idSite, String nombre, Integer sad, String fechaDesc, String horaDesc, Boolean activo, Integer fase, ArrayList<Object[]> paramsPS) {
        String condicion = "";
        
        if (idSite != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_ID_SITE, "=", idSite);
        }
        if (nombre != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_NOMBRE, "=", nombre);
        }
        if (sad != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_SAD, "=", sad);
        }
        if (fechaDesc != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_FECHA_DESC, "=", fechaDesc);
        }
        if (horaDesc != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_HORA_DESC, "=", horaDesc);
        }
        if (activo != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_ACTIVO, "=", activo);
        }
        if (fase != null) {
            condicion = InteraccionBD.anadeCampoCondicion(condicion, paramsPS, CAMPO_FASE, "=", fase);
        }
        
        return condicion;
    }
    
    //Función para obtener la colección de Sites que se ajustan a los limites pasados
    public static ArrayList<SiteRA> getSites(Integer idSite, String nombre, Integer sad, String fechaDesc, String horaDesc, Boolean activo, Integer fase,
            ArrayList<String> campos, String sqlExtra, Boolean distinct) throws SQLException, NoSuchFieldException {
        InteraccionBD interBD = new InteraccionBD();

        ArrayList<SiteRA> res = null;
        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();

        condicion = getCondicion(idSite, nombre, sad, fechaDesc, horaDesc, activo, fase, paramsPS);

        //Por defecto lo devolvemos ordenado por Site
        if (sqlExtra == null || sqlExtra.trim().length() == 0) {
            String sqlOrderBy = InteraccionBD.anadeCampoOrderBy(null, CAMPO_ID_SITE);
            sqlExtra = InteraccionBD.anadeSqlExtra(null, sqlOrderBy);
        }
        
        ArrayList<Object[]> resAux = interBD.getDatosTabla(TABLA, campos, condicion, paramsPS, sqlExtra, distinct);

        if (resAux != null) {
            int n = resAux.size();
            res = new ArrayList<SiteRA>();

            for (int i = 0; i < n; i++) {
                res.add(new SiteRA(resAux.get(i), campos));
            }
        }

        return res;
    }

    public static SiteRA getSitePorId(Integer idSite) throws SQLException, NoSuchFieldException {
        SiteRA res = null;
        ArrayList<SiteRA> resAux = getSites(idSite, null, null, null, null, null, null, null, null, null);

        if (resAux != null && resAux.size() > 0) {
            res = resAux.get(0);
        }
        return res;
    }

    //Función para añadir un site a la BD
    public static int insertSite(Integer idSite, String nombre, Integer sad, String fechaDesc, String horaDesc, Boolean activo, Integer fase, String sqlExtra) throws SQLException {
        InteraccionBD interBD = new InteraccionBD();

        String valores = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        ArrayList<String> campos = new ArrayList<String>();
        ArrayList<String> autoInc = new ArrayList<String>();
        ArrayList<String> condAutoInc = new ArrayList<String>();
        int res = 0;

        if (idSite != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, idSite);
            campos.add(CAMPO_ID_SITE);
        } else {    //Es campo clave, asignamos el incremento de la última
            valores = InteraccionBD.anadeCampoValorAutoInc(valores, CAMPO_ID_SITE);
            campos.add(CAMPO_ID_SITE);
            autoInc.add(CAMPO_ID_SITE);
            condAutoInc.add(null);
        }
        if (nombre != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, nombre);
            campos.add(CAMPO_NOMBRE);
        }
        if (sad != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, sad);
            campos.add(CAMPO_SAD);
        }
        if (fechaDesc != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, fechaDesc);
            campos.add(CAMPO_FECHA_DESC);
        }
        if (horaDesc != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, horaDesc);
            campos.add(CAMPO_HORA_DESC);
        }
        if (activo != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, activo);
            campos.add(CAMPO_ACTIVO);
        }
        if (fase != null) {
            valores = InteraccionBD.anadeCampoValor(valores, paramsPS, fase);
            campos.add(CAMPO_FASE);
        }

        interBD.inicioTransaccion();
        res = interBD.insertDatosTabla(TABLA, campos, valores, paramsPS, sqlExtra, autoInc, condAutoInc);
        interBD.finTransaccion();

        return res;
    }

    public static int insertSite(SiteRA site, String sqlExtra) throws SQLException {
        return insertSite(site.idSite, site.nombre, site.sad, site.fechaDesc, site.horaDesc, site.activo, site.fase, sqlExtra);
    }
    //Función para añadir un site a la BD
    public static int updateSite(Integer idSite, String nombre, Integer sad, String fechaDesc, String horaDesc, Boolean activo, Integer fase,
            Integer idSiteVal, String nombreVal, Integer sadVal, String fechaDescVal, String horaDescVal, Boolean activoVal, Integer faseVal, String sqlExtra) throws SQLException {
        InteraccionBD interBD = new InteraccionBD();

        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        ArrayList<String> campos = new ArrayList<String>();
        int res = 0;

        if (idSiteVal != null && !idSiteVal.equals(idSite)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, idSiteVal);
            campos.add(CAMPO_ID_SITE);
        }
        if (nombreVal != null && !nombreVal.equals(nombre)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, nombreVal);
            campos.add(CAMPO_NOMBRE);
        }
        if (sadVal != null && !sadVal.equals(sad)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, sadVal);
            campos.add(CAMPO_SAD);
        }
        if (fechaDescVal != null && !fechaDescVal.equals(fechaDesc)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, fechaDescVal);
            campos.add(CAMPO_FECHA_DESC);
        }
        if (horaDescVal != null && !horaDescVal.equals(horaDesc)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, horaDescVal);
            campos.add(CAMPO_HORA_DESC);
        }
        if (activoVal != null && !activoVal.equals(activo)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, activoVal);
            campos.add(CAMPO_ACTIVO);
        }
        if (faseVal != null && !faseVal.equals(fase)) {
            InteraccionBD.anadeCampoValor(null, paramsPS, faseVal);
            campos.add(CAMPO_FASE);
        }

        //Condiciones de actualización
        condicion = getCondicion(idSite, nombre, sad, fechaDesc, horaDesc, activo, fase, paramsPS);

        interBD.inicioTransaccion();
        res = interBD.updateDatosTabla(TABLA, campos, condicion, paramsPS, sqlExtra);
        interBD.finTransaccion();

        return res;
    }

    public static int updateSite(SiteRA siteViejo, SiteRA siteNuevo, String sqlExtra) throws SQLException {
        return updateSite(siteViejo.idSite, siteViejo.nombre, siteViejo.sad, siteViejo.fechaDesc, siteViejo.horaDesc, siteViejo.activo, siteViejo.fase,
                siteNuevo.idSite, siteNuevo.nombre, siteNuevo.sad, siteNuevo.fechaDesc, siteNuevo.horaDesc, siteNuevo.activo, siteNuevo.fase, sqlExtra);
    }
    //Función para añadir/modificar un site a la BD
    public static int insertOrUpdateSite(Integer idSite, String nombre, Integer sad, String fechaDesc, String horaDesc, Boolean activo, Integer fase,
            Integer idSiteVal, String nombreVal, Integer sadVal, String fechaDescVal, String horaDescVal, Boolean activoVal, Integer faseVal, String sqlExtra) throws SQLException {
        int res;

        res = updateSite(idSite, nombre, sad, fechaDesc, horaDesc, activo, fase, idSiteVal, nombreVal, sadVal, fechaDescVal, horaDescVal, activoVal, faseVal, sqlExtra);

        if (res < 0) {
            res = insertSite(idSiteVal, nombreVal, sadVal, fechaDescVal, horaDescVal, activoVal, faseVal, sqlExtra);
        }
        return res;
    }

    public static int insertOrUpdateSite(SiteRA siteViejo, SiteRA siteNuevo, String sqlExtra) throws SQLException {

        return insertOrUpdateSite(siteViejo.idSite, siteViejo.nombre, siteViejo.sad, siteViejo.fechaDesc, siteViejo.horaDesc, siteViejo.activo, siteViejo.fase,
                siteNuevo.idSite, siteNuevo.nombre, siteNuevo.sad, siteNuevo.fechaDesc, siteNuevo.horaDesc, siteNuevo.activo, siteNuevo.fase, sqlExtra);
    }
    //Función para eliminar Sites que se ajustan a los limites pasados
    public static int deleteSites(Integer idSite, String nombre, Integer sad, String fechaDesc, String horaDesc, Boolean activo, Integer fase, String sqlExtra) throws SQLException {
        InteraccionBD interBD = new InteraccionBD();

        String condicion = "";
        ArrayList<Object[]> paramsPS = new ArrayList<Object[]>();
        int res = 0;

        condicion = getCondicion(idSite, nombre, sad, fechaDesc, horaDesc, activo, fase, paramsPS);

        interBD.inicioTransaccion();
        res = interBD.deleteDatosTabla(TABLA, condicion, paramsPS, sqlExtra);
        interBD.finTransaccion();

        return res;
    }

    public static int deleteSites(SiteRA site, String sqlExtra) throws SQLException {
        return deleteSites(site.idSite, site.nombre, site.sad, site.fechaDesc, site.horaDesc, site.activo, site.fase, sqlExtra);
    }

    public Object[] toObject() {
        return new Object[]{this.idSite, this.nombre, this.sad, this.fechaDesc, this.horaDesc, this.activo, this.fase};
    }

    public static Object[] getCamposTabla() throws SQLException {
        InteraccionBD interBD = new InteraccionBD();

        return interBD.getCamposTabla(TABLA);
    }
}
