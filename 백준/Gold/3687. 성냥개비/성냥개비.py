a={
    2:[1],
    3:[7],
    4:[4],
    5:[2,3,5],
    6:[6,9,0],
    7:[8],   
}
def return_num(arr):
    num=0
    for i in arr:
        num*=10
        num+=i
    return num
maxdp = [[] for i in range(101)]
for i in range(2,101):
    for key in a:
        if i>=key:
            for value in a[key]:
                if i-key==1:
                    continue
                tmp2=maxdp[i-key]+[value]
                tmp2.sort(reverse=True)
                if len(maxdp[i])<len(tmp2):
                    maxdp[i]=tmp2
                elif len(maxdp[i])==len(tmp2) and tmp2>maxdp[i]:
                    maxdp[i]=tmp2
mindp = [[] for i in range(2)]+[[9]*50 for i in range(2,101)]
for i in range(2,101):
#     print(i)
    for key in a:
        if i>=key:
#             print('key',key)
            for value in a[key]:
                if i-key==1:
                    continue
                tmp2=mindp[i-key]+[value]
                if tmp2==[0]:
                    continue
                tmp=[]
                stack=0
                for s in tmp2:
                    if s==0:
                        stack+=1
                    else:
                        tmp.append(s)
                tmp.sort()
                tmp2 = [tmp[0]]+ [0]*stack+tmp[1:]
#                 print(tmp2)
                if len(mindp[i])>len(tmp2):
                    mindp[i]=tmp2
                elif len(mindp[i])==len(tmp2) and tmp2<mindp[i]:
                    mindp[i]=tmp2
for i in range(int(input())):
    n = int(input())
    print(return_num(mindp[n]), return_num(maxdp[n]))