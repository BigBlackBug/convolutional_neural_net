package neurons;

import java.util.ArrayList;
import java.util.List;

import neurons.misc.Connection;
import neurons.misc.Weight;
import activation.ActivationFunction;

public class Neuron implements INeuron {
	protected double netValue;
	private double output;
	private double sensivity;
	private List<Connection> connections=new ArrayList<Connection>();
	
	private Weight bias;
	
	public void addConnection(Connection connection) {
		getConnections().add(connection);
	}
	
	@Override
	public double activate(ActivationFunction activator){
		output = activator.activate(computeNetValue());//TODO cache net value
		return output;
	}
	
	protected double computeNetValue(){
		double value = 0;
		for(Connection conn:getConnections()){
			value+=conn.weight.value*conn.neuron.getOutput();
		}
		netValue = (value+bias.value);
		return netValue;
	}

	
	public void setSensivity(double sensivity) {
		this.sensivity=sensivity;
	}

	public double getWeightFrom(int j) {
		return connections.get(j).weight.value;
	}
	
	public void updateWeightFrom(int i,double delta) {
		Weight prevWeight = connections.get(i).weight;
		prevWeight.value+=delta;
	}

	public void updateBias(double d) {
		bias.value+=d;
	}

	@Override
	public double getNetValue() {
		return netValue;
	}

	@Override
	public double getOutput() {
		return output;
	}

	
	public double getSensivity() {
		return sensivity;
	}

	public List<Connection> getConnections() {
		return connections;
	}

	public void setConnections(List<Connection> connections) {
		this.connections = connections;
	}

	public Weight getBias() {
		return bias;
	}

	public void setBias(Weight bias) {
		this.bias = bias;
	}

}
