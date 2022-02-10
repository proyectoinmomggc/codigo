/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import dominio.d_propietario_dgi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author gonza
 */
public class p_propietario_dgi {
    public static void guardar(d_propietario_dgi pro) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        PreparedStatement pst;
        Integer resultado;

        try {
            pst = c.prepareStatement("Update propietarios_dgi set ciudad=?, departamento=?, pais=? Where prop_id=?");
            pst.setString(1, pro.getCiudad());
            pst.setString(2, pro.getDepartamento());
            pst.setString(3, pro.getPais());
            pst.setInt(4, pro.getProp_id());
            resultado = pst.executeUpdate();
            if (resultado == 0) {
                pst = c.prepareStatement("Insert Into propietarios_dgi Values (?,?,?,?)");
                pst.setInt(1, pro.getProp_id());
                pst.setString(2, pro.getCiudad());
                pst.setString(3, pro.getDepartamento());
                pst.setString(4, pro.getPais());
                pst.executeUpdate();
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        c.close();
    }
    
    public static d_propietario_dgi buscar(Integer prop_id) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        Statement st;
        ResultSet res;
        d_propietario_dgi pro = null;

        try {
            st = c.createStatement();
            res = st.executeQuery("select * from propietarios_dgi where prop_id=" + prop_id);
            while (res.next()) {
                pro = new d_propietario_dgi();
                pro.setProp_id(res.getInt("prop_id"));
                pro.setCiudad(res.getString("ciudad"));
                pro.setDepartamento(res.getString("departamento"));
                pro.setPais(res.getString("pais"));
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return pro;
    }

    public static void eliminar(Integer prop_id) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        Statement st;

        try {
            st = c.createStatement();
            st.execute("delete from propietarios_dgi where prop_id=" + prop_id);
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        c.close();
    }
}
