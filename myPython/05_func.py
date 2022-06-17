
# def say_hi():
#     print('Hi...')
#     print('my friends')

# say_hi()


def say_hi(name):
    print('Hi {}'.format(name))

# parameter vs argument
say_hi('Peter')
say_hi('everyone')

def say_Hi(name = 'there'):
    print('Hi {}'.format(name))

say_Hi()
say_Hi('Edwin')

def say_hi(first,last):
    print('Hi {} {}!'.format(first,last))

say_hi('Edwin','Lee')
say_hi(first='Mary',last='Tan')
say_hi(last='Tan', first='Mary')

def square(number):
    return number*number

result = square(5)
print(result)

def odd_or_even(number):
    if number%2 == 0:
        return 'Even'
    else:
        return 'Odd'
num_str = odd_or_even(7)
print(num_str)