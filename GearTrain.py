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

def fix(list1,list2,max_ratio):
    #Should resize list1 by multiplying elements to be the same length as list2
    #Calls test for every potential resize, returns first that works
    #_________________________________________________________







    #_________________________________________________________
    raise NotImplemented

def gearTrain(input_speed, output_speed, min_gear, max_gear, max_ratio):
    R = input_speed / float(output_speed)

    #We need to find two numbers A and B such that:
    # A/B = R
    # A = t[1]*t[3]*t[5]...
    # B = t[0]*t[2]*t[4]...
    # The factors (or products of factors) are between min_gear and max_gear

    B = 0
    allValid = []
    while B<500:
        B+=1
        validB = True
        fB = factor(B)
        for f in fB:
            if not(min_gear <= f <= max_gear):
                validB = False
                break
        if validB and round(R*B) == R*B:
            allValid.append([factor(R*B),fB])
    
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
            return fix(longer,shorter,target_ratio)

        
    return "No solution!"

gearTrain(28,1,0,20,2)
            


