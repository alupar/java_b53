package manager;

import model.ContactData;
import model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }

    public void returnToHomePage() {
        click(By.linkText("home page"));
    }

    public void modifyContact(ContactData contact, ContactData modifiedContact) {
        openHomePage();
        initContactModification(contact);
        fillContactForm(modifiedContact);
        submitContactModification();
        returnToHomePage();
    }

    private void initContactModification(ContactData contact) {
        click(By.cssSelector("td.center > a[href^='edit.php?id=" + contact.id() + "']"));
    }

    private void submitContactModification() {
        click(By.name("update"));
    }

    public void openHomePage() {
        click(By.linkText("home"));
    }

    public void iniContactCreation() {
        click(By.linkText("add new"));
    }

    public void createContact(ContactData contact) {
        iniContactCreation();
        fillContactForm(contact);
        submitContactCreation();
        //returnToHomePage();
    }

    public void createContact(ContactData contact, GroupData group) {
        iniContactCreation();
        fillContactForm(contact);
        selectGroupOnEditPage(group);
        submitContactCreation();
        openHomePage();
    }

    private void fillContactForm(ContactData contact) {
        type(By.name("firstname"), contact.firstname());
        type(By.name("middlename"), contact.middlename());
        type(By.name("lastname"), contact.lastname());
        if (contact.photo() != null && !contact.photo().isEmpty()) {
            File file = new File(contact.photo());
            if (file.exists()) {
                attach(By.name("photo"), String.valueOf(file));
            }
        }
        type(By.name("company"), contact.company());
        type(By.name("address"), contact.address());
        type(By.name("home"), contact.homePhone());
        type(By.name("mobile"), contact.mobile());
        type(By.name("work"), contact.workPhone());
        type(By.name("email"), contact.email());
        type(By.name("email2"), contact.email2());
        type(By.name("homepage"), contact.homepage());
    }

    private void submitContactCreation() {
        click(By.cssSelector("[value='Enter']"));
    }

    public void removeContactsOnHomePage(ContactData contact) {
        openHomePage();
        selectGroupOnHomePage();
        selectContact(contact);
        click(By.cssSelector("[type='button'][value='Delete']"));
    }

    public void removeContactOnEditPage(ContactData contact) {
        openHomePage();
        selectGroupOnHomePage();
        initContactModification(contact);
        click(By.cssSelector("[value='Delete']"));
//        openHomePage();
    }

    private void selectContact(ContactData contact) {
//        click(By.name("selected[]"));
        click(By.cssSelector(String.format("input[id='%s']", contact.id())));
    }

    public int getCount() {
        openHomePage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    public void removeAllContacts() {
        openHomePage();
        selectGroupOnHomePage();
        click(By.cssSelector("#MassCB"));
        click(By.cssSelector("[type='button'][value='Delete']"));
//        if (manager.driver.switchTo().alert() != null) {
//            manager.driver.switchTo().alert().accept();
//        }
    }

    public void removeAllContactsOneByOne() {
        openHomePage();
        selectAllContacts();
        click(By.cssSelector("[type='button'][value='Delete']"));
    }

    private void selectAllContacts() {
        var checkboxies = manager.driver.findElements(By.name("selected[]"));
        for (var checkbox : checkboxies) {
            checkbox.click();
        }
    }


    public List<ContactData> getList() {
        openHomePage();
        var contacts = new ArrayList<ContactData>();
        var trows = manager.driver.findElements(By.cssSelector("tr[name='entry']"));
        for (var trow : trows) {
            var checkbox = trow.findElement(By.cssSelector("tr[name='entry'] input[type='checkbox']"));
            var contactId = checkbox.getAttribute("id");
            var td = trow.findElements(By.tagName("td"));
            var lastname = td.get(1).getText();
            var firstname = td.get(2).getText();
            var address = td.get(3).getText();
            contacts.add(new ContactData().withId(contactId).withLastName(lastname).withFirstName(firstname).withAddress(address));
        }
        return contacts;
    }

    public void deleteContactFromGroup(ContactData contact, GroupData group) {
        openHomePage();
//        selectGroupOnHomePage();
        selectDeleteGroupName(group);
        selectContact(contact);
        removeContactFromGroup();
        openHomePage();
    }

    private void selectGroupOnHomePage() {
        WebElement dropdown = manager.driver.findElement(By.name("group"));
        Select select = new Select(dropdown);
        select.selectByVisibleText("[all]");
    }

    private void removeContactFromGroup() {
        click(By.name("remove"));
    }

    public void selectDeleteGroupName(GroupData group) {
        new Select(manager.driver.findElement(By.name("group"))).selectByValue(group.id());
    }

    private void selectGroupOnEditPage(GroupData group) {
        new Select(manager.driver.findElement(By.name("new_group"))).selectByValue(group.id());
    }
}
