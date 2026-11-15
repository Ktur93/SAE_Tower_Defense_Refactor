//eta nouveau code
// Fait tourner le jeu puis choisit la transition de victoire ou de defaite.
package universite_paris8.iut.ademir.demo1.Modele.Jeu.Etats;

import universite_paris8.iut.ademir.demo1.Modele.Jeu.Partie;

public class EtatEnCours implements EtatPartie {

    @Override
    public void mettreAJour(Partie partie) {
        partie.mettreAJourJeu();
        if (partie.portailMort()) {
            if (partie.getVagueEnCours()) {
                partie.changerEtat(new EtatPortailDetruit());
            } else {
                partie.changerEtat(new EtatDefaite());
            }
        } else if (partie.getToutesLesVaguesTerminees()) {
            partie.changerEtat(new EtatVictoire());
        }
    }

    @Override
    public boolean peutInteragir() {
        return true;
    }
}
