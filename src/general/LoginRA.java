/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package general;

import RA.ResponsableRA;
import java.sql.SQLException;

/**
 *
 * @author Victor Fernandez
 */
public class LoginRA {
    private String usuario;
    private String password;
    private Integer idResponsable;
    private Integer rol;
    
    public LoginRA(String usuario, String password) throws SQLException, NoSuchFieldException {
        ResponsableRA resp;
        
        this.usuario = usuario;
        this.password = password;
        
        resp = ResponsableRA.getResponsablePorUsuario(usuario);
                
        if (resp != null && resp.getActivo()) {
            this.idResponsable = resp.getIdResponsable();
            this.rol = resp.getRol();
        }
    }
    
    public Integer getIdResponsable() {
        return idResponsable;
    }

    public void setIdResponsable(Integer idResponsable) {
        this.idResponsable = idResponsable;
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
}
