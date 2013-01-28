package convolution;

import general.Neuron;

import java.awt.Color;
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
		Color color = new Color(image.getRGB(x, y));
		int red = color.getRed();
		int green = color.getGreen();
		int blue = color.getBlue();
		return Math.pow(0.2126*Math.pow(red,2.2) + 0.7152*Math.pow(green,2.2) + 0.0722*Math.pow(blue,2.2),1/2.2);
	}
}
