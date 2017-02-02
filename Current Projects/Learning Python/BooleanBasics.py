numA = input('Enter your first number: ')
numB = input('Enter your second number: ')

numbers = [1,2,3,4,5,6,7,8]
print("\n")

for number in numbers:
    print(str(numA) + " is greater than " + str(number) + ": " + str((int(numA) > number)))
    print("{} is greater than {}: ".format(numB,number) + str((int(numB) > number)))
    print("{} is equal to {}:".format(numA,number) + str(int(numA) == number))
    print("{} is equal to {}:".format(numB,number) + str(int(numB) == number) + "\n")
