// Java Program to create a to create a 
// canvas and mouse listener to the 
// canvas ( a circle of radius 5 will appear 
// at the points where mouse are clicked or 
// dragged on the canvas) 
import java.awt.*; 
import javax.swing.*; 
import java.awt.event.*; 

class canvas extends JFrame implements MouseListener, MouseMotionListener { 

	// create a canvas 
	Canvas c; 

	// constuctor 
	canvas() 
	{ 
		super("canvas"); 

		// create a empty canvas 
		c = new Canvas() { 
			public void paint(Graphics g) 
			{ 
			} 
		}; 

		// set background 
		c.setBackground(Color.black); 

		// add mouse listener 
		c.addMouseListener(this); 
		c.addMouseMotionListener(this); 

		add(c); 
		setSize(400, 300); 
		show(); 
	} 

	// mouse listener and mouse motion listener mehods 
	public void mouseClicked(MouseEvent e) 
	{ 
		Graphics g = c.getGraphics(); 

		g.setColor(Color.red); 

		// get X and y position 
		int x, y; 
		x = e.getX(); 
		y = e.getY(); 

		// draw a Oval at the point 
		// where mouse is moved 
		g.fillOval(x, y, 5, 5); 
	} 

	public void mouseMoved(MouseEvent e) 
	{ 
	} 

	public void mouseDragged(MouseEvent e) 
	{ 
		Graphics g = c.getGraphics(); 

		g.setColor(Color.red); 

		// get X and y position 
		int x, y; 
		x = e.getX(); 
		y = e.getY(); 

		// draw a Oval at the point where mouse is moved 
		g.fillOval(x, y, 5, 5); 
	} 

	public void mouseExited(MouseEvent e) 
	{ 
	} 

	public void mouseEntered(MouseEvent e) 
	{ 
	} 

	public void mouseReleased(MouseEvent e) 
	{ 
	} 

	public void mousePressed(MouseEvent e) 
	{ 
	} 
	// main class 
	public static void main(String args[]) 
	{ 
		canvas c = new canvas(); 
	} 
} 
