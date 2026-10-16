package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.AriaRole;

public class MyBookingsPage
{
    Page page;

    private static final String BookingCard = "#booking-card";

    public MyBookingsPage(Page page)
    {
        this.page = page;
    }

    public void goTo()
    {
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("View My Bookings")).click();
    }

    public void verifyBookingwithRefNo(String bookingRef)
    {
        Locator bookingCards = page.locator(BookingCard);
        PlaywrightAssertions.assertThat(bookingCards.filter(new Locator.FilterOptions().setHasText(bookingRef))).isVisible();
    }



}
