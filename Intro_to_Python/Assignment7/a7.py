import math
import random as rn
import numpy as np
# import matplotlib.pyplot as plt
import csv


########################
# PROBLEM 1
########################
#input point (x,y) and coefficients of line (a,b,c)
#output distance of point to line
#require 3 decimal point precision
def d(point,line):
    x,y=point
    a,b,c=line
    return round((abs(a*x+b*y+c))/math.sqrt(a**2+b**2),3)

########################
# PROBLEM 2
########################
#do not change this function, we have already completed this function for you.
#input list of numbers as strings
#output sorted as numbers using radix sort
def radix (lst,digit_index = 0):
    if lst:
        cluster = [[] for _ in range(10)]
        for number in lst:
            index = int(number[-(digit_index + 1)])
            cluster[index] += [number]
       
        sorted, unsorted = [],[]
        for block in cluster:
            for number in block:
                if len(number) > digit_index + 1:
                    unsorted.append(number)
                else:
                    sorted.append(number)
        return sorted + radix(unsorted, digit_index + 1) 
    else:
        return []

#decimal radix sort
#input decimal (no whole numbers or zero) as string
#output sorted 
#requires radix
def radix_decimal (lst):
    nl=[]
    nll=[]
    nlll=[]
    nllll=[]
    ml=0
    for i in lst:
        if len(i)>ml:
            ml=len(i)-1
    for j in lst:
        nl.append(int(float(j)*(10**ml)))
    for k in nl:
        nll.append(str(k))
    for o in radix(nll):
        nlll.append(str(round(int(o)*(10**(-ml)),3)))
    for p in nlll:
        nllll.append(p.strip('0'))
    return nllll

########################
# PROBLEM 3
########################
#INPUT parameters to LV model (note that we have already written some parts of this function)
#OUTPUT two lists history_rabbit, history_fox of populations
# Replace the marvelous code with the correct code.
def rabbit_fox(br,dr,df,bf,rabbit,fox,time_limit):
    i = 0
    history_rabbit = []
    history_fox = []
    while i < time_limit:
        history_rabbit.append(rabbit)
        history_fox.append(fox)
        new_rabbit=(history_rabbit[-1]+(history_rabbit[-1]*br)-(history_rabbit[-1]*history_fox[-1]*dr))
        new_fox=(math.ceil(history_fox[-1]+(bf*dr*history_rabbit[-1]*history_fox[-1])-(history_fox[-1]*df)))
        rabbit=math.ceil(new_rabbit)
        fox=math.ceil(new_fox)
        i+=1
    return history_rabbit,history_fox

########################
# PROBLEM 4
########################
#INPUT path, filename
#OUTPUT list of parent, child pairs
#CONSTRAINT use csv reader
def get_data(path, filename):
    lst=[]
    complete_path=path+filename
    with open(complete_path) as file:
        for i in file:
            row=i.rstrip().split(',')
            lst.append(row)
    return lst

# We have already completed this function for you.
#input parent name
#output children
#constraint using list comprehension
def get_child(name,data):
    return [child for parent,child in data if parent == name]

#input parent name
#output true if has children
#constraint using list comprehension
def has_children(name,data):
    return [name==parent for parent in data[0]]

#input child name
#output parent of child
#constraint using list comprehension
def get_parent(name,data):
    return [parent for parent,child in data if child==name]

#input child name1, child name2
#output true of children have same parent
#constraint using list comprehension
def siblings(name1,name2,data):
    return [parent==name1 and parent==name2 for parent,child in data]

#input grandparent name1, grandchild name2
#output true if name1 is grandparent to name2
#constraint using list comprehension 
def grandparent(name1,name2,data):
    return [name1==child and name2==parent for child,parent in data]

#input nothing
#output all names
#constraint list comprehension only
def get_all(data):
    return [name[0] and name[1] for name in data ]

#input name1, name2
#output true if name1 and name 2 are cousins, i.e., have the same grandparents
def cousins(name1,name2,data):
    return 

########################
# PROBLEM 5
########################
#input list of numbers
#output a list of two elements i.e. sum and a Boolean vector of the numbers that are summed
def max_adjacent(lst):
    pass

########################
# PROBLEM 6
########################
#driver cost
#input hourly rate, time
#output cost for the driver
def driver_cost(hr_rate,t):
    return hr_rate*t

#input distance,cost
#output operating cost
def operating_cost(distance,speed):
    return (((60+speed)/2)*distance)/100

#total cost
def total_cost(speed,distance,hr_rate):
    return driver_cost(hr_rate,distance/speed)+operating_cost(distance,speed)

#input distance, hourly rate, and acceptable speeds
#output tuple (total cost, optimal speed)
def min_cost(distance,hr_rate,speeds):
    min_c=100000
    for i in range(speeds[0],speeds[1]+1):
        if total_cost(i,distance,hr_rate)<min_c:
            min_c=total_cost(i,distance,hr_rate)
            speed=i
    return min_c,speed

########################
# PROBLEM 7
########################
def get_fish_data(path,name):
    age=[]
    length=[]
    complete_path=path+name
    with open(complete_path, 'r') as file:
        header=file.readline().rstrip().split(',')
        for i in file:
            row=i.rstrip().split(',')
            age.append(float(row[0]))
            length.append(float(row[1]))
    return age,length

#INPUT two lists X values and Y values of data
#RETURN a polynomial of degree three
def make_function(X,Y,degree):
    polyfit=np.polyfit(X,Y,degree)
    return np.poly1d(polyfit)

########################
# PROBLEM 8
########################
#root finding formula for algorithm
def f80(x):
    return math.exp(-x)

def f81(x):
    return math.sqrt(4*x + 7)

def f82(x):
    return math.sqrt(math.sqrt(4*x + 7))

def f83(x):
    return (3*(x**2)+2)**(1/5)

#input function that finds x and initial guess
#output approximate positive root
def approx_root(f, initial_guess):
    pass

########################
# PROBLEM 9
########################
#input sides a,b and angle between
#output length opposite to angle
def cosine_law(a,b,angle):
    return round(math.sqrt(a**2+b**2-2*a*b*math.cos(math.radians(angle))),2)

#intput start time, stop time, speed
#output distance 
def distance(start,stop,speed):
    return (stop-start)*speed

########################
# PROBLEM 10
########################
#do not change this function
#input simple parabola
def f10(x):
    return 12 - x**2

#input interval and function
#output rectangle dimensions of largest area
def op_rect(a,b,f):
    max=0
    x=a
    while x<=b:
        h=f(x)
        w=(x-a)*2
        if h>=0:
            if w*h>max:
                max=w*h
                bw=w
                bh=h
        x+=.01
    return bw,bh

########################
# PROBLEM 11
########################
#various business models
#price 
def p(x):
    return 5-.002*x

#revenue
def R(x):
    return x*p(x)

#cost
def C(x):
    return 3+1.1*x

#profit
def P(x):
    return R(x)-C(x)

#input revenue function and interval of units sold
#output maximal revenuetuple (item,revenue)
def max_revenue(R,a,b):
    rev=-1000000000
    unit=0
    for i in range(a,b+1):
        if P(i)>rev:
            rev=P(i)
            unit=i
    return rev,unit

########################
# PROBLEM 12
########################
#input string and positive integer n
#output a list of the longest string that have no more than n distinct symbols
def max_n(str,n):
    pass

if __name__ == '__main__':
    
        #uncomment to help

        # #problem 1 #####################################
        # line1 = (4,6,-26)
        # point1 = (2,-4)
        # print(d(point1,line1))

            
        #problem 2 #####################################
        # data21 = ["101","10","12","1000","99","1","5", '100', '120', '990', '310', '0', '301', '102', '654']
        # print(radix(data21))

        # data22 = [".301",".101",".20",".1",".12",".654",".99",".31",".309",]

        # print(radix_decimal(data22))
        # d_22 = data22[::]
        # d_22.sort()
        # print(d_22)

        #problem 3 #####################################
        # br = 0.03
        # dr = 0.0004
        # df = 0.25
        # bf = 0.11
        # rabbit = 3000  #initial population size
        # fox = 200  #initial population size
        # time_limit = 2000
        # history_rabbit, history_fox = rabbit_fox(br,dr,df,bf,rabbit,fox, time_limit)


        # for j in range(0,2000,200):
        #     print(j, history_rabbit[j], history_fox[j])

        # # The following code will create the plot in Figure 1. This code is given to
        # # help you with the visualization. 
        # # Please remember to comment it out before submitting to the Autograder. 
        # # Also comment out the "import matplotlib" at the top of this file.

        # plt.plot(list(range(0,time_limit)),history_rabbit)
        # plt.plot(list(range(0,time_limit)),history_fox)
        # plt.xlabel("Time")
        # plt.ylabel("Population Size")
        # plt.legend(["Rabbit","Fox"])
        # plt.title("Lotka-Volterra Model for Rabbit & Fox")
        # plt.show()



        #problem 4 #####################################

        # Only when submitting to the Autograder, leave the path as blank string "", only provide the filename "family.txt"
        # To test on your system, you may need to provide the path as well. We encourage some testing to figure it out. 
        # please remember that on Windows - the path use two back slashes \\, while on MAC and Linux the path use forward slash  /

        data4 = get_data("Assignment7/", "family.txt")
        print(data4)

        print(has_children('0',data4)) #true
        print(has_children('7',data4)) #false
        print(get_child('6',data4))
        print(get_parent('g',data4))
        print(siblings('7','A',data4)) #true
        print(siblings('2','7',data4)) #false
        print(grandparent('0','3',data4)) #true
        print(grandparent('0','7',data4)) #false
        print(get_all(data4))
        print(cousins('3','6',data4)) #true
        print(cousins('3','5',data4)) #false



        # #problem 5 #####################################
        # data5 = [[5,1,4,1,5],[5,6,2,4],[4,5,1,1],[1,5,10,4,1],[1,1,1,1,1]]
        # for d in data5:
        #     print(max_adjacent(d))


        #problem 6 #####################################
        # s_a,s_b = 40,60
        # hr_rate = 14
        # distance = 100
        # print(min_cost(distance,hr_rate,(s_a,s_b)))
        # hr_rate = 15
        # print(min_cost(distance,hr_rate,(s_a,s_b)))

        # problem 7 #####################################

        # Only when submitting to the Autograder, leave the path as blank string "", only provide the filename "fish_data.txt"
        # To test on your system, you may need to provide the path as well. We encourage some testing to figure it out. 
        # please remember that on Windows - the path use two back slashes \\, while on MAC and Linux the path use forward slash  /

        # X7,Y7 = get_fish_data("Assignment7/", 'fish_data.txt')
        # data7 = [[i,j] for i,j in zip(X7,Y7)]
        # print(data7)


        # The following code is for drawing the plot. Please comment it out after testing your solution and before submitting to the Autograder. 
        # Also, comment out the import matplotlib at the top of this file.

        # plt.plot(X7,Y7,'ro')
        # xp7 = np.linspace(1,14,10)
        # degree = 3
        # p3 = make_function(X7,Y7,degree)
        # plt.plot(xp7,p3(xp7),'b')
        # plt.xlabel("Age (years)")
        # plt.ylabel("Length (inches)")
        # plt.title("Rock Bass Otolith")
        # plt.show()


        #problem 8 #####################################
        # x_star = approx_root(f81,4)
        # print(x_star, x_star**2 - 4*x_star - 7)

        # x_star = approx_root(f80,.5)
        # print(x_star, x_star - math.exp(-x_star))


        # x_star = approx_root(f82,4)
        # print(x_star, x_star**4 - 4*x_star - 7)

        # x_star = approx_root(f83,3)
        # print(x_star, x_star**5 - 3*(x_star**2) - 2)

        #problem 9 #####################################
        # Sa = 24
        # Sb = 18
        # start_a = 1
        # start_b = 1.5
        # stop = 3
        # a,b = 34,56
        # print(cosine_law(*(distance(start_a,stop,Sa),distance(start_b,stop,Sb)),a+b))

        # s_a,s_b = 40,60
        # hr_rate = 14
        # distance = 100
        # print(min_cost(distance,hr_rate,(s_a,s_b)))
        # hr_rate = 15
        # print(min_cost(distance,hr_rate,(s_a,s_b)))


        #problem 10 #####################################
        # a10,b10 = 0, 2 * math.sqrt(3)
        # print(op_rect(a10,b10,f10))


        #problem 11 #####################################
        # p_11, u_11 = max_revenue(R,1,1000)
        # print(u_11)    

        # Visualization
        # The visualization code should be commented out before submitting to the Autograder.

        # x11 = np.linspace(1,1299,100)
        # y11 = P(x11)
        # plt.plot(x11,y11,'r')
        # plt.plot(u_11,p_11,'go')
        # plt.xlabel("Units")
        # plt.ylabel("Revenue $MM")
        # plt.title ("Maximizing Revenue")
        # plt.show()



        #problem 12 #####################################
        # data11 = ["aaaba", "abcba", "abbcde","aaabbbaaaaaac","abcdeffg"]
        # for d in data11:
        #     for i in range(1,7):
        #         print(f"{d} with {i} max is\n {max_n(d,i)}")
        
        print()