/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import persistencia.p_inquilinos;

/**
 *
 * @author Gonzalo
 */
public class d_inquilino {

    Integer prop_id;
    Integer inq_casa;
    String inq_nombre;
    String inq_direccion;
    Integer inq_tel;
    String inq_tipoalq;
    Date inq_fechaic;
    Date arlmrl;
    String inq_garantia;
    String inq_padcasa;
    Integer nms;
    Integer nmi;
    Float inq_impalq;
    Float inq_saldo;
    Float inq_por;
    Integer inq_plazo;
    String inq_observaciones;
    String inq_irpf;
    Date inq_fchcontratoaux;
    Float inq_impalq_aux;

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

    public String getInq_nombre() {
        return inq_nombre;
    }

    public void setInq_nombre(String inq_nombre) {
        this.inq_nombre = inq_nombre;
    }

    public String getInq_direccion() {
        return inq_direccion;
    }

    public void setInq_direccion(String inq_direccion) {
        this.inq_direccion = inq_direccion;
    }

    public Integer getInq_tel() {
        return inq_tel;
    }

    public void setInq_tel(Integer inq_tel) {
        this.inq_tel = inq_tel;
    }

    public String getInq_tipoalq() {
        return inq_tipoalq;
    }

    public void setInq_tipoalq(String inq_tipoalq) {
        this.inq_tipoalq = inq_tipoalq;
    }

    public Date getInq_fechaic() {
        return inq_fechaic;
    }

    public void setInq_fechaic(Date inq_fechaic) {
        this.inq_fechaic = inq_fechaic;
    }

    public String getInq_garantia() {
        return inq_garantia;
    }

    public void setInq_garantia(String inq_garantia) {
        this.inq_garantia = inq_garantia;
    }

    public String getInq_padcasa() {
        return inq_padcasa;
    }

    public void setInq_padcasa(String inq_padcasa) {
        this.inq_padcasa = inq_padcasa;
    }

    public Integer getNms() {
        return nms;
    }

    public void setNms(Integer nms) {
        this.nms = nms;
    }

    public Integer getNmi() {
        return nmi;
    }

    public void setNmi(Integer nmi) {
        this.nmi = nmi;
    }

    public Float getInq_impalq() {
        return inq_impalq;
    }

    public void setInq_impalq(Float inq_impalq) {
        this.inq_impalq = inq_impalq;
    }

    public Float getInq_saldo() {
        return inq_saldo;
    }

    public void setInq_saldo(Float inq_saldo) {
        this.inq_saldo = inq_saldo;
    }

    public Float getInq_por() {
        return inq_por;
    }

    public void setInq_por(Float inq_por) {
        this.inq_por = inq_por;
    }

    public Integer getInq_plazo() {
        return inq_plazo;
    }

    public void setInq_plazo(Integer inq_plazo) {
        this.inq_plazo = inq_plazo;
    }

    public String getInq_observaciones() {
        return inq_observaciones;
    }

    public void setInq_observaciones(String inq_observaciones) {
        this.inq_observaciones = inq_observaciones;
    }

    public Date getArlmrl() {
        return arlmrl;
    }

    public void setArlmrl(Date arlmrl) {
        this.arlmrl = arlmrl;
    }

    public String getInq_irpf() {
        return inq_irpf;
    }

    public void setInq_irpf(String inq_irpf) {
        this.inq_irpf = inq_irpf;
    }

    public Date getInq_fchcontratoaux() {
        return inq_fchcontratoaux;
    }

    public void setInq_fchcontratoaux(Date inq_fchcontratoaux) {
        this.inq_fchcontratoaux = inq_fchcontratoaux;
    }

    public Float getInq_impalq_aux() {
        return inq_impalq_aux;
    }

    public void setInq_impalq_aux(Float inq_impalq_aux) {
        this.inq_impalq_aux = inq_impalq_aux;
    }
    
    
    
    public int obtenerUltimoDiaMes(int anio, int mes) {

        Calendar calendario = Calendar.getInstance();
        calendario.set(anio, mes - 1, 1);
        return calendario.getActualMaximum(Calendar.DAY_OF_MONTH);

    }

    public void eliminarinquilino(Integer prop_id,Integer inq_casa) throws Exception {
        p_inquilinos.eliminarinquilino(prop_id,inq_casa);
    }
    
    public void reiniciarimpalquilerparticular(Integer prop_id,Integer inq_casa) throws Exception {
        p_inquilinos.reiniciarimpalquilerparticular(prop_id,inq_casa);
    }
    
    public void reiniciarimpalquilergeneral() throws Exception {
        p_inquilinos.reiniciarimpalquilergeneral();
    }
    
    public Date sumar1mes(Date fecha){
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(fecha);
        calendario.add(Calendar.MONTH, 1);
        return calendario.getTime();
    }
    
    public Date sumar4meses(Date fecha){
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(fecha);
        calendario.add(Calendar.MONTH, 4);
        return calendario.getTime();
    }
    
    public Date restar4meses(Date fecha){
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(fecha);
        calendario.add(Calendar.MONTH, -4);
        return calendario.getTime();
    }
    
    public Date restar1mes(Date fecha){
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(fecha);
        calendario.add(Calendar.MONTH, -1);
        return calendario.getTime();
    }
    
    public Date sumar1anio(Date fecha){
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(fecha);
        
        calendario.add(Calendar.YEAR, 1);
        return calendario.getTime();
    }
    
    public List<d_inquilino> listarinquilinos() throws Exception {
        List<d_inquilino> lista = new ArrayList<d_inquilino>();

        try {
            lista = p_inquilinos.listarinquilinos();
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return lista;
    }
    
    public List<d_inquilino> listarinquilinosparactualizarsaldos() throws Exception {
        List<d_inquilino> lista = new ArrayList<d_inquilino>();

        try {
            lista = p_inquilinos.listarinquilinosparactualizarsaldos();
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return lista;
    }
    
    public List<d_inquilino> listarinquilinoscompleto() throws Exception {
        List<d_inquilino> lista = new ArrayList<d_inquilino>();

        try {
            lista = p_inquilinos.listarinquilinoscompleto();
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return lista;
    }

    public d_inquilino buscarinquilino(Integer nroprin, Integer casainq) throws Exception {
        d_inquilino inq = null;

        try {
            inq = p_inquilinos.buscarinquilino(nroprin, casainq);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return inq;
    }
    
    public Date buscarfechareajusteoriginal(Integer nroprin, Integer casainq) throws Exception {
        try {
            return p_inquilinos.buscarfechareajusteoriginal(nroprin, casainq);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }
    
    public Date devuelve_fecha_ic(Integer nroprin, Integer casainq) throws Exception {
        try {
            return p_inquilinos.devuelve_fecha_ic(nroprin, casainq);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }
    
    public String buscarnombreinquilino(Integer nroprin, Integer casainq) throws Exception {

        try {
            return p_inquilinos.buscarnombreinquilino(nroprin, casainq);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }
    
    public d_inquilino buscarinquilinopornombre(String nombre) throws Exception {
        d_inquilino inq = null;

        try {
            inq = p_inquilinos.buscarinquilinopornombre(nombre);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return inq;
    }

    public List<d_gastos_inq> listarinquilinosmorosos() throws Exception {
        List<d_gastos_inq> lista = new ArrayList<d_gastos_inq>();

        try {
            lista = p_inquilinos.listarinquilinosmorosos();
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return lista;
    }

    public List<d_inquilino> listarinquilinospornombre() throws Exception {
        List<d_inquilino> lista = new ArrayList<d_inquilino>();

        try {
            lista = p_inquilinos.listarinquilinospornombre();
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return lista;
    }

    public List<d_inquilino> listarinquilinospordireccion() throws Exception {
        List<d_inquilino> lista = new ArrayList<d_inquilino>();

        try {
            lista = p_inquilinos.listarinquilinospordireccion();
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return lista;
    }

    public Integer obtenerid(Integer nroprin) throws Exception {
        Integer idobtenido = -1;
        idobtenido = p_inquilinos.generarid(nroprin);

        return idobtenido;
    }

    public void guardarinquilino(d_inquilino inq) throws Exception {
        if (inq.getInq_fchcontratoaux()==null){
            inq.setInq_fchcontratoaux(inq.getInq_fechaic());
        }
        p_inquilinos.guardarinquilino(inq);
    }

    public void actualizarimporteaux(Integer prop_id, Integer inq_casa, Float importe) throws Exception {
        p_inquilinos.actualizarimporteaux(prop_id, inq_casa, importe);
    }
    
    public void actualizarsaldo(Integer prop_id, Integer inq_casa, Float importe) throws Exception {
        p_inquilinos.actualizarsaldo(prop_id, inq_casa, importe);
    }
    
    public void actualizardatosalquiler(d_inquilino inq) throws Exception {
        p_inquilinos.actualizardatosalquiler(inq);
    }

    public void actualizarplazo(Integer prop_id, Integer inq_casa, Integer plazo) throws Exception {
        p_inquilinos.actualizarplazo(prop_id, inq_casa, plazo);
    }
    
    public void actualizarreajuste(Integer prop_id, Integer inq_casa, Date reajuste) throws Exception {
        p_inquilinos.actualizarreajuste(prop_id, inq_casa, reajuste);
    }
    
    public void actualizarfechareajuste(Integer prop_id, Integer inq_casa, Date fecha) throws Exception {
        p_inquilinos.actualizarfechareajuste(prop_id, inq_casa, fecha);
    }
}
