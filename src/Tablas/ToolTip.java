/*
 * MiRender.java
 *
 * Created on 7 de noviembre de 2007, 10:44
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Tablas;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;


/**
 *
 * @author Ruth_Cordon
 */

public class ToolTip extends DefaultTableCellRenderer {
   @Override
   public Component getTableCellRendererComponent(JTable table,Object value,boolean isSelected,boolean hasFocus,int row,int column){
       super.getTableCellRendererComponent (table, value, isSelected, hasFocus, row, column);
       
       if (value!=null){
            setToolTipText(value.toString());
       }
       setHorizontalAlignment(SwingConstants.CENTER);
       return this;
    }   
}





