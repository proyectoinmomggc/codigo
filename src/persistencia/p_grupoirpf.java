/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import dominio.d_grupoirpf;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gonzalo
 */
public class p_grupoirpf {

    public static float montogrupoirpfporprop(Integer prop_id) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        Statement st;
        ResultSet res;
        float resultado = 0f;

        try {
            st = c.createStatement();
            res = st.executeQuery("select sum(porcentaje) as suma from grupoirpf where prop_id='" + prop_id + "'");
            while (res.next()) {
                resultado = (res.getFloat("suma"));
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return resultado;
    }

    public static void guardarnuevogrupoirpf(d_grupoirpf gru) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        PreparedStatement pst;

        try {
            pst = c.prepareStatement("Insert Into grupoirpf Values (?,?,?,?,?,?)");
            pst.setInt(1, gru.getProp_id());
            pst.setString(2, gru.getNombre());
            pst.setString(3, gru.getCigrupo());
            pst.setFloat(4, gru.getPorcentaje());
            pst.setFloat(5, gru.getMonto());
            pst.setString(6, gru.getFecha());
            pst.executeUpdate();
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        c.close();
    }

    public static Integer cantciporprop(Integer prop_id) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        Statement st;
        ResultSet res;
        Integer cantidad = 0;

        try {
            st = c.createStatement();
            String consulta = "select count (*) as totalci from grupoirpf where prop_id=" + prop_id + "";
            res = st.executeQuery(consulta);
            while (res.next()) {
                cantidad = cantidad + Integer.parseInt(res.getString("totalci"));
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return cantidad;
    }

    public static Integer cantnomdistintos(String cigrupo) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        Statement st;
        ResultSet res;
        Integer cantidad = 0;

        try {
            st = c.createStatement();
            String consulta = "select count (distinct nombre) as cantnombre from grupoirpf\n"
                    + "where cigrupo='" + cigrupo + "' group by cigrupo order by cantnombre desc";
            res = st.executeQuery(consulta);
            while (res.next()) {
                cantidad = (res.getInt("cantnombre"));
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return cantidad;
    }

    public static String nombredeci(String cigrupo) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        Statement st;
        ResultSet res;
        String nombredeci = "";

        try {
            st = c.createStatement();
            String consulta = "select top 1 nombre from grupoirpf where cigrupo='" + cigrupo + "'";
            res = st.executeQuery(consulta);
            while (res.next()) {
                nombredeci = (res.getString("nombre"));
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return nombredeci;
    }

    public static void actualizargrupoirpf(d_grupoirpf gru) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        PreparedStatement pst;

        try {
            pst = c.prepareStatement("Update grupoirpf set nombre=?, porcentaje=? Where prop_id=? and cigrupo=?");
            pst.setString(1, gru.getNombre());
            pst.setFloat(2, gru.getPorcentaje());
            pst.setInt(3, gru.getProp_id());
            pst.setString(4, gru.getCigrupo());
            pst.executeUpdate();
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        c.close();
    }

    public static void actualizarnombresdeci(String nombre, String cigrupo) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        PreparedStatement pst;

        try {
            pst = c.prepareStatement("update grupoirpf set nombre='" + nombre + "' where cigrupo='" + cigrupo + "'");
            pst.executeUpdate();
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        c.close();
    }

    public static void eliminargrupoirpf(d_grupoirpf gru) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        PreparedStatement pst;

        try {
            pst = c.prepareStatement("delete from grupoirpf where prop_id=" + gru.getProp_id() + " and cigrupo='" + gru.getCigrupo() + "'and porcentaje='" + gru.getPorcentaje() + "'");
            pst.execute();
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        c.close();
    }

    public static List<d_grupoirpf> listarciporproppago(Integer prop_id) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        d_grupoirpf gru = null;
        Statement st;
        ResultSet res;
        List<d_grupoirpf> lista = new ArrayList<d_grupoirpf>();

        //ver de filtrar por fecha
        try {
            st = c.createStatement();
            res = st.executeQuery("select distinct cigrupo from grupoirpf where prop_id=" + prop_id);
            while (res.next()) {
                gru = new d_grupoirpf();
                gru.setCigrupo(res.getString("cigrupo"));
                lista.add(gru);
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return lista;
    }

    public static List<d_grupoirpf> listarciporprop(Integer prop_id) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        d_grupoirpf gru = null;
        Statement st;
        ResultSet res;
        List<d_grupoirpf> lista = new ArrayList<d_grupoirpf>();

        //ver de filtrar por fecha
        try {
            st = c.createStatement();
            //select distinct cigrupo
            res = st.executeQuery("select cigrupo, nombre, porcentaje from grupoirpf where prop_id='" + prop_id + "' group by cigrupo, nombre, porcentaje");
            while (res.next()) {
                gru = new d_grupoirpf();
                gru.setCigrupo(res.getString("cigrupo"));
                gru.setNombre(res.getString("nombre"));
                gru.setPorcentaje(res.getFloat("porcentaje"));
                lista.add(gru);
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return lista;
    }

    public static List<d_grupoirpf> listarciporpropvieja(Integer prop_id) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        d_grupoirpf gru = null;
        Statement st;
        ResultSet res;
        List<d_grupoirpf> lista = new ArrayList<d_grupoirpf>();

        //ver de filtrar por fecha
        try {
            st = c.createStatement();
            res = st.executeQuery("select distinct cigrupo from grupoirpf where prop_id=" + prop_id);
            while (res.next()) {
                gru = new d_grupoirpf();
                gru.setCigrupo(res.getString("cigrupo"));
                gru = buscargrupoirpfporci(gru.getCigrupo());
                lista.add(gru);
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return lista;
    }

    public static d_grupoirpf buscargrupoirpfporci(String cigrupo) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        Statement st;
        ResultSet res;
        d_grupoirpf gru = null;

        try {
            st = c.createStatement();
            res = st.executeQuery("select * from grupoirpf where cigrupo='" + cigrupo + "'");
            while (res.next()) {
                gru = new d_grupoirpf();
                gru.setProp_id(res.getInt("prop_id"));
                gru.setNombre(res.getString("nombre"));
                gru.setCigrupo(res.getString("cigrupo"));
                gru.setPorcentaje(res.getFloat("porcentaje"));
                gru.setMonto(res.getFloat("monto"));
                gru.setFecha(res.getString("fecha"));
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return gru;
    }

    public static void actualizarmontogrupoirpf(d_grupoirpf gru) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        PreparedStatement pst;

        try {
            pst = c.prepareStatement("Update grupoirpf set nombre=?, porcentaje=?, monto=? Where prop_id=? and cigrupo=? and fecha=?");
            pst.setString(1, gru.getNombre());
            pst.setFloat(2, gru.getPorcentaje());
            pst.setFloat(3, gru.getMonto());
            pst.setInt(4, gru.getProp_id());
            pst.setString(5, gru.getCigrupo());
            pst.setString(6, gru.getFecha());
            pst.executeUpdate();
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        c.close();
    }

    public static Integer obtenercantci(Integer prop_id) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        Statement st;
        ResultSet res;
        Integer cantidad = 0;

        try {
            st = c.createStatement();
            String consulta = "SELECT COUNT(DISTINCT cigrupo)as cantidad FROM grupoirpf where prop_id=" + prop_id + " group by cigrupo ";
            res = st.executeQuery(consulta);
            while (res.next()) {
                cantidad = cantidad + Integer.parseInt(res.getString("cantidad"));
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return cantidad;
    }

    public static Float obtenermontoci(Integer prop_id, String cigrupo, String fecha) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        Statement st;
        ResultSet res;
        Float monto = 0.0f;

        try {
            st = c.createStatement();
            String consulta = "SELECT monto FROM grupoirpf where prop_id='" + prop_id + "' and cigrupo = '" + cigrupo + "' and fecha = '" + fecha + "'";
            res = st.executeQuery(consulta);
            while (res.next()) {
                monto = res.getFloat("monto");
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return monto;
    }

    public static d_grupoirpf buscargrupoirpf(Integer prop_id, String cigrupo) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        Statement st;
        ResultSet res;
        d_grupoirpf gru = null;

        try {
            st = c.createStatement();
            res = st.executeQuery("select * from grupoirpf where prop_id='" + prop_id + "'and cigrupo='" + cigrupo + "'");
            while (res.next()) {
                gru = new d_grupoirpf();
                gru.setProp_id(res.getInt("prop_id"));
                gru.setNombre(res.getString("nombre"));
                gru.setCigrupo(res.getString("cigrupo"));
                gru.setPorcentaje(res.getFloat("porcentaje"));
                gru.setMonto(res.getFloat("monto"));
                gru.setFecha(res.getString("fecha"));
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return gru;
    }

    public static List<d_grupoirpf> buscargrupoirpfporfecha(Integer prop_id, String fecha) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        Statement st;
        ResultSet res;
        d_grupoirpf gru = null;
        List<d_grupoirpf> lista = new ArrayList<d_grupoirpf>();

        try {
            st = c.createStatement();
            res = st.executeQuery("select * from grupoirpf where prop_id='" + prop_id + "'and fecha='" + fecha + "'");
            while (res.next()) {
                gru = new d_grupoirpf();
                gru.setProp_id(res.getInt("prop_id"));
                gru.setNombre(res.getString("nombre"));
                gru.setCigrupo(res.getString("cigrupo"));
                gru.setPorcentaje(res.getFloat("porcentaje"));
                gru.setMonto(res.getFloat("monto"));
                gru.setFecha(res.getString("fecha"));
                lista.add(gru);
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return lista;
    }

    public static void actualizarmontogrupoirpfporfecha(d_grupoirpf gru) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        PreparedStatement pst;

        try {
            pst = c.prepareStatement("Update grupoirpf set monto=? Where prop_id=? and fecha=? and nombre=? and cigrupo=?");
            pst.setFloat(1, gru.getMonto());
            pst.setInt(2, gru.getProp_id());
            pst.setString(3, gru.getFecha());
            pst.setString(4, gru.getNombre());
            pst.setString(5, gru.getCigrupo());
            pst.executeUpdate();
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        c.close();
    }

    public static float montogrupoirpfmenosci(Integer prop_id, String cigrupo) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        Statement st;
        ResultSet res;
        float resultado = 0f;

        try {
            st = c.createStatement();
            res = st.executeQuery("select distinct cigrupo,porcentaje from grupoirpf where prop_id='" + prop_id + "'and cigrupo <> '" + cigrupo + "'group by cigrupo,porcentaje");
            while (res.next()) {
                resultado = resultado + (res.getFloat("porcentaje"));
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return resultado;
    }

    public static List<d_grupoirpf> listarmontospormes(String fecha) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        d_grupoirpf gru = null;
        Statement st;
        ResultSet res;
        List<d_grupoirpf> lista = new ArrayList<d_grupoirpf>();

        try {
            st = c.createStatement();
            res = st.executeQuery("select prop_id,nombre,cigrupo, sum (monto) as monto,fecha from grupoirpf where fecha=" + fecha + " and monto > 0 group by prop_id,nombre,cigrupo, fecha");
            while (res.next()) {
                gru = new d_grupoirpf();
                gru.setProp_id(res.getInt("prop_id"));
                gru.setNombre(res.getString("nombre"));
                gru.setCigrupo(res.getString("cigrupo"));
                gru.setMonto(res.getFloat("monto"));
                gru.setFecha(res.getString("fecha"));
                lista.add(gru);
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return lista;
    }

    public static List<d_grupoirpf> listargruposirpf() throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        d_grupoirpf gru = null;
        Statement st;
        ResultSet res;
        List<d_grupoirpf> lista = new ArrayList<d_grupoirpf>();

        try {
            st = c.createStatement();
            res = st.executeQuery("select * from grupoirpf");
            while (res.next()) {
                gru = new d_grupoirpf();
                gru.setProp_id(res.getInt("prop_id"));
                gru.setNombre(res.getString("nombre"));
                gru.setCigrupo(res.getString("cigrupo"));
                gru.setPorcentaje(res.getFloat("porcentaje"));
                gru.setMonto(res.getFloat("monto"));
                gru.setFecha(res.getString("fecha"));
                lista.add(gru);
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return lista;
    }

    public static List<d_grupoirpf> listarproppormes(String fecha) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        d_grupoirpf gru = null;
        Statement st;
        ResultSet res;
        List<d_grupoirpf> lista = new ArrayList<d_grupoirpf>();

        try {
            st = c.createStatement();
            res = st.executeQuery("select distinct (prop_id) from grupoirpf where fecha='" + fecha + "'");
            while (res.next()) {
                gru = new d_grupoirpf();
                gru.setProp_id(res.getInt("prop_id"));
                lista.add(gru);
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return lista;
    }

    public static List<d_grupoirpf> listargrupospormes(String fecha) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        d_grupoirpf gru = null;
        Statement st;
        ResultSet res;
        List<d_grupoirpf> lista = new ArrayList<>();

        try {
            st = c.createStatement();
            res = st.executeQuery("select prop_id,nombre,cigrupo,SUM(monto)as monto from grupoirpf where fecha in (" + fecha + ") group by prop_id,nombre,cigrupo order by prop_id");
            while (res.next()) {
                gru = new d_grupoirpf();
                gru.setProp_id(res.getInt("prop_id"));
                gru.setNombre(res.getString("nombre"));
                gru.setCigrupo(res.getString("cigrupo"));
                //gru.setNombre(res.getString("nombre"));
                //gru.setCigrupo(res.getString("cigrupo"));
                //gru.setPorcentaje(res.getFloat("porcentaje"));
                gru.setMonto(res.getFloat("monto"));
                
                //gru.setFecha(res.getString("fecha"));
                lista.add(gru);
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return lista;
    }

    public static d_grupoirpf existeciparafecha(String cigrupo, String fecha, Integer prop_id) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        d_grupoirpf gru = null;
        Statement st;
        ResultSet res;

        try {
            st = c.createStatement();
            res = st.executeQuery("select * from grupoirpf where cigrupo='" + cigrupo + "' and fecha='" + fecha + "' and prop_id='" + prop_id + "'");
            while (res.next()) {
                gru = new d_grupoirpf();
                gru.setProp_id(res.getInt("prop_id"));
                gru.setNombre(res.getString("nombre"));
                gru.setCigrupo(res.getString("cigrupo"));
                gru.setPorcentaje(res.getFloat("porcentaje"));
                gru.setMonto(res.getFloat("monto"));
                gru.setFecha(res.getString("fecha"));
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return gru;
    }

    public static d_grupoirpf grupoirpfdeprop(String cigrupo, Integer prop_id) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        d_grupoirpf gru = null;
        Statement st;
        ResultSet res;

        try {
            st = c.createStatement();
            res = st.executeQuery("select top 1 * from grupoirpf where cigrupo='" + cigrupo + "' and prop_id='" + prop_id + "' order by fecha desc");
            while (res.next()) {
                gru = new d_grupoirpf();
                gru.setProp_id(res.getInt("prop_id"));
                gru.setNombre(res.getString("nombre"));
                gru.setCigrupo(res.getString("cigrupo"));
                gru.setPorcentaje(res.getFloat("porcentaje"));
                gru.setMonto(res.getFloat("monto"));
                gru.setFecha(res.getString("fecha"));
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return gru;
    }

    public static List<d_grupoirpf> listarcidistintas() throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        d_grupoirpf gru = null;
        Statement st;
        ResultSet res;
        List<d_grupoirpf> lista = new ArrayList<d_grupoirpf>();

        try {
            st = c.createStatement();
            res = st.executeQuery("select distinct cigrupo from grupoirpf");
            while (res.next()) {
                gru = new d_grupoirpf();
                gru.setCigrupo(res.getString("cigrupo"));
                lista.add(gru);
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return lista;
    }

    public static float montocedulapormes(d_grupoirpf gru, Integer mes, Integer anio) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        Statement st;
        ResultSet res;
        float resultado = 0f;

        try {
            st = c.createStatement();
            res = st.executeQuery("select sum(irpf)as total from movimientos where prop_id='" + gru.getProp_id() + "'"
                    + " and irpftipo='SI' AND \n"
                    + " MONTH(fecha) = " + mes + " AND YEAR(fecha) = " + anio + "");
            while (res.next()) {
                resultado = ((res.getFloat("total")) * gru.getPorcentaje()) / 100;
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return resultado;
    }

    public static Integer actualizarcigrupo(d_grupoirpf gru, String civieja) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        PreparedStatement pst;
        int filas = 0;

        try {
            pst = c.prepareStatement("Update grupoirpf set cigrupo=? Where cigrupo=?");
            pst.setString(1, gru.getCigrupo());
            pst.setString(2, civieja);
            filas = pst.executeUpdate();
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        c.close();
        return filas;
    }
}
