# [Platinum I] 수열과 쿼리 23 - 16979 

[문제 링크](https://www.acmicpc.net/problem/16979) 

### 성능 요약

메모리: 163240 KB, 시간: 9372 ms

### 분류

자료 구조, 세그먼트 트리, 오프라인 쿼리, 값 / 좌표 압축, mo's

### 제출 일자

2025년 7월 4일 03:41:52

### 문제 설명

<p>길이가 N인 수열 A<sub>1</sub>, A<sub>2</sub>, ..., A<sub>N</sub>이 주어진다. 이때, 다음 쿼리를 수행하는 프로그램을 작성하시오.</p>

<ul>
	<li><code>s e</code>: s ≤ i < j ≤ e 이면서 A<sub>i</sub> > A<sub>j</sub>인 (i, j) 쌍의 개수를 출력한다. (1 ≤ s ≤ e ≤ N)</li>
</ul>

### 입력 

 <p>첫째 줄에 수열의 크기 N (1 ≤ N ≤ 100,000), 쿼리의 개수 M (1 ≤ M ≤ 100,000)이 주어진다.</p>

<p>둘째 줄에는 A<sub>1</sub>, A<sub>2</sub>, ..., A<sub>N</sub>이 주어진다. (1 ≤ A<sub>i</sub> ≤ 1,000,000,000)</p>

<p>셋째 줄부터 M개의 줄에는 쿼리가 한 줄에 하나씩 주어진다.</p>

### 출력 

 <p>모든 쿼리마다 답을 출력한다.</p>

