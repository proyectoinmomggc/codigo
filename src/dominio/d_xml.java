package dominio;

import java.io.IOException;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author david
 */
public class d_xml {

    public String recibexmldevuelvetipo(String xml) throws Exception {
        try {
            DocumentBuilderFactory domFactory = DocumentBuilderFactory
                    .newInstance();
            domFactory.setNamespaceAware(true);
            DocumentBuilder builder = domFactory.newDocumentBuilder();
            Document doc = builder
                    .parse(new InputSource(new StringReader(xml)));

            NodeList todosContactos = doc.getElementsByTagName("ObtenerCFEPorReferenciaResult");
            Node unContacto = todosContactos.item(0);

            if (unContacto.getNodeType() == Node.ELEMENT_NODE) {
                Element elemento = (Element) unContacto;

                NodeList lista = elemento.getElementsByTagName("a:tipo")
                        .item(0).getChildNodes();
                Node valor = (Node) lista.item(0);
                if (valor == null) {
                    return "";
                }
                return (valor.getNodeValue());
            }
        } catch (IOException | ParserConfigurationException | DOMException | SAXException ex) {
            throw new Exception(ex.getMessage());
        }
        return "";
    }
    
    public String recibexmldevuelveserie(String xml) throws Exception {
        try {
            DocumentBuilderFactory domFactory = DocumentBuilderFactory
                    .newInstance();
            domFactory.setNamespaceAware(true);
            DocumentBuilder builder = domFactory.newDocumentBuilder();
            Document doc = builder
                    .parse(new InputSource(new StringReader(xml)));

            NodeList todosContactos = doc.getElementsByTagName("ObtenerCFEPorReferenciaResult");
            Node unContacto = todosContactos.item(0);

            if (unContacto.getNodeType() == Node.ELEMENT_NODE) {
                Element elemento = (Element) unContacto;

                NodeList lista = elemento.getElementsByTagName("a:serie")
                        .item(0).getChildNodes();
                Node valor = (Node) lista.item(0);
                if (valor == null) {
                    return "";
                }
                return (valor.getNodeValue());
            }
        } catch (IOException | ParserConfigurationException | DOMException | SAXException ex) {
            throw new Exception(ex.getMessage());
        }
        return "";
    }
    
    public String recibexmldevuelvenumero(String xml) throws Exception {
        try {
            DocumentBuilderFactory domFactory = DocumentBuilderFactory
                    .newInstance();
            domFactory.setNamespaceAware(true);
            DocumentBuilder builder = domFactory.newDocumentBuilder();
            Document doc = builder
                    .parse(new InputSource(new StringReader(xml)));

            NodeList todosContactos = doc.getElementsByTagName("ObtenerCFEPorReferenciaResult");
            Node unContacto = todosContactos.item(0);

            if (unContacto.getNodeType() == Node.ELEMENT_NODE) {
                Element elemento = (Element) unContacto;

                NodeList lista = elemento.getElementsByTagName("a:numero")
                        .item(0).getChildNodes();
                Node valor = (Node) lista.item(0);
                if (valor == null) {
                    return "";
                }
                return (valor.getNodeValue());
            }
        } catch (IOException | ParserConfigurationException | DOMException | SAXException ex) {
            throw new Exception(ex.getMessage());
        }
        return "";
    }
    
    public String recibexmlfacturaydevuelvefechaemisionticket(String xml) throws Exception {
        try {
            DocumentBuilderFactory domFactory = DocumentBuilderFactory
                    .newInstance();
            domFactory.setNamespaceAware(true);
            DocumentBuilder builder = domFactory.newDocumentBuilder();
            Document doc = builder
                    .parse(new InputSource(new StringReader(xml)));

            NodeList todosContactos = doc.getElementsByTagName("nsAd:eTck");
            Node unContacto = todosContactos.item(0);

            if (unContacto.getNodeType() == Node.ELEMENT_NODE) {
                Element elemento = (Element) unContacto;

                NodeList lista = elemento.getElementsByTagName("nsAd:TmstFirma")
                        .item(0).getChildNodes();
                Node valor = (Node) lista.item(0);
                if (valor == null) {
                    return "";
                }
                return (valor.getNodeValue());
            }
        } catch (IOException | ParserConfigurationException | DOMException | SAXException ex) {
            throw new Exception(ex.getMessage());
        }
        return "";
    }
    
    public String recibexmlfacturaydevuelvefechaemisionfactura(String xml) throws Exception {
        try {
            DocumentBuilderFactory domFactory = DocumentBuilderFactory
                    .newInstance();
            domFactory.setNamespaceAware(true);
            DocumentBuilder builder = domFactory.newDocumentBuilder();
            Document doc = builder
                    .parse(new InputSource(new StringReader(xml)));

            NodeList todosContactos = doc.getElementsByTagName("nsAd:eFact");
            Node unContacto = todosContactos.item(0);

            if (unContacto.getNodeType() == Node.ELEMENT_NODE) {
                Element elemento = (Element) unContacto;

                NodeList lista = elemento.getElementsByTagName("nsAd:TmstFirma")
                        .item(0).getChildNodes();
                Node valor = (Node) lista.item(0);
                if (valor == null) {
                    return "";
                }
                return (valor.getNodeValue());
            }
        } catch (IOException | ParserConfigurationException | DOMException | SAXException ex) {
            throw new Exception(ex.getMessage());
        }
        return "";
    }
    
    public String recibexmlfacturaydevuelverutcliente(String xml) throws Exception {
        try {
            DocumentBuilderFactory domFactory = DocumentBuilderFactory
                    .newInstance();
            domFactory.setNamespaceAware(true);
            DocumentBuilder builder = domFactory.newDocumentBuilder();
            Document doc = builder
                    .parse(new InputSource(new StringReader(xml)));

            NodeList todosContactos = doc.getElementsByTagName("nsAd:Receptor");
            Node unContacto = todosContactos.item(0);

            if (unContacto.getNodeType() == Node.ELEMENT_NODE) {
                Element elemento = (Element) unContacto;

                NodeList lista = elemento.getElementsByTagName("nsAd:DocRecep")
                        .item(0).getChildNodes();
                Node valor = (Node) lista.item(0);
                if (valor == null) {
                    return "";
                }
                return (valor.getNodeValue());
            }
        } catch (IOException | ParserConfigurationException | DOMException | SAXException ex) {
            throw new Exception(ex.getMessage());
        }
        return "";
    }

    public String recibexmlfacturaydevuelvenombrecliente(String xml) throws Exception {
        try {
            DocumentBuilderFactory domFactory = DocumentBuilderFactory
                    .newInstance();
            domFactory.setNamespaceAware(true);
            DocumentBuilder builder = domFactory.newDocumentBuilder();
            Document doc = builder
                    .parse(new InputSource(new StringReader(xml)));

            NodeList todosContactos = doc.getElementsByTagName("nsAd:Receptor");
            Node unContacto = todosContactos.item(0);

            if (unContacto.getNodeType() == Node.ELEMENT_NODE) {
                Element elemento = (Element) unContacto;

                NodeList lista = elemento.getElementsByTagName("nsAd:RznSocRecep")
                        .item(0).getChildNodes();
                Node valor = (Node) lista.item(0);
                if (valor == null) {
                    return "";
                }
                return (valor.getNodeValue());
            }
        } catch (IOException | ParserConfigurationException | DOMException | SAXException ex) {
            throw new Exception(ex.getMessage());
        }
        return "";
    }

    public String recibexmlfacturaydevuelvedireccioncliente(String xml) throws Exception {
        try {
            DocumentBuilderFactory domFactory = DocumentBuilderFactory
                    .newInstance();
            domFactory.setNamespaceAware(true);
            DocumentBuilder builder = domFactory.newDocumentBuilder();
            Document doc = builder
                    .parse(new InputSource(new StringReader(xml)));

            NodeList todosContactos = doc.getElementsByTagName("nsAd:Receptor");
            Node unContacto = todosContactos.item(0);

            if (unContacto.getNodeType() == Node.ELEMENT_NODE) {
                Element elemento = (Element) unContacto;

                NodeList lista = elemento.getElementsByTagName("nsAd:DirRecep")
                        .item(0).getChildNodes();
                Node valor = (Node) lista.item(0);
                if (valor == null) {
                    return "";
                }
                return (valor.getNodeValue());
            }
        } catch (IOException | ParserConfigurationException | DOMException | SAXException ex) {
            throw new Exception(ex.getMessage());
        }
        return "";
    }
    
    public String recibexmlfacturaydevuelveciudadcliente(String xml) throws Exception {
        try {
            DocumentBuilderFactory domFactory = DocumentBuilderFactory
                    .newInstance();
            domFactory.setNamespaceAware(true);
            DocumentBuilder builder = domFactory.newDocumentBuilder();
            Document doc = builder
                    .parse(new InputSource(new StringReader(xml)));

            NodeList todosContactos = doc.getElementsByTagName("nsAd:Receptor");
            Node unContacto = todosContactos.item(0);

            if (unContacto.getNodeType() == Node.ELEMENT_NODE) {
                Element elemento = (Element) unContacto;

                NodeList lista = elemento.getElementsByTagName("nsAd:CiudadRecep")
                        .item(0).getChildNodes();
                Node valor = (Node) lista.item(0);
                if (valor == null) {
                    return "";
                }
                return (valor.getNodeValue());
            }
        } catch (IOException | ParserConfigurationException | DOMException | SAXException ex) {
            throw new Exception(ex.getMessage());
        }
        return "";
    }
    
    public String recibexmlfacturaydevuelvedepartamentocliente(String xml) throws Exception {
        try {
            DocumentBuilderFactory domFactory = DocumentBuilderFactory
                    .newInstance();
            domFactory.setNamespaceAware(true);
            DocumentBuilder builder = domFactory.newDocumentBuilder();
            Document doc = builder
                    .parse(new InputSource(new StringReader(xml)));

            NodeList todosContactos = doc.getElementsByTagName("nsAd:Receptor");
            Node unContacto = todosContactos.item(0);

            if (unContacto.getNodeType() == Node.ELEMENT_NODE) {
                Element elemento = (Element) unContacto;

                NodeList lista = elemento.getElementsByTagName("nsAd:DeptoRecep")
                        .item(0).getChildNodes();
                Node valor = (Node) lista.item(0);
                if (valor == null) {
                    return "";
                }
                return (valor.getNodeValue());
            }
        } catch (IOException | ParserConfigurationException | DOMException | SAXException ex) {
            throw new Exception(ex.getMessage());
        }
        return "";
    }
    
    public String recibexmlfacturaydevuelvepaiscliente(String xml) throws Exception {
        try {
            DocumentBuilderFactory domFactory = DocumentBuilderFactory
                    .newInstance();
            domFactory.setNamespaceAware(true);
            DocumentBuilder builder = domFactory.newDocumentBuilder();
            Document doc = builder
                    .parse(new InputSource(new StringReader(xml)));

            NodeList todosContactos = doc.getElementsByTagName("nsAd:Receptor");
            Node unContacto = todosContactos.item(0);

            if (unContacto.getNodeType() == Node.ELEMENT_NODE) {
                Element elemento = (Element) unContacto;

                NodeList lista = elemento.getElementsByTagName("nsAd:PaisRecep")
                        .item(0).getChildNodes();
                Node valor = (Node) lista.item(0);
                if (valor == null) {
                    return "";
                }
                return (valor.getNodeValue());
            }
        } catch (IOException | ParserConfigurationException | DOMException | SAXException ex) {
            throw new Exception(ex.getMessage());
        }
        return "";
    }

    public String recibexmlfacturaydevuelveconcepto(String xml) throws Exception {
        try {
            DocumentBuilderFactory domFactory = DocumentBuilderFactory
                    .newInstance();
            domFactory.setNamespaceAware(true);
            DocumentBuilder builder = domFactory.newDocumentBuilder();
            Document doc = builder
                    .parse(new InputSource(new StringReader(xml)));

            NodeList todosContactos = doc.getElementsByTagName("nsAd:Detalle");
            Node unContacto = todosContactos.item(0);

            if (unContacto.getNodeType() == Node.ELEMENT_NODE) {
                Element elemento = (Element) unContacto;

                NodeList lista = elemento.getElementsByTagName("nsAd:NomItem")
                        .item(0).getChildNodes();
                Node valor = (Node) lista.item(0);
                if (valor == null) {
                    return "";
                }
                return (valor.getNodeValue());
            }
        } catch (IOException | ParserConfigurationException | DOMException | SAXException ex) {
            throw new Exception(ex.getMessage());
        }
        return "";
    }

    public String recibexmlfacturaydevuelvereferencia(String xml) throws Exception {
        //SI ES UNA NOTA DE CREDITO, DEVUELVE Nº Y SERIE QUE REFERENCIA
        try {
            DocumentBuilderFactory domFactory = DocumentBuilderFactory
                    .newInstance();
            domFactory.setNamespaceAware(true);
            DocumentBuilder builder = domFactory.newDocumentBuilder();
            Document doc = builder
                    .parse(new InputSource(new StringReader(xml)));

            NodeList todosContactos = doc.getElementsByTagName("nsAd:Referencia");
            Node unContacto = todosContactos.item(0);

            if (unContacto.getNodeType() == Node.ELEMENT_NODE) {
                Element elemento = (Element) unContacto;

                NodeList lista = elemento.getElementsByTagName("nsAd:Serie")
                        .item(0).getChildNodes();
                Node valor = (Node) lista.item(0);

                NodeList lista1 = elemento.getElementsByTagName("nsAd:NroCFERef")
                        .item(0).getChildNodes();
                Node valor1 = (Node) lista1.item(0);

                if (valor == null) {
                    return "";
                }

                String unico = valor.getNodeValue() + " " + valor1.getNodeValue();

                return (unico);
            }
        } catch (IOException | ParserConfigurationException | DOMException | SAXException ex) {
            throw new Exception(ex.getMessage());
        }
        return "";
    }
    
    public String recibexmlfacturaydevuelvefechareferencia(String xml) throws Exception {
        //SI ES UNA NOTA DE CREDITO, DEVUELVE Nº Y SERIE QUE REFERENCIA
        try {
            DocumentBuilderFactory domFactory = DocumentBuilderFactory
                    .newInstance();
            domFactory.setNamespaceAware(true);
            DocumentBuilder builder = domFactory.newDocumentBuilder();
            Document doc = builder
                    .parse(new InputSource(new StringReader(xml)));

            NodeList todosContactos = doc.getElementsByTagName("nsAd:Referencia");
            Node unContacto = todosContactos.item(0);

            if (unContacto.getNodeType() == Node.ELEMENT_NODE) {
                Element elemento = (Element) unContacto;

                NodeList lista = elemento.getElementsByTagName("nsAd:FechaCFEref")
                        .item(0).getChildNodes();
                Node valor = (Node) lista.item(0);

                if (valor == null) {
                    return "";
                }

                return valor.getNodeValue();
            }
        } catch (IOException | ParserConfigurationException | DOMException | SAXException ex) {
            throw new Exception(ex.getMessage());
        }
        return "";
    }

    public String recibexmlfacturaydevuelveadenda(String xml) throws Exception {
        //NO FUNCIONA, LA IDEA ES QUE DEVUELVA LA ADENDA
        try {
            DocumentBuilderFactory domFactory = DocumentBuilderFactory
                    .newInstance();
            domFactory.setNamespaceAware(true);
            DocumentBuilder builder = domFactory.newDocumentBuilder();
            Document doc = builder
                    .parse(new InputSource(new StringReader(xml)));

            NodeList todosContactos = doc.getElementsByTagName("nsAdenda:CFE");
            Node unContacto = todosContactos.item(0);

            if (unContacto.getNodeType() == Node.ELEMENT_NODE) {
                Element elemento = (Element) unContacto;

                NodeList lista = elemento.getElementsByTagName("nsAdenda:Adenda")
                        .item(0).getChildNodes();
                Node valor = (Node) lista.item(0);
                if (valor == null) {
                    return "";
                }
                return (valor.getNodeValue());
            }
        } catch (IOException | ParserConfigurationException | DOMException | SAXException ex) {
            throw new Exception(ex.getMessage());
        }
        return "";
    }

    public String recibexmlfacturaydevuelvetotalsiniva(String xml) throws Exception {
        try {
            DocumentBuilderFactory domFactory = DocumentBuilderFactory
                    .newInstance();
            domFactory.setNamespaceAware(true);
            DocumentBuilder builder = domFactory.newDocumentBuilder();
            Document doc = builder
                    .parse(new InputSource(new StringReader(xml)));

            NodeList todosContactos = doc.getElementsByTagName("nsAd:Totales");
            Node unContacto = todosContactos.item(0);

            if (unContacto.getNodeType() == Node.ELEMENT_NODE) {
                Element elemento = (Element) unContacto;

                NodeList lista = elemento.getElementsByTagName("nsAd:MntNetoIVATasaBasica")
                        .item(0).getChildNodes();
                Node valor = (Node) lista.item(0);
                if (valor == null) {
                    return "0";
                }
                return (valor.getNodeValue());
            }
        } catch (IOException | ParserConfigurationException | DOMException | SAXException ex) {
            throw new Exception(ex.getMessage());
        }
        return "0";
    }

    public String recibexmlfacturaydevuelveiva(String xml) throws Exception {
        try {
            DocumentBuilderFactory domFactory = DocumentBuilderFactory
                    .newInstance();
            domFactory.setNamespaceAware(true);
            DocumentBuilder builder = domFactory.newDocumentBuilder();
            Document doc = builder
                    .parse(new InputSource(new StringReader(xml)));

            NodeList todosContactos = doc.getElementsByTagName("nsAd:Totales");
            Node unContacto = todosContactos.item(0);

            if (unContacto.getNodeType() == Node.ELEMENT_NODE) {
                Element elemento = (Element) unContacto;

                NodeList lista = elemento.getElementsByTagName("nsAd:MntIVATasaBasica")
                        .item(0).getChildNodes();
                Node valor = (Node) lista.item(0);
                if (valor == null) {
                    return "0";
                }
                return (valor.getNodeValue());
            }
        } catch (IOException | ParserConfigurationException | DOMException | SAXException ex) {
            throw new Exception(ex.getMessage());
        }
        return "0";
    }
    
    public String devuelveultimoidticket(String xml) throws Exception {
        try {
            DocumentBuilderFactory domFactory = DocumentBuilderFactory
                    .newInstance();
            domFactory.setNamespaceAware(true);
            DocumentBuilder builder = domFactory.newDocumentBuilder();
            Document doc = builder
                    .parse(new InputSource(new StringReader(xml)));

            NodeList todosContactos = doc.getElementsByTagName("<a:Tipo>101</a:Tipo>");
            Node unContacto = todosContactos.item(0);

            if (unContacto.getNodeType() == Node.ELEMENT_NODE) {
                Element elemento = (Element) unContacto;

                NodeList lista = elemento.getElementsByTagName("a:ultnro")
                        .item(0).getChildNodes();
                Node valor = (Node) lista.item(0);
                if (valor == null) {
                    return "";
                }
                return (valor.getNodeValue());
            }
        } catch (IOException | ParserConfigurationException | DOMException | SAXException ex) {
            throw new Exception(ex.getMessage());
        }
        return "";
    }

}
