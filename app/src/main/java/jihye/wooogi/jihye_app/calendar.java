package jihye.wooogi.jihye_app;


public class calendar {
    private int contents_num;
    private int current_num;
    private String date;
    private String contents_1;
    private String contents_2;
    private String contents_3;
    private String contents_4;


    public calendar() {
    }

    public calendar(int contents_num, int current_num, String date, String contents_1, String contents_2, String contents_3, String contents_4) {
        this.contents_num = contents_num;
        this.current_num = current_num;
        this.date = date;
        this.contents_1 = contents_1;
        this.contents_2 = contents_2;
        this.contents_3 = contents_3;
        this.contents_4 = contents_4;
    }

    public int getContents_num() {
        return contents_num;
    }

    public void setContents_num(int contents_num) {
        this.contents_num = contents_num;
    }

    public int getCurrent_num() {
        return current_num;
    }

    public void setCurrent_num(int current_num) {
        this.current_num = current_num;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContents_1() {
        return contents_1;
    }

    public void setContents_1(String contents_1) {
        this.contents_1 = contents_1;
    }

    public String getContents_2() {
        return contents_2;
    }

    public void setContents_2(String contents_2) {
        this.contents_2 = contents_2;
    }

    public String getContents_3() {
        return contents_3;
    }

    public void setContents_3(String contents_3) {
        this.contents_3 = contents_3;
    }

    public String getContents_4() {
        return contents_4;
    }

    public void setContents_4(String contents_4) {
        this.contents_4 = contents_4;
    }
}
