package network;

import java.util.ArrayList;
import java.util.List;

import layers.AbstractLayer;
import layers.InputLayer;


public class GeneralNeuralNetwork extends NeuralNetwork{
	public List<Double> compute(List<Double> input){
		AbstractLayer firstLayer = layers.get(0);
		InputLayer inputLayer = (InputLayer) firstLayer;
		inputLayer.setInput(input);
		
		List<Double> output=new ArrayList<Double>();
		do{
			output=firstLayer.activate();
		}while((firstLayer=firstLayer.getNextLayer())!=null);
		
		return output;
	}

	@Override
	public void finalize() {
		AbstractLayer prevLayer = layers.get(0);
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
