package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.stqa.mantis.common.CommonFunctions;

import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;

public class UserRegistrationTests extends TestBase {

    public static List<String> randomUsername() {
        return List.of(CommonFunctions.randomString(8));
    }

    @ParameterizedTest
    @MethodSource("randomUsername")
    public void canRegisterUser(String username) {
        var email = String.format("%s@localhost", username);
        //создать пользователя (адрес) на почтовом сервере (JamesHelper)
        app.jamesCli().addUser(email,DEFAULT_PASSWORD);
        //заполняем форму создания и отправляем -- браузер
        app.user().createNewAccount(username, email);
        //ждём и получаем почту (MailHelper) и извлекаем ссылку
        var url = app.mail().extractActivationLink(email, DEFAULT_PASSWORD, Duration.ofSeconds(10));
        //проходим по ссылке и завершаем регистрацию -- браузер
        app.user().activateUserAccount(url);
        //проверяем, что пользователь может залогиниться (HttpSessionHelper)
        app.http().login(username, DEFAULT_PASSWORD);
        Assertions.assertTrue(app.http().isLoggedIn());
    }

}
