import sys
input = lambda : sys.stdin.readline().strip()

c,n = map(int,input().split())

trie = {}
for i in range(c):
    word = input()
    tmp_dict = trie
    for chr in word:
        if chr not in tmp_dict:
            tmp_dict[chr] = {}
        tmp_dict = tmp_dict[chr]
    tmp_dict['\\']='\\'

nick = {input() for i in range(n)}
q = int(input())
for _ in range(q):
    team = input()
    tmp_dict = trie
    index= 0
    for t in team:
        # print(t, tmp_dict)
        if t in tmp_dict:
            tmp_dict = tmp_dict[t]
            index+=1
            if '\\' in tmp_dict and  team[index:] in nick:
                print("Yes")
                break
        else:
            print("No")
            break
    else:
        print("No")