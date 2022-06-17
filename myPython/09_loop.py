# print('1')
# print('2')
# print('3')
# print('4')
# print('5')

# while
i = 1
while (i <= 5):
    print(i)
    i += 1

    if (i == 3):
        break

# for loop
animals = ['cat','dog','bear']
print(animals)

for animal in animals:
    print(animal.upper())

index = 0
while index<len(animals):
    print(animals[index])
    index += 1

# range()
for num in range(5):
    print(num)

for num in range(1,3):
    print(num)

for num in range(1, 10, 3):
    print(num)
