package fr.diginamic.recensement.services;

import fr.diginamic.recensement.entites.Recensement;
import fr.diginamic.recensement.entites.Ville;
import fr.diginamic.recensement.exceptions.DonneeInvalideException;
import java.util.List;
import java.util.Scanner;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * Recherche et affichage de toutes les villes d'un département dont la population est comprise
 * entre une valeur min et une valeur max renseignées par l'utilisateur.
 *
 * @author DIGINAMIC
 */
public class RecherchePopulationBorneService extends MenuService {

  @Override
  public void traiter(Recensement rec, Scanner scanner) throws DonneeInvalideException {

    System.out.println("Quel est le code du département recherché ? ");
    String choix = scanner.nextLine();

    System.out.println("Choisissez une population minimum (en milliers d'habitants): ");
    String saisieMin = scanner.nextLine();

    System.out.println("Choisissez une population maximum (en milliers d'habitants): ");
    String saisieMax = scanner.nextLine();

    if (NumberUtils.toInt(saisieMin) == 0) {
      throw new DonneeInvalideException("La population minimum est invalide");
    }

    if (NumberUtils.toInt(saisieMax) == 0) {
      throw new DonneeInvalideException("La population maximum est invalide");
    }

    int min = Integer.parseInt(saisieMin) * 1000;
    int max = Integer.parseInt(saisieMax) * 1000;

    if (min < 0) {
      throw new DonneeInvalideException("La population minimum est négative");
    }

    if (max < 0) {
      throw new DonneeInvalideException("La population maximum est négative");
    }

    if (min > max) {
      throw new DonneeInvalideException("Population minimum est supérieure à population maximum ");
    }

    List<Ville> villes = rec.getVilles();
    boolean codeDeptValide = false;
    for (Ville ville : villes) {
      if (ville.getCodeDepartement().equalsIgnoreCase(choix)) {
        codeDeptValide = true;
        if (ville.getPopulation() >= min && ville.getPopulation() <= max) {
          System.out.println(ville);
        }
      }
    }
    if (!codeDeptValide) {
      throw new DonneeInvalideException("Code département inconnu");
    }
  }
}
