package universite_paris8.iut.ademir.demo1.Modele.Jeu;

import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;

public class GestionnaireScore {

    private final ReadOnlyIntegerWrapper rubis;

    //obs nouveau code
    // Cree une reserve observable conservee pendant toute la partie.
    public GestionnaireScore() {
        this.rubis = new ReadOnlyIntegerWrapper(400);
    }

    //obs nouveau code
    // Lit la valeur de la reserve observable.
    public int getRubis() {
        return rubis.get();
    }

    //otp nouveau code
    // Ajoute les rubis uniquement si le montant est valide.
    //obs nouveau code
    // Un ajout valide notifie automatiquement les observateurs.
    public void ajouterRubis(int montant) {
        if (montant < 0) {
            System.out.printf("Montant invalide%n");
        } else {
            rubis.set(rubis.get() + montant);
        }
    }

    //otp nouveau code
    // Retire les rubis uniquement si le montant et le solde le permettent.
    //obs nouveau code
    // Un retrait valide notifie automatiquement les observateurs.
    public void retirerRubis(int montant) {
        if (montant < 0 || montant > rubis.get()) {
            System.out.printf("Montant invalide%n");
        } else {
            rubis.set(rubis.get() - montant);
        }
    }

    //obs nouveau code
    // Retablit le budget sans remplacer la propriete observee.
    public void recommencer() {
        rubis.set(400);
    }

    //obs nouveau code
    // Expose les notifications sans autoriser une modification directe du solde.
    public ReadOnlyIntegerProperty rubisProperty() {
        return rubis.getReadOnlyProperty();
    }
}