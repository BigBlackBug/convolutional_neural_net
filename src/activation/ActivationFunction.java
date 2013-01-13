package activation;

import java.util.List;

public interface ActivationFunction {
	public double activate(double x);
	public double derivative(double x);
}
