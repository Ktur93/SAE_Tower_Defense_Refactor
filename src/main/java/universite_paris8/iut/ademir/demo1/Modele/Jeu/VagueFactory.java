package universite_paris8.iut.ademir.demo1.Modele.Jeu;

import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;

import java.util.ArrayList;

public class VagueFactory {

    public ArrayList<Vague> creerToutesLesVagues(
            ArrayList<Position> chemin1,
            ArrayList<Position> chemin2,
            ArrayList<Position> chemin3) {

        ArrayList<Vague> vagues = new ArrayList<>();

        vagues.add(creerVague1(chemin1));
        vagues.add(creerVague2(chemin1, chemin2));
        vagues.add(creerVague3(chemin1, chemin2));
        vagues.add(creerVague4(chemin1, chemin2, chemin3));
        vagues.add(creerVague5(chemin1, chemin2, chemin3));

        return vagues;
    }

    private Vague creerVague1(ArrayList<Position> chemin1) {
        Vague vague = new Vague();
        vague.creeVague1(chemin1);
        return vague;
    }

    private Vague creerVague2(
            ArrayList<Position> chemin1,
            ArrayList<Position> chemin2) {

        Vague vague = new Vague();
        vague.creeVague2(chemin1, chemin2);
        return vague;
    }

    private Vague creerVague3(
            ArrayList<Position> chemin1,
            ArrayList<Position> chemin2) {

        Vague vague = new Vague();
        vague.creeVague3(chemin1, chemin2);
        return vague;
    }

    private Vague creerVague4(
            ArrayList<Position> chemin1,
            ArrayList<Position> chemin2,
            ArrayList<Position> chemin3) {

        Vague vague = new Vague();
        vague.creeVague4(chemin1, chemin2, chemin3);
        return vague;
    }

    private Vague creerVague5(
            ArrayList<Position> chemin1,
            ArrayList<Position> chemin2,
            ArrayList<Position> chemin3) {

        Vague vague = new Vague();
        vague.creeVague5(chemin1, chemin2, chemin3);
        return vague;
    }
}
