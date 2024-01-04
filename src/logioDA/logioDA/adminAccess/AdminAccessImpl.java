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

/**
 * 管理者のデータアクセス用クラスです。
 */
class AdminAccessImpl implements AdminAccess{
	
	/** データが入ってるディレクトリへのパス */
	private final Path dataDirPath = Paths.get("src", "userdata");
	
	/** 管理者の情報を格納するファイルへのパス */
	private final Path adminFilePath = dataDirPath.resolve(Paths.get("admin.txt"));
	
	/** 一般ユーザー一覧を格納するファイルへのパス */
	private final Path allUsersFilePath = dataDirPath.resolve(Paths.get("users.txt"));
	
	/**
	 * コンストラクタ
	 */
	AdminAccessImpl(){
		super();
	}
	
	/**
	 * 管理者の情報を格納するファイルが作成済みかどうかを確認するメソッド。
	 * 
	 * @return 作成済みであればtrue、そうでなければfalseを返します。
	 * @throws Exception  例外
	 */
	public boolean isPrepared() throws Exception{
		File adminFile = adminFilePath.toFile();
		return adminFile.exists();
	}
	
	/**
	 * 入力されたnameとpasswordが管理者のものと一致しているか確認するメソッド。
	 * 
	 * @param name  名前
	 * @param password  パスワード
	 * @return 一致していればtrue、そうでなければfalseを返します。
	 * @throws Exception  例外
	 */
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
	
	/**
	 * 管理者を作成するメソッド。
	 * 
	 * @param name  名前
	 * @param password  パスワード
	 * @throws Exception  例外
	 */
	public void createAdmin(String name, String password) throws Exception{
		BufferedWriter fileWrite = new BufferedWriter(new FileWriter(adminFilePath.toString(), false));
		try(fileWrite){
			fileWrite.write(name + "\r\n" + password + "\r\n");
			fileWrite.flush();
		}
	}
	
	/**
	 * 一般ユーザー一覧を取得するメソッド。
	 * 
	 * @return 一般ユーザーの名前のリストを返します。
	 * @throws Exception 例外
	 */
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
