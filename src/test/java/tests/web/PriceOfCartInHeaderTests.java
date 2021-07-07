package tests.web;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

import allure.tag.AutoMember;
import allure.tag.Layer;
import allure.tag.ManualMember;
import allure.tag.Microservice;
import allure.tag.TM4J;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import helpers.PopUpHelper;
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
import tests.TestBase;


@Feature("Корзина")
@Story("Отображение стоимости в корзине в хедере")
@Layer("web")
@Microservice("cart")
@Owner("anovikova")
@AutoMember("anovikova")
@ManualMember("asidorov")
@Tags({@Tag("smoke"), @Tag("planetazdorovo")})
public class PriceOfCartInHeaderTests extends TestBase {

  PopUpHelper popUpHelper = new PopUpHelper();

  @BeforeEach
  void openPage() {
    open(Configuration.baseUrl);
  }

  @Test
  @DisplayName("Отображение счетчика при добавлении первого товара")
  @TM4J("PZ-T2")
  @Severity(SeverityLevel.NORMAL)
  void showingPriceTest() {
    String expectedItemName = "Льняное масло 250мл царевщино";

    step("Закрыть попап выбора города", (step) -> {
      popUpHelper.popupCityClose();
    });

    step("Выполнить поиск", (step) -> {
      $(".header-sub .search [name='q']").setValue("Льняное масло 250мл царевщино");
      $(".header-sub .search button.icon-search").click();
      $$(".card-list__element").shouldHave(CollectionCondition.sizeGreaterThan(0));

    });

    step("Добавить в корзину один товар из результатов поиска", (step) -> {
      SelenideElement resultItem = $$(".product-card")
          .findBy(Condition.text(expectedItemName))
          .shouldHave(Condition.exist);

      resultItem.$(".service_btn_buy_main:not(.q1)").click();
    });

    step("Проверить, что в корзине в хедере верно указана стоимость товара", (step) -> {
      $(".search span.header-basket-price").shouldHave(Condition.text("от 82 ₽"));
    });
  }

  @Test
  @DisplayName("Отображение стоимости при добавлении двух единиц одного товара")
  @TM4J("PZ-T3")
  @Severity(SeverityLevel.NORMAL)
  void showingPriceTwoItemsTest() {
    String expectedItemName = "Льняное масло 250мл царевщино";

    step("Закрыть попап выбора города", (step) -> {
      popUpHelper.popupCityClose();
    });

    step("Выполнить поиск", (step) -> {
      $(".header-sub .search [name='q']").setValue("Льняное масло 250мл царевщино");
      $(".header-sub .search button.icon-search").click();
      $$(".card-list__element").shouldHave(CollectionCondition.sizeGreaterThan(0));
    });

    step("Добавить в корзину 2 единицы товара", (step) -> {
      SelenideElement resultItem = $$(".product-card")
          .findBy(Condition.text(expectedItemName))
          .shouldHave(Condition.exist);
      resultItem.$(".service_btn_buy_main:not(.q1)").click();
      resultItem.$("div.product-card__content > .product-card__action .btn-product_add").click();
    });

    step("Проверить, что в корзине в хедере стоимость указана за две единицы товара", (step) -> {
      $(".search span.header-basket-price").shouldHave(Condition.text("от 164 ₽"));
    });
  }
}
