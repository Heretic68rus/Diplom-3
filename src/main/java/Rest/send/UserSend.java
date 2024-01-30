package Rest.send;

import Rest.dto.UserCreateRequest;
import io.restassured.response.Response;

public class UserSend extends BaseSend {
    public Response createUser(UserCreateRequest userCreateRequest) {
        return getDefaultRequestSpecification()
                .body(userCreateRequest)
                .when()
                .post("/auth/register");
    }

    public Response deleteUser() {
        return getDefaultRequestSpecification()
                .when()
                .delete("/auth/login");
    }
}
