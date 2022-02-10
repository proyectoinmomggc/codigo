/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import persistencia.p_configuracion;

/**
 *
 * @author gonza
 */
public class d_configuracion {
    String clave;
    String cfe;
    String imprimir;

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getCfe() {
        return cfe;
    }

    public void setCfe(String cfe) {
        this.cfe = cfe;
    }

    public String getImprimir() {
        return imprimir;
    }

    public void setImprimir(String imprimir) {
        this.imprimir = imprimir;
    }
    
    public void actualizarconfiguracion(d_configuracion conf) throws Exception {
        p_configuracion.actualizarconfiguracion(conf);
    }
    
    public d_configuracion buscarconfiguracion() throws Exception {
        d_configuracion conf = null;

        try {
            conf = p_configuracion.buscarconfiguracion();
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return conf;
    }
}
