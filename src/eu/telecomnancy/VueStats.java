package eu.telecomnancy;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

public class VueStats extends VBox implements Observateur{
    private final Jeu jeu;
    private Label stats;

    public VueStats(Jeu jeu) {
        super();
        this.jeu = jeu;
        this.jeu.ajouterObservateur(this);

        stats = new Label("Nombre de parties gagnées/jouées : 0/0");
        stats.setFont(new Font(24));

        this.setAlignment(Pos.CENTER);
        this.setPadding(new javafx.geometry.Insets(5, 0, 9, 0));
        this.getChildren().add(stats);
    }

    public void reagir(){
        stats.setText("Nombre de parties gagnées/jouées : " + jeu.NbGagnees() + "/" + jeu.NbJouees());
    }
}
