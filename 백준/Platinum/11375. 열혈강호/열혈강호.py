import sys
input = lambda : sys.stdin.readline().rstrip()

n, m = map(int,input().split())

# 선택된 정점 번호
selected = [-1] * (m+1)

graph = [list(map(int,input().split()))[1:] for i in range(n)]

def bimatch(N):
    # print('visited',visited)                                           
    if visited[N]:
        # print(f'visited[{N}]')                                        
        return False                                      
    visited[N] = True                                     
                                                          
    for num in graph[N]:                                   
        if selected[num] == -1 or bimatch(selected[num]):         
            selected[num] = N
            # print(f'for {num} in graph[{N}]')                                
            return True                                   
    # print(f'for graph[{N}] else')                                                          
    return False  

for i in range(n):            
    visited = [False] * (n)      
    bimatch(i)
    # print('final visited',visited)                                           
    # print('selected',selected)
    
result = 0               
for i in range(1,m+1):  
    if selected[i] >= 0:         
        result += 1 
        
print(result)