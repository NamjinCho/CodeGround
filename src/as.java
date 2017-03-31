import java.util.ArrayList;

public class as {

	public static void main(String[]args)
	{
		ArrayList<Boolean> as = new ArrayList<>();
		
		as.add(true);
		as.add(false);
		as.add(false);
		as.remove(1);
		as.add(1,true);
		
		System.out.println(as.get(1));
	}
}
