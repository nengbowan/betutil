package dto.req;

//��λͶע��Ϣ
public class Ge{
    public Ge(Integer[] ge) {
        this.ge = ge;
    }

    private Integer[] ge;

    public Integer[] getGe() {
        return ge;
    }

    public static Ge build(Integer... ge) {
        return new Ge(ge);
    }

}
