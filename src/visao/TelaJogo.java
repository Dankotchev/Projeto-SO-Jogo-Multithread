package visao;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import modelo.Jogador;
import modelo.ObjFixo;
import modelo.ObjMovimento;

public class TelaJogo extends JPanel implements ActionListener {

    //  imagens e elementos
    private Image fundo;
    private Jogador jogador;
    private List<ObjFixo> listaObjFixo = new ArrayList<>();
    private List<ObjMovimento> listaObjMovimento = new ArrayList<>();

    // Vetor de imagens para serem sorteadas na inicialização
    private String diversosDrops[] = {
        "/recurso/objmov01.png", "/recurso/objmov02.png",
        "/recurso/objmov03.png"};

    private Timer timer;

    public TelaJogo() {

        setFocusable(true);
        setDoubleBuffered(true);

        // Inicializando elementos sem variações ou multiplos
        fundo = new ImageIcon(getClass().getResource("/recurso/fundo.png")).getImage();
        jogador = new Jogador(this.getWidth() / 2, this.getHeight(), "/recurso/jogador.png", 3, true);

        // Inserção de Elementos Drop, acima da tela de jogo, para cairem "aos poucos"
        for (int i = 0; i < 10; i++) {
            ObjMovimento drop = new ObjMovimento(randomizarPosicaoInicial(this.getHeight()),
                    -100, this.randomizarDrop(), 1, true);
            listaObjMovimento.add(drop);
            Thread threadDrop = new Thread(drop);
            threadDrop.start();
        }

        // Inserção dos Elementos Fixos na tela
        for (int i = 0; i < 10; i++) {
            ObjFixo fixo = new ObjFixo("/recurso/objfixo.png", 1, true);
            listaObjFixo.add(fixo);
        }

        timer = new Timer(5, this);
        timer.start();

        addKeyListener(new Teclado());
    }

    private int randomizarPosicaoInicial(int largura) {
        // Uma margem lateral de 50 pixel para cada lado da tela ao criar novos Drops
        return 50 + ((int) Math.random() * (largura - 100));
    }

    private String randomizarDrop() {
        int i = (int) (Math.random() * this.diversosDrops.length);
        return this.diversosDrops[i];
    }

    // Isso aqui em teoria coloca os objetos na tela
    @Override
    public void paint(Graphics g) {
        Graphics2D graficos = (Graphics2D) g;

        // Colocar a imagem de fundo na tela
        graficos.drawImage(fundo, 0, 0, null);

        // Colocar a imagem do Jogador na tela
        if (jogador.isVisible()) {
            graficos.drawImage(jogador.getImagem(), jogador.getX(), jogador.getY(), this);
        }

        // Colocar a imagem dos Objetos Fixos na tela
        if (!listaObjFixo.isEmpty()) {
            for (ObjFixo of : listaObjFixo) {
                if (of.isVisible()) {
                    graficos.drawImage(of.getImagem(), of.getX(), of.getY(), this);
                }
            }
        }

        // Colocar a imagem dos Objetos Movimento na tela
        if (!listaObjMovimento.isEmpty()) {
            for (ObjMovimento om : listaObjMovimento) {
                if (om.isVisible()) {
                    graficos.drawImage(om.getImagem(), om.getX(), om.getY(), this);
                }
            }
        }

        g.dispose();
    }

//    public void checarColisao() {
//        if (!nave.getTiros().isEmpty()) {
//            for (int i = 0; i < nave.getTiros().size(); i++) { //tm do VETOR DO TIRO 
//                Tiro tiro = nave.getTiros().get(i);//tira tiro por tiro um por vez
//                if (tiro.isVisivel()) { // SE P TIRO TIVER VISIVEL
//                    if (!aliens.isEmpty()) {
//                        for (int x = 0; x < aliens.size(); x++) {
//                            Alien a = aliens.get(x);
//                            if (a.isVisivel()) {//NAO FAZ SENTIDO CONTINUAR O COD SE o alien for invisisvel
//                                // verificao colicao 
//                                if (tiro.getBordas().intersects(a.getBordas())) {
//                                    a.setVida(0);
//                                    tiro.setVida(0);
//                                    aliens.remove(a);
//                                    nave.getTiros().remove(tiro);
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        jogador.atualizarPosicao();
//        colisao();
        repaint();
    }

    public class Teclado extends KeyAdapter implements KeyListener {

        private Timer timer;

        public Teclado() {
            timer = new Timer(500, (ActionListener) this);
            timer.start();
        }

        @Override
        public void keyPressed(KeyEvent e) {
            jogador.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            jogador.keyReleased(e);
        }
    }
}
