from itertools import combinations
s = input()
result = set()
for i in range(len(s)):
    for j in range(i+1,len(s)+1):
        # print(s[i:j])
        result.add(s[i:j])
        # print(g)
    # break
print(len(result))