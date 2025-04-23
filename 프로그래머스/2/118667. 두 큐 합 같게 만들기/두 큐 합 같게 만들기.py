def solution(queue1, queue2):
    ns1,ns2 = {0:0},{0:0}
    
    hap1 = 0
    for i in range(len(queue1)):
        hap1+=queue1[i]
        ns1[hap1] = i+1
    
    hap2 = 0
    for i in range(len(queue2)):
        hap2+=queue2[i]
        ns2[hap2] = i+1
    
    hap=0
    for i in range(len(queue1)-1,-1,-1):
        hap-=queue1[i]
        ns2[hap] = len(queue2) + i
    hap=0
    for i in range(len(queue2)-1,-1,-1):
        hap-=queue2[i]
        ns1[hap] = len(queue1) + i
    
    
    if (hap1+hap2)%2:
        # print('case1')
        return -1
    
    result = len(queue1)*2 + len(queue2)*2+1
    cnt=0
    for key in ns1:
        key2 = (hap2-hap1)//2 + key
        # print('key',key,key2)
        if key2 in ns2:
            # print('possible', ns1[key], ns2[key2])
            result = min(result, ns1[key] + ns2[key2])
        cnt+=1
        if cnt == len(queue1):
            break
    cnt = 0
    for key in ns2:
        key2 = (hap1-hap2)//2 + key
        # print('key',key,key2)
        if key2 in ns1:
            # print('possible', ns2[key], ns1[key2])
            result = min(result, ns2[key] + ns1[key2])
        cnt+=1
        if cnt == len(queue1):
            break

    # print('result',result)
    if result ==len(queue1)*2 + len(queue2)*2+1:
        return -1
    
    return result
