/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import dominio.d_gastos_inq;
import dominio.d_inquilino;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Gonzalo
 */
public class p_inquilinos {

    public static Integer generarid(Integer prop_id) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        ResultSet res;
        Statement st;
        Boolean haydatos;
        Integer idobtenido;
        Integer idretorno;

        haydatos = false;
        idobtenido = -1;
        idretorno = 1;
        try {
            st = c.createStatement();
            res = st.executeQuery("Select prop_id, inq_casa From inquilinos where prop_id=" + prop_id + " Order By inq_casa desc");
            haydatos = res.next();
            while (haydatos) {
                idobtenido = (res.getInt("inq_casa"));
                if (idobtenido >= idretorno) {
                    idretorno = idobtenido + 1;
                }
                haydatos = false;
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return idretorno;
    }

    public static Date devuelve_fecha_ic(Integer prop_id, Integer inq_casa) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        ResultSet res;
        Statement st;
        Date fecha = null;

        try {
            st = c.createStatement();
            res = st.executeQuery("Select inq_fechaic From inquilinos where prop_id=" + prop_id + " and inq_casa=" + inq_casa);
            while (res.next()) {
                fecha = (res.getDate("inq_fechaic"));
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return fecha;
    }

    public static void guardarinquilino(d_inquilino inq) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        PreparedStatement pst;
        Integer resultado;
        java.sql.Date sqld = ASqlDate(inq.getArlmrl());
        java.sql.Date sqld1 = ASqlDate(inq.getInq_fechaic());
        java.sql.Date sqld2 = ASqlDate(inq.getInq_fchcontratoaux());

        try {
            pst = c.prepareStatement("Update inquilinos set inq_nombre=?, inq_direccion=?, inq_tel=?, inq_tipoalq=?, inq_fechaic=?, arlmrl=?, inq_garantia=?, inq_padcasa=?, inq_impalq=?, inq_saldo=?, inq_por=?, inq_plazo=?, inq_observaciones=?, inq_irpf=?,inq_fchcontratoaux=?,inq_impalq_aux=? Where prop_id=? and inq_casa=?");
            pst.setString(1, inq.getInq_nombre());
            pst.setString(2, inq.getInq_direccion());
            pst.setInt(3, inq.getInq_tel());
            pst.setString(4, inq.getInq_tipoalq());
            pst.setDate(5, sqld1);
            pst.setDate(6, sqld);
            pst.setString(7, inq.getInq_garantia());
            pst.setString(8, inq.getInq_padcasa());
            pst.setFloat(9, inq.getInq_impalq());
            pst.setFloat(10, inq.getInq_saldo());
            pst.setFloat(11, inq.getInq_por());
            pst.setInt(12, inq.getInq_plazo());
            pst.setString(13, inq.getInq_observaciones());
            pst.setString(14, inq.getInq_irpf());
            pst.setDate(15, sqld2);
            pst.setFloat(16, inq.getInq_impalq_aux());
            pst.setInt(17, inq.getProp_id());
            pst.setInt(18, inq.getInq_casa());
            resultado = pst.executeUpdate();
            if (resultado == 0) {
                pst = c.prepareStatement("Insert Into inquilinos (prop_id, inq_casa, inq_nombre, inq_direccion, inq_tel, inq_tipoalq, inq_fechaic, arlmrl, inq_garantia, inq_padcasa, inq_impalq, inq_saldo, inq_por, inq_plazo, inq_observaciones, inq_irpf,inq_fchcontratoaux,inq_impalq_aux) Values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                pst.setInt(1, inq.getProp_id());
                pst.setInt(2, inq.getInq_casa());
                pst.setString(3, inq.getInq_nombre());
                pst.setString(4, inq.getInq_direccion());
                pst.setInt(5, inq.getInq_tel());
                pst.setString(6, inq.getInq_tipoalq());
                pst.setDate(7, sqld1);
                pst.setDate(8, sqld);
                pst.setString(9, inq.getInq_garantia());
                pst.setString(10, inq.getInq_padcasa());
                pst.setFloat(11, inq.getInq_impalq());
                pst.setFloat(12, inq.getInq_saldo());
                pst.setFloat(13, inq.getInq_por());
                pst.setInt(14, inq.getInq_plazo());
                pst.setString(15, inq.getInq_observaciones());
                pst.setString(16, inq.getInq_irpf());
                pst.setDate(17, sqld2);
                pst.setFloat(18, inq.getInq_impalq_aux());
                pst.executeUpdate();
            }
        } catch (SQLException ex) {
            if (ex.getMessage().equals("Los datos de cadena o binarios se truncarían.")) {
                throw new Exception("revise campos, ya que al menos uno supera el limite de caracteres");
            }
        }
        c.close();
    }

    public static List<d_inquilino> listarinquilinos() throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        d_inquilino inq = null;
        Statement st;
        ResultSet res;
        List<d_inquilino> lista = new ArrayList<d_inquilino>();

        try {
            st = c.createStatement();
            res = st.executeQuery("select * from inquilinos order by prop_id");
            while (res.next()) {
                inq = new d_inquilino();
                inq.setProp_id(Integer.parseInt(res.getString("prop_id")));
                inq.setInq_casa(Integer.parseInt(res.getString("inq_casa")));
                inq.setInq_nombre(res.getString("inq_nombre"));
                inq.setInq_direccion(res.getString("inq_direccion"));
                inq.setInq_tel(Integer.parseInt(res.getString("inq_tel")));
                inq.setInq_observaciones(res.getString("inq_observaciones"));
                inq.setInq_fechaic(res.getDate("inq_fechaic"));
                inq.setArlmrl(res.getDate("arlmrl"));
                lista.add(inq);
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return lista;

    }

    public static List<d_inquilino> listarinquilinosparactualizarsaldos() throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        d_inquilino inq = null;
        Statement st;
        ResultSet res;
        List<d_inquilino> lista = new ArrayList<d_inquilino>();

        try {
            st = c.createStatement();
            res = st.executeQuery("select * from inquilinos order by prop_id");
            while (res.next()) {
                inq = new d_inquilino();
                inq.setProp_id(Integer.parseInt(res.getString("prop_id")));
                inq.setInq_casa(Integer.parseInt(res.getString("inq_casa")));
                lista.add(inq);
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return lista;

    }

    public static List<d_inquilino> listarinquilinoscompleto() throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        d_inquilino inq = null;
        Statement st;
        ResultSet res;
        List<d_inquilino> lista = new ArrayList<>();

        try {
            st = c.createStatement();
            res = st.executeQuery("select * from inquilinos order by inq_fechaic asc");
            while (res.next()) {
                inq = new d_inquilino();
                inq.setProp_id(res.getInt("prop_id"));
                inq.setInq_casa(res.getInt("inq_casa"));
                inq.setInq_nombre(res.getString("inq_nombre"));
                inq.setInq_direccion(res.getString("inq_direccion"));
                inq.setInq_tel(res.getInt("inq_tel"));
                inq.setInq_tipoalq(res.getString("inq_tipoalq"));
                inq.setInq_fechaic(res.getDate("inq_fechaic"));
                inq.setArlmrl(res.getDate("arlmrl"));
                inq.setInq_garantia(res.getString("inq_garantia"));
                inq.setInq_padcasa(res.getString("inq_padcasa"));
                inq.setNms(res.getInt("nms"));
                inq.setNmi(res.getInt("nmi"));
                inq.setInq_impalq(res.getFloat("inq_impalq"));
                inq.setInq_saldo(res.getFloat("inq_saldo"));
                inq.setInq_por(res.getFloat("inq_por"));
                inq.setInq_plazo(res.getInt("inq_plazo"));
                inq.setInq_observaciones(res.getString("inq_observaciones"));
                inq.setInq_irpf(res.getString("inq_irpf"));
                inq.setInq_fchcontratoaux(res.getDate("inq_fchcontratoaux"));
                inq.setInq_impalq_aux(res.getFloat("inq_impalq_aux"));
                lista.add(inq);
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return lista;

    }

    public static List<d_inquilino> listarinquilinospornombre() throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        d_inquilino inq = null;
        Statement st;
        ResultSet res;
        List<d_inquilino> lista = new ArrayList<d_inquilino>();

        try {
            st = c.createStatement();
            res = st.executeQuery("select * from inquilinos order by inq_nombre");
            while (res.next()) {
                inq = new d_inquilino();
                inq.setProp_id(Integer.parseInt(res.getString("prop_id")));
                inq.setInq_casa(Integer.parseInt(res.getString("inq_casa")));
                inq.setInq_nombre(res.getString("inq_nombre"));
                inq.setInq_direccion(res.getString("inq_direccion"));
                inq.setInq_tel(Integer.parseInt(res.getString("inq_tel")));
                inq.setInq_observaciones(res.getString("inq_observaciones"));
                lista.add(inq);
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return lista;

    }

    public static List<d_inquilino> listarinquilinosbloqueados() throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        d_inquilino inq = null;
        Statement st;
        ResultSet res;
        List<d_inquilino> lista = new ArrayList<d_inquilino>();

        try {
            st = c.createStatement();
            res = st.executeQuery("select * from inquilinos where bloqueado = 1 order by inq_nombre");
            while (res.next()) {
                inq = new d_inquilino();
                inq.setProp_id(Integer.parseInt(res.getString("prop_id")));
                inq.setInq_casa(Integer.parseInt(res.getString("inq_casa")));
                inq.setInq_nombre(res.getString("inq_nombre"));
                inq.setInq_direccion(res.getString("inq_direccion"));
                inq.setInq_tel(Integer.parseInt(res.getString("inq_tel")));
                inq.setInq_observaciones(res.getString("inq_observaciones"));
                lista.add(inq);
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return lista;

    }

    public static List<d_inquilino> listarinquilinospordireccion() throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        d_inquilino inq = null;
        Statement st;
        ResultSet res;
        List<d_inquilino> lista = new ArrayList<d_inquilino>();

        try {
            st = c.createStatement();
            res = st.executeQuery("select * from inquilinos order by inq_direccion");
            while (res.next()) {
                inq = new d_inquilino();
                inq.setProp_id(Integer.parseInt(res.getString("prop_id")));
                inq.setInq_casa(Integer.parseInt(res.getString("inq_casa")));
                inq.setInq_nombre(res.getString("inq_nombre"));
                inq.setInq_direccion(res.getString("inq_direccion"));
                inq.setInq_tel(Integer.parseInt(res.getString("inq_tel")));
                inq.setInq_observaciones(res.getString("inq_observaciones"));
                lista.add(inq);
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return lista;
    }

    public static List<d_gastos_inq> listarinquilinosmorosos() throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        d_gastos_inq gas = null;
        Statement st;
        ResultSet res;
        List<d_gastos_inq> lista = new ArrayList<>();

        try {
            st = c.createStatement();
            Integer mes = devuelvemes(new Date());
            Integer anio = devuelveanio(new Date());
            String fecha_filtro = (mes + "" + anio);
            res = st.executeQuery("select prop_id, inq_casa,count(*) as total_meses,sum(importe)as total_importe from gastos_inq where (concat(mqp,aqp)<" + fecha_filtro + ") and \n"
                    + "(detalle='SALDO ALQUILER' or detalle='ALQUILER MES' or detalle='ALQUILER') \n"
                    + " and estado=0 group by prop_id, inq_casa order by prop_id");

            /*res = st.executeQuery("select prop_id, inq_casa,count(*) as total_meses,sum(importe)as total_importe from gastos_inq where (concat(mqp,aqp)!='" + fecha_filtro + "') and \n"
                    + "(detalle='SALDO ALQUILER' or detalle='ALQUILER MES' or detalle='ALQUILER') \n"
                    + " and estado=0 group by prop_id, inq_casa order by prop_id");*/
            while (res.next()) {
                gas = new d_gastos_inq();
                gas.setProp_id(Integer.parseInt(res.getString("prop_id")));
                gas.setInq_casa(Integer.parseInt(res.getString("inq_casa")));
                gas.setAqp(Integer.parseInt(res.getString("total_meses")));
                gas.setImporte((res.getFloat("total_importe")));
                lista.add(gas);
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return lista;
    }

    //new Date()
    public static Integer devuelvemes(Date fecha) {
        String formato = "M";
        SimpleDateFormat dateFormat = new SimpleDateFormat(formato);
        return Integer.parseInt(dateFormat.format(fecha));
    }

    public static Integer devuelveanio(Date fecha) {
        String formato = "yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(formato);
        return Integer.parseInt(dateFormat.format(fecha));
    }

    public static Date buscarfechareajusteoriginal(Integer prop_id, Integer inq_casa) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        Statement st;
        ResultSet res;

        try {
            st = c.createStatement();
            res = st.executeQuery("select arlmrl from inquilinos where prop_id='" + prop_id + "'and inq_casa='" + inq_casa + "'");
            while (res.next()) {
                return res.getDate("arlmrl");
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return null;
    }

    public static Date buscarfechaicoriginal(Integer prop_id, Integer inq_casa) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        Statement st;
        ResultSet res;

        try {
            st = c.createStatement();
            res = st.executeQuery("select inq_fechaic from inquilinos where prop_id='" + prop_id + "'and inq_casa='" + inq_casa + "'");
            while (res.next()) {
                return res.getDate("inq_fechaic");
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return null;
    }

    public static Date buscarfechaarlmrloriginal(Integer prop_id, Integer inq_casa) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        Statement st;
        ResultSet res;

        try {
            st = c.createStatement();
            res = st.executeQuery("select arlmrl from inquilinos where prop_id='" + prop_id + "'and inq_casa='" + inq_casa + "'");
            while (res.next()) {
                return res.getDate("arlmrl");
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return null;
    }

    public static d_inquilino buscarinquilino(Integer prop_id, Integer inq_casa) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        Statement st;
        ResultSet res;
        d_inquilino inq = null;

        try {
            st = c.createStatement();
            res = st.executeQuery("select * from inquilinos where prop_id='" + prop_id + "'and inq_casa='" + inq_casa + "'");
            while (res.next()) {
                inq = new d_inquilino();
                inq.setProp_id(res.getInt("prop_id"));
                inq.setInq_casa(res.getInt("inq_casa"));
                inq.setInq_nombre(res.getString("inq_nombre"));
                inq.setInq_direccion(res.getString("inq_direccion"));
                inq.setInq_tel(res.getInt("inq_tel"));
                inq.setInq_tipoalq(res.getString("inq_tipoalq"));
                inq.setInq_fechaic(res.getDate("inq_fechaic"));
                inq.setArlmrl(res.getDate("arlmrl"));
                inq.setInq_garantia(res.getString("inq_garantia"));
                inq.setInq_padcasa(res.getString("inq_padcasa"));
                inq.setNms(res.getInt("nms"));
                inq.setNmi(res.getInt("nmi"));
                inq.setInq_impalq(res.getFloat("inq_impalq"));
                inq.setInq_saldo(res.getFloat("inq_saldo"));
                inq.setInq_por(res.getFloat("inq_por"));
                inq.setInq_plazo(res.getInt("inq_plazo"));
                inq.setInq_observaciones(res.getString("inq_observaciones"));
                inq.setInq_irpf(res.getString("inq_irpf"));
                inq.setInq_fchcontratoaux(res.getDate("inq_fchcontratoaux"));
                inq.setInq_impalq_aux(res.getFloat("inq_impalq_aux"));
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return inq;
    }

    public static String buscarnombreinquilino(Integer prop_id, Integer inq_casa) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        Statement st;
        ResultSet res;

        try {
            st = c.createStatement();
            res = st.executeQuery("select inq_nombre from inquilinos where prop_id='" + prop_id + "'and inq_casa='" + inq_casa + "'");
            while (res.next()) {
                return (res.getString("inq_nombre"));
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return "";
    }

    public static d_inquilino buscarinquilinopornombre(String nombre) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        Statement st;
        ResultSet res;
        d_inquilino inq = null;

        try {
            st = c.createStatement();
            res = st.executeQuery("select * from inquilinos where inq_nombre='" + nombre + "'");
            while (res.next()) {
                inq = new d_inquilino();
                inq.setProp_id(res.getInt("prop_id"));
                inq.setInq_casa(res.getInt("inq_casa"));
                inq.setInq_nombre(res.getString("inq_nombre"));
                inq.setInq_direccion(res.getString("inq_direccion"));
                inq.setInq_tel(res.getInt("inq_tel"));
                inq.setInq_tipoalq(res.getString("inq_tipoalq"));
                inq.setInq_fechaic(res.getDate("inq_fechaic"));
                inq.setArlmrl(res.getDate("arlmrl"));
                inq.setInq_garantia(res.getString("inq_garantia"));
                inq.setInq_padcasa(res.getString("inq_padcasa"));
                inq.setNms(res.getInt("nms"));
                inq.setNmi(res.getInt("nmi"));
                inq.setInq_impalq(res.getFloat("inq_impalq"));
                inq.setInq_saldo(res.getFloat("inq_saldo"));
                inq.setInq_por(res.getFloat("inq_por"));
                inq.setInq_plazo(res.getInt("inq_plazo"));
                inq.setInq_observaciones(res.getString("inq_observaciones"));
                inq.setInq_irpf(res.getString("inq_irpf"));
                inq.setInq_fchcontratoaux(res.getDate("inq_fchcontratoaux"));
                inq.setInq_impalq_aux(res.getFloat("inq_impalq_aux"));
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return inq;
    }

    public static void actualizarimporteaux(Integer prop_id, Integer inq_casa, Float importe) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        PreparedStatement pst;

        //ACTUALIZAR EN NUEVA TABLA DE IMPORTES DE ALQUILER
        try {
            pst = c.prepareStatement("Update inquilinos set inq_impalq_aux = ? Where prop_id=? and inq_casa=?");
            pst.setFloat(1, importe);
            pst.setInt(2, prop_id);
            pst.setInt(3, inq_casa);
            pst.executeUpdate();
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public static void actualizarsaldo(Integer prop_id, Integer inq_casa, Float importe) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        PreparedStatement pst;

        try {
            pst = c.prepareStatement("Update inquilinos set inq_saldo = ? Where prop_id=? and inq_casa=?");
            pst.setFloat(1, importe);
            pst.setInt(2, prop_id);
            pst.setInt(3, inq_casa);
            pst.executeUpdate();
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public static void actualizarplazo(Integer prop_id, Integer inq_casa, Integer plazo) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        PreparedStatement pst;

        try {
            pst = c.prepareStatement("Update inquilinos set inq_plazo = ? Where prop_id=? and inq_casa=?");
            pst.setInt(1, plazo);
            pst.setInt(2, prop_id);
            pst.setInt(3, inq_casa);
            pst.executeUpdate();
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public static void actualizarreajuste(Integer prop_id, Integer inq_casa, Date reajuste) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        PreparedStatement pst;

        try {
            pst = c.prepareStatement("Update inquilinos set arlmrl = ? Where prop_id=? and inq_casa=?");
            pst.setDate(1, ASqlDate(reajuste));
            pst.setInt(2, prop_id);
            pst.setInt(3, inq_casa);
            pst.executeUpdate();
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public static void actualizardatosalquiler(d_inquilino inq) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        PreparedStatement pst;

        try {
            pst = c.prepareStatement("Update inquilinos set inq_fchcontratoaux = ?, inq_impalq=? Where prop_id=? and inq_casa=?");
            pst.setDate(1, ASqlDate(inq.getInq_fchcontratoaux()));
            pst.setFloat(2, inq.getInq_impalq());
            pst.setInt(3, inq.getProp_id());
            pst.setInt(4, inq.getInq_casa());
            pst.executeUpdate();
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public static void actualizarfechareajuste(Integer prop_id, Integer inq_casa, Date fecha) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        PreparedStatement pst;

        java.sql.Date sqld = ASqlDate(fecha);

        try {
            pst = c.prepareStatement("Update inquilinos set arlmrl = ? Where prop_id=? and inq_casa=?");
            pst.setDate(1, sqld);
            pst.setInt(2, prop_id);
            pst.setInt(3, inq_casa);
            pst.executeUpdate();
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public static void reiniciarimpalquilergeneral() throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        Statement st;

        try {
            st = c.createStatement();
            st.execute("update inquilinos set inq_impalq_aux=inq_impalq");
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        c.close();
    }

    public static void reiniciarimpalquilerparticular(Integer prop_id, Integer inq_casa) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        Statement st;

        try {
            st = c.createStatement();
            st.execute("update inquilinos set inq_impalq_aux=inq_impalq where prop_id='" + prop_id + "'and inq_casa='" + inq_casa + "'");
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        c.close();
    }

    public static void eliminarinquilino(Integer prop_id, Integer inq_casa) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        Statement st;

        try {
            st = c.createStatement();
            st.execute("delete from inquilinos where prop_id='" + prop_id + "'and inq_casa='" + inq_casa + "'");
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        c.close();
    }

    public static void bloquear_inquilino(Integer prop_id, Integer inq_casa) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        Statement st;

        try {
            st = c.createStatement();
            st.execute("update inquilinos set bloqueado = 1 where prop_id='" + prop_id + "'and inq_casa='" + inq_casa + "'");
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        c.close();
    }

    public static void desbloquear_inquilino(Integer prop_id, Integer inq_casa) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        Statement st;

        try {
            st = c.createStatement();
            st.execute("update inquilinos set bloqueado = 0 where prop_id='" + prop_id + "'and inq_casa='" + inq_casa + "'");
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        c.close();
    }

    public static Boolean inquilino_bloqueado(Integer prop_id, Integer inq_casa) throws Exception {
        Connection c;
        p_conexion conex = p_conexion.getInstancia();
        c = conex.crearconexion();
        ResultSet res;
        Statement st;
        Integer bloqueado = 0;

        try {
            st = c.createStatement();
            res = st.executeQuery("Select bloqueado From inquilinos where prop_id=" + prop_id + " and inq_casa=" + inq_casa);
            while (res.next()) {
                bloqueado = (res.getInt("bloqueado"));
                if (bloqueado == 1) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
        res.close();
        c.close();
        return false;
    }

    public static java.sql.Date ASqlDate(java.util.Date fecha) {
        java.sql.Date sqldate = null;
        sqldate = new java.sql.Date(fecha.getTime());

        return sqldate;
    }

}
