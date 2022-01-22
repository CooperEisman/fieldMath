public class fieldPoint implements fieldItem {
    private double[] components;


    //New point with list of args
    public fieldPoint(double[] location) {
        this.components = location;
    }

    //New point with Length, Initializes to 0.0 at each index
    public fieldPoint(int length) {
        components = new double[length];
        clear();
    }

    //returns Type
    public String type() {
        return "point";
    }

    //toString for output
    public String toString() {
        String out = "(" + components[0];
        for(int i = 1; i < components.length; i++) {
            out += ", " + components[i];
        }
        out += ")";
        return out;
    }

    public String args() {
        String out = "";

        for(int i = 0; i < components.length; i++) {
            out += " " + getComponent(i);
        }

        return out;
    }

    //Returns a Component at a Dimension
    public double getComponent(int dimension) {
        if(dimension >= components.length){
            return 0.0;
        }
        return components[dimension];
    }

    //Gets order of the Point
    public int getOrder() {
        return components.length;
    }

    public fieldVector returnDifference(fieldItem pointTwo) {
        double[] coordinates;

        if(pointTwo.getOrder() > getOrder()) {
            coordinates = new double[pointTwo.getOrder()];
        } else {
            coordinates = new double[getOrder()];
        }

        for(int i = 0; i < coordinates.length; i++) {
            coordinates[i] = getComponent(i) - pointTwo.getComponent(i);
        }

        return new fieldVector(coordinates);
    }

    public double[] getComponents() {
        return components;
    }

    //Clears the Point
    public void clear() {
        for(int i = 0; i < components.length; i++) {
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

    //Sets component at a given index
    public void setComponent(int dimension, double value) {
        components[dimension] = value;
    }

    //Sets to equal
    public void setComponents(double[] components) {
        this.components = components;
    }
}
