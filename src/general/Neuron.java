package general;

import java.util.ArrayList;
import java.util.List;

import activation.ActivationFunction;

public class Neuron {

	protected List<Connection> connections=new ArrayList<Connection>();
	public Weight bias;
	public double netValue;
	public double output;
	public double sensivity;
	public boolean activated=false;
	
	
	public void addConnection(Connection connection) {
		connections.add(connection);
	}
	
	public double activate(ActivationFunction activator){
		activated=true;
		output = activator.activate(computeNetValue());//TODO cache net value
		return output;
	}
	
	public double computeNetValue(){
		double value = 0;
		for(Connection conn:connections){
			value+=conn.weight.value*conn.neuron.output;
		}
		netValue=value+bias.value;
		return netValue;
	}

	public void setSensivity(double sensivity) {
		this.sensivity=sensivity;
	}

	public double getWeightFrom(int j) {
		return connections.get(j).weight.value;
	}

}