package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

import static manager.ContactHelper.randomEmail;

public class ContactCreationTests extends TestBase {

    public static List<ContactData> contactProvider() {
        var result = new ArrayList<>(List.of(new ContactData("Есения", "Фёдоровна", "Осипова", "Яндекс", "119021, Москва, ул. Льва Толстого, 16", "89450101010", "+79410000000", "89490101013", "oksana8128@hotmail.com", "arseniy1977@mail.ru", "https://www.google.com/")));
        for (var lastName : List.of("", "Неизвестный")) {
            for (var firstName : List.of("", "Эрнст")) {
                for (var address : List.of("", "г Москва, ул Болотниковская, д 31")) {

                    result.add(new ContactData().withLastName(lastName).withFirstName(firstName).withAddress(address));
                }

            }
        }
        for (int i = 0; i < 5; i++) {
            result.add(new ContactData().withLastName(randomString(i * 10)).withFirstName(randomString(i * 10)).withAddress(randomString(i * 10)).withEmail(randomEmail()));
        }
        return result;
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultipleContact(ContactData contact) {
        int contactCount = app.contacts().getCount();
        app.contacts().createContact(contact);
        int newContactCount = app.contacts().getCount();
        Assertions.assertEquals(contactCount + 1, newContactCount);
    }

}
