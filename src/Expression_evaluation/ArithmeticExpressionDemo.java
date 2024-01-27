package Expression_evaluation;

import java.util.Scanner;

public class ArithmeticExpressionDemo {

    //	中缀表达式：9+(3-1)*3+10/2
    public static void main(String[] args) {
        // 从控制台获取中缀表达式
        System.out.println("请输入表达式：");
        Scanner sc = new Scanner(System.in);
        String infixExp = sc.nextLine();
        sc.close();

        // 创建对象，进行后续操作
        ArithmeticExp ae = new ArithmeticExp(infixExp);
        System.out.println("中缀表达式："+ae.getInfixExp());

        // 转化为后缀表达式
        ae.cover2PostfixExp();
        System.out.println("后缀表达式："+ae.getPostfixExp());

        // 使用后缀表达式 进行 四则运算
        int calculateExpResult = ae.calculateExpResult();

        // 结果输出
        System.out.println("计算结果："+calculateExpResult);

    }
}
