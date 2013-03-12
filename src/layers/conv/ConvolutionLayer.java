package layers.conv;


import java.util.ArrayList;
import java.util.List;

import neurons.INeuron;
import neurons.Neuron;

import layers.AbstractLayer;

import activation.ActivationFunction;


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
	public List<Double> activateLayer(){
		List<Double> output=new ArrayList<Double>();
		for (FeatureMap fm : featureMaps) {
			output.addAll(fm.activate());
		}
		
		return output;
	}

	@Override
	protected void finalizeLayer() {
		if(prevLayer instanceof ImageInputLayer){
			fmSize=1+(prevLayer.size()-kernelSize)/kernelStep;
		}else{
			ConvolutionLayer l=(ConvolutionLayer)prevLayer;
			fmSize=1+(l.fmSize-kernelSize)/kernelStep;
		}
		for (int i = 0; i < fmCount; i++) {
			featureMaps.add(new FeatureMap(prevLayer, activator, kernelStep, kernelSize, fmSize));
		}
	}

	@Override
	public List<INeuron> getNeurons() {//TODO cache
		List<INeuron> neurons=new ArrayList<INeuron>();
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
	public INeuron get(int i) {
		return getNeurons().get(i);
	}

//	@Override
//	public void computeSensivity() {
//		// TODO Auto-generated method stub
//		
//	}

}
