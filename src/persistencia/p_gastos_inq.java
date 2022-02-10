/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import dominio.d_gastos_inq;
import dominio.d_movimiento;
import dominio.d_propietario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gonzalo
 */
public class p_gastos_inq {

    public static d_gastos_inq buscargastoporinqpendientes(d_gastos_inq gas) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        d_gastos_inq gas1 = null;
        Statement st;
        ResultSet res;

        try {
            st = c.createStatement();
            res = st.executeQuery("select * from gastos_inq where prop_id='" + gas.getProp_id() + "'and inq_casa='" + gas.getInq_casa() + "' and detalle='" + gas.getDetalle() + "' and mqp='" + gas.getMqp() + "' and aqp='" + gas.getAqp() + "' and estado=0");
            while (res.next()) {
                gas1 = new d_gastos_inq();
                gas1.setProp_id(Integer.parseInt(res.getString("prop_id")));
                gas1.setInq_casa(Integer.parseInt(res.getString("inq_casa")));
                gas1.setImporte(res.getFloat("importe"));
                gas1.setDetalle(res.getString("detalle"));
                gas1.setAqp(Integer.parseInt(res.getString("aqp")));
                gas1.setMqp(Integer.parseInt(res.getString("mqp")));
                gas1.setEstado(Integer.parseInt(res.getString("estado")));
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return gas1;
    }

    public static d_gastos_inq buscargastoalquiler(d_gastos_inq gas) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        d_gastos_inq gas1 = null;
        Statement st;
        ResultSet res;

        try {
            st = c.createStatement();
            res = st.executeQuery("select * from gastos_inq where prop_id='" + gas.getProp_id() + "'and inq_casa='" + gas.getInq_casa() + "' and detalle LIKE '%ALQUILER%' and mqp='" + gas.getMqp() + "' and aqp='" + gas.getAqp() + "'");
            while (res.next()) {
                gas1 = new d_gastos_inq();
                gas1.setProp_id(Integer.parseInt(res.getString("prop_id")));
                gas1.setInq_casa(Integer.parseInt(res.getString("inq_casa")));
                gas1.setImporte(res.getFloat("importe"));
                gas1.setDetalle(res.getString("detalle"));
                gas1.setAqp(Integer.parseInt(res.getString("aqp")));
                gas1.setMqp(Integer.parseInt(res.getString("mqp")));
                gas1.setEstado(Integer.parseInt(res.getString("estado")));
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return gas1;
    }

    public static d_gastos_inq buscargasto_entregaacuenta(d_gastos_inq gas) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        d_gastos_inq gas1 = null;
        Statement st;
        ResultSet res;

        try {
            st = c.createStatement();
            res = st.executeQuery("select TOP(1) * from gastos_inq where prop_id='" + gas.getProp_id() + "'and inq_casa='" + gas.getInq_casa() + "' and (detalle='SALDO ALQUILER' or detalle='ALQUILER') and mqp='" + gas.getMqp() + "' and aqp='" + gas.getAqp() + "' and estado=0");
            while (res.next()) {
                gas1 = new d_gastos_inq();
                gas1.setProp_id(Integer.parseInt(res.getString("prop_id")));
                gas1.setInq_casa(Integer.parseInt(res.getString("inq_casa")));
                gas1.setImporte(res.getFloat("importe"));
                gas1.setDetalle(res.getString("detalle"));
                gas1.setAqp(Integer.parseInt(res.getString("aqp")));
                gas1.setMqp(Integer.parseInt(res.getString("mqp")));
                gas1.setEstado(Integer.parseInt(res.getString("estado")));
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return gas1;
    }

    public static d_gastos_inq buscaralquilermesmasalto(d_gastos_inq gas) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        d_gastos_inq gas1 = null;
        Statement st;
        ResultSet res;
        int i = 0;

        try {
            st = c.createStatement();
            res = st.executeQuery("SELECT MAX(aqp)as aqp,MAX(mqp)as mqp,estado,importe FROM gastos_inq where prop_id='" + gas.getProp_id() + "'and inq_casa='" + gas.getInq_casa() + "'\n"
                    + " and detalle ='ALQUILER MES' GROUP BY aqp,estado,importe order by aqp desc");
            while (res.next()) {
                if (i == 0) {
                    gas1 = new d_gastos_inq();
                    gas1.setProp_id(gas.getProp_id());
                    gas1.setInq_casa(gas.getInq_casa());
                    gas1.setAqp(Integer.parseInt(res.getString("aqp")));
                    gas1.setMqp(Integer.parseInt(res.getString("mqp")));
                    gas1.setEstado(Integer.parseInt(res.getString("estado")));
                    gas1.setImporte(res.getFloat("importe"));
                    i = i + 1;
                } else {
                    break;
                }
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return gas1;
    }

    public static d_gastos_inq buscarconvenioreintegro(d_gastos_inq gas) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        d_gastos_inq gas1 = null;
        Statement st;
        ResultSet res;

        try {
            String consulta = ("select * from gastos_inq where prop_id='" + gas.getProp_id() + "'and inq_casa='" + gas.getInq_casa() + "' and mqp='" + gas.getMqp() + "' and aqp='" + gas.getAqp() + "' and estado=2");
            st = c.createStatement();
            res = st.executeQuery(consulta);
            while (res.next()) {
                gas1 = new d_gastos_inq();
                gas1.setProp_id(Integer.parseInt(res.getString("prop_id")));
                gas1.setInq_casa(Integer.parseInt(res.getString("inq_casa")));
                gas1.setImporte(res.getFloat("importe"));
                gas1.setDetalle(res.getString("detalle"));
                gas1.setAqp(Integer.parseInt(res.getString("aqp")));
                gas1.setMqp(Integer.parseInt(res.getString("mqp")));
                gas1.setEstado(Integer.parseInt(res.getString("estado")));
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return gas1;
    }

    public static d_gastos_inq buscarconvenioreintegrocompleto(d_gastos_inq gas) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        d_gastos_inq gas1 = null;
        Statement st;
        ResultSet res;

        try {
            String consulta = ("select * from gastos_inq where prop_id='" + gas.getProp_id() + "'and inq_casa='" + gas.getInq_casa() + "' and mqp='" + gas.getMqp() + "' and aqp='" + gas.getAqp() + "' "
                    + "and detalle='" + gas.getDetalle() + "'and importe='" + gas.getImporte() + "'and estado='" + gas.getEstado() + "'");
            st = c.createStatement();
            res = st.executeQuery(consulta);
            while (res.next()) {
                gas1 = new d_gastos_inq();
                gas1.setProp_id(Integer.parseInt(res.getString("prop_id")));
                gas1.setInq_casa(Integer.parseInt(res.getString("inq_casa")));
                gas1.setImporte(res.getFloat("importe"));
                gas1.setDetalle(res.getString("detalle"));
                gas1.setAqp(Integer.parseInt(res.getString("aqp")));
                gas1.setMqp(Integer.parseInt(res.getString("mqp")));
                gas1.setEstado(Integer.parseInt(res.getString("estado")));
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return gas1;
    }

    public static d_gastos_inq buscarconvenioreintegroabonado(d_gastos_inq gas) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        d_gastos_inq gas1 = null;
        Statement st;
        ResultSet res;

        try {
            String consulta = ("select * from gastos_inq where prop_id='" + gas.getProp_id() + "' and inq_casa='" + gas.getInq_casa() + "' and detalle='" + gas.getDetalle() + "' and mqp='" + gas.getMqp() + "' and aqp='" + gas.getAqp() + "' and estado=3");
            st = c.createStatement();
            res = st.executeQuery(consulta);
            while (res.next()) {
                gas1 = new d_gastos_inq();
                gas1.setProp_id(Integer.parseInt(res.getString("prop_id")));
                gas1.setInq_casa(Integer.parseInt(res.getString("inq_casa")));
                gas1.setImporte(res.getFloat("importe"));
                gas1.setDetalle(res.getString("detalle"));
                gas1.setAqp(Integer.parseInt(res.getString("aqp")));
                gas1.setMqp(Integer.parseInt(res.getString("mqp")));
                gas1.setEstado(Integer.parseInt(res.getString("estado")));
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return gas1;
    }

    public static d_gastos_inq buscaralquilermes(d_gastos_inq gas) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        d_gastos_inq gas1 = null;
        Statement st;
        ResultSet res;

        try {
            st = c.createStatement();
            res = st.executeQuery("select * from gastos_inq where prop_id='" + gas.getProp_id() + "'and inq_casa='" + gas.getInq_casa() + "' and (detalle='" + gas.getDetalle() + "' or detalle='ALQUILER MES' or detalle='ALQUILER') and mqp='" + gas.getMqp() + "' and aqp='" + gas.getAqp() + "'");
            while (res.next()) {
                gas1 = new d_gastos_inq();
                gas1.setProp_id(Integer.parseInt(res.getString("prop_id")));
                gas1.setInq_casa(Integer.parseInt(res.getString("inq_casa")));
                gas1.setImporte(res.getFloat("importe"));
                gas1.setDetalle(res.getString("detalle"));
                gas1.setAqp(Integer.parseInt(res.getString("aqp")));
                gas1.setMqp(Integer.parseInt(res.getString("mqp")));
                gas1.setEstado(Integer.parseInt(res.getString("estado")));
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return gas1;
    }

    public static d_gastos_inq buscaralquilerdias(d_gastos_inq gas) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        d_gastos_inq gas1 = null;
        Statement st;
        ResultSet res;

        try {
            st = c.createStatement();
            res = st.executeQuery("select * from gastos_inq where prop_id='" + gas.getProp_id() + "'and inq_casa='" + gas.getInq_casa() + "' and detalle='" + gas.getDetalle() + "' and mqp='" + gas.getMqp() + "' and aqp='" + gas.getAqp() + "'");
            while (res.next()) {
                gas1 = new d_gastos_inq();
                gas1.setProp_id(Integer.parseInt(res.getString("prop_id")));
                gas1.setInq_casa(Integer.parseInt(res.getString("inq_casa")));
                gas1.setImporte(res.getFloat("importe"));
                gas1.setDetalle(res.getString("detalle"));
                gas1.setAqp(Integer.parseInt(res.getString("aqp")));
                gas1.setMqp(Integer.parseInt(res.getString("mqp")));
                gas1.setEstado(Integer.parseInt(res.getString("estado")));
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return gas1;
    }

    public static d_gastos_inq buscargastoporinqpagado(d_gastos_inq gas) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        d_gastos_inq gas1 = null;
        Statement st;
        ResultSet res;

        try {
            st = c.createStatement();
            res = st.executeQuery("select * from gastos_inq where prop_id='" + gas.getProp_id() + "'and inq_casa='" + gas.getInq_casa() + "' and (detalle='" + gas.getDetalle() + "' or detalle='ALQUILER' or detalle='SALDO ALQUILER') and mqp='" + gas.getMqp() + "' and aqp='" + gas.getAqp() + "' and estado=1");
            while (res.next()) {
                gas1 = new d_gastos_inq();
                gas1.setProp_id(Integer.parseInt(res.getString("prop_id")));
                gas1.setInq_casa(Integer.parseInt(res.getString("inq_casa")));
                gas1.setImporte(res.getFloat("importe"));
                gas1.setDetalle(res.getString("detalle"));
                gas1.setAqp(Integer.parseInt(res.getString("aqp")));
                gas1.setMqp(Integer.parseInt(res.getString("mqp")));
                gas1.setEstado(Integer.parseInt(res.getString("estado")));
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return gas1;
    }

    public static d_gastos_inq buscargastoporinqnopendientes(d_gastos_inq gas) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        d_gastos_inq gas1 = null;
        Statement st;
        ResultSet res;

        try {
            st = c.createStatement();
            res = st.executeQuery("select prop_id,inq_casa,sum(importe)as importe,detalle,aqp,mqp,estado from gastos_inq where prop_id='" + gas.getProp_id() + "'and inq_casa='" + gas.getInq_casa() + "' and detalle='" + gas.getDetalle() + "' and mqp='" + gas.getMqp() + "' and aqp='" + gas.getAqp() + "' and estado=1 group by prop_id,inq_casa,detalle,aqp,mqp,estado");
            while (res.next()) {
                gas1 = new d_gastos_inq();
                gas1.setProp_id(Integer.parseInt(res.getString("prop_id")));
                gas1.setInq_casa(Integer.parseInt(res.getString("inq_casa")));
                gas1.setImporte(res.getFloat("importe"));
                gas1.setDetalle(res.getString("detalle"));
                gas1.setAqp(Integer.parseInt(res.getString("aqp")));
                gas1.setMqp(Integer.parseInt(res.getString("mqp")));
                gas1.setEstado(Integer.parseInt(res.getString("estado")));
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return gas1;
    }

    public static List<d_gastos_inq> listargastosporinqpendientes(Integer prop_id, Integer inq_casa) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        d_gastos_inq gas = null;
        Statement st;
        ResultSet res;
        List<d_gastos_inq> lista = new ArrayList<d_gastos_inq>();

        try {
            st = c.createStatement();
            res = st.executeQuery("select * from gastos_inq where prop_id='" + prop_id + "'and inq_casa='" + inq_casa + "' order by aqp,mqp asc");
            while (res.next()) {
                gas = new d_gastos_inq();
                gas.setProp_id(Integer.parseInt(res.getString("prop_id")));
                gas.setInq_casa(Integer.parseInt(res.getString("inq_casa")));
                gas.setImporte(res.getFloat("importe"));
                gas.setDetalle(res.getString("detalle"));
                gas.setAqp(Integer.parseInt(res.getString("aqp")));
                gas.setMqp(Integer.parseInt(res.getString("mqp")));
                gas.setEstado(Integer.parseInt(res.getString("estado")));
                lista.add(gas);
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return lista;
    }

    public static List<d_gastos_inq> listarconveniosreintegros() throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        d_gastos_inq gas = null;
        Statement st;
        ResultSet res;
        List<d_gastos_inq> lista = new ArrayList<d_gastos_inq>();

        try {
            String consulta = ("select * from gastos_inq where estado=2 or estado=3 order by prop_id");
            st = c.createStatement();
            res = st.executeQuery(consulta);
            while (res.next()) {
                gas = new d_gastos_inq();
                gas.setProp_id(Integer.parseInt(res.getString("prop_id")));
                gas.setInq_casa(Integer.parseInt(res.getString("inq_casa")));
                gas.setImporte(res.getFloat("importe"));
                gas.setDetalle(res.getString("detalle"));
                gas.setAqp(Integer.parseInt(res.getString("aqp")));
                gas.setMqp(Integer.parseInt(res.getString("mqp")));
                gas.setEstado(Integer.parseInt(res.getString("estado")));
                lista.add(gas);
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return lista;
    }

    public static List<d_gastos_inq> listarconveniosreintegrosporinq(int prop_id, int inq_casa) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        d_gastos_inq gas = null;
        Statement st;
        ResultSet res;
        List<d_gastos_inq> lista = new ArrayList<d_gastos_inq>();

        try {
            String consulta = ("select * from gastos_inq where prop_id='" + prop_id + "'and inq_casa='" + inq_casa + "' and (estado=2 or estado=3) order by prop_id");
            st = c.createStatement();
            res = st.executeQuery(consulta);
            while (res.next()) {
                gas = new d_gastos_inq();
                gas.setProp_id(Integer.parseInt(res.getString("prop_id")));
                gas.setInq_casa(Integer.parseInt(res.getString("inq_casa")));
                gas.setImporte(res.getFloat("importe"));
                gas.setDetalle(res.getString("detalle"));
                gas.setAqp(Integer.parseInt(res.getString("aqp")));
                gas.setMqp(Integer.parseInt(res.getString("mqp")));
                gas.setEstado(Integer.parseInt(res.getString("estado")));
                lista.add(gas);
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return lista;
    }

    public static List<d_gastos_inq> cargarlistaconveniosreintegrossinaplicar(d_gastos_inq gas1) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        d_gastos_inq gas = null;
        Statement st;
        ResultSet res;
        List<d_gastos_inq> lista = new ArrayList<d_gastos_inq>();

        try {
            String consulta = ("select * from gastos_inq where prop_id='" + gas1.getProp_id() + "'and inq_casa='" + gas1.getInq_casa() + "' and estado=2");
            st = c.createStatement();
            res = st.executeQuery(consulta);
            while (res.next()) {
                gas = new d_gastos_inq();
                gas.setProp_id(Integer.parseInt(res.getString("prop_id")));
                gas.setInq_casa(Integer.parseInt(res.getString("inq_casa")));
                gas.setImporte(res.getFloat("importe"));
                gas.setDetalle(res.getString("detalle"));
                gas.setAqp(Integer.parseInt(res.getString("aqp")));
                gas.setMqp(Integer.parseInt(res.getString("mqp")));
                gas.setEstado(Integer.parseInt(res.getString("estado")));
                lista.add(gas);
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return lista;
    }

    public static void guardargastoinq(d_gastos_inq gas) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        PreparedStatement pst;
        Integer resultado;

        try {
            pst = c.prepareStatement("Update gastos_inq set importe=?, estado=? Where prop_id=? and inq_casa=? and aqp=? and mqp=? and detalle=?");
            pst.setFloat(1, gas.getImporte());
            pst.setInt(2, gas.getEstado());
            pst.setInt(3, gas.getProp_id());
            pst.setInt(4, gas.getInq_casa());
            pst.setInt(5, gas.getAqp());
            pst.setInt(6, gas.getMqp());
            pst.setString(7, gas.getDetalle());
            resultado = pst.executeUpdate();
            if (resultado == 0) {
                pst = c.prepareStatement("Insert Into gastos_inq Values (?,?,?,?,?,?,?)");
                pst.setInt(1, gas.getProp_id());
                pst.setInt(2, gas.getInq_casa());
                pst.setFloat(3, gas.getImporte());
                pst.setString(4, gas.getDetalle());
                pst.setInt(5, gas.getAqp());
                pst.setInt(6, gas.getMqp());
                pst.setInt(7, gas.getEstado());
                pst.executeUpdate();
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        c.close();
    }

    public static void actualizarimporte(d_gastos_inq gas) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        PreparedStatement pst;

        try {
            pst = c.prepareStatement("Update gastos_inq set importe=?, estado=? Where prop_id=? and inq_casa=? and aqp=? and mqp=? and detalle=?");
            pst.setFloat(1, gas.getImporte());
            pst.setInt(2, gas.getEstado());
            pst.setInt(3, gas.getProp_id());
            pst.setInt(4, gas.getInq_casa());
            pst.setInt(5, gas.getAqp());
            pst.setInt(6, gas.getMqp());
            pst.setString(7, gas.getDetalle());
            pst.executeUpdate();
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        c.close();
    }

    public static void actualizarimporte_y_detalle_luego_de_entrega_alquiler(d_gastos_inq gas) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        PreparedStatement pst;

        try {
            pst = c.prepareStatement("Update gastos_inq set detalle='SALDO ALQUILER', importe=?, estado=? Where prop_id=? and inq_casa=? and aqp=? and mqp=? and (detalle='ALQUILER' or detalle='SALDO ALQUILER')");
            pst.setFloat(1, gas.getImporte());
            pst.setInt(2, gas.getEstado());
            pst.setInt(3, gas.getProp_id());
            pst.setInt(4, gas.getInq_casa());
            pst.setInt(5, gas.getAqp());
            pst.setInt(6, gas.getMqp());
            pst.executeUpdate();
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        c.close();
    }

    public static void actualizarimporte_luegodeentrega(d_gastos_inq gas) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        PreparedStatement pst;

        try {
            pst = c.prepareStatement("Update TOP (1) gastos_inq set importe=?, estado=0 Where prop_id=? and inq_casa=? and aqp=? and mqp=? and (detalle='SALDO ALQUILER' or detalle='ALQUILER')");
            pst.setFloat(1, gas.getImporte());
            pst.setInt(2, gas.getProp_id());
            pst.setInt(3, gas.getInq_casa());
            pst.setInt(4, gas.getAqp());
            pst.setInt(5, gas.getMqp());
            pst.executeUpdate();
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        c.close();
    }

    public static void actualizarestadosaldo(d_gastos_inq gas) throws Exception {//estado 1 para pagar 
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        PreparedStatement pst;

        try {
            pst = c.prepareStatement("Update gastos_inq set estado=? Where prop_id=? and inq_casa=? and aqp=? and mqp=? and (detalle='SALDO ALQUILER' or detalle='ALQUILER')");
            pst.setInt(1, gas.getEstado());
            pst.setInt(2, gas.getProp_id());
            pst.setInt(3, gas.getInq_casa());
            pst.setInt(4, gas.getAqp());
            pst.setInt(5, gas.getMqp());
            pst.executeUpdate();
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        c.close();
    }

    public static Integer cantsaldospendientes(Integer prop_id, Integer inq_casa) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        Integer cant = 0;
        Statement st;
        ResultSet res;

        try {
            st = c.createStatement();
            res = st.executeQuery("select count(*)as cant from gastos_inq where prop_id='" + prop_id + "'and inq_casa='" + inq_casa + "' and estado=0");
            while (res.next()) {
                cant = (Integer.parseInt(res.getString("cant")));
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return cant;
    }

    public static void actualizarsaldoalquiler(d_gastos_inq gas) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        Statement st;

        try {
            d_gastos_inq aux = new d_gastos_inq();
            aux = p_gastos_inq.buscargastoporinqpendientes(gas);
            d_movimiento mov = new d_movimiento();
            mov.setProp_id(gas.getProp_id());
            mov.setInq_casa(gas.getInq_casa());
            mov.setAqp(gas.getAqp());
            mov.setMqp(gas.getMqp());
            mov = mov.buscarcuotaacuenta(mov);
            if (mov == null) {
                st = c.createStatement();
                st.execute("delete from gastos_inq where prop_id=" + gas.getProp_id() + "and inq_casa=" + gas.getInq_casa() + "and detalle='SALDO ALQUILER' and aqp=" + gas.getAqp() + "and mqp=" + gas.getMqp());
                return;
            }
            float monto = aux.getImporte() + gas.getImporte();
            st = c.createStatement();
            st.execute("update gastos_inq set importe=" + monto + " where prop_id=" + gas.getProp_id() + "and inq_casa=" + gas.getInq_casa() + "and detalle='SALDO ALQUILER' and aqp=" + gas.getAqp() + "and mqp=" + gas.getMqp());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        c.close();
    }

    public static void eliminargastos(Integer prop_id, Integer inq_casa) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        Statement st;

        try {
            st = c.createStatement();
            st.execute("delete from gastos_inq where prop_id='" + prop_id + "'and inq_casa='" + inq_casa + "'");
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        c.close();
    }

    public static void cambiarestadoconvenioreintegro(d_gastos_inq gas) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        Statement st;

        //estado=2 no pago
        //estado=3 pago
        try {
            st = c.createStatement();
            st.execute("update gastos_inq set estado='" + gas.getEstado() + "' where prop_id='" + gas.getProp_id() + "' "
                    + "and inq_casa='" + gas.getInq_casa() + "' and mqp='" + gas.getMqp() + "' and aqp='" + gas.getAqp() + "' and detalle='" + gas.getDetalle() + "'");
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        c.close();
    }

    public static void eliminarconvenioreintegro(d_gastos_inq gas) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        Statement st;

        try {
            st = c.createStatement();
            st.execute("delete from gastos_inq where prop_id='" + gas.getProp_id() + "'and inq_casa='" + gas.getInq_casa() + "' and mqp='" + gas.getMqp() + "' and aqp='" + gas.getAqp() + "' and estado=2");
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        c.close();
    }

    public static void eliminargastossinimporte() throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        Statement st;

        try {
            st = c.createStatement();
            st.execute("delete from gastos_inq where importe=0");
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        c.close();
    }

    public static Boolean actualizarestadoauno(d_gastos_inq gas) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        Statement st;
        Boolean resultado = false;

        try {
            st = c.createStatement();
            int rs = st.executeUpdate("delete from gastos_inq where prop_id='" + gas.getProp_id() + "'and inq_casa='" + gas.getInq_casa() + "' and mqp='" + gas.getMqp() + "' and aqp='" + gas.getAqp() + "' and detalle='" + gas.getDetalle() + "'");
            if (rs == 1) {
                resultado = true;
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        c.close();
        return resultado;
    }
}
