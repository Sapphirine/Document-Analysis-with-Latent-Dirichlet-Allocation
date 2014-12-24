package sun;

import java.util.ArrayList;
import java.util.List;

public class Document {
	int id;
	List<Relevant_topic> topics=new ArrayList<Relevant_topic>();
	public Document(int id){
		this.id=id;
	}
	public void addRelativeTopic(int tid, double score){
		topics.add(new Relevant_topic(new Topic(tid), score));
	}
	public void addRelativeTopic(Topic t, double score){
		topics.add(new Relevant_topic(t, score));
	}
	public int getID(){
		return id;
	}
	public List<Relevant_topic> getRelativeTopic(){
		return topics;
	}
}
