package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import steps.MainPage;

public class AvaisalesDemoTests extends BaseTest {

    MainPage mainPage = new MainPage();

    @BeforeEach
    void openMainPage() {
        mainPage.open();
    }

    @Test
    @DisplayName("Main page switched to tickets search by default")
    void checkSwitchedToTicketsSearch() {
        mainPage.checkSwitchedToTicketsSearch();
    }

    @Test
    @DisplayName("Enable dark theme")
    void enableDarkTheme() {
        mainPage.clickMenuButton()
                .enableDarkTheme()
                .checkDarkThemeEnabled();
    }

    @Test
    @DisplayName("Go to subscription payment from via floating button")
    void goToSubscriptionPaymentByFloatingButton() {
        mainPage.clickSubscriptionFloatingButton()
                .clickWantMoreButton()
                .checkSubscriptionPaymentFormOpen();
    }

    @Test
    @DisplayName("Go to subscription payment from via header button")
    void goToSubscriptionPaymentByHeaderButton() {
        mainPage.clickSubscriptionHeaderButton()
                .clickWantMoreButton()
                .checkSubscriptionPaymentFormOpen();
    }

    @Test
    @DisplayName("Hotel search is enabled by default")
    void hotelSearchIsEnabled() {
        mainPage.checkHotelSearchEnabled();
    }

    @Test
    @DisplayName("Tabs appear in header as user scrolls down")
    void checkTabsAppear() {
        mainPage.scrollDownToPromoTitle()
                .checkTabsAppear();
    }

    @ValueSource(strings = {"Токи", "Ваши", "Пеки"})
    @ParameterizedTest(name = "Search when no flights to the destination for the given dates")
    void searchWhenNoFlightsToDestination(String destination) {
        mainPage.setDestinationAutocomplete(destination)
                .selectDepartureDate()
                .selectReturnDate()
                .uncheckHotelSearch()
                .runSearch()
                .checkSearchReturnNoResults();
    }

}
