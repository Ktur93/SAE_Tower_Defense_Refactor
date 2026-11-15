//mpd nouveaucode
package universite_paris8.iut.ademir.demo1.Modele.Monstres.Decorateurs;

import javafx.collections.ObservableList;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.Monstre;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.VarianteMonstre;

public class MonstreSoigneur extends MonstreDecorateur {

    private static final int DELAI_SOIN = 180;
    private static final int RAYON_SOIN = 3;
    private static final int QUANTITE_SOIN = 10;
    private int compteurSoin;

    public MonstreSoigneur(Monstre monstreDecore) {
        super(monstreDecore);
        this.compteurSoin = 0;
    }

    @Override
    public void agir(ObservableList<Monstre> monstres) {
        super.agir(monstres);
        compteurSoin++;
        if (compteurSoin >= DELAI_SOIN) {
            soignerMonstres(monstres);
            compteurSoin = 0;
        }
    }

    public void soignerMonstres(ObservableList<Monstre> monstres) {
        for (Monstre cible : monstres) {
            if (peutSoigner(cible)) {
                cible.setPv(Math.min(cible.getPv() + QUANTITE_SOIN, cible.getPvMax()));
            }
        }
    }

    private boolean peutSoigner(Monstre cible) {
        if (cible.getMonstreDeBase() == getMonstreDeBase() || cible.estMort()) {
            return false;
        }
        int dx = getPosition().getX() - cible.getPosition().getX();
        int dy = getPosition().getY() - cible.getPosition().getY();
        return dx * dx + dy * dy <= RAYON_SOIN * RAYON_SOIN;
    }

    @Override
    public VarianteMonstre getVariante() {
        return VarianteMonstre.SOIGNEUR;
    }
}
