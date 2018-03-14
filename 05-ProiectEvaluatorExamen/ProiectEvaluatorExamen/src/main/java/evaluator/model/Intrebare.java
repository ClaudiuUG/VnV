package evaluator.model;

import java.util.List;

public class Intrebare {
	
	private String enunt;
	private List<String> varianteRaspuns;
	private int variantaCorecta;
	private String domeniu;
	
	public Intrebare() {
	}
	
	public Intrebare(String enunt, List<String> varianteRaspuns,
					 int variantaCorecta, String domeniu) {
		this.enunt = enunt;
		this.varianteRaspuns = varianteRaspuns;
		this.variantaCorecta = variantaCorecta;
		this.domeniu = domeniu;
	}


	public String getEnunt() {
		return enunt;
	}

	public void setEnunt(String enunt)
    {
		this.enunt = enunt;
	}

	public List<String> getVariante() {
	    return this.varianteRaspuns;
	}

	public List<String> setVarianteRaspuns(List<String> varianteRaspuns)
    {
	    return this.varianteRaspuns =  varianteRaspuns;
	}

	public int getVariantaCorecta() {
		return variantaCorecta;
	}

	public void setVariantaCorecta(int variantaCorecta) {
		this.variantaCorecta = variantaCorecta;
	}

	public String getDomeniu() {
		return domeniu;
	}

	public void setDomeniu(String domeniu) {
		this.domeniu = domeniu;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Intrebare){
			Intrebare i = (Intrebare) obj;
			if(this.enunt.equals(i.getEnunt()) &&
			   this.varianteRaspuns.equals(i.getVariante()) &&
			   this.variantaCorecta == i.getVariantaCorecta() &&
			   this.domeniu.equals(i.getDomeniu()))
			    return true;
		}
		return false;
	}

	@Override
	public String toString()
	{
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(enunt);
		stringBuilder.append("\n");
		stringBuilder.append(domeniu);
		stringBuilder.append("\n");
		for (String variantaRaspuns: this.varianteRaspuns)
		{
			stringBuilder.append(variantaRaspuns);
			stringBuilder.append("\n");
		}
		stringBuilder.append(variantaCorecta);
		stringBuilder.append("\n");
		return stringBuilder.toString();
	}
}
