//otp nouveau code
package universite_paris8.iut.ademir.demo1.Modele.Jeu.Operations;

import universite_paris8.iut.ademir.demo1.Modele.Cartes.Carte;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;
import universite_paris8.iut.ademir.demo1.Modele.Jeu.Partie;

public class AchatCase extends OperationPayante {

    private final Carte carte;
    private final Position position;
    private final int prix;

    public AchatCase(Partie partie, Carte carte, Position position, int prix) {
        super(partie);
        this.carte = carte;
        this.position = position;
        this.prix = prix;
    }

    @Override
    protected boolean conditionsRespectees() {
        return position != null
                && carte.estDansCarte(position.getX(), position.getY())
                && carte.Tuile(position.getX(), position.getY()) == Carte.CASE_BLOQUEE;
    }

    @Override
    protected int obtenirPrix() {
        return prix;
    }

    @Override
    protected boolean appliquer() {
        return carte.caseDebloquer(position);
    }
}
