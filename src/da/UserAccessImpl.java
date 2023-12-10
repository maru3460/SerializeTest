package da;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import bl.User;
import bl.UserAccess;

class UserAccessImpl implements UserAccess{
	private Path dataDirPath = Paths.get("src", "userdata");
	private Path allUsersFilePath = dataDirPath.resolve(Paths.get("users.txt"));
	
	public void initDir() throws Exception{
		if (Files.exists(dataDirPath) == false) {
			Files.createDirectories(dataDirPath);
			Files.createFile(allUsersFilePath);
		}else if(Files.exists(allUsersFilePath) == false) {
			Files.createFile(allUsersFilePath);
		}
	}
	
	public boolean searchUser(String name) throws Exception{
		BufferedReader fileRead = new BufferedReader(new FileReader(allUsersFilePath.toString()));
		try(fileRead){
			String tmpUser;
			while((tmpUser = fileRead.readLine()) != null) {
				if(name.equals(tmpUser)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public User readUser(String name)throws Exception{
		Path userFilePath = dataDirPath.resolve(Paths.get(name + ".ser"));
		ObjectInputStream fileRead = new ObjectInputStream(new FileInputStream(userFilePath.toString()));
		
		User user;
		try(fileRead){
			user = (User) fileRead.readObject();
		}
		return user;
	}
	
	public void writeUser(User user)throws Exception{
		Path userFilePath = dataDirPath.resolve(Paths.get(user.getName() + ".ser"));
		ObjectOutputStream fileWrite = new ObjectOutputStream(new FileOutputStream(userFilePath.toString()));
		try(fileWrite){
			fileWrite.writeObject(user);
		}
	}
	
	public void addUser(String name)throws Exception{
		BufferedWriter fileWrite = new BufferedWriter(new FileWriter(allUsersFilePath.toString(), true));
		try(fileWrite){
			fileWrite.write(name + "\r\n");
			fileWrite.flush();
		}
	}
	
	public boolean deleteUser(String deleteName)throws Exception{
		Path userFilePath = dataDirPath.resolve(Paths.get(deleteName + ".ser"));
		if(Files.exists(userFilePath) == true) {
			Files.delete(userFilePath);
			editUserFileDelete(deleteName);
			return true;
		}
		
		return false;
	}
	
	private void editUserFileDelete(String deleteName)throws Exception{
		BufferedReader fileRead = new BufferedReader(new FileReader(allUsersFilePath.toString()));
		StringBuilder sb = new StringBuilder();
		try(fileRead){
			String tmpUser;
			while((tmpUser = fileRead.readLine()) != null) {
				if (!tmpUser.equals(deleteName)){
					sb.append(tmpUser + "\r\n");
				}
			}
		}
		
		BufferedWriter fileWrite = new BufferedWriter(new FileWriter(allUsersFilePath.toString(), false));
		try(fileWrite){
			fileWrite.write(sb.toString());
			fileWrite.flush();
		}
	}
}
