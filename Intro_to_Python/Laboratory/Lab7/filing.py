import os

def getCurrentDirectory():
    """
    This uses a command built into the python module `os` 
    that shows the current working directory. 

    Returns:
        A string that shows the current working directory 
        (Where the program is being executed at)
    """
    return os.getcwd()




def readingEx1():
    """
    This function will not return anything. 

    This function will be a "workspace" for us to practice reading files
    """
    with open("C200-Assignments-kalerobi/Laboratory/Lab7/blank.txt", 'r') as someFile:
        contents=someFile.read()
        return contents


def readingEx2():
    """
    This function will not return anything. 

    This function will be a "workspace" for us to practice reading files
    """
    with open("C200-Assignments-kalerobi/Laboratory/Lab7/blank.txt", 'r') as someFile:
        contents=someFile.readlines()
        return contents


def writeEx1():
    """
    This function will not return anything. 

    This function will be a "workspace" for us to practice reading files
    """
    stuff=['a','b','c','d','e','f']
    with open('C200-Assignments-kalerobi/Laboratory/Lab7/wrong.txt','w') as fileToWrite:
        for s in stuff:
            fileToWrite.write(s)


def writeEx2():
    """
    This function will not return anything. 

    This function will be a "workspace" for us to practice reading files
    """
    with open('C200-Assignments-kalerobi/Laboratory/Lab7/wrong.text','a') as fileToWrite:
        for s in range(4):
            fileToWrite.write('more\n')



def FileIO_example(filePath, newFile): 
    '''
    Given a file path, we want to open the file, read each line and count
    the number of vocabs in each line. We will write to
    the newFile the lines that have more than 5 vocabs and clean them up
    (use strip). You are provided the path to the file we want to write.

    Return number of all lines that has less than or equal to 5 vocabs.
    '''
    result=[]
    count=0
    with open('C200-Assignments-kalerobi/Laboratory/Lab7/testing.data','r') as fileToSplit:
        vocab=fileToSplit.read()
    splitvocab=vocab.splitlines()
    for line in splitvocab:
        if(len(line.split())>5):
            result +=[line.strip()]
        else:
            count+=1
    with open('C200-Assignments-kalerobi/Laboratory/Lab7/output.txt', 'w') as outputFile:
        for line in result:
            outputFile.write(line+'\n')
    return count

def calculation():
    '''
    Given a file path, we want to open the file, read each line. in each line we have a number
    we want to calculate the summation of numbers in last 2 lines and write the sum at the end of the 
    file we read from it. (eah time that we run this function we add one number of fibonacci series to the file)
    '''
    with open('C200-Assignments-kalerobi/Laboratory/Lab7/calculation.txt', 'r') as dataFile:
        numbers=dataFile.readlines()
    sum=int(numbers[-1])+int(numbers[-2])
    with open('C200-Assignments-kalerobi/Laboratory/Lab7/calculation.txt', 'a') as outputFile:
        outputFile.write('\n'+str(sum))


if __name__ == "__main__":
    print()
    print("Examples of Reading")
    print("Our current working directory: " + getCurrentDirectory())
    print()
    print("Reading")
    readex1 = readingEx1()
    print("~"*30)
    print(readex1, end="") # end= removes the \n automatically added
    print("*EOF*")
    print("-" * 20)
    
    readex2 = readingEx2()
    print("~"*30)
    print(readex2, end="") # end= removes the \n automatically added
    print("*EOF*")
    print("-" * 20)
    print()

    print("Writing")
    print("-" * 20)
    writeEx1()
    writeEx2()
    print()
    print("Strip Lab Result: " + str(FileIO_example("Laboratory/Lab8/testing.data", "Laboratory/Lab8/clean.txt")))

    # calculation("Laboratory/Lab8/calculation.txt")
    calculation()