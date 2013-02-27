package training;

import java.awt.image.BufferedImage;
import java.util.List;

import layers.AbstractLayer;
import network.conv.ConvolutionalNeuralNetwork;
import neurons.INeuron;
import neurons.Neuron;
import neurons.misc.Connection;
import activation.ActivationFunction;

public class Bpp {
	private static final double LEARNING_RATE = 0.8;
	private static final double MOMENTUM_RATE = 0.6;
	private ConvolutionalNeuralNetwork nn;

	public Bpp(ConvolutionalNeuralNetwork nn) {
		this.nn = nn;
	}
	private void computeSensivityLast(AbstractLayer lastLayer, List<Double> target){
		List<INeuron> neurons = lastLayer.getNeurons();
		ActivationFunction activator = lastLayer.getActivator();
		for (int i = 0; i < neurons.size(); i++) {
			Neuron neuron = (Neuron) neurons.get(i);
			neuron.setSensivity((target.get(i) - neuron.getOutput())
					* activator.derivative(neuron.getNetValue()));
		}
	}
	
	private void computeSensivity(AbstractLayer layer) {
		AbstractLayer nextLayer = layer.getNextLayer();
		ActivationFunction activator = layer.getActivator();
		for (int j = 0; j < layer.size(); j++) {
			Neuron neuron = (Neuron) layer.get(j);
			double sensivity = activator.derivative(neuron.getNetValue())* getSum(nextLayer, j);
			neuron.setSensivity(sensivity);
		}
	}

	private double getSum(AbstractLayer nextLayer, int j) {
		double value = 0;
		for (int i = 0; i < nextLayer.size(); i++) {
			Neuron otherNeuron = (Neuron) nextLayer.get(i);
			value += otherNeuron.getSensivity() * otherNeuron.getWeightFrom(j);
		}
		return value;
	}

	
	public void updateWeights(AbstractLayer layer) {
		double delta = 0;
		for (int j = 0; j < layer.size(); j++) {
			Neuron currentNeuron = (Neuron) layer.get(j);
			delta = LEARNING_RATE * currentNeuron.getSensivity();
			currentNeuron.updateBias(delta);
			for (Connection conn : currentNeuron.getConnections()) {
				double input = conn.neuron.getOutput();
				delta = LEARNING_RATE * currentNeuron.getSensivity() * input
						+ MOMENTUM_RATE * conn.weight.getPrevDelta();
				conn.weight.updateWeight(delta);
			}

		}
	}
	
	public void train(BufferedImage input, List<Double> target) {
		double error;
		int epoch=0;
		long maxEpochCount=100000000L;
		do {
			error=0;
			error += iteration(input, target);
			System.out.println(error);
		} while(error>0.01 && ++epoch<maxEpochCount);
		System.out.println(epoch);
	
	} 
	
	public double iteration(BufferedImage input, List<Double> target){
		AbstractLayer layer=nn.getLastLayer();
		List<Double> output = nn.compute(input);
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