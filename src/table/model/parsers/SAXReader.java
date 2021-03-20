package table.model.parsers;

import table.model.Student;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.ArrayList;

public class SAXReader {
    private SAXParser documentModelReader;
    private ParserHandler writerHandler;

    public SAXReader() {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            documentModelReader = factory.newSAXParser();
            writerHandler = new ParserHandler();
        }
        catch (Exception e) { e.printStackTrace(); }
    }

    public ArrayList<Student> readAndParse(String parsePath) {
        ArrayList<Student> documentEntries=null;
        try {
            documentModelReader.parse(parsePath, writerHandler);
            documentEntries = writerHandler.getEntries();
        }
        catch (Exception e) { e.printStackTrace(); }

        return documentEntries;
    }
}
