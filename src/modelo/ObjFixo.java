package modelo;

import java.awt.Container;
import java.util.Random;

public class ObjFixo extends ObjetosTelaGeral implements Runnable {

    public ObjFixo() {
    }

    public ObjFixo(int x, int y, String caminhoImagem, int vida, boolean visivel, Container telaJogo) {
        super(x, y, caminhoImagem, vida, visivel, telaJogo);
        Random rand = new Random();
        this.x = rand.nextInt(800);
        this.y = rand.nextInt(800);
    }



    @Override
    public void run() {
        while (true) {

        }
    }
}
