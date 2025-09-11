
def solution(n, m, x, y, r, c, k):
    dis = abs(x-r)+abs(y-c)
    if dis%2 != k%2 or dis>k:
        return 'impossible'
    
    answer = []
    d,l,r,u,o = max(0,r-x), max(0,y-c),max(0,c-y),max(0,x-r), (k-dis)//2

    for i in range(k):
        if x<n and d:
            answer.append('d')
            x+=1
            d-=1
            continue
        if x<n and o:
            answer.append('d')
            x+=1
            u+=1
            o-=1
            continue
        
        if y>1 and o:
            answer.append('l')
            y-=1
            r+=1
            o-=1
            continue
        if y>1 and l:
            answer.append('l')
            y-=1
            l-=1
            continue
            
        if y<m and r:
            answer.append('r')
            y+=1
            r-=1
            continue  
        if y<m and o:
            answer.append('r')
            y+=1
            l+=1
            o-=1
            continue
        if x>1 and u:
            answer.append('u')
            x-=1
            u-=1
            continue
        if x>1 and o:
            answer.append('u')
            x-=1
            d+=1
            o-=1
            continue

        
    
                
                
            

    
        
    return ''.join(answer)