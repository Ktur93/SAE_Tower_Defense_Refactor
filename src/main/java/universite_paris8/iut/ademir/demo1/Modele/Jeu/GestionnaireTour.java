
package universite_paris8.iut.ademir.demo1.Modele.Jeu;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Carte;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;
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

    //CSP ancien code
    // public boolean placerTour(Tour tour, Carte carte, Partie partie) {
    //CSP nouveau code
    public boolean placerTour(Tour tour, Partie partie) {

        if (tour == null) {
            return false;
        }

        Position position = tour.getPosition();

        //CSP ancien code
        // if (!carte.estCaseTour(position.getX(), position.getY())) {
        if (!Carte.getInstance().estCaseTour(position.getX(), position.getY())) {
            return false;
        }

        if (partie.getRubis() < tour.getPrix()) {
            return false;
        }

        partie.retirerRubis(tour.getPrix());
        this.tours.add(tour);

        return true;
    }

    public void faireAttaquerTours(
            int compteur,
            ObservableList<Monstre> monstres,
            ObservableList<Projectile> projectiles) {

        for (Tour tour : tours) {
            Projectile projectile = tour.attaquer(monstres, compteur);

            if (projectile != null) {
                projectiles.add(projectile);
            }
        }
    }

    public void faireAmeliorerTours(Tour tour, Partie partie) {

        if (tour.getNivT() < tour.getNivMax()
                && partie.getRubis() >= prixAmelioration) {

            tour.ameliorer();
            partie.retirerRubis(prixAmelioration);
            tour.setNivT(tour.getNivT() + 1);
        }
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
