//otp nouveau code
package universite_paris8.iut.ademir.demo1.Modele.Jeu.Operations;

import javafx.collections.ObservableList;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Carte;
import universite_paris8.iut.ademir.demo1.Modele.Jeu.Partie;
import universite_paris8.iut.ademir.demo1.Modele.Tour.Tour;

public class PlacementTour extends OperationPayante {

    private final Carte carte;
    private final Tour tour;
    private final ObservableList<Tour> tours;

    public PlacementTour(Partie partie, Carte carte, Tour tour, ObservableList<Tour> tours) {
        super(partie);
        this.carte = carte;
        this.tour = tour;
        this.tours = tours;
    }

    @Override
    protected boolean conditionsRespectees() {
        return tour != null
                && tour.getPosition() != null
                && carte.estCaseTour(tour.getPosition().getX(), tour.getPosition().getY());
    }

    @Override
    protected int obtenirPrix() {
        return tour.getPrix();
    }

    @Override
    protected boolean appliquer() {
        return tours.add(tour);
    }
}
