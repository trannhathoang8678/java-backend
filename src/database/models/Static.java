package database.models;

import java.math.BigDecimal;

public class Static {
    private String maker;
    private int sold;
    private BigDecimal totalMoney;

    public Static(String maker, int sold, BigDecimal totalMoney) {
        this.maker = maker;
        this.sold = sold;
        this.totalMoney = totalMoney;
    }

    public Static() {
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }
    public String toString()
    {
        return("Make: " + maker + " , " + "Sold: " + sold + " , " + "Total money: " + totalMoney);
    }
}
