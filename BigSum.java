public class BigSum
{
    public static void main(String args[]) 
    {
        String a = args[0];
        String b = args[1];
        add(a, b);
    }
    
    public static void add(String a, String b)
    {
        int A = a.length();
        int B = b.length();
        int diff = Math.abs(A-B);
        int max = A>B?A:B;
        if(diff!=0)
        {
            String head = "";
            for(int i=0;i<diff;i++)
                head+="0";
            if(A>B)
                b = head+b;
            else
                a = head+a;
        }
        
        int sum[] = new int[max+1];
        for(int i=0;i<sum.length;i++)
            sum[i] = 0;
        
        a = rev(a);
        b = rev(b);


        for(int i=0;i<max;i++)
        {
            int temp = toInt(a, i) + toInt(b, i) + sum[i];
            
            if(temp > 9)
            {
                sum[i+1] = temp/10;
                temp %= 10;
            }
            
            sum[i] = temp;
        }

        for(int i=max;i>=0;i--)
            System.out.print(sum[i]);
    }
    

    public static int toInt(String s, int i)
    {
        return Integer.parseInt(s.charAt(i)+"");
    }
    
    public static String rev(String org)
    {
        String ret = "";
        for(int i=org.length()-1;i>=0;i--)
            ret += org.charAt(i)+"";
        return ret;
    }
    
    public static <E> void p(E e)
    {
        System.out.println(e);
    }
}
