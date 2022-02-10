/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.sql.Timestamp;
import persistencia.p_caja;

/**
 *
 * @author gonza
 */
public class d_caja {

    Timestamp fecha;
    Float monto;

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public Float getMonto() {
        return monto;
    }

    public void setMonto(Float monto) {
        this.monto = monto;
    }

    public d_caja buscarcaja() throws Exception {
        d_caja caja = null;

        try {
            caja = p_caja.buscarcaja();
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return caja;
    }

    public void actualizarcaja(d_caja caja) throws Exception {
        try {
            p_caja.actualizarcaja(caja);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }
}
