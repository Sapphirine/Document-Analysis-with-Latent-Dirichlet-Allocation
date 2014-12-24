package ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import sun.Document;
import sun.Topic;
import sun.Word;

public class Lda_ui extends JFrame{
	public Lda_ui(List<Word> words, List<Topic> topics, List<Document> documents){
		Container c=getContentPane();
		c.setLayout(null);
		
		JPanel panel_left=new JPanel();
		panel_left.setBounds(30, 20, 200, 500);
		panel_left.setBorder(BorderFactory.createBevelBorder(0));
		
		
		JPanel panel_middle=new JPanel();
		panel_middle.setBounds(260, 20, 220, 500);
		panel_middle.setBorder(BorderFactory.createBevelBorder(0));
		
		JPanel panel_right=new JPanel();
		panel_right.setBounds(510, 20, 220, 500);
		panel_right.setBorder(BorderFactory.createBevelBorder(0));
		
		panel_left.setLayout(null);
		panel_middle.setLayout(null);
		panel_right.setLayout(null);
		c.add(panel_left);
		c.add(panel_middle);
		c.add(panel_right);
		
		JLabel lbl1=new JLabel("Words");
		lbl1.setBounds(70, 5, 80, 30);
		JLabel lbl2=new JLabel("Document No.");
		lbl2.setBounds(50, 5, 120, 30);
		JLabel lbl3=new JLabel("Topics");
		lbl3.setBounds(70, 5, 80, 30);
		panel_left.add(lbl1);
		panel_middle.add(lbl2);
		panel_right.add(lbl3);
		
		DefaultListModel<String> listmodel_left=new DefaultListModel<String>();
		JList<String> list_left = new JList<String>(listmodel_left); 
		list_left.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		for(Word word : words){
			listmodel_left.addElement(word.getName());
		}
		DefaultListModel<String> listmodel_middle=new DefaultListModel<String>();
		JList<String> list_middle = new JList<String>(listmodel_middle); 
		list_middle.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		for(Document doc : documents){
			listmodel_middle.addElement(doc.getID()+"");
		}
		DefaultListModel<String> listmodel_right=new DefaultListModel<String>();
		JList<String> list_right = new JList<String>(listmodel_right); 
		list_right.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		for(Topic topic : topics){
			String[] topic_words=topic.getTopWords();
			listmodel_right.addElement((topic.getID()+1)+" {"+topic_words[0]+","+topic_words[1]+","+topic_words[2]+"}");
		}
		list_left.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent evt){
				JList list = (JList)evt.getSource();
		        if (evt.getClickCount() == 2) {
		            int index = list.locationToIndex(evt.getPoint());
		            new Word_ui(words);
		        }
			}
		});
		list_middle.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent evt){
				JList list = (JList)evt.getSource();
		        if (evt.getClickCount() == 2) {
		            int index = list.locationToIndex(evt.getPoint());
		            new Doc_ui(documents.get(index));
		        }
			}
		});
		list_right.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent evt){
				JList list = (JList)evt.getSource();
		        if (evt.getClickCount() == 2) {
		            int index = list.locationToIndex(evt.getPoint());
		            new Topic_ui(topics.get(index));
		        }
			}
		});
		
		JScrollPane listScroller_left = new JScrollPane(list_left);
		listScroller_left.setBounds(18, 35, 160, 450);
		listScroller_left.setBackground(Color.WHITE);
		panel_left.add(listScroller_left);
		JScrollPane listScroller_middle = new JScrollPane(list_middle);
		listScroller_middle.setBounds(18, 35, 175, 450);
		listScroller_middle.setBackground(Color.WHITE);
		panel_middle.add(listScroller_middle);
		JScrollPane listScroller_right = new JScrollPane(list_right);
		listScroller_right.setBounds(18, 35, 175, 450);
		listScroller_right.setBackground(Color.WHITE);
		panel_right.add(listScroller_right);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setTitle("LDA");
	    setSize(780,580);
	    setVisible(true);
	    this.setLocationRelativeTo(null);
	}
}
