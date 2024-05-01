import java.util.Random;

import javax.swing.JPanel;

public class Bomb {
	int x,y;
	int speed;
	
	int width, height;
	boolean isGet=false;
	
	public Bomb(int width, int height) {
		this.width=width;
		this.height=height;

		x = (int) (Math.random() * 500);
		y=0;
		speed+=(int)(Math.random()*15+1);
	}
	
	public void falling() {
		y+=speed;
		if(y>height) {
			isGet=true;
		}
	}
}