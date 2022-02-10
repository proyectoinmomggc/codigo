/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion.paneles;

import dominio.d_cfe;
import dominio.d_clave;
import dominio.d_movimiento;
import dominio.d_parametroscfe;
import dominio.d_xml;
import interfaces.observador_documentos;
import interfaces.observador_mov;
import interfaces.observador_prop;
import java.awt.Dialog;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import static jdk.nashorn.internal.objects.NativeString.toUpperCase;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.datacontract.schemas._2004._07.sicfecontract.SICFERespuestaObtenerCFE;
import persistencia.p_conexion;

/**
 *
 * @author Gonzalo
 */
public class p_agregar_recibo extends javax.swing.JDialog implements observador_documentos {

    p_control con = p_control.getInstancia();
    d_cfe cfe;
    int id_documento;
    int id_generado = 0;
    String concepto, iva;

    /**
     * Creates new form p_fichainquilino
     */
    public p_agregar_recibo(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        cargar_id_documento();

        cargar();
    }

    void cargar_id_documento() {
        this.id_documento = con.id_documento;
    }

    void cargar() {
        try {
            String xml;
            cfe = datos_cfe();
            xml = obtenerCFEPorID_funcion_devuelvexml(cfe);
            txtid.setText(String.valueOf(id_documento));
            txtdgi.setText(cfe.getSerie() + " " + cfe.getNumero() + " | CREADO: " + devuelve_fecha_emision(cfe, xml));
            txtnombre.setText(devuelve_nombre(cfe, xml));
            txtci.setText(devuelve_ci(cfe, xml));
            txtdireccion.setText(devuelve_domicilio(cfe, xml));
            txtentrada.setText(devuelve_importe(cfe, xml));
            devuelve_concepto(cfe, xml);
            devuelve_iva(cfe, xml);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, toUpperCase(ex.getMessage()), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    d_cfe datos_cfe() throws Exception {
        d_cfe cfe = new d_cfe();

        cfe = cfe.buscarcfe(id_documento);
        if (cfe == null) {
            throw new Exception("no existe pdf solicitado");
        }
        return cfe;
    }

    String devuelve_fecha_emision(d_cfe cfe, String xml) throws Exception {
        d_xml dx = new d_xml();
        String fecha_emision = "";

        if (cfe.getTipo().equals(101)) {
            fecha_emision = dx.recibexmlfacturaydevuelvefechaemisionticket(xml);
        }
        if (cfe.getTipo().equals(111)) {
            fecha_emision = dx.recibexmlfacturaydevuelvefechaemisionfactura(xml);
        }
        Date fecha_d = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(fecha_emision);
        return parsefechaemitida(fecha_d);
    }

    String devuelve_nombre(d_cfe cfe, String xml) throws Exception {
        d_xml dx = new d_xml();
        return dx.recibexmlfacturaydevuelvenombrecliente(xml);
    }

    String devuelve_ci(d_cfe cfe, String xml) throws Exception {
        d_xml dx = new d_xml();
        return dx.recibexmlfacturaydevuelverutcliente(xml);
    }

    String devuelve_domicilio(d_cfe cfe, String xml) throws Exception {
        d_xml dx = new d_xml();
        return dx.recibexmlfacturaydevuelvedireccioncliente(xml);
    }

    String devuelve_importe(d_cfe cfe, String xml) throws Exception {
        d_xml dx = new d_xml();
        return con.mostrarnumero(Float.parseFloat(dx.recibexmlfacturaydevuelvetotalsiniva(xml))
                + (Float.parseFloat(dx.recibexmlfacturaydevuelveiva(xml))));
    }

    void devuelve_concepto(d_cfe cfe, String xml) throws Exception {
        d_xml dx = new d_xml();
        concepto = dx.recibexmlfacturaydevuelveconcepto(xml);
    }

    void devuelve_iva(d_cfe cfe, String xml) throws Exception {
        d_xml dx = new d_xml();
        iva = con.mostrarnumero(Float.parseFloat(dx.recibexmlfacturaydevuelveiva(xml)));
    }

    String parsefechaemitida(java.util.Date fecha) throws Exception {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String nuevafecha = "";
        //Date fechaDate = null;

        try {
            nuevafecha = formato.format(fecha);
            //fechaDate = formato.parse(nuevafecha);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return nuevafecha;
    }

    String obtenerCFEPorID_funcion_devuelvexml(d_cfe cfe) throws Exception {
        //d_cfe cfe = new d_cfe();
        d_parametroscfe pcfe = new d_parametroscfe();
        //buscar movimiento y parametroscfe que trae usuario, clave, tenant
        SICFERespuestaObtenerCFE obtenerVersion = new SICFERespuestaObtenerCFE();
        QName fooQNameSerie = new QName("http://schemas.datacontract.org/2004/07/SICFEContract", "Serie");
        QName fooQNameRucemisor = new QName("http://schemas.datacontract.org/2004/07/SICFEContract", "rucemisor");
        org.datacontract.schemas._2004._07.sicfecontract.IdCFE i = new org.datacontract.schemas._2004._07.sicfecontract.IdCFE();

        pcfe = pcfe.buscarparametroscfe();
        if (pcfe == null) {
            throw new Exception("debe ingresar parametros cfe: nombre, clave y tenant");
        }

        JAXBElement<String> serie = new JAXBElement<String>(fooQNameSerie, String.class, cfe.getSerie());
        JAXBElement<String> rucemisor = new JAXBElement<String>(fooQNameRucemisor, String.class, cfe.getRucemisor());
        /*
            JAXBElement<String> rucemisor = new JAXBElement<>(new QName(org.datacontract.schemas._2004._07.sicfecontract.IdCFE.class.getSimpleName()),
                String.class, "rucemisor");
            rucemisor.setValue("120196190011");
         */
        i.setRucemisor(rucemisor);
        i.setSerie(serie);
        i.setTipo(cfe.getTipo());
        i.setNumero(cfe.getNumero());
        i.setObservado(cfe.getObservado());
        obtenerVersion = obtenerCFEPorID(pcfe.getNomusuario(), pcfe.getClave(), pcfe.getTenant(), i.getTipo(), cfe.getSerie(), i.getNumero(), true);
        if (obtenerVersion.getCodigo() != 0) {
            throw new Exception("CFE solicitado no existe, o no fue ingresado");
        }
        return obtenerVersion.getXml().getValue();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btningresar = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtid = new javax.swing.JTextField();
        txtdgi = new javax.swing.JTextField();
        txtcomision = new javax.swing.JTextField();
        txtnombre = new javax.swing.JTextField();
        txtci = new javax.swing.JTextField();
        txtdireccion = new javax.swing.JTextField();
        txtentrada = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("INGRESO DE DOCUMENTO DGI AL DIARIO");
        getContentPane().setLayout(null);

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setText("NOMBRE ");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(10, 70, 120, 17);

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setForeground(java.awt.Color.red);
        jLabel5.setText("LUEGO DE INGRESADO SE PUEDE VER EN MOVIMIENTOS DIARIOS");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(10, 390, 500, 17);

        btningresar.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        btningresar.setText("INGRESAR");
        btningresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btningresarActionPerformed(evt);
            }
        });
        getContentPane().add(btningresar);
        btningresar.setBounds(490, 340, 120, 30);

        jLabel16.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel16.setText("ID");
        getContentPane().add(jLabel16);
        jLabel16.setBounds(10, 20, 140, 17);

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setText("CI");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(10, 120, 120, 17);

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setText("DIRECCION");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(10, 170, 120, 17);

        jLabel7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel7.setText("ENTRADA");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(10, 220, 120, 17);

        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setText("COMISION");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(10, 270, 120, 17);

        jLabel17.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel17.setText("DGI");
        getContentPane().add(jLabel17);
        jLabel17.setBounds(150, 20, 140, 17);

        txtid.setEditable(false);
        txtid.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtid.setPreferredSize(new java.awt.Dimension(6, 40));
        txtid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtidKeyTyped(evt);
            }
        });
        getContentPane().add(txtid);
        txtid.setBounds(10, 40, 130, 30);

        txtdgi.setEditable(false);
        txtdgi.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtdgi.setPreferredSize(new java.awt.Dimension(6, 40));
        txtdgi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtdgiKeyTyped(evt);
            }
        });
        getContentPane().add(txtdgi);
        txtdgi.setBounds(150, 40, 460, 30);

        txtcomision.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        txtcomision.setPreferredSize(new java.awt.Dimension(6, 40));
        txtcomision.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcomisionKeyTyped(evt);
            }
        });
        getContentPane().add(txtcomision);
        txtcomision.setBounds(10, 290, 600, 30);

        txtnombre.setEditable(false);
        txtnombre.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtnombre.setPreferredSize(new java.awt.Dimension(6, 40));
        txtnombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtnombreKeyTyped(evt);
            }
        });
        getContentPane().add(txtnombre);
        txtnombre.setBounds(10, 90, 600, 30);

        txtci.setEditable(false);
        txtci.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtci.setPreferredSize(new java.awt.Dimension(6, 40));
        txtci.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtciKeyTyped(evt);
            }
        });
        getContentPane().add(txtci);
        txtci.setBounds(10, 140, 600, 30);

        txtdireccion.setEditable(false);
        txtdireccion.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtdireccion.setPreferredSize(new java.awt.Dimension(6, 40));
        txtdireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtdireccionKeyTyped(evt);
            }
        });
        getContentPane().add(txtdireccion);
        txtdireccion.setBounds(10, 190, 600, 30);

        txtentrada.setEditable(false);
        txtentrada.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtentrada.setPreferredSize(new java.awt.Dimension(6, 40));
        txtentrada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtentradaKeyTyped(evt);
            }
        });
        getContentPane().add(txtentrada);
        txtentrada.setBounds(10, 240, 600, 30);

        setBounds(0, 0, 638, 460);
    }// </editor-fold>//GEN-END:initComponents

    private void btningresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btningresarActionPerformed
        d_movimiento mov = new d_movimiento();

        try {
            Date fecha = fechaactual();
            mov.setProp_id(0);
            mov.setInq_casa(0);
            mov.setMqp(0);
            mov.setAqp(0);
            mov.setDetalle(concepto);
            mov.setEntrada(con.guardarnumero(txtentrada.getText()));
            mov.setSalida(0f);
            if ((Float.parseFloat(txtcomision.getText()) < 0) || txtcomision.getText().equals("")) {
                throw new Exception("la comisión no puede ser menor a 0, o vacía");
            }
            mov.setComision(con.guardarnumero(txtcomision.getText()));
            mov.setIva(con.guardarnumero(iva));
            mov.setIrpf(0f);
            mov.setFecha(fecha);
            mov.setIrpftipo("");
            mov.setTipo(cfe.getTipo() + "_" + cfe.getSerie() + "_" + cfe.getNumero());//ACA SE AGREGA Nº Y SERIE E-TICKET
            mov.setTipopago("");
            //BUSCAR id_generado ANTES DE GUARDAR
            if (mov.tiene_recibo(id_generado)) {
                throw new Exception("YA FUE INGRESADO EL RECIBO, ID: " + id_generado);
            }
            id_generado = mov.guardarmovimientoe_ticket(mov);
            JOptionPane.showMessageDialog(this, "RECIBO GENERADO CORRECTAMENTE, MOVIMIENTO Nº: " + id_generado, "AVISO", JOptionPane.INFORMATION_MESSAGE);            
            txtid.setText(String.valueOf(id_generado));
            actualizar_id_cfe(mov.getComision());
            actualizar();
            generarrrecibo(id_generado, txtnombre.getText(), txtci.getText(), txtdireccion.getText(), concepto, txtentrada.getText());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, toUpperCase(ex.getMessage()), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btningresarActionPerformed

    void generarrrecibo(int id, String nombre, String ci, String direccion, String concepto, String importe) throws Exception {
        java.sql.Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();

        String template = "recibo-eticket.jasper";
        JasperReport reporte = null;
        reporte = (JasperReport) JRLoader.loadObject(template);

        Map param = new HashMap();

        param.put("fecha", parsefechadate_recibo(new Date()));
        param.put("nombre", nombre);
        param.put("ci", ci);
        param.put("direccion", direccion);
        param.put("texto", concepto);
        //param.put("adenda", txtadenda.getText() + " - ID TRANSACC.: " + id);
        param.put("movimiento", "entrada movimiento: " + String.valueOf(id));
        param.put("pesos", (importe));
        JasperPrint jasperprint = JasperFillManager.fillReport(reporte, param, c);
        JasperViewer visor = new JasperViewer(jasperprint, false);
        visor.setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        visor.setTitle("Recibo");
        visor.setVisible(true);
        //this.dispose();
    }

    void actualizar_id_cfe(Float comision_ingresada) throws Exception {
        d_cfe cfe = new d_cfe();
        cfe.actualizar_id(id_documento, id_generado);
        con.escribirfichero("SE ACTUALIZA ID CFE EN BASE, ID VIEJO: " + id_documento + " - ID NUEVO: " + id_generado + " - COMISION: " + con.mostrarnumero(comision_ingresada));
    }

    Date fechaactual() throws Exception {
        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy", new Locale("es_ES"));
        String fechastr = (formateador.format(new Date()));

        return parsefechadate(fechastr);
    }

    Date parsefechadate(String fecha) throws Exception {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaDate = null;

        try {
            fechaDate = formato.parse(fecha);
        } catch (ParseException ex) {
            throw new Exception("revise formato de año");
        }
        return fechaDate;
    }

    Date parsefechadate_recibo(Date fecha) throws Exception {
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
    
    private void txtidKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtidKeyTyped
        char car = evt.getKeyChar();
        if (!Character.isDigit(car)) {
            evt.consume();
            getToolkit().beep();
        }
    }//GEN-LAST:event_txtidKeyTyped

    private void txtdgiKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdgiKeyTyped
        char car = evt.getKeyChar();
        if (!Character.isDigit(car)) {
            evt.consume();
            getToolkit().beep();
        }
    }//GEN-LAST:event_txtdgiKeyTyped

    private void txtcomisionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcomisionKeyTyped
        char car = evt.getKeyChar();
        if (!Character.isDigit(car)) {
            evt.consume();
            getToolkit().beep();
        }
    }//GEN-LAST:event_txtcomisionKeyTyped

    private void txtnombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombreKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnombreKeyTyped

    private void txtciKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtciKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtciKeyTyped

    private void txtdireccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdireccionKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdireccionKeyTyped

    private void txtentradaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtentradaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtentradaKeyTyped

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
            java.util.logging.Logger.getLogger(p_agregar_recibo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(p_agregar_recibo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(p_agregar_recibo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(p_agregar_recibo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                p_agregar_recibo dialog = new p_agregar_recibo(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btningresar;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTextField txtci;
    private javax.swing.JTextField txtcomision;
    private javax.swing.JTextField txtdgi;
    private javax.swing.JTextField txtdireccion;
    private javax.swing.JTextField txtentrada;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtnombre;
    // End of variables declaration//GEN-END:variables

    private static SICFERespuestaObtenerCFE obtenerCFEPorID(java.lang.String usuario, java.lang.String clave, java.lang.String tenant, Integer tipo, java.lang.String serie, Integer numero, java.lang.Boolean devolverXML) {
        org.tempuri.ImpSICFEEmisor service = new org.tempuri.ImpSICFEEmisor();
        org.tempuri.ISICFEEmisor port = service.getBasicHttpBindingISICFEEmisor();
        return port.obtenerCFEPorID(usuario, clave, tenant, tipo.shortValue(), serie, numero.longValue(), true, devolverXML);
    }

    @Override
    public void actualizar() {
        //p_control con = p_control.getInstancia();

        con.actualizar_doc();
    }
}
