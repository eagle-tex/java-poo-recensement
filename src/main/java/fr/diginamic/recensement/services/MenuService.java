package fr.diginamic.recensement.services;

import fr.diginamic.recensement.entites.Recensement;
import fr.diginamic.recensement.exceptions.DonneeInvalideException;
import java.util.Scanner;

/**
 * Classe représentant un service
 *
 * @author DIGINAMIC
 */
public abstract class MenuService {

  /**
   * Méthode abstraite de traitement que doivent posséder toutes les méthodes de services
   *
   * <p>@param Recensement : recensement
   *
   * @param scanner scanner
   */
  public abstract void traiter(Recensement recensement, Scanner scanner)
      throws DonneeInvalideException;
}
