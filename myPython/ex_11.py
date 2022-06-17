# Print list in reverse order using a loop
'''
# Solution 1: Using a reversed() function and for loop
list1 = [10, 20, 30, 40, 50]
# reverse list
new_list = reversed(list1)
# iterate reversed list
for item in new_list:
    print(item)
'''

# Solution 2: Using for loop and the len() function
list1 = [10, 20, 30, 40, 50]
# get list size
# len(list1) -1: because index start with 0
# iterate list in reverse order
# star from last item to first
size = len(list1) - 1
for i in range(size, -1, -1):
    print(list1[i])
