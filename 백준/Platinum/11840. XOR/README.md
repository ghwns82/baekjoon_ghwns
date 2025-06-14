# [Platinum II] XOR - 11840 

[문제 링크](https://www.acmicpc.net/problem/11840) 

### 성능 요약

메모리: 275436 KB, 시간: 1068 ms

### 분류

자료 구조, 트리, 트라이

### 제출 일자

2025년 6월 15일 03:29:32

### 문제 설명

<p>You are given numbers N, x and a sequence of N numbers. Find the largest possible interval of consequently following elements, such that "xor"of these elements is not less than x. I.e., more formally, find such i and k that</p>

<p>a<sub>i</sub> ⊕ a<sub>i+1</sub> ⊕ · · · ⊕ a<sub>i+k−1</sub> ≥ x, 1 ≤ i ≤ i + k − 1 ≤ N, and k is largest possible positive number.</p>

<p>It's guaranteed that for each test from the testset such an interval exists.</p>

<p>We remind you that xor(⊕) operation is applied to numbers in binary representation, so that for each pair of bits the following is true:</p>

<ul>
	<li>0 ⊕ 0 = 0</li>
	<li>0 ⊕ 1 = 1</li>
	<li>1 ⊕ 0 = 1</li>
	<li>1 ⊕ 1 = 0</li>
</ul>

<p>The result of this operation doesn't depend on the order of operands a⊕b = b⊕a. Moreover (a⊕(a⊕b)) = b.</p>

<p>In Pascal this operation is represented as xor. In C/C++/Java as ∧.</p>

### 입력 

 <p>The first line of input contains N (1 ≤ N ≤ 250 000) and x (0 ≤ x ≤ 1 000 000 000). The second line of input contains N non-negative numbers not exceeding 10<sup>9</sup>.</p>

### 출력 

 <p>The first line of output must contain two numbers: i and k. In case of many solutions output the one with the smallest i.</p>

