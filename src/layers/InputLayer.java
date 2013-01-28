package layers;

import java.util.ArrayList;
import java.util.List;

import activation.EmptyActivator;


public class InputLayer extends FullyConnectedLayer{
	private List<Double> input=new ArrayList<Double>();
	
	public InputLayer(int layerSize) {
		super(new EmptyActivator(),layerSize);
	}
	
	@Override
	protected void finalizeLayer() {
	}
	
	public void setInput(List<Double> input) {
		this.input = input;
	}
	
	@Override
	public List<Double> activateLayer() {
		return input;
	}

}
