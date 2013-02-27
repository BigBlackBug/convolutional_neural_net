package convolution.main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

import layers.FullyConnectedLayer;
import layers.conv.ConvolutionLayer;
import layers.conv.ImageInputLayer;
import network.conv.ConvolutionalNeuralNetwork;
import training.Bpp;
import activation.TangentFunction;

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
		cnn.addLayer(new FullyConnectedLayer(new TangentFunction(), 50));
		cnn.addLayer(new FullyConnectedLayer(new TangentFunction(), 1));
		cnn.finalize();
		Bpp bpp=new Bpp(cnn);
		BufferedImage img=ImageIO.read(new File("C://piece.bmp"));
		BufferedImage imgTest=ImageIO.read(new File("C://test.bmp"));
		BufferedImage imgTest2=ImageIO.read(new File("C://test2.bmp"));
//		ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
//	    ColorConvertOp op = new ColorConvertOp(cs, null);
//
//	    BufferedImage bufferedImage = new BufferedImage(img.getWidth(), img.getHeight(),BufferedImage.TYPE_BYTE_GRAY);
//	    bufferedImage = op.filter(img, null);
//	    ImageIO.write(bufferedImage, "jpg", new File("c://test2.jpg"));
		
	    bpp.train(img, Arrays.asList(0.0));
	    
	    List<Double> compute = cnn.compute(img);
	    System.out.println(compute.get(0));
	    compute = cnn.compute(imgTest);
	    System.out.println(compute.get(0));
	    compute = cnn.compute(imgTest2);
	    System.out.println(compute.get(0));
//	    
//	    BufferedImage test=ImageIO.read(new File("C://test.bmp"));
//	    int count=0;
//	    for(int i=0;i<test.getWidth()/2;i++){
//	    	for (int j = 0; j < test.getHeight()/2; j++) {
//	    		BufferedImage sub = test.getSubimage(i, j, 29, 29);
//	    		List<Double> compute2 = cnn.compute(sub);
//	    		Double r = compute2.get(0);
//	    		if(Math.abs(r-1)<0.05){
//	    			count++;
//	    		}
//			}
//	    }
//	    System.out.println(count);
//	    
	    
//		


	}
}

