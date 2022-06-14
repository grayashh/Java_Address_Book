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
	static final String ROOT_PATH = System.getProperty("user.dir"); // 현재 작업중인 디렉토리를 가져옴
	static final String ADDRESS = ROOT_PATH + "//juso.txt"; // juso.txt를 저장할 위치 지정
	static File file = new File(ADDRESS); // file을 ADDRESS 위치에 생성

	public static void main(String[] args) throws IOException {
		//Intro
		System.out.println("[기말프로젝트] 60221348 김태강");
		System.out.println("[연락처 관리 프로그램]");

		List<PersonInfo> person = new ArrayList<>(); // person List 객체를 생성

		if (file.createNewFile()) { // 파일이 없다면 print_menu로 파일이 있다면 readTxT 후 print_menu
			print_menu(person);
		} else {
			readTxt(person);
			print_menu(person);
		}
	}

	//메뉴 출력
	private static void print_menu(List<PersonInfo> person)throws IOException {
		boolean run = true; // 메뉴 반복 true
		while (run) {
			boolean in_run = true;
			int num = 0;
			Scanner sc = new Scanner(System.in); // Scanner 객체 생성
			System.out.println();
			System.out.println("1. 연락처 출력");
			System.out.println("2. 연락처 등록");
			System.out.println("3. 연락처 삭제");
			System.out.println("4. 끝내기");
			System.out.println();

			System.out.print("메뉴를 선택하세요: ");
			while(in_run) { // 잘못된 메뉴 반복 입력 받기
			try{
				num = sc.nextInt();
			switch (num) {
				case 1 -> { // 연락처 출력
					System.out.println();
					readTxt(person); // juso.txt에서 텍스트를 읽어와 list에 저장
					view_juso(person);
					System.out.println();
					in_run = false;
				}
				case 2 -> { //연락처 추가
					System.out.println();
					add_juso(sc, person);
					in_run = false;
				}
				case 3 -> { // 연락처 삭제
					delete_juso(sc, person);
					in_run = false;
				}
				case 4 -> { // 프로그램 종료
					System.out.println("프로그램을 종료합니다.");
					run = false;
					System.exit(0);
				}
				default -> { // 1~4 외의 숫자를 입력했을 시 실행
					System.out.println();
					System.out.print("잘못된 메뉴입니다. 메뉴를 다시 선택하세요: ");
				}
			}
		}
			catch(Exception e){ // 숫자 외 다른 문자를 입력했을 시 실행
				sc = new Scanner(System.in); // Scanner 초기화
				System.out.print("잘못된 메뉴입니다. 메뉴를 다시 선택하세요: ");
			}
		}
	}
}

	// 주소록 출력
	private static void view_juso(List<PersonInfo> person) {

		if (person.size() == 0) { // 만약 주소록 리스트 size가 0이라면 메세지 출력
			System.out.println("등록된 연락처가 없습니다.");
		}
		try {
			Reader fr = new FileReader(ADDRESS);
			BufferedReader br = new BufferedReader(fr);
			String line = "";
			while ((line = br.readLine()) != null) { // 한 줄씩 불러와 1lne에 넣어 출력하고 불러온 값이 없다면(null) break	
				System.out.println(line);
			}
			br.close();
		} catch (Exception e) { // 파일 입출력 예외처리
			e.printStackTrace();
		}
	}

	// 주소록 추가
	private static void add_juso(Scanner sc, List<PersonInfo> person) { // 연락처 추가 메소드
		String name;
		String age;
		String tel;
		System.out.print("이름 입력: "); // 이름을 입력 받는다
		name = sc.next();
		System.out.print("나이 입력: "); // 나이를 입력 받는다
		age = sc.next();
		System.out.print("번호 입력: "); // 번호를 입력 받는다
		tel = sc.next();
		person.add(new PersonInfo(name, age, tel)); // person List에 PersonInfo 객체 추가
		update(person); // 번호 순서대로 넘버링
		writeTxt(person); // 텍스트 쓰기
	}

	// 주소록 삭제
	private static void delete_juso(Scanner sc, List<PersonInfo> person) throws IOException {
		boolean run2 = true;
		int num2 = 0;
		int del = 0;
		boolean in_run = true;
		
		while (run2) { // 고급 기능 반복
			System.out.println();
			System.out.println("1. 순번 삭제");
			System.out.println("2. 이름 삭제");
			System.out.println("3. 번호 삭제");
			System.out.println("4. 메인 메뉴로 이동");
			System.out.println();

			System.out.print("세부 메뉴를 선택하세요: ");

			in_run = true;
			
			while(in_run) { // 잘못된 입력 다시 받기 반복
			try {
				num2 = sc.nextInt();
			}catch (Exception e) { // 입출력 예외처리
				sc = new Scanner(System.in); // Scanner 초기화
			}

			switch (num2) {
				case 1 -> { // 순번 삭제
					boolean run = true;
					while (run) { // 잘못된 입력 다시 받기 반복
						try {
							System.out.println();
							System.out.print("삭제할 연락처 순번은? ");
							del = sc.nextInt();
							person.remove(del - 1); // del번째 원소 연락처 리스트에서 삭제
							System.out.print(del + "번 연락처가 삭제되었습니다.\n");
							// 입력 다시 받기 loop 빠져나오기
							run = false;
							run2 = false; 
							in_run = false;
						} catch (Exception e) { // 입출력 예외처리
							sc = new Scanner(System.in); // Scanner 초기화
							System.out.println("존재하지 않는 연락처입니다.");
						}
					}
					update(person); // 넘버링
					writeTxt(person); // 파일 쓰기
				}
				case 2 -> { // 이름 삭제
					boolean run = true;
					while (run) {  // 잘못된 입력 다시 받기 반복
				try{
					System.out.println();
					System.out.print("삭제할 연락처 이름은? ");
					String str = sc.next();
					for (int i = 0; i < person.size(); i++) { // 연락처 list size 만큼 반복
						PersonInfo n = (PersonInfo) person.get(i); // i번째 person list 객체를 가져옴
						if (n.getName().equals(str)) { // i번째 객체와 입력받은 연락처 이름과 같은지 확인
							person.remove(i); // i번째 객체 제거
							System.out.print(n.getName() + " 연락처가 삭제되었습니다.\n");
							update(person); // 넘버링
							writeTxt(person); // 파일 쓰기
							//입력 다시받기 Loop 빠져나오기
							run = false;
							run2 = false;
							in_run = false;
						}
					}
				}
				catch(Exception e){ //입출력 예외처리
					System.out.println("잘못된 입력입니다.");
				}
				finally{
					if (run2 == true) { // 연락처 list 내에 이름이 같은 사람이 없다면
					System.out.println("존재하지 않는 연락처입니다.");
					}
				}
			}
		}
				case 3 -> { // 번호 삭제
					boolean run = true;
					while (run) { // 잘못된 입력 다시 받기 반복
					try{
					System.out.println();
					System.out.print("삭제할 연락처 번호는? ");
					String str2 = sc.next();
					for (int i = 0; i < person.size(); i++) { // 연락처 list size 만큼 반복
						PersonInfo n = (PersonInfo) person.get(i); // i번째 person list 객체를 가져옴
						if (n.getTel().equals(str2)) { // i번째 객체와 입력받은 연락처 번호과 같은지 확인
							person.remove(i); // i번째 객체 제거
							System.out.print(n.getNum() + "번 연락처가 삭제되었습니다.\n");
							System.out.println();
							update(person); // 넘버링
							writeTxt(person); // 파일 쓰기
							//입력 다시받기 Loop 빠져나오기
							run = false;
							run2 = false;
							in_run = false;
						}
					}
				}catch(Exception e){
					System.out.println("잘못된 입력입니다.");
				}
				finally{ // 연락처 list 내에 번호가 같은 사람이 없다면
					if (run2 == true) {
					System.out.println("존재하지 않는 번호입니다.");
					}
				}
			}
		}
				case 4 -> { // 끝내기
					System.out.println();
					run2 = false;
					in_run = false;
					print_menu(person);
				}
				default -> { // 1~4 외의 숫자를 입력했을 시 실행
					System.out.println();
					System.out.print("잘못된 세부 메뉴입니다. 세부 메뉴를 다시 선택하세요: ");
				}
			}
		}
	}
}

	// 번호 순차대로 다시 넘버링
	private static void update(List<PersonInfo> person) {
		for (int i = 0; i < person.size(); i++) { // 연락처 list size 만큼 반복
			// 1부터 넘버링
			PersonInfo n = (PersonInfo) person.get(i);
			n.setNum(i + 1);
		}
	}

	// 텍스트 읽어오기
	private static List<PersonInfo> readTxt(List<PersonInfo> person) {
		person.clear(); // List 비우기
		try {
			Reader fr = new FileReader(ADDRESS);
			BufferedReader br = new BufferedReader(fr);
			String line = "";
			int idx;
			String[] words = new String[3]; // name, age, tel을 받을 배열 선언
			while ((line = br.readLine()) != null) { // 한 줄씩 불러와 1lne에 넣어 출력하고 불러온 값이 없다면(null) break
				idx = line.indexOf("]"); // ]가 위치한 index 번호 저장
				line = line.substring(idx + 1); // ] 앞 공백 뒤 부터 line에 저장
				words = line.split("\t"); // tab을 기준으로 단어 나누기
				person.add(new PersonInfo(words[0], words[1], words[2])); // 나눠진 단어 주소록 list에 넣기
			}
			br.close();
		} catch (Exception e) { // 파일 입출력 예외처리
			e.printStackTrace();
		}
		return person;
	}

	// 텍스트 쓰기
	private static void writeTxt(List<PersonInfo> person) {
		try {
			Writer fw = new FileWriter(ADDRESS);

			BufferedWriter bw = new BufferedWriter(fw);

			for (int i = 0; i < person.size(); i++) { // 연락처 list size 만큼 반복
				PersonInfo writeperson = (PersonInfo) person.get(i); // i번째 객체 가져오기
				bw.write("[" + writeperson.getNum() + "]"); // 순서 쓰기
				bw.write(writeperson.getName() + "\t"); // 이름 쓰기
				bw.write(writeperson.getAge() + "\t"); // 나이 쓰기
				bw.write(writeperson.getTel()); // 번호 쓰기
				bw.write("\r\n"); //
			}
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class PersonInfo { // 사용자 정보를 저장할 클래스 생성
	private int num; // 순서
	private String name; // 이름
	private String age; // 나이
	private String tel; // 전화번호

	public PersonInfo(String name, String age, String tel) { // 이름, 나이, 전화번호를 받아 객체를 생성하는 생성자
		this.name = name;
		this.age = age;
		this.tel = tel;
	}

	// 순서, 이름, 나이, 전화번호를 반환하는 메소드
	public int getNum() {
		return num;
	}
	public String getName() {
		return name;
	}
	public String getAge() {
		return age;
	}
	public String getTel() {
		return tel;
	}

	// 순서, 이름, 나이, 전화번호를 변경하는 메소드
	public void setNum(int num) {
		this.num = num;
	}

	public void setName(String name) {
		this.name = name;
	}


	public void setAge(String age) {
		this.age = age;
	}


	public void setTel(String tel) {
		this.tel = tel;
	}
}