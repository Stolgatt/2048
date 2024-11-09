package eu.telecomnancy;

import javafx.geometry.Insets;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;

public class VuePanneauGauche extends VBox implements Observateur {
    private final Jeu jeu;
    private final Label objectif;
    private final Label maxScore;
    private final Label score;

    public VuePanneauGauche(Jeu jeu) {
        super();
        this.jeu = jeu;
        this.jeu.ajouterObservateur(this);

        //Affichage de l'objectif actuel
        objectif = new Label("Objectif actuel : " + this.jeu.getObjectif());
        maxScore = new Label("Score maximal atteint : " + this.jeu.getMaxScore());
        score = new Label("Score actuel : " + this.jeu.getScore());

        String styleTexte = "-fx-font-weight: bold; -fx-font-size: 24";
        objectif.setStyle(styleTexte);
        maxScore.setStyle(styleTexte);
        score.setStyle(styleTexte);

        //Ajout régions vides haut et bas et entre logo et affichages
        Region top = new Region();
        Region bottom = new Region();
        VBox.setVgrow(top, Priority.ALWAYS);
        VBox.setVgrow(bottom, Priority.ALWAYS);

        VBox affichage = new VBox(30, objectif, maxScore, score);
        this.getChildren().addAll(top,affichage, bottom);
        this.setPadding(new Insets(10, 10, 10, 70));
    }

    public void reagir(){
        score.setText("Score actuel : " + this.jeu.getScore());
        objectif.setText("Objectif actuel : " + this.jeu.getObjectif());
        maxScore.setText("Score maximal atteint : " + this.jeu.getMaxScore());
    }
}
