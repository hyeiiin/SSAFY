import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_17478_������ {

	public static void func(int i, int n) {
		String s = "";
		for (int j = 0; j < i; j++) {
			s += "____";
		}

		System.out.println(s + "\"����Լ��� ������?\"");
		if (i < n) {
			System.out.println(s + "\"�� ����. �������� �� �� ����⿡ �̼��� ��� ������ ����� ������ �־���.");
			System.out.println(s+ "���� ������� ��� �� ���ο��� ������ ������ �߰�, ��� �����Ӱ� ����� �־���.");
			System.out.println(s+ "���� ���� ��κ� �ǾҴٰ� �ϳ�. �׷��� ��� ��, �� ���ο��� �� ���� ã�ƿͼ� ������.\"");
			func(i + 1, n);
		}
		if (i == n) {
			System.out.println(s + "\"����Լ��� �ڱ� �ڽ��� ȣ���ϴ� �Լ����\"");
//			return;
		}
		System.out.println(s + "��� �亯�Ͽ���.");

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		System.out.println("��� �� ��ǻ�Ͱ��а� �л��� ������ �������� ã�ư� ������.");

		func(0, n);

	}

}
