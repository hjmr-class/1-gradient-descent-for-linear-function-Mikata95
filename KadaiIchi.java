import java.util.Date;
import java.util.Random;

public class KadaiIchi {

    static final double ALPHA = 0.001;
    double a;
    double b;

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
        KadaiIchi kadaiIchi = new KadaiIchi();
        kadaiIchi.initParameters();
        for (int i = 0; i < 500; i++) {
            double eSum = 0.0;
            for (int j =0; j < 100; j++) {
                double x = kadaiIchi.randOne(0)*100-50;
                kadaiIchi.setA(kadaiIchi.getA() - (KadaiIchi.ALPHA * kadaiIchi.funcDa(x)));
                kadaiIchi.setB(kadaiIchi.getB() - (KadaiIchi.ALPHA * kadaiIchi.funcDb(x)));
                eSum += kadaiIchi.funcError(kadaiIchi.funcY(x), kadaiIchi.funcYHat(x));
            }
            double eAve = eSum / 100;
            System.out.format("%d, %f\n", i, eAve);
        }
        System.out.format("a = %f, b = %f\n", kadaiIchi.getA(), kadaiIchi.getB());
    }

    void initParameters() {
        long seed = new Date().getTime();
        a = randOne(seed);
        b = randOne(seed);
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
        double yHat = 5.0 * x - 3.0;
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