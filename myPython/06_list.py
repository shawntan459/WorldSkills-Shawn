# create a list
animals = ['cat','dog','bear']
print(animals)

# size of list
print(len(animals))

# list indexing
print(animals[0])
print(animals[-1])

# change an object at a given index
animals[0] = 'zebra'
print(animals)

# Add items
animals.append('cow')
print(animals)

animals.insert(1, 'duck')
print(animals)

# slicing
some_animals = animals[1:3]
print(some_animals)

first_two = animals[:2]
print(first_two)

last_two = animals[3:5]
print(last_two)

# find an item
dog_idx = animals.index('dog')
print(dog_idx)

# cat_idx = animals.index('cat')

# exception
try:
    cat_idx = animals.index('cat')
except:
    cat_idx = 'No cat found'
print(cat_idx)

# make a copy of the list
animals2 = animals.copy()
print(animals2)

# remove an item
animals2.remove('dog')
print(animals2)

# slicing step size
List = [1,2,3,4,5,6,7,8,9]
print(List[3:9])
print(List[3:9:2])

