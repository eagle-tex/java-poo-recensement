package fr.diginamic.recensement.services;

import fr.diginamic.recensement.entites.Recensement;
import fr.diginamic.recensement.entites.Ville;
import java.util.List;
import java.util.Scanner;

/**
 * Recherche et affichage de la population d'une région
 *
 * @author DIGINAMIC
 */
public class RecherchePopulationRegionService extends MenuService {

  @Override
  public void traiter(Recensement rec, Scanner scanner) {

    System.out.println(
        "Quel est le nom (ou le début de nom, 3 lettres minimum) de la région recherchée ? ");
    String choix = scanner.nextLine();

    List<Ville> villes = rec.getVilles();
    int somme = 0;
    String nom = null;
    for (Ville ville : villes) {
      if (ville.getNomRegion().toLowerCase().equals(choix.toLowerCase())
          || (choix.length() >= 3
              && ville.getNomRegion().toLowerCase().startsWith(choix.toLowerCase()))
          || ville.getCodeRegion().toLowerCase().equals(choix.toLowerCase())) {
        somme += ville.getPopulation();
        nom = ville.getNomRegion();
      }
    }
    if (somme > 0) {
      System.out.println("Population de la région " + nom + " : " + somme);
    } else {
      System.out.println("Région " + choix + " non trouvée.");
    }
  }
}
