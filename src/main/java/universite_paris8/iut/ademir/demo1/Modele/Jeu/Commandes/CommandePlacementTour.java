//cmd nouveau code
// Memorise la tour placee et le prix paye pour pouvoir les annuler.
package universite_paris8.iut.ademir.demo1.Modele.Jeu.Commandes;

import universite_paris8.iut.ademir.demo1.Modele.Jeu.GestionnaireTour;
import universite_paris8.iut.ademir.demo1.Modele.Jeu.Partie;
import universite_paris8.iut.ademir.demo1.Modele.Tour.Tour;

public class CommandePlacementTour implements Commande {

    private final Partie partie;
    private final GestionnaireTour gestionnaire;
    private final Tour tour;
    private int prixPaye;
    private boolean executee;

    public CommandePlacementTour(Partie partie, GestionnaireTour gestionnaire, Tour tour) {
        this.partie = partie;
        this.gestionnaire = gestionnaire;
        this.tour = tour;
    }

    @Override
    public boolean executer() {
        boolean reussie = false;
        if (!executee && tour != null && !gestionnaire.getTours().contains(tour)) {
            int prix = tour.getPrix();
            if (gestionnaire.placerTour(tour, partie)) {
                prixPaye = prix;
                executee = true;
                reussie = true;
            }
        }
        return reussie;
    }

    @Override
    public boolean annuler() {
        boolean reussie = false;
        if (executee && !partie.getVagueEnCours() && partie.getEtatPartie().peutInteragir()
                && tour.getNivT() == 1 && gestionnaire.getTours().remove(tour)) {
            partie.ajouterRubis(prixPaye);
            executee = false;
            reussie = true;
        }
        return reussie;
    }
}
