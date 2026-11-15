//otp nouveau code
// Template commun : verifie, realise l action puis preleve son prix.
package universite_paris8.iut.ademir.demo1.Modele.Jeu.Operations;

import universite_paris8.iut.ademir.demo1.Modele.Jeu.Partie;

public abstract class OperationPayante {

    private final Partie partie;

    protected OperationPayante(Partie partie) {
        this.partie = partie;
    }

    // Le Template fixe l'ordre des etapes ; les sous-classes precisent les regles.
    public final boolean executer() {
        if (!conditionsRespectees()) {
            return false;
        }

        // On garde le prix controle, meme si l'action fait evoluer le prix suivant.
        int prix = obtenirPrix();
        if (prix < 0 || partie.getRubis() < prix) {
            return false;
        }

        if (!appliquer()) {
            return false;
        }

        partie.retirerRubis(prix);
        return true;
    }

    protected abstract boolean conditionsRespectees();

    protected abstract int obtenirPrix();

    // Une action qui renvoie false doit laisser le jeu dans son etat initial.
    protected abstract boolean appliquer();
}
