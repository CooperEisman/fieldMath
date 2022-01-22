public class main {
    public static void main(String[] args) {
        double[] a = {3.0,-2.0,7.0};
        double[] b = {3.0,2.0,1.0};
        fieldVector VectorOne = new fieldVector(a);
        fieldConfig f = new fieldConfig();
        System.out.println(f);


        System.out.println(f.addItem(VectorOne,"VectorOne"));
        System.out.println(f);

    }

}
