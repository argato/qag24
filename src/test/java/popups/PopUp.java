package popups;

import static com.codeborne.selenide.Selenide.$;

public class PopUp {

  public void popupCityClose() {
    $(".popup-city-accept .popup__close ").click();
  }
}
