package tests;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.MainPage;

import java.util.Locale;

public class AvaisalesDemoTests extends BaseTest {

    Faker faker = new Faker(new Locale("ru-RU"));
    MainPage mainPage = new MainPage();

    @BeforeEach
    void openMainPage() {
        mainPage.open();
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
    @DisplayName("Search for nearest tickers for an inland flight")
    void searchFirstAvailableTickets() {
        String origin = "Санкт-";
        String destination = "Мос";
        mainPage.setOriginAutocomplete(origin)
                .setDestinationAutocomplete(destination)
                .selectDepartureDate()
                .selectReturnDate()
                .uncheckHotelSearch()
                .runSearch()
                .checkSearchStarted(origin, destination);
    }

}
