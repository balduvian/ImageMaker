import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Imager {
	
	public Window window;
	
	public int[][] grid = {
			{0,0,7,7,0,0,0,0,0,0},
			{0,0,0,0,0,0,2,0,0,0},
			{1,0,0,0,1,0,7,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,1,0,1,0,0},
			{0,3,0,0,0,0,1,0,0,0},
			{0,0,0,1,0,0,0,0,0,0},
			{0,0,0,0,1,0,0,2,0,0},
			{0,1,2,3,4,5,6,7,8,0},
			{0,0,0,5,0,0,0,0,0,0}
	};
	
	public Imager() throws IOException{
		window = new Window();
		while(true){
			window.canvas.repaint();
		}
	}
	
	public static void rect(BufferedImage b, Color c, int x, int y, int w, int h){
		Graphics2D g = b.createGraphics();
		g.setColor(c);
		g.fillRect(x, y, w, h);
	}
	//h
	public static void arrayToRect(BufferedImage b, int[][] a){
		Color[] colors = {Color.WHITE,Color.GRAY,Color.RED,Color.ORANGE,Color.YELLOW,Color.GREEN,Color.BLUE,Color.MAGENTA};
		for(int y=0;y<a.length;y++){
			for(int x=0;x<a[y].length;x++){
				try{
				rect(b,colors[a[y][x]],x,y,1,1);
				}catch(Exception e){}
			}
		}
	}
	
	public static BufferedImage makeImage(int x,int y){
		BufferedImage img = new BufferedImage(x,y, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = img.createGraphics();
		g.setColor(new Color(0,0,0,0));
		g.fillRect(0, 0, x, y);
		return img;
	}
	
	public static void SaveImage(BufferedImage b, String name){
		File f = new File(name);
		try {
			ImageIO.write(b, "PNG", f);
		}catch (IOException e){}
	}
	
	public static void main(String[] args) throws IOException {
		new Imager();
	}

}
