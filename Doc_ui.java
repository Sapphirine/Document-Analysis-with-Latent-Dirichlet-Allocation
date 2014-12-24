package ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import sun.Document;
import sun.Relevant_topic;

public class Doc_ui extends JFrame{
	public Doc_ui(Document d){
		Container c=getContentPane();
		c.setLayout(null);
		
		JLabel lbl=new JLabel("<html>Topic Proportions for document <font color='blue'>"+d.getID()+"</font></html>");
		lbl.setLocation(30, 10);
		lbl.setSize(new Dimension(300,40));
		lbl.setFont(new Font("TimesRoman", Font.BOLD, 18));
		c.add(lbl);
		
		List<Relevant_topic> topics=d.getRelativeTopic();
		
		int i;
		for(i=0;i<topics.size()&&i<6;i++){
			double prop=topics.get(i).score;
			if(prop<.01) break;
			DecimalFormat decimalformat=new DecimalFormat(".0");
			String propstr=decimalformat.format(prop*100);
			JButton btn=new JButton();
			
			JLabel lbl_topic=new JLabel("Topic "+(i+1));
			lbl_topic.setBounds(20,50+i*30,40,25);
			lbl_topic.setFont(new Font("Arial", Font.ITALIC, 12));
			c.add(lbl_topic);
			
			int width=(int)(topics.get(i).score*310);
			btn.setBounds(70, 50+i*30, width, 25);
			btn.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			btn.setBackground(Color.orange);
			btn.setOpaque(true);
			
			btn.addMouseListener(new MouseAdapter(){
				public void mouseEntered(MouseEvent evt){
					JButton button = (JButton)evt.getSource();
			        button.setBackground(Color.yellow);
				}
			});
			btn.addMouseListener(new MouseAdapter(){
				public void mouseExited(MouseEvent evt){
					JButton button = (JButton)evt.getSource();
			        button.setBackground(Color.orange);
				}
			});
			final int f=i;
			btn.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e){
					new Topic_ui(topics.get(f).t);
				}
			});
			c.add(btn);
			
			JLabel lbl_prop=new JLabel(propstr+"%");
			lbl_prop.setBounds(width+80,50+i*30,80,25);
			lbl_prop.setFont(new Font("Arial", Font.ITALIC, 12));
			c.add(lbl_prop);
		}
		
		JPanel legend=new JPanel();
		//legend.setBackground(Color.white);
		legend.setBounds(20, 240, 380, 100);
		legend.setLayout(null);
		c.add(legend);
		
		for(int j=0;j<i;j++){
			int x=j/3;
			int y=j%3;
			String str_legend="Topic "+(j+1)+"="+topics.get(j).t.getRelativeString();
			JLabel lbl_legend=new JLabel(str_legend);
			lbl_legend.setBounds(5+x*190, 10+y*25, 180, 20);
			lbl_legend.setFont(new Font("Arial", Font.PLAIN, 10));
			legend.add(lbl_legend);
		}
		
	    setTitle("Documents");
	    setSize(450,360);
	    setVisible(true);
	    this.setLocationRelativeTo(null);
	}
}
