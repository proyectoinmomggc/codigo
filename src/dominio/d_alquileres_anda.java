/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.util.ArrayList;
import java.util.List;
import persistencia.p_alquileres_anda;

/**
 *
 * @author gonza
 */
public class d_alquileres_anda {
    Integer prop_id;
    Integer inq_casa;
    String id_anda;

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

    public String getId_anda() {
        return id_anda;
    }

    public void setId_anda(String id_anda) {
        this.id_anda = id_anda;
    }
    
    public void guardar_alquiler_anda(d_alquileres_anda alq) throws Exception {
        p_alquileres_anda.guardar_alquiler_anda(alq);
    }

    public d_alquileres_anda buscar_alquiler_anda(Integer prop_id, Integer inq_casa) throws Exception {
        d_alquileres_anda alq = null;

        try {
            alq = p_alquileres_anda.buscar_alquiler_anda(prop_id, inq_casa);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return alq;
    }
    
    public d_alquileres_anda buscar_alquiler_anda_nombre(String nombre) throws Exception {
        d_alquileres_anda alq = null;

        try {
            alq = p_alquileres_anda.buscar_alquiler_anda_nombre(nombre);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return alq;
    }

    public List<d_alquileres_anda> listar_alquileres_anda() throws Exception {
        List<d_alquileres_anda> lista = new ArrayList<>();

        try {
            lista = p_alquileres_anda.listar_alquileres_anda();
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return lista;
    }
    
    public void eliminar_alquiler_anda(Integer prop_id, Integer inq_casa) throws Exception {
        p_alquileres_anda.eliminar_alquiler_anda(prop_id, inq_casa);
    }
}
