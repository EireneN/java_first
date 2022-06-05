package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {
    @Parameter(names = "-c", description = "Contact count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    @Parameter(names = "-d", description = "Data format")
    public String format;


    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex) {
            jCommander.usage();
            return;
        }
        generator.run();
    }


    private void run() throws IOException {
        List<ContactData> contacts = generateContact(count);
        if (format.equals("csv")) {
            saveAsCsv(contacts, new File(file));
        } else if (format.equals("xml")) {
            saveAsXml(contacts, new File(file));
        } else if (format.equals("json")) {
            saveAsJson(contacts, new File(file));
        } else {
            System.out.println("Unrecognized format" + format);
        }
    }

    private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(contacts);
        Writer writer = new FileWriter(file);
        writer.write(json);
        writer.close();

    }

    private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.processAnnotations(ContactData.class);
        String xml = xstream.toXML(contacts);
        Writer writer = new FileWriter(file);
        writer.write(xml);
        writer.close();
    }

    private void saveAsCsv (List<ContactData> contacts, File file) throws IOException {
        System.out.println(new File(".").getAbsolutePath());
        Writer writer = new FileWriter(file);
        for (ContactData contact : contacts) {
            writer.write(String.format("%s;%s;%s,%s,%s,%s,%s,%s,%s\n", contact.getFirstname(), contact.getLastname(), contact.getAddress()
                    ,contact.getMobilePhone(), contact.getHomePhone(), contact.getWorkPhone(), contact.getEmail()
            , contact.getEmailTwo(), contact.getEmailThree()));
        }
        writer.close();
    }

    private List<ContactData> generateContact(int count) {
        List<ContactData> contacts = new ArrayList<ContactData>();
        for (int i = 0; i < count; i++){
            contacts.add(new ContactData().withFirstname(String.format("Иван %s", i))
                    .withLastname(String.format("Иванов %s", i)).withAddress(String.format("Москва, Красная площаль д.1 %s", i))
                    .withMobilePhone(String.format("+79213456789 %s", i)).withWorkPhone(String.format("567 89 09 %s", i))
                    .withHomePhone(String.format("8 (812) 657 45 45 %s", i)).withPhoneTwo(String.format("+73211221212 %s", i))
                    .withEmail(String.format("ivanov@mail.ru", i)).withEmailTwo(String.format("ivan99@yandex.ru %s", i))
                    .withEmailThree(String.format("ivanov@gmail.com %s", i)));
        }
        return contacts;
    }
}
