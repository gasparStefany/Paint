import java.awt.*;
import java.awt.image.BufferedImage;

public class Circunferencia extends Figura {

    public Circunferencia(double xInicial, double yInicial, double xFinal, double yFinal, Color cor) {
        super(xInicial, yInicial, xFinal, yFinal, cor);
    }

    public Circunferencia() {
        this.isCircunferencia = true;
    }

    @Override
    public void desenharFiguraDDA(BufferedImage g) {}

    /*Metodos nao implementados para circunferencia*/
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

    /**
     * Metodo auxiliar para o algoritmo de Bresenham
     * @param x valor x do ponto
     * @param y valor y do ponto
     * @param xc valor x do centro
     * @param yc valor y do centro
     * @param g bufferedimage para ser desenhada
     */
    public void setPixels(double x, double y, double xc, double yc, BufferedImage g){
        int sx1 = (int) Math.round(xc + x);
        int sx2 = (int)Math.round(xc - x);
        int sx3 = (int)Math.round(xc + y);
        int sx4 = (int)Math.round(xc - y);

        int sy1 = (int)Math.round(yc + y);
        int sy2 = (int)Math.round(yc - y);
        int sy3 = (int)Math.round(yc + x);
        int sy4 = (int)Math.round(yc - x);

        colorirPonto(sx1, sy1, g);
        colorirPonto(sx1, sy2, g);
        colorirPonto(sx2, sy1, g);
        colorirPonto(sx2, sy2, g);
        colorirPonto(sx3, sy3, g);
        colorirPonto(sx3, sy4, g);
        colorirPonto(sx4, sy3, g);
        colorirPonto(sx4, sy4, g);
    }

    /**
     * Implementacao do algoritmo de Bresenham para desenho de uma circunferencia
     * @param g BufferedImage
     */
    @Override
    public void desenharFiguraBresenham(BufferedImage g) {
        this.isCircunferencia = true;
        double r = Math.sqrt(Math.pow((pontoFinal.x - pontoInicial.x),2) + Math.pow((pontoFinal.y - pontoInicial.y), 2));
        double x = 0.0;
        double y = r;
        double p = 3 - 2 * r;

        setPixels(x, y, pontoInicial.x, pontoInicial.y, g);

        while( x < y){
            if(p < 0) p+= 4 * x + 6;
            else {
                p += 4 * (x - y) + 10;
                y--;
            }
            x++;
            setPixels(x, y, pontoInicial.x, pontoInicial.y, g);
        }
    }
}
