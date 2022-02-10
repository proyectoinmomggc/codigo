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
import persistencia.p_parametros;
import persistencia.p_propietario;

/**
 *
 * @author Gonzalo
 */
public class d_parametro {
    Integer iva;
    Float procaumento;
    Float irpf;
    Date fecha;


    public Integer getIva() {
        return iva;
    }

    public void setIva(Integer iva) {
        this.iva = iva;
    }      

    public Float getProcaumento() {
        return procaumento;
    }

    public void setProcaumento(Float procaumento) {
        this.procaumento = procaumento;
    }

    public Float getIrpf() {
        return irpf;
    }

    public void setIrpf(Float irpf) {
        this.irpf = irpf;
    }
    
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    public void guardarparametro(d_parametro par) throws Exception {
        p_parametros.guardarparametro(par);
    }
    
    public void actualizarparametro(d_parametro par) throws Exception {
        p_parametros.actualizarparametro(par);
    }
    
    public d_parametro buscarparametro() throws Exception {
        d_parametro par = null;

        try {
            par = p_parametros.buscarparametro();
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return par;
    }
    
    public List<d_parametro> listarparametros() throws Exception {
        List<d_parametro> lista = new ArrayList<d_parametro>();
        
        try {
            lista = p_parametros.listarparametros();
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return lista;
    }
    
    public d_parametro buscarparametroporfecha(Date fecha) throws Exception {
        d_parametro par = null;

        try {
            par = p_parametros.buscarparametroporfecha(fecha);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return par;
    }
}
