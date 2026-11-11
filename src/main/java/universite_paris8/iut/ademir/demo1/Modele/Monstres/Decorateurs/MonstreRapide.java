//mpd nouveaucode
package universite_paris8.iut.ademir.demo1.Modele.Monstres.Decorateurs;

import javafx.collections.ObservableList;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.Monstre;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.VarianteMonstre;

public class MonstreRapide extends MonstreDecorateur {

    private static final double MULTIPLICATEUR_VITESSE = 1.5;
    private final double vitesseDeBase;

    public MonstreRapide(Monstre monstreDecore) {
        super(monstreDecore);
        this.vitesseDeBase = monstreDecore.getVitesse();
    }


    @Override
    public double getVitesseNormal() {
        if (getPv() <= getPvMax() / 2.0) {
            return vitesseDeBase * MULTIPLICATEUR_VITESSE;
        }
        return vitesseDeBase;
    }

    @Override
    public void agir(ObservableList<Monstre> monstres) {
        super.agir(monstres);
        if (!estGlace()) {
            setVitesse(getVitesseNormal());
        }
    }


    @Override
    public VarianteMonstre getVariante() {
        return VarianteMonstre.RAPIDE;
    }
}
