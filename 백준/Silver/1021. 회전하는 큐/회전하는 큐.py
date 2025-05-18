n, m = map(int, input().split())
o_nums = list(map(int, input().split()))

a = [i for i in range(1, n+1)]
result = 0
for out_num in o_nums:
    o_index = a.index(out_num)
    if o_index <= len(a)-1-o_index:
        result += o_index
    else:
        result += len(a)-o_index
    a = a[o_index+1:]+a[:o_index]
print(result)