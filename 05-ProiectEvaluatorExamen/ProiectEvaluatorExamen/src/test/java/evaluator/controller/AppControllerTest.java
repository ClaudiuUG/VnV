package evaluator.controller;

import evaluator.exception.NotAbleToCreateStatisticsException;
import evaluator.exception.NotAbleToCreateTestException;
import evaluator.helpers.TestBase;
import evaluator.model.GeneratedTest;
import evaluator.model.Intrebare;
import evaluator.model.Statistica;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class AppControllerTest extends TestBase
{
    // WBT_1
    @Test
    public void ExistaSuficienteIntrebariSiDomenii_TestGeneratCorect()
    {
        PopulateQuestionRepo(5);

        AssertTestCreation(true);
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

    @Test
    public void GenerateStatistics_StatisticsCreated() throws NotAbleToCreateStatisticsException
    {
        int numberOfQuestions = 5;
        PopulateQuestionRepo(numberOfQuestions);

        Statistica statistica = controller.getStatistica();

        AssertStatistics(statistica, numberOfQuestions);
    }

    @Test
    public void EmptyQuestionRepository_GenerateStatistics_StatisticsNotCreated()
    {
        boolean exceptionWasThrown = false;
        try
        {
            Statistica statistica = controller.getStatistica();
        } catch (NotAbleToCreateStatisticsException e)
        {
            exceptionWasThrown = true;
        }

        Assert.assertTrue(exceptionWasThrown);
    }
}