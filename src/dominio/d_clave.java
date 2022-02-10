/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import persistencia.p_clave;

/**
 *
 * @author Gonzalo
 */
public class d_clave {
    String clave;

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
    
    public void actualizarclave(d_clave cla) throws Exception {
        p_clave.actualizarclave(cla);
    }
    
    public d_clave buscarclave() throws Exception {
        d_clave cla = null;

        try {
            cla = p_clave.buscarclave();
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return cla;
    }
}
