package xjnu.edu.weixin.mall.utils;

import java.util.Collection;
import java.util.List;

import org.jeecgframework.core.util.ReflectHelper;
import org.jeecgframework.core.util.oConvertUtils;

public class JsArrayCreator {
	
	
	public String getJsArray ( String title , JsArrayItemModal parrentItemModal , JsArrayItemModal childItemModal ,List parrentList ) {
		String resultString = "'" + title + "':[" ;
		resultString += getJsArray ( parrentItemModal , childItemModal , parrentList ) ;
		resultString += "]";
		return resultString;
	}
	
	public String getJsArray ( String title , JsArrayItemModal parrentItemModal ,List parrentList ) {
		String resultString = "'" + title + "':[" ;
		resultString += getJsArray ( parrentItemModal , parrentList ) ;
		resultString += "]";
		return resultString;
	}

	
	
	private String getJsArray ( JsArrayItemModal parrentItemModal , JsArrayItemModal childItemModal ,List parrentList ) {

		StringBuffer resultBuffer = new StringBuffer();
		if(parrentList !=  null && parrentList.size() > 0 )
		{
			for (Object item : parrentList) {
				ReflectHelper reflectHelper = new ReflectHelper(item);
				String id = oConvertUtils.getString(reflectHelper.getMethodValue(parrentItemModal.getIdField()));
				String text = oConvertUtils.getString(reflectHelper.getMethodValue(parrentItemModal.getTextField()));
				resultBuffer.append("[\"");
				resultBuffer.append(id);
				resultBuffer.append("\",\"");
				resultBuffer.append(toHexString(text) );
				resultBuffer.append("\"");
				resultBuffer.append(parseChildNode(  parrentItemModal ,  childItemModal ,  item ) );
				resultBuffer.append("],");
				
			}
			resultBuffer.deleteCharAt(resultBuffer.length()-1);
		}
		
		return resultBuffer.toString() ;
	}
	
	
	private String getJsArray ( JsArrayItemModal parrentItemModal , List parrentList ) {

		StringBuffer resultBuffer = new StringBuffer();
		if(parrentList !=  null && parrentList.size() > 0 )
		{
			for (Object item : parrentList) {
				ReflectHelper reflectHelper = new ReflectHelper(item);
				String id = oConvertUtils.getString(reflectHelper.getMethodValue(parrentItemModal.getIdField()));
				String text = oConvertUtils.getString(reflectHelper.getMethodValue(parrentItemModal.getTextField()));
				resultBuffer.append("[\"");
				resultBuffer.append(id);
				resultBuffer.append("\",\"");
				resultBuffer.append(toHexString(text) );
				resultBuffer.append("\"");
				resultBuffer.append("],");
				
			}
			resultBuffer.deleteCharAt(resultBuffer.length()-1);
		}
		
		return resultBuffer.toString() ;
	}
	
	
	private String parseChildNode( JsArrayItemModal parrentItemModal , JsArrayItemModal childItemModal , Object parrentItem )
	{
		
		StringBuffer resultBuffer = new StringBuffer();
		ReflectHelper reflectHelper = new ReflectHelper(parrentItem);
		String name = oConvertUtils.getString(reflectHelper.getMethodValue(parrentItemModal.getNameField()));
		resultBuffer.append(",\"");
		resultBuffer.append(name);
		resultBuffer.append("\"");
		
		
		Collection childrenList = (Collection) reflectHelper.getMethodValue(parrentItemModal.getChildField()) ;
		if(  childrenList != null && childrenList.size() > 0 )
		{
			resultBuffer.append(",[");
			for (Object item : childrenList) {
				ReflectHelper reflectHelper1 = new ReflectHelper(item);
				String childId = oConvertUtils.getString(reflectHelper1.getMethodValue(childItemModal.getIdField()));
				String childText = oConvertUtils.getString(reflectHelper1.getMethodValue(childItemModal.getTextField()));
				String childName = oConvertUtils.getString(reflectHelper1.getMethodValue(childItemModal.getNameField()));
				
				resultBuffer.append("[\"");
				resultBuffer.append(childId);
				resultBuffer.append("\",\"");
				resultBuffer.append(toHexString(childText) );
				resultBuffer.append("\",\"");
				resultBuffer.append(childName);
				resultBuffer.append("\"");
				
				resultBuffer.append("],");
			}
			resultBuffer.deleteCharAt(resultBuffer.length()-1);
			resultBuffer.append("]");
		}
		return resultBuffer.toString() ;
		
	}
	
	
	private String toHexString( String s ) 
	{ 
		StringBuffer strBuffer= new  StringBuffer();
		for (int i=0 ; i<s.length() ; i++) 
		{ 
			int ch = (int)s.charAt(i); 
			String hexString = "" ;
			if(ch > 0xff)
				hexString = "\\u" + Integer.toHexString(ch) ;
			else
				hexString =s.substring(i , i+1);
			strBuffer.append(hexString) ;
		}
		return strBuffer.toString() ;
	} 
	
	

}
