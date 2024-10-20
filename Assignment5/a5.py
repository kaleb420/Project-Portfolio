import random as rn
import matplotlib.pyplot as plt
import numpy as np
import math


###########################################################################
# Functions for Problem 1
###########################################################################
#recursion
def h(n):
    pass

#memoization
#use this dictionary
dh1 = {-2:1,-1:1,0:1,1:4}
def hmemo(n):
    pass

#while-loop
def hw(n):
    pass

#tail recursion
def htr(n, i = 2, acc0 = 1, acc1 = 4):
    pass
    
#recursion
def p(n):
    pass

#tail recursion
def ptr(n,acc=1000):
    pass

#recursion 
def d(n):
    pass

#tail recursion
def dtr(n,acc=1):
    pass
    
#recursion
def c(n):
    pass

#tail recursion
def ctr(n,acc1=9,acc2=0):
    pass

#while-loop
def cw(n):
    pass






###########################################################################
# Functions for Problem 2
###########################################################################
#INPUT t = (a,b,c)
#RETURN return complex or real roots
def q(t):
    pass
   
   


###########################################################################
# Functions for Problem 3
###########################################################################
#INPUT coefficients of quadratic (ax^2 + bx + c)
#RETURN m, n 
#CONSTRAINT round to 2 decimal places
def c_s(coefficients):
    pass

#INPUT coefficients for quadratic ax^2 + bx + c 
#RETURN return real roots uses c_s
def q_(coefficients):
    pass


###########################################################################
# Functions for Problem 4
###########################################################################
#INPUT List of numbers
#RETURN Various means
def mean(lst):
    pass

def var(lst):
    pass

def std(lst):
    pass

def mean_centered(lst):
    pass




###########################################################################
# Functions for Problem 5
###########################################################################
#INPUT supply and demand coefficients
#RETURN solution of quadratic equations
def equi(s,d):
    pass




###########################################################################
# Functions for Problem 6
###########################################################################
#INPUT a nested list of people encoded as 0's and 1's. v0 and v1 are the respective lists respresenting the people pairs.
#   You'll be comparing the smallest degree of difference between each sublist representing each person.
#RETURN person pair with the smallest degree (smallest degree of difference between the person pair lists)
#You cannot use sort of any kind
def inner_prod(v0,v1):
    pass

def mag(v):
    pass

def angle(v0,v1):
    pass

def match(people):
    pass

def best_match(scores):
   pass 



###########################################################################
# Functions for Problem 7
###########################################################################
def determinant(matrix):
    pass

def solve(eq1,eq2):
    pass

#do not change
def f_1(x):
    return (1/4)*(-2*x + 11)

#do not change
def f_2(x):
    return (1/3)*(5*x + 5)




###########################################################################
# Functions for Problem 8 
###########################################################################
#input two lists of points
#output the shared points using a single list comprehension
def intersection(x,y):
    pass


#input two points
#output city block distance
def block_distance(p0, p1):
    pass

#input the center point and city block distance bd
#output list of points less than equal distance to center
def get_points(center,bd):
    pass




###########################################################################
# Functions for Problem 9
###########################################################################
#INPUT list of numbers
#OUTPUT Boolean if geometric series
def is_geometric_sequence(lst):
    pass



###########################################################################
# Functions for Problem 10
###########################################################################
#INPUT portfolio of stock price, shares, market
#OUTPUT current total value
def value(portfolio, market):
   pass


###########################################################################
# Functions for Problem 11
###########################################################################
#INPUT a (possibly empty) list of numbers
#OUTPUT show error (as mentioned in the PDF) or smoothed values
#problem 12
def smooth(lst):
   pass 



###########################################################################
#problem 12
###########################################################################
#input secret code and all possible values
#output the string equal to the code
#must be done recursively
def break_code(secret_code, combinations):
    pass


#Do not change this code
#generates a secret code from a combination of values    
def m(useless_parameter=0):
    rn.seed(useless_parameter+1)
    combinations = "".join([chr(i) for i in range(ord('0'),ord('0') + rn.randint(5,35))])
    secret_code = ""
    for _ in range(rn.randint(4,8 + rn.randint(0,20))):
        secret_code += rn.choice(combinations)
    
    return secret_code, break_code(secret_code,combinations)





if __name__ == "__main__":
    """
    If you want to do some of your own testing in this file, 
    please put any print statements you want to try in 
    this if statement.
    
    Comment out the test before submitting to the Autograder.
    """

    # #problem 1

    # for i in range(5):
    #     print(f"n = {i}")
    #     print("c", c(i),ctr(i),cw(i))
    #     print("p", p(i), ptr(i))
    #     print("h", h(i), hmemo(i), hw(i), htr(i))
    #     print('d', d(i), dtr(i))

    # #problem 2
    # print(q((3,4,2)))
    # print(q((1,3,-4)))
    # print(q((1,-2,-4)))


    #problem 3 pairs should be identical
    # print(q((1,-4,-8)), q_((1,-4,-8)))
    # print(q((1,3,-4)),q_((1,3,-4)))
    # print(q((3,4,2))) #q_ won't work on complex roots
   
    
    # #problem 4
    #no example output 
    # lst = [1,3,3,2,9,10]

    # print(mean(lst))
    # print(var(lst))
    # print(std(lst))
    # print(mean(mean_centered(lst)))

    # #problem 5
    # s = (-.025,-.5,60)
    # d = (0.02,.6,20)
    # print(equi(s,d))
    
    #work this by hand
    # s = (5,7,-350)
    # d = (4,-8,1000)
    # print(equi(s,d))

    #problem 6
    # people0 = [[0,1,1],[1,0,0],[1,1,1]]
    # print(match(people0))
    # print(best_match(match(people0)))

    # people1 = [[0,1,1,0,0,0,1],
    #            [1,1,0,1,1,1,0],
    #            [1,0,1,1,0,1,1],
    #            [1,0,0,1,1,0,0],
    #            [1,1,1,0,0,1,0]]
    # print(best_match(match(people1)))
    # #output is ([1, 1, 0, 1, 1, 1, 0], [1, 0, 0, 1, 1, 0, 0], 39.23)

    # v0,v1 = (2,3,-1), (1,-3,5)
    # print(angle(v0,v1)) #122.83

    # v0,v1 = (3,4,-1),(2,-1,1)
    # print(angle(v0,v1)) #85.41

    # v0,v1 = (5,-1,1),(1,1,-1)
    # print(angle(v0,v1)) #70.53


    # #problem 7
    # print(determinant([[1,2],[2,3]])) #-1

    # eq1,eq2 = [1,1,3],[2,3,1]
    # print(solve(eq1,eq2))
    # eq1,eq2 = [[2,4,11],[-5,3,5]]
    # x_star,y_star = solve(eq1,eq2)
    # print(solve(eq1,eq2))
    # eq1,eq2 = [[3,-5,4],[7,4,25]]
    # print(solve(eq1,eq2))

    #Uncomment to see visualization (make sure to comment before submitting to the Autograder)
    # x = np.linspace(-2,6,100)
    # plt.plot(x,f_1(x),'r')
    # plt.plot(x,f_2(x),'b')
    # plt.plot(x_star,y_star,'go')
    # plt.show()

    
    #problem 8
    
    # A = ((0,-1),2)
    # B = ((0,1),1)
    # C = ((4,4),1)
    # p = get_points(*A)
    # q = get_points(*B)
    # r = intersection(p,q)
    # s = get_points(*C)
    # t = intersection(s,q)

    # for points in p,q,r,s:
    #     print(points)

    #uncomment to see visualization
    # color = 'rgbmy'

    # for i,pts in enumerate([p,q,r,s,t]):
    #     plt.plot([x for x,_ in pts],[y for _,y in pts],color[i] + 'o')

    # plt.gca().legend(("A: ((0,-1),2)", "B: ((0,1),1)", r"$\mathsf{A}\cap\mathsf{B}$","C: ((4,4),1)", r"$\mathsf{B}\cap\mathsf{C}$"))
    # plt.axis([-7, 7, -7, 7])
    # plt.grid()
    # plt.gca().set_aspect("equal")

    # plt.grid(True)
    # plt.title("City with square streets.")
    # plt.show()


    #problem 9
    # data = [[1,2,4,6],[2,4,8,16],[10,30,90,270,810,2430]]
    # for d in data:
    #     print(is_geometric_sequence(d))

    
    #problem 10
    # portfolios =  {'A':{'stock':{'x':(41.45,45),'y':(22.20,1000)}},'B':{'stock':{'x':(33.45,15),'y':(12.20,400)}}}
    # market = {'x':43.00, 'y':22.50}

    # for name, portfolio in portfolios.items():
    #     print(f"{name} {value(portfolio,market)}")

    
    #problem 11
    # data = [[], [1],[1,2],[1,2,2,3],[0,2,4,6,8]]
    # for d in data:
    #     print(smooth(d))  

    #problem 12
    #the parameter i isn't used
    # for i in range(5):
    #     print(m(i))
