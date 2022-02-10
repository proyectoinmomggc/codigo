/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.util.Date;
import persistencia.p_alquileres_info;

/**
 *
 * @author gonza
 */
public class d_alquileres_info {

    Integer prop_id;
    Integer inq_casa;
    Float importe;
    Date fecha;
    String detalle;

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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    
    public void guardar_alquiler_info(d_alquileres_info alq) throws Exception {
        p_alquileres_info.guardar_alquiler_info(alq);
    }

    public d_alquileres_info buscar_alquiler_info(Integer prop_id, Integer inq_casa) throws Exception {
        d_alquileres_info alq = null;

        try {
            alq = p_alquileres_info.buscar_alquiler_info(prop_id, inq_casa);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return alq;
    }

    public void eliminar_alquiler_info(Integer prop_id, Integer inq_casa) throws Exception {
        p_alquileres_info.eliminar_alquiler_info(prop_id, inq_casa);
    }

}
