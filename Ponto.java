public class Ponto {
    public double x;
    public double y;

    public Ponto(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    public Ponto clone(){
        return (new Ponto(this.x, this.y));
    }
}