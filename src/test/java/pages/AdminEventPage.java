package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;

public class AdminEventPage
{
    Page page;

    private static final String EventTitleInput = "#event-title-input";
    private static final String DescriptionTextArea = "#admin-event-form textarea";
    private static final String CategoryLabel = "Category";
    private static final String CityLabel = "City";
    private static final String VenueLabel = "Venue";
    private static final String EventDateTimeLabel = "Event Date & Time";
    private static final String PriceLabel = "Price ($)";
    private static final String SeatsLabel = "Total Seats";
    private static final String AddEventButton = "#add-event-btn";
    private static final String SuccessToast = "Event created!";


    public AdminEventPage(Page page)
    {
        this.page = page;
    }

    public void goTo()
    {
        page.navigate("https://eventhub.rahulshettyacademy.com/admin/events");
    }

    public void createEvent(String title, String description, String category, String city, String venue,
                            String dateTime, String price, String totalSeats)
    {
        page.locator(EventTitleInput).fill(title, new Locator.FillOptions().setTimeout(10000));
        page.locator(DescriptionTextArea).fill(description);
        page.getByLabel(CategoryLabel).selectOption(category);
        page.getByLabel(CityLabel).fill(city);
        page.getByLabel(VenueLabel).fill(venue);
        page.getByLabel(EventDateTimeLabel).fill(dateTime);
        page.getByLabel(PriceLabel).fill(price);
        page.getByLabel(SeatsLabel).fill(totalSeats);

        page.locator(AddEventButton).click(new Locator.ClickOptions().setTimeout(10000));

        PlaywrightAssertions.assertThat(page.getByText(SuccessToast)).isVisible();
    }
}
