package activation;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public interface ActivationFunction {
	public double activate(double x);
	public double derivative(double x);
}
