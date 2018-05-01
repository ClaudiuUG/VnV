package evaluator.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import evaluator.exception.DuplicateIntrebareException;
import evaluator.exception.InputValidationFailedException;
import evaluator.exception.NotAbleToCreateTestException;
import evaluator.model.Intrebare;
import evaluator.model.Statistica;

import evaluator.controller.AppController;
import evaluator.exception.NotAbleToCreateStatisticsException;
import evaluator.model.GeneratedTest;
import evaluator.repository.IntrebariRepository;
import evaluator.util.InputValidation;

//functionalitati
//i.	 adaugarea unei noi intrebari pentru un anumit domeniu (enunt intrebare, raspuns 1, raspuns 2, raspuns 3, raspunsul corect, domeniul) in setul de intrebari disponibile;
//ii.	 crearea unui nou test (testul va contine 5 intrebari alese aleator din cele disponibile, din domenii diferite);
//iii.	 afisarea unei statistici cu numarul de intrebari organizate pe domenii.

public class StartApp {

	private static final String fileName = "intrebari.txt";
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

		IntrebariRepository repository = new IntrebariRepository(fileName);
		AppController appController = new AppController(repository);
		
		boolean activ = true;
		String optiune = null;
		
		while(activ){

            appController.loadIntrebariFromFile();

			System.out.println("");
			System.out.println("1.Adauga intrebare");
			System.out.println("2.Creeaza test");
			System.out.println("3.Statistica");
			System.out.println("4.Exit");
			System.out.println("");
			
			optiune = console.readLine();
			
			switch(optiune){
			case "1" :
				handleNewQuestionInsertion(appController, console);
				break;
			case "2" :
			    handleNewTestInsertion(appController, console);
				break;
			case "3" :
				Statistica statistica;
				try {
					statistica = appController.getStatistica();
					System.out.println(statistica);
				} catch (NotAbleToCreateStatisticsException e) {
					System.out.println(e.getMessage());
				}
				
				break;
			case "4" :
				activ = false;
				break;
			default:
				break;
			}
		}
		
	}

	private static void handleNewQuestionInsertion(AppController appController, BufferedReader console) throws IOException
    {
		try
		{
			Intrebare intrebare = readNewQuestion(appController, console);
			appController.addNewIntrebare(intrebare);
			System.out.println(intrebare);
		} catch (DuplicateIntrebareException e)
		{
			e.printStackTrace();
		} catch (InputValidationFailedException e)
		{
			e.printStackTrace();
		}
	}

	private static Intrebare readNewQuestion(AppController appController, BufferedReader console) throws IOException
	{
        try
        {
            Intrebare intrebare = new Intrebare();
            System.out.println("Introduceti domeniul intrebarii:");
            String domeniu = console.readLine();
            InputValidation.validateDomeniu(domeniu);
            intrebare.setDomeniu(domeniu);

            System.out.println("Introduceti enuntul intrebarii: ");
            String enunt = console.readLine();
            InputValidation.validateEnunt(enunt);
            intrebare.setEnunt(enunt);

            List<String> varianteRaspuns = new LinkedList<>();
            for (int i = 1; i <=3; i++)
            {
                System.out.println("Introduceti varianta " + i + " de raspuns.");
                String variantaRaspuns = console.readLine();
                InputValidation.validateVariantaRaspuns(variantaRaspuns, i);
                varianteRaspuns.add(variantaRaspuns);
            }
            intrebare.setVarianteRaspuns(varianteRaspuns);

            System.out.println("Introduceti varianta corecta");
            int variantaCorecta = Integer.parseInt(console.readLine());
            intrebare.setVariantaCorecta(variantaCorecta);

            return intrebare;
        } catch (InputValidationFailedException e)
        {
            System.out.println("Valoare invalida. " + e.getMessage());
            return null;
        } catch (NumberFormatException e)
        {
            System.out.println("Valoarea introdusa nu e un numar.");
            return null;
        }
    }

    private static void handleNewTestInsertion(AppController appController, BufferedReader console) throws IOException
    {
        try
        {
            GeneratedTest test = appController.createNewTest();
            System.out.println(test);
        } catch (NotAbleToCreateTestException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
