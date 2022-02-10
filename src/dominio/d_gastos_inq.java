/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.util.ArrayList;
import java.util.List;
import persistencia.p_gastos_inq;

/**
 *
 * @author Gonzalo
 */
public class d_gastos_inq {

    Integer prop_id;
    Integer inq_casa;
    Float importe;
    String detalle;
    Integer aqp;
    Integer mqp;
    Integer estado;

    public Integer getProp_id() {
        return prop_id;
    }

    public void setProp_id(Integer prop_id) {
        this.prop_id = prop_id;
    }

    public Integer getInq_casa() {
        return inq_casa;
    }

    public void setInq_casa(Integer inq_casa) {
        this.inq_casa = inq_casa;
    }

    public Float getImporte() {
        return importe;
    }

    public void setImporte(Float importe) {
        this.importe = importe;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Integer getAqp() {
        return aqp;
    }

    public void setAqp(Integer aqp) {
        this.aqp = aqp;
    }

    public Integer getMqp() {
        return mqp;
    }

    public void setMqp(Integer mqp) {
        this.mqp = mqp;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public d_gastos_inq buscargastoporinqpendiente(d_gastos_inq gas) throws Exception {
        d_gastos_inq gas1 = new d_gastos_inq();

        try {
            gas1 = p_gastos_inq.buscargastoporinqpendientes(gas);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return gas1;
    }
    
    public d_gastos_inq buscargastoalquiler(d_gastos_inq gas) throws Exception {
        d_gastos_inq gas1 = new d_gastos_inq();

        try {
            gas1 = p_gastos_inq.buscargastoalquiler(gas);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return gas1;
    }

    public d_gastos_inq buscaralquilermesmasalto(d_gastos_inq gas) throws Exception {
        d_gastos_inq gas1 = new d_gastos_inq();

        try {
            gas1 = p_gastos_inq.buscaralquilermesmasalto(gas);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return gas1;
    }
    
    public d_gastos_inq buscargasto_entregaacuenta(d_gastos_inq gas) throws Exception {
        d_gastos_inq gas1 = new d_gastos_inq();

        try {
            gas1 = p_gastos_inq.buscargasto_entregaacuenta(gas);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return gas1;
    }

    public d_gastos_inq buscarconvenioreintegro(d_gastos_inq gas) throws Exception {
        d_gastos_inq gas1 = new d_gastos_inq();

        try {
            gas1 = p_gastos_inq.buscarconvenioreintegro(gas);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return gas1;
    }
    //buscarconvenioreintegrocompleto
    
    public d_gastos_inq buscarconvenioreintegrocompleto(d_gastos_inq gas) throws Exception {
        d_gastos_inq gas1 = new d_gastos_inq();

        try {
            gas1 = p_gastos_inq.buscarconvenioreintegrocompleto(gas);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return gas1;
    }
    
    public d_gastos_inq buscarconvenioreintegroabonado(d_gastos_inq gas) throws Exception {
        d_gastos_inq gas1 = new d_gastos_inq();

        try {
            gas1 = p_gastos_inq.buscarconvenioreintegroabonado(gas);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return gas1;
    }

    public d_gastos_inq buscargastoporinqpagado(d_gastos_inq gas) throws Exception {
        d_gastos_inq gas1 = new d_gastos_inq();

        try {
            gas1 = p_gastos_inq.buscargastoporinqpagado(gas);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return gas1;
    }

    public Integer cantsaldospendientes(Integer prop_id, Integer inq_casa) throws Exception {
        Integer cant = 0;

        try {
            cant = p_gastos_inq.cantsaldospendientes(prop_id, inq_casa);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return cant;
    }

    public d_gastos_inq buscargastoporinqnopendiente(d_gastos_inq gas) throws Exception {
        d_gastos_inq gas1 = new d_gastos_inq();

        try {
            gas1 = p_gastos_inq.buscargastoporinqnopendientes(gas);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return gas1;
    }

    public d_gastos_inq buscaralquilermes(d_gastos_inq gas) throws Exception {
        d_gastos_inq gas1 = new d_gastos_inq();

        try {
            gas1 = p_gastos_inq.buscaralquilermes(gas);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return gas1;
    }
    
    public d_gastos_inq buscaralquilerdias(d_gastos_inq gas) throws Exception {
        d_gastos_inq gas1 = new d_gastos_inq();

        try {
            gas1 = p_gastos_inq.buscaralquilerdias(gas);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return gas1;
    }

    public List<d_gastos_inq> listargastosporinq(Integer prop_id, Integer inq_casa) throws Exception {
        List<d_gastos_inq> lista = new ArrayList<d_gastos_inq>();

        try {
            lista = p_gastos_inq.listargastosporinqpendientes(prop_id, inq_casa);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return lista;
    }

    public List<d_gastos_inq> listarconveniosreintegros() throws Exception {
        List<d_gastos_inq> lista = new ArrayList<d_gastos_inq>();

        try {
            lista = p_gastos_inq.listarconveniosreintegros();
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return lista;
    }

    public List<d_gastos_inq> listarconveniosreintegrosporinq(int prop_id, int inq_casa) throws Exception {
        List<d_gastos_inq> lista = new ArrayList<d_gastos_inq>();

        try {
            lista = p_gastos_inq.listarconveniosreintegrosporinq(prop_id, inq_casa);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return lista;
    }

    public List<d_gastos_inq> cargarlistaconveniosreintegrossinaplicar(d_gastos_inq gas) throws Exception {
        List<d_gastos_inq> lista = new ArrayList<d_gastos_inq>();

        try {
            lista = p_gastos_inq.cargarlistaconveniosreintegrossinaplicar(gas);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return lista;
    }

    public void guardargastoinq(d_gastos_inq gas) throws Exception {
        p_gastos_inq.guardargastoinq(gas);
    }

    public void actualizarimporte(d_gastos_inq gas) throws Exception {
        p_gastos_inq.actualizarimporte(gas);
    }
    
    public void actualizarimporte_y_detalle_luego_de_entrega_alquiler(d_gastos_inq gas) throws Exception {
        p_gastos_inq.actualizarimporte_y_detalle_luego_de_entrega_alquiler(gas);
    }
    
    public void actualizarimporte_luegodeentrega(d_gastos_inq gas) throws Exception {
        p_gastos_inq.actualizarimporte_luegodeentrega(gas);
    }
    
    public void actualizarestadosaldo(d_gastos_inq gas) throws Exception {
        p_gastos_inq.actualizarestadosaldo(gas);
    }

    public void cambiarestadoconvenioreintegro(d_gastos_inq gas) throws Exception {
        p_gastos_inq.cambiarestadoconvenioreintegro(gas);
    }

    public void actualizarsaldoalquiler(d_gastos_inq gas) throws Exception {
        p_gastos_inq.actualizarsaldoalquiler(gas);
    }

    public void eliminargastos(Integer prop_id, Integer inq_casa) throws Exception {
        p_gastos_inq.eliminargastos(prop_id, inq_casa);
    }

    public void eliminarconvenioreintegro(d_gastos_inq gas) throws Exception {
        p_gastos_inq.eliminarconvenioreintegro(gas);
    }
    
    public void eliminargastossinimporte() throws Exception {
        p_gastos_inq.eliminargastossinimporte();
    }
    
    public Boolean actualizarestadoauno(d_gastos_inq gas) throws Exception {
        return p_gastos_inq.actualizarestadoauno(gas);
    }
}
