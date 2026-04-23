package com.solvd.socialnetwork.xml;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;

public class JaxbUtil {

    public static <T> void marshal(T object, File file) throws JAXBException {
        JAXBContext ctx = JAXBContext.newInstance(object.getClass());
        Marshaller m = ctx.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        m.marshal(object, file);
        }

    public static <T> T unmarshal(Class<T> clazz, File file) throws JAXBException {
        JAXBContext ctx = JAXBContext.newInstance(clazz);
        Unmarshaller um = ctx.createUnmarshaller();
        return (T) um.unmarshal(file);
        }
}
