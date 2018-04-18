package evaluator.helpers;

import evaluator.model.Intrebare;

import java.util.Arrays;
import java.util.List;

public class TestHelper
{
    public static final String QuestionPersistanceFileName = "../../resources/intrebariTest.txt";

    public static Intrebare GetValidQuestion()
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
}
