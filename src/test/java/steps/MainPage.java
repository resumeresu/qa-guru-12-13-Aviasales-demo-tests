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
    public void open() {
        Selenide.open("/");
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
    public MainPage clickSubscriptionHeaderButton() {
        $(byAttribute("data-test-id", "add-something-more-button")).click();
        return this;
    }

    @Step("Check hotel search enabled")
    public void checkHotelSearchEnabled() {
        $("#clicktripz").shouldBe(checked);
    }

    @Step("Check switched to tickets search")
    public void checkSwitchedToTicketsSearch() {
        $(".header__title").shouldHave(text("Поиск дешёвых авиабилетов"));
        $(".header__title-form").shouldHave(text("Лучший способ купить авиабилеты дёшево"));
        $(".form-tabs__item.--avia").shouldHave(cssClass("is-active"));
        $("#origin-label").shouldHave(text("Откуда"));
        $(".form-submit__label").shouldHave(text("Найти билеты"));
    }

    @Step("Scroll down to promo title")
    public MainPage scrollDownToPromoTitle() {
        $(".services-promo__title").scrollIntoView(true);
        return this;
    }

    @Step("Check tabs appear in header as user scrolls down")
    public void checkTabsAppear() {
        $(".form-tabs.--show-on-sticky").shouldBe(visible);
    }

    @Step("Set destination")
    public MainPage setDestinationAutocomplete(String destination) {
        $("#destination").setValue(destination);
        $("#destination-item-0").click();
        return this;
    }

    @Step("Select first available departure date")
    public MainPage selectDepartureDate() {
        $(".--departure").click();
        $$(".calendar__day-cell")
                .filterBy(attribute("aria-disabled","false"))
                .findBy(not(cssClass("visuallyDisabled")))
                .click();
        return this;
    }

    @Step("Select first available return date")
    public MainPage selectReturnDate() {
        $$(".calendar__day-cell")
                .filterBy(attribute("aria-disabled","false"))
                .findBy(not(cssClass("visuallyDisabled")))
                .click();
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

    @Step("Check search returned no results")
    public void checkSearchReturnNoResults() {
        $(".error-informer__container").shouldHave(text("Билеты не найдены"));
    }

}
