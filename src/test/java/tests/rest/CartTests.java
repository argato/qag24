package tests.rest;

import allure.tag.AutoMember;
import allure.tag.Layer;
import allure.tag.ManualMember;
import allure.tag.Microservice;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

@Epic("Корзина")
@Feature("Счетчик корзины")
@Story("Проверка отображения счетчика для разных пользователей")
@Layer("rest")
@Microservice("cart")
@Owner("anovikova")
@AutoMember("anovikova")
@ManualMember("ipetrov")
@Severity(SeverityLevel.NORMAL)
@Tags({@Tag("smoke"), @Tag("planetazdorovo")})
public class CartTests extends TestBaseAPI {

  private final ServiceAPI serviceAPI = new ServiceAPI();

  @Test
  @DisplayName("Для не авторизованного по API пользователя после добавления товара в корзину по API")
  void addItemToCartAsNewUserTest() {
    String cookies = serviceAPI.getCookieAnonymousUser();
    serviceAPI.addToCartWithCheckResult(cookies, 1);
  }

  @Test
  @DisplayName("Для авторизованного по API пользователя после добавления товара в корзину по API")
  void checkCartCounterAuthUserAPITest() {
    String cookies = serviceAPI.getCookieAfterAPIAuth();
    int result = serviceAPI.getCurrentValueInCartAPI(cookies) + 1;
    serviceAPI.addToCartWithCheckResult(cookies, result);
  }

  @Test
  @DisplayName("Для авторизованного по API пользователя после добавления товара(2 шт.) в корзину по API")
  void checkCartCounterAuthUserDoubleItemTest() {
    String cookies = serviceAPI.getCookieAfterAPIAuth();
    int currentCount = serviceAPI.getCurrentValueInCartAPI(cookies);
    serviceAPI.addToCartConfigured(cookies, currentCount, 31, 2);
  }
}
