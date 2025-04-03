def solution(s):
    s = s.replace('{', '[').replace('}', ']')
    sets = eval(s)
    sets.sort(key=len)

    answer = []
    seen = set()
    for part in sets:
        for num in part:
            if num not in seen:
                seen.add(num)
                answer.append(num)
                break
    return answer
