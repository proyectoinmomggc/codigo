/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import dominio.d_parametro;
import dominio.d_propietario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static persistencia.p_inquilinos.ASqlDate;

/**
 *
 * @author Gonzalo
 */
public class p_parametros {

  
    public static d_parametro buscarparametro() throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        Statement st;
        ResultSet res;
        d_parametro par = null;

        try {
            st = c.createStatement();
            res = st.executeQuery("select * from parametros");
            while (res.next()) {
                par = new d_parametro();
                par.setIva(res.getInt("iva"));
                par.setProcaumento(res.getFloat("procaumento"));
                par.setFecha(res.getDate("fecha"));
                par.setIrpf(res.getFloat("irpf"));
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return par;
    }

    public static d_parametro buscarparametroporfecha(Date fecha) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        Statement st;
        ResultSet res;
        d_parametro par = null;
        java.sql.Date sqld = ASqlDate(fecha);

        try {
            st = c.createStatement();
            res = st.executeQuery("select * from parametros where fecha = '" + sqld + "'");
            while (res.next()) {
                par = new d_parametro();
                par.setIva(res.getInt("iva"));
                par.setProcaumento(res.getFloat("procaumento"));
                par.setFecha(res.getDate("fecha"));
                par.setIrpf(res.getFloat("irpf"));
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return par;
    }

    public static List<d_parametro> listarparametros() throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        d_parametro par = null;
        Statement st;
        ResultSet res;
        List<d_parametro> lista = new ArrayList<d_parametro>();

        try {
            st = c.createStatement();
            res = st.executeQuery("select * from parametros order by fecha");
            while (res.next()) {
                par = new d_parametro();
                par.setIva(res.getInt("iva"));
                par.setProcaumento(res.getFloat("procaumento"));
                par.setFecha(res.getDate("fecha"));
                par.setIrpf(res.getFloat("irpf"));
                lista.add(par);
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return lista;
    }

    public static void guardarparametro(d_parametro par) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        PreparedStatement pst;
        java.sql.Date sqld = ASqlDate(par.getFecha());

        try {
            pst = c.prepareStatement("Insert Into parametros Values (?,?,?,?)");
            pst.setInt(1, par.getIva());
            pst.setFloat(2, par.getProcaumento());
            pst.setDate(3, sqld);
            pst.setFloat(4, par.getIrpf());
            pst.executeUpdate();
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        c.close();
    }

    public static void actualizarparametro(d_parametro par) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        PreparedStatement pst;
        java.sql.Date sqld = ASqlDate(par.getFecha());

        try {
            pst = c.prepareStatement("Update parametros set iva=?, procaumento=?, irpf=? Where fecha=?");
            pst.setInt(1, par.getIva());
            pst.setFloat(2, par.getProcaumento());
            pst.setFloat(3, par.getIrpf());
            pst.setDate(4, sqld);
            pst.executeUpdate();
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        c.close();
    }
}
