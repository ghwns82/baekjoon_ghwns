from collections import deque

def str_to_int(str_time):
    tmp=0
    for j,v in enumerate(list(map(int,str_time.split(":")))):
        tmp+=v*(60**(2-j))
    return tmp
def int_to_str(int_time):
    h = int_time//3600
    int_time%=3600
    m = int_time//60
    int_time%=60
    s = int_time
    return ':'.join([str(i).zfill(2) for i in [h,m,s]])

def solution(play_time, adv_time, logs):
    play_time = str_to_int(play_time)
    imos = [0]*(play_time+2)
    for i in logs:
        s,e=i.split('-')
        imos[str_to_int(s)]+=1
        imos[str_to_int(e)]-=1
    now = 0
    for i in range(len(imos)):
        now+=imos[i]
        imos[i]=now
    adv_time = str_to_int(adv_time)
    
    # print(imos[str_to_int("01:31:00")-4 : str_to_int("01:31:00")+3])
    # print(imos[str_to_int("01:31:00")+adv_time-4 : str_to_int("01:31:00")+adv_time+3])
    # print(str_to_int("01:31:00")-4, str_to_int("01:31:00")+3)
    ns = 0
    for i in range(adv_time):
        ns+=imos[i]
    s,e = 0, adv_time-1
    answer = [ns,s,e]
    cnt=0
    while e<len(imos)-1:
        ns-=imos[s]
        s+=1
        e+=1
        ns+=imos[e]
        
        if ns>answer[0]:
            cnt+=1
            answer = max(answer,[ns,s,e])
        
    
    
    return int_to_str(answer[1])