/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import dominio.d_clave;
import dominio.d_parametro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import static persistencia.p_inquilinos.ASqlDate;

/**
 *
 * @author Gonzalo
 */
public class p_clave {
    public static d_clave buscarclave() throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        Statement st;
        ResultSet res;
        d_clave cla = null;

        try {
            st = c.createStatement();
            res = st.executeQuery("select * from clave");
            while (res.next()) {
                cla = new d_clave();
                cla.setClave(res.getString("clave"));
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return cla;
    }

    public static void actualizarclave(d_clave cla) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        PreparedStatement pst;

        try {
            pst = c.prepareStatement("Update clave set clave=?");
            pst.setString(1, cla.getClave());
            pst.executeUpdate();
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        c.close();
    }
}
