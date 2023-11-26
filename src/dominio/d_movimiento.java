/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import persistencia.p_movimientos;

/**
 *
 * @author Gonzalo
 */
public class d_movimiento {

    Integer id;
    Integer prop_id;
    Integer inq_casa;
    Integer mqp;
    Integer aqp;
    String detalle;
    Float entrada;
    Float salida;
    Float comision;
    Float iva;
    String tipo;
    Date fecha;
    Float irpf;
    String nombreinq;
    String irpftipo;
    Float saldo_a;
    Float saldo_b;
    String tipopago = "";

    public String getNombreinq() {
        return nombreinq;
    }

    public void setNombreinq(String nombreinq) {
        this.nombreinq = nombreinq;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Integer getMqp() {
        return mqp;
    }

    public void setMqp(Integer mqp) {
        this.mqp = mqp;
    }

    public Integer getAqp() {
        return aqp;
    }

    public void setAqp(Integer aqp) {
        this.aqp = aqp;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Float getEntrada() {
        //DecimalFormat formateador = new DecimalFormat("####.##");
        //formateador.format(entrada);
        return entrada;
    }

    public void setEntrada(Float entrada) {
        this.entrada = entrada;
    }

    public Float getSalida() {
        return salida;
    }

    public void setSalida(Float salida) {
        this.salida = salida;
    }

    public Float getComision() {
        return comision;
    }

    public void setComision(Float comision) {
        this.comision = comision;
    }

    public Float getIva() {
        return iva;
    }

    public void setIva(Float iva) {
        this.iva = iva;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Float getIrpf() {
        return irpf;
    }

    public void setIrpf(Float irpf) {
        this.irpf = irpf;
    }

    public String getIrpftipo() {
        return irpftipo;
    }

    public void setIrpftipo(String irpftipo) {
        this.irpftipo = irpftipo;
    }

    public Float getSaldo_a() {
        return saldo_a;
    }

    public void setSaldo_a(Float saldo_a) {
        this.saldo_a = saldo_a;
    }

    public Float getSaldo_b() {
        return saldo_b;
    }

    public void setSaldo_b(Float saldo_b) {
        this.saldo_b = saldo_b;
    }

    public String getTipopago() {
        return tipopago;
    }

    public void setTipopago(String tipopago) {
        this.tipopago = tipopago;
    }

    public Boolean existesalidaanda(d_movimiento mov) throws Exception {
        return p_movimientos.existesalidaanda(mov);
    }

    public Boolean tiene_recibo(Integer id) throws Exception {
        return p_movimientos.tiene_recibo(id);
    }

    public Integer ultimoid() throws Exception {
        Integer idobtenido = -1;
        idobtenido = p_movimientos.ultimoid();
        return idobtenido;
    }
    //cantidad_movimientos_alquiler_para_una_fecha
    
    public int cantidad_movimientos_alquiler_para_una_fecha(d_movimiento m) throws Exception {
        return p_movimientos.cantidad_movimientos_alquiler_para_una_fecha(m);
    }
    
    public Integer minimoid() throws Exception {
        Integer idobtenido = -1;
        idobtenido = p_movimientos.minimoid();
        return idobtenido;
    }

    public Integer ultimoidprop(Integer prop_id) throws Exception {
        Integer idobtenido = -1;
        idobtenido = p_movimientos.ultimoidprop(prop_id);
        return idobtenido;
    }

    public Integer cantmovimientosdeprop(Integer prop_id) throws Exception {
        Integer cant = -1;
        cant = p_movimientos.cantmovimientosdeprop(prop_id);
        return cant;
    }

    public Integer cantmovimientosalquiler(Integer prop_id, Integer inq_casa) throws Exception {
        Integer cant = -1;
        cant = p_movimientos.cantmovimientosalquiler(prop_id, inq_casa);
        return cant;
    }

    public Integer cantmovimientos(Integer prop_id, Integer inq_casa) throws Exception {
        Integer cant;
        cant = p_movimientos.cantmovimientos(prop_id, inq_casa);
        return cant;
    }

    public List<d_movimiento> listarmovimientos() throws Exception {
        List<d_movimiento> lista = new ArrayList<d_movimiento>();

        try {
            lista = p_movimientos.listarmovimientos();
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return lista;
    }

    public List<d_movimiento> mesesatraso_listado(Integer prop_id, Integer inq_casa) throws Exception {
        List<d_movimiento> lista = new ArrayList<>();

        try {
            lista = p_movimientos.mesesatraso_listado(prop_id, inq_casa);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return lista;
    }

    public List<d_movimiento> listarmovimientosdeinq(Integer prop_id, Integer inq_casa) throws Exception {
        List<d_movimiento> lista = new ArrayList<d_movimiento>();

        try {
            lista = p_movimientos.listarmovimientosdeinq(prop_id, inq_casa);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return lista;
    }

    public List<d_movimiento> listarmovimientosentrefechas(Date fecha1, Date fecha2) throws Exception {
        List<d_movimiento> lista = new ArrayList<d_movimiento>();

        try {
            lista = p_movimientos.listarmovimientosentrefechas(fecha1, fecha2);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return lista;
    }

    public List<d_movimiento> listarmovimientosentrefechastipopago(Date fecha1, Date fecha2, String tipo) throws Exception {
        List<d_movimiento> lista = new ArrayList<d_movimiento>();

        try {
            lista = p_movimientos.listarmovimientosentrefechastipopago(fecha1, fecha2, tipo);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return lista;
    }

    public List<d_movimiento> listarmovimientosentrefechassincfe(Date fecha1, Date fecha2) throws Exception {
        List<d_movimiento> lista = new ArrayList<d_movimiento>();

        try {
            lista = p_movimientos.listarmovimientosentrefechassincfe(fecha1, fecha2);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return lista;
    }

    public List<d_movimiento> listarmovimientosentrefechaseinq(Integer prop_id, Integer inq_id, Date fecha1, Date fecha2) throws Exception {
        List<d_movimiento> lista = new ArrayList<d_movimiento>();

        try {
            lista = p_movimientos.listarmovimientosentrefechaseinq(prop_id, inq_id, fecha1, fecha2);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return lista;
    }

    public Float totalmovimientosentrefechaseinq(Integer prop_id, Integer inq_id, Date fecha1, Date fecha2) throws Exception {
        Float total = 0f;

        try {
            total = p_movimientos.totalmovimientosentrefechaseinq(prop_id, inq_id, fecha1, fecha2);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return total;
    }

    public List<d_movimiento> listarmovimientosentrefechasypro(Integer prop_id, Date fecha1, Date fecha2) throws Exception {
        List<d_movimiento> lista = new ArrayList<d_movimiento>();

        try {
            lista = p_movimientos.listarmovimientosentrefechasypro(prop_id, fecha1, fecha2);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return lista;
    }

    public d_movimiento buscarmovimiento(Integer id) throws Exception {
        d_movimiento mov = null;

        try {
            mov = p_movimientos.buscarmovimiento(id);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return mov;
    }

    public d_movimiento buscarconvenioreintegropago(d_movimiento mov1) throws Exception {
        d_movimiento mov = null;

        try {
            mov = p_movimientos.buscarconvenioreintegropago(mov1);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return mov;
    }

    public d_movimiento buscarcuotaporanioymes(d_movimiento mov1) throws Exception {
        d_movimiento mov = null;

        try {
            mov = p_movimientos.buscarcuotaporanioymes(mov1);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return mov;
    }

    public d_movimiento buscarcuotapaga(d_movimiento mov) throws Exception {
        d_movimiento mov1 = null;

        try {
            mov1 = p_movimientos.buscarcuotapaga(mov);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return mov1;
    }

    public d_movimiento buscarcuotaacuenta(d_movimiento mov) throws Exception {
        d_movimiento mov1 = null;

        try {
            mov1 = p_movimientos.buscarcuotaacuenta(mov);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return mov1;
    }

    public Integer buscarultimacuotapagaalq(d_movimiento mov) throws Exception {
        Integer idmov = -1;

        try {
            idmov = p_movimientos.buscarultimacuotapagaalq(mov);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return idmov;
    }

    public Integer buscarultimacuotapagasaldoalq(d_movimiento mov) throws Exception {
        Integer idmov = -1;

        try {
            idmov = p_movimientos.buscarultimacuotapagasaldoalq(mov);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return idmov;
    }

    public d_movimiento buscaralquilermesoalquiler(d_movimiento mov, String tabla) throws Exception {
        d_movimiento mov1 = null;

        try {
            mov1 = p_movimientos.buscaralquilermesoalquiler(mov, tabla);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return mov1;
    }

    public Integer mesesatraso(Integer prop_id, Integer inq_casa) throws Exception {
        Integer total = 0;

        try {
            total = p_movimientos.mesesatraso(prop_id, inq_casa);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return total;
    }

    public Float totalmesesatraso(Integer prop_id, Integer inq_casa) throws Exception {
        Float total = 0f;

        try {
            total = p_movimientos.totalmesesatraso(prop_id, inq_casa);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return total;
    }

    public Float totalentregassaldo(int prop_id, int inq_casa, int mqp, int aqp) throws Exception {
        Float saldo = 0f;

        saldo = p_movimientos.totalentregassaldo(prop_id, inq_casa, mqp, aqp);

        return saldo;
    }
    //totalentregassaldo_no_alquiler

    public Float totalentregassaldo_no_alquiler(int prop_id, int inq_casa, int mqp, int aqp, String detalle) throws Exception {
        Float saldo = 0f;

        saldo = p_movimientos.totalentregassaldo_no_alquiler(prop_id, inq_casa, mqp, aqp, detalle);

        return saldo;
    }

    public Float obtenersaldoprophastafecha(Integer prop_id, Date fecha, Float irpf) throws Exception {
        Float saldo = 0.0f;

        saldo = p_movimientos.obtenersaldoprophastafecha(prop_id, fecha, irpf);

        return saldo;
    }

    public Float obtenertotalirpfpormes(Integer prop_id, Integer mqp, Integer aqp) throws Exception {
        Float saldo = 0f;

        saldo = p_movimientos.obtenertotalirpfpormes(prop_id, mqp, aqp);

        return saldo;
    }

    public Float obtenersaldoprop(Integer prop_id) throws Exception {
        Float saldo = 0.0f;

        saldo = p_movimientos.obtenersaldoprop(prop_id);

        return saldo;
    }

    public Float totalentradatipopagohoy(String tipopago) throws Exception {
        Float total = 0f;

        total = p_movimientos.totalentradatipopagohoy(tipopago);

        return total;
    }

    public Float totalsalidatipopagohoy(String tipopago) throws Exception {
        Float total = 0f;

        total = p_movimientos.totalsalidatipopagohoy(tipopago);

        return total;
    }

    public void actualizarmovimiento(d_movimiento mov) throws Exception {
        p_movimientos.actualizarmovimiento(mov);
    }

    public void actualizardetalle(d_movimiento mov) throws Exception {
        p_movimientos.actualizardetalle(mov);
    }

    public void actualizartipomov(int id_mov, String detalle) throws Exception {
        p_movimientos.actualizartipomov(id_mov, detalle);
    }

    public void actualizardetallealquiler(d_movimiento mov) throws Exception {
        p_movimientos.actualizardetallealquiler(mov);
    }

    public void guardarmovimientopropretira(d_movimiento mov) throws Exception {
        p_movimientos.guardarmovimientopropretira(mov);
    }

    public void guardarmovimientopropingresa(d_movimiento mov) throws Exception {
        p_movimientos.guardarmovimientopropingresa(mov);
    }

    public void guardarmovimientogastoinmo(d_movimiento mov) throws Exception {
        p_movimientos.guardarmovimientogastoinmo(mov);
    }

    public void guardarmovimientogastocta(d_movimiento mov) throws Exception {
        p_movimientos.guardarmovimientogastocta(mov);
    }

    public int guardarmovimientoinqpaga(d_movimiento mov) throws Exception {
        return p_movimientos.guardarmovimientoinqpaga(mov);
    }

    public int guardarmovimientoe_ticket(d_movimiento mov) throws Exception {
        return p_movimientos.guardarmovimientoe_ticket(mov);
    }

    public void guardarprimermovimiento(Integer prop_id, Float saldo) throws Exception {
        p_movimientos.guardarprimermovimiento(prop_id, saldo);
    }

    public d_movimiento obtenertotalesentrefechas(Date fecha1, Date fecha2) throws Exception {
        d_movimiento mov = null;

        try {
            mov = p_movimientos.obtenertotalesentrefechas(fecha1, fecha2);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return mov;
    }

    public d_movimiento obtenertotalesirpfentrefechas(Date fecha1, Date fecha2) throws Exception {
        d_movimiento mov = null;

        try {
            mov = p_movimientos.obtenertotalesirpfentrefechas(fecha1, fecha2);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return mov;
    }

    public d_movimiento obtenertotalesgastosentrefechas(Date fecha1, Date fecha2) throws Exception {
        d_movimiento mov = null;

        try {
            mov = p_movimientos.obtenertotalesgastosentrefechas(fecha1, fecha2);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return mov;
    }

    public d_movimiento buscarsaldoalquiler(d_movimiento pmov) throws Exception {
        d_movimiento mov = null;

        try {
            mov = p_movimientos.buscarsaldoalquiler(pmov);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return mov;
    }

    public void eliminarmovimiento(Integer id) throws Exception {
        p_movimientos.eliminarmovimiento(id);
    }

    public void ejecutarconsulta(String consulta) throws Exception {
        p_movimientos.ejecutarconsulta(consulta);
    }
}
