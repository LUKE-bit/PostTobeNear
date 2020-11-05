package model;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

public class Order {
    private int orderId;
    private String type;
    private int money;
    private Timestamp time;
    private String description;
    private int isTake;
    private int isDone;
    private int postId;
    private int takeId;
    public int getIsTake() {
        return isTake;
    }

    public void setIsTake(int isTake) {
        this.isTake = isTake;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }


    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIsDone() {
        return isDone;
    }

    public void setIsDone(int isDone) {
        this.isDone = isDone;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getTakeId() {
        return takeId;
    }

    public void setTakeId(int takeId) {
        this.takeId = takeId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", type='" + type + '\'' +
                ", money=" + money +
                ", time=" + time +
                ", description='" + description + '\'' +
                ", isTake=" + isTake +
                ", isDone=" + isDone +
                ", postId=" + postId +
                ", takeId=" + takeId +
                '}';
    }
}
