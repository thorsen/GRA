package general;

import java.util.ArrayList;
import java.util.Map.Entry;

public class ArrayListExt<E> extends ArrayList<E> {
    /*
    //Función que devuelve el ArrayList de las claves de un ArrayList de entrys
    public ArrayList<E> getKeysArrayList(ArrayList<Entry<K, V>> nivelesEntry) {
        ArrayList res = null;
        
        int nNiveles = nivelesEntry != null ? nivelesEntry.size() : 0;
        
        if (nNiveles > 0) {
            res = new ArrayList<E>();
            
            for (int i = 0; i < nNiveles; i++) {
                res.add(nivelesEntry.get(i).getKey());
            }
        }
                
        return res;
    }
    
    //Función que devuelve el ArrayList de los valores de un ArrayList de entrys
    public static ArrayList getValuesArrayList(ArrayList<Entry> nivelesEntry) {
        ArrayList res = null;
        
        int nNiveles = nivelesEntry != null ? nivelesEntry.size() : 0;
        
        if (nNiveles > 0) {
            res = new ArrayList();
            
            for (int i = 0; i < nNiveles; i++) {
                res.add(nivelesEntry.get(i).getValue());
            }
        }
                
        return res;
    }
     * */
}
