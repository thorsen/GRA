/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package general;

/**
 *
 * @author Victor Fernandez
 */
public class TratCadenas {
        
    // Devuelve si una cadena es entero
    public static boolean esEnteroValido(String cad){
        boolean res = true;
        try {
            Integer.parseInt(cad);
	} catch (NumberFormatException e){
            res = false;
	} finally {
            return res;
        }
    }
    
    //Devuelve si una cadena es de tipo año
    public static boolean esAnoValido(String cad){
        boolean res = true;
        
        //Asignamos en res si es o no entero válido, por si ya lo es
        if (res = esEnteroValido(cad)) {
            int numAno = Integer.parseInt(cad);
            
            res = (numAno >= 1900 && numAno <= 2999);
        }
        
        return res;
    }
}
