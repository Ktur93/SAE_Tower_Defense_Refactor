package universite_paris8.iut.ademir.demo1.Modele.Tour.Ciblage;

import universite_paris8.iut.ademir.demo1.Modele.Monstres.Monstre;

public class CiblagePlusFort extends CiblageSelonPv {

    @Override
    protected boolean estMeilleureCible(Monstre monstre, Monstre cible) {
        return monstre.getPv() > cible.getPv();
    }

    @Override
    public TypeCiblage getType() {
        return TypeCiblage.PLUS_FORT;
    }

    @Override
    public StrategieCiblage strategieSuivante() {
        return new CiblagePremier();
    }
}