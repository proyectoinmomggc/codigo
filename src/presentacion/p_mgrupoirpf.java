/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import dominio.d_grupoirpf;
import dominio.d_propietario;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
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
public class p_mgrupoirpf extends javax.swing.JDialog {

    DefaultTableModel mdltabpro = new DefaultTableModel();
    List<d_grupoirpf> listagrupoirpf = new ArrayList<d_grupoirpf>();
    public d_propietario pro = null;

    /**
     * Creates new form p_mgrupoirpf
     */
    public p_mgrupoirpf(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        p_control c = p_control.getInstancia();
        tblgrupoirpf.setModel(mdltabpro);

        //mdltabpro.addColumn("ID PROP");
        mdltabpro.addColumn("NOMBRE");
        mdltabpro.addColumn("CI");
        mdltabpro.addColumn("PORCENTAJE");
        c.alinear_contenido_columna_izquierda(tblgrupoirpf, 2);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton5 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtnropro = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblgrupoirpf = tblgrupoirpf = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblnombreprop = new javax.swing.JLabel();
        txtci = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtporcentaje = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtnombre = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        lblnropro = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("GRUPO IRPF");
        getContentPane().setLayout(null);

        jButton5.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jButton5.setText("AGREGAR");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5);
        jButton5.setBounds(280, 310, 120, 30);

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setText("ID PROP");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(10, 240, 190, 17);

        txtnropro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnroproActionPerformed(evt);
            }
        });
        txtnropro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtnroproKeyPressed(evt);
            }
        });
        getContentPane().add(txtnropro);
        txtnropro.setBounds(10, 40, 138, 30);

        jButton3.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jButton3.setText("BUSCAR");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3);
        jButton3.setBounds(280, 40, 120, 30);

        tblgrupoirpf.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        tblgrupoirpf.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblgrupoirpfMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tblgrupoirpfMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tblgrupoirpfMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblgrupoirpfMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblgrupoirpfMouseReleased(evt);
            }
        });
        tblgrupoirpf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblgrupoirpfKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblgrupoirpfKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tblgrupoirpfKeyTyped(evt);
            }
        });
        jScrollPane2.setViewportView(tblgrupoirpf);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(10, 130, 396, 100);

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setText("NOMBRE");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(10, 70, 170, 17);

        jLabel7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel7.setText("CI");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(10, 340, 170, 17);

        lblnombreprop.setBackground(java.awt.Color.green);
        lblnombreprop.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblnombreprop.setOpaque(true);
        getContentPane().add(lblnombreprop);
        lblnombreprop.setBounds(10, 90, 390, 30);

        txtci.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtciFocusLost(evt);
            }
        });
        txtci.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtciActionPerformed(evt);
            }
        });
        txtci.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtciKeyTyped(evt);
            }
        });
        getContentPane().add(txtci);
        txtci.setBounds(10, 360, 138, 30);

        jButton6.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jButton6.setText("ELIMINAR");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6);
        jButton6.setBounds(280, 360, 120, 30);

        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setText("PORCENTAJE");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(10, 390, 190, 17);

        txtporcentaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtporcentajeActionPerformed(evt);
            }
        });
        getContentPane().add(txtporcentaje);
        txtporcentaje.setBounds(10, 410, 138, 30);

        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setText("NOMBRE");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(10, 290, 170, 17);

        txtnombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnombreActionPerformed(evt);
            }
        });
        getContentPane().add(txtnombre);
        txtnombre.setBounds(10, 310, 138, 30);

        jButton4.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jButton4.setText("LIMPIAR");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4);
        jButton4.setBounds(280, 410, 120, 30);

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setText("ID PROP");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(10, 20, 170, 17);

        lblnropro.setBackground(java.awt.Color.green);
        lblnropro.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblnropro.setOpaque(true);
        getContentPane().add(lblnropro);
        lblnropro.setBounds(10, 260, 390, 30);

        setBounds(0, 0, 432, 492);
    }// </editor-fold>//GEN-END:initComponents

    void limpiarcampos() {
        pro = null;

        lblnombreprop.setText("-");
        lblnropro.setText("");
        txtnombre.setText("");
        txtci.setText("");
        txtporcentaje.setText("");
    }

    void buscarpropietario() throws Exception {
        p_control con = p_control.getInstancia();
        d_propietario prop1 = new d_propietario();

        if (txtnropro.getText().equals("")) {
            throw new Exception("propietario no existe");
        }

        txtnropro.setEditable(false);
        prop1.setProp_id(Integer.parseInt(txtnropro.getText()));
        prop1 = prop1.buscarpropietario(prop1.getProp_id());

        if (prop1 != null) {
            con.guardarprimermovimiento(prop1);
            pro = prop1;
            lblnropro.setText(pro.getProp_id().toString());
        } else {
            txtnropro.setEditable(true);
            throw new Exception("propietario no existe");
        }
    }

    void cargarpropietario() {
        p_control con = p_control.getInstancia();
        d_grupoirpf gru1 = new d_grupoirpf();
        Vector v;

        try {
            if (pro != null) {
                lblnombreprop.setText(pro.getProp_nombre());
                listagrupoirpf = null;
                listagrupoirpf = gru1.listarciporprop(pro.getProp_id());
                if (listagrupoirpf.size() <= 0) {
                    cleartable();
                    /*
                    v = new Vector();
                    v.add(pro.getProp_id());
                    v.add(pro.getProp_nombre());
                    v.add(pro.getProp_cirut());
                    v.add("100");
                    mdltabpro.addRow(v);
                     */
                    lblnropro.setText(pro.getProp_id().toString());
                    txtnombre.setText(pro.getProp_nombre());
                    txtci.setText(pro.getProp_cirut());
                    txtporcentaje.setText("100,00");
                } else {
                    cleartable();
                    //listagrupoirpf = pro.listarpropietarios();
                    for (d_grupoirpf aux : listagrupoirpf) {
                        //if (aux.getPorcentaje() == 100f) {
                        //  continue;
                        //}
                        gru1 = new d_grupoirpf();
                        v = new Vector();
                        gru1.setProp_id(aux.getProp_id());
                        gru1.setNombre(aux.getNombre());
                        gru1.setCigrupo(aux.getCigrupo());
                        gru1.setPorcentaje(aux.getPorcentaje());
                        //v.add(gru1.getProp_id());
                        v.add(gru1.getNombre());
                        v.add(gru1.getCigrupo());
                        v.add(con.mostrarnumero(gru1.getPorcentaje()));
                        mdltabpro.addRow(v);
                    }
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, toUpperCase(ex.getMessage()), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    void cleartable() {
        for (int i = 0; i < tblgrupoirpf.getRowCount(); i++) {
            mdltabpro.removeRow(i);
            i -= 1;
        }
    }

    void selecciontabla() throws Exception {
        p_control con = p_control.getInstancia();
        d_grupoirpf gru1 = new d_grupoirpf();

        if (pro == null) {
            lblnropro.setText(pro.getProp_id().toString());
            txtnombre.setText(pro.getProp_nombre());
            txtci.setText(pro.getProp_cirut());
            txtporcentaje.setText("100,00");
        } else {
            lblnropro.setText(pro.getProp_id().toString());
            int fila = tblgrupoirpf.getSelectedRow();
            if (fila != -1) {
                txtnombre.setText((String) tblgrupoirpf.getValueAt(fila, 0));
                txtci.setText((String) tblgrupoirpf.getValueAt(fila, 1));
                txtporcentaje.setText((String) tblgrupoirpf.getValueAt(fila, 2));
            }
        }
    }

    void limpiartodo() {
        pro = null;
        txtnropro.setEditable(true);
        txtnropro.setText("");
        lblnombreprop.setText("-");
        cleartable();
        lblnropro.setText("");
        txtnombre.setText("");
        txtci.setText("");
        txtporcentaje.setText("");
    }

    void controlar() throws Exception {
        d_grupoirpf gru1 = new d_grupoirpf();

        if (pro == null) {
            throw new Exception("propietario no existe");
        }
        if (lblnropro.getText().equals("")) {
            throw new Exception("propietario no existe");
        }
        if (txtci.getText().equals("")) {
            throw new Exception("numero de ci no puede estar vacio");
        }
        if (txtporcentaje.getText().equals("")) {
            throw new Exception("numero porcentaje no puede estar vacio");
        }

        gru1 = gru1.buscargrupoirpf(pro.getProp_id(), txtci.getText());
        if (gru1 != null) {
            //editar
            //control suma porcentaje = 100
            int ax = JOptionPane.showConfirmDialog(null, "¿DESEA EDITAR DEL PROP.: " + pro.getProp_id() + " \n NOMBRE: " + gru1.getNombre() + " A: " + txtnombre.getText() + " \n CI: " + gru1.getCigrupo() + " A: " + txtci.getText() + " \n PORCENTAJE: " + gru1.getPorcentaje() + " A: " + txtporcentaje.getText() + "?", "CONFIRMACION", JOptionPane.YES_NO_CANCEL_OPTION);
            if (ax == JOptionPane.YES_OPTION) {
                editar(gru1);
            }
        } else {
            //agregar
            int ax = JOptionPane.showConfirmDialog(null, "¿DESEA AGREGAR AL PROP.: " + pro.getProp_id() + " \n NOMBRE: " + txtnombre.getText() + " \n CI: " + txtci.getText() + " \n PORCENTAJE: " + txtporcentaje.getText() + "?", "CONFIRMACION", JOptionPane.YES_NO_CANCEL_OPTION);
            if (ax == JOptionPane.YES_OPTION) {
                agregar();
            }
        }
    }

    void editar(d_grupoirpf gru1) throws Exception {
        p_control con = p_control.getInstancia();
        float porcentajeasumar = 0f;
        float porcentajetotalactual = 0f;
        //String fecha = fechaactual();

        porcentajeasumar = con.guardarnumero((txtporcentaje.getText()));
        porcentajetotalactual = gru1.montogrupoirpfmenosci(gru1.getProp_id(), gru1.getCigrupo());
        float montosumado = porcentajetotalactual + porcentajeasumar;

        if (montosumado > 100) {
            throw new Exception("revisar porcentaje, el total de las CI ingresadas y sumadas debe ser 100");
        } else if (montosumado < 100) {
            int ax = JOptionPane.showConfirmDialog(null, toUpperCase("el monto de porcentajes sumados no llega al 100%, ¿desea confirmar igual la actualizacion de los datos?"), "CONFIRMACION", JOptionPane.YES_NO_CANCEL_OPTION);
            if (ax == JOptionPane.YES_OPTION) {
                gru1.setNombre(txtnombre.getText());
                gru1.setCigrupo(txtci.getText());
                gru1.setPorcentaje(porcentajeasumar);
                gru1.actualizargrupoirpf(gru1);
                con.escribirfichero("GRUPO IRPF - se actualiza a id prop.: " + gru1.getProp_id() + " -- nombre: " + gru1.getNombre()
                        + " -- ci grupo: " + gru1.getCigrupo() + " -- porcentaje: " + con.mostrarnumero(gru1.getPorcentaje())
                        + " -- monto: " + con.mostrarnumero(gru1.getMonto())+" -- fecha: "+gru1.getFecha());
                JOptionPane.showMessageDialog(this, "DATOS ACTUALIZADOS CORRECTAMENTE", "AVISO", JOptionPane.INFORMATION_MESSAGE);
                cargarpropietario();
            }
        } else {
            gru1.setNombre(txtnombre.getText());
            gru1.setCigrupo(txtci.getText());
            gru1.setPorcentaje(porcentajeasumar);
            gru1.actualizargrupoirpf(gru1);
            con.escribirfichero("GRUPO IRPF - se actualiza a id prop.: " + gru1.getProp_id() + " -- nombre: " + gru1.getNombre()
                        + " -- ci grupo: " + gru1.getCigrupo() + " -- porcentaje: " + con.mostrarnumero(gru1.getPorcentaje())
                        + " -- monto: " + con.mostrarnumero(gru1.getMonto())+" -- fecha: "+gru1.getFecha());
            JOptionPane.showMessageDialog(this, "DATOS ACTUALIZADOS CORRECTAMENTE", "AVISO", JOptionPane.INFORMATION_MESSAGE);
            cargarpropietario();
        }
    }

    void agregar() throws Exception {
        //fijarse si ya el propietario no tiene 5 ci ingreasadas
        //cantciporprop
        //guardarnuevogrupoirpf
        p_control con = p_control.getInstancia();
        d_grupoirpf gru1 = new d_grupoirpf();
        String fecha = fechaactual();

        gru1.setProp_id(pro.getProp_id());
        gru1.setNombre(txtnombre.getText());
        gru1.setCigrupo(txtci.getText());
        gru1.setPorcentaje(con.guardarnumero((txtporcentaje.getText())));
        gru1.setFecha(fecha);
        gru1.setMonto(0F);

        Integer cantciporprop = gru1.cantciporprop(pro.getProp_id());
        if (cantciporprop >= 5) {
            throw new Exception("CI a ingresar supera el limite de ingresos permitidos por Propietario");
        }

        float porcentajeasumar = 0f;
        float porcentajetotalactual = 0f;
        //String fecha = fechaactual();

        porcentajeasumar = con.guardarnumero((txtporcentaje.getText()));
        porcentajetotalactual = gru1.montogrupoirpfporprop(gru1.getProp_id());
        float montosumado = porcentajetotalactual + porcentajeasumar;

        if (montosumado > 100) {
            throw new Exception("revisar porcentaje, el total de las CI ingresadas y sumadas debe ser 100");
        } else if (montosumado < 100) {
            int ax = JOptionPane.showConfirmDialog(null, toUpperCase("el monto de porcentajes sumados no llega al 100%, ¿Desea confirmar igual la actualizacion de los datos?"), "CONFIRMACION", JOptionPane.YES_NO_CANCEL_OPTION);
            if (ax == JOptionPane.YES_OPTION) {
                gru1.guardarnuevogrupoirpf(gru1);
                con.escribirfichero("GRUPO IRPF - se agrega a id prop.: " + gru1.getProp_id() + " -- nombre: " + gru1.getNombre()
                        + " -- ci grupo: " + gru1.getCigrupo() + " -- porcentaje: " + con.mostrarnumero(gru1.getPorcentaje())
                        + " -- monto: " + con.mostrarnumero(gru1.getMonto()) + " -- fecha: " + fecha);
                JOptionPane.showMessageDialog(this, "DATOS GUARDADOS CORRECTAMENTE", "AVISO", JOptionPane.INFORMATION_MESSAGE);
                cargarpropietario();
            }
        } else {
            gru1.guardarnuevogrupoirpf(gru1);
            con.escribirfichero("GRUPO IRPF - se agrega a id prop.: " + gru1.getProp_id() + " -- nombre: " + gru1.getNombre()
                        + " -- ci grupo: " + gru1.getCigrupo() + " -- porcentaje: " + con.mostrarnumero(gru1.getPorcentaje())
                        + " -- monto: " + con.mostrarnumero(gru1.getMonto()) + " -- fecha: " + fecha);
            JOptionPane.showMessageDialog(this, "DATOS GUARDADOS CORRECTAMENTE", "AVISO", JOptionPane.INFORMATION_MESSAGE);
            cargarpropietario();
        }
    }

    void eliminar(d_grupoirpf gru) throws Exception {
        p_control con = p_control.getInstancia();
        if (gru == null) {
            throw new Exception("error al eliminar registro \n CI: " + txtci.getText() + " no existe en el grupo del Prop.: " + pro.getProp_id());
        }

        int ax = JOptionPane.showConfirmDialog(null, "¿SEGURO DESEA ELIMINAR AL PROP.: " + pro.getProp_id() + " LA CI: " + txtci.getText() + "?", "CONFIRMACION", JOptionPane.YES_NO_CANCEL_OPTION);
        if (ax == JOptionPane.YES_OPTION) {
            gru.eliminargrupoirpf(gru);
            con.escribirfichero("GRUPO IRPF - se elimina, id prop.: " + gru.getProp_id() + " -- ci grupo: " + gru.getCigrupo()
                    + " -- porcentaje: " + con.mostrarnumero(gru.getPorcentaje()));
            JOptionPane.showMessageDialog(this, "DATOS ELIMINADOS CORRECTAMENTE", "AVISO", JOptionPane.INFORMATION_MESSAGE);
            cargarpropietario();
        }
    }

    String fechaactual() throws Exception {
        Date fechadate = null;
        String fecha = "";
        Integer mes = -1;
        Integer anio = -1;

        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy", new Locale("es_ES"));
        fecha = (formateador.format(new Date()));

        fechadate = parsefechadate(fecha);
        mes = devuelvemes(fechadate);
        anio = devuelveanio(fechadate);

        fecha = mes.toString() + anio.toString();

        return fecha;
    }

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

    Date parsefechadate(String fecha) throws Exception {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaDate = null;

        try {
            fechaDate = formato.parse(fecha);
        } catch (Exception ex) {
            throw new Exception("revise formato de año");
        }
        return fechaDate;
    }


    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        try {
            controlar();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, toUpperCase(ex.getMessage()), "ERROR", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_jButton5ActionPerformed

    private void txtnroproActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnroproActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnroproActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        funcbuscar();
    }//GEN-LAST:event_jButton3ActionPerformed

    void funcbuscar() {
        try {
            limpiarcampos();
            buscarpropietario();
            cargarpropietario();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, toUpperCase(ex.getMessage()), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void tblgrupoirpfMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblgrupoirpfMouseClicked
        try {
            selecciontabla();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, toUpperCase(ex.getMessage()), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_tblgrupoirpfMouseClicked

    private void tblgrupoirpfMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblgrupoirpfMouseEntered
        try {
            selecciontabla();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, toUpperCase(ex.getMessage()), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_tblgrupoirpfMouseEntered

    private void tblgrupoirpfMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblgrupoirpfMouseExited
        try {
            selecciontabla();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, toUpperCase(ex.getMessage()), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_tblgrupoirpfMouseExited

    private void tblgrupoirpfMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblgrupoirpfMousePressed
        try {
            selecciontabla();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, toUpperCase(ex.getMessage()), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_tblgrupoirpfMousePressed

    private void tblgrupoirpfMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblgrupoirpfMouseReleased
        try {
            selecciontabla();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, toUpperCase(ex.getMessage()), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_tblgrupoirpfMouseReleased

    private void tblgrupoirpfKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblgrupoirpfKeyPressed
        try {
            selecciontabla();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, toUpperCase(ex.getMessage()), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_tblgrupoirpfKeyPressed

    private void tblgrupoirpfKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblgrupoirpfKeyReleased
        try {
            selecciontabla();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, toUpperCase(ex.getMessage()), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_tblgrupoirpfKeyReleased

    private void tblgrupoirpfKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblgrupoirpfKeyTyped
        try {
            selecciontabla();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, toUpperCase(ex.getMessage()), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_tblgrupoirpfKeyTyped

    private void txtciActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtciActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtciActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        d_grupoirpf gru1 = new d_grupoirpf();

        try {
            //controlar();
            gru1 = gru1.buscargrupoirpf(pro.getProp_id(), txtci.getText());
            eliminar(gru1);
            lblnropro.setText("");
            txtnombre.setText("");
            txtci.setText("");
            txtporcentaje.setText("");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, toUpperCase(ex.getMessage()), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void txtporcentajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtporcentajeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtporcentajeActionPerformed

    private void txtnombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnombreActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        limpiartodo();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void txtnroproKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnroproKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            funcbuscar();
        }
    }//GEN-LAST:event_txtnroproKeyPressed

    private void txtciKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtciKeyTyped
        char car = evt.getKeyChar();
        if (!Character.isDigit(car)) {
            evt.consume();
            getToolkit().beep();
        }
    }//GEN-LAST:event_txtciKeyTyped

    private void txtciFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtciFocusLost

    }//GEN-LAST:event_txtciFocusLost

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
            java.util.logging.Logger.getLogger(p_mgrupoirpf.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(p_mgrupoirpf.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(p_mgrupoirpf.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(p_mgrupoirpf.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                p_mgrupoirpf dialog = new p_mgrupoirpf(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblnombreprop;
    private javax.swing.JLabel lblnropro;
    private javax.swing.JTable tblgrupoirpf;
    private javax.swing.JTextField txtci;
    private javax.swing.JTextField txtnombre;
    private javax.swing.JTextField txtnropro;
    private javax.swing.JTextField txtporcentaje;
    // End of variables declaration//GEN-END:variables
}
