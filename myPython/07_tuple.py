# create a Tuple

weekend_tuple = ('Sat', 'Sun')

# index
sat = weekend_tuple[0]

print(sat)

# weekend_tuple[0] = 'new Sat' # not allowed

# Convert a tuple to a list
weekend_list = list(weekend_tuple)
weekend_list[0] = 'new Sat'
print(weekend_list)