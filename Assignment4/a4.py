import math
import numpy as np

###########################################################################
# Functions for Problem 1
###########################################################################
#INPUT dlst = [day, month, year]
#RETURN string corresponding to the day of the week (i.e. "Mon", "Sun", etc)
week = {0:"Sun", 1:"Mon", 2:"Tue", 3:"Wed", 4:"Thu", 5:"Fri", 6:"Sat"}
def a(dlst):
    pass


def b(dlst):
    pass


def c(dlst):
    pass


def day(dlst):
    pass



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
    pass



#INPUT sinking fund values except deposit
#OUTPUT a list of period, deposit, interest accrued, total fund
def sinking_fund(final_amt, r, m, y):
    pass


#problem 4
#INPUT Weight in space and earth (pounds)
#OUTPUT altitude (kilometers)
def orbit(A_w, E_w):
    pass


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
    pass


#input x,tau
#output value of x of the taylor appoximation to e with tau+1 terms
def e_(x,tau):
    pass







if __name__ == "__main__":
    """
    If you want to do some of your own testing in this file, 
    please put any print statements you want to try in 
    this if statement.

    You **do not** have to put anything here
    """

    #problem 1
    print(day([14,2,2000]))
    print(day([14,2,1963]))
    print(day([14,2,1972]))

    #problem 2
    print(tree_age(50,20))
    print(tree_age(100,20))


    #problem 3
    S = 30000
    m = 4
    r = 10/100
    y = 2
    for i in sinking_fund(S,r,m,y):
        print(i)


    #problem 4
    print(orbit(5,165))

    # #problem 5
    v0,h0 = 25,200
    v1,h1 = 6,35
    print(P_ws(v0,h0,v1,h1))

    # problem 6
    x = np.arange(-4,4,.5)

    for i in x:
        print(math.cos(i),cos_(i,2),cos_(i,3),cos_(i,4))

    print(math.exp(2), e_(2,1), e_(2,5), e_(2,10))
    print(math.exp(3), e_(3,50))
