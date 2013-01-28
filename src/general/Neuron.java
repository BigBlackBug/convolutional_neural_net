package general;

import java.util.ArrayList;
import java.util.List;

import activation.ActivationFunction;

public class Neuron {
	public double netValue;
	public double output;
	public double sensivity;
	public boolean activated=false;
	public List<Connection> connections=new ArrayList<Connection>();
	
	public Weight bias;
	
	public void addConnection(Connection connection) {
		connections.add(connection);
	}
	
	/* (non-Javadoc)
	 * @see general.INeuron#activate(activation.ActivationFunction)
	 */
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
	
	public void updateWeightFrom(int i,double delta) {
		Weight prevWeight = connections.get(i).weight;
		prevWeight.value+=delta;
	}

	public void updateBias(double d) {
		bias.value+=d;
	}

}
