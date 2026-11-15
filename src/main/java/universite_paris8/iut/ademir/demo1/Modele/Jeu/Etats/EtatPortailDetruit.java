//eta nouveau code
// Termine la vague deja lancee avant d afficher la defaite.
package universite_paris8.iut.ademir.demo1.Modele.Jeu.Etats;

import universite_paris8.iut.ademir.demo1.Modele.Jeu.Partie;

public class EtatPortailDetruit implements EtatPartie {

    @Override
    public void mettreAJour(Partie partie) {
        partie.mettreAJourJeu();
        if (!partie.getVagueEnCours()) {
            partie.changerEtat(new EtatDefaite());
        }
    }
}
