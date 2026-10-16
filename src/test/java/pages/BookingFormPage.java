package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.AriaRole;

public class BookingFormPage
{
    Page page;

    private static final String fullName = "Full Name";
    private static final String customaerEmail = "#customer-email";
    private static final String phonePlaceholder = "+91 98765 43210";
    private static final String confirmBookungButton = "Confirm Booking";

    public BookingFormPage(Page page)
    {
        this.page = page;
    }

    public String bookAndConfirm(String fname, String custEmail, String phoneNum)
    {
        page.getByLabel(fullName).fill(fname);
        page.locator(customaerEmail).fill(custEmail);

        page.getByPlaceholder(phonePlaceholder).fill(phoneNum);

        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(confirmBookungButton)).click();

        //Verify booking
        PlaywrightAssertions.assertThat(page.getByText("Your tickets are reserved.")).isVisible();
        String bookingRef = page.locator(".booking-ref").innerText();
        return bookingRef;
    }

}
