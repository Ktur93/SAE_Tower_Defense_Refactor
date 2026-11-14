package universite_paris8.iut.ademir.demo1.Modele.Tour;

import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;

public class TourFactory {

    public Tour creerTour(TypeTour type, Position position) {
        if (type == null) {
            return null;
        }

        switch (type) {
            case CANON:
                TourCanon tourC = new TourCanon(position);
                return tourC;
            case ARCHER:
                TourArcher tourA = new TourArcher(position);
                return tourA;
            case GLACE:
                TourGlace tourG = new TourGlace(position);
                return tourG;

            case POISON:
                TourPoison tourP = new TourPoison(position);
                return tourP;
            case FEU:
                TourFeu tourFeu = new TourFeu(position);
                return tourFeu;
            case FOUDRE:
                TourFoudre tourFoudre = new TourFoudre(position);
                return tourFoudre;
            case EXPLOSIVE:
                TourExplosive tourExplosive = new TourExplosive(position);
                return tourExplosive;
            default:
                return null;
        }
    }
}
