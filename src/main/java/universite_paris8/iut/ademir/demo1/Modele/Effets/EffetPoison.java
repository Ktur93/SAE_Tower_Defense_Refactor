package universite_paris8.iut.ademir.demo1.Modele.Effets;

import universite_paris8.iut.ademir.demo1.Modele.Monstres.Monstre;

public class EffetPoison extends Effet {

    public EffetPoison () {
        super();
    }

    @Override
    protected void appliquerEffet(Monstre monstre) {
        if (this.compteur % 45 == 0) {
            monstre.recevoirDegats(10);
        }

        this.compteur++;
    }
}