package universite_paris8.iut.ademir.demo1.Modele.Tour.Ciblage;

import javafx.collections.ObservableList;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.Monstre;
import universite_paris8.iut.ademir.demo1.Modele.Tour.Tour;

public class CiblagePlusProchePortail implements StrategieCiblage {

    @Override
    public Monstre choisirCible(ObservableList<Monstre> monstres, Tour tour) {
        Monstre cible = null;

        for (Monstre monstre : monstres) {
            if (tour.estAPorter(monstre)) {
                // si le monstre du for each est plus proche du portail que cible (monstre courant)
                if (cible == null || distancePortail(monstre) < distancePortail(cible)) {
                    cible = monstre;
                }
            }
        }

        return cible;
    }

    private double distancePortail(Monstre monstre) {
        // la position du portail est en cases, on la convertit en pixels
        double dx = monstre.getDernierePositionX() * 64 - monstre.getX();
        double dy = monstre.getDernierePositionY() * 64 - monstre.getY();

        return dx * dx + dy * dy;
    }

    @Override
    public TypeCiblage getType() {
        return TypeCiblage.PLUS_PROCHE_PORTAIL;
    }

    @Override
    public StrategieCiblage strategieSuivante() {
        return new CiblagePremier();
    }
}