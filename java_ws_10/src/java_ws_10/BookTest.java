package java_ws_10;


public class BookTest {

	public static void main(String[] args) {
		
		IBookManager manager = BookManagerImpl.getInstance();
//		1. 도서 정보를 저장
//		manager.add(new Book("1234","Java Pro", "김하나", "jaen.kr", 15000,"Java 기본 문법",20));
//		manager.add(new Book("1235","Java Pro2", "김하나", "jaen.kr", 25000,"Java 응용",30));
//		manager.add(new Book("32456","분석설계", "홍길동", "jaen.kr", 30000,"sw 모델링",10));
//		manager.add(new Magazine("546787","월간 알고리즘", "소나무", "jaen.kr", 10000,"알고리즘 7월", 11,2023, 07));
//		
//		2. 도서 전체 목록 조회
		System.out.println("=====도서 전체 목록 조회================");
		Book[] books = manager.getList();
		for (Book book : books) {
//		for (Book book : manager.getList()) {
			System.out.println(book);
		}
//		3. 도서 중 일반 도서 목록 조회
		System.out.println("======일반 도서 목록 조회========");
		for (Book book : manager.getBooks()) {
			System.out.println(book);
		}
//		4. 도서 중 잡지 목록 조회
		System.out.println("=====잡지 목록 조회==========");
		for (Magazine magazine : manager.getMagazines()) {
			System.out.println(magazine);
		}
//		5. 검색어가 포함된 도서를 검색
//		6. ISBN 일치 검색
//		7. 도서 가격 종합
//		8. 도서 가격 평균
//		9. 도서 판매
		System.out.println("===========도서 판매================");
		try {
			manager.sell("11111", 30);
		} catch (IsbnNotFoundException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
//		10. 도서 구매 
//		11. 도서 리스트를 파일로 출력
		System.out.println("=====ISBN 일부 일치 삭제?=======");
		manager.remove("123");
		System.out.println("=====도서 전체 목록 조회================");
		books = manager.getList();
		for (Book book : books) {
//		for (Book book : manager.getList()) {
			System.out.println(book);
		}
	
		manager.saveData();

	}

}
