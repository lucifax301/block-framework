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
import com.qiniu.util.Auth;

public class QiniuPicUtil {

//	private final static String BUCKET_PUBLIC = "liliyun";
//	private final static String AK = "wTZfpMov09_Pvgpzt01kVbTGoFKMcMf2CUmYs5n2";
//	private final static String SK = "CzQMFHoGeNOVuF_0sG96oFzrQtVdRx25-aQrudp1";	
//	private final static String DOMAIN = "http://7xnvu2.com1.z0.glb.clouddn.com/";
	private final static String BUCKET_PUBLIC = "block";
	private final static String AK = "PTItNpj94aCURrMY4wGrj2aEo_Rq0ah4hkwBKuZo";
	private final static String SK = "uzqUtaJAon3qMcVJUvH_lfh-VkFVR00BrkGx-gM4";	
	private final static String DOMAIN = "http://p9ldod2bi.bkt.clouddn.com/";
	
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
//      Configuration cfg = new Configuration(Zone.zone0());
//      //...其他参数参考类注释
//      UploadManager uploadManager = new UploadManager(cfg);
//      Response response = uploadManager.put(localFilePath, key, upToken);
//      //解析上传成功的结果
//      DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);

        return ret;

    }
    
    public static DefaultPutRet uploadFileNew(File file) throws AuthException, JSONException, QiniuException {
        String uptoken = getUpToken();

        // 可选的上传选项，具体说明请参见使用手册。
        PutExtra extra = new PutExtra();

        // 上传文件
        //PutRet ret = IoApi.putFile(uptoken, file.getName(), file.getAbsolutePath(), extra);
        Configuration cfg = new Configuration(Zone.zone2());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        Response response = uploadManager.put(file.getAbsolutePath(), file.getName(), uptoken);
        //解析上传成功的结果
        DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);

        return putRet;

    }
    
    public static DefaultPutRet uploadFileOverride(File file) throws AuthException, JSONException, QiniuException {
        String uptoken = getUpToken();

        // 可选的上传选项，具体说明请参见使用手册。
        PutExtra extra = new PutExtra();
        
        // 上传文件
        //PutRet ret = IoApi.putFile(uptoken, file.getName(), file.getAbsolutePath(), extra);
        Configuration cfg = new Configuration(Zone.zone2());
        BucketManager bucketManager = new BucketManager(auth, cfg);
        bucketManager.delete(BUCKET_PUBLIC, file.getName());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        Response response = uploadManager.put(file.getAbsolutePath(), file.getName(), uptoken);
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
//        Configuration cfg = new Configuration(Zone.zone0());
//        //...其他参数参考类注释
//        UploadManager uploadManager = new UploadManager(cfg);
//        Response response = uploadManager.put(localFilePath, key, upToken);
//        //解析上传成功的结果
//        DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);

        return ret;

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
