import sys
input = lambda : sys.stdin.readline().rstrip()

class Node:
    def __init__(self):
        self.cnt = 0
        self.left, self.right = None, None
        
        
class mytree:
    def __init__(self):
        self.root = Node()
    def convert2bin(self,num):
        return bin(num)[2:].zfill(30)
    def add(self, num):
        bin_num = self.convert2bin(num)
        node = self.root
        for i in bin_num:
            node.cnt+=1
            if i=='0':
                if not node.left:
                    node.left = Node()
                node = node.left
            else:
                if not node.right:
                    node.right = Node()
                node = node.right
        node.cnt+=1
    def rm(self, num):
        bin_num = self.convert2bin(num)
        node = self.root
        node.cnt-=1
        for i in bin_num:
            if i=='0':
                node = node.left
            else:
                node = node.right
            
            node.cnt-=1
    def xor(self,num):
        bin_num = self.convert2bin(num)
        result = []
        node = self.root
        for i in bin_num:
            first, second = [node.left,node.right][::(i=='1')*2-1]
            if first and first.cnt:
                result.append('1')
                node = first
            else:
                result.append('0')
                node = second
        print(int(''.join(result),2))
                    
            
                
    def show_first(self):
        node = self.root
        while True:
            if node.left:
                node = node.left
                print([0,'cnt : ', node.cnt])
                
            elif node.right:
                node = node.right
                print([1,'cnt : ', node.cnt])
            else:
                break
            
            
tree = mytree()
tree.add(0)
cmd2 = [mytree.show_first,mytree.add,mytree.rm,mytree.xor]
for _ in range(int(input())):
    cmd,value = map(int,input().split())
    cmd2[cmd](tree, value)
