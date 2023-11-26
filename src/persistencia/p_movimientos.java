/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import dominio.d_movimiento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import presentacion.paneles.p_control;

/**
 *
 * @author Gonzalo
 */
public class p_movimientos {

    public static Integer ultimoid() throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        ResultSet res;
        Statement st;
        Integer idobtenido;

        idobtenido = -1;
        try {
            st = c.createStatement();
            res = st.executeQuery("SELECT MAX(id)as id FROM movimientos");
            while (res.next()) {
                idobtenido = (res.getInt("id"));
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return idobtenido;
    }

    public static Integer minimoid() throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        ResultSet res;
        Statement st;
        Integer idobtenido;

        idobtenido = -1;
        try {
            st = c.createStatement();
            res = st.executeQuery("SELECT MIN(id)as id FROM movimientos");
            while (res.next()) {
                idobtenido = (res.getInt("id"));
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return idobtenido;
    }

    public static Float obtenertotalirpfpormes(Integer prop_id, Integer mqp, Integer aqp) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        Statement st;
        ResultSet res;
        Float monto = 0.0f;

        try {
            st = c.createStatement();
            String consulta = "SELECT sum(irpf) as monto FROM movimientos where prop_id='" + prop_id + "' and irpftipo = 'SI' and MONTH(fecha) = '" + mqp + "' and YEAR(fecha) = '" + aqp + "'";
            res = st.executeQuery(consulta);
            while (res.next()) {
                monto = res.getFloat("monto");
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return monto;
    }

    public static Boolean existesalidaanda(d_movimiento mov) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        Statement st;
        ResultSet res;

        try {
            st = c.createStatement();
            String consulta = "SELECT * FROM movimientos where "
                    + "prop_id='" + mov.getProp_id() + "' and inq_casa = '" + mov.getInq_casa() + "' and "
                    + "mqp = '" + mov.getMqp() + "' and aqp = '" + mov.getAqp() + "' and salida > 0"
                    + "and detalle='ANDA'";
            res = st.executeQuery(consulta);
            while (res.next()) {
                return true;
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return false;
    }

    public static int cantidad_movimientos_alquiler_para_una_fecha(d_movimiento mov) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        Statement st;
        ResultSet res;

        try {
            st = c.createStatement();
            String consulta = "SELECT count (id)as id FROM movimientos where "
                    + "prop_id='" + mov.getProp_id() + "' and inq_casa = '" + mov.getInq_casa() + "' and "
                    + "mqp = '" + mov.getMqp() + "' and aqp = '" + mov.getAqp() + "' and detalle like '%ALQUILER%'";
            res = st.executeQuery(consulta);
            while (res.next()) {
                return res.getInt("id");
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return -1;
    }

    public static Float totalentregassaldo(int prop_id, int inq_casa, int mqp, int aqp) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        ResultSet res;
        Statement st;
        Float total = 0f;

        try {
            st = c.createStatement();
            res = st.executeQuery("select sum(entrada) as entrada from movimientos where prop_id='" + prop_id + "'and inq_casa='" + inq_casa + "'and "
                    + "(detalle='A CUENTA ALQUILER' or detalle='A CUENTA: ALQUILER' or detalle='SALDO ALQUILER' or detalle='ALQUILER' or detalle='A CUENTA: SALDO ALQUILER DIAS') and aqp='" + aqp + "'and mqp='" + mqp + "'");
            while (res.next()) {
                total = (res.getFloat("entrada"));
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return total;
    }

    public static Float totalentregassaldo_no_alquiler(int prop_id, int inq_casa, int mqp, int aqp, String detalle) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        ResultSet res;
        Statement st;
        Float total = 0f;

        try {
            st = c.createStatement();
            res = st.executeQuery("select sum(entrada) as entrada from movimientos where prop_id='" + prop_id + "'and inq_casa='" + inq_casa + "'and "
                    + "(detalle='A CUENTA: " + detalle + "' or detalle='A CUENTA: SALDO " + detalle + "' or detalle='SALDO " + detalle + "') and aqp='" + aqp + "'and mqp='" + mqp + "'");
            while (res.next()) {//A CUENTA: TASA MUNICIPAL
                total = (res.getFloat("entrada"));
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return total;
    }

    public static Integer ultimoidprop(Integer prop_id) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        ResultSet res;
        Statement st;
        Integer idobtenido;

        idobtenido = -1;
        try {
            st = c.createStatement();
            res = st.executeQuery("SELECT MAX(id)as id FROM movimientos where prop_id=" + prop_id);
            while (res.next()) {
                idobtenido = (res.getInt("id"));
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return idobtenido;
    }

    public static d_movimiento buscarmovimiento(Integer id) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        Statement st;
        ResultSet res;
        d_movimiento mov = null;

        try {
            st = c.createStatement();
            res = st.executeQuery("select * from movimientos where id=" + id);
            while (res.next()) {
                mov = new d_movimiento();
                mov.setId(res.getInt("id"));
                mov.setProp_id(res.getInt("prop_id"));
                mov.setInq_casa(res.getInt("inq_casa"));
                mov.setMqp(res.getInt("mqp"));
                mov.setAqp(res.getInt("aqp"));
                mov.setDetalle(res.getString("detalle"));
                mov.setEntrada(res.getFloat("entrada"));
                mov.setSalida(res.getFloat("salida"));
                mov.setComision(res.getFloat("comision"));
                mov.setIva(res.getFloat("iva"));
                mov.setTipo(res.getString("tipo"));
                mov.setFecha(res.getDate("fecha"));
                mov.setIrpf(res.getFloat("irpf"));
                mov.setIrpftipo(res.getString("irpftipo"));
                mov.setTipopago(res.getString("tipopago"));
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return mov;
    }

    public static Boolean tiene_recibo(Integer id) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        Statement st;
        ResultSet res;

        try {
            st = c.createStatement();
            res = st.executeQuery("select * from movimientos where id=" + id);
            while (res.next()) {
                return true;
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return false;
    }

    public static Integer cantmovimientosdeprop(Integer prop_id) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        Statement st;
        ResultSet res;
        Integer cant = -1;

        try {
            st = c.createStatement();
            res = st.executeQuery("select count(id)as cantidad from movimientos where prop_id=" + prop_id);
            while (res.next()) {
                cant = (res.getInt("cantidad"));
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return cant;
    }

    public static Integer cantmovimientosalquiler(Integer prop_id, Integer inq_casa) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        Statement st;
        ResultSet res;
        Integer cant = -1;

        try {
            st = c.createStatement();
            res = st.executeQuery("select count(id)as cantidad from movimientos where prop_id='" + prop_id + "'and inq_casa='" + inq_casa + "'and detalle like '%ALQUILER%'");
            while (res.next()) {
                cant = (res.getInt("cantidad"));
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return cant;
    }

    public static Integer cantmovimientos(Integer prop_id, Integer inq_casa) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        Statement st;
        ResultSet res;
        Integer cant = -1;

        try {
            st = c.createStatement();
            res = st.executeQuery("select count(*)as cantidad from movimientos where prop_id='" + prop_id + "'and inq_casa='" + inq_casa + "'");
            while (res.next()) {
                cant = (res.getInt("cantidad"));
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return cant;
    }

    public static void guardarprimermovimiento(Integer prop_id, Float saldo) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        PreparedStatement pst;
        java.sql.Date sqld = ASqlDate(new Date());

        try {
            pst = c.prepareStatement("Insert Into movimientos Values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            pst.setInt(1, prop_id);
            pst.setInt(2, 0);
            pst.setInt(3, 0);
            pst.setInt(4, 0);
            pst.setString(5, "SALDO MIGRACION");
            pst.setFloat(6, saldo); //saldo
            pst.setInt(7, 0);
            pst.setInt(8, 0);
            pst.setInt(9, 0);
            pst.setString(10, "");
            pst.setDate(11, sqld);
            pst.setFloat(12, 0);
            pst.setString(13, "");
            pst.setString(14, "");
            pst.executeUpdate();
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        c.close();
    }

    public static d_movimiento buscarsaldoalquiler(d_movimiento pmov) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        Statement st;
        ResultSet res;
        d_movimiento mov = null;

        try {
            st = c.createStatement();
            res = st.executeQuery("select * from movimientos where prop_id='" + pmov.getProp_id() + "'and inq_casa='" + pmov.getInq_casa() + "'and (detalle='SALDO ALQUILER' or detalle='ALQUILER') and aqp='" + pmov.getAqp() + "'and mqp='" + pmov.getMqp() + "'");
            while (res.next()) {
                mov = new d_movimiento();
                mov.setId(res.getInt("id"));
                mov.setProp_id(res.getInt("prop_id"));
                mov.setInq_casa(res.getInt("inq_casa"));
                mov.setMqp(res.getInt("mqp"));
                mov.setAqp(res.getInt("aqp"));
                mov.setDetalle(res.getString("detalle"));
                mov.setEntrada(res.getFloat("entrada"));
                mov.setSalida(res.getFloat("salida"));
                mov.setComision(res.getFloat("comision"));
                mov.setIva(res.getFloat("iva"));
                mov.setTipo(res.getString("tipo"));
                mov.setFecha(res.getDate("fecha"));
                mov.setIrpf(res.getFloat("irpf"));
                mov.setTipopago(res.getString("tipopago"));
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return mov;
    }

    public static d_movimiento buscarcuotapaga(d_movimiento mov) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        Statement st;
        ResultSet res;
        //java.sql.Date sqld1 = ASqlDate(mov.getFecha());
        String consulta = "select * from movimientos where prop_id='" + mov.getProp_id() + "'and inq_casa='" + mov.getInq_casa() + "'and detalle='ALQUILER' and aqp='" + mov.getAqp() + "'and mqp='" + mov.getMqp() + "'";
        d_movimiento mov1 = null;

        try {
            st = c.createStatement();
            res = st.executeQuery(consulta);
            while (res.next()) {
                mov1 = new d_movimiento();
                mov1.setId(res.getInt("id"));
                mov1.setProp_id(res.getInt("prop_id"));
                mov1.setInq_casa(res.getInt("inq_casa"));
                mov1.setMqp(res.getInt("mqp"));
                mov1.setAqp(res.getInt("aqp"));
                mov1.setDetalle(res.getString("detalle"));
                mov1.setEntrada(res.getFloat("entrada"));
                mov1.setSalida(res.getFloat("salida"));
                mov1.setComision(res.getFloat("comision"));
                mov1.setIva(res.getFloat("iva"));
                mov1.setTipo(res.getString("tipo"));
                mov1.setFecha(res.getDate("fecha"));
                mov1.setIrpf(res.getFloat("irpf"));
                mov1.setTipopago(res.getString("tipopago"));
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return mov1;
    }

    public static d_movimiento buscaralquilermesoalquiler(d_movimiento mov, String tabla) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        Statement st;
        ResultSet res;
        //java.sql.Date sqld1 = ASqlDate(mov.getFecha());
        String consulta = "select * from " + tabla + " where prop_id='" + mov.getProp_id() + "'and inq_casa='" + mov.getInq_casa() + "'and (detalle='ALQUILER' or detalle='ALQUILER MES') and aqp='" + mov.getAqp() + "'and mqp='" + mov.getMqp() + "'";
        d_movimiento mov1 = null;

        try {
            st = c.createStatement();
            res = st.executeQuery(consulta);
            while (res.next()) {
                if (tabla.equals("movimientos")) {
                    mov1 = new d_movimiento();
                    mov1.setId(res.getInt("id"));
                    mov1.setProp_id(res.getInt("prop_id"));
                    mov1.setInq_casa(res.getInt("inq_casa"));
                    mov1.setMqp(res.getInt("mqp"));
                    mov1.setAqp(res.getInt("aqp"));
                    mov1.setDetalle(res.getString("detalle"));
                    mov1.setEntrada(res.getFloat("entrada"));
                    mov1.setSalida(res.getFloat("salida"));
                    mov1.setComision(res.getFloat("comision"));
                    mov1.setIva(res.getFloat("iva"));
                    mov1.setTipo(res.getString("tipo"));
                    mov1.setFecha(res.getDate("fecha"));
                    mov1.setIrpf(res.getFloat("irpf"));
                    mov1.setIrpftipo(res.getString("irpftipo"));
                    mov1.setTipopago(res.getString("tipopago"));
                } else {
                    mov1 = new d_movimiento();
                    //mov1.setId(res.getInt("id"));
                    mov1.setProp_id(res.getInt("prop_id"));
                    mov1.setInq_casa(res.getInt("inq_casa"));
                    mov1.setMqp(res.getInt("mqp"));
                    mov1.setAqp(res.getInt("aqp"));
                    mov1.setDetalle(res.getString("detalle"));
                    mov1.setEntrada(res.getFloat("entrada"));
                    //mov1.setSalida(res.getFloat("salida"));
                    //mov1.setComision(res.getFloat("comision"));
                    //mov1.setIva(res.getFloat("iva"));
                    //mov1.setTipo(res.getString("tipo"));
                    mov1.setFecha(res.getDate("fecha"));
                    //mov1.setIrpf(res.getFloat("irpf"));
                    //mov1.setTipopago(res.getString("tipopago"));
                }
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return mov1;
    }

    public static d_movimiento buscarcuotaacuenta(d_movimiento mov) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        Statement st;
        ResultSet res;
        //java.sql.Date sqld1 = ASqlDate(mov.getFecha());
        String consulta = "select * from movimientos where prop_id='" + mov.getProp_id() + "'and inq_casa='" + mov.getInq_casa() + "'and detalle='A CUENTA ALQUILER' and aqp='" + mov.getAqp() + "'and mqp='" + mov.getMqp() + "'";
        d_movimiento mov1 = null;
        Float importe = 0f;

        try {
            st = c.createStatement();
            res = st.executeQuery(consulta);
            while (res.next()) {
                mov1 = new d_movimiento();
                importe = importe + (res.getFloat("entrada"));
                mov1.setId(res.getInt("id"));
                mov1.setProp_id(res.getInt("prop_id"));
                mov1.setInq_casa(res.getInt("inq_casa"));
                mov1.setMqp(res.getInt("mqp"));
                mov1.setAqp(res.getInt("aqp"));
                mov1.setDetalle(res.getString("detalle"));
                mov1.setEntrada(importe);
                mov1.setSalida(res.getFloat("salida"));
                mov1.setComision(res.getFloat("comision"));
                mov1.setIva(res.getFloat("iva"));
                mov1.setTipo(res.getString("tipo"));
                mov1.setFecha(res.getDate("fecha"));
                mov1.setIrpf(res.getFloat("irpf"));
                mov1.setTipopago(res.getString("tipopago"));
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return mov1;
    }

    public static d_movimiento buscarcuotaporanioymes(d_movimiento mov) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        Statement st;
        ResultSet res;
        //java.sql.Date sqld1 = ASqlDate(mov.getFecha());
        String consulta = "select * from movimientos where prop_id='" + mov.getProp_id() + "'and inq_casa='" + mov.getInq_casa() + "'and detalle='SALDO ALQUILER' or detalle='ALQUILER' and aqp='" + mov.getAqp() + "'and mqp='" + mov.getMqp() + "'";
        d_movimiento mov1 = null;

        try {
            st = c.createStatement();
            res = st.executeQuery(consulta);
            while (res.next()) {
                mov1 = new d_movimiento();
                mov1.setId(res.getInt("id"));
                mov1.setProp_id(res.getInt("prop_id"));
                mov1.setInq_casa(res.getInt("inq_casa"));
                mov1.setMqp(res.getInt("mqp"));
                mov1.setAqp(res.getInt("aqp"));
                mov1.setDetalle(res.getString("detalle"));
                mov1.setEntrada(res.getFloat("entrada"));
                mov1.setSalida(res.getFloat("salida"));
                mov1.setComision(res.getFloat("comision"));
                mov1.setIva(res.getFloat("iva"));
                mov1.setTipo(res.getString("tipo"));
                mov1.setFecha(res.getDate("fecha"));
                mov1.setIrpf(res.getFloat("irpf"));
                mov1.setTipopago(res.getString("tipopago"));
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return mov1;
    }

    public static d_movimiento buscarconvenioreintegropago(d_movimiento mov) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        Statement st;
        ResultSet res;
        //java.sql.Date sqld1 = ASqlDate(mov.getFecha());
        String consulta = "select * from movimientos where prop_id='" + mov.getProp_id() + "'and inq_casa='" + mov.getInq_casa() + "'and "
                + "(detalle= 'CONVENIO' or detalle= 'REINTEGRO') and aqp='" + mov.getAqp() + "'and mqp='" + mov.getMqp() + "'";
        d_movimiento mov1 = null;

        try {
            st = c.createStatement();
            res = st.executeQuery(consulta);
            while (res.next()) {
                mov1 = new d_movimiento();
                mov1.setId(res.getInt("id"));
                mov1.setProp_id(res.getInt("prop_id"));
                mov1.setInq_casa(res.getInt("inq_casa"));
                mov1.setMqp(res.getInt("mqp"));
                mov1.setAqp(res.getInt("aqp"));
                mov1.setDetalle(res.getString("detalle"));
                mov1.setEntrada(res.getFloat("entrada"));
                mov1.setSalida(res.getFloat("salida"));
                mov1.setComision(res.getFloat("comision"));
                mov1.setIva(res.getFloat("iva"));
                mov1.setTipo(res.getString("tipo"));
                mov1.setFecha(res.getDate("fecha"));
                mov1.setIrpf(res.getFloat("irpf"));
                mov1.setTipopago(res.getString("tipopago"));
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return mov1;
    }

    public static Integer buscarultimacuotapagaalq(d_movimiento mov) throws Exception {
        //devuelve id movimiento
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        Statement st;
        ResultSet res;
        //java.sql.Date sqld1 = ASqlDate(mov.getFecha());
        String consulta = "SELECT id,aqp,mqp FROM movimientos where prop_id='" + mov.getProp_id() + "'and inq_casa='" + mov.getInq_casa() + "'and detalle like '%ALQUILER%' order by aqp desc, mqp desc";
        Integer idmov = -1;

        try {
            st = c.createStatement();
            res = st.executeQuery(consulta);
            while (res.next()) {
                idmov = (res.getInt("id"));
                break;
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return idmov;
    }

    public static Integer buscarultimacuotapagasaldoalq(d_movimiento mov) throws Exception {
        //devuelve id movimiento
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        Statement st;
        ResultSet res;
        //java.sql.Date sqld1 = ASqlDate(mov.getFecha());
        String consulta = "select max(id)as id from movimientos where prop_id='" + mov.getProp_id() + "'and inq_casa='" + mov.getInq_casa() + "'and (detalle='SALDO ALQUILER' or detalle='A CUENTA ALQUILER') group by aqp order by aqp desc";
        Integer idmov = -1;

        try {
            st = c.createStatement();
            res = st.executeQuery(consulta);
            while (res.next()) {
                idmov = (res.getInt("id"));
                break;
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return idmov;
    }

    public static void guardarmovimientopropretira(d_movimiento mov) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        PreparedStatement pst;
        java.sql.Date sqld = ASqlDate(mov.getFecha());

        try {
            pst = c.prepareStatement("Insert Into movimientos Values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            pst.setInt(1, mov.getProp_id());
            pst.setInt(2, 0);
            pst.setInt(3, 0);
            pst.setInt(4, 0);
            pst.setString(5, mov.getDetalle());
            pst.setInt(6, 0);
            pst.setFloat(7, mov.getSalida());
            pst.setInt(8, 0);
            pst.setInt(9, 0);
            pst.setString(10, "");
            pst.setDate(11, sqld);
            pst.setInt(12, 0);
            pst.setString(13, "");
            pst.setString(14, mov.getTipopago());
            pst.executeUpdate();
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        c.close();
    }

    public static void guardarmovimientogastocta(d_movimiento mov) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        PreparedStatement pst;
        java.sql.Date sqld = ASqlDate(mov.getFecha());

        try {
            pst = c.prepareStatement("Insert Into movimientos Values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            pst.setInt(1, mov.getProp_id());
            pst.setInt(2, 0);
            pst.setInt(3, mov.getMqp());
            pst.setInt(4, mov.getAqp());
            pst.setString(5, mov.getDetalle());
            pst.setFloat(6, 0);
            pst.setFloat(7, mov.getSalida());
            pst.setFloat(8, mov.getComision());
            pst.setFloat(9, mov.getIva());
            pst.setString(10, "");
            pst.setDate(11, sqld);
            pst.setInt(12, 0);
            pst.setString(13, "");
            pst.setString(14, mov.getTipopago());
            pst.executeUpdate();
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        c.close();
    }

    public static int guardarmovimientoinqpaga(d_movimiento mov) throws Exception {
        //SI RETORNA -1 NO GUARDÓ, SINO SI Y DEVUELVE ID
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        PreparedStatement pst;
        java.sql.Date sqld = ASqlDate(mov.getFecha());
        Integer id = 0;

        try {
            pst = c.prepareStatement("Insert Into movimientos Values (?,?,?,?,?,?,?,?,?,?,?,?,?,?);", Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, mov.getProp_id());
            pst.setInt(2, mov.getInq_casa());
            pst.setInt(3, mov.getMqp());
            pst.setInt(4, mov.getAqp());
            pst.setString(5, mov.getDetalle());
            pst.setFloat(6, mov.getEntrada());
            pst.setFloat(7, mov.getSalida());
            pst.setFloat(8, mov.getComision());
            pst.setFloat(9, mov.getIva());
            pst.setString(10, mov.getTipo());
            pst.setDate(11, sqld);
            pst.setFloat(12, mov.getIrpf());
            pst.setString(13, mov.getIrpftipo());
            pst.setString(14, mov.getTipopago());
            id = pst.executeUpdate();
            if (id > 0) {
                ResultSet generatedKeys = pst.getGeneratedKeys();
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        c.close();
        return id;
    }

    public static int guardarmovimientoe_ticket(d_movimiento mov) throws Exception {
        //SI RETORNA -1 NO GUARDÓ, SINO SI Y DEVUELVE ID
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        PreparedStatement pst;
        java.sql.Date sqld = ASqlDate(mov.getFecha());
        Integer id = 0;

        try {
            pst = c.prepareStatement("Insert Into movimientos Values (?,?,?,?,?,?,?,?,?,?,?,?,?,?);", Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, mov.getProp_id());
            pst.setInt(2, mov.getInq_casa());
            pst.setInt(3, mov.getMqp());
            pst.setInt(4, mov.getAqp());
            pst.setString(5, mov.getDetalle());
            pst.setFloat(6, mov.getEntrada());
            pst.setFloat(7, mov.getSalida());
            pst.setFloat(8, mov.getComision());
            pst.setFloat(9, mov.getIva());
            pst.setString(10, mov.getTipo());
            pst.setDate(11, sqld);
            pst.setFloat(12, mov.getIrpf());
            pst.setString(13, mov.getIrpftipo());
            pst.setString(14, mov.getTipopago());
            id = pst.executeUpdate();
            if (id > 0) {
                ResultSet generatedKeys = pst.getGeneratedKeys();
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        c.close();
        return id;
    }

    public static void guardarmovimientogastoinmo(d_movimiento mov) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        PreparedStatement pst;
        java.sql.Date sqld = ASqlDate(mov.getFecha());

        try {
            pst = c.prepareStatement("Insert Into movimientos Values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            pst.setInt(1, 0);
            pst.setInt(2, 0);
            pst.setInt(3, 0);
            pst.setInt(4, 0);
            pst.setString(5, mov.getDetalle());
            pst.setFloat(6, 0);
            pst.setFloat(7, mov.getSalida());
            pst.setInt(8, 0);
            pst.setInt(9, 0);
            pst.setString(10, "");
            pst.setDate(11, sqld);
            pst.setFloat(12, 0);
            pst.setString(13, "");
            pst.setString(14, mov.getTipopago());
            pst.executeUpdate();
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        c.close();
    }

    public static void guardarmovimientopropingresa(d_movimiento mov) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        PreparedStatement pst;
        java.sql.Date sqld = ASqlDate(mov.getFecha());

        try {
            pst = c.prepareStatement("Insert Into movimientos Values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            pst.setInt(1, mov.getProp_id());
            pst.setInt(2, 0);
            pst.setInt(3, 0);
            pst.setInt(4, 0);
            pst.setString(5, mov.getDetalle());
            pst.setFloat(6, mov.getEntrada());
            pst.setInt(7, 0);
            pst.setInt(8, 0);
            pst.setInt(9, 0);
            pst.setString(10, "");
            pst.setDate(11, sqld);
            pst.setFloat(12, 0);
            pst.setString(13, "");
            pst.setString(14, mov.getTipopago());
            pst.executeUpdate();
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        c.close();
    }

    public static void actualizarmovimiento(d_movimiento mov) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        PreparedStatement pst;
        Integer resultado;
        java.sql.Date sqld = ASqlDate(mov.getFecha());

        try {
            pst = c.prepareStatement("Update movimientos set prop_id=?, inq_casa=?, mqp=?, aqp=?, detalle=?, entrada=?, salida=?, comision=?, iva=?, tipo=?, fecha=?, irpf=?, tipopago=? Where id=?");
            pst.setInt(1, mov.getProp_id());
            pst.setInt(2, mov.getInq_casa());
            pst.setInt(3, mov.getMqp());
            pst.setInt(4, mov.getAqp());
            pst.setString(5, mov.getDetalle());
            pst.setFloat(6, mov.getEntrada());
            pst.setFloat(7, mov.getSalida());
            pst.setFloat(8, mov.getComision());
            pst.setFloat(9, mov.getIva());
            pst.setString(10, mov.getTipo());
            pst.setDate(11, sqld);
            pst.setFloat(12, mov.getIrpf());
            pst.setString(13, mov.getTipopago());
            pst.setInt(14, mov.getId());
            resultado = pst.executeUpdate();
            if (resultado == 0) {
                pst = c.prepareStatement("Insert Into movimientos Values (NULL,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                pst.setInt(1, mov.getProp_id());
                pst.setInt(2, mov.getInq_casa());
                pst.setInt(3, mov.getMqp());
                pst.setInt(4, mov.getAqp());
                pst.setString(5, mov.getDetalle());
                pst.setFloat(6, mov.getEntrada());
                pst.setFloat(7, mov.getSalida());
                pst.setFloat(8, mov.getComision());
                pst.setFloat(9, mov.getIva());
                pst.setString(10, mov.getTipo());
                pst.setDate(11, sqld);
                pst.setFloat(12, mov.getIrpf());
                pst.setString(13, mov.getTipopago());
                pst.executeUpdate();
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        c.close();
    }

    public static void actualizardetalle(d_movimiento mov) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        PreparedStatement pst;

        try {
            pst = c.prepareStatement("Update movimientos set detalle='ALQUILER' Where prop_id=? and inq_casa=? and mqp=? and aqp=? and detalle='SALDO ALQUILER'");
            pst.setInt(1, mov.getProp_id());
            pst.setInt(2, mov.getInq_casa());
            pst.setInt(3, mov.getMqp());
            pst.setInt(4, mov.getAqp());
            pst.executeUpdate();
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        c.close();
    }

    public static void actualizartipomov(int id_mov, String detalle) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        PreparedStatement pst;

        try {
            pst = c.prepareStatement("Update movimientos set tipo='" + detalle + "', irpftipo='101' Where id=" + id_mov);
            pst.executeUpdate();
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        c.close();
    }

    public static void actualizardetallealquiler(d_movimiento mov) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        PreparedStatement pst;

        try {
            pst = c.prepareStatement("Update movimientos set detalle='ALQUILER' Where id=?");
            pst.setInt(1, mov.getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        c.close();
    }

    public static List<d_movimiento> listarmovimientosdeinq(Integer prop_id, Integer inq_casa) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        d_movimiento mov = null;
        Statement st;
        ResultSet res;
        List<d_movimiento> lista = new ArrayList<d_movimiento>();

        try {
            st = c.createStatement();
            res = st.executeQuery("select * from movimientos where prop_id='" + prop_id + "' and inq_casa='" + inq_casa + "'");
            while (res.next()) {
                mov = new d_movimiento();
                mov.setId(Integer.parseInt(res.getString("id")));
                mov.setProp_id(Integer.parseInt(res.getString("prop_id")));
                mov.setInq_casa(Integer.parseInt(res.getString("inq_casa")));
                mov.setMqp(Integer.parseInt(res.getString("mqp")));
                mov.setAqp(Integer.parseInt(res.getString("aqp")));
                mov.setDetalle((res.getString("detalle")));
                mov.setEntrada(res.getFloat("entrada"));
                mov.setSalida((res.getFloat("salida")));
                mov.setComision(res.getFloat("comision"));
                mov.setIva(res.getFloat("iva"));
                mov.setTipo((res.getString("tipo")));
                mov.setFecha((res.getDate("fecha")));
                mov.setIrpf((res.getFloat("irpf")));
                mov.setTipopago((res.getString("tipopago")));
                lista.add(mov);
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return lista;
    }

    public static List<d_movimiento> listarmovimientos() throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        d_movimiento mov = null;
        Statement st;
        ResultSet res;
        List<d_movimiento> lista = new ArrayList<d_movimiento>();

        try {
            st = c.createStatement();
            res = st.executeQuery("select * from movimientos order by fecha desc");
            while (res.next()) {
                mov = new d_movimiento();
                mov.setId(Integer.parseInt(res.getString("id")));
                mov.setProp_id(Integer.parseInt(res.getString("prop_id")));
                mov.setInq_casa(Integer.parseInt(res.getString("inq_casa")));
                mov.setMqp(Integer.parseInt(res.getString("mqp")));
                mov.setAqp(Integer.parseInt(res.getString("aqp")));
                mov.setDetalle((res.getString("detalle")));
                mov.setEntrada(res.getFloat("entrada"));
                mov.setSalida((res.getFloat("salida")));
                mov.setComision(res.getFloat("comision"));
                mov.setIva(res.getFloat("iva"));
                mov.setTipo((res.getString("tipo")));
                mov.setFecha((res.getDate("fecha")));
                mov.setIrpf((res.getFloat("irpf")));
                mov.setTipopago((res.getString("tipopago")));
                lista.add(mov);
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return lista;
    }

    public static Float obtenersaldoprophastafecha(Integer prop_id, Date fecha, Float irpf) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        Statement st;
        ResultSet res;
        java.sql.Date sqld1 = ASqlDate(fecha);
        Float control_saldo = 0.0f;
        Float saldo = 0f;
        Integer id = 0;
        Float a = 0f;

        try {
            st = c.createStatement();
            String consulta = "select id,sum(entrada-salida-comision-iva-irpf)as saldo_b\n"
                    + "FROM movimientos where prop_id='" + prop_id + "' and fecha < '" + sqld1 + "' group by id";
            res = st.executeQuery(consulta);
            while (res.next()) {
                //saldoactual = (Float.parseFloat(res.getString("entrada")) - Float.parseFloat(res.getString("salida")) - Float.parseFloat(res.getString("comision")) - Float.parseFloat(res.getString("iva")) - Float.parseFloat(res.getString("irpf")));
                id = Integer.parseInt(res.getString("id"));
                saldo = guardarnumero(Float.parseFloat(res.getString("saldo_b")));
                //saldo = guardarnumero(saldo);

                if (saldo < 0) {
                    a = 0f;
                    a = saldo * -1;
                    //a = a;
                    control_saldo = control_saldo - a;
                    control_saldo = guardarnumero(control_saldo);
                } else {
                    control_saldo = control_saldo + saldo;
                    control_saldo = guardarnumero(control_saldo);
                }
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return control_saldo;
    }

    public static Float guardarnumero(Float numero) throws Exception {
        DecimalFormat formatter = new DecimalFormat("#.##");
        Float resultado = 0f;

        String valor = formatter.format(numero);
        valor = valor.replace(".", "").replace(",", ".");
        resultado = Float.parseFloat(valor);

        return resultado;
    }

    public static Float obtenersaldoprop(Integer prop_id) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        Statement st;
        ResultSet res;
        Float saldogeneral = 0.0f;

        try {
            st = c.createStatement();

            res = st.executeQuery("SELECT COALESCE(sum(entrada),0)as entrada, COALESCE(sum(salida),0)as salida, COALESCE(sum(comision),0)as comision, COALESCE(sum(iva),0)as iva, COALESCE(sum(irpf),0)as irpf FROM movimientos where prop_id=" + prop_id);
            while (res.next()) {
                saldogeneral = (Float.parseFloat(res.getString("entrada")) - Float.parseFloat(res.getString("salida")) - Float.parseFloat(res.getString("comision")) - Float.parseFloat(res.getString("iva")) - Float.parseFloat(res.getString("irpf")));
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return saldogeneral;
    }

    public static List<d_movimiento> listarmovimientosentrefechas(Date fecha1, Date fecha2) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        d_movimiento mov = null;
        Statement st;
        ResultSet res;
        List<d_movimiento> lista = new ArrayList<d_movimiento>();
        java.sql.Date sqld1 = ASqlDate(fecha1);
        java.sql.Date sqld2 = ASqlDate(fecha2);

        try {
            st = c.createStatement();
            res = st.executeQuery("select * from movimientos where detalle <>'SALDO MIGRACION' and fecha BETWEEN '" + sqld1 + " 'and' " + sqld2 + "'");
            while (res.next()) {
                mov = new d_movimiento();
                mov.setId(Integer.parseInt(res.getString("id")));
                mov.setProp_id(Integer.parseInt(res.getString("prop_id")));
                mov.setInq_casa(Integer.parseInt(res.getString("inq_casa")));
                mov.setMqp(Integer.parseInt(res.getString("mqp")));
                mov.setAqp(Integer.parseInt(res.getString("aqp")));
                mov.setDetalle((res.getString("detalle")));
                mov.setEntrada((res.getFloat("entrada")));
                mov.setSalida((res.getFloat("salida")));
                mov.setComision((res.getFloat("comision")));
                mov.setIva((res.getFloat("iva")));
                mov.setTipo((res.getString("tipo")));
                mov.setFecha((res.getDate("fecha")));
                mov.setIrpf((res.getFloat("irpf")));
                mov.setTipopago((res.getString("tipopago")));
                lista.add(mov);
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return lista;
    }

    public static List<d_movimiento> listarmovimientosentrefechastipopago(Date fecha1, Date fecha2, String tipo) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        d_movimiento mov = null;
        Statement st;
        ResultSet res;
        List<d_movimiento> lista = new ArrayList<d_movimiento>();
        java.sql.Date sqld1 = ASqlDate(fecha1);
        java.sql.Date sqld2 = ASqlDate(fecha2);

        try {
            st = c.createStatement();
            res = st.executeQuery("select * from movimientos where detalle <>'SALDO MIGRACION' and fecha BETWEEN '" + sqld1 + "'and' " + sqld2 + "'and tipopago= '" + tipo + "'");
            while (res.next()) {
                mov = new d_movimiento();
                mov.setId(Integer.parseInt(res.getString("id")));
                mov.setProp_id(Integer.parseInt(res.getString("prop_id")));
                mov.setInq_casa(Integer.parseInt(res.getString("inq_casa")));
                mov.setMqp(Integer.parseInt(res.getString("mqp")));
                mov.setAqp(Integer.parseInt(res.getString("aqp")));
                mov.setDetalle((res.getString("detalle")));
                mov.setEntrada((res.getFloat("entrada")));
                mov.setSalida((res.getFloat("salida")));
                mov.setComision((res.getFloat("comision")));
                mov.setIva((res.getFloat("iva")));
                mov.setTipo((res.getString("tipo")));
                mov.setFecha((res.getDate("fecha")));
                mov.setIrpf((res.getFloat("irpf")));
                mov.setTipopago((res.getString("tipopago")));
                lista.add(mov);
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return lista;
    }

    public static List<d_movimiento> listarmovimientosentrefechassincfe(Date fecha1, Date fecha2) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        d_movimiento mov = null;
        Statement st;
        ResultSet res;
        List<d_movimiento> lista = new ArrayList<d_movimiento>();
        java.sql.Date sqld1 = ASqlDate(fecha1);
        java.sql.Date sqld2 = ASqlDate(fecha2);

        try {
            st = c.createStatement();
            res = st.executeQuery("select * from movimientos where entrada > 0 and fecha BETWEEN '" + sqld1 + " 'and' " + sqld2 + "' and detalle like '%ALQUILER%' and id NOT IN (SELECT idmov FROM cfe)");
            while (res.next()) {
                mov = new d_movimiento();
                mov.setId(Integer.parseInt(res.getString("id")));
                mov.setProp_id(Integer.parseInt(res.getString("prop_id")));
                mov.setInq_casa(Integer.parseInt(res.getString("inq_casa")));
                mov.setMqp(Integer.parseInt(res.getString("mqp")));
                mov.setAqp(Integer.parseInt(res.getString("aqp")));
                mov.setDetalle((res.getString("detalle")));
                mov.setEntrada((res.getFloat("entrada")));
                mov.setSalida((res.getFloat("salida")));
                mov.setComision((res.getFloat("comision")));
                mov.setIva((res.getFloat("iva")));
                mov.setTipo((res.getString("tipo")));
                mov.setFecha((res.getDate("fecha")));
                mov.setIrpf((res.getFloat("irpf")));
                mov.setTipopago((res.getString("tipopago")));
                lista.add(mov);
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return lista;
    }

    public static List<d_movimiento> listarmovimientosentrefechasypro(Integer prop_id, Date fecha1, Date fecha2) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        d_movimiento mov = null;
        Statement st;
        ResultSet res;
        List<d_movimiento> lista = new ArrayList<d_movimiento>();
        java.sql.Date sqld1 = ASqlDate(fecha1);
        java.sql.Date sqld2 = ASqlDate(fecha2);

        try {
            st = c.createStatement();

            res = st.executeQuery("select \n"
                    + "a.id,\n"
                    + "a.inq_casa,\n"
                    + "a.fecha,\n"
                    + "a.tipo,\n"
                    + "a.detalle,\n"
                    + "a.mqp,\n"
                    + "a.aqp,\n"
                    + "a.entrada, \n"
                    + "a.salida,\n"
                    + "a.comision,\n"
                    + "a.iva,\n"
                    + "a.irpf, \n"
                    + "( \n"
                    + "select ISNULL(sum(b.entrada - b.salida-b.comision - b.iva- b.irpf),0) as saldo_ant\n"
                    + "from movimientos as b \n"
                    + "where b.fecha < '" + sqld1 + "' and prop_id='" + prop_id + "' \n"
                    + ") as saldo_a,\n"
                    + "sum(a.entrada-a.salida-a.comision-a.iva-a.irpf)as saldo_b\n"
                    + "from movimientos as a where a.prop_id='" + prop_id + "' and a.fecha between '" + sqld1 + "' and '" + sqld2 + "' \n"
                    + "group by a.id,a.inq_casa,a.entrada, a.fecha,a.tipo,a.detalle,a.mqp,\n"
                    + "a.aqp,\n"
                    + "a.salida,\n"
                    + "a.comision,\n"
                    + "a.iva,\n"
                    + "a.irpf\n"
                    + "order by a.id");

            while (res.next()) {
                mov = new d_movimiento();
                mov.setId(Integer.parseInt(res.getString("id")));
                mov.setFecha((res.getDate("fecha")));
                mov.setTipo((res.getString("tipo")));
                mov.setDetalle((res.getString("detalle")));
                mov.setMqp(Integer.parseInt(res.getString("mqp")));
                mov.setAqp(Integer.parseInt(res.getString("aqp")));
                mov.setEntrada((res.getFloat("entrada")));
                mov.setSalida((res.getFloat("salida")));
                mov.setComision((res.getFloat("comision")));
                mov.setIva((res.getFloat("iva")));
                mov.setIrpf((res.getFloat("irpf")));
                if (mov.getMqp() == 0 && mov.getAqp() == 0) {
                    mov.setNombreinq(("-"));
                }
                mov.setInq_casa(Integer.parseInt(res.getString("inq_casa")));
                mov.setNombreinq(res.getString("tipo"));
                mov.setSaldo_a((res.getFloat("saldo_a")));

                mov.setSaldo_b((res.getFloat("saldo_b")));

                lista.add(mov);
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return lista;
    }

    public static List<d_movimiento> listarmovimientosentrefechaseinq(Integer prop_id, Integer inq_id, Date fecha1, Date fecha2) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        d_movimiento mov = null;
        Statement st;
        ResultSet res;
        List<d_movimiento> lista = new ArrayList<d_movimiento>();
        java.sql.Date sqld1 = ASqlDate(fecha1);
        java.sql.Date sqld2 = ASqlDate(fecha2);

        try {
            st = c.createStatement();
            res = st.executeQuery("select * from movimientos where fecha BETWEEN '" + sqld1 + "' and '" + sqld2 + "' and prop_id='" + prop_id + "' and inq_casa='" + inq_id + "'order by fecha desc");
            while (res.next()) {
                mov = new d_movimiento();
                mov.setId(Integer.parseInt(res.getString("id")));
                mov.setProp_id(Integer.parseInt(res.getString("prop_id")));
                mov.setInq_casa(Integer.parseInt(res.getString("inq_casa")));
                mov.setMqp(Integer.parseInt(res.getString("mqp")));
                mov.setAqp(Integer.parseInt(res.getString("aqp")));
                mov.setDetalle((res.getString("detalle")));
                mov.setEntrada((res.getFloat("entrada")));
                mov.setSalida((res.getFloat("salida")));
                mov.setComision((res.getFloat("comision")));
                mov.setIva((res.getFloat("iva")));
                mov.setTipo((res.getString("tipo")));
                mov.setFecha((res.getDate("fecha")));
                mov.setIrpf((res.getFloat("irpf")));
                mov.setTipopago((res.getString("tipopago")));
                lista.add(mov);
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return lista;
    }

    public static Float totalmovimientosentrefechaseinq(Integer prop_id, Integer inq_id, Date fecha1, Date fecha2) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        Statement st;
        ResultSet res;
        java.sql.Date sqld1 = ASqlDate(fecha1);
        java.sql.Date sqld2 = ASqlDate(fecha2);

        try {
            st = c.createStatement();
            res = st.executeQuery("select sum(entrada)as total from movimientos where fecha BETWEEN '" + sqld1 + "' and '" + sqld2 + "' and prop_id='" + prop_id + "' and inq_casa='" + inq_id + "' and detalle like '%ALQUILER%'");
            while (res.next()) {
                return ((res.getFloat("total")));
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return 0f;
    }

    public static d_movimiento obtenertotalesentrefechas(Date fecha1, Date fecha2) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        java.sql.Date sqld1 = ASqlDate(fecha1);
        java.sql.Date sqld2 = ASqlDate(fecha2);
        Statement st;
        ResultSet res;

        String consulta = "Select sum(entrada) as entrada, sum(salida) as salida, sum(comision) as comision, sum(iva) as iva from movimientos where detalle <>'SALDO MIGRACION' and fecha BETWEEN '" + sqld1 + "' and '" + sqld2 + "'";
        d_movimiento mov1 = null;

        try {
            st = c.createStatement();
            res = st.executeQuery(consulta);
            while (res.next()) {
                mov1 = new d_movimiento();
                mov1.setEntrada(res.getFloat("entrada"));
                mov1.setSalida(res.getFloat("salida"));
                mov1.setComision(res.getFloat("comision"));
                mov1.setIva(res.getFloat("iva"));
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return mov1;
    }

    public static d_movimiento obtenertotalesirpfentrefechas(Date fecha1, Date fecha2) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        java.sql.Date sqld1 = ASqlDate(fecha1);
        java.sql.Date sqld2 = ASqlDate(fecha2);
        Statement st;
        ResultSet res;

        String consulta = "select sum(irpf)as irpf from movimientos where irpftipo='SI' and fecha BETWEEN '" + sqld1 + "' and '" + sqld2 + "'";
        d_movimiento mov1 = null;

        try {
            st = c.createStatement();
            res = st.executeQuery(consulta);
            while (res.next()) {
                mov1 = new d_movimiento();
                mov1.setIrpf(res.getFloat("irpf"));
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return mov1;
    }

    public static d_movimiento obtenertotalesgastosentrefechas(Date fecha1, Date fecha2) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        java.sql.Date sqld1 = ASqlDate(fecha1);
        java.sql.Date sqld2 = ASqlDate(fecha2);
        Statement st;
        ResultSet res;

        String consulta = "Select sum(salida) as salida from movimientos where prop_id=0 and inq_casa=0 and mqp=0 and aqp=0 and fecha BETWEEN '" + sqld1 + "' and '" + sqld2 + "'";
        d_movimiento mov1 = null;

        try {
            st = c.createStatement();
            res = st.executeQuery(consulta);
            while (res.next()) {
                mov1 = new d_movimiento();
                mov1.setSalida(res.getFloat("salida"));
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return mov1;
    }

    public static void eliminarmovimiento(Integer id) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        Statement st;

        try {
            st = c.createStatement();
            st.execute("delete from movimientos where id=" + id);
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        c.close();
    }

    public static Integer mesesatraso(Integer prop_id, Integer inq_casa) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        Statement st;
        ResultSet res;
        Integer totalmeses = 0;
        Date fechaactual = new Date();
        Integer mqp = devuelvemes(fechaactual);
        Integer aqp = devuelveanio(fechaactual);
        String fecha = mqp.toString() + aqp.toString();

        try {
            st = c.createStatement();
            res = st.executeQuery("select count(*) as total from gastos_inq where (concat(mqp,aqp)!='" + fecha + "') and (detalle='SALDO ALQUILER' or detalle='ALQUILER MES' or detalle='ALQUILER') and prop_id='" + prop_id + "'and inq_casa='" + inq_casa + "' and estado=0");
            while (res.next()) {
                totalmeses = (res.getInt("total"));
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return totalmeses;
    }

    public static List<d_movimiento> mesesatraso_listado(Integer prop_id, Integer inq_casa) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        Statement st;
        ResultSet res;
        d_movimiento mov = null;
        List<d_movimiento> lista = new ArrayList<>();
        Date fechaactual = new Date();
        Integer mqp = devuelvemes(fechaactual);
        Integer aqp = devuelveanio(fechaactual);
        String fecha = mqp.toString() + aqp.toString();

        try {
            st = c.createStatement();
            res = st.executeQuery("select * from gastos_inq where (concat(mqp,aqp)<" + fecha + ") and (detalle='SALDO ALQUILER' or detalle='ALQUILER MES' or detalle='ALQUILER') and prop_id='" + prop_id + "'and inq_casa='" + inq_casa + "' and estado=0");
            while (res.next()) {
                mov = new d_movimiento();
                mov.setAqp(res.getInt("aqp"));
                mov.setMqp(res.getInt("mqp"));
                mov.setDetalle(res.getString("detalle"));
                mov.setEntrada(res.getFloat("importe"));
                lista.add(mov);
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return lista;
    }

    public static Float totalmesesatraso(Integer prop_id, Integer inq_casa) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        Statement st;
        ResultSet res;
        Float totalmeses = 0f;
        Date fechaactual = new Date();
        Integer mqp = devuelvemes(fechaactual);
        Integer aqp = devuelveanio(fechaactual);
        String fecha = mqp.toString() + aqp.toString();

        try {
            st = c.createStatement();
            res = st.executeQuery("select sum(importe) as total from gastos_inq where (concat(mqp,aqp)<" + fecha + ") and (detalle='SALDO ALQUILER' or detalle='ALQUILER MES' or detalle='ALQUILER') and prop_id='" + prop_id + "'and inq_casa='" + inq_casa + "' and estado=0");
            while (res.next()) {
                totalmeses = (res.getFloat("total"));
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return totalmeses;
    }

    public static Integer totalmesesatraso(Integer idmovimiento, Date fechaultimomovimiento) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        Statement st;
        ResultSet res;
        Integer resultado = 0;

        java.sql.Date sqld = ASqlDate(fechaultimomovimiento);

        try {
            st = c.createStatement();
            res = st.executeQuery("SELECT DATEDIFF(month, '" + sqld + "', CONVERT(date, GETDATE())) as resultado from movimientos where id='" + idmovimiento + "'");
            while (res.next()) {
                resultado = (res.getInt("resultado"));
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return resultado;
    }

    public static Float totalentradatipopagohoy(String tipopago) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        ResultSet res;
        Statement st;
        Float total = 0f;

        try {
            st = c.createStatement();
            res = st.executeQuery("select sum(entrada) as entrada from movimientos where tipopago='" + tipopago + "' and fecha=CONVERT(DATE, GETDATE())");
            while (res.next()) {
                total = (res.getFloat("entrada"));
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return total;
    }

    public static Float totalsalidatipopagohoy(String tipopago) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        ResultSet res;
        Statement st;
        Float total = 0f;

        try {
            st = c.createStatement();
            res = st.executeQuery("select sum(salida) as salida from movimientos where tipopago='" + tipopago + "' and fecha=CONVERT(DATE, GETDATE())");
            while (res.next()) {
                total = (res.getFloat("salida"));
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return total;
    }

    public static void ejecutarconsulta(String consulta) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        Statement st;

        try {
            st = c.createStatement();
            st.execute(consulta);
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        c.close();
    }

    public static Integer devuelvemes(Date fecha) {
        String formato = "MM";
        SimpleDateFormat dateFormat = new SimpleDateFormat(formato);
        return Integer.parseInt(dateFormat.format(fecha));
    }

    public static Integer devuelveanio(Date fecha) {
        String formato = "yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(formato);
        return Integer.parseInt(dateFormat.format(fecha));
    }

    public static java.sql.Date ASqlDate(java.util.Date fecha) {
        java.sql.Date sqldate = null;
        sqldate = new java.sql.Date(fecha.getTime());

        return sqldate;
    }
}
