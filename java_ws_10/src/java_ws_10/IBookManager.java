package java_ws_10;

/**
 * interface 왜 만들어요?
 *  목적1. 협업 : 같은 기능을 만들 때, 구현부가 다를 수 있음. 메소드명/클래스도 다를 수 있음 따라서 
 *  			통일의 목적
 *  목적2. 교체 용이 : 다형성을 적용하여 상속 받아서 구현 된 클래스를 가져와 넣을 수 있음
 *  목적 3. 다중 상속 : 각각의 클래스마다 다양한 기능을 가지고 있는데, 
 *  				특징에 맞게 인터페이스를 상속받아서 구현 가능
 * @author SSAFY
 *
 */
public interface IBookManager {
//	1. 도서 정보를 저장
	void add(Book book);
	void remove(String isbn);
//	2. 도서 전체 목록 조회
	Book[] getList();
//	3. 도서 중 일반 도서 목록 조회
	Book[] getBooks();
//	4. 도서 중 잡지 목록 조회
	Magazine[] getMagazines();
//	5. 검색어가 포함된 도서를 검색
	Book[] searchByTitle(String title);
//	6. ISBN 일치 검색
	Book searchByIsbn(String isbn);
//	7. 도서 가격 종합
	int getTotalPrice();
//	8. 도서 가격 평균
	double getPriceAvg();
//	9. 도서 판매
	void sell(String isbn, int quantity) throws IsbnNotFoundException;
//	10. 도서 구매 
	void buy(String isbn, int quantity);
	//파일 쓰기
	void saveData();

}
