package Steps;

import Base.BaseSteps;
import Base.BaseUtils;
import Base.DataStore;
import Pages.GoogleHomePage;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class GoogleSearchSteps extends BaseSteps{
    public GoogleSearchSteps(BaseUtils base, DataStore data) {
        super(base, data);
    }
    @Given("^I navigate to Google page$")
    public void iNavigateToGooglePage() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        base.driver.navigate().to(BaseUtils.url());
    }

    @And("^I search for \"([^\"]*)\"$")
    public void iSearchFor(String value) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        GoogleHomePage page = new GoogleHomePage(base.driver);
        page.SearchForKeyword(value);
    }

    @Then("^I should be redirected to result page$")
    public void iShouldBeRedirectedToResultPage() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        System.out.println(base.driver.getCurrentUrl());
    }
}
