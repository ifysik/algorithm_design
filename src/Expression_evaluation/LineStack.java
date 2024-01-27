package Expression_evaluation;

import java.util.ArrayList;
import java.util.List;

public class LineStack<T> {
    private List<T> data;


    /**
     * 无参 构造方法
     */
    public LineStack() {
        this.data = new ArrayList<T>();
    }
    /**
     * 压栈
     */
    public void push(T e) {
        // 不需要判断是否已满
        this.data.add(e);
    }

    /**
     * 弹栈
     */
    public T pop() {
        if(isEmpty()) {
            throw new RuntimeException("栈空，无法弹栈~");
        }
        return this.data.remove(this.data.size() - 1);
    }

    /**
     * 获取栈顶元素
     */
    public T top() {
        if(isEmpty()) {
            throw new RuntimeException("栈空，无法获取栈顶元素~");
        }
        return this.data.get(this.data.size() - 1);
    }

    /**
     * 判断栈空
     */
    public boolean isEmpty() {
        if(this.data.size() == 0) {
            return true;
        }
        return false;
    }
}
