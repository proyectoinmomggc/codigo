/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion.paneles;

import dominio.d_cfe;
import dominio.d_clave;
import dominio.d_configuracion;
import dominio.d_parametroscfe;
import dominio.d_xml;
import java.awt.Dimension;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import static jdk.nashorn.internal.objects.NativeString.toUpperCase;
import org.datacontract.schemas._2004._07.sicfecontract.SICFERespuestaBuffer;
import org.datacontract.schemas._2004._07.sicfecontract.SICFERespuestaObtenerCFE;

/**
 *
 * @author Gonzalo
 */
public class p_notascredito extends javax.swing.JDialog {

    p_control con = p_control.getInstancia();
    DefaultTableModel mdltabnot = new DefaultTableModel();
    List<d_cfe> listanotas = new ArrayList<d_cfe>();

    /**
     * Creates new form p_listado_mov
     */
    public p_notascredito(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        tblnotas.setModel(mdltabnot);

        mdltabnot.addColumn("ID");
        mdltabnot.addColumn("SERIE");
        mdltabnot.addColumn("NUMERO");
        mdltabnot.addColumn("TIPO");
        cargarnotas();
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
        tblnotas = tblnotas = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        jButton4 = new javax.swing.JButton();
        txtclave = new javax.swing.JPasswordField();
        jLabel16 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("NOTAS DE CREDITO");
        getContentPane().setLayout(null);

        tblnotas.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        tblnotas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblnotasMouseClicked(evt);
            }
        });
        tblnotas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblnotasKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(tblnotas);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(10, 20, 464, 135);

        jButton4.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jButton4.setText("OBTENER PDF");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4);
        jButton4.setBounds(480, 20, 120, 30);
        getContentPane().add(txtclave);
        txtclave.setBounds(10, 180, 138, 30);

        jLabel16.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel16.setText("CLAVE");
        getContentPane().add(jLabel16);
        jLabel16.setBounds(10, 160, 70, 17);

        setBounds(0, 0, 629, 258);
    }// </editor-fold>//GEN-END:initComponents

    private void tblnotasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblnotasMouseClicked
        try {
            //selecciontabla();
            if (evt.getClickCount() == 2) {
                //HIZO DOBLE CLIC
                String xml = obtenerCFEPorID_funcion_devuelvexml();

                mostrarmensajeinfo(devuelvemensajeparaimprimir(xml));
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, toUpperCase(ex.getMessage()), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_tblnotasMouseClicked

    void mostrarmensajeinfo(String texto) throws Exception {
        if (texto.equals("")) {
            return;
        }
        JTextArea textArea = new JTextArea(texto); //AQUI VA TEXTO
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        scrollPane.setPreferredSize(new Dimension(400, 250));
        JOptionPane.showMessageDialog(null, scrollPane, "INFORMACION DE LA NOTA DE CRÉDITO SELECCIONADA",
                JOptionPane.INFORMATION_MESSAGE);
    }

    String obtenerCFEPorID_funcion_devuelvexml() throws Exception {
        if (id_seleccionado() == -1) {
            return "";
        }
        d_cfe cfe = new d_cfe();
        controlarclave();
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
        cfe = cfe.buscarcfe(id_seleccionado());
        if (cfe == null) {
            throw new Exception("no existe pdf solicitado");
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

    String devuelvemensajeparaimprimir(String xml) throws Exception {
        d_xml dx = new d_xml();
        String fecha_emision = "";
        String fecha_emision_referencia = "";

        if (tipo_doc().equals("E-TICKET")) {
            fecha_emision = dx.recibexmlfacturaydevuelvefechaemisionticket(xml);
        }
        if (tipo_doc().equals("E-FACTURA")) {
            fecha_emision = dx.recibexmlfacturaydevuelvefechaemisionfactura(xml);
        }

        fecha_emision_referencia = dx.recibexmlfacturaydevuelvefechareferencia(xml);

        Date fecha_d = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(fecha_emision);
        Date fecha_d_r = new SimpleDateFormat("yyyy-MM-dd").parse(fecha_emision_referencia);
        //id_seleccionado()
        String mensaje = "ID MOVIMIENTO: " + id_seleccionado() + "\n"
                + "FECHA CREACION DE LA NOTA: " + parsefechaemitida(fecha_d) + "\n"
                + "NOMBRE: " + dx.recibexmlfacturaydevuelvenombrecliente(xml) + "\n"
                + "CI - RUT: " + dx.recibexmlfacturaydevuelverutcliente(xml) + "\n"
                + "DIRECCION: " + dx.recibexmlfacturaydevuelvedireccioncliente(xml) + "\n"
                + "CONCEPTO: " + dx.recibexmlfacturaydevuelveconcepto(xml) + "\n"
                //+ "ADENDA: " + dx.recibexmlfacturaydevuelveadenda(xml) + "\n"
                + "REFERENCIA A " + tipo_doc() + ": " + dx.recibexmlfacturaydevuelvereferencia(xml) + ""
                + " (" + parsefechaemitida(fecha_d_r) + ")" + "\n"
                + "IMPORTE: " + con.mostrarnumero(Float.parseFloat(dx.recibexmlfacturaydevuelvetotalsiniva(xml))) + "\n"
                + "IVA: " + con.mostrarnumero(Float.parseFloat(dx.recibexmlfacturaydevuelveiva(xml))) + "\n"
                + "-----------------------------------------------" + "\n"
                + "TOTAL ($U): " + con.mostrarnumero(Float.parseFloat(dx.recibexmlfacturaydevuelvetotalsiniva(xml))
                        + (Float.parseFloat(dx.recibexmlfacturaydevuelveiva(xml))));
        return (mensaje);
    }

    String fechaemisionseguntipo() {
        int row = tblnotas.getSelectedRow();
        if (row == -1) {
            return "";

        } else {
            String tipo = String.valueOf(tblnotas.getValueAt(row, 3));
            if (tipo.equals("e-Ticket")) {
                return "E-TICKET";
            }
            if (tipo.equals("e-Factura")) {
                return "E-FACTURA";
            }
        }
        return "";
    }

    String tipo_doc() {
        int row = tblnotas.getSelectedRow();
        if (row == -1) {
            return "";

        } else {
            String tipo = String.valueOf(tblnotas.getValueAt(row, 3));
            if (tipo.equals("E-TICKET")) {
                return "E-TICKET";
            }
            if (tipo.equals("E-FACTURA")) {
                return "E-FACTURA";
            }
        }
        return "";
    }

    Integer id_seleccionado() {
        int row = tblnotas.getSelectedRow();
        if (row != -1) {
            return (int) tblnotas.getValueAt(row, 0);
        }
        return -1;
    }

    private static SICFERespuestaObtenerCFE obtenerCFEPorID(java.lang.String usuario, java.lang.String clave, java.lang.String tenant, Integer tipo, java.lang.String serie, Integer numero, java.lang.Boolean devolverXML) {
        org.tempuri.ImpSICFEEmisor service = new org.tempuri.ImpSICFEEmisor();
        org.tempuri.ISICFEEmisor port = service.getBasicHttpBindingISICFEEmisor();
        return port.obtenerCFEPorID(usuario, clave, tenant, tipo.shortValue(), serie, numero.longValue(), true, devolverXML);
    }

    private void tblnotasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblnotasKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tblnotasKeyPressed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int id = -1;
        d_cfe cfe = new d_cfe();

        try {
            controlarclave();
            id = selecciontabla();
            if (id == -1) {
                return;
            }
            cfe = cfe.buscarcfe(id);
            d_parametroscfe pcfe = new d_parametroscfe();
            //buscar movimiento y parametroscfe que trae usuario, clave, tenant
            SICFERespuestaBuffer obtenerVersion = new SICFERespuestaBuffer();
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
            obtenerVersion = obtenerPDF(pcfe.getNomusuario(), pcfe.getClave(), i, pcfe.getTenant(), "");
            if (obtenerVersion.getCodigo() != 0) {
                throw new Exception("CFE solicitado no existe, o no fue ingresado");
            }
            pdf(obtenerVersion, id);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, toUpperCase(ex.getMessage()), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    void pdf(SICFERespuestaBuffer obtenerVersion, int id) throws FileNotFoundException, IOException {
        QName fooQNameSerie = new QName("http://schemas.datacontract.org/2004/07/SICFEContract", "Buffer");
        JAXBElement<byte[]> buffer = new JAXBElement<byte[]>(fooQNameSerie, byte[].class, new byte[obtenerVersion.getBuffer().getValue().length]);
        buffer = obtenerVersion.getBuffer();

        byte[] bytes = new byte[buffer.getValue().length];
        bytes = buffer.getValue();

        //below is the different part
        String nombrearch = "cfe-" + id + ".pdf";
        File someFile = new File(nombrearch);
        someFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(someFile);
        fos.write(bytes);
        fos.flush();
        fos.close();
        Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + nombrearch);
    }

    private static SICFERespuestaBuffer obtenerPDF(java.lang.String nomusuario, java.lang.String clave, org.datacontract.schemas._2004._07.sicfecontract.IdCFE pIdCFE, java.lang.String pTenant, java.lang.String template) {
        org.tempuri.ImpSICFEEmisor service = new org.tempuri.ImpSICFEEmisor();
        org.tempuri.ISICFEEmisor port = service.getBasicHttpBindingISICFEEmisor();
        //SICFERespuestaBuffer x = new SICFERespuestaBuffer();
        return port.obtenerPDF(nomusuario, clave, pIdCFE, pTenant, template);
        //x.getBuffer().getValue()
        //JOptionPane.showMessageDialog(null, x.getCodigo()); //error

        //return x;
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

    void cargarnotas() {
        d_cfe cfe = new d_cfe();
        d_cfe cfe1 = null;
        Vector v;

        try {
            cleartable();
            listanotas = cfe.listarnotas();
            for (d_cfe aux : listanotas) {
                cfe1 = new d_cfe();
                v = new Vector();
                cfe1.setIdmov(aux.getIdmov());
                cfe1.setSerie(aux.getSerie());
                cfe1.setNumero(aux.getNumero());
                cfe1.setTipo(aux.getTipo());
                v.add(cfe1.getIdmov());
                v.add(cfe1.getSerie());
                v.add(cfe1.getNumero());
                v.add(cargartipocfe(cfe1));
                mdltabnot.addRow(v);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, toUpperCase(ex.getMessage()), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    String cargartipocfe(d_cfe cfe) throws Exception {
        String tipo = "";

        if (cfe == null) {
            tipo = "Generar CFE";
            return tipo;
        }
        if (cfe.getTipo().equals(101)) {
            tipo = "E-TICKET";
        }
        if (cfe.getTipo().equals(102)) {
            tipo = "E-TICKET";
        }
        if (cfe.getTipo().equals(111)) {
            tipo = "E-FACTURA";
        }
        if (cfe.getTipo().equals(112)) {
            tipo = "E-FACTURA";
        }
        return tipo;
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

    Date parsefechadate_devuelvedate(String fecha) throws Exception {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaDate = null;

        try {
            fechaDate = formato.parse(fecha);
        } catch (ParseException ex) {
            throw new Exception("revise formato de año");
        }
        return fechaDate;
    }

    String parsefechastring(java.util.Date fecha) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String fechastr = formato.format(fecha);

        return fechastr;
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

    Integer selecciontabla() throws Exception {
        int id = -1;
        p_control con = p_control.getInstancia();

        int row = tblnotas.getSelectedRow(); //guarda el numero de fila que selecciona el usuario

        if (row != -1) {
            id = (int) tblnotas.getValueAt(row, 0);
        } else {
            JOptionPane.showMessageDialog(null, toUpperCase("debe seleccionar una fila"), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return id;
    }

    void cleartable() {
        for (int i = 0; i < tblnotas.getRowCount(); i++) {
            mdltabnot.removeRow(i);
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
            java.util.logging.Logger.getLogger(p_notascredito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(p_notascredito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(p_notascredito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(p_notascredito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                p_notascredito dialog = new p_notascredito(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblnotas;
    private javax.swing.JPasswordField txtclave;
    // End of variables declaration//GEN-END:variables

}
