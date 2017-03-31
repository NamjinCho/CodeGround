import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileInputStream;

/* ����ϴ� Ŭ�������� Solution �̾�� �ϸ�, ������ Solution.java �� ����� ���� �����մϴ�.
   �̷��� ��Ȳ������ �����ϰ� java Solution ������� ���α׷��� ������ �� �� �ֽ��ϴ�. */

class TwoColor {
	public static void main(String args[]) throws Exception	{
		/* �Ʒ� �޼ҵ� ȣ���� ������ ǥ���Է�(Ű����) ��� sample_input.txt ���Ϸ� ���� �о���ڴٴ� �ǹ��� �ڵ��Դϴ�.
		   ���� ������ PC ���� �׽�Ʈ �� ����, �Է°��� sample_input.txt�� ������ �� �� �ڵ带 ù �κп� ����ϸ�,
		   ǥ���Է� ��� sample_input.txt ���Ϸ� ���� �Է°��� �о� �� �� �ֽ��ϴ�.
		   ����, ���� PC���� �Ʒ� �޼ҵ带 ������� �ʰ� ǥ���Է��� ����Ͽ� �׽�Ʈ�ϼŵ� �����մϴ�.
		   ��, Codeground �ý��ۿ��� "�����ϱ�" �� ������ �ݵ�� �� �޼ҵ带 ����ų� �ּ�(//) ó�� �ϼž� �մϴ�. */
		Scanner sc = new Scanner(new FileInputStream("input_sample.txt"));
        
		//Scanner sc = new Scanner(System.in);

		int T;
		int test_case;

		T = Integer.parseInt(sc.nextLine().trim());
		
		
		
		for(test_case = 1; test_case <= T; test_case++) {
			// �� �κп��� �˰��� ���α׷��� �ۼ��Ͻʽÿ�. �⺻ ������ �ڵ带 ���� �Ǵ� �����ϰ� ������ �ڵ带 ����ϼŵ� �˴ϴ�.
			ArrayList<Node> nodes=new ArrayList<>();
			String line=sc.nextLine();
			int count = 0;
			int node_size = Integer.parseInt(line);
			line=sc.nextLine();
			int path_size = Integer.parseInt(line);
			for(int i=0;i<node_size;i++)
			{
				Node node = new Node();
				node.index = i;
				node.color = 0;
				node.connectedVertex=new ArrayList<>();
				nodes.add(node);
			}
			for(int i=0;i<path_size;i++)
			{
				line = sc.nextLine();
				String []path_elem = line.split(" ");
				int start=Integer.parseInt(path_elem[0]);
				int end = Integer.parseInt(path_elem[1]);
				nodes.get(start-1).connectedVertex.add(end-1);
				nodes.get(end-1).connectedVertex.add(start-1);
			}
			int result=0;
			if(twoColor(nodes,0))
				result = 1;
			else
				result = 0;
			// �� �κп��� ������ ����Ͻʽÿ�.
			System.out.println("Case #" + test_case);
			System.out.println(result);
		}
	}
	public static boolean twoColor(ArrayList<Node> arr , int index)
	{
		int cVSize = arr.get(index).connectedVertex.size();
		int myColor = arr.get(index).color;
		boolean flag = false;
		for(int i=0;i<cVSize;i++)
		{
			int nextIndex = arr.get(index).connectedVertex.get(i);
			int nextColor = arr.get(nextIndex).color;
			if(myColor==0)
			{
				myColor = 1;
				for(int j=0;j<arr.get(index).connectedVertex.size();j++){
					
					int nextIndex2 = arr.get(index).connectedVertex.get(j);
					int nextColor2 = arr.get(nextIndex2).color;
					if(nextColor2==myColor && !flag)
					{
						if(nextColor2==1)
							myColor=2;
						else
							myColor=1;
						arr.get(index).color=myColor;
						flag=!flag;
					}
					else if(nextColor2!=0 && !flag)
						flag=!flag;
					else if(nextColor2==myColor && flag)
						return false;
				}
				arr.get(index).color=myColor;
				
			}
			if (nextColor==0&&!twoColor(arr,nextIndex))
				return false;
			
			
		}
		
		return true;
		
	}
	public static class Node{
		int index;
		int color;
		ArrayList<Integer> connectedVertex;
	}
}