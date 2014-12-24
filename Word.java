package sun;

import java.util.ArrayList;
import java.util.List;

public class Word {
	String name;
	List<Relevant_topic> topics=new ArrayList<Relevant_topic>();
	public Word(String n){
		name=n;
	}
	
	public void addTopic(Topic t, double score){
		for(int i=0;i<topics.size()&&i<20;i++){
			if(topics.get(i).score<=score&&topics.get(i).t.getID()!=t.getID()){
				topics.add(i, new Relevant_topic(t, score));
				if(topics.size()>20){
					topics.remove(topics.size()-1);
				}
				return;
			}
		}
		if(topics.size()<20){
			topics.add(new Relevant_topic(t, score));
		}
	}
	
	public String getName(){
		return name;
	}
}
