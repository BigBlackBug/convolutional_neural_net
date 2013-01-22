package general;

public class Weight {

	public double value;
	
	public Weight(double weight) {
		this.value = weight;
	}
	public Weight(){
		value=Math.random()*2-1;
	}

}
