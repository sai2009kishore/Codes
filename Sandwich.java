public class Sandwich
{
  public String getSandwich(String str) 
  {
    int cnt = count(str, "bread");
    if(cnt <=1)
      return "";

    return rev(breakIt(rev(breakIt(str, "bread")), "daerb"));
  }

  public String breakIt(String str, String sub)
  {
    int in = str.indexOf(sub);
    return str.substring(in+sub.length(), str.length());
  }

  public String rev(String str)
  {
    String re = "";
    for(int i=str.length()-1;i>=0;i--)
      re += str.charAt(i)+"";
    return re;
  }

  public int count(String str, String sub)
  {
    int count = 0;
    for(int i=0;i<str.length()-4;i++)
      if(str.substring(i, i+5).equals("bread"))
        count++;
    return count;
  }
}
