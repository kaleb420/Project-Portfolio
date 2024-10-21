import math
import numpy as np

###########################################################################
# Functions for Problem 1
###########################################################################
#INPUT dlst = [day, month, year]
#RETURN string corresponding to the day of the week (i.e. "Mon", "Sun", etc)
week = {0:"Sun", 1:"Mon", 2:"Tue", 3:"Wed", 4:"Thu", 5:"Fri", 6:"Sat"}
def a(dlst):
    d,m,y=dlst
    return y-(14-m)/12

def b(dlst):
    d,m,y=dlst
    x=a(dlst)+a(dlst)/4-a(dlst)/100+a(dlst)/400
    return math.floor(x)

def c(dlst):
    d,m,y=dlst
    return m+12*((14-m)/12)-2

def day(dlst):
    d,m,y=dlst
    return week[(d+b(dlst)+(31*(c(dlst)/12)))%7]

###########################################################################
# Functions for Problem 2
###########################################################################
def tree_age(height, time):
    pass





###########################################################################
# Functions for Problem 3
###########################################################################
#INPUT values for annuity
#OUTPUT deposit amount needed
def deposit(S,i,n):
    R=S/(((1+i)**n-1)/i)
    return R

#INPUT sinking fund values except deposit
#OUTPUT a list of period, deposit, interest accrued, total fund
def sinking_fund(final_amt, r, m, y):
    interest=r/m
    n=m*y
    R=final_amt/(((1+interest)**n-1)/interest)
    lst=[]
    for i in range(n):
        total_fund=R+(lst[-1]*interest)+lst[-1]
        lst.append[i,R,interest,total_fund]
    return lst 

#problem 4
#INPUT Weight in space and earth (pounds)
#OUTPUT altitude (kilometers)
def orbit(A_w, E_w):
    return math.ceil((6400/math.sqrt(A_w/E_w))-6400)

#problem 5
#INPUT pair of wind velocity, height 
#OUTPUT constant P value
def P_ws(v0,h0,v1,h1):
    pass


#problem 6
#approximation
#input x,tau
#output value of x of the taylor appoximation to cos with tau+1 terms
def cos_(x,tau):
    for i in range(tau+1):
        cos=((-1)**i/math.factorial(2*i))*x**(2*i)
    return cos

#input x,tau
#output value of x of the taylor appoximation to e with tau+1 terms
def e_(x,tau):
    for i in range(tau+1):
        e=((x)**i/math.factorial(i))**(1/x)
    return e

if __name__ == "__main__":
    """
    If you want to do some of your own testing in this file, 
    please put any print statements you want to try in 
    this if statement.

    You **do not** have to put anything here
    """

    #problem 1
    # print(day([14,2,2000]))
    # print(day([14,2,1963]))
    # print(day([14,2,1972]))

    # #problem 2
    # print(tree_age(50,20))
    # print(tree_age(100,20))


    # #problem 3
    # S = 30000
    # m = 4
    # r = 10/100
    # y = 2
    # for i in sinking_fund(S,r,m,y):
    #     print(i)


    # #problem 4
    # print(orbit(5,165))

    # # #problem 5
    # v0,h0 = 25,200
    # v1,h1 = 6,35
    # print(P_ws(v0,h0,v1,h1))

    # # problem 6
    x = np.arange(-4,4,.5)

    for i in x:
        print(math.cos(i),cos_(i,2),cos_(i,3),cos_(i,4))

    print(math.exp(2), e_(2,1), e_(2,5), e_(2,10))
    print(math.exp(3), e_(3,50))
