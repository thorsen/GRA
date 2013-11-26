package general;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableCellRenderer;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.Number;

/**
 *
 * @author Victor Fernandez
 */
public class JTableExport extends JTable {

    private JPopupMenu jpmPopup;
    private JMenuItem jmiExportar;
    private HashMap<Integer[], Color> celdasColor;

    public JTableExport() {
        super();

        //Creamos el listener para el click
        this.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableExportMouseClicked(evt);
            }
        });

        //Inicializamos el menú Popup
        this.jpmPopup = new JPopupMenu();
        this.jmiExportar = new JMenuItem();

        this.jmiExportar.setText("Exportar a Excel");

        this.jmiExportar.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                export(evt);
            }
        });

        this.jpmPopup.add(this.jmiExportar);
    }
    
    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int rowIndex, int colIndex) {
        // get the current row
        Component comp = super.prepareRenderer(renderer, rowIndex, colIndex);
        // even index, not selected
        
        Integer rowCol[] = new Integer[]{rowIndex, colIndex};
        
        if (this.celdasColor != null && this.celdasColor.containsKey(rowCol)) {
            comp.setBackground(this.celdasColor.get(rowCol));
        }
        return comp;
    }
    
    public void updateCeldasColor(int rowIndex, int colIndex, Color color, Boolean anadir) {
        Integer rowCol[] = new Integer[]{rowIndex, colIndex};
        
        if (anadir && color != null) {
            if (this.celdasColor == null)
                this.celdasColor = new HashMap<Integer[], Color>();
            
            this.celdasColor.put(rowCol, color);
        } else {
            if (this.celdasColor != null && this.celdasColor.containsKey(rowCol)) {
                this.celdasColor.remove(rowCol);
            }
        }
        
        this.update(this.getGraphics());
    }
    
    private void tableExportMouseClicked(java.awt.event.MouseEvent evt) {
        if (evt.getButton() == MouseEvent.BUTTON3 && !evt.isConsumed()) {
            evt.consume();

            this.jpmPopup.show(this, evt.getX(), evt.getY());
        }
    }

    public boolean export(java.awt.event.ActionEvent evt) {
        File file = null;
        String nombreHoja;
        boolean res = false;
        try {
            final JFileChooser jfc = new JFileChooser(Auxiliares.dameRutaDefecto(this));
            jfc.addChoosableFileFilter(new FileNameExtensionFilter("Excel", "xls", "xlsx"));

            int returnVal = jfc.showSaveDialog(this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                String name, ext;
                file = jfc.getSelectedFile();
                
                name = file.getName();
                ext = name.lastIndexOf(".") != -1 ? name.substring(name.lastIndexOf(".")) : "";
                        
                if (!file.exists() && !ext.toLowerCase().contains("xls"))
                    file = new File(file.getAbsolutePath() + ".xls");
            }

            if (file != null) {
                if (this.getToolTipText() != null) {
                    nombreHoja = this.getToolTipText();
                } else {
                    nombreHoja = "Hoja1";                //Nuestro flujo de salida para apuntar a donde vamos a escribir
                }
                DataOutputStream out = new DataOutputStream(new FileOutputStream(file));

                //Representa nuestro archivo en excel y necesita un OutputStream para saber donde va locoar los datos
                WritableWorkbook workBook = Workbook.createWorkbook(out);

                //Como excel tiene muchas hojas esta crea o toma la hoja
                //Coloca el nombre del "tab" y el indice del tab
                WritableSheet sheet = workBook.createSheet(nombreHoja, 0);

                int nRows = this.getRowCount();
                int nCols = this.getColumnCount();

                //Añadimos las cabeceras
                WritableCellFormat wcFormat = new WritableCellFormat();
                wcFormat.setBackground(Colour.GRAY_25);
                wcFormat.setAlignment(Alignment.CENTRE);
                
                String colName;

                for (int i = 0; i < nCols; i++) {
                    colName = this.getColumnName(i);
                    
                    colName = colName.replaceAll("<[^>]*>", "");
                    
                    sheet.addCell(new Label(i, 0, colName, wcFormat));
                }

                //Añadimos los valores de las celdas
                String objetoString;
                Double valor;
                for (int i = 0; i < nRows; i++) {
                    for (int j = 0; j < nCols; j++) {
                        Object objeto = this.getValueAt(i, j);

                        if (objeto == null) {
                            continue;                        
                        }
                        
                        objetoString = objeto.toString();
                        
                        //Blank, Boolean, jxl.write.biff.CellValue, DateTime, Formula, Label, Number
                        try {
                            valor = Double.parseDouble(objetoString);
                        } catch (NumberFormatException e) {
                            valor = null;
                        }
                                
                        if (valor != null) {
                            sheet.addCell(new Number(j, i + 1, valor));
                        } else {
                            //Tenemos que truncar el contenido porque Excel no admite más de 1023 caracteres por celda
                            if (objetoString.length() > 1023)
                                objetoString = "INCOMPLETO!!! " + objetoString.substring(0, 1023 - "INCOMPLETO!!! ".length());
                                
                            sheet.addCell(new Label(j, i + 1, objetoString));
                        }
                    }
                }
        
                //Ajustamos el ancho de celdas
                for (int i = 0; i < nCols; i++) {
                    sheet.setColumnView(i, ((Double) Math.floor(this.getColumnModel().getColumn(i).getWidth() / 5.85)).intValue());
                }
                
                workBook.write();

                //Cerramos el WritableWorkbook y DataOutputStream
                workBook.close();
                out.close();

                res = true;
            }
        } catch (IOException e) {
            MensajeApp.muestraError(null, e, "Error realizando exportación");
        } catch (WriteException e) {
            MensajeApp.muestraError(null, e, "Error realizando exportación");
        } catch (Exception e) {
            MensajeApp.muestraError(null, e, "Error realizando exportación");
        } finally {
            if (res) {
                MensajeApp.muestraInfo(null, "Exportación finalizada correctamente");
            }
            return res;
        }
    }
}
