package ru.stqa.mantis.manager;

import org.openqa.selenium.By;

import static ru.stqa.mantis.tests.TestBase.DEFAULT_PASSWORD;

public class UserHelper extends HelperBase {

    public UserHelper(ApplicationManager manager) {
        super(manager);
    }

    public void createNewAccount(String username, String email) {
        openSignupPage();
        type(By.id("username"), username);
        type(By.id("email-field"), email);
        click(By.cssSelector("input[type='submit'][value='Signup']"));
        accountRegistrationProcessed();
    }

    public void activateUserAccount(String url) {
        manager.driver().get(url);
        editAccount();
        updateUser();
    }

    private void updateUser() {
        click(By.cssSelector("button[type='submit'].btn-success"));
    }

    private void editAccount() {
        type(By.id("password"), DEFAULT_PASSWORD);
        type(By.id("password-confirm"), DEFAULT_PASSWORD);
    }

    private void accountRegistrationProcessed() {
        click(By.linkText("Proceed"));
    }

    private void openSignupPage() {
        click(By.cssSelector("a[href='signup_page.php']"));
    }

}
