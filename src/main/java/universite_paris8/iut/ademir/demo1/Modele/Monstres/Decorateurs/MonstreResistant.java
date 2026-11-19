//mpd nouveaucode
package universite_paris8.iut.ademir.demo1.Modele.Monstres.Decorateurs;

import universite_paris8.iut.ademir.demo1.Modele.Monstres.Monstre;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.VarianteMonstre;

public class MonstreResistant extends MonstreDecorateur {

    public MonstreResistant(Monstre monstreDecore) {
        super(monstreDecore);
    }

    @Override
    public void recevoirDegatsPoison(int degats) {
        recevoirDegats(degats);
    }

    @Override
    public void recevoirDegatsGlace(int degats) {
        recevoirDegats(degats);
    }

    @Override
    public VarianteMonstre getVariante() {
        return VarianteMonstre.RESISTANT;
    }
}
