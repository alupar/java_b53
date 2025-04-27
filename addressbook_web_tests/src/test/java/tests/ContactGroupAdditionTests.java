package tests;

import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContactGroupAdditionTests extends TestBase {

    @Test
    public void testAddContactToGroupWithVerification() {
        List<GroupData> groups = app.hbm().getGroupList();
        List<ContactData> contacts = app.hbm().getContactList();
        if (groups.isEmpty()) {
            app.hbm().createGroup(new GroupData("", "Новейшая группа", "new_header", "new_footer"));
            groups = app.hbm().getGroupList();
        }
        if (contacts.isEmpty()) {
            app.hbm().createContact(new ContactData()
                    .withFirstName("Список")
                    .withLastName("Пустой")
                    .withAddress("Небылоконтактов д 1, кв 6"));
            contacts = app.hbm().getContactList();
        }
        var index = new Random().nextInt(groups.size());
        GroupData targetGroup = groups.get(index);
        List<ContactData> oldRelated = app.hbm().getContactsInGroup(targetGroup);
        ContactData contactToAdd = findContactNotInGroup(contacts, oldRelated);
        if (contactToAdd == null) {
            contactToAdd = new ContactData()
                    .withFirstName("Контакт")
                    .withLastName("Новейший");
            app.hbm().createContact(contactToAdd);
            List<ContactData> contactsWithNew = app.hbm().getContactList();
            contactToAdd = app.hbm().getContactList().get(contactsWithNew.size() - 1);
        }
        app.contacts().createContactInGroup(contactToAdd, targetGroup); // то ради чего всё и делается
        List<ContactData> newRelated = app.hbm().getContactsInGroup(targetGroup);
        List<ContactData> expectedList = new ArrayList<>(oldRelated);
        Comparator<ContactData> compareByIdContact = (o1, o2) ->
                Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        newRelated.sort(compareByIdContact);
        expectedList.add(contactToAdd);
        expectedList.sort(compareByIdContact);
        assertEquals(newRelated, expectedList);
    }

    private ContactData findContactNotInGroup(
            List<ContactData> allContacts,
            List<ContactData> groupContacts) {

        for (ContactData contact : allContacts) {
            boolean found = false;
            for (ContactData groupContact : groupContacts) {
                if (contact.id().equals(groupContact.id())) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                return contact;
            }
        }
        return null;
    }
}
