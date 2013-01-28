package convolution.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import layers.FullyConnectedLayer;
import layers.InputLayer;
import training.Bpp2;
import training.Pair;
import activation.TangentFunction;

public class Main {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
//		ConvolutionalNeuralNetwork cnn=new ConvolutionalNeuralNetwork();
//		cnn.addLayer(new ImageInputLayer(new TangentFunction(), 29, 29));
//		cnn.addLayer(new ConvolutionLayer(new TangentFunction(), 6, 2, 5));
//		cnn.addLayer(new ConvolutionLayer(new TangentFunction(), 50, 2, 5));
//		cnn.addLayer(new FullyConnectedLayer(new TangentFunction(), 100));
//		cnn.addLayer(new FullyConnectedLayer(new TangentFunction(), 1));
//		cnn.finalize();
//		BackPropagation bpp=new BackPropagation(cnn);
//		BufferedImage img=ImageIO.read(new File("C://piece.bmp"));

//		ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
//	    ColorConvertOp op = new ColorConvertOp(cs, null);
//
//	    BufferedImage bufferedImage = new BufferedImage(img.getWidth(), img.getHeight(),BufferedImage.TYPE_BYTE_GRAY);
//	    bufferedImage = op.filter(img, null);
//	    ImageIO.write(bufferedImage, "jpg", new File("c://test2.jpg"));
		
//	    bpp.train(img, Arrays.asList(1.0));
	    
//	    List<Double> compute = cnn.compute(img);
//	    
//	    BufferedImage test=ImageIO.read(new File("C://test.bmp"));
//	    int count=0;
//	    for(int i=0;i<test.getWidth()/2;i++){
//	    	for (int j = 0; j < test.getHeight()/2; j++) {
//	    		BufferedImage sub = test.getSubimage(i, j, 29, 29);
//	    		List<Double> compute = cnn.compute(sub);
//	    		Double r = compute.get(0);
//	    		if(Math.abs(r-1)<0.05){
//	    			count++;
//	    		}
//			}
//	    }
//	    System.out.println(count);
	    
//	    System.out.println(compute.get(0));
		
		GeneralNeuralNetwork nn=new GeneralNeuralNetwork();
		nn.addLayer(new InputLayer(2));
		nn.addLayer(new FullyConnectedLayer(new TangentFunction(), 6));
		nn.addLayer(new FullyConnectedLayer(new TangentFunction(), 1));
		nn.finalize();
		
		Bpp2 bpp=new Bpp2(nn);
		List<Pair<List<Double>,List<Double>>> pairs=new ArrayList<Pair<List<Double>,List<Double>>>();
		pairs.add(new Pair<List<Double>,List<Double>>(Arrays.asList(1.0,1.0), Arrays.asList(0.0)));
		pairs.add(new Pair<List<Double>,List<Double>>(Arrays.asList(1.0,0.0), Arrays.asList(1.0)));
		pairs.add(new Pair<List<Double>,List<Double>>(Arrays.asList(0.0,1.0), Arrays.asList(1.0)));
		pairs.add(new Pair<List<Double>,List<Double>>(Arrays.asList(0.0,0.0), Arrays.asList(0.0)));
		bpp.train(pairs);
		System.out.println(3);
		System.out.println(nn.compute(pairs.get(3).first).get(0));
		System.out.println(nn.compute(pairs.get(2).first).get(0));
		System.out.println(nn.compute(pairs.get(1).first).get(0));
		System.out.println(nn.compute(pairs.get(0).first).get(0));

	}
}

