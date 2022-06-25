package visao;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import modelo.Imagem;
import modelo.Jogador;
import modelo.ObjFixo;
import modelo.ObjMovimento;
import modelo.ObjetosTelaGeral;

public class TelaJogo extends JPanel implements ActionListener {

    //  imagens e elementos
    private Image fundo;
    private Imagem vitoria;
    private Imagem derrota;
    private Jogador jogador;
    private List<ObjMovimento> listaObjMovimento = Collections.synchronizedList(new ArrayList<>());
    private List<ObjFixo> listaObjFixo = new ArrayList<>();

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
        vitoria = new Imagem(44, 194, "/recurso/vitoria.png", true);
        derrota = new Imagem(44, 194, "/recurso/derrota.png", true);
        jogador = new Jogador(250, 700, "/recurso/jogador.png", true);

        // Inserção de Elementos Drop, acima da tela de jogo, para cairem "aos poucos"
        // Elementos posicionados horizontalmente de forma aleatória na tela de jodo
        for (int i = 0; i < 10; i++) {
            int x = (int) (Math.random() * 500) + 50;
            ObjMovimento drop = new ObjMovimento(x, -150, this.randomizarDrop(), true);
            listaObjMovimento.add(drop);
            Thread threadDrop = new Thread(drop);
            threadDrop.start();
        }

        // Inserção dos Elementos Fixos na tela
        for (int i = 0; i < 10; i++) {
            int x = (int) (Math.random() * 800);
            int y = (int) (Math.random() * 800);
            ObjFixo fixo = new ObjFixo(x, y, "/recurso/objfixo.png", true);
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

        // Caso as vidas do jogador acabe, o mesmo perde o jogo
        if (this.jogador.getVida() == ObjetosTelaGeral.Estado.MORTO) {
            graficos.drawImage(this.derrota.getImagem(),
                    this.derrota.getX(), this.derrota.getY(), this);
        }

        // Caso todos os Drops sejam pegos
        int i = 0;
        int tm = listaObjMovimento.size();
        for (ObjMovimento om : listaObjMovimento) {
            if (om.getVida() == ObjetosTelaGeral.Estado.MORTO) {
                i++;
            }
        }
        if (i == tm) {
            graficos.drawImage(this.vitoria.getImagem(),
                    this.vitoria.getX(), this.vitoria.getY(), this);
        }

        if (listaObjMovimento.isEmpty()) {
            graficos.drawImage(this.vitoria.getImagem(),
                    this.vitoria.getX(), this.vitoria.getY(), this);
        }

        g.dispose();
    }

    public void colisao() {
        synchronized (listaObjMovimento) {
            if (!listaObjMovimento.isEmpty()) {
                for (ObjMovimento om : listaObjMovimento) {
                    int i = listaObjMovimento.indexOf(om);
                    //  1º Para cada Objeto em Movimento, verificar se está visivel na tela 
                    // 2º Verificar se o Objeto em Movimento colide no objeto Jogador
                    if (om.isVisible()) {
                        if (om.getBounds().intersects(this.jogador.getBounds())) {
                            // Houve a colisão com o Jogador
                            // Remover o Objeto em Movimento da lista
                            om.setVisible(false);
                            om.setVida(ObjetosTelaGeral.Estado.MORTO);
                            System.out.println("Objeto Morto");

//                            listaObjMovimento.remove(i);
                        } else if (om.getY() >= (900 - om.getImagem().getHeight(this))) {
                            // Não houve colisão com o jogador, mas um Objeto em Movimento atingiu o fim da tela
                            // O objeto fica invisivel e o jogador perde uma vida (ou o jogo)
                            om.setVisible(false);
                            this.jogador.setVida(ObjetosTelaGeral.Estado.MORTO);
                        }
                    }
                }

            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent actEvt
    ) {
        jogador.atualizarPosicao();
        colisao();
        repaint();

    }

    public class Teclado extends KeyAdapter implements ActionListener {

        private Timer timer;

        public Teclado() {
            timer = new Timer(1000, this);
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
            colisao();
            repaint();
        }
    }
}
