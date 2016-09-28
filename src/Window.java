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
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Window extends JFrame{
	
	Canvas canvas;
	JButton j;
	JTextField inp;
	int[][] block = new int[16][16];
	
	public class Canvas extends JPanel{
		public void paintComponent(Graphics g){
			Color[] colors = {Color.WHITE,Color.GRAY,Color.RED,Color.ORANGE,Color.YELLOW,Color.GREEN,Color.BLUE,Color.MAGENTA};
			super.paintComponent(g);
			setBackground(Color.GRAY);
			for(int y=0;y<block.length;y++){
				for(int x=0;x<block.length;x++){
					g.setColor(colors[block[y][x]]);
					g.fill3DRect(x*32+(getWidth()/2)-(block.length*16), y*32+(getHeight()/2)-(block.length*16), 32, 32, true);
				}
			}
		}
	}
	
	public Window(){//h
		setTitle("Image Maker");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(640, 640);
		canvas = new Canvas();
		canvas.addMouseListener(new MListen());
		j = new JButton("Create");
		inp = new JTextField();
		j.addActionListener(new CListen());
		JPanel bc = new JPanel();
		bc.setLayout(new GridLayout());
		bc.add(j);
		bc.add(inp);
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
	
	public class MListen implements MouseListener{
	    
		public void mouseClicked(MouseEvent e) {
			try{
				block[(block.length*16)/32-(getHeight()/2)/32+e.getY()/32+1][(block.length*16)/32-(getWidth()/2)/32+e.getX()/32]++;
			}catch(Exception ex){
			}
		}
		public void mouseEntered(MouseEvent arg0) {
		}
		public void mouseExited(MouseEvent arg0) {
		}
		public void mousePressed(MouseEvent arg0) {
		}
		public void mouseReleased(MouseEvent arg0) {
		}

	}
	
}
