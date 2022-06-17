# Convert two lists into a dictionary

keys = ['Ten', 'Twenty', 'Thirty']
values = [10, 20, 30]

# empty dictionary
dict = {}

for i in range(len(keys)):
    dict[keys[i]] = values[i]
print(dict)
