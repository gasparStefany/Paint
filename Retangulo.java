import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Retangulo extends Figura{

    /**
     * Construtor Padrao para Criacao do retangulo
     * @param xInicial - Valor que sera atribuido para o pontoInicial.x
     * @param yInicial - Valor que sera atribuido para o pontoInicial.y
     * @param xFinal - Valor que sera atribuido para o pontoFinal.x
     * @param yFinal - Valor que sera atribuido para o pontoFinal.y
     * @param cor - cor para desenho do retangulo
     */
    public Retangulo(double xInicial, double yInicial, double xFinal, double yFinal, Color cor) {
        super(xInicial, yInicial, xFinal, yFinal, cor);
    }

    public Retangulo(){
        this.isRetangulo = true;
    }

    @Override
    public void desenharFiguraDDA(BufferedImage g) {}
    
    //public void desenharRetangulo(BufferedImage g){
    @Override
    public void desenharFiguraBresenham(BufferedImage g) {    
        this.isRetangulo = true;
        double dX = pontoFinal.x - pontoInicial.x;
        double dY = pontoFinal.y - pontoInicial.y;

        Reta top = new Reta();
        Reta bottom = new Reta();
        Reta left = new Reta();
        Reta right = new Reta();

        top.pontoInicial = new Ponto(pontoInicial.x, pontoInicial.y);
        top.pontoFinal = new Ponto(pontoInicial.x + dX,pontoInicial.y);

        bottom.pontoInicial = new Ponto(pontoFinal.x - dX, pontoFinal.y);
        bottom.pontoFinal = new Ponto(pontoFinal.x, pontoFinal.y);

        left.pontoInicial = new Ponto(pontoInicial.x, pontoInicial.y);
        left.pontoFinal = new Ponto(bottom.pontoInicial.x, bottom.pontoInicial.y);

        right.pontoInicial = new Ponto(top.pontoFinal.x, top.pontoFinal.y);
        right.pontoFinal = new Ponto(pontoFinal.x, pontoFinal.y);
        bottom.cor = Color.BLACK;
        top.cor = Color.BLACK;
        left.cor = Color.BLACK;
        right.cor = Color.BLACK;

        top.desenharFiguraBresenham(g);
        bottom.desenharFiguraBresenham(g);
        left.desenharFiguraBresenham(g);
        right.desenharFiguraBresenham(g);
    }

    /*Metodos nao implementados para retangulo*/
    @Override
    public void rotacionarFigura(double grau) { }
    
    @Override
    public void mudarEscalaFigura(double escalaX, double escalaY) {
    
    }
    
    @Override
    public void moverFigura(Ponto novoPonto) {
    
    }
    
    @Override
    public void espelharFigura(int opcode) {
    
    }
    
    @Override
    public void shearFigura(double fatorX, int opcode) {

    }

}
