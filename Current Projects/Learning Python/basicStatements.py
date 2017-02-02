print("Some simple if statements")
if 5 > 3:
    print("5 is greater than 3.")
if not 3 > 5:
    print("3 is not greater than 5.")

print()

print("The list of numbers is 3,9, and 8. You will guess a number and it will scan the list for it.")
nums = [3,9,8]
userNum = input("Pick a number:")

for magicNum in nums:
    if magicNum == int(userNum):
        print("Number was in list")
    if not magicNum == int(userNum):
        print("Not Found Yet...")

print()

#RANGE is basically a way of starting a for loop.
# I created 'chances' and set it to 3, so the loop would happen 3 times.
# These types of loops start at index 0.
print("Now we will go over RANGE")
chances = 3
for attempts in range(chances):
    user_answer = int(input("Guess a number: "))
    print("Attempt #" + str(attempts  + 1) + ": is " + str(user_answer))
    print()
