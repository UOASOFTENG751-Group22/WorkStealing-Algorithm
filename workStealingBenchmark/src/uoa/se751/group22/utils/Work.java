package uoa.se751.group22.utils;
import java.awt.Color;

public class Work {

	public static void computeWork(int value) {
	    double xmin = -1.0;
	    double ymin = -1.0;
	    double width = 2.0;
	    double height = 2.0;
	    for (int i = 0; i < value; i++) {
	        for (int j = 0; j < value; j++) {
	            double x = xmin + i * width / value;
	            double y = ymin + j * height / value;
	            Complex z = new Complex(x, y);
	            newton(z);
	        }
	    }
	    long currentThreadID = Thread.currentThread().getId();
	    //System.out.println("-- Thread "+currentThreadID+ " finished work("+value+")");
	}
	
	private static class Complex {
	    private final double re;   // the real part
	    private final double im;   // the imaginary part
	    
	    // create a new object with the given real and imaginary parts
	    public Complex(double real, double imag) {
	        re = real;
	        im = imag;
	    }
	    
	    // return a string representation of the invoking Complex object
	    public String toString() {
	        if (im == 0) return re + "";
	        if (re == 0) return im + "i";
	        if (im <  0) return re + " - " + (-im) + "i";
	        return re + " + " + im + "i";
	    }

	    public double abs()   { return Math.hypot(re, im); } 

	    // return a new Complex object whose value is (this - b)
	    public Complex minus(Complex b) {
	        Complex a = this;
	        double real = a.re - b.re;
	        double imag = a.im - b.im;
	        return new Complex(real, imag);
	    }

	    // return a new Complex object whose value is (this * b)
	    public Complex times(Complex b) {
	        Complex a = this;
	        double real = a.re * b.re - a.im * b.im;
	        double imag = a.re * b.im + a.im * b.re;
	        return new Complex(real, imag);
	    }

	    // return a new Complex object whose value is the reciprocal of this
	    public Complex reciprocal() {
	        double scale = re*re + im*im;
	        return new Complex(re / scale, -im / scale);
	    }

	    // return a / b
	    public Complex divides(Complex b) {
	        Complex a = this;
	        return a.times(b.reciprocal());
	    }
	}
	
	private static Color newton(Complex z) {
	    double EPSILON = 0.00000001;
	    Complex four = new Complex(4, 0);
	    Complex one = new Complex(1, 0);
	    Complex root1 = new Complex(1, 0);
	    Complex root2 = new Complex(-1, 0);
	    Complex root3 = new Complex(0, 1);
	    Complex root4 = new Complex(0, -1);
	    for (int i = 0; i < 100; i++) {
	        Complex f = z.times(z).times(z).times(z).minus(one);
	        Complex fp = four.times(z).times(z).times(z);
	        z = z.minus(f.divides(fp));
	        if (z.minus(root1).abs() <= EPSILON) return Color.WHITE;
	        if (z.minus(root2).abs() <= EPSILON) return Color.RED;
	        if (z.minus(root3).abs() <= EPSILON) return Color.GREEN;
	        if (z.minus(root4).abs() <= EPSILON) return Color.BLUE;
	    }
	    return Color.BLACK;
	}

}
