//cmd nouveau code
// Conserve les placements reussis pour les annuler du plus recent au plus ancien.
package universite_paris8.iut.ademir.demo1.Modele.Jeu.Commandes;

import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyBooleanWrapper;

import java.util.ArrayDeque;
import java.util.Deque;

public class HistoriqueCommandes {

    private final Deque<Commande> commandes = new ArrayDeque<>();
    private final ReadOnlyBooleanWrapper annulationPossible = new ReadOnlyBooleanWrapper(false);

    public boolean executer(Commande commande) {
        boolean reussie = commande != null && commande.executer();
        if (reussie) {
            commandes.push(commande);
            annulationPossible.set(true);
        }
        return reussie;
    }

    public boolean annulerDerniere() {
        boolean reussie = false;
        if (!commandes.isEmpty()) {
            reussie = commandes.peek().annuler();
            if (reussie) {
                commandes.pop();
            }
        }
        annulationPossible.set(!commandes.isEmpty());
        return reussie;
    }

    public void vider() {
        commandes.clear();
        annulationPossible.set(false);
    }

    public boolean peutAnnuler() {
        return annulationPossible.get();
    }

    public ReadOnlyBooleanProperty annulationPossibleProperty() {
        return annulationPossible.getReadOnlyProperty();
    }
}
