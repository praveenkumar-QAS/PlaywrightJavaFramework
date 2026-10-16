package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.AriaRole;

public class DashBoardPage
{
    Page page;

    public DashBoardPage(Page page)
    {
        this.page= page;
    }

    public void waitForEventToLoad()
    {
        PlaywrightAssertions.assertThat(page.getByRole(AriaRole.LINK,
                new Page.GetByRoleOptions().setName("Browse Events →"))).isVisible();
    }
}
