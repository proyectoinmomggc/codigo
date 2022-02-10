/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import dominio.d_cfe;
import dominio.d_inquilino;
import dominio.d_movimiento;
import dominio.d_propietario;
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
public class p_cfe {

    public static Integer ultimoid() throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        ResultSet res;
        Statement st;
        Integer idobtenido;

        idobtenido = -1;
        try {
            st = c.createStatement();
            res = st.executeQuery("SELECT MAX(idmov)as id FROM cfe");
            while (res.next()) {
                idobtenido = (res.getInt("id"));
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return idobtenido;
    }

    public static Integer minid() throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        ResultSet res;
        Statement st;
        Integer idobtenido;

        idobtenido = -1;
        try {
            st = c.createStatement();
            res = st.executeQuery("SELECT MIN(idmov)as id FROM cfe");
            while (res.next()) {
                idobtenido = (res.getInt("id"));
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return idobtenido;
    }

    public static d_cfe buscarcfe(Integer idmov) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        Statement st;
        ResultSet res;
        d_cfe cfe = null;

        try {
            st = c.createStatement();
            res = st.executeQuery("select * from cfe where idmov=" + idmov);
            while (res.next()) {
                cfe = new d_cfe();
                cfe.setIdmov(res.getInt("idmov"));
                cfe.setSerie(res.getString("serie"));
                cfe.setNumero(res.getInt("numero"));
                cfe.setRucemisor(res.getString("rucemisor"));
                cfe.setTipo(res.getInt("tipo"));
                cfe.setObservado(res.getInt("observado"));
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return cfe;
    }

    public static List<d_cfe> listarcfe() throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        d_cfe cfe = null;
        Statement st;
        ResultSet res;
        List<d_cfe> lista = new ArrayList<d_cfe>();

        try {
            st = c.createStatement();
            res = st.executeQuery("select * from cfe order by idmov");
            while (res.next()) {
                cfe = new d_cfe();
                cfe.setIdmov(res.getInt("idmov"));
                cfe.setSerie(res.getString("serie"));
                cfe.setNumero(res.getInt("numero"));
                cfe.setRucemisor(res.getString("rucemisor"));
                cfe.setTipo(res.getInt("tipo"));
                cfe.setObservado(res.getInt("observado"));
                lista.add(cfe);
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return lista;
    }

    public static List<d_cfe> listarcfe_todos() throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        d_cfe cfe = null;
        Statement st;
        ResultSet res;
        List<d_cfe> lista = new ArrayList<d_cfe>();

        try {
            st = c.createStatement();
            res = st.executeQuery("select * from cfe where (tipo=111 or tipo=101) order by idmov asc");
            //res = st.executeQuery("select * from cfe order by idmov desc");
            while (res.next()) {
                cfe = new d_cfe();
                cfe.setIdmov(res.getInt("idmov"));
                cfe.setSerie(res.getString("serie"));
                cfe.setNumero(res.getInt("numero"));
                cfe.setRucemisor(res.getString("rucemisor"));
                cfe.setTipo(res.getInt("tipo"));
                cfe.setObservado(res.getInt("observado"));
                lista.add(cfe);
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return lista;
    }

    public static List<d_cfe> listarnotas() throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        d_cfe cfe = null;
        Statement st;
        ResultSet res;
        List<d_cfe> lista = new ArrayList<d_cfe>();

        try {
            st = c.createStatement();
            res = st.executeQuery("select * from cfe where tipo=102 or tipo=112 order by idmov desc");
            while (res.next()) {
                cfe = new d_cfe();
                cfe.setIdmov(res.getInt("idmov"));
                cfe.setSerie(res.getString("serie"));
                cfe.setNumero(res.getInt("numero"));
                cfe.setRucemisor(res.getString("rucemisor"));
                cfe.setTipo(res.getInt("tipo"));
                cfe.setObservado(res.getInt("observado"));
                lista.add(cfe);
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return lista;
    }

    public static void guardarcfe(d_cfe cfe) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        PreparedStatement pst;
        Integer resultado;

        try {
            pst = c.prepareStatement("Update cfe set serie=?, numero=?, rucemisor=?, tipo=?, observado=? Where idmov=?");
            pst.setString(1, cfe.getSerie());
            pst.setInt(2, cfe.getNumero());
            pst.setString(3, cfe.getRucemisor());
            pst.setInt(4, cfe.getTipo());
            pst.setInt(5, cfe.getObservado());
            pst.setInt(6, cfe.getIdmov());
            resultado = pst.executeUpdate();
            if (resultado == 0) {
                pst = c.prepareStatement("Insert Into cfe Values (?,?,?,?,?,?)");
                pst.setInt(1, cfe.getIdmov());
                pst.setString(2, cfe.getSerie());
                pst.setInt(3, cfe.getNumero());
                pst.setString(4, cfe.getRucemisor());
                pst.setInt(5, cfe.getTipo());
                pst.setInt(6, cfe.getObservado());
                pst.executeUpdate();
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        c.close();
    }

    public static void actualizar_id(int id_viejo, int id_nuevo) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        PreparedStatement pst;

        try {
            pst = c.prepareStatement("update cfe set idmov=" + id_nuevo + " where idmov=" + id_viejo);
            int i=pst.executeUpdate();
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        c.close();
    }
}
