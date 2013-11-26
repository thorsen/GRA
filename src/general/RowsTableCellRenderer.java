/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package general;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Victor Fernandez
 */
public class RowsTableCellRenderer extends DefaultTableCellRenderer {
    private Integer[] rows;
    private Color[] colorsBG;
    private Color[] colorsFG;
    
    public void addColorBG(Color color) {
        int nColors = colorsBG != null ? colorsBG.length : 0;
        Color[] colorsAux = new Color[nColors + 1];
        
        for (int i = 0; i < nColors; i++) {
            colorsAux[i] = colorsBG[i];
        }
        
        colorsAux[nColors] = color;
        
        colorsBG = colorsAux;
    }
    
    public Color[] getColorsBG() {
        return colorsBG;
    }
    
    public void removeColorBG(int pos) {
        int nColors = colorsBG != null ? colorsBG.length : 0;
        if (pos >= 0 && pos < nColors) {
            Color[] colorsAux = new Color[nColors - 1];

            for (int i = 0; i < pos; i++) {
                colorsAux[i] = colorsBG[i];
            }
            
            for (int i = pos + 1; i < nColors; i++) {
                colorsAux[i - 1] = colorsBG[i];
            }

            colorsBG = colorsAux;
        }
    }

    public void setColorsBG(Color[] colors) {
        this.colorsBG = colors;
    }
    
    public void addColorFG(Color color) {
        int nColors = colorsFG != null ? colorsFG.length : 0;
        Color[] colorsAux = new Color[nColors + 1];
        
        for (int i = 0; i < nColors; i++) {
            colorsAux[i] = colorsFG[i];
        }
        
        colorsAux[nColors] = color;
        
        colorsFG = colorsAux;
    }
    
    public Color[] getColorsFG() {
        return colorsFG;
    }
    
    public void removeColorFG(int pos) {
        int nColors = colorsFG != null ? colorsFG.length : 0;
        if (pos >= 0 && pos < nColors) {
            Color[] colorsAux = new Color[nColors - 1];

            for (int i = 0; i < pos; i++) {
                colorsAux[i] = colorsFG[i];
            }
            
            for (int i = pos + 1; i < nColors; i++) {
                colorsAux[i - 1] = colorsFG[i];
            }

            colorsFG = colorsAux;
        }
    }

    public void setColorsFG(Color[] colors) {
        this.colorsFG = colors;
    }
    
    public void addRow(Integer row) {
        int nRows = rows != null ? rows.length : 0;
        Integer[] rowsAux = new Integer[nRows + 1];
        
        for (int i = 0; i < nRows; i++) {
            rowsAux[i] = rows[i];
        }
        
        rowsAux[nRows] = row;
        
        rows = rowsAux;
    }

    public Integer[] getRows() {
        return rows;
    }
    
    public void removeRow(int pos) {
        int nRows = rows != null ? rows.length : 0;
        if (pos >= 0 && pos < nRows) {
            Integer[] rowsAux = new Integer[nRows - 1];

            for (int i = 0; i < pos; i++) {
                rowsAux[i] = rows[i];
            }
            
            for (int i = pos + 1; i < nRows; i++) {
                rowsAux[i - 1] = rows[i];
            }

            rows = rowsAux;
        }
    }

    public void setRows(Integer[] rows) {
        this.rows = rows;
    }
    
    public void addColoredRow(Integer row, Color colorBG, Color colorFG) {
        addRow(row);
        addColorBG(colorBG);
        addColorFG(colorFG);
    }
    
    public void removeColoredRow(Integer row) {
        int pos = findRowPosition(row);
        
        removeRow(pos);
        removeColorBG(pos);
        removeColorFG(pos);
    }
    
    public int findRowPosition(Integer row) {
        int res = -1;
        int nRows = rows != null ? rows.length : 0;
        
        for (int i = 0; i < nRows; i++) {
            if (rows[i].equals(row)) {
                res = i;
                break;
            }
        }
        
        return res;
    }

    public RowsTableCellRenderer(Integer[] rows, Color[] colorsBG, Color[] colorsFG) {
        this.rows = rows;
        this.colorsBG = colorsBG;
        this.colorsFG = colorsFG;
    }
    
    public RowsTableCellRenderer() {
        this.rows = null;
        this.colorsBG = null;
        this.colorsFG = null;
    }
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
        int pos = findRowPosition(row);
        if (pos != -1) {
            c.setBackground(isSelected ? table.getSelectionBackground() : colorsBG[pos]);
            c.setForeground(isSelected ? table.getSelectionForeground() : colorsFG[pos]);
        } else {
            c.setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
            c.setForeground(isSelected ? table.getSelectionForeground() : table.getForeground());
        }
        
        return c;
    }
}
