package evaluator.util;


import evaluator.exception.InputValidationFailedException;
import evaluator.model.Intrebare;

import java.util.List;

public class InputValidation {

	public static void validateEnunt(String enunt) throws InputValidationFailedException{
        if(enunt == null)
            throw new InputValidationFailedException("Enuntul este nul!");
		enunt = enunt.trim();
		if(enunt.equals(""))
			throw new InputValidationFailedException("Enuntul este vid!");
		if(!Character.isUpperCase(enunt.charAt(0)))
			throw new InputValidationFailedException("Prima litera din enunt nu e majuscula!");
		if(!String.valueOf(enunt.charAt(enunt.length()-1)).equals("?"))
			throw new InputValidationFailedException("Ultimul caracter din enunt nu e '?'!");
		if(enunt.length() > 1000)
			throw new InputValidationFailedException("Lungimea enuntului depaseste 100 de caractere!");
		
	}

	public static void validateVarianteRaspuns(List<String> varianteRaspuns) throws InputValidationFailedException {
		if (varianteRaspuns.size() != 3) {
			throw new InputValidationFailedException("Numarul de variante de raspuns trebuie sa fie 3.");
		}
		for (int i = 0; i < varianteRaspuns.size(); i++)
		{
			String variantaRaspuns = varianteRaspuns.get(i);
			int numarVarianta = i + 1;
			validateVariantaRaspuns(variantaRaspuns, numarVarianta);
		}
	}

	public static void validateVariantaRaspuns(String varianta, Integer numarVarianta) throws InputValidationFailedException{

	    if (varianta == null)
            throw new InputValidationFailedException("Enuntul este nul!");
		varianta = varianta.trim();
		if(varianta.equals(""))
			throw new InputValidationFailedException("Varianta " + numarVarianta + " este vida!");
		if(varianta.length() < 3)
		    throw new InputValidationFailedException("Varianta " + numarVarianta + "are lungimea mai mica decat 3");
		if(!String.valueOf(varianta.charAt(0)).equals(numarVarianta.toString()) || !String.valueOf(varianta.charAt(1)).equals(")"))
			throw new InputValidationFailedException("Varianta " + numarVarianta + " nu incepe cu '" + numarVarianta + ")'!");
		if(varianta.length() > 100)
			throw new InputValidationFailedException("Lungimea variantei " + numarVarianta + " depaseste 100 de caractere!" );
	}
	
	public static void validateVariantaCorecta(int variantaCorecta) throws InputValidationFailedException{
		if(variantaCorecta < 0 || variantaCorecta > 3 )
			throw new InputValidationFailedException("Varianta corecta nu este unul dintre numerele raspunsurilor posibile");
	}
	
	public static void validateDomeniu(String domeniu) throws InputValidationFailedException{

        if(domeniu == null)
            throw new InputValidationFailedException("Domeniul este null!");
		domeniu = domeniu.trim();
		if(domeniu.equals(""))
			throw new InputValidationFailedException("Domeniul este vid!");
		if(!Character.isUpperCase(domeniu.charAt(0)))
			throw new InputValidationFailedException("Prima litera din domeniu nu e majuscula!");
		if(domeniu.length() < 2)
            throw new InputValidationFailedException("Lungimea domeniului depaseste 30 de caractere!");
		if(domeniu.length() > 30)
			throw new InputValidationFailedException("Lungimea domeniului depaseste 30 de caractere!");
		
	}

	public static void validateQuestion(Intrebare intrebare) throws InputValidationFailedException
    {
		InputValidation.validateEnunt(intrebare.getEnunt());
		InputValidation.validateVarianteRaspuns(intrebare.getVarianteRaspuns());
		InputValidation.validateDomeniu(intrebare.getDomeniu());
	}
}
