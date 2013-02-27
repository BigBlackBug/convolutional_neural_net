package neurons.misc;

import java.util.Random;

public class Weight {
	private static final Random RANDOM = new Random(System.nanoTime());
	private static final double BOUNDARY = 0.5;
	public double value;
	private double prevDelta;
	
	public Weight(){
		value=BOUNDARY-RANDOM.nextDouble()*BOUNDARY*2;
	}
	
	public void updateWeight(double delta) {
		value += delta;
		prevDelta = delta;
	}
	
	public double getPrevDelta() {
		return prevDelta;
	}

}
