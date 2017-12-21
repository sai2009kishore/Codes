import java.util.Scanner;

/**
* Calculates sum and product of large numbers
* Created by Sai Kishore on 12/20/2017.
*/

class BigMultiply
{
	// main method
	public static void main(String args[])
	{
		Scanner scanner = new Scanner(System.in);
		String firstNum = scanner.next();
		String secondNum = scanner.next();
		p(multiplyPoint(firstNum, secondNum));
	}
	
	/**
	* Multiplies the two numbers digit by digit
	* @param firstNum - The first number
	* @param secondNum - the second number
	* @return - Product of the given numbers
	*/
	private static String multiplyPoint(String firstNum, String secondNum)
	{
		if(firstNum.contains(".") || secondNum.matches("."))
		{
			int decimals = 0;
			String temp = "";
			if(firstNum.contains("."))
			{
				decimals += firstNum.length() - firstNum.indexOf(".") - 1;
				for(int i=0;i<firstNum.length();i++)
					if(firstNum.charAt(i) != '.')
						temp += firstNum.charAt(i);
				firstNum = temp;
			}
			temp = "";
			if(secondNum.contains("."))
			{
				decimals += secondNum.length() - secondNum.indexOf(".") - 1;
				for(int i=0;i<secondNum.length();i++)
					if(secondNum.charAt(i) != '.')
						temp += secondNum.charAt(i);
				secondNum = temp;
			}
			String result = multiply(firstNum, secondNum);
			result = result.substring(0, result.length()-decimals) + "." + result.substring(result.length()-decimals);
			return rev(trim(rev(trim(result))));
		}
		else
			return trim(multiply(firstNum, secondNum));
	}

	/**
	* Multiplies the two numbers digit by digit
	* @param firstNum - The first number
	* @param secondNum - the second number
	* @return - Product of the given numbers
	*/
	private static String multiply(String firstNum, String secondNum)
	{
		String result = "0";
		if(firstNum.equals("0") || secondNum.equals("0"))
			return "0";
		if(firstNum.equals("1"))
			return secondNum;
		if(secondNum.equals("1"))
			return firstNum;
		int length1 = firstNum.length();
		int length2 = secondNum.length();
		String max = length1>length2 ? firstNum : secondNum;
		String min = length1>length2 ? secondNum : firstNum;

		max = rev(max);
		min = rev(min);

		length1 = max.length();
		length2 = min.length();

		String muls[] = new String[length2];
		for(int i=0;i<muls.length;i++)
			muls[i] = "";


		for(int i=0;i<length2;i++)
		{
			int carry[] = new int[length1+1];
			for(int c=0;c<carry.length;c++)
				carry[c] = 0;
			int temp;
			for(int j=0;j<length1;j++)
			{
				temp = ( toInt(max, j) * toInt(min, i) ) + carry[j];
				if(temp > 10)
				{
					carry[j+1] = temp/10;
					temp = temp % 10;
				}
				muls[i] += temp;
			}
			muls[i] = getZeros(i) + muls[i] + carry[length1];
		}

		for(int i=0;i<muls.length;i++)
			muls[i] = rev(muls[i]);

		for(String mul : muls)
			result = add(result, mul);

		return (result);
	}

	/**
	* Provides the specified number of zeroes in string form
	* @param num - The required number of zeroes
	* @return - The string with num zeroes
	*/
	private static String getZeros(int num)
	{
		String temp = "";
		for(int i=0;i<num;i++)
			temp += "0";
		return temp;
	}

	/**
	* Adds two numbers digit by digit
	* @param a - The first number
	* @param b - The second number
	* @return - the sum of two numbers
	*/
	private static String add(String a, String b)
	{
		String result = "";
		int A = a.length();
		int B = b.length();
		int diff = Math.abs(A-B);
		int max = A>B?A:B;
		if(diff!=0)
		{
			String head = getZeros(diff);
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
			result += sum[i];

		return (result);
	}

	/**
	* Converts the char at a particular location in a string to int
	* @param s - The string whose char is tp be converted
	* @param i - The position of the character
	* @return - The number at given position
	*/
	private static int toInt(String s, int i)
	{
		return Integer.parseInt(s.charAt(i)+"");
	}

	/**
	* Provides the reverse of given string
	* @param org - The string to be reversed
	* @return - The reversed string of org
	*/
	private static String rev(String org)
	{
		String ret = "";
		for(int i=org.length()-1;i>=0;i--)
			ret += org.charAt(i)+"";
		return ret;
	}

	/**
	* Trims the leading zeroes of a number
	* @param str - The number in String form
	* @return - The number without leading zeroes
	*/
	private static String trim(String str)
	{
		if(!str.startsWith("0"))
			return str;

		int i = 0;
		while(i<str.length() && str.charAt(i) == '0')
			i++;
		
		if(i == str.length())
			return "0";
		
		if(str.charAt(i) == '.')
			return "0"+str.substring(i);
		
		return str.substring(i);
	}

	/**
	* Generic print statement
	* @param e - Any object or data-type to be printed
	*/
	static <E> void p(E e)
	{
		System.out.println(e);
	}
}
