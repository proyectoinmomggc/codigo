/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import dominio.d_propietario;
import interfaces.observador_prop;
import java.awt.event.KeyAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import presentacion.paneles.p_control;
import presentacion.paneles.p_fichapropietario;
import java.awt.event.KeyEvent;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;
import static jdk.nashorn.internal.objects.NativeString.toUpperCase;
import persistencia.p_conexion;

/**
 *
 * @author Gonzalo
 */
public class p_lstpropietarios extends javax.swing.JFrame implements observador_prop{

    DefaultTableModel mdltabpro = new DefaultTableModel();
    List<d_propietario> listapropietarios = new ArrayList<d_propietario>();
    TableRowSorter trs;
    /**
     * Creates new form p_lstpropietarios
     */
    public p_lstpropietarios() {
        initComponents();
        setLocationRelativeTo(null);

        tblpropietarios.setModel(mdltabpro);

        mdltabpro.addColumn("ID PROP");
        mdltabpro.addColumn("NOMBRE");
        mdltabpro.addColumn("DIRECCION");
        mdltabpro.addColumn("NRO CONTACTO");
        mdltabpro.addColumn("CI/RUT");
        mdltabpro.addColumn("OBSERVACIONES");

        cargarpropietarios();
        cargarobservador();
    }

    
    void cargarobservador() {
        p_control con = p_control.getInstancia();
        con.registrarobservador_prop(this);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        tblpropietarios = tblpropietarios = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        btnexportar = new javax.swing.JButton();
        cmbordenar = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtfiltro = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("LISTA DE PROPIETARIOS");
        getContentPane().setLayout(null);

        tblpropietarios.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        tblpropietarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblpropietariosMouseClicked(evt);
            }
        });
        tblpropietarios.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblpropietariosKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(tblpropietarios);
        if (tblpropietarios.getColumnModel().getColumnCount() > 0) {
            tblpropietarios.getColumnModel().getColumn(0).setHeaderValue("Title 1");
            tblpropietarios.getColumnModel().getColumn(1).setHeaderValue("Title 2");
            tblpropietarios.getColumnModel().getColumn(2).setHeaderValue("null");
        }

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(10, 80, 952, 410);

        btnexportar.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        btnexportar.setText("EXPORTAR");
        btnexportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnexportarActionPerformed(evt);
            }
        });
        getContentPane().add(btnexportar);
        btnexportar.setBounds(10, 500, 120, 30);

        cmbordenar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID", "NOMBRE", "DIRECCION" }));
        cmbordenar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbordenarKeyPressed(evt);
            }
        });
        getContentPane().add(cmbordenar);
        cmbordenar.setBounds(10, 40, 138, 30);

        jButton1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jButton1.setText("BUSCAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(840, 40, 120, 30);

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setText("ORDEN");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(10, 20, 50, 17);

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setText("FILTRO");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(170, 20, 260, 17);

        txtfiltro.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        txtfiltro.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                txtfiltroAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        txtfiltro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtfiltroActionPerformed(evt);
            }
        });
        txtfiltro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtfiltroKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtfiltroKeyTyped(evt);
            }
        });
        getContentPane().add(txtfiltro);
        txtfiltro.setBounds(170, 40, 270, 30);

        setBounds(0, 0, 1012, 580);
    }// </editor-fold>//GEN-END:initComponents

    private void btnexportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnexportarActionPerformed
        try {
            java.sql.Connection c;
            p_conexion conex = p_conexion.getInstancia();
            c = conex.crearconexion(); 

            //javax.swing.JOptionPane.showMessageDialog(null, "Conexion establecida");

            String template = "report-3.jasper";
            JasperReport reporte = null;
            reporte = (JasperReport) JRLoader.loadObject(template);

            //Map param = new HashMap();
            //param.put("id", 1);
            JasperPrint jasperprint = JasperFillManager.fillReport(reporte, null, c);
            //donde dice 'null' va el parametro, si es utilizado
            JasperViewer visor = new JasperViewer(jasperprint, false);
            visor.setTitle("Lista de Propietarios");
            visor.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, toUpperCase(e), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnexportarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        funcbuscar();
    }//GEN-LAST:event_jButton1ActionPerformed

    void funcbuscar(){
        d_propietario pro = new d_propietario();        // TODO add your handling coded_propietario pro = new d_propietario();
        d_propietario pro1 = null;
        Vector v;

        try {
            if (cmbordenar.getSelectedItem().equals("ID")) {
                listapropietarios = pro.listarpropietariosporid();
            }
            if (cmbordenar.getSelectedItem().equals("NOMBRE")) {
                listapropietarios = pro.listarpropietariospornombre();
            }
            if (cmbordenar.getSelectedItem().equals("DIRECCION")) {
                listapropietarios = pro.listarpropietariospordireccion();
            }
            cleartable();
            for (d_propietario aux : listapropietarios) {
                pro1 = new d_propietario();
                v = new Vector();
                pro1.setProp_id(aux.getProp_id());
                pro1.setProp_nombre(aux.getProp_nombre());
                pro1.setProp_direccion(aux.getProp_direccion());
                pro1.setProp_numcontacto(aux.getProp_numcontacto());
                pro1.setProp_cirut(aux.getProp_cirut());
                pro1.setProp_observaciones(aux.getProp_observaciones());
                v.add(pro1.getProp_id());
                v.add(pro1.getProp_nombre());
                v.add(pro1.getProp_direccion());
                v.add(pro1.getProp_numcontacto());
                v.add(pro1.getProp_cirut());
                v.add(pro1.getProp_observaciones());
                mdltabpro.addRow(v);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, toUpperCase(ex.getMessage()), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void tblpropietariosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblpropietariosKeyPressed
                       // TODO add your handling code here:
    }//GEN-LAST:event_tblpropietariosKeyPressed

    private void tblpropietariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblpropietariosMouseClicked
        try {
            if (evt.getClickCount() == 2) {
               selecciontabla(); 
            }            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, toUpperCase(ex.getMessage()), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_tblpropietariosMouseClicked

    private void cmbordenarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbordenarKeyPressed
        if (evt.getKeyCode()==KeyEvent.VK_ENTER){
            funcbuscar();
        } 
    }//GEN-LAST:event_cmbordenarKeyPressed

    private void txtfiltroAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_txtfiltroAncestorAdded
        txtfiltro.requestFocus();
    }//GEN-LAST:event_txtfiltroAncestorAdded

    private void txtfiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtfiltroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtfiltroActionPerformed

    private void txtfiltroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfiltroKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            funcbuscar();
        }
    }//GEN-LAST:event_txtfiltroKeyPressed

    private void txtfiltroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfiltroKeyTyped
        txtfiltro.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e); //To change body of generated methods, choose Tools | Templates.
                trs.setRowFilter(RowFilter.regexFilter("^"+txtfiltro.getText().toUpperCase(), 1));
            }
        });
        trs = new TableRowSorter(mdltabpro);
        tblpropietarios.setRowSorter(trs);
    }//GEN-LAST:event_txtfiltroKeyTyped

    void cleartable() {
        for (int i = 0; i < tblpropietarios.getRowCount(); i++) {
            mdltabpro.removeRow(i);
            i -= 1;
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(p_lstpropietarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(p_lstpropietarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(p_lstpropietarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(p_lstpropietarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new p_lstpropietarios().setVisible(true);
            }
        });
    }

    void cargarpropietarios() {
        d_propietario pro = new d_propietario();
        d_propietario pro1 = null;
        Vector v;

        try {
            cleartable();
            listapropietarios = pro.listarpropietarios();
            for (d_propietario aux : listapropietarios) {
                pro1 = new d_propietario();
                v = new Vector();
                pro1.setProp_id(aux.getProp_id());
                pro1.setProp_nombre(aux.getProp_nombre());
                pro1.setProp_direccion(aux.getProp_direccion());
                pro1.setProp_numcontacto(aux.getProp_numcontacto());
                pro1.setProp_cirut(aux.getProp_cirut());
                pro1.setProp_observaciones(aux.getProp_observaciones());
                v.add(pro1.getProp_id());
                v.add(pro1.getProp_nombre());
                v.add(pro1.getProp_direccion());
                v.add(pro1.getProp_numcontacto());
                v.add(pro1.getProp_cirut());
                v.add(pro1.getProp_observaciones());
                mdltabpro.addRow(v);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, toUpperCase(ex.getMessage()), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    void selecciontabla() throws Exception {
        p_fichapropietario fic = null;
        p_control con = p_control.getInstancia();

        int row = tblpropietarios.getSelectedRow(); //guarda el numero de fila que selecciona el usuario

        if (row != -1) {
            d_propietario pro = new d_propietario();
            //datoseleccionado += tblmodelos.getValueAt(row, 2); //guarda el valor que hay en la columna idoperario
            //idpieza = Integer.parseInt(datoseleccionado); //convierte a Integer
            pro.setProp_id((Integer) tblpropietarios.getValueAt(row, 0));
            pro.setProp_nombre((String) tblpropietarios.getValueAt(row, 1));
            pro.setProp_direccion((String) tblpropietarios.getValueAt(row, 2));
            pro.setProp_numcontacto((Integer) tblpropietarios.getValueAt(row, 3));
            pro.setProp_cirut((String) tblpropietarios.getValueAt(row, 4));
            pro.setProp_observaciones((String) tblpropietarios.getValueAt(row, 5));
            pro = pro.buscarpropietario(pro.getProp_id());
            con.prop = pro;
            fic = new p_fichapropietario(this, true);
            fic.setVisible(true);
            //con.cargarpropietario(pro);
            //notificarseleccion(); 
        } else {
            JOptionPane.showMessageDialog(null, toUpperCase("debe seleccionar una fila"), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnexportar;
    private javax.swing.JComboBox<String> cmbordenar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblpropietarios;
    private javax.swing.JTextField txtfiltro;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actualizar() {
        cargarpropietarios();
    }
}
