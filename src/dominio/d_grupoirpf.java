/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.util.ArrayList;
import java.util.List;
import persistencia.p_grupoirpf;

/**
 *
 * @author Gonzalo
 */
public class d_grupoirpf {

    Integer prop_id;
    String nombre;
    String cigrupo;
    Float porcentaje;
    Float monto;
    String fecha;

    public Integer getProp_id() {
        return prop_id;
    }

    public void setProp_id(Integer prop_id) {
        this.prop_id = prop_id;
    }

    public String getCigrupo() {
        return cigrupo;
    }

    public void setCigrupo(String cigrupo) {
        this.cigrupo = cigrupo;
    }

    public Float getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(Float porcentaje) {
        this.porcentaje = porcentaje;
    }

    public Float getMonto() {
        return monto;
    }

    public void setMonto(Float monto) {
        this.monto = monto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public d_grupoirpf existeciparafecha(String cigrupo, String fecha,Integer prop_id) throws Exception {
        d_grupoirpf gru = new d_grupoirpf();

        try {
            gru = p_grupoirpf.existeciparafecha(cigrupo,fecha,prop_id);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return gru;
    }
    
    public d_grupoirpf grupoirpfdeprop(String cigrupo, Integer prop_id) throws Exception {
        d_grupoirpf gru = new d_grupoirpf();

        try {
            gru = p_grupoirpf.grupoirpfdeprop(cigrupo,prop_id);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return gru;
    }
    
    public List<d_grupoirpf> listarciporprop(Integer prop_id) throws Exception {
        List<d_grupoirpf> lista = new ArrayList<d_grupoirpf>();

        try {
            lista = p_grupoirpf.listarciporprop(prop_id);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return lista;
    }

    public List<d_grupoirpf> listarciporproppago(Integer prop_id) throws Exception {
        List<d_grupoirpf> lista = new ArrayList<d_grupoirpf>();

        try {
            lista = p_grupoirpf.listarciporproppago(prop_id);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return lista;
    }

    public List<d_grupoirpf> listarcidistintas() throws Exception {
        List<d_grupoirpf> lista = new ArrayList<d_grupoirpf>();

        try {
            lista = p_grupoirpf.listarcidistintas();
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return lista;
    }

    public void actualizarmontogrupoirpf(d_grupoirpf gru) throws Exception {
        p_grupoirpf.actualizarmontogrupoirpf(gru);
    }

    public void actualizargrupoirpf(d_grupoirpf gru) throws Exception {
        p_grupoirpf.actualizargrupoirpf(gru);
    }

    public void actualizarmontogrupoirpfporfecha(d_grupoirpf gru) throws Exception {
        p_grupoirpf.actualizarmontogrupoirpfporfecha(gru);
    }

    public Integer actualizarcigrupo(d_grupoirpf gru, String civieja) throws Exception {
        Integer filas = 0;
        filas = p_grupoirpf.actualizarcigrupo(gru, civieja);
        return filas;
    }

    public void guardarnuevogrupoirpf(d_grupoirpf gru) throws Exception {
        p_grupoirpf.guardarnuevogrupoirpf(gru);
    }

    public void actualizarnombresdeci(String nombre, String cigrupo) throws Exception {
        p_grupoirpf.actualizarnombresdeci(nombre, cigrupo);
    }

    public void eliminargrupoirpf(d_grupoirpf gru) throws Exception {
        p_grupoirpf.eliminargrupoirpf(gru);
    }

    public Float obtenermontoci(Integer prop_id, String cigrupo, String fecha) throws Exception {
        Float monto = 0f;

        monto = p_grupoirpf.obtenermontoci(prop_id, cigrupo, fecha);

        return monto;
    }
    
    public Float montocedulapormes(d_grupoirpf gru, Integer mes, Integer anio) throws Exception {
        Float monto = 0f;

        monto = p_grupoirpf.montocedulapormes(gru, mes, anio);

        return monto;
    }


    public Float montogrupoirpfmenosci(Integer prop_id, String cigrupo) throws Exception {
        Float monto = 0.0f;

        monto = p_grupoirpf.montogrupoirpfmenosci(prop_id, cigrupo);

        return monto;
    }

    public Float montogrupoirpfporprop(Integer prop_id) throws Exception {
        Float monto = 0.0f;

        monto = p_grupoirpf.montogrupoirpfporprop(prop_id);

        return monto;
    }

    public Integer obtenercantci(Integer prop_id) throws Exception {
        Integer cantidad = 0;

        cantidad = p_grupoirpf.obtenercantci(prop_id);

        return cantidad;
    }

    public Integer cantnomdistintos(String cigrupo) throws Exception {
        Integer cantidad = 0;

        cantidad = p_grupoirpf.cantnomdistintos(cigrupo);

        return cantidad;
    }

    public Integer cantciporprop(Integer prop_id) throws Exception {
        Integer cantidad = 0;

        cantidad = p_grupoirpf.cantciporprop(prop_id);

        return cantidad;
    }

    public String nombredeci(String cigrupo) throws Exception {
        String nombredeci = "";

        nombredeci = p_grupoirpf.nombredeci(cigrupo);

        return nombredeci;
    }

    public d_grupoirpf buscargrupoirpf(Integer prop_id, String cigrupo) throws Exception {
        d_grupoirpf gru = null;

        try {
            gru = p_grupoirpf.buscargrupoirpf(prop_id, cigrupo);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return gru;
    }

    public List<d_grupoirpf> buscargrupoirpfporfecha(Integer prop_id, String fecha) throws Exception {
        d_grupoirpf gru = null;
        List<d_grupoirpf> lista = new ArrayList<d_grupoirpf>();

        try {
            lista = p_grupoirpf.buscargrupoirpfporfecha(prop_id, fecha);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return lista;
    }

    public List<d_grupoirpf> listarmontospormes(String fecha) throws Exception {
        List<d_grupoirpf> lista = new ArrayList<d_grupoirpf>();

        try {
            lista = p_grupoirpf.listarmontospormes(fecha);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return lista;
    }
    
    public List<d_grupoirpf> listargrupospormes(String fecha) throws Exception {
        List<d_grupoirpf> lista = new ArrayList<d_grupoirpf>();

        try {
            lista = p_grupoirpf.listargrupospormes(fecha);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return lista;
    }
    
    public List<d_grupoirpf> listarproppormes(String fecha) throws Exception {
        List<d_grupoirpf> lista = new ArrayList<d_grupoirpf>();

        try {
            lista = p_grupoirpf.listarproppormes(fecha);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return lista;
    }

    public List<d_grupoirpf> listargruposirpf() throws Exception {
        List<d_grupoirpf> lista = new ArrayList<d_grupoirpf>();

        try {
            lista = p_grupoirpf.listargruposirpf();
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return lista;
    }
}
