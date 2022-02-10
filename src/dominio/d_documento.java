/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import persistencia.p_documento;

/**
 *
 * @author gonza
 */
public class d_documento {

    Timestamp fecha;
    String nombre;
    String direccion;
    String ci;
    String texto;
    String descripcion_monto;
    Float monto;
    String tipo_moneda;
    String tipo;

    public d_documento() {
    }


    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getDescripcion_monto() {
        return descripcion_monto;
    }

    public void setDescripcion_monto(String descripcion_monto) {
        this.descripcion_monto = descripcion_monto;
    }

    public Float getMonto() {
        return monto;
    }

    public void setMonto(Float monto) {
        this.monto = monto;
    }

    public String getTipo_moneda() {
        return tipo_moneda;
    }

    public void setTipo_moneda(String tipo_moneda) {
        this.tipo_moneda = tipo_moneda;
    }

    
    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }


    public d_documento buscar_documento(Timestamp fecha) throws Exception {
        d_documento doc = null;

        try {
            doc = p_documento.buscar_documento(fecha);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return doc;
    }

    public void guardar_documento(d_documento doc) throws Exception {
        p_documento.guardar_documento(doc);
    }
    
    public List<d_documento> listar_documentos() throws Exception {
        List<d_documento> lista = new ArrayList();

        try {
            lista = p_documento.listar_documentos();
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return lista;
    }

    public d_documento eliminar_documento(Timestamp fecha) throws Exception {
        d_documento doc = null;

        try {
            p_documento.eliminar_documento(fecha);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return doc;
    }
}
