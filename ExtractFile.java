package sun;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ui.Lda_ui;

public class ExtractFile {
	static List<Document> docs=new ArrayList<Document>();
	static List<Topic> topics=new ArrayList<Topic>();
	static List<Word> words=new ArrayList<Word>();
	public void extract(String filename){
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(new File(filename)));
			String s;
			int k=0;
			while((s=br.readLine())!=null){
				String[] doc_list=s.trim().split("\\s");
				Document doc=new Document(Integer.parseInt(doc_list[0]));
				docs.add(doc);
				String[] list=doc_list[1].substring(1, doc_list[1].length()-1).split(",");
				for(String topic_scores : list){
					String[] topic_score=topic_scores.split(":");
					Topic topic=topics.get(Integer.parseInt(topic_score[0]));
					doc.addRelativeTopic(topic, Double.parseDouble(topic_score[1]));
					topic.addRelevantDoc(doc, Double.parseDouble(topic_score[1]));
				}
				k++;
			}
			System.out.println(docs);
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void extract2(String filename){
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(new File(filename)));
			String s;
			int k=0;
			while((s=br.readLine())!=null){
				String[] topic_list=s.trim().split("\\s");
				Topic topic=new Topic(Integer.parseInt(topic_list[0]));
				topics.add(topic);
				String[] list=topic_list[1].substring(1, topic_list[1].length()-1).split(",");
				for(String word_scores : list){
					String[] word_score=word_scores.split(":");
					Word word=new Word(word_score[0]);
					words.add(word);
					topic.addRelativeWord(word, Double.parseDouble(word_score[1]));
					word.addTopic(topic, Double.parseDouble(word_score[1]));
				}
				k++;
			}
			System.out.println(topics);
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args){
		ExtractFile ef=new ExtractFile();
		ef.extract2("Input/vectordump2");
		ef.extract("Input/vectordump");
		Lda_ui ui=new Lda_ui(words, topics, docs);
	}
}
