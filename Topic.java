package sun;

import java.util.ArrayList;
import java.util.List;

public class Topic {
	int id;
	List<Relevant_word> words=new ArrayList<Relevant_word>();
	List<Relevant_doc> docs=new ArrayList<Relevant_doc>();
	Topic(int id){
		this.id=id;
	}
	public void addRelativeWord(Word w, double score){
		words.add(new Relevant_word(w, score));
	}
	public void addRelevantDoc(Document d, double score){
		for(int i=0;i<docs.size()&&i<20;i++){
			if(docs.get(i).score<=score&&docs.get(i).doc.getID()!=d.getID()){
				docs.add(i, new Relevant_doc(d, score));
				if(docs.size()>20){
					docs.remove(docs.size()-1);
				}
				return;
			}
		}
		if(docs.size()<20){
			docs.add(new Relevant_doc(d, score));
		}
	}
	public int getID(){
		return id;
	}
	public List<Relevant_word> getRelaventWords(){
		return words;
	}
	public List<Relevant_doc> getRelaventDocs(){
		return docs;
	}
	public String[] getTopWords(){
		if(words.size()==0) return null;
		String[] s={words.get(0).word.getName(),words.get(1).word.getName(),words.get(2).word.getName()};
		return s;
	}
	public List<Relevant_word> getRelativeWord(){
		return words;
	}
	public String getRelativeString(){
		return "{"+words.get(0).word.getName()+", "+words.get(1).word.getName()+", "+words.get(2).word.getName()+"}";
	}
}
