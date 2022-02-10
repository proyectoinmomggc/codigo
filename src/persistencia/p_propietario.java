/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import dominio.d_movimiento;
import dominio.d_propietario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import static persistencia.p_movimientos.ASqlDate;

/**
 *
 * @author Gonzalo
 */
public class p_propietario {

    public static Integer generarid() throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        ResultSet res;
        Statement st;
        Boolean haydatos;
        Integer idobtenido;
        Integer idretorno;

        haydatos = false;
        idobtenido = -1;
        idretorno = 1;
        try {
            st = c.createStatement();
            res = st.executeQuery("Select prop_id From propietarios where prop_id < 5000 Order By prop_id Desc");
            haydatos = res.next();
            while (haydatos) {
                idobtenido = (res.getInt("prop_id"));
                if (idobtenido >= idretorno) {
                    idretorno = idobtenido + 1;
                }
                haydatos = false;
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return idretorno;
    }

    public static d_propietario buscarpropietario(Integer nroprop) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        Statement st;
        ResultSet res;
        d_propietario prop = null;

        try {
            st = c.createStatement();
            res = st.executeQuery("select * from propietarios where prop_id=" + nroprop);
            while (res.next()) {
                prop = new d_propietario();
                prop.setProp_id(res.getInt("prop_id"));
                prop.setProp_nombre(res.getString("prop_nombre"));
                prop.setProp_direccion(res.getString("prop_direccion"));
                prop.setProp_saldo(res.getFloat("prop_saldo"));
                prop.setProp_numcontacto(res.getInt("prop_numcontacto"));
                prop.setProp_cirut(res.getString("prop_cirut"));
                prop.setProp_observaciones(res.getString("prop_observaciones"));
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return prop;
    }
    
    public static String nombre_propietario(Integer nroprop) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        Statement st;
        ResultSet res;
        d_propietario prop = null;

        try {
            st = c.createStatement();
            res = st.executeQuery("select prop_nombre from propietarios where prop_id=" + nroprop);
            while (res.next()) {
                return (res.getString("prop_nombre"));
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return "";
    }
    
    public static String cirut_propietario(Integer nroprop) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        Statement st;
        ResultSet res;
        d_propietario prop = null;

        try {
            st = c.createStatement();
            res = st.executeQuery("select prop_cirut from propietarios where prop_id=" + nroprop);
            while (res.next()) {
                return (res.getString("prop_cirut"));
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return "";
    }

    public static List<d_propietario> listarpropietarios() throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        d_propietario pro = null;
        Statement st;
        ResultSet res;
        List<d_propietario> lista = new ArrayList<d_propietario>();

        try {
            st = c.createStatement();
            res = st.executeQuery("select * from propietarios");
            while (res.next()) {
                pro = new d_propietario();
                pro.setProp_id(Integer.parseInt(res.getString("prop_id")));
                pro.setProp_nombre(res.getString("prop_nombre"));
                pro.setProp_direccion(res.getString("prop_direccion"));
                pro.setProp_saldo(res.getFloat("prop_saldo"));
                pro.setProp_numcontacto(Integer.parseInt(res.getString("prop_numcontacto")));
                pro.setProp_cirut(res.getString("prop_cirut"));
                pro.setProp_observaciones(res.getString("prop_observaciones"));
                lista.add(pro);
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return lista;
    }

    public static List<d_propietario> listarpropietariosporid() throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        d_propietario pro = null;
        Statement st;
        ResultSet res;
        List<d_propietario> lista = new ArrayList<d_propietario>();

        try {
            st = c.createStatement();
            res = st.executeQuery("select * from propietarios order by prop_id");
            while (res.next()) {
                pro = new d_propietario();
                pro.setProp_id(Integer.parseInt(res.getString("prop_id")));
                pro.setProp_nombre(res.getString("prop_nombre"));
                pro.setProp_direccion(res.getString("prop_direccion"));
                pro.setProp_saldo(res.getFloat("prop_saldo"));
                pro.setProp_numcontacto(Integer.parseInt(res.getString("prop_numcontacto")));
                pro.setProp_cirut(res.getString("prop_cirut"));
                pro.setProp_observaciones(res.getString("prop_observaciones"));
                lista.add(pro);
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return lista;
    }

    public static List<d_propietario> listarpropietariospornombre() throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        d_propietario pro = null;
        Statement st;
        ResultSet res;
        List<d_propietario> lista = new ArrayList<d_propietario>();

        try {
            st = c.createStatement();
            res = st.executeQuery("select * from propietarios order by prop_nombre");
            while (res.next()) {
                pro = new d_propietario();
                pro.setProp_id(Integer.parseInt(res.getString("prop_id")));
                pro.setProp_nombre(res.getString("prop_nombre"));
                pro.setProp_direccion(res.getString("prop_direccion"));
                pro.setProp_saldo(res.getFloat("prop_saldo"));
                pro.setProp_numcontacto(Integer.parseInt(res.getString("prop_numcontacto")));
                pro.setProp_cirut(res.getString("prop_cirut"));
                pro.setProp_observaciones(res.getString("prop_observaciones"));
                lista.add(pro);
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return lista;
    }

    public static List<d_propietario> listarpropietariospordireccion() throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        d_propietario pro = null;
        Statement st;
        ResultSet res;
        List<d_propietario> lista = new ArrayList<d_propietario>();

        try {
            st = c.createStatement();
            res = st.executeQuery("select * from propietarios order by prop_direccion");
            while (res.next()) {
                pro = new d_propietario();
                pro.setProp_id(Integer.parseInt(res.getString("prop_id")));
                pro.setProp_nombre(res.getString("prop_nombre"));
                pro.setProp_direccion(res.getString("prop_direccion"));
                pro.setProp_saldo(res.getFloat("prop_saldo"));
                pro.setProp_numcontacto(Integer.parseInt(res.getString("prop_numcontacto")));
                pro.setProp_cirut(res.getString("prop_cirut"));
                pro.setProp_observaciones(res.getString("prop_observaciones"));
                lista.add(pro);
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return lista;
    }

    public static JasperViewer rep_listapropietarios() throws Exception {
        //Connection c;
        //p_conexion conex = p_conexion.getInstancia();
        //c = conex.crearconexion();
        String DRIVER = "com.mysql.jdbc.Driver";
        String RUTA = "jdbc:mysql://localhost/inmobiliaria";
        String USER = "root";
        String PASSWORD = "";
        java.sql.Connection CONEXION;

        Class.forName(DRIVER);
        CONEXION = DriverManager.getConnection(RUTA, USER, PASSWORD);
        //javax.swing.JOptionPane.showMessageDialog(null, "Conexion establecida");

        String template = "report-3.jasper";
        JasperReport reporte = null;
        reporte = (JasperReport) JRLoader.loadObject(template);

        //Map param = new HashMap();
        //param.put("id", 1);
        JasperPrint jasperprint = JasperFillManager.fillReport(reporte, null, CONEXION);
        //donde dice 'null' va el parametro, si es utilizado
        JasperViewer visor = new JasperViewer(jasperprint, false);
        visor.setTitle("Lista de Propietarios");
        return visor;
        //visor.setVisible(true);
    }

    public static void guardarpropietario(d_propietario pro) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        PreparedStatement pst;
        Integer resultado;

        try {
            pst = c.prepareStatement("Update propietarios set prop_nombre=?, prop_direccion=?, prop_saldo=?, prop_numcontacto=?, prop_cirut=?, prop_observaciones=? Where prop_id=?");
            pst.setString(1, pro.getProp_nombre());
            pst.setString(2, pro.getProp_direccion());
            pst.setFloat(3, pro.getProp_saldo());
            pst.setInt(4, pro.getProp_numcontacto());
            pst.setString(5, pro.getProp_cirut());
            pst.setString(6, pro.getProp_observaciones());
            pst.setInt(7, pro.getProp_id());
            resultado = pst.executeUpdate();
            if (resultado == 0) {
                pst = c.prepareStatement("Insert Into propietarios Values (?,?,?,?,?,?,?)");
                pst.setInt(1, pro.getProp_id());
                pst.setString(2, pro.getProp_nombre());
                pst.setString(3, pro.getProp_direccion());
                pst.setFloat(4, pro.getProp_saldo());
                pst.setInt(5, pro.getProp_numcontacto());
                pst.setString(6, pro.getProp_cirut());
                pst.setString(7, pro.getProp_observaciones());
                pst.executeUpdate();
            }
        } catch (SQLException ex) {
            if (ex.getMessage().equals("Los datos de cadena o binarios se truncarían.")) {
                throw new Exception("revise campos, ya que al menos uno supera el limite de caracteres");
            }

        }
        c.close();
    }

    public static List<d_propietario> listarsobregirospropietarios() throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        d_propietario pro = null;
        Statement st;
        ResultSet res;
        List<d_propietario> lista = new ArrayList<d_propietario>();

        try {
            st = c.createStatement();
            res = st.executeQuery("select prop_id, prop_nombre, prop_saldo from propietarios where prop_saldo < 0 order by prop_id");
            while (res.next()) {
                pro = new d_propietario();
                pro.setProp_id(Integer.parseInt(res.getString("prop_id")));
                pro.setProp_nombre(res.getString("prop_nombre"));
                pro.setProp_saldo(res.getFloat("prop_saldo"));
                lista.add(pro);
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return lista;
    }

    public static void actualizarsaldoprop(Integer id, Float saldo) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        PreparedStatement pst;

        try {
            pst = c.prepareStatement("update propietarios set prop_saldo='" + saldo + "' where prop_id='" + id + "'");
            pst.executeUpdate();
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        c.close();
    }
}
