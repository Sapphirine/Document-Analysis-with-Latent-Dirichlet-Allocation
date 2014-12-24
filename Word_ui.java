package ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import sun.Word;

public class Word_ui extends JFrame{
	public Word_ui(List<Word> words){
		Container c=getContentPane();
		c.setLayout(null);
		
		JLabel lbl=new JLabel("Word distribution on topics");
		lbl.setLocation(10, 0);
		lbl.setSize(new Dimension(300,40));
		lbl.setFont(new Font("TimesRoman", Font.BOLD, 17));
		c.add(lbl);
		
		
		DefaultListModel<JPanel> listmodel=new DefaultListModel<JPanel>();
		JList<JPanel> list = new JList<JPanel>(listmodel); 
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		JScrollPane listScroller = new JScrollPane(list);
		listScroller.setBounds(10, 55, 380, 250);
		listScroller.setBackground(Color.WHITE);
		c.add(listScroller);
		for(Word word : words){
			
		}
		
	    setTitle("Words");
	    setSize(400,340);
	    setVisible(true);
	    this.setLocationRelativeTo(null);
	}
}
