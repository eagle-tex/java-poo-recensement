package fr.diginamic.recensement.services;

import fr.diginamic.recensement.entites.Recensement;
import fr.diginamic.recensement.entites.Ville;
import java.util.List;
import java.util.Scanner;

/**
 * Recherche et affichage de la population d'une ville
 *
 * @author DIGINAMIC
 */
public class RecherchePopulationVilleService extends MenuService {

  @Override
  public void traiter(Recensement rec, Scanner scanner) {

    System.out.println(
        "Quel est le nom de la ville recherchée (ou début de nom, 3 lettres minimum) ? ");
    String choix = scanner.nextLine();

    List<Ville> villes = rec.getVilles();
    for (Ville ville : villes) {
      if (ville.getNom().equalsIgnoreCase(choix)
          || (choix.length() >= 3
              && ville.getNom().toLowerCase().startsWith(choix.toLowerCase()))) {
        System.out.println(ville);
      }
    }
  }
}
