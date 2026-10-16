package tests;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;
import pages.*;

public class FrameworkBuildClass extends BaseTest
{
    @Test(description = "Create Event - Book that event and verify if its booked", groups = "smoke")
    public void Demotest()
    {
        LoginPage loginPage = new LoginPage(page, base_url);
        DashBoardPage dashBoardPage = loginPage.LogInToApplication();
        String eventTitle  = "Playwright Automation Test";

        //Create an Event
        dashBoardPage.waitForEventToLoad();
        AdminEventPage adminEventPage = new AdminEventPage(page);
        adminEventPage.goTo();
        adminEventPage.createEvent("Playwright Automation Test",
                "Playwright Automation Test description",
                "Concert",
                "Siwan",
                "Indoor Stadium",
                "2026-12-25T18:50",
                "100",
                "50");

        //Verify newly created event on Event card
        EventsPage eventsPage = new EventsPage(page);

        eventsPage.goTo();

        Locator targetCard = eventsPage.findEventCard(eventTitle);
        int seatsBeforeBooking = eventsPage.getSeatsCount(targetCard);
        BookingFormPage bookingFormPage = eventsPage.proceedToBookingEvent(targetCard);

        String bookingRef = bookingFormPage.bookAndConfirm("Praveen Kumar",
                "abc@gmail.com", "8797811256");

        //verify bookings on My Booking page
        MyBookingsPage myBookingsPage = new MyBookingsPage(page);
        myBookingsPage.goTo();

        myBookingsPage.verifyBookingwithRefNo(bookingRef);

        //Verify seat count on event page
        eventsPage.goTo();
        eventsPage.verifySeatsCount(eventTitle);


    }

}
