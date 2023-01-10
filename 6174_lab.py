# 6174 lab
# Taek Kim

# The program will perform 6174 calculation using recursion 

# recursive function
def calculation(passed, n):
    
    # base case, returns the number of iteration
    if(passed == 6174):
        print("calculation done! (calculated", n, "times)")
        return n

    # constructor
    number = str(passed)
    numArr = []

    # bubble sort algorithm to sort the numbers
    for x in range(4):
        numArr.append(int(number[x]))
    

    for i in range(3):
        for j in range(3-i):
            if(numArr[j] > numArr[j+1]):
                temp = numArr[j]
                numArr[j] = numArr[j+1]
                numArr[j+1] = temp
    # - # - # - # - # - # - # - # - # - # - # - # - #


    # using stored numbers get biggest and smallest number
    sNum = ""
    bNum = ""

    for i in range(4):
        sNum += str(numArr[i])
        bNum += str(numArr[3-i])

    theVal = int(bNum) - int(sNum)
    # - # - # - # - # - # - # - # - # - # - # - # - # - # - #

    # display the calculation of the iteration    
    print("number passed:" , passed)
    print(bNum + " - " + sNum + " =", theVal, "\n")

    # pass the value to next iteration of recursion
    return (calculation(theVal, n+1))


# main 
userInput = int(input("Enter a 4 digit number, having at least two different number: "))
calculation(userInput, 0)
