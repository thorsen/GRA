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

	public static String DMA_HMS = "dd/MM/yyyy HH:mm:ss.SSS";
	public static String HMS = "HH:mm:ss.SSS";
	public static String HMS_SIMPLE = "HH:mm:ss";
	public static String MS = "mm:ss.SSS";
	public static String MS_SIMPLE = "mm:ss";

    public JSpinnerDate(String dateFormat) {
        super(new SpinnerDateModel());
        
        this.formato = dateFormat;
        
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(this, dateFormat);
        this.setEditor(timeEditor);
    }
    
    public JSpinnerDate() {
        this(DMA_HMS);
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
		long res = 0;

        GregorianCalendar gCal1 = new GregorianCalendar();
		gCal1.setTime((Date) this.getValue());

		if (!this.formato.contentEquals(DMA_HMS)) {
			res = (((gCal1.get(GregorianCalendar.HOUR_OF_DAY) * 60 + gCal1.get(GregorianCalendar.MINUTE)) * 60) + gCal1.get(GregorianCalendar.SECOND)) * 1000 + gCal1.get(GregorianCalendar.MILLISECOND);
		} else
			res = gCal1.getTimeInMillis();
        
        return res;
    }
    
    public String getText() {
        return TratFechas.toStringFechaGen(getTimeInMillis(), this.formato);
    }
}
