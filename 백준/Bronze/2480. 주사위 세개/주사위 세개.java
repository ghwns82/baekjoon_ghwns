import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.InputStreamReader;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
    String line = rd.readLine();
    StringTokenizer tokens = new StringTokenizer(line);
    StringBuilder output = new StringBuilder();
    int a = Integer.parseInt(tokens.nextToken());
    int b = Integer.parseInt(tokens.nextToken());
    int c = Integer.parseInt(tokens.nextToken());

    int num=0;
    if(a==b&&b==c){
      num = 10000+a*1000;
    }else if (a!=b && a!=c && b!=c){
      int max = Math.max(a, Math.max(b,c));
      num = max*100;
  }else{
      if (a==b||a==c){
        num = 1000+a*100;
      }
      else{
        num= 1000+b*100;
      }
    }
    output.append(num);
    System.out.println(output);
  }

}