package activation;

import static java.lang.Math.tanh;

public class TangentFunction implements ActivationFunction {

	public static final double BETTA = 0.75;

	@Override
	public double activate(double x) {
		x = x * BETTA;
		return Math.tanh(x);
	}

	@Override
	public double derivative(double x) {
		x = x * BETTA;
		return 1 - Math.pow(tanh(x), 2);
	}

}
