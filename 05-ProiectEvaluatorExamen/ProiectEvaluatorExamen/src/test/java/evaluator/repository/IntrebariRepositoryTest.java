package evaluator.repository;

import evaluator.exception.DuplicateIntrebareException;
import evaluator.exception.InputValidationFailedException;
import evaluator.helpers.TestHelper;
import evaluator.model.Intrebare;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/*
* Test class for black box testing the addition of the questions to the repository.
* This was done for Lab2
 */
public class IntrebariRepositoryTest
{
    TestHelper helper;
    IntrebariRepository repository;

    @Before
    public void setUp()
    {
        repository = new IntrebariRepository(TestHelper.QuestionPersistanceFileName);
    }

    @After
    public void tearDown()
    {
        repository.removeAll();
    }

    /// TC1_ECP
    @Test
    public void AdauagareIntrebareValida() throws DuplicateIntrebareException, InputValidationFailedException
    {
        Intrebare question = TestHelper.GetValidQuestion();

        repository.addIntrebare(question);

        AssertQuestionAddedToRepository(question);
    }

    private void AssertQuestionAddedToRepository(Intrebare question)
    {
        Assert.assertTrue(repository.exists(question));
    }

    /// TC2_ECP
    @Test
    public void EnuntVid_IntrebareInvalida() throws DuplicateIntrebareException
    {
        Intrebare question = TestHelper.GetValidQuestion();
        question.setEnunt("");

        AssertInputValidationFailsAtRepoAdd(question);
    }

    private void AssertInputValidationFailsAtRepoAdd(Intrebare question) throws DuplicateIntrebareException
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

    /// TC08_BVA
    @Test
    public void RaspunsFormatDin3Caractere_IntrebareValida() throws DuplicateIntrebareException, InputValidationFailedException
    {
        Intrebare question = TestHelper.GetValidQuestion();
        question.setVarianteRaspuns(Arrays.asList("1)3", "2)4", "3)a"));

        repository.addIntrebare(question);

        AssertQuestionAddedToRepository(question);
    }

    /// TC09_BVA
    @Test
    public void RaspunsFormatDin2Caractere_IntrebareInvalida() throws DuplicateIntrebareException
    {
        Intrebare question = TestHelper.GetValidQuestion();
        question.setVarianteRaspuns(Arrays.asList("1)", "2)", "3)"));

        AssertInputValidationFailsAtRepoAdd(question);
    }

    // TC4_ECP
    @Test
    public void PrimaLiteraEnuntMinuscula_IntrebareInvalida() throws DuplicateIntrebareException
    {
        Intrebare question = TestHelper.GetValidQuestion();
        question.setEnunt("de ce?");

        AssertInputValidationFailsAtRepoAdd(question);
    }

    // TC5_ECP
    @Test
    public void OSinguraVariantaDeRaspund_IntrebareInvalida() throws DuplicateIntrebareException
    {
        Intrebare question = TestHelper.GetValidQuestion();
        List<String> varianteRaspuns = new LinkedList<>();
        varianteRaspuns.add("1) D-aia");
        question.setVarianteRaspuns(varianteRaspuns);

        AssertInputValidationFailsAtRepoAdd(question);
    }

    // TC9_ECP
    @Test
    public void VariantaDeRaspunsFaraNumar_IntrebareInvalida() throws DuplicateIntrebareException
    {
        Intrebare question = TestHelper.GetValidQuestion();
        question.getVarianteRaspuns().set(1, "Da.");

        AssertInputValidationFailsAtRepoAdd(question);
    }

    // TC10_BVA
    @Test
    public void DomeniulContineUnSingurCaracter_IntrebareInvalida() throws DuplicateIntrebareException
    {
        Intrebare question = TestHelper.GetValidQuestion();
        question.setDomeniu("A");

        AssertInputValidationFailsAtRepoAdd(question);
    }

    // TC11_BVA
    @Test
    public void DomeniulContine30Caractere_IntrebareValida() throws DuplicateIntrebareException, InputValidationFailedException
    {
        Intrebare question = TestHelper.GetValidQuestion();
        question.setDomeniu("B12345678901234567890123456789");

        repository.addIntrebare(question);
        AssertQuestionAddedToRepository(question);
    }


    // TC12_BVA
    @Test
    public void DomeniulContine31Caractere_IntrebareInvalida() throws DuplicateIntrebareException, InputValidationFailedException
    {
        Intrebare question = TestHelper.GetValidQuestion();
        question.setDomeniu("B123456789012345678901234567890");

        AssertInputValidationFailsAtRepoAdd(question);
    }
}