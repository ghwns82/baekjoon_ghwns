from collections import defaultdict

def solution(edges):
    graph_in = defaultdict(list)
    graph_out = defaultdict(list)
    points = set()
    for a,b in edges:
        graph_out[a].append(b)
        graph_in[b].append(a)
        points.add(a)
        points.add(b)

    
    created = 0
    for dot_number in points:
        if dot_number not in graph_out and dot_number not in graph_in:
            break
        if len(graph_out[dot_number])>=2 and dot_number not in graph_in:
            if created==0 or len(graph_out[created])<len(graph_out[dot_number]):
                created = dot_number
                break
    
    
    result = [created,0,0,0]            
    for start in graph_out[created]:
        next = start
        while True:
            if len(graph_out[next]) == 2:
                result[3]+=1
                break
    
            elif len(graph_out[next]) == 1:
                next=graph_out[next][0]
                if next == start: # 도넛
                    result[1]+=1
                    break
            elif not graph_out[next]: # 막대
                result[2]+=1
                break
                
    return result