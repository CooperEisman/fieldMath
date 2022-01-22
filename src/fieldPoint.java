public class fieldPoint implements fieldItem {
    private double[] location;


    //New point with list of args
    public fieldPoint(double[] location) {
        this.location = location;
    }

    //returns Type
    public String type() {
        return "Point";
    }

    //toString for output
    public String toString() {
        String out = "(" + location[0];
        for(int i = 1;i < location.length; i++) {
            out += ", " + location[i];
        }
        out += ")";
        return out;
    }

    public String args() {
        String out = "";

        for(int i = 0; i < location.length; i++) {
            out += " " + getCoordinate(i);
        }

        return out;
    }

    //Returns a Component at a Dimension
    public double getCoordinate(int dimension) {
        if(dimension >= location.length){
            return 0.0;
        }
        return location[dimension];
    }

    //Gets order of the Point
    public int getOrder() {
        return location.length;
    }

    public fieldVector findDifference(fieldPoint pointTwo) {
        double[] coordinates;

        if(pointTwo.getOrder() > getOrder()) {
            coordinates = new double[pointTwo.getOrder()];
        } else {
            coordinates = new double[getOrder()];
        }

        for(int i = 0; i < coordinates.length; i++) {
            coordinates[i] = getCoordinate(i) - pointTwo.getCoordinate(i);
        }

        return new fieldVector(coordinates);
    }

    public double[] getLocation() {
        return location;
    }
}
