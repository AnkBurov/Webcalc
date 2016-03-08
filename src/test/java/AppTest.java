
import io.khasang.webcalc.controller.AppController;
import io.khasang.webcalc.model.Calc;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

public class AppTest {
//    ApplicationContext context = new AnnotationConfigApplicationContext(io.khasang.webcalc.configuration.RootConfig.class);
//    Calc calc = (Calc) context.getBean("calc");

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