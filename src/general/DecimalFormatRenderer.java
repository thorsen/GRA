package general;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class DecimalFormatRenderer extends DefaultTableCellRenderer {
    //private static final DecimalFormat formatter = new DecimalFormat( "#.00" );
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        //value = formatter.format((Number)value);
        value = value != null ? value.toString() : null;
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column );
    }
}