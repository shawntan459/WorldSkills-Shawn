# Calculate the sum of all numbers from 1 to a given number

# Solution 1: Using for loop and range() function
# s: store sum of all numbers
# s = 0
# n = int(input("Enter number "))
# # run loop n times
# # stop: n+1 (because range never include stop number in result)
# for i in range(1, n + 1, 1):
#     # add current number to sum variable
#     s += i
# print("\n")
# print("Sum is: ", s)


# Solution 2: Using the built-in function sum()
n = int(input("Enter number "))
# pass range of numbers to sum() function
x = sum(range(1, n + 1))
print('Sum is:', x)
