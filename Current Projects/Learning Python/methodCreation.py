import random

def add(a,b):
    print(a+b)
def subtract(a,b):
    print(a-b)
def multiply(a,b):
    print(a*b)
def divide(a,b):
    print(a/b)

A = int(input("Choose a number: "))
B = int(input("Choose another number: "))
add(A,B)
subtract(A,B)
multiply(A,B)
divide(A,B)

print()

#How to return values from a method.
def getRandomNumber():
    num = random.randint(1,100)
    return num

print(getRandomNumber())
