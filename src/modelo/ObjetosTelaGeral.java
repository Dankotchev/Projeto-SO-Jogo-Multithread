package modelo;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ObjetosTelaGeral extends JLabel {

    protected int x;
    protected int y;
    protected Image imagem;
    protected String caminhoImagem;
    protected int vida;

    public ObjetosTelaGeral(int x, int y, String caminhoImagem, int vida,
            boolean visivel) {
        this.x = x;
        this.y = y;
        this.caminhoImagem = caminhoImagem;
        this.vida = vida;
        this.setVisible(visivel);
        this.imagem = new ImageIcon(getClass().getResource(caminhoImagem)).getImage();
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Image getImagem() {
        return imagem;
    }

    public void setImagem(Image imagem) {
        this.imagem = imagem;
    }

    public String getCaminhoImagem() {
        return caminhoImagem;
    }

    public void setCaminhoImagem(String caminhoImagem) {
        this.caminhoImagem = caminhoImagem;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }
}