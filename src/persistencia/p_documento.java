/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import dominio.d_caja;
import dominio.d_documento;
import dominio.d_propietario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gonza
 */
public class p_documento {

    public static d_documento buscar_documento(Timestamp fecha) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        Statement st;
        ResultSet res;
        d_documento doc = null;

        try {
            st = c.createStatement();
            res = st.executeQuery("select * from documentos where fecha = convert(datetime, '"+fecha+"', 121)");
            while (res.next()) {
                doc = new d_documento();
                doc.setFecha(res.getTimestamp("fecha"));
                doc.setNombre(res.getString("nombre"));
                doc.setDireccion(res.getString("direccion"));
                doc.setCi(res.getString("ci"));
                doc.setTexto(res.getString("texto"));
                doc.setDescripcion_monto(res.getString("descripcion_monto"));
                doc.setMonto(res.getFloat("monto"));
                doc.setTipo_moneda(res.getString("tipo_moneda"));
                doc.setTipo(res.getString("tipo"));
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return doc;
    }

    public static void guardar_documento(d_documento doc) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        PreparedStatement pst;
        Integer resultado;

        try {
            pst = c.prepareStatement("Update documentos set nombre=?,direccion=?,ci=?,texto=?,descripcion_monto=?,monto=?,tipo_moneda=?, tipo=? Where fecha=?");
            pst.setString(1, doc.getNombre());
            pst.setString(2, doc.getDireccion());
            pst.setString(3, doc.getCi());
            pst.setString(4, doc.getTexto());
            pst.setString(5, doc.getDescripcion_monto());
            pst.setFloat(6, doc.getMonto());
            pst.setString(7, doc.getTipo_moneda());
            pst.setString(8, doc.getTipo());
            //pst.setTimestamp(9, ASqlDate(doc.getFecha()));
            pst.setTimestamp(9, (doc.getFecha()));
            resultado = pst.executeUpdate();
            if (resultado == 0) {
                pst = c.prepareStatement("Insert Into documentos Values (?,?,?,?,?,?,?,?,?)");
                //pst.setTimestamp(1, ASqlDate(doc.getFecha()));
                pst.setTimestamp(1, (doc.getFecha()));
                pst.setString(2, doc.getNombre());
                pst.setString(3, doc.getDireccion());
                pst.setString(4, doc.getCi());
                pst.setString(5, doc.getTexto());
                pst.setString(6, doc.getDescripcion_monto());
                pst.setFloat(7, doc.getMonto());
                pst.setString(8, doc.getTipo_moneda());
                pst.setString(9, doc.getTipo());
                pst.executeUpdate();
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        c.close();
    }

    public static List<d_documento> listar_documentos() throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        d_documento doc = null;
        Statement st;
        ResultSet res;
        List<d_documento> lista = new ArrayList();

        try {
            st = c.createStatement();
            res = st.executeQuery("select * from documentos ORDER BY fecha desc");
            while (res.next()) {
                doc = new d_documento();
                doc.setFecha(res.getTimestamp("fecha"));
                doc.setNombre(res.getString("nombre"));
                doc.setDireccion(res.getString("direccion"));
                doc.setCi(res.getString("ci"));
                doc.setTexto(res.getString("texto"));
                doc.setDescripcion_monto(res.getString("descripcion_monto"));
                doc.setMonto(res.getFloat("monto"));
                doc.setTipo_moneda(res.getString("tipo_moneda"));
                doc.setTipo(res.getString("tipo"));
                lista.add(doc);
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return lista;
    }

    public static void eliminar_documento(Timestamp fecha) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        Statement st;

        try {
            st = c.createStatement();
            st.execute("delete from documentos where fecha = convert(datetime, '"+fecha+"', 121)");
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        c.close();
    }

    public static java.sql.Timestamp ASqlDate(Timestamp fecha) {
        java.sql.Timestamp sqldate = null;
        //sqldate = new java.sql.Timestamp(fecha.getTime());
        sqldate = new java.sql.Timestamp(System.currentTimeMillis());

        return sqldate;
    }
}
