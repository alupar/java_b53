package tests;

import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Test;

public class ContactCreationTests extends TestBase {

    @Test
    public void canCreateContactWithEmptyFields() {
        app.contacts().createContact(new ContactData());
    }

    @Test
    public void canCreateContactWithFullData() {
        app.contacts().createContact(new ContactData("Есения", "Фёдоровна", "Осипова", "Яндекс", "119021, Москва, ул. Льва Толстого, 16", "89450101010", "+79410000000", "89490101013", "oksana8128@hotmail.com", "arseniy1977@mail.ru", "https://www.google.com/"));
    }


}
