package modelo;

import java.awt.Container;
import java.awt.Image;

public class ObjMovimento extends ObjetosTelaGeral implements Runnable {

    private int dx;
    private int dy;
    private int velocidade;
    private int acrescimoDist;
    private int distancia;

    public ObjMovimento() {
    }

    public ObjMovimento(int x, int y, String caminhoImagem, int vida, boolean visivel, Container telaJogo) {
        super(x, y, caminhoImagem, vida, visivel, telaJogo);
    }



    @Override
    public void run() {
        while (vida != 0) {
            int soma = 0;
            while (soma < this.distancia) {
                soma += this.acrescimoDist;
                andar();

            }
            sortearTudo();
        }
        super.visivel = false;
    }

    public void sortearTudo() {
        this.distancia = (int) (Math.random() * 300) + 100;

        do {
            this.dy = (int) (Math.random() * 5) - 2;
        } while (this.dy == 0);

        this.velocidade = (int) (Math.random() * 30) + 50;
        this.acrescimoDist = (int) Math.sqrt(Math.pow(this.dx, 2));
    }

    private void andar() {
        try {
            // faço a thread do objeto aguardar antes de andar de novo
            Thread.sleep(1000 / this.velocidade);

            this.y += this.dy;
            if (this.y >= this.telaJogo.getHeight()) {
                // Drop tocou o "chão", então ele desaparece
                this.vida = 0;
            }

        } catch (InterruptedException ex) {
            System.out.println("Erro");
        }
    }

}
