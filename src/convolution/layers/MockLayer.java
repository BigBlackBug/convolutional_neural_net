package convolution.layers;

import general.Neuron;

import java.util.List;

import activation.ActivationFunction;
import activation.EmptyActivator;


public class MockLayer extends AbstractLayer{
	
	public MockLayer() {
		super(new EmptyActivator());
	}

	@Override
	protected void finalizeLayer() {
		throw new RuntimeException("not imlpemented yet");
	}

	@Override
	public List<Double> activate() {
		throw new RuntimeException("not imlpemented yet");
	}

	@Override
	public List<Neuron> getNeurons() {
		throw new RuntimeException("not imlpemented yet");
	}

	@Override
	public int size() {
		throw new RuntimeException("not imlpemented yet");
	}

	@Override
	public int getNeuronCount() {
		throw new RuntimeException("not imlpemented yet");
	}

	@Override
	public Neuron get(int i) {
		throw new RuntimeException("not imlpemented yet");
	}

	@Override
	public void computeSensivity() {
		throw new RuntimeException("not imlpemented yet");
	}

}
