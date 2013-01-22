package convolution.layers;

import general.Neuron;
import general.NeuronMatrix;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import activation.ActivationFunction;

import convolution.FeatureMap;
import convolution.ImageInputNeuron;

public class ImageInputLayer extends AbstractLayer{

	private BufferedImage image;
	private int height;
	private int width;
	private NeuronMatrix neurons=new NeuronMatrix();

	public ImageInputLayer(ActivationFunction activator,int width,int height){
		super(activator);
		this.height=height;
		this.width=width;
	}
	
	@Override
	public List<Double> activateLayer() {
		List<Double> result=new ArrayList<Double>();
		for(Neuron neuron:neurons.asList()){
			ImageInputNeuron imageNeuron=((ImageInputNeuron)neuron);
			result.add(imageNeuron.activate(activator));
		}
		return result;
	}

	@Override
	protected void finalizeLayer() {
		neurons.clear();
		for (int i = 0; i < width; i++) {
			ArrayList<Neuron> list = new ArrayList<Neuron>();
			for (int j = 0; j < height; j++) {
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
//		return image.getWidth();
		return width;
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
		return neurons.asList().get(i);
	}

	public void setImage(BufferedImage image) {
		this.image=image;
		finalizeLayer();
	}

//	@Override
//	public void computeSensivity() {
//		
//	}
}
