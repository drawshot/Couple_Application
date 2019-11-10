package jihye.wooogi.jihye_app;

public class coupon {
    private String coupon;
    private String s_date;
    private String is_use;
    private String e_date;
    private String s_text;
    private String e_text;
    private String food_name;
    private String is_click;

    public coupon() {
    }

    public coupon(String coupon, String s_date, String e_date, String is_use, String s_text, String e_text, String food_name, String is_click) {
        this.coupon = coupon;
        this.s_date = s_date;
        this.e_date = e_date;
        this.is_use = is_use;
        this.s_text = s_text;
        this.e_text = e_text;
        this.food_name = food_name;
        this.is_click = is_click;
    }

    public String getCoupon() {
        return coupon;
    }

    public void setCoupon(String coupon) {
        this.coupon = coupon;
    }

    public String getS_Date() {
        return s_date;
    }

    public void setS_Date(String s_date) {
        this.s_date = s_date;
    }

    public String getE_Date() {
        return e_date;
    }

    public void setE_Date(String e_date) {
        this.e_date = e_date;
    }

    public String getS_Text() {
        return s_text;
    }

    public void setS_Text(String s_text) {
        this.s_text = s_text;
    }

    public String getE_Text() {
        return e_text;
    }

    public void setE_Text(String e_text) {
        this.e_text = e_text;
    }

    public String getIs_use() {
        return is_use;
    }

    public void setIs_use(String is_use) {
        this.is_use = is_use;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public String getIs_click() {
        return is_click;
    }

    public void setIs_click(String is_click) {
        this.is_click = is_click;
    }
}
