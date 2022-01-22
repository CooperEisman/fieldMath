public class main {
    public static void main(String[] args) {
        double[] a = {3.0,-2.0,7.0};
        double[] b = {3.0,2.0,1.0};
        fieldVector vU = new fieldVector(a);
        fieldVector vV = new fieldVector(b);
        System.out.println(vV.returnSum(vU));

    }

}
