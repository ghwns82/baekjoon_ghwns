int(input())
ph_list=sorted(list(map(int, input().split())))
start, end=0, len(ph_list)-1
min_mix = abs(ph_list[0] + ph_list[1])
result = [[ph_list[0], ph_list[1]]]
while start < end:
    mix = ph_list[start] + ph_list[end]
    if abs(mix) < min_mix:
        result.append([ph_list[start], ph_list[end]])
        min_mix = abs(mix)
    if mix>0:
        end-=1
    elif mix<0:
        start+=1
    else:
        break
print(*sorted(result[-1]))