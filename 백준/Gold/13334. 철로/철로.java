import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer token;
	
	public static void main(String[] args) throws Exception {
		token = new StringTokenizer(input.readLine());
		int n = Integer.parseInt(token.nextToken());
		Set<Integer> check = new HashSet<Integer>();
		PriorityQueue<int[]> arr = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[0], o2[0]);  // 첫 번째 원소를 기준으로 정렬
            }
        });
		PriorityQueue<Integer> hq = new PriorityQueue<>();
		int result = 0;
		
		for (int i = 0; i < n; i++) {
			token = new StringTokenizer(input.readLine());
			int a = Integer.parseInt(token.nextToken());
			int b = Integer.parseInt(token.nextToken());
			int c = Math.min(a, b);
			int d = Math.max(a,b);
			check.add(c);
			arr.add(new int[] {d,c});
		}
		token = new StringTokenizer(input.readLine());
		int d = Integer.parseInt(token.nextToken());
		Object[] tmp = check.toArray();
		Arrays.sort(tmp);
		
		for (Object p : tmp) {
			int ps = (int)p;
			int pe = ps+d;
			while (arr.size()>0) {
				int e = arr.peek()[0];
				int s = arr.peek()[1];
				if (e<=pe) {
					int tmp2 = arr.poll()[1];
					hq.add(tmp2);
					continue;
				}
				if (s==(int)ps && e>pe) {
					arr.poll();
					continue;
				}
				break;
			}
//			System.out.println(ps+" "+pe);
//			System.out.println(Arrays.toString(hq.toArray()));
			while (hq.size()>0 && hq.peek()<ps)
				hq.poll();
			result = Math.max(result, hq.size());
			
		}
		System.out.println(result);
		
	}
}