package tests.web;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

import allure.tag.AutoMember;
import allure.tag.Layer;
import allure.tag.ManualMember;
import allure.tag.Microservice;
import allure.tag.TM4J;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import popups.PopUp;

@Epic("Корзина")
@Feature("Отображение в хедере")
@Story("Отображение при добавлении первого товара")
@Layer("web")
@Microservice("cart")
@Owner("anovikova")
@TM4J("QC5-11")
@Tags({@Tag("smoke"), @Tag("planetazdorovo")})
public class CounterOfCartInHeaderTests extends TestBaseUI {

  @BeforeEach
  void openPage() {

    open(Configuration.baseUrl);
    PopUp popUp = new PopUp();
    popUp.popupCityClose();
  }

  @Test
  @DisplayName("Отображение счетчика при добавлении первого товара")
  @AutoMember("anovikova")
  @ManualMember("ipetrov")
  @Severity(SeverityLevel.NORMAL)
  void showingCounterTest() {
    String expectedItemName = "Льняное масло 250мл царевщино";

    step("Закрыть попап выбора города", (step) -> {
      // popUp.popupCityClose();
    });

    step("Выполнить поиск", (step) -> {
      $(".header-sub .search [name='q']").setValue("Льняное масло 250мл царевщино");
      $(".header-sub .search button.icon-search").click();
      $$(".card-list__element").shouldHave(sizeGreaterThan(0));
    });
    step("Добавить в корзину один товар из результатов поиска", (step) -> {
      SelenideElement resultItem = $$(".product-card")
          .findBy(text(expectedItemName))
          .shouldBe(visible);

      resultItem.$(".service_btn_buy_main:not(.q1)").click();
    });
    step("Проверить, что счетчик корзины отображается и равен единице", (step) -> {
      $(".search span.icon__count_pump").shouldHave(text("1"));
    });
  }

  @Test
  @DisplayName("Отображение счетчика при добавлении двух единиц одного товара")
  @AutoMember("anovikova")
  @ManualMember("ipetrov")
  @Severity(SeverityLevel.NORMAL)
  void showingCounterTwoItemsTest() {
    String expectedItemName = "Льняное масло 250мл царевщино";

    step("Закрыть попап выбора города", (step) -> {
      //  popUp.popupCityClose();
    });

    step("Выполнить поиск", (step) -> {
      $(".header-sub .search [name='q']").setValue("Льняное масло 250мл царевщино");
      $(".header-sub .search button.icon-search").click();
      $$(".card-list__element").shouldHave(sizeGreaterThan(0));
    });

    step("Добавить в корзину две единицы одного товара из результатов поиска", (step) -> {
      SelenideElement resultItem = $$(".product-card")
          .findBy(text(expectedItemName))
          .shouldBe(visible);

      resultItem.$(".service_btn_buy_main:not(.q1)").click();
      $(".search span.icon__count_pump").shouldHave(text("1"));
      resultItem.$("div.product-card__content > .product-card__action .btn-product_add").click();
    });

    step("Проверить, что счетчик корзины отображается и равен единице", (step) -> {
      $(".search span.icon__count_pump").shouldHave(text("1"));
    });
  }

  @Test
  @DisplayName("Отображение счетчика, при нажатии \"Купить в один клик\".")
  @AutoMember("anovikova")
  @ManualMember("ipetrov")
  @Severity(SeverityLevel.NORMAL)
  void buyOneClickShowingCounterTest() {

    String expectedItemName = "Льняное масло 250мл царевщино";

    step("Закрыть попап выбора города", (step) -> {
      //  popUp.popupCityClose();
    });

    step("Выполнить поиск", (step) -> {

      $(".header-sub .search [name='q']").setValue("Льняное масло 250мл царевщино");
      $(".header-sub .search button.icon-search").click();
      $$(".card-list__element").shouldHave(sizeGreaterThan(0));
    });

    step("Нажать \"Купить в один клик\" на одном из результатов поиска", (step) -> {
      SelenideElement resultItem = $$(".product-card")
          .findBy(text(expectedItemName))
          .shouldBe(visible);
      resultItem.scrollTo()
                .$("div.product-card__content > .product-card__buynow a[data-entity='basket-checkout-button']")
                .click();
    });
    step(
        "Проверить, что счетчик корзины отображается и равен единице при нажатии \"Купить в один клик\"",
        (step) -> {
          $(".search span.icon__count_pump").shouldHave(text("1"));
        });
  }
}
