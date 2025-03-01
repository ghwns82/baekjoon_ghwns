import sys
input = lambda : sys.stdin.readline().rstrip()

n, m,k = map(int,input().split())

# 선택된 정점 번호
selected = [-1] * (m+1)
graph,graph2 = [],[]
for i in range(n):
    graph.append(list(map(int,input().split()))[1:])
    graph2.append(graph[-1])
    

    
    
def bimatch(N):
    if visited[N]:
        return False                                      
    visited[N] = True                                     
                                                          
    for num in graph[N]:                                   
        if selected[num] == -1 or bimatch(selected[num]):         
            selected[num] = N
            return True                                   
    return False  



for i in range(n):            
    visited = [False] * (n)      
    bimatch(i)
result = 0               
for i in range(1,m+1):  
    if selected[i] >= 0:         
        result += 1 

graph += graph2
selected = [-1] * (m+1)
for i in range(2*n):            
    visited = [False] * (n*2)      
    bimatch(i)
    
result2 = 0               
for i in range(1,m+1):  
    if selected[i] >= 0:         
        result2 += 1 
        
print(result+min(result2-result,k))