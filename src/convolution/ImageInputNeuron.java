package convolution;

import general.Neuron;

import java.awt.image.BufferedImage;

import activation.ActivationFunction;

public class ImageInputNeuron extends Neuron{
	private BufferedImage image;
	private int x;
	private int y;
	
	public ImageInputNeuron(BufferedImage image, int x, int y) {
		this.image = image;
		this.x = x;
		this.y = y;
	}

	@Override
	public double computeNetValue() {
		return image.getRGB(x, y);
	}
}
