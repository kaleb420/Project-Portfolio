import math
import random as rn


#problem 1
#input real number
#return real number
def g(x):
    if x!=0:
        g=x+2
    else:
        g=x+1
    return g

#problem 2
#input year 1977-1997
#return percent income or "error: year" if year 
#is outside range (note that there is a single space after the colon in "error: year")
def f(t):
    if 1977<=t<=1997:
        j=t-1977
        if 0<=j<=7:
            cost=(2/7)*j+12
            return cost
        elif 7<j<=10:
            cost=j+7
            return cost
        elif 10<j<=20:
            cost=(3/5)*j+11
            return cost
    else:
        return "YearError: " + str(t)

#problem 3
#input t in the range [0, 2]
#output dollars (round to 2 decimal places)
def h(t):
    h0=110/((1/2)*t+1)
    h1=26*((1/4)*t**2-1)**2+52
    p= round(h0-h1,2)
    if 0<=t<=2:
        return p
    else:
        return("YearError: " +str(t))

#problem 4
#input tuple (a,b,c) coefficients
#output tuple roots (x_1, x_2) where x_1 >= x_2
def q(coefficients):
    a,b,c=coefficients
    x1=((-b)+(b**2-4*a*c)**(1/2))/(2*a)
    x2=((-b)-(b**2-4*a*c)**(1/2))/(2*a)
    if x1>=x2:
        return(x1,x2)
    else:
        return(x2,x1)

#problem 5
#input [arg1,op,arg2,ans]
#output arg1 op arg2 == ans
def eq(lst):
    # arg1,op,arg2,ans=lst
    # for i in op:
        # if arg1, i, arg2==ans
    pass

#problem 6
#input string of COVID symptoms "ABC", "ACB",...,"CBA"
#output 'very likely', 'likely', 'somewhat likely' based on severity
def covid(symptoms):
    A,B,C = symptoms
    if A=="A":
        return("very likely")
    elif A=="B":
        return("likely")
    elif A=="C":
        return("somewhat likely")

#problem 7
#INPUT two numbers
#RETURN maximum of the two
#You cannot use Python's max function
#You must use if, elif, else (or some combination)
def max2d(x,y):
    if x>y:
        return x
    elif y>x:
        return y
    else:
        return x

#INPUT 3 numbers
#RETURN maximum of the three
#You must use your max2D function
def max3d(x,y,z):
    if max2d(x,y)>z:
        return max2d(x,y)
    else:
        return z

#problem 8
#INPUT [name0, name1, votes] where votes is a non-empty list of 0,1
#RETURN a tuple (name, c, t) where name is the winner, c is the number of winning votes
#t is the total votes cast 
def decision(data):
    name0,name1,votes=data
    vote_total=sum(votes)/len(votes)
    if vote_total<.5:
        return name0, len(votes)-sum(votes), len(votes)
    elif vote_total>.5:
        return name1, sum(votes), len(votes)
    else:
        return "tie", sum(votes), len(votes)-sum(votes)

    

#problem 9 
#INPUT three values: all have values or two have values and the remain has None
#OUTPUT for two values, return the computed None variable
#for three values return True or False using isclose(x,y,abs_tol = 0.001)
#remember to convert degrees to radians
def solve(theta,opposite,adjacent):
    if theta==None:
        d=math.degrees(math.atan(opposite/adjacent))
        return d
    elif opposite==None:
        d=math.tan(math.radians(theta))
        return d*adjacent
    elif adjacent==None:
        d=math.tan(math.radians(theta))
        return opposite/d
    elif (theta,opposite,adjacent):
        d=math.degrees(math.atan(opposite/adjacent))
        j=math.isclose(theta,d,abs_tol=.001)
        return j
    else:
        return "Multiple variables unaccounted for"


#problem 10
#input home price and interest rate
#output payment
def future(A, r):
    A=round((home_price*.2)*((1+(r/n))**(n*t)-1)/(r/n),2)
    return A

#problem 11
#input coefficients ax + by > c and a point
#output return True if equation true, false otherwise
def linear_query(a,b,c,point):
    x,y=point
    d=a*x+b*y>=c
    return d



#problem 12
#input time, speed1, speed2 both heading in same direction
#output time train 2 reaches train 1
def train_type1(t0, s1, s2):
    t=round((t0*s1)/(s2-s1),2)
    return t



#input speed 1, speed 2, distance heading towards each other
#output time to reach each other
def train_type2(s1, s2, d):
    t=round(d/(s1+s2),2)
    return t



#problem 13
#input n >= k, use math module
#output nCr
def C(n,k):
   C=math.factorial(n)/(math.factorial(n-k)*math.factorial(k))
   if n>k:
       return(C)


#problem 14
#input side of equilateral triangle
#output area of largest circle inscribes
#use solve from problem 9
def circle(x):
    # side_length=solve[theta=opposite=adjacent=]

    # circle_area=math.pi*r**2
    pass

# problem 15
#input time elapsed on earth
#output relative time to traveler
def time_contract(earth_time):
    c = 186000 #speed of light in mi/sec
    v = .9 * c
    dilated_time=round(earth_time*(1-(v/c)**2)**(1/2),2)
    return dilated_time


# problem 16
#input coefficients of the two linear equations of two variables
#(a1x + b1y + c1 = 0), (a2x + b2y + c2 = 0)
#output tuple (x,y) the solution
def linear_solver(eq1, eq2):
    a1,x,b1,y,c1=eq1
    a2,x,b2,y,c2=eq2
    if a1*x+b1*y+c1==0:
        if a2*x+b2*y+c2==0:
            if a1-a2==0:
                j=b1-b2
                k=c1-c2
                y=-k/j
                x=(-c1-b1*y)/a1
                return (x,y)
            elif a1+a2==0:
                j=b1+b2
                k=c1+c2
                y=-k/j
                x=(-c1-b1*y)/a1
                return (x,y)
            elif b1-b2==0:
                j=a1-a2
                k=c1-c2
                x=-k/j
                y=(-c1-b1*x)/a1
                return (x,y)
            elif b1+b2==0:
                j=a1+a2
                k=c1+c2
                x=-k/j
                y=(-c1-b1*x)/a1
                return (x,y)
        else:
            return "eq2 does not equal 0"
    else: 
        return "eq1 does not equal 0"

#input coefficients of the two linear equations of two variables, and the proposed solution to the equations
#(a1x + b1y + c1 = 0), (a2x + b2y + c2 = 0)
# and tuple (x,y) the solution
#output True if solution works, False otherwise
def confirm(eq1, eq2, solution):
    solution=linear_solver(eq1,eq2)
    if eq1==solution:
        return "True"
    elif eq2==solution:
        return "True"
    else:
        return "False"


#problem 17
#input Booleans representing signal
#output tuple (f,g) 
def circuit(A,B,X,Y):
    input=[0,1]
    for A in input:
        for B in input:
            for X in input:
                for Y in input:
                    if X==1: #start of f formula
                        orange1=0
                    else:
                        orange1=1
                    if B==1 or orange1==1:
                        blue1=1
                    else:
                        blue1=0
                    if X==1 and Y==1: #start of g formula
                        green2=1
                    else:
                        green2=0
                    if green2==1:
                        orange2=0
                    else: 
                        orange2=1
                        if blue1==1 and A==1: 
                            f=1
                        else:
                            f=0
                            if orange2==1 or A==1:
                                g=1
                            else:
                                g=0
                            return f,g

#problem 18
#input three tuples (n1, lst1),...,(n3, lst3) where n is name, 
# lst are accidents reported
# output tuple (nx,ny,nz) where names are ordered by the fewest accidents
# use sum(), but nothing else
# have only one return
def accident_ordering(c1, c2, c3):
    a,alst=c1
    b,blst=c2
    c,clst=c3
    pass


if __name__ == "__main__":
    """
    The code in "__main__" is not being graded, but a tool for you to test 
    your code outside of the unit testing Feel free to add print statements. 
    
    Please comment this testing code before submitting to the Autograder.
    """

    #problem 1 
    # print(g(0))
    # print(g(1))
    # print(g(1.01))

    #problem 2
    # print(f(1976))
    # print(f(1977))
    # print(f(1985))
    # print(f(1988))
    # print(f(2000))

    #problem 3
    # print(h(0))
    # print(h(1))
    # print(h(1.5))
    # print(h(2))
    # print(h(3))

    #problem 4
    # print(q((1,0,-1)))
    # print(q((6,-1,-35)))
    # print(q((1,-7,-7)))

    #problem 5
    # print(eq([14, "/",2, 7]))
    # print(eq([20, "*",19, 381]))
    # print(eq([20, "*",19, 380]))
    # print(eq([2,"**",3,8]))
    # print(eq([1.1,'-',1,.1])) #saw in class this doesn't work! (will return False)

    #problem 6
    # print(covid('ABC'),covid('ACB'))
    # print(covid('BAC'),covid('BCA'))
    # print(covid('CAB'),covid('CBA'))

    #problem 7
    # print(max3d(1,2,3))
    # print(max3d(1,3,2))
    # print(max3d(3,2,1))

    #problem 8
    # data0 = ['B','Z',[0,1,1,0,1,0,0]]
    # print(decision(data0))
    # data1 = ['B', 'Z',[1,0,1]]
    # print(decision(data1))
    # data2 = ['B', 'Z',[1,0,1,0,1,1,0,0]]
    # print(decision(data2))


    #problem 9
    # print(solve(5,None,105600))
    # print(solve(None,9238.9,105600))
    # print(solve(5,9238.8,None))
    # print(solve(5,9238.8,105600))
    # print(solve(5,9100,105600))

    #problem 10
    # home_price, rate = 250000, 6/100
    # t,n = 2,12                         #years, monthly
    # payment = future(home_price,rate)
    # print(f"{n} payments yearly for {t} years requires ${payment}")
    # confirm this achieves 50000
    # A = round(payment*((1 + rate/n)**(t*n)-1)/(rate/n), 2)
    # print(A)

    #problem 11
    # point1 = (-6,0)
    # point2 = (4,-5)
    # print(linear_query(3,-4,12,point1))
    # print(linear_query(3,-4,12,point2))
    
    #problem 12
    # t0_12,s1_12,s2_12 = 2,40,60
    # print(f"Time = {train_type1(t0_12,s1_12,s2_12)} hr")
    # d_12 = 400
    # print(f"Time = {train_type2(s1_12,s2_12,d_12)} hr")

    #problem 13
    # print(C(8,3))

    #problem 14
    # x_14 = 14*math.sqrt(3)
    # print(circle(x_14))

    #problem 15
    # earth_time = [1,25,50,75] #years that elapsed

    # for et in earth_time:
        # print(time_contract(et))

    #problem 16
    # data_16 = [((1,1,-5),(3,-1,-3)), ((-1,-1,5),(2,-1,-4))]

    # for d in data_16:
    # print(linear_solver(*d))
    # print(confirm(*d, linear_solver(*d)))

    #problem 17
    input_17 = [0,1]
    for A in input_17:
        for B in input_17:
            for X in input_17:
                for Y in input_17:
                    print(f"{A} {B} {X} {Y} {circuit(A,B,X,Y)}")

    #problem 18
    # data_18 = [(('a',(1,2)),('b',(1,3)),('c',(1,4))),(('a',(1,2)),('b',(1,4)),('c',(1,3))),
        # (('a',(1,3)),('b',(1,2)),('c',(1,4))),(('a',(1,4)),('b',(1,2)),('c',(1,3))),
        # (('a',(1,3)),('b',(1,4)),('c',(1,2))),(('a',(1,4)),('b',(1,3)),('c',(1,2)))]

    # for d in data_18:
    # print(accident_ordering(*d))