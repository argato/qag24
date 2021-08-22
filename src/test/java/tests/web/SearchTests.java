package tests.web;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
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

@Feature("Поиск")
@Story("Поиск валидных значений")
@Layer("web")
@Microservice("search")
@Owner("anovikova")
@Tags({@Tag("smoke"), @Tag("regress"), @Tag("planetazdorovo")})
public class SearchTests extends TestBaseUI {

  PopUp popUp = new PopUp();

  @BeforeEach
  void openPage() {
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
      popUp.popupCityClose();
    });

    step("Поиск по строке {searchedValue}", (step) -> {
      String searchedValue = "матрас";
      $(".header-sub .search [name='q']").setValue(searchedValue);
      $(".header-sub .search button.icon-search").click();
    });

    step("Проверка наличия результатов поиска", (step) -> {
      $$(".card-list__element").shouldHave(sizeGreaterThan(0));
    });
  }
}