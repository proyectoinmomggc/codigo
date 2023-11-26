/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import dominio.d_clave;
import dominio.d_configuracion;
import dominio.d_parametro;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static jdk.nashorn.internal.objects.NativeString.toUpperCase;
import presentacion.paneles.p_control;

/**
 *
 * @author Gonzalo
 */
public class p_parametros extends javax.swing.JFrame {

    DefaultTableModel mdltabpar = new DefaultTableModel();
    List<d_parametro> listaparametros = new ArrayList<d_parametro>();
    d_parametro par = null;

    /**
     * Creates new form otros
     */
    public p_parametros() {
        initComponents();
        setLocationRelativeTo(null);
        p_control c = p_control.getInstancia();
        tblparametros.setModel(mdltabpar);

        mdltabpar.addColumn("FECHA");
        mdltabpar.addColumn("IVA");
        mdltabpar.addColumn("PORCENTAJE AUMENTO");
        mdltabpar.addColumn("IRPF");
        
        c.alinear_contenido_columna_izquierda(tblparametros, 1);
        c.alinear_contenido_columna_izquierda(tblparametros, 2);
        c.alinear_contenido_columna_izquierda(tblparametros, 3);
        //cargarfecha();
        cargarparametros();
    }

    void cleartable() {
        for (int i = 0; i < tblparametros.getRowCount(); i++) {
            mdltabpar.removeRow(i);
            i -= 1;
        }
    }

    String cargarfecha(Date fecha) {
        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy", new Locale("es_ES"));
        return (formateador.format(fecha));
    }

    void cargarparametros() {
        p_control con = p_control.getInstancia();
        d_parametro par = new d_parametro();
        d_parametro par1 = null;
        Vector v;

        try {
            cleartable();
            listaparametros = par.listarparametros();
            for (d_parametro aux : listaparametros) {
                par1 = new d_parametro();
                v = new Vector();
                par1.setFecha(aux.getFecha());
                par1.setIva(aux.getIva());
                par1.setProcaumento(aux.getProcaumento());
                par1.setIrpf(aux.getIrpf());
                v.add(cargarfecha(par1.getFecha()));
                v.add(par1.getIva());
                v.add(con.mostrarnumero(par1.getProcaumento()));
                v.add(con.mostrarnumero(par1.getIrpf()));
                mdltabpar.addRow(v);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, toUpperCase(ex.getMessage()), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    void selecciontabla() throws Exception {
        //p_fichapropietario fic = null;
        p_control con = p_control.getInstancia();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date nuevafecha = null;
        int row = tblparametros.getSelectedRow(); //guarda el numero de fila que selecciona el usuario

        if (row != -1) {
            par = new d_parametro();
            //datoseleccionado += tblmodelos.getValueAt(row, 2); //guarda el valor que hay en la columna idoperario
            //idpieza = Integer.parseInt(datoseleccionado); //convierte a Integer
            String fecha = "";
            fecha = (String) tblparametros.getValueAt(row, 0);
            nuevafecha = formatter.parse(fecha);
            par.setFecha(parsefechadate(nuevafecha));
            par.setIva((Integer) tblparametros.getValueAt(row, 1));
            par.setProcaumento(con.guardarnumero(((String) tblparametros.getValueAt(row, 2))));
            par.setIrpf(con.guardarnumero(((String) tblparametros.getValueAt(row, 3))));
            cargarparametro(par);
            //con.cargarpropietario(pro);
            //notificarseleccion(); 
        } else {
            JOptionPane.showMessageDialog(null, toUpperCase("debe seleccionar una fila"), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
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

    void cargarparametro(d_parametro par) throws Exception {
        p_control con = p_control.getInstancia();
        if (par != null) {
            txtiva.setText(par.getIva().toString());
            txtprocaumento.setText(con.mostrarnumero(par.getProcaumento()));
            txtirpf.setText(con.mostrarnumero(par.getIrpf()));
            jdcfecha.setDate(par.getFecha());
        }
    }

    String parsefechastring(java.util.Date fecha) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String fechastr = formato.format(fecha);

        return fechastr;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtiva = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtprocaumento = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtirpf = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtclave = new javax.swing.JPasswordField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblparametros = tblparametros = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        jdcfecha = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("MANTENIMIENTO DE PARAMETROS");
        getContentPane().setLayout(null);

        jButton1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jButton1.setText("GUARDAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(430, 240, 120, 30);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setText("IVA");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(10, 220, 90, 17);

        txtiva.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        getContentPane().add(txtiva);
        txtiva.setBounds(10, 240, 138, 30);

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setText("PORC. AUMENTO");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(200, 220, 250, 17);

        txtprocaumento.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        getContentPane().add(txtprocaumento);
        txtprocaumento.setBounds(200, 240, 138, 30);

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setText("FECHA");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(200, 270, 200, 17);

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setText("IRPF");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(10, 270, 100, 17);

        txtirpf.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        getContentPane().add(txtirpf);
        txtirpf.setBounds(10, 290, 138, 30);

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setText("CLAVE");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(10, 320, 200, 17);

        txtclave.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        getContentPane().add(txtclave);
        txtclave.setBounds(10, 340, 138, 30);

        tblparametros.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        tblparametros.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblparametrosMouseClicked(evt);
            }
        });
        tblparametros.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblparametrosKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblparametrosKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tblparametrosKeyTyped(evt);
            }
        });
        jScrollPane2.setViewportView(tblparametros);
        if (tblparametros.getColumnModel().getColumnCount() > 0) {
            tblparametros.getColumnModel().getColumn(0).setHeaderValue("Title 1");
            tblparametros.getColumnModel().getColumn(1).setHeaderValue("Title 2");
            tblparametros.getColumnModel().getColumn(2).setHeaderValue("null");
        }

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(10, 20, 540, 190);

        jdcfecha.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        getContentPane().add(jdcfecha);
        jdcfecha.setBounds(200, 290, 138, 30);

        setBounds(0, 0, 582, 425);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        p_control con = p_control.getInstancia();
        Date fecha = null;
        d_parametro par1 = new d_parametro();

        try {
            if (par != null) {
                controlarclave();
                fecha = parsefechadate(jdcfecha.getDate());
                par.setFecha(fecha);
                controlardia(fecha);
                par.setIva(Integer.parseInt(txtiva.getText()));
                par.setProcaumento(con.guardarnumero((txtprocaumento.getText())));
                par.setIrpf(con.guardarnumero((txtirpf.getText())));

                par1 = par.buscarparametroporfecha(fecha);
                if (par1 == null) {
                    par.guardarparametro(par);
                    con.escribirfichero("PARAMETRO - se guarda, fecha: " + parsefechastring(par.getFecha())
                            + " -- iva: " + par.getIva() + " -- porc. aumento: " + con.mostrarnumero(par.getProcaumento())
                            + " -- irpf: " + con.mostrarnumero(par.getIrpf()));
                    JOptionPane.showMessageDialog(this, "PARAMETRO GUARDADO CORRECTAMENTE", "AVISO", JOptionPane.INFORMATION_MESSAGE);
                    cargarparametros();
                } else {
                    par.actualizarparametro(par);
                    con.escribirfichero("PARAMETRO - se actualiza, fecha: " + parsefechastring(par.getFecha())
                            + " -- iva: " + par.getIva() + " -- porc. aumento: " + con.mostrarnumero(par.getProcaumento())
                            + " -- irpf: " + con.mostrarnumero(par.getIrpf()));
                    JOptionPane.showMessageDialog(this, "PARAMETRO ACTUALIZADO CORRECTAMENTE", "AVISO", JOptionPane.INFORMATION_MESSAGE);
                    cargarparametros();
                }
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, toUpperCase(ex.getMessage()), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    void controlardia(Date fecha) throws Exception {
        SimpleDateFormat formato = new SimpleDateFormat("dd");
        String fechastr = formato.format(fecha);
        if (!fechastr.equals("01")) {
            throw new Exception("debe elegir siempre el dia 1 del mes");
        }

    }

    d_parametro buscarparametro(d_parametro par) throws Exception {
        par = par.buscarparametroporfecha(par.getFecha());
        return par;
    }

    private void tblparametrosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblparametrosMouseClicked
        try {
            selecciontabla();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, toUpperCase(ex.getMessage()), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_tblparametrosMouseClicked

    private void tblparametrosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblparametrosKeyPressed
        try {
            selecciontabla();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, toUpperCase(ex.getMessage()), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_tblparametrosKeyPressed

    private void tblparametrosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblparametrosKeyReleased
        try {
            selecciontabla();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, toUpperCase(ex.getMessage()), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_tblparametrosKeyReleased

    private void tblparametrosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblparametrosKeyTyped
        try {
            selecciontabla();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, toUpperCase(ex.getMessage()), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_tblparametrosKeyTyped

    void controlarclave() throws Exception {
        d_clave cla = new d_clave();
        d_configuracion conf = new d_configuracion();

        conf = conf.buscarconfiguracion();

        if (conf.getClave().equals("NO")) {
            return;
        }

        cla = cla.buscarclave();

        if (cla == null) {
            throw new Exception("no existe clave ingresada en el sistema");
        }

        if (!txtclave.getText().equals(cla.getClave())) {
            throw new Exception("clave incorrecta");
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
            java.util.logging.Logger.getLogger(p_parametros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(p_parametros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(p_parametros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(p_parametros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new p_parametros().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane2;
    private com.toedter.calendar.JDateChooser jdcfecha;
    private javax.swing.JTable tblparametros;
    private javax.swing.JPasswordField txtclave;
    private javax.swing.JTextField txtirpf;
    private javax.swing.JTextField txtiva;
    private javax.swing.JTextField txtprocaumento;
    // End of variables declaration//GEN-END:variables
}
