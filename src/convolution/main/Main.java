package convolution.main;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import layers.AbstractLayer;
import layers.FullyConnectedLayer;
import layers.conv.ConvolutionLayer;
import layers.conv.FeatureMap;
import layers.conv.ImageInputLayer;
import network.conv.ConvolutionalNeuralNetwork;
import training.Bpp;
import activation.EmptyActivator;
import activation.TangentFunction;

public class Main {

	private static BufferedImage outputToImage(List<Double> source){
		int size = (int) Math.sqrt(source.size());
		int[] pix=new int[source.size()];
		Random r = new Random();
		for (int i = 0; i < pix.length; i++) {
			
			pix[i]=(int) (source.get(i)*255*0x00010101);
		}
		
	    Toolkit tk = Toolkit.getDefaultToolkit();
	    Image createImage = tk.createImage(new MemoryImageSource(100, 50, pix, 0, 100));
	    BufferedImage buffered = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
	    buffered.getGraphics().drawImage(createImage, 0, 0 , null);
	    return buffered;
	}
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		ConvolutionalNeuralNetwork cnn=new ConvolutionalNeuralNetwork();
		cnn.addLayer(new ImageInputLayer(new EmptyActivator(), 29, 29));
		cnn.addLayer(new ConvolutionLayer(new TangentFunction(), 6, 2, 5));
		cnn.addLayer(new ConvolutionLayer(new TangentFunction(), 50, 2, 5));
		cnn.addLayer(new FullyConnectedLayer(new TangentFunction(), 50));
		cnn.addLayer(new FullyConnectedLayer(new TangentFunction(), 1));
		cnn.finalize();
		Bpp bpp=new Bpp(cnn);
		BufferedImage piece=ImageIO.read(new File("C://piece.bmp"));
		BufferedImage positive=ImageIO.read(new File("C://positive.bmp"));
		BufferedImage negative=ImageIO.read(new File("C://negative.bmp"));
	    bpp.train(positive, Arrays.asList(1.0));
//	    List<Double> compute = cnn.compute(img);
//	    System.out.println(compute.get(0));
//	    compute = cnn.compute(imgTest);
//	    System.out.println(compute.get(0));
	    List<Double> compute = cnn.compute(negative);
	    System.out.println(compute.get(0));
	    List<Double> comp3ute = cnn.compute(positive);
	    System.out.println(comp3ute.get(0));
//	    


	}
}

