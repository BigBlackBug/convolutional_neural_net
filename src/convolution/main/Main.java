package convolution.main;

import java.awt.Graphics2D;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

import training.BackPropagation;
import activation.TangentFunction;
import convolution.layers.ConvolutionLayer;
import convolution.layers.FullyConnectedLayer;
import convolution.layers.ImageInputLayer;

public class Main {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		ConvolutionalNeuralNetwork cnn=new ConvolutionalNeuralNetwork();
		cnn.addLayer(new ImageInputLayer(new TangentFunction(), 29, 29));
		cnn.addLayer(new ConvolutionLayer(new TangentFunction(), 6, 2, 5));
		cnn.addLayer(new ConvolutionLayer(new TangentFunction(), 50, 2, 5));
		cnn.addLayer(new FullyConnectedLayer(new TangentFunction(), 100));
		cnn.addLayer(new FullyConnectedLayer(new TangentFunction(), 1));
		cnn.finalize();
		BackPropagation bpp=new BackPropagation(cnn);
		BufferedImage img=ImageIO.read(new File("C://piece.bmp"));

//		ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
//	    ColorConvertOp op = new ColorConvertOp(cs, null);
//
//	    BufferedImage bufferedImage = new BufferedImage(img.getWidth(), img.getHeight(),BufferedImage.TYPE_BYTE_GRAY);
//	    bufferedImage = op.filter(img, null);
//	    ImageIO.write(bufferedImage, "jpg", new File("c://test2.jpg"));
		
	    bpp.train(img, Arrays.asList(1.0));
	    
//	    List<Double> compute = cnn.compute(img);
	    
	    BufferedImage test=ImageIO.read(new File("C://test.bmp"));
	    int count=0;
	    for(int i=0;i<test.getWidth()/2;i++){
	    	for (int j = 0; j < test.getHeight()/2; j++) {
	    		BufferedImage sub = test.getSubimage(i, j, 29, 29);
	    		List<Double> compute = cnn.compute(sub);
	    		Double r = compute.get(0);
	    		if(Math.abs(r-1)<0.05){
	    			count++;
	    		}
			}
	    }
	    System.out.println(count);
	    
//	    System.out.println(compute.get(0));
		

	}

}
