import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Controller
 * - JDBC를 이용해 STOCK 테이블과 실제로 통신하는 클래스
 * - stockList는 selectAll() 조회 결과를 담아두는 캐시(목록) 역할을 한다.
 */
public class StockController {

    // 조회 결과를 담아두는 목록 (View에서 그대로 출력할 때 사용)
    private ArrayList<Stock> stockList = new ArrayList<>();

    /**
     * 등록: STOCK 테이블에 새로운 주식 정보를 INSERT 한다.
     */
    public boolean insertStock(Stock stock) {
        String sql = "INSERT INTO STOCK (STOCK_ID, STOCK_NAME, BUY_PRICE, QUANTITY, BUY_DATE, MEMO) "
                   + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, stock.getStockId());
            pstmt.setString(2, stock.getStockName());
            pstmt.setInt(3, stock.getBuyPrice());
            pstmt.setInt(4, stock.getQuantity());
            pstmt.setDate(5, stock.getBuyDate());
            pstmt.setString(6, stock.getMemo());

            int result = pstmt.executeUpdate();
            return result > 0;

        } catch (SQLException e) {
            System.out.println("[오류] 등록 실패: " + e.getMessage());
            return false;
        }
    }

    /**
     * 조회: STOCK 테이블의 전체 데이터를 조회하여 stockList에 담는다.
     */
    public ArrayList<Stock> selectAll() {
        stockList.clear();
        String sql = "SELECT STOCK_ID, STOCK_NAME, BUY_PRICE, QUANTITY, BUY_DATE, MEMO "
                   + "FROM STOCK ORDER BY STOCK_ID";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Stock stock = new Stock(
                        rs.getInt("STOCK_ID"),
                        rs.getString("STOCK_NAME"),
                        rs.getInt("BUY_PRICE"),
                        rs.getInt("QUANTITY"),
                        rs.getDate("BUY_DATE"),
                        rs.getString("MEMO")
                );
                stockList.add(stock);
            }

        } catch (SQLException e) {
            System.out.println("[오류] 조회 실패: " + e.getMessage());
        }

        return stockList;
    }

    /**
     * 수정: STOCK_ID를 기준으로 나머지 컬럼 값을 UPDATE 한다.
     */
    public boolean updateStock(Stock stock) {
        String sql = "UPDATE STOCK SET STOCK_NAME = ?, BUY_PRICE = ?, QUANTITY = ?, "
                   + "BUY_DATE = ?, MEMO = ? WHERE STOCK_ID = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, stock.getStockName());
            pstmt.setInt(2, stock.getBuyPrice());
            pstmt.setInt(3, stock.getQuantity());
            pstmt.setDate(4, stock.getBuyDate());
            pstmt.setString(5, stock.getMemo());
            pstmt.setInt(6, stock.getStockId());

            int result = pstmt.executeUpdate();
            return result > 0;

        } catch (SQLException e) {
            System.out.println("[오류] 수정 실패: " + e.getMessage());
            return false;
        }
    }

    /**
     * 삭제: STOCK_ID를 기준으로 데이터를 DELETE 한다.
     */
    public boolean deleteStock(int stockId) {
        String sql = "DELETE FROM STOCK WHERE STOCK_ID = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, stockId);
            int result = pstmt.executeUpdate();
            return result > 0;

        } catch (SQLException e) {
            System.out.println("[오류] 삭제 실패: " + e.getMessage());
            return false;
        }
    }

    public ArrayList<Stock> getStockList() {
        return stockList;
    }
}
