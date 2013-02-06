package layers;

import java.util.ArrayList;
import java.util.List;

import neurons.INeuron;
import neurons.Neuron;
import neurons.misc.Connection;
import neurons.misc.Weight;
import activation.ActivationFunction;

public class FullyConnectedLayer extends AbstractLayer{
	protected List<INeuron> neurons=new ArrayList<INeuron>();
	
	public FullyConnectedLayer(ActivationFunction activator, int layerSize) {
		super(activator);
		for (int i = 0; i < layerSize; i++) {
			neurons.add(new Neuron());
		}
	}
	
	protected FullyConnectedLayer(ActivationFunction activator){
		super(activator);
	}

	public List<Double> getOutput(){
		List<Double> output=new ArrayList<Double>();
		for(INeuron neuron:neurons){
			output.add(neuron.getOutput());
		}
		return output;
	}
	
	@Override
	
	protected void finalizeLayer() {
		for (int i = 0; i < neurons.size(); i++) {
			Neuron neuron = (Neuron) neurons.get(i);
			for(INeuron otherNeuron: prevLayer.getNeurons()){
				neuron.addConnection(new Connection(otherNeuron, new Weight()));
			}
			neuron.setBias(new Weight());
		}
	}

	@Override
	public List<Double> activateLayer() {
		List<Double> output=new ArrayList<Double>();
		for (INeuron neuron : neurons) {
			output.add(neuron.activate(activator));
		}
		return output;
	}
	
	@Override
	public List<INeuron> getNeurons() {
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
	public INeuron get(int i) {
		return neurons.get(i);
	}
}
