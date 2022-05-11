import java.util.Date;
import java.util.Random;

public class KadaiNi {

    static final double ALPHA = 0.001;
    double a;
    double b;
    double r;

    public double getA() {
        return a;
    }
    public double getB() {
        return b;
    }
    public void setA(double a) {
        this.a = a;
    }
    public void setB(double b) {
        this.b = b;
    }

    public static void main(String[] args) {
        KadaiNi kadaiNi = new KadaiNi();
        kadaiNi.initParameters();
        for (int i = 0; i < 500; i++) {
            double eSum = 0.0;
            for (int j =0; j < 100; j++) {
                double x = kadaiNi.randOne(0)*100-50;
                kadaiNi.setA(kadaiNi.getA() - (KadaiIchi.ALPHA * kadaiNi.funcDa(x)));
                kadaiNi.setB(kadaiNi.getB() - (KadaiIchi.ALPHA * kadaiNi.funcDb(x)));
                eSum += kadaiNi.funcError(kadaiNi.funcY(x), kadaiNi.funcYHat(x));
            }
            double eAve = eSum / 100;
            System.out.format("%d, %f\n", i, eAve);
        }
        System.out.format("a = %f, b = %f\n", kadaiNi.getA(), kadaiNi.getB());
    }

    void initParameters() {
        long seed = new Date().getTime();
        a = randOne(seed);
        b = randOne(seed);
        r = randOne(seed);
    }
    double randOne(long seed) {
        Random generator;
        if (seed == 0) {
            generator = new Random();
        } else {
            generator = new Random(seed);
        }
        return generator.nextDouble();
    }
    double funcDa(double x) {
        double y = funcY(x);
        double yHat = funcYHat(x);
        double da = (y - yHat)*x;
        return da;
    }
    double funcY(double x) {
        double y = a * x + b;
        return y;
    }
    double funcYHat(double x) {
        double yHat = 5.0 * x - 3.0 + (r * 0.1);
        return yHat;
    }
    double funcDb(double x) {
        double y = funcY(x);
        double yHat = funcYHat(x);
        double db = (y - yHat)*1;
        return db;
    }
    double funcError(double y, double yHat) {
        double e = 0.5 * (y - yHat) * (y - yHat);
        return e;
    }
}
