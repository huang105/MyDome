package bean;

/**
 * Created by xiaowu on 2017/6/9.
 */

public class MessageBean<T> extends BaseMessage {

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
