package tests;

import common.CommonFunctions;
import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTests extends TestBase {

    @Test
    public void testPhones() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData()
                    .withHome(CommonFunctions.randomPhoneNumber.apply(10))
                    .withMobile(CommonFunctions.randomPhoneNumber.apply(10))
                    .withWork(CommonFunctions.randomPhoneNumber.apply(10)));
        }
        app.contacts().updateHomePage();
        var contacts = app.hbm().getContactList();
        var expected = contacts.stream().collect(Collectors.toMap(ContactData::id, contact -> Stream.of(contact.homePhone(), contact.mobile(), contact.workPhone())
                .filter(s -> s != null && !"".equals(s))
                .collect(Collectors.joining("\n"))
        ));
        var phones = app.contacts().getPhones();
        Assertions.assertEquals(expected, phones);
    }

    @Test
    public void testAddress() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData()
                    .withAddress(CommonFunctions.randomString(10)));
        }
        app.contacts().updateHomePage();
        var contacts = app.hbm().getContactList();
        var contact = contacts.get(0);
        var addresses = app.contacts().getAddress(contact);
        var expected = Stream.of(contact.address())
                .filter(s -> s != null && !"".equals(s))
                .collect(Collectors.joining("\n"));
        Assertions.assertEquals(expected, addresses);
    }

    @Test
    public void testEmail() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData()
                    .withEmail(CommonFunctions.randomEmail())
                    .withEmail2(CommonFunctions.randomEmail())
                    .withEmail3(CommonFunctions.randomEmail()));
        }
        app.contacts().updateHomePage();
        var contacts = app.hbm().getContactList();
        var contact = contacts.get(0);
        var emails = app.contacts().getEMail(contact);
        var expected = Stream.of(contact.email(), contact.email2(), contact.email3())
                .filter(s -> s != null && !"".equals(s))
                .collect(Collectors.joining("\n"));
        Assertions.assertEquals(expected, emails);
    }

    @Test
    void testAddressEmailPhones() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData()
                    .withFirstName(CommonFunctions.randomString(5))
                    .withHome(CommonFunctions.randomPhoneNumber.apply(7))
                    .withMobile(CommonFunctions.randomPhoneNumber.apply(10))
                    .withWork(CommonFunctions.randomPhoneNumber.apply(4))
                    .withAddress(CommonFunctions.randomString(10))
                    .withEmail(CommonFunctions.randomEmail())
                    .withEmail2(CommonFunctions.randomEmail())
                    .withEmail3(CommonFunctions.randomEmail()));
        }
        app.contacts().updateHomePage();
        var contacts = app.hbm().getContactList();
        var contact = contacts.iterator().next();
        var phones = app.contacts().getPhones(contact);
        var expectedPhones = Stream.of(contact.homePhone(), contact.mobile(), contact.workPhone())
                .filter(s -> s != null && !"".equals(s))
                .collect(Collectors.joining("\n"));
        Assertions.assertEquals(expectedPhones, phones, "check phones");
        var addresses = app.contacts().getAddress(contact);
        var expectedAddresses = Stream.of(contact.address())
                .filter(s -> s != null && !"".equals(s))
                .collect(Collectors.joining("\n"));
        Assertions.assertEquals(expectedAddresses, addresses, "check addresses");
        var emails = app.contacts().getEMail(contact);
        var expectedEmails = Stream.of(contact.email(), contact.email2(), contact.email3())
                .filter(s -> s != null && !"".equals(s))
                .collect(Collectors.joining("\n"));
        Assertions.assertEquals(expectedEmails, emails, "check email");
    }

}