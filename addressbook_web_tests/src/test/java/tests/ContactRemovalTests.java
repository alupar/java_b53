package tests;

import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class ContactRemovalTests extends TestBase {


    @Test
    void deleteContactFromGroup() {
//        если нет ни одного контакта, то создаём его
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData()
                    .withFirstName("contact for group")
                    .withLastName("contact for group")
                    .withEmail("groups@contact.com"));
        }
//        если нет ни одной группы, то создаём её
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData().withName("group for contact"));
        }
//        если нет групп с контактами, то создаём
        if (getGroupsWithContacts().isEmpty()) {
            app.hbm().addContactToGroup();
        }
        var groups = getGroupsWithContacts();
        var index = new Random().nextInt(groups.size());
        var group = groups.get(index);
        var oldRelated = app.hbm().getContactsInGroup(group);
        var contactToRemove = app.hbm().getContactsInGroup(group).get(0);
        app.contacts().deleteContactFromGroup(contactToRemove, group);
        var newRelated = app.hbm().getContactsInGroup(group);
        var expectedList = new ArrayList<>(oldRelated);
        Comparator<ContactData> compareByIdContact = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newRelated.sort(compareByIdContact);
        expectedList.remove(contactToRemove);
        expectedList.sort(compareByIdContact);
        Assertions.assertEquals(newRelated, expectedList);
    }

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

    public List<GroupData> getGroupsWithContacts() {
        List<GroupData> allGroups = app.hbm().getGroupList();
        List<GroupData> groupsWithContacts = new ArrayList<>();
        for (GroupData group : allGroups) {
            List<ContactData> contacts = app.hbm().getContactsInGroup(group);
            if (contacts != null && !contacts.isEmpty()) {
                groupsWithContacts.add(group);
            }
        }
        return groupsWithContacts;
    }

}


