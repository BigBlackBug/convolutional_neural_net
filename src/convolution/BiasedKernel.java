package convolution;

import general.Weight;

import java.util.ArrayList;
import java.util.List;

public class BiasedKernel {
	public List<Kernel> weights;
	public Weight bias;
	
	public BiasedKernel(int kernelsCount,int kernelSize) {
		weights=new ArrayList<Kernel>();
		for (int i = 0; i < kernelsCount; i++) {
			weights.add(new Kernel(kernelSize));
		}
		bias=new Weight();
	}

	public Weight getKernelUnit(int kernel,int i, int j) {
		return weights.get(kernel).getWeightUnit(i, j);
	}

	public Kernel getKernel(int kernelPos) {
		return weights.get(kernelPos);
	}
}
