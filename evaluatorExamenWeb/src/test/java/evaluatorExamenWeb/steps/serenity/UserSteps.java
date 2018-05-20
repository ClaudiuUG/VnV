package evaluatorExamenWeb.steps.serenity;

import evaluatorExamenWeb.pages.BileteDictionaryPage;
import evaluatorExamenWeb.pages.DictionaryPage;
import net.thucydides.core.annotations.Step;
import org.hamcrest.Matcher;
import org.hamcrest.core.StringContains;
import org.junit.Assert;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasItem;

public class UserSteps
{
    BileteDictionaryPage dictionaryPage;

    @Step
    public void enters(String keyword) {
        dictionaryPage.enter_keywords(keyword);
    }

    @Step
    public void starts_search() {
        dictionaryPage.lookup_terms();
    }

    @Step
    public void should_see_definition(String definition) {
        assertThat(dictionaryPage.getDefinitions(), hasItem(containsString(definition)));
    }

    @Step
    public void should_not_see_definition(String definition) {
        Assert.assertFalse(!dictionaryPage.getDefinitions().contains(definition));
    }

    @Step
    public void is_the_home_page() {
        dictionaryPage.open();
    }

    @Step
    public void looks_for(String term) {
        enters(term);
        starts_search();
    }

    @Step
    public void clicks_on_logo() {
        dictionaryPage.click_logo();
    }

    @Step
    public void should_be_on_home_page() {
        Assert.assertTrue(dictionaryPage.compatibleWithUrl("http://www.cs.ubbcluj.ro/"));
    }

    @Step
    public void should_not_be_on_homepage() {
        Assert.assertFalse(dictionaryPage.compatibleWithUrl("http://www.cs.ubbcluj.ro/"));
    }

    @Step
    public void clicks_on_first_article() {

    }
}
