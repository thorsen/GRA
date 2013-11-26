/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package general;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

/**
 *
 * @author Victor Fernandez
 */
public class JSpinnerDate extends JSpinner {
    private String formato;

    public JSpinnerDate(String dateFormat) {
        super(new SpinnerDateModel());
        
        this.formato = dateFormat;
        
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(this, dateFormat);
        this.setEditor(timeEditor);
    }
    
    public JSpinnerDate() {
        this("dd/MM/yyyy HH:mm:ss.SSS");
    }
   
    public void setText(String fecha) {
        try {
            SimpleDateFormat sdfIn = new SimpleDateFormat(this.formato);

            this.setValue(sdfIn.parse(fecha));
        } catch (ParseException e) {
            MensajeApp.muestraError(null, e, "Fallo parseando fecha. Debe seguir el formato " + formato);
        }
    }
    
    public void setTimeInMillis(Long fechaMillis) {
        this.setText(TratFechas.toStringFechaGen(fechaMillis, this.formato));
    }
    
    public long getTimeInMillis() {
        GregorianCalendar gCal1 = new GregorianCalendar();
        gCal1.setTime((Date) this.getValue());
        
        return gCal1.getTimeInMillis();
    }
    
    public String getText() {
        return TratFechas.toStringFechaGen(getTimeInMillis(), this.formato);
    }
}
