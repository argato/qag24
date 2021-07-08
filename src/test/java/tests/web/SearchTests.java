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
import com.codeborne.selenide.Configuration;
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

@Feature("Поиск")
@Story("Поиск валидных значений")
@Layer("web")
@Microservice("search")
@Owner("anovikova")
@Tags({@Tag("smoke"), @Tag("regress"), @Tag("planetazdorovo")})
public class SearchTests extends TestBaseUI {

  PopUpHelper popUpHelper = new PopUpHelper();

  @BeforeEach
  void openPage() {
    RestAssured.baseURI = App.config.apiUrl();
    Configuration.baseUrl = App.config.webUrl();
    open(Configuration.baseUrl);
  }

  @Test
  @DisplayName("Строка в нижнем регистре")
  @TM4J("PZ-T1")
  @AutoMember("anovikova")
  @ManualMember("divanov")
  @Severity(SeverityLevel.CRITICAL)
  void validStringSearchTest() {
    step("Закрыть попап выбора города", (step) -> {
      popUpHelper.popupCityClose();
    });

    step("Поиск по строке {searchedValue}", (step) -> {
      String searchedValue = "матрас";
      $(".header-sub .search [name='q']").setValue(searchedValue);
      $(".header-sub .search button.icon-search").click();
    });

    step("Проверка наличия результатов поиска", (step) -> {
      $$(".card-list__element").shouldHave(CollectionCondition.sizeGreaterThan(0));
    });
  }
}