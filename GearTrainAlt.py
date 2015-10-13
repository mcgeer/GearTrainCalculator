#Gear Train Calculator
#Authors Ethan, Simon, Riley, Thomas

def factor(integer):
    '''Returns the factors of an integer.'''
    factors = []
    lastFactor = 2

    while True:
        for n in xrange(lastFactor,int(pow(integer,0.5)+1)):
            if integer % n == 0:
                factors.append(n)
                integer = integer/n
                lastFactor = n
                break
        else:
            factors.append(integer)
            break
    return factors

def pair(list1,list2,max_ratio,prev=[]):
    #Tests if 2 equal length list1, list2 can be rearranged
    #such that each element A of list1 (a) can be paired to a list2 element B
    #so A/B < max_ratio.
    if len(list1) == 0:
        return True,prev

    for a in range(len(list1)):
        for b in range(len(list2)):
            if list1[a]/float(list2[b]) <= max_ratio:
                if list1[a] == list2[b]:
                    #Don't bother with 1:1 gears
                    res = pair(list1[:a]+list1[a+1:],list2[:b]+list2[b+1:],max_ratio,prev)
                else:
                    res = pair(list1[:a]+list1[a+1:],list2[:b]+list2[b+1:],max_ratio,prev+[[list1[a],list2[b]]])
                if res[0]:
                    return res
    return False,[]

def self_check(solution,input_speed,output_speed):
    #Do a final self check on any given solution via simulation
    speed = input_speed
    n = 0
    while n < len(solution):
        speed = speed*solution[n][1]/solution[n][0]
        n+=1
    if speed == output_speed:
        return True
    return False

def fix(list1,list2,max_ratio,max_gear):
    #Should resize list1 by multiplying elements to be the same length
    #list1 is the longer list.
    #Calls test for every potential resize, returns first that works
    #Also returns a boolean that determines success of the algorithm.
    originalList1 = list(list1)
    targetLength = len(list2)

    diff = len(list1)-len(list2)
    
    if diff == 0:
        return list1,list2,True

    if pair(list1,list2+[1]*diff,max_ratio): #Try adding a 1
        return list1,list2+[1]*diff,True

    #Get minimum of list1
    minLong = list1[0] #list1 is already sorted
    validShort = []

    for a in list1[1:]:
        if a*minLong < max_gear:
            list1 = list(originalList1[1:]) #Reset list1
            list1.remove(a)
            list1.append(a*minLong)
            if pair(list1,list2,max_ratio)[0]:
                return fix(list1,list2,max_ratio,max_gear) #Recursive call       
    return originalList1,list2,False #Failed

def niceDisplay(gearTrain,input_speed,output_speed):
    '''Prints the gear train in an easily readable format.'''
    n = 0
    out = str(input_speed)+" Rotation"+"  >>>>  "
    while n < len(gearTrain):
        out+="--("+str(int(gearTrain[n][1]))+")("
        out+=str(int(gearTrain[n][0]))+")--"
        n+=1
    out+="  >>>>  "+str(output_speed)+" Rotation"
    print
    print out
    print
    raw_input("PRESS ENTER FOR MORE SOLUTIONS>>>")
    print

def niceInput():
    '''Nicely get user input.'''
    a = int(raw_input("Input rotation speed:"))
    b = int(raw_input("Output rotation speed:"))
    c = int(raw_input("Minimum teeth on gear:"))
    d = int(raw_input("Maximum teeth on gear:"))
    e = int(raw_input("Maximum gear ratio:"))
    gearTrain(a,b,c,d,e)

def shiftMin(listOfNumbers,min_num,constant):
    '''Find a constant so all numbers times that constant > min_num'''
    for i in listOfNumbers:
        while i*constant < min_num:
            constant+=1
    return constant
        
def gearTrain(input_speed, output_speed, min_gear, max_gear, max_ratio):
    print
    print "Calcuating..."
    print
    
    R = input_speed / float(output_speed)

    #We need to find two numbers A and B such that:
    # A/B = R
    # A = t[1]*t[3]*t[5]...
    # B = t[0]*t[2]*t[4]...
    # The factors (or products of factors) are between min_gear and max_gear

    #Check for any invalid inputs
    if min_gear > max_gear:
        print "Why is your min_gear > max_gear?!"
        return

    testDenominator = 0
    allValid = []
    while testDenominator<50000 and len(allValid) < 10: #Iterate across all possible denominators.
        testDenominator+=1
        validDenominator = True
        factors = factor(testDenominator)

        numerator = R*testDenominator
        if round(numerator) != numerator: #Numerator must be integer
            continue
        factors2 = factor(numerator)

        for f in factors: #Check for large primes that violate constraints
            if not(f <= max_gear):
                validDenominator = False
                break

        if not(validDenominator):continue
        
        for f in factors2: #Now check numerator
            if not(f <= max_gear):
                validDenominator = False
                break
        if not(validDenominator):continue

        allValid.append([factors2,factors])

    #Now test all the solutions and modify them as needed
    allSolutions = []
    for solution in allValid:
        #Figure out which list is bigger
        if len(solution[0]) == len(solution[1]):
            #The lists are already equal size, test them once
            res = pair(solution[0],solution[1],max_ratio)
            if res[0]:
                newSolution = res[1]
            
        elif len(solution[0]) > len(solution[1]):
            #The lists are different sizes, fix
            solution[0],solution[1],worked = fix(solution[0],solution[1],max_ratio,max_gear)
            if not(worked): continue
            newSolution = pair(solution[0],solution[1],max_ratio)[1]

        else:
            #Reciprocal max_ratio because denominator is longer
            solution[1],solution[0],worked = fix(sorted(solution[1]),solution[0],1./max_ratio,max_gear)               
            if not(worked): continue
            newSolution = pair(solution[0],solution[1],max_ratio)[1]

        #We now need to shift any gears that are too small by multiplying their pair
        valid = True
        print newSolution
        for i in newSolution:
            constant = 1
            while i[0]*constant < min_gear or i[1]*constant < min_gear:
                constant+=1
            i[0]*=constant
            i[1]*=constant
            if i[0] > max_gear or i[1] > max_gear:
                valid = False
                break
        if not(valid): continue               

        if not(self_check(newSolution,input_speed,output_speed)): #Do a self check
            continue

        if not(newSolution in allSolutions): #check that solution is unique
            allSolutions.append(newSolution)
            niceDisplay(newSolution,input_speed,output_speed) #Print the solution
                
    if len(allSolutions) == 0:
        print "No solution!"
    else:
        print "No more solutions!"

if __name__ == "__main__":niceInput()
            

