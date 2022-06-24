package modelo;

public class ObjFixo extends ObjetosTelaGeral implements Runnable {

    public ObjFixo(int x, int y, String caminhoImagem, int vida, boolean visivel) {
        super(x, y, caminhoImagem, vida, visivel);
    }

    @Override
    public void run() {
        while (true) {
        }
    }
}
