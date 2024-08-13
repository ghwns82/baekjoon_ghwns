/////////////////////////////////////////////////////////////////////////////////////////////
// 기본 제공코드는 임의 수정해도 관계 없습니다. 단, 입출력 포맷 주의
// 아래 표준 입출력 예제 필요시 참고하세요.
// 표준 입력 예제
// int a;
// double b;
// char g;
// String var;
// long AB;
// a = sc.nextInt();                           // int 변수 1개 입력받는 예제
// b = sc.nextDouble();                        // double 변수 1개 입력받는 예제
// g = sc.nextByte();                          // char 변수 1개 입력받는 예제
// var = sc.next();                            // 문자열 1개 입력받는 예제
// AB = sc.nextLong();                         // long 변수 1개 입력받는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
// 표준 출력 예제
// int a = 0;                            
// double b = 1.0;               
// char g = 'b';
// String var = "ABCDEFG";
// long AB = 12345678901234567L;
//System.out.println(a);                       // int 변수 1개 출력하는 예제
//System.out.println(b); 		       						 // double 변수 1개 출력하는 예제
//System.out.println(g);		       						 // char 변수 1개 출력하는 예제
//System.out.println(var);		       				   // 문자열 1개 출력하는 예제
//System.out.println(AB);		       				     // long 변수 1개 출력하는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
{private static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer tokens;
	private static StringBuilder output = new StringBuilder();
	static int p1_win;
	static int p2_win;

	public static void recursion(boolean[] visited, int[] p1, Object[] p2, int index, int score1, int score2) {
		if (index == 9) {
			if (score1 > score2)
				p1_win += 1;
			else if (score1 < score2)
				p2_win += 1;
			return;
		}
		for (int i = 0; i < p2.length; i++) {
			if (!visited[i]) {
				visited[i]=true;
				if (p1[index] > (int) p2[i]) {
					recursion(visited, p1, p2, index + 1, score1 + p1[index] + (int) p2[i], score2);
				} else if (p1[index] < (int) p2[i]) {
					recursion(visited, p1, p2, index + 1, score1, score2 + p1[index] + (int) p2[i]);
				}
				visited[i]=false;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(input.readLine());
		int tc = Integer.parseInt(tokens.nextToken());
		int arr[] = new int[9];
		Set<Integer> arr2;
		for (int t = 0; t < tc; t++) {
			tokens = new StringTokenizer(input.readLine());
			arr2 = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18));
			for (int i = 0; i < 9; i++) {
				arr[i] = Integer.parseInt(tokens.nextToken());
				arr2.remove(arr[i]);
			}

			p1_win = 0;
			p2_win = 0;
			recursion(new boolean[9], arr, arr2.toArray(), 0, 0, 0);
			output.append("#").append(t + 1).append(" ").append(p1_win).append(" ").append(p2_win).append("\n");
		}
		
		System.out.println(output);

	}
}