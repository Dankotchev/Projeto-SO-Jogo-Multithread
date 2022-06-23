package modelo;

public class ObjFixo extends ObjetosTelaGeral implements Runnable {

    public ObjFixo(String caminhoImagem, int vida, boolean visivel) {
        this.x = (int) (Math.random() * 800);
        this.y = (int) (Math.random() * 800);
        this.caminhoImagem = caminhoImagem;
        this.vida = vida;
        this.setVisible(visivel);
    }

    @Override
    public void run() {
        while (true) {
        }
    }
}
