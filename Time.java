import java.awt.Font;
import java.applet.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Date;
import java.awt.geom.*;
/*<applet code="Time.class" width=1000 height=600 ></applet>*/
public class Time extends Applet implements Runnable
{
	Date date;
	int hh, mm, ss;
	double angleS;
	double angleM;
	double angleH;
	Font myFont;
	public void init()
	{
		myFont= new Font("TimesRoman", Font.BOLD, 30);
		date=new Date();
		Thread t=new Thread(this, "Clock");
		t.start();
		ss=date.getSeconds();
		mm=date.getMinutes();
		hh=date.getHours();
		angleS=2*Math.PI*ss/60-Math.PI/2;
		angleM=2*Math.PI*mm/60-Math.PI/2;
		angleH=(hh*30+mm/2)*Math.PI/180-Math.PI/2;
		//angleH=2*Math.PI*hh/12-Math.PI/2;
	}
	
	public void paint(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		date=new Date();
		setBackground(Color.BLACK);
		g.setColor(Color.WHITE);
		
		for(double dd=0;dd<2*Math.PI;dd+=Math.PI/30)
			g.drawString(".", 600+(int)(190*Math.cos(dd)), 300+(int)(190*Math.sin(dd)));
		
		g.setFont(myFont);
		g.drawString(date.getHours()+":"+date.getMinutes()+":"+date.getSeconds(), 550, 200);
		
		for(double dd=0;dd<2*Math.PI;dd+=Math.PI/6)
			g.drawString(".", 600+(int)(190*Math.cos(dd)), 300+(int)(190*Math.sin(dd)));
		
		g.drawOval(400, 100, 400, 400);
		g.drawOval(399, 99, 402, 402);
		g.drawOval(398, 98, 404, 404);
		g.fillOval(592, 292, 16, 16);
		
		g.setColor(Color.GREEN);
		g2.setStroke(new BasicStroke(6));
		g2.draw(new Line2D.Float(600, 300, 600+(int)(100*Math.cos(angleH)), 300+(int)(100*Math.sin(angleH))));
			//g.drawLine(600, 300, 600+(int)(100*Math.cos(angleH)), 300+(int)(100*Math.sin(angleH)));
		
		g.setColor(Color.BLUE);
		g2.setStroke(new BasicStroke(4));
		g2.draw(new Line2D.Float(600, 300, 600+(int)(140*Math.cos(angleM)), 300+(int)(140*Math.sin(angleM))));
			//g.drawLine(600, 300, 600+(int)(140*Math.cos(angleM)), 300+(int)(140*Math.sin(angleM)));
		
		g.setColor(Color.RED);
		g2.setStroke(new BasicStroke(2));
		g2.draw(new Line2D.Float(600, 300, 600+(int)(180*Math.cos(angleS)), 300+(int)(180*Math.sin(angleS))));
			//g.drawLine(600, 300, 600+(int)(180*Math.cos(angleS)), 300+(int)(180*Math.sin(angleS)));
	}
	
	
	public void run()
	{
		try
		{
			while(true)
			{
				Thread.sleep(1000);
				repaint();
				angleS+=Math.PI/30;
				angleM+=Math.PI/1800;
				angleH+=Math.PI/21600;
			}
		}
		catch(Exception e){}
	}
}
