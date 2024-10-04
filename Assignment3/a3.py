import math

#Problem 1

#INPUT n0 start colony size, m growth rate, t time
#RETURN final colony size
def N(n_0, m, t):
    final_colony_size=n_0*math.exp(m*t)
    return final_colony_size

#INPUT t days
#RETURN number of teeth
def N_t(t):
    number_of_teeth=71.8*math.exp(-8.96*math.exp(-.0685*t))
    return math.ceil(number_of_teeth)

#INPUT pressures Pi, Pf 
#RETURN work joules
def W(P_i, P_f):
    work_joules=8.314*300*math.log(P_i/P_f)
    return math.ceil(work_joules)

#INPUT V miles per hour, A area, C_l lift coefficient
#RETURN lbs 
def L(V,A,C_l):
    lbs=.0033*V**2*A*C_l
    return math.ceil(lbs)

###########################################################################
# Functions for Problem 2
###########################################################################
#INPUT coef = (a,b,c)
#RETURN tuple ('up'|'down', (vertex, y-value of vertex) )
###########################################################################
def q(coef):
    a,b,c=coef
    x=-b/(2*a)
    if a>0:
        s="up"
    elif a<0:
        s="down"
    y=a*x**2+b*x+c
    return s, [round(x,2), round(y,2)]

###########################################################################
# Functions for Problem 3
###########################################################################
#INPUT object x and list lst
#RETURN True if object occurs in the list
#CONSTRAINT You cannot use 'x in y' -- must use bounded looping
def m(x,lst):
    for i in lst:
        if i==x: 
            return True
    return False

#INPUT receipt= [[x0,y0],[x1,y1],...,[xn,yn]]
# x is item, y is cost
# tax_rate is the tax on taxable items
# no_tax is a list of items not taxable
#RETURN total amount owed (round values to 2 nearest decimal places)
def amt(receipt, tax_rate, no_tax):
    for i in receipt:
        if i==no_tax:
            return (no_tax)
        elif i==float:
            y=(i*tax_rate)+i
            return round(y,2)


###########################################################################
# Functions for Problem 4
###########################################################################
#INPUT p0 = (x0,y0) p1 = (x1,y1)
#RETURN dictionary y = mx + b
def make_line(p0,p1):
    x0,y0,x1,y1 = *p0,*p1
    m = round((y1 - y0)/(x1 - x0),2)
    b = round(y0 - (m*x0),2)
    return {'m':m, 'b':b}


#INPUT two lines as dictionary
#RETURN a point (x,y) of intersection: "same line", "parallel lines" (x,y) 
#rounded to two places
def intersection(l0,l1): 
    p0,p1=l0
    m = round((p1[1] - p0[1])/(x1 - x0),2)
    b = round(y0 - (m*x0),2)
    p2,p3=l1
    for x in l0:
        y_line0=m*x+b
    for x in l1:
        y_line1=m*x+b
    if y_line0==y_line1:
        return "same line"
    else:
        return "parallel line"

###########################################################################
# Functions for Problem 5
###########################################################################
#INPUT List of numbers
#RETURN Various means or error message

err_msg = ["Data Error: 0 values", "Data Error: 0 in data"]

def arithmetic_mean(nlst):
    if nlst==[] or 0 in nlst:
        return err_msg
    else:
        return sum(nlst)/len(nlst)


def geo_mean(nlst):
    sum_of=0
    if nlst==[] or 0 in nlst:
        return err_msg
    else:
        for i in nlst:
            sum_of+=math.log10(i)
        return 10**(sum_of/len(nlst))


def har_mean(nlst):
    sum_of=0
    if nlst==[] or 0 in nlst:
        return err_msg
    else: 
        for i in nlst:
            sum_of+=1/i
        return len(nlst)/sum_of


def RMS_mean(nlst):
    sum_of=0
    if nlst==[] or 0 in nlst:
        return err_msg
    else: 
        for i in nlst:
            sum_of+=i**2
        return math.sqrt(sum_of/len(nlst))



###########################################################################
# Function for Problem 6
###########################################################################
#INPUT x object, integer y, list of objects
#RETURN true if x occurs at least y times, false otherwise
def occur_at_least(x,y,lst):
    d=0
    for i in lst:
        if i==x:
            d+=1
    if d>=y:
        return True
    else: 
        return False

###########################################################################
# Functions for Problem 7
###########################################################################
#input two objects x,y and list
#returns True if x occurs strictly more than y in lst, False otherwise
def occurs_more(x,y,lst):
    d=0
    for i in lst:
        if i==x:
            d+=1
    if d>y:
        return True
    else: 
        return False


#input two objects x, y and list
#return if the number of times x,y occur in list are equal, then return the list
#if x occurs more than y, then remove the occurrences from the left side until
#their counts are equal, then return the list
#if y occurs more than x, the same procedure
def equal_remove(x,y,lst):
    tmp = lst
    def cnt_occurs(x,lst):
        x_occur=0
        y_occur=0
        for i in lst:
            if i==x:
                x_occur+=0
            elif i==y:
                y_occur+=0
        if x_occur==y_occur:
            return True
        elif lst==[]:
            return True
    
    x_c,y_c = cnt_occurs(x,lst),cnt_occurs(y,lst)

    def re_k(x,cnt,lst):
        tmp,nr = [],0
        for i in lst:
            if x_c<y_c:
                del lst[i]
            if x_c>y_c:
                del lst[i]
        return lst

    
    if x_c < y_c:
        tmp = re_k(y,y_c-x_c,tmp)
    elif x_c > y_c:
        tmp = re_k(x,x_c - y_c,tmp)

    return tmp        


###########################################################################
# Functions for Problem 8
###########################################################################
#INPUT list of numbers
#RETURN True if geometric series, False otherwise
def is_geo(xlst):
    ratio=xlst[1]/xlst[0]
    if len(xlst)>2:
        for i in xlst:
            if xlst[i]/i==ratio:
                return 1
            else:
                return 0
    else:
        return 0

###########################################################################
# Functions for Problem 9
###########################################################################
#INPUT pair of points in 2D
#RETURN distance round to two decimal places
def net_displacement(p0,p1):
    x0,y0=p0
    x1,y1=p1
    distance=round(((x0-x1)**2+(y0-y1)**2)**(1/2),2)
    return distance

#INPUT starting position (x,y) and list of one step directions w,e,s,n that move the positon
#of x,y
#RETURN a tuple final destination, distance, distance from start
def track(start_pos, movement):
    x,y=start_pos
    e=0
    w=0
    s=0
    n=0
    for i in movement:
        if i=='e':
            x=x+1 
        elif i=='w':
            x=x-1
        elif i=='s':
            y=y-1
        elif i=='n':
            y=y+1
        if i=='e':
            e+=1
        elif i=='w':
            w+=1
        elif i=='s':
            s+=1
        elif i=='n':
            n+=1
    distance=round(((start_pos[0]-x)**2+(start_pos[1]-y)**2)**(1/2),2)
    number_of_movements=e+w+s+n
    return (x,y), number_of_movements, distance

###########################################################################
# Functions for Problem 10
###########################################################################
#INPUT pair of tuples from tracking
#RETURN distance betweem two ending places 
def final_distance(m0, m1):
    for i in m0:
        m0_x=i[0]
        m0_y=i[1]
        for j in m1:
            m1_x=i[0]
            m1_y=i[1]
    distance=round(((m0_x-m1_x)**2+(mx_-j[1])**2)**(1/2),2)
    return round(distance,2)


###########################################################################
# Functions for Problem 11
###########################################################################
#INPUT presidential percentage
#RETURN house seats needed
def h_p(pres_per_11):
    h=1/(1-pres_per_11)**3
    pres_per_11=1/(-h**3-h**2-3*h+1)
    return pres_per_11


###########################################################################
# Functions for Problem 12
###########################################################################
#INPUT amt and list of donations
#RETURN tuple: amt, donations left, the amount of the goal left
def go_fund_me(amt,donations):
    returned=[]
    amount_left=sum(donations)-amt
    if sum(donations)-amount_left>=0:
        while sum(donations)-amt>=0:
            returned=donations.append(donations[-1])
            amount_left=sum(donations)
        return amt, returned, amount_left
    elif amount_left<=0:
        return amt, donations, amount_left


###########################################################################
# Functions for Problem 13
###########################################################################
#INPUT credit score cs and list of potential clients [[n0,cd0],[n1,cd1],...,[nm,cdm]] where n is name, cd is a dictionary of unweighted credit values
#RETURN list of people and their score that is strictly greater than cs; if nobody qualifies, then return empty list
def loan(cr, lst):
    P=(lst['P'])
    A=(lst['A'])
    L=(lst['L'])
    N=(lst['N'])
    C=(lst['C'])
    for i in lst:
        for x in i[1]:
            if x=='P':
                new_P=P*.35
            elif x=='A':
                new_A=A*.30
            elif x=='L':
                new_L=L*.15
            elif x=='N':
                new_N=N*.10
            elif x=='C':
                new_C=C*.10
    cs=new_P+new_A+new_L+N+C
    if cs>cr:
        return [lst[0], cr]
    else:
        return []

#Problem 14
#INPUT current temperature T(t) of fish, environment temperature T_e, and initial temperature T_0
#OUTPUT The time (in hours) that elapsed after the murder reported as a float round to six decimal places
#Use the temperatures given in problem description and equation 46 to find k, then use k, and solve for t.
def time(T_t, T_e, T_0):
    # k = ???? #you have to determine this
    k=-.304023
    t=.73397
    T_t=T_e+(T_0-T_e)*math.exp(-k*t)
    return T_t

#Problem 15
# A tuple containing Input height, gravity, initial velocity
# Output (t0,t1) that rocket is at that height (round both values to 2 decimal places)
def rocket(data):
    h,g,v=data
    t0=round(((-v)+math.sqrt((v)**2-4*-g*h))/(2*g),2)
    t1=round(((-v)-math.sqrt((v)**2-4*-g*h))/(2*g),2)
    if t0>=t1:
        return (t0,t1)
    else:
        return (t1,t0)

#problem 16
#input signals A,B
#output signals X,Y
def ad(A,B):
    if B==1:
        not_1=0
    else:
        not_1=1
    if not_1==1 and A==1:
        and_1=1
    else:
        and_1=0
    if A==1:
        not_2=0
    else:
        not_2=1
    if not_2==1 and B==1:
        and_2=1
    else:
        and_2=0
    if and_1==1 or and_2==1:
        x=1
    else:
        x=0
    if A==1:
        not_3=0
    else:
        not_3=1
    if not_3==1 and B==1:
        y=1
    else:
        y=0
    return (x,y)

#problem 17
#this function is completed for you
#taken from homework example
def analytic_fence(size):
    y = math.sqrt(4*((size)/3))
    x = size/y
    return [(x,y), 3*y + 4*x]

#input size of single paddock
#output [(x,y), total_fence_used]
def fence(size):
    y = math.sqrt(4*((size)/3))
    x = size/y
    return math.isclose(((x,y), 3*y + 4*x), size, abs_tol=2)

#problem 18
#INPUT an atomic event
#OUTPUT the numeric value
def X(omega):
    a,b = omega
    # return a + b lecture studing sum of dice
    for i in range(0-7):
        for j in range(0-7):
            return i+j

#INPUT event space and r.v. function
#OUTPUT return the value from the expected_value function. There is nothing returned from random_variable as we are simply printing.
#prints output and expected value
def random_variable(Omega,X):

    def Prob(val,X_):
        n = 0
        for k,v in X_.items():
            n += len(v)
        return len(X_[val])/n

    def expected_value(X_):
        # find the expected value and return that inside this function
        # return expected value
        pass

    X_,n = {},len(Omega)

    for omega in Omega:
        val = X(omega)
        if val in X_.keys():
            X_[val].append(omega)
        else:
            X_[val] = [omega]
    
    print(f"Original Data\n {Omega}")
    print(f"Random variable as dictionary key \n{X_}")
    print(f"Probabilities")
    for val in X_.keys():
        print(f"P(X = {val}) = {Prob(val,X_)}")
    print(f"Expected Value of X \n{expected_value(X_)}")




if __name__ == "__main__":
    """
    If you want to do some of your own testing in this file, 
    please put any print statements you want to try in 
    this if statement.

    You **do not** have to put anything here
    """
    # #problem 1
    # print(N(500,100,4)) 
    # print(N_t(1000))
    # print(W(10,1))
    # print(L(33.8,512,0.515))

    #problem 2
    # print(q((-2.6,7.6,-10)))
    # print(q((1,-10.2,26.01)))

    #problem 3
    # receipt = [[1,1.45],[3,10.00],[2,1.45],[5,2.00]]
    # tax_rate,no_tax = 7/100, [33,5,2]
    # print(amt(receipt,tax_rate, no_tax))
    # print(amt(receipt,10/100,[]))

    # #problem 4
    # p0 = (32,32)
    # p1 = (29,5)
    # p2 = (15,10)
    # p3 = (49,25)
    # p4 = (15,30)
    # p5 = (50,15)
 
    # l0,l1 = make_line(p0,p1),make_line(p2,p3)
    # print(intersection(l0,l1))
    # l0 = make_line(p4,p5)
    # print(intersection(l0,l1))
    
    # p6,p7,p8 = (0,0),(1,1),(2,2)
    # p9,p10 = (0,1),(1,2)
    # print(intersection(make_line(p6,p7),make_line(p7,p8))) # same line
    # print(intersection(make_line(p6,p7),make_line(p9,p10))) # parallel lines

    #problem 5
    # print(arithmetic_mean([]))
    # print(arithmetic_mean([1,2,3]))
    # print(geo_mean([]))
    # print(geo_mean([2,4,8]))
    # print(har_mean([]))
    # print(har_mean([1,2,3]))
    # print(har_mean([1,2,0]))
    # print(RMS_mean([1,3,4,5,7]))

    #problem 6
    # data6 = [[1,4,[1,2,1,2,1,1]], [1,3,[1,2,1,2,1,1]],
    #     [1,4,(1,2,1,2,1,0)], ]

    # for d in data6:
    #     print(occur_at_least(*d))

    #problem 7
    # lst = [2,2,3,1,2,1,1,2]
    # print(occurs_more(1,2,lst))
    # print(occurs_more(2,3,lst))
    # print(occurs_more(2,3,[]))
 

    # print(equal_remove(1,2,lst))
    # print(equal_remove(1,3,lst))
    # print(equal_remove(2,3,lst))
    # print(occurs_more(2,3,(equal_remove(2,3,lst))))

    # #problem 8
    # xlst = [1/2,1/4,1/8,1/16,1/32]
    # print(is_geo(xlst))
    # xlst = [1,-3,9,-27]
    # print(is_geo(xlst))
    # xlst = [625,125,25]
    # print(is_geo(xlst))
    # xlst = [1/2,1/4,1/8,1/16,1/31]
    # print(is_geo(xlst))
    # xlst = [1,-3,9,-26]
    # print(is_geo(xlst))
    # xlst = [625,125,24]
    # print(is_geo(xlst))
    # print(is_geo([1/2,1/4]))

    # #problem 9
    # data_m9 = [[(0,0), list(10*'n' + 15*'e' + 10*'s'+15*'w')],
    #       [(0,0), list(3*'n' + 4*'e')],
    #       [(1,2), list(3*'s' + 4*'w')]]

    # for d in data_m9:
    #     print(track(*d))

    #problem 10
    # data_m10 = [[(0,0), list(10*'n' + 15*'e' + 10*'s'+15*'w')],
    #       [(0,0), list(3*'n' + 4*'e')],
    #       [(1,2), list(3*'s' + 4*'w')]]

    # print(final_distance(track(*data_m10[1]),track(*(data_m10[2]))))

    # #problem 11
    # pres_per_11 = 60/100
    # print(h_p(pres_per_11))
    # for pres_per in range(0,100,10):
    #     print(h_p(pres_per/100))

    # #problem 12
    data12 = [[100,[10,15,20,30,29,13,15,40]],
        [100,[]],
        [100,[30,4]]]

    for d in data12:
        print(go_fund_me(*d))
    
    print(go_fund_me(50, [45,47,78]))

    #Problem 13
    # data = [['x',{'P':600, 'L':700,'A': 500, 'N': 170, 'C': 250}],
    #     ['y',{'P':550, 'L':720,'A': 500, 'N': 230, 'C': 250}],
    #     ['b',{'P':560, 'L':710,'A': 500, 'N': 221, 'C': 250}],
    #     ['c',{'P':800, 'L':700,'A': 200, 'N': 100, 'C': 150}],
    #     ['a',{'P':800, 'L':800,'A': 600, 'N': 250, 'C': 150}],
    #     ['z',{'P':800, 'L':800,'A': 500, 'N': 250, 'C': 150}]]
    # print(loan(550,data))

    #problem 14
    #initial scene of the crime data
   
    # no_alibis = {"Ursala":[3,4],"Shilah":[2,2.5],"Kaiser":[1,2]}
    # T_t = 81
    # T_e = 65
    # T_0 = 85
    # time_discovered = 4 #PM Dr. D's living room
    # suspects = []

    # time_of_murder = time_discovered - time(T_t, T_e, T_0)
    # for name,times in no_alibis.items():
    #     start,end = times
    #     if start <= time_of_murder <= end:
    #         suspects.append(name)

    # print(f"The suspect(s) {suspects}")

    #problem 15
    # data_15 = (180, -16, 120)
    # print(rocket(data_15))

    #problem 16
    # for i_16 in [(0,0),(0,1),(1,0),(1,1)]:
    #     print(ad(*i_16))
    
    #problem 17
    # size = 900
    # print(f"analytic {analytic_fence(size)}")
    # print(f"non-analytic {fence(size)}")

    # size = 1000
    # print(f"analytic {analytic_fence(size)}")
    # print(f"non-analytic {fence(size)}")

    #problem 18
    # build event space
    # Omega = []
    # for i in range(1,7):
    #     for j in range(1,7):
    #         Omega.append((i,j))
    # #call function to produce output
    # #does not return a value
    # random_variable(Omega,X)