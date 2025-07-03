import sys
input = lambda : sys.stdin.readline().rstrip()


from collections import Counter
n,m = map(int,input().split())

child = [[] for i in range(n+1)]

salary = [int(input())]
for index in range(2,n+1):
    s,parent = map(int,input().split())
    salary.append(s)
    if parent==-1:
        continue
    child[parent].append(index)
    

tree_range = [[0, 0] for _ in range(n + 1)]
order = [0] * (n + 1)

visited = 0
stack = [(1, 0)]  # (현재노드, 상태: 0=방문전, 1=하위노드 방문후)

while stack:
    node, state = stack.pop()
    if state == 0:
        # 방문 전 처리
        order[node] = visited
        visited += 1
        tree_range[node][0] = visited
        # 하위 노드를 처리한 후 돌아오도록 push
        stack.append((node, 1))
        # 자식 노드들을 역순으로 push (DFS 순서를 위해)
        for child_node in reversed(child[node]):
            stack.append((child_node, 0))
    else:
        # 하위 노드 다 방문한 후
        tree_range[node][1] = visited
        
# print('order',order)
# print('tree_range',tree_range)

class Node:
    def __init__(self,start,end,left=None,right=None):
        self.range = (start,end)
        self.data = 0
        self.lazy = 0
        self.left_child = left
        self.right_child = right
        
class Seg:
    def __init__(self):
        self.root = self.create(0,n-1)
    
    def create(self,start,end):
        if start==end:
            return Node(start,end)
        mid = (start+end)//2
        return Node(start,end,
            left = self.create(start,mid),
            right = self.create(mid+1,end)
            )
    def propagate(self, cur: Node, search=False):
        start, end = cur.range
        if cur.lazy:
            cur.data+=(end-start+1)*cur.lazy
            if cur.left_child:  # 리프 노드가 아닐 경우 lazy 값 전파
                cur.left_child.lazy += cur.lazy
                cur.right_child.lazy += cur.lazy
            cur.lazy = 0  # 현재 노드의 lazy 값을 초기
        
    def modify(self, cur:Node, s,e, k):
        start,end=cur.range
        # print('cur_node',[start,end],[cur.data,cur.lazy])
        self.propagate(cur)
        if s > end or e < start:  # 범위를 벗어난 경우
            return
        if s <= start and end <= e:  # 범위 전체가 포함되는 경우
            cur.lazy +=k  # Lazy 값 반영
            self.propagate(cur)  # 현재 노드에 즉시 반영
            return
        # 구간을 나누어 좌우 자식에 대해 처리
        self.modify(cur.left_child, s, e,k)
        self.modify(cur.right_child, s, e,k)
        # 자식 노드들의 데이터를 합산하여 갱신
        cur.data = cur.left_child.data + cur.right_child.data
        # print('final cur_node',[start,end],[cur.data,cur.lazy])
        
        
        
    def search(self,cur:Node,index):
        start,end=cur.range
        self.propagate(cur)  # Lazy 전파

        if start == end==index:
            return cur.data
        mid = (start+end)//2
        if mid<index:
            return self.search(cur.right_child,index)
        else:
            return self.search(cur.left_child,index)
    def show(self, cur:Node):
        print(cur.range, [cur.data, cur.lazy])
        if cur.left_child:
            self.show(cur.left_child)
            self.show(cur.right_child)    
        
segtree=Seg()

# segtree.show(segtree.root)
# segtree.show(segtree.root)
tmp = Counter()
for _ in range(m):
    ctype, *content = input().split()
    if ctype=='p':
        i,w = map(int,content)
        tmp[i]+=w
        
        
    else:
        for i,w in tmp.items():
            s,e = tree_range[i]
            if s<=e-1:    
                segtree.modify(segtree.root,s,e-1,w)
        tmp = Counter()
        
        value = segtree.search(segtree.root, order[int(content[0])])
        
        print(value+salary[int(content[0])-1])
        
    # segtree.show(segtree.root)