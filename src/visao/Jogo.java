package visao;

import javax.swing.JFrame;

public class Jogo extends JFrame {

    public Jogo() {
        add(new TelaJogo());
        setTitle("DANILO    &   GIOVANA");
        setSize(600, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Jogo();
    }
}
