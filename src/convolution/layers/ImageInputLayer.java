package convolution.layers;

import general.Neuron;
import general.NeuronMatrix;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import activation.ActivationFunction;

import convolution.ImageInputNeuron;

public class ImageInputLayer extends AbstractLayer{

	private BufferedImage image;
	private NeuronMatrix neurons=new NeuronMatrix();

	public ImageInputLayer(ActivationFunction activator,BufferedImage image){
		super(activator);
		this.image=image;
	}
	
	@Override
	public List<Double> activate() {
		List<Double> result=new ArrayList<Double>();
		for(Neuron neuron:neurons.asList()){
			ImageInputNeuron imageNeuron=((ImageInputNeuron)neuron);
			result.add(imageNeuron.activate(activator));
		}
		return result;
	}

	@Override
	protected void finalizeLayer() {
		for(int i=0;i<image.getWidth();i++){
			ArrayList<Neuron> list=new ArrayList<Neuron>();
			for(int j=0;j<image.getHeight();j++){
				list.add(new ImageInputNeuron(image, i, j));
			}
			neurons.add(list);
		}
	}

	@Override
	public List<Neuron> getNeurons() {
		return neurons.asList();
	}

	@Override
	public int size() {//TODO only squares are currently supported
		return image.getWidth();
	}

	@Override
	public int getNeuronCount() {
		return neurons.size();
	}

	public List<ArrayList<Neuron>> getNeuronBlock(int i, int j, int kernelSize, int kernelStep) {
		int starti=i*kernelStep;
		int startj=j*kernelStep;
		List<ArrayList<Neuron>> block=new ArrayList<ArrayList<Neuron>>();
		for(int ii=starti;ii<starti+kernelSize;ii++){
			ArrayList<Neuron> line=new ArrayList<Neuron>();
			ArrayList<Neuron> theseNeurons = neurons.get(i);
			for(int jj=startj;jj<startj+kernelSize;jj++){
				line.add(theseNeurons.get(j));
			}
			block.add(line);
		}
		return block;
	}

	@Override
	public Neuron get(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void computeSensivity() {
		
	}
}
