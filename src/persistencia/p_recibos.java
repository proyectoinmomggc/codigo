/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import dominio.d_recibo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author Gonzalo
 */
public class p_recibos {

    public static void guardarrecibo(d_recibo rec) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        PreparedStatement pst;
        java.sql.Date sqld = ASqlDate(rec.getFecha());
        Integer resultado;

        try {
            pst = c.prepareStatement("Update recibos set detalle=?, fecha=?, nombre_inq=?, importe=? Where idmovimiento=?");
            pst.setString(1, rec.getDetalle());
            pst.setDate(2, sqld);
            pst.setString(3, rec.getNombre_inq());
            pst.setFloat(4, rec.getImporte());
            pst.setInt(5, rec.getIdmovimiento());
            resultado = pst.executeUpdate();
            if (resultado == 0) {

                pst = c.prepareStatement("Insert Into recibos Values (?,?,?,?,?)");
                pst.setInt(1, rec.getIdmovimiento());
                pst.setString(2, rec.getDetalle());
                pst.setDate(3, sqld);
                pst.setString(4, rec.getNombre_inq());
                pst.setFloat(5, rec.getImporte());
                pst.executeUpdate();

            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        c.close();
    }

    public static void guardarlistadodeuda(List<d_recibo> lista) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        PreparedStatement pst;
        Integer i = 0;

        try {
            if (lista != null) {
                for (d_recibo aux : lista) {
                    pst = c.prepareStatement("Insert Into recibos Values (?,?,?,?,?)");
                    i = i + 1;
                    pst.setInt(1, i);
                    pst.setString(2, aux.getDetalle());
                    pst.setDate(3, ASqlDate(aux.getFecha()));
                    pst.setString(4, aux.getNombre_inq());
                    pst.setFloat(5, aux.getImporte());
                    pst.executeUpdate();
                }
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        c.close();
    }

    public static void eliminarrecibos() throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        Statement st;

        try {
            st = c.createStatement();
            st.execute("delete from recibos");
        } catch (Exception ex) {
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
