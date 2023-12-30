package logioBL.manageUser;

import java.util.Optional;

import logioBL.user.User;
import logioBL.userAccess.UserAccess;

class ManageUserImpl implements ManageUser{
	private User user;
	private UserAccess userAccess;
	private String bfName;
	
	ManageUserImpl(User user, UserAccess userAccess) {
		this.user = user;
		this.userAccess = userAccess;
		bfName = user.getName();
	}
	
	public String getName() {
		return user.getName();
	}
	public Optional<String> setName(String name) throws Exception{
		if(userAccess.searchUser(name) || (name.length() == 0)) {
			String msg = "その名前は使用できません。";
			return Optional.of(msg);
		}
		user.setName(name);
		return Optional.empty();
	}
	
	public String getComments() {
		return user.getComments();
	}
	public void setComments(String comments) {
		user.setComments(comments);
	}
	public void addComments(String comments) {
		user.setComments(user.getComments() + comments);
	}
	public void deleteComments() {
		user.setComments("");
	}
	
	public User getUser() {
		return user;
	}
	
	public UserAccess getUserAccess() {
		return userAccess;
	}
	
	public String getBfName() {
		return bfName;
	}
}
