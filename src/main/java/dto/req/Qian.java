package dto.req;

//ǧλͶע��Ϣ
public class Qian{
    private Integer[] qian;

    public Qian(Integer[] qian) {
        this.qian = qian;
    }

    public Integer[] getQian() {
        return qian;
    }

    public void setQian(Integer[] qian) {
        this.qian = qian;
    }

    public static Qian build(Integer... qian) {
       return new Qian(qian);
    }


}
