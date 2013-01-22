package training;

import general.Neuron;

import java.awt.image.BufferedImage;
import java.util.List;

import activation.ActivationFunction;
import convolution.layers.AbstractLayer;
import convolution.main.ConvolutionalNeuralNetwork;

public class BackPropagation {
	private static final double LEARNING_RATE = 0.8;
	private ConvolutionalNeuralNetwork nn;
	private double[] gradients;
	private double[] prevGradients;

	public BackPropagation(ConvolutionalNeuralNetwork nn) {
		this.nn = nn;
	}
	private void computeSensivityLast(AbstractLayer lastLayer, List<Double> target){
		List<Neuron> neurons = lastLayer.getNeurons();
		ActivationFunction activator = lastLayer.getActivator();
		for (int i = 0; i < neurons.size(); i++) {
			Neuron neuron = neurons.get(i);
			neuron.setSensivity((target.get(i) - neuron.output)
					* activator.derivative(neuron.netValue));
		}
	}
	
	private void computeSensivity(AbstractLayer layer) {
		AbstractLayer nextLayer = layer.getNextLayer();
		ActivationFunction activator = layer.getActivator();
		for (int j = 0; j < layer.size(); j++) {
			Neuron neuron = layer.get(j);
			double sensivity = activator.derivative(neuron.netValue)* getSum(nextLayer, j);
			neuron.setSensivity(sensivity);
		}
	}

	private double getSum(AbstractLayer nextLayer, int j) {
		double value = 0;
		for (int i = 0; i < nextLayer.size(); i++) {
			Neuron otherNeuron = nextLayer.get(i);
			value += otherNeuron.sensivity * otherNeuron.getWeightFrom(j);
		}
		return value;
	}
	
	public void updateWeights(AbstractLayer layer){
		double delta=0;
		List<Double> input = layer.getPrevLayer().getLastOutput();
		for(int j=0;j<layer.size();j++){
			Neuron currentNeuron = layer.get(j);
			delta=LEARNING_RATE*currentNeuron.sensivity;
			currentNeuron.bias.value=delta;
			for (int i = 0; i < input.size(); i++) {
				delta*=input.get(i);
				currentNeuron.updateWeightFrom(j, delta);
			}
		}
	}
	
	public void train(BufferedImage image, List<Double> target){

		long maxEpochCount=10000000000L;
		double epoch=0;
		double error;
		do {
			error=0;
//			for (Pair<List<Double>, List<Double>> pair : pairs) {
				error += iteration(image, target);
//			}
//			error/=pairs.size();
			System.out.println(error);
		} while(error>0.01 && ++epoch<maxEpochCount);
		
		
	}
	
	public double iteration(BufferedImage image, List<Double> target){
		AbstractLayer layer=nn.getLastLayer();
		List<Double> output = nn.compute(image);
		computeSensivityLast(layer, target);
		List<AbstractLayer> layers = nn.getLayers();
		for (int i = layers.size() - 2; i > 0; i--) {
			computeSensivity(layers.get(i));
		}
		
		for (int i = 1; i < layers.size(); i++) {
			updateWeights(layers.get(i));
		}

		double fidd = calculateTargetFunction(output, target);
		return fidd;
	}
	
	public double calculateTargetFunction(List<Double> output,List<Double> target){
		assert output.size()==target.size();
		double result=0;
		for (int i = 0; i < output.size(); i++) {
			result+=Math.sqrt(Math.abs(output.get(i)-target.get(i)));
		}
		return result/2;
	}
}
