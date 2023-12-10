package pre;

import java.util.Optional;
import java.util.Scanner;

import bl.ManageUser;
import di.ManageUserContainer;

public class Main {

	public static void main(String[] args) throws Exception{
		ManageUserContainer.initDir();
		
		boolean run = true;
		String input;
		int inputInt;
		Scanner scan = new Scanner(System.in);
		
		end:
		while(run) {
			System.out.println("何をしますか");
			System.out.println("1.ログイン");
			System.out.println("2.新規ユーザー登録");
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
				login(scan);
				break;
			case 2:
				createUser(scan);
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
	
	private static void login(Scanner scan) throws Exception{
		System.out.println("名前を入力してください。");
		System.out.print(">");
		String name = scan.nextLine();
		Optional<ManageUser> tmpManageUser = ManageUserContainer.login(name);
		
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
				ManageUserContainer.logout(manageUser);
				System.out.println("\r\nログアウトしました。");
				return;
			default:
				System.out.println("\r\n適切な数字を入力してください");
			}
			
			System.out.println();
			System.out.println("-------------------------------");
		}
	}

	private static void createUser(Scanner scan) throws Exception{
		System.out.println("名前を決めてください。");
		System.out.print(">");
		String name = scan.nextLine();
		
		if(ManageUserContainer.searchUser(name) || (name.length() == 0)) {
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
		ManageUserContainer.createUser(name, sb.toString());
		System.out.println("ユーザーを作成しました。");
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
