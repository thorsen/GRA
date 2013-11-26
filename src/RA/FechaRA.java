package RA;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Ruth Cordon
 */
public class FechaRA {

//--------------------------------------------------------------------------------------------// 
    // Construye la nueva fecha sumando los segundos a la fecha
    // Formato ddmmaaaa hhmmss.ss
    public String sumaFecha(String fecha, double segundos) {


        // Se construye la fecha        
        int dia = Integer.parseInt(fecha.substring(0, 2));
        int mes = Integer.parseInt(fecha.substring(2, 4));
        int año = Integer.parseInt(fecha.substring(4, 8));
        int hora = Integer.parseInt(fecha.substring(9, 11));
        int minuto = Integer.parseInt(fecha.substring(11, 13));
        double segundo = Double.parseDouble(fecha.substring(13, 18));

        java.util.Calendar g = new java.util.GregorianCalendar();
        g = new java.util.GregorianCalendar(año, mes - 1, dia);
        SimpleDateFormat fdate = new SimpleDateFormat("ddMMyyyy");
        DecimalFormat fdec = new DecimalFormat("00");
        DecimalFormat fdec2 = new DecimalFormat("00.00");
        double segundo_new = segundo + segundos;
        int minuto_new = minuto;
        int hora_new = hora;
        if (segundo_new >= 60) {
            segundo_new = segundo_new - 60;
            minuto_new = minuto + 1;
            if (minuto_new >= 60) {
                minuto_new = minuto_new - 60;
                hora_new = hora + 1;
                if (hora_new >= 24) {
                    hora_new = hora_new - 24;
                    g.add(Calendar.DAY_OF_MONTH, 1);
                }
            }

        }
        String seg = fdec2.format(segundo_new).replace(",", ".");
        String fecha_nueva = fdate.format(g.getTime()) + " " + fdec.format(hora_new) + fdec.format(minuto_new) + seg;

        return fecha_nueva;

    }
//--------------------------------------------------------------------------------------------// 
    // Compara dos fechas. Devuelve 0 si F1=F2; <0 si F1<F2; >0 si F1>F2
    public int ComparaFechas(String F1, String F2) {

        int dia1 = Integer.parseInt(F1.substring(0, 2));
        int mes1 = Integer.parseInt(F1.substring(2, 4));
        int año1 = Integer.parseInt(F1.substring(4, 8));
        int hora1 = Integer.parseInt(F1.substring(9, 11));
        int minuto1 = Integer.parseInt(F1.substring(11, 13));
        double segundo1 = Double.parseDouble(F1.substring(13, 18));

        int dia2 = Integer.parseInt(F2.substring(0, 2));
        int mes2 = Integer.parseInt(F2.substring(2, 4));
        int año2 = Integer.parseInt(F2.substring(4, 8));
        int hora2 = Integer.parseInt(F2.substring(9, 11));
        int minuto2 = Integer.parseInt(F2.substring(11, 13));
        double segundo2 = Double.parseDouble(F2.substring(13, 18));

        GregorianCalendar G1 = new GregorianCalendar(año1, (mes1 - 1), dia1);
        GregorianCalendar G2 = new GregorianCalendar(año2, (mes2 - 1), dia2);

        int salida = G1.getTime().compareTo(G2.getTime());
        if (salida == 0) { // miramos hora, minuto y segundo
            if (hora1 == hora2) { // Misma hora
                if ((minuto1 == minuto2)) { // Misma hora, Mismo minuto
                    if (segundo1 == segundo2) { // Mismo segundo
                        salida = 0;
                    } else {
                        if (segundo1 < segundo2) {
                            salida = -99; // Menor
                        } else {
                            salida = 99; // Mayor
                        }
                    }
                } else {
                    if (minuto1 < minuto2) {
                        salida = -99; // Menor
                    } else {
                        salida = 99; // Mayor
                    }
                }
            } else {
                if (hora1 < hora2) {
                    salida = -99; // Menor
                } else {
                    salida = 99; // Mayor
                }
            }
        }
        return salida;
    }
//--------------------------------------------------------------------------------------------//    
    // Da formato de fecha RA (ddmmaaaa hhmmss.00) a una fecha (dd/mm/aaaa  hh:mm:ss)
    public String format(String fechaIN) {

        String fechaOUT = null;

        String fecha_aux = fechaIN.replace("/", "");
        fecha_aux = fecha_aux.replace(":", "");
        fecha_aux = fecha_aux.replace("  ", " ");
        fechaOUT = fecha_aux + ".00";

        System.out.println(fecha_aux);
        return fechaOUT;
    }

//--------------------------------------------------------------------------------------------//    
    //Devuelve si una cadena es de tipo año
    public boolean isAnio(String anio) {
        boolean b = true;
        try {
            int a = Integer.parseInt(anio);
            if ((a < 1900) || (a > 2999)) {
                b = false;
            }
        } catch (Exception e) {
            b = false;
        } finally {
            return b;
        }
    }
    
    //--------------------------------------------------------------------------------------------//    
    
    //Devuelve si una cadena es de tipo fecha ddmmaaaa 
    public boolean isDate(String fechax){
        boolean b=true;
        try {
            int d = Integer.parseInt(fechax.substring(0, 2));
            int m = Integer.parseInt(fechax.substring(2, 4));
            int a = Integer.parseInt(fechax.substring(4));
            if ((a < 1900) || (a > 2050) || (m < 1) || (m > 12) || (d < 1) || (d > 31)) {
                b = false;
            } else {
                if ((a%4 != 0) && (m == 2) && (d > 28)) {//Año no bisiesto y es febrero y el dia es mayor a 28
                    b=false;
                } else {
                    if ((((m == 4) || (m == 6) || (m == 9) || (m==11)) && (d>30)) || ((m==2) && (d>29)))
                        b=false;
                }
            }
        } catch(Exception e){
            b = false;
        } finally {
            return b;
        }
    }

    //--------------------------------------------------------------------------------------------//    

    // Devuelve si una cadena es de tipo de hora hhmm
    public boolean isHora(String horax) {
        boolean b = false;
        try {
            int h = Integer.parseInt(horax.substring(0, 2));
            int m = Integer.parseInt(horax.substring(2, 3));
            int mm = Integer.parseInt(horax.substring(3));
            if ((h >= 0)&(h <= 23)&(m >= 0)&(m <= 5)&(mm == 0))
                b=true;
        } catch(Exception e) {
            b = false;
        } finally {
            return b;
        }
    }


    //--------------------------------------------------------------------------------------------//    

    // Devuelve si una cadena es fecha (ddmmaaaa hhmm)
    public boolean isFecha(String fecha){
        boolean b=true;
        if (fecha.length() == 13) { // Posible fecha
            if (!(isDate(fecha.substring(0, 8))) | !(isHora(fecha.substring(9))) | !fecha.substring(8, 9).equals(" "))
                b = false;
        } else
            b = false;

        return b;
    }
}
