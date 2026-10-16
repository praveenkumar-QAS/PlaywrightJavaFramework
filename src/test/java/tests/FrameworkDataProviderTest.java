package tests;

import com.microsoft.playwright.Locator;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.*;
import utils.DataProviderUtil;

import java.util.HashMap;

public class FrameworkDataProviderTest extends BaseTest
{

    @DataProvider(name = "eventBookingData")
    public Object[][] eventBookingData()
    {
        return DataProviderUtil.getJsonDataToMap("src/test/resources/eventBookingData.json");
    }

    @Test(dataProvider = "eventBookingData", groups = "Regression", description = "Create Event - Book that event and verify if its booked")
    public void Demotest(HashMap<String, String> data)
    {
        LoginPage loginPage = new LoginPage(page, base_url);
        DashBoardPage dashBoardPage = loginPage.LogInToApplication();

        //Create an Event
        dashBoardPage.waitForEventToLoad();
        AdminEventPage adminEventPage = new AdminEventPage(page);
        adminEventPage.goTo();
        adminEventPage.createEvent(data.get("eventPrifix"),
                data.get("description"),
                data.get("category"),
                data.get("city"),
                data.get("venue"),
                data.get("dateTime"),
                data.get("price"),
                data.get("totalSeats"));

        //Verify newly created event on Event card
        EventsPage eventsPage = new EventsPage(page);

        eventsPage.goTo();

        Locator targetCard = eventsPage.findEventCard(data.get("eventPrifix"));
        int seatsBeforeBooking = eventsPage.getSeatsCount(targetCard);
        BookingFormPage bookingFormPage = eventsPage.proceedToBookingEvent(targetCard);

        String bookingRef = bookingFormPage.bookAndConfirm(data.get("firstName"),
                data.get("email"), data.get("phone"));

        //verify bookings on My Booking page
        MyBookingsPage myBookingsPage = new MyBookingsPage(page);
        myBookingsPage.goTo();

        myBookingsPage.verifyBookingwithRefNo(bookingRef);

        //Verify seat count on event page
        eventsPage.goTo();
        eventsPage.verifySeatsCount(data.get("eventPrifix"));


    }

}
