package api.tests;


import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
class LoginDto{
    private String usernameOrEmail;
    private String password;

    public String getUsernameOrEmail() {
        return usernameOrEmail;
    }

    public void setUsernameOrEmail(String usernameOrEmail) {
        this.usernameOrEmail = usernameOrEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

public class RestAssureDemoTest {
    @BeforeTest
    public void setUp() {
        baseURI = "http://training.skillo-bg.com:3100/";


    }

    @Test
    public void testLogin() {

        LoginDto loginDto = new LoginDto();

        loginDto.setUsernameOrEmail("valyo2");
        loginDto.setPassword("pass1234");

        Response response = given()
                .log().all()
                .header("Content-Type", "application/json")
                .body(loginDto)
                .when()
                .post("/users/login")
                .then()
                .log()
                .all()
                .extract()
                .response();

        int statusCode = response.statusCode();

        Assert.assertEquals(statusCode, HttpStatus.SC_CREATED);


    }
}
