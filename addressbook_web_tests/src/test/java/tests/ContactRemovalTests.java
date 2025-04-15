package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

public class ContactRemovalTests extends TestBase {

    @Test
    public void canRemoveContactOnHomePage() {
        if (!app.contacts().isContactPresent()) {
            app.contacts().createContact(new ContactData().withFirstName("Вася").withLastName("Петров"));
        }
        app.contacts().removeContactsOnHomePage();
    }

    @Test
    public void canRemoveContactOnEditPage() {
        if (!app.contacts().isContactPresent()) {
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
