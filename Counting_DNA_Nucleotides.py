# Read the file and get the DNA string
file = open('sample_dna.txt', 'r')
dna = file.read()

# Print the original DNA string
print "DNA String: ", dna

# Print the count of each letter
print "Count of A: ", dna.count("A")
print "Count of C: ", dna.count("C")
print "Count of G: ", dna.count("G")
print "Count of T: ", dna.count("T")

# End of program