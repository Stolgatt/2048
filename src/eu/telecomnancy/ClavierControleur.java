package eu.telecomnancy;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class ClavierControleur implements EventHandler<KeyEvent> {
    private final Jeu jeu;

    public ClavierControleur(Jeu jeu) {
        this.jeu = jeu;
    }

    @Override
    public void handle(KeyEvent event) {
        KeyCode keyCode = event.getCode();

        switch (keyCode) {
            case Z:
                jeu.deplacer("haut");
                break;
            case S:
                jeu.deplacer("bas");
                break;
            case Q:
                jeu.deplacer("gauche");
                break;
            case D:
                jeu.deplacer("droite");
                break;
            default:
                break;
        }
    }

    public void configurerClavier(Scene scene) {
        scene.addEventHandler(KeyEvent.KEY_PRESSED, this);
    }
}