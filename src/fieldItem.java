interface fieldItem {

    String type();

    String toString();

    String args();

    int getOrder();

    //Clears the Vector
    void clear();

    fieldVector returnDifference(fieldItem pointTwo);

    double getComponent(int dimension);

    double[] getComponents();

    void scalarMultiply(double value);

}
