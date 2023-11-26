/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

/**
 *
 * @author Gonzalo
 */
public class d_generarcfe {

    String nomusuario = ""; //erp
    String clave = ""; //erp
    String tenant = ""; //instag
    String cliente = "";
    String cfexml = "";
    String referenciaERP = "";
    String referenciaERP2 = "";
    Boolean devolverQR = false;
    Integer sizeQR = -1; //10
    Integer imprime = -1; //1
    String recurso = "";
    String template = ""; //Rollo
    Boolean devolverXML = false;
    Boolean erpPideValidacion = false;
    String version = "";

    public d_generarcfe ticketdefecto() {
        d_generarcfe gt = new d_generarcfe();

        gt.setNomusuario(""); //erp
        gt.setClave(""); //erp
        gt.setTenant(""); //instag
        gt.setCliente("");
        gt.setCfexml("");
        gt.setReferenciaERP("");
        gt.setReferenciaERP2("");
        gt.setDevolverQR(false);
        gt.setSizeQR(10); //10
        gt.setImprime(1); //1
        gt.setRecurso("");
        gt.setTemplate("Rollo"); //Rollo
        gt.setDevolverXML(false);
        gt.setErpPideValidacion(false);
        gt.setVersion("");

        return gt;
    }

    public String cfexmlticket(d_movimiento mov) throws Exception {
        String fecha = formatearfechastring(mov.getFecha());
        d_propietario pro = new d_propietario();
        DecimalFormat df = new DecimalFormat("#.00");
        String resultado = "";

        pro = pro.buscarpropietario(mov.getProp_id());

        String scomision = "";
        String siva = "";

        scomision = df.format(mov.getComision());
        scomision = scomision.replace(".", "").replace(",", ".");

        siva = df.format(mov.getIva());
        siva = siva.replace(".", "").replace(",", ".");

        Float comision = Float.parseFloat(scomision);
        Float iva = Float.parseFloat(siva);

        Float total = comision + iva;
        //String stotal = total.toString();
        String stotal = df.format(total);
        stotal = stotal.replace(".", "").replace(",", ".");
        String stotalcondecimal = stotal; //aca

        if (stotalcondecimal.equals(".00")) {
            stotalcondecimal = "0.0";
        }

        //stotal = stotal.replace(".", "").replace(",", ".");
        String separador = Pattern.quote(".");
        String[] parts = stotal.split(separador);
        //String partentera = parts[0]; // 123
        String partdecimal = "0." + parts[1]; // 654321

        Float fpartedecimal = Float.parseFloat(partdecimal);
        total = total - fpartedecimal;
        String stotal1 = df.format(total);
        stotal1 = stotal1.replace(".", "").replace(",", ".");

        if (stotal1.equals(".00")) {
            stotal1 = "0.0";
        }

        if (partdecimal.equals("0.00")) {
            partdecimal = "0.0";
        }

        if (!partdecimal.equals("0.0")) {
            resultado = "<?xml version='1.0' encoding='UTF-8' ?><nsAdenda:CFE_Adenda xmlns:nsAdenda=\'http://cfe.dgi.gub.uy\'>\n"
                    + "			  <nsAd:CFE version=\'1.0\' xmlns:nsAd=\'http://cfe.dgi.gub.uy\'>\n"
                    + "			    <nsAd:eTck>\n"
                    + "			      <nsAd:Encabezado>\n"
                    + "			        <nsAd:IdDoc>\n"
                    + "			          <nsAd:TipoCFE>101</nsAd:TipoCFE>\n"
                    + "			          <nsAd:Serie>A</nsAd:Serie>\n"
                    + "			          <nsAd:Nro>2727</nsAd:Nro>\n"
                    + "			          <nsAd:FchEmis>" + fecha + "</nsAd:FchEmis>\n"
                    + "			          <nsAd:MntBruto>1</nsAd:MntBruto>\n"
                    + "			          <nsAd:FmaPago>1</nsAd:FmaPago>\n"
                    + "			        </nsAd:IdDoc>\n"
                    + "			        <nsAd:Emisor>\n"
                    + "			          <nsAd:RUCEmisor>120196190011</nsAd:RUCEmisor>\n"
                    + "			          <nsAd:RznSoc>Stagno Palermo Diego Fernando</nsAd:RznSoc>\n"
                    + "			          <nsAd:NomComercial>Stagno Palermo Diego Fernando</nsAd:NomComercial>\n"
                    + "			          <nsAd:EmiSucursal>Sucursal principal</nsAd:EmiSucursal>\n"
                    + "			          <nsAd:CdgDGISucur>4</nsAd:CdgDGISucur>\n"
                    + "			          <nsAd:DomFiscal>18 de Julio 1393</nsAd:DomFiscal>\n"
                    + "			          <nsAd:Ciudad>PAYSANDU</nsAd:Ciudad>\n"
                    + "			          <nsAd:Departamento>PAYSANDU</nsAd:Departamento>\n"
                    + "			        </nsAd:Emisor>\n"
                    + "			        <nsAd:Receptor>\n"
                    + "			          "
                    + "			          <nsAd:TipoDocRecep>3</nsAd:TipoDocRecep>\n"
                    + "			          <nsAd:CodPaisRecep>UY</nsAd:CodPaisRecep>\n"
                    + "			          <nsAd:DocRecep>" + pro.getProp_cirut() + "</nsAd:DocRecep>\n"
                    + "			          <nsAd:RznSocRecep>" + pro.getProp_nombre() + "</nsAd:RznSocRecep>\n"
                    + "			          <nsAd:DirRecep>" + pro.getProp_direccion() + "</nsAd:DirRecep>\n"
                    + "			          <nsAd:CiudadRecep>Paysandu</nsAd:CiudadRecep>\n"
                    + "			          <nsAd:DeptoRecep>PAYSANDU</nsAd:DeptoRecep>\n"
                    + "			          <nsAd:PaisRecep>Uruguay</nsAd:PaisRecep>\n"
                    + "			        </nsAd:Receptor>\n"
                    + "			        <nsAd:Totales>\n"
                    + "			          <nsAd:TpoMoneda>UYU</nsAd:TpoMoneda>\n"
                    + "			          <nsAd:MntNoGrv>0</nsAd:MntNoGrv>\n"
                    + "			          <nsAd:MntNetoIvaTasaMin>0</nsAd:MntNetoIvaTasaMin>\n"
                    + "			          <nsAd:MntNetoIVATasaBasica>" + comision + "</nsAd:MntNetoIVATasaBasica>\n"
                    + "			          <nsAd:IVATasaMin>10</nsAd:IVATasaMin>\n"
                    + "			          <nsAd:IVATasaBasica>22</nsAd:IVATasaBasica>\n"
                    + "			          <nsAd:MntIVATasaMin>0</nsAd:MntIVATasaMin>\n"
                    + "			          <nsAd:MntIVATasaBasica>" + iva + "</nsAd:MntIVATasaBasica>\n"
                    + "			          <nsAd:MntTotal>" + stotalcondecimal + "</nsAd:MntTotal>\n"
                    + "			          <nsAd:CantLinDet>2</nsAd:CantLinDet>\n"
                    + "			          <nsAd:MontoNF>-" + partdecimal + "</nsAd:MontoNF>\n"
                    + "			          <nsAd:MntPagar>" + stotal1 + "</nsAd:MntPagar>\n"
                    + "			        </nsAd:Totales>\n"
                    + "			      </nsAd:Encabezado>\n"
                    + "			      <nsAd:Detalle>\n"
                    + "			        <nsAd:Item>\n"
                    + "			          <nsAd:NroLinDet>1</nsAd:NroLinDet>\n"
                    + "			          <nsAd:IndFact>3</nsAd:IndFact>\n"
                    + "			          <nsAd:NomItem>" + mov.getDetalle() + ": " + mov.getMqp() + "/" + mov.getAqp() + "</nsAd:NomItem>\n"
                    + "			          <nsAd:Cantidad>1.000</nsAd:Cantidad>\n"
                    + "			          <nsAd:UniMed>N/A</nsAd:UniMed>\n"
                    + "			          <nsAd:PrecioUnitario>" + stotalcondecimal + "</nsAd:PrecioUnitario>\n"
                    + "			          <nsAd:MontoItem>" + stotalcondecimal + "</nsAd:MontoItem>\n"
                    + "			        </nsAd:Item>"
                    + "<nsAd:Item>\n"
                    + "			          <nsAd:NroLinDet>2</nsAd:NroLinDet>\n"
                    + "			          <nsAd:IndFact>7</nsAd:IndFact>\n"
                    + "			          <nsAd:NomItem>Redondeo</nsAd:NomItem>\n"
                    + "			          <nsAd:Cantidad>1</nsAd:Cantidad>\n"
                    + "			          <nsAd:UniMed>N/A</nsAd:UniMed>\n"
                    + "			          <nsAd:PrecioUnitario>" + partdecimal + "</nsAd:PrecioUnitario>\n"
                    + "			          <nsAd:MontoItem>" + partdecimal + "</nsAd:MontoItem>\n"
                    + "			        </nsAd:Item>	"
                    + ""
                    + "		        \n"
                    + "			      </nsAd:Detalle>\n"
                    + "			      <nsAd:DscRcgGlobal>\n"
                    + "			      </nsAd:DscRcgGlobal>\n"
                    + "			      <nsAd:CAEData>\n"
                    + "			        <nsAd:CAE_ID>1</nsAd:CAE_ID>\n"
                    + "			        <nsAd:DNro>1</nsAd:DNro>\n"
                    + "			        <nsAd:HNro>1</nsAd:HNro>\n"
                    + "			        <nsAd:FecVenc></nsAd:FecVenc>\n"
                    + "			      </nsAd:CAEData>\n"
                    + "			    </nsAd:eTck>\n"
                    + "			  </nsAd:CFE>\n"
                    + "			  <nsAdenda:Adenda>"
                    + ""
                    + "<nsAdenda:AdendaSicfe>\n"
                    + "      <nsAdenda:IdCaja>N/A</nsAdenda:IdCaja>\n"
                    + "      <nsAdenda:IdUsuario>N/A</nsAdenda:IdUsuario>\n"
                    + "    </nsAdenda:AdendaSicfe>&lt;br&gt;&lt;br&gt;ID TRANSACC.:&lt;br&gt;" + mov.getId() + "&lt;/h5&gt;</nsAdenda:Adenda>\n"
                    + "			</nsAdenda:CFE_Adenda>";
        } else {
            resultado = "<?xml version='1.0' encoding='UTF-8' ?><nsAdenda:CFE_Adenda xmlns:nsAdenda=\'http://cfe.dgi.gub.uy\'>\n"
                    + "			  <nsAd:CFE version=\'1.0\' xmlns:nsAd=\'http://cfe.dgi.gub.uy\'>\n"
                    + "			    <nsAd:eTck>\n"
                    + "			      <nsAd:Encabezado>\n"
                    + "			        <nsAd:IdDoc>\n"
                    + "			          <nsAd:TipoCFE>101</nsAd:TipoCFE>\n"
                    + "			          <nsAd:Serie>A</nsAd:Serie>\n"
                    + "			          <nsAd:Nro>2727</nsAd:Nro>\n"
                    + "			          <nsAd:FchEmis>" + fecha + "</nsAd:FchEmis>\n"
                    + "			          <nsAd:MntBruto>1</nsAd:MntBruto>\n"
                    + "			          <nsAd:FmaPago>1</nsAd:FmaPago>\n"
                    + "			        </nsAd:IdDoc>\n"
                    + "			        <nsAd:Emisor>\n"
                    + "			          <nsAd:RUCEmisor>120196190011</nsAd:RUCEmisor>\n"
                    + "			          <nsAd:RznSoc>Stagno Palermo Diego Fernando</nsAd:RznSoc>\n"
                    + "			          <nsAd:NomComercial>Stagno Palermo Diego Fernando</nsAd:NomComercial>\n"
                    + "			          <nsAd:EmiSucursal>Sucursal principal</nsAd:EmiSucursal>\n"
                    + "			          <nsAd:CdgDGISucur>4</nsAd:CdgDGISucur>\n"
                    + "			          <nsAd:DomFiscal>18 de Julio 1393</nsAd:DomFiscal>\n"
                    + "			          <nsAd:Ciudad>PAYSANDU</nsAd:Ciudad>\n"
                    + "			          <nsAd:Departamento>PAYSANDU</nsAd:Departamento>\n"
                    + "			        </nsAd:Emisor>\n"
                    + "			        <nsAd:Receptor>\n"
                    + "			          "
                    + "			          <nsAd:TipoDocRecep>3</nsAd:TipoDocRecep>\n"
                    + "			          <nsAd:CodPaisRecep>UY</nsAd:CodPaisRecep>\n"
                    + "			          <nsAd:DocRecep>" + pro.getProp_cirut() + "</nsAd:DocRecep>\n"
                    + "			          <nsAd:RznSocRecep>" + pro.getProp_nombre() + "</nsAd:RznSocRecep>\n"
                    + "			          <nsAd:DirRecep>" + pro.getProp_direccion() + "</nsAd:DirRecep>\n"
                    + "			          <nsAd:CiudadRecep>Paysandu</nsAd:CiudadRecep>\n"
                    + "			          <nsAd:DeptoRecep>PAYSANDU</nsAd:DeptoRecep>\n"
                    + "			          <nsAd:PaisRecep>Uruguay</nsAd:PaisRecep>\n"
                    + "			        </nsAd:Receptor>\n"
                    + "			        <nsAd:Totales>\n"
                    + "			          <nsAd:TpoMoneda>UYU</nsAd:TpoMoneda>\n"
                    + "			          <nsAd:MntNoGrv>0</nsAd:MntNoGrv>\n"
                    + "			          <nsAd:MntNetoIvaTasaMin>0</nsAd:MntNetoIvaTasaMin>\n"
                    + "			          <nsAd:MntNetoIVATasaBasica>" + comision + "</nsAd:MntNetoIVATasaBasica>\n"
                    + "			          <nsAd:IVATasaMin>10</nsAd:IVATasaMin>\n"
                    + "			          <nsAd:IVATasaBasica>22</nsAd:IVATasaBasica>\n"
                    + "			          <nsAd:MntIVATasaMin>0</nsAd:MntIVATasaMin>\n"
                    + "			          <nsAd:MntIVATasaBasica>" + iva + "</nsAd:MntIVATasaBasica>\n"
                    + "			          <nsAd:MntTotal>" + stotalcondecimal + "</nsAd:MntTotal>\n"
                    + "			          <nsAd:CantLinDet>1</nsAd:CantLinDet>\n"
                    + "			          <nsAd:MontoNF>-" + partdecimal + "</nsAd:MontoNF>\n"
                    + "			          <nsAd:MntPagar>" + stotal1 + "</nsAd:MntPagar>\n"//aca
                    + "			        </nsAd:Totales>\n"
                    + "			      </nsAd:Encabezado>\n"
                    + "			      <nsAd:Detalle>\n"
                    + "			        <nsAd:Item>\n"
                    + "			          <nsAd:NroLinDet>1</nsAd:NroLinDet>\n"
                    + "			          <nsAd:IndFact>3</nsAd:IndFact>\n"
                    + "			          <nsAd:NomItem>" + mov.getDetalle() + ": " + mov.getMqp() + "/" + mov.getAqp() + "</nsAd:NomItem>\n"
                    + "			          <nsAd:Cantidad>1.000</nsAd:Cantidad>\n"
                    + "			          <nsAd:UniMed>N/A</nsAd:UniMed>\n"
                    + "			          <nsAd:PrecioUnitario>" + stotalcondecimal + "</nsAd:PrecioUnitario>\n"
                    + "			          <nsAd:MontoItem>" + stotalcondecimal + "</nsAd:MontoItem>\n"
                    + "			        </nsAd:Item>"
                    + "	"
                    + ""
                    + "		        \n"
                    + "			      </nsAd:Detalle>\n"
                    + "			      <nsAd:DscRcgGlobal>\n"
                    + "			      </nsAd:DscRcgGlobal>\n"
                    + "			      <nsAd:CAEData>\n"
                    + "			        <nsAd:CAE_ID>1</nsAd:CAE_ID>\n"
                    + "			        <nsAd:DNro>1</nsAd:DNro>\n"
                    + "			        <nsAd:HNro>1</nsAd:HNro>\n"
                    + "			        <nsAd:FecVenc></nsAd:FecVenc>\n"
                    + "			      </nsAd:CAEData>\n"
                    + "			    </nsAd:eTck>\n"
                    + "			  </nsAd:CFE>\n"
                    + "			  <nsAdenda:Adenda>"
                    + ""
                    + "<nsAdenda:AdendaSicfe>\n"
                    + "      <nsAdenda:IdCaja>N/A</nsAdenda:IdCaja>\n"
                    + "      <nsAdenda:IdUsuario>N/A</nsAdenda:IdUsuario>\n"
                    + "    </nsAdenda:AdendaSicfe>&lt;br&gt;&lt;br&gt;ID TRANSACC.:&lt;br&gt;" + mov.getId() + "&lt;/h5&gt;</nsAdenda:Adenda>\n"
                    + "			</nsAdenda:CFE_Adenda>";
        }
        return resultado;
    }

    public String cfexmlfactura(d_movimiento mov) throws Exception {
        String fecha = formatearfechastring(mov.getFecha());
        d_propietario pro = new d_propietario();
        DecimalFormat df = new DecimalFormat("#.00");
        String resultado = "";
        d_propietario_dgi p = new d_propietario_dgi();

        pro = pro.buscarpropietario(mov.getProp_id());
        p = p.buscar(mov.getProp_id());

        if (p == null) {
            throw new Exception("no se puede generar la factura electronica, ya que el propietario no cuenta con ciudad, departamento y pais ingresados");
        }

        String scomision = "";
        String siva = "";

        scomision = df.format(mov.getComision());
        scomision = scomision.replace(".", "").replace(",", ".");

        siva = df.format(mov.getIva());
        siva = siva.replace(".", "").replace(",", ".");

        Float comision = Float.parseFloat(scomision);
        Float iva = Float.parseFloat(siva);

        Float total = comision + iva;

        //String stotal = total.toString();
        String stotal = df.format(total);
        stotal = stotal.replace(".", "").replace(",", ".");
        String stotalcondecimal = stotal;

        //stotal = stotal.replace(".", "").replace(",", ".");
        String separador = Pattern.quote(".");
        String[] parts = stotal.split(separador);
        //String partentera = parts[0]; // 123
        String partdecimal = "0." + parts[1]; // 654321

        Float fpartedecimal = Float.parseFloat(partdecimal);
        total = total - fpartedecimal;
        String stotal1 = df.format(total);
        stotal1 = stotal1.replace(".", "").replace(",", ".");

        if (partdecimal.equals("0.00")) {
            partdecimal = "0.0";
        }

        if (!partdecimal.equals("0.0")) {
            resultado = "<?xml version='1.0' encoding='UTF-8' ?><nsAdenda:CFE_Adenda xmlns:nsAdenda=\'http://cfe.dgi.gub.uy\'>\n"
                    + "			  <nsAd:CFE version=\'1.0\' xmlns:nsAd=\'http://cfe.dgi.gub.uy\'>\n"
                    + "			    <nsAd:eFact>\n"
                    + "			      <nsAd:Encabezado>\n"
                    + "			        <nsAd:IdDoc>\n"
                    + "			          <nsAd:TipoCFE>111</nsAd:TipoCFE>\n"
                    + "			          <nsAd:Serie>A</nsAd:Serie>\n"
                    + "			          <nsAd:Nro>2727</nsAd:Nro>\n"
                    + "			          <nsAd:FchEmis>" + fecha + "</nsAd:FchEmis>\n"
                    + "			          <nsAd:MntBruto>1</nsAd:MntBruto>\n"
                    + "			          <nsAd:FmaPago>1</nsAd:FmaPago>\n"
                    + "			        </nsAd:IdDoc>\n"
                    + "			        <nsAd:Emisor>\n"
                    + "			          <nsAd:RUCEmisor>120196190011</nsAd:RUCEmisor>\n"
                    + "			          <nsAd:RznSoc>Stagno Palermo Diego Fernando</nsAd:RznSoc>\n"
                    + "			          <nsAd:NomComercial>Stagno Palermo Diego Fernando</nsAd:NomComercial>\n"
                    + "			          <nsAd:EmiSucursal>Sucursal principal</nsAd:EmiSucursal>\n"
                    + "			          <nsAd:CdgDGISucur>4</nsAd:CdgDGISucur>\n"
                    + "			          <nsAd:DomFiscal>18 de Julio 1393</nsAd:DomFiscal>\n"
                    + "			          <nsAd:Ciudad>PAYSANDU</nsAd:Ciudad>\n"
                    + "			          <nsAd:Departamento>PAYSANDU</nsAd:Departamento>\n"
                    + "			        </nsAd:Emisor>\n"
                    + "			        <nsAd:Receptor>\n"
                    + "			          "
                    + "			          <nsAd:TipoDocRecep>2</nsAd:TipoDocRecep>\n"
                    + "			          <nsAd:CodPaisRecep>UY</nsAd:CodPaisRecep>\n"
                    + "			          <nsAd:DocRecep>" + pro.getProp_cirut() + "</nsAd:DocRecep>\n"
                    + "			          <nsAd:RznSocRecep>" + pro.getProp_nombre() + "</nsAd:RznSocRecep>\n"
                    + "			          <nsAd:DirRecep>" + pro.getProp_direccion() + "</nsAd:DirRecep>\n"
                    + "			          <nsAd:CiudadRecep>" + p.getCiudad() + "</nsAd:CiudadRecep>\n"
                    + "			          <nsAd:DeptoRecep>" + p.getDepartamento() + "</nsAd:DeptoRecep>\n"
                    + "			          <nsAd:PaisRecep>" + p.getPais() + "</nsAd:PaisRecep>\n"
                    + "			        </nsAd:Receptor>\n"
                    + "			        <nsAd:Totales>\n"
                    + "			          <nsAd:TpoMoneda>UYU</nsAd:TpoMoneda>\n"
                    + "			          <nsAd:MntNoGrv>0</nsAd:MntNoGrv>\n"
                    + "			          <nsAd:MntNetoIvaTasaMin>0</nsAd:MntNetoIvaTasaMin>\n"
                    + "			          <nsAd:MntNetoIVATasaBasica>" + comision + "</nsAd:MntNetoIVATasaBasica>\n"
                    + "			          <nsAd:IVATasaMin>10</nsAd:IVATasaMin>\n"
                    + "			          <nsAd:IVATasaBasica>22</nsAd:IVATasaBasica>\n"
                    + "			          <nsAd:MntIVATasaMin>0</nsAd:MntIVATasaMin>\n"
                    + "			          <nsAd:MntIVATasaBasica>" + iva + "</nsAd:MntIVATasaBasica>\n"
                    + "			          <nsAd:MntTotal>" + stotalcondecimal + "</nsAd:MntTotal>\n"
                    + "			          <nsAd:CantLinDet>2</nsAd:CantLinDet>\n"
                    + "			          <nsAd:MontoNF>-" + partdecimal + "</nsAd:MontoNF>\n"
                    + "			          <nsAd:MntPagar>" + stotal1 + "</nsAd:MntPagar>\n"
                    + "			        </nsAd:Totales>\n"
                    + "			      </nsAd:Encabezado>\n"
                    + "			      <nsAd:Detalle>\n"
                    + "			        <nsAd:Item>\n"
                    + "			          <nsAd:NroLinDet>1</nsAd:NroLinDet>\n"
                    + "			          <nsAd:IndFact>3</nsAd:IndFact>\n"
                    + "			          <nsAd:NomItem>" + mov.getDetalle() + ": " + mov.getMqp() + "/" + mov.getAqp() + "</nsAd:NomItem>\n"
                    + "			          <nsAd:Cantidad>1.000</nsAd:Cantidad>\n"
                    + "			          <nsAd:UniMed>N/A</nsAd:UniMed>\n"
                    + "			          <nsAd:PrecioUnitario>" + stotalcondecimal + "</nsAd:PrecioUnitario>\n"
                    + "			          <nsAd:MontoItem>" + stotalcondecimal + "</nsAd:MontoItem>\n"
                    + "			        </nsAd:Item>"
                    + "<nsAd:Item>\n"
                    + "			          <nsAd:NroLinDet>2</nsAd:NroLinDet>\n"
                    + "			          <nsAd:IndFact>7</nsAd:IndFact>\n"
                    + "			          <nsAd:NomItem>Redondeo</nsAd:NomItem>\n"
                    + "			          <nsAd:Cantidad>1</nsAd:Cantidad>\n"
                    + "			          <nsAd:UniMed>N/A</nsAd:UniMed>\n"
                    + "			          <nsAd:PrecioUnitario>" + partdecimal + "</nsAd:PrecioUnitario>\n"
                    + "			          <nsAd:MontoItem>" + partdecimal + "</nsAd:MontoItem>\n"
                    + "			        </nsAd:Item>	"
                    + ""
                    + "		        \n"
                    + "			      </nsAd:Detalle>\n"
                    + "			      <nsAd:DscRcgGlobal>\n"
                    + "			      </nsAd:DscRcgGlobal>\n"
                    + "			      <nsAd:CAEData>\n"
                    + "			        <nsAd:CAE_ID>1</nsAd:CAE_ID>\n"
                    + "			        <nsAd:DNro>1</nsAd:DNro>\n"
                    + "			        <nsAd:HNro>1</nsAd:HNro>\n"
                    + "			        <nsAd:FecVenc></nsAd:FecVenc>\n"
                    + "			      </nsAd:CAEData>\n"
                    + "			    </nsAd:eFact>\n"
                    + "			  </nsAd:CFE>\n"
                    + "			  <nsAdenda:Adenda>"
                    + ""
                    + "<nsAdenda:AdendaSicfe>\n"
                    + "      <nsAdenda:IdCaja>N/A</nsAdenda:IdCaja>\n"
                    + "      <nsAdenda:IdUsuario>N/A</nsAdenda:IdUsuario>\n"
                    + "    </nsAdenda:AdendaSicfe>&lt;br&gt;&lt;br&gt;ID TRANSACC.:&lt;br&gt;" + mov.getId() + "&lt;/h5&gt;</nsAdenda:Adenda>\n"
                    + "			</nsAdenda:CFE_Adenda>";
        } else {
            resultado = "<?xml version='1.0' encoding='UTF-8' ?><nsAdenda:CFE_Adenda xmlns:nsAdenda=\'http://cfe.dgi.gub.uy\'>\n"
                    + "			  <nsAd:CFE version=\'1.0\' xmlns:nsAd=\'http://cfe.dgi.gub.uy\'>\n"
                    + "			    <nsAd:eFact>\n"
                    + "			      <nsAd:Encabezado>\n"
                    + "			        <nsAd:IdDoc>\n"
                    + "			          <nsAd:TipoCFE>111</nsAd:TipoCFE>\n"
                    + "			          <nsAd:Serie>A</nsAd:Serie>\n"
                    + "			          <nsAd:Nro>2727</nsAd:Nro>\n"
                    + "			          <nsAd:FchEmis>" + fecha + "</nsAd:FchEmis>\n"
                    + "			          <nsAd:MntBruto>1</nsAd:MntBruto>\n"
                    + "			          <nsAd:FmaPago>1</nsAd:FmaPago>\n"
                    + "			        </nsAd:IdDoc>\n"
                    + "			        <nsAd:Emisor>\n"
                    + "			          <nsAd:RUCEmisor>120196190011</nsAd:RUCEmisor>\n"
                    + "			          <nsAd:RznSoc>Stagno Palermo Diego Fernando</nsAd:RznSoc>\n"
                    + "			          <nsAd:NomComercial>Stagno Palermo Diego Fernando</nsAd:NomComercial>\n"
                    + "			          <nsAd:EmiSucursal>Sucursal principal</nsAd:EmiSucursal>\n"
                    + "			          <nsAd:CdgDGISucur>4</nsAd:CdgDGISucur>\n"
                    + "			          <nsAd:DomFiscal>18 de Julio 1393</nsAd:DomFiscal>\n"
                    + "			          <nsAd:Ciudad>PAYSANDU</nsAd:Ciudad>\n"
                    + "			          <nsAd:Departamento>PAYSANDU</nsAd:Departamento>\n"
                    + "			        </nsAd:Emisor>\n"
                    + "			        <nsAd:Receptor>\n"
                    + "			          "
                    + "			          <nsAd:TipoDocRecep>2</nsAd:TipoDocRecep>\n"
                    + "			          <nsAd:CodPaisRecep>UY</nsAd:CodPaisRecep>\n"
                    + "			          <nsAd:DocRecep>" + pro.getProp_cirut() + "</nsAd:DocRecep>\n"
                    + "			          <nsAd:RznSocRecep>" + pro.getProp_nombre() + "</nsAd:RznSocRecep>\n"
                    + "			          <nsAd:DirRecep>" + pro.getProp_direccion() + "</nsAd:DirRecep>\n"
                    + "			          <nsAd:CiudadRecep>"+p.getCiudad()+"</nsAd:CiudadRecep>\n"
                    + "			          <nsAd:DeptoRecep>"+p.getDepartamento()+"</nsAd:DeptoRecep>\n"
                    + "			          <nsAd:PaisRecep>"+p.getPais()+"</nsAd:PaisRecep>\n"
                    + "			        </nsAd:Receptor>\n"
                    + "			        <nsAd:Totales>\n"
                    + "			          <nsAd:TpoMoneda>UYU</nsAd:TpoMoneda>\n"
                    + "			          <nsAd:MntNoGrv>0</nsAd:MntNoGrv>\n"
                    + "			          <nsAd:MntNetoIvaTasaMin>0</nsAd:MntNetoIvaTasaMin>\n"
                    + "			          <nsAd:MntNetoIVATasaBasica>" + comision + "</nsAd:MntNetoIVATasaBasica>\n"
                    + "			          <nsAd:IVATasaMin>10</nsAd:IVATasaMin>\n"
                    + "			          <nsAd:IVATasaBasica>22</nsAd:IVATasaBasica>\n"
                    + "			          <nsAd:MntIVATasaMin>0</nsAd:MntIVATasaMin>\n"
                    + "			          <nsAd:MntIVATasaBasica>" + iva + "</nsAd:MntIVATasaBasica>\n"
                    + "			          <nsAd:MntTotal>" + stotalcondecimal + "</nsAd:MntTotal>\n"
                    + "			          <nsAd:CantLinDet>1</nsAd:CantLinDet>\n"
                    + "			          <nsAd:MontoNF>-" + partdecimal + "</nsAd:MontoNF>\n"
                    + "			          <nsAd:MntPagar>" + stotal1 + "</nsAd:MntPagar>\n"
                    + "			        </nsAd:Totales>\n"
                    + "			      </nsAd:Encabezado>\n"
                    + "			      <nsAd:Detalle>\n"
                    + "			        <nsAd:Item>\n"
                    + "			          <nsAd:NroLinDet>1</nsAd:NroLinDet>\n"
                    + "			          <nsAd:IndFact>3</nsAd:IndFact>\n"
                    + "			          <nsAd:NomItem>" + mov.getDetalle() + ": " + mov.getMqp() + "/" + mov.getAqp() + "</nsAd:NomItem>\n"
                    + "			          <nsAd:Cantidad>1.000</nsAd:Cantidad>\n"
                    + "			          <nsAd:UniMed>N/A</nsAd:UniMed>\n"
                    + "			          <nsAd:PrecioUnitario>" + stotalcondecimal + "</nsAd:PrecioUnitario>\n"
                    + "			          <nsAd:MontoItem>" + stotalcondecimal + "</nsAd:MontoItem>\n"
                    + "			        </nsAd:Item>"
                    + "	"
                    + ""
                    + "		        \n"
                    + "			      </nsAd:Detalle>\n"
                    + "			      <nsAd:DscRcgGlobal>\n"
                    + "			      </nsAd:DscRcgGlobal>\n"
                    + "			      <nsAd:CAEData>\n"
                    + "			        <nsAd:CAE_ID>1</nsAd:CAE_ID>\n"
                    + "			        <nsAd:DNro>1</nsAd:DNro>\n"
                    + "			        <nsAd:HNro>1</nsAd:HNro>\n"
                    + "			        <nsAd:FecVenc></nsAd:FecVenc>\n"
                    + "			      </nsAd:CAEData>\n"
                    + "			    </nsAd:eFact>\n"
                    + "			  </nsAd:CFE>\n"
                    + "			  <nsAdenda:Adenda>"
                    + ""
                    + "<nsAdenda:AdendaSicfe>\n"
                    + "      <nsAdenda:IdCaja>N/A</nsAdenda:IdCaja>\n"
                    + "      <nsAdenda:IdUsuario>N/A</nsAdenda:IdUsuario>\n"
                    + "    </nsAdenda:AdendaSicfe>&lt;br&gt;&lt;br&gt;ID TRANSACC.:&lt;br&gt;" + mov.getId() + "&lt;/h5&gt;</nsAdenda:Adenda>\n"
                    + "			</nsAdenda:CFE_Adenda>";
        }

        return resultado;
    }

    public String cfexmlfactura_emitida(String nombre, String rut, String direccion, String ciudad, String departamento, String pais, String concepto, Float total_recibido, Float iva_recibido, String adenda, Date fecha_recibida) throws Exception {
        String fecha = formatearfechastring(fecha_recibida);
        DecimalFormat df = new DecimalFormat("#.00");
        String resultado = "";

        String scomision = "";
        String siva = "";

        scomision = df.format(total_recibido);
        scomision = scomision.replace(".", "").replace(",", ".");

        siva = df.format(iva_recibido);
        siva = siva.replace(".", "").replace(",", ".");

        Float comision = Float.parseFloat(scomision);
        Float iva = Float.parseFloat(siva);

        Float total = comision + iva;

        //String stotal = total.toString();
        String stotal = df.format(total);
        stotal = stotal.replace(".", "").replace(",", ".");
        String stotalcondecimal = stotal;

        //stotal = stotal.replace(".", "").replace(",", ".");
        String separador = Pattern.quote(".");
        String[] parts = stotal.split(separador);
        //String partentera = parts[0]; // 123
        String partdecimal = "0." + parts[1]; // 654321

        Float fpartedecimal = Float.parseFloat(partdecimal);
        total = total - fpartedecimal;
        String stotal1 = df.format(total);
        stotal1 = stotal1.replace(".", "").replace(",", ".");

        if (partdecimal.equals("0.00")) {
            partdecimal = "0.0";
        }

        if (!partdecimal.equals("0.0")) {
            resultado = "<?xml version='1.0' encoding='UTF-8' ?><nsAdenda:CFE_Adenda xmlns:nsAdenda=\'http://cfe.dgi.gub.uy\'>\n"
                    + "			  <nsAd:CFE version=\'1.0\' xmlns:nsAd=\'http://cfe.dgi.gub.uy\'>\n"
                    + "			    <nsAd:eFact>\n"
                    + "			      <nsAd:Encabezado>\n"
                    + "			        <nsAd:IdDoc>\n"
                    + "			          <nsAd:TipoCFE>111</nsAd:TipoCFE>\n"
                    + "			          <nsAd:Serie>A</nsAd:Serie>\n"
                    + "			          <nsAd:Nro>2727</nsAd:Nro>\n"
                    + "			          <nsAd:FchEmis>" + fecha + "</nsAd:FchEmis>\n"
                    + "			          <nsAd:MntBruto>1</nsAd:MntBruto>\n"
                    + "			          <nsAd:FmaPago>1</nsAd:FmaPago>\n"
                    + "			        </nsAd:IdDoc>\n"
                    + "			        <nsAd:Emisor>\n"
                    + "			          <nsAd:RUCEmisor>120196190011</nsAd:RUCEmisor>\n"
                    + "			          <nsAd:RznSoc>Stagno Palermo Diego Fernando</nsAd:RznSoc>\n"
                    + "			          <nsAd:NomComercial>Stagno Palermo Diego Fernando</nsAd:NomComercial>\n"
                    + "			          <nsAd:EmiSucursal>Sucursal principal</nsAd:EmiSucursal>\n"
                    + "			          <nsAd:CdgDGISucur>4</nsAd:CdgDGISucur>\n"
                    + "			          <nsAd:DomFiscal>18 de Julio 1393</nsAd:DomFiscal>\n"
                    + "			          <nsAd:Ciudad>PAYSANDU</nsAd:Ciudad>\n"
                    + "			          <nsAd:Departamento>PAYSANDU</nsAd:Departamento>\n"
                    + "			        </nsAd:Emisor>\n"
                    + "			        <nsAd:Receptor>\n"
                    + "			          "
                    + "			          <nsAd:TipoDocRecep>2</nsAd:TipoDocRecep>\n"
                    + "			          <nsAd:CodPaisRecep>UY</nsAd:CodPaisRecep>\n"
                    + "			          <nsAd:DocRecep>" + rut + "</nsAd:DocRecep>\n"
                    + "			          <nsAd:RznSocRecep>" + nombre + "</nsAd:RznSocRecep>\n"
                    + "			          <nsAd:DirRecep>" + direccion + "</nsAd:DirRecep>\n"
                    + "			          <nsAd:CiudadRecep>" + ciudad + "</nsAd:CiudadRecep>\n"
                    + "			          <nsAd:DeptoRecep>" + departamento + "</nsAd:DeptoRecep>\n"
                    + "			          <nsAd:PaisRecep>" + pais + "</nsAd:PaisRecep>\n"
                    + "			        </nsAd:Receptor>\n"
                    + "			        <nsAd:Totales>\n"
                    + "			          <nsAd:TpoMoneda>UYU</nsAd:TpoMoneda>\n"
                    + "			          <nsAd:MntNoGrv>0</nsAd:MntNoGrv>\n"
                    + "			          <nsAd:MntNetoIvaTasaMin>0</nsAd:MntNetoIvaTasaMin>\n"
                    + "			          <nsAd:MntNetoIVATasaBasica>" + comision + "</nsAd:MntNetoIVATasaBasica>\n"
                    + "			          <nsAd:IVATasaMin>10</nsAd:IVATasaMin>\n"
                    + "			          <nsAd:IVATasaBasica>22</nsAd:IVATasaBasica>\n"
                    + "			          <nsAd:MntIVATasaMin>0</nsAd:MntIVATasaMin>\n"
                    + "			          <nsAd:MntIVATasaBasica>" + iva + "</nsAd:MntIVATasaBasica>\n"
                    + "			          <nsAd:MntTotal>" + stotalcondecimal + "</nsAd:MntTotal>\n"
                    + "			          <nsAd:CantLinDet>2</nsAd:CantLinDet>\n"
                    + "			          <nsAd:MontoNF>-" + partdecimal + "</nsAd:MontoNF>\n"
                    + "			          <nsAd:MntPagar>" + stotal1 + "</nsAd:MntPagar>\n"
                    + "			        </nsAd:Totales>\n"
                    + "			      </nsAd:Encabezado>\n"
                    + "			      <nsAd:Detalle>\n"
                    + "			        <nsAd:Item>\n"
                    + "			          <nsAd:NroLinDet>1</nsAd:NroLinDet>\n"
                    + "			          <nsAd:IndFact>3</nsAd:IndFact>\n"
                    + "			          <nsAd:NomItem>" + concepto + "</nsAd:NomItem>\n"
                    + "			          <nsAd:Cantidad>1.000</nsAd:Cantidad>\n"
                    + "			          <nsAd:UniMed>N/A</nsAd:UniMed>\n"
                    + "			          <nsAd:PrecioUnitario>" + stotalcondecimal + "</nsAd:PrecioUnitario>\n"
                    + "			          <nsAd:MontoItem>" + stotalcondecimal + "</nsAd:MontoItem>\n"
                    + "			        </nsAd:Item>"
                    + "<nsAd:Item>\n"
                    + "			          <nsAd:NroLinDet>2</nsAd:NroLinDet>\n"
                    + "			          <nsAd:IndFact>7</nsAd:IndFact>\n"
                    + "			          <nsAd:NomItem>Redondeo</nsAd:NomItem>\n"
                    + "			          <nsAd:Cantidad>1</nsAd:Cantidad>\n"
                    + "			          <nsAd:UniMed>N/A</nsAd:UniMed>\n"
                    + "			          <nsAd:PrecioUnitario>" + partdecimal + "</nsAd:PrecioUnitario>\n"
                    + "			          <nsAd:MontoItem>" + partdecimal + "</nsAd:MontoItem>\n"
                    + "			        </nsAd:Item>	"
                    + ""
                    + "		        \n"
                    + "			      </nsAd:Detalle>\n"
                    + "			      <nsAd:DscRcgGlobal>\n"
                    + "			      </nsAd:DscRcgGlobal>\n"
                    + "			      <nsAd:CAEData>\n"
                    + "			        <nsAd:CAE_ID>1</nsAd:CAE_ID>\n"
                    + "			        <nsAd:DNro>1</nsAd:DNro>\n"
                    + "			        <nsAd:HNro>1</nsAd:HNro>\n"
                    + "			        <nsAd:FecVenc></nsAd:FecVenc>\n"
                    + "			      </nsAd:CAEData>\n"
                    + "			    </nsAd:eFact>\n"
                    + "			  </nsAd:CFE>\n"
                    + "			  <nsAdenda:Adenda>"
                    + ""
                    + "<nsAdenda:AdendaSicfe>\n"
                    + "      <nsAdenda:IdCaja>N/A</nsAdenda:IdCaja>\n"
                    + "      <nsAdenda:IdUsuario>N/A</nsAdenda:IdUsuario>\n"
                    + "    </nsAdenda:AdendaSicfe>" + adenda + "</nsAdenda:Adenda>\n"
                    + "			</nsAdenda:CFE_Adenda>";
        } else {
            resultado = "<?xml version='1.0' encoding='UTF-8' ?><nsAdenda:CFE_Adenda xmlns:nsAdenda=\'http://cfe.dgi.gub.uy\'>\n"
                    + "			  <nsAd:CFE version=\'1.0\' xmlns:nsAd=\'http://cfe.dgi.gub.uy\'>\n"
                    + "			    <nsAd:eFact>\n"
                    + "			      <nsAd:Encabezado>\n"
                    + "			        <nsAd:IdDoc>\n"
                    + "			          <nsAd:TipoCFE>111</nsAd:TipoCFE>\n"
                    + "			          <nsAd:Serie>A</nsAd:Serie>\n"
                    + "			          <nsAd:Nro>2727</nsAd:Nro>\n"
                    + "			          <nsAd:FchEmis>" + fecha + "</nsAd:FchEmis>\n"
                    + "			          <nsAd:MntBruto>1</nsAd:MntBruto>\n"
                    + "			          <nsAd:FmaPago>1</nsAd:FmaPago>\n"
                    + "			        </nsAd:IdDoc>\n"
                    + "			        <nsAd:Emisor>\n"
                    + "			          <nsAd:RUCEmisor>120196190011</nsAd:RUCEmisor>\n"
                    + "			          <nsAd:RznSoc>Stagno Palermo Diego Fernando</nsAd:RznSoc>\n"
                    + "			          <nsAd:NomComercial>Stagno Palermo Diego Fernando</nsAd:NomComercial>\n"
                    + "			          <nsAd:EmiSucursal>Sucursal principal</nsAd:EmiSucursal>\n"
                    + "			          <nsAd:CdgDGISucur>4</nsAd:CdgDGISucur>\n"
                    + "			          <nsAd:DomFiscal>18 de Julio 1393</nsAd:DomFiscal>\n"
                    + "			          <nsAd:Ciudad>PAYSANDU</nsAd:Ciudad>\n"
                    + "			          <nsAd:Departamento>PAYSANDU</nsAd:Departamento>\n"
                    + "			        </nsAd:Emisor>\n"
                    + "			        <nsAd:Receptor>\n"
                    + "			          "
                    + "			          <nsAd:TipoDocRecep>2</nsAd:TipoDocRecep>\n"
                    + "			          <nsAd:CodPaisRecep>UY</nsAd:CodPaisRecep>\n"
                    + "			          <nsAd:DocRecep>" + rut + "</nsAd:DocRecep>\n"
                    + "			          <nsAd:RznSocRecep>" + nombre + "</nsAd:RznSocRecep>\n"
                    + "			          <nsAd:DirRecep>" + direccion + "</nsAd:DirRecep>\n"
                    + "			          <nsAd:CiudadRecep>"+ciudad+"</nsAd:CiudadRecep>\n"
                    + "			          <nsAd:DeptoRecep>"+departamento+"</nsAd:DeptoRecep>\n"
                    + "			          <nsAd:PaisRecep>"+pais+"</nsAd:PaisRecep>\n"
                    + "			        </nsAd:Receptor>\n"
                    + "			        <nsAd:Totales>\n"
                    + "			          <nsAd:TpoMoneda>UYU</nsAd:TpoMoneda>\n"
                    + "			          <nsAd:MntNoGrv>0</nsAd:MntNoGrv>\n"
                    + "			          <nsAd:MntNetoIvaTasaMin>0</nsAd:MntNetoIvaTasaMin>\n"
                    + "			          <nsAd:MntNetoIVATasaBasica>" + comision + "</nsAd:MntNetoIVATasaBasica>\n"
                    + "			          <nsAd:IVATasaMin>10</nsAd:IVATasaMin>\n"
                    + "			          <nsAd:IVATasaBasica>22</nsAd:IVATasaBasica>\n"
                    + "			          <nsAd:MntIVATasaMin>0</nsAd:MntIVATasaMin>\n"
                    + "			          <nsAd:MntIVATasaBasica>" + iva + "</nsAd:MntIVATasaBasica>\n"
                    + "			          <nsAd:MntTotal>" + stotalcondecimal + "</nsAd:MntTotal>\n"
                    + "			          <nsAd:CantLinDet>1</nsAd:CantLinDet>\n"
                    + "			          <nsAd:MontoNF>-" + partdecimal + "</nsAd:MontoNF>\n"
                    + "			          <nsAd:MntPagar>" + stotal1 + "</nsAd:MntPagar>\n"
                    + "			        </nsAd:Totales>\n"
                    + "			      </nsAd:Encabezado>\n"
                    + "			      <nsAd:Detalle>\n"
                    + "			        <nsAd:Item>\n"
                    + "			          <nsAd:NroLinDet>1</nsAd:NroLinDet>\n"
                    + "			          <nsAd:IndFact>3</nsAd:IndFact>\n"
                    + "			          <nsAd:NomItem>" + concepto + "</nsAd:NomItem>\n"
                    + "			          <nsAd:Cantidad>1.000</nsAd:Cantidad>\n"
                    + "			          <nsAd:UniMed>N/A</nsAd:UniMed>\n"
                    + "			          <nsAd:PrecioUnitario>" + stotalcondecimal + "</nsAd:PrecioUnitario>\n"
                    + "			          <nsAd:MontoItem>" + stotalcondecimal + "</nsAd:MontoItem>\n"
                    + "			        </nsAd:Item>"
                    + "	"
                    + ""
                    + "		        \n"
                    + "			      </nsAd:Detalle>\n"
                    + "			      <nsAd:DscRcgGlobal>\n"
                    + "			      </nsAd:DscRcgGlobal>\n"
                    + "			      <nsAd:CAEData>\n"
                    + "			        <nsAd:CAE_ID>1</nsAd:CAE_ID>\n"
                    + "			        <nsAd:DNro>1</nsAd:DNro>\n"
                    + "			        <nsAd:HNro>1</nsAd:HNro>\n"
                    + "			        <nsAd:FecVenc></nsAd:FecVenc>\n"
                    + "			      </nsAd:CAEData>\n"
                    + "			    </nsAd:eFact>\n"
                    + "			  </nsAd:CFE>\n"
                    + "			  <nsAdenda:Adenda>"
                    + ""
                    + "<nsAdenda:AdendaSicfe>\n"
                    + "      <nsAdenda:IdCaja>N/A</nsAdenda:IdCaja>\n"
                    + "      <nsAdenda:IdUsuario>N/A</nsAdenda:IdUsuario>\n"
                    + "    </nsAdenda:AdendaSicfe>" + adenda + "</nsAdenda:Adenda>\n"
                    + "			</nsAdenda:CFE_Adenda>";
        }

        return resultado;
    }
    
    public String cfexmlfactura_emitida_dolares(String nombre, String rut, String direccion, String ciudad, String departamento, String pais, String concepto, Float cotizacion_dolar,Float total_recibido, Float iva_recibido, String adenda, Date fecha_recibida) throws Exception {
        String fecha = formatearfechastring(fecha_recibida);
        DecimalFormat df = new DecimalFormat("#.00");
        DecimalFormat df_cotizacion = new DecimalFormat("####.000");
        String resultado = "";

        String scomision = "";
        String siva = "";

        scomision = df.format(total_recibido);
        scomision = scomision.replace(".", "").replace(",", ".");

        siva = df.format(iva_recibido);
        siva = siva.replace(".", "").replace(",", ".");

        Float comision = Float.parseFloat(scomision);
        Float iva = Float.parseFloat(siva);

        Float total = comision + iva;

        //String stotal = total.toString();
        String stotal = df.format(total);
        stotal = stotal.replace(".", "").replace(",", ".");
        String stotalcondecimal = stotal;
        
        //String stotal = total.toString();
        String scotizacion = df_cotizacion.format(cotizacion_dolar);
        scotizacion = scotizacion.replace(".", "").replace(",", ".");
        String scotizacion_xml = scotizacion;

        //stotal = stotal.replace(".", "").replace(",", ".");
        String separador = Pattern.quote(".");
        String[] parts = stotal.split(separador);
        //String partentera = parts[0]; // 123
        String partdecimal = "0." + parts[1]; // 654321

        Float fpartedecimal = Float.parseFloat(partdecimal);
        total = total - fpartedecimal;
        String stotal1 = df.format(total);
        stotal1 = stotal1.replace(".", "").replace(",", ".");

        if (partdecimal.equals("0.00")) {
            partdecimal = "0.0";
        }

        if (!partdecimal.equals("0.0")) {
            resultado = "<?xml version='1.0' encoding='UTF-8' ?><nsAdenda:CFE_Adenda xmlns:nsAdenda=\'http://cfe.dgi.gub.uy\'>\n"
                    + "			  <nsAd:CFE version=\'1.0\' xmlns:nsAd=\'http://cfe.dgi.gub.uy\'>\n"
                    + "			    <nsAd:eFact>\n"
                    + "			      <nsAd:Encabezado>\n"
                    + "			        <nsAd:IdDoc>\n"
                    + "			          <nsAd:TipoCFE>111</nsAd:TipoCFE>\n"
                    + "			          <nsAd:Serie>A</nsAd:Serie>\n"
                    + "			          <nsAd:Nro>2727</nsAd:Nro>\n"
                    + "			          <nsAd:FchEmis>" + fecha + "</nsAd:FchEmis>\n"
                    + "			          <nsAd:MntBruto>1</nsAd:MntBruto>\n"
                    + "			          <nsAd:FmaPago>1</nsAd:FmaPago>\n"
                    + "			        </nsAd:IdDoc>\n"
                    + "			        <nsAd:Emisor>\n"
                    + "			          <nsAd:RUCEmisor>120196190011</nsAd:RUCEmisor>\n"
                    + "			          <nsAd:RznSoc>Stagno Palermo Diego Fernando</nsAd:RznSoc>\n"
                    + "			          <nsAd:NomComercial>Stagno Palermo Diego Fernando</nsAd:NomComercial>\n"
                    + "			          <nsAd:EmiSucursal>Sucursal principal</nsAd:EmiSucursal>\n"
                    + "			          <nsAd:CdgDGISucur>4</nsAd:CdgDGISucur>\n"
                    + "			          <nsAd:DomFiscal>18 de Julio 1393</nsAd:DomFiscal>\n"
                    + "			          <nsAd:Ciudad>PAYSANDU</nsAd:Ciudad>\n"
                    + "			          <nsAd:Departamento>PAYSANDU</nsAd:Departamento>\n"
                    + "			        </nsAd:Emisor>\n"
                    + "			        <nsAd:Receptor>\n"
                    + "			          "
                    + "			          <nsAd:TipoDocRecep>2</nsAd:TipoDocRecep>\n"
                    + "			          <nsAd:CodPaisRecep>UY</nsAd:CodPaisRecep>\n"
                    + "			          <nsAd:DocRecep>" + rut + "</nsAd:DocRecep>\n"
                    + "			          <nsAd:RznSocRecep>" + nombre + "</nsAd:RznSocRecep>\n"
                    + "			          <nsAd:DirRecep>" + direccion + "</nsAd:DirRecep>\n"
                    + "			          <nsAd:CiudadRecep>" + ciudad + "</nsAd:CiudadRecep>\n"
                    + "			          <nsAd:DeptoRecep>" + departamento + "</nsAd:DeptoRecep>\n"
                    + "			          <nsAd:PaisRecep>" + pais + "</nsAd:PaisRecep>\n"
                    + "			        </nsAd:Receptor>\n"
                    + "			        <nsAd:Totales>\n"
                    + "			          <nsAd:TpoMoneda>USD</nsAd:TpoMoneda>\n"
                    + "			          <nsAd:TpoCambio>" + scotizacion_xml + "</nsAd:TpoCambio>\n"
                    + "			          <nsAd:MntNoGrv>0</nsAd:MntNoGrv>\n"
                    + "			          <nsAd:MntNetoIvaTasaMin>0</nsAd:MntNetoIvaTasaMin>\n"
                    + "			          <nsAd:MntNetoIVATasaBasica>" + comision + "</nsAd:MntNetoIVATasaBasica>\n"
                    + "			          <nsAd:IVATasaMin>10</nsAd:IVATasaMin>\n"
                    + "			          <nsAd:IVATasaBasica>22</nsAd:IVATasaBasica>\n"
                    + "			          <nsAd:MntIVATasaMin>0</nsAd:MntIVATasaMin>\n"
                    + "			          <nsAd:MntIVATasaBasica>" + iva + "</nsAd:MntIVATasaBasica>\n"
                    + "			          <nsAd:MntTotal>" + stotalcondecimal + "</nsAd:MntTotal>\n"
                    + "			          <nsAd:CantLinDet>2</nsAd:CantLinDet>\n"
                    + "			          <nsAd:MontoNF>-" + partdecimal + "</nsAd:MontoNF>\n"
                    + "			          <nsAd:MntPagar>" + stotal1 + "</nsAd:MntPagar>\n"
                    + "			        </nsAd:Totales>\n"
                    + "			      </nsAd:Encabezado>\n"
                    + "			      <nsAd:Detalle>\n"
                    + "			        <nsAd:Item>\n"
                    + "			          <nsAd:NroLinDet>1</nsAd:NroLinDet>\n"
                    + "			          <nsAd:IndFact>3</nsAd:IndFact>\n"
                    + "			          <nsAd:NomItem>" + concepto + "</nsAd:NomItem>\n"
                    + "			          <nsAd:Cantidad>1.000</nsAd:Cantidad>\n"
                    + "			          <nsAd:UniMed>N/A</nsAd:UniMed>\n"
                    + "			          <nsAd:PrecioUnitario>" + stotalcondecimal + "</nsAd:PrecioUnitario>\n"
                    + "			          <nsAd:MontoItem>" + stotalcondecimal + "</nsAd:MontoItem>\n"
                    + "			        </nsAd:Item>"
                    + "<nsAd:Item>\n"
                    + "			          <nsAd:NroLinDet>2</nsAd:NroLinDet>\n"
                    + "			          <nsAd:IndFact>7</nsAd:IndFact>\n"
                    + "			          <nsAd:NomItem>Redondeo</nsAd:NomItem>\n"
                    + "			          <nsAd:Cantidad>1</nsAd:Cantidad>\n"
                    + "			          <nsAd:UniMed>N/A</nsAd:UniMed>\n"
                    + "			          <nsAd:PrecioUnitario>" + partdecimal + "</nsAd:PrecioUnitario>\n"
                    + "			          <nsAd:MontoItem>" + partdecimal + "</nsAd:MontoItem>\n"
                    + "			        </nsAd:Item>	"
                    + ""
                    + "		        \n"
                    + "			      </nsAd:Detalle>\n"
                    + "			      <nsAd:DscRcgGlobal>\n"
                    + "			      </nsAd:DscRcgGlobal>\n"
                    + "			      <nsAd:CAEData>\n"
                    + "			        <nsAd:CAE_ID>1</nsAd:CAE_ID>\n"
                    + "			        <nsAd:DNro>1</nsAd:DNro>\n"
                    + "			        <nsAd:HNro>1</nsAd:HNro>\n"
                    + "			        <nsAd:FecVenc></nsAd:FecVenc>\n"
                    + "			      </nsAd:CAEData>\n"
                    + "			    </nsAd:eFact>\n"
                    + "			  </nsAd:CFE>\n"
                    + "			  <nsAdenda:Adenda>"
                    + ""
                    + "<nsAdenda:AdendaSicfe>\n"
                    + "      <nsAdenda:IdCaja>N/A</nsAdenda:IdCaja>\n"
                    + "      <nsAdenda:IdUsuario>N/A</nsAdenda:IdUsuario>\n"
                    + "    </nsAdenda:AdendaSicfe>" + adenda + "</nsAdenda:Adenda>\n"
                    + "			</nsAdenda:CFE_Adenda>";
        } else {
            resultado = "<?xml version='1.0' encoding='UTF-8' ?><nsAdenda:CFE_Adenda xmlns:nsAdenda=\'http://cfe.dgi.gub.uy\'>\n"
                    + "			  <nsAd:CFE version=\'1.0\' xmlns:nsAd=\'http://cfe.dgi.gub.uy\'>\n"
                    + "			    <nsAd:eFact>\n"
                    + "			      <nsAd:Encabezado>\n"
                    + "			        <nsAd:IdDoc>\n"
                    + "			          <nsAd:TipoCFE>111</nsAd:TipoCFE>\n"
                    + "			          <nsAd:Serie>A</nsAd:Serie>\n"
                    + "			          <nsAd:Nro>2727</nsAd:Nro>\n"
                    + "			          <nsAd:FchEmis>" + fecha + "</nsAd:FchEmis>\n"
                    + "			          <nsAd:MntBruto>1</nsAd:MntBruto>\n"
                    + "			          <nsAd:FmaPago>1</nsAd:FmaPago>\n"
                    + "			        </nsAd:IdDoc>\n"
                    + "			        <nsAd:Emisor>\n"
                    + "			          <nsAd:RUCEmisor>120196190011</nsAd:RUCEmisor>\n"
                    + "			          <nsAd:RznSoc>Stagno Palermo Diego Fernando</nsAd:RznSoc>\n"
                    + "			          <nsAd:NomComercial>Stagno Palermo Diego Fernando</nsAd:NomComercial>\n"
                    + "			          <nsAd:EmiSucursal>Sucursal principal</nsAd:EmiSucursal>\n"
                    + "			          <nsAd:CdgDGISucur>4</nsAd:CdgDGISucur>\n"
                    + "			          <nsAd:DomFiscal>18 de Julio 1393</nsAd:DomFiscal>\n"
                    + "			          <nsAd:Ciudad>PAYSANDU</nsAd:Ciudad>\n"
                    + "			          <nsAd:Departamento>PAYSANDU</nsAd:Departamento>\n"
                    + "			        </nsAd:Emisor>\n"
                    + "			        <nsAd:Receptor>\n"
                    + "			          "
                    + "			          <nsAd:TipoDocRecep>2</nsAd:TipoDocRecep>\n"
                    + "			          <nsAd:CodPaisRecep>UY</nsAd:CodPaisRecep>\n"
                    + "			          <nsAd:DocRecep>" + rut + "</nsAd:DocRecep>\n"
                    + "			          <nsAd:RznSocRecep>" + nombre + "</nsAd:RznSocRecep>\n"
                    + "			          <nsAd:DirRecep>" + direccion + "</nsAd:DirRecep>\n"
                    + "			          <nsAd:CiudadRecep>"+ciudad+"</nsAd:CiudadRecep>\n"
                    + "			          <nsAd:DeptoRecep>"+departamento+"</nsAd:DeptoRecep>\n"
                    + "			          <nsAd:PaisRecep>"+pais+"</nsAd:PaisRecep>\n"
                    + "			        </nsAd:Receptor>\n"
                    + "			        <nsAd:Totales>\n"
                    + "			          <nsAd:TpoMoneda>USD</nsAd:TpoMoneda>\n"
                    + "			          <nsAd:TpoCambio>" + scotizacion_xml + "</nsAd:TpoCambio>\n"
                    + "			          <nsAd:MntNoGrv>0</nsAd:MntNoGrv>\n"
                    + "			          <nsAd:MntNetoIvaTasaMin>0</nsAd:MntNetoIvaTasaMin>\n"
                    + "			          <nsAd:MntNetoIVATasaBasica>" + comision + "</nsAd:MntNetoIVATasaBasica>\n"
                    + "			          <nsAd:IVATasaMin>10</nsAd:IVATasaMin>\n"
                    + "			          <nsAd:IVATasaBasica>22</nsAd:IVATasaBasica>\n"
                    + "			          <nsAd:MntIVATasaMin>0</nsAd:MntIVATasaMin>\n"
                    + "			          <nsAd:MntIVATasaBasica>" + iva + "</nsAd:MntIVATasaBasica>\n"
                    + "			          <nsAd:MntTotal>" + stotalcondecimal + "</nsAd:MntTotal>\n"
                    + "			          <nsAd:CantLinDet>1</nsAd:CantLinDet>\n"
                    + "			          <nsAd:MontoNF>-" + partdecimal + "</nsAd:MontoNF>\n"
                    + "			          <nsAd:MntPagar>" + stotal1 + "</nsAd:MntPagar>\n"
                    + "			        </nsAd:Totales>\n"
                    + "			      </nsAd:Encabezado>\n"
                    + "			      <nsAd:Detalle>\n"
                    + "			        <nsAd:Item>\n"
                    + "			          <nsAd:NroLinDet>1</nsAd:NroLinDet>\n"
                    + "			          <nsAd:IndFact>3</nsAd:IndFact>\n"
                    + "			          <nsAd:NomItem>" + concepto + "</nsAd:NomItem>\n"
                    + "			          <nsAd:Cantidad>1.000</nsAd:Cantidad>\n"
                    + "			          <nsAd:UniMed>N/A</nsAd:UniMed>\n"
                    + "			          <nsAd:PrecioUnitario>" + stotalcondecimal + "</nsAd:PrecioUnitario>\n"
                    + "			          <nsAd:MontoItem>" + stotalcondecimal + "</nsAd:MontoItem>\n"
                    + "			        </nsAd:Item>"
                    + "	"
                    + ""
                    + "		        \n"
                    + "			      </nsAd:Detalle>\n"
                    + "			      <nsAd:DscRcgGlobal>\n"
                    + "			      </nsAd:DscRcgGlobal>\n"
                    + "			      <nsAd:CAEData>\n"
                    + "			        <nsAd:CAE_ID>1</nsAd:CAE_ID>\n"
                    + "			        <nsAd:DNro>1</nsAd:DNro>\n"
                    + "			        <nsAd:HNro>1</nsAd:HNro>\n"
                    + "			        <nsAd:FecVenc></nsAd:FecVenc>\n"
                    + "			      </nsAd:CAEData>\n"
                    + "			    </nsAd:eFact>\n"
                    + "			  </nsAd:CFE>\n"
                    + "			  <nsAdenda:Adenda>"
                    + ""
                    + "<nsAdenda:AdendaSicfe>\n"
                    + "      <nsAdenda:IdCaja>N/A</nsAdenda:IdCaja>\n"
                    + "      <nsAdenda:IdUsuario>N/A</nsAdenda:IdUsuario>\n"
                    + "    </nsAdenda:AdendaSicfe>" + adenda + "</nsAdenda:Adenda>\n"
                    + "			</nsAdenda:CFE_Adenda>";
        }

        return resultado;
    }

    public String cfexmlticket_emitida(String nombre, String ci, String direccion, String concepto, Float total_recibido, Float iva_recibido, String adenda, Date fecha_recibida) throws Exception {
        String fecha = formatearfechastring(fecha_recibida);
        DecimalFormat df = new DecimalFormat("#.00");
        String resultado = "";

        String scomision = "";
        String siva = "";

        scomision = df.format(total_recibido);
        scomision = scomision.replace(".", "").replace(",", ".");

        siva = df.format(iva_recibido);
        siva = siva.replace(".", "").replace(",", ".");

        Float comision = Float.parseFloat(scomision);
        Float iva = Float.parseFloat(siva);

        Float total = comision + iva;

        //String stotal = total.toString();
        String stotal = df.format(total);
        stotal = stotal.replace(".", "").replace(",", ".");
        String stotalcondecimal = stotal;

        //stotal = stotal.replace(".", "").replace(",", ".");
        String separador = Pattern.quote(".");
        String[] parts = stotal.split(separador);
        //String partentera = parts[0]; // 123
        String partdecimal = "0." + parts[1]; // 654321

        Float fpartedecimal = Float.parseFloat(partdecimal);
        total = total - fpartedecimal;
        String stotal1 = df.format(total);
        stotal1 = stotal1.replace(".", "").replace(",", ".");

        if (partdecimal.equals("0.00")) {
            partdecimal = "0.0";
        }

        if (!partdecimal.equals("0.0")) {
            resultado = "<?xml version='1.0' encoding='UTF-8' ?><nsAdenda:CFE_Adenda xmlns:nsAdenda=\'http://cfe.dgi.gub.uy\'>\n"
                    + "			  <nsAd:CFE version=\'1.0\' xmlns:nsAd=\'http://cfe.dgi.gub.uy\'>\n"
                    + "			    <nsAd:eTck>\n"
                    + "			      <nsAd:Encabezado>\n"
                    + "			        <nsAd:IdDoc>\n"
                    + "			          <nsAd:TipoCFE>101</nsAd:TipoCFE>\n"
                    + "			          <nsAd:Serie>A</nsAd:Serie>\n"
                    + "			          <nsAd:Nro>2727</nsAd:Nro>\n"
                    + "			          <nsAd:FchEmis>" + fecha + "</nsAd:FchEmis>\n"
                    + "			          <nsAd:MntBruto>1</nsAd:MntBruto>\n"
                    + "			          <nsAd:FmaPago>1</nsAd:FmaPago>\n"
                    + "			        </nsAd:IdDoc>\n"
                    + "			        <nsAd:Emisor>\n"
                    + "			          <nsAd:RUCEmisor>120196190011</nsAd:RUCEmisor>\n"
                    + "			          <nsAd:RznSoc>Stagno Palermo Diego Fernando</nsAd:RznSoc>\n"
                    + "			          <nsAd:NomComercial>Stagno Palermo Diego Fernando</nsAd:NomComercial>\n"
                    + "			          <nsAd:EmiSucursal>Sucursal principal</nsAd:EmiSucursal>\n"
                    + "			          <nsAd:CdgDGISucur>4</nsAd:CdgDGISucur>\n"
                    + "			          <nsAd:DomFiscal>18 de Julio 1393</nsAd:DomFiscal>\n"
                    + "			          <nsAd:Ciudad>PAYSANDU</nsAd:Ciudad>\n"
                    + "			          <nsAd:Departamento>PAYSANDU</nsAd:Departamento>\n"
                    + "			        </nsAd:Emisor>\n"
                    + "			        <nsAd:Receptor>\n"
                    + "			          "
                    + "			          <nsAd:TipoDocRecep>3</nsAd:TipoDocRecep>\n"
                    + "			          <nsAd:CodPaisRecep>UY</nsAd:CodPaisRecep>\n"
                    + "			          <nsAd:DocRecep>" + ci + "</nsAd:DocRecep>\n"
                    + "			          <nsAd:RznSocRecep>" + nombre + "</nsAd:RznSocRecep>\n"
                    + "			          <nsAd:DirRecep>" + direccion + "</nsAd:DirRecep>\n"
                    + "			          <nsAd:CiudadRecep>Paysandu</nsAd:CiudadRecep>\n"
                    + "			          <nsAd:DeptoRecep>PAYSANDU</nsAd:DeptoRecep>\n"
                    + "			          <nsAd:PaisRecep>Uruguay</nsAd:PaisRecep>\n"
                    + "			        </nsAd:Receptor>\n"
                    + "			        <nsAd:Totales>\n"
                    + "			          <nsAd:TpoMoneda>UYU</nsAd:TpoMoneda>\n"
                    + "			          <nsAd:MntNoGrv>0</nsAd:MntNoGrv>\n"
                    + "			          <nsAd:MntNetoIvaTasaMin>0</nsAd:MntNetoIvaTasaMin>\n"
                    + "			          <nsAd:MntNetoIVATasaBasica>" + comision + "</nsAd:MntNetoIVATasaBasica>\n"
                    + "			          <nsAd:IVATasaMin>10</nsAd:IVATasaMin>\n"
                    + "			          <nsAd:IVATasaBasica>22</nsAd:IVATasaBasica>\n"
                    + "			          <nsAd:MntIVATasaMin>0</nsAd:MntIVATasaMin>\n"
                    + "			          <nsAd:MntIVATasaBasica>" + iva + "</nsAd:MntIVATasaBasica>\n"
                    + "			          <nsAd:MntTotal>" + stotalcondecimal + "</nsAd:MntTotal>\n"
                    + "			          <nsAd:CantLinDet>2</nsAd:CantLinDet>\n"
                    + "			          <nsAd:MontoNF>-" + partdecimal + "</nsAd:MontoNF>\n"
                    + "			          <nsAd:MntPagar>" + stotal1 + "</nsAd:MntPagar>\n"
                    + "			        </nsAd:Totales>\n"
                    + "			      </nsAd:Encabezado>\n"
                    + "			      <nsAd:Detalle>\n"
                    + "			        <nsAd:Item>\n"
                    + "			          <nsAd:NroLinDet>1</nsAd:NroLinDet>\n"
                    + "			          <nsAd:IndFact>3</nsAd:IndFact>\n"
                    + "			          <nsAd:NomItem>" + concepto + "</nsAd:NomItem>\n"
                    + "			          <nsAd:Cantidad>1.000</nsAd:Cantidad>\n"
                    + "			          <nsAd:UniMed>N/A</nsAd:UniMed>\n"
                    + "			          <nsAd:PrecioUnitario>" + stotalcondecimal + "</nsAd:PrecioUnitario>\n"
                    + "			          <nsAd:MontoItem>" + stotalcondecimal + "</nsAd:MontoItem>\n"
                    + "			        </nsAd:Item>"
                    + "<nsAd:Item>\n"
                    + "			          <nsAd:NroLinDet>2</nsAd:NroLinDet>\n"
                    + "			          <nsAd:IndFact>7</nsAd:IndFact>\n"
                    + "			          <nsAd:NomItem>Redondeo</nsAd:NomItem>\n"
                    + "			          <nsAd:Cantidad>1</nsAd:Cantidad>\n"
                    + "			          <nsAd:UniMed>N/A</nsAd:UniMed>\n"
                    + "			          <nsAd:PrecioUnitario>" + partdecimal + "</nsAd:PrecioUnitario>\n"
                    + "			          <nsAd:MontoItem>" + partdecimal + "</nsAd:MontoItem>\n"
                    + "			        </nsAd:Item>	"
                    + ""
                    + "		        \n"
                    + "			      </nsAd:Detalle>\n"
                    + "			      <nsAd:DscRcgGlobal>\n"
                    + "			      </nsAd:DscRcgGlobal>\n"
                    + "			      <nsAd:CAEData>\n"
                    + "			        <nsAd:CAE_ID>1</nsAd:CAE_ID>\n"
                    + "			        <nsAd:DNro>1</nsAd:DNro>\n"
                    + "			        <nsAd:HNro>1</nsAd:HNro>\n"
                    + "			        <nsAd:FecVenc></nsAd:FecVenc>\n"
                    + "			      </nsAd:CAEData>\n"
                    + "			    </nsAd:eTck>\n"
                    + "			  </nsAd:CFE>\n"
                    + "			  <nsAdenda:Adenda>"
                    + ""
                    + "<nsAdenda:AdendaSicfe>\n"
                    + "      <nsAdenda:IdCaja>N/A</nsAdenda:IdCaja>\n"
                    + "      <nsAdenda:IdUsuario>N/A</nsAdenda:IdUsuario>\n"
                    + "    </nsAdenda:AdendaSicfe>" + adenda + "</nsAdenda:Adenda>\n"
                    + "			</nsAdenda:CFE_Adenda>";
        } else {
            resultado = "<?xml version='1.0' encoding='UTF-8' ?><nsAdenda:CFE_Adenda xmlns:nsAdenda=\'http://cfe.dgi.gub.uy\'>\n"
                    + "			  <nsAd:CFE version=\'1.0\' xmlns:nsAd=\'http://cfe.dgi.gub.uy\'>\n"
                    + "			    <nsAd:eTck>\n"
                    + "			      <nsAd:Encabezado>\n"
                    + "			        <nsAd:IdDoc>\n"
                    + "			          <nsAd:TipoCFE>101</nsAd:TipoCFE>\n"
                    + "			          <nsAd:Serie>A</nsAd:Serie>\n"
                    + "			          <nsAd:Nro>2727</nsAd:Nro>\n"
                    + "			          <nsAd:FchEmis>" + fecha + "</nsAd:FchEmis>\n"
                    + "			          <nsAd:MntBruto>1</nsAd:MntBruto>\n"
                    + "			          <nsAd:FmaPago>1</nsAd:FmaPago>\n"
                    + "			        </nsAd:IdDoc>\n"
                    + "			        <nsAd:Emisor>\n"
                    + "			          <nsAd:RUCEmisor>120196190011</nsAd:RUCEmisor>\n"
                    + "			          <nsAd:RznSoc>Stagno Palermo Diego Fernando</nsAd:RznSoc>\n"
                    + "			          <nsAd:NomComercial>Stagno Palermo Diego Fernando</nsAd:NomComercial>\n"
                    + "			          <nsAd:EmiSucursal>Sucursal principal</nsAd:EmiSucursal>\n"
                    + "			          <nsAd:CdgDGISucur>4</nsAd:CdgDGISucur>\n"
                    + "			          <nsAd:DomFiscal>18 de Julio 1393</nsAd:DomFiscal>\n"
                    + "			          <nsAd:Ciudad>PAYSANDU</nsAd:Ciudad>\n"
                    + "			          <nsAd:Departamento>PAYSANDU</nsAd:Departamento>\n"
                    + "			        </nsAd:Emisor>\n"
                    + "			        <nsAd:Receptor>\n"
                    + "			          "
                    + "			          <nsAd:TipoDocRecep>3</nsAd:TipoDocRecep>\n"
                    + "			          <nsAd:CodPaisRecep>UY</nsAd:CodPaisRecep>\n"
                    + "			          <nsAd:DocRecep>" + ci + "</nsAd:DocRecep>\n"
                    + "			          <nsAd:RznSocRecep>" + nombre + "</nsAd:RznSocRecep>\n"
                    + "			          <nsAd:DirRecep>" + direccion + "</nsAd:DirRecep>\n"
                    + "			          <nsAd:CiudadRecep>Paysandu</nsAd:CiudadRecep>\n"
                    + "			          <nsAd:DeptoRecep>PAYSANDU</nsAd:DeptoRecep>\n"
                    + "			          <nsAd:PaisRecep>Uruguay</nsAd:PaisRecep>\n"
                    + "			        </nsAd:Receptor>\n"
                    + "			        <nsAd:Totales>\n"
                    + "			          <nsAd:TpoMoneda>UYU</nsAd:TpoMoneda>\n"
                    + "			          <nsAd:MntNoGrv>0</nsAd:MntNoGrv>\n"
                    + "			          <nsAd:MntNetoIvaTasaMin>0</nsAd:MntNetoIvaTasaMin>\n"
                    + "			          <nsAd:MntNetoIVATasaBasica>" + comision + "</nsAd:MntNetoIVATasaBasica>\n"
                    + "			          <nsAd:IVATasaMin>10</nsAd:IVATasaMin>\n"
                    + "			          <nsAd:IVATasaBasica>22</nsAd:IVATasaBasica>\n"
                    + "			          <nsAd:MntIVATasaMin>0</nsAd:MntIVATasaMin>\n"
                    + "			          <nsAd:MntIVATasaBasica>" + iva + "</nsAd:MntIVATasaBasica>\n"
                    + "			          <nsAd:MntTotal>" + stotalcondecimal + "</nsAd:MntTotal>\n"
                    + "			          <nsAd:CantLinDet>1</nsAd:CantLinDet>\n"
                    + "			          <nsAd:MontoNF>-" + partdecimal + "</nsAd:MontoNF>\n"
                    + "			          <nsAd:MntPagar>" + stotal1 + "</nsAd:MntPagar>\n"
                    + "			        </nsAd:Totales>\n"
                    + "			      </nsAd:Encabezado>\n"
                    + "			      <nsAd:Detalle>\n"
                    + "			        <nsAd:Item>\n"
                    + "			          <nsAd:NroLinDet>1</nsAd:NroLinDet>\n"
                    + "			          <nsAd:IndFact>3</nsAd:IndFact>\n"
                    + "			          <nsAd:NomItem>" + concepto + "</nsAd:NomItem>\n"
                    + "			          <nsAd:Cantidad>1.000</nsAd:Cantidad>\n"
                    + "			          <nsAd:UniMed>N/A</nsAd:UniMed>\n"
                    + "			          <nsAd:PrecioUnitario>" + stotalcondecimal + "</nsAd:PrecioUnitario>\n"
                    + "			          <nsAd:MontoItem>" + stotalcondecimal + "</nsAd:MontoItem>\n"
                    + "			        </nsAd:Item>"
                    + "	"
                    + ""
                    + "		        \n"
                    + "			      </nsAd:Detalle>\n"
                    + "			      <nsAd:DscRcgGlobal>\n"
                    + "			      </nsAd:DscRcgGlobal>\n"
                    + "			      <nsAd:CAEData>\n"
                    + "			        <nsAd:CAE_ID>1</nsAd:CAE_ID>\n"
                    + "			        <nsAd:DNro>1</nsAd:DNro>\n"
                    + "			        <nsAd:HNro>1</nsAd:HNro>\n"
                    + "			        <nsAd:FecVenc></nsAd:FecVenc>\n"
                    + "			      </nsAd:CAEData>\n"
                    + "			    </nsAd:eTck>\n"
                    + "			  </nsAd:CFE>\n"
                    + "			  <nsAdenda:Adenda>"
                    + ""
                    + "<nsAdenda:AdendaSicfe>\n"
                    + "      <nsAdenda:IdCaja>N/A</nsAdenda:IdCaja>\n"
                    + "      <nsAdenda:IdUsuario>N/A</nsAdenda:IdUsuario>\n"
                    + "    </nsAdenda:AdendaSicfe>" + adenda + "</nsAdenda:Adenda>\n"
                    + "			</nsAdenda:CFE_Adenda>";
        }

        return resultado;
    }

    public String cfexmlnotacreditoticket(d_movimiento mov, int numero) throws Exception {
        String fecha = formatearfechastring(mov.getFecha());
        d_propietario pro = new d_propietario();
        DecimalFormat df = new DecimalFormat("#.00");
        String resultado = "";

        pro = pro.buscarpropietario(mov.getProp_id());

        String scomision = "";
        String siva = "";

        scomision = df.format(mov.getComision());
        scomision = scomision.replace(".", "").replace(",", ".");

        siva = df.format(mov.getIva());
        siva = siva.replace(".", "").replace(",", ".");

        Float comision = Float.parseFloat(scomision);
        Float iva = Float.parseFloat(siva);

        Float total = comision + iva;
        //String stotal = total.toString();
        String stotal = df.format(total);
        stotal = stotal.replace(".", "").replace(",", ".");
        String stotalcondecimal = stotal;

        //stotal = stotal.replace(".", "").replace(",", ".");
        String separador = Pattern.quote(".");
        String[] parts = stotal.split(separador);
        //String partentera = parts[0]; // 123
        String partdecimal = "0." + parts[1]; // 654321

        Float fpartedecimal = Float.parseFloat(partdecimal);
        total = total - fpartedecimal;
        String stotal1 = df.format(total);
        stotal1 = stotal1.replace(".", "").replace(",", ".");

        String ceros = "";

        if (numero > 9) {
            ceros = "00000";
        } else {
            ceros = "000000";
        }

        if (partdecimal.equals("0.00")) {
            partdecimal = "0.0";
        }

        if (!partdecimal.equals("0.0")) {
            resultado = "<?xml version='1.0' encoding='UTF-8' ?><nsAdenda:CFE_Adenda xmlns:nsAdenda=\'http://cfe.dgi.gub.uy\'>\n"
                    + "			  <nsAd:CFE version=\'1.0\' xmlns:nsAd=\'http://cfe.dgi.gub.uy\'>\n"
                    + "			    <nsAd:eTck>\n"
                    + "			      <nsAd:Encabezado>\n"
                    + "			        <nsAd:IdDoc>\n"
                    + "			          <nsAd:TipoCFE>102</nsAd:TipoCFE>\n"
                    + "			          <nsAd:Serie>A</nsAd:Serie>\n"
                    + "			          <nsAd:Nro>2727</nsAd:Nro>\n"
                    + "			          <nsAd:FchEmis>" + fecha + "</nsAd:FchEmis>\n"
                    + "			          <nsAd:MntBruto>1</nsAd:MntBruto>\n"
                    + "			          <nsAd:FmaPago>1</nsAd:FmaPago>\n"
                    + "			        </nsAd:IdDoc>\n"
                    + "			        <nsAd:Emisor>\n"
                    + "			          <nsAd:RUCEmisor>120196190011</nsAd:RUCEmisor>\n"
                    + "			          <nsAd:RznSoc>Stagno Palermo Diego Fernando</nsAd:RznSoc>\n"
                    + "			          <nsAd:NomComercial>Stagno Palermo Diego Fernando</nsAd:NomComercial>\n"
                    + "			          <nsAd:EmiSucursal>Sucursal principal</nsAd:EmiSucursal>\n"
                    + "			          <nsAd:CdgDGISucur>4</nsAd:CdgDGISucur>\n"
                    + "			          <nsAd:DomFiscal>18 de Julio 1393</nsAd:DomFiscal>\n"
                    + "			          <nsAd:Ciudad>PAYSANDU</nsAd:Ciudad>\n"
                    + "			          <nsAd:Departamento>PAYSANDU</nsAd:Departamento>\n"
                    + "			        </nsAd:Emisor>\n"
                    + "			        <nsAd:Receptor>\n"
                    + "			          "
                    + "			          <nsAd:TipoDocRecep>3</nsAd:TipoDocRecep>\n"
                    + "			          <nsAd:CodPaisRecep>UY</nsAd:CodPaisRecep>\n"
                    + "			          <nsAd:DocRecep>" + pro.getProp_cirut() + "</nsAd:DocRecep>\n"
                    + "			          <nsAd:RznSocRecep>" + pro.getProp_nombre() + "</nsAd:RznSocRecep>\n"
                    + "			          <nsAd:DirRecep>" + pro.getProp_direccion() + "</nsAd:DirRecep>\n"
                    + "			          <nsAd:CiudadRecep>Paysandu</nsAd:CiudadRecep>\n"
                    + "			          <nsAd:DeptoRecep>PAYSANDU</nsAd:DeptoRecep>\n"
                    + "			          <nsAd:PaisRecep>Uruguay</nsAd:PaisRecep>\n"
                    + "			        </nsAd:Receptor>\n"
                    + "			        <nsAd:Totales>\n"
                    + "			          <nsAd:TpoMoneda>UYU</nsAd:TpoMoneda>\n"
                    + "			          <nsAd:MntNoGrv>0</nsAd:MntNoGrv>\n"
                    + "			          <nsAd:MntNetoIvaTasaMin>0</nsAd:MntNetoIvaTasaMin>\n"
                    + "			          <nsAd:MntNetoIVATasaBasica>" + comision + "</nsAd:MntNetoIVATasaBasica>\n"
                    + "			          <nsAd:IVATasaMin>10</nsAd:IVATasaMin>\n"
                    + "			          <nsAd:IVATasaBasica>22</nsAd:IVATasaBasica>\n"
                    + "			          <nsAd:MntIVATasaMin>0</nsAd:MntIVATasaMin>\n"
                    + "			          <nsAd:MntIVATasaBasica>" + iva + "</nsAd:MntIVATasaBasica>\n"
                    + "			          <nsAd:MntTotal>" + stotalcondecimal + "</nsAd:MntTotal>\n"
                    + "			          <nsAd:CantLinDet>2</nsAd:CantLinDet>\n"
                    + "			          <nsAd:MontoNF>-" + partdecimal + "</nsAd:MontoNF>\n"
                    + "			          <nsAd:MntPagar>" + stotal1 + "</nsAd:MntPagar>\n"
                    + "			        </nsAd:Totales>\n"
                    + "			      </nsAd:Encabezado>\n"
                    + "			      <nsAd:Detalle>\n"
                    + "			        <nsAd:Item>\n"
                    + "			          <nsAd:NroLinDet>1</nsAd:NroLinDet>\n"
                    + "			          <nsAd:IndFact>3</nsAd:IndFact>\n"
                    + "			          <nsAd:NomItem>" + mov.getDetalle() + ": " + mov.getMqp() + "/" + mov.getAqp() + "</nsAd:NomItem>\n"
                    + "			          <nsAd:Cantidad>1.000</nsAd:Cantidad>\n"
                    + "			          <nsAd:UniMed>N/A</nsAd:UniMed>\n"
                    + "			          <nsAd:PrecioUnitario>" + stotalcondecimal + "</nsAd:PrecioUnitario>\n"
                    + "			          <nsAd:MontoItem>" + stotalcondecimal + "</nsAd:MontoItem>\n"
                    + "			        </nsAd:Item>"
                    + "<nsAd:Item>\n"
                    + "			          <nsAd:NroLinDet>2</nsAd:NroLinDet>\n"
                    + "			          <nsAd:IndFact>7</nsAd:IndFact>\n"
                    + "			          <nsAd:NomItem>Redondeo</nsAd:NomItem>\n"
                    + "			          <nsAd:Cantidad>1</nsAd:Cantidad>\n"
                    + "			          <nsAd:UniMed>N/A</nsAd:UniMed>\n"
                    + "			          <nsAd:PrecioUnitario>" + partdecimal + "</nsAd:PrecioUnitario>\n"
                    + "			          <nsAd:MontoItem>" + partdecimal + "</nsAd:MontoItem>\n"
                    + "			        </nsAd:Item>"
                    + "	"
                    + ""
                    + "		        \n"
                    + "			      </nsAd:Detalle>\n"
                    + "			        \n"
                    + "			      <nsAd:DscRcgGlobal>\n"
                    + "			      </nsAd:DscRcgGlobal>"
                    + "<nsAd:Referencia>\n"
                    + "        <nsAd:Referencia>\n"
                    + "          <nsAd:NroLinRef>1</nsAd:NroLinRef>\n"
                    + "          <nsAd:TpoDocRef>101</nsAd:TpoDocRef>\n"
                    + "          <nsAd:Serie>A</nsAd:Serie>\n"
                    + "          <nsAd:NroCFERef>" + ceros + "" + numero + "</nsAd:NroCFERef>\n"
                    + "          <nsAd:FechaCFEref>" + fecha + "</nsAd:FechaCFEref>\n"
                    + "        </nsAd:Referencia>\n"
                    + "      </nsAd:Referencia>"
                    + ""
                    + "\n"
                    + "			      <nsAd:CAEData>\n"
                    + "			        <nsAd:CAE_ID>1</nsAd:CAE_ID>\n"
                    + "			        <nsAd:DNro>1</nsAd:DNro>\n"
                    + "			        <nsAd:HNro>1</nsAd:HNro>\n"
                    + "			        <nsAd:FecVenc></nsAd:FecVenc>\n"
                    + "			      </nsAd:CAEData>\n"
                    + "			    </nsAd:eTck>\n"
                    + "			  </nsAd:CFE>\n"
                    + "			  <nsAdenda:Adenda>"
                    + ""
                    + "<nsAdenda:AdendaSicfe>\n"
                    + "      <nsAdenda:IdCaja>N/A</nsAdenda:IdCaja>\n"
                    + "      <nsAdenda:IdUsuario>N/A</nsAdenda:IdUsuario>\n"
                    + "    </nsAdenda:AdendaSicfe>&lt;br&gt;&lt;br&gt;ID TRANSACC.:&lt;br&gt;" + mov.getId() + "&lt;/h5&gt;</nsAdenda:Adenda>\n"
                    + "			</nsAdenda:CFE_Adenda>";
        } else {
            resultado = "<?xml version='1.0' encoding='UTF-8' ?><nsAdenda:CFE_Adenda xmlns:nsAdenda=\'http://cfe.dgi.gub.uy\'>\n"
                    + "			  <nsAd:CFE version=\'1.0\' xmlns:nsAd=\'http://cfe.dgi.gub.uy\'>\n"
                    + "			    <nsAd:eTck>\n"
                    + "			      <nsAd:Encabezado>\n"
                    + "			        <nsAd:IdDoc>\n"
                    + "			          <nsAd:TipoCFE>102</nsAd:TipoCFE>\n"
                    + "			          <nsAd:Serie>A</nsAd:Serie>\n"
                    + "			          <nsAd:Nro>2727</nsAd:Nro>\n"
                    + "			          <nsAd:FchEmis>" + fecha + "</nsAd:FchEmis>\n"
                    + "			          <nsAd:MntBruto>1</nsAd:MntBruto>\n"
                    + "			          <nsAd:FmaPago>1</nsAd:FmaPago>\n"
                    + "			        </nsAd:IdDoc>\n"
                    + "			        <nsAd:Emisor>\n"
                    + "			          <nsAd:RUCEmisor>120196190011</nsAd:RUCEmisor>\n"
                    + "			          <nsAd:RznSoc>Stagno Palermo Diego Fernando</nsAd:RznSoc>\n"
                    + "			          <nsAd:NomComercial>Stagno Palermo Diego Fernando</nsAd:NomComercial>\n"
                    + "			          <nsAd:EmiSucursal>Sucursal principal</nsAd:EmiSucursal>\n"
                    + "			          <nsAd:CdgDGISucur>4</nsAd:CdgDGISucur>\n"
                    + "			          <nsAd:DomFiscal>18 de Julio 1393</nsAd:DomFiscal>\n"
                    + "			          <nsAd:Ciudad>PAYSANDU</nsAd:Ciudad>\n"
                    + "			          <nsAd:Departamento>PAYSANDU</nsAd:Departamento>\n"
                    + "			        </nsAd:Emisor>\n"
                    + "			        <nsAd:Receptor>\n"
                    + "			          "
                    + "			          <nsAd:TipoDocRecep>3</nsAd:TipoDocRecep>\n"
                    + "			          <nsAd:CodPaisRecep>UY</nsAd:CodPaisRecep>\n"
                    + "			          <nsAd:DocRecep>" + pro.getProp_cirut() + "</nsAd:DocRecep>\n"
                    + "			          <nsAd:RznSocRecep>" + pro.getProp_nombre() + "</nsAd:RznSocRecep>\n"
                    + "			          <nsAd:DirRecep>" + pro.getProp_direccion() + "</nsAd:DirRecep>\n"
                    + "			          <nsAd:CiudadRecep>Paysandu</nsAd:CiudadRecep>\n"
                    + "			          <nsAd:DeptoRecep>PAYSANDU</nsAd:DeptoRecep>\n"
                    + "			          <nsAd:PaisRecep>Uruguay</nsAd:PaisRecep>\n"
                    + "			        </nsAd:Receptor>\n"
                    + "			        <nsAd:Totales>\n"
                    + "			          <nsAd:TpoMoneda>UYU</nsAd:TpoMoneda>\n"
                    + "			          <nsAd:MntNoGrv>0</nsAd:MntNoGrv>\n"
                    + "			          <nsAd:MntNetoIvaTasaMin>0</nsAd:MntNetoIvaTasaMin>\n"
                    + "			          <nsAd:MntNetoIVATasaBasica>" + comision + "</nsAd:MntNetoIVATasaBasica>\n"
                    + "			          <nsAd:IVATasaMin>10</nsAd:IVATasaMin>\n"
                    + "			          <nsAd:IVATasaBasica>22</nsAd:IVATasaBasica>\n"
                    + "			          <nsAd:MntIVATasaMin>0</nsAd:MntIVATasaMin>\n"
                    + "			          <nsAd:MntIVATasaBasica>" + iva + "</nsAd:MntIVATasaBasica>\n"
                    + "			          <nsAd:MntTotal>" + stotalcondecimal + "</nsAd:MntTotal>\n"
                    + "			          <nsAd:CantLinDet>1</nsAd:CantLinDet>\n"
                    + "			          <nsAd:MontoNF>-" + partdecimal + "</nsAd:MontoNF>\n"
                    + "			          <nsAd:MntPagar>" + stotal1 + "</nsAd:MntPagar>\n"
                    + "			        </nsAd:Totales>\n"
                    + "			      </nsAd:Encabezado>\n"
                    + "			      <nsAd:Detalle>\n"
                    + "			        <nsAd:Item>\n"
                    + "			          <nsAd:NroLinDet>1</nsAd:NroLinDet>\n"
                    + "			          <nsAd:IndFact>3</nsAd:IndFact>\n"
                    + "			          <nsAd:NomItem>" + mov.getDetalle() + ": " + mov.getMqp() + "/" + mov.getAqp() + "</nsAd:NomItem>\n"
                    + "			          <nsAd:Cantidad>1.000</nsAd:Cantidad>\n"
                    + "			          <nsAd:UniMed>N/A</nsAd:UniMed>\n"
                    + "			          <nsAd:PrecioUnitario>" + stotalcondecimal + "</nsAd:PrecioUnitario>\n"
                    + "			          <nsAd:MontoItem>" + stotalcondecimal + "</nsAd:MontoItem>\n"
                    + "			        </nsAd:Item>"
                    + "	"
                    + ""
                    + "		        \n"
                    + "			      </nsAd:Detalle>\n"
                    + "			        \n"
                    + "			      <nsAd:DscRcgGlobal>\n"
                    + "			      </nsAd:DscRcgGlobal>"
                    + "<nsAd:Referencia>\n"
                    + "        <nsAd:Referencia>\n"
                    + "          <nsAd:NroLinRef>1</nsAd:NroLinRef>\n"
                    + "          <nsAd:TpoDocRef>101</nsAd:TpoDocRef>\n"
                    + "          <nsAd:Serie>A</nsAd:Serie>\n"
                    + "          <nsAd:NroCFERef>" + ceros + "" + numero + "</nsAd:NroCFERef>\n"
                    + "          <nsAd:FechaCFEref>" + fecha + "</nsAd:FechaCFEref>\n"
                    + "        </nsAd:Referencia>\n"
                    + "      </nsAd:Referencia>"
                    + ""
                    + "\n"
                    + "			      <nsAd:CAEData>\n"
                    + "			        <nsAd:CAE_ID>1</nsAd:CAE_ID>\n"
                    + "			        <nsAd:DNro>1</nsAd:DNro>\n"
                    + "			        <nsAd:HNro>1</nsAd:HNro>\n"
                    + "			        <nsAd:FecVenc></nsAd:FecVenc>\n"
                    + "			      </nsAd:CAEData>\n"
                    + "			    </nsAd:eTck>\n"
                    + "			  </nsAd:CFE>\n"
                    + "			  <nsAdenda:Adenda>"
                    + "<nsAdenda:AdendaSicfe>\n"
                    + "      <nsAdenda:IdCaja>N/A</nsAdenda:IdCaja>\n"
                    + "      <nsAdenda:IdUsuario>N/A</nsAdenda:IdUsuario>\n"
                    + "    </nsAdenda:AdendaSicfe>&lt;br&gt;&lt;br&gt;ID TRANSACC.:&lt;br&gt;" + mov.getId() + "&lt;/h5&gt;</nsAdenda:Adenda>\n"
                    + "			</nsAdenda:CFE_Adenda>";
        }

        return resultado;
    }

    public String cfexmlnotacreditofactura(d_movimiento mov, int numero) throws Exception {
        String fecha = formatearfechastring(mov.getFecha());
        d_propietario pro = new d_propietario();
        DecimalFormat df = new DecimalFormat("#.00");
        String resultado = "";
        d_propietario_dgi p = new d_propietario_dgi();

        pro = pro.buscarpropietario(mov.getProp_id());
        p = p.buscar(mov.getProp_id());

        if (p == null) {
            throw new Exception("no se puede generar la factura electronica, ya que el propietario no cuenta con ciudad, departamento y pais ingresados");
        }
        String scomision = "";
        String siva = "";

        scomision = df.format(mov.getComision());
        scomision = scomision.replace(".", "").replace(",", ".");

        siva = df.format(mov.getIva());
        siva = siva.replace(".", "").replace(",", ".");

        Float comision = Float.parseFloat(scomision);
        Float iva = Float.parseFloat(siva);

        Float total = comision + iva;
        //String stotal = total.toString();
        String stotal = df.format(total);
        stotal = stotal.replace(".", "").replace(",", ".");
        String stotalcondecimal = stotal;

        //stotal = stotal.replace(".", "").replace(",", ".");
        String separador = Pattern.quote(".");
        String[] parts = stotal.split(separador);
        //String partentera = parts[0]; // 123
        String partdecimal = "0." + parts[1]; // 654321

        Float fpartedecimal = Float.parseFloat(partdecimal);
        total = total - fpartedecimal;
        String stotal1 = df.format(total);
        stotal1 = stotal1.replace(".", "").replace(",", ".");

        String ceros = "";

        if (numero > 9) {
            ceros = "00000";
        } else {
            ceros = "000000";
        }

        if (partdecimal.equals("0.00")) {
            partdecimal = "0.0";
        }

        if (!partdecimal.equals("0.0")) {
            resultado = "<?xml version='1.0' encoding='UTF-8' ?><nsAdenda:CFE_Adenda xmlns:nsAdenda=\'http://cfe.dgi.gub.uy\'>\n"
                    + "			  <nsAd:CFE version=\'1.0\' xmlns:nsAd=\'http://cfe.dgi.gub.uy\'>\n"
                    + "			    <nsAd:eFact>\n"
                    + "			      <nsAd:Encabezado>\n"
                    + "			        <nsAd:IdDoc>\n"
                    + "			          <nsAd:TipoCFE>112</nsAd:TipoCFE>\n"
                    + "			          <nsAd:Serie>A</nsAd:Serie>\n"
                    + "			          <nsAd:Nro>2727</nsAd:Nro>\n"
                    + "			          <nsAd:FchEmis>" + fecha + "</nsAd:FchEmis>\n"
                    + "			          <nsAd:MntBruto>1</nsAd:MntBruto>\n"
                    + "			          <nsAd:FmaPago>1</nsAd:FmaPago>\n"
                    + "			        </nsAd:IdDoc>\n"
                    + "			        <nsAd:Emisor>\n"
                    + "			          <nsAd:RUCEmisor>120196190011</nsAd:RUCEmisor>\n"
                    + "			          <nsAd:RznSoc>Stagno Palermo Diego Fernando</nsAd:RznSoc>\n"
                    + "			          <nsAd:NomComercial>Stagno Palermo Diego Fernando</nsAd:NomComercial>\n"
                    + "			          <nsAd:EmiSucursal>Sucursal principal</nsAd:EmiSucursal>\n"
                    + "			          <nsAd:CdgDGISucur>4</nsAd:CdgDGISucur>\n"
                    + "			          <nsAd:DomFiscal>18 de Julio 1393</nsAd:DomFiscal>\n"
                    + "			          <nsAd:Ciudad>PAYSANDU</nsAd:Ciudad>\n"
                    + "			          <nsAd:Departamento>PAYSANDU</nsAd:Departamento>\n"
                    + "			        </nsAd:Emisor>\n"
                    + "			        <nsAd:Receptor>\n"
                    + "			          "
                    + "			          <nsAd:TipoDocRecep>2</nsAd:TipoDocRecep>\n"
                    + "			          <nsAd:CodPaisRecep>UY</nsAd:CodPaisRecep>\n"
                    + "			          <nsAd:DocRecep>" + pro.getProp_cirut() + "</nsAd:DocRecep>\n"
                    + "			          <nsAd:RznSocRecep>" + pro.getProp_nombre() + "</nsAd:RznSocRecep>\n"
                    + "			          <nsAd:DirRecep>" + pro.getProp_direccion() + "</nsAd:DirRecep>\n"
                    + "			          <nsAd:CiudadRecep>" + p.getCiudad() + "</nsAd:CiudadRecep>\n"
                    + "			          <nsAd:DeptoRecep>" + p.getDepartamento() + "</nsAd:DeptoRecep>\n"
                    + "			          <nsAd:PaisRecep>" + p.getPais() + "</nsAd:PaisRecep>\n"
                    + "			        </nsAd:Receptor>\n"
                    + "			        <nsAd:Totales>\n"
                    + "			          <nsAd:TpoMoneda>UYU</nsAd:TpoMoneda>\n"
                    + "			          <nsAd:MntNoGrv>0</nsAd:MntNoGrv>\n"
                    + "			          <nsAd:MntNetoIvaTasaMin>0</nsAd:MntNetoIvaTasaMin>\n"
                    + "			          <nsAd:MntNetoIVATasaBasica>" + comision + "</nsAd:MntNetoIVATasaBasica>\n"
                    + "			          <nsAd:IVATasaMin>10</nsAd:IVATasaMin>\n"
                    + "			          <nsAd:IVATasaBasica>22</nsAd:IVATasaBasica>\n"
                    + "			          <nsAd:MntIVATasaMin>0</nsAd:MntIVATasaMin>\n"
                    + "			          <nsAd:MntIVATasaBasica>" + iva + "</nsAd:MntIVATasaBasica>\n"
                    + "			          <nsAd:MntTotal>" + stotalcondecimal + "</nsAd:MntTotal>\n"
                    + "			          <nsAd:CantLinDet>2</nsAd:CantLinDet>\n"
                    + "			          <nsAd:MontoNF>-" + partdecimal + "</nsAd:MontoNF>\n"
                    + "			          <nsAd:MntPagar>" + stotal1 + "</nsAd:MntPagar>\n"
                    + "			        </nsAd:Totales>\n"
                    + "			      </nsAd:Encabezado>\n"
                    + "			      <nsAd:Detalle>\n"
                    + "			        <nsAd:Item>\n"
                    + "			          <nsAd:NroLinDet>1</nsAd:NroLinDet>\n"
                    + "			          <nsAd:IndFact>3</nsAd:IndFact>\n"
                    + "			          <nsAd:NomItem>" + mov.getDetalle() + ": " + mov.getMqp() + "/" + mov.getAqp() + "</nsAd:NomItem>\n"
                    + "			          <nsAd:Cantidad>1.000</nsAd:Cantidad>\n"
                    + "			          <nsAd:UniMed>N/A</nsAd:UniMed>\n"
                    + "			          <nsAd:PrecioUnitario>" + stotalcondecimal + "</nsAd:PrecioUnitario>\n"
                    + "			          <nsAd:MontoItem>" + stotalcondecimal + "</nsAd:MontoItem>\n"
                    + "			        </nsAd:Item>"
                    + "<nsAd:Item>\n"
                    + "			          <nsAd:NroLinDet>2</nsAd:NroLinDet>\n"
                    + "			          <nsAd:IndFact>7</nsAd:IndFact>\n"
                    + "			          <nsAd:NomItem>Redondeo</nsAd:NomItem>\n"
                    + "			          <nsAd:Cantidad>1</nsAd:Cantidad>\n"
                    + "			          <nsAd:UniMed>N/A</nsAd:UniMed>\n"
                    + "			          <nsAd:PrecioUnitario>" + partdecimal + "</nsAd:PrecioUnitario>\n"
                    + "			          <nsAd:MontoItem>" + partdecimal + "</nsAd:MontoItem>\n"
                    + "			        </nsAd:Item>"
                    + "	"
                    + ""
                    + "		        \n"
                    + "			      </nsAd:Detalle>\n"
                    + "			        \n"
                    + "			      <nsAd:DscRcgGlobal>\n"
                    + "			      </nsAd:DscRcgGlobal>"
                    + "<nsAd:Referencia>\n"
                    + "        <nsAd:Referencia>\n"
                    + "          <nsAd:NroLinRef>1</nsAd:NroLinRef>\n"
                    + "          <nsAd:TpoDocRef>111</nsAd:TpoDocRef>\n"
                    + "          <nsAd:Serie>A</nsAd:Serie>\n"
                    + "          <nsAd:NroCFERef>" + ceros + "" + numero + "</nsAd:NroCFERef>\n"
                    + "          <nsAd:FechaCFEref>" + fecha + "</nsAd:FechaCFEref>\n"
                    + "        </nsAd:Referencia>\n"
                    + "      </nsAd:Referencia>"
                    + ""
                    + "\n"
                    + "			      <nsAd:CAEData>\n"
                    + "			        <nsAd:CAE_ID>1</nsAd:CAE_ID>\n"
                    + "			        <nsAd:DNro>1</nsAd:DNro>\n"
                    + "			        <nsAd:HNro>1</nsAd:HNro>\n"
                    + "			        <nsAd:FecVenc></nsAd:FecVenc>\n"
                    + "			      </nsAd:CAEData>\n"
                    + "			    </nsAd:eFact>\n"
                    + "			  </nsAd:CFE>\n"
                    + "			  <nsAdenda:Adenda>"
                    + "<nsAdenda:AdendaSicfe>\n"
                    + "      <nsAdenda:IdCaja>N/A</nsAdenda:IdCaja>\n"
                    + "      <nsAdenda:IdUsuario>N/A</nsAdenda:IdUsuario>\n"
                    + "    </nsAdenda:AdendaSicfe>&lt;br&gt;&lt;br&gt;ID TRANSACC.:&lt;br&gt;" + mov.getId() + "&lt;/h5&gt;</nsAdenda:Adenda>\n"
                    + "			</nsAdenda:CFE_Adenda>";
        } else {
            resultado = "<?xml version='1.0' encoding='UTF-8' ?><nsAdenda:CFE_Adenda xmlns:nsAdenda=\'http://cfe.dgi.gub.uy\'>\n"
                    + "			  <nsAd:CFE version=\'1.0\' xmlns:nsAd=\'http://cfe.dgi.gub.uy\'>\n"
                    + "			    <nsAd:eFact>\n"
                    + "			      <nsAd:Encabezado>\n"
                    + "			        <nsAd:IdDoc>\n"
                    + "			          <nsAd:TipoCFE>112</nsAd:TipoCFE>\n"
                    + "			          <nsAd:Serie>A</nsAd:Serie>\n"
                    + "			          <nsAd:Nro>2727</nsAd:Nro>\n"
                    + "			          <nsAd:FchEmis>" + fecha + "</nsAd:FchEmis>\n"
                    + "			          <nsAd:MntBruto>1</nsAd:MntBruto>\n"
                    + "			          <nsAd:FmaPago>1</nsAd:FmaPago>\n"
                    + "			        </nsAd:IdDoc>\n"
                    + "			        <nsAd:Emisor>\n"
                    + "			          <nsAd:RUCEmisor>120196190011</nsAd:RUCEmisor>\n"
                    + "			          <nsAd:RznSoc>Stagno Palermo Diego Fernando</nsAd:RznSoc>\n"
                    + "			          <nsAd:NomComercial>Stagno Palermo Diego Fernando</nsAd:NomComercial>\n"
                    + "			          <nsAd:EmiSucursal>Sucursal principal</nsAd:EmiSucursal>\n"
                    + "			          <nsAd:CdgDGISucur>4</nsAd:CdgDGISucur>\n"
                    + "			          <nsAd:DomFiscal>18 de Julio 1393</nsAd:DomFiscal>\n"
                    + "			          <nsAd:Ciudad>PAYSANDU</nsAd:Ciudad>\n"
                    + "			          <nsAd:Departamento>PAYSANDU</nsAd:Departamento>\n"
                    + "			        </nsAd:Emisor>\n"
                    + "			        <nsAd:Receptor>\n"
                    + "			          "
                    + "			          <nsAd:TipoDocRecep>2</nsAd:TipoDocRecep>\n"
                    + "			          <nsAd:CodPaisRecep>UY</nsAd:CodPaisRecep>\n"
                    + "			          <nsAd:DocRecep>" + pro.getProp_cirut() + "</nsAd:DocRecep>\n"
                    + "			          <nsAd:RznSocRecep>" + pro.getProp_nombre() + "</nsAd:RznSocRecep>\n"
                    + "			          <nsAd:DirRecep>" + pro.getProp_direccion() + "</nsAd:DirRecep>\n"
                    + "			          <nsAd:CiudadRecep>"+p.getCiudad()+"</nsAd:CiudadRecep>\n"
                    + "			          <nsAd:DeptoRecep>"+p.getDepartamento()+"</nsAd:DeptoRecep>\n"
                    + "			          <nsAd:PaisRecep>"+p.getPais()+"</nsAd:PaisRecep>\n"
                    + "			        </nsAd:Receptor>\n"
                    + "			        <nsAd:Totales>\n"
                    + "			          <nsAd:TpoMoneda>UYU</nsAd:TpoMoneda>\n"
                    + "			          <nsAd:MntNoGrv>0</nsAd:MntNoGrv>\n"
                    + "			          <nsAd:MntNetoIvaTasaMin>0</nsAd:MntNetoIvaTasaMin>\n"
                    + "			          <nsAd:MntNetoIVATasaBasica>" + comision + "</nsAd:MntNetoIVATasaBasica>\n"
                    + "			          <nsAd:IVATasaMin>10</nsAd:IVATasaMin>\n"
                    + "			          <nsAd:IVATasaBasica>22</nsAd:IVATasaBasica>\n"
                    + "			          <nsAd:MntIVATasaMin>0</nsAd:MntIVATasaMin>\n"
                    + "			          <nsAd:MntIVATasaBasica>" + iva + "</nsAd:MntIVATasaBasica>\n"
                    + "			          <nsAd:MntTotal>" + stotalcondecimal + "</nsAd:MntTotal>\n"
                    + "			          <nsAd:CantLinDet>1</nsAd:CantLinDet>\n"
                    + "			          <nsAd:MontoNF>-" + partdecimal + "</nsAd:MontoNF>\n"
                    + "			          <nsAd:MntPagar>" + stotal1 + "</nsAd:MntPagar>\n"
                    + "			        </nsAd:Totales>\n"
                    + "			      </nsAd:Encabezado>\n"
                    + "			      <nsAd:Detalle>\n"
                    + "			        <nsAd:Item>\n"
                    + "			          <nsAd:NroLinDet>1</nsAd:NroLinDet>\n"
                    + "			          <nsAd:IndFact>3</nsAd:IndFact>\n"
                    + "			          <nsAd:NomItem>" + mov.getDetalle() + ": " + mov.getMqp() + "/" + mov.getAqp() + "</nsAd:NomItem>\n"
                    + "			          <nsAd:Cantidad>1.000</nsAd:Cantidad>\n"
                    + "			          <nsAd:UniMed>N/A</nsAd:UniMed>\n"
                    + "			          <nsAd:PrecioUnitario>" + stotalcondecimal + "</nsAd:PrecioUnitario>\n"
                    + "			          <nsAd:MontoItem>" + stotalcondecimal + "</nsAd:MontoItem>\n"
                    + "			        </nsAd:Item>"
                    + "	"
                    + ""
                    + "		        \n"
                    + "			      </nsAd:Detalle>\n"
                    + "			        \n"
                    + "			      <nsAd:DscRcgGlobal>\n"
                    + "			      </nsAd:DscRcgGlobal>"
                    + "<nsAd:Referencia>\n"
                    + "        <nsAd:Referencia>\n"
                    + "          <nsAd:NroLinRef>1</nsAd:NroLinRef>\n"
                    + "          <nsAd:TpoDocRef>111</nsAd:TpoDocRef>\n"
                    + "          <nsAd:Serie>A</nsAd:Serie>\n"
                    + "          <nsAd:NroCFERef>" + ceros + "" + numero + "</nsAd:NroCFERef>\n"
                    + "          <nsAd:FechaCFEref>" + fecha + "</nsAd:FechaCFEref>\n"
                    + "        </nsAd:Referencia>\n"
                    + "      </nsAd:Referencia>"
                    + ""
                    + "\n"
                    + "			      <nsAd:CAEData>\n"
                    + "			        <nsAd:CAE_ID>1</nsAd:CAE_ID>\n"
                    + "			        <nsAd:DNro>1</nsAd:DNro>\n"
                    + "			        <nsAd:HNro>1</nsAd:HNro>\n"
                    + "			        <nsAd:FecVenc></nsAd:FecVenc>\n"
                    + "			      </nsAd:CAEData>\n"
                    + "			    </nsAd:eFact>\n"
                    + "			  </nsAd:CFE>\n"
                    + "			  <nsAdenda:Adenda>"
                    + "<nsAdenda:AdendaSicfe>\n"
                    + "      <nsAdenda:IdCaja>N/A</nsAdenda:IdCaja>\n"
                    + "      <nsAdenda:IdUsuario>N/A</nsAdenda:IdUsuario>\n"
                    + "    </nsAdenda:AdendaSicfe>&lt;br&gt;&lt;br&gt;ID TRANSACC.:&lt;br&gt;" + mov.getId() + "&lt;/h5&gt;</nsAdenda:Adenda>\n"
                    + "			</nsAdenda:CFE_Adenda>";
        }

        return resultado;
    }

    public String cfexmlnotacreditofactura_emitida(String nombre, String rut, String direccion, String ciudad, String departamento, String pais, String concepto, Float total_recibido, Float iva_recibido, String adenda, Date fecha_recibida, int numero) throws Exception {

        String fecha = formatearfechastring(fecha_recibida);
        DecimalFormat df = new DecimalFormat("#.00");
        String resultado = "";

        String scomision = "";
        String siva = "";

        scomision = df.format(total_recibido);
        scomision = scomision.replace(".", "").replace(",", ".");

        siva = df.format(iva_recibido);
        siva = siva.replace(".", "").replace(",", ".");

        Float comision = Float.parseFloat(scomision);
        Float iva = Float.parseFloat(siva);

        Float total = comision + iva;
        //String stotal = total.toString();
        String stotal = df.format(total);
        stotal = stotal.replace(".", "").replace(",", ".");
        String stotalcondecimal = stotal;

        //stotal = stotal.replace(".", "").replace(",", ".");
        String separador = Pattern.quote(".");
        String[] parts = stotal.split(separador);
        //String partentera = parts[0]; // 123
        String partdecimal = "0." + parts[1]; // 654321

        Float fpartedecimal = Float.parseFloat(partdecimal);
        total = total - fpartedecimal;
        String stotal1 = df.format(total);
        stotal1 = stotal1.replace(".", "").replace(",", ".");

        String ceros = "";

        if (numero > 9) {
            ceros = "00000";
        } else {
            ceros = "000000";
        }

        if (partdecimal.equals("0.00")) {
            partdecimal = "0.0";
        }

        if (!partdecimal.equals("0.0")) {
            resultado = "<?xml version='1.0' encoding='UTF-8' ?><nsAdenda:CFE_Adenda xmlns:nsAdenda=\'http://cfe.dgi.gub.uy\'>\n"
                    + "			  <nsAd:CFE version=\'1.0\' xmlns:nsAd=\'http://cfe.dgi.gub.uy\'>\n"
                    + "			    <nsAd:eFact>\n"
                    + "			      <nsAd:Encabezado>\n"
                    + "			        <nsAd:IdDoc>\n"
                    + "			          <nsAd:TipoCFE>112</nsAd:TipoCFE>\n"
                    + "			          <nsAd:Serie>A</nsAd:Serie>\n"
                    + "			          <nsAd:Nro>2727</nsAd:Nro>\n"
                    + "			          <nsAd:FchEmis>" + fecha + "</nsAd:FchEmis>\n"
                    + "			          <nsAd:MntBruto>1</nsAd:MntBruto>\n"
                    + "			          <nsAd:FmaPago>1</nsAd:FmaPago>\n"
                    + "			        </nsAd:IdDoc>\n"
                    + "			        <nsAd:Emisor>\n"
                    + "			          <nsAd:RUCEmisor>120196190011</nsAd:RUCEmisor>\n"
                    + "			          <nsAd:RznSoc>Stagno Palermo Diego Fernando</nsAd:RznSoc>\n"
                    + "			          <nsAd:NomComercial>Stagno Palermo Diego Fernando</nsAd:NomComercial>\n"
                    + "			          <nsAd:EmiSucursal>Sucursal principal</nsAd:EmiSucursal>\n"
                    + "			          <nsAd:CdgDGISucur>4</nsAd:CdgDGISucur>\n"
                    + "			          <nsAd:DomFiscal>18 de Julio 1393</nsAd:DomFiscal>\n"
                    + "			          <nsAd:Ciudad>PAYSANDU</nsAd:Ciudad>\n"
                    + "			          <nsAd:Departamento>PAYSANDU</nsAd:Departamento>\n"
                    + "			        </nsAd:Emisor>\n"
                    + "			        <nsAd:Receptor>\n"
                    + "			          "
                    + "			          <nsAd:TipoDocRecep>2</nsAd:TipoDocRecep>\n"
                    + "			          <nsAd:CodPaisRecep>UY</nsAd:CodPaisRecep>\n"
                    + "			          <nsAd:DocRecep>" + rut + "</nsAd:DocRecep>\n"
                    + "			          <nsAd:RznSocRecep>" + nombre + "</nsAd:RznSocRecep>\n"
                    + "			          <nsAd:DirRecep>" + direccion + "</nsAd:DirRecep>\n"
                    + "			          <nsAd:CiudadRecep>" + ciudad + "</nsAd:CiudadRecep>\n"
                    + "			          <nsAd:DeptoRecep>" + departamento + "</nsAd:DeptoRecep>\n"
                    + "			          <nsAd:PaisRecep>" + pais + "</nsAd:PaisRecep>\n"
                    + "			        </nsAd:Receptor>\n"
                    + "			        <nsAd:Totales>\n"
                    + "			          <nsAd:TpoMoneda>UYU</nsAd:TpoMoneda>\n"
                    + "			          <nsAd:MntNoGrv>0</nsAd:MntNoGrv>\n"
                    + "			          <nsAd:MntNetoIvaTasaMin>0</nsAd:MntNetoIvaTasaMin>\n"
                    + "			          <nsAd:MntNetoIVATasaBasica>" + comision + "</nsAd:MntNetoIVATasaBasica>\n"
                    + "			          <nsAd:IVATasaMin>10</nsAd:IVATasaMin>\n"
                    + "			          <nsAd:IVATasaBasica>22</nsAd:IVATasaBasica>\n"
                    + "			          <nsAd:MntIVATasaMin>0</nsAd:MntIVATasaMin>\n"
                    + "			          <nsAd:MntIVATasaBasica>" + iva + "</nsAd:MntIVATasaBasica>\n"
                    + "			          <nsAd:MntTotal>" + stotalcondecimal + "</nsAd:MntTotal>\n"
                    + "			          <nsAd:CantLinDet>2</nsAd:CantLinDet>\n"
                    + "			          <nsAd:MontoNF>-" + partdecimal + "</nsAd:MontoNF>\n"
                    + "			          <nsAd:MntPagar>" + stotal1 + "</nsAd:MntPagar>\n"
                    + "			        </nsAd:Totales>\n"
                    + "			      </nsAd:Encabezado>\n"
                    + "			      <nsAd:Detalle>\n"
                    + "			        <nsAd:Item>\n"
                    + "			          <nsAd:NroLinDet>1</nsAd:NroLinDet>\n"
                    + "			          <nsAd:IndFact>3</nsAd:IndFact>\n"
                    + "			          <nsAd:NomItem>" + concepto + "</nsAd:NomItem>\n"
                    + "			          <nsAd:Cantidad>1.000</nsAd:Cantidad>\n"
                    + "			          <nsAd:UniMed>N/A</nsAd:UniMed>\n"
                    + "			          <nsAd:PrecioUnitario>" + stotalcondecimal + "</nsAd:PrecioUnitario>\n"
                    + "			          <nsAd:MontoItem>" + stotalcondecimal + "</nsAd:MontoItem>\n"
                    + "			        </nsAd:Item>"
                    + "<nsAd:Item>\n"
                    + "			          <nsAd:NroLinDet>2</nsAd:NroLinDet>\n"
                    + "			          <nsAd:IndFact>7</nsAd:IndFact>\n"
                    + "			          <nsAd:NomItem>Redondeo</nsAd:NomItem>\n"
                    + "			          <nsAd:Cantidad>1</nsAd:Cantidad>\n"
                    + "			          <nsAd:UniMed>N/A</nsAd:UniMed>\n"
                    + "			          <nsAd:PrecioUnitario>" + partdecimal + "</nsAd:PrecioUnitario>\n"
                    + "			          <nsAd:MontoItem>" + partdecimal + "</nsAd:MontoItem>\n"
                    + "			        </nsAd:Item>"
                    + "	"
                    + ""
                    + "		        \n"
                    + "			      </nsAd:Detalle>\n"
                    + "			        \n"
                    + "			      <nsAd:DscRcgGlobal>\n"
                    + "			      </nsAd:DscRcgGlobal>"
                    + "<nsAd:Referencia>\n"
                    + "        <nsAd:Referencia>\n"
                    + "          <nsAd:NroLinRef>1</nsAd:NroLinRef>\n"
                    + "          <nsAd:TpoDocRef>111</nsAd:TpoDocRef>\n"
                    + "          <nsAd:Serie>A</nsAd:Serie>\n"
                    + "          <nsAd:NroCFERef>" + ceros + "" + numero + "</nsAd:NroCFERef>\n"
                    + "          <nsAd:FechaCFEref>" + fecha + "</nsAd:FechaCFEref>\n"
                    + "        </nsAd:Referencia>\n"
                    + "      </nsAd:Referencia>"
                    + ""
                    + "\n"
                    + "			      <nsAd:CAEData>\n"
                    + "			        <nsAd:CAE_ID>1</nsAd:CAE_ID>\n"
                    + "			        <nsAd:DNro>1</nsAd:DNro>\n"
                    + "			        <nsAd:HNro>1</nsAd:HNro>\n"
                    + "			        <nsAd:FecVenc></nsAd:FecVenc>\n"
                    + "			      </nsAd:CAEData>\n"
                    + "			    </nsAd:eFact>\n"
                    + "			  </nsAd:CFE>\n"
                    + "			  <nsAdenda:Adenda>"
                    + "<nsAdenda:AdendaSicfe>\n"
                    + "      <nsAdenda:IdCaja>N/A</nsAdenda:IdCaja>\n"
                    + "      <nsAdenda:IdUsuario>N/A</nsAdenda:IdUsuario>\n"
                    + "    </nsAdenda:AdendaSicfe>" + adenda + "</nsAdenda:Adenda>\n"
                    + "			</nsAdenda:CFE_Adenda>";
        } else {
            resultado = "<?xml version='1.0' encoding='UTF-8' ?><nsAdenda:CFE_Adenda xmlns:nsAdenda=\'http://cfe.dgi.gub.uy\'>\n"
                    + "			  <nsAd:CFE version=\'1.0\' xmlns:nsAd=\'http://cfe.dgi.gub.uy\'>\n"
                    + "			    <nsAd:eFact>\n"
                    + "			      <nsAd:Encabezado>\n"
                    + "			        <nsAd:IdDoc>\n"
                    + "			          <nsAd:TipoCFE>112</nsAd:TipoCFE>\n"
                    + "			          <nsAd:Serie>A</nsAd:Serie>\n"
                    + "			          <nsAd:Nro>2727</nsAd:Nro>\n"
                    + "			          <nsAd:FchEmis>" + fecha + "</nsAd:FchEmis>\n"
                    + "			          <nsAd:MntBruto>1</nsAd:MntBruto>\n"
                    + "			          <nsAd:FmaPago>1</nsAd:FmaPago>\n"
                    + "			        </nsAd:IdDoc>\n"
                    + "			        <nsAd:Emisor>\n"
                    + "			          <nsAd:RUCEmisor>120196190011</nsAd:RUCEmisor>\n"
                    + "			          <nsAd:RznSoc>Stagno Palermo Diego Fernando</nsAd:RznSoc>\n"
                    + "			          <nsAd:NomComercial>Stagno Palermo Diego Fernando</nsAd:NomComercial>\n"
                    + "			          <nsAd:EmiSucursal>Sucursal principal</nsAd:EmiSucursal>\n"
                    + "			          <nsAd:CdgDGISucur>4</nsAd:CdgDGISucur>\n"
                    + "			          <nsAd:DomFiscal>18 de Julio 1393</nsAd:DomFiscal>\n"
                    + "			          <nsAd:Ciudad>PAYSANDU</nsAd:Ciudad>\n"
                    + "			          <nsAd:Departamento>PAYSANDU</nsAd:Departamento>\n"
                    + "			        </nsAd:Emisor>\n"
                    + "			        <nsAd:Receptor>\n"
                    + "			          "
                    + "			          <nsAd:TipoDocRecep>2</nsAd:TipoDocRecep>\n"
                    + "			          <nsAd:CodPaisRecep>UY</nsAd:CodPaisRecep>\n"
                    + "			          <nsAd:DocRecep>" + rut + "</nsAd:DocRecep>\n"
                    + "			          <nsAd:RznSocRecep>" + nombre + "</nsAd:RznSocRecep>\n"
                    + "			          <nsAd:DirRecep>" + direccion + "</nsAd:DirRecep>\n"
                    + "			          <nsAd:CiudadRecep>"+ciudad+"</nsAd:CiudadRecep>\n"
                    + "			          <nsAd:DeptoRecep>"+departamento+"</nsAd:DeptoRecep>\n"
                    + "			          <nsAd:PaisRecep>"+pais+"</nsAd:PaisRecep>\n"
                    + "			        </nsAd:Receptor>\n"
                    + "			        <nsAd:Totales>\n"
                    + "			          <nsAd:TpoMoneda>UYU</nsAd:TpoMoneda>\n"
                    + "			          <nsAd:MntNoGrv>0</nsAd:MntNoGrv>\n"
                    + "			          <nsAd:MntNetoIvaTasaMin>0</nsAd:MntNetoIvaTasaMin>\n"
                    + "			          <nsAd:MntNetoIVATasaBasica>" + comision + "</nsAd:MntNetoIVATasaBasica>\n"
                    + "			          <nsAd:IVATasaMin>10</nsAd:IVATasaMin>\n"
                    + "			          <nsAd:IVATasaBasica>22</nsAd:IVATasaBasica>\n"
                    + "			          <nsAd:MntIVATasaMin>0</nsAd:MntIVATasaMin>\n"
                    + "			          <nsAd:MntIVATasaBasica>" + iva + "</nsAd:MntIVATasaBasica>\n"
                    + "			          <nsAd:MntTotal>" + stotalcondecimal + "</nsAd:MntTotal>\n"
                    + "			          <nsAd:CantLinDet>1</nsAd:CantLinDet>\n"
                    + "			          <nsAd:MontoNF>-" + partdecimal + "</nsAd:MontoNF>\n"
                    + "			          <nsAd:MntPagar>" + stotal1 + "</nsAd:MntPagar>\n"
                    + "			        </nsAd:Totales>\n"
                    + "			      </nsAd:Encabezado>\n"
                    + "			      <nsAd:Detalle>\n"
                    + "			        <nsAd:Item>\n"
                    + "			          <nsAd:NroLinDet>1</nsAd:NroLinDet>\n"
                    + "			          <nsAd:IndFact>3</nsAd:IndFact>\n"
                    + "			          <nsAd:NomItem>" + concepto + "</nsAd:NomItem>\n"
                    + "			          <nsAd:Cantidad>1.000</nsAd:Cantidad>\n"
                    + "			          <nsAd:UniMed>N/A</nsAd:UniMed>\n"
                    + "			          <nsAd:PrecioUnitario>" + stotalcondecimal + "</nsAd:PrecioUnitario>\n"
                    + "			          <nsAd:MontoItem>" + stotalcondecimal + "</nsAd:MontoItem>\n"
                    + "			        </nsAd:Item>"
                    + "	"
                    + ""
                    + "		        \n"
                    + "			      </nsAd:Detalle>\n"
                    + "			        \n"
                    + "			      <nsAd:DscRcgGlobal>\n"
                    + "			      </nsAd:DscRcgGlobal>"
                    + "<nsAd:Referencia>\n"
                    + "        <nsAd:Referencia>\n"
                    + "          <nsAd:NroLinRef>1</nsAd:NroLinRef>\n"
                    + "          <nsAd:TpoDocRef>111</nsAd:TpoDocRef>\n"
                    + "          <nsAd:Serie>A</nsAd:Serie>\n"
                    + "          <nsAd:NroCFERef>" + ceros + "" + numero + "</nsAd:NroCFERef>\n"
                    + "          <nsAd:FechaCFEref>" + fecha + "</nsAd:FechaCFEref>\n"
                    + "        </nsAd:Referencia>\n"
                    + "      </nsAd:Referencia>"
                    + ""
                    + "\n"
                    + "			      <nsAd:CAEData>\n"
                    + "			        <nsAd:CAE_ID>1</nsAd:CAE_ID>\n"
                    + "			        <nsAd:DNro>1</nsAd:DNro>\n"
                    + "			        <nsAd:HNro>1</nsAd:HNro>\n"
                    + "			        <nsAd:FecVenc></nsAd:FecVenc>\n"
                    + "			      </nsAd:CAEData>\n"
                    + "			    </nsAd:eFact>\n"
                    + "			  </nsAd:CFE>\n"
                    + "			  <nsAdenda:Adenda>"
                    + "<nsAdenda:AdendaSicfe>\n"
                    + "      <nsAdenda:IdCaja>N/A</nsAdenda:IdCaja>\n"
                    + "      <nsAdenda:IdUsuario>N/A</nsAdenda:IdUsuario>\n"
                    + "    </nsAdenda:AdendaSicfe>" + adenda + "</nsAdenda:Adenda>\n"
                    + "			</nsAdenda:CFE_Adenda>";
        }

        return resultado;
    }

    public String cfexmlnotacreditoeticket_emitida(String nombre, String ci, String direccion, String concepto, Float total_recibido, Float iva_recibido, String adenda, Date fecha_recibida, int numero) throws Exception {

        String fecha = formatearfechastring(fecha_recibida);
        DecimalFormat df = new DecimalFormat("#.00");
        String resultado = "";

        String scomision = "";
        String siva = "";

        scomision = df.format(total_recibido);
        scomision = scomision.replace(".", "").replace(",", ".");

        siva = df.format(iva_recibido);
        siva = siva.replace(".", "").replace(",", ".");

        Float comision = Float.parseFloat(scomision);
        Float iva = Float.parseFloat(siva);

        Float total = comision + iva;
        //String stotal = total.toString();
        String stotal = df.format(total);
        stotal = stotal.replace(".", "").replace(",", ".");
        String stotalcondecimal = stotal;

        //stotal = stotal.replace(".", "").replace(",", ".");
        String separador = Pattern.quote(".");
        String[] parts = stotal.split(separador);
        //String partentera = parts[0]; // 123
        String partdecimal = "0." + parts[1]; // 654321

        Float fpartedecimal = Float.parseFloat(partdecimal);
        total = total - fpartedecimal;
        String stotal1 = df.format(total);
        stotal1 = stotal1.replace(".", "").replace(",", ".");

        String ceros = "";

        if (numero > 9) {
            ceros = "00000";
        } else {
            ceros = "000000";
        }

        if (partdecimal.equals("0.00")) {
            partdecimal = "0.0";
        }

        if (!partdecimal.equals("0.0")) {
            resultado = "<?xml version='1.0' encoding='UTF-8' ?><nsAdenda:CFE_Adenda xmlns:nsAdenda=\'http://cfe.dgi.gub.uy\'>\n"
                    + "			  <nsAd:CFE version=\'1.0\' xmlns:nsAd=\'http://cfe.dgi.gub.uy\'>\n"
                    + "			    <nsAd:eTck>\n"
                    + "			      <nsAd:Encabezado>\n"
                    + "			        <nsAd:IdDoc>\n"
                    + "			          <nsAd:TipoCFE>102</nsAd:TipoCFE>\n"
                    + "			          <nsAd:Serie>A</nsAd:Serie>\n"
                    + "			          <nsAd:Nro>2727</nsAd:Nro>\n"
                    + "			          <nsAd:FchEmis>" + fecha + "</nsAd:FchEmis>\n"
                    + "			          <nsAd:MntBruto>1</nsAd:MntBruto>\n"
                    + "			          <nsAd:FmaPago>1</nsAd:FmaPago>\n"
                    + "			        </nsAd:IdDoc>\n"
                    + "			        <nsAd:Emisor>\n"
                    + "			          <nsAd:RUCEmisor>120196190011</nsAd:RUCEmisor>\n"
                    + "			          <nsAd:RznSoc>Stagno Palermo Diego Fernando</nsAd:RznSoc>\n"
                    + "			          <nsAd:NomComercial>Stagno Palermo Diego Fernando</nsAd:NomComercial>\n"
                    + "			          <nsAd:EmiSucursal>Sucursal principal</nsAd:EmiSucursal>\n"
                    + "			          <nsAd:CdgDGISucur>4</nsAd:CdgDGISucur>\n"
                    + "			          <nsAd:DomFiscal>18 de Julio 1393</nsAd:DomFiscal>\n"
                    + "			          <nsAd:Ciudad>PAYSANDU</nsAd:Ciudad>\n"
                    + "			          <nsAd:Departamento>PAYSANDU</nsAd:Departamento>\n"
                    + "			        </nsAd:Emisor>\n"
                    + "			        <nsAd:Receptor>\n"
                    + "			          "
                    + "			          <nsAd:TipoDocRecep>3</nsAd:TipoDocRecep>\n"
                    + "			          <nsAd:CodPaisRecep>UY</nsAd:CodPaisRecep>\n"
                    + "			          <nsAd:DocRecep>" + ci + "</nsAd:DocRecep>\n"
                    + "			          <nsAd:RznSocRecep>" + nombre + "</nsAd:RznSocRecep>\n"
                    + "			          <nsAd:DirRecep>" + direccion + "</nsAd:DirRecep>\n"
                    + "			          <nsAd:CiudadRecep>Paysandu</nsAd:CiudadRecep>\n"
                    + "			          <nsAd:DeptoRecep>PAYSANDU</nsAd:DeptoRecep>\n"
                    + "			          <nsAd:PaisRecep>Uruguay</nsAd:PaisRecep>\n"
                    + "			        </nsAd:Receptor>\n"
                    + "			        <nsAd:Totales>\n"
                    + "			          <nsAd:TpoMoneda>UYU</nsAd:TpoMoneda>\n"
                    + "			          <nsAd:MntNoGrv>0</nsAd:MntNoGrv>\n"
                    + "			          <nsAd:MntNetoIvaTasaMin>0</nsAd:MntNetoIvaTasaMin>\n"
                    + "			          <nsAd:MntNetoIVATasaBasica>" + comision + "</nsAd:MntNetoIVATasaBasica>\n"
                    + "			          <nsAd:IVATasaMin>10</nsAd:IVATasaMin>\n"
                    + "			          <nsAd:IVATasaBasica>22</nsAd:IVATasaBasica>\n"
                    + "			          <nsAd:MntIVATasaMin>0</nsAd:MntIVATasaMin>\n"
                    + "			          <nsAd:MntIVATasaBasica>" + iva + "</nsAd:MntIVATasaBasica>\n"
                    + "			          <nsAd:MntTotal>" + stotalcondecimal + "</nsAd:MntTotal>\n"
                    + "			          <nsAd:CantLinDet>2</nsAd:CantLinDet>\n"
                    + "			          <nsAd:MontoNF>-" + partdecimal + "</nsAd:MontoNF>\n"
                    + "			          <nsAd:MntPagar>" + stotal1 + "</nsAd:MntPagar>\n"
                    + "			        </nsAd:Totales>\n"
                    + "			      </nsAd:Encabezado>\n"
                    + "			      <nsAd:Detalle>\n"
                    + "			        <nsAd:Item>\n"
                    + "			          <nsAd:NroLinDet>1</nsAd:NroLinDet>\n"
                    + "			          <nsAd:IndFact>3</nsAd:IndFact>\n"
                    + "			          <nsAd:NomItem>" + concepto + "</nsAd:NomItem>\n"
                    + "			          <nsAd:Cantidad>1.000</nsAd:Cantidad>\n"
                    + "			          <nsAd:UniMed>N/A</nsAd:UniMed>\n"
                    + "			          <nsAd:PrecioUnitario>" + stotalcondecimal + "</nsAd:PrecioUnitario>\n"
                    + "			          <nsAd:MontoItem>" + stotalcondecimal + "</nsAd:MontoItem>\n"
                    + "			        </nsAd:Item>"
                    + "<nsAd:Item>\n"
                    + "			          <nsAd:NroLinDet>2</nsAd:NroLinDet>\n"
                    + "			          <nsAd:IndFact>7</nsAd:IndFact>\n"
                    + "			          <nsAd:NomItem>Redondeo</nsAd:NomItem>\n"
                    + "			          <nsAd:Cantidad>1</nsAd:Cantidad>\n"
                    + "			          <nsAd:UniMed>N/A</nsAd:UniMed>\n"
                    + "			          <nsAd:PrecioUnitario>" + partdecimal + "</nsAd:PrecioUnitario>\n"
                    + "			          <nsAd:MontoItem>" + partdecimal + "</nsAd:MontoItem>\n"
                    + "			        </nsAd:Item>"
                    + "	"
                    + ""
                    + "		        \n"
                    + "			      </nsAd:Detalle>\n"
                    + "			        \n"
                    + "			      <nsAd:DscRcgGlobal>\n"
                    + "			      </nsAd:DscRcgGlobal>"
                    + "<nsAd:Referencia>\n"
                    + "        <nsAd:Referencia>\n"
                    + "          <nsAd:NroLinRef>1</nsAd:NroLinRef>\n"
                    + "          <nsAd:TpoDocRef>101</nsAd:TpoDocRef>\n"
                    + "          <nsAd:Serie>A</nsAd:Serie>\n"
                    + "          <nsAd:NroCFERef>" + ceros + "" + numero + "</nsAd:NroCFERef>\n"
                    + "          <nsAd:FechaCFEref>" + fecha + "</nsAd:FechaCFEref>\n"
                    + "        </nsAd:Referencia>\n"
                    + "      </nsAd:Referencia>"
                    + ""
                    + "\n"
                    + "			      <nsAd:CAEData>\n"
                    + "			        <nsAd:CAE_ID>1</nsAd:CAE_ID>\n"
                    + "			        <nsAd:DNro>1</nsAd:DNro>\n"
                    + "			        <nsAd:HNro>1</nsAd:HNro>\n"
                    + "			        <nsAd:FecVenc></nsAd:FecVenc>\n"
                    + "			      </nsAd:CAEData>\n"
                    + "			    </nsAd:eTck>\n"
                    + "			  </nsAd:CFE>\n"
                    + "			  <nsAdenda:Adenda>"
                    + "<nsAdenda:AdendaSicfe>\n"
                    + "      <nsAdenda:IdCaja>N/A</nsAdenda:IdCaja>\n"
                    + "      <nsAdenda:IdUsuario>N/A</nsAdenda:IdUsuario>\n"
                    + "    </nsAdenda:AdendaSicfe>" + adenda + "</nsAdenda:Adenda>\n"
                    + "			</nsAdenda:CFE_Adenda>";
        } else {
            resultado = "<?xml version='1.0' encoding='UTF-8' ?><nsAdenda:CFE_Adenda xmlns:nsAdenda=\'http://cfe.dgi.gub.uy\'>\n"
                    + "			  <nsAd:CFE version=\'1.0\' xmlns:nsAd=\'http://cfe.dgi.gub.uy\'>\n"
                    + "			    <nsAd:eTck>\n"
                    + "			      <nsAd:Encabezado>\n"
                    + "			        <nsAd:IdDoc>\n"
                    + "			          <nsAd:TipoCFE>102</nsAd:TipoCFE>\n"
                    + "			          <nsAd:Serie>A</nsAd:Serie>\n"
                    + "			          <nsAd:Nro>2727</nsAd:Nro>\n"
                    + "			          <nsAd:FchEmis>" + fecha + "</nsAd:FchEmis>\n"
                    + "			          <nsAd:MntBruto>1</nsAd:MntBruto>\n"
                    + "			          <nsAd:FmaPago>1</nsAd:FmaPago>\n"
                    + "			        </nsAd:IdDoc>\n"
                    + "			        <nsAd:Emisor>\n"
                    + "			          <nsAd:RUCEmisor>120196190011</nsAd:RUCEmisor>\n"
                    + "			          <nsAd:RznSoc>Stagno Palermo Diego Fernando</nsAd:RznSoc>\n"
                    + "			          <nsAd:NomComercial>Stagno Palermo Diego Fernando</nsAd:NomComercial>\n"
                    + "			          <nsAd:EmiSucursal>Sucursal principal</nsAd:EmiSucursal>\n"
                    + "			          <nsAd:CdgDGISucur>4</nsAd:CdgDGISucur>\n"
                    + "			          <nsAd:DomFiscal>18 de Julio 1393</nsAd:DomFiscal>\n"
                    + "			          <nsAd:Ciudad>PAYSANDU</nsAd:Ciudad>\n"
                    + "			          <nsAd:Departamento>PAYSANDU</nsAd:Departamento>\n"
                    + "			        </nsAd:Emisor>\n"
                    + "			        <nsAd:Receptor>\n"
                    + "			          "
                    + "			          <nsAd:TipoDocRecep>3</nsAd:TipoDocRecep>\n"
                    + "			          <nsAd:CodPaisRecep>UY</nsAd:CodPaisRecep>\n"
                    + "			          <nsAd:DocRecep>" + ci + "</nsAd:DocRecep>\n"
                    + "			          <nsAd:RznSocRecep>" + nombre + "</nsAd:RznSocRecep>\n"
                    + "			          <nsAd:DirRecep>" + direccion + "</nsAd:DirRecep>\n"
                    + "			          <nsAd:CiudadRecep>Paysandu</nsAd:CiudadRecep>\n"
                    + "			          <nsAd:DeptoRecep>PAYSANDU</nsAd:DeptoRecep>\n"
                    + "			          <nsAd:PaisRecep>Uruguay</nsAd:PaisRecep>\n"
                    + "			        </nsAd:Receptor>\n"
                    + "			        <nsAd:Totales>\n"
                    + "			          <nsAd:TpoMoneda>UYU</nsAd:TpoMoneda>\n"
                    + "			          <nsAd:MntNoGrv>0</nsAd:MntNoGrv>\n"
                    + "			          <nsAd:MntNetoIvaTasaMin>0</nsAd:MntNetoIvaTasaMin>\n"
                    + "			          <nsAd:MntNetoIVATasaBasica>" + comision + "</nsAd:MntNetoIVATasaBasica>\n"
                    + "			          <nsAd:IVATasaMin>10</nsAd:IVATasaMin>\n"
                    + "			          <nsAd:IVATasaBasica>22</nsAd:IVATasaBasica>\n"
                    + "			          <nsAd:MntIVATasaMin>0</nsAd:MntIVATasaMin>\n"
                    + "			          <nsAd:MntIVATasaBasica>" + iva + "</nsAd:MntIVATasaBasica>\n"
                    + "			          <nsAd:MntTotal>" + stotalcondecimal + "</nsAd:MntTotal>\n"
                    + "			          <nsAd:CantLinDet>1</nsAd:CantLinDet>\n"
                    + "			          <nsAd:MontoNF>-" + partdecimal + "</nsAd:MontoNF>\n"
                    + "			          <nsAd:MntPagar>" + stotal1 + "</nsAd:MntPagar>\n"
                    + "			        </nsAd:Totales>\n"
                    + "			      </nsAd:Encabezado>\n"
                    + "			      <nsAd:Detalle>\n"
                    + "			        <nsAd:Item>\n"
                    + "			          <nsAd:NroLinDet>1</nsAd:NroLinDet>\n"
                    + "			          <nsAd:IndFact>3</nsAd:IndFact>\n"
                    + "			          <nsAd:NomItem>" + concepto + "</nsAd:NomItem>\n"
                    + "			          <nsAd:Cantidad>1.000</nsAd:Cantidad>\n"
                    + "			          <nsAd:UniMed>N/A</nsAd:UniMed>\n"
                    + "			          <nsAd:PrecioUnitario>" + stotalcondecimal + "</nsAd:PrecioUnitario>\n"
                    + "			          <nsAd:MontoItem>" + stotalcondecimal + "</nsAd:MontoItem>\n"
                    + "			        </nsAd:Item>"
                    + "	"
                    + ""
                    + "		        \n"
                    + "			      </nsAd:Detalle>\n"
                    + "			        \n"
                    + "			      <nsAd:DscRcgGlobal>\n"
                    + "			      </nsAd:DscRcgGlobal>"
                    + "<nsAd:Referencia>\n"
                    + "        <nsAd:Referencia>\n"
                    + "          <nsAd:NroLinRef>1</nsAd:NroLinRef>\n"
                    + "          <nsAd:TpoDocRef>101</nsAd:TpoDocRef>\n"
                    + "          <nsAd:Serie>A</nsAd:Serie>\n"
                    + "          <nsAd:NroCFERef>" + ceros + "" + numero + "</nsAd:NroCFERef>\n"
                    + "          <nsAd:FechaCFEref>" + fecha + "</nsAd:FechaCFEref>\n"
                    + "        </nsAd:Referencia>\n"
                    + "      </nsAd:Referencia>"
                    + ""
                    + "\n"
                    + "			      <nsAd:CAEData>\n"
                    + "			        <nsAd:CAE_ID>1</nsAd:CAE_ID>\n"
                    + "			        <nsAd:DNro>1</nsAd:DNro>\n"
                    + "			        <nsAd:HNro>1</nsAd:HNro>\n"
                    + "			        <nsAd:FecVenc></nsAd:FecVenc>\n"
                    + "			      </nsAd:CAEData>\n"
                    + "			    </nsAd:eTck>\n"
                    + "			  </nsAd:CFE>\n"
                    + "			  <nsAdenda:Adenda>"
                    + "<nsAdenda:AdendaSicfe>\n"
                    + "      <nsAdenda:IdCaja>N/A</nsAdenda:IdCaja>\n"
                    + "      <nsAdenda:IdUsuario>N/A</nsAdenda:IdUsuario>\n"
                    + "    </nsAdenda:AdendaSicfe>" + adenda + "</nsAdenda:Adenda>\n"
                    + "			</nsAdenda:CFE_Adenda>";
        }

        return resultado;
    }

    String formatearfechastring(java.util.Date fecha) {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        String fechastr = formato.format(fecha);

        return fechastr;
    }

    Date formatearfechaxml(String fecha) throws Exception {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
        Date fechaDate = null;

        try {
            fechaDate = formato.parse(fecha);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return fechaDate;
    }

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

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getCfexml() {
        return cfexml;
    }

    public void setCfexml(String cfexml) {
        this.cfexml = cfexml;
    }

    public String getReferenciaERP() {
        return referenciaERP;
    }

    public void setReferenciaERP(String referenciaERP) {
        this.referenciaERP = referenciaERP;
    }

    public String getReferenciaERP2() {
        return referenciaERP2;
    }

    public void setReferenciaERP2(String referenciaERP2) {
        this.referenciaERP2 = referenciaERP2;
    }

    public Boolean getDevolverQR() {
        return devolverQR;
    }

    public void setDevolverQR(Boolean devolverQR) {
        this.devolverQR = devolverQR;
    }

    public Integer getSizeQR() {
        return sizeQR;
    }

    public void setSizeQR(Integer sizeQR) {
        this.sizeQR = sizeQR;
    }

    public Integer getImprime() {
        return imprime;
    }

    public void setImprime(Integer imprime) {
        this.imprime = imprime;
    }

    public String getRecurso() {
        return recurso;
    }

    public void setRecurso(String recurso) {
        this.recurso = recurso;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public Boolean getDevolverXML() {
        return devolverXML;
    }

    public void setDevolverXML(Boolean devolverXML) {
        this.devolverXML = devolverXML;
    }

    public Boolean getErpPideValidacion() {
        return erpPideValidacion;
    }

    public void setErpPideValidacion(Boolean erpPideValidacion) {
        this.erpPideValidacion = erpPideValidacion;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

}
