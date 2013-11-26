/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package general;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Victor Fernandez
 */
public class MensajeApp {
    public static void muestraError(Component c, Exception e, String texto) {
         try {
            String desc = "<html>";
            if (texto != null && texto.length() > 0)
                desc += texto;

            if (e != null) {
                if (desc.length()>0)
                    desc += "<br>";

                desc += "<i><b>Error: </b>" + e.toString() + "</i>";
                desc += "<br>";

                desc += "<i><b>Pila Error: </b>&#09;";

                StackTraceElement ste[] = e.getStackTrace();

                int nSte = ste.length;

                for (int i = 0; i < nSte; i++) {
                    if (    Class.forName(ste[i].getClassName()).getPackage().getName().startsWith("java") || 
                            Class.forName(ste[i].getClassName()).getPackage().getName().startsWith("com."))
                        continue;

                    desc += ste[i].toString() + "<br>&#09;";
                }

                desc += "</i>";
            }    

            desc += "</html>";

            JOptionPane.showMessageDialog(c, new JLabel(desc), "Error en la aplicación", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void muestraInfo(Component c, String texto) {
        //Se mantienen las etiquetas HTML por si en un futuro interesa darle formato al mensaje
        String desc = "<html>";
        
        desc += texto;
        
        desc += "</html>";
        
        JOptionPane.showMessageDialog(c, new JLabel(desc), "Información", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void muestraWarning(Component c, String texto) {
        //Se mantienen las etiquetas HTML por si en un futuro interesa darle formato al mensaje
        String desc = "<html>";
        
        desc += texto;
        
        desc += "</html>";
        
        JOptionPane.showMessageDialog(c, new JLabel(desc), "Aviso", JOptionPane.WARNING_MESSAGE);
    }
}
