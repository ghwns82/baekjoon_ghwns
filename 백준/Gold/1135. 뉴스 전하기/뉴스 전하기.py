class tree:

    def __init__(self):
        self.root = None


class node:

    def __init__(self, data):
        self.parent = None
        self.data = data
        self.children = []


from itertools import permutations

n = int(input())
dp = [-1] * (n)
nodes = [node(n) for i in range(n)]
for c, p in enumerate(list(map(int, input().split()))[1:], 1):
    nodes[p].children.append(c)
    nodes[c].parent = p


def cal(cvalues):
    tmp = 1 + cvalues[0]
    for i, v in enumerate(cvalues, 1):
        tmp = max(tmp, i + v)
    return tmp


def search(index):
    # dp에 기록되어있나?
    if dp[index] != -1:
        return dp[index]

    # dp구하기
    # 1.자식이 없으면 0
    if not nodes[index].children:
        dp[index] = 0
        return 0
    # 2.자식들이 있으면 구하기
    cvalues = sorted([search(c) for c in nodes[index].children], reverse=True)
    dp[index] = cal(cvalues)
    return dp[index]


print(search(0))
# print(dp)
