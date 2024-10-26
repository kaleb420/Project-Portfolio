import random as rn
# import matplotlib.pyplot as plt
import numpy as np
import math


###########################################################################
# Functions for Problem 1
###########################################################################
#recursion
def h(n):
    if n<=0:
        return 1
    else:
        return 2*n+h(n-1)+h(n-2)

#memoization
#use this dictionary
dh1 = {-2:1,-1:1,0:1,1:4}
def hmemo(n):
    if n not in dh1.keys():
        if n==0:
            dh1[n]=0
        else:
            dh1[n]=2*n+hmemo(n-1)+hmemo(n-2)
    return dh1[n]

#while-loop
def hw(n):
    while n>=0:
        return 2*n+hw(n-1)+hw(n-2)
    return 1

#tail recursion
def htr(n, i = 2, acc0 = 1, acc1 = 2):
    if n==0:
        return acc0
    else:
        return htr(n-1,acc1,2*n+acc0+acc1)
    
#recursion
def p(n):
    if n==0:
        return 10000
    else:
        return p(n-1)+.02*p(n-1)

#tail recursion
def ptr(n,acc=10000):
    if n==0:
        return acc
    else:
        return ptr(n-1, acc+.02*ptr(n-1))

#recursion 
def d(n):
    if n==0:
        return 1
    else: 
        return 3*d(n-1)+1

#tail recursion
def dtr(n,acc=1):
    if n==0:
        return acc
    else:
        return dtr(n-1, 3*acc+1)
    
#recursion
def c(n):
    if n==0:
        return 1
    else: 
        return 9*c(n-1)+10**(n-1)-c(n-1)

#tail recursion
def ctr(n,acc1=9,acc2=0):
    if n==0:
        return acc1
    else:
        return ctr(n-1,9*acc1+10**(n-1)-acc1)

#while-loop
def cw(n):
    while n==0:
        return 9*cw(n-1)+10**(n-1)-cw(n-1)
    return 1

###########################################################################
# Functions for Problem 2
###########################################################################
#INPUT t = (a,b,c)
#RETURN return complex or real roots
def q(t):
    a,b,c=t
    root_type=b**2-(4*a*c)
    if root_type>=0:
        real_0=round((-b+math.sqrt(root_type))/(2*a),2)
        real_1=round((-b-math.sqrt(root_type))/(2*a),2)
        if real_0>=real_1:
            return (real_1,real_0)
        else:
            return (real_0,real_1)
    else:
        no_dis=round(-b/(2*a),2)
        imaginary_p=round(math.sqrt(root_type*-1)/(2*a),2)
        imaginary_n=round(-math.sqrt(root_type*-1)/(2*a),2)
        return complex(no_dis,imaginary_p), complex(no_dis,imaginary_n)

###########################################################################
# Functions for Problem 3
###########################################################################
#INPUT coefficients of quadratic (ax^2 + bx + c)
#RETURN m, n 
#CONSTRAINT round to 2 decimal places
def c_s(coefficients):
    a,b,c=coefficients
    m=round(b/(2*a),2)
    n=round((c-(b**2)/(4*a)),2)
    return m,n

#INPUT coefficients for quadratic ax^2 + bx + c 
#RETURN return real roots uses c_s
def q_(coefficients):
    m,n,=c_s(coefficients)
    x0=round(-m+math.sqrt(-n),2)
    x1=round(-m-math.sqrt(-n),2)
    return x1,x0

###########################################################################
# Functions for Problem 4
###########################################################################
#INPUT List of numbers
#RETURN Various means
def mean(lst):
    return round(sum(lst)/len(lst),2)

def var(lst):
    sum_of=0
    for i in lst:
        sum_of+=(i-mean(lst))**2
    return round((1/len(lst))*sum_of,2)

def std(lst):
    return round(math.sqrt(var(lst)),2)

def mean_centered(lst):
    mc=[]
    for i in lst:
        mc.append(i-mean(lst))
    return mc

###########################################################################
# Functions for Problem 5
###########################################################################
#INPUT supply and demand coefficients
#RETURN solution of quadratic equations
def equi(s,d):
    a1,b1,c1=s
    a2,b2,c2=d
    a=a2-a1
    b=b2-b1
    c=c2-c1
    root_type=b**2-(4*a*c)
    if root_type>=0:
        real_0=round((-b+math.sqrt(root_type))/(2*a),2)
        real_1=round((-b-math.sqrt(root_type))/(2*a),2)
        return real_0,real_1
    else:
        no_dis=round(-b/(2*a),2)
        imaginary_p=round(math.sqrt(root_type*-1)/(2*a),2)
        imaginary_n=round(-math.sqrt(root_type*-1)/(2*a),2)
        return complex(no_dis,imaginary_p), complex(no_dis,imaginary_n)

###########################################################################
# Functions for Problem 6
###########################################################################
#INPUT a nested list of people encoded as 0's and 1's. v0 and v1 are the respective lists respresenting the people pairs.
#   You'll be comparing the smallest degree of difference between each sublist representing each person.
#RETURN person pair with the smallest degree (smallest degree of difference between the person pair lists)
#You cannot use sort of any kind
def inner_prod(v0,v1):
    ip=0
    for i in range(len(v0)):
        ip+=v0[i]*v1[i]
    return ip

def mag(v):
    return math.sqrt(inner_prod(v,v))

def angle(v0,v1):
    theta=math.acos(inner_prod(v0,v1)/mag(v0)*mag(v1))
    return round(theta*(180/math.pi),2)

def match(people):
    lst=[]
    for i in people:
        for j in people:
            lst+=[[i,j,angle(i,j)]]
    return lst

def best_match(scores):
    best=10000000
    for i in scores:
        if scores[i]<best:
            best=angle[i]
    return best

###########################################################################
# Functions for Problem 7
###########################################################################
def determinant(matrix):
    return matrix[0][0]*matrix[1][1]-matrix[0][1]*matrix[1][0]

def solve(eq1,eq2):
    a,b,c=eq1
    d,e,f=eq2
    m_a=[c,f,b,e]
    det_a=m_a[0]*m_a[3]-m_a[1]*m_a[2]
    m_b=[a,d,b,e]
    det_b=m_b[0]*m_b[3]-m_b[1]*m_b[2]
    m_d=[a,d,c,f]
    det_d=m_d[0]*m_d[3]-m_d[1]*m_d[2]
    x=round(det_a/det_b,2)
    y=round(det_d/det_b,2)
    return x,y

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
    return [p for p in x if p in y]

#input two points
#output city block distance
def block_distance(p0, p1):
    minimum=0
    for i in range(len(p0)):
        minimum+=abs(p0[i]-p1[i])
    return minimum

#input the center point and city block distance bd
#output list of points less than equal distance to center
def get_points(center,bd):
    lst=[]
    while center in range(block_distance(center,bd)):
        lst.append(bd)
    return lst

###########################################################################
# Functions for Problem 9
###########################################################################
#INPUT list of numbers
#OUTPUT Boolean if geometric series
def is_geometric_sequence(lst):
    ratio=lst[1]/lst[0]
    if len(lst)>2:
        for i in range(len(lst[1:])):
            if lst[i+1]/lst[i]!=ratio:
                return False
        return True
    else:
        return False

###########################################################################
# Functions for Problem 10
###########################################################################
#INPUT portfolio of stock price, shares, market
#OUTPUT current total value
def value(portfolio, market):
    p_value=0
    m_value=0
    for i in portfolio['stock']:
        p_value+=(portfolio['stock'][i][0]*portfolio['stock'][i][1])
        m_value+=market[i]*portfolio['stock'][i][1]
    return round(((m_value-p_value)/p_value),2)*100

###########################################################################
# Functions for Problem 11
###########################################################################
#INPUT a (possibly empty) list of numbers
#OUTPUT show error (as mentioned in the PDF) or smoothed values
#problem 12
def smooth(lst):
   newlst=[]
   if lst==[]:
       return "AveError: list empty"
   else:
        for i in range(len(lst[1:])):
            newlst.append(round((lst[i]+lst[i+1])/2,2))
        return newlst

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
    
    # # work this by hand
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
    
    A = ((0,-1),2)
    B = ((0,1),1)
    C = ((4,4),1)
    p = get_points(*A)
    q = get_points(*B)
    r = intersection(p,q)
    s = get_points(*C)
    t = intersection(s,q)

    for points in p,q,r,s:
        print(points)

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
