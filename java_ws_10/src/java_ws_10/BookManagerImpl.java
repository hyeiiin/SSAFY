package java_ws_10;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class BookManagerImpl implements IBookManager {
	
	private BookManagerImpl() {
		loadData();
	}
	
	private static BookManagerImpl instance = new BookManagerImpl();

	public static BookManagerImpl getInstance() {
	
		return instance;
	}

	//배열, List, Map, Set
	private List<Book> list = new ArrayList<Book>();
	
	@Override
	public void add(Book book) {
		list.add(book);
	}

	@Override
	public void remove(String isbn) {
		final int size = list.size();
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getIsbn().contains(isbn)) {
				System.out.println(list.get(i));
				list.remove(i--);
			}
		}
	}

	@Override
	public Book[] getList() {
		Book[] result = new Book[list.size()];
		
		return list.toArray(result);
	}

	@Override
	public Book[] getBooks() {
		ArrayList<Book> temp = new ArrayList<>();
		for (Book book : list) {
			if(!(book instanceof Magazine)) {//매거진 타입이 아니면, temp 리스트에 book객체 담기
				temp.add(book);
			}
		}
		//반환 타입 맞추기 위해서 Book[]배열 만들기
		Book[] result = new Book[temp.size()];
		return temp.toArray(result);
	}

	@Override
	public Magazine[] getMagazines() {
		ArrayList<Book> temp = new ArrayList<>();
		for (Book book : list) {
			if(book instanceof Magazine) {//매거진 타입이 아니면, temp 리스트에 book객체 담기
				temp.add(book);
			}
		}
		//반환 타입 맞추기 위해서 Book[]배열 만들기
		Magazine[] result = new Magazine[temp.size()];
		return temp.toArray(result);
	}

	@Override
	public Book[] searchByTitle(String title) {
		ArrayList<Book> temp = new ArrayList<>();
		for (Book book : list) {
			if(book.getTitle().contains(title)) {
				temp.add(book);
			}
		}
		return temp.toArray(new Book[temp.size()]);
	}

	@Override
	public Book searchByIsbn(String isbn) {
		for (Book book : list) {
			if(book.getIsbn().equals(isbn)) return book;
		}
		return null;
	}

	@Override
	public int getTotalPrice() {
		int totalPrice = 0;
		for (Book book : list) {
			totalPrice += book.getPrice();
		}
		return totalPrice;
	}

	@Override
	public double getPriceAvg() {
		// TODO Auto-generated method stub
		return (double)getTotalPrice()/list.size();
	}

	@Override
	public void sell(String isbn, int quantity) throws IsbnNotFoundException{
//		Book book = searchByIsbn(isbn);//위에 메서드가 구현되어 있다면 활용하면 좋음.
		Book findBook = null;
		for (Book book : list) {
			if(book.getIsbn().equals(isbn)) {
				findBook = book;
				break;
			}
		}
		if(findBook==null) {
			throw new IsbnNotFoundException(isbn);
		}else { //null이 아니면 팔기
		//원래 수량보다, 팔려는 수량이 클 수 있음 
		//어떻게 할까? 1. exception 발생시킨다. 2. 그냥 뺀다. 3. console에 찍고 값은 셋팅 안한다 등등
		// 생각해보고 구현할 것
		findBook.setQuantity(findBook.getQuantity()-quantity);
		}
	}

	@Override
	public void buy(String isbn, int quantity) {
		// TODO Auto-generated method stub

	}
	@Override
	public void saveData() {
		try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("book.dat"))){
			out.writeObject(list);
		}catch(Exception e) {
			System.out.println("파일 쓰기 실패!");
			e.printStackTrace();
		}
	}
	//BookManagerImpl 객체가 생성 될 때, 파일에서 도서 리스트를 로드한다.
	private void loadData() {
		File f = new File("book.dat");
		if(f.exists()) {// 파일이 존재하면 파일에서 데이터 읽기.
			try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(f))){
				list = (List<Book>) in.readObject(); 
			}catch(Exception e) {
				System.out.println("파일 읽기 실패 ㅜㅜ");
				e.printStackTrace();
			}
		}else { //파일이 존재하지 않으면
			list = new ArrayList<Book>();
			list.add(new Book("1234","Java Pro", "김하나", "jaen.kr", 15000,"Java 기본 문법",20));
			list.add(new Book("1235","Java Pro2", "김하나", "jaen.kr", 25000,"Java 응용",30));
			list.add(new Book("32456","분석설계", "홍길동", "jaen.kr", 30000,"sw 모델링",10));
			list.add(new Magazine("546787","월간 알고리즘", "소나무", "jaen.kr", 10000,"알고리즘 7월", 11,2023, 07));
		}
	}

}
