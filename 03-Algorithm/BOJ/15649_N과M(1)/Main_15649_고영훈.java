import java.util.LinkedList;
import java.util.Scanner;

public class Main_15649_고영훈 {
	public static void f(LinkedList<Integer> seq, int m, String ret) {
		if (m == 1) {
			for (Integer n : seq) {
				System.out.println(ret + n);
			}
		} else {
			for (Integer n : seq) {
				LinkedList<Integer> subseq = (LinkedList<Integer>) seq.clone();
				subseq.remove(n);
				f(subseq, m - 1, ret + n + " ");
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		int m = s.nextInt();
		
		LinkedList<Integer> seq = new LinkedList<>();
		for (int i = 1; i <= n; i++) {
			seq.add(i);
		}
		f(seq, m, "");
	}
}
