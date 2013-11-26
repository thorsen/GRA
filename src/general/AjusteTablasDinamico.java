package general;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.*;

public class AjusteTablasDinamico implements TableModelListener
{
    private JTable tabla;
    private int espaciado;

    private Map<TableColumn, Integer> columnSizes = new HashMap<TableColumn, Integer>();
    
    public static final int ESPACIADO_COL_PEQ = 6;
    public static final int ESPACIADO_COL_MED = 12;
    public static final int ESPACIADO_COL_GRAN = 18;
    public static final int TAM_MAX = 150;

    public AjusteTablasDinamico(JTable table) {
        this(table, ESPACIADO_COL_PEQ);
    }

    public AjusteTablasDinamico(JTable tabla, int espaciado) {
        int nCols;
        TableColumn tc;        
        
        this.tabla = tabla;
        this.espaciado = espaciado;

        this.tabla.getModel().addTableModelListener(this);

        this.tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        nCols = this.tabla.getColumnCount();
        for (int i = 0; i < nCols; i++) {
            tc = this.tabla.getColumnModel().getColumn(i);

            tc.setPreferredWidth(tc.getMinWidth());
        }
        
        this.tableChanged(null);
    }

    public void ajustarCols() {
        TableColumnModel tcm = this.tabla.getColumnModel();

        for (int i = 0; i < tcm.getColumnCount(); i++)
            ajustarCol(i);
    }

    public void ajustarCol(final int col) {
            TableColumn tc = this.tabla.getColumnModel().getColumn(col);

            if (!tc.getResizable()) return;

            int anchoCabecera = getAnchoCabecera(col);
            int anchoDatos = getAnchoDatos(col);

            actualizarAnchoCol(col, Math.min(Math.max(anchoCabecera, anchoDatos), TAM_MAX));
    }

    private int getAnchoCabecera(int col) {
        TableColumn tc = this.tabla.getColumnModel().getColumn(col);
        Object value = tc.getHeaderValue();
        TableCellRenderer renderer = tc.getHeaderRenderer();

        if (renderer == null)
            renderer = this.tabla.getTableHeader().getDefaultRenderer();

        Component c = renderer.getTableCellRendererComponent(this.tabla, value, false, false, -1, col);
        return c.getMinimumSize().width;
    }

    private int getAnchoDatos(int col) {
        int anchoPref = 0;
        int anchoMax = this.tabla.getColumnModel().getColumn(col).getMaxWidth();

        for (int i = 0; i < this.tabla.getRowCount(); i++) {
            anchoPref = Math.max(anchoPref, getAnchoPreferido(i, col));

            if (anchoPref >= anchoMax)
                break;
        }

        return anchoPref;
    }

    private int getAnchoPreferido(int fila, int col) {
        TableCellRenderer tcr = this.tabla.getCellRenderer(fila, col);
        Component c = this.tabla.prepareRenderer(tcr, fila, col);
        int ancho = c.getPreferredSize().width + this.tabla.getIntercellSpacing().width;

        return ancho;
    }

    private void actualizarAnchoCol(int col, int ancho) {
        final TableColumn tc = this.tabla.getColumnModel().getColumn(col);

        if (!tc.getResizable()) return;

        ancho += this.espaciado;

        ancho = Math.max(ancho, tc.getPreferredWidth());

        columnSizes.put(tc, new Integer(tc.getWidth()));
        this.tabla.getTableHeader().setResizingColumn(tc);
        tc.setWidth(ancho);
    }
    
    public void maximizaSiEsPosible() {
        TableColumn tc;
        int anchoCols = 0;
        
        this.tabla.update(this.tabla.getGraphics());
        
        int nCols = this.tabla.getColumnCount();
        for (int i = 0; i < nCols; i++) {
            tc = this.tabla.getColumnModel().getColumn(i);
            anchoCols += tc.getWidth();
        }
        
        if (this.tabla.getWidth() > anchoCols) { //Podemos hacer las columnas más grandes
            int diff = this.tabla.getWidth() - anchoCols;
            int diffAcum = 0, ancho;
            
            for (int i = 0; i < nCols; i++) {
                tc = this.tabla.getColumnModel().getColumn(i);
                if (i != nCols - 1) { 
                    ancho = (int) (Math.round((i + 1) * diff / (nCols * 1.0)) - Math.round((i) * diff / (nCols * 1.0)));
                    diffAcum += ancho;
                } else 
                    ancho = diff - diffAcum;
                
                actualizarAnchoCol(i, tc.getWidth() + ancho - this.espaciado); //El espaciado ya está añadido
            }
        }
    }

    public void tableChanged(TableModelEvent e) {
        if (e != null && e.getType() == TableModelEvent.UPDATE)
                ajustarCol(this.tabla.convertColumnIndexToView(e.getColumn()));
        else
                ajustarCols();
    }
}