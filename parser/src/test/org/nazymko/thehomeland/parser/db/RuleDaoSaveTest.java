package org.nazymko.thehomeland.parser.db;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by nazymko.patronus@gmail.com.
 */

public class RuleDaoSaveTest {

    @Test
    public void testSave() throws Exception {
        String regexp = "Дата:(\\s*(((\\d\\d)(([02468][048])|([13579][26]))-02-29)|((\\d\\d)(\\d\\d))-((((0\\d)|(1[0-2]))-((0\\d)|(1\\d)|(2[0-8])))|((((0[13578])|(1[02]))-31)|(((0[1,3-9])|(1[0-2]))-(29|30)))))\\s([01]\\d|2[0-3]):([0-5]\\d):([0-5]\\d))";
        String str = "Дата: 2016-03-04 21:00:00  Тип поїздки: Гірськолижні Залишилось місць: 4 Їдемо на: Неоплан 51 місце (Weber) Живемо в: ГОТЕЛЬ БАРТКА 5-8.03, КОТЕДЖ БАРТКА 5-8.03 Орг.збір: 1200 грн. З клубною карткою: 1100 грн.";

        Pattern compile = Pattern.compile(regexp);
        Matcher matcher = compile.matcher(str);

        boolean find = matcher.find();
        System.out.println("find = " + find);
        System.out.println("matcher = " + matcher);
        for (int i = 0; i < matcher.groupCount(); i++) {
            try {
                System.out.println(i+ " : matcher = " + matcher.group(i));
            } catch (Throwable any) {
                System.err.println(any);
            }
        }
    }


}