package evaluator.repository;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;


import evaluator.model.Intrebare;
import evaluator.exception.DuplicateIntrebareException;

public class IntrebariRepository {

    private String fileName;
	private List<Intrebare> intrebari;
	
	public IntrebariRepository(String fileName) {
		setIntrebari(new LinkedList<Intrebare>());
		this.fileName = fileName;
	}
	
	public void addIntrebare(Intrebare intrebare) throws DuplicateIntrebareException{
		if(exists(intrebare))
			throw new DuplicateIntrebareException("Intrebarea deja exista!");
		intrebari.add(intrebare);
		appendQuestionToFile(fileName, intrebare);
	}
	
	public boolean exists(Intrebare i){
		for(Intrebare intrebare : intrebari)
			if(intrebare.equals(i))
				return true;
		return false;
	}

    private void appendQuestionToFile(String f, Intrebare intrebare)
    {
        // TODO: implement append question to file
    }
	
	public Intrebare pickRandomIntrebare(){
		Random random = new Random();
		return intrebari.get(random.nextInt(intrebari.size()));
	}
	
	public int getNumberOfDistinctDomains(){
		return getDistinctDomains().size();
		
	}
	
	public Set<String> getDistinctDomains(){
		Set<String> domains = new TreeSet<String>();
		for(Intrebare intrebre : intrebari)
			domains.add(intrebre.getDomeniu());
		return domains;
	}
	
	public List<Intrebare> getIntrebariByDomain(String domain){
		List<Intrebare> intrebariByDomain = new LinkedList<Intrebare>();
		for(Intrebare intrebare : intrebari){
			if(intrebare.getDomeniu().equals(domain)){
				intrebariByDomain.add(intrebare);
			}
		}
		
		return intrebariByDomain;
	}
	
	public int getNumberOfIntrebariByDomain(String domain){
		return getIntrebariByDomain(domain).size();
	}
	
	public List<Intrebare> loadIntrebariFromFile(){
		
		List<Intrebare> intrebari = new LinkedList<Intrebare>();
		BufferedReader br = null; 
		String line = null;
		List<String> intrebareAux;
		Intrebare intrebare;

		try{
			br = new BufferedReader(new FileReader(fileName));
			line = br.readLine();
			while(line != null){
				intrebareAux = new LinkedList<String>();
				while(!line.equals("##")){
					intrebareAux.add(line);
					line = br.readLine();
				}
				intrebare = new Intrebare();
				intrebare.setEnunt(intrebareAux.get(0));
				List<String> varianteRaspuns = new LinkedList<>();
				varianteRaspuns.add(intrebareAux.get(1));
				varianteRaspuns.add(intrebareAux.get(2));
				varianteRaspuns.add(intrebareAux.get(3));
				intrebare.setVarianteRaspuns(varianteRaspuns);
				intrebare.setVariantaCorecta(Integer.parseInt(intrebareAux.get(4)));
				intrebare.setDomeniu(intrebareAux.get(5));
				intrebari.add(intrebare);
				line = br.readLine();
			}

		}
		catch (IOException e) {
			// TODO: handle exception
		}
		finally{
			try {
			    if (br != null) {
                    br.close();
                }
			} catch (IOException e) {
				// TODO: handle exception
			}
		}
		
		return intrebari;
	}
	
	public List<Intrebare> getIntrebari() {
		return intrebari;
	}

	public void setIntrebari(List<Intrebare> intrebari) {
		this.intrebari = intrebari;
	}
	
}
