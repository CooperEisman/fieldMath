public class fieldPoint implements fieldItem {
    private double[] location;

    //New point with list of args
    public fieldPoint(double[] location) {
        this.location = location;
    }

    //returns type
    public String type() {
        return "Point";
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
