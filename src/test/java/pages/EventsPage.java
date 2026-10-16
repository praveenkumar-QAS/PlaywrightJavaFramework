package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.LocatorAssertions;
import com.microsoft.playwright.assertions.PlaywrightAssertions;

public class EventsPage
{
    Page page;

    public EventsPage(Page page)
    {
        this.page = page;
    }

    public void goTo()
    {
        page.locator("#nav-events").click();
    }

    public Locator waitForEventsToLoad()
    {
        page.waitForTimeout(2000);
        Locator eventcards = page.getByTestId("event-card");
        PlaywrightAssertions.assertThat(eventcards.first()).isVisible();
        return eventcards;
    }

    public Locator findEventCard(String eventTitle)
    {
        Locator eventCards = waitForEventsToLoad();
        return eventCards.filter(new Locator.FilterOptions().setHasText(eventTitle));
    }

    public int getSeatsCount(Locator targetCard)
    {
        String seatstext = targetCard.getByText("seats").innerText();
        System.out.println("Total available seats: "+seatstext);
        return Integer.parseInt(seatstext.split(" ")[0]);
    }

    public BookingFormPage proceedToBookingEvent(Locator targetCard)
    {
        targetCard.getByTestId("book-now-btn").click();
        return new BookingFormPage(page);
    }

    public void verifySeatsCount(String eventTitle)
    {
        Locator targetCardAfterBooking = findEventCard(eventTitle);
        String seatsTextAfterBooking = targetCardAfterBooking.getByText("seats").innerText();
        System.out.println(seatsTextAfterBooking);
    }



}
