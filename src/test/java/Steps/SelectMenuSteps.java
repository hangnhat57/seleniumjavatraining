package Steps;

import Base.BaseSteps;
import Base.BaseUtils;
import Base.DataStore;
import Pages.SelectmenuPage;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class SelectMenuSteps extends BaseSteps{
    public SelectMenuSteps(BaseUtils base, DataStore data) {
        super(base, data);
    }
    SelectmenuPage i = new SelectmenuPage(base.driver);
    @Given("^I navigate to \"([^\"]*)\"$")
    public void iNavigateTo(String url) throws Throwable {
        base.driver.navigate().to(url);
    }

    @When("^I click on Select a speed drop down list$")
    public void iClickOnSelectASpeedDropDownList() throws Throwable {
        // Write code here that turns the phrase above into concrete actions

        i.ClickSpeedSelect();
    }

    @And("^I choose \"([^\"]*)\" option$")
    public void iChooseOption(String value) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        i.SelectOption(value);
    }

    @Then("^The Select a speed drop down list should display \"([^\"]*)\"$")
    public void theSelectASpeedDropDownListShouldDisplay(String actual) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        i.VerifySelectDisplay(actual);
    }
}
