
package universite_paris8.iut.ademir.demo1.Modele.Jeu;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.Monstre;

import java.util.ArrayList;

public class GestionnaireVague {

    private final ArrayList<Vague> vagues;
    private final VagueFactory vagueFactory;

    private int indiceVague;
    private final BooleanProperty vagueEnCours;

    public GestionnaireVague(
            ArrayList<Position> chemin,
            ArrayList<Position> chemin2,
            ArrayList<Position> chemin3) {

        this.vagues = new ArrayList<>();
        this.vagueFactory = new VagueFactory();

        this.indiceVague = 0;
        this.vagueEnCours = new SimpleBooleanProperty(false);

        creerVagues(chemin, chemin2, chemin3);
    }

    // Création des cinq vagues
    public void creerVagues(
            ArrayList<Position> chemin,
            ArrayList<Position> chemin2,
            ArrayList<Position> chemin3) {

        this.vagues.clear();

        this.vagues.addAll(
                vagueFactory.creerToutesLesVagues(
                        chemin, chemin2, chemin3
                )
        );
    }

    // Lancement de la prochaine vague
    public void lancerProchaineVague() {

        if (vagueEnCours.get()) {
            return;
        }

        if (indiceVague >= vagues.size()) {
            return;
        }

        vagueEnCours.set(true);
    }

    // Mise à jour de la vague actuelle
    // Retourne true lorsqu'une vague vient de se terminer
    public boolean mettreAJour(
            int compteur,
            ObservableList<Monstre> monstres) {

        if (!vagueEnCours.get()) {
            return false;
        }

        if (indiceVague >= vagues.size()) {
            vagueEnCours.set(false);
            return false;
        }

        Vague vagueActuelle = vagues.get(indiceVague);

        vagueActuelle.mettreAJourVague(compteur, monstres);

        if (vagueActuelle.tousLesMonstresEnvoyes()
                && monstres.isEmpty()) {

            indiceVague++;
            vagueEnCours.set(false);

            return true;
        }

        return false;
    }

    // Réinitialisation des vagues
    public void recommencer(
            ArrayList<Position> chemin,
            ArrayList<Position> chemin2,
            ArrayList<Position> chemin3) {

        this.indiceVague = 0;
        this.vagueEnCours.set(false);

        creerVagues(chemin, chemin2, chemin3);
    }

    public ArrayList<Vague> getVagues() {
        return vagues;
    }

    public int getIndiceVague() {
        return indiceVague;
    }

    public void setIndiceVague(int indiceVague) {
        this.indiceVague = indiceVague;
    }

    public int getIndiceVaguePlusUn() {
        return indiceVague + 1;
    }

    public boolean getVagueEnCours() {
        return vagueEnCours.get();
    }

    public BooleanProperty vagueEnCoursProperty() {
        return vagueEnCours;
    }

    public boolean getToutesLesVaguesTerminees() {
        return indiceVague >= vagues.size();
    }
}
