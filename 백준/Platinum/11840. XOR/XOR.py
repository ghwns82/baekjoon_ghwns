import sys
input = lambda : sys.stdin.readline().rstrip()
# import sys
# input = lambda : sys.stdin.readline().rstrip()

class Node:
    def __init__(self):
        self.index = 250000
        self.left, self.right = None, None
        
        
class mytree:
    def __init__(self):
        self.root = Node()
    def convert2bin(self,num):
        return bin(num)[2:].zfill(30)
    def add(self, num, index):
        bin_num = self.convert2bin(num)
        node = self.root
        for i in bin_num:
            node.index=min(node.index, index)
            if i=='0':
                if not node.left:
                    node.left = Node()
                node = node.left
            else:
                if not node.right:
                    node.right = Node()
                node = node.right
        node.index=min(node.index, index)
    def find(self,num):
        bin_num = self.convert2bin(num)
        node = self.root
        result = 250000

        # k를 따라감, break 없으면 리프노드 index 정보 반영
        # k보다 큰 수가 존재하면 그 노드의 index 정보 반영
        
        for i in range(30):
            if bin_k[i] == '1': # 달라야 함
                if bin_num[i]=='0':
                    if node.right: # 다름
                        node = node.right
                        continue
                    break
                else:
                    if node.left:# 다름
                        node = node.left
                        continue
                    break
            else:   # 같음 => 따라감, 다름 => 정보 반영
                if bin_num[i]=='0':
                    if node.right:
                        result = min(result, node.index)
                    if node.left:
                        node = node.left
                        continue
                    break
                else:
                    if node.left:
                        result = min(result, node.index)
                    if node.right:
                        node = node.right
                        continue
                    break
        else:
            result = min(result, node.index)
        return result
                
            
tree = mytree()
n,k = map(int,input().split())
bin_k = tree.convert2bin(k)
ns = [0]
result = [0,260000]
index=0
tree.add(0,index)
for num in map(int,input().split()):
    index+=1
    ns.append(ns[-1]^num)
    i = tree.find(ns[-1])
    if i<index:
        result = max(result, [index-i, -i-1])

    tree.add(ns[-1],index)
print(-result[1],result[0])
    
    
    
    