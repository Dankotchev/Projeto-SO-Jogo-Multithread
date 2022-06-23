package modelo;

import java.awt.Container;
import java.awt.Image;
import java.awt.event.KeyEvent;

public class Jogador extends ObjetosTelaGeral {

    private int dx;
    private int dy;

    public Jogador(int x, int y, String caminhoImagem, int vida, boolean visivel) {
        super(x, y, caminhoImagem, vida, visivel);
    }

    public void keyPressed(KeyEvent evt) {
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                this.dx = 5;
                break;
            case KeyEvent.VK_LEFT:
                this.dx = -5;
                break;
            case KeyEvent.VK_SPACE:
                // Se tecla espaço precionada ele fará uma especie de salto rápido
                this.dy = -15;
                break;
        }
    }

    public void keyReleased(KeyEvent evt) {
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                this.dx = 0;
                break;
            case KeyEvent.VK_LEFT:
                this.dx = 0;
                break;
            case KeyEvent.VK_SPACE:
                // Ao soltar a tecla espaço o jogador volta a cair para a base do jogo
                this.dy = -20;
                break;
        }
    }

    public void atualizarPosicao() {
        this.setX(dx);
        this.setY(dy);

        if (this.getX() < 0) {
            this.setX(0);
        }
        if (this.getX() > 600) {
            this.setX(600);
        }

        if (this.getY() < 300) {
            this.setY(300);
        }
        if (this.getY() > 800) {
            this.setY(800);
        }

    }

}
