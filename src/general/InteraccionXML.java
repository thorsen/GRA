/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package general;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class InteraccionXML {
    public static final String NOM_FFT_SCHEMA = "fftXmlSchema";
    
    public static final String TAG_NAMESPACE = "ns0";
    public static final String TAG_ESPECTRO = TAG_NAMESPACE + ":" + "Espectro";
    public static final String TAG_DATOS = TAG_NAMESPACE + ":" + "Datos";
    public static final String TAG_ID_DATO = "id";
    public static final String TAG_FRECUENCIA = TAG_NAMESPACE + ":" + "Frecuencia";
    public static final String TAG_NIVEL = TAG_NAMESPACE + ":" + "Nivel";
    public static final String RUTA_NAMESPACE = "http://xml.netbeans.org/schema/fftXmlSchema";
    
    public static int creaFftXmlSchema() throws IOException, SQLException {
        int res = 0;
        InteraccionFic interFic = new InteraccionFic("src/general/" + NOM_FFT_SCHEMA + ".xsd", InteraccionFic.READ);
        String linea, xmlSchema = "";
        InteraccionBD interBD = new InteraccionBD();
        
        while ((linea = interFic.leeLinea()) != null) {
            if (xmlSchema.length() > 0)
                xmlSchema += "\n";
            else //Es la primera línea, cambiamos el encoding
                linea = linea.replace("UTF-8", "UTF-16");
            
            xmlSchema += linea;
        }
        
        interFic.finOpFichero();
        
        interBD.creaXmlSchema(NOM_FFT_SCHEMA, xmlSchema);
        
        return res;
    }
    
    public static String creaXml(LinkedHashMap<Double, Double> espectroFFT) throws ParserConfigurationException, TransformerException {
        String res = null;

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        //Elemento raíz
        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement(TAG_ESPECTRO);
        Attr attrXsi = doc.createAttribute("xmlns:xsi");
        attrXsi.setValue("http://www.w3.org/2001/XMLSchema-instance");
        rootElement.setAttributeNode(attrXsi);
        Attr attrNs0 = doc.createAttribute("xmlns:" + TAG_NAMESPACE);
        attrNs0.setValue(RUTA_NAMESPACE);
        rootElement.setAttributeNode(attrNs0);
        Attr attrSchemaLocation = doc.createAttribute("xsi:schemaLocation");
        attrSchemaLocation.setValue("http://xml.netbeans.org/schema/fftXmlSchema fftXmlSchema.xsd");
        rootElement.setAttributeNode(attrSchemaLocation);

        doc.appendChild(rootElement);
        
        Iterator it = espectroFFT.entrySet().iterator();
        Entry entry;
        
        Element datos, frecuencia, nivel;
        Attr idDato;
        
        int i = 1;
        
        while (it.hasNext()) {
            entry = (Entry) it.next();
            
            //Datos
            datos = doc.createElement(TAG_DATOS);
            rootElement.appendChild(datos);
            
            //IdDato
            idDato = doc.createAttribute(TAG_ID_DATO);
            idDato.setValue(String.valueOf(i++));
            datos.setAttributeNode(idDato);

            //Frecuencia
            frecuencia = doc.createElement(TAG_FRECUENCIA);
            frecuencia.appendChild(doc.createTextNode(entry.getKey().toString()));
            datos.appendChild(frecuencia);
            //Nivel
            nivel = doc.createElement(TAG_NIVEL);
            nivel.appendChild(doc.createTextNode(entry.getValue().toString()));
            datos.appendChild(nivel);
        }

        //Creamos el XML
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        //StreamResult result = new StreamResult(res);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        StreamResult result = new StreamResult(baos);
        transformer.transform(source, result);
        
        res = baos.toString().replace("UTF-8", "UTF-16");
        
        return res;
    }
    
    //Función que devuelve el array de datos del xml formado por <[id, frecuencia, nivel, valido],...,[]>
    public static ArrayList<DatoXML> leeXml(String xml) throws Exception
    {
        ArrayList<DatoXML> res = new ArrayList<DatoXML>();
        Integer idDato = null;
        Double frecuencia = null, nivel = null;
        String nombreHijo, valorHijo;
        
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(xml));
        Document doc =  builder.parse(is);
        
        NodeList datos = doc.getElementsByTagName(TAG_DATOS);
        NodeList hijosDatos;
        
        int nDatos = datos.getLength();
        int nHijosDatos;
        
        for (int i = 0; i < nDatos; i++) {
            hijosDatos = datos.item(i).getChildNodes();
            nHijosDatos = hijosDatos.getLength();
            
            idDato = Integer.parseInt(datos.item(i).getAttributes().getNamedItem(TAG_ID_DATO).getNodeValue());
            
            for (int j = 0; j < nHijosDatos; j++) {
                nombreHijo = hijosDatos.item(j).getNodeName();
                valorHijo = hijosDatos.item(j).getTextContent();
                
                if (nombreHijo.contentEquals(TAG_FRECUENCIA))
                    frecuencia = Double.parseDouble(valorHijo);
                else if (nombreHijo.contentEquals(TAG_NIVEL))
                    nivel = Double.parseDouble(valorHijo);
            }
            
            res.add(new DatoXML(idDato, frecuencia, nivel));
        }
        
        return res;
    }
}