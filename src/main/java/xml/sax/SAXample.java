//package xml.sax;
//
//import com.codeinside.xjb.Root;
//import org.xml.sax.InputSource;
//import org.xml.sax.SAXException;
//import org.xml.sax.XMLReader;
//
//import javax.xml.bind.JAXBContext;
//import javax.xml.bind.JAXBException;
//import javax.xml.bind.Unmarshaller;
//import javax.xml.bind.UnmarshallerHandler;
//import javax.xml.parsers.ParserConfigurationException;
//import javax.xml.parsers.SAXParser;
//import javax.xml.parsers.SAXParserFactory;
//import java.io.IOException;
//
//public class SAXample {
//
//
//    public static void main(String[] args) throws ParserConfigurationException, SAXException, JAXBException, IOException {
//        SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
//        InputSource inputSource = new InputSource(SAXample.class.getResourceAsStream("/ignore/CustomerOrders.xml"));
//
//        //JAXB
//        Unmarshaller unmarshaller = JAXBContext.newInstance(Root.class).createUnmarshaller();
//        UnmarshallerHandler resultHandler = unmarshaller.getUnmarshallerHandler();
//
//        //SAX
//        XMLReader xmlReader = parser.getXMLReader();
//        xmlReader.setContentHandler(resultHandler);
//        xmlReader.parse(inputSource);
//
//        //Result
//        Root result = (Root) resultHandler.getResult();
//        System.out.println(result);
//    }
//}
