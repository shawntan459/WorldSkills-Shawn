# Booleans

bool_1 = True
bool_2 = False

price = 20
print(price>10 and price<30)

price = 5
print(price>10 or price<30)

print(not price>10)

temperature = 8
if temperature > 30: 
    print("It's a hot day")
    print("Drink more water")

elif temperature > 20: # 20 - 30
    print("It's a nice day")

elif temperature > 10: # 10 - 20
    print("It's a bit cold")

else: # <10
    print("It's cold")

print("Done")

    