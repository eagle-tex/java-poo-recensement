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
 * Cas d'utilisation: affichage des N villes les plus peuplées d'une région donnée
 *
 * @author DIGINAMIC
 */
public class RechercheVillesPlusPeupleesRegion extends MenuService {

  @Override
  public void traiter(Recensement recensement, Scanner scanner) throws ExceptionApplication {

    System.out.println("Veuillez saisir un nom de région:");
    String nomRegion = scanner.nextLine();

    System.out.println("Veuillez saisir un nombre de villes:");
    String nbVillesStr = scanner.nextLine();
    if (NumberUtils.toInt(nbVillesStr) == 0) {
      throw new NombreNonEntierException("Le nombre de villes saisi doit être entier");
    }
    int nbVilles = Integer.parseInt(nbVillesStr);
    if (nbVilles <= 0) {
      throw new DonneeInvalideException("Le nombre de villes doit être supérieur à 0");
    }

    List<Ville> villesRegions = new ArrayList<Ville>();

    List<Ville> villes = recensement.getVilles();
    for (Ville ville : villes) {
      if (ville.getNomRegion().toLowerCase().startsWith(nomRegion.toLowerCase())) {
        villesRegions.add(ville);
      }
    }

    Collections.sort(villesRegions, new EnsemblePopComparateur(false));
    System.out.println(
        "Les " + nbVilles + " villes les plus peuplées de la région " + nomRegion + " sont :");
    if (villesRegions.size() > 0) {
      for (int i = 0; i < nbVilles; i++) {
        Ville ville = villesRegions.get(i);
        System.out.println(ville.getNom() + " : " + ville.getPopulation() + " habitants.");
      }
    } else {
      throw new DonneeInvalideException("Nom de région non trouvé");
    }
  }
}
