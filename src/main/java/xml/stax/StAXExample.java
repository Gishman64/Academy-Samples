//package xml.stax;
//
////import com.codeinside.xjb.CustomerType;
//
//import javax.xml.bind.JAXBContext;
//import javax.xml.bind.JAXBElement;
//import javax.xml.bind.JAXBException;
//import javax.xml.bind.Unmarshaller;
//import javax.xml.stream.XMLInputFactory;
//import javax.xml.stream.XMLStreamException;
//import javax.xml.stream.XMLStreamReader;
//import javax.xml.stream.events.XMLEvent;
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.List;
//
//public class StAXExample {
//
//    public static final String RESOURCE = "/ignore/CustomerOrders.xml";
//
//    public static void main(String[] args) throws XMLStreamException, JAXBException {
//        final XMLInputFactory xmlStreamFactory = XMLInputFactory.newFactory();
//        final InputStream source = StAXExample.class.getResourceAsStream(RESOURCE);
////        final Unmarshaller customerUnmarshaller = JAXBContext.newInstance(CustomerType.class).createUnmarshaller();
//        final XMLStreamReader reader = xmlStreamFactory.createXMLStreamReader(source);
//
//        try {
////            List<CustomerType> customers = new ArrayList<>();
//
//            while (reader.hasNext()) {
//                final int event = reader.next();
//                if (XMLEvent.START_ELEMENT == event) {
//                    if (reader.getLocalName().equals("Customer")) {
////                        JAXBElement<CustomerType> unmarshal;
////                        unmarshal = customerUnmarshaller.unmarshal(reader, CustomerType.class);
////
////                        if (unmarshal != null) {
////                            customers.add(unmarshal.getValue());
//                        }
//                    }
//                }
////            }
////            customers.forEach(System.out::println);
////        } /* we */ finally {
//            //did it
//            reader.close();
////        }
//
////    }
////}
