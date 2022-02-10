/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion.paneles;

import dominio.d_cfe;
import dominio.d_gastos_inq;
import dominio.d_inquilino;
import dominio.d_movimiento;
import dominio.d_parametro;
import dominio.d_parametroscfe;
import dominio.d_propietario;
import interfaces.observador_documentos;
import interfaces.observador_inq;
import interfaces.observador_inq_paga;
import interfaces.observador_prop;
import java.util.ArrayList;
import java.util.List;
import interfaces.observador_mov;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import org.datacontract.schemas._2004._07.sicfecontract.RespuestaObtenerCAE;
import org.datacontract.schemas._2004._07.sicfecontract.SICFERespuestaObtenerCFE;

/**
 *
 * @author Gonzalo
 */
public class p_control {

    private static p_control instancia = null;
    private List<observador_mov> observadores_mov = new ArrayList<observador_mov>();
    private List<observador_prop> observadores_prop = new ArrayList<observador_prop>();
    private List<observador_inq> observadores_inq = new ArrayList<observador_inq>();
    private List<observador_documentos> observadores_doc = new ArrayList<observador_documentos>();
    private List<observador_inq_paga> observadores_inq_paga = new ArrayList<observador_inq_paga>();
    public d_propietario prop = null;
    public d_inquilino inq = null;
    public d_movimiento mov = null;
    public int plazoextendido = 0;
    public Boolean consultarclave = false;
    public Boolean imprimircfe = true;
    public int id_documento;

    public void alinear_contenido_columna_izquierda(JTable tabla, int columna) {
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
        tabla.getColumnModel().getColumn(columna).setCellRenderer(rightRenderer);
    }

    public void guardarprimermovimiento(d_propietario prop1) throws Exception {
        d_movimiento mov1 = new d_movimiento();

        Integer cant = -1;
        cant = mov1.cantmovimientosdeprop(prop1.getProp_id());
        if (cant == 0 || cant == -1) {
            //ingresar primer movimiento saldo actual
            if (prop1.getProp_saldo() != 0) {
                mov1.guardarprimermovimiento(prop1.getProp_id(), prop1.getProp_saldo());
            }
        }
    }

    public String tiene_doc_dgi(Integer idmov) throws Exception {
        d_parametroscfe pcfe = new d_parametroscfe();
        pcfe = pcfe.buscarparametroscfe();
        if (pcfe == null) {
            throw new Exception("debe ingresar parametros cfe: nombre, clave y tenant");
        }
        SICFERespuestaObtenerCFE obtenerVersion = new SICFERespuestaObtenerCFE();
        obtenerVersion = obtenerCFEPorReferencia(pcfe.getNomusuario(), pcfe.getClave(), pcfe.getTenant(), String.valueOf(idmov), false, false);
        if (obtenerVersion.getCodigo() != 0) {
            return "";
        }
        //111_A _119
        //111_A _119
        if (obtenerVersion.getCodigo() == 0) {
            String resultado;
            resultado = obtenerVersion.getTipo().toString() + "_"
                    + obtenerVersion.getSerie().getValue() + "_"
                    + obtenerVersion.getNumero().toString();

            //resultado.replace(" ", "");
            return resultado;
        }
        return "";
    }

    public int minimo_entre_cfe_y_mov() throws Exception {
        //BUSCA MINIMO ID CFE Y MOVIMIENTOS, Y DEVUELVE EL MAS CHICO
        d_cfe cfe = new d_cfe();
        d_movimiento m = new d_movimiento();
        int ult_id_cfe = cfe.minimoid();
        int ult_id_mov = m.minimoid();

        return Math.min(ult_id_cfe, ult_id_mov);
    }

    public int maximo_mov() throws Exception {
        //BUSCA MINIMO ID CFE Y MOVIMIENTOS, Y DEVUELVE EL MAS CHICO

        d_movimiento m = new d_movimiento();

        int id_mov = m.ultimoid();

        return (id_mov);
    }

    public void actualizarlistadomovimientos() {
        p_listadosmov fic = new p_listadosmov(null, true);

        fic.setVisible(true);
    }

    public String mostrarnumero(Float numero) throws Exception {
        DecimalFormat formatter = new DecimalFormat(
                "#,##0.00",
                new DecimalFormatSymbols(new Locale("pt", "BR")));
        String resultado;

        resultado = formatter.format(numero);

        return resultado;
    }

    public Float guardarnumero(String numero) throws Exception {
        numero = limpiar(numero);
        Float resultado = 0f;

        resultado = Float.parseFloat(numero);

        return resultado;
    }

    public String limpiar(String numero) {
        Float res;
        numero = numero.replace(".", "").replace(",", ".");
        //DecimalFormat formatter = new DecimalFormat("#.##");
        //numero = numero.replace(".", "").replace(",", ".");
        //numero = formatter.format(numero);
        res = Float.parseFloat(numero);
        return String.valueOf(res);
    }

    public String devuelveestadodgi() throws Exception {
        //DEVUELVE TEST O PROD PARA SABER A DONDE APUNTA DGI        
        org.tempuri.ImpSICFEEmisor service = new org.tempuri.ImpSICFEEmisor();
        org.tempuri.ISICFEEmisor port = service.getBasicHttpBindingISICFEEmisor();
        String prod = "PROD";
        String test = "TEST";
        String name = port.toString();

        boolean esprod = name.contains(prod);
        boolean estest = name.contains(test);

        if (esprod) {
            return prod;
        }
        if (estest) {
            return test;
        }
        throw new Exception("NO SE PUDO ENCONTRAR RUTA DE DGI");
    }

    public void actualizar_deuda_inq(d_inquilino inq) throws Exception {
        Float importetotal = 0f;
        importetotal = 0f;

        Boolean sesumaimporte = false;

        List<d_gastos_inq> listagastos = obtenerlistadogastospendientesinq(inq);
        //List<d_movimiento> listamovimientos = listamovimientos(inq);

        //lbldeuda.setText("");
        int mesactual = devuelvemes(new Date());
        int anioactual = devuelveanio(new Date());

        if (listagastos != null) {
            for (d_gastos_inq aux : listagastos) {
                if (aux.getEstado().equals(0)) {
                    String fechaxstr = "01/" + aux.getMqp() + "/" + aux.getAqp();
                    sesumaimporte = sesumaimporte(fechaxstr);
                    if (sesumaimporte) {
                        if (aux.getMqp() != mesactual || aux.getAqp() != anioactual) {
                            importetotal = importetotal + (aux.getImporte());
                        }
                    } else {
                        if (aux.getEstado() == 0) {
                            if (aux.getMqp() != mesactual || aux.getAqp() != anioactual) {
                                importetotal = importetotal + (aux.getImporte());
                            }
                        }
                    }
                } else {
                    if (aux.getEstado() == 0) {
                        importetotal = importetotal + (aux.getImporte());
                    }
                }
            }
        }

        inq.actualizarsaldo(inq.getProp_id(), inq.getInq_casa(), importetotal);
    }

    Boolean sesumaimporte(String fecha) throws Exception {
        Boolean sesumaimporte = false;
        Date fechalistado = null;
        Date fechaactual = null;

        //BUSCAR SI EN ESA FECHA NO TIENE UNA ENTREGA PAGA, NO SUMAR EL TOTAL DEL MES A LA DEUDA TOTAL
        fechalistado = parsefechadate(fecha);
        fechaactual = fechaactual();

        int mesfechalistado = devuelvemes(fechalistado);
        int aniofechalistado = devuelveanio(fechalistado);

        int mesfechaactual = devuelvemes(fechaactual);
        int aniofechaactual = devuelveanio(fechaactual);

        String fechalistadosrt = "01/" + mesfechalistado + "/" + aniofechalistado;
        String aniofechaactualsrt = "01/" + mesfechaactual + "/" + aniofechaactual;

        fechalistado = parsefechadate(fechalistadosrt);
        fechaactual = parsefechadate(aniofechaactualsrt);

        int compara = fechalistado.compareTo(fechaactual);

        if (compara == -1) {
            sesumaimporte = true;
        }

        return sesumaimporte;
    }

    List<d_gastos_inq> obtenerlistadogastospendientesinq(d_inquilino inq) throws Exception {
        d_gastos_inq gas = new d_gastos_inq();
        List<d_gastos_inq> listagastos = null;
        listagastos = new ArrayList<d_gastos_inq>();

        return listagastos = gas.listargastosporinq(inq.getProp_id(), inq.getInq_casa());
    }

    List<d_movimiento> listamovimientos(d_inquilino inq) throws Exception {
        d_movimiento mov = new d_movimiento();
        List<d_movimiento> listamovimientos = null;
        listamovimientos = new ArrayList<d_movimiento>();

        listamovimientos = mov.listarmovimientosdeinq(inq.getProp_id(), inq.getInq_casa());
        return listamovimientos;
    }

    Integer devuelvemes(Date fecha) {
        String formato = "MM";
        SimpleDateFormat dateFormat = new SimpleDateFormat(formato);
        return Integer.parseInt(dateFormat.format(fecha));
    }

    Integer devuelveanio(Date fecha) {
        String formato = "yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(formato);
        return Integer.parseInt(dateFormat.format(fecha));
    }

    Date parsefechadate(String fecha) throws Exception {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaDate = null;

        try {
            fechaDate = formato.parse(fecha);
        } catch (ParseException ex) {
            throw new Exception("revise formato de año");
        }
        return fechaDate;
    }

    Date fechaactual() throws Exception {
        Date fechadate = null;
        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy", new Locale("es_ES"));
        String fechastr = (formateador.format(new Date()));

        return fechadate = parsefechadate(fechastr);
    }

    public void actualizar_doc() {
        for (observador_documentos observador : observadores_doc) {
            observador.actualizar();
        }
    }

     public void actualizar_inq_paga() {
         observadores_inq_paga.forEach((observador) -> {
             observador.actualizar_listado();
        });
    }
    
    public void actualizar_mov() {
        for (observador_mov observador : observadores_mov) {
            observador.actualizar_mov();
        }
    }

    public void actualizar_prop() {
        for (observador_prop observador : observadores_prop) {
            observador.actualizar();
        }
    }

    public void actualizar_inq() {
        for (observador_inq observador : observadores_inq) {
            observador.actualizar();
        }
    }

    public void registrarobservador_inq_paga(observador_inq_paga observador) {
        this.observadores_inq_paga.add(observador);
    }
    
    public void registrarobservador_doc(observador_documentos observador) {
        this.observadores_doc.add(observador);
    }

    public void registrarobservador_prop(observador_prop observador) {
        this.observadores_prop.add(observador);
    }

    public void registrarobservador_inq(observador_inq observador) {
        this.observadores_inq.add(observador);
    }

    public void registrarobservador_mov(observador_mov observador) {
        this.observadores_mov.add(observador);
    }

    private p_control() {

    }

    public void escribirfichero(String info) throws Exception {
        Date fecha = new Date();
        String fecha_nombre_archivo = parsefechastringarchivo(fecha);
        //FileWriter fw = new FileWriter("log-" + fecha_nombre_archivo + ".txt", true);
        File archivo = new File("log-" + fecha_nombre_archivo + ".txt");
        if (archivo.exists()) {
            //ya existe
            BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, true));
            String fechastr = parsefechalogstring(fecha);
            bw.write("[" + fechastr + "]" + "\n");
            bw.write(info);
            bw.write("\n");
            bw.write("------------------------------------------------------------------------");
            bw.write("\n");
            bw.close();
        } else {
            //hay que crearlo
            BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, true));
            String fechastr = parsefechalogstring(fecha);
            bw.write("[" + fechastr + "]" + "\n");
            bw.write(info);
            bw.write("\n");
            bw.write("------------------------------------------------------------------------");
            bw.write("\n");
            bw.close();
        }
    }

    String parsefechalogstring(java.util.Date fecha) {
        SimpleDateFormat formato = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
        String fechastr = formato.format(fecha);

        return fechastr;
    }

    String parsefechastring(java.util.Date fecha) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String fechastr = formato.format(fecha);

        return fechastr;
    }

    String parsefechastringarchivo(java.util.Date fecha) {
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yy");
        String fechastr = formato.format(fecha);

        return fechastr;
    }

    public Integer obtenerMayorNumDOCCFE(int tipo) throws Exception {

        int resultado = -1;

        d_parametroscfe pcfe = new d_parametroscfe();

        RespuestaObtenerCAE obtenerVersion = new RespuestaObtenerCAE();

        pcfe = pcfe.buscarparametroscfe();
        if (pcfe == null) {
            throw new Exception("debe ingresar parametros cfe: nombre, clave y tenant");
        }

        obtenerVersion = obtenerCAE(pcfe.getNomusuario(), pcfe.getClave(), pcfe.getTenant());
        if (obtenerVersion.getCodigo() != 0) {
            throw new Exception("CFE solicitado no existe, o no fue ingresado");
        }

        if (tipo == 111) {
            resultado = obtenerVersion.getCAEList().getValue().getDatoCAE().get(3).getUltnro().getValue();
        }
        if (tipo == 101) {
            resultado = obtenerVersion.getCAEList().getValue().getDatoCAE().get(0).getUltnro().getValue();
        }
        if (tipo == 102) {
            resultado = obtenerVersion.getCAEList().getValue().getDatoCAE().get(1).getUltnro().getValue();
        }
        if (tipo == 112) {
            resultado = obtenerVersion.getCAEList().getValue().getDatoCAE().get(4).getUltnro().getValue();
        }

        //ULTIMOS ID USADOS
        //System.out.println("TICKET "+e_ticket);
        //System.out.println("NC TICKET "+nc_e_ticket);
        //System.out.println("FACTURA "+e_factura);
        //System.out.println("nc factura "+nc_e_factura);
        return resultado;
    }

    private static RespuestaObtenerCAE obtenerCAE(java.lang.String nomusuario, java.lang.String pass, java.lang.String paramTenant) {
        org.tempuri.ImpSICFEEmisor service = new org.tempuri.ImpSICFEEmisor();
        org.tempuri.ISICFEEmisor port = service.getBasicHttpBindingISICFEEmisor();
        return port.obtenerCAE(nomusuario, pass, paramTenant);
    }

    public static p_control getInstancia() {
        if (instancia == null) {
            instancia = new p_control();
        }
        return instancia;
    }

    private static SICFERespuestaObtenerCFE obtenerCFEPorReferencia(java.lang.String usuario, java.lang.String clave, java.lang.String tenant, java.lang.String referenciaERP, java.lang.Boolean devolverImagenQR, java.lang.Boolean devolverXML) {
        org.tempuri.ImpSICFEEmisor service = new org.tempuri.ImpSICFEEmisor();
        org.tempuri.ISICFEEmisor port = service.getBasicHttpBindingISICFEEmisor();
        return port.obtenerCFEPorReferencia(usuario, clave, tenant, referenciaERP, devolverImagenQR, devolverXML);
    }
}
