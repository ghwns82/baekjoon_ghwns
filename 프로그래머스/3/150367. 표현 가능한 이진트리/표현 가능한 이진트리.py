def solution(numbers):
    answer = []
    
    for number in numbers:
        bi_num = bin(number)[2:]
        unit = 1
        while len(bi_num) > unit:
            unit<<=1
        bi_num = bi_num.zfill(unit)
        result = 1
        stack = [(unit>>1, unit>>2)] # index,len_arm
        while stack:
            index,len_arm = stack.pop()
            if len_arm==0:
                continue
            left,right = index - len_arm, index+len_arm

            if bi_num[index]=='0' and not bi_num[left] == bi_num[right]=='0':
                result = 0
                break
            stack.append([left,len_arm>>1])
            stack.append([right,len_arm>>1])
        answer.append(result)

    return answer