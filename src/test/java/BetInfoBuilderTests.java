import dto.req.*;

public class BetInfoBuilderTests {
    public static void main(String[] args) {
        System.out.println(BetInfoBuilder.toString(Ge.build(new Integer[]{1}),
                Shi.build(new Integer[]{1, 2}),
                Bai.build(new Integer[]{1, 2, 3}),
                Qian.build(new Integer[]{1, 2, 3,4}),
                Wan.build(new Integer[]{1, 2, 3,4,5})));
    }
}
