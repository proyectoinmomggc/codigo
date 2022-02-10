/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import dominio.d_caja;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import static org.hsqldb.HsqlDateTime.timestampValue;

/**
 *
 * @author gonza
 */
public class p_caja {

    public static d_caja buscarcaja() throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        Statement st;
        ResultSet res;
        d_caja caja = null;
        //Timestamp f;

        try {
            st = c.createStatement();
            res = st.executeQuery("select * from caja");
            while (res.next()) {
                caja = new d_caja();
                //caja.setFecha(res.getTimestamp("fecha"));
                caja.setMonto(res.getFloat("monto"));
                caja.setFecha(res.getTimestamp("fecha"));
                //caja.setFecha(f.getTime()));

            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return caja;
    }

    public static void actualizarcaja(d_caja caja) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        PreparedStatement pst;
        //java.sql.Timestamp sqld = ASqlDate(caja.getFecha());
        Integer resultado;

        try {
            pst = c.prepareStatement("Update caja set fecha=getdate(), monto=?");
            //pst.setTimestamp(1, caja.getFecha());
            pst.setFloat(1, caja.getMonto());
            resultado = pst.executeUpdate();
            if (resultado == 0) {
                pst = c.prepareStatement("Insert into caja (fecha,monto) values(getdate(),?)");
                //pst.setTimestamp(1, caja.getFecha());
                pst.setFloat(1, caja.getMonto());
                pst.executeUpdate();
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        c.close();
    }

    public static java.sql.Timestamp ASqlDate(Timestamp fecha) {
        //Date f=new Date(fecha.getTime());
        java.sql.Timestamp sqldate = null;
        sqldate = new java.sql.Timestamp(fecha.getTime());

        return sqldate;
    }
}
