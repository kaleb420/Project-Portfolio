# We have added import math
# It's only needed once
import math

# Problem 1
#input radius r, height h
#return volume
<<<<<<< HEAD
def c(r,h): (2,5)
r = 2 
h = 5
volume=(1/3) * math.pi * r ** 2 * h
roundedvolume=round(volume,2)
print(roundedvolume)
pass


# Problem 2
#input t days
#output oxygen conten percent of it normal level
def f(t): (0)
t=10
Oxygen_Content_10= 100 * ((t ** 2 + 10 * t + 100) / ( t ** 2 + 20 * t + 100))
print(Oxygen_Content_10)
=======
def c(r,h): 
    volume=(1/3) * math.pi * r ** 2 * h
    roundedvolume=round(volume,2)
    return roundedvolume
    
# Problem 2
#input t days
#output oxygen conten percent of it normal level
def f(t):
    Oxygen_Content=100 * ((t**2+10*t+100)/(t**2+20*t+100))
    Rounded_Oxygen_Content=round(Oxygen_Content,2)
    return Rounded_Oxygen_Content
>>>>>>> f30648e1f976d04b38446056e5601b2270e66518

# Problem 3
#input t hours
#return percent watching tv
<<<<<<< HEAD
def P(t): (8)
t=8
People_Watching_TV=.01354 * t ** 4 - .49375 * t ** 3 + 2.58333 * t ** 2 + 3.8 * t + 31.60704
Rounded_Peole_Watching_TV=round(People_Watching_TV, 2)
print(Rounded_Peole_Watching_TV)
=======
def P(t):
   People_Watching_TV=.01354*t**4-.49375*t**3+2.58333*t**2+3.8*t+31.60704
   Rounded_People_Watching_TV=round(People_Watching_TV,2)
   return Rounded_People_Watching_TV
>>>>>>> f30648e1f976d04b38446056e5601b2270e66518

# problem 4
#input x percent
#return millions of dollars
<<<<<<< HEAD
def cost(x): (70)
x=70
Removed_Waste= (0.5 * x) / (100 - x)
print(Removed_Waste)
Rounded_Removed_Waste= round(Removed_Waste,2)
print("$",Rounded_Removed_Waste,"million")
=======
def cost(x):
    Removed_Waste=(.5*x)/(100-x)
    Rounded_Removed_Waste= round(Removed_Waste,2)
    return Rounded_Removed_Waste
>>>>>>> f30648e1f976d04b38446056e5601b2270e66518

# Problem 5
#input dosage a mg and years t
#return child dosage mg
<<<<<<< HEAD
def D(t,a): (500,4)
t,a= 4,500
Dosage= ((t + 1) / 24 ) * a 
Rounded_Dosage= round(Dosage,2)
print(Rounded_Dosage)
=======
def D(t,a):
    Dosage=((t+1)/24)*a
    Rounded_Dosage= round(Dosage,2)
    return Rounded_Dosage
>>>>>>> f30648e1f976d04b38446056e5601b2270e66518

# Problem 6
#input number of susceptible, but healthy children
#output number of the infected children
# use math.ceil() before returning your final answer.
<<<<<<< HEAD
def I(S): 100
S= 100
Infected_Children= (192 * math.log((S/762),2) - S + 763)
Rounded_Infected_Children= math.ceil(Infected_Children)
print(Rounded_Infected_Children)
=======
def I(S):
    Infected_Children=(192*math.log((S/762),2)-S+763)
    Rounded_Infected_Children=math.ceil(Infected_Children)
    return Rounded_Infected_Children
>>>>>>> f30648e1f976d04b38446056e5601b2270e66518

# Problem 7
#input number of items 
#output total cost 
# q > 0
<<<<<<< HEAD
def C(q): 10
q= 10
Cost= .01 * q ** 3 - .6 * q ** 2 + 13 * q + 1000
print(Cost)

#input number of items
#output average cost
def A(q): 5
Average_Cost= Cost/q
print(Average_Cost)
=======
def C(q):
    Cost=.01*q**3-.6*q**2+13*q+1000
    return Cost

#input number of items
#output average cost
def A(q):
    Cost=.01*q**3-.6*q**2+13*q+1000
    Average_Cost=Cost/q
    return Average_Cost
>>>>>>> f30648e1f976d04b38446056e5601b2270e66518

# Problem 8
#input months t=0,...,11
#output items sold x 1000
<<<<<<< HEAD
def hh(t): 5
t = 5
Sales_Model= (532/(1+869 * math.e ** (-1.33 * t)))
Rounded_Sales_Model= math.floor(Sales_Model)
print(Rounded_Sales_Model)
=======
def hh(t):
    Sales_Model=(532/(1+869*math.e**(-1.33*t)))
    Rounded_Sales_Model=math.floor(Sales_Model)
    return Rounded_Sales_Model
>>>>>>> f30648e1f976d04b38446056e5601b2270e66518

# Problem 9
#input time seconds
#output feet
<<<<<<< HEAD
def height(t): 5
t = 5
height= -16 * t ** 2 + 64 * t + 80
print(height)
=======
def height(t):
    height=-16*t**2+64*t+80
    Rounded_height=round(height,2)
    return Rounded_height
>>>>>>> f30648e1f976d04b38446056e5601b2270e66518

# Problem 10
#input t hours
#output percent treatment
<<<<<<< HEAD
def B(t): 10
t = 10 
Treatment= (.44 * t ** 4 + 700) / (.1 * t ** 4 + 7)
Rounded_Treatment= round(Treatment,2)
print(Rounded_Treatment)
=======
def B(t):
    Treatment=(.44*t**4+700)/(.1*t**4+7)
    Rounded_Treatment=round(Treatment,2)
    return Rounded_Treatment
>>>>>>> f30648e1f976d04b38446056e5601b2270e66518

# Problem 11
#input coefficients for quadratic and value
#output True if value is root, False otherwise
<<<<<<< HEAD
def quad(a,b,c,x): 5
a=2
b=5
c=-12
x=-4
quad1 = a * x ** 2 + b * x + c
print(quad1 == 0)
x=3/2
quad2 = a * x ** 2 + b * x + c
print(quad2 == 0)
x=1
quad3 = a * x ** 2 + b * x + c
print(quad3 == 0)
=======
def quad(a,b,c,x):
    quad=a*x**2+b*x+c
    return (quad==0)
>>>>>>> f30648e1f976d04b38446056e5601b2270e66518

# Problem 12 
#input P principle, n times per year, t years, r rate
#output dollars
<<<<<<< HEAD
def R(P,r,n,t): (22000,.06,1,7)
P= 22000
t= 7
n= 1
r= .06
Sinking_Fund= P * ((1+ (r/n)) ** (n * t) - 1) / (r/n)
Rounded_Sinking_Fund=round(Sinking_Fund,2)
print(Rounded_Sinking_Fund)
=======
def R(P,r,n,t):
    Sinking_Fund=P*((1+(r/n))**(n*t)-1)/(r/n)
    Rounded_Sinking_Fund=round(Sinking_Fund,2)
    return Rounded_Sinking_Fund
>>>>>>> f30648e1f976d04b38446056e5601b2270e66518

#Problem 13
#input dimensions w,l,h for width, length, height of a 
# rectangular solid
#output total surface area
<<<<<<< HEAD
def S(w,l,h): (2,4,6)
l=2
w=4
h=6
Surface_Area= 2 * (w * l + h * l + h * w)
print(Surface_Area)
=======
def S(w,l,h):
    Surface_Area=2*(w*l+h*l+h*w)
    return Surface_Area
>>>>>>> f30648e1f976d04b38446056e5601b2270e66518

#Problem 14
#input side s of a square
#output diagonal length 
<<<<<<< HEAD
def square_diagonal(s): 10
s=10 
diagonal_length = math.sqrt(s ** 2 + s ** 2)
print(diagonal_length)


#input diagonal of a square
#output area of largest circle inscribed in square
def circle_area(d): 10
a = circle_area(square_diagonal(s))
print(a)

=======
def square_diagonal(s):
   diagonal_length=math.sqrt(s**2+s**2)
   return diagonal_length

#input diagonal of a square
#output area of largest circle inscribed in square
def circle_area(d):
   radius= math.sqrt(d/2)/2
   circle_area=math.pi * radius ** 2
   return circle_area
>>>>>>> f30648e1f976d04b38446056e5601b2270e66518

#Problem 15
#input earned runs e, innings pitched i, total innings t
#output earned runs average
<<<<<<< HEAD
def ERA(e,i,t): (4,6,9)
e = 4
i = 6
t = 9
ERA= (e/i)*t
RoundedERA = round(ERA,2)
print(RoundedERA)
=======
def ERA(e,i,t):
  ERA=(e/i)*t
  RoundedERA=round(ERA,2)
  return RoundedERA
>>>>>>> f30648e1f976d04b38446056e5601b2270e66518


#problem 16
#input temperature (F), wind speed (mph)
#output wind chill
<<<<<<< HEAD
def T_wc(temp,wind_speed): T_wc
WindChill= 35.74 + .6215 * 2 -35.75 * 5 ** .16 + .4275 * 2 * 5 ** .16
Rounded_Wind_Chill=math.floor(WindChill)
print(Rounded_Wind_Chill)

=======
def T_wc(temp,wind_speed):
   Windchill=35.74+.6215*temp-35.75*wind_speed**.16+.4275*temp*wind_speed**.16
   Rounded_Windchill=math.floor(Windchill)
   return Rounded_Windchill
>>>>>>> f30648e1f976d04b38446056e5601b2270e66518

#problem 17
#input n
#output approximate to n!
<<<<<<< HEAD
def fact_est(n): 10
n= 10
print(math.factorial(n),fact_est(n))
n= n * 10
n=(math.factorial(n),fact_est(n))
print(n)

=======
def fact_est(n):
    n0=math.sqrt(2*math.pi*n)*(n/math.e)**n
    n1=int(n0)
    return n1
>>>>>>> f30648e1f976d04b38446056e5601b2270e66518

#problem 18
#input sphere volune
#output radius of the sphere
<<<<<<< HEAD
def volume_to_radius(v): 10
r=math.sqrt(2) * s
volume_to_radius(r)
print(volume_to_radius)
=======
def volume_to_radius(v):
    radius= ((3*v)/(4*math.pi))**(1/3)
    rounded_radius=round(radius, 2)
    return rounded_radius


>>>>>>> f30648e1f976d04b38446056e5601b2270e66518

def side_max_square(v):
    radius= volume_to_radius(v)
    diameter= radius * 2
    rounded_side_max_square=round(diameter/math.sqrt(3),2)
    return rounded_side_max_square

#problem 19
#input list of market prices per share
#output a tuple containing average price and the last price
def app(market):
    average=sum(market)/len(market)
    last= market [-1]
    return (average,last)

# problem 20
#input n
#ouptut return the value that would be returned from employee's model
def model1(n):
    model1=10*n**3-35*n**2+50*n-24
    return model1

# input n
#ouptut implement the more intutitve model yourself that can represent the values shown in equation 43 in the PDF
def model2(n):
    model2=n**4
    return model2

#input a tuple containing values
#output: tuple of tuples comparing the outputs from both models, as shown in sample output in the PDF.
# Example: ((x0, model1(x0), model2(x0)), (x1,model1(x1), model2(x1)), (x2,model1(x2), model2(x2)), (x3,model1(x3), model2(x3)), ((x4, model1(x4), model2(x4))
def compare_models(inputs):
    compare_models= (inputs[0], model1(inputs[0]), model2(inputs[0])), (inputs[1],model1(inputs[1]), model2(inputs[1])), (inputs[2],model1(inputs[2]), model2(inputs[2])), (inputs[3],model1(inputs[3]), model2(inputs[3])), (inputs[4], model1(inputs[4]), model2(inputs[4]))
    return compare_models




if __name__ == "__main__":
    """
    The following tests are given by us. For example, after completing problem 1, 
    you can uncomment the tests for problem 1 and run the a1.py file to see the output.
    Similarly, you can uncomment the tests for other problems as you complete them.
    
    If you want to do some of your own testing, you can also add them, for example if you want to
    test problem 1 then you can add another print statement and call c() function with your own
    input value to see the output of c() on that value -- print(c(5, 7)) or print(c(4, 47)) etc.
    
    Please remember to comment the test cases before submitting to the Autograder. You can use them 
    as long as you want while testing on your system, but please comment the below test cases before
    submitting to the Autograder.
    """

    #problem 1
    #volume of cone
    # print(c(2,5)) 
    # print(c(3,7))

    #problem 2
    #oxygen content
    # print(f(0))
    # print(f(10))

    #problem 3
    #tv watching
    # print(P(0))
    # print(P(3))
    # print(P(8))

    #problem 4
    #toxic waste
    # print(cost(50))
    # print(cost(70))
    # print(cost(90))

    #problem 5
    # cowling's rule
    # print(D(4,500))
    
    #problem 6
    #flu outbreak
    # S_6 = 100
    # print(I(S_6))
    # S_6 = 300
    # print(I(S_6)) 

    #problem 7
    #average cost
    #make your own inputs/outputs
    
    
    #problem 8
    # print(hh(0))
    # print(hh(5))
    # print(hh(10))

    #problem 9
    # print(height(5))
   
    #problem 10        
    #make your own inputs/outputs

    #problem 11
    #quadratic roots
    # print(quad(2,5,-12,-4))
    # print(quad(2,5,-12,3/2))
    # print(quad(2,5,-12,1))

    # problem 12
    # Sinking Fund
    # P = 22000
    # n = 1
    # t = 7
    # r = 6/100
    # print(R(P,r,n,t))
    # P = 500
    # n = 12
    # t = 20
    # r = 4/100
    # print(R(P,r,n,t))
    # P = 1200
    # n = 4
    # t = 10
    # r = 8/100
    # print(R(P,r,n,t))

    #problem 13
    #make your own inputs/outputs

    #problem 14
    # s_13 = 10
    # a_13  = circle_area(square_diagonal(s_13))
    # print(a_13)

    #problem 15
    # e_14,i_14,t_14 = 4,6,9
    # print(ERA(e_14,i_14,t_14))

    
    #problem 16
    # temp_15, wind_speed_15 = 2,5
    # print(T_wc(temp_15,wind_speed_15))

    #problem 17
    # n0_16 = 10
    # print(math.factorial(n0_16),fact_est(n0_16))
    # n0_16 = n0_16 * 10
    # print(math.factorial(n0_16),fact_est(n0_16))
    
    #problem 18
    #v = 268.08
    #print(volume_to_radius(v), side_max_square(v))
    
    #problem 19
    #market = [40 ,35 ,34 ,38 ,50]
    #print(app(market))
    
    #problem 20
    #inputs = (1,2,3,4,5)
    #print(compare_models(inputs))
    
    