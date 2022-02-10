/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.util.Date;
import java.util.List;
import persistencia.p_recibos;
import persistencia.p_recibos_salida_prop;

/**
 *
 * @author Gonzalo
 */
public class d_recibo {

    Integer idmovimiento;
    String detalle;
    Date fecha;
    String nombre_inq;
    Float importe;

    public Integer getIdmovimiento() {
        return idmovimiento;
    }

    public void setIdmovimiento(Integer idmovimiento) {
        this.idmovimiento = idmovimiento;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNombre_inq() {
        return nombre_inq;
    }

    public void setNombre_inq(String nombre_inq) {
        this.nombre_inq = nombre_inq;
    }

    public Float getImporte() {
        return importe;
    }

    public void setImporte(Float importe) {
        this.importe = importe;
    }

    public void guardarrecibo(d_recibo rec,String tipo) throws Exception {
        if(tipo.equals("alquiler")){
            p_recibos.guardarrecibo(rec);
        }
        if(tipo.equals("salida")){
            p_recibos_salida_prop.guardarrecibo(rec);
        }        
    }
    
    public void guardarlistadodeuda(List<d_recibo> lista,String tipo) throws Exception {
        if(tipo.equals("alquiler")){
            p_recibos.guardarlistadodeuda(lista);
        }
        if(tipo.equals("salida")){
            p_recibos_salida_prop.guardarlistadodeuda(lista);
        }
    }

    public void eliminarrecibo(String tipo) throws Exception {
        if(tipo.equals("alquiler")){
            p_recibos.eliminarrecibos();
        }
        if(tipo.equals("salida")){
            p_recibos_salida_prop.eliminarrecibos();
        }        
    }
    
}
