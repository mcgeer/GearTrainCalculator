#Gear Train Calculator
#Authors Ethan, Simon, Riley

#Speeds in rps and gear sizes in pitch diameter
def GearTrainCalculator(Input_speed, Output_speed, min_gear, max_gear, max_ratio):
    R = Output_speed / Input_speed
    Gear_Train = []
    Gear_Pair_Found = False 
    #Calculate R
    #With R as ratio calculate Positive integer ratio where the remainder is not prime? Start at max gear and --
    #Loop through moduluses if 1 is not sufficient
    while Input_speed != Output_speed:
        for size in range(min_gear, max_gear - 1):
            for size_2 in range(max_gear, min_gear + 1, -1):
                if size_2%size == 0:
                    gear_ratio = size_2/size
                    if gear_ratio <= max_ratio:
                        #Code for Gear concatenation
                        Input_speed *= gear_ratio
                        Gear_Train.append([size, size_2])
                        Gear_Pair_Found = True
                        break
           # print "I'm here, myan"
            if Gear_Pair_Found:
                print "I found one myan ", Input_speed
                Gear_Pair_Found = False
                break
    return Gear_Train


#=====================MAIN=====================

print(GearTrainCalculator(1, 8, 1, 10, 5))
print "By Jove we have done it"
