package dto.req;

//百位投注信息
public class Bai{
    public Integer[] getBai() {
        return bai;
    }

    public void setBai(Integer[] bai) {
        this.bai = bai;
    }

    private Integer[] bai;

    public Bai(Integer[] bai) {
        this.bai = bai;
    }

    public static Bai build(Integer... bai) {
        return new Bai(bai);
    }
}
