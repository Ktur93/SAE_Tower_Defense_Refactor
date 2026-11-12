package universite_paris8.iut.ademir.demo1.Modele.Jeu;

import javafx.collections.ObservableList;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.*;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.MonstresResistants.*;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.MonstresRapides.*;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.MonstresSoigneurs.*;

import java.util.ArrayList;

public class Vague {

    private ArrayList<Monstre> monstresVague;
    private int indiceMonstre;
    private long dernierSpawn;
    private long delaiSpawn;


    public Vague () {
        this.monstresVague = new ArrayList<>();
        this.indiceMonstre = 0;
        this.dernierSpawn = 0;
        this.delaiSpawn = 40;
    }


    public void creeVague1(ArrayList<Position> chemin1) {
        creeVague1(chemin1, 1);
    }

    public void creeVague1(ArrayList<Position> chemin1, int niveau) {
        this.monstresVague.clear();

        // Dans tous les niveaux, on commence avec 5 zombies classique
        for (int i = 0; i < 5; i++) {
            monstresVague.add(new Zombie(chemin1));
        }

        // a partir du niveau 2 on ajoute un rapide
        if (niveau >= 2) {
            monstresVague.add(new ZombieRapide(chemin1));
        }

        // a partir du niveau 3 on ajoute un résistant
        if (niveau >= 3) {
            monstresVague.add(new ZombieResistant(chemin1));
        }

        // a partir du niveau 4 on ajoute un soigneur
        if (niveau >= 4) {
            monstresVague.add(new ZombieSoigneur(chemin1));
        }

        // a partir du niveau 5 on ajoute deux monstres
        if (niveau >= 5) {
            monstresVague.add(new ZombieRapide(chemin1));
            monstresVague.add(new ZombieResistant(chemin1));
        }
    }



    public void creeVague2(
            ArrayList<Position> chemin1,
            ArrayList<Position> chemin2) {

        creeVague2(chemin1, chemin2, 1);
    }

    public void creeVague2(
            ArrayList<Position> chemin1,
            ArrayList<Position> chemin2,
            int niveau) {

        this.monstresVague.clear();

        // Monstres classiques dans tous les niveaux
        for (int i = 0; i < 10; i++) {
            monstresVague.add(new Zombie(chemin1));
            monstresVague.add(new Araignee(chemin2));
        }

        if (niveau >= 2) {
            monstresVague.add(new ZombieRapide(chemin1));
            monstresVague.add(new AraigneeRapide(chemin2));
        }

        if (niveau >= 3) {
            monstresVague.add(new ZombieResistant(chemin1));
            monstresVague.add(new AraigneeResistante(chemin2));
        }

        if (niveau >= 4) {
            monstresVague.add(new ZombieSoigneur(chemin1));
            monstresVague.add(new AraigneeSoigneuse(chemin2));
        }

        if (niveau >= 5) {
            monstresVague.add(new ZombieRapide(chemin1));
            monstresVague.add(new ZombieResistant(chemin1));
        }
    }

    public void creeVague3(
            ArrayList<Position> chemin1,
            ArrayList<Position> chemin2) {

        creeVague3(chemin1, chemin2, 1);
    }

    public void creeVague3(
            ArrayList<Position> chemin1,
            ArrayList<Position> chemin2,
            int niveau) {

        this.monstresVague.clear();

        for (int i = 0; i < 15; i++) {
            monstresVague.add(new Zombie(chemin1));
            monstresVague.add(new Araignee(chemin2));
            monstresVague.add(new Squelette(chemin1));
        }

        if (niveau >= 2) {
            monstresVague.add(new ZombieRapide(chemin1));
            monstresVague.add(new AraigneeRapide(chemin2));
            monstresVague.add(new SqueletteRapide(chemin1));
        }

        if (niveau >= 3) {
            monstresVague.add(new ZombieResistant(chemin1));
            monstresVague.add(new AraigneeResistante(chemin2));
            monstresVague.add(new SqueletteResistant(chemin1));
        }

        if (niveau >= 4) {
            monstresVague.add(new ZombieSoigneur(chemin1));
            monstresVague.add(new AraigneeSoigneuse(chemin2));
            monstresVague.add(new SqueletteSoigneur(chemin1));
        }

        if (niveau >= 5) {
            monstresVague.add(new ZombieRapide(chemin1));
            monstresVague.add(new ZombieResistant(chemin1));
        }
    }

    public void creeVague4(
            ArrayList<Position> chemin1,
            ArrayList<Position> chemin2,
            ArrayList<Position> chemin3) {

        creeVague4(chemin1, chemin2, chemin3, 1);
    }

    public void creeVague4(
            ArrayList<Position> chemin1,
            ArrayList<Position> chemin2,
            ArrayList<Position> chemin3,
            int niveau) {

        this.monstresVague.clear();

        for (int i = 0; i < 15; i++) {
            monstresVague.add(new Zombie(chemin1));
            monstresVague.add(new Araignee(chemin2));
            monstresVague.add(new Squelette(chemin3));
            monstresVague.add(new Pillager(chemin2));
        }

        if (niveau >= 2) {
            monstresVague.add(new ZombieRapide(chemin1));
            monstresVague.add(new AraigneeRapide(chemin2));
            monstresVague.add(new SqueletteRapide(chemin3));
            monstresVague.add(new PillagerRapide(chemin2));
        }

        if (niveau >= 3) {
            monstresVague.add(new ZombieResistant(chemin1));
            monstresVague.add(new AraigneeResistante(chemin2));
            monstresVague.add(new SqueletteResistant(chemin3));
            monstresVague.add(new PillagerResistant(chemin2));
        }

        if (niveau >= 4) {
            monstresVague.add(new ZombieSoigneur(chemin1));
            monstresVague.add(new AraigneeSoigneuse(chemin2));
            monstresVague.add(new SqueletteSoigneur(chemin3));
            monstresVague.add(new PillagerSoigneur(chemin2));
        }

        if (niveau >= 5) {
            monstresVague.add(new ZombieRapide(chemin1));
            monstresVague.add(new ZombieResistant(chemin1));
        }
    }

    public void creeVague5(
            ArrayList<Position> chemin1,
            ArrayList<Position> chemin2,
            ArrayList<Position> chemin3) {

        creeVague5(chemin1, chemin2, chemin3, 1);
    }

    public void creeVague5(
            ArrayList<Position> chemin1,
            ArrayList<Position> chemin2,
            ArrayList<Position> chemin3,
            int niveau) {

        this.monstresVague.clear();

        for (int i = 0; i < 18; i++) {
            monstresVague.add(new Zombie(chemin1));
            monstresVague.add(new Araignee(chemin2));
            monstresVague.add(new Squelette(chemin3));
            monstresVague.add(new Pillager(chemin3));
        }

        monstresVague.add(new ChauveSouris(chemin1));

        if (niveau >= 2) {
            monstresVague.add(new ZombieRapide(chemin1));
            monstresVague.add(new AraigneeRapide(chemin2));
            monstresVague.add(new SqueletteRapide(chemin3));
            monstresVague.add(new PillagerRapide(chemin3));
            monstresVague.add(new ChauveSourisRapide(chemin1));
        }

        if (niveau >= 3) {
            monstresVague.add(new ZombieResistant(chemin1));
            monstresVague.add(new AraigneeResistante(chemin2));
            monstresVague.add(new SqueletteResistant(chemin3));
            monstresVague.add(new PillagerResistant(chemin3));
            monstresVague.add(new ChauveSourisResistante(chemin1));
        }

        if (niveau >= 4) {
            monstresVague.add(new ZombieSoigneur(chemin1));
            monstresVague.add(new AraigneeSoigneuse(chemin2));
            monstresVague.add(new SqueletteSoigneur(chemin3));
            monstresVague.add(new PillagerSoigneur(chemin3));
            monstresVague.add(new ChauveSourisSoigneuse(chemin1));
        }

        if (niveau >= 5) {
            monstresVague.add(new ZombieRapide(chemin1));
            monstresVague.add(new ZombieResistant(chemin1));
        }

        // Le boss est ajouté en dernier
        monstresVague.add(new Boss(chemin1));
    }

    public void mettreAJourVague(int compteur, ObservableList<Monstre> monstresPartie) {
        if (indiceMonstre >= monstresVague.size()) {

        } else if (indiceMonstre == 0 || compteur - dernierSpawn >= delaiSpawn) {
            monstresPartie.add(monstresVague.get(indiceMonstre));
            indiceMonstre++;
            dernierSpawn = compteur;
        }
    }

    public boolean tousLesMonstresEnvoyes() {
        return indiceMonstre >= monstresVague.size();
    }

    public void setIndiceMonstreZero() {
        this.indiceMonstre = 0;
    }

    public void ajouterMonstre(Monstre monstre) {
        monstresVague.add(monstre);
    }
}