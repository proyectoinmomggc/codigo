/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import dominio.d_alquileres_info;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

/**
 *
 * @author gonza
 */
public class p_alquileres_info {

    public static void guardar_alquiler_info(d_alquileres_info alq) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        PreparedStatement pst;
        Integer resultado;

        try {
            pst = c.prepareStatement("Update alquileres_info set importe=?, fecha=?, detalle=? Where prop_id=? and inq_casa=?");
            pst.setFloat(1, alq.getImporte());
            pst.setDate(2, ASqlDate(alq.getFecha()));
            pst.setString(3, alq.getDetalle());
            pst.setInt(4, alq.getProp_id());
            pst.setInt(5, alq.getInq_casa());            
            resultado = pst.executeUpdate();
            if (resultado == 0) {
                pst = c.prepareStatement("Insert Into alquileres_info Values (?,?,?,?,?)");
                pst.setInt(1, alq.getProp_id());
                pst.setInt(2, alq.getInq_casa());
                pst.setFloat(3, alq.getImporte());
                pst.setDate(4, ASqlDate(alq.getFecha()));
                pst.setString(5, alq.getDetalle());
                pst.executeUpdate();
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        c.close();
    }

    public static Boolean tiene_correccion(Integer prop_id, Integer inq_casa) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        Statement st;
        ResultSet res;

        try {
            st = c.createStatement();
            res = st.executeQuery("select * from alquileres_info where prop_id=" + prop_id + " and inq_casa=" + inq_casa);
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
    
    public static d_alquileres_info buscar_alquiler_info(Integer prop_id, Integer inq_casa) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        Statement st;
        ResultSet res;
        d_alquileres_info alq = null;

        try {
            st = c.createStatement();
            res = st.executeQuery("select * from alquileres_info where prop_id=" + prop_id + " and inq_casa=" + inq_casa);
            while (res.next()) {
                alq = new d_alquileres_info();
                alq.setProp_id(res.getInt("prop_id"));
                alq.setInq_casa(res.getInt("inq_casa"));
                alq.setImporte(res.getFloat("importe"));
                alq.setFecha(res.getDate("fecha"));
                alq.setDetalle(res.getString("detalle"));
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return alq;
    }

    public static void eliminar_alquiler_info(Integer prop_id, Integer inq_casa) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        Statement st;

        try {
            st = c.createStatement();
            st.execute("delete from alquileres_info where prop_id=" + prop_id + " and inq_casa=" + inq_casa);
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        c.close();
    }
    
    public static java.sql.Date ASqlDate(java.util.Date fecha) {
        java.sql.Date sqldate = null;
        sqldate = new java.sql.Date(fecha.getTime());

        return sqldate;
    }
}
