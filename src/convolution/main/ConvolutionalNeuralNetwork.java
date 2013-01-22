package convolution.main;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import convolution.layers.AbstractLayer;
import convolution.layers.ImageInputLayer;

public class ConvolutionalNeuralNetwork {
	private List<AbstractLayer> layers;
	
	public ConvolutionalNeuralNetwork() {
		layers=new ArrayList<AbstractLayer>();
	}
	
	/**
	 * at the current stage of development you MUST call this method after you
	 * finish adding new layers
	 */
	public void finalize() {// FIXME bullshit look at finalize of neuron
		AbstractLayer prevLayer = layers.get(0);
		if (prevLayer instanceof ImageInputLayer) {

			AbstractLayer nextLayer = layers.get(1);
			prevLayer.finalize(null, nextLayer);
			for (int i = 1; i < layers.size() - 1; i++) {
				AbstractLayer layer = layers.get(i);
				layer.finalize(layers.get(i - 1), layers.get(i + 1));
			}
			prevLayer = layers.get(layers.size() - 1);
			prevLayer.finalize(layers.get(layers.size() - 2), null);
		}
	}
	
	public void addLayer(AbstractLayer layer){
		layers.add(layer);
	}
	public List<AbstractLayer> getLayers() {
		return layers;
	}
	public List<Double> compute(BufferedImage input){
		((ImageInputLayer)layers.get(0)).setImage(input);//FIXME bullshit
		List<Double> output=new ArrayList<Double>();
		for (AbstractLayer layer : layers) {
			output=layer.activate();
		}
		return output;
	}

	public AbstractLayer getLastLayer() {
		return layers.get(layers.size()-1);
	}
}
