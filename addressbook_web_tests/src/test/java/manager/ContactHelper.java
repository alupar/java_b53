package manager;

import model.ContactData;
import org.openqa.selenium.By;

import java.util.Random;

public class ContactHelper extends HelperBase {

    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }

    public void returnToHomePage() {
        click(By.linkText("home page"));
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


    private void fillContactForm(ContactData contact) {
        type(By.name("firstname"), contact.firstname());
        type(By.name("middlename"), contact.middlename());
        type(By.name("lastname"), contact.lastname());
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

    public void removeContactsOnHomePage() {
        openHomePage();
        selectContact();
        click(By.cssSelector("[type='button'][value='Delete']"));
    }

    public void removeContactOnEditPage() {
        openHomePage();
        click(By.cssSelector("[title='Edit']"));
        click(By.cssSelector("[value='Delete']"));
    }

    private void selectContact() {
        click(By.name("selected[]"));
    }

    public boolean isContactPresent() {
        openHomePage();
        return manager.isElementPresent(By.name("selected[]"));
    }

    public int getCount() {
        openHomePage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    public void removeAllContacts() {
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

    public static String randomEmail() {
        var rnd = new Random();
        return rnd.nextInt(1000) + "@test.ru";
    }

}
