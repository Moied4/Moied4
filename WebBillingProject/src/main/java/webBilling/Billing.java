package webBilling;

import java.util.Objects;

public class Billing {
    private int client_ID;
    private String client_LName;
    private String client_FName;
    private String product_Name;
    private double prd_Price;
    private int prd_Qty;

    public static double Fed_Tax = 0.05;  // 5%
    public static double Prv_Tax = 0.07;  // 7%

    public Billing() {}

    public Billing(int client_ID, String client_LName, String client_FName,
                   String product_Name, double prd_Price, int prd_Qty) {
        this.client_ID = client_ID;
        this.client_LName = client_LName;
        this.client_FName = client_FName;
        this.product_Name = product_Name;
        this.prd_Price = prd_Price;
        this.prd_Qty = prd_Qty;
    }

    // Getters and setters

    public int getClient_ID() {
        return client_ID;
    }

    public void setClient_ID(int client_ID) {
        this.client_ID = client_ID;
    }

    public String getClient_LName() {
        return client_LName;
    }

    public void setClient_LName(String client_LName) {
        this.client_LName = client_LName;
    }

    public String getClient_FName() {
        return client_FName;
    }

    public void setClient_FName(String client_FName) {
        this.client_FName = client_FName;
    }

    public String getProduct_Name() {
        return product_Name;
    }

    public void setProduct_Name(String product_Name) {
        this.product_Name = product_Name;
    }

    public double getPrd_Price() {
        return prd_Price;
    }

    public void setPrd_Price(double prd_Price) {
        this.prd_Price = prd_Price;
    }

    public int getPrd_Qty() {
        return prd_Qty;
    }

    public void setPrd_Qty(int prd_Qty) {
        this.prd_Qty = prd_Qty;
    }

    // CalculateBilling method: total = price * qty + taxes
    public double CalculateBilling() {
        double subtotal = prd_Price * prd_Qty;
        double totalTax = subtotal * (Fed_Tax + Prv_Tax);
        return subtotal + totalTax;
    }

    @Override
    public String toString() {
        return "Billing{" +
                "client_ID=" + client_ID +
                ", client_LName='" + client_LName + '\'' +
                ", client_FName='" + client_FName + '\'' +
                ", product_Name='" + product_Name + '\'' +
                ", prd_Price=" + prd_Price +
                ", prd_Qty=" + prd_Qty +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Billing billing = (Billing) o;

        return client_ID == billing.client_ID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(client_ID);
    }
}
