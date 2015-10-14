#Gear Train Calculator
#Authors Ethan, Simon, Riley, Thomas

def factor(a):
    original = a
    factors = []
    last = 2

    while True:
        for n in xrange(last,int(pow(a,0.5)+1)):
            if a % n == 0:
                factors.append(n)
                a = a/n
                last = n
                break
        else:
            factors.append(a)
            break
    return factors

def test(list1,list2,max_ratio):
    #Should test if 2 equal length factA, factB can be rearranged
    #_________________________________________________________






    #_________________________________________________________
    raise NotImplemented
#@param: numerator   = a list of factored elements or possible gear teeth numbers
#@param: denominator = a list of factored elements or possible gear teeth numbers
#@param: max_gear    = the max size of a gear
#@param: max_ratio   = the max ratio of gear sizes
def fix(numerator, denominator, max_gear, max_ratio):
    #Should resize list1 by multiplying elements to be the same length as list2
    #Calls test for every potential resize, returns first that works
    #_________________________________________________________
    
    #Hold elements of num and denum
    #print numerator, " ", denominator, " ", max_gear, " ", max_ratio
    shortest = []; longest = []; placehold =[]
    
    if(len(numerator) <= len(denominator)):
        shortest = numerator
        longest = denominator
    else:
        shortest = denominator
        longest = numerator
    placehold = longest[:]                                          #This variable holds the longest incase the first attempt does not work
    for major_pivot in range(len(shortest) ):                        #loop through rotation values for the shortest list
        for pivot in range(len(longest)):                           #loop through rotation values for largest list
            for over in range(len(shortest), len(longest)):         #loop through entries that are above shortest list
                multipliedIn = False
                for i in range(len(shortest)):                      #Loop through indicies to multiply too
                    if not (1.0/max_ratio >= shortest[i] / float(longest[i]) >= max_ratio):
                        break   #Values already do not work together thus break
                    temp = longest[i] * longest[over]
                    if (temp <= max_gear and 1.0/max_ratio >= shortest[i] / float(temp) >= max_ratio and shortest[i]/ float(temp) != 1):
                        #This means it worked I can mult in!
                        multipliedIn = True
                        longest[i] = temp
                        break #go to next overflow value
                if not multipliedIn:
                    #break to rearrange through rotation
                    break
                if(over == len(longest)-1):
                    longest = longest[0:len(shortest)]
                    if(len(numerator) == len(shortest)):
                        return True, shortest, longest
                    else:
                        return True, longest, shortest
            #This rotates the values
            placehold.append(placehold.pop(0))
            longest = placehold[:]
        shortest.append(shortest.pop(0))
              
        

    #_________________________________________________________
    return False, numerator, denominator

def gearTrain(input_speed, output_speed, min_gear, max_gear, max_ratio):
    R = input_speed / float(output_speed)

    #We need to find two numbers A and B such that:
    # A/B = R
    # A = t[1]*t[3]*t[5]...
    # B = t[0]*t[2]*t[4]...
    # The factors (or products of factors) are between min_gear and max_gear

    B = 0
    allValid = []
    while B<500000:
        B+=1
        validB = True
        fB = factor(B)
        for f in fB:
            if not(min_gear <= f <= max_gear):
                validB = False
                break
        if validB and round(R*B) == R*B:
            allValid.append([factor(R*B),fB])

    solFound = False    
    #Figure out which list is bigger
    for solution in allValid:
        if len(solution[0]) == len(solution[1]):
            #The lists are already equal size, test them once
           if test(solution[0],solution[1],max_ratio):
               return solution
        else:
            #The lists are different sizes, fix
            shorter,longer = sorted(solution,key=lambda x: len(x))
            target_ratio = max_ratio
            if longer == solution[0]:
                target_ratio = 1./max_ratio
            #Make them the same size by mulpying the elements of the longer list together, testing each time
            retval = fix(solution[0],solution[1],max_gear, target_ratio)
            if(retval[0] == True):
                solFound = True
                print retval
                print solution
                raw_input("\n\n\nPRESS ENTER FOR MORE SOLUTIONS>>>")
                
    if not solFound:
        print"No  solution!"
    else:
        print "No more solutions found"

    testDenominator = 0
    allValid = []


gearTrain(100000,100,5,25,5)
            


