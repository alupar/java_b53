package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static manager.ContactHelper.randomEmail;

public class ContactCreationTests extends TestBase {

    public static List<ContactData> contactProvider() {
        var result = new ArrayList<>(List.of(new ContactData("", "Есения", "Фёдоровна", "Осипова", "Яндекс", "119021, Москва, ул. Льва Толстого, 16", "89450101010", "+79410000000", "89490101013", "oksana8128@hotmail.com", "arseniy1977@mail.ru", "https://www.google.com/")));
        for (var lastName : List.of("", "Неизвестный")) {
            for (var firstName : List.of("", "Эрнст")) {
                for (var address : List.of("", "г Москва, ул Болотниковская, д 31")) {

                    result.add(new ContactData().withLastName(lastName).withFirstName(firstName).withAddress(address));
                }

            }
        }
        for (int i = 0; i < 5; i++) {
            result.add(new ContactData()
                    .withLastName(randomString(i * 10))
                    .withFirstName(randomString(i * 10))
                    .withAddress(randomString(i * 10))
                    .withEmail(randomEmail()));
        }
        return result;
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultipleContact(ContactData contact) {
        var oldContacts = app.contacts().getList();
        app.contacts().createContact(contact);
        var newContacts = app.contacts().getList();
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.add(contact.withId(newContacts.get(newContacts.size() - 1).id()).withMiddleName("").withCompany("").withHome("").withMobile("").withWork("").withEmail("").withEmail2("").withHomepage(""));
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts, expectedList);
    }
}
