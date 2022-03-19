package application;

import java.util.ArrayList;

public class Commune {
	
	public static ArrayList<Commune> listeCommunes = new ArrayList<Commune>();
	private String codePostal;
	private String nom;

	public Commune(String ligne) throws Exception {
		this.codePostal = ligne.substring(3, 8);
		this.nom = ligne.substring(13, ligne.toCharArray().length);
		String reg = "[a-zA-Z]+";

		if (nom.toCharArray().length < 3) {
			throw new Exception("Le nom est trop petit");

		} else if (codePostal.matches(reg) == true) {
			throw new Exception("Le CP n'est pas numerique");
		}

		listeCommunes.add(this);
		
	}

	public String getCodePostal() {
		return codePostal;
	}

	public String getNom() {
		return nom;
	}

	public String toString() {

		return "Code:" + codePostal + "; Nom:" + nom;

	}


}
