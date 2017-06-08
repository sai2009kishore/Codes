import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Scanner;
/*<applet code="PrimeGrid.class" height=310 width=400>
</applet>*/
public class PrimeGrid extends JApplet implements ActionListener
{
	JTextField tf=new JTextField(6);
	public void init()
	{
		System.out.print("Limit: ");
		int n=(new Scanner(System.in)).nextInt();
		tf=new JTextField(10);
		Container c=getContentPane();
		c.setLayout(new FlowLayout());
		JPanel jp=new JPanel();
		jp.setLayout(new GridLayout(10,20));
		for(int i=1;i<=n;i++)
		{
			Button b=new Button(i+"");
			int flag=0;
			jp.add(b);
			for(int j=2;j<=i/2;j++)
				if(i%j==0)
					flag=1;
			if(flag==0)
				b.setBackground(Color.RED);
			else
				b.setBackground(Color.YELLOW);
			b.addActionListener(this);
		}
		int v=ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
		int h=ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
		JScrollPane jsp=new JScrollPane(jp,v,h);
		c.add(jsp);
		c.add(tf);
	}
	public void actionPerformed(ActionEvent e)
	{
		int flag=0;
		int x=Integer.parseInt(e.getActionCommand());
		for(int i=2;i<=x/2;i++)
			if(x%i==0)
				flag=1;
		if(flag==1)
		{
			tf.setText("COMPOSITE");
			tf.setBackground(Color.YELLOW);	
		}
		else
		{
			tf.setText("PRIME");
			tf.setBackground(Color.RED);			
		}
	}
}
