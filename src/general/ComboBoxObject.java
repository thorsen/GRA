/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package general;

import javax.swing.JComboBox;

/**
 *
 * @author Victor Fernandez
 */

//Clase para poder almacenar pares <codigo, descripcion> en ComboBox de manera que solo
//muestre la descripción, pero sea posible obtener el código sin realizar nuevas consultas
//a la base de datos
public class ComboBoxObject {
    private Object codigo;
    private Object descripcion;

    public ComboBoxObject(Integer codigo, String descripcion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    public Object getCodigo() {
        return codigo;
    }

    public void setCodigo(Object codigo) {
        this.codigo = codigo;
    }

    public Object getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(Object descripcion) {
        this.descripcion = descripcion;
    }
    
    public static Object getClaveCombo(JComboBox jcb, int pos) {
        Object res = null;

        if (pos >= 0 && pos < jcb.getItemCount())
            res = ((ComboBoxObject)jcb.getItemAt(pos)).getCodigo();
       
        return res;
    }
    
    public static Object getClaveSelCombo(JComboBox jcb) {
        int pos = jcb.getSelectedIndex();
       
        return getClaveCombo(jcb, pos);
    }
    
    public static Object getDescCombo(JComboBox jcb, int pos) {
        Object res = null;

        if (pos >= 0 && pos < jcb.getItemCount())
            res = ((ComboBoxObject)jcb.getItemAt(pos)).getDescripcion();
       
        return res;
    }
    
    public static Object getDescSelCombo(JComboBox jcb) {
        int pos = jcb.getSelectedIndex();
       
        return getDescCombo(jcb, pos);
    }
    
    public static ComboBoxObject getItemAt(JComboBox jcb, int pos) {
        return (ComboBoxObject) jcb.getItemAt(pos);
    }
    
    public static ComboBoxObject getItemWithKey(JComboBox jcb, Object key) {
        ComboBoxObject res = null;
        
        int nItems = jcb.getItemCount();
        int pos = -1;
                
        for (int i = 0; i < nItems; i++) {
            if (key.equals(getClaveCombo(jcb, i))) {
                pos = i;
                break;
            }
        }
        
        if (pos != -1)
            res = getItemAt(jcb, pos);
        
        return res;
    }
    
    public static ComboBoxObject getItemWithValue(JComboBox jcb, Object value) {
        ComboBoxObject res = null;
        
        int nItems = jcb.getItemCount();
        int pos = -1;
                
        for (int i = 0; i < nItems; i++) {
            if (value.equals(getDescCombo(jcb, i))) {
                pos = i;
                break;
            }
        }
        
        if (pos != -1)
            res = getItemAt(jcb, pos);
        
        return res;
    }

    @Override
    public String toString() {
        return descripcion.toString();
    }
}