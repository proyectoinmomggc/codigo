/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion.paneles;

import dominio.d_gastos_inq;
import dominio.d_inquilino;
import dominio.d_movimiento;
import java.awt.Dimension;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import static jdk.nashorn.internal.objects.NativeString.toUpperCase;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import persistencia.p_conexion;
import static persistencia.p_movimientos.devuelveanio;
import static persistencia.p_movimientos.devuelvemes;

/**
 *
 * @author Gonzalo
 */
public class listadoinqatrasados extends javax.swing.JDialog {

    DefaultTableModel mdltabinq = new DefaultTableModel();
    List<d_gastos_inq> listainquilinos = new ArrayList<d_gastos_inq>();

    /**
     * Creates new form listadosmora1
     */
    public listadoinqatrasados(JDialog parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        p_control c = p_control.getInstancia();
        tblinquilinos.setModel(mdltabinq);

        mdltabinq.addColumn("ID PROP");
        mdltabinq.addColumn("ID INQ");
        mdltabinq.addColumn("NOMBRE");
        mdltabinq.addColumn("SALDO");
        mdltabinq.addColumn("MESES DE ATRASO");
        
        c.alinear_contenido_columna_izquierda(tblinquilinos, 3);
        c.alinear_contenido_columna_izquierda(tblinquilinos, 4);
        cargarinquilinosmorosos();
    }

    void cargarinquilinosmorosos() {
        p_control con = p_control.getInstancia();
        d_inquilino inq = new d_inquilino();
        d_gastos_inq inq1 = null;
        d_movimiento mov = new d_movimiento();
        Float saldototal = 0f;
        Float saldoinq = 0f;
        Integer mesesatraso = 0;
        Vector v;
        String nombre;

        try {
            //listainquilinos = inq.listarinquilinos();
            listainquilinos = inq.listarinquilinosmorosos();
            for (d_gastos_inq aux : listainquilinos) {
                
                inq1 = new d_gastos_inq();
                v = new Vector();
                inq1.setProp_id(aux.getProp_id());
                inq1.setInq_casa(aux.getInq_casa());
                inq1.setAqp(aux.getAqp());
                inq1.setImporte(aux.getImporte());
                nombre = inq.buscarnombreinquilino(inq1.getProp_id(), inq1.getInq_casa());
                //con.actualizar_deuda_inq(inq1);

                v.add(inq1.getProp_id());
                v.add(inq1.getInq_casa());
                v.add(nombre);
                v.add(con.mostrarnumero(inq1.getImporte()));
                v.add(inq1.getAqp());
                saldototal += inq1.getImporte();
                mdltabinq.addRow(v);
                //}
            }
            //JOptionPane.showMessageDialog(null, toUpperCase(saldototal), "ERROR", JOptionPane.ERROR_MESSAGE);
            lblsaldo.setText(con.mostrarnumero(saldototal));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, toUpperCase(ex.getMessage()), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
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
        tblinquilinos = tblinquilinos = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        jLabel1 = new javax.swing.JLabel();
        lblsaldo = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("INQUILINOS ATRASADOS");
        setModalityType(null);
        getContentPane().setLayout(null);

        tblinquilinos.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        tblinquilinos.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblinquilinos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblinquilinosMouseClicked(evt);
            }
        });
        tblinquilinos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblinquilinosKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(tblinquilinos);
        if (tblinquilinos.getColumnModel().getColumnCount() > 0) {
            tblinquilinos.getColumnModel().getColumn(0).setMaxWidth(30);
            tblinquilinos.getColumnModel().getColumn(0).setHeaderValue("Title 1");
            tblinquilinos.getColumnModel().getColumn(1).setMaxWidth(30);
            tblinquilinos.getColumnModel().getColumn(1).setHeaderValue("Title 2");
            tblinquilinos.getColumnModel().getColumn(2).setPreferredWidth(60);
            tblinquilinos.getColumnModel().getColumn(2).setHeaderValue("null");
        }

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(10, 20, 440, 350);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setText("SALDO TOTAL");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(10, 380, 95, 17);

        lblsaldo.setBackground(java.awt.Color.green);
        lblsaldo.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblsaldo.setOpaque(true);
        getContentPane().add(lblsaldo);
        lblsaldo.setBounds(10, 400, 400, 30);

        jButton1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jButton1.setText("EXPORTAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(10, 440, 120, 30);

        setBounds(0, 0, 499, 522);
    }// </editor-fold>//GEN-END:initComponents

    private void tblinquilinosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblinquilinosMouseClicked
        try {
            //selecciontabla();
            if (evt.getClickCount() == 2) {
                //HIZO DOBLE CLIC
                mostrardeuda(armarreporte());
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, toUpperCase(ex.getMessage()), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_tblinquilinosMouseClicked

    String armarreporte() throws Exception {
        p_control con = p_control.getInstancia();
        String texto = "";

        Integer prop_id, inq_casa;
        String nombre;
        int row = tblinquilinos.getSelectedRow(); //guarda el numero de fila que selecciona el usuario

        if (row != -1) {
            d_movimiento mov = new d_movimiento();
            List<d_movimiento> lista = new ArrayList<>();
            prop_id = ((Integer) tblinquilinos.getValueAt(row, 0));
            inq_casa = ((Integer) tblinquilinos.getValueAt(row, 1));
            nombre = ((String) tblinquilinos.getValueAt(row, 2));
            lista = mov.mesesatraso_listado(prop_id, inq_casa);
            if (lista.isEmpty()) {
                return "";
            }
            texto = prop_id + " - " + inq_casa + " | " + nombre +"\n";
            texto = texto + "---\n";
            Float saldoinq = mov.totalmesesatraso(prop_id, inq_casa);
            for (d_movimiento aux : lista) {
                texto = texto + "FECHA: " + aux.getMqp() + "/" + aux.getAqp()
                        + "\nDETALLE: " + aux.getDetalle() + "\nIMPORTE: " + con.mostrarnumero(aux.getEntrada()) + "\n\n";
                
            }
            texto = texto + "---\n";
            texto = texto + "TOTAL DEUDA ($): " + con.mostrarnumero(saldoinq);
        } else {
            return "";
        }
        return texto;
    }

    void mostrardeuda(String texto) throws Exception {
        if (texto.equals("")) {
            return;
        }
        JTextArea textArea = new JTextArea(texto); //AQUI VA TEXTO
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        scrollPane.setPreferredSize(new Dimension(300, 400));
        JOptionPane.showMessageDialog(null, scrollPane, "REPORTE DE DEUDA DE INQUILINO",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void tblinquilinosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblinquilinosKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tblinquilinosKeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            java.sql.Connection c;
            p_conexion conex = p_conexion.getInstancia();
            c = conex.crearconexion();
            Date fechaactual = new Date();
            Integer mqp = devuelvemes(fechaactual);
            Integer aqp = devuelveanio(fechaactual);
            String fecha = mqp.toString() + aqp.toString();

            String template = "report-inquilinosmorosos.jasper";
            JasperReport reporte = null;
            reporte = (JasperReport) JRLoader.loadObject(template);

            Map param = new HashMap();
            param.put("fecha", fecha);
            JasperPrint jasperprint = JasperFillManager.fillReport(reporte, param, c);
            //donde dice 'null' va el parametro, si es utilizado
            //this.setVisible(false);
            JasperViewer visor = new JasperViewer(jasperprint, false);
            visor.setTitle("Lista de inquilinos con mora");
            visor.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, toUpperCase(e), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    Integer devuelvemes(Date fecha) {
        String formato = "MM";
        SimpleDateFormat dateFormat = new SimpleDateFormat(formato);
        return Integer.parseInt(dateFormat.format(fecha));
    }

    Integer devuelveanio(Date fecha) {
        String formato = "yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(formato);
        return Integer.parseInt(dateFormat.format(fecha));
    }

    void selecciontabla() throws Exception {
        p_fichainquilino fic = null;
        p_control con = p_control.getInstancia();

        int row = tblinquilinos.getSelectedRow(); //guarda el numero de fila que selecciona el usuario

        if (row != -1) {
            d_inquilino inq = new d_inquilino();
            //datoseleccionado += tblmodelos.getValueAt(row, 2); //guarda el valor que hay en la columna idoperario
            //idpieza = Integer.parseInt(datoseleccionado); //convierte a Integer
            inq.setProp_id((Integer) tblinquilinos.getValueAt(row, 0));
            inq.setInq_casa((Integer) tblinquilinos.getValueAt(row, 1));
            inq.setInq_saldo(con.guardarnumero((tblinquilinos.getValueAt(row, 3).toString())));
            con.inq = inq;
            fic = new p_fichainquilino(this, true);
            fic.setVisible(true);
            //con.cargarpropietario(inq);
            //notificarseleccion(); 
        } else {
            JOptionPane.showMessageDialog(null, toUpperCase("debe seleccionar una fila"), "ERROR", JOptionPane.ERROR_MESSAGE);
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
            java.util.logging.Logger.getLogger(listadoinqatrasados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(listadoinqatrasados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(listadoinqatrasados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(listadoinqatrasados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
                listadoinqatrasados dialog = new listadoinqatrasados(new javax.swing.JDialog(), true);
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
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblsaldo;
    private javax.swing.JTable tblinquilinos;
    // End of variables declaration//GEN-END:variables
}