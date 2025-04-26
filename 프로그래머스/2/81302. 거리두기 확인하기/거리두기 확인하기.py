def check(place):
    print()
    direction = [[0,1],[1,0],[0,-1],[-1,0]]
    for i in range(5):
        for j in range(5):
            pcnt = 0
            for a,b in direction:
                dx,dy = i+a,j+b
                if 0<=dx<5 and 0<=dy<5 and place[dx][dy]=='P':
                    pcnt+=1
            print([i,j],pcnt)
            if place[i][j]=='P' and pcnt or pcnt>1 and place[i][j]=='O':
                return 0
    return 1
            

def solution(places):
    answer = [check(place) for place in places]
    return answer