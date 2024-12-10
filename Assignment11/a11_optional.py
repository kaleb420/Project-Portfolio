import random as rn


#problem 1
#input list of semi-ordered intervals
#output combined intervals under conditions
def combine(lst):
    nlst=[]
    if len(lst)==1:
        return lst
    for i in range(len(lst)-1):
        if nlst==[]:
            if lst[i][1]>=lst[i+1][0]:
                combined=[lst[i][0],lst[i+1][1]]
                nlst.append(combined)
            else:
                nlst.append(lst[i])
                nlst.append(lst[i+1])
        else:
            if nlst[-1][1]>=lst[i+1][0]:
                combined=[nlst[-1][0],lst[i+1][1]]
                nlst[-1]=combined
            else:
                nlst.append(lst[i+1])
    return nlst

#problem 2
def b(lst):
    k=1
    if len(lst)==0 or len(lst)==1:
        return -1
    else:
        while k!=len(lst):
            rs=lst[-k:]
            ls=lst[:-k]
            if sum(rs)==sum(ls):
                return len(ls)
            else:
                k+=1
        return -1

#problem 3
#defined the homework
def star(n):
    if n==0:
        return 1
    else:
        return n*star(n-1)

#defined in homework    
def star_star(n):
    if n==0:
        return 1
    else:
        return star(n)*star_star(n-1)

if __name__ == "__main__":

    #problem 1
    # lst = [[[1,3]],
    #     [[1,3],[4,5]],
    #     [[1,3],[2,6],[8,11],[9,12]],
    #     [[1,2],[2,3],[3,4],[4,12]],
    #     [[1,2],[3,4],[5,6]]]

    # for el in lst:
    #     print(f"{el} → {combine(el)}")
        
    #problem 2
    # data = [[1,2,3],[0,1,1,0,1,1],[0],[3,3],[1,2,3,3,2,1],[1,2,3,3,2,6],[1,0,1]]

    # for d in data:
    #     ptr = b(d)
    #     print(ptr, d[0:ptr],d[ptr:],sum(d[0:ptr])-sum(d[ptr:]))

    #problem 3
    # for i in range(10):
    #     print(i, star_star(i))


    print()