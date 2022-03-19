package application;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controlleur {

		@FXML
		TextField tfNomCommune;

		@FXML
		TextField tfCodePostal;

		@FXML
		Label lbinfo;

		@FXML
		Label lbIndice;

		ArrayList<Commune> listFilter = new ArrayList<Commune>();
		private boolean b;
		private int cpt = -1;

		@FXML
		private void actionChercheNom(ActionEvent evt) {
			listFilter.clear();
			b = true;
			if (tfNomCommune.getText().toCharArray().length < 3) {
				lbinfo.setText("entrer au moins 3 caractéres");
			} else {
				for (Commune commune : Commune.listeCommunes) {
					if (commune.getNom().contains(tfNomCommune.getText().toUpperCase())) {
						listFilter.add(commune);
					}

				}
				lbinfo.setText(listFilter.size() + " commune(s) trouvée(s)");
				tfCodePostal.clear();
			}
		}

		@FXML
		private void actionChercheCP(ActionEvent evt) {
			listFilter.clear();
			b = true;
			if (tfCodePostal.getText().toCharArray().length < 2) {
				lbinfo.setText("entrer au moins 2 caractères");
			} else {
				for (Commune commune : Commune.listeCommunes) {
					if (commune.getCodePostal().startsWith(tfCodePostal.getText())) {
						listFilter.add(commune);
					}

				}
				lbinfo.setText(listFilter.size() + " commune(s) trouv�e(s)");
				tfNomCommune.clear();
			}
		}

		@FXML
		private void actionAvant(ActionEvent evt) {

			if (!(listFilter.isEmpty())) {

				if (b == true) {
					cpt = 1;
					lbIndice.setText("1/" + listFilter.size());
					tfNomCommune.setText(listFilter.get(0).getNom());
					tfCodePostal.setText(listFilter.get(0).getCodePostal());
				}

				if (cpt > 1 && b == false) {
					cpt--;
					lbIndice.setText(cpt + "/" + listFilter.size());
					tfNomCommune.setText(listFilter.get(cpt - 1).getNom());
					tfCodePostal.setText(listFilter.get(cpt - 1).getCodePostal());
				}
				b = false;

			}
		}

		@FXML
		private void actionApres(ActionEvent evt) {
			if (!(listFilter.isEmpty())) {

				if (b == true) {
					cpt = 1;
					lbIndice.setText(cpt + "/" + listFilter.size());
					tfNomCommune.setText(listFilter.get(0).getNom());
					tfCodePostal.setText(listFilter.get(0).getCodePostal());
				}

				if (cpt >= 1 && cpt < listFilter.size() && b == false) {
					cpt++;
					lbIndice.setText(cpt + "/" + listFilter.size());
					tfNomCommune.setText(listFilter.get(cpt - 1).getNom());
					tfCodePostal.setText(listFilter.get(cpt - 1).getCodePostal());
				}
				b = false;
			}
		}

		@FXML
		public void LireFichier() throws IOException {
			Path P1 = Paths.get("C:/");
			Path P2 = Paths.get(P1.toString(), "CP.txt");
			System.out.println(P1.toString());
			System.out.println(P2.toString());

			if (Files.exists(P2)) {

				List<String> lignes = Files.readAllLines(P2);

				for (String string : lignes) {

					try {
						new Commune(string);
					} catch (Exception e) {
						// TODO: handle exception
						System.out.println(e.getMessage());
					}

				}

			} else {
				System.out.println("le fichier n'existe pas");
			}

		}

		public void initialize() throws Exception {
			// TODO Auto-generated method stub
			this.LireFichier();

			for (int i = 0; i < 5; i++) {
				System.out.println(Commune.listeCommunes.get(i).toString());

			}

			System.out.println("taille:"+ Commune.listeCommunes.size());
		}

	}
	

