import java.awt.Font;
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.awt.geom.*;
/*<applet code="Fan.class" width=700 height=500 ></applet>*/
public class Fan extends Applet implements Runnable, KeyListener 
{
	Font myFont;
	double angle=Math.PI/2;
	int time=100;
	public void init()
	{
		myFont= new Font("TimesRoman", Font.BOLD, 30);
		Thread t=new Thread(this, "Fan");
		t.start();
		addKeyListener(this);
	}
	
	public void paint(Graphics g)
	{
		g.setFont(myFont);
		Graphics2D g1=(Graphics2D)g;
		setBackground(Color.GRAY);
		g.setColor(Color.decode("#4CAF50"));
		g.fillOval(305, 255, 30, 30);
		g1.setStroke(new BasicStroke(12));
		g.setColor(Color.decode("#7D1935"));
		g1.draw(new Line2D.Float(320, 270, 320+(int)(100*Math.cos(angle)), 270+(int)(100*Math.sin(angle))));
		g.setColor(Color.decode("#FC4A1A"));
		g1.draw(new Line2D.Float(320, 270, 320+(int)(100*Math.cos(angle+Math.PI/2)), 270+(int)(100*Math.sin(angle+Math.PI/2))));
		g.setColor(Color.decode("#FFF056"));
		g1.draw(new Line2D.Float(320, 270, 320+(int)(100*Math.cos(angle+Math.PI)), 270+(int)(100*Math.sin(angle+Math.PI))));
		g.setColor(Color.decode("#3B5998"));
		g1.draw(new Line2D.Float(320, 270, 320+(int)(100*Math.cos(angle+3*Math.PI/2)), 270+(int)(100*Math.sin(angle+3*Math.PI/2))));
		g.setColor(Color.BLACK);
		g.drawString(time+"", 50, 50);
	}
	
	public void run()
	{
		try
		{
			while(true)
			{
				angle+=Math.PI/30;
				Thread.sleep(time);
				repaint();
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	public void keyPressed(KeyEvent ke){}
	public void keyReleased(KeyEvent ke){}
	
	public void keyTyped(KeyEvent ke)
	{
		char ch=ke.getKeyChar();
		if(ch=='+' || ch=='z')
			time+=5;
		else if(ch=='-' || ch=='x')
		{
			if(time>5)
				time-=5;
		}
	}
	
}
