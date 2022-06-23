package modelo;

import java.awt.Container;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ObjetosTelaGeral extends JLabel {

    protected int x;
    protected int y;
    protected Image imagem;
    protected String caminhoImagem;
    protected int altura;
    protected int largura;
    protected int vida;
    protected boolean visivel;
    protected Container telaJogo;

    public ObjetosTelaGeral() {
    }

    public ObjetosTelaGeral(int x, int y, String caminhoImagem, int vida,
            boolean visivel, Container telaJogo) {
        this.x = x;
        this.y = y;
        this.caminhoImagem = caminhoImagem;
        this.vida = vida;
        this.visivel = visivel;
        this.telaJogo = telaJogo;
    }

    @Override
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
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

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getLargura() {
        return largura;
    }

    public void setLargura(int largura) {
        this.largura = largura;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public boolean isVisivel() {
        return visivel;
    }

    public void setVisivel(boolean visivel) {
        this.visivel = visivel;
    }

    public Rectangle getBordas() {
        return new Rectangle(x, y, largura, altura);
    }

    public void carregarImagem() {
        this.imagem = new ImageIcon(getClass().getResource(caminhoImagem)).getImage();
        this.altura = this.imagem.getHeight(null);
        this.largura = this.imagem.getWidth(null);
    }
}
