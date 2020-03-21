# Read the file and get the DNA string
file = open('sample_dna.txt', 'r')
dna = file.read()
print "DNA: ", dna

rna = ""

# Generate the RNA string
for i in dna:
    # Replace all occurrences of T with U
    if i == "T":
        rna += "U"
    else:
        rna += i

# Print the RNA string
print "RNA: ", rna

# End of program