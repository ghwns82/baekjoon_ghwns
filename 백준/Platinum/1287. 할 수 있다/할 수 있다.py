import sys
ex = input()

def rock():
    print('ROCK')
    sys.exit()
def check_num(x):
    x=x.replace('-','')
    if not x:
        # print('stop 1')
        rock()
    return True


left=0
for i in ex:
    if i=='(':
        left+=1
    if i==')':
        left-=1
        if left<0:
            # print('stop 2')
            rock()
if left:
    # print('stop 3')
    rock()  
ex = '('+ex+')'
def cal(start_index):
    numbers=[]
    opers=[]
    stack=''
    index = start_index
    while True:
        index+=1
        c = ex[index]
        # print('index,c',index,c,'stack',stack,numbers,opers)
        if c=='(':
            if stack:
                # print('stop 4')
                rock()
            result=cal(index)
            numbers.append(result[0])
            index= result[1]
        elif c.isdigit():
            stack+=c
        elif c in '+-*/':
            if len(stack):
                if check_num(stack):
                    numbers.append(stack)
                if c=='/':
                    c='//'
                opers.append(c)
                stack=''
            else:
                if len(numbers) == len(opers)+1:
                    if c=='/':
                        c='//'
                    opers.append(c)
                else:
                    # print('stop 5')
                    rock()
        elif c ==')':
            if stack and check_num(stack):
                numbers.append(stack)
            break
    if len(numbers)-1 != len(opers):
        # print('stop 6')
        rock()
    
    final = ''

    for i in range(len(opers)):
        final+=str(int(numbers[i])) + opers[i]
    final+=str(int(numbers[-1]))
    if '/0' in final:
        # print('stop 7')
        rock()
    tmp3 = eval(final.replace('//','/'))
    if tmp3 != int(tmp3):
        # print('stop 8')
        rock()
    # print('final',final)
    return str(eval(final)), index

print(int(cal(0)[0]))