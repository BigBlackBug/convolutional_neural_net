package general;

import java.util.Random;

public class Weight {
	private static final Random RANDOM = new Random();
	public double value;
	
	public Weight(){
		value=RANDOM.nextDouble()*0.6-1;
	}

}
