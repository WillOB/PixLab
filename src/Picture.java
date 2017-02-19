import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture. This class inherits from SimplePicture and
 * allows the student to add functionality to the Picture class.
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture {
	///////////////////// constructors //////////////////////////////////

	/**
	 * Constructor that takes no arguments
	 */
	public Picture() {
		/*
		 * not needed but use it to show students the implicit call to super()
		 * child constructors always call a parent constructor
		 */
		super();
	}

	/**
	 * Constructor that takes a file name and creates the picture
	 * 
	 * @param fileName
	 *            the name of the file to create the picture from
	 */
	public Picture(String fileName) {
		// let the parent class handle this fileName
		super(fileName);
	}

	/**
	 * Constructor that takes the width and height
	 * 
	 * @param height
	 *            the height of the desired picture
	 * @param width
	 *            the width of the desired picture
	 */
	public Picture(int height, int width) {
		// let the parent class handle this width and height
		super(width, height);
	}

	/**
	 * Constructor that takes a picture and creates a copy of that picture
	 * 
	 * @param copyPicture
	 *            the picture to copy
	 */
	public Picture(Picture copyPicture) {
		// let the parent class do the copy
		super(copyPicture);
	}

	/**
	 * Constructor that takes a buffered image
	 * 
	 * @param image
	 *            the buffered image to use
	 */
	public Picture(BufferedImage image) {
		super(image);
	}

	////////////////////// methods ///////////////////////////////////////

	/**
	 * Method to return a string with information about this picture.
	 * 
	 * @return a string with information about the picture such as fileName,
	 *         height and width.
	 */
	public String toString() {
		String output = "Picture, filename " + getFileName() + " height " + getHeight() + " width " + getWidth();
		return output;

	}

	/** Method to set the blue to 0 */
	public void zeroBlue() {
		Pixel[][] pixels = this.getPixels2D();
		for (Pixel[] rowArray : pixels) {
			for (Pixel pixelObj : rowArray) {
				pixelObj.setBlue(0);
			}
		}
	}
	
	/**Method to set red and green to 0 */
	public void keepOnlyBlue(){
		Pixel[][] pixels = this.getPixels2D();
		for (Pixel[] rowArray : pixels) {
			for (Pixel pixelObj : rowArray) {
				pixelObj.setGreen(0);
				pixelObj.setRed(0);
			}
		}
	}
	
	/**Method to negate a picture */
	public void negate(){
		Pixel[][] pixels = this.getPixels2D();
		for (Pixel[] rowArray : pixels) {
			for (Pixel pixelObj : rowArray) {
				int red = pixelObj.getRed();
				int blue = pixelObj.getBlue();
				int green = pixelObj.getGreen();
				pixelObj.setRed(255 - red);
				pixelObj.setGreen(255 - green);
				pixelObj.setBlue(255 - blue);
			}
		}
	}
	
	/**Method to convert a picture to grayscale */
	public void grayscale(){
		Pixel[][] pixels = this.getPixels2D();
		for (Pixel[] rowArray : pixels) {
			for (Pixel pixelObj : rowArray) {
				int red = pixelObj.getRed();
				int green = pixelObj.getGreen();
				int blue = pixelObj.getBlue();
				int avg = (red + blue + green) / 3;
				pixelObj.setRed(avg);
				pixelObj.setGreen(avg);
				pixelObj.setBlue(avg);
			}
		}
	}
	
	/**Method to make fish easier to see in water.jpg */
	public void fixUnderwater(){
		Pixel[][] pixels = this.getPixels2D();
		for (Pixel[] rowArray : pixels) {
			for (Pixel pixelObj : rowArray) {
				int green = pixelObj.getGreen();
				int blue = pixelObj.getBlue();
				if (blue > green){
					pixelObj.setBlue(blue + 30);
				} else {
					pixelObj.setBlue(blue - 10);
				}
				
			}
		}
	}

	/**
	 * Method that mirrors the picture around a vertical mirror in the center of
	 * the picture from left to right
	 */
	public void mirrorVertical() {
		Pixel[][] pixels = this.getPixels2D();
		Pixel leftPixel = null;
		Pixel rightPixel = null;
		int width = pixels[0].length;
		for (int row = 0; row < pixels.length; row++) {
			for (int col = 0; col < width / 2; col++) {
				leftPixel = pixels[row][col];
				rightPixel = pixels[row][width - 1 - col];
				rightPixel.setColor(leftPixel.getColor());
			}
		}
	}

	/** Method to mirror a picture around vertical axis in center of picture
	 * from right to left
	 */
	public void mirrorVerticalRightToLeft() {
		Pixel [][] pixels = this.getPixels2D();
		Pixel leftPixel = null;
		Pixel rightPixel = null;
		int width = pixels[0].length;
		for (int row = 0; row < pixels.length; row++) {
			for (int col = 0; col <= width / 2; col++) {
				leftPixel = pixels[row][col];
				rightPixel = pixels[row][width - 1 - col];
				leftPixel.setColor(rightPixel.getColor());
			}
		}
	}
	
	/** Method to mirror a picture from top to bottom, around central horizontal axis */
	public void mirrorHorizontal(){
		Pixel[][] pixels = this.getPixels2D();
		Pixel topPixel = null;
		Pixel bottomPixel = null;
		int height = pixels.length;
		for (int column = 0; column < pixels[0].length; column++){
			for (int row = 0; row < height / 2; row++){
				topPixel = pixels[row][column];
				bottomPixel = pixels[height - 1 - row][column];
				bottomPixel.setColor(topPixel.getColor());
			}
		}
	}
	
	/** Method to mirror a picture from bottom to top, around horizontal axis. */
	public void mirrorHorizontalBotToTop() {
		Pixel[][] pixels = this.getPixels2D();
		Pixel topPixel = null;
		Pixel bottomPixel = null;
		int height = pixels.length;
		for (int column = 0; column < pixels[0].length; column++) {
			for (int row = 0; row < height / 2; row++){
				topPixel = pixels[row][column];
				bottomPixel = pixels[height - 1 - row][column];
				topPixel.setColor(bottomPixel.getColor());
			}
		}
	}
	
	/** Method to diagonally mirror a picture */
	public void mirrorDiagonal(){
		Pixel[][] pixels = this.getPixels2D();
		Pixel leftPixel = null;
		Pixel mirrorPixel = null;
		for (int row = 0; row < pixels.length; row++){
			for (int col = 0; col <= row; col++){
			leftPixel = pixels[row][col];
			mirrorPixel = pixels[col][row];
			mirrorPixel.setColor(leftPixel.getColor());
			}
		}
	}
	
	/** Mirror just part of a picture of a temple */
	public void mirrorTemple() {
		int mirrorPoint = 276;
		Pixel leftPixel = null;
		Pixel rightPixel = null;
		int count = 0;
		Pixel[][] pixels = this.getPixels2D();

		// loop through the rows
		for (int row = 27; row < 97; row++) {
			// loop from 13 to just before the mirror point
			for (int col = 13; col < mirrorPoint; col++) {

				leftPixel = pixels[row][col];
				rightPixel = pixels[row][mirrorPoint - col + mirrorPoint];
				rightPixel.setColor(leftPixel.getColor());
				
				count++;
			}
		}
		System.out.println(count);
	}

	/** Method to mirror the snowman's arms */
	public void mirrorArms(){
		Pixel[][] pixels = this.getPixels2D();
		int mirrorRow = 195;
		Pixel topPixel = null;
		Pixel bottomPixel = null;
		int count = 0;
		for (int row = 165; row < mirrorRow; row++){
			for (int col = 100; col < 300; col++){
				if (col < 170 || col > 238){
				topPixel = pixels[row][col];
				bottomPixel = pixels[225 - count][col];
				bottomPixel.setColor(topPixel.getColor());
				}
			}
			count++;
		}
	}
	
	/** Method to mirror the seagull */
	public void mirrorGull(){
		Pixel[][] pixels = this.getPixels2D();
		int mirrorCol = 350;
		Pixel leftPixel = null;
		Pixel rightPixel = null;
		for (int row = 233; row < 321; row++){
			int count = 0;
			for (int col = 235; col < mirrorCol; col++){
				leftPixel = pixels[row][col];
				rightPixel = pixels[row][465 - count];
				rightPixel.setColor(leftPixel.getColor());
				count++;
			}
		}
	}
	
	/**
	 * copy from the passed fromPic to the specified startRow and startCol in
	 * the current picture
	 * 
	 * @param fromPic
	 *            the picture to copy from
	 * @param startRow
	 *            the start row to copy to
	 * @param startCol
	 *            the start col to copy to
	 */
	public void copy(Picture fromPic, int startRow, int startCol) {
		Pixel fromPixel = null;
		Pixel toPixel = null;
		Pixel[][] toPixels = this.getPixels2D();
		Pixel[][] fromPixels = fromPic.getPixels2D();
		for (int fromRow = 0, toRow = startRow; fromRow < fromPixels.length
				&& toRow < toPixels.length; fromRow++, toRow++) {
			for (int fromCol = 0, toCol = startCol; fromCol < fromPixels[0].length
					&& toCol < toPixels[0].length; fromCol++, toCol++) {
				fromPixel = fromPixels[fromRow][fromCol];
				toPixel = toPixels[toRow][toCol];
				toPixel.setColor(fromPixel.getColor());
			}
		}
	}

	/**
	 * copy from the passed fromPic to the specified startRow and startCol in
	 * the current picture
	 * 
	 * @param fromPic
	 *            the picture to copy from
	 * @param startRow
	 *            the start row to copy to
	 * @param startCol
	 *            the start col to copy to
	 * @param startRowFrom
	 * 				the start row to copy from
	 * @param endRowFrom
	 * 				the end row to copy from
	 * @param startColFrom
	 * 				the start col to copy from
	 * @param endColFrom
	 * 				the last col to copy from
	 */
	public void copyWithParameters(Picture fromPic, int startRow, int startCol,
			int startRowFrom, int endRowFrom, int startColFrom, int endColFrom) {
		Pixel fromPixel = null;
		Pixel toPixel = null;
		Pixel[][] toPixels = this.getPixels2D();
		Pixel[][] fromPixels = fromPic.getPixels2D();
		for (int fromRow = 0, toRow = startRow; fromRow < fromPixels.length
				&& toRow < toPixels.length; fromRow++, toRow++) {
			if(fromRow >= startRowFrom && fromRow <= endRowFrom) {
				for (int fromCol = 0, toCol = startCol; fromCol < fromPixels[0].length
						&& toCol < toPixels[0].length; fromCol++, toCol++) {
					if(fromCol >= startColFrom && fromCol <= endColFrom){
					fromPixel = fromPixels[fromRow][fromCol];
					toPixel = toPixels[toRow][toCol];
					toPixel.setColor(fromPixel.getColor());
					}
				}
			}
		}
	}
	
	/** Method to create a collage of several pictures */
	public void createCollage() {
		Picture flower1 = new Picture("flower1.jpg");
		Picture flower2 = new Picture("flower2.jpg");
		this.copy(flower1, 0, 0);
		this.copy(flower2, 100, 0);
		this.copy(flower1, 200, 0);
		Picture flowerNoBlue = new Picture(flower2);
		flowerNoBlue.zeroBlue();
		this.copy(flowerNoBlue, 300, 0);
		this.copy(flower1, 400, 0);
		this.copy(flower2, 500, 0);
		this.mirrorVertical();
		//this.write("collage.jpg");
	}

	
	/**
	 * Method to create a collage, with one picture mirrored, one made blue, and one red
	 */
	public void myCollage(){
		Picture koala = new Picture("koala.jpg");
		Picture moon = new Picture("moon-surface.jpg");
		Picture door = new Picture("thruDoor.jpg");
		Picture koalaMirrored = new Picture(koala);
		koalaMirrored.mirrorVertical();
		koalaMirrored.keepOnlyBlue();
		koala.negate();
		this.copy(koala, 0, 0);
		this.copy(koalaMirrored, 0, 320);
		moon.zeroBlue();
		this.copy(moon, 240, 0);
		this.copy(door, 240, 320);
	}
	
	/**
	 * Method to show large changes in color
	 * 
	 * @param edgeDist
	 *            the distance for finding edges
	 */
	public void edgeDetection(int edgeDist) {
		Pixel leftPixel = null;
		Pixel rightPixel = null;
		Pixel topPixel = null;
		Pixel bottomPixel = null;
		Pixel[][] pixels = this.getPixels2D();
		Color rightColor = null;
		Color bottomColor = null;
		for (int row = 0; row < pixels.length; row++) {
			for (int col = 0; col < pixels[0].length - 1; col++) {
				leftPixel = pixels[row][col];
				rightPixel = pixels[row][col + 1];
				rightColor = rightPixel.getColor();
				if (leftPixel.colorDistance(rightColor) > edgeDist)
					leftPixel.setColor(Color.BLACK);
				else
					leftPixel.setColor(Color.WHITE);
			}
		}
		for (int col = 0; col < pixels[0].length; col++) {
			for (int row = 0; row < pixels.length - 1; row++) {
				topPixel = pixels[row][col];
				bottomPixel = pixels[row + 1][col];
				bottomColor = bottomPixel.getColor();
				if (topPixel.colorDistance(bottomColor) > edgeDist)
					topPixel.setColor(Color.BLACK);
			}
		}
	}
	
	/** 
	 * Second method to show large changes in color
	 * 
	 *  @param edgeDist
	 *  			the distance for finding edges
	 *  */
	public void edgeDetection2(int edgeDist) {
		Pixel leftPixel = null;
		Pixel rightPixel = null;
		Pixel topPixel = null;
		Pixel bottomPixel = null;
		Pixel[][] pixels = this.getPixels2D();
		for (Pixel[] rowArray : pixels) {
			for (Pixel pixelObj : rowArray) {
				int red = pixelObj.getRed();
				int green = pixelObj.getGreen();
				int blue = pixelObj.getBlue();
				pixelObj.setRed((red + blue + green) / 3);
			}
		}
		for (int row = 0; row < pixels.length; row++) {
			for (int col = 0; col < pixels[0].length - 1; col++) {
				leftPixel = pixels[row][col];
				rightPixel = pixels[row][col + 1];
				int rightColor = rightPixel.getRed();
				int leftColor = leftPixel.getRed();
				if (Math.abs(leftColor - rightColor) > edgeDist)
					leftPixel.setColor(Color.BLACK);
				else
					leftPixel.setColor(Color.WHITE);
			}
		}
		for (int col = 0; col < pixels[0].length; col++) {
			for (int row = 0; row < pixels.length - 1; row++) {
				topPixel = pixels[row][col];
				bottomPixel = pixels[row + 1][col];
				int topColor = topPixel.getRed();
				int bottomColor = bottomPixel.getRed();
				if (Math.abs(topColor - bottomColor) > edgeDist)
					topPixel.setColor(Color.BLACK);
			}
		}
	}

	/*
	 * Main method for testing - each class in Java can have a main method
	 */
	public static void main(String[] args) {
		Picture beach = new Picture("beach.jpg");
		beach.explore();
		beach.zeroBlue();
		beach.explore();
	}

} // this } is the end of class Picture, put all new methods before this
