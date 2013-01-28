package convolution.main;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import layers.AbstractLayer;
import layers.ImageInputLayer;


public class ConvolutionalNeuralNetwork extends NeuralNetwork {
	public List<Double> compute(BufferedImage input){
		((ImageInputLayer)layers.get(0)).setImage(input);//FIXME bullshit
		List<Double> output=new ArrayList<Double>();
		for (AbstractLayer layer : layers) {
			output=layer.activate();
		}
		return output;
	}

	@Override
	public void finalize() {
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
		}else{
			throw new IllegalArgumentException("invalid structure.;");
		}
	}
}
