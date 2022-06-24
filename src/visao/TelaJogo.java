package visao;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import modelo.Imagem;
import modelo.Jogador;
import modelo.ObjFixo;
import modelo.ObjMovimento;

public class TelaJogo extends JPanel implements ActionListener {

    //  imagens e elementos
    private Image fundo;
    private Imagem vitoria;
    private Imagem derrota;
    private Jogador jogador;
    private List<ObjFixo> listaObjFixo = new ArrayList<>();
    private List<ObjMovimento> listaObjMovimento = new ArrayList<>();

    // Vetor de imagens para serem sorteadas na inicialização
    private String diversosDrops[] = {
        "/recurso/objmov01.png", "/recurso/objmov02.png",
        "/recurso/objmov03.png", "/recurso/objmov04.png",
        "/recurso/objmov05.png", "/recurso/objmov06.png",
        "/recurso/objmov07.png", "/recurso/objmov08.png",
        "/recurso/objmov09.png", "/recurso/objmov10.png"};

    private Timer timer;

    public TelaJogo() {

        setFocusable(true);
        setDoubleBuffered(true);

        // Inicializando elementos sem variações ou multiplos
        fundo = new ImageIcon(getClass().getResource("/recurso/fundo.png")).getImage();
        vitoria = new Imagem(40, 140, "/recurso/vitoria.png", 0, true);
        derrota = new Imagem(40, 140, "/recurso/derrota.png", 0, true);
        jogador = new Jogador(this.getWidth() / 2, 700, "/recurso/jogador.png", 1, true);

        // Inserção de Elementos Drop, acima da tela de jogo, para cairem "aos poucos"
        // Elementos posicionados horizontalmente de forma aleatória na tela de jodo
        for (int i = 0; i < 10; i++) {
            int x = (int) (Math.random() * 500) + 50;
            ObjMovimento drop = new ObjMovimento(x, -150, this.randomizarDrop(), 1, true);
            listaObjMovimento.add(drop);
            Thread threadDrop = new Thread(drop);
            threadDrop.start();
        }

        // Inserção dos Elementos Fixos na tela
        for (int i = 0; i < 10; i++) {
            int x = (int) (Math.random() * 800);
            int y = (int) (Math.random() * 800);
            ObjFixo fixo = new ObjFixo(x, y, "/recurso/objfixo.png", 1, true);
            listaObjFixo.add(fixo);
        }

        timer = new Timer(5, this);
        timer.start();

        addKeyListener(new Teclado());
    }

    private String randomizarDrop() {
        int i = (int) (Math.random() * this.diversosDrops.length);
        return this.diversosDrops[i];
    }

    // Isso aqui em teoria coloca os objetos na tela
    @Override
    public synchronized void paint(Graphics g) {
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

        // Caso as vidas do jogador acabe, o mesmo perde o jogo
        if (this.jogador.getVida() == 0) {
            graficos.drawImage(this.derrota.getImagem(),
                    this.derrota.getX(), this.derrota.getY(), this);
        }

        g.dispose();
    }

    public void colisao() {

        if (!listaObjMovimento.isEmpty()) {
            for (ObjMovimento om : listaObjMovimento) {
                //  1º Para cada Objeto em Movimento, verificar se está visivel na tela 
                // 2º Verificar se o Objeto em Movimento colide no objeto Jogador
                if (om.isVisible()) {
                    if (om.getBounds().intersects(this.jogador.getBounds())) {
                        // Houve a colisão com o Jogador
                        om.setVida(0);
                        om.setVisible(false);
                        listaObjMovimento.remove(om);
                    } else if (om.getX() >= 900) {
                        // Não houve colisão com o jogador, mas um Objeto em Movimento atingiu o fim da tela
                        // O objeto fica invisivel e o jogador perde uma vida (ou o jogo)
                        om.setVisible(false);
                        this.jogador.setVida(0);
                    }
                }
            }

        }
    }

    @Override
    public void actionPerformed(ActionEvent ae
    ) {
        jogador.atualizarPosicao();
        colisao();
        repaint();
    }

    public class Teclado extends KeyAdapter implements ActionListener {

        private Timer timer;

        public Teclado() {
            timer = new Timer(500, this);
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

        @Override
        public void actionPerformed(ActionEvent ae) {
            jogador.atualizarPosicao();
//        colisao();
            repaint();
        }
    }
}
