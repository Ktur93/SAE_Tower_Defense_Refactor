package universite_paris8.iut.ademir.demo1.Modele.Monstres;

import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;

public class DeplacementVolant implements StrategieDeplacement {

    private static final int TAILLE_TUILE = 64;

    private Position destination;
    private boolean arrive;

    public DeplacementVolant(Position destination) {
        this.destination = destination;
        this.arrive = false;
    }

    @Override
    public void avancer(Monstre monstre) {
        if (arrive) {
            return;
        }


        double destinationX = destination.getX() * TAILLE_TUILE;
        double destinationY = destination.getY() * TAILLE_TUILE;

        // on commence par calculer la difference entre la position du monstre et le portail
        double dx = destinationX - monstre.getX();
        double dy = destinationY - monstre.getY();

        // Puis on calcule la distance
        double distance = Math.sqrt(dx * dx + dy * dy);

        // Si le monstre peut atteindre le portail à ce déplacement,
        // on le place exactement dessus.
        if (distance <= monstre.getVitesse()) {
            monstre.setX(destinationX);
            monstre.setY(destinationY);
            arrive = true;
        } else {
            monstre.setX(monstre.getX() + monstre.getVitesse() * dx / distance);
            monstre.setY(monstre.getY() + monstre.getVitesse() * dy / distance);
        }
    }

    @Override
    public boolean estArrive() {
        return arrive;
    }

    @Override
    public Position getPosition(Monstre monstre) {
        int colonne = (int) (monstre.getX() / TAILLE_TUILE);
        int ligne = (int) (monstre.getY() / TAILLE_TUILE);

        return new Position(colonne, ligne);
    }
}