package general;

import java.awt.Component;
import java.util.HashMap;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;

public class JComboBoxCellEditor extends DefaultCellEditor {
    private HashMap<Integer, Integer> mapCombos; //Almacenamos los distintos index selecccionados en las distintas filas
    
    public JComboBoxCellEditor(JComboBox jcb) {
        super(jcb);
        this.mapCombos = new HashMap<Integer, Integer>();
    }

    public void setSelectedIndex(int row, int index) {
        this.mapCombos.put(row, index);
    }
    
    public Integer getSelectedIndex(int row) {
        Integer res = this.mapCombos.get(row);
        
        if (res == null)
            res = -1;
        
        return res;
    }
    
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        Component res = super.getTableCellEditorComponent(table, value, isSelected, row, column);
        
        ((JComboBox) res).setSelectedIndex(getSelectedIndex(row));
        
        return res;
    }
}
