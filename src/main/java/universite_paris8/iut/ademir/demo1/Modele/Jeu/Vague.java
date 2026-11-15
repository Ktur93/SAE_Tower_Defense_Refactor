package universite_paris8.iut.ademir.demo1.Modele.Jeu;

import javafx.collections.ObservableList;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.*;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.Decorateurs.MonstreRapide;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.Decorateurs.MonstreResistant;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.Decorateurs.MonstreSoigneur;

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

        for (int i = 0; i < 4; i++) {
            monstresVague.add(new Zombie(chemin1));
        }
        monstresVague.add(new MonstreResistant(new Zombie(chemin1)));

        if (niveau >= 2) {
            monstresVague.add(new MonstreRapide(new Zombie(chemin1)));
        }
        if (niveau >= 3) {
            monstresVague.add(new MonstreResistant(new Zombie(chemin1)));
        }
        if (niveau >= 4) {
            monstresVague.add(new MonstreSoigneur(new Zombie(chemin1)));
        }
        ajouterMonstresNiveau5(chemin1, niveau);
    }

    public void creeVague2(ArrayList<Position> chemin1, ArrayList<Position> chemin2) {
        creeVague2(chemin1, chemin2, 1);
    }

    public void creeVague2(ArrayList<Position> chemin1, ArrayList<Position> chemin2, int niveau) {
        this.monstresVague.clear();

        for (int i = 0; i < 9; i++) {
            monstresVague.add(new Zombie(chemin1));
            monstresVague.add(new Araignee(chemin2));
        }

        monstresVague.add(new MonstreResistant(new Zombie(chemin1)));
        monstresVague.add(new MonstreResistant(new Araignee(chemin2)));

        if (niveau >= 2) {
            monstresVague.add(new MonstreRapide(new Zombie(chemin1)));
            monstresVague.add(new MonstreRapide(new Araignee(chemin2)));
        }
        if (niveau >= 3) {
            monstresVague.add(new MonstreResistant(new Zombie(chemin1)));
            monstresVague.add(new MonstreResistant(new Araignee(chemin2)));
        }
        if (niveau >= 4) {
            monstresVague.add(new MonstreSoigneur(new Zombie(chemin1)));
            monstresVague.add(new MonstreSoigneur(new Araignee(chemin2)));
        }
        ajouterMonstresNiveau5(chemin1, niveau);
    }

    public void creeVague3(ArrayList<Position> chemin1, ArrayList<Position> chemin2) {
        creeVague3(chemin1, chemin2, 1);
    }

    public void creeVague3(ArrayList<Position> chemin1, ArrayList<Position> chemin2, int niveau) {
        this.monstresVague.clear();

        for (int i = 0; i < 14; i++) {
            monstresVague.add(new Zombie(chemin1));
            monstresVague.add(new Araignee(chemin2));
            monstresVague.add(new Squelette(chemin1));
        }

        monstresVague.add(new MonstreResistant(new Zombie(chemin1)));
        monstresVague.add(new MonstreResistant(new Araignee(chemin2)));
        monstresVague.add(new MonstreResistant(new Squelette(chemin1)));

        if (niveau >= 2) {
            monstresVague.add(new MonstreRapide(new Zombie(chemin1)));
            monstresVague.add(new MonstreRapide(new Araignee(chemin2)));
            monstresVague.add(new MonstreRapide(new Squelette(chemin1)));
        }
        if (niveau >= 3) {
            monstresVague.add(new MonstreResistant(new Zombie(chemin1)));
            monstresVague.add(new MonstreResistant(new Araignee(chemin2)));
            monstresVague.add(new MonstreResistant(new Squelette(chemin1)));
        }
        if (niveau >= 4) {
            monstresVague.add(new MonstreSoigneur(new Zombie(chemin1)));
            monstresVague.add(new MonstreSoigneur(new Araignee(chemin2)));
            monstresVague.add(new MonstreSoigneur(new Squelette(chemin1)));
        }
        ajouterMonstresNiveau5(chemin1, niveau);
    }

    public void creeVague4(ArrayList<Position> chemin1, ArrayList<Position> chemin2, ArrayList<Position> chemin3) {
        creeVague4(chemin1, chemin2, chemin3, 1);
    }

    public void creeVague4(ArrayList<Position> chemin1, ArrayList<Position> chemin2, ArrayList<Position> chemin3, int niveau) {
        this.monstresVague.clear();

        for (int i = 0; i < 14; i++) {
            monstresVague.add(new Zombie(chemin1));
            monstresVague.add(new Araignee(chemin2));
            monstresVague.add(new Squelette(chemin3));
            monstresVague.add(new Pillager(chemin2));
        }

        monstresVague.add(new MonstreResistant(new Zombie(chemin1)));
        monstresVague.add(new MonstreResistant(new Araignee(chemin2)));
        monstresVague.add(new MonstreResistant(new Squelette(chemin3)));
        monstresVague.add(new MonstreResistant(new Pillager(chemin2)));
        monstresVague.add(new MonstreResistant(new ChauveSouris(chemin1)));

        if (niveau >= 2) {
            monstresVague.add(new MonstreRapide(new Zombie(chemin1)));
            monstresVague.add(new MonstreRapide(new Araignee(chemin2)));
            monstresVague.add(new MonstreRapide(new Squelette(chemin3)));
            monstresVague.add(new MonstreRapide(new Pillager(chemin2)));
        }
        if (niveau >= 3) {
            monstresVague.add(new MonstreResistant(new Zombie(chemin1)));
            monstresVague.add(new MonstreResistant(new Araignee(chemin2)));
            monstresVague.add(new MonstreResistant(new Squelette(chemin3)));
            monstresVague.add(new MonstreResistant(new Pillager(chemin2)));
        }
        if (niveau >= 4) {
            monstresVague.add(new MonstreSoigneur(new Zombie(chemin1)));
            monstresVague.add(new MonstreSoigneur(new Araignee(chemin2)));
            monstresVague.add(new MonstreSoigneur(new Squelette(chemin3)));
            monstresVague.add(new MonstreSoigneur(new Pillager(chemin2)));
        }
        ajouterMonstresNiveau5(chemin1, niveau);
    }

    public void creeVague5(ArrayList<Position> chemin1, ArrayList<Position> chemin2, ArrayList<Position> chemin3) {
        creeVague5(chemin1, chemin2, chemin3, 1);
    }

    public void creeVague5(ArrayList<Position> chemin1, ArrayList<Position> chemin2, ArrayList<Position> chemin3, int niveau) {
        this.monstresVague.clear();

        for (int i = 0; i < 18; i++) {
            monstresVague.add(new Zombie(chemin1));
            monstresVague.add(new Araignee(chemin2));
            monstresVague.add(new Squelette(chemin3));
            monstresVague.add(new Pillager(chemin3));
        }

        monstresVague.add(new MonstreResistant(new Zombie(chemin1)));
        monstresVague.add(new MonstreResistant(new Araignee(chemin2)));
        monstresVague.add(new MonstreResistant(new Squelette(chemin3)));
        monstresVague.add(new MonstreResistant(new Pillager(chemin3)));
        monstresVague.add(new MonstreResistant(new Zombie(chemin1)));
        monstresVague.add(new MonstreResistant(new Araignee(chemin2)));
        monstresVague.add(new MonstreResistant(new Squelette(chemin3)));
        monstresVague.add(new MonstreResistant(new Pillager(chemin3)));

        if (niveau >= 2) {
            monstresVague.add(new MonstreRapide(new Zombie(chemin1)));
            monstresVague.add(new MonstreRapide(new Araignee(chemin2)));
            monstresVague.add(new MonstreRapide(new Squelette(chemin3)));
            monstresVague.add(new MonstreRapide(new Pillager(chemin3)));
            monstresVague.add(new MonstreRapide(new ChauveSouris(chemin1)));
        }
        if (niveau >= 3) {
            monstresVague.add(new MonstreResistant(new Zombie(chemin1)));
            monstresVague.add(new MonstreResistant(new Araignee(chemin2)));
            monstresVague.add(new MonstreResistant(new Squelette(chemin3)));
            monstresVague.add(new MonstreResistant(new Pillager(chemin3)));
            monstresVague.add(new MonstreResistant(new ChauveSouris(chemin1)));
        }
        if (niveau >= 4) {
            monstresVague.add(new MonstreSoigneur(new Zombie(chemin1)));
            monstresVague.add(new MonstreSoigneur(new Araignee(chemin2)));
            monstresVague.add(new MonstreSoigneur(new Squelette(chemin3)));
            monstresVague.add(new MonstreSoigneur(new Pillager(chemin3)));
            monstresVague.add(new MonstreSoigneur(new ChauveSouris(chemin1)));
        }
        ajouterMonstresNiveau5(chemin1, niveau);

        monstresVague.add(new Boss(chemin1));
    }

    private void ajouterMonstresNiveau5(ArrayList<Position> chemin, int niveau) {
        if (niveau >= 5) {
            monstresVague.add(new MonstreRapide(new Zombie(chemin)));
            monstresVague.add(new MonstreResistant(new Zombie(chemin)));
        }
    }


    public void mettreAJourVague(int compteur, ObservableList<Monstre> monstres) {
        if (this.indiceMonstre < this.monstresVague.size()) {

            if (this.indiceMonstre == 0 || compteur - dernierSpawn >= delaiSpawn) {
                monstres.add(this.monstresVague.get(this.indiceMonstre));
                this.indiceMonstre++;
                this.dernierSpawn = compteur;
            }
        }
    }

    public boolean tousLesMonstresEnvoyes() {
        return this.indiceMonstre >= this.monstresVague.size();
    }

    public void setIndiceMonstreZero() {
        this.indiceMonstre = 0;
    }

    public void ajouterMonstre(Monstre monstre) {
        monstresVague.add(monstre);
    }
}
