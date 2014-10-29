package xjnu.edu.weixin.mall.service.impl.mall;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xjnu.edu.weixin.mall.entity.mall.TbMallCart;
import xjnu.edu.weixin.mall.entity.mall.TbMallProductEntity;
import xjnu.edu.weixin.mall.entity.mall.TbMallWorkTimeEntity;
import xjnu.edu.weixin.mall.service.mall.CartServiceI;
import xjnu.edu.weixin.mall.service.mall.OrderServiceI;
import xjnu.edu.weixin.mall.service.mall.TbBuilddingCatalogServiceI;
import xjnu.edu.weixin.mall.service.mall.TimeServiceI;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("timeService")
@Transactional
public class TimeServiceImpl extends CommonServiceImpl implements TimeServiceI {

	@Override
	public String checkTime(String shopId , TbMallWorkTimeEntity selectWorkTime ) {
		String validateString = ""; 
		//TbMallWorkTimeEntity selectWorkTime = this.getEntity(TbMallWorkTimeEntity.class, timeId) ;
		
		Calendar cal = Calendar.getInstance() ;
		if( selectWorkTime.getTodayOrTomorow().equals("tomorow") && cal.get(Calendar.HOUR_OF_DAY) >= 5 )
		{		
			cal.add(Calendar.DAY_OF_MONTH, 1) ;
		}
		if(! checkWorkDayOrDate( cal.get(Calendar.DAY_OF_WEEK) ,selectWorkTime.getNotWorkDay() ) )
		{
			validateString = selectWorkTime.getNotWorkDayExplain() ;
		}
		else if(! checkWorkDayOrDate( cal.get(Calendar.DAY_OF_MONTH) ,selectWorkTime.getNotWorkDate() ) )
		{
			validateString = selectWorkTime.getNotWorkDateExplain() ;
		}
		else if( selectWorkTime.getTodayOrTomorow().equals("today") && (!checkOutofDeadTime(selectWorkTime )) )
		{
			validateString = "已过该时间段的送货截止时间，请重新选择送货时间！";
		}
		return validateString;
	}
	
	
	@Override
	public Calendar getRequestTime(TbMallWorkTimeEntity selectWorkTime ) {
		
		Calendar requestCalender = getDate( selectWorkTime) ;
		Calendar cal = getTimeFromString(selectWorkTime.getStartTime() ) ;
		requestCalender.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY)) ;
		requestCalender.set(Calendar.MINUTE, cal.get(Calendar.MINUTE)) ;
		return requestCalender;
	}
	
	@Override
	public Calendar getEndTime(TbMallWorkTimeEntity selectWorkTime ) {
		
		Calendar requestCalender = getDate( selectWorkTime) ;
		Calendar cal = getTimeFromString(selectWorkTime.getEndTime() ) ;
		requestCalender.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY)) ;
		requestCalender.set(Calendar.MINUTE, cal.get(Calendar.MINUTE)) ;
		return requestCalender;
	}
	
	private Calendar getDate(TbMallWorkTimeEntity selectWorkTime)
	{
		Calendar cal = Calendar.getInstance() ;
		if( selectWorkTime.getTodayOrTomorow().equals("tomorow") && cal.get(Calendar.HOUR_OF_DAY) >= 5 )
		{		
			cal.add(Calendar.DAY_OF_MONTH, 1) ;
		}
		return cal ;
		
	}
	
	private Calendar getTimeFromString(String timeString )
    {
		
		Calendar requestCal = Calendar.getInstance() ;
		String[] lastSubmitTimes = timeString.split(":") ;
    	int hour = Integer.parseInt(lastSubmitTimes[0]) ;
    	int minute = Integer.parseInt(lastSubmitTimes[1]) ;
    	requestCal.set(Calendar.HOUR_OF_DAY , hour);
    	requestCal.set(Calendar.MINUTE , minute);
    	return requestCal ;
    }
	
	
	private boolean checkWorkDayOrDate( int currentDayOrDate , String notWorkDayOrDateString)
    {
    	
    	boolean returnFlag = true ;
    	if(notWorkDayOrDateString == null || notWorkDayOrDateString.endsWith(""))
    		return true ;
    	String[] notWorkDayOrDates = notWorkDayOrDateString.split(",");
    	for(String dayOrDate : notWorkDayOrDates)
    	{
    		int nDayOrDate = Integer.parseInt(dayOrDate) ;
    		if( currentDayOrDate == nDayOrDate)
    		{
    			returnFlag = false ;
    			break;
    		}
    	}
    	return returnFlag ;
    }
	
	private boolean checkOutofDeadTime(TbMallWorkTimeEntity workTime )
    {
		
		Calendar requestCal = Calendar.getInstance() ;
		Calendar now = Calendar.getInstance() ;
    	String lastSubmitTimeString = workTime.getLastSubmitTime() ;
    	String[] lastSubmitTimes = lastSubmitTimeString.split(":") ;
    	int hour = Integer.parseInt(lastSubmitTimes[0]) ;
    	int minute = Integer.parseInt(lastSubmitTimes[1]) ;
    	requestCal.set(Calendar.HOUR_OF_DAY , hour);
    	requestCal.set(Calendar.MINUTE , minute);
    	if( requestCal.before(now))
    	{
    		return false ;
    	}
    	
    	return true ;
    }
	
	
	private boolean checkDateAndDay(Calendar now , TbMallWorkTimeEntity workTime)
	{
		return ! checkWorkDayOrDate( now.get(Calendar.DAY_OF_WEEK) , workTime.getNotWorkDay() )  && 
		! checkWorkDayOrDate( now.get(Calendar.DAY_OF_MONTH) ,workTime.getNotWorkDate() );
	}
	
	@Override
	public List<TbMallWorkTimeEntity> getTbMallWorkTimeList( String shopId ) {
		// TODO Auto-generated method stub
		Calendar now = Calendar.getInstance() ;
		List<TbMallWorkTimeEntity> workTimeList = this.findByProperty(TbMallWorkTimeEntity.class, "shopId", shopId);
		for( TbMallWorkTimeEntity workTime : workTimeList )
		{
			if ( now.get(Calendar.HOUR_OF_DAY) < 5)
			{
				workTime.setTodayOrTomorow("tomorow");
			}
			else if( checkOutofDeadTime( workTime ) )
			{
				workTime.setTodayOrTomorow("today");
			}
			else 
			{
				workTime.setTodayOrTomorow("tomorow");
			}
		}
		return workTimeList;
	}

	
	
	
	
}