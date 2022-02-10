/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.util.ArrayList;
import java.util.List;
import persistencia.p_cfe;

/**
 *
 * @author e274263
 */
public class d_cfe {
    Integer idmov;
    String serie;
    Integer numero;
    String rucemisor;
    Integer tipo;
    Integer observado;

    public Integer getIdmov() {
        return idmov;
    }

    public void setIdmov(Integer idmov) {
        this.idmov = idmov;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getRucemisor() {
        return rucemisor;
    }

    public void setRucemisor(String rucemisor) {
        this.rucemisor = rucemisor;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public Integer getObservado() {
        return observado;
    }

    public void setObservado(Integer observado) {
        this.observado = observado;
    }
    
    public List<d_cfe> listarcfe() throws Exception {
        List<d_cfe> lista = new ArrayList<d_cfe>();

        try {
            lista = p_cfe.listarcfe();
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return lista;
    }
    
    public List<d_cfe> listarcfe_todos() throws Exception {
        List<d_cfe> lista = new ArrayList<>();

        try {
            lista = p_cfe.listarcfe_todos();
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return lista;
    }
    
    public List<d_cfe> listarnotas() throws Exception {
        List<d_cfe> lista = new ArrayList<d_cfe>();

        try {
            lista = p_cfe.listarnotas();
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return lista;
    }
    
    public void guardarcfe(d_cfe par) throws Exception {
        p_cfe.guardarcfe(par);
    }
    
    public void actualizar_id(int id_viejo, int id_nuevo) throws Exception {
        p_cfe.actualizar_id(id_viejo, id_nuevo);
    }
    
    public d_cfe buscarcfe(Integer idmov) throws Exception {
        d_cfe par = null;

        try {
            par = p_cfe.buscarcfe(idmov);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return par;
    }
    
    public Integer ultimoid() throws Exception {
        Integer idobtenido = -1;
        idobtenido = p_cfe.ultimoid();
        return idobtenido;
    }
    
    public Integer minimoid() throws Exception {
        Integer idobtenido = -1;
        idobtenido = p_cfe.minid();
        return idobtenido;
    }
}
