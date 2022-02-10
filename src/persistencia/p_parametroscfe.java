/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import dominio.d_cfe;
import dominio.d_parametroscfe;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author e274263
 */
public class p_parametroscfe {

    public static d_parametroscfe buscarparametroscfe() throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        Statement st;
        ResultSet res;
        d_parametroscfe cfe = null;

        try {
            st = c.createStatement();
            res = st.executeQuery("select * from parametroscfe");
            while (res.next()) {
                cfe = new d_parametroscfe();
                cfe.setNomusuario(res.getString("nomusuario"));
                cfe.setClave(res.getString("clave"));
                cfe.setTenant(res.getString("tenant"));
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return cfe;
    }

    public static void guardarparametroscfe(d_parametroscfe cfe) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        PreparedStatement pst;
        Integer resultado;

        try {
            pst = c.prepareStatement("Update parametroscfe set clave=?, tenant=? Where nomusuario=?");
            pst.setString(1, cfe.getClave());
            pst.setString(2, cfe.getTenant());
            pst.setString(3, cfe.getNomusuario());
            resultado = pst.executeUpdate();
            if (resultado == 0) {
                pst = c.prepareStatement("Insert Into parametroscfe Values (?,?,?)");
                pst.setString(1, cfe.getNomusuario());
                pst.setString(2, cfe.getClave());
                pst.setString(3, cfe.getTenant());
                pst.executeUpdate();
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        c.close();
    }
}
