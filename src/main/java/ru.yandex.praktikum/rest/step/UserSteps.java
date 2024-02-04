package ru.yandex.praktikum.rest.step;

import ru.yandex.praktikum.rest.dto.UserCreateRequest;
import ru.yandex.praktikum.rest.send.UserSend;
import io.restassured.response.ValidatableResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserSteps {
    private final UserSend userSend;

    public ValidatableResponse createUser(String email, String password, String name) {
        UserCreateRequest userCreateRequest = new UserCreateRequest();
        userCreateRequest.setEmail(email);
        userCreateRequest.setPassword(password);
        userCreateRequest.setName(name);
        return userSend.createUser(userCreateRequest).then();
    }

    public ValidatableResponse deleteUser() {
        return userSend.deleteUser().then();
    }
}

