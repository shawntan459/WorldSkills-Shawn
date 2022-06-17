# String

# define strings using single or double quotes
age = '20'
fruit = 'apple'

# Indexing
first_char = fruit[0]
print(first_char)

xyz = fruit[-3]
print(xyz)

# Slicing
ap = fruit[0:2]
ap = fruit[:2]
print(ap)

# 5 is length of the string
print(fruit[2:5])
print(fruit[2:])

# Concatenation
first = 'I'
second = 'love'
third = 'Python'
sentence = first + ' ' + second + ' ' + third
print(sentence)
print('* {} {} {}'.format(first,second,third))

print('my sentence = ', sentence)

# built-in function
my_len = len(fruit)
print('LEN = ', my_len)

# nested
print('len: ', len(fruit))

version = 3
print('I love python' + str(version))
version = '3'
print('I love python ' + version)

my_input = input('Enter a name of a fruit: ')
print(my_input, " is a lovely fruit")

# Method
fruit = 'ApPle'
print(fruit.lower())
print(fruit.upper())

print(fruit.find('p'))
print(fruit.replace('p','q'))

# ctrl + /
'''     dd
adsffg
gfgf
'''

"""

"""
print('I {} Python'.format('love'))
print('{} {} {}'.format('I','love','python'))
print('I {0} {1} . {1} {0}s me'.format('love','Python'))


# in 
contain = 'Python' in sentence
print('contain= ', contain)


