import sys
input = lambda: sys.stdin.readline().strip()

n = int(input())
unsorted_runner = []
for i in range(n):
    unsorted_runner.append([i,int(input())])
unsorted_runner.sort(key=lambda x : x[1])

runner = [0]*n
num = 0
for i,v in unsorted_runner:
    runner[i] = num
    num+=1

# unsorted_runner.sort(key=lambda x : x[0])
unit = int(n**0.5)+1
cnt_table = [0]*n
partial_cnt_table =[0]*(n//unit+1)

for i in range(n):
    value = runner[i]
    result = 0
    for j in range((value-1)//unit):
        result += partial_cnt_table[j]
    for j in range(max((value-1)//unit * unit,0),value):
        result += cnt_table[j]
    print(i - result+1)
    
    cnt_table[value]+=1
    partial_cnt_table[value//unit]+=1