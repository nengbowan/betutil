package dto.req;

//十位投注信息
public class Shi{
    private Integer[] shi;

    public Integer[] getShi() {
        return shi;
    }

    public void setShi(Integer[] shi) {
        this.shi = shi;
    }

    public static Shi build(Integer... shi) {
        return new Shi(shi);
    }

    public Shi(Integer[] shi) {
        this.shi = shi;
    }
}
