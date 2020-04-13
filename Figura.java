import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Figura {

    public Ponto pontoInicial, pontoFinal;
    public Color cor;
    public boolean isCircunferencia;
    public boolean isDentroJanela;
    double u1, u2;

    public Figura(double xInicial, double yInicial, double xFinal, double yFinal, Color cor) {
        this.pontoInicial = new Ponto( xInicial, yInicial);
        this.pontoFinal = new Ponto( xFinal, yFinal);
        this.cor = cor;
        this.isCircunferencia = false;
        this.isDentroJanela = true;
    }
    public Figura(){
        this.pontoFinal = null;
        this.pontoInicial = null;
        this.cor = Color.black;
        this.isDentroJanela = true;
        this.isCircunferencia = false;}

    public Figura(boolean isCircunferencia){
        this.pontoFinal = null;
        this.pontoInicial = null;
        this.cor = Color.black;
        this.isDentroJanela = true;
        this.isCircunferencia = isCircunferencia;
    }

    public void colorirPonto (int x, int y, BufferedImage g){
        g.setRGB(x,y,this.cor.getRGB());
    }

    /**
     * Implementacao do algoritmo de CohenSutherland para recorte
     * @param pontoMin pontoMinimo da Janela de Recorte
     * @param pontoMax pontoMaximo da Janela de Recorte
     */
    public void cohenSutherland(Ponto pontoMin, Ponto pontoMax){
        boolean aceito = false, feito = false;

        int cFora;

        double xInt = 0.0, yInt = 0.0;

        double xMin = pontoMin.x;
        double yMin = pontoMin.y;
        double xMax = pontoMax.x;
        double yMax = pontoMax.y;

        double x1 = this.pontoInicial.x;
        double y1 = this.pontoInicial.y;
        double x2 = this.pontoFinal.x;
        double y2 = this.pontoFinal.y;

        while(!feito){
            int c1 = getCodigo(this.pontoInicial, pontoMin, pontoMax);
            int c2 = getCodigo(this.pontoFinal, pontoMin, pontoMax);

            if(c1 == 0 && c2 == 0){ // dentro da janela
                aceito = true;
                feito = true;
            } else if ((c1 & c2) != 0){ // totalmente fora da janela
                this.isDentroJanela = false;
                feito = true;
            } else {
                cFora = (c1 != 0) ? c1 : c2;

                if(getBit(cFora, 0 ) == 1){
                    xInt = xMin;
                    yInt = y1 + (y2 - y1) * (xMin - x1) / (x2 - x1);
                }else if(getBit(cFora, 1) == 1){
                    xInt = xMax;
                    yInt = y1 + (y2 - y1) * (xMax - x1) / (x2 - x1);
                }else if(getBit(cFora, 2) == 1){
                    yInt = yMin;
                    xInt = x1 + (x2 - x1) * (yMin - y1) / (y2 - y1);
                }else if(getBit(cFora, 3) == 1){
                    yInt = yMax;
                    xInt = x1 + (x2 - x1) * (yMax - y1) / (y2 - y1);
                }
                if(cFora == c1){
                    x1 = xInt;
                    y1 = yInt;
                    this.pontoInicial = new Ponto(Math.round(xInt), Math.round(yInt));
                }else{
                    x2 = xInt;
                    y2 = yInt;
                    this.pontoFinal = new Ponto(Math.round(xInt), Math.round(yInt));
                }
            }
        }

    }

    /**
     * Implementacao do algoritmo de Liang Barsky para recorte
     * @param pontoMin pontoMinimo da Janela de Recorte
     * @param pontoMax pontoMaximo da Janela de Recorte
     */
    public void liangBarsky(Ponto pontoMin, Ponto pontoMax){
        double x1 = this.pontoInicial.x;
        double y1 = this.pontoInicial.y;
        double x2 = this.pontoFinal.x;
        double y2 = this.pontoFinal.y;

        double dX = x2 - x1;
        double dY = y2 - y1;

        this.u1 = 0.0;
        this.u2 = 1.0;

        this.isDentroJanela = false;

        if(clipSet(-dX,x1 - pontoMin.x))
            if(clipSet(dX, pontoMax.x - x1))
                if(clipSet(-dY, y1 - pontoMin.y))
                    if(clipSet(dY, pontoMax.y - y1)){
                        if(u2 < 1.0){
                            x2 = x1 + dX * u2;
                            y2 = y1 + dY * u2;
                        }
                        if(u1 > 0.0){
                            x1 = x1 + dX * u1;
                            y1 = y1 + dY * u1;
                        }

                        this.isDentroJanela = true;
                        this.pontoInicial = new Ponto(Math.round(x1), Math.round(y1));
                        this.pontoFinal = new Ponto(Math.round(x2), Math.round(y2));
                    }
    }


    /*Metodos que vao ser implementados pelas classes Reta e Circunferencia*/
    public abstract void desenharFiguraDDA(BufferedImage g);
    public abstract void desenharFiguraBresenham(BufferedImage g);
    public abstract void rotacionarFigura(double grau);
    public abstract void mudarEscalaFigura(double escalaX, double escalaY);
    public abstract void moverFigura(Ponto novoPonto);
    public abstract void espelharFigura(int opcode);
    public abstract void shearFigura(double fatorX, int opcode);

    @Override
    public String toString() {
        return "Figura{" +
                "pontoInicial=" + pontoInicial +
                ", pontoFinal=" + pontoFinal +
                '}';
    }

    /**
     * Metodo de ClipSet para uso no algoritmo de Liang Barsky
     * @param p
     * @param q
     * @return
     */
    public boolean clipSet(double p, double q){
        double r = q / p;

        if(p < 0){
            if(r > 1){
                return false;
            }
            else if(r > u1){
                u1 = r;
            }
        }else if(p > 0){
            if(r < 0){
                return false;
            }
            else if(r < u2){
                u2 = r;
            }
        }else if(q < 0){
            return false;
        }

        return true;
    }

    /**
     * Metodo obtem qual o codigo de posicao da reta para o algoritmo de Cohen-Sutherland
     * @param ponto Ponto da reta
     * @param pontoMin Ponto min do recorte
     * @param pontoMax Ponto max do recorte
     * @return codigo de posicao
     */
    public int getCodigo(Ponto ponto, Ponto pontoMin, Ponto pontoMax){
        int codigo = 0;

        if(ponto.x < pontoMin.x)
            codigo++;
        if(ponto.x > pontoMax.x)
            codigo += 2;
        if(ponto.y < pontoMin.y)
            codigo += 4;
        if(ponto.y > pontoMax.y)
            codigo += 8;

        return codigo;
    }

    /**
     * Metodo pega o valor de bit que sera comparado no algoritmo de Cohen-Sutherland
     * @param codigo valor para se retirar o bit
     * @param pos posicao do bit
     * @return valor do bit
     */
    public int getBit(int codigo, int pos){
        int bit = codigo << (31 - pos);
        bit = bit >>> 31;
        return bit;
    }
}