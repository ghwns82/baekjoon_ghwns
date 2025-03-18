def solution(n, info):
    info = info[::-1]
    opposite_score = 0
    for i in range(11):
        if info[i]:
            opposite_score += i
            
    answer = [-1]
    how_to_do = []
    result = 0
    
    stack = [(-1, n ,0,0,opposite_score,)] # 작업완료된인덱스, 나머지화살수,직전에사용한화살수, 내점수, 어피치점수
    
    while stack:
        last_index, remain,used_cnt, my_score, your_score = stack.pop()
        while -1<last_index < len(how_to_do):
            how_to_do.pop()
        if last_index == len(how_to_do):
            how_to_do.append(used_cnt)
            
        next_index = last_index + 1
        if next_index>10 or remain==0:
            if my_score - your_score > result:
                result = my_score -your_score
                answer = how_to_do+[0]*(11-len(how_to_do))

            continue

        for use in range(remain+1):
            next_my_score, next_your_score = my_score, your_score
            if info[next_index] < use:
                next_my_score += next_index
                if info[next_index]:
                    next_your_score -= next_index
            
            stack.append([next_index, remain - use,use, next_my_score, next_your_score])
        
    
    
    return answer[::-1]