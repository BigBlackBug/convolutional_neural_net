package neurons.conv;


import java.awt.Color;
import java.awt.image.BufferedImage;

import neurons.Neuron;

public class ImageInputNeuron extends Neuron{
	private BufferedImage image;
	private int x;
	private int y;
	
	public ImageInputNeuron(BufferedImage image, int x, int y) {
		this.image = image;
		this.x = x;
		this.y = y;
	}
	

	public BufferedImage getImage() {
		return image;
	}


	public void setImage(BufferedImage image) {
		this.image = image;
	}

	@Override
	public double computeNetValue() {
		Color color = new Color(image.getRGB(x, y));
		int red = color.getRed();
		int green = color.getGreen();
		int blue = color.getBlue();
		netValue=(Math.pow(0.2126*Math.pow(red,2.2) + 0.7152*Math.pow(green,2.2) + 0.0722*Math.pow(blue,2.2),1/2.2))/255;
		return netValue;
	}
}
