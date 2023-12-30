package logioPre.presentation;

import java.io.Console;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import logioBL.manageAdmin.ManageAdmin;
import logioBL.manageUser.ManageUser;
import logioDI.diContainer.Container;

public class Main {

	public static void main(String[] args) throws Exception{
		boolean run = true;
		String input;
		int inputInt;
		Scanner scan = new Scanner(System.in);
		Console console = System.console();
		if(console == null) {
			System.out.println("IDEではなくコンソールで実行してください。");
			scan.close();
			return;
		}
		
		Container container = new Container();
		if(container.needAdmin()) {
			makeAdmin(scan, console, container);
			System.out.println();
			System.out.println("-------------------------------");
		}
		
		end:
		while(run) {
			System.out.println("何をしますか");
			System.out.println("1.ログイン");
			System.out.println("2.新規ユーザー登録");
			System.out.println("3.管理者ログイン");
			System.out.println("9.終了");
			System.out.print(">");
			input = scan.nextLine();
			try {
				inputInt = Integer.parseInt(input);
			}catch(NumberFormatException e) {
				System.out.println("正確な数字を入力してください。");
				System.out.println();
				System.out.println("-------------------------------");
				continue;
			}
			switch(inputInt) {
			case 1:
				login(scan, container);
				break;
			case 2:
				createUser(scan, container);
				break;
			case 3:
				adminLogin(scan, console, container);
				break;
			case 9:
				System.out.println("終了します。");
				break end;
			default:
				System.out.println("適切な数字を入力してください");
			}
			System.out.println();
			System.out.println("-------------------------------");
		}
		scan.close();

	}
	
	private static void makeAdmin(Scanner scan, Console console, Container container) throws Exception{
		System.out.println("初めての実行です。最初に管理者を作成します。");
		
		String name;
		char[] password;
		while(true) {
			System.out.println("名前を入力してください。");
			System.out.print(">");
			name = scan.nextLine();
			if(name.length() == 0) {
				System.out.println("無効な名前です。");
				System.out.println();
				System.out.println("-------------------------------");
				continue;
			}
			System.out.println("パスワードを入力してください。");
			System.out.print(">");
			password = console.readPassword();
			if(password.length == 0) {
				System.out.println("無効なパスワードです。");
				System.out.println();
				System.out.println("-------------------------------");
				continue;
			}
			break;
		}
		container.createAdmin(name, new String(password));
		System.out.println("管理者の作成が完了しました");
	};
	
	private static void login(Scanner scan, Container container) throws Exception{
		System.out.println("名前を入力してください。");
		System.out.print(">");
		String name = scan.nextLine();
		Optional<ManageUser> tmpManageUser = container.login(name);
		
		if(tmpManageUser.isEmpty() || (name.length() == 0)) {
			System.out.println("無効な名前です。");
			return;
		}
		System.out.println();
		System.out.println("-------------------------------");
		
		ManageUser manageUser = tmpManageUser.get();
		String input;
		int inputInt;
		while(true) {
			System.out.println("名前：" + manageUser.getName());
			System.out.println("コメント：\r\n" + manageUser.getComments() + "\r\n");
			System.out.println("何をしますか");
			System.out.println("1.名前の変更");
			System.out.println("2.コメントの変更");
			System.out.println("3.コメントの追加");
			System.out.println("9.ログアウト");
			System.out.print(">");
			input = scan.nextLine();
			try {
				inputInt = Integer.parseInt(input);
			}catch(NumberFormatException e) {
				System.out.println("正確な数字を入力してください。");
				System.out.println();
				System.out.println("-------------------------------");
				continue;
			}
			switch(inputInt) {
			case 1:
				System.out.println("\r\n名前を入力してください。");
				System.out.print(">");
				Optional<String> opt = manageUser.setName(scan.nextLine());
				if(opt.isPresent()) {
					System.out.println(opt.get());
					break;
				}
				System.out.println("名前を変更しました。");
				break;
			case 2:
				manageUser.setComments(inputComments(scan));
				System.out.println("コメントを変更しました。");
				break;
			case 3:
				manageUser.addComments(inputComments(scan));
				System.out.println("コメントを変更しました。");
				break;
			case 9:
				container.logout(manageUser);
				System.out.println("\r\nログアウトしました。");
				return;
			default:
				System.out.println("\r\n適切な数字を入力してください");
			}
			
			System.out.println();
			System.out.println("-------------------------------");
		}
	}

	private static void createUser(Scanner scan, Container container) throws Exception{
		System.out.println("名前を決めてください。");
		System.out.print(">");
		String name = scan.nextLine();
		
		if(container.searchUser(name) || (name.length() == 0)) {
			System.out.println("無効な名前です。");
			return;
		}
		
		System.out.println("コメントを入力してください");
		System.out.println("endで入力を終了します。");
		StringBuilder sb = new StringBuilder();
		String tmpComments;
		boolean run = true;
		while(run) {
			System.out.print(">");
			tmpComments = scan.nextLine();
			if(tmpComments.equals("end")) {
				run = false;
			}else {
				sb.append(tmpComments + "\r\n");
			}
		}
		container.createUser(name, sb.toString());
		System.out.println("ユーザーを作成しました。");
	}
	
	private static void adminLogin(Scanner scan, Console console, Container container) throws Exception{
		System.out.println("名前を入力してください。");
		System.out.print(">");
		String name = scan.nextLine();
		System.out.println("パスワードを入力してください。");
		System.out.print(">");
		char[] password = console.readPassword();
		Optional<ManageAdmin> tmpManageAdmin = container.adminLogin(name, new String(password));
		
		if(tmpManageAdmin.isEmpty()) {
			System.out.println("名前もしくはパスワードが無効です。");
			return;
		}
		System.out.println();
		System.out.println("-------------------------------");
		
		ManageAdmin manageAdmin = tmpManageAdmin.get();
		String input;
		int inputInt;
		while(true) {
			System.out.println("名前：" + manageAdmin.getAdminName() + "\r\n");
			System.out.println("何をしますか");
			System.out.println("1.全ユーザーの表示");
			System.out.println("9.ログアウト");
			System.out.print(">");
			input = scan.nextLine();
			try {
				inputInt = Integer.parseInt(input);
			}catch(NumberFormatException e) {
				System.out.println("正確な数字を入力してください。");
				System.out.println();
				System.out.println("-------------------------------");
				continue;
			}
			switch(inputInt) {
			case 1:
				System.out.println("ユーザー一覧");
				List<String> users = manageAdmin.getAllUserName();
				for(String user: users) {
					System.out.println(user);
				}
				System.out.println();
				System.out.println("-------------------------------");
				break;
			case 9:
				System.out.println("\r\nログアウトしました。");
				return;
			default:
				System.out.println("\r\n適切な数字を入力してください");
			}
			
			System.out.println();
		}
	}
	
	private static String inputComments(Scanner scan) throws Exception{
		System.out.println("\r\nコメントを入力してください");
		System.out.println("endで入力を終了します。");
		StringBuilder sb = new StringBuilder();
		String tmpComments;
		boolean run = true;
		while(run) {
			System.out.print(">");
			tmpComments = scan.nextLine();
			if(tmpComments.equals("end")) {
				run = false;
			}else {
				sb.append(tmpComments + "\r\n");
			}
		}
		return sb.toString();
	}
}
