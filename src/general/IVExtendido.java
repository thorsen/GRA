/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package general;

import java.awt.Color;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JTextField;

/**
 *
 * @author Victor Fernandez
 */
public class IVExtendido extends InputVerifier {
    private JDialog jd;
    private String tipo;
    private boolean permiteVacio;
    private boolean verifDura;
    
    public static final Color FONDO_EDIT = new Color(255, 255, 255);
    public static final Color FONDO_NO_EDIT = new Color(204, 204, 204);
    public static final Color FONDO_EDIT_ERROR = new Color(255, 127, 127);
    public static final Color FONDO_NO_EDIT_ERROR = new Color(204, 76, 76);
    
    public static final String TIPO_DOUBLE = "double";
    public static final String TIPO_INT = "int";
    public static final String TIPO_FECHA = "fecha";
    public static final String TIPO_STRING = "string";

    //Si !permiteVacío, informará de que el campo debe contener un valor
    //Si verficaciónDura, no permitirá abandonar el campo sin que tenga una entrada válida
    public IVExtendido(JDialog jd, String tipo, boolean permiteVacio, boolean verifDura) {
        this.jd = jd;
        this.tipo = tipo;
        this.permiteVacio = permiteVacio;
        this.verifDura = verifDura;
    }

    private void actualizaColor(boolean valido, JTextField jtf) {
        Color cf;

        if (!valido) {
            if (!jtf.isEditable())
                cf = FONDO_NO_EDIT_ERROR;
            else
                cf = FONDO_EDIT_ERROR;
        } else {
            if (!jtf.isEditable())
                cf = FONDO_NO_EDIT;                            
            else
                cf = FONDO_EDIT;
        }

        jtf.setBackground(cf);
    }

    @Override
    public boolean verify(JComponent input) {
            boolean res = true;
            JTextField jtf = (JTextField) input;

            String valorText = jtf.getText();

            if (valorText.length() != 0) {
                if (tipo.contentEquals(TIPO_DOUBLE)) { //Tipo double
                    try {
                        Double.parseDouble(valorText);
                    } catch (NumberFormatException e) {
                        res = false;
                        MensajeApp.muestraWarning(jd, "El valor del campo " + jtf.getName() + " debe ser un número");
                    }
                } else if (tipo.contentEquals(TIPO_INT)) { //Tipo int
                    try {
                        Integer.parseInt(valorText);
                    } catch (NumberFormatException e) {
                        res = false;
                        MensajeApp.muestraWarning(jd, "El valor del campo " + jtf.getName() + " debe ser un número");
                    }
                } else if (tipo.contentEquals(TIPO_FECHA)) { //Tipo fecha
                    if (TratFechas.millisFecha(valorText) == -1) //Es un error en parseo, TratFechas dará la excepción
                        res = false;
                } else if (tipo.contentEquals(TIPO_STRING)) { //Tipo string
                    //En principio, no hay que validar nada
                } else {
                    res = false;
                    MensajeApp.muestraWarning(jd, "Tipo de validación del campo " + jtf.getName() + " no soportado");
                }
            } else {
                if (!permiteVacio) {
                    res = false;
                    MensajeApp.muestraWarning(jd, "El campo " + jtf.getName() + " debe contener un valor");
                }
            }

            actualizaColor(res, jtf);

            if (!res && !verifDura)
                res = true;

            return res;
        }

    public boolean verifyFinal(JComponent input) {
        boolean res, verifDuraOrig;

        verifDuraOrig = this.verifDura;
        this.verifDura = true;
        res = verify(input);
        this.verifDura = verifDuraOrig;
        
        return res;
    }
}

