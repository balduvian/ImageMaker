import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.MouseInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Window extends JFrame{
	
	Canvas canvas;
	JButton j;
	JTextField inp;
	int[][] block = new int[32][32];
	int size = 16;
	int ay,ax;
	Color[] colors = {Color.WHITE,Color.BLACK};
	//JLabel t;
	boolean c;
	
	public class Canvas extends JPanel{
		public void paintComponent(Graphics g){
			//t.setText(Boolean.toString(c));
			super.paintComponent(g);
			setBackground(Color.GRAY);
			for(int y=0;y<block.length;y++){
				for(int x=0;x<block.length;x++){
					g.setColor(colors[block[y][x]]);
					g.fillRect(x*size+(getWidth()/2)-(block.length*(size/2)), y*size+(getHeight()/2)-(block.length*(size/2)), size, size);
				}
			}
		}
	}
	
	public Window(){//hhh
		setTitle("Image Maker");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(640, 640);
		canvas = new Canvas();
		canvas.addMouseListener(new MListen());
		canvas.addMouseMotionListener(new MListen2());
		j = new JButton("Create");
		inp = new JTextField();
		//t = new JLabel(Boolean.toString(c));
		j.addActionListener(new CListen());
		JPanel bc = new JPanel();
		bc.setLayout(new GridLayout());
		bc.add(j);
		bc.add(inp);
		//bc.add(t);
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
	    cp.add(canvas,BorderLayout.CENTER);
	    cp.add(bc, BorderLayout.SOUTH);
	    //setResizable(false);
		setVisible(true);
	}
	
	public class CListen implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			BufferedImage img = Imager.makeImage(block[0].length,block.length);
			Imager.arrayToRect(img,block);
			Imager.SaveImage(img,inp.getText()+".png");
		}
	}
	
	public void drawin(MouseEvent e){
		if(c){
			try{
				int y= ((block.length*(size/2)/size)-(getHeight()/2/size)+(e.getY()/size));
				int x= ((block[0].length*(size/2)/size)-(getWidth()/2/size)+(e.getX()/size));
				if(ay!=y || ax!=x){
					ay=y;
					ax=x;
					block[y][x]++;
					block[y][x] %= colors.length;
				}
			}catch(Exception ex){
			}
		}
	}
	
	public class MListen2 implements MouseMotionListener{

		public void mouseDragged(MouseEvent e) {
			drawin(e);
		}
		public void mouseMoved(MouseEvent e) {
		}
	}
	
	public class MListen implements MouseListener{
		public void mouseClicked(MouseEvent e) {
		}
		public void mouseEntered(MouseEvent e) {
		}
		public void mouseExited(MouseEvent e) {
		}
		public void mousePressed(MouseEvent e) {
			c=true;
			drawin(e);
		}
		public void mouseReleased(MouseEvent e) {
			c=false;
		}

	}
	
}
