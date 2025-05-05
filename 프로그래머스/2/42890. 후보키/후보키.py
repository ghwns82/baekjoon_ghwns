def solution(relation):
    result = []
    length = len(relation[0])
    for num in range(1,(1<<length) +1):
        # print('num',num)
        for i in result:
            if num&i==i:
                break
        else:        
            attribute = set()
            for row in relation:
                key = tuple(row[i] for i in range(length) if (1<<i)&num)
                # print('key',key,'attribute',attribute)
                if key in attribute:
                    break
                attribute.add(key)
            else:
                result.append(num)
        # print('result',result)
    # print(result)
    return len(result)