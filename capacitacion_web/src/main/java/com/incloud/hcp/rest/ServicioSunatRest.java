package com.incloud.hcp.rest;

import com.incloud.hcp.service.dto.MensajePrefactura;
import com.incloud.hcp.service.dto.PrefacturaDto;
import com.incloud.hcp.service.wsdlSunat.BillConsultPortBidingServiceLocator;
import com.incloud.hcp.service.wsdlSunat.BillConsultService;
import com.incloud.hcp.service.wsdlSunat.StatusResponse;
import com.incloud.hcp.service.wsdlSunat.flyWeight.FunctionsXML;
import com.incloud.hcp.utils.DateUtils;
import org.apache.axis.client.Stub;
import org.apache.axis.message.MessageElement;
import org.apache.axis.message.PrefixedQName;
import org.apache.axis.message.SOAPHeaderElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping(value = "/api/sunat")
public class ServicioSunatRest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //@Autowired
    //private SociedadService sociedadService;

    @Value("${cfg.sunat.user}")
    private String repositoryUser;

    @Value("${cfg.sunat.pass}")
    private String repositoryPass;



    @PostMapping(value = "/validarComprobante",produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<MensajePrefactura> VerificarComprobanteRest(@RequestParam(value = "file") MultipartFile archivoSunat){
        MensajePrefactura bean = new MensajePrefactura();
        String codess;
        String messages;
        String serieComprobante;
        String nroComprobante;
        PrefacturaDto prefactura = new PrefacturaDto();

        try {
            File sunatXml = FunctionsXML.convert(archivoSunat);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(sunatXml);
            doc.getDocumentElement().normalize();
            NodeList ProveedorNodeList = doc.getElementsByTagName("cac:AccountingSupplierParty");
            NodeList ClienteNodeList = doc.getElementsByTagName("cac:AccountingCustomerParty");
            NodeList FacturaNodeList = doc.getElementsByTagName("Invoice");
            NodeList TaxSubTotalNodeList = doc.getElementsByTagName("cac:TaxTotal");
            NodeList TaxotalNodeList = doc.getElementsByTagName("cac:LegalMonetaryTotal");
            String rucProveedor = FunctionsXML.getTagValueByAttributeHTML(ProveedorNodeList, "cbc:ID","schemeAgencyName", "PE:SUNAT");
            String rucCliente = FunctionsXML.getTagValueByAttributeHTML(ClienteNodeList, "cbc:ID","schemeAgencyName", "PE:SUNAT");
            String referenciaFactura = FunctionsXML.getTagValueHTML(FacturaNodeList, "cbc:ID");
            String tipoComprobante = FunctionsXML.getTagValueHTML(FacturaNodeList, "cbc:InvoiceTypeCode");
            List<String> taxSubTotal = FunctionsXML.getTagValueIntoTagHTML(TaxSubTotalNodeList, "cbc:Name", "IGV", "cbc:TaxAmount","cbc:TaxableAmount" );
            String montoTotal = FunctionsXML.getTagValueHTML(TaxotalNodeList, "cbc:PayableAmount");

            String[] refFacturaStrings = referenciaFactura.split("-");
            serieComprobante =  refFacturaStrings[0];
            nroComprobante = refFacturaStrings[1];

            BillConsultPortBidingServiceLocator locator =  new BillConsultPortBidingServiceLocator ();
            //locator.setBillConsultServicePortEndpointAddress("https://www.sunat.gob.pe/ol-it-wsconscpegem/billConsultService");
            locator.setBillConsultServicePortEndpointAddress("https://ww1.sunat.gob.pe/ol-it-wsconscpegem/billConsultService");
            BillConsultService port = locator.getBillConsultServicePort();
            Stub stub = ((Stub) port) ;
            int nroComprobanteInteger = Integer.parseInt(nroComprobante + "");
            SOAPHeaderElement wsseSecurity = new SOAPHeaderElement(new PrefixedQName("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd","Security", "wsse"));
            MessageElement usernameToken = new MessageElement("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd", "wsse:UsernameToken");
            MessageElement username = new MessageElement("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd", "wsse:Username");
            MessageElement password = new MessageElement("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd", "wsse:Password");

            username.setObjectValue(rucCliente.concat(repositoryUser));
            usernameToken.addChild(username);
            password.setObjectValue(repositoryPass);
            usernameToken.addChild(password);
            wsseSecurity.addChild(usernameToken);
            stub.setHeader(wsseSecurity);
            StatusResponse ff = port.getStatus(rucProveedor, tipoComprobante, serieComprobante, nroComprobanteInteger);

            codess = ff.getStatusCode();
            messages = ff.getStatusMessage();
            if (codess.equalsIgnoreCase("0001") || codess.equalsIgnoreCase("0009") || codess.equalsIgnoreCase("0010")) {
                bean.setType("S");
                bean.setMensaje(messages);
            } else {
                bean.setType("E");
                bean.setMensaje(messages);
            }

//            if(bean.getType().equalsIgnoreCase("S") ){
                String fechaEmisionString = FunctionsXML.getTagValueHTML(FacturaNodeList, "cbc:IssueDate");
                String codigoMoneda = FunctionsXML.getTagValueHTML(FacturaNodeList, "cbc:DocumentCurrencyCode");
                Date fechaEmision = DateUtils.stringToUtilDate (fechaEmisionString);

                //Sociedad sociedad = sociedadService.getOneByRucCliente(rucCliente);

                //prefactura.setSociedad(sociedad.getCodigoSociedad());
                prefactura.setProveedorRuc(rucProveedor);
                prefactura.setFechaEmision(fechaEmision);
                prefactura.setReferencia(referenciaFactura);
                prefactura.setCodigoMoneda(codigoMoneda);
                prefactura.setIgv(taxSubTotal.get(0));
                prefactura.setSubTotal(taxSubTotal.get(1));
                prefactura.setTotal(montoTotal);
                bean.setPrefactura(prefactura);
               // prefactura.setObservaciones(    );

//            }

        } catch (IOException e) {
            messages = e.toString();
            bean.setType("EX");
            if(messages.equalsIgnoreCase("El Usuario ingresado no existe")) { // si el RUC del receptor no es uno valido, el usuario de autenticacion generado usando dicho RUC no es correcto
                messages = "El receptor no es el correcto";
            }
            bean.setMensaje(messages);
            System.out.println(e.toString());
        }catch (Exception e) {
            // TODO: handle exception
            messages = e.toString();
            bean.setType("EL");
            bean.setMensaje(messages);
            System.out.println(e.toString());
        }

        return new ResponseEntity<>(bean, HttpStatus.OK);
    }
}
