/**
 * 
 */
package myshape;

import java.awt.*;

/**
 * @author Tapiwa Musasa and Ang Li
 * 
 *  We will define the features that the shapes would need probably the following: 
 *  color, width, height, position, borderline, 
 */
public abstract class MyShape {
	
	private int height;
	private Color Shapecolor;
	private int width;
	private Point location = new Point(0, 0);

	/**
	 * 
	 */
	public MyShape() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the color
	 */
	public Color getMyDrawingColor() {
		return Shapecolor;
	}

	/**
	 * @param Shapecolor the color to set
	 */
	public void setMyDrawingColor(Color color) {
		this.Shapecolor = color;
	}

	/**
	 * @return the width
	 */
	public int getShapeWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setShapeWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the location
	 */
	public Point getDrawingLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setDrawingLocation(Point location) {
		this.location = location;
	}

	/**
	 * @return the height
	 */
	public int getDrawingHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setDrawingHeight(int height) {
		this.height = height;
	}
	
	public abstract void draw();

}
