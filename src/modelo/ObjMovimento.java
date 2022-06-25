package modelo;

import visao.TelaJogo;

public class ObjMovimento extends ObjetosTelaGeral implements Runnable {

    private int dx;
    private int dy;
    private int velocidade;
    private int distancia;
    private int acrescimoDist;

    public ObjMovimento(int x, int y, String caminhoImagem, boolean visivel) {
        super(x, y, caminhoImagem, visivel);
    }

    @Override
    public void run() {
        while (this.vida == Estado.VIVO) {
            int soma = 0;
            while (soma < this.distancia) {
                soma += this.acrescimoDist;
                this.movimentarObj();
            }
            sortearTudo();
        }
        this.setVisible(false);
    }

    public void sortearTudo() {
        this.distancia = (int) (Math.random() * 300) + 200;

        do {
            this.dx = (int) (Math.random() * 3);
            this.dy = (int) (Math.random() * 3);
        } while (this.dx == 0 && this.dy == 0);

        this.velocidade = (int) (Math.random() * 30) + 70;
        this.acrescimoDist = (int) Math.sqrt(Math.pow(this.dx, 2)
                + Math.pow(this.dy, 2));
    }

    private  void movimentarObj() {
        try {
            Thread.sleep(1000 / this.velocidade);

            this.setLocation(this.getX(), this.getY() + this.dy);
            if (this.getY() >= 900) {
                // Drop tocou o "chão", então ele desaparece
                this.setVida(Estado.FORA);
            }

        } catch (InterruptedException ex) {
            System.out.println("Erro");
        }
    }
}
