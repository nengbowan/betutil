package dto.req;

//��λͶע��Ϣ
public class Wan{
    
    private Integer[] wan;

    public Integer[] getWan() {
        return wan;
    }

    public Wan(Integer[] wan) {
        this.wan = wan;
    }

    public void setWan(Integer[] wan) {
        this.wan = wan;
    }

    public static Wan build(Integer... wan) {
        return new Wan(wan);
    }

}