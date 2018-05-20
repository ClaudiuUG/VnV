package evaluatorExamenWeb.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.stream.Collectors;

@DefaultUrl("http://www.cs.ubbcluj.ro/")
public class BileteDictionaryPage extends PageObject
{
    @FindBy(name="s")
    private WebElementFacade searchTerms;

    @FindBy(className = "logo")
    private WebElementFacade logo;

//    @FindBy(className="btn btn-link btn-searchbox btn-lg")
//    private WebElementFacade lookupButton;


    public void enter_keywords(String keyword) {
        searchTerms.type(keyword);
    }

    public void lookup_terms() {
        Robot robot = null;
        try
        {

            robot = new Robot();
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            robot.delay(200);
        } catch (AWTException e)
        {
            e.printStackTrace();
        }
        // selenium.keyPressNative("10"); // Enter
        // lookupButton.click();

    }

    public List<String> getDefinitions() {
        WebElementFacade definitionList = find(By.id("content"));
        return definitionList.findElements(By.className("title")).stream()
                .map( element -> element.getText() )
                .collect(Collectors.toList());
    }

    public void click_logo() {
        logo.click();
    }
}
