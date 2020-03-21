# Import Clustal Omega wrapper
from Bio.Align.Applications import ClustalOmegaCommandline

# Define input file
in_file = "/Users/vijinimallawaarachchi/Documents/Python/Acanthaster_planci_Gnomon.fsa"

# Define output file
out_file = "aligned.fasta"

# Get the command for Clustal Omega
clustalomega_cline = ClustalOmegaCommandline(infile=in_file, outfile=out_file, verbose=True, auto=True)

# Print the executable command
print(clustalomega_cline)
