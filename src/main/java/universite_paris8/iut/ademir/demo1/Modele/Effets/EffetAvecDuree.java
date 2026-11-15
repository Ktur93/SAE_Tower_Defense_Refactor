package universite_paris8.iut.ademir.demo1.Modele.Effets;

public abstract class EffetAvecDuree extends Effet {

    @Override
    public void activer() {
        super.activer();

        // un nouveau coup relance la duree
        compteur = 0;
    }
}