import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;

public class finalproject {
	static final String rootPath = System.getProperty("user.dir");
	static final String address = rootPath + "//juso.txt";
	static File file = new File(address);

	public static void main(String[] args) throws IOException {
		System.out.println("[기말프로젝트] 60221348 김태강");
		System.out.println("[연락처 관리 프로그램]");

		List<PersonInfo> person = new ArrayList<>();
		if (file.createNewFile()) {
			print_menu(person);
		} else {
			readTxt(person);
			print_menu(person);
		}
	}

	private static void print_menu(List<PersonInfo> person) throws IOException {
		boolean runx = true;
		while (runx) {
			boolean rune = true;
			int num = 0;
			Scanner sc = new Scanner(System.in);	
			System.out.println();
			System.out.println("1. 연락처 출력");
			System.out.println("2. 연락처 등록");
			System.out.println("3. 연락처 삭제");
			System.out.println("4. 끝내기");
			System.out.println();

			System.out.print("메뉴를 선택하세요: ");
			while(rune) {
			try{
				num = sc.nextInt();
			switch (num) {
				case 1 -> {
					System.out.println();
					readTxt(person);
					update(person);
					writeTxt(person);
					view_juso(person);
					System.out.println();
					rune = false;
				}
				case 2 -> {
					System.out.println();
					add_juso(sc, person);
					rune = false;
				}
				case 3 -> {
					delete_juso(sc, person);
					rune = false;
				}
				case 4 -> {
					System.out.println("프로그램을 종료합니다.");
					System.exit(0);
				}
				default -> {
					System.out.println();
					System.out.print("잘못된 메뉴입니다. 메뉴를 다시 선택하세요: ");
				}
			}
		}
			catch(Exception e){
				sc = new Scanner(System.in);
				System.out.print("잘못된 메뉴입니다. 메뉴를 다시 선택하세요: ");
			}
		}
	}
}

	// 주소록 출력
	private static void view_juso(List<PersonInfo> person) {

		if (person.size() == 0) {
			System.out.println("등록된 연락처가 없습니다.");
		}

		Reader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(address);
			br = new BufferedReader(fr);
			String line = "";
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 주소록 추가
	private static void add_juso(Scanner sc, List<PersonInfo> person) {
		String name;
		String age;
		String tel;
		System.out.print("이름 입력: ");
		name = sc.next();
		System.out.print("나이 입력: ");
		age = sc.next();
		System.out.print("번호 입력: ");
		tel = sc.next();
		person.add(new PersonInfo(name, age, tel));
		update(person);
		writeTxt(person);
	}

	// 주소록 삭제
	private static void delete_juso(Scanner sc, List<PersonInfo> person) throws IOException {
		boolean run2 = true;
		int num2 = 0;
		int del = 0;

		while (run2) {
			boolean rune = true;

			System.out.println();
			System.out.println("1. 순번 삭제");
			System.out.println("2. 이름 삭제");
			System.out.println("3. 번호 삭제");
			System.out.println("4. 메인 메뉴로 이동");
			System.out.println();

			System.out.print("세부 메뉴를 선택하세요: ");
			while(rune) {
			try {
				num2 = sc.nextInt();
			}catch (Exception e) {
				sc = new Scanner(System.in);
			}

			switch (num2) {
				case 1 -> {
					boolean run = true;
					while (run) {
						try {
							System.out.println();
							System.out.print("삭제할 연락처 순번은? ");
							del = sc.nextInt();
							person.remove(del - 1);
							System.out.print(del + "번 연락처가 삭제되었습니다.\n");
							run = false;
							run2 = false;
							rune = false;
							break;
						} catch (Exception e) {
							sc = new Scanner(System.in);
							System.out.println("존재하지 않는 연락처입니다.");
							continue;
						}
					}
					update(person);
					writeTxt(person);
					run2 = false;
				}
				case 2 -> {
					boolean run = true;
					while (run) {
				try{
					System.out.println();
					System.out.print("삭제할 연락처 이름은? ");
					String str = sc.next();
					for (int i = 0; i < person.size(); i++) {
						PersonInfo sercageerson2 = (PersonInfo) person.get(i);
						if (sercageerson2.getName().contains(str)) {
							person.remove(i);
							System.out.print(sercageerson2.getName() + " 연락처가 삭제되었습니다.\n");
							update(person);
							writeTxt(person);
							run = false;
							run2 = false;
							rune = false;
						}
					}
				}
				catch(Exception e){
					System.out.println("잘못된 이름입니다. 다시 입력하세요");
				}
				finally{
					if (run2 == true) {
					System.out.println("존재하지 않는 연락처입니다.");
					}
				}
			}
		}
				case 3 -> {
					boolean run = true;
					while (run) {
					try{
					System.out.println();
					System.out.print("삭제할 연락처 번호는? ");
					String str2 = sc.next();
					for (int i = 0; i < person.size(); i++) {
						PersonInfo sercageerson2 = (PersonInfo) person.get(i);
						if (sercageerson2.getTel().contains(str2)) {
							person.remove(i);
							System.out.print(sercageerson2.getNum() + "번 연락처가 삭제되었습니다.\n");
							System.out.println();
							update(person);
							writeTxt(person);
							run = false;
							run2 = false;
							rune = false;
						}
					}
				}catch(Exception e){
					System.out.println("잘못된 번호입니다. 다시 입력하세요");
				}
				finally{
					if (run2 == true) {
					System.out.println("존재하지 않는 번호입니다.");
					}
				}
			}
		}
				case 4 -> {
					System.out.println();
					run2 = false;
					rune = false;
					print_menu(person);
				}
				default -> {
					System.out.println();
					System.out.print("잘못된 세부 메뉴입니다. 세부 메뉴를 다시 선택하세요: ");
				}
			}
		}
	}
}

	// 번호 순차대로 다시 넘버링
	private static void update(List<PersonInfo> person) {
		for (int i = 0; i < person.size(); i++) {
			PersonInfo n = (PersonInfo) person.get(i);
			n.setNum(i + 1);
		}
	}

	// 텍스트 읽어오기
	private static List<PersonInfo> readTxt(List<PersonInfo> person) {
		person.clear();
		Reader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(address);
			br = new BufferedReader(fr);
			String line = "";
			int idx;
			String[] words = new String[3];
			while ((line = br.readLine()) != null) {
				idx = line.indexOf("]");
				line = line.substring(idx + 1);
				words = line.split("\t");
				person.add(new PersonInfo(words[0], words[1], words[2]));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return person;
	}

	// 텍스트 쓰기
	private static void writeTxt(List<PersonInfo> person) {
		Writer fw = null;
		BufferedWriter bw = null;

		try {
			// 주스트림
			fw = new FileWriter(address);

			// 메인스트림
			bw = new BufferedWriter(fw);

			for (int i = 0; i < person.size(); i++) {
				PersonInfo writeperson = (PersonInfo) person.get(i);
				bw.write("[" + writeperson.getNum() + "]");
				bw.write(writeperson.getName() + "\t");
				bw.write(writeperson.getAge() + "\t");
				bw.write(writeperson.getTel());
				bw.write("\r\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				bw.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

class PersonInfo {
	private int num;
	private String name;
	private String age;
	private String tel;

	public PersonInfo(String name, String age, String tel) {
		this.name = name;
		this.age = age;
		this.tel = tel;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
}