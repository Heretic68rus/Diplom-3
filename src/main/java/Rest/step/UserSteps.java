package Rest.step;

import Rest.dto.UserCreateRequest;
import Rest.send.UserSend;
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

