import cn.edu.aynu.rjxy.service.AccountService;


public class TestTransfer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//实现从zs账户上转账200到ls账户上
		AccountService.transfer("zss", "ls", 200);

	}

}
