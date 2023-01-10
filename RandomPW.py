# Lab-1 RandomPW
# Taek Kim

from datetime import datetime as t

# Password Components                # length:
upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" # 26
lower = upper.lower()                # 26
number = "0123456789"                # 10
symbol = "~!@#$%^&*+=?"              # 12


# get password conditions from user
length   = int(input("Enter the length of Password: "))
ifUpper  = input("Does the PW contains uppercase letter? (Y/N): ").upper()
ifLower  = input("Does the PW contains lowercase letter? (Y/N): ").upper()
ifNumber = input("Does the PW contains number? (Y/N): ").upper()
ifSymbol = input("Does the PW contains symbol? (Y/N): ").upper()


### depends on user input, append elements to pw container ###
pwComponents = ""

if(ifUpper == "Y"):
    pwComponents += upper
if(ifLower == "Y"):
    pwComponents += lower
if(ifNumber == "Y"):
    pwComponents += number
if(ifSymbol == "Y"):
    pwComponents += symbol


# initialize password variable
pw = ""

# loop as the length of the password
for i in range(length):
    
    ### random calculation upon time ###
    currentTime = t.now()
    date = int(currentTime.strftime("%Y%m%d"))
    time = int(t.now().strftime("%H%M%S"))
    randomValue = ((date+(i*5)) % (time*(i+7))) % len(pwComponents)
    ### - - - - - - - - - - - - - - - ###
    # for each loop, append a random character from the element pool to the password
    pw += pwComponents[randomValue]


### displasy the generated password and output to a file
fo = open("password.txt", "a")

print("Password generated: ", pw)
fo.write(pw + "\n")

fo.close()