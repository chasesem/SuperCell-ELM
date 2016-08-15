import java.util.List;

import javax.annotation.Resource;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.supercell.elma.dao.ComplaintDao;
import com.supercell.elma.dao.MerchantStateDao;
import com.supercell.elma.dao.RecommendedDishesDao;
import com.supercell.elma.entity.Complaint;
import com.supercell.elma.entity.MerchantState;
import com.supercell.elma.entity.RecommendedDishes;
import com.supercell.elma.service.ListenerService;
import com.supercell.elma.service.MerchantManageService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext-common.xml"})
public class DaoTest {
	@Resource(name="complaintDao")
	private ComplaintDao complaintDao;
	@Resource(name="recommendeddishesDao")
	private RecommendedDishesDao dao;
	@Test
	public void test() {

	}
}
