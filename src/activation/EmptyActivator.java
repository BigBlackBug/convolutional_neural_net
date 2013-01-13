package activation;

public class EmptyActivator implements ActivationFunction{

	@Override
	public double activate(double x) {
		return x;
	}

	@Override
	public double derivative(double x) {
		return x;
	}

}
