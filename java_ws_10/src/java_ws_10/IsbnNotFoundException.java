package java_ws_10;

public class IsbnNotFoundException extends Exception {

	public IsbnNotFoundException(String isbn) {
		super(isbn+" 이런 고유 번호는 없어요");
	}
}
