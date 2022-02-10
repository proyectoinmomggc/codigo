/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import dominio.d_movimiento;
import dominio.d_propietario;
import dominio.d_recibo;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import static jdk.nashorn.internal.objects.NativeString.toUpperCase;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import persistencia.p_conexion;
import presentacion.paneles.p_control;

/**
 *
 * @author MGiordano
 */
public class p_propretira extends javax.swing.JFrame {

    d_propietario prop = null;

    /**
     * Creates new form propretira
     */
    public p_propretira() {
        initComponents();
        setLocationRelativeTo(null);
        cargarfecha();
        //this.setMinimumSize(new Dimension(800, 600)); 
    }

    void cargarfecha() {
        jdcfecha.setDate(new Date());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtpropid = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtdetalle = new javax.swing.JTextField();
        txtsalida = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        lblnombre = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblsaldo = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cmbtipo = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jdcfecha = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("RETIRO DE PROPIETARIO");
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setText("ID PROP");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(10, 20, 57, 17);

        txtpropid.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        txtpropid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpropidActionPerformed(evt);
            }
        });
        txtpropid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtpropidKeyPressed(evt);
            }
        });
        getContentPane().add(txtpropid);
        txtpropid.setBounds(10, 40, 138, 30);

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setText("IMPORTE");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(200, 190, 64, 17);

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setText("DETALLE");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(200, 240, 110, 17);

        txtdetalle.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        getContentPane().add(txtdetalle);
        txtdetalle.setBounds(200, 260, 138, 80);

        txtsalida.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        getContentPane().add(txtsalida);
        txtsalida.setBounds(200, 210, 138, 30);

        jButton1.setText("BUSCAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(160, 40, 120, 30);

        jButton2.setText("CONFIRMA");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(440, 210, 120, 30);

        lblnombre.setBackground(java.awt.Color.green);
        lblnombre.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblnombre.setOpaque(true);
        getContentPane().add(lblnombre);
        lblnombre.setBounds(10, 90, 550, 40);

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setText("NOMBRE");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(10, 70, 63, 17);

        jLabel7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel7.setText("FECHA");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(10, 190, 47, 17);

        lblsaldo.setBackground(java.awt.Color.green);
        lblsaldo.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblsaldo.setOpaque(true);
        getContentPane().add(lblsaldo);
        lblsaldo.setBounds(10, 150, 550, 40);

        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setText("SALDO");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(10, 130, 47, 17);

        cmbtipo.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        cmbtipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "RETIRO", "RESGUARDO", "GIRO ABITAB", "GIRO REDPAGOS", "DEP.BROU", "DEP.BBVA", "DEP.SANTANDER", "DEP.ITAU", "DEP.SCOTIABANK", "OTROS (INGRESAR DETALLE)" }));
        cmbtipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbtipoActionPerformed(evt);
            }
        });
        getContentPane().add(cmbtipo);
        cmbtipo.setBounds(10, 260, 138, 30);

        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setText("TIPO");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(10, 240, 32, 17);

        jdcfecha.setDateFormatString("dd/MM/yyyy");
        jdcfecha.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        getContentPane().add(jdcfecha);
        jdcfecha.setBounds(10, 210, 138, 30);

        setBounds(0, 0, 590, 385);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        p_control con = p_control.getInstancia();
        Date fecha = null;
        d_movimiento mov = new d_movimiento();

        try {
            //controlar campos
            controlar_prop();
            mov.setProp_id(Integer.parseInt(txtpropid.getText()));
            mov.setDetalle(devuelvedetalle());
            mov.setSalida(con.guardarnumero((txtsalida.getText())));
            fecha = parsefechadate(jdcfecha.getDate());
            mov.setFecha(fecha);
            int ax = JOptionPane.showConfirmDialog(null,
                    "¿DESEA GUARDAR LOS DATOS?", "CONFIRMACION", JOptionPane.YES_NO_CANCEL_OPTION);
            if (ax == JOptionPane.YES_OPTION) {
                mov.setTipopago(devuelvetipopago());
                con.guardarprimermovimiento(prop);             
                mov.guardarmovimientopropretira(mov);
                
                con.escribirfichero("PROP - retira (salida), id prop: " + mov.getProp_id() + " -- " + "detalle: " + mov.getDetalle() + " -- " + "salida: " + con.mostrarnumero(mov.getSalida()) + " -- tipo de pago: " + mov.getTipopago() + " -- "
                        + "fecha: " + parsefechastring(mov.getFecha()));
                
                actualizarsaldoprop();
                JOptionPane.showMessageDialog(this, "MOVIMIENTO GUARDADO CORRECTAMENTE", "AVISO", JOptionPane.INFORMATION_MESSAGE);
                mostrarrecibo(mov);
                txtpropid.setText("");
                lblnombre.setText("-");
                lblsaldo.setText("-");
                txtdetalle.setText("");
                txtsalida.setText("");
                cargarfecha();
                prop = null;
                //int ax1 = JOptionPane.showConfirmDialog(null, "¿desea imprimir recibo?");
                //if (ax1 == JOptionPane.YES_OPTION) {
                //mostrar reporte factura
                //}
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, toUpperCase(ex.getMessage()), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    String parsefechastring(java.util.Date fecha) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String fechastr = formato.format(fecha);

        return fechastr;
    }
    
    String devuelvetipopago() throws Exception {
        int seleccion = JOptionPane.showOptionDialog(null,
                "MEDIO DE PAGO",
                "SELECCIONE UNA OPCIÓN",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null, // null para icono por defecto.
                new Object[]{"CAJA", "BANCO"}, // null para YES, NO y CANCEL
                "opcion 1");

        if (seleccion == -1) {
            throw new Exception("DEBE SELECCIONAR UNA OPCION DE PAGO");
        }

        if (seleccion == 0) {
            return "CAJA";
        }
        if (seleccion == 1) {
            return "BANCO";
        }
        return "";
    }
    
    void actualizarsaldoprop() {
        d_propietario pro1 = null;
        Float saldo = 0f;

        try {
            pro1 = new d_propietario();
            saldo = pro1.saldototalprop(prop.getProp_id());
            pro1.actualizarsaldoprop(prop.getProp_id(), saldo);
            prop.setProp_saldo(saldo);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, toUpperCase(ex.getMessage()), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    String devuelvedetalle() throws Exception {
        String detalle = "";
        if (cmbtipo.getSelectedItem().equals("OTROS (INGRESAR DETALLE)")) {
            if (txtdetalle.getText().equals("")) {
                throw new Exception("debe ingresar un detalle");
            }
            detalle = txtdetalle.getText();
        }
        if (!cmbtipo.getSelectedItem().equals("OTROS (INGRESAR DETALLE)")) {
            detalle = cmbtipo.getSelectedItem().toString();
        }
        return detalle;
    }

    private void txtpropidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpropidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtpropidActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        funcbuscar();
    }//GEN-LAST:event_jButton1ActionPerformed

    void funcbuscar() {
        p_control con = p_control.getInstancia();
        Float saldo = 0.0F;
        d_movimiento mov = new d_movimiento();
        Integer nroprin = -1;

        lblnombre.setText("-");
        lblsaldo.setText("-");
        try {
            controlar_prop();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, toUpperCase(ex.getMessage()), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    void controlar_prop() throws Exception {
        p_control con = p_control.getInstancia();
        Integer nroprin = -1;

        prop = new d_propietario();

        if (txtpropid.getText().equals("")) {
            lblnombre.setText("-");
            throw new Exception("debe ingresar un id de propietario");
        }

        nroprin = Integer.parseInt(txtpropid.getText());
        prop = prop.buscarpropietario(nroprin);
        if (prop != null) {
            con.guardarprimermovimiento(prop);
            lblnombre.setText(prop.getProp_nombre());
            actualizarsaldoprop();
            lblsaldo.setText(con.mostrarnumero(prop.getProp_saldo()));
            con.prop = null;
        } else {
            prop = null;
            limpiar_campos();
            throw new Exception("propietario no existe");
        }
    }
    
    void limpiar_campos(){
        lblnombre.setText("-");
        lblsaldo.setText("-");
        cargarfecha();
        txtsalida.setText("");
        txtdetalle.setText("");
    }
    
    Float buscarsaldoprop(Integer id) throws Exception {
        d_propietario pro1 = null;

        pro1 = new d_propietario();
        return pro1.saldototalprop(id);

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

    void mostrarrecibo(d_movimiento mov) throws Exception {
        if (mov == null) {
            return;
        }

        java.sql.Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();

        d_propietario prop = new d_propietario();
        prop = prop.buscarpropietario(mov.getProp_id());
        if (prop == null) {
            return;
        }
        String template = "recibopropietarioretira.jasper";
        JasperReport reporte = null;
        reporte = (JasperReport) JRLoader.loadObject(template);

        Map param = new HashMap();
        param.put("direccion", prop.getProp_direccion());
        param.put("movimiento", "salida");
        int id = mov.ultimoidprop(mov.getProp_id());
        mov = mov.buscarmovimiento(id);
        guardarrecibo(mov, prop.getProp_id() + " - " + prop.getProp_nombre());

        JasperPrint jasperprint = JasperFillManager.fillReport(reporte, param, c);
        //donde dice 'null' va el parametro, si es utilizado
        //this.setVisible(false);
        JasperViewer visor = new JasperViewer(jasperprint, false);
        visor.setTitle("Recibo");
        visor.setVisible(true);
    }

    void guardarrecibo(d_movimiento mov, String nombreprop) throws Exception {
        d_recibo rec = new d_recibo();

        rec.eliminarrecibo("salida");

        rec.setIdmovimiento(mov.getId());
        if (mov.getMqp() != 0 && mov.getAqp() != 0) {
            rec.setDetalle(mov.getDetalle() + ": " + mov.getMqp() + "/" + mov.getAqp());
        } else {
            rec.setDetalle(mov.getDetalle());
        }

        rec.setFecha(mov.getFecha());
        rec.setNombre_inq(nombreprop);
        rec.setImporte(mov.getSalida());
        rec.guardarrecibo(rec,"salida");
    }


    private void cmbtipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbtipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbtipoActionPerformed

    private void txtpropidKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpropidKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            funcbuscar();
        }
    }//GEN-LAST:event_txtpropidKeyPressed

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
            java.util.logging.Logger.getLogger(p_propretira.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(p_propretira.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(p_propretira.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(p_propretira.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new p_propretira().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmbtipo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private com.toedter.calendar.JDateChooser jdcfecha;
    private javax.swing.JLabel lblnombre;
    private javax.swing.JLabel lblsaldo;
    private javax.swing.JTextField txtdetalle;
    private javax.swing.JTextField txtpropid;
    private javax.swing.JTextField txtsalida;
    // End of variables declaration//GEN-END:variables
}
