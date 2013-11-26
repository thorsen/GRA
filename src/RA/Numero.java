
package RA;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/**
 *
 * @author Ruth Cordon
 */

public class Numero {

//--------------------------------------------------------------------------------------------//    
    
    // Devuelve la expresión en binario de un numero (formato f: Ejemplo: Formato f=3 --> 000)
    public String  getBinario(int n, int f){
        String b=Integer.toBinaryString(n);
        int l=b.length();
        for (int i=0;i<(f-l);i++){
           b="0"+b;
        }
        return b;
    }
 
//--------------------------------------------------------------------------------------------//    
   
     // Redondea un numero al numero de decimales indicado
    public double redondear( double numero, int decimales ) {
        return Math.round(numero*Math.pow(10,decimales))/Math.pow(10,decimales);
   }
    
//--------------------------------------------------------------------------------------------//         
    
    // Devuelve el numero de decimales de un nº
    public int getndecs (double n) {
        int dec=0;
        String ns=String.valueOf(n);
        int posptos=ns.indexOf(".");
        if (posptos!=-1){//tiene decimales
            dec=ns.substring(posptos+1).length();                    
        } 
        return dec;      
    }
    
//--------------------------------------------------------------------------------------------//         
    
    // Trunca un número a un nº de decimales
    public double Truncar(double nD, int nDec){   
       if(nD > 0)
           nD = Math.floor(nD * Math.pow(10,nDec))/Math.pow(10,nDec);
       else
           nD = Math.ceil(nD * Math.pow(10,nDec))/Math.pow(10,nDec);   
       return nD;   
   } 
    
//--------------------------------------------------------------------------------------------//         
    
     // Dvuelve el bit de la posición i de una cadena de n caracteres
    public int  getBit(String b,int pos,int n){
        return(Integer.parseInt(b.substring(n-pos,(n-pos)+1),2));
    }
    
//--------------------------------------------------------------------------------------------//         
    
        // Devuelve el mínimo entre dos números
    public int getMin(int n1, int n2){
        if (n1<=n2){
            int  min=n1;
            return min;
        } else {
            int  min=n2;
            return min;
        }
    }
    
//--------------------------------------------------------------------------------------------//                 
    
    // Devuelve el máximo entre dos números
    public double getMax(double n1, double n2){
        if (n1>=n2){
            double  max=n1;
            return max;
        } else {
            double  max=n2;
            return max;
        }
    }
    
//--------------------------------------------------------------------------------------------//                 
        // Devuelve el bin de velocidad correspondiente a un numero
    public int getBinVel(double v){
        Numero N=new Numero();
        return ((int)N.Truncar(2*v+0.5,0));
    }

//--------------------------------------------------------------------------------------------//         
    
    // Devuelve el bin de dirección correspondiente a un numero (bines de 10º). No se usa
    public int getBinDir(double d){
        Numero N=new Numero();
        return ((int)N.Truncar((d/10)+0.5,0));
    }
    
//--------------------------------------------------------------------------------------------//        
    
     // Devuelve si un numero es entero
    public boolean isEntero(String numero){
        boolean b=false;
        try {
            Integer.parseInt(numero);
            b= true;
	} catch (NumberFormatException e){
            b= false;
	}finally{
            return b;
        }
    }

//--------------------------------------------------------------------------------------------//        
   
    // Devuelve si una cadena representa un numero
      public boolean isNumeric(String numero){
            boolean b=false;
            try {
                Double.parseDouble(numero);
                b= true;
            } catch (NumberFormatException e){
                b= false;
            }finally{
                return b;
            }
        } 
 //--------------------------------------------------------------------------------------------//    

    // Devuelve si un numero es entero
    public boolean isEntero(double numero){
        boolean b=false;
        try {
            if ((int)numero-numero==0){b= true;}     
	} catch (NumberFormatException e){
            b= false;
	}finally{
            return b;
        }
    }

//--------------------------------------------------------------------------------------------//        

     // Da formato a un numero
    public String formato(String f,double numero){        
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator('.');
        DecimalFormat formato=new DecimalFormat(f,simbolos);
        return(formato.format(numero));
    }
    
//--------------------------------------------------------------------------------------------//        

     // Devuelve el multiplo de un numero mas cercano a otro
    public int multiplo(int multiplo,double numero){        
        System.out.println("numero "+numero);
        int min=multiplo;
        
        while (min<numero){
            System.out.println(min);
            min=min+multiplo;
        }
        
        if (min==multiplo){
            min=min+multiplo;
        }
        return min-multiplo;
        
    }
    
//--------------------------------------------------------------------------------------------//        

    // Devuelve la distancia entre dos puntos
    public double distancia(double[] punto_1,double[] punto_2){        
        double distancia=java.lang.Math.sqrt(java.lang.Math.pow(punto_1[0]-punto_2[0],2)+java.lang.Math.pow(punto_1[1]-punto_2[1],2));     
        return distancia;        
    }    
    
}
