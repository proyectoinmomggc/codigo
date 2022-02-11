/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion.paneles;

import dominio.d_clave;
import dominio.d_configuracion;
import dominio.d_gastos_inq;
import dominio.d_movimiento;
import interfaces.observador_mov;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static jdk.nashorn.internal.objects.NativeString.toUpperCase;

/**
 *
 * @author Gonzalo
 */
public class p_listadosreintegrosconvenios extends javax.swing.JDialog implements observador_mov {

    DefaultTableModel mdltabnot = new DefaultTableModel();
    List<d_gastos_inq> listanotas = new ArrayList<d_gastos_inq>();

    /**
     * Creates new form p_listado_mov
     */
    public p_listadosreintegrosconvenios(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        p_control c = p_control.getInstancia();
        tbllistado.setModel(mdltabnot);

        mdltabnot.addColumn("PROP ID");
        mdltabnot.addColumn("INQ");
        mdltabnot.addColumn("IMPORTE");
        mdltabnot.addColumn("DETALLE");
        mdltabnot.addColumn("MQP");
        mdltabnot.addColumn("AQP");
        mdltabnot.addColumn("APLICADO");
        
        c.alinear_contenido_columna_izquierda(tbllistado, 2);
        cargar();
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
        tbllistado = tbllistado = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        jButton4 = new javax.swing.JButton();
        txtclave = new javax.swing.JPasswordField();
        jLabel16 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtnroprin = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtcasainq = new javax.swing.JTextField();
        btnbuscar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("LISTADO CONVENIOS - REINTEGROS");
        getContentPane().setLayout(null);

        tbllistado.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        tbllistado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbllistadoMouseClicked(evt);
            }
        });
        tbllistado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbllistadoKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(tbllistado);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(10, 80, 550, 135);

        jButton4.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jButton4.setText("ELIMINAR");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4);
        jButton4.setBounds(570, 80, 120, 30);
        getContentPane().add(txtclave);
        txtclave.setBounds(10, 240, 138, 30);

        jLabel16.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel16.setText("CLAVE");
        getContentPane().add(jLabel16);
        jLabel16.setBounds(10, 220, 70, 17);

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setText("ID PROP");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(10, 20, 57, 17);

        txtnroprin.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        txtnroprin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtnroprinKeyPressed(evt);
            }
        });
        getContentPane().add(txtnroprin);
        txtnroprin.setBounds(10, 40, 138, 30);

        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setText("ID INQ");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(160, 20, 43, 17);

        txtcasainq.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        txtcasainq.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtcasainqKeyPressed(evt);
            }
        });
        getContentPane().add(txtcasainq);
        txtcasainq.setBounds(160, 40, 138, 30);

        btnbuscar.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        btnbuscar.setText("FILTRAR");
        btnbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscarActionPerformed(evt);
            }
        });
        btnbuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnbuscarKeyPressed(evt);
            }
        });
        getContentPane().add(btnbuscar);
        btnbuscar.setBounds(310, 40, 120, 30);

        setBounds(0, 0, 719, 319);
    }// </editor-fold>//GEN-END:initComponents

    private void tbllistadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbllistadoMouseClicked

    }//GEN-LAST:event_tbllistadoMouseClicked

    private void tbllistadoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbllistadoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbllistadoKeyPressed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        p_control con = p_control.getInstancia();
        d_gastos_inq gas = new d_gastos_inq();
        d_movimiento mov = new d_movimiento();
        
        try {
            gas = selecciontabla();
            if (gas != null) {
                controlarclave();
                if (estaaplicado()) {
                    throw new Exception("no se pueden eliminar convenios/reintegros ya aplicados");
                }
                int ax = JOptionPane.showConfirmDialog(null,
                        "¿DESEA ELIMINAR LOS DATOS?", "CONFIRMACION", JOptionPane.YES_NO_CANCEL_OPTION);
                if (ax == JOptionPane.YES_OPTION) {
                    gas.eliminarconvenioreintegro(gas);
                    con.escribirfichero("INQ - se elimina reint conv a cuenta id prop: " + gas.getProp_id() + " -- id inq: " + gas.getInq_casa()+" -- mqp: " + gas.getMqp() + " -- " + "aqp: " + gas.getAqp() + " -- " + "detalle: " + gas.getDetalle() + " -- " + "importe: " + con.mostrarnumero(gas.getImporte())+ " -- estado: " +(gas.getEstado()));
                    JOptionPane.showMessageDialog(this, gas.getDetalle() + " ELIMINADO CORRECTAMENTE", "AVISO", JOptionPane.INFORMATION_MESSAGE);
                    cargar();
                    actualizar_mov();
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, toUpperCase(ex.getMessage()), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    Boolean estaaplicado() {
        int row = tbllistado.getSelectedRow();
        if (((String) tbllistado.getValueAt(row, 6)).equals("SI")) {
            return true;
        }
        return false;
    }

    private void txtnroprinKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnroprinKeyPressed
        try {
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                funbuscarconfiltro();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, toUpperCase(ex.getMessage()), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_txtnroprinKeyPressed

    private void txtcasainqKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcasainqKeyPressed
        try {
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                funbuscarconfiltro();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, toUpperCase(ex.getMessage()), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_txtcasainqKeyPressed

    private void btnbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarActionPerformed
        try {
            funbuscarconfiltro();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, toUpperCase(ex.getMessage()), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnbuscarActionPerformed

    void funbuscarconfiltro() throws Exception {
        p_control con = p_control.getInstancia();
        d_gastos_inq gas = new d_gastos_inq();
        d_gastos_inq gas1 = null;
        Vector v;

        try {
            cleartable();
            if (txtnroprin.getText().equals("") || txtcasainq.getText().equals("")) {
                cargar();
                return;
            }
            int prop_id = Integer.parseInt(txtnroprin.getText());
            int inq_casa = Integer.parseInt(txtcasainq.getText());
            listanotas = gas.listarconveniosreintegrosporinq(prop_id, inq_casa);
            for (d_gastos_inq aux : listanotas) {
                gas1 = new d_gastos_inq();
                v = new Vector();
                gas1.setProp_id(aux.getProp_id());
                gas1.setInq_casa(aux.getInq_casa());
                gas1.setImporte(aux.getImporte());
                gas1.setDetalle(aux.getDetalle());
                gas1.setMqp(aux.getMqp());
                gas1.setAqp(aux.getAqp());
                gas1.setEstado(aux.getEstado());
                v.add(gas1.getProp_id());
                v.add(gas1.getInq_casa());
                v.add(con.mostrarnumero(gas1.getImporte()));
                v.add(gas1.getDetalle());
                v.add(gas1.getMqp());
                v.add(gas1.getAqp());
                if (gas1.getEstado() == 3) {
                    v.add("SI");
                } else {
                    v.add("NO");
                }
                mdltabnot.addRow(v);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, toUpperCase(ex.getMessage()), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnbuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnbuscarKeyPressed
        try {
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                funbuscarconfiltro();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, toUpperCase(ex.getMessage()), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnbuscarKeyPressed

    void buscarconvenioreintegropago(String detalle, d_movimiento mov1) throws Exception {
        d_movimiento mov = new d_movimiento();

        mov1.setDetalle(detalle);
        mov = mov.buscarconvenioreintegropago(mov1);
        if (mov != null) {
            throw new Exception(detalle + " ya fue utilizado en una cobranza, primero debe borrar el movimiento correspondiente");
        }
    }

    void controlarclave() throws Exception {
        d_clave cla = new d_clave();
        d_configuracion conf = new d_configuracion();

        conf = conf.buscarconfiguracion();

        if (conf.getClave().equals("NO")) {
            return;
        }
        cla = cla.buscarclave();

        if (!txtclave.getText().equals(cla.getClave())) {
            throw new Exception("clave incorrecta");
        }
    }

    void cargar() {
        p_control con = p_control.getInstancia();
        d_gastos_inq gas = new d_gastos_inq();
        d_gastos_inq gas1 = null;
        Vector v;

        try {
            cleartable();
            listanotas = gas.listarconveniosreintegros();
            for (d_gastos_inq aux : listanotas) {
                gas1 = new d_gastos_inq();
                v = new Vector();
                gas1.setProp_id(aux.getProp_id());
                gas1.setInq_casa(aux.getInq_casa());
                gas1.setImporte(aux.getImporte());
                gas1.setDetalle(aux.getDetalle());
                gas1.setMqp(aux.getMqp());
                gas1.setAqp(aux.getAqp());
                gas1.setEstado(aux.getEstado());
                v.add(gas1.getProp_id());
                v.add(gas1.getInq_casa());
                v.add(con.mostrarnumero(gas1.getImporte()));
                v.add(gas1.getDetalle());
                v.add(gas1.getMqp());
                v.add(gas1.getAqp());
                if (gas1.getEstado() == 3) {
                    v.add("SI");
                } else {
                    v.add("NO");
                }
                mdltabnot.addRow(v);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, toUpperCase(ex.getMessage()), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    Integer devuelvemes(Integer mes, Date fecha) {
        Integer mesres = 0;

        if (mes == 0) {
            SimpleDateFormat formato = new SimpleDateFormat("M");
            String fechastr = formato.format(fecha);
            mesres = Integer.parseInt(fechastr);
        } else {
            mesres = mes;
        }

        return mesres;
    }

    Integer devuelveanio(Integer anio, Date fecha) {
        Integer aniores = 0;

        if (anio == 0) {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy");
            String fechastr = formato.format(fecha);
            aniores = Integer.parseInt(fechastr);
        } else {
            aniores = anio;
        }

        return aniores;
    }

    String parsefechastring(java.util.Date fecha) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String fechastr = formato.format(fecha);

        return fechastr;
    }

    Date parsefechadate(Date fecha) throws Exception {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaDate = null;

        try {
            String nuevafecha = formato.format(fecha);
            fechaDate = formato.parse(nuevafecha);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return fechaDate;
    }

    d_gastos_inq selecciontabla() throws Exception {
        p_control con = p_control.getInstancia();
        int id = -1;
        d_gastos_inq gas = null;

        int row = tbllistado.getSelectedRow(); //guarda el numero de fila que selecciona el usuario

        if (row != -1) {
            gas = new d_gastos_inq();
            id = (int) tbllistado.getValueAt(row, 0);
            gas.setProp_id(id);
            gas.setInq_casa((Integer) tbllistado.getValueAt(row, 1));
            gas.setImporte(con.guardarnumero(((String) tbllistado.getValueAt(row, 2))));
            gas.setDetalle((String) tbllistado.getValueAt(row, 3));
            gas.setMqp((Integer) tbllistado.getValueAt(row, 4));
            gas.setAqp((Integer) tbllistado.getValueAt(row, 5));
            if (tbllistado.getValueAt(row, 6).equals("SI")) {
                gas.setEstado(3);
            } else {
                gas.setEstado(2);
            }
            gas = gas.buscarconvenioreintegrocompleto(gas);
        } else {
            JOptionPane.showMessageDialog(null, toUpperCase("debe seleccionar una fila"), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return gas;
    }

    void cleartable() {
        for (int i = 0; i < tbllistado.getRowCount(); i++) {
            mdltabnot.removeRow(i);
            i -= 1;
        }
        listanotas = null;
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
            java.util.logging.Logger.getLogger(p_listadosreintegrosconvenios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(p_listadosreintegrosconvenios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(p_listadosreintegrosconvenios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(p_listadosreintegrosconvenios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                p_listadosreintegrosconvenios dialog = new p_listadosreintegrosconvenios(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnbuscar;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tbllistado;
    private javax.swing.JTextField txtcasainq;
    private javax.swing.JPasswordField txtclave;
    private javax.swing.JTextField txtnroprin;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actualizar_mov() {
        p_control con = p_control.getInstancia();

        con.actualizar_mov();
    }

}