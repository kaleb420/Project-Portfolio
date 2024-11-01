import math
import numpy as np
# import matplotlib.pyplot as plt

########################
# PROBLEM 1
########################
#recursive functions
def cbr(x,b=2):
    msg=''
    if x//b==0:
        return msg.append(int(x%b))
    else:
        return cbr(x//b), msg.append(int(x%b))

def d(x,b=2):
    pass


########################
# PROBLEM 2
########################
def msi(x):
    difference=0
    for i in range(len(x)+1):
        for j in range(len(x)+1):
            if sum(x[i:j])>difference:
                difference=sum(x[i:j])
                greatest_value_i=i
                greatest_value_j=j
    return [greatest_value_i, greatest_value_j, difference]

########################
# PROBLEM 3
########################
#INPUT string and shift
#OUTPUT encoded string
def encode(msg,shift):
    encoded_dict={}
    number_alphabet={'a':1, 'b':2, 'c':3, 'd':4, 'e':5, 'f':6, 'g':7, 'h':8, 'i':9, 'j':10, 'k':11, 'l':12, 'm':13, 'n':14, 'o':15, 'p':16, 'q':17, 'r':18, 's':19, 't':20, 'u':21, 'v':22, 'w':23, 'x':24, 'y':25, 'z':26, '{':27}
    message=''
    msg1=msg.replace(' ', '{')
    if shift>27:
        shift-=27
    for k,v in number_alphabet.items():
        encoded_dict[v]=k
    for i in msg1:
        encoded=number_alphabet[i]+shift
        if encoded>27:
            encoded-=27
        message+=encoded_dict[encoded]
    return message

#INPUT encoded string and shift
#OUTPUT decoded string
def decode(msg,shift):
    decoded_dict={}
    number_alphabet={'a':1, 'b':2, 'c':3, 'd':4, 'e':5, 'f':6, 'g':7, 'h':8, 'i':9, 'j':10, 'k':11, 'l':12, 'm':13, 'n':14, 'o':15, 'p':16, 'q':17, 'r':18, 's':19, 't':20, 'u':21, 'v':22, 'w':23, 'x':24, 'y':25, 'z':26, ' ':27}
    message=''
    msg1=msg.replace('{', ' ')
    if shift>27:
        shift-=27
    for k,v in number_alphabet.items():
        decoded_dict[v]=k
    for i in msg1:
        decoded=number_alphabet[i]-shift
        if decoded<1:
            decoded+=27
        message+=decoded_dict[decoded]
    return message

########################
# PROBLEM 4
########################
#INPUT list of immutable objects
#RETURN probability distribution as a list
def makeProbability(xlst):
    dict={}
    new_lst=[]
    for i in xlst:
        if i not in dict:
            dict[i]=1
        elif i in dict:
            dict[i]+=1
    lst=list(dict.values())
    for j in range(len(lst)):
        new_lst.append(lst[j]/len(xlst))
    return new_lst

#INPUT probability distribution
#RETURN non-negative number entropy
def entropy(xlst):
    sum_of=0
    for i in makeProbability(xlst):
        sum_of+=i*math.log2(i)
    return round(-sum_of,2)

########################
# PROBLEM 5
########################
#INPUT list of 0s 1s
#OUTPUT longest list of 1s
def L(x):
    longest=[0]
    for i in x:
        if i==1:
            longest[-1]+=1
        elif i==0:
            longest.append(0)
    return max(longest)
    
########################
# PROBLEM 6
########################
#INPUT non-negative integer
#OUTPUT True if divisible by 9, False otherwise
def div_9(x):
    j=0
    if x==9 or x==0:
        return True
    elif len(str(x))!=1:
        for i in str(x):
            j+=int(i)
        return div_9(j)
    else:
        return False

########################
# PROBLEM 7
########################
def tiles(n,v,lst):
    pass



########################
# PROBLEM 8
########################
#INPUT data points (x0,y0),...,(xn,yn)
#OUTPUT best regression slope m_hat, intercept b_hat
def std_linear_regression(data):
    xyp=0
    xs=0
    ys=0
    xsq=0
    for i in data:
        xyp+=i[0]*i[1]
        xs+=i[0]
        ys+=i[1]
        xsq+=i[0]**2
    sxy=xyp-(xs*ys)/len(data)
    sxx=xsq-(xs**2/len(data))
    m_hat=round(sxy/sxx,3)
    b_hat=round((ys-m_hat*xs)/len(data),3)

if __name__ == "__main__":

    #problem 1
    x1 = cbr(5,3)
    print(x1,d(x1,3))
    x1 = cbr(11,2)
    print(x1,bin(11),int(bin(11),2),d(x1,2))

    for i in range(2,7):
        print(f"base {i}")
        for j in range(1,9):
            print(f"{j}_10, {cbr(j,i)}_{i}, {d(cbr(j,i),i)}_{10}")


    #problem 2
    # x2 = [7, -9, 5, 10, -9, 6, 9, 3, 3, 9]
    # print(msi(x2))


    #problem 3
    # data = ["abc xyz","the cat", "i love ctwohundred"]
    # for i,j in enumerate(data,start=2):
    #     print(f"original msg {j}")
    #     print(f"encoded  msg {encode(j,i)}")
    #     print(f"decoded  msg {decode(encode(j,i),i)}")

    # secret_msg = encode("the quick brown fox jumps over the lazy dog", 24)
    # print(secret_msg)
    # print(decode(secret_msg,24))

    # # #Problem 4
    # data4 = [["a", "b", "a", "c", "c", "a"],[1],[1,2,3,4]]
    # # 1.46, -0.0, 2.0; 0 is minimal, log(n) is maximal
    # for d in data4:
    #     print(entropy(d)) 


    # #Problem  5
    # data5 = [[0],[1],[1,1,0,1,1,1],[0,1,1,0],[0,1,1,1,0,0,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1]]
    # for d in data5:
    #     print(L(d))


    # # #Problem 6
    # data6 = [99,0,18273645,22,27]
    # for d in data6:
    #     print(div_9(d), not bool(d % 9))

    ##problem 7
    # n = 6
    # v = [1,2,3]
    # print(tiles(n,v,[[i] for i in v]))
    # for i in tiles(n,v,[[i] for i in v]):
    #     print(sum(i), end="")
    # n = 4
    # v = [1,2]
    # print(tiles(n,v,[[i] for i in v]))
    # for i in tiles(n,v,[[i] for i in v]):
    #     print(sum(i), end="")    

    #problem 8

    # data8 = [(209,89),(139,74),(101,86),(74,74),(67,68),(49,67),(119,97),(98,92)]
    # m_hat, b_hat, R_sq  = std_linear_regression(data8)
    # print(m_hat,b_hat,R_sq)
    # plt.plot([x for x,_ in data8],[y for _,y in data8],'ro')
    # plt.plot([x for x,_ in data8],[m_hat*x + b_hat for x,_ in data8],'b')
    # plt.xlabel("$M Payroll")
    # plt.ylabel("Season Wins")
    # plt.title(f"Least Squares: m = {m_hat}, b = {b_hat}, R^2 = {R_sq} ")
    # plt.ylabel("Y")
    # plt.show()

    print()
