package neurons;

import java.util.List;

import activation.ActivationFunction;

public class InputNeuron implements INeuron {

	private double value;

	public void setValue(double value) {
		this.value = value;
	}

	public double getValue() {
		return value;
	}

	@Override
	public double activate(ActivationFunction activator) {
		return value;
	}

	@Override
	public double getOutput() {
		return value;
	}

	@Override
	public double getNetValue() {
		return value;
	}

}
