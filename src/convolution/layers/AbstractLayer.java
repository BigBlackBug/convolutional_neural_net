package convolution.layers;

import general.Neuron;

import java.util.List;

import activation.ActivationFunction;


public abstract class AbstractLayer {
	protected AbstractLayer prevLayer;
	protected AbstractLayer nextLayer;
	protected ActivationFunction activator;
	
	public AbstractLayer(ActivationFunction activator) {
		this.activator = activator;
	}

	public void finalize(AbstractLayer prevLayer,AbstractLayer nextLayer){//TODO maybe make it call finalize for other layers?
		this.nextLayer=nextLayer;
		this.prevLayer=prevLayer;
		finalizeLayer();
	}
	
	protected abstract void finalizeLayer();
	public abstract List<Double> activate();
	public abstract List<Neuron> getNeurons();
	public abstract Neuron get(int i);
	public abstract int size();
	public abstract int getNeuronCount();

	public abstract void computeSensivity();
	
}
