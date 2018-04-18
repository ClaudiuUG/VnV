package evaluator.model;

import java.util.LinkedList;
import java.util.List;

public class GeneratedTest
{

	private List<Intrebare> intrebari;
	
	public GeneratedTest() {
		intrebari = new LinkedList<Intrebare>(); 
	}
	
	public List<Intrebare> getIntrebari() {
		return intrebari;
	}
	
	public void setIntrebari(List<Intrebare> intrebari) {
		this.intrebari = intrebari;
	}

    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        for (Intrebare intrebare : this.intrebari)
        {
            stringBuilder.append(intrebare);
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
