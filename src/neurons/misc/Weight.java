package neurons.misc;

import java.util.Random;

public class Weight {
	private static final Random RANDOM = new Random(System.nanoTime());
	public double value;
	
	public Weight(){
		value=RANDOM.nextDouble()*0.5-1;
	}

}
