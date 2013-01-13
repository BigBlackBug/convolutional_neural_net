package general;

import java.util.ArrayList;
import java.util.List;

public class NeuronMatrix {
	private ArrayList<ArrayList<Neuron>> neurons = new ArrayList<ArrayList<Neuron>>();
	private int size = 0;

	public ArrayList<Neuron> get(int i) {
		return neurons.get(i);
	}

	public Neuron get(int i, int j) {
		return neurons.get(i).get(j);
	}

	public void add(ArrayList<Neuron> list) {
		neurons.add(list);
		size += list.size();
	}

	public List<Neuron> asList() {
		List<Neuron> result = new ArrayList<Neuron>();
		for (ArrayList<Neuron> list : neurons) {
			result.addAll(list);
		}
		return result;
	}

	public int size() {
		return size;
	}
}