package logioDA.userAccess;

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

import logioBL.user.User;
import logioBL.userAccess.UserAccess;

/**
 * 一般ユーザーのデータアクセス用クラスです。
 */
class UserAccessImpl implements UserAccess{
	
	/** データが入ってるディレクトリへのパス */
	private final Path dataDirPath = Paths.get("src", "userdata");
	
	/** 一般ユーザー一覧を格納するファイルへのパス */
	private final Path allUsersFilePath = dataDirPath.resolve(Paths.get("users.txt"));
	
	/**
	 * コンストラクタ
	 */
	UserAccessImpl(){
		super();
	}
	
	/**
	 * フィールドのパス先のファイルとディレクトリが存在しなければ作成するメソッド。
	 * 
	 * @return 存在しなかった場合、trueを返す。
	 */
	public boolean initDir() throws Exception{
		if (Files.exists(dataDirPath) == false) {
			Files.createDirectories(dataDirPath);
			Files.createFile(allUsersFilePath);
			return true;
		}else if(Files.exists(allUsersFilePath) == false) {
			Files.createFile(allUsersFilePath);
			return true;
		}
		return false;
	}
	
	/**
	 * 入力された名前と一致するユーザーが存在するか確認するメソッド
	 * 
	 * @param name ユーザー名
	 * @return 存在すればtrue、しなければfalseを返します。
	 * @throws Exception 例外
	 */
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
	
	/**
	 * ユーザーの名前を受け取り、そのユーザーのインスタンスを返すメソッド。
	 * 
	 * @param name ユーザー名
	 * @return ユーザーのインスタンス
	 * @throws Exception 例外
	 */
	public User readUser(String name)throws Exception{
		Path userFilePath = dataDirPath.resolve(Paths.get(name + ".ser"));
		ObjectInputStream fileRead = new ObjectInputStream(new FileInputStream(userFilePath.toString()));
		
		User user;
		try(fileRead){
			user = (User) fileRead.readObject();
		}
		return user;
	}
	
	/**
	 * ユーザーのインスタンスを受け取り、シリアライズするメソッド。
	 * 
	 * @param user ユーザーのインスタンス
	 * @throws Exception 例外
	 */
	public void writeUser(User user)throws Exception{
		Path userFilePath = dataDirPath.resolve(Paths.get(user.getName() + ".ser"));
		ObjectOutputStream fileWrite = new ObjectOutputStream(new FileOutputStream(userFilePath.toString()));
		try(fileWrite){
			fileWrite.writeObject(user);
		}
	}
	
	/**
	 * ユーザー一覧に新しいユーザー名を書き込むメソッド。
	 * 
	 * @param name ユーザー名
	 * @throws Exception 例外
	 */
	public void addUser(String name)throws Exception{
		BufferedWriter fileWrite = new BufferedWriter(new FileWriter(allUsersFilePath.toString(), true));
		try(fileWrite){
			fileWrite.write(name + "\r\n");
			fileWrite.flush();
		}
	}
	
	/**
	 * 入力されたユーザーのファイルを削除するメソッド
	 * 
	 * @param deleteName 削除するユーザーの名前
	 * @return deleteNameのユーザーが存在すれば削除してtrue、存在しなければfalseを返します。
	 * @throws Exception 例外
	 */
	public boolean deleteUser(String deleteName)throws Exception{
		Path userFilePath = dataDirPath.resolve(Paths.get(deleteName + ".ser"));
		if(Files.exists(userFilePath) == true) {
			Files.delete(userFilePath);
			editUserFileDelete(deleteName);
			return true;
		}
		
		return false;
	}
	
	/**
	 * ユーザー一覧からユーザーを削除するメソッド。
	 * 
	 * @paramu deleteName 削除するユーザーの名前
	 * @throws Exception 例外
	 */
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
