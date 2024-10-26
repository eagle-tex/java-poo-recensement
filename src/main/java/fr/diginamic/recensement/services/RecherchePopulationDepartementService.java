package fr.diginamic.recensement.services;

import fr.diginamic.recensement.entites.Recensement;
import fr.diginamic.recensement.entites.Ville;
import fr.diginamic.recensement.exceptions.ExceptionApplication;
import fr.diginamic.recensement.exceptions.NombreNonEntierException;
import java.util.List;
import java.util.Scanner;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * Recherche et affichage de la population d'un département
 *
 * @author DIGINAMIC
 */
public class RecherchePopulationDepartementService extends MenuService {

  @Override
  public void traiter(Recensement rec, Scanner scanner) throws ExceptionApplication {

    System.out.println("Quel est le code du département recherché ? ");
    String choix = scanner.nextLine();

    if (!NumberUtils.isDigits(choix)) {
      throw new NombreNonEntierException("Le département doit être un entier.");
    }

    List<Ville> villes = rec.getVilles();
    int somme = 0;
    for (Ville ville : villes) {
      if (ville.getCodeDepartement().equalsIgnoreCase(choix)) {
        somme += ville.getPopulation();
      }
    }
    if (somme > 0) {
      System.out.println("Population du département " + choix + " : " + somme);
    } else {
      System.out.println("Département " + choix + " non trouvé.");
    }
  }
}
