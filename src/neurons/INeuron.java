package neurons;

import java.util.List;

import activation.ActivationFunction;

public interface INeuron {

	public double activate(ActivationFunction activator);

	public double getOutput();

	public double getNetValue();

}