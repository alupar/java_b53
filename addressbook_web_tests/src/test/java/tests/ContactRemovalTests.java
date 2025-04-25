package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

public class ContactRemovalTests extends TestBase {

    @Test
    public void canRemoveContactOnHomePage() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData().withFirstName("Вася").withLastName("Петров").withEmail("test@test.com"));
        }
        var oldContacts = app.hbm().getContactList();
        var index = new Random().nextInt(oldContacts.size());
        app.contacts().removeContactsOnHomePage(oldContacts.get(index));
        var newContacts = app.hbm().getContactList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.remove(index);
        Assertions.assertEquals(newContacts, expectedList);
    }

    @Test
    public void canRemoveContactOnEditPage() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData().withFirstName("Вася").withLastName("Петров").withEmail("test@test.com"));
        }
        var oldContacts = app.hbm().getContactList();
        var index = new Random().nextInt(oldContacts.size());
        app.contacts().removeContactOnEditPage(oldContacts.get(index));
        var newContacts = app.hbm().getContactList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.remove(index);
        Assertions.assertEquals(newContacts, expectedList);
    }

    @Test
    public void canRemoveAllContactsAtOnce() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData().withFirstName("Вася").withLastName("Петров"));
        }
        app.contacts().removeAllContacts();
    }
}
