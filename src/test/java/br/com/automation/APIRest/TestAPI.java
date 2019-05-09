package br.com.automation.APIRest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

import io.restassured.http.ContentType;

public class TestAPI {

	@Test
	public void TestGET() {
		String uriBase = "https://postman-echo.com/get";
		given()
			.relaxedHTTPSValidation()
			.param("foo1", "bar1")
			.param("foo2", "bar2")
		.when()
			.get(uriBase)
		.then()
			.statusCode(200) // O status http retornado foi 200
			.contentType(ContentType.JSON) // O response foi retornado no formato JSON
			.body("headers.host", equalTo("postman-echo.com")) // A chave “host” possui exatamente o valor postman-echo.com”
			.body("args.foo1", containsString("bar")); // A chave “foo1” contém o valor “bar”
	}
	
	@Test
	public void TestPOST() {
		String uriBase = "https://postman-echo.com/post";
		given()
			.relaxedHTTPSValidation().body("{ foo1: bar3, foo2: bar2 }")
		.when()
			.post(uriBase)
		.then()
			.statusCode(200) // O status http retornado foi 200
			.contentType(ContentType.JSON) // O response foi retornado no formato JSON
			.body("headers.host", equalTo("postman-echo.com")) // A chave “host” possui exatamente o valor postman-echo.com”
			.body("data", containsString("{ foo1: bar3, foo2: bar2 }")); // A chave “data” contém o valor "{ foo1: bar3, foo2: bar2 }"
	}

}
