package network;

import java.util.ArrayList;
import java.util.List;

import layers.AbstractLayer;


public abstract class NeuralNetwork {
	protected List<AbstractLayer> layers;
	public NeuralNetwork() {
		layers=new ArrayList<AbstractLayer>();
	}
	/**
	 * at the current stage of development you MUST call this method after you
	 * finish adding new layers
	 */
	public abstract void finalize() ;// FIXME bullshit look at finalize of neuron
	
	public void addLayer(AbstractLayer layer){
		layers.add(layer);
	}
	
	public List<AbstractLayer> getLayers() {
		return layers;
	}
	
	public AbstractLayer getLastLayer() {
		return layers.get(layers.size()-1);
	}
	
}
