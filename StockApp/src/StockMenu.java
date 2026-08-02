import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * View
 * - 사용자로부터 입력을 받아 Controller에 작업을 위임하고,
 *   결과를 화면에 출력하는 역할을 담당한다.
 */
public class StockMenu {

    private Scanner sc = new Scanner(System.in);
    private StockController controller = new StockController();
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 메인 메뉴: 프로그램 진입점 역할
     */
    public void mainMenu() {
        while (true) {
            System.out.println("\n========== 주식 관리 프로그램 ==========");
            System.out.println("1. 등록  2. 조회  3. 수정  4. 삭제  5. 종료");
            System.out.print("메뉴 선택 >> ");

            String input = sc.nextLine().trim();
            switch (input) {
                case "1":
                    insertStock();
                    break;
                case "2":
                    selectAll();
                    break;
                case "3":
                    updateStock();
                    break;
                case "4":
                    deleteStock();
                    break;
                case "5":
                    System.out.println("프로그램을 종료합니다.");
                    return;
                default:
                    System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
            }
        }
    }

    /**
     * 등록 화면
     */
    public void insertStock() {
        try {
            System.out.print("주식번호: ");
            int stockId = Integer.parseInt(sc.nextLine().trim());

            System.out.print("종목명: ");
            String stockName = sc.nextLine().trim();

            System.out.print("매수가: ");
            int buyPrice = Integer.parseInt(sc.nextLine().trim());

            System.out.print("수량: ");
            int quantity = Integer.parseInt(sc.nextLine().trim());

            System.out.print("매수일 (yyyy-MM-dd): ");
            Date buyDate = new Date(sdf.parse(sc.nextLine().trim()).getTime());

            System.out.print("메모 (없으면 엔터): ");
            String memo = sc.nextLine().trim();
            if (memo.isEmpty()) memo = null;

            Stock stock = new Stock(stockId, stockName, buyPrice, quantity, buyDate, memo);
            boolean result = controller.insertStock(stock);

            System.out.println(result ? "등록 완료" : "등록 실패");

        } catch (NumberFormatException e) {
            System.out.println("[오류] 숫자 형식이 올바르지 않습니다.");
        } catch (ParseException e) {
            System.out.println("[오류] 날짜 형식이 올바르지 않습니다. (예: 2026-08-01)");
        }
    }

    /**
     * 조회 화면
     */
    public void selectAll() {
        ArrayList<Stock> list = controller.selectAll();

        System.out.println("\n번호   종목명          매수가     수량   매수일       메모");
        System.out.println("---------------------------------------------------------------");
        if (list.isEmpty()) {
            System.out.println("등록된 주식 정보가 없습니다.");
        } else {
            for (Stock stock : list) {
                System.out.println(stock);
            }
        }
    }

    /**
     * 수정 화면
     */
    public void updateStock() {
        try {
            System.out.print("수정할 주식번호: ");
            int stockId = Integer.parseInt(sc.nextLine().trim());

            System.out.print("종목명: ");
            String stockName = sc.nextLine().trim();

            System.out.print("매수가: ");
            int buyPrice = Integer.parseInt(sc.nextLine().trim());

            System.out.print("수량: ");
            int quantity = Integer.parseInt(sc.nextLine().trim());

            System.out.print("매수일 (yyyy-MM-dd): ");
            Date buyDate = new Date(sdf.parse(sc.nextLine().trim()).getTime());

            System.out.print("메모 (없으면 엔터): ");
            String memo = sc.nextLine().trim();
            if (memo.isEmpty()) memo = null;

            Stock stock = new Stock(stockId, stockName, buyPrice, quantity, buyDate, memo);
            boolean result = controller.updateStock(stock);

            System.out.println(result ? "수정 완료" : "수정 실패 (해당 주식번호가 없습니다)");

        } catch (NumberFormatException e) {
            System.out.println("[오류] 숫자 형식이 올바르지 않습니다.");
        } catch (ParseException e) {
            System.out.println("[오류] 날짜 형식이 올바르지 않습니다. (예: 2026-08-01)");
        }
    }

    /**
     * 삭제 화면
     */
    public void deleteStock() {
        try {
            System.out.print("삭제할 주식번호: ");
            int stockId = Integer.parseInt(sc.nextLine().trim());

            boolean result = controller.deleteStock(stockId);
            System.out.println(result ? "삭제 완료" : "삭제 실패 (해당 주식번호가 없습니다)");

        } catch (NumberFormatException e) {
            System.out.println("[오류] 숫자 형식이 올바르지 않습니다.");
        }
    }
}
