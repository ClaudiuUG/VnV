package evaluator.Integration;

import evaluator.controller.AppControllerTest;
import evaluator.exception.DuplicateIntrebareException;
import evaluator.exception.InputValidationFailedException;
import evaluator.exception.NotAbleToCreateStatisticsException;
import evaluator.exception.NotAbleToCreateTestException;
import evaluator.helpers.TestBase;
import evaluator.model.Intrebare;
import evaluator.model.Statistica;
import evaluator.repository.IntrebariRepositoryTest;
import org.junit.Assert;
import org.junit.Test;

public class IntegrationBigBang extends TestBase
{
    private IntrebariRepositoryTest intrebariRepositoryTest = new IntrebariRepositoryTest();
    private AppControllerTest appControllerTest = new AppControllerTest();

    @SuppressWarnings("Duplicates")
    @Test
    public void UnitAddQuestion() throws DuplicateIntrebareException, InputValidationFailedException
    {
        intrebariRepositoryTest.setUp();
        intrebariRepositoryTest.AdauagareIntrebareValida();
        intrebariRepositoryTest.EnuntVid_IntrebareInvalida();
        intrebariRepositoryTest.RaspunsFormatDin3Caractere_IntrebareValida();
        intrebariRepositoryTest.RaspunsFormatDin2Caractere_IntrebareInvalida();
        intrebariRepositoryTest.PrimaLiteraEnuntMinuscula_IntrebareInvalida();
        intrebariRepositoryTest.OSinguraVariantaDeRaspund_IntrebareInvalida();
        intrebariRepositoryTest.VariantaDeRaspunsFaraNumar_IntrebareInvalida();
        intrebariRepositoryTest.DomeniulContineUnSingurCaracter_IntrebareInvalida();
        intrebariRepositoryTest.DomeniulContine30Caractere_IntrebareValida();
        intrebariRepositoryTest.DomeniulContine31Caractere_IntrebareInvalida();
        intrebariRepositoryTest.tearDown();
    }

    @Test
    public void UnitGenerateTest()
    {
        appControllerTest.setUp();
        appControllerTest.DomeniiInsuficiente_TestulNuSeGenereaza();
        appControllerTest.tearDown();
        appControllerTest.setUp();
        appControllerTest.ExistaSuficienteIntrebariSiDomenii_TestGeneratCorect();
        appControllerTest.tearDown();
    }

    @Test
    public void UnitGenerateStatistics() throws NotAbleToCreateStatisticsException
    {
        appControllerTest.setUp();
        appControllerTest.GenerateStatistics_StatisticsCreated();
        appControllerTest.tearDown();
        appControllerTest.setUp();
        appControllerTest.EmptyQuestionRepository_GenerateStatistics_StatisticsNotCreated();
        appControllerTest.tearDown();
    }


    @SuppressWarnings("Duplicates")
    @Test
    public void Integration_BigBang() throws DuplicateIntrebareException, InputValidationFailedException, NotAbleToCreateTestException, NotAbleToCreateStatisticsException
    {
        int numberOfQuestions = 5;
        PopulateQuestionRepo(numberOfQuestions);
        Intrebare intrebare = GetValidQuestion();
        controller.addNewIntrebare(GetValidQuestion());
        numberOfQuestions = repository.getIntrebari().size();
        controller.createNewTest();
        Statistica statistica = controller.getStatistica();

        Assert.assertTrue(repository.exists(intrebare));
        AssertStatistics(statistica, numberOfQuestions);
    }
}
