package table.model.parsers;

import java.util.ArrayList;
import java.util.Stack;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import table.model.Student;

public class ParserHandler extends DefaultHandler {
    private ArrayList<Student> entryArray = new ArrayList<Student>();

    private Stack elementStack = new Stack();
    private Stack objectStack = new Stack();

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        this.elementStack.push(qName);

        if ("Entry".equals(qName)) {
            Student entry = new Student();

            this.objectStack.push(entry);
        }
    }

    public void characters(char[] ch, int start, int length) throws SAXException {
        String value = new String(ch, start, length).trim();

        if (value.length() == 0) return;


        if ("fullName".equals(currentElement())) {
            Student entry = (Student) this.objectStack.peek();
            entry.setFullName(value);
        }
        else if ("year".equals(currentElement())) {
            Student entry = (Student) this.objectStack.peek();
            entry.setYear(Integer.parseInt(value));
        }
        else if ("groupId".equals(currentElement())) {
            Student entry = (Student) this.objectStack.peek();
            entry.setGroupId(Integer.parseInt(value));
        }
        else if ("assignmentsAmount".equals(currentElement())) {
            Student entry = (Student) this.objectStack.peek();
            entry.setAssignmentsAmount(Integer.parseInt(value));
        }
        else if ("passedAmount".equals(currentElement())) {
            Student entry = (Student) this.objectStack.peek();
            entry.setPassedAmount(Integer.parseInt(value));
        }
        else if ("progLang".equals(currentElement())) {
            Student entry = (Student) this.objectStack.peek();
            entry.setProgrammingLanguage(value);
        }
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        this.elementStack.pop();

        if ("Entry".equals(qName)) {
            Student object = (Student) this.objectStack.pop();
            this.entryArray.add(object);
        }
    }

    private String currentElement() {
        return (String) this.elementStack.peek();
    }

    public ArrayList<Student> getEntries() {
        return entryArray;
    }
}
