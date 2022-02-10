package dominio;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Unknown
 */
public class RendererInqPaga extends DefaultTableCellRenderer{

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        
        if(table.getValueAt(row, 1).equals("ALQUILER ABONADO")){ 
            setBackground(Color.green);
            setForeground(Color.blue);//color de letra
        }else{
            setBackground(Color.red);
            setForeground(Color.white);//color de letra
        }
        
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
