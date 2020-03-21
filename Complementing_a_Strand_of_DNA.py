# Read the file and get the DNA string
file = open("sample_dna.txt", "r")
dna = file.read()

# Print the original DNA string
print "DNA String: ", dna

# Create dictionary of complementing nucleobase pairs
comp_pairs = {"A" : "T", "T" : "A", "G" : "C", "C" : "G"}

complementing_strand = ""

# Generate the complementing strand
for i in range (len(dna)-1, -1, -1):
    complementing_strand += comp_pairs[dna[i]]

# Print the complementing strand
print "Complement: ", complementing_strand

# End of program