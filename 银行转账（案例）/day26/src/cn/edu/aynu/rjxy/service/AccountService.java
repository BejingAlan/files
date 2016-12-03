package cn.edu.aynu.rjxy.service;

import cn.edu.aynu.rjxy.bean.Account;
import cn.edu.aynu.rjxy.dao.AccountDao;
import cn.edu.aynu.rjxy.utils.JDBCUtils;

public class AccountService {
	//转账事务
	public static void transfer(String fromName,String toName,double balance){
		try {
			//开启事务
			JDBCUtils.startTransaction();
			
			AccountDao dao = new AccountDao();
			//查询两个账户的金额
			Account accountFrom = dao.find(fromName);
			Account accountTo = dao.find(toName);
			//判断是否可以转账
			if(balance<accountFrom.getMoney()){
				//可以转账
				//设置转出账户的金额
				accountFrom.setMoney(accountFrom.getMoney()-balance);
				//设置转入账户的金额
				accountTo.setMoney(accountTo.getMoney()+balance);
				//执行数据库更改操作
				dao.update(accountFrom); 
				dao.update(accountTo);
				//提交事务
				JDBCUtils.commit();
				System.out.println("事务提交成功");
			}else{
				System.out.println("转出账户金额不足");
			}
			
		} catch (Exception e) {
			//回滚事务
			JDBCUtils.rollback();
			System.out.println("事务提交失败");
			e.printStackTrace();
		}finally{
			//释放资源
			JDBCUtils.close();
		}
	}
}
