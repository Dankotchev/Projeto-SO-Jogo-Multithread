package modelo;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ObjetosTelaGeral extends JLabel {

    protected Image imagem;
    protected String caminhoImagem;

    // 
    protected Estado vida;

    public ObjetosTelaGeral(int x, int y, String caminhoImagem, boolean visivel) {
        this.setLocation(x, y);
        this.caminhoImagem = caminhoImagem;
        this.vida = Estado.VIVO;
        this.setVisible(visivel);
        this.imagem = new ImageIcon(getClass().getResource(caminhoImagem)).getImage();
        this.setBounds(x, y, this.imagem.getWidth(this), this.imagem.getHeight(this));

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

    public Estado getVida() {
        return vida;
    }

    public void setVida(Estado vida) {
        this.vida = vida;
    }

    public enum Estado {
        VIVO,
        MORTO,
        FORA
    }
}
