package universite_paris8.iut.ademir.demo1.Modele.Effets;

import universite_paris8.iut.ademir.demo1.Modele.Monstres.Monstre;

public class EffetPoison extends Effet {

    public EffetPoison () {
        super();
    }

    @Override
    public void appliquer(Monstre monstre) {
        if (this.actif) {
            if (this.compteur % 45 == 0) {
                monstre.recevoirDegats(10);
            }

            this.compteur++;
        }
    }
}