package tests.rest;

import allure.tag.Layer;
import allure.tag.Microservice;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import tests.TestBase;

@Epic("Корзина")
@Feature("Счетчик корзины")
@Layer("rest")
@Microservice("cart")
@Owner("anovikova")
@Tags({@Tag("smoke"), @Tag("planetazdorovo")})
public class CartTestsAPI extends TestBase {

  private final ServiceAPI serviceAPI = new ServiceAPI();

  @Test
  @Story("Проверка счетчика корзины не авторизованного по API пользователя после добавления товара в корзину по API")
  void addItemToCartAsNewUserTest() {
    String cookies = serviceAPI.getCookieNewUser();
    serviceAPI.addToCartWithCheckResult(cookies, 1);
  }

  @Test
  @Story("Проверка счетчика корзины авторизованного по API пользователя после добавления товара в корзину по API")
  void checkCartCounterAuthUserAPITest() {
    String cookies = serviceAPI.getCookieAfterAPIAuth();
    int result = serviceAPI.getCurrentValueInCartAPI(cookies) + 1;
    serviceAPI.addToCartWithCheckResult(cookies, result);
  }

  @Test
  @Story("Проверка счетчика корзины авторизованного по API пользователя после добавления товара(2 шт.) в корзину по API")
  void checkCartCounterAuthUserDoubleItemTest() {
    String cookies = serviceAPI.getCookieAfterAPIAuth();
    int currentCount = serviceAPI.getCurrentValueInCartAPI(cookies);
    serviceAPI.addToCartConfigured(cookies, currentCount, 31, 2);
  }
}
