package convolution.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import layers.FullyConnectedLayer;
import layers.InputLayer;
import network.GeneralNeuralNetwork;
import training.Bpp2;
import training.Pair;
import activation.TangentFunction;

public class MainGen {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GeneralNeuralNetwork nn=new GeneralNeuralNetwork();
		nn.addLayer(new InputLayer(2));
		nn.addLayer(new FullyConnectedLayer(new TangentFunction(), 6));
		nn.addLayer(new FullyConnectedLayer(new TangentFunction(), 4));
		nn.addLayer(new FullyConnectedLayer(new TangentFunction(), 1));
		nn.finalize();
		
		Bpp2 bpp=new Bpp2(nn);
		List<Pair<List<Double>,List<Double>>> pairs=new ArrayList<Pair<List<Double>,List<Double>>>();
		pairs.add(new Pair<List<Double>,List<Double>>(Arrays.asList(1.0,1.0), Arrays.asList(0.0)));
		pairs.add(new Pair<List<Double>,List<Double>>(Arrays.asList(1.0,0.0), Arrays.asList(1.0)));
		pairs.add(new Pair<List<Double>,List<Double>>(Arrays.asList(0.0,1.0), Arrays.asList(1.0)));
		pairs.add(new Pair<List<Double>,List<Double>>(Arrays.asList(0.0,0.0), Arrays.asList(0.0)));
		bpp.train(pairs);
		System.out.println(3);
		
		System.out.println(nn.compute(pairs.get(2).first).get(0));
		System.out.println(nn.compute(pairs.get(1).first).get(0));
		System.out.println(nn.compute(pairs.get(3).first).get(0));
		System.out.println(nn.compute(pairs.get(0).first).get(0));

	}

}
