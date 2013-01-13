package training;

import java.awt.image.BufferedImage;
import java.util.List;

import convolution.layers.AbstractLayer;
import convolution.main.ConvolutionalNeuralNetwork;

public class BackPropagation {
	private ConvolutionalNeuralNetwork nn;

	public BackPropagation(ConvolutionalNeuralNetwork nn) {
		this.nn = nn;
	}
	public void train(BufferedImage image, List<Double> output){
		AbstractLayer layer=nn.getLastLayer();
		
//		OutputLayer outputLayer = (OutputLayer)layers.get(layers.size()-1);
//		outputLayer.setTarget(target);
//		List<Double> output;
//		double fidd;
//		output = compute(input);
//		for (int i = layers.size() - 1; i >= 0; i--) {
//			layers.get(i).computeSensivity();
//		}
//		for (GeneralLayer layer : layers) {
//			layer.updateWeights();
//		}
	}
}
