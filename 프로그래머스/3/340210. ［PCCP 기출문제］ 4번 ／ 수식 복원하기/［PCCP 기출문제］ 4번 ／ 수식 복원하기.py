def c2n(unit,num):
    d=1
    result=0
    for i in range(len(num)-1,-1,-1):
        if int(num[i]) >=unit:
            return -1
        result += int(num[i])*d
        d*=unit
    return result
def n2c(unit,num):
    d=1
    result=[]
    while num>=d:
        d*=unit
    d//=unit
    while d>0:
        if num//d >=unit:
            return -1
        result.append(str(num//d))
        num%=d
        d//=unit
    if result:
        return ''.join(result)
    else:
        return '0'


def solution(expressions):
    units = list(range(2,10))
    answer = []
    
    for ex in expressions:
        if ex[-1]=='X':
            answer.append(ex[:-1])
        next = []
        data = ex.split()
        for unit in units:
            a = c2n(unit, data[0])
            b = c2n(unit, data[2])
            if a<0 or b<0:
                continue
            if ex[-1]=='X':
                next.append(unit)
                continue
            c = c2n(unit, data[4])
            if data[1]=="+":
                d=a+b
            else:
                d=a-b
            if d==c:
                next.append(unit)
        units = next
    print(units)
    for i,ex in enumerate(answer):
        data = ex.split()
        tmp = set()
        for unit in units:
            a = c2n(unit, data[0])
            b = c2n(unit, data[2])
            if a<0 or b<0:
                continue
            if data[1]=="+":
                d=a+b
            else:
                d=a-b
            r=n2c(unit,d)
            if r==-1:
                continue
            tmp.add(r)
        if len(tmp)!=1:
            answer[i]+='?'
        else:
            answer[i]+=list(tmp)[0]
        
    
    
    return answer