package evaluator.controller;

import evaluator.exception.NotAbleToCreateTestException;
import evaluator.helpers.TestHelper;
import evaluator.model.GeneratedTest;
import evaluator.model.Intrebare;
import evaluator.repository.IntrebariRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class AppControllerTest
{
    TestHelper helper;
    AppController controller;
    IntrebariRepository repository;

    @Before
    public void setUp()
    {
        helper = new TestHelper();
        repository = new IntrebariRepository(helper.QuestionPersistanceFileName);
        controller = new AppController(repository);
    }

    @After
    public void tearDown()
    {
        repository.removeAll();
    }

    // WBT_1
    @Test
    public void ExistaSuficientaIntrebariSiDomenii_TestGeneratCorect()
    {
        PopulateQuestionRepo(5);

        AssertTestCreation(true);
    }

    private void PopulateQuestionRepo(int numberOfQuestions)
    {
        for (int i=0; i < numberOfQuestions; ++i)
        {
            AddQuestionWithCustomDomain("Domeniu " + i);
        }
    }

    private void AddQuestionWithCustomDomain(String domain)
    {
        Intrebare question = TestHelper.GetValidQuestion();
        question.setDomeniu(domain);
        try
        {
            repository.addIntrebare(question);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void AssertTestCreation(boolean testShouldCreate)
    {
        boolean testCreationFailed = false;
        GeneratedTest test = null;
        try
        {
            test = controller.createNewTest();
        } catch (NotAbleToCreateTestException e)
        {
            testCreationFailed = true;
            e.printStackTrace();
        }
        if (testShouldCreate)
        {
            Assert.assertFalse(testCreationFailed);
            Assert.assertNotNull(test);
            return;
        }
        Assert.assertTrue(testCreationFailed);
        Assert.assertNull(test);
    }

    // WBT_1
    @Test
    public void IntrebariInsuficiente_TestulNuSeGenereaza()
    {
        PopulateQuestionRepo(4);

        AssertCreateNewTestThrowsException(NotAbleToCreateTestException.class);
    }

    private void AssertCreateNewTestThrowsException(Class<NotAbleToCreateTestException> expectedExceptionClass)
    {
        Exception actualException = null;
        try
        {
            controller.createNewTest();
        } catch (NotAbleToCreateTestException e)
        {
            actualException = e;
        }
        finally
        {
            Assert.assertNotNull(actualException);
            Assert.assertTrue(expectedExceptionClass == actualException.getClass());
        }
    }

    @Test
    public void DomeniiInsuficiente_TestulNuSeGenereaza()
    {
        PopulateQuestionRepo(5);

        List<Intrebare> intrebari = repository.getIntrebari();
        intrebari.remove(intrebari.size() - 1);
        intrebari.add(intrebari.get(0));
        repository.setIntrebari(intrebari);

        AssertCreateNewTestThrowsException(NotAbleToCreateTestException.class);
    }
}