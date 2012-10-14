import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import com.qiren.*;

/**
 * @author qiren
 * @version 1.0
 * @created 11-10-2012 13:30:54
 */
public class Main {

	public static boolean isNumber(String str){
		return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String city = null;
		double salary = 0;
		String[] citySet = {"广州", "深圳", "上海", "北京"};
		boolean isValidCity = false;
		boolean isValidSalary = true;
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		IndividualIncomeTax taxCal = new IndividualIncomeTax();
		
		System.out.println("-----------------------个人所得税计算器---------------------");
		System.out.println("----------本版本支持的城市有：广州，深圳，上海，北京-------------");
		System.out.println("请输入所在城市：");
		
		//判断输入城市是否有效
		city = in.readLine();
		while (!isValidCity) {
			for (int i = 0; i < citySet.length; i++) {
				if (city.equals(citySet[i])) {
					isValidCity = true;
					break;
				}
			}
			if (!isValidCity) {
				System.out.println("请输入有效的城市！！");
				city = in.readLine();
			} else break;
		}
		
		//输入每笔工资并且判断工资是否合法
		System.out.println("请输入本月所得工资（每笔用空格隔开）：");
		String line = in.readLine();
		while (line != null && !line.equals("quit")) {
			StringTokenizer token = new StringTokenizer(line);
			while (token.hasMoreTokens()) {
				String singleSalary = token.nextToken();
				if (isNumber(singleSalary))
					salary += Double.valueOf(singleSalary);
				else {
					System.out.println("输入的每笔工资应该为数字, 请重新输入:");
					isValidSalary = false;
					break;
				}
			
			}
		
			if (isValidSalary) {
				taxCal.calculate(salary, city);
				System.out.printf("需缴纳的个人所得税为： %.2f\n", taxCal.getTax());
				System.out.printf("需缴纳的五险一金费用为： %.2f\n", taxCal.getFiveOneInsuranceFund());
				System.out.printf("税后总收入为：%.2f\n" , taxCal.getSalaryAfterTax());
				System.out.println("输入 'quit' 结束本次输入,或者继续输入本月工资：");
			}
			
			isValidSalary = true;
			salary = 0;
			line = in.readLine();
		}
	}

}
