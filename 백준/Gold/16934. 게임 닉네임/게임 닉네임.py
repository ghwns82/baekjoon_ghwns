import sys
input = lambda : sys.stdin.readline().rstrip()
from collections import Counter

class node:
    def __init__(self,data):
        self.data = data
        self.size = 1
        self.next = {}


root = node('')
ct = Counter()
for i in range(int(input())):
    cur=root
    second_name=[]
    flag=True
    name = input()
    if name in ct:
        ct[name]+=1
        print(name+str(ct[name]))
        continue
    for s in name:
        # print('cur is',cur.data,cur.next.keys())
        if flag:
            second_name.append(s)
        if s not in cur.next:
            cur.next[s] = node(s)
            cur = cur.next[s]
            flag=False
        else:
            cur = cur.next[s]
            cur.size+=1

    my_name = ''.join(second_name)
    ct[name]+=1
    print(my_name)
