package training;

import general.Neuron;

import java.util.List;

import layers.AbstractLayer;
import activation.ActivationFunction;
import convolution.main.GeneralNeuralNetwork;

public class Bpp2 {
	private static final double LEARNING_RATE = 0.2;
	private GeneralNeuralNetwork nn;
//	private double[] gradients;
//	private double[] prevGradients;

	public Bpp2(GeneralNeuralNetwork nn) {
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
			currentNeuron.updateBias(delta);
			for (int i = 0; i < input.size(); i++) {
				delta=LEARNING_RATE*currentNeuron.sensivity*input.get(i);
				delta*=input.get(i);
				currentNeuron.updateWeightFrom(i, delta);
			}
		}
	}
	
	public void train(List<Pair<List<Double>, List<Double>>> pairs) {
		double error;
		int epoch=0;
		long maxEpochCount=100000000L;
		do {
			error=0;
			for (Pair<List<Double>, List<Double>> pair : pairs) {
				error += iteration(pair.first, pair.second);
			}
			error/=pairs.size();
			System.out.println(error);
		} while(error>0.01 && ++epoch<maxEpochCount);
		System.out.println(epoch);
	
	} 
	
	public double iteration(List<Double> input, List<Double> target){
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
