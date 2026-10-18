package universite_paris8.iut.ademir.demo1.Modele.Tour;

import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;

public class TourFactory {
    public Tour creerTour(String type, Position position) {
        if (type == null) {
            return null;
        }
        switch (type) {
            case "CANON":
                TourCanon tourC = new TourCanon(position);
                return tourC;
            case "ARCHER":
                TourArcher tourA = new TourArcher(position);
                return tourA;
            case "GLACE":
                TourGlace tourG = new TourGlace(position);
                return tourG;
            case "POISON":
                TourPoison tourP = new TourPoison(position);
                return tourP;
            default:
                return null;
        }
    }
}
