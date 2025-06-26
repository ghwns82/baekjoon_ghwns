import sys
input = sys.stdin.readline

class Node:
    def __init__(self):
        self.data = 0
        self.left = None
        self.right = None

class Trie:
    def __init__(self, arr):
        self.root = Node()
        for i in arr:
            self.add(i, show=False)

    def num2bin(self, num):
        return bin(num)[2:].zfill(25)

    def add(self,num, show=True):
        flag = num in nums
        nums.add(num)
        if show:
            print(len(nums))
        if flag:
            return

        # print('add',num)
        cur = self.root
        for i in self.num2bin(num):
            if i=='0':
                if not cur.left:
                    cur.left = Node()
                next = cur.left
            else:
                if not cur.right:
                    cur.right = Node()
                next = cur.right
            next.data+=1
            # print([cur.left,cur.right])
            cur = next

    def remove_min(self):
        cur = self.root
        stack=[]
        for _ in range(25):
            if cur.left and cur.left.data:
                stack.append('0')
                cur = cur.left
            else:
                stack.append('1')
                cur=cur.right
            cur.data-=1

        num=int(''.join(stack),2)
        nums.remove(num)
        # print('remove',num)
        print(num)
    def remove_max(self):
        cur = self.root
        stack=[]
        for _ in range(25):
            if cur.right and cur.right.data:
                stack.append('1')
                cur = cur.right
            else:
                stack.append('0')
                cur=cur.left
            cur.data-=1

        num=int(''.join(stack),2)
        nums.remove(num)
        print(num)
    
    def find_min(self, num, reverse:int):
        cur = self.root
        stack = []
        for i in self.num2bin(num):
            # print('child',cur.left,cur.right)
            if not cur.left or not cur.left.data:
                stack.append('1')
                cur = cur.right
                continue
            if not cur.right or not cur.right.data:
                stack.append('0')
                cur = cur.left
                continue

            cur = [cur.left,cur.right][int(i)^reverse]
            stack.append('01'[int(i)^reverse])
        # print(stack)
        num2=int(''.join(stack),2)
        # print('result',num2)
        print(num2^num)

    def show(self, num=0):
        cur = self.root
        if num:
            for i in self.num2bin(num):
                print(cur.data)
                print([cur.left,cur.right])
                cur = [cur.left,cur.right][i=='1']
            return



        for i in range(25):
            print([cur.left,cur.right])
            if int(input()):
                cur = cur.right
            else:
                cur = cur.left



for _ in range(int(input())):

    n,q = map(int,input().split())
    arr = list(map(int,input().split()))
    nums = set()
    tree = Trie(arr)


    for _ in range(q):
        cmd = list(map(int,input().split()))
        if cmd[0]>=4:
            if cmd[0]==4:
                tree.remove_min()
            else:
                tree.remove_max()
        elif cmd[0]==3:
            tree.add(cmd[1])

        else:
            tree.find_min(cmd[1],cmd[0]-1)
        # print(nums)
        # tree.show(2)