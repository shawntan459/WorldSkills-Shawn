# creat a dict
customer = {'name':'John','age':19}
print(customer)

# get a value
c_name = customer['name']
print(c_name)

c_age = customer.get('age')
print(c_age)

# update value
customer['age'] = 20
print(customer)

# add a new pair
customer['address'] = 'Clementi'
print(customer)

# remove a pair
upd_customer = customer.pop('age')
print(customer)

# get the number of pairs in the dictionary
print(len(customer))


