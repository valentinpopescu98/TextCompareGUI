import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.Highlighter.HighlightPainter;

public class GUI 
{
	static Base base = new Base(null, null);
	static JTextArea ta1;
	static JTextArea ta2;
	
	public GUI(Base base) 
	{
		this.base = base;
	}
	
	
	private static void highlightTextAreas(JTextArea ta1, JTextArea ta2) throws BadLocationException
	{
		Highlighter highlighter1 = ta1.getHighlighter();
		Highlighter highlighter2 = ta2.getHighlighter();
	    HighlightPainter green = new DefaultHighlighter.DefaultHighlightPainter(Color.green);
	    HighlightPainter red = new DefaultHighlighter.DefaultHighlightPainter(Color.red);
	    
    	String text1 = ta1.getText();
	    String text2 = ta2.getText();
		
		if(text1.length() > text2.length())
		{
			for(int i = 0 ; i < text2.length(); i++)
			{
				if(text1.charAt(i) == text2.charAt(i))
				{
					highlighter1.addHighlight(i, i + 1, green);
					highlighter2.addHighlight(i, i + 1, green);
				}
				else
				{
					highlighter1.addHighlight(i, i + 1, red);
					highlighter2.addHighlight(i, i + 1, red);
				}
			}
			
			for(int i = text2.length(); i < text1.length(); i++)
				highlighter1.addHighlight(i, i + 1, red);
		}
		
		else if(text2.length() > text1.length())
		{
			for(int i = 0 ; i < text1.length(); i++)
			{
				if(text2.charAt(i) == text1.charAt(i))
				{
					highlighter1.addHighlight(i, i + 1, green);
					highlighter2.addHighlight(i, i + 1, green);
				}
				else
				{
					highlighter1.addHighlight(i, i + 1, red);
					highlighter2.addHighlight(i, i + 1, red);
				}
			}
			
			for(int i = text1.length(); i < text2.length(); i++)
				highlighter2.addHighlight(i, i + 1, red);
		}
		
		else
		{
			for(int i = 0 ; i < text1.length(); i++)
			{
				if(text1.charAt(i) == text2.charAt(i))
				{
					highlighter1.addHighlight(i, i + 1, green);
					highlighter2.addHighlight(i, i + 1, green);
				}
				else
				{
					highlighter1.addHighlight(i, i + 1, red);
					highlighter2.addHighlight(i, i + 1, red);
				}
			}
		}
	}
	
	private static void compareTextAreas() 
	{
	    try
	    {
	    	base.setText1(ta1.getText());
		    base.setText2(ta2.getText());
		    
		    ta1.getHighlighter().removeAllHighlights();
		    ta2.getHighlighter().removeAllHighlights();
		    
		    if(ta1.getText().length() != 0 && ta2.getText().length() != 0)
		    {
		    	if(base.compareStrings(base.text1, base.text2))
			    	JOptionPane.showMessageDialog(null, "The text strings are identical.", "Compare result", JOptionPane.INFORMATION_MESSAGE);
			    else
			    {
			    	JOptionPane.showMessageDialog(null, "The text strings are different.", "Compare result", JOptionPane.INFORMATION_MESSAGE);
			    	highlightTextAreas(ta1, ta2);
			    }
		    }
		    else JOptionPane.showMessageDialog(null, "Please add something in both text boxes.", "Warning! Add text.", JOptionPane.WARNING_MESSAGE);
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
			
			ta1.getHighlighter().removeAllHighlights();
		    ta2.getHighlighter().removeAllHighlights();
			
			JOptionPane.showMessageDialog(null, "The text boxes have been cleared...", "Text cleared", JOptionPane.INFORMATION_MESSAGE);
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
