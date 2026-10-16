package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.AriaRole;

public class LoginPage
{
    Page page;
    String base_url;

    public LoginPage(Page page, String base_url)
    {
        this.page = page;
        this.base_url = base_url;
    }

    public static final String emailPlaceholder = "you@email.com";
    public static final String passwordLabel = "Password";

    public DashBoardPage LogInToApplication()
    {
        page.navigate(base_url);
        System.out.println(page.title());
        PlaywrightAssertions.assertThat(page).hasTitle("EventHub — Discover & Book Events");
        //Login and verify successfully logged In
        //page.getByLabel("Email").fill("rahulshetty1@yahoo.com");
        page.getByPlaceholder(emailPlaceholder).fill("rahulshetty1@yahoo.com");

        page.getByLabel(passwordLabel).fill("Magiclife1!");

        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sign In")).click();
        DashBoardPage dashBoardPage = new DashBoardPage(page);
        return dashBoardPage;
    }
}
