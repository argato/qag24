package tests.web;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
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
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
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

@Feature("Корзина")
@Story("Переход в корзину")
@Layer("web")
@Microservice("cart")
@Owner("anovikova")
@Tags({@Tag("smoke"), @Tag("planetazdorovo")})
public class GoToCartTests extends TestBaseUI {

  PopUp popUp = new PopUp();

  @BeforeEach
  void openPage() {
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
      popUp.popupCityClose();
    });

    step("Выполнить поиск", (step) -> {
      $(".header-sub .search [name='q']").setValue("Льняное масло 250мл царевщино");
      $(".header-sub .search button.icon-search").click();
      $$(".card-list__element").shouldHave(sizeGreaterThan(0));
    });

    step("Нажать на \"Купить в один клик\" на одном из результатов поиска", (step) -> {
      SelenideElement resultItem = $$(".product-card")
          .findBy(text(expectedItemName))
          .shouldBe(visible);

      resultItem.scrollTo()
                .$("div.product-card__content > .product-card__buynow a[data-entity='basket-checkout-button']")
                .click();
    });

    step("Проверить, что произошел переход в корзину к шагу выбора аптеки", (step) -> {
      $(".page-content__basket-inn h1").shouldHave(
          text("Выберите аптеку, в которой хотите забрать заказ"));
    });
  }
}
