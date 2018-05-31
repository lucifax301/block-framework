import java.io.File;
import java.io.FileInputStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.block.framework.file.FileItem;
import com.block.framework.file.FileService;
import com.block.framework.file.FileServiceFactory;


@RunWith(SpringJUnit4ClassRunner.class) // 整合 
//@ContextConfiguration(locations={"classpath:spring-init.xml","classpath:spring-mvc.xml"}) // 加载配置
@ContextConfiguration(locations={"classpath:spring-file-init.xml"}) 
public class TestFile {

	@Autowired
	FileServiceFactory facotory;
	
	@Test
    public void testUpload() throws Exception { 
		FileService service = facotory.getService("qiniu");
		FileItem item =new FileItem();
		File file=new File("f:\\test1.jpg");
		FileInputStream in = new FileInputStream(file);
		item.setInput(in);
		item.setFileName("test1");
		item.setSuffix(".jpg");
		System.out.println( service.updateImg(file));
		in.close();
	}
}
