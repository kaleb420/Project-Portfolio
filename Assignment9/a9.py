import matplotlib.pyplot as plt
import numpy as np
import sqlite3
import random as rn
from scipy.stats import pearsonr
from sklearn.cluster import KMeans

# problem 1
#translates a random int into a step along the random walk
#parameters: int i for the step index, numpy array x for tracking the left/right location at index i, parameter constant is for our internal use.
#numpy array y for tracking the forward/backward location at index i
#return: none
# Don't change the parameter "constant", you have to only work with x, y and i.
# Comment the import of matplotlib before submitting to the Autograder
def step(x, y, i, constant):
    rn.seed(constant)
    direction = rn.randint(1,4) #leave this
    # TODO: implement this function
    pass

#Do not change -- visualization
def graphit(x,y,n):
    plt.title("Random {0} Walk\nLast Location {1},{2}".format(n,int(x[n-1]),int(y[n-1])) )
    plt.plot(x,y) 
    plt.plot([x[1],x[1]],[y[1]-10,y[1]+10], "b-")
    plt.plot([x[1]-10,x[1]+10],[y[1],y[1]], "b-")
    plt.plot([x[n-1]-10,x[n-1]+10],[y[n-1],y[n-1]], "r-")
    plt.plot([x[n-1],x[n-1]],[y[n-1]-10,y[n-1]+10], "r-")
    plt.show() 


#Problem 2
# Replace the pass statements with correct code implementation
class Line:
    def __init__(self, **kwargs):
        self.b=kwargs
        self.m=kwargs

    def build_lambda(self):
        self.fn=lambda x: self.m*x+self.b
    
    def sign(self):
        if self.b > 0:
            return f"+ {round(self.b,2)}"
        elif self.b < 0:
            return f"- {round(abs(self.b),2)}"
        else:
            return ""

    def get_slope_intercept(self):
        return self.m,self.b

    def __and__(self, right_line):
        if self.fn==right_line.fn:
            return "same"
        elif self.m==right_line.m and self.fn!=right_line.fn:
            return "parallel"
        
    def __call__(self, arg):
        return lambda x: arg.m*x+arg.b
    
    def __str__(self):
        return f"y = {'' if self.m == 1 else round(self.m,2)}x {self.sign()}"


#Problem 3
# We have provided the file "mydatabase.db" with the HW.
# When submitting to the Autograder, use as as i.e. sqlite3.connect("mydatabase.db") function.

# Some tips:
# When testing on your system, if the sqlite3.connect("mydatabase.db") does not work because Python can't find the database file then you may need to 
# provide the absolute path i.e. sqlite3.connect("replace_with_absolute_path/mydatabase.db")
# We encourage some testing to figure it out. 
# Remember that on Windows - the path use two back slashes \\, while on MAC and Linux the path use forward slash  / 

connection = sqlite3.connect("mydatabase.db")
my_cursor = connection.cursor()
my_cursor.execute("""SELECT name FROM sqlite_master WHERE type='table'""")
if not (list(my_cursor.fetchall())):
    
    # print('Building Weather table...')
    my_cursor.execute(''' CREATE TABLE Weather (City text, State text, High real, Low real)''')
    my_cursor.execute("INSERT INTO Weather Values('Phoenix', 'Arizona', 105, 90)")
    my_cursor.execute("INSERT INTO Weather Values('Tucson', 'Arizona', 101, 92)")
    my_cursor.execute("INSERT INTO Weather Values('Flag Staff', 'Arizona', 105, 90)")
    my_cursor.execute("INSERT INTO Weather Values('San Diego', 'California', 77, 60)")
    my_cursor.execute("INSERT INTO Weather Values('Albuquerque', 'New Mexico', 80, 72)")
    my_cursor.execute("INSERT INTO Weather Values('Nome', 'Alaska', 64 ,-54)")
else:
    print('Weather Table Exists')

data = [
    ('Phoenix', 'Arizona', 105, 90),('Tucson', 'Arizona', 101, 92),
    ('Flag Staff', 'Arizona', 105, 90), ('San Diego', 'California', 77, 60),
    ('Alguquerque', 'New Mexico', 80, 72), ('Nome', 'Alaska', 64 ,-54)
]

# We have completed query1 for you.
def query1(db_cursor):
    temp = []
    for i in db_cursor.execute("SELECT * FROM Weather"):
        temp.append[i]
    return temp
    

def query2(db_cursor):
    pass

def query3(db_cursor):
    pass

def query4(db_cursor):
    pass

def query5(db_cursor):
    pass

def query6(db_cursor):
    pass

def query7(db_cursor):
    pass



# problem 4
def correlation4(data):
    a=[]
    b=[]
    c=[]
    d=[]
    corr=[]
    header=data[0]
    data.remove(data[0])
    for i in data:
        a.append(i[0])
        b.append(i[1])
        c.append(i[2])
        d.append(i[3])
    lst=[a,b,c,d]
    for k in range(len(lst)):
        for j in range(len(lst)):
            if k<j:
                correlation,pv=pearsonr(lst[k],lst[j])
                corr.append([header[k],header[j],float(correlation)])
    corr.sort(key=lambda x: x[2])
    return corr

# problem 5
def my_cluster(data,k):
    kmeans=KMeans(n_cluster=k, random_state=0,n_init='auto').fit(data)
    return kmeans

# Do not change -- for visualization
def my_plot(data,cluster_object):
    fig, ax = plt.subplots()
    X = np.array(data)
    colors = ["#4EACC5", "#FF9C34"]
    centroid_colors = ['red','blue']

    for i,c in zip(range(2),colors):
        plt.scatter(X[cluster_object.labels_ == i,0],X[cluster_object.labels_ == i,1],color = colors[i])
        cntdx,cntdy = cluster_object.cluster_centers_[i]
        plt.scatter(cntdx,cntdy,color = centroid_colors[i], marker='.')
        plt.text(cntdx-1,cntdy-1,f"{cntdx,cntdy}")
    plt.show()



#TEST CASES
if __name__ == "__main__":

    #problem 1
    # #number of steps
    # n = 100000   #You should change the number of steps to see
    #             #to see how it affects the plot
    # x = np.zeros(n) 
    # y = np.zeros(n) 
    # constant = 54

    # #fill array with step values 
    # for i in range(1,n):
    #     step(x,y,i, constant+i)
    # graphit(x,y,n)


    # #problem 2
    # x = Line(points = ((-2,3),(3,5)))
    # y = Line(point_intercept = ((-2,3), 3.8))
    # z = Line(function = '0.4x + 3.8')
    # a = Line(point_slope = ((-2,3),.4))
    # b = Line(function = 'x - 3')
    
    # print(x(2),x)
    # print(y(2),y)
    # print(z(2),z)
    # print(a(2),a)
    # print(b(2),b)

    # M1 = Line(function = 'x + 3')
    # N2 = Line(function = '-2x + 12')
    # P3 = Line(function = '-2x + 11')
    # print(M1 & N2)
    # print(M1 & M1)
    # print(N2 & P3)

    # l1 = Line(function = 'x + 1')
    # l2 = Line(function = 'x - 5')
    # print(f"{l1} intersect {l2} yields {l1 & l2}")


    # #problem 3
    # # QUERY 1 Select all the tuples
    # print("Query 1")
    # print(query1(my_cursor))
    # print("List Comprehension: ", data)


    # # QUERY 2 
    # # Select All the tuples where the high temperature is less than 80
    # print("\nQuery 2")
    # print(query2(my_cursor))
    # print("List Comprehension: ", [d for d in data if d[2] < 80 ])


    # # QUERY 3 
    # # Select All the cities where the low temperature is greater than the low of Albuquerque 
    # print("\nQuery 3")
    # print(query3(my_cursor))
    # print("List Comprehension: ",[d[0] for d in data if d[3] > [d[3] for d in data if d[0] == 'Alguquerque'][0]])


    # # QUERY 4 
    # # Select the city and temperature with the smallest low temperature 
    # print("\nQuery 4")
    # print(query4(my_cursor))
    # print("List Comprehension: ",[(d[0],d[3]) for d in data if d[3] in (sorted(data, key = lambda x:x[3])[0])])


    # # QUERY 5 
    # # Select the city temperature with the largest high temperature 
    # print("\nQuery 5")
    # print(query5(my_cursor))
    # print("List Comprehension: ",[(d[0],d[2]) for d in data if d[2] in (sorted(data, key = lambda x:x[2],reverse=True)[0])])


    # # QUERY 6 
    # # Display the average High and Low temperatures
    # # You are not allowed to use Avg()
    # print("\nQuery 6")
    # print(query6(my_cursor))
    # print("List Comprehension: ", [(sum([d[2] for d in data])/len(data),sum([d[3] for d in data])/len(data))])


    # # QUERY 7 
    # # Give the counts of cities by their Low temperatures
    # print(query7(my_cursor))
    # print("List Comprehension: ", sorted([(i,list(map((lambda x: x[3]),data)).count(i)) for i in set(map((lambda x: x[3]),data))]))

    
    # problem 4
    # data4 = [('A', 'B', 'C', 'D'), (30, 31, 28, 932), (1, 2, 9, 7), (33, 35, 6, 1127), (14, 15, 18, 213), (5, 7, 3, 33), (7, 9, 29, 61), (19, 21, 15, 383), (10, 12, 16, 113)]
    # print(data4)
    # print(correlation4(data4))


    # problem 5
    # data_from_class = [(2,5),(1,5),(1,10), (10,1),(9,2), (12,15),(11,15),(5,5), (6, 6),(4,3)]

    # cluster_object = my_cluster(data_from_class,2)
    # print(cluster_object.cluster_centers_)
    # print(cluster_object.labels_)
    # my_plot(data_from_class,cluster_object)

    # # data for homework
    # data5 = [(30, 31, 28, 932), (1, 2, 9, 7), (33, 35, 6, 1127),
    # (14, 15, 18, 213), (5, 7, 3, 33), (7, 9, 29, 61), 
    # (19, 21, 15, 383), (10, 12, 16, 113)]

    # datac = [(i[0],i[1]) for i in correlation4(data)]
    # cluster_object = my_cluster(datac, 2)
    # print(cluster_object.cluster_centers_)
    # print(cluster_object.labels_)
    # my_plot(data,cluster_object)
    
    print()