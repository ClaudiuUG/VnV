package evaluator.repository;

import evaluator.exception.DuplicateIntrebareException;
import evaluator.exception.InputValidationFailedException;
import evaluator.model.Intrebare;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/*
* Test class for black box testing the addition of the questions to the repository.
* This was done for Lab2
 */
public class IntrebariRepositoryTest
{
    public final String testQuestionPersistanceFile = "../resources/intrebariTest.txt";

    IntrebariRepository repository;

    @Before
    public void setUp()
    {
        repository = new IntrebariRepository(testQuestionPersistanceFile);
    }

    @After
    public void tearDown()
    {
        repository.removeAll();
    }

    @Test
    public void AdauagareIntrebareValida() throws DuplicateIntrebareException, InputValidationFailedException
    {
        Intrebare question = GetVaidQuestion();

        repository.addIntrebare(question);

        AssertQuestionAddedToRepository(question);
    }

    private Intrebare GetVaidQuestion()
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

    private void AssertQuestionAddedToRepository(Intrebare question)
    {
        Assert.assertTrue(repository.exists(question));
    }

    @Test
    public void AdaugareIntrebareEnuntVid() throws DuplicateIntrebareException
    {
        Intrebare question = GetVaidQuestion();
        question.setEnunt("");

        AssertInputValidationFailedAtRepoAdd(question);
    }

    private void AssertInputValidationFailedAtRepoAdd(Intrebare question) throws DuplicateIntrebareException
    {
        boolean inputValidationFailed = false;
        try
        {
            repository.addIntrebare(question);
        } catch (InputValidationFailedException e)
        {
            inputValidationFailed = true;
        }

        Assert.assertTrue(inputValidationFailed);
    }

    @Test
    public void RaspunsValidFormatDin3Caractere() throws DuplicateIntrebareException, InputValidationFailedException
    {
        Intrebare question = GetVaidQuestion();
        question.setVarianteRaspuns(Arrays.asList("1)3", "2)4", "3)a"));

        repository.addIntrebare(question);

        AssertQuestionAddedToRepository(question);
    }

    @Test
    public void RaspunsInvalidFormatDin2Caractere() throws DuplicateIntrebareException
    {
        Intrebare question = GetVaidQuestion();
        question.setVarianteRaspuns(Arrays.asList("1)", "2)", "3)"));

        AssertInputValidationFailedAtRepoAdd(question);
    }
}