package evaluatorExamenWeb.features.search;

import evaluatorExamenWeb.steps.serenity.EndUserSteps;
import evaluatorExamenWeb.steps.serenity.UserSteps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Pending;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(SerenityRunner.class)
public class SearchStory
{
    @Managed(uniqueSession = true)
    public WebDriver webdriver;

    @Steps
    public UserSteps anna;

    @Test
    public void searching_by_keyword_examen_should_display_the_corresponding_article() {
        anna.is_the_home_page();
        anna.looks_for("examen");
        anna.should_see_definition("Informații despre examenul în vederea obținerii certificatului de competență lingvistică la limba engleză pentru studenții anului III și studenții aflați în prelungire de studii");
    }

    @Test
    public void searching_by_keyword_sesiune_should_not_display_exam_article() {
        anna.is_the_home_page();
        anna.looks_for("sesiune");
        anna.should_not_see_definition("Informații despre examenul în vederea obținerii certificatului de competență lingvistică la limba engleză pentru studenții anului III și studenții aflați în prelungire de studii");
    }

    @Test
    public void logoClick_goes_to_homepage()
    {
        anna.is_the_home_page();
        anna.should_see_definition("Dr. Ioan Pop, Institutul de Tehnologie din Karlsruhe: Tehnologii cuantice: începutul unei noi revoluţii informatice");
    }

    @Test
    public void after_search_not_on_homepage()
    {
        anna.is_the_home_page();
        anna.should_not_see_definition("Informații despre examenul în vederea obținerii certificatului de competență lingvistică la limba engleză pentru studenții anului III și studenții aflați în prelungire de studii");
    }

//    @Test
//    public void searching_by_keyword_banana_should_display_the_corresponding_article() {
//        anna.is_the_home_page();
//        anna.looks_for("pear");
//        anna.should_see_definition("An edible fruit produced by the pear tree, similar to an apple but elongated towards the stem.");
//    }
//
//    @Pending
//    @Test
//    public void searching_by_ambiguious_keyword_should_display_the_disambiguation_page() {
//    }
}
