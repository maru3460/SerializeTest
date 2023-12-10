package bl;

import java.io.Serializable;

public class User implements Serializable{
	private String name;
	private String comments;
	
	public User(String name, String comments){
		this.name = name;
		this.comments = comments;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
}
