package tests.rest;

import static filters.CustomLogFilter.customLogFilter;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import io.qameta.allure.restassured.AllureRestAssured;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServiceAPI {

  int getCurrentValueInCartAPI(String cookies) {
    Pattern pattern = Pattern.compile("<span class=\"cart-qty\">\\((\\d{1,})\\)<\\/span>");
    String cartPage = given()
        .filter(customLogFilter().withCustomTemplates())
        .contentType("application/x-www-form-urlencoded; charset=UTF-8")
        .cookie("NOPCOMMERCE.AUTH", cookies)
        .when()
        .log().uri()
        .log().body()
        .get("http://demowebshop.tricentis.com/cart")
        .then()
        .statusCode(200)
        .extract()
        .body().asString();

    Matcher matcher = pattern.matcher(cartPage);
    int s = 0;
    if (matcher.find()) {
      s = Integer.parseInt(matcher.group(1));
    }
    return s;
  }

  String getCookieAfterAPIAuth() {
    return given()
        .filter(customLogFilter().withCustomTemplates())
        .contentType("application/x-www-form-urlencoded")
        .formParam("Email", "qw123@qwer.ty")
        .formParam("Password", "123456")
        .when()
        .log().uri()
        .log().body()
        .post("http://demowebshop.tricentis.com/login")
        .then()
        .statusCode(302)
        .log().body()
        .extract()
        .response()
        .getCookie("NOPCOMMERCE.AUTH");
  }

  String getCookieNewUser() {
    return given()
        .filter(customLogFilter().withCustomTemplates())
        .contentType("application/x-www-form-urlencoded")
        .when()
        .log().uri()
        .log().body()
        .post("http://demowebshop.tricentis.com/login")
        .then()
        .statusCode(200)
        .extract()
        .response()
        .getCookie("NOPCOMMERCE.AUTH");
  }

  void addToCart(String cookies) {
    given()
        .filter(customLogFilter().withCustomTemplates())
        .contentType("application/x-www-form-urlencoded; charset=UTF-8")
        .body("addtocart_31.EnteredQuantity=1")
        .cookie("NOPCOMMERCE.AUTH", cookies)
        .when()
        .log().uri()
        .log().body()
        .post("http://demowebshop.tricentis.com/addproducttocart/details/31/1")
        .then()
        .statusCode(200)
        .body("success", is(true));
  }

  void addToCartConfigured(String cookies, int currentCounter, int itemNumber, int quantity) {
    int result = currentCounter + quantity;
    given()
        .filter(customLogFilter().withCustomTemplates())
        .contentType("application/x-www-form-urlencoded; charset=UTF-8")
        .body("addtocart_" + itemNumber + ".EnteredQuantity=" + quantity)
        .cookie("NOPCOMMERCE.AUTH", cookies)
        .when()
        .log().uri()
        .log().body()
        .post("http://demowebshop.tricentis.com/addproducttocart/details/" + itemNumber + "/1")
        .then()
        .statusCode(200)
        .body("success", is(true))
        .body("updatetopcartsectionhtml", is("(" + result + ")"));
  }

  void addToCartWithCheckResult(String cookies, Integer result) {
    given()
        .filter(customLogFilter().withCustomTemplates())
        .contentType("application/x-www-form-urlencoded; charset=UTF-8")
        .body("addtocart_31.EnteredQuantity=1")
        .cookie("NOPCOMMERCE.AUTH", cookies)
        .when()
        .log().uri()
        .log().body()
        .post("http://demowebshop.tricentis.com/addproducttocart/details/31/1")
        .then()
        .statusCode(200)
        .body("success", is(true))
        .body("updatetopcartsectionhtml", is("(" + result + ")"));
  }

  static void logout() {
    given()
        .filter(new AllureRestAssured())
        .when()
        .log().uri()
        .log().body()
        .get("http://demowebshop.tricentis.com/logout")
        .then()
        .statusCode(302);
  }
}