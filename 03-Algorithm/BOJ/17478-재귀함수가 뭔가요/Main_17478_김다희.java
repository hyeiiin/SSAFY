import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
public class Main {
	
	static StringBuilder ans=new StringBuilder();
	static int target;
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		target=Integer.parseInt(br.readLine());
		System.out.println("��� �� ��ǻ�Ͱ��а� �л��� ������ �������� ã�ư� ������.");
		recursion(0);
		System.out.print(ans.toString());
	}
	static void recursion(int count){
		for(int i=0;i<count;i++) {
			ans.append("____");
		}
		ans.append("\"����Լ��� ������?\"\n");
		if(count==target) {
			for(int i=0;i<count;i++) {
				ans.append("____");
			}
			ans.append("\"����Լ��� �ڱ� �ڽ��� ȣ���ϴ� �Լ����\"\n");
			for(int i=0;i<count;i++) {
				ans.append("____");
			}
		}
		else {
			for(int i=0;i<count;i++) {
				ans.append("____");
			}
			ans.append("\"�� ����. �������� �� �� ����⿡ �̼��� ��� ������ ����� ������ �־���.\n");
			for(int i=0;i<count;i++) {
				ans.append("____");
			}
			ans.append("���� ������� ��� �� ���ο��� ������ ������ �߰�, ��� �����Ӱ� ����� �־���.\n");
			for(int i=0;i<count;i++) {
				ans.append("____");
			}
			ans.append("���� ���� ��κ� �ǾҴٰ� �ϳ�. �׷��� ��� ��, �� ���ο��� �� ���� ã�ƿͼ� ������.\"\n");
			
			if(count<target)
				recursion(count+1);
			for(int i=0;i<count;i++) {
				ans.append("____");
			}
		}
		ans.append("��� �亯�Ͽ���.\n");
	}
}