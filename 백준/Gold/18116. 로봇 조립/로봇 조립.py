import sys
input = lambda: sys.stdin.readline().strip()

MAX_VALUE = 10**6+1


parent=list(range(MAX_VALUE))
cnt_list = [1]*MAX_VALUE

def find(x):
    if parent[x] != x:
        parent[x] = find(parent[x])
    return parent[x]
    
        
    

def union(a,b):
    ra, rb = find(a), find(b)
    if ra==rb:
        return
    # print('ra,rb',ra,rb)
    min_idx, max_idx = min(ra,rb), max(ra,rb)
    
    cnt_list[min_idx] += cnt_list[max_idx]
    cnt_list[max_idx] = 0
    
    parent[max_idx] = min_idx
    
    # print('parent',parent)
    # print('cnt',cnt_list)
    find(a), find(b)
    
for _ in range(int(input())):
    cmd = input().split()
    if cmd[0] == 'I':
        union(int(cmd[1]), int(cmd[2]))
    else:
        print(cnt_list[find(int(cmd[1]))])
        
    # print('parent',parent)
    # print('cnt',cnt_list)
