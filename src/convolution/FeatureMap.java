package convolution;

import general.Connection;
import general.Neuron;
import general.NeuronMatrix;

import java.util.ArrayList;
import java.util.List;

import activation.ActivationFunction;
import convolution.layers.AbstractLayer;
import convolution.layers.ConvolutionLayer;
import convolution.layers.ImageInputLayer;

public class FeatureMap {
	private int kernelStep;
	private int kernelSize;
	private int fmSize;
	private NeuronMatrix neurons;
	private BiasedKernel kernel;//тут веса ко всем предыдущим FM
	private ActivationFunction activator;
	
	public FeatureMap(AbstractLayer previousLayer, ActivationFunction activator, 
			int kernelStep, int kernelSize, int fmSize) {
		this.kernelStep = kernelStep;
		this.kernelSize = kernelSize;
		this.activator=activator;
		this.fmSize = fmSize;
		initNeurons();
		if(previousLayer instanceof ConvolutionLayer){
			ConvolutionLayer layer=(ConvolutionLayer)previousLayer;
			initWeights(layer.getFeatureMaps().size());
			init(layer);
		}else if(previousLayer instanceof ImageInputLayer){
			ImageInputLayer layer=(ImageInputLayer)previousLayer;
			initWeights();
			init(layer);
		}
		
	}

//	public FeatureMap(){
//		initNeurons();
//	}
	
	private void initWeights(int kernelsCount){//prev featuremap size
		kernel=new BiasedKernel(kernelsCount, kernelSize);
	}
	
	private void initWeights(){//prev featuremap size
		kernel=new BiasedKernel(0, kernelSize);
	}
	
	private void initNeurons(){
		for (int i = 0; i < fmSize; i++) {
			ArrayList<Neuron> list=new ArrayList<Neuron>();
			for (int j = 0; j < fmSize; j++) {
				list.add(new Neuron());
			}
			neurons.add(list);
		}
	}
	
	private void init(ConvolutionLayer layer){
		List<FeatureMap> featureMaps=layer.getFeatureMaps();
		initWeights(featureMaps.size());
		
		for(int k=0; k<featureMaps.size(); k++){//6FIXME optimize cycle
			for (int i = 0; i < fmSize; i++) {
				for (int j = 0; j < fmSize; j++) {//100
					Neuron neuron = neurons.get(i,j);
					List<ArrayList<Neuron>> neurons = featureMaps.get(k).getNeuronBlock(i, j, kernelSize, kernelStep);
					connect(neuron,neurons,k);
				}
			}
		}
	}

	private void init(ImageInputLayer layer){
		initWeights(1);
		for (int i = 0; i < fmSize; i++) {
			for (int j = 0; j < fmSize; j++) {
				Neuron neuron = neurons.get(i,j);
				List<ArrayList<Neuron>> neurons=layer.getNeuronBlock(i,j,kernelSize,kernelStep);
				connect(neuron,neurons);
			}
		}
	}

	/**
	 * connects aNeuron to all of the neurons in the provided list
	 * using the weight values from the kernel
	 * @param aNeuron
	 * @param list
	 */
	private void connect(Neuron aNeuron, List<ArrayList<Neuron>> list, int kernelPos) {
		assert list.size()==kernelSize;
		Kernel currKernel = kernel.getKernel(kernelPos);
		
		for (int i = 0; i < kernelSize; i++) {
			ArrayList<Neuron> otherNeurons=list.get(i);
			assert otherNeurons.size()==kernelSize;
			
			for (int j = 0; j < kernelSize; j++) {
				Neuron otherNeuron = otherNeurons.get(j);
				aNeuron.addConnection(new Connection(otherNeuron, currKernel.getWeightUnit(i, j)));
			}
		}//and bias
		aNeuron.bias=kernel.bias;
	}
	
	private void connect(Neuron aNeuron, List<ArrayList<Neuron>> list) {
		connect(aNeuron, list, 0);
	}
	
	private List<ArrayList<Neuron>> getNeuronBlock(int i, int j, int blockSize, int stepSize) {
		int startx=i*stepSize;
		int starty=j*stepSize;
		List<ArrayList<Neuron>> block=new ArrayList<ArrayList<Neuron>>();
		for (int ii = startx; ii < startx + blockSize; ii++) {
			ArrayList<Neuron> line = new ArrayList<Neuron>();
			for (int jj = starty; jj < starty + blockSize; jj++) {
				line.add(neurons.get(ii).get(jj));
			}
			block.add(line);
		}
		return block;
	}
	
	public List<Double> activate(){
		List<Double> output=new ArrayList<Double>();
		for (int i = 0; i < kernelSize; i++) {
			for (int j = 0; j < kernelSize; j++) {
				output.add(neurons.get(i, j).activate(activator));
			}
		}
		return output;
	}
	
	public int getNeuronCount(){
		return neurons.size();
	}

	public List<Neuron> getNeurons() {
		return neurons.asList();
	}
}