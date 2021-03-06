package br.com.consultanfe.servico;

import br.com.consultanfe.exception.NfeException;
import br.com.consultanfe.util.ConstantesUtil;
import br.com.consultanfe.util.XmlUtil;
import br.inf.portalfiscal.nfe.schema.envcce.TEnvEvento;
import br.inf.portalfiscal.nfe.schema.envcce.TRetEnvEvento;

import javax.xml.bind.JAXBException;
import java.io.InputStream;

/**
 * @author Samuel Oliveira - samuk.exe@hotmail.com
 * Data: 28/09/2017 - 11:11
 */
class CartaCorrecao {

    static TRetEnvEvento eventoCCe(InputStream nfeCacerts, TEnvEvento enviEvento, boolean valida, String tipo) throws NfeException {

        try {

            String xml = XmlUtil.objectToXml(enviEvento);
            xml = xml.replaceAll(" xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\"", "");
            xml = xml.replaceAll("<evento v", "<evento xmlns=\"http://www.portalfiscal.inf.br/nfe\" v");

            xml = Eventos.enviarEvento(nfeCacerts, xml, ConstantesUtil.EVENTO.CCE,valida, tipo);

            return XmlUtil.xmlToObject(xml, TRetEnvEvento.class);

        } catch (JAXBException e) {
            throw new NfeException(e.getMessage());
        }

    }

}
