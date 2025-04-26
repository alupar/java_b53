package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.CommonFunctions;
import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    public static List<ContactData> contactProvider() throws IOException {
        var result = new ArrayList<>(List.of(new ContactData(/*"", "Есения", "Фёдоровна", "Осипова", "Яндекс", "119021, Москва, ул. Льва Толстого, 16", "89450101010", "+79410000000", "89490101013", "oksana8128@hotmail.com", "arseniy1977@mail.ru", "https://www.google.com/", "src/test/resources/images/pilot.jpg"*/)));
        var json = Files.readString(Paths.get("contacts.json"));
        ObjectMapper mapper = new ObjectMapper();
        var value = mapper.readValue(json, new TypeReference<List<ContactData>>() {
        });
        result.addAll(value);
        return result;
    }

    public static List<ContactData> singleRandomContact() {
        return List.of(new ContactData()
                .withFirstName(CommonFunctions.randomString(10))
                .withLastName(CommonFunctions.randomString(20))
                .withAddress(CommonFunctions.randomString(30)));
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultipleContact(ContactData contact) {
        var oldContacts = app.hbm().getContactList();
        app.contacts().createContact(contact);
        var newContacts = app.hbm().getContactList();
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        var maxId = newContacts.get(newContacts.size() - 1).id();

        var expectedList = new ArrayList<>(oldContacts);
        expectedList.add(contact.withId(maxId).withPhoto(""));
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts, expectedList);
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateContactsJdbc(ContactData contact) {
        var oldContacts = app.jdbc().getContactList();
        app.contacts().createContact(contact);
        var newContacts = app.jdbc().getContactList();
        Comparator<ContactData> compareByIdContact = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareByIdContact);
        var maxId = newContacts.get(newContacts.size() - 1).id();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.add(contact.withId(maxId).withPhoto(""));
        expectedList.sort(compareByIdContact);
        Assertions.assertEquals(newContacts, expectedList);
    }

    @ParameterizedTest
    @MethodSource("singleRandomContact")
    public void canCreateContactInGroup(ContactData contact) {
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "new group", "group header", "group footer"));
        }
        var group = app.hbm().getGroupList().get(0);
        var oldRelated = app.hbm().getContactsInGroup(group);
        app.contacts().createContact(contact, group);
        var newRelated = app.hbm().getContactsInGroup(group);
        Comparator<ContactData> compareByIdContact = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newRelated.sort(compareByIdContact);
        var maxId = newRelated.get(newRelated.size() - 1).id();
        var expectedList = new ArrayList<>(oldRelated);
        expectedList.add(contact.withId(maxId).withPhoto(""));
        expectedList.sort(compareByIdContact);
        Assertions.assertEquals(newRelated, expectedList);
    }
}
