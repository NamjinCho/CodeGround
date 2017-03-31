import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileInputStream;

/* 사용하는 클래스명이 Solution 이어야 하며, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해 볼 수 있습니다. */

class TwoColor {
	public static void main(String args[]) throws Exception	{
		/* 아래 메소드 호출은 앞으로 표준입력(키보드) 대신 sample_input.txt 파일로 부터 읽어오겠다는 의미의 코드입니다.
		   만약 본인의 PC 에서 테스트 할 때는, 입력값을 sample_input.txt에 저장한 후 이 코드를 첫 부분에 사용하면,
		   표준입력 대신 sample_input.txt 파일로 부터 입력값을 읽어 올 수 있습니다.
		   또한, 본인 PC에서 아래 메소드를 사용하지 않고 표준입력을 사용하여 테스트하셔도 무방합니다.
		   단, Codeground 시스템에서 "제출하기" 할 때에는 반드시 이 메소드를 지우거나 주석(//) 처리 하셔야 합니다. */
		Scanner sc = new Scanner(new FileInputStream("input_sample.txt"));
        
		//Scanner sc = new Scanner(System.in);

		int T;
		int test_case;

		T = Integer.parseInt(sc.nextLine().trim());
		
		
		
		for(test_case = 1; test_case <= T; test_case++) {
			// 이 부분에서 알고리즘 프로그램을 작성하십시오. 기본 제공된 코드를 수정 또는 삭제하고 본인이 코드를 사용하셔도 됩니다.
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
			// 이 부분에서 정답을 출력하십시오.
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