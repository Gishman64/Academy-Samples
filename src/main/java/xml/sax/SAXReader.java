package xml.sax;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.SAXParser;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class SAXReader {
    private final SAXParser parser;
    private final XMLReader xmlReader;
    private final List<Class<?>> registered;

    public SAXReader(SAXParser parser) {
        this.parser = parser;
        registered = new Reflections("com.codeinside.xjb", new SubTypesScanner(false))
                .getAllTypes()
                .stream()
                .map(name -> {
                    try {
                        return Class.forName(name);
                    } catch (ClassNotFoundException e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        try {
            xmlReader = parser.getXMLReader();
        } catch (SAXException e) {
            e.printStackTrace();
            throw new IllegalStateException(e);
        }
    }

    public Unmarshaller unmarshaller() {
        try {
            return JAXBContext.newInstance(registered.toArray(new Class[0])).createUnmarshaller();
        } catch (JAXBException e) {
            throw new IllegalStateException(e);
        }
    }

    public XMLReader xml() {
        return this.xmlReader;
    }
}
