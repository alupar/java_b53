package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

public class ContactRemovalTests extends TestBase {

    @Test
    public void canRemoveContactOnHomePage() {
        if (app.contacts().getCount() == 0) {
            app.contacts().createContact(new ContactData().withFirstName("Вася").withLastName("Петров"));
        }
        var oldContacts = app.contacts().getList();
        var index = new Random().nextInt(oldContacts.size());
        app.contacts().removeContactsOnHomePage(oldContacts.get(index));
        var newContacts = app.contacts().getList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.remove(index);
        Assertions.assertEquals(newContacts, expectedList);
    }

    @Test
    public void canRemoveContactOnEditPage() {
        if (app.contacts().getCount() == 0) {
            app.contacts().createContact(new ContactData().withFirstName("София").withLastName("Емельянова").withMiddleName("Дмитриевна"));
        }
        app.contacts().removeContactOnEditPage();
    }

    @Test
    public void canRemoveAllContactsAtOnce() {
        if (app.contacts().getCount() == 0) {
            app.contacts().createContact(new ContactData().withFirstName("Вася").withLastName("Петров"));
        }
        app.contacts().removeAllContacts();
    }
}
