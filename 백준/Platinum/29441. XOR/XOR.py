import sys
input = lambda : sys.stdin.readline().rstrip()

class Node:
    def __init__(self):
        self.left, self.right = None, None
        
        
class mytree:
    def __init__(self):
        self.root = Node()
    def convert2bin(self,num):
        return bin(num)[2:].zfill(32)
    def add(self, num):
        bin_num = self.convert2bin(num)
        node = self.root
        for i in bin_num:
            if i=='0':
                if not node.left:
                    node.left = Node()
                node = node.left
            else:
                if not node.right:
                    node.right = Node()
                node = node.right
    
    def xor(self,num):
        bin_num = self.convert2bin(num)
        result = []
        node = self.root
        for i in bin_num:
            first, second = [node.left,node.right][::(i=='1')*2-1]
            if first:
                result.append('1')
                node = first
            else:
                result.append('0')
                node = second
        return int(''.join(result),2)
                    
            
                


tree = mytree()
n = int(input())
for i in list(map(int,input().split())):
    tree.add(i)


result = []
m = int(input())
for i in list(map(int,input().split())):
    result.append(tree.xor(i)^i)
    
print(*result)
    
