package webBillingService;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Billing")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {
    "client_ID",
    "client_LName",
    "client_FName",
    "product_Name",
    "prd_Price",
    "prd_Qty"
})
public class Billing implements Serializable {

    private static final long serialVersionUID = 1L;

    private int client_ID;
    private String client_LName;
    private String client_FName;
    private String product_Name;
    private double prd_Price;
    private int prd_Qty;

    // Getters and Setters
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

    // Readable toString() for debugging
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
}
