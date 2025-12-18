import webbrowser
# import matplotlib.pyplot as plt
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
    split=[]
    listl=len(lst)//3
    for i in range(0,len(lst),listl):
        split.append(lst[i:i+listl])
    if len(split)==3:
        split.append([])
    return split

#input recursive funciton takes a list of coins with one fake
#returns fake
#only use weigh for len(list) > 3
def find(lst):
    if len(lst)>3:
        nlst=split_weight(lst)
        return find(nlst)
    else:
        if lst[0]==lst[1]:
            return lst[2]
        elif lst[0]==lst[2]:
            return lst[1]
        elif lst[1]==lst[2]:
            return lst[0]

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
        if tag['element']=='h2':
            self.body= "<h2 class='text-center'> " + content + ' </h2>' + self.body
    
    #overload + 
    #must return self to cascade
    def __add__(self, image):
        self.body+="<img src=" + f"'{image}'" + " class='center'><br>"
        return self.body

    #makes HTML page
    def make_page(self):
        htmlpage=self.top+self.body+self.bottom
        return htmlpage
#printable
    def __str__(self):
        return self.make_page()

    #save as name + .html
    def save(self,name):
        with open(name+'.html','w') as file:
            file.write(self.make_page())

#input english name (string)
#output egyptian name (string)
def translate(name):
    egyptian=''
    for i in name.lower():
        if i in 'ck':
            egyptian+='c'
        elif i in 'wvou':
            egyptian+='w'
        elif i in 'yi':
            egyptian+='y'
        else:
            egyptian+=i
    return egyptian

#input letter
#returns unique file name of jpg image for that letter
def get_image_filename(letter):
    if letter in 'ck':
        filename='eglyph'+'c'+'.jgp'
    elif letter in 'wvou':
        filename='eglyph'+'w'+'.jpg'
    elif letter in 'yi':
        filename='eglyph'+'y'+'.jpg'
    else:
        filename='eglyph'+letter+'.jpg'
    return filename

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
    credit=pd.read_csv(credit_data)
    credit.dropna(inplace=True)
    lst=list(credit.columns)
    output=lst[0:1]
    data=lst[1:]
    y=credit[output]
    x=credit[data]
    tm=tree.DecisionTreeClassifier()
    return tm.fit(x,y)

##########################
#            
#       problem 4
#
##########################
#input flips and model
#the product of the flips
def likelihood(flips,model):
    t=1
    for i in flips:
        t*=model[i]
    return t

#input flips and all models
#output list of model ID and product sorted by greatest product value
def max_likelihood(flips, models):
    keys=models.keys()
    nlst=[]
    for key in keys:
        t=1
        for j in flips:
            t*=models[key][j]
        nlst.append([key,t])
    s=sorted(nlst, key=lambda x: -x[1])
    return s

if __name__ == "__main__":

    #problem 1

    print(split_weight([1,1,1,1,1,2]))
    coins = [1,1,1,1,1,1,1,1,1,1,1,1,1,2,1]
    fake = find(coins)
    print(fake,coins.index(fake),cnt)
    coins = [2,1,1,]
    fake = find(coins)
    print(fake,coins.index(fake),cnt)


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
    # models = {0:{"H":.1, "T":.9},1:{"H":.3, "T":.7},2:{"H":.5, "T":.5},
    #       3:{"H":.6, "T":.4},4:{"H":.8, "T":.2}}
    # flips = "HHTT"
    # print(likelihood(flips,models[2]))

    # flips = "H"*rn.randint(0,5) + "T"*rn.randint(0,5)
    # print(flips)
    # print(max_likelihood(flips,models))

    print()

