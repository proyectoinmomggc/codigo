/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import persistencia.p_parametros;
import persistencia.p_parametroscfe;

/**
 *
 * @author e274263
 */
public class d_parametroscfe {
    String nomusuario;
    String clave;
    String tenant;

    public String getNomusuario() {
        return nomusuario;
    }

    public void setNomusuario(String nomusuario) {
        this.nomusuario = nomusuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }
    
    public void guardarparametroscfe(d_parametroscfe par) throws Exception {
        p_parametroscfe.guardarparametroscfe(par);
    }
    
    public d_parametroscfe buscarparametroscfe() throws Exception {
        d_parametroscfe par = null;

        try {
            par = p_parametroscfe.buscarparametroscfe();
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return par;
    }
}
