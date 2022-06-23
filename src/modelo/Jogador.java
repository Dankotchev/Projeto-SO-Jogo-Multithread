package modelo;

import java.awt.Container;
import java.awt.Image;
import java.awt.event.KeyEvent;

public class Jogador extends ObjetosTelaGeral {

    private int dx;
    private int dy;

    public Jogador() {
    }

    public Jogador(int x, int y, String caminhoImagem, int vida, boolean visivel, Container telaJogo) {
        super(x, y, caminhoImagem, vida, visivel, telaJogo);
    }

    public Jogador(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
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

    public void keyRelessed(KeyEvent evt) {
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
        if (this.getX() > super.telaJogo.getWidth()) {
            this.setX(this.telaJogo.getWidth());
        }

        if (this.getY() < 100) {
            this.setY(100);
        }
        if (this.getY() > super.telaJogo.getHeight()) {
            this.setY(this.telaJogo.getHeight());
        }

    }

}
