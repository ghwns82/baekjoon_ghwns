import sys

input = lambda: sys.stdin.readline().strip()

n = int(input())
arr = list(map(int, input().split()))


class Node:
    def __init__(self, start, end, left=None, right=None):
        self.range = (start, end)
        if left:
            self.data = []
            left_index, right_index=0,0
            while len(self.data)<2:
                if left.data[left_index] >= right.data[right_index]:
                    self.data.append(left.data[left_index])
                    left_index+=1
                else:
                    self.data.append(right.data[right_index])
                    right_index+=1
        else:
            self.data = [(arr[start],start), (-1,-1)]
        self.left_child = left
        self.right_child = right


class Seg:
    def __init__(self):
        self.root = self.create(0, n - 1)

    def create(self, start, end):
        if start == end:
            return Node(start, end)
        mid = (start + end) // 2
        return Node(
            start, end, left=self.create(start, mid), right=self.create(mid + 1, end)
        )


    def modify(self, cur: Node, index, k):
        start, end = cur.range
        
        if start == end:
            cur.data = [(k,start), (-1,-1)]
            # print('modify here', [start,end], cur.data)
            return

        mid = (start + end) // 2
        if mid < index:
            self.modify(cur.right_child, index, k)
        else:
            self.modify(cur.left_child, index, k)
        
        cur.data = []
        left_index, right_index=0,0
        while len(cur.data)<2:
            if cur.left_child.data[left_index] >= cur.right_child.data[right_index]:
                cur.data.append(cur.left_child.data[left_index])
                left_index+=1
            else:
                cur.data.append(cur.right_child.data[right_index])
                right_index+=1

    def search(self, cur: Node, s, e):
        start, end = cur.range

        if start == s and end == e:
            return cur.data

        mid = (start + end) // 2
        if mid < s:
            return self.search(cur.right_child, s, e)
        elif mid >= e:
            return self.search(cur.left_child, s, e)
        else:
            return self.search(cur.left_child, s, mid) + self.search(cur.right_child, mid + 1, e)

    def show(self, cur: Node):
        print(cur.range, 'data', cur.data)
        if cur.left_child:
            self.show(cur.left_child)
            self.show(cur.right_child)


segtree = Seg()

# segtree.show(segtree.root)
for _ in range(int(input())):
    ctype, *content = list(map(int, input().split()))
    if ctype == 1:
        i,v = content
        segtree.modify(segtree.root, i-1,v)
    else:
        i, j = content
        result=sorted(segtree.search(segtree.root, i-1,j-1), reverse=True)[:2]
        # print('result',result)
        print(result[0][0]+result[1][0])
    # segtree.show(segtree.root)