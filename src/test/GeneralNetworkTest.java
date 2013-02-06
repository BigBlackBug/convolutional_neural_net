package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import layers.FullyConnectedLayer;
import layers.InputLayer;

import network.GeneralNeuralNetwork;

import org.junit.Before;
import org.junit.Test;

import training.Bpp2;
import training.Pair;
import activation.TangentFunction;

public class GeneralNetworkTest {
	GeneralNeuralNetwork nn;
	List<Pair<List<Double>,List<Double>>> pairs=new ArrayList<Pair<List<Double>,List<Double>>>();
	@Before
	public void setUp() throws Exception {
		nn=new GeneralNeuralNetwork();
		nn.addLayer(new InputLayer(2));
		nn.addLayer(new FullyConnectedLayer(new TangentFunction(), 6));
		nn.addLayer(new FullyConnectedLayer(new TangentFunction(), 1));
		nn.finalize();
		
		Bpp2 bpp=new Bpp2(nn);
		pairs.add(new Pair<List<Double>,List<Double>>(Arrays.asList(1.0,1.0), Arrays.asList(0.0)));
		pairs.add(new Pair<List<Double>,List<Double>>(Arrays.asList(1.0,0.0), Arrays.asList(1.0)));
		pairs.add(new Pair<List<Double>,List<Double>>(Arrays.asList(0.0,1.0), Arrays.asList(1.0)));
		pairs.add(new Pair<List<Double>,List<Double>>(Arrays.asList(0.0,0.0), Arrays.asList(0.0)));
		bpp.train(pairs);
	}

	@Test
	public void test() {
		Pair<List<Double>, List<Double>> pair = pairs.get(2);
		assertEquals(pair.second.get(0), nn.compute(pair.first).get(0), 0.01);
		pair = pairs.get(1);
		assertEquals(pair.second.get(0), nn.compute(pair.first).get(0), 0.01);
		pair = pairs.get(0);
		assertEquals(pair.second.get(0), nn.compute(pair.first).get(0), 0.01);
		pair = pairs.get(3);
		assertEquals(pair.second.get(0), nn.compute(pair.first).get(0), 0.01);
	}

}
