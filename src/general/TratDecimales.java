/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package general;

import java.math.BigDecimal;

/**
 *
 * @author Victor Fernandez
 */
public class TratDecimales {
    public static int DEC_SECTOR = 2;
    public static int DEC_DIRECCION = 3;
    public static int DEC_VARIABLE_RA = 5;
    public static int DEC_RESULTADO = 1;
    public static int DEC_POTENCIA = 15;
    
    //Función para redondear a una precisión determinada según el modo establecido  
    public static Double round(Double valor, int precision, int modo) throws NumberFormatException {

        BigDecimal bg = new BigDecimal(valor.toString());
        
        return bg.setScale(precision, modo).doubleValue();
    }
    
    //Función para redondear a una precisión determinada con el modo por defeto
    public static Double round(Double valor, int precision) throws NumberFormatException {
        return round(valor, precision, BigDecimal.ROUND_HALF_UP);
    }
    
    //Función para redondear a una precisión determinada con el modo por defeto
    public static Double roundBaja(Double valor, int precision) throws NumberFormatException {
        return round(valor, precision, BigDecimal.ROUND_HALF_DOWN);
    }
}
