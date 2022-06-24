package modelo;

public class ObjMovimento extends ObjetosTelaGeral implements Runnable {

    private int dx;
    private int dy;
    private int velocidade;
    private int acrescimoDist;
    private int distancia = 10;

    public ObjMovimento(int x, int y, String caminhoImagem, int vida, boolean visivel) {
        super(x, y, caminhoImagem, vida, visivel);
    }

    @Override
    public void run() {
        while (vida != 0) {
            int soma = 0;
            while (soma < this.distancia) {
                soma += this.acrescimoDist;
                movimentarObj();

            }
            sortearTudo();
        }
        this.setVisible(false);
    }

    public void sortearTudo() {

        // Poderá ser necessário alterar esse comportamento do andar (distância e acrestimo)
        this.distancia = (int) (Math.random() * 300) + 100;
        do {
            this.dy = (int) (Math.random() * 5) - 2;
        } while (this.dy == 0);
        this.acrescimoDist = (int) Math.sqrt(Math.pow(this.dx, 2));

        // Seleciona a velocidade do movimento atual
        this.velocidade = (int) (Math.random() * 30) + 50;
    }

    private void movimentarObj() {
        try {
            Thread.sleep(1000 / this.velocidade);

            this.y += this.dy;
            if (this.y >= 900) {
                // Drop tocou o "chão", então ele desaparece
                this.vida = 0;
            }

        } catch (InterruptedException ex) {
            System.out.println("Erro");
        }
    }

}
