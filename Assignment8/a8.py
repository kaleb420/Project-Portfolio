import numpy as np
import random as rn
import matplotlib.pyplot as plt
import matplotlib
import math
import matplotlib.cm as cm




###############################
## PROBLEM ONE               ##
###############################
#input lst of integers, non-negative integer
#output sort of tuples [(s,[s0,s1,...]), (t,[t0,t1,...])] where s = sum([s0,s1,...])
#using the |k-s| ...
def kns(lst, k = 0):
    nlst=[]
    sorted=[]
    for i in range(len(lst)+1):
        for j in range(len(lst)+1):
            for k in range(len(lst)+1):
                if k!=0:
                    if lst[i:j:k]!=[] and (sum(lst[i:j:k]),lst[i:j:k]) not in nlst:
                        nlst+=[(sum(lst[i:j:k]),lst[i:j:k])]
 
###############################
## PROBLEM two               ##
###############################
#recursive choice
def c_2(n,m):
    if m == 0 or n == m:
        return 1
    else:
        return c_2(n-1, m) + c_2(n-1, m-1)

#input n >= 1
#output recursion
def B(n):
    if n==0:
        return 1
    else:
        return -(sum([c_2(n+1,i)*B(i) for i in range(n)])/(n+1))

###############################
## PROBLEM three             ##
###############################
#INPUTS ith candle, starting value of x, default width, and the four critical values: open, close, max_p, min_p.  
#RETURN three tuples: (point, width, height, color), topline, bottomline
#topline ((xt0,yt0),(xt1,yt1)) line from max to top middle of box
#bottomline ((xb0,yb0),(xb1,yb1)) line from min to bottom middle of box
def make(i,start,width_default,d):
    open=d[0]
    close=d[1]
    max_p=d[2]
    min_p=d[3]
    height=abs(close-open)
    if close>open:
        color='green'
    elif close<open:
        color='red'
    if color=='green':
        top=close
        bottom=open
        y=open
    elif color=='red':
        top=open
        bottom=close
        y=close
    point=start,y
    center=(width_default/2)+start
    top_line=((center,top),(center,max_p))
    bottom_line=((center,bottom),(center,min_p))
    return (point,width_default,height,color),top_line,bottom_line

###############################
## PROBLEM four              ##
###############################
#input list of numbers
#output list of permuations -- order does not matter
def permutation(lst):
    nlst=[]
    if len(lst)==0:
        return [[]]
    for i in range(len(lst)):
        first=lst[i]
        remainder=lst[:i]+lst[i+1:]
        for j in permutation(remainder):
            nlst.append([first]+j)
    return nlst

###############################
## PROBLEM five              ##
###############################
class CN:
    def __init__(self, real=0,imag=0):
        self.real = real
        self.imag = imag
    
    def sgn(self,n):
        return 1 if n >= 0 else -1
    
    def __abs__(self):
        return math.sqrt(self.real**2 + self.imag**2)
    
    def __str__(self):
        return f"({self.real} {'+' if self.sgn(self.imag) == 1 else '-'} {abs(self.imag)}j)"

    def get_real(self):
        return self.real
    
    def get_imag(self):
        return self.imag

    def set_real(self,new_real_value):
        self.real = new_real_value

    def set_imag(self,new_imag_value):
        self.imag = new_imag_value

    def __add__(self, right_object):
        if isinstance(right_object,int) or isinstance(right_object,float):
            real = self.real + right_object
            return CN(real,self.imag)
        else:
            return CN(self.real + right_object.get_real(), self.imag + right_object.get_imag())

    def __radd__(self,right_object):
       return CN(self.real + right_object, self.imag)

          
    def __sub__(self, right_object):
        real = self.real - right_object.get_real()
        imag = self.imag - right_object.get_imag()
        return CN(real,imag)
    
    def __mul__(self, right_object):
        x,y = self.real, self.imag
        a,b = right_object.get_real(), right_object.get_imag()
        r_ = x*a - y*b
        i_ = x*b + y*a
        return CN(r_, i_)
    
    def __pow__(self,power):
        return CN((self.real**power),(self.imag**power))
        
    def __truediv__(self,divisor):
        pass

 


###############################
## PROBLEM six               ##
###############################
class fraction:
    def __init__(self,numerator,denominator):
        self.numerator = numerator
        self.denominator = denominator
        self.reduce()

    def get_numerator(self):    
        return self.numerator
    
    def get_denominator(self):
        return self.denominator
 
    def reduce(self):
        def gcd(a,b):
            while b != 0:
                a,b = b,a % b
            return a

        cf = abs(gcd(self.denominator,self.numerator))
        self.denominator//=cf 
        self.numerator//=cf
    
    def __str__(self):
        return f"({self.get_numerator()}/{self.get_denominator()})"
    
    def __add__(self,other):
        return fraction(((self.numerator*other.denominator)+(self.denominator*other.numerator)),(self.denominator*other.denominator))

    def __mul__(self,other):
        return fraction((self.numerator*other.numerator),(self.denominator*other.denominator))

###############################
## PROBLEM seven             ##
###############################
#the dictionary for the transation
aa_d = {}

#the list to store the contents of the FASTA file
DNA_d = []

#the correct translation
actual = "PLHSPHPANFCVFSRD-IPYSEHLRRGALDPGRFRGPRSELSEIERARSRDLRRGPGPPGGEAAARRPLEAAGPLAGPRRRSGVAGRGGFQRGDGAVRGGPGAGARPVEEAGQQRRRLHDRGPGKVRQAGRPRPQGPSLPKPPGRASPTFLSQDLPGFPRHEDLLLPPGPEPRLLTSQSPRPEGGGRAEPRRGAPGRPTPRAVRAEPPARVPAASGPGQLPGERLPCWAPVPGRAPAGWVRGACGAGAGE-ALSARRSSWATACW-PSPGTTPETSAPRCRRRWTSS-ATLSRRWFPSTAELWVGGRGIPRRPSPCLSKASPRSSLLAVLSRGQDARGRR"

# INPUT path and file name of amino acid file
# RETURN a dictionary 
# Key is a tuple (c0, c1, ... , cn) where ci are codons
# Value is a pair [name, abbreviation] for the amino acid
# make sure to close the file
def get_amino_acids(path, filename):
    key=[]
    complete_path=path+filename
    with open (complete_path,'r') as file:
        for i in file:
            row=i.rstrip().split(',')
            DNA_d.append([row[0],row[1]])
            key.append(row[2:])
    for j in range(len(DNA_d)):
        aa_d[tuple(key[j])]=DNA_d[j]
    return aa_d

#INPUT path and file name of DNA sequence file
#RETURN a list [header, DNA]
#header is first line in the file
#DNA is a string of letters from remainder of file
#no whitespace
#make sure to close the file
def get_DNA(path, filename):
    complete_path=path+filename
    with open(complete_path,'r') as file:
        header=file.readline().rstrip().split(',')
        for i in file:
            row=i.rstrip().split(',')
        header+=row
    return header

# INPUT A list containing our FASTA file and the dictionary obtained from get_amino_acids
# RETURN a string representing the protein
# using the dictionary
def translate(DNA_d, thedict):
    pass

###############################
## PROBLEM eight             ##
###############################
#input function and epsilon
#output lambda expression (derivative)
def derivative(f, epsilon):
    return lambda x: (f(x+epsilon)-f(x-epsilon))/(2*epsilon)
    
#leave as is
def f(x):
    return x**2 - 3*x

if __name__ == "__main__":

    #problem 1
    lst = [1,2,3]
    print(kns(lst,0))
    print(kns(lst,3))
    print(kns(lst,sum(lst)))
    print(kns([1,2,1],2))
    
    
    #problem 2
    # for i in range(6):
    #     print(f"B({i}) == {B(i)}")
    # B(0) = 1
    # B(1) = -0.5
    # B(2) = 0.16666666666666666
    # B(3) = -0.0
    # B(4) = -0.033333333333333305
    # B(5) = -7.401486830834377e-17


    #problem 3
    # data = [[20,15,32,10],[10,14,15,9],[22,23,27,9],[15,16,16,15],[26,12,30,2],[5,30,40,4]]
    # fig = plt.figure()
    # ax = fig.add_subplot(111)
    # start = 0
    # default_width = 10
    # for i in range(len(data)):
    
    #     candle_box,top_line,bottom_line = make(i,start,default_width,data[i])
    #     print(candle_box)
    #     ax.add_patch(matplotlib.patches.Rectangle(*candle_box[0:3],color = candle_box[3]))
    #     plt.plot([x for x,_ in top_line],[y for _,y in top_line],'black')
    #     plt.plot([x for x,_ in bottom_line],[y for _,y in bottom_line],'black')
    #     start += default_width

    # plt.xlabel("time (hour)")
    # plt.ylabel("Stock X price")
    # plt.title("Candlestick for Stock X mm/dd/yyyy")  
    # plt.xlim([0, 60])
    # plt.ylim([0, 35])
    # plt.show()

    #problem 4
    # print(permutation([1]))
    # print(permutation([1,2]))
    # print(permutation([1,2,3]))
    # print(permutation([1,2,3,4]))


    #problem 5
    w = CN(1,2)
    x = CN(2,1)

    y = complex(1,2)
    z = complex(2,1)

    for i in range(5):
        print(i, w**i, y**i)
    print(w/x,y/z)


    # #uncomment to see mandelbrot
    # Remember to comment out the following plotting code and also the import of matplotlib before submitting to the Autograder.
    
    # MAX_ITER = 300
    # width, height = 500, 500
    # xmin,xmax = -2.0,.5 
    # xwidth = xmax - xmin 
    # ymin, ymax = -1.0,2.0
    # yheight = ymax - ymin - 1

    # def mandelbrot(c):
    #     z = n = 0
    #     while abs(z) <= 2 and n < 100:
    #         z = z**2 + c
    #         n += 1
    #     return n

    # m_ = np.zeros((width, height))
    # X = list(range(width))
    # for x in range(width):
    #     for y in range(height):
    #         c = CN((x/width)*xwidth + xmin,(y/height)*yheight + ymin)
    #         v = mandelbrot(c)
    #         m_[x,y] = v if v < (MAX_ITER/7) else 1 - math.sqrt(v/MAX_ITER)

    # fig, ax = plt.subplots()
    # ax.imshow(m_, aspect='equal', interpolation='nearest', cmap=cm.inferno )
    # plt.axis ('off')
    # plt.show()

    #problem 6
    # x = fraction(2*3*4,4*3*5)
    # y = fraction(2*7,-7*2)
    # z = fraction(-13,-14)
    # a = fraction(-13*2*7,14)
    # print(x, y, z, a)
    # print(f"{x} + {y} == {x + y}")
    # print(f"{x}*{y} == {x * y}")
    # b,c = fraction(1,2),fraction(3,5)
    # print(f"{b} + {c} == {b + c}")
    
    
    # problem 7
    # Only when submitting to the Autograder, leave the path as blank string "", only provide the filename "DNA.txt" or "amino_acids.txt"
    # To test on your system, you may need to provide the path as well. We encourage some testing to figure it out. 
    # please remember that on Windows - the path use two back slashes \\, while on MAC and Linux the path use forward slash  /
        
    # fn1, fn2 = "amino_acids.txt", "DNA.txt"
    # print(fn1,fn2)
    
    # aa_d = get_amino_acids("Assignment8/", fn1)
    # DNA_d = get_DNA("Assignment8/", fn2)
    # protein = translate(DNA_d)

    # # print("Dictionary")
    # print(aa_d)
    # print("FASTA file")
    # print(DNA_d)
    # print("Translations match:", str(protein == actual))

    # #should return "PLHS"    
    # print(translate(["nothing", "CCACTGCACTCA"]))

    # #should returns "D-"
    # print(translate(["nothing", "GACTAA"]))

    
    # # problem 8
    # data = 3 
    # epsilon = 10e-8
    # print((derivative(f,epsilon)(data)))
    # f_prime = derivative((lambda x:x**2-3*x),epsilon)
    # print(f_prime(data))

    # # uncomment to see the AI plot and your derivative in action!
    # # Remember to comment out the following plotting code and also the import of matplotlib before submitting to the Autograder.
    # # The following plotting code makes use of your derivative function.
    # N = 50
    # x = np.linspace(1,14,100)
    # gm = np.zeros(N)
    # r = np.zeros(N)
    # def mean(lst):
    #     s_ = 0
    #     N = len(lst)
    #     for i in range(N):
    #         s_ += lst[i]
    #     m_ = round(s_/N,2)
    #     return m_

    # def residuals(lst,m):
    #     s_ = 0
    #     N = len(lst)
    #     for i in range(N):
    #         s_ += (lst[i] - m)**2
    #     m_ = (1/2)*(s_/N)
    #     return m_
    # data = [1,1,2,6,10,12,13,14]

    # def update(w,data):
    #     eta = .2
    #     epsilon = 0.00001
    #     return w - eta*(derivative(lambda x:residuals(data,x),epsilon)(w))

    # m_ = mean(data)
    # fmean = 1
    # for i in range(N):
    #     gm[i] = fmean
    #     r[i] = residuals(data,fmean)
    #     print(fmean,residuals(data,fmean))
    #     fmean = update(fmean,data)

    # print(gm[-1])
    # print(m_)
    # plt.plot(gm,r,'bo')
    # for i in range(1,N):
    #     plt.plot([gm[i-1],gm[i]],[r[i-1],r[i]],'b--')
    # plt.plot(m_,residuals(data,m_),'ro')
    # plt.xlabel("Possible means")
    # plt.ylabel("Error")
    # plt.title(f"Using AI to search for the best mean {gm[-1]}")
    # plt.show()


    print()

