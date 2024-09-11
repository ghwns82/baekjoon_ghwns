import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	private static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;
    private static StringBuilder output = new StringBuilder();
    
   
    
    public static void main(String[] args) throws IOException {
    	tokens = new StringTokenizer(input.readLine());
    	int n = Integer.parseInt(tokens.nextToken());
    	int arr[][] = new int[n][2];
    	PriorityQueue<int []> hq1;
    	PriorityQueue<Integer> hq2;
    	int lastTime, outTime;
    	int result[] = new int[n];
    	int index=0;
    	int max_v = 0;
    	for (int i = 0; i < n; i++) {
    		tokens = new StringTokenizer(input.readLine());
    		arr[i][0] = Integer.parseInt(tokens.nextToken());
    		arr[i][1] = Integer.parseInt(tokens.nextToken());
		}
    	Arrays.sort(arr, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0]!=o2[0] ? o1[0]-o2[0] : o1[1]-o2[1];
			}
		});
    	hq1 = new PriorityQueue<>(new Comparator<int []>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0]!=o2[0] ? o1[0]-o2[0] : o1[1]-o2[1];
			}
		});
    	hq2 = new PriorityQueue<>();
    	for (int i = 0; i < n; i++) {
			hq2.add(i);
		}
    	
    	
    	
    	lastTime = arr[0][0];
    	outTime = arr[0][1];
    	hq1.add(new int[]{outTime, hq2.poll()});
    	result[0]++;
    	for (int i = 1; i < n; i++) {
    		int a = arr[i][0];
    		int b = arr[i][1];
    		while ((hq1.size()>0) && (a>hq1.peek()[0])) {
				index = hq1.poll()[1];
				hq2.add(index);
			}
			index = hq2.poll();
			
			max_v = Math.max(max_v, index);
			result[index]+=1;
			hq1.add(new int[]{b, index});
		}
    	
    	
    	output.append(max_v+1).append('\n');
    	for (int i = 0; i <= max_v; i++) {
			output.append(result[i]).append(" ");
		}
    	System.out.println(output);
    	
	}
}