package com.block.framework.core.http;

public class HttpUri {

	private String schema;
	
	private String path;
	
	
	
	public String getSchema() {
		return schema;
	}



	public void setSchema(String schema) {
		this.schema = schema;
	}



	public String getPath() {
		return path;
	}



	public void setPath(String path) {
		this.path = path;
	}



	@Override
	public boolean equals(Object obj){
		HttpUri o = (HttpUri)obj;
		return this.schema.equals(o.getSchema())&&this.path.equals(o.getSchema());
	}
	@Override
	public int hashCode(){
		int h=schema.hashCode();
		return (schema.hashCode()+path.hashCode())^ (h >>> 16);
	}
}
