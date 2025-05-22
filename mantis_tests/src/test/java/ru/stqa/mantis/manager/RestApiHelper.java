package ru.stqa.mantis.manager;

import io.swagger.client.ApiClient;
import io.swagger.client.ApiException;
import io.swagger.client.Configuration;
import io.swagger.client.api.UserApi;
import io.swagger.client.auth.ApiKeyAuth;
import io.swagger.client.model.AccessLevel;
import io.swagger.client.model.User;
import io.swagger.client.model.UserAddResponse;

public class RestApiHelper extends HelperBase {

    public RestApiHelper(ApplicationManager manager) {
        super(manager);
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        ApiKeyAuth Authorization = (ApiKeyAuth) defaultClient.getAuthentication("Authorization");
        Authorization.setApiKey(manager.property("apiKey"));
    }

    public void createNewAccountApi(String username, String email) {
        User body = new User();
        body.setUsername(username);
        body.setEmail(email);
        body.setPassword("password");
        body.setAccessLevel(new AccessLevel().id(25L));
        UserApi apiInstance = new UserApi();
        try {
            UserAddResponse result = apiInstance.userAdd(body);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling UserApi#userAdd");
            e.printStackTrace();
        }
    }
}
