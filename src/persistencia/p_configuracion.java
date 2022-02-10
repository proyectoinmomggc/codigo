/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import dominio.d_configuracion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author gonza
 */
public class p_configuracion {

    public static d_configuracion buscarconfiguracion() throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        Statement st;
        ResultSet res;
        d_configuracion conf = null;

        try {
            st = c.createStatement();
            res = st.executeQuery("select * from configuracion");
            while (res.next()) {
                conf = new d_configuracion();
                conf.setClave(res.getString("clave"));
                conf.setCfe(res.getString("cfe"));
                conf.setImprimir(res.getString("imprimir"));
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return conf;
    }

    public static void actualizarconfiguracion(d_configuracion conf) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        PreparedStatement pst;

        try {
            pst = c.prepareStatement("Update configuracion set clave=?, cfe=?, imprimir=?");
            pst.setString(1, conf.getClave());
            pst.setString(2, conf.getCfe());
            pst.setString(3, conf.getImprimir());
            pst.executeUpdate();
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        c.close();
    }
}
