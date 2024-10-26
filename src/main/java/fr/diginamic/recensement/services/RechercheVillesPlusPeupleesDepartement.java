package fr.diginamic.recensement.services;

import fr.diginamic.recensement.entites.Recensement;
import fr.diginamic.recensement.entites.Ville;
import fr.diginamic.recensement.exceptions.DonneeInvalideException;
import fr.diginamic.recensement.exceptions.ExceptionApplication;
import fr.diginamic.recensement.exceptions.NombreNonEntierException;
import fr.diginamic.recensement.services.comparators.EnsemblePopComparateur;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Cas d'utilisation: affichage des N villes les plus peuplées d'une département donné
 *
 * @author DIGINAMIC
 */
public class RechercheVillesPlusPeupleesDepartement extends MenuService {

  @Override
  public void traiter(Recensement recensement, Scanner scanner) throws ExceptionApplication {

    System.out.println("Veuillez saisir un numéro de département:");
    String numDept = scanner.nextLine();

    System.out.println("Veuillez saisir un nombre de villes:");
    String nbVillesStr = scanner.nextLine();
    if (NumberUtils.toInt(nbVillesStr) == 0) {
      throw new NombreNonEntierException("Le nombre de villes saisi doit être entier");
    }
    int nbVilles = Integer.parseInt(nbVillesStr);
    if (nbVilles <= 0) {
      throw new DonneeInvalideException("Le nombre de villes doit être supérieur à 0");
    }

    List<Ville> villesDept = new ArrayList<Ville>();

    List<Ville> villes = recensement.getVilles();
    for (Ville ville : villes) {
      if (ville.getCodeDepartement().equalsIgnoreCase(numDept)) {
        villesDept.add(ville);
      }
    }

    Collections.sort(villesDept, new EnsemblePopComparateur(false));

    if (villesDept.size() > 0) {
      System.out.println(
          "Les " + nbVilles + " villes les plus peuplées du département " + numDept + " :");
      for (int i = 0; i < nbVilles; i++) {
        Ville ville = villesDept.get(i);
        System.out.println(ville.getNom() + " : " + ville.getPopulation() + " habitants.");
      }
    } else {
      throw new DonneeInvalideException("Code/Numéro de département non trouvé");
    }
  }
}
