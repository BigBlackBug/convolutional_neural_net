package neurons.misc;

import neurons.INeuron;

public class Connection {
	public INeuron neuron;
	public Weight weight;
	
	public Connection(INeuron neuron, Weight weight) {
		this.neuron = neuron;
		this.weight = weight;
	}
}
