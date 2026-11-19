
package universite_paris8.iut.ademir.demo1.Modele.Jeu;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Carte;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;
import universite_paris8.iut.ademir.demo1.Modele.Jeu.Operations.AmeliorationTour;
import universite_paris8.iut.ademir.demo1.Modele.Jeu.Operations.PlacementTour;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.Monstre;
import universite_paris8.iut.ademir.demo1.Modele.Projectile.Projectile;
import universite_paris8.iut.ademir.demo1.Modele.Tour.Tour;

public class GestionnaireTour {

    private final ObservableList<Tour> tours;
    private int prixAmelioration;

    public GestionnaireTour() {
        this.tours = FXCollections.observableArrayList();
        this.prixAmelioration = 75;
    }

    //CSP nouveau code
    //otp nouveau code
    // Delegue le placement et son paiement a l operation specialisee.
    public boolean placerTour(Tour tour, Partie partie) {
        PlacementTour placement = new PlacementTour(partie, Carte.getInstance(), tour, tours);
        return placement.executer();
    }

    public void faireAttaquerTours(int compteur, ObservableList<Monstre> monstres, ObservableList<Projectile> projectiles) {

        for (Tour tour : tours) {
            Projectile projectile = tour.attaquer(monstres, compteur);

            if (projectile != null) {
                projectiles.add(projectile);
            }
        }
    }

    //otp nouveau code
    // Delegue l amelioration avec le prix actuel du gestionnaire.
    public void faireAmeliorerTours(Tour tour, Partie partie) {
        AmeliorationTour amelioration = new AmeliorationTour(partie, tour, prixAmelioration);
        amelioration.executer();
    }

    public Tour trouverTour(Position position) {
        for (Tour tour : tours) {
            if (tour.getPosition().equals(position)) {
                return tour;
            }
        }
        return null;
    }

    public ObservableList<Tour> getTours() {
        return tours;
    }

    public int getPrixAmelioration() {
        return this.prixAmelioration;
    }

    public void augmenterPrixAmelioration() {
        this.prixAmelioration += 75;
    }

    public void recommencer() {
        this.tours.clear();
        this.prixAmelioration = 75;
    }
}
