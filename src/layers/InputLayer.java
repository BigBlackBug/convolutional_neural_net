package layers;

import java.util.List;

import neurons.InputNeuron;
import activation.EmptyActivator;


public class InputLayer extends FullyConnectedLayer{
	
	public InputLayer(int layerSize) {
		super(new EmptyActivator());
		for (int i = 0; i < layerSize; i++) {
			neurons.add(new InputNeuron());
		}
	}
	
	@Override
	protected void finalizeLayer() {
	}
	
	public void setInput(List<Double> input) {
		for(int i=0;i<input.size();i++){
			InputNeuron neuron = (InputNeuron) neurons.get(i);
			neuron.setValue(input.get(i));
		}
	}

}
