
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import javax.sound.sampled.*;
import javax.swing.*;

public class Main extends JFrame {
	GamePanel panel;
	GameThread thread;
	Audio audio;
	
	public Main() {
		this.setTitle("크리스마스 트리 만들기");
		this.setBounds(100, 100, 500, 500);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		buildGUI();	
		
		this.setVisible(true);
	}
	
	public void buildGUI() {
		panel = new GamePanel();
		this.add(panel);
		
		thread=new GameThread();
		thread.start();

		audio=new Audio();
		audio.start();
	
		this.addKeyListener(handler);
	}
	
	private KeyListener handler = new KeyAdapter() {

		@Override
		public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_LEFT)
					panel.TreeX-=5;
				else if(e.getKeyCode()==KeyEvent.VK_RIGHT)
					panel.TreeX+=5;
		}
		
	};
	
	class GamePanel extends JPanel {
		int TreeX, TreeY;
		int w=40,h=50;
		int width, height;
		int red, green, blue;
		ImageIcon iconB, iconT, iconS, iconBomb, iconStar;
		Image imgB, imgT, imgS, imgBomb, imgStar;
		Font f;
		
		ArrayList<Ornament> ornaments = new ArrayList<Ornament>();
		ArrayList<Bomb> bombs = new ArrayList<Bomb>();
		ArrayList<Star> stars = new ArrayList<Star>();
		int ornamentCnt=7;
		boolean isStar = false;
		
		public GamePanel() {
			buildGraphics();
		}
		
		public void buildGraphics() {
			iconB = new ImageIcon("image/background.png");
			iconT = new ImageIcon("image/first.png");
			iconS = new ImageIcon("image/end.png");
			iconBomb = new ImageIcon("image/bomb.png");
			iconStar = new ImageIcon("image/star.png");
			imgB = iconB.getImage();
			imgT = iconT.getImage();
			imgS = iconS.getImage();
			imgBomb = iconBomb.getImage();
			imgStar = iconStar.getImage();
			
			f=new Font("맑은고딕체", Font.PLAIN, 20);
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			if(width==0 || height==0) {
				width=getWidth();
				height=getHeight();			
				TreeX=200;
				TreeY=350;
			}
			g.drawImage(imgB, 0, 0, this.getWidth(), this.getHeight(), this);
			randomColor();
			g.setColor(new Color(red,green,blue));
			for(int i=0;i<ornaments.size(); i++) {				
				Ornament o = ornaments.get(i);		
				g.fillOval(o.x, o.y, 25,25);
			}
			for(int i=0;i<bombs.size();i++) {
				Bomb b = bombs.get(i);
				g.drawImage(imgBomb, b.x, b.y, 25, 25, this);
			}
			for(int i=0;i<stars.size();i++) {
				Star s = stars.get(i);
				g.drawImage(imgStar, s.x, s.y, 25, 25, this);
			}
			g.drawImage(imgT, TreeX, TreeY, 80, 100, this);
			
			g.setColor(Color.WHITE);
			g.drawString("필요한 오너먼트 수: " + ornamentCnt, 10, 30);
			g.drawString("별 획득: " + isStar, 10, 50);
			
			if(ornamentCnt==0 && isStar==true) {				
				g.drawImage(imgS,150, 70, 200, 200, this);
				g.setFont(f);
				g.drawString("크리스마스 트리가 완성됐습니다!", 100, 55);
				for(int i=0;i<ornaments.size();i++) ornaments.remove(i);
				for(int i=0;i<bombs.size();i++) bombs.remove(i);
				for(int i=0;i<stars.size();i++) stars.remove(i);
				thread=null;
			}
		}
		
		public void randomColor() {
			this.red = (int)(Math.random()*256);
			this.green = (int)(Math.random()*256);
			this.blue = (int)(Math.random() * 256);
		}
		
		public void falling() {
			for(int i=0;i<ornaments.size();i++) {
				Ornament o = ornaments.get(i);
				o.falling();
				if(o.isGet) ornaments.remove(i);
			}
			for(int i=0;i<bombs.size();i++) {
				Bomb b = bombs.get(i);
				b.falling();
				if(b.isGet) bombs.remove(i);
			}
			for(int i=0;i<stars.size();i++) {
				Star s = stars.get(i);
				s.falling();
				if(s.isGet) stars.remove(i);
			}
			if(TreeX<0) TreeX=0;
			if(TreeX+80>width) TreeX=width-80;
		}
		
		public void makeOrnament() {
			if(width==0||height==0) return;
			int n = (int)(Math.random()*17);
			if(n==0) {
				ornaments.add(new Ornament(width,height));
			}
		}
		public void makeBomb() {
			if(width==0||height==0) return;
			int n = (int)(Math.random()*45);
			if(n==0) {
				bombs.add(new Bomb(width, height));
			}
		}
		public void makeStar() {
			if(width==0||height==0) return;
			int n = (int)(Math.random()*55);
			if(n==0) {
				stars.add(new Star(width, height));
			}
		}
		
		public void getOrnament() {
			for(int i=0;i<ornaments.size(); i++) {				
				Ornament o = ornaments.get(i);		
				
				if(TreeX<=o.x && o.x+25<=TreeX+80 && o.y>TreeY) {
					o.isGet=true;
					if(ornamentCnt>0) ornamentCnt--;
				}
			}
		}
		
		public void getBomb() {
			for(int i=0;i<bombs.size();i++) {
				Bomb b = bombs.get(i);
				
				if(TreeX<=b.x && b.x+25<=TreeX+80 && b.y>TreeY) {
					b.isGet=true;
					ornamentCnt++;
				}
			}
		}
		
		public void getStar() {
			for(int i=0;i<stars.size();i++) {
				Star s = stars.get(i);
				
				if(TreeX<=s.x && s.x+25<=TreeX+80 && s.y>TreeY) {
					s.isGet=true;
					isStar=true;
				}
			}
		}
		
		
		public void gameSetter() {
			makeOrnament();
			makeBomb();
			makeStar();
			falling();
			getOrnament();
			getBomb();
			getStar();
			repaint();
		}
	}
	
	class GameThread extends Thread {

		@Override
		public void run() {
			while(thread == Thread.currentThread()) {
				try {
					Thread.sleep(50);
				} catch(InterruptedException e1) { }
				panel.gameSetter();
			}
		}
		
	}
}

