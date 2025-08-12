package fpoly.sonhaph40315_20_6.duan_prostore.model;

public class ThongKe_Model {

        private String name;
        private int totalQuantity;
        private double totalRevenue;

        public ThongKe_Model(String name, int totalQuantity, double totalRevenue) {
            this.name = name;
            this.totalQuantity = totalQuantity;
            this.totalRevenue = totalRevenue;
        }

        public String getName() {
            return name;
        }

        public int getTotalQuantity() {
            return totalQuantity;
        }

        public double getTotalRevenue() {
            return totalRevenue;
        }

    public void setName(String name) {
        this.name = name;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}


