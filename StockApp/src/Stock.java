import java.sql.Date;

/**
 *
 * STOCK_ID    NUMBER        (PK, 주식번호)
 * STOCK_NAME  VARCHAR2(50)  (종목명)
 * BUY_PRICE   NUMBER        (매수가)
 * QUANTITY    NUMBER        (수량)
 * BUY_DATE    DATE          (매수일)
 * MEMO        VARCHAR2(300) (메모, NULL 허용)
 */
public class Stock {

    private int stockId;       // 주식번호 (PK)
    private String stockName;  // 종목명
    private int buyPrice;      // 매수가
    private int quantity;      // 수량
    private Date buyDate;      // 매수일
    private String memo;       // 메모

    public Stock() {
    }

    public Stock(int stockId, String stockName, int buyPrice, int quantity, Date buyDate, String memo) {
        this.stockId = stockId;
        this.stockName = stockName;
        this.buyPrice = buyPrice;
        this.quantity = quantity;
        this.buyDate = buyDate;
        this.memo = memo;
    }

    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public int getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(int buyPrice) {
        this.buyPrice = buyPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(Date buyDate) {
        this.buyDate = buyDate;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Override
    public String toString() {
        return String.format("%-6d %-15s %-10d %-6d %-12s %-20s",
                stockId, stockName, buyPrice, quantity, buyDate, memo == null ? "" : memo);
    }
}
