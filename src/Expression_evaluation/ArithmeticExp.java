package Expression_evaluation;

public class ArithmeticExp {
    // 成员变量
    private String infixExp;      // 中缀表达式
    private String postfixExp;    // 后缀表达式

    public ArithmeticExp(String infixExp) {
        this.infixExp = infixExp;
    }

    public String getInfixExp() {
        return infixExp;
    }

    public String getPostfixExp() {
        return postfixExp;
    }

    /**
     * 1.转化成后缀表达式（逆波兰表达式）
     */
    public void cover2PostfixExp(){

        // 创建堆栈
        LineStack<Character> ls = new LineStack<Character>();
        this.postfixExp = "";

        // 遍历表达式的每一个字符
        for(int i = 0; this.infixExp != null && i < this.infixExp.length();i++) {
            char ch = this.infixExp.charAt(i);
            if(ch != ' ') {	// 当前字符不为空 时的操作
                if(isLeftBracket(ch)) {	// 是左括号，压栈
                    ls.push(ch);
                } else if(isRightBracket(ch)) { // 是右括号，将所有操作符出栈，直到遇到一个左括号，并将这个左括号丢弃
                    char topOperator = ls.pop();
                    while(!isLeftBracket(topOperator)) {
                        postfixExp += (topOperator + " ");	// 使用空格 隔开
                        topOperator = ls.pop();
                    }
                } else if(isOperator(ch)) {	// 是操作符，要判断优先级，再决定是否需要入栈
                    /**
                     * 如果栈为空，直接进栈。 如果栈非空，则需要将栈顶运算符的优先级和要入栈的运算符的优先级进行比较
                     * 	将栈中比要入栈的运算符优先级高的运算符都出栈，然后将该运算符入栈
                     */
                    if(!ls.isEmpty()) { // 如果栈非空
                        Character topOperator = ls.top();
                        while(topOperator != null && priority(topOperator) >= priority(ch)) {
                            postfixExp += (ls.pop() + " ");
                            if(!ls.isEmpty()) {
                                topOperator = ls.top();
                            }else {
                                break;
                            }
                        }
                    }

                    // 将当前操作符 压栈
                    ls.push(ch);
                } else {
                    if(i > 0 && isNumber(infixExp.charAt(i - 1))) {
                        postfixExp = postfixExp.substring(0, postfixExp.length() - 1) + ch + " ";
                    } else {
                        postfixExp += ch + " ";
                    }
                }

            }
        }

        while(!ls.isEmpty()) {
            postfixExp += (ls.pop() + " ");
        }

        // 去除表达式中的最后一个空格
        postfixExp = postfixExp.trim();
    }

    /**
     * 运算符优先级比较
     */
    public int priority(char charValue) {
        switch(charValue) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
            case '%':
                return 2;
            case '^':
                return 3;
        }
        return 0;
    }


    /**
     * 2.使用后缀表达式 进行 四则运算
     */
    public int calculateExpResult() {
        String[] strs = this.postfixExp.split(" ");
        LineStack<Integer> ls = new LineStack<Integer>();
        int result = 0;
        for(int i = 0; i < strs.length; i++) {

            // 如果是操作符，从堆栈获取两个值，进行运算
            if(strs[i].length() == 1 && isOperator(strs[i].charAt(0))) {
                int num2 = ls.pop();
                int num1 = ls.pop();
                ls.push(calculate2Numbers(num1,num2,strs[i].charAt(0)));
            } else {	// 如果是数字，压入堆栈
                ls.push(Integer.parseInt(strs[i]));
            }
        }
        return ls.pop();
    }

    /**
     * 两数的运算操作
     */
    public Integer calculate2Numbers(int num1, int num2, char operator) {
        switch(operator) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                return num1 / num2;
            case '%':
                return num1 % num2;
            case '^':
                return num1 ^ num2;
        }
        return null;
    }

    /**
     * 判断是否是左括号
     */
    public boolean isLeftBracket(char ch) {
        if(ch == '(') {
            return true;
        }
        return false;
    }

    /**
     * 判断是否是右括号
     */
    public boolean isRightBracket(char ch) {
        if(ch == ')') {
            return true;
        }
        return false;
    }

    /**
     * 判断是否是数字
     */
    public boolean isNumber(char ch) {
        if(ch >= '0' && ch <= '9') {
            return true;
        }
        return false;
    }

    /**
     * 判断是否是操作符
     */
    public boolean isOperator(char ch) {
        if(ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '^' || ch == '%') {
            return true;
        }
        return false;
    }
}
