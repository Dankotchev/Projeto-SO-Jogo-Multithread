package modelo;

public class ObjFixo extends ObjetosTelaGeral implements Runnable {

    public ObjFixo(int x, int y, String caminhoImagem, boolean visivel) {
        super(x, y, caminhoImagem, visivel);
    }

    @Override
    public void run() {
        synchronized (this) {
            while (true) {
            }
        }
    }
}
