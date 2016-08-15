package misc;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
public class Test {
	@org.junit.Test
	public void test() throws ClassNotFoundException {
		Class.forName("com.supercell.elmm.util.ClientFactory");
	}
}
