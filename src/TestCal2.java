import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.StringTokenizer;

import com.qiren.*;

/**
 * @author qiren
 * @version 1.0
 * @created 11-10-2012 13:30:54
 */
public class TestCal2 {

	public static boolean isNumber(String str){
		return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
	}
	
	public static boolean isDate(String str){
		return str.matches("^[12]\\d{3}[-]?(0[1-9])|1[12]$");
	}
	/**
	 * @param args
	 */
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		double salary = 0;
		boolean isValidSalary = true;
		boolean isValidDate = false;
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		IndividualIncomeTax taxCal = new IndividualIncomeTax();
		
		System.out.println("-----------------------个人所得税计算器---------------------");
		
		//输入每笔工资并且判断工资是否合法
		System.out.println("请输入工资收入时间（如 2012-09），输入'quit'推出系统：");
		String line = in.readLine();
		while (line != null && !line.equals("quit")) {
			String date;
			while(!isDate(line)) {
				System.out.println("时间格式不合法，请重新输入（如2012-09)：");
				line = in.readLine();			
			}
			date = new String(line);
			if (taxCal.getEachTax().containsKey(date))
				salary = taxCal.getEachTax().get(date).get(0);
			else
				salary = 0;
			
			System.out.println("请输入本月所得工资（每笔用空格隔开）：");
			line = in.readLine();
			StringTokenizer token = new StringTokenizer(line);
			while (token.hasMoreTokens()) {
				String singleSalary = token.nextToken();
				if (isNumber(singleSalary)) {
					salary += Double.valueOf(singleSalary);
					taxCal.calculateEachIncome(date, salary);
				}
				else {
					System.out.println("输入的每笔工资应该为数字, 请重新输入:");
					isValidSalary = false;
					break;
				}
			
			}
		
			if (isValidSalary) {
				//taxCal.calculateEachTax(salary);
				for (int t = 1; t < taxCal.getEachTax().get(date).size(); t++)
					System.out.printf(date + " 第" + t + "笔需缴纳的个人所得税为： \t\t\t%.2f\n", taxCal.getEachTax().get(date).get(t));
				System.out.println("输入 'quit' 结束本次输入,或者继续输入发放工资日期：");
				System.out.println("请输入日期：");
			}
			
			line = in.readLine();
			isValidSalary = true;
		}
		System.out.println("系统已退出，欢迎再次使用");
	}

}
