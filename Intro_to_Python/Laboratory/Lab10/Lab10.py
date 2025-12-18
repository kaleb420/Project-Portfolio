import pandas as pd
import numpy as np

# Function to calculate the mean of the first four columns in a DataFrame
def mean(df):
    return df.iloc[:,:4].mean().tolist()

# Function to count the number of missing (NaN) values in each column of the DataFrame
def num_missing_values(df):
    return df.isnull().sum().tolist()

# Function to count the number of duplicate rows in the DataFrame
def num_duplicates(df):
    return df.duplicated().sum().tolist()

# Function to compute the class distribution of a specific column ('species') in the DataFrame
def class_distribution(df):
    df=df.drop_duplicates()
    return df['variety'].value_counts().tolist()

if __name__ == "__main__":
    print("Pandas Practice")
    df = pd.read_csv('https://gist.githubusercontent.com/netj/8836201/raw/6f9306ad21398ea43cba4f7d537619d0e07d5ae3/iris.csv')
    answers  = [[5.8433, 3.0573, 3.7580, 1.1993], 
                0, 
                1, 
                [50, 50, 49]]
    functions = [mean,num_missing_values,num_duplicates,class_distribution]

    for (func, ans) in zip(functions, answers):
        print("="*5, "Testing: "+func.__name__+"()", "="*5)
        print("Our Inputs:", type(df))
        print("Your Outputs:")
        result = func(df)
        correct = int(np.all(np.isclose(result, ans, rtol=1e-4)))
        print(f"{func.__name__} : {result}, {('WRONG', 'RIGHT')[correct]}")
        print("\n\n")