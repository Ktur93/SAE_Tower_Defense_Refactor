package universite_paris8.iut.ademir.demo1.Modele.Monstres.Deplacements;

import java.util.ArrayList;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.Monstre;

public class DeplacementMonstre implements StrategieDeplacement {
    private ArrayList<Position> chemin;
    private int indiceChemin;

    public DeplacementMonstre(ArrayList<Position> chemin) {
        this.chemin = chemin;
        this.indiceChemin = 0;
    }

    public void avancer(Monstre monstre) {
        if (!estArrive()) {
            Position posSuivante = chemin.get(indiceChemin + 1);
            double cibleX = posSuivante.getX() * 64;
            double cibleY = posSuivante.getY() * 64;

            deplacerHorizontalement(monstre, cibleX);
            deplacerVerticalement(monstre, cibleY);

            // quand la case est atteinte on passe a la suivante
            if (monstre.getX() == cibleX && monstre.getY() == cibleY) {
                indiceChemin++;
            }
        }
    }

    private void deplacerHorizontalement(Monstre monstre, double cibleX) {
        double nouvellePosition = calculerNouvellePosition(
                monstre.getX(), cibleX, monstre.getVitesse()
        );

        monstre.setX(nouvellePosition);
    }

    private void deplacerVerticalement(Monstre monstre, double cibleY) {
        double nouvellePosition = calculerNouvellePosition(
                monstre.getY(), cibleY, monstre.getVitesse()
        );

        monstre.setY(nouvellePosition);
    }

    private double calculerNouvellePosition(double position, double cible, double vitesse) {
        if (position < cible) {
            position = position + vitesse;

            if (position > cible) {
                position = cible;
            }
        } else if (position > cible) {
            position = position - vitesse;

            if (position < cible) {
                position = cible;
            }
        }

        return position;
    }

    public boolean estArrive() {
        return indiceChemin >= chemin.size() - 1;
    }

    @Override
    public Position getPosition(Monstre monstre) {
        return chemin.get(indiceChemin);
    }
}