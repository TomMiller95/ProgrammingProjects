#This is how to create a basic array
numbers = [0,1,2,3,4,5,6,7]

#Direct index access
print("The number at position four in the list is " + str(numbers[3]))

#The size of a list is found using the len() method
print("The length of the list is " + str(len(numbers)))

#This is how to get the last element of the list
print("The last number in the list is " + str(numbers[len(numbers)-1]))

#For loop to cycle through every element in the list
for number in numbers:
    print(number)

