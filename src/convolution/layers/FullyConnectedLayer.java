package convolution.layers;

import general.Connection;
import general.Neuron;
import general.Weight;

import java.util.ArrayList;
import java.util.List;

import activation.ActivationFunction;

public class FullyConnectedLayer extends AbstractLayer{
	private List<Neuron> neurons=new ArrayList<Neuron>();
	
	public FullyConnectedLayer(ActivationFunction activator, int layerSize) {
		super(activator);
		for (int i = 0; i < layerSize; i++) {
			neurons.add(new Neuron());
		}
	}

	public List<Double> getOutput(){
		List<Double> output=new ArrayList<Double>();
		for(Neuron neuron:neurons){
			if(neuron.activated){
				output.add(neuron.output);
			}else{
				throw new IllegalArgumentException("neurons are not activated");
			}
		}
		return output;
	}
	
	@Override
	protected void finalizeLayer() {
		for (Neuron neuron : neurons) {
			for(Neuron otherNeuron:prevLayer.getNeurons()){
				neuron.addConnection(new Connection(otherNeuron, new Weight()));
			}
			neuron.bias=new Weight();
		}
	}

	@Override
	public List<Double> activate() {
		List<Double> output=new ArrayList<Double>();
		for (Neuron neuron : neurons) {
			output.add(neuron.activate(activator));
		}
		return output;
	}
	
	@Override
	public List<Neuron> getNeurons() {
		return neurons;
	}

	@Override
	public int size() {
		return getNeuronCount();
	}

	@Override
	public int getNeuronCount() {
		return neurons.size();
	}
	
	@Override
	public void computeSensivity() {
		for (int j = 0; j < size(); j++) {
			Neuron neuron = neurons.get(j);
			double sensivity = activator.derivative(neuron.netValue)*getSum(nextLayer, j);
			neuron.setSensivity(sensivity);
		}
	}

	private double getSum(AbstractLayer nLayer,int j) {
		double value=0;
		for (int i = 0; i < nLayer.size(); i++) {
			Neuron otherNeuron = nLayer.get(i);
			value+=otherNeuron.sensivity*otherNeuron.getWeightFrom(j);
		}
		return value;
	}

	@Override
	public Neuron get(int i) {
		return neurons.get(i);
	}
}
