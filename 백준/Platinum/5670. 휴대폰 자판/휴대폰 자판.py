import sys
input = lambda : sys.stdin.readline().strip()

def make():
    trie = {}
    words = [input() for _ in range(int(input()))]
    
    for word in words: 
        tmp = trie
        for i in word:
            if i not in tmp:
                tmp[i] = {}
            tmp = tmp[i]
        tmp[0] = 1

    result = 0
    for word in words:
        tmp = trie[word[0]]
        result+=1
        for i in word[1:]:
            if len(tmp.keys())!=1:
                result+=1
            tmp = tmp[i]
            # print(result)
    a = round(result/len(words),2)
    print(f'{a:0.2f}')

while True:
    try: 
        make()
    except:
        break