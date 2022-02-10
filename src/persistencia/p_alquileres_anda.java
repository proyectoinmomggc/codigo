/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import dominio.d_alquileres_anda;
import dominio.d_cfe;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gonza
 */
public class p_alquileres_anda {
    public static void guardar_alquiler_anda(d_alquileres_anda alq) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        PreparedStatement pst;
        Integer resultado;

        try {
            pst = c.prepareStatement("Update alquileres_anda set id_anda=? Where prop_id=? and inq_casa=?");
            pst.setString(1, alq.getId_anda());
            pst.setInt(2, alq.getProp_id());
            pst.setInt(3, alq.getInq_casa());
            resultado = pst.executeUpdate();
            if (resultado == 0) {
                pst = c.prepareStatement("Insert Into alquileres_anda Values (?,?,?)");
                pst.setInt(1, alq.getProp_id());
                pst.setInt(2, alq.getInq_casa());
                pst.setString(3, alq.getId_anda());
                pst.executeUpdate();
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        c.close();
    }

    public static d_alquileres_anda buscar_alquiler_anda(Integer prop_id, Integer inq_casa) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        Statement st;
        ResultSet res;
        d_alquileres_anda alq = null;

        try {
            st = c.createStatement();
            res = st.executeQuery("select * from alquileres_anda where prop_id=" + prop_id + " and inq_casa=" + inq_casa);
            while (res.next()) {
                alq = new d_alquileres_anda();
                alq.setProp_id(res.getInt("prop_id"));
                alq.setInq_casa(res.getInt("inq_casa"));
                alq.setId_anda(res.getString("id_anda"));
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return alq;
    }
    
    public static d_alquileres_anda buscar_alquiler_anda_nombre(String nombre) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        Statement st;
        ResultSet res;
        d_alquileres_anda alq = null;

        try {
            st = c.createStatement();
            res = st.executeQuery("select * from alquileres_anda where id_anda='" + nombre + "'");
            while (res.next()) {
                alq = new d_alquileres_anda();
                alq.setProp_id(res.getInt("prop_id"));
                alq.setInq_casa(res.getInt("inq_casa"));
                alq.setId_anda(res.getString("id_anda"));
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return alq;
    }

    public static List<d_alquileres_anda> listar_alquileres_anda() throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        d_alquileres_anda alq = null;
        Statement st;
        ResultSet res;
        List<d_alquileres_anda> lista = new ArrayList<>();

        try {
            st = c.createStatement();
            res = st.executeQuery("select * from alquileres_anda order by prop_id");
            while (res.next()) {
                alq = new d_alquileres_anda();
                alq.setProp_id(res.getInt("prop_id"));
                alq.setInq_casa(res.getInt("inq_casa"));
                alq.setId_anda(res.getString("id_anda"));
                lista.add(alq);
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return lista;
    }
    
    public static void eliminar_alquiler_anda(Integer prop_id, Integer inq_casa) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        Statement st;

        try {
            st = c.createStatement();
            st.execute("delete from alquileres_anda where prop_id=" + prop_id + " and inq_casa=" + inq_casa);
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        c.close();
    }
}
