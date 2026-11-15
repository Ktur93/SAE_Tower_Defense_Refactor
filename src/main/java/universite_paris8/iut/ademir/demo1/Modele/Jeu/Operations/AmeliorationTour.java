//otp nouveau code
package universite_paris8.iut.ademir.demo1.Modele.Jeu.Operations;

import universite_paris8.iut.ademir.demo1.Modele.Jeu.Partie;
import universite_paris8.iut.ademir.demo1.Modele.Tour.Tour;

public class AmeliorationTour extends OperationPayante {

    private final Tour tour;
    private final int prix;

    public AmeliorationTour(Partie partie, Tour tour, int prix) {
        super(partie);
        this.tour = tour;
        this.prix = prix;
    }

    @Override
    protected boolean conditionsRespectees() {
        return tour != null && tour.getNivT() < tour.getNivMax();
    }

    @Override
    protected int obtenirPrix() {
        return prix;
    }

    @Override
    protected boolean appliquer() {
        tour.ameliorer();
        tour.setNivT(tour.getNivT() + 1);
        return true;
    }
}
