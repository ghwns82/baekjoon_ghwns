import sys

input = lambda: sys.stdin.readline().strip()

n, m, k = map(int, input().split())
cards = sorted(map(int, input().split()))
minsu = list(map(int, input().split()))

parent = [0] * (n + 1)
visited = [0] * (n + 1)
card_index = 0
for i in range(1, n + 1):
    if cards[card_index] <= i:
        card_index += 1
        if card_index == m:
            break
    parent[i] = cards[card_index]


def find(x):
    # print("find", x)
    if visited[parent[x]] == 1:
        # print("visited", x)
        parent[x] = find(parent[x])
        return parent[x]
    return parent[x]


# print(parent)

for minsu_card in minsu:
    chulsu_card = find(minsu_card)
    visited[chulsu_card] = 1
    print(chulsu_card)
    # print(visited)
    # print(parent)