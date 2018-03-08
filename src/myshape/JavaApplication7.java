/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package myshape;

/**
 *
 * @author angli
 */

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;



public class JavaApplication7 extends JFrame {

  public JavaApplication7() { 
    // add check box group
    ButtonGroup ShapesGroup = new ButtonGroup();
    JRadioButton shapeLine = new JRadioButton("Shape line");
    JRadioButton shapeOval = new JRadioButton("Oval shape");
    JRadioButton shapeRect = new JRadioButton("Rectangle shape");
    ShapesGroup.add(shapeLine);
    ShapesGroup.add(shapeOval);
    ShapesGroup.add(shapeRect);
    shapeRect.setSelected(true);
    JPanel shapePamel = new JPanel(new FlowLayout());
    shapePamel.add(shapeLine);
    shapePamel.add(shapeOval);
    shapePamel.add(shapeRect);
    this.setLayout(new BorderLayout());
    this.add(shapePamel, BorderLayout.NORTH);
    this.setSize(600, 600);
    this.setTitle("My drawing dashboard");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.add(new PaintSurface(), BorderLayout.CENTER);
    this.setVisible(true);
  }

  private class PaintSurface extends JComponent {

    ArrayList<Shape> shapes = new ArrayList<Shape>();
    String myShapeRect = "rect";
    String myShapeOval = "oval";
    String myShapeLine = "line";

    Point startDrag, endDrag;

    public PaintSurface() {
      this.addMouseListener(new MouseAdapter() {
        public void mousePressed(MouseEvent e) {
          startDrag = new Point(e.getX(), e.getY());
          endDrag = startDrag;
          repaint();
        }
        
        public void actionPerformed(ActionEvent e) {
        	myShapeRect = e.getActionCommand().toString();
        	myShapeOval = e.getActionCommand().toString();
        	myShapeLine = e.getActionCommand().toString();
        }

        public void mouseReleased(MouseEvent e) {
           //Ang: Make rectangle builds the rectangle, Make oval builds the oval 
           //TODO for TAP: Make JFrame Selector for user to choose either Rectangle or Oval
            // add check box group
            Shape o= makeOval(startDrag.x, startDrag.y, e.getX(), e.getY());
            Shape l = makeLine(startDrag.x, startDrag.y, e.getX(), e.getY());
            Shape r = makeRectangle(startDrag.x, startDrag.y, e.getX(), e.getY());

            Shape shape = null;
            if (myShapeRect.equals("rect")) {
            	shapes.add(r);
            } else
                if (myShapeOval.equals("oval")) {
                	shapes.add(o);
                } 
                else
                    if (myShapeLine.equals("line")) {
                    	shapes.add(l);
                    } 
//            if (shape != null) {
//               shapes.add(r);
//               shapes.add(o);
//               shapes.add(l);
//               startDrag = null;
//               endDrag = null;
//               repaint();
//            }
//            shapes.add(r);
            shapes.add(o);
//            shapes.add(l);
            startDrag = null;
            endDrag = null;
            repaint();

        }

      });

      this.addMouseMotionListener(new MouseMotionAdapter() {
        public void mouseDragged(MouseEvent e) {
          endDrag = new Point(e.getX(), e.getY());
          repaint();
        }
      });
    }
    private void paintBackground(Graphics2D g2){
       
      g2.setPaint(Color.LIGHT_GRAY);
      for (int i = 0; i < getSize().width; i += 10) {
        Shape line = new Line2D.Float(i, 0, i, getSize().height);
        g2.draw(line);
      }

      for (int i = 0; i < getSize().height; i += 10) {
        Shape line = new Line2D.Float(0, i, getSize().width, i);
        g2.draw(line);
      }

      
    }
    public void paint(Graphics g) {
    	
      Graphics2D g2 = (Graphics2D) g;
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      paintBackground(g2);
      Color[] colors = { Color.YELLOW, Color.MAGENTA, Color.CYAN , Color.RED, Color.BLUE, Color.PINK};
      
      int colorIndex = 0;

      g2.setStroke(new BasicStroke(2));
      g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.50f));

      for (Shape s : shapes) {
        g2.setPaint(Color.BLACK);
        g2.draw(s);
        //use just colorIndex after options pane
        g2.setPaint(colors[colorIndex]);
        g2.setPaint(colors[(colorIndex++) % 6]);
        g2.fill(s);
      }

      if (startDrag != null && endDrag != null) {
        g2.setPaint(Color.LIGHT_GRAY);
        Shape r = makeRectangle(startDrag.x, startDrag.y, endDrag.x, endDrag.y);
        g2.draw(r);
      }
      if (startDrag != null && endDrag != null) {
          g2.setPaint(Color.LIGHT_GRAY);
          Shape o = makeOval(startDrag.x, startDrag.y, endDrag.x, endDrag.y);
          g2.draw(o);
        }
      if (startDrag != null && endDrag != null) {
          g2.setPaint(Color.LIGHT_GRAY);
          Shape l = makeLine(startDrag.x, startDrag.y, endDrag.x, endDrag.y);
          g2.draw(l);
        }
    }

    private Rectangle2D.Float makeRectangle(int x1, int y1, int x2, int y2) {
      return new Rectangle2D.Float(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));
    }
    
    private Ellipse2D.Float makeOval(float x, float y, float w, float h){
        return new Ellipse2D.Float(Math.min(x, w), Math.min(y, h), Math.abs(x - w), Math.abs(y - h));
    }
    
    private Ellipse2D.Float makeLine(float xy, float yx, float wz, float hi){
        return new Ellipse2D.Float(Math.min(xy, wz), Math.min(yx, hi), Math.abs(xy - wz), Math.abs(yx - hi));
    }
  }
}