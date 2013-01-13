package general;

public class Connection {
	public Neuron neuron;
	public Weight weight;
	
	public Connection(Neuron neuron, Weight weight) {
		this.neuron = neuron;
		this.weight = weight;
	}
}
