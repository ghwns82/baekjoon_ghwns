line1=list(map(int,input().split()))
line2=list(map(int,input().split()))

def updown(x,y,line):
    a1,b1,a2,b2 = line
    tmp = (x-a1)*(b1-b2) -(y-b1)*(a1-a2)
    if tmp>0:
        return 1
    elif tmp<0:
        return -1
    else:
        return 0

def iscross(line1,line2):
    x1,y1,x2,y2 = line1
    line = line2
    c1 = updown(x1,y1,line)
    if c1 == 0 and min(line[::2])<=x1<=max(line[::2]) and min(line[1::2])<=y1<=max(line[1::2]):
        return 1
    c2 = updown(x2,y2,line)
    if c2 == 0 and min(line[::2])<=x2<=max(line[::2]) and min(line[1::2])<=y2<=max(line[1::2]):
        return 1
    x1,y1,x2,y2 = line2
    line = line1
    c3 = updown(x1,y1,line)
    if c3 == 0 and min(line[::2])<=x1<=max(line[::2]) and min(line[1::2])<=y1<=max(line[1::2]):
        return 1
    c4 = updown(x2,y2,line)
    if c4 == 0 and min(line[::2])<=x2<=max(line[::2]) and min(line[1::2])<=y2<=max(line[1::2]):
        return 1
    
    return c1*c2<0 and c3*c4<0
        
print(+iscross(line1,line2))



# -9 -5 -2 1
# -2 1 3 3
# ans: 1

# -4 -2 -4 6
# -4 -5 -4 -2
# ans:1
# 0 0 0 2
# 0 -2 0 4

# ANS: 1

# 0 -2 0 4
# 0 0 0 2

# ANS 1

# 0 1 1 3
# 1 0 3 4
# 0

# input:
# 1000000 10 10 1000000
# 10 10 800000 800000

# output:
# 1