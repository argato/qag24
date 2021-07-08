package tests.web;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

import allure.tag.AutoMember;
import allure.tag.JiraIssue;
import allure.tag.JiraIssues;
import allure.tag.Layer;
import allure.tag.ManualMember;
import allure.tag.Microservice;
import allure.tag.TM4J;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import config.App;
import helpers.PopUpHelper;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

@Feature("Корзина")
@Story("Переход в корзину")
@Layer("web")
@Microservice("cart")
@Owner("anovikova")
@Tags({@Tag("smoke"), @Tag("planetazdorovo")})
public class GoToCart extends TestBaseUI {

  PopUpHelper popUpHelper = new PopUpHelper();

  @BeforeEach
  void openPage() {

    RestAssured.baseURI = App.config.apiUrl();
    Configuration.baseUrl = App.config.webUrl();
    open(Configuration.baseUrl);
  }

  @Test
  @DisplayName("Переход в корзину при нажатии \"Купить в один клик.\"")
  @TM4J("PZ-T55")
  @JiraIssues({@JiraIssue("PZ-11")})
  @AutoMember("anovikova")
  @ManualMember("asidorov")
  @Severity(SeverityLevel.NORMAL)
  void buyOneClickGoToCartTest() {
    String expectedItemName = "Льняное масло 250мл царевщино";
    step("Закрыть попап выбора города", (step) -> {
      popUpHelper.popupCityClose();
    });

    step("Выполнить поиск", (step) -> {
      $(".header-sub .search [name='q']").setValue("Льняное масло 250мл царевщино");
      $(".header-sub .search button.icon-search").click();
      $$(".card-list__element").shouldHave(CollectionCondition.sizeGreaterThan(0));
    });

    step("Нажать на \"Купить в один клик\" на одном из результатов поиска", (step) -> {
      SelenideElement resultItem = $$(".product-card")
          .findBy(Condition.text(expectedItemName))
          .shouldHave(Condition.exist);

      resultItem.scrollTo()
                .$("div.product-card__content > .product-card__buynow a[data-entity='basket-checkout-button']")
                .click();
    });

    step("Проверить, что произошел переход в корзину к шагу выбора аптеки", (step) -> {
      $(".page-content__basket-inn h1").shouldHave(
          Condition.text("Выберите аптеку, в которой хотите забрать заказ"));
    });
  }
}
