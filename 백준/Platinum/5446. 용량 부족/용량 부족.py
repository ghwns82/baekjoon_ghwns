import sys
input = sys.stdin.readline
class Node:
    def __init__(self):
        self.data={}
        self.count = [0,0]

class Tree:
    def __init__(self):
        self.root = Node()
        self.result = 0
    def insert(self, file, rm=True):
        if rm:
            file+='0'
        cur = self.root
        cur.count[rm]+=1
        for s in file:
            if s not in cur.data:
                cur.data[s] = Node()
            cur = cur.data[s]
            cur.count[rm]+=1
    def search(self, cur):    
        if not cur.count[0] and cur.count[1]>0:
            self.result+=1
            # print('끝')
            return
        for key,next in cur.data.items():
            # print(key)
            self.search(next)
for i in range(int(input())):
    tree = Tree()
    for i in range(int(input())):
        tree.insert(input())
    for i in range(int(input())):
        tree.insert(input(), rm=False)

    tree.search(tree.root)
    print(tree.result)