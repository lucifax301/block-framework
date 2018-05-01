package com.block.framework.core.interceptor.db;

import java.sql.Connection;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import com.block.framework.common.model.BaseModel;
import com.block.framework.common.model.DBModel;
import com.block.framework.common.util.ThreadTruck;
import com.block.framework.core.constant.CoreConstants;
import com.block.framework.core.context.RequestContext;




/**
 * sql路由拦截器
 * @author lilixc
 *
 */
@Intercepts(@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class}))
public class SqlRouteInterceptor implements Interceptor{
	
	public static final String SQLKEY = "delegate.boundSql.sql";
	public static final String PREFIX = "/*!mycat: schema = %s */ %s";
	
	@SuppressWarnings("rawtypes")
	@Override
	public Object intercept(final Invocation invocation) throws Throwable {
		StatementHandler statementHandler = (StatementHandler) invocation.getTarget(); 
		Object paramObject = statementHandler.getParameterHandler().getParameterObject();
		String schema = null;
		boolean mgrdb = false;
//		if (paramObject instanceof BaseModel) {
//			BaseModel baseModel = (BaseModel) paramObject;
//			schema = baseModel.getDblink();
//			mgrdb = baseModel.getMgrdb();
//			//分页插件中封装了原参数 详情可见源码
//		} else if (paramObject instanceof Map) {
//			Map params = (Map) paramObject;
//			schema  = (String)params.get("dblink");
//			if (params.get("mgrdb") != null) {
//				mgrdb = (boolean)params.get("mgrdb");
//			} else {
//				mgrdb = false;
//			}
//		}
		
		
		//dao有指定哪个库
		//目前除了mgr db外，其他不指定
		String DB_ROUTE = (String)ThreadTruck.get(CoreConstants.ROUTE_DB);
		if(DB_ROUTE!=null){
			if(CoreConstants.MRG.equals(DB_ROUTE)){
				mgrdb = true;
			}else{//不是mgr就用用户本身的db路由
				DBModel user = RequestContext.<DBModel>getValue(CoreConstants.USER_SESSION);
				if(user!=null){
					schema = RequestContext.<DBModel>getValue(CoreConstants.USER_SESSION).getDblink();;
					mgrdb = RequestContext.<DBModel>getValue(CoreConstants.USER_SESSION).isMgrdb();
				}else{
					mgrdb = true;
				}
			}
		}else{
			DBModel user = RequestContext.<DBModel>getValue(CoreConstants.USER_SESSION);
			if(user!=null){
				schema = RequestContext.<DBModel>getValue(CoreConstants.USER_SESSION).getDblink();
				mgrdb = RequestContext.<DBModel>getValue(CoreConstants.USER_SESSION).isMgrdb();
			}else{
				mgrdb = true;
			}
		}
		
		//如果设置mgrdb=true时，连接走管理数据库时，不会做数据路由，直接操作管理库
		if (StringUtils.isNotEmpty(schema) && mgrdb == false) {
			MetaObject metaStatementHandler = SystemMetaObject.forObject(statementHandler);  
			String sql = (String) metaStatementHandler.getValue(SQLKEY);  
			sql = String.format(PREFIX, schema, sql); 
			metaStatementHandler.setValue(SQLKEY, sql);  
		}
		return invocation.proceed();  
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties arg0) {
	}

}
