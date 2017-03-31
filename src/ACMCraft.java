import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.io.FileInputStream;

/* ����ϴ� Ŭ�������� Solution �̾�� �ϸ�, ������ Solution.java �� ����� ���� �����մϴ�.
   �̷��� ��Ȳ������ �����ϰ� java Solution ������� ���α׷��� ������ �� �� �ֽ��ϴ�. */

class ACMCraft {
			public static void main(String args[]) throws Exception	{
			/* �Ʒ� �޼ҵ� ȣ���� ������ ǥ���Է�(Ű����) ��� sample_input.txt ���Ϸ� ���� �о���ڴٴ� �ǹ��� �ڵ��Դϴ�.
			   ���� ������ PC ���� �׽�Ʈ �� ����, �Է°��� sample_input.txt�� ������ �� �� �ڵ带 ù �κп� ����ϸ�,
			   ǥ���Է� ��� sample_input.txt ���Ϸ� ���� �Է°��� �о� �� �� �ֽ��ϴ�.
			   ����, ���� PC���� �Ʒ� �޼ҵ带 ������� �ʰ� ǥ���Է��� ����Ͽ� �׽�Ʈ�ϼŵ� �����մϴ�.
			   ��, Codeground �ý��ۿ��� "�����ϱ�" �� ������ �ݵ�� �� �޼ҵ带 ����ų� �ּ�(//) ó�� �ϼž� �մϴ�. */
			Scanner sc = new Scanner(new FileInputStream("acm.txt"));
			//Scanner sc = new Scanner(System.in);

			int T;
			int test_case;

			T = Integer.parseInt(sc.nextLine().trim());
			class Node{
				int index;
				int myCost;
				ArrayList<Integer> end;
				ArrayList<Integer> cost;
			}
			
			for(test_case = 1; test_case <= T; test_case++) {
				// �� �κп��� �˰��� ���α׷��� �ۼ��Ͻʽÿ�. �⺻ ������ �ڵ带 ���� �Ǵ� �����ϰ� ������ �ڵ带 ����ϼŵ� �˴ϴ�.
	        
				ArrayList<Node> nodes=new ArrayList<>();
				String []elements=sc.nextLine().split(" ");
				int count = 0;
				int node_size = Integer.parseInt(elements[0]);
				int path_size = Integer.parseInt(elements[1]);
				String []eachCost = sc.nextLine().split(" ");
				
				for(int i=1;i<=node_size;i++)
				{
					Node node = new Node();
					node.index = i;
					node.end=new ArrayList<>();
					node.cost=new ArrayList<>();
					node.myCost = Integer.parseInt(eachCost[i-1]);
					nodes.add(node);
				}
				
				for(int i=0;i<path_size;i++)
				{
					String line  = sc.nextLine();
					String []path_elem=line.split(" ");
					int start = Integer.parseInt(path_elem[0]);
					int end = Integer.parseInt(path_elem[1]);
					
					int cost = Integer.parseInt(eachCost[start-1]);
					nodes.get(end-1).end.add(start);
					nodes.get(end-1).cost.add(cost);
				}
					int dist[] = new int[node_size];
				
					String trip_path=sc.nextLine().trim();
					int start = Integer.parseInt(trip_path);
					int end = 1;
					//��������Ʈ��
					
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
								dist[j]=Integer.MIN_VALUE;
							visit.add(j,false);
						}
					}
					
					while(visit.contains(false))
					{
						int index= findMinIndex(dist,visit);
						if(index == 0)
							break;
						visit.remove(index);
						visit.add(index,true);
						
						for(int c = 0;c<nodes.get(index).end.size();c++)
						{
							int newDist = dist[index] + nodes.get(index).cost.get(c);
							
							if(newDist > dist[nodes.get(index).end.get(c)-1])
								dist[nodes.get(index).end.get(c)-1]=newDist;
						
						}
					}
		
				
				// �� �κп��� ������ ����Ͻʽÿ�.
				System.out.println("Case #" + test_case);
				System.out.println((dist[0] + nodes.get(start-1).myCost));
			}
		}
		
		public static int findMinIndex(int arr[],ArrayList<Boolean> visit)
		{
			int max=Integer.MIN_VALUE;
			int index=-1;
			for(int i=0;i<arr.length;i++)
			{
				if(max<arr[i] && index !=i && visit.get(i)==false)
				{
					max=arr[i];
					index=i;
				}
			}
			return index;
		}
	}
