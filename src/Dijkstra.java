import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.io.FileInputStream;

/* 사용하는 클래스명이 Solution 이어야 하며, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해 볼 수 있습니다. */

class Dijkstra {
			public static void main(String args[]) throws Exception	{
			/* 아래 메소드 호출은 앞으로 표준입력(키보드) 대신 sample_input.txt 파일로 부터 읽어오겠다는 의미의 코드입니다.
			   만약 본인의 PC 에서 테스트 할 때는, 입력값을 sample_input.txt에 저장한 후 이 코드를 첫 부분에 사용하면,
			   표준입력 대신 sample_input.txt 파일로 부터 입력값을 읽어 올 수 있습니다.
			   또한, 본인 PC에서 아래 메소드를 사용하지 않고 표준입력을 사용하여 테스트하셔도 무방합니다.
			   단, Codeground 시스템에서 "제출하기" 할 때에는 반드시 이 메소드를 지우거나 주석(//) 처리 하셔야 합니다. */
			Scanner sc = new Scanner(new FileInputStream("sample_input.txt"));
			//Scanner sc = new Scanner(System.in);

			int T;
			int test_case;

			T = Integer.parseInt(sc.nextLine().trim());
			class Node{
				int index;
				ArrayList<Integer> end;
				ArrayList<Integer> cost;
			}
			
			for(test_case = 1; test_case <= T; test_case++) {
				// 이 부분에서 알고리즘 프로그램을 작성하십시오. 기본 제공된 코드를 수정 또는 삭제하고 본인이 코드를 사용하셔도 됩니다.
	        
				ArrayList<Node> nodes=new ArrayList<>();
				String []elements=sc.nextLine().split(" ");
				int count = 0;
				int k = Integer.parseInt(elements[2]);
				int node_size = Integer.parseInt(elements[0]);
				int path_size = Integer.parseInt(elements[1]);
				for(int i=1;i<=node_size;i++)
				{
					Node node = new Node();
					node.index = i;
					node.end=new ArrayList<>();
					node.cost=new ArrayList<>();
					nodes.add(node);
				}
				for(int i=0;i<path_size;i++)
				{
					String []path_elem=sc.nextLine().split(" ");
					int start = Integer.parseInt(path_elem[0]);
					int end = Integer.parseInt(path_elem[1]);
					int cost = Integer.parseInt(path_elem[2]);
					
					nodes.get(start-1).end.add(end);
					nodes.get(start-1).cost.add(cost);
					
					nodes.get(end-1).end.add(start);
					nodes.get(end-1).cost.add(cost);
					
					
					
				}
				int trip_size = Integer.parseInt(sc.nextLine().trim());
				
				for(int i=0;i<trip_size;i++)
				{
					String []trip_path=sc.nextLine().split(" ");
					int start = Integer.parseInt(trip_path[0]);
					int end = Integer.parseInt(trip_path[1]);
					//다이제스트라
					int dist[] = new int[node_size];
					ArrayList<Boolean> visit = new ArrayList<>();
					for(int j=0;j<node_size;j++)
					{
						if(j+1 == start){
							dist[j]=0;
							visit.add(j,true);
						}
						else{
							if(nodes.get(start-1).end.contains(j+1))
								dist[j]=nodes.get(start-1).cost.get(nodes.get(start-1).end.indexOf(j+1));
							else
								dist[j]=Integer.MAX_VALUE;
							visit.add(j,false);
						}
					}
					
					while(visit.contains(false))
					{
						int index= findMinIndex(dist,visit);
						visit.remove(index);
						visit.add(index,true);
						
						for(int c = 0;c<nodes.get(index).end.size();c++)
						{
							int newDist = dist[index] + nodes.get(index).cost.get(c);
							
							if(newDist < dist[nodes.get(index).end.get(c)-1])
								dist[nodes.get(index).end.get(c)-1]=newDist;
						
						}
					}
					
					if(k < dist[end-1])
						count++;
					
				}
				
				// 이 부분에서 정답을 출력하십시오.
				System.out.println("Case #" + test_case);
				System.out.println(count);
			}
		}
		
		public static int findMinIndex(int arr[],ArrayList<Boolean> visit)
		{
			int min=Integer.MAX_VALUE;
			int index=-1;
			for(int i=0;i<arr.length;i++)
			{
				if(min>arr[i] && index !=i && visit.get(i)==false)
				{
					min=arr[i];
					index=i;
				}
			}
			return index;
		}
	}
