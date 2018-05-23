package demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;



@RunWith(SpringJUnit4ClassRunner.class) // 整合 
//@ContextConfiguration(locations={"classpath:spring-init.xml","classpath:spring-mvc.xml"}) // 加载配置
@ContextConfiguration(locations={"classpath:spring-local-init.xml","classpath:spring-mvc.xml"}) // 加载配置
@WebAppConfiguration("classpath:")
public class MockTest {

	@Autowired
	private WebApplicationContext wac;
	
	@Test
    public void testLogin() throws Exception { 
    	
	    MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		
		//TestController.edit(null);
		
		MockMvc mockMvc =MockMvcBuilders.webAppContextSetup(wac).build();
		ResultActions resultActions = mockMvc.perform((MockMvcRequestBuilders.get("/demo/add").param("id", "1").param("name", "test").param("val", "valvalue").param("cuid", "12")));
		//ResultActions resultActions = mockMvc.perform((MockMvcRequestBuilders.get("/demo/list").param("id", "1").param("name", "test").param("val", "valvalue").param("cuid", "12")));
		MvcResult mvcResult = resultActions.andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println("response:" + result);
		
    } 
}
