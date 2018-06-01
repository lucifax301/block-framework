package com.block.framework.file.qiniu;

import java.io.File;

import org.json.JSONException;

import com.block.framework.common.util.StringUtil;
import com.google.gson.Gson;
import com.qiniu.api.auth.AuthException;
import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.io.IoApi;
import com.qiniu.api.io.PutExtra;
import com.qiniu.api.io.PutRet;
import com.qiniu.api.rs.PutPolicy;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.util.Auth;

public class QiniuPicUtil {

//	private final static String BUCKET_PUBLIC = "liliyun";
//	private final static String AK = "wTZfpMov09_Pvgpzt01kVbTGoFKMcMf2CUmYs5n2";
//	private final static String SK = "CzQMFHoGeNOVuF_0sG96oFzrQtVdRx25-aQrudp1";	
//	private final static String DOMAIN = "http://7xnvu2.com1.z0.glb.clouddn.com/";
//	private final static String BUCKET_PUBLIC = "block";
//	private final static String AK = "PTItNpj94aCURrMY4wGrj2aEo_Rq0ah4hkwBKuZo";
//	private final static String SK = "uzqUtaJAon3qMcVJUvH_lfh-VkFVR00BrkGx-gM4";	
//	private final static String DOMAIN = "http://p9ldod2bi.bkt.clouddn.com/";
	
	private static String BUCKET_PUBLIC = QiniuConfig.getConfig().getBucklet();
	private static String AK = QiniuConfig.getConfig().getAk();
	private static String SK = QiniuConfig.getConfig().getSk();	
	private static String DOMAIN = QiniuConfig.getConfig().getDomain();
	private static String zone = QiniuConfig.getConfig().getZone();
	
	
	static Auth auth = Auth.create(AK, SK);
	
	/**
	 * 获取七牛上存储的图片路径
	 * @param headIconName
	 * @return
	 */
	public static String getPicFromQN(String headIconName){

		String resoureName ="FnjqWFunVDKhLb3fDO0OeDCFzuWB";//默认图片
		if(StringUtil.isNullString(headIconName)){
			return auth.privateDownloadUrl(DOMAIN + resoureName);
		}else {
			long expires = System.currentTimeMillis()/1000L + 600; //十分钟
			return auth.privateDownloadUrl(DOMAIN + headIconName, expires);
		}
	}
	
	/**
	 * 获取七牛图片上传public token
	 * @return
	 */
	public static String getPublicToken(){
		return auth.uploadToken(BUCKET_PUBLIC);
	}
	
	
    //通过File上传
    public static PutRet uploadFile(File file) throws AuthException, JSONException {
        String uptoken = getUpToken();

        // 可选的上传选项，具体说明请参见使用手册。
        PutExtra extra = new PutExtra();

        // 上传文件
        PutRet ret = IoApi.putFile(uptoken, file.getName(), file.getAbsolutePath(), extra);

        return ret;

    }
    
    public static DefaultPutRet uploadFileNew(File file) throws AuthException, JSONException, QiniuException {
        String uptoken = getUpToken();

        // 可选的上传选项，具体说明请参见使用手册。
        PutExtra extra = new PutExtra();
        Zone zoneObbj = null;
        if("zone0".equals(zone)){
        	zoneObbj = Zone.zone0();
        }else if("zone1".equals(zone)){
        	zoneObbj = Zone.zone1();
        }else if("zone2".equals(zone)){
        	zoneObbj = Zone.zone2();
        }
        System.out.println(BUCKET_PUBLIC+" "+DOMAIN+" "+zone);
        // 上传文件
        Configuration cfg = new Configuration(zoneObbj);
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //Response response = uploadManager.put(file.getAbsolutePath(), file.getName(), uptoken);
        Response response = uploadManager.put(file.getAbsolutePath(), null, uptoken);
        //解析上传成功的结果
        DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);

        return putRet;

    }
    
    
    
    public static PutRet uploadFileWithRandomName(File file) throws AuthException, JSONException {
        String uptoken = getUpToken();

        // 可选的上传选项，具体说明请参见使用手册。
        PutExtra extra = new PutExtra();

        // 上传文件
        PutRet ret = IoApi.putFile(uptoken, null, file.getAbsolutePath(), extra);

        return ret;

    }
    
    public static DefaultPutRet uploadFileWithRandomNameNew(File file) throws AuthException, JSONException, QiniuException {
        String uptoken = getUpToken();

        // 可选的上传选项，具体说明请参见使用手册。
        PutExtra extra = new PutExtra();

        // 上传文件
        //PutRet ret = IoApi.putFile(uptoken, null, file.getAbsolutePath(), extra);
        Zone zoneObbj = null;
        if("zone0".equals(zone)){
        	zoneObbj = Zone.zone0();
        }else if("zone1".equals(zone)){
        	zoneObbj = Zone.zone1();
        }else if("zone2".equals(zone)){
        	zoneObbj = Zone.zone2();
        }
        Configuration cfg = new Configuration(zoneObbj);
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        Response response = uploadManager.put(file.getAbsolutePath(), null, uptoken);
        //解析上传成功的结果
        DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);

        return putRet;

    }
    
    //获取凭证
    private static String getUpToken() throws AuthException, JSONException {
        Mac mac = getMac();
        PutPolicy putPolicy = new PutPolicy(BUCKET_PUBLIC);
        String uptoken = putPolicy.token(mac);
        return uptoken;
    }

    private static Mac getMac() {
        Mac mac = new Mac(AK, SK);
        return mac;
    }
}
