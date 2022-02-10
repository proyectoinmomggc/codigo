/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import net.sf.jasperreports.view.JasperViewer;
import persistencia.p_inquilinos;
import persistencia.p_movimientos;
import persistencia.p_propietario;
import presentacion.paneles.p_control;

/**
 *
 * @author Gonzalo
 */
public class d_propietario {

    Integer prop_id;
    String prop_nombre;
    String prop_direccion;
    Float prop_saldo;
    Integer prop_numcontacto;
    String prop_cirut;
    String prop_observaciones;

    public Integer getProp_id() {
        return prop_id;
    }

    public void setProp_id(Integer prop_id) {
        this.prop_id = prop_id;
    }

    public String getProp_nombre() {
        return prop_nombre;
    }

    public void setProp_nombre(String prop_nombre) {
        this.prop_nombre = prop_nombre;
    }

    public String getProp_direccion() {
        return prop_direccion;
    }

    public void setProp_direccion(String prop_direccion) {
        this.prop_direccion = prop_direccion;
    }

    public Float getProp_saldo() {
        return prop_saldo;
    }

    public void setProp_saldo(Float prop_saldo) {
        this.prop_saldo = prop_saldo;
    }

    public Integer getProp_numcontacto() {
        return prop_numcontacto;
    }

    public void setProp_numcontacto(Integer prop_numcontacto) {
        this.prop_numcontacto = prop_numcontacto;
    }

    public String getProp_cirut() {
        return prop_cirut;
    }

    public void setProp_cirut(String prop_cirut) {
        this.prop_cirut = prop_cirut;
    }

    public String getProp_observaciones() {
        return prop_observaciones;
    }

    public void setProp_observaciones(String prop_observaciones) {
        this.prop_observaciones = prop_observaciones;
    }

    public List<d_propietario> listarpropietarios() throws Exception {
        List<d_propietario> lista = new ArrayList<d_propietario>();

        try {
            lista = p_propietario.listarpropietarios();
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return lista;
    }

    public List<d_propietario> listarpropietariosporid() throws Exception {
        List<d_propietario> lista = new ArrayList<d_propietario>();

        try {
            lista = p_propietario.listarpropietariosporid();
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return lista;
    }

    public List<d_propietario> listarpropietariospornombre() throws Exception {
        List<d_propietario> lista = new ArrayList<d_propietario>();

        try {
            lista = p_propietario.listarpropietariospornombre();
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return lista;
    }

    public List<d_propietario> listarpropietariospordireccion() throws Exception {
        List<d_propietario> lista = new ArrayList<d_propietario>();

        try {
            lista = p_propietario.listarpropietariospordireccion();
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return lista;
    }

    public void guardarpropietario(d_propietario pro) throws Exception {
        p_propietario.guardarpropietario(pro);
    }

    public Integer obtenerid() throws Exception {
        Integer idobtenido = -1;
        idobtenido = p_propietario.generarid();
        return idobtenido;
    }

    public JasperViewer rep_listapropietarios() throws Exception {
        JasperViewer reporte = null;

        reporte = p_propietario.rep_listapropietarios();

        return reporte;
    }

    public d_propietario buscarpropietario(Integer nroprin) throws Exception {
        d_propietario prop = null;

        try {
            prop = p_propietario.buscarpropietario(nroprin);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return prop;
    }
    
    public String nombre_propietario(Integer nroprin) throws Exception {

        try {
            return p_propietario.nombre_propietario(nroprin);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }
    
    public String cirut_propietario(Integer nroprin) throws Exception {

        try {
            return p_propietario.cirut_propietario(nroprin);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public List<d_propietario> listarsobregirospropietarios() throws Exception {
        List<d_propietario> lista = new ArrayList<d_propietario>();

        try {
            lista = p_propietario.listarsobregirospropietarios();
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return lista;
    }

    public Float saldototalprop(Integer id) throws Exception {
        d_movimiento mov = new d_movimiento();
        Float saldogeneral = 0f;

        saldogeneral = mov.obtenersaldoprophastafecha(id, fechainicio(), 0f);
        

        return saldogeneral;
    }

    Date fechahoy() {
        java.util.Date fecha = new Date();
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(fecha);

        calendar.add(Calendar.DAY_OF_YEAR, 5);

        return calendar.getTime();
    }

    Date fechainicio() {
        java.util.Date fecha = new Date();
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(fecha);

        calendar.add(Calendar.YEAR, +100);

        return calendar.getTime();
    }

    public void actualizarsaldoprop(Integer id, Float saldo) throws Exception {
        p_control con = p_control.getInstancia();
        p_propietario.actualizarsaldoprop(id, saldo);
        con.escribirfichero("PROP - se actualiza saldo a cuenta id prop: " + id + " -- " + "total: " + saldo);
    }
}
