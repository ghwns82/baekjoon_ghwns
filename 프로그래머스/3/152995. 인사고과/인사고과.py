from collections import defaultdict
from bisect import bisect


data = defaultdict(int)
arr =[]

class Node:
    def __init__(self,start,end):
        self.data=None
        self.range=(start,end)
        self.left=None
        self.right=None

class MergeSortTree:
    def __init__(self,start,end):
        self.root = self.create(start,end)
        
    def create(self,start,end):
        nd = Node(start,end)
        if start==end:
            nd.data=data[arr[start]]
        else:
            mid = (start+end)//2
            nd.left =self.create(start,mid)
            nd.right = self.create(mid+1,end)
            nd.data = max(nd.left.data, nd.right.data)
        return nd
    def search(self,index,value,cur_node):
        if index == cur_node.range[0]:
            return cur_node.data > value
        else:
            mid = sum(cur_node.range)//2
            if index>mid:
                return self.search(index,value,cur_node.right)
            else:
                return self.search(mid+1,value,cur_node.right) or self.search(index,value,cur_node.left)
        
    
    
def solution(scores):
    global data, arr
    for a,b in scores:
        data[a] = max(b,data[a])
    
    arr= sorted(data.keys())
    mst = MergeSortTree(0,len(arr)-1)
    
    rank = []
    
    a,b = scores[0]
    if a!=arr[-1] and mst.search(bisect(arr,a),b,mst.root):
        return -1
    
    for a,b in scores:
        if a==arr[-1]:
            rank.append(a+b)
            continue
        r=mst.search(bisect(arr,a),b,mst.root)
        if not r:
            rank.append(a+b)
    rank.sort(reverse=True)
    # print(rank)
    answer = rank.index(sum(scores[0]))+1

    return answer
    