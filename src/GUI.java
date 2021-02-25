import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.*;

public class GUI 
{
	static Base base = new Base(null, null);
	static JTextArea ta1;
	static JTextArea ta2;
	
	public GUI(Base base) 
	{
		this.base = base;
	}
	
	private static void compareTextAreas() 
	{
	    try
	    {
	    	base.setText1(ta1.getText());
		    base.setText2(ta2.getText());
		    
		    if(base.compareStrings(base.text1, base.text2))
		    	JOptionPane.showMessageDialog(null, "The text strings are identical.", "Compare Result", JOptionPane.INFORMATION_MESSAGE);
		    else
		    	JOptionPane.showMessageDialog(null, "The text strings are different.", "Compare Result", JOptionPane.INFORMATION_MESSAGE);
	    }
	    catch(Throwable exception)
	    {
	    	exception.printStackTrace();
	    }
	}
	
	private static void clearTextAreas()
	{
		try
		{
			ta1.setText(null);
			ta2.setText(null);
			
			JOptionPane.showMessageDialog(null, "The text boxes have been cleared...", "Text Cleared", JOptionPane.INFORMATION_MESSAGE);
		}
		catch (Throwable exception) 
		{
			exception.printStackTrace();
		}
	}
	
	private static void userInterface() 
	{
		JPanel panel1 = new JPanel();
	    panel1.setBorder(new TitledBorder(new EtchedBorder(), "First String"));
	    ta1 = new JTextArea(5, 20);
	    ta1.setLineWrap(true);
	    ta1.setWrapStyleWord(true);
	    JScrollPane scroll1 = new JScrollPane(ta1);
	    scroll1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	    panel1.add(scroll1);
	    
	    
	    JPanel panel2 = new JPanel();
	    panel2.setBorder(new TitledBorder(new EtchedBorder(), "Second String"));
	    ta2 = new JTextArea(5, 20);
	    ta2.setLineWrap(true);
	    ta2.setWrapStyleWord(true);
	    JScrollPane scroll2 = new JScrollPane(ta2);
	    scroll2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	    panel2.add(scroll2);
	    
	    
	    JPanel panel3 = new JPanel();
	    
	    JButton compareBtn = new JButton("COMPARE");
	    compareBtn.addActionListener(new ActionListener() 
	    {
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if(e.getSource() == compareBtn)
					compareTextAreas();
			}
		});
	    
	    JButton clearBtn = new JButton("CLEAR");
	    clearBtn.addActionListener(new ActionListener() 
	    {
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if(e.getSource() == clearBtn)
					clearTextAreas();
			}
    	});
	    
	    JButton quitBtn = new JButton("QUIT");
	    quitBtn.addActionListener(new ActionListener() 
    	{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if(e.getSource() == quitBtn)
					base.quit();
			}
    	});
	    
	    panel3.add(compareBtn);
	    panel3.add(clearBtn);
	    panel3.add(quitBtn);
	    
	    
	    JFrame frame = new JFrame("Debug Application");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setSize(400,300);
	    
	    frame.getContentPane().add(BorderLayout.PAGE_START, panel1);
	    frame.getContentPane().add(BorderLayout.CENTER, panel2);
	    frame.getContentPane().add(BorderLayout.PAGE_END, panel3);
	    frame.setVisible(true);
	}
	
	public static void main(String args[])
	{
		userInterface();
	}
}
