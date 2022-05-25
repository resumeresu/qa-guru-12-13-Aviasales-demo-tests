package steps;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MainPage {

    @Step("Open the main page")
    public MainPage open() {
        Selenide.open("/");
        return this;
    }

    @Step("Click services menu")
    public MainPage clickMenuButton() {
        $(".--services").click();
        return this;
    }

    @Step("Enable dark theme")
    public MainPage enableDarkTheme() {
        $(byText("Включить тёмную тему")).click();
        return this;
    }

    @Step("Check dark theme enabled")
    public void checkDarkThemeEnabled() {
        $("html").shouldHave(cssClass("--night"));
    }

    @Step("Click subscription info floating button")
    public MainPage clickSubscriptionFloatingButton() {
        $(byAttribute("data-test-id", "floating-premium-entry-point")).click();
        return this;
    }

    @Step("Click 'Want more' button")
    public MainPage clickWantMoreButton() {
        $(byAttribute("data-test-id", "want-more-button")).click();
        return this;
    }

    @Step("Check subscription info modal is open")
    public void checkSubscriptionPaymentFormOpen() {
        $(byAttribute("aria-label", "credit card form")).shouldBe(visible);
        $(byAttribute("aria-label", "offer prices list")).shouldBe(visible);
        $(byAttribute("data-test-id", "pay-button")).shouldBe(visible);
    }

    @Step("Click subscription info header button")
    public MainPage clickSubscriptionHeaderButton () {
        $(byAttribute("data-test-id", "add-something-more-button")).click();
        return this;
    }

    @Step("Вводим пункт отправления")
    public MainPage setOriginAutocomplete(String origin) {
        $("#destination").shouldBe(focused);
        $("#origin").click();
        $("#origin").sendKeys(Keys.BACK_SPACE);
        $("#origin").sendKeys(Keys.BACK_SPACE);
        $("#origin").sendKeys(Keys.BACK_SPACE);
        $("#origin").setValue(origin);
        $("#origin-item-0").click();
        return this;
    }

    @Step("Вводим пункт назначения")
    public MainPage setDestinationAutocomplete(String destination) {
        $("#destination").setValue(destination);
        $("#destination-item-0").click();
        return this;
    }

    @Step("Выбираем первую доступную дату отправления")
    public MainPage selectDepartureDate() {
        $(".--departure").click();
        $$(".calendar__day-cell")
                .filterBy(attribute("aria-disabled","false"))
                .findBy(not(cssClass("visuallyDisabled")))
                .click();
        return this;
    }

    @Step("Выбираем дату возвращения")
    public MainPage selectReturnDate() {
        $$(".calendar__day-cell")
                .filterBy(attribute("aria-disabled","false"))
                .findBy(not(cssClass("visuallyDisabled")))
                .click();
        return this;
    }

    @Step("Check hotel search enabled")
    public MainPage checkHotelSearchEnabled() {
        $("#clicktripz").shouldBe(checked);
        return this;
    }

    @Step("Uncheck hotel search")
    public MainPage uncheckHotelSearch() {
        $(byAttribute("data-test-id", "checkbox-booking")).click();
        return this;
    }

    @Step("Run tickets search")
    public MainPage runSearch() {
        $(".avia-form__submit").click();
        return this;
    }

    @Step("Check search has been started")
    public void checkSearchStarted(String origin, String destination) {
        //$(".serp-modal__title").shouldHave(text(origin + "—" + destination));
        $(".search-countdown__title").shouldHave(text("Ищём авиабилеты..."));
    }

}
