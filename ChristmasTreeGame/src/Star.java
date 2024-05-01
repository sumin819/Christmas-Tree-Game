import java.util.Random;

import javax.swing.JPanel;

public class Star {
	int x,y;
	int speed;
	
	int width, height;
	boolean isGet=false;
	
	public Star(int width, int height) {
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