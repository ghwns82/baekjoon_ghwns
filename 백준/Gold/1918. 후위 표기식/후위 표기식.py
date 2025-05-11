def trans(line):
    i=0
    cnt = line.count('*')+line.count('/')
    while cnt:
        if line[i] in ['*','/']:
            line[i-1:i+2]=[''.join(line[i-1]+line[i+1]+line[i])]
            cnt-=1
        else:
            i+=1
    i=0
    # print(line)
    while len(line)>1:
        # print('+-',line)
        if line[i] in ['+','-']:
            # print(line[i-1]+line[i+1]+line[i])
            line[i-1:i+2]=[''.join(line[i-1]+line[i+1]+line[i])]
        else:
            i+=1
    return line
line=list(input())
branket_list=[]
i=0
while i<len(line):
    if line[i]=='(':
        branket_list.append(i)
    elif line[i]==')':
        start=branket_list.pop()
        # print('trans',line[start+1:i])
        line[start:i+1] = trans(line[start+1:i])
        i=start
    # print('main',i,branket_list)
    i+=1
line = trans(line)

print(line[0])