package tests;

import io.qameta.allure.Allure;
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
        Allure.step("Precondition: if there is no contact, then create one", step -> {
            if (app.hbm().getContactCount() == 0) {
                app.hbm().createContact(new ContactData()
                        .withFirstName("contact for group")
                        .withLastName("contact for group")
                        .withEmail("groups@contact.com"));
            }
        });
        Allure.step("Precondition: if there is no group, then create one", step -> {
            if (app.hbm().getGroupCount() == 0) {
                app.hbm().createGroup(new GroupData().withName("group for contact"));
            }
        });
        Allure.step("Precondition: if there are no groups with contacts, then create", step -> {
            if (getGroupsWithContacts().isEmpty()) {
                app.hbm().addContactToGroup();
            }
        });
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
        Allure.step("Checking if a contact has been removed from a group", step -> {
            Assertions.assertEquals(newRelated, expectedList);
        });
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


