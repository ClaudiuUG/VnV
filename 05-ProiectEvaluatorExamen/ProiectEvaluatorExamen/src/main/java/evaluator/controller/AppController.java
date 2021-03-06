package evaluator.controller;

import java.util.LinkedList;
import java.util.List;

import evaluator.exception.InputValidationFailedException;
import evaluator.model.Intrebare;
import evaluator.model.Statistica;
import evaluator.model.GeneratedTest;
import evaluator.repository.IntrebariRepository;
import evaluator.exception.DuplicateIntrebareException;
import evaluator.exception.NotAbleToCreateStatisticsException;
import evaluator.exception.NotAbleToCreateTestException;

public class AppController {
	
	private IntrebariRepository intrebariRepository;
	
	public AppController(IntrebariRepository intrebariRepository) {
		this.intrebariRepository = intrebariRepository;
	}
	
	public Intrebare addNewIntrebare(Intrebare intrebare) throws DuplicateIntrebareException, InputValidationFailedException
	{
		intrebariRepository.addIntrebare(intrebare);
		return intrebare;
	}
	
	public boolean exists(Intrebare intrebare){
		return intrebariRepository.exists(intrebare);
	}
	
	public GeneratedTest createNewTest() throws NotAbleToCreateTestException{
		
		if(intrebariRepository.getIntrebari().size() < 5)
			throw new NotAbleToCreateTestException("Nu exista suficiente intrebari pentru crearea unui test!(5)");
		if(intrebariRepository.getNumberOfDistinctDomains() < 5)
			throw new NotAbleToCreateTestException("Nu exista suficiente domenii pentru crearea unui test!(5)");
		List<Intrebare> testIntrebari = new LinkedList<Intrebare>();
		List<String> domenii = new LinkedList<String>();
		GeneratedTest test = new GeneratedTest();
		while(testIntrebari.size() != 5){
			Intrebare intrebare = intrebariRepository.pickRandomIntrebare();
			if(!domenii.contains(intrebare.getDomeniu())){
				testIntrebari.add(intrebare);
				domenii.add(intrebare.getDomeniu());
			}
		}
		test.setIntrebari(testIntrebari);
		return test;
	}
	
	public void loadIntrebariFromFile(){
		intrebariRepository.setIntrebari(intrebariRepository.loadIntrebariFromFile());
	}
	
	public Statistica getStatistica() throws NotAbleToCreateStatisticsException{
		
		if(intrebariRepository.getIntrebari().isEmpty())
			throw new NotAbleToCreateStatisticsException("Repository-ul nu contine nicio intrebare!");
		
		Statistica statistica = new Statistica();
		for(String domeniu : intrebariRepository.getDistinctDomains()){
			statistica.add(domeniu, intrebariRepository.getNumberOfIntrebariByDomain(domeniu));
		}
		
		return statistica;
	}
}
