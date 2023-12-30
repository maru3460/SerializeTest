package logioDA.adminAccess;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import logioBL.adminAccess.AdminAccess;

class AdminAccessImpl implements AdminAccess{
	private final Path dataDirPath = Paths.get("src", "userdata");
	private final Path adminFilePath = dataDirPath.resolve(Paths.get("admin.txt"));
	private final Path allUsersFilePath = dataDirPath.resolve(Paths.get("users.txt"));
	
	AdminAccessImpl(){
		super();
	}
	
	public boolean isPrepared() throws Exception{
		File adminFile = adminFilePath.toFile();
		return adminFile.exists();
	}
	
	public boolean checkAdmin(String name, String password) throws Exception{
		BufferedReader fileRead = new BufferedReader(new FileReader(adminFilePath.toString()));
		try(fileRead){
			String tmp;
			tmp = fileRead.readLine();
			if(!tmp.equals(name)) {
				return false;
			}
			tmp = fileRead.readLine();
			if(!tmp.equals(password)) {
				return false;
			}
		}
		return true;
	}
	
	public void createAdmin(String name, String password) throws Exception{
		BufferedWriter fileWrite = new BufferedWriter(new FileWriter(adminFilePath.toString(), false));
		try(fileWrite){
			fileWrite.write(name + "\r\n" + password + "\r\n");
			fileWrite.flush();
		}
	}
	
	public List<String> getAllUserName() throws Exception{
		List<String> userNames = new ArrayList<>();
		BufferedReader fileRead = new BufferedReader(new FileReader(allUsersFilePath.toString()));
		try(fileRead){
			String tmp;
			while((tmp = fileRead.readLine()) != null) {
				if(!tmp.equals("")) {
					userNames.add(tmp);
				}
			}
		}
		return userNames;
	}
}
