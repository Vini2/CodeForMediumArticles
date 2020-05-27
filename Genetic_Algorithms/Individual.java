class Individual {
    int fitness = 0;
    int geneLength = 5;
    int[] genes = new int[5];

    public Individual() {
        Random rn = new Random();
        //Set genes randomly for each individual
        for (int i = 0; i < geneLength; i++) {
            genes[i] = rn.nextInt() % 2;
        }
        fitness = 0;
    }

    //Calculate fitness
    public void calculateFitness() {
        fitness = 0;
        for (int i = 0; i < geneLength; i++) {
            if (genes[i] == 1) {
                ++fitness;
            }
        }
    }

}