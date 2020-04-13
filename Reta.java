import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Classe de implementação de uma Reta como uma figura
 * Algoritmos de formacao da reta:
 *    Reta DDA
 *    Reta Bresenham
 * Algoritmo de Manipulação da reta:
 *    Translação
 *    Rotação
 *    Escala
 */

public class Reta extends Figura{

    /**
     * Construtor Padrao para Criacao da Reta
     * @param xInicial - Valor que sera atribuido para o pontoInicial.x
     * @param yInicial - Valor que sera atribuido para o pontoInicial.y
     * @param xFinal - Valor que sera atribuido para o pontoFinal.x
     * @param yFinal - Valor que sera atribuido para o pontoFinal.y
     * @param cor - cor para desenho da reta
     */
    public Reta(double xInicial, double yInicial, double xFinal, double yFinal, Color cor) {
        super(xInicial, yInicial, xFinal, yFinal, cor);
    }

    public Reta(){ }

    /**
     * Metodo para desenhar a reta na tela utilizando o algoritmo DDA
     * Fonte: Slides de Computacao Grafica presentes no SGA
     */
    @Override
    public void desenharFiguraDDA(BufferedImage g) {

        double dX = this.pontoFinal.x - this.pontoInicial.x;
        double dY = this.pontoFinal.y - this.pontoInicial.y;
        double x = this.pontoInicial.x;
        double y = this.pontoInicial.y;
        int passos;
        // colore o ponto inicial
        colorirPonto((int)Math.round(x), (int)Math.round(y), g);
        if(Math.abs(dX) > Math.abs(dY))
            passos = (int)Math.abs(dX);
        else
            passos = (int)Math.abs(dY);

        double xIncr = dX/passos;
        double yIncr = dY/passos;

        for(;passos >= 0; passos--) {
            x += xIncr;
            y += yIncr;
            colorirPonto((int)Math.round(x), (int)Math.round(y), g);
        }

    }


    /**
     * Metodo para plotar a figura utilizando o algoritmo de Bresenham
     * Fonte: Slides de Computacao Grafica Presentes no SGA
     */
    @Override
    public void desenharFiguraBresenham(BufferedImage g) {
        int dX, dY, x, y, i, const1, const2, p, xIncr, yIncr;

        dX = (int)Math.round(this.pontoFinal.x - this.pontoInicial.x);
        dY = (int)Math.round(this.pontoFinal.y - this.pontoInicial.y);

        if( dX >= 0)
            xIncr = 1;
        else{
            xIncr = -1;
            dX = dX * -1;
        }
        if(dY >= 0)
            yIncr = 1;
        else {
            yIncr = -1;
            dY = dY * -1;
        }
        x = (int)Math.round(pontoInicial.x);
        y = (int)Math.round(pontoInicial.y);
        colorirPonto(x, y, g);

        if(dY < dX){
            p = (2 * dY) - dX;
            const1 = 2 * dY;
            const2 = 2*(dY - dX);

            for(i = 0; i < dX; i++){
                x += xIncr;
                if (p < 0)
                    p += const1;
                else{
                    y += yIncr;
                    p += const2;
                }
                colorirPonto(x, y, g);
            }
        }else{
            p = (2 * dX) - dY;
            const1 = 2 * dX;
            const2 = 2 * (dX - dY);

            for (i = 0; i < dY; i++){
                y += yIncr;
                if (p < 0)
                    p += const1;
                else{
                    x += xIncr;
                    p += const2;
                }
                colorirPonto(x, y, g);
            }
        }
    }


    /**
     * Metodo de rotacao por grau, utilizando a simplificacao da matriz de rotacao
     * @param grau - grau de rotacao
     */
    @Override
    public void rotacionarFigura(double grau) {
        if(!this.isCircunferencia) {
            Ponto pontoOriginal = this.pontoInicial.clone();
            moverFigura(new Ponto(0, 0));
            grau = Math.toRadians(grau);

            Ponto novoPontoFinal = new Ponto(
                    (this.pontoFinal.x*Math.cos(grau)) - (this.pontoFinal.y * Math.sin(grau)),
                    (this.pontoFinal.x*Math.sin(grau)) + (this.pontoFinal.y * Math.cos(grau)));

            this.pontoFinal = novoPontoFinal;

            moverFigura(pontoOriginal);
        }
    }

    /**
     * Metodo para alterar a escala da figura multiplicando cada um
     * dos parametros com o seu ponto final
     * @param escalaX - fator de escala em relacao a x
     * @param escalaY - fator de escala em ralacao a y
     */
    @Override
    public void mudarEscalaFigura(double escalaX, double escalaY) {
        if(!this.isCircunferencia){
            //Armazena a posicao inicial da linha
            Ponto pontoOriginal = this.pontoInicial.clone();

            // aplica a translacao pra origem
            moverFigura(new Ponto(0, 0));

            //Alterando a escala corretamente
            Ponto novoPontoFinal = new Ponto(Math.round(this.pontoFinal.x * escalaX), Math.round(this.pontoFinal.y * escalaY));

            this.pontoFinal = novoPontoFinal;

            //Aplicando a translacao para o ponto original
            moverFigura(pontoOriginal);
        }
    }

    /**
     * Metodo de espelhamento ou reflaxao de uma figura
     * @param opcode Qual o eixo de reflexao.
     */
    @Override
    public void espelharFigura(int opcode) {
        if(!this.isCircunferencia) {
            if(opcode == 0) {//espelhamento eixo x
                //Armazena posicao inicial da linha
                Ponto pontoOriginal = this.pontoInicial.clone();

                //aplica translacao para origem
                this.moverFigura(new Ponto(0,0));

                //x' = x
                //y' = y * -1
                Ponto novoPontoFinal = new Ponto(this.pontoFinal.x, (this.pontoFinal.y * -1));

                this.pontoFinal = novoPontoFinal;

                moverFigura(pontoOriginal);
            }else if(opcode == 1) {
                //Armazena posicao inicial da linha
                Ponto pontoOriginal = this.pontoInicial.clone();

                //aplica translacao para origem
                this.moverFigura(new Ponto(0,0));

                //x' = x
                //y' = y * -1
                Ponto novoPontoFinal = new Ponto((this.pontoFinal.x * -1), this.pontoFinal.y);

                this.pontoFinal = novoPontoFinal;

                moverFigura(pontoOriginal);
            }else if(opcode == 2){
                // como espelhar em relacao a origem e' igual a rotacao de 180 graus..
                this.rotacionarFigura(180);
            }
        }
    }

    /**
     * Metodo de cisalhamento de uma reta
     * @param fator Qual o fator de "forca" que esta sendo aplicado a reta
     * @param opcode Qual o eixo que esta sendo aplicado
     */
    @Override
    public void shearFigura(double fator, int opcode) {
        if(!this.isCircunferencia) {
            if (opcode == 0) {//espelhamento eixo x
                //Armazena posicao inicial da linha
                Ponto pontoOriginal = this.pontoInicial.clone();

                //aplica translacao para origem
                this.moverFigura(new Ponto(0, 0));

                //x' = x
                //y' = y * -1
                Ponto novoPontoFinal = new Ponto(Math.round(this.pontoFinal.x + fator * this.pontoFinal.y), this.pontoFinal.y);

                this.pontoFinal = novoPontoFinal;

                moverFigura(pontoOriginal);
            } else if (opcode == 1) {
                //Armazena posicao inicial da linha
                Ponto pontoOriginal = this.pontoInicial.clone();

                //aplica translacao para origem
                this.moverFigura(new Ponto(0, 0));

                //x' = x
                //y' = y * -1
                Ponto novoPontoFinal = new Ponto(this.pontoFinal.x, Math.round(this.pontoFinal.y + fator * this.pontoFinal.x));

                this.pontoFinal = novoPontoFinal;

                moverFigura(pontoOriginal);
            }
        }
    }

    /**
     * Metodo de chamada para o Algoritmo de CohenSutherland implementado na classe abstrata Figura
     * @param img BufferedImage onde é desenhado cada reta
     * @param pontoMin Ponto minimo da janela de recorte
     * @param pontoMax Ponto Maximo da Janela de recorte
     */
    public void cohenClip(BufferedImage img, Ponto pontoMin, Ponto pontoMax){
        this.cohenSutherland(pontoMin, pontoMax);
        if(this.isDentroJanela)
            this.desenharFiguraBresenham(img);
    }

    /**
     * Metodo de chamada para o Algoritmo de LiangBarksy implementado na classe abstrata Figura
     * @param img BufferedImage onde é desenhado cada reta
     * @param pontoMin Ponto minimo da janela de recorte
     * @param pontoMax Ponto Maximo da Janela de recorte
     */
    public void liangClip(BufferedImage img, Ponto pontoMin, Ponto pontoMax){
        this.liangBarsky(pontoMin, pontoMax);
        if(this.isDentroJanela)
        this.desenharFiguraBresenham(img);
    }

    /**
     * Metodo de Translacao T(a,b)
     * @param novoPonto Ponto do Mouse (x,y)
     */
    @Override
    public void moverFigura(Ponto novoPonto) {
        double dX = novoPonto.x - this.pontoInicial.x;
        double dY = novoPonto.y - this.pontoInicial.y;

        this.pontoInicial = new Ponto(this.pontoInicial.x + dX, this.pontoInicial.y + dY);
        this.pontoFinal = new Ponto(this.pontoFinal.x + dX, this.pontoFinal.y + dY);
    }
}
