/*
    sugar = int(input())
    bag = 0

    while sugar >= 0:
        if sugar % 5 == 0:
            bag += sugar//5
            print(bag)
            break

        sugar -= 3
        bag += 1

    else:
        print(-1)
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	static int SUGAR, BAG = 0, flag = 1;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		SUGAR = Integer.parseInt(br.readLine());
		
		while (SUGAR >= 0) {
			if (SUGAR % 5 == 0) {
				flag = 0;
				BAG += SUGAR/5;
				System.out.println(BAG);
				break;
			}
			
			SUGAR -= 3;
			BAG += 1;
		}
		if (flag == 1) {
			System.out.println(-1);
		}
		
	}
}
