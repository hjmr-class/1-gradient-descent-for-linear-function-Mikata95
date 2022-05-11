import java.util.Date;
import java.util.Random;

public class KadaiSan {

    static final double ALPHA = 0.001;
    double a;
    double b;
    double c;

    public double getA() {
        return a;
    }
    public double getB() {
        return b;
    }
    public double getC() {
        return c;
    }
    public void setA(double a) {
        this.a = a;
    }
    public void setB(double b) {
        this.b = b;
    }
    public void setC(double c) {
        this.c = c;
    }

    public static void main(String[] args) {
        KadaiSan kadaiSan = new KadaiSan();
        kadaiSan.initParameters();
        for (int i = 0; i < 500; i++) {
            double eSum = 0.0;
            for (int j =0; j < 100; j++) {
                double x = kadaiSan.randOne(0)*10-5;
                kadaiSan.setA(kadaiSan.getA() - (KadaiSan.ALPHA * kadaiSan.funcDa(x)));
                kadaiSan.setB(kadaiSan.getB() - (KadaiSan.ALPHA * kadaiSan.funcDb(x)));
                kadaiSan.setC(kadaiSan.getC() - (KadaiSan.ALPHA * kadaiSan.funcDc(x)));
                eSum += kadaiSan.funcError(kadaiSan.funcY(x), kadaiSan.funcYHat(x));
            }
            double eAve = eSum / 100;
            System.out.format("%d, %f\n", i, eAve);
        }
        System.out.format("a = %f, b = %f, c = %f\n", kadaiSan.getA(), kadaiSan.getB(), kadaiSan.getC());
    }

    void initParameters() {
        long seed = new Date().getTime();
        a = randOne(seed);
        b = randOne(seed);
        c = randOne(seed);
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
        double da = (y - yHat) * x * x;
        return da;
    }

    double funcY(double x) {
        double y = a * x * x + b * x + c;
        return y;
    }

    double funcYHat(double x) {
        double yHat = x * x + 2 * x +  3;
        return yHat;
    }

    double funcDb(double x) {
        double y = funcY(x);
        double yHat = funcYHat(x);
        double db = (y - yHat) * x;
        return db;
    }

    double funcDc(double x) {
        double y = funcY(x);
        double yHat = funcYHat(x);
        double dc = (y - yHat) * 1;
        return dc;
    }

    double funcError(double y, double yHat) {
        double e = 0.5 * (y - yHat) * (y - yHat);
        return e;
    }

}