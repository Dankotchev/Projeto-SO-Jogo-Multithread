package modelo;

import java.awt.event.KeyEvent;

public class Jogador extends ObjetosTelaGeral {

    private int dx;
    private int dy;

    public Jogador(int x, int y, String caminhoImagem, boolean visivel) {
        super(x, y, caminhoImagem, visivel);
    }

    public void keyPressed(KeyEvent evt) {
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                this.dx = +1;
                break;
            case KeyEvent.VK_LEFT:
                this.dx = -1;
                break;
            case KeyEvent.VK_SPACE:
                // Se tecla espaço precionada ele fará uma especie de salto rápido
                this.dy = -3;
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
                this.dy = +4;
                break;
        }
    }

    public void atualizarPosicao() {
        this.setLocation(this.getX() + dx, this.getY() + dy);

        if (this.getX() < 0) {
            this.setLocation(0, this.getY());
        }
        if (this.getX() > (600 - this.imagem.getWidth(this))) {
            this.setLocation(600 - this.imagem.getWidth(this), this.getY());
        }

        if (this.getY() < 300) {
            this.setLocation(this.getX(), 300);
        }
        if (this.getY() > (800 - this.imagem.getHeight(this))) {
            this.setLocation(this.getX(), 800 - this.imagem.getHeight(this));
        }

    }

}
