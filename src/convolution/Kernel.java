package convolution;


import general.Weight;

import java.util.ArrayList;
import java.util.List;

public class Kernel {
	
	public List<ArrayList<Weight>> weights=new ArrayList<ArrayList<Weight>>();
	
	public Kernel(int size) {
		for (int i = 0; i < size; i++) {
			ArrayList<Weight> list=new ArrayList<Weight>();
			for (int j = 0; j < size; j++) {
				list.add(new Weight());
			}
			weights.add(list);
		}
	}
	
	public Weight getWeightUnit(int i, int j){
		return weights.get(i).get(j);
	}
}
