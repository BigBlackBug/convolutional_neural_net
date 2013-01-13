package convolution.layers;

import general.Neuron;

import java.util.ArrayList;
import java.util.List;

import activation.ActivationFunction;

import convolution.FeatureMap;

public class ConvolutionLayer extends AbstractLayer{
	public List<FeatureMap> featureMaps=new ArrayList<FeatureMap>();
	private int kernelStep;
	private int kernelSize;
	private int fmCount;
	private int fmSize;
	
	
	public ConvolutionLayer(ActivationFunction activator, int fmCount, int kernelStep, int kernelSize){  
		super(activator);
		this.kernelStep = kernelStep;
		this.kernelSize = kernelSize;
		this.fmCount = fmCount;
	}
	
	public List<FeatureMap> getFeatureMaps() {
		return featureMaps;
	}
	
	@Override
	public List<Double> activate(){
		List<Double> output=new ArrayList<Double>();
		for (FeatureMap fm : featureMaps) {
			output.addAll(fm.activate());
		}
		return output;
	}

	@Override
	protected void finalizeLayer() {
		fmSize=1+(prevLayer.size()-kernelSize)/kernelStep;
		for (int i = 0; i < fmCount; i++) {
			featureMaps.add(new FeatureMap(prevLayer, activator, kernelStep, kernelSize, fmSize));
		}
	}

	@Override
	public List<Neuron> getNeurons() {//TODO cache
		List<Neuron> neurons=new ArrayList<Neuron>();
		for (FeatureMap fm : featureMaps) {
			neurons.addAll(fm.getNeurons());
		}
		return neurons;
	}

	@Override
	public int size() {
		return fmCount;
	}

	@Override
	public int getNeuronCount() {
		int total=0;
		for (FeatureMap fm : featureMaps) {
			total+=fm.getNeuronCount();
		}
		return total;
	}

	@Override
	public Neuron get(int i) {
		return getNeurons().get(i);
	}

	@Override
	public void computeSensivity() {
		// TODO Auto-generated method stub
		
	}

}
