
public class fib_checker {
	int n;
	
	public fib_checker()
	{
		n=0;
	}
	
	private boolean fibcheck(int[] fib_list)
	{
		boolean fib_rule = false;
		while(n != fib_list.length)
		{
			if((fib_list[n] + fib_list[n+1]) == fib_list[n+2])
			{
				n++;
				if((n+2) == (fib_list.length-1))
				{fib_rule =true;
					break;}//Breaks the loop as it has reached the end of the loop
			}
			else 
				n=0;
				return false;
		}
		n=0;
		return true;
	}
	
	private boolean fibcheck2(int[] fib_list)
	{
		boolean fib_true = false;
		int[] fib_xcheck = new int[fib_list.length];
		for(int x=0;x!=fib_list.length;x++)
		{
			if(x==0)
			{
				fib_xcheck[x] = x ;
			}
			if (x==1)
			{
				fib_xcheck[x] = x;
			}
			else
			{
				fib_xcheck[x]= fib_xcheck[x-1] + fib_xcheck[x-2];
				System.out.println("Check "+fib_xcheck[x]);
			}
				
		}
		for(int i:fib_xcheck)
			{System.out.print(i + ",");}
		
		while(fib_true!=true)
		{
			while(true)
			{
				if (fib_list[n] == fib_xcheck[n])
				{
					if (n==(fib_list.length -1) )
					{
						System.out.println(true);
						return true;
					}
					
						n++;
				}
				else;
				System.out.println(false);
				return false;
			}
		}	
	return true; 		
	}

	public static void main(String[] argv)
	{
		fib_checker fc=new fib_checker();
		int[] testcase1 = {0,1,1,2,3,5,8,13};
		int[] testcase2 = {12,13,25,38,63,101};
		fc.fibcheck(testcase1);
		fc.fibcheck2(testcase1);
	}
}
