import webbrowser
import matplotlib.pyplot as plt
import pandas as pd
from sklearn import tree
import random as rn


##########################
#            
#       problem 1
#
##########################
#do not change the following function code
cnt = 0
def weigh(x,y):
    global cnt
    cnt+=1
    return sum(x) == sum(y)

#input list of numbers
#returns list of 3 list equal size + empty, [x], [x,y]
def split_weight(lst):
    pass

#input recursive funciton takes a list of coins with one fake
#returns fake
#only use weigh for len(list) > 3
def find(lst):
    pass

##########################
#            
#       problem 2
#
##########################
#html class
class my_HTML:
    def __init__(self):
        self.top = "<html><head></head><body>"
        self.body = "" #the contents of the page
        self.bottom = "</body></html>"
    
    #use switch to look for h2
    def add_element(self,content,**tag):
        pass
    
    #overload + 
    #must return self to cascade
    def __add__(self, image):
        pass

    #makes HTML page
    def make_page(self):
        pass
    
    #printable
    def __str__(self):
        return self.make_page()

    #save as name + .html
    def save(self,name):
        pass

#input english name (string)
#output egyptian name (string)
def translate(name):
    pass

#input letter
#returns unique file name of jpg image for that letter
def get_image_filename(letter):
    pass

#do not change
def add_hieroglyphs(page,name):
    egypt_name = translate(name)
    for h in egypt_name:
        page = page + get_image_filename(h)



##########################
#            
#       problem 3
#
##########################
#input file name
#output tree model from skicit learn 
def build_tree_model(credit_data):
    pass

##########################
#            
#       problem 4
#
##########################
#input flips and model
#the product of the flips
def likelihood(flips,model):
    num=0
    while len(flips)>num:
        model[flips[num]]
        num+=1

#input flips and all models
#output list of model ID and product sorted by greatest product value
def max_likelihood(flips, models):
    pass


if __name__ == "__main__":

    #problem 1

    # print(split_weight([1,1,1,1,1,2,1,1]))
    # coins = [1,1,1,1,1,1,1,1,1,1,1,1,1,2,1]
    # fake = find(coins)
    # print(fake,coins.index(fake),cnt)
    # coins = [2,1,1,]
    # fake = find(coins)
    # print(fake,coins.index(fake),cnt)


    #problem 2
        
    #read your name
    #file nmust be name.txt
    # with open("name.txt") as file_object1:
    #     name = file_object1.readline()

    # page = my_HTML()
    # print(page)
    # page.add_element(f"{name} = {translate(name)}", element = 'h2' )
    # print(page)
    # page = page + "eglyphTOP.jpg"
    # add_hieroglyphs(page,name)
    # page = page + "eglyphBOTTOM.jpg"
    # print(page)
    # page.save(translate(name))

    # webbrowser.open_new(translate(name) + ".html")

    # problem 3
    # path, filename = "", "cr.csv"
    # tree_model = build_tree_model(path + filename)
    # unknown = pd.DataFrame(data = [[0.025892469,38,0,74.5,1,13,0,0,0,2],[0.762158561,44,4,0.224465502,21000,5,0,1,0,3]],columns = ['B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J ', 'K'])
    # print(tree_model.predict(unknown))  #1,0
    # tree.plot_tree(tree_model)
    # plt.show()

    #problem 4
    models = {0:{"H":.1, "T":.9},1:{"H":.3, "T":.7},2:{"H":.5, "T":.5},
          3:{"H":.6, "T":.4},4:{"H":.8, "T":.2}}
    flips = "HHTT"
    print(likelihood(flips,models[2]))

    flips = "H"*rn.randint(0,5) + "T"*rn.randint(0,5)
    print(flips)
    print(max_likelihood(flips,models))

    print()

