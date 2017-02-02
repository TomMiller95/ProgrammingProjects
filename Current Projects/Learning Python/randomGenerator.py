#Random number generatorrrrr!
import random
randNum = random.randint(0,2)

#This allows a user to enter 10 numbers and will print out the lowest
# one in the end.
chances = 10
for count in range(chances):
    userInput = int(input("Pick a number: "))
    if count == 0:
        lowestNum = userInput
    if not count == 0:
        if lowestNum > userInput:
            lowestNum = userInput
print("Your lowest number is: " + str(lowestNum))

print()

#This will print out a bunch of random numbers, and then select
# the lowest.
lowest = 101  #100 is the highest the randint will be able to choose.
for index in range(15):
    randNum = random.randint(0,100)
    print("Pick #" + str(index) + " is: " + str(randNum))
    if lowest > randNum:
        lowest = randNum

print("The lowest number generated was: " + str(lowest))
