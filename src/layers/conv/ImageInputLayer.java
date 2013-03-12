package layers.conv;


import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import neurons.INeuron;
import neurons.Neuron;
import neurons.conv.ImageInputNeuron;
import neurons.misc.NeuronMatrix;

import layers.AbstractLayer;

import activation.ActivationFunction;

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
		for(INeuron neuron:neurons.asList()){
			ImageInputNeuron imageNeuron=((ImageInputNeuron)neuron);
			result.add(imageNeuron.activate(activator));
		}
		return result;
	}

	@Override
	protected void finalizeLayer() {
		if(image!=null){
			for (int y = 0; y < height; y++) {
				ArrayList<Neuron> list = neurons.get(y);
				for (int x = 0; x < width; x++) {
					ImageInputNeuron imageNeuron = (ImageInputNeuron) list.get(x);
					imageNeuron.setImage(image);
				}
			}
		}else{
			for (int y = 0; y < width; y++) {
				ArrayList<Neuron> list = new ArrayList<Neuron>();
				for (int x = 0; x < height; x++) {
					list.add(new ImageInputNeuron(image, x, y));
				}
				neurons.add(list);
			}
		}
	}

	@Override
	public List<INeuron> getNeurons() {
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
			ArrayList<Neuron> theseNeurons = neurons.get(ii);
			
			ArrayList<Neuron> line=new ArrayList<Neuron>();
			for(int jj=startj;jj<startj+kernelSize;jj++){
				line.add(theseNeurons.get(jj));
			}
			block.add(line);
		}
		return block;
	}

	@Override
	public INeuron get(int i) {
		return neurons.asList().get(i);
	}

	public void setImage(BufferedImage image) {
		assert image.getWidth() == width;
		assert image.getHeight() == height;
		this.image=image;
		finalizeLayer();
	}

//	@Override
//	public void computeSensivity() {
//		
//	}
}
