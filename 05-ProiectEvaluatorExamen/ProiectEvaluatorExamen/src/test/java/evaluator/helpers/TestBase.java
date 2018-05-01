package evaluator.helpers;

import evaluator.controller.AppController;
import evaluator.model.Intrebare;
import evaluator.model.Statistica;
import evaluator.repository.IntrebariRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

import java.util.Arrays;
import java.util.List;

public class TestBase
{
    protected static final String QuestionPersistanceFileName = "../../resources/intrebariTest.txt";

    protected AppController controller;
    protected IntrebariRepository repository;

    @Before
    public void setUp()
    {
        repository = new IntrebariRepository(QuestionPersistanceFileName);
        controller = new AppController(repository);
    }

    @After
    public void tearDown()
    {
        repository.removeAll();
    }

    protected void PopulateQuestionRepo(int numberOfQuestions)
    {
        for (int i=0; i < numberOfQuestions; ++i)
        {
            AddQuestionWithCustomDomain("Domeniu " + i);
        }
    }

    protected void AddQuestionWithCustomDomain(String domain)
    {
        Intrebare question = GetValidQuestion();
        question.setDomeniu(domain);
        try
        {
            repository.addIntrebare(question);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    protected static Intrebare GetValidQuestion()
    {
        String enunt = "De ce?";
        List<String> raspunsuri = Arrays.asList(
                "1) D-aia.",
                "2) Da.",
                "3) Nu."
        );
        int variantaCorecta = 1;
        String domeniu = "Filozofie";
        return new Intrebare(enunt, raspunsuri,variantaCorecta, domeniu);
    }

    protected void AssertStatistics(Statistica statistica, int numberOfQuestions)
    {
        Assert.assertNotNull(statistica);
        int mapSize = statistica.getIntrebariDomenii().size();
        Assert.assertEquals(numberOfQuestions, mapSize);
        AssertStatisticsStringIsFormatted(statistica.toString());
    }

    private void AssertStatisticsStringIsFormatted(String statisticsString)
    {
        String[] lines = statisticsString.split("\\r?\\n");
        for (String line: lines)
        {
            AssertLineIsFormatted(line);
        }
    }

    private void AssertLineIsFormatted(String line)
    {
        String[] lineComponents = line.split(": ");
        int expectedLineComponentsNumber = 2;
        Assert.assertEquals(expectedLineComponentsNumber, lineComponents.length);
        String domain = lineComponents[0];
        Assert.assertNotEquals(0, domain.length());
        String number = lineComponents[1];
        Assert.assertNotEquals(0, number);
        Integer.parseInt(number);
    }
}
