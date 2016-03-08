
import io.khasang.webcalc.controller.AppController;
import io.khasang.webcalc.model.Calc;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.ui.Model;

public class AppTest {
    @Test
    public void testHomePage() throws Exception {
        AppController controller = new AppController();
        Model model = null;
        Assert.assertEquals("home", controller.home(model));
    }

    @Test
    public void calcTest() {
        Calc calc = new Calc();
        Assert.assertEquals("55 + 5 * 3 - 5 + 3.5 * 33 / 55.7 = 67.07361<BR>",
                calc.launchCalc("55 + 5 * 3 - 5 + 3.5 * 33 / 55.7"));
        calc = new Calc();
        Assert.assertEquals("-5 + 2 * 5 / -2.5 ^ -2 = 57.5<BR>",
                calc.launchCalc("-5 + 2 * 5 / -2.5 ^ -2"));
        calc = new Calc();
        Assert.assertEquals("2 + 2 * 2 ^ 2 = 10.0<BR>", calc.launchCalc("2 + 2 * 2 ^ 2"));
        calc = new Calc();
        Assert.assertEquals("2 + 2 * 2 ^ -2 = 2.5<BR>", calc.launchCalc("2 + 2 * 2 ^ -2"));
        calc = new Calc();
        Assert.assertEquals("2 + 2 * 2 ^ 0 = 4.0<BR>", calc.launchCalc("2 + 2 * 2 ^ 0"));
        calc = new Calc();
        Assert.assertEquals("2 + 2 * 2 ^ 1 = 6.0<BR>", calc.launchCalc("2 + 2 * 2 ^ 1"));
        calc = new Calc();
        Assert.assertEquals("-5 ^ 2 = 25.0<BR>", calc.launchCalc("-5 ^ 2"));
        calc = new Calc();
        Assert.assertEquals("Введен некорректный формат сообщения<BR>", calc.launchCalc("5 +5"));
        calc = new Calc();
        Assert.assertEquals("Введен некорректный формат сообщения<BR>", calc.launchCalc("5 + 5 5"));
        calc = new Calc();
        Assert.assertEquals("Введен некорректный символ<BR>", calc.launchCalc("5 &* 5"));
        calc = new Calc();
        Assert.assertEquals("Введена пустая строчка<BR>", calc.launchCalc(""));
        calc = new Calc();
        Assert.assertEquals("На ноль делить нельзя<BR>", calc.launchCalc("5 / 0"));
        calc = new Calc();
        Assert.assertEquals("На ноль делить нельзя<BR>", calc.launchCalc("-5 / 0"));
        calc = new Calc();
        Assert.assertEquals("На ноль делить нельзя<BR>", calc.launchCalc("0 / 0"));
    }
}