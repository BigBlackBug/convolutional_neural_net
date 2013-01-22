package convolution.layers;

import general.Neuron;

import java.util.List;

import activation.ActivationFunction;


public abstract class AbstractLayer {
	protected AbstractLayer prevLayer;
	protected AbstractLayer nextLayer;
	protected ActivationFunction activator;
	
	protected List<Double> lastOutput;
	public AbstractLayer(ActivationFunction activator) {
		this.activator = activator;
	}

	public ActivationFunction getActivator() {
		return activator;
	}
	public AbstractLayer getNextLayer() {
		return nextLayer;
	}
	
	public AbstractLayer getPrevLayer() {
		return prevLayer;
	}
	
	public void finalize(AbstractLayer prevLayer,AbstractLayer nextLayer){//TODO maybe make it call finalize for other layers?
		this.nextLayer=nextLayer;
		this.prevLayer=prevLayer;
		finalizeLayer();
	}
	public List<Double> activate(){
		lastOutput=activateLayer();
		return lastOutput;
	}
	
	public List<Double> getLastOutput() {
		return lastOutput;
	}
	
	protected abstract void finalizeLayer();
	protected abstract List<Double> activateLayer();
	public abstract List<Neuron> getNeurons();
	public abstract Neuron get(int i);
	public abstract int size();
	public abstract int getNeuronCount();

//	public abstract void computeSensivity();
	
}
