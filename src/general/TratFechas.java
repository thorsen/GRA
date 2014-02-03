package general;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/**
 *
 * @author Ruth Cordon
 */
public class TratFechas {
    public static final String FORMATO_FECHA = "dd/MM/yyyy HH:mm:ss.SSS";
    
    public static final String FECHA_MIN = "01/01/1970 00:00:00.000";
    public static final String FECHA_MAX = "31/12/2999 23:59:59.999";

    public static final String HORA_MIN = "00:00:00.000";
    public static final String HORA_SIMPLE_MIN = "00:00:00";

    public static final String MINUTOS_MIN = "00:00.000";
    public static final String MINUTOS_SIMPLE_MIN = "00:00";

    //Obtiene los milisegundos de una fecha con el formato <formato>
    public static long millisFechaGen(String fecha, String formato) {
        long res = -1;
        
        try {
            SimpleDateFormat sdfIn = new SimpleDateFormat(formato);

			if (!fecha.isEmpty())
				res =  sdfIn.parse(fecha).getTime();
			else
				res = millisFechaGen(FECHA_MAX, FORMATO_FECHA);
        } catch (ParseException e) {
            MensajeApp.muestraError(null, e, "Fallo parseando fecha. Debe seguir el formato " + formato);
        } finally {
            return res;
        }
    }
    
    //Obtiene los milisegundos de una fecha con el formato <FORMATO_FECHA>
    public static long millisFecha(String fecha) {
        return millisFechaGen(fecha, FORMATO_FECHA);
    }
    
    //Compara dos fechas
    public static int comparaFechasMillis(long fecha1, long fecha2) {
        GregorianCalendar gCal1 = new GregorianCalendar();
        gCal1.setTimeInMillis(fecha1);

        GregorianCalendar gCal2 = new GregorianCalendar();
        gCal2.setTimeInMillis(fecha2);

        return gCal1.compareTo(gCal2);
    }
    
    //Compara dos fechas
    public static int comparaFechasGen(String fecha1, String fecha2, String formato) {
        return comparaFechasMillis(millisFechaGen(fecha1, formato), millisFechaGen(fecha2, formato));
    }
    
    //Compara dos fechas
    public static int comparaFechas(String fecha1, String fecha2) {
        return comparaFechasGen(fecha1, fecha2, FORMATO_FECHA);
    }
    
    //Pasa de un formato de fecha a otro
    public static String cambiaFormFechaGen(String fecha, String formatoIn, String formatoOut) {
        String res = null;
        try {
            SimpleDateFormat sdfIn = new SimpleDateFormat(formatoIn);
            SimpleDateFormat sdfOut = new SimpleDateFormat(formatoOut);

            res = sdfOut.format(sdfIn.parse(fecha));
        } catch (ParseException e) {
            MensajeApp.muestraError(null, e, "Fallo parseando fecha");
        } finally {
            return res;
        }
    }

    //Pasa a cadena una fecha en milisegundos
    public static String toStringFechaGen(long fechaMillis, String formato) {
        SimpleDateFormat sdf = new SimpleDateFormat(formato);

        return sdf.format(fechaMillis).substring(0, formato.length());
    }
    
    //Da formato <FORMATO_FECHA> a una fecha en milisegundos
    public static String toStringFecha(long fechaMillis) {
        return toStringFechaGen(fechaMillis, FORMATO_FECHA);
    }
    
    //Construye la nueva fecha sumando los milisegundos a la fecha
    public static String incFechaGen(String fecha, long millis, String formato) {
        return toStringFechaGen(millisFechaGen(fecha, formato) + millis, formato);
    }
    
    //Incrementa la fecha de formato <FORMATO_FECHA>
    public static String incFecha(String fecha, long millis) {
        return toStringFechaGen(millisFecha(fecha) + millis, FORMATO_FECHA);
    }
}
