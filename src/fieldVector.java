public class fieldVector implements fieldItem {
    private double[] components;

    //New Vector with Length, Initializes to 0.0 at each index
    public fieldVector(int length) {
        components = new double[length];
        clear();
    }

    //New Vector with Components
    public fieldVector(double[] components) {
        this.components = new double[components.length];

        for(int i = 0;i < this.components.length; i++) {
            this.components[i] = components[i];
        }
    }

    //intialize with two points
    public fieldVector(fieldPoint pointOne, fieldPoint pointTwo) {
        components = pointOne.findDifference(pointTwo).getComponents();
    }

    //returns type
    public String type() {
        return "Vector";
    }

    //Print Function Handling
    public String toString() {
        String out = "<" + components[0];
        for(int i = 1;i < components.length; i++) {
            out += ", " + components[i];
        }
        out += ">";
        return out;
    }

    //returns order of the vector
    public int getOrder() {
        return components.length;
    }

    //Returns all Components of the Vector
    public double[] getComponents() {
        return components;
    }

    //Returns a Component at a Dimension
    public double getComponent(int dimension) {
        if(dimension >= components.length){
            return 0.0;
        }
        return components[dimension];
    }

    //Sets component at a given index
    public void setComponent(int dimension, double value) {
        components[dimension] = value;
    }

    //Sets to equal
    public void setComponents(double[] components) {
        this.components = components;
    }

    //Clears the Vector
    public void clear() {
        for(int i = 0;i < components.length; i++) {
            components[i] = 0.0;
        }
    }

    //Returns Scalar Multiple
    public fieldVector getScalarMultiple(double value) {
        fieldVector newVector = new fieldVector(getComponents());

        for(int i = 0;i < components.length; i++) {
            newVector.setComponent(i,components[i]*value);
        }

        return newVector;
    }

    //Multiplies this vector by a scalar
    public void scalarMultiply(double value) {
        fieldVector newVector = getScalarMultiple(value);
        setComponents(newVector.getComponents());
    }

    //Returns the sum of this vector and 'addedVector'
    public fieldVector returnSum(fieldVector addedVector) {
        fieldVector sum;

        if(addedVector.getOrder() > getOrder()) {
            sum = new fieldVector(addedVector.getOrder());
        } else {
            sum = new fieldVector(getOrder());
        }

        for(int i = 0;i < sum.getOrder(); i++) {
            sum.setComponent(i,addedVector.getComponent(i) + getComponent(i));
        }
        return sum;
    }

    //Sets this vector to the sum of it an another vector
    public void add(fieldVector addedVector) {
        fieldVector newVector = returnSum(addedVector);
        setComponents(newVector.getComponents());
    }

    //Allows addition of multiple vectors
    public void add(fieldVector[] addedVectors) {
        fieldVector newVector = new fieldVector(getComponents());
        for(int i = 0; i < addedVectors.length; i++) {
            newVector.add(addedVectors[i]);
        }

        setComponents(newVector.getComponents());
    }

    //Subtracts one vector from annother
    public fieldVector returnDifference(fieldVector subtractedVector) {
        subtractedVector.scalarMultiply(-1.0);
        return returnSum(subtractedVector);
    }

    //Subtracts the second vector from this vector
    public void subtract(fieldVector subtractedVector) {
        fieldVector newVector = returnDifference(subtractedVector);
        setComponents(newVector.getComponents());
    }
}
