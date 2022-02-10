/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import persistencia.p_propietario_dgi;

/**
 *
 * @author gonza
 */
public class d_propietario_dgi {

    Integer prop_id;
    String ciudad;
    String departamento;
    String pais;

    public Integer getProp_id() {
        return prop_id;
    }

    public void setProp_id(Integer prop_id) {
        this.prop_id = prop_id;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void guardar(d_propietario_dgi pro) throws Exception {
        p_propietario_dgi.guardar(pro);
    }

    public d_propietario_dgi buscar(Integer prop_id) throws Exception {
        d_propietario_dgi pro = null;

        try {
            pro = p_propietario_dgi.buscar(prop_id);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return pro;
    }

    public void eliminar(Integer prop_id) throws Exception {
        p_propietario_dgi.eliminar(prop_id);
    }
    
}
