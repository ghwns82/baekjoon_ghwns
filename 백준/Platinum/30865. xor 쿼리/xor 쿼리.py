import sys
input = sys.stdin.readline

class node:
    def __init__(self,data):
        self.data = data
        self.size = 1
        self.next = {}

class trie:
    def __init__(self):
        self.root = node('')
    def convert2str(self,num):
        return bin(num)[2:].zfill(31)
    def add(self,num):
        num = self.convert2str(num)
        cur = self.root
        for s in num:
            if s not in cur.next:
                cur.next[s] = node(s)
            else:
                cur.next[s].size+=1
            cur = cur.next[s]
    
    def remove(self,num):
        num = self.convert2str(num)
        cur = self.root
        for s in num:
            cur.next[s].size-=1
            cur = cur.next[s]

    def search(self,goal,num):
        num = self.convert2str(num)
        cur = self.root
        result = []
        for s in num:
            bit = str(int(s)^1)
            if bit in cur.next:
                if cur.next[bit].size>=goal:
                    cur = cur.next[bit]
                    result.append(bit)
                else:
                    goal-= cur.next[bit].size
                    cur = cur.next[s]
                    result.append(s)
            else:
                cur = cur.next[s]
                result.append(s)


        return int(''.join(result),2)


tr = trie()
n = int(input())
arr = list(map(int,input().split()))
for i in arr:
    tr.add(i)
q = int(input())
for i in range(q):
    a,b,c = map(int,input().split())
    if a==1:
        tr.remove(arr[b-1])
        tr.add(c)
        arr[b-1]=c
    else:
        print(tr.search(b,c)^c)