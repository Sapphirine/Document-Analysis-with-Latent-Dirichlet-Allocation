package ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;

import sun.Relevant_doc;
import sun.Relevant_word;
import sun.Topic;

public class Topic_ui extends JFrame{
	public Topic_ui(Topic t){
		Container c=getContentPane();
		c.setLayout(null);
		
		JLabel lbl=new JLabel(t.getRelativeString());
		lbl.setLocation(240, 0);
		lbl.setSize(new Dimension(300,40));
		lbl.setFont(new Font("TimesRoman", Font.BOLD, 17));
		c.add(lbl);
		
		JSeparator sep=new JSeparator();
		sep.setBounds(0, 35, 650, 20);
		c.add(sep);
		
		JLabel lbl_wd=new JLabel("Word Distributions");
		lbl_wd.setLocation(15, 40);
		lbl_wd.setSize(new Dimension(300,40));
		lbl_wd.setFont(new Font("TimesRoman", Font.BOLD, 14));
		c.add(lbl_wd);
		
		List<Relevant_word> words=t.getRelaventWords();
		List<Relevant_doc> docs=t.getRelaventDocs();
		
		JPanel panel=new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.white);
		int i;
		double total_prop=1;
		for(i=0;i<words.size()&&i<8;i++){
			double prop=words.get(i).score;
			total_prop-=prop;
			if(prop<.001) break;
			DecimalFormat decimalformat=new DecimalFormat(".0");
			String propstr=decimalformat.format(prop*100);
			
			JLabel lbl_topic=new JLabel(words.get(i).word.getName());
			lbl_topic.setBounds(10,10+i*21,55,18);
			lbl_topic.setFont(new Font("Arial", Font.ITALIC, 12));
			panel.add(lbl_topic);
			
			int width=(int)(words.get(i).score*500);
			JButton btn=new JButton();
			btn.setBounds(80, 10+i*21, width, 18);
			btn.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			btn.setBackground(Color.blue);
			btn.setOpaque(true);
			
			btn.addMouseListener(new MouseAdapter(){
				public void mouseEntered(MouseEvent evt){
					JButton button = (JButton)evt.getSource();
			        button.setBackground(Color.cyan);
				}
			});
			btn.addMouseListener(new MouseAdapter(){
				public void mouseExited(MouseEvent evt){
					JButton button = (JButton)evt.getSource();
			        button.setBackground(Color.blue);
				}
			});
			panel.add(btn);
			
			JLabel lbl_prop=new JLabel(propstr+"%");
			lbl_prop.setBounds(width+90,10+i*21,80,18);
			lbl_prop.setFont(new Font("Arial", Font.ITALIC, 12));
			panel.add(lbl_prop);
		}
		JLabel lbl_topic=new JLabel("Others");
		lbl_topic.setBounds(10,10+i*21,55,18);
		lbl_topic.setFont(new Font("Arial", Font.ITALIC, 12));
		panel.add(lbl_topic);
		
		int width=(int)(total_prop*500);
		JButton btn=new JButton();
		btn.setBounds(80, 10+i*21, width, 18);
		btn.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		btn.setBackground(Color.blue);
		btn.setOpaque(true);
		
		btn.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent evt){
				JButton button = (JButton)evt.getSource();
		        button.setBackground(Color.cyan);
			}
		});
		btn.addMouseListener(new MouseAdapter(){
			public void mouseExited(MouseEvent evt){
				JButton button = (JButton)evt.getSource();
		        button.setBackground(Color.blue);
			}
		});
		panel.add(btn);
		
		DecimalFormat decimalformat=new DecimalFormat(".0");
		String propstr=decimalformat.format(total_prop*100);
		JLabel lbl_prop=new JLabel(propstr+"%");
		lbl_prop.setBounds(width+87,10+i*21,80,18);
		lbl_prop.setFont(new Font("Arial", Font.ITALIC, 12));
		panel.add(lbl_prop);
		
		JScrollPane panelScroller= new JScrollPane(panel);
		panelScroller.setBounds(10, 77, 602, 208);
		c.add(panelScroller);
		
		JLabel lbl_rd=new JLabel("Relevant Documents");
		lbl_rd.setBounds(10,300,180,30);
		lbl_rd.setFont(new Font("TimesRoman", Font.BOLD, 14));
		c.add(lbl_rd);
		
		for(int j=0;j<docs.size();j++){
			int x=j/7;
			int y=j%7;
			JLabel lbl_doc=new JLabel(docs.get(j).doc.getID()+"");
			lbl_doc.setBounds(15+x*190, 330+y*25, 80, 25);
			lbl_doc.setFont(new Font("SERIF", Font.BOLD, 12));
			//lbl_doc.setForeground(Color.blue);
			
			lbl_doc.addMouseListener(new MouseAdapter(){
				public void mouseEntered(MouseEvent evt){
					JLabel ldoc = (JLabel)evt.getSource();
			        ldoc.setForeground(Color.gray);
				}
			});
			lbl_doc.addMouseListener(new MouseAdapter(){
				public void mouseExited(MouseEvent evt){
					JLabel ldoc = (JLabel)evt.getSource();
			        ldoc.setForeground(Color.black);
				}
			});
			final int f=j;
			lbl_doc.addMouseListener(new MouseAdapter(){
				public void mousePressed(MouseEvent evt){
			        new Doc_ui(docs.get(f).doc);
				}
			});
			c.add(lbl_doc);
		}
		
	    setTitle("Topics");
	    setSize(622,560);
	    setVisible(true);
	    this.setLocationRelativeTo(null);
	}
}
