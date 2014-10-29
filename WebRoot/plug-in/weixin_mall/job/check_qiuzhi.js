function checkUserName(){
    var username    = $.trim($("#name").val());
    var regexChineseName = /^[\u4e00-\u9fa5]{2,}$/;
    if(username == null || username == ""){
        check_username = false;
    }else if(username.length < 2 || username.length > 5){
        check_username = false;
    }else if(!/^[,A-Z_a-z\u0391-\uFFE5\.\s\,]*$/.test(username)){
        check_username = false;
    }else if(!regexChineseName.test(username)){
        check_username = false;
    }else{
        check_username = true;
    }
    return check_username;
}
function checkMobile(){
    var mobile = $.trim($("#phoneNumber").val());
    var reg_mobile         = /^1[34578]\d{9}$/;
    if(mobile == null || mobile == ''){
        check_mobile = false;
    }else if(!reg_mobile.test(mobile)){
        check_mobile = false;
    }else{
        check_mobile = true;
    }
    return check_mobile;
}
function checkBlockWord(content){
    var blockWord = '';
    $.ajax({
        type:'GET',
        dataType:'json',
        url:'http://m.wealink.com/ajax/blockwords.php',
        data:{'keywords':content},
        async: false,
        cache: false,
        success:function(resp){
            if(resp.flag=='no'){
                blockWord = resp.msg;
            }
        }
    });

    return $.trim(blockWord);
}
function checkInformation(){
    var user_name = $.trim($('#name').val());
    if(user_name == ''){
        WealinkMobile.Popup.alert('请填写姓名','warn',2);
        return false;
    }
    if(!checkUserName()){
        WealinkMobile.Popup.alert('请输入正确的中文姓名','warn',2);
        return false;   
    }
    var block_word = checkBlockWord(user_name);
    if(block_word!=''){
        WealinkMobile.Popup.alert('姓名中不能包含敏感词“'+block_word+'”','warn',2);
        return false;   
    }
    
    var sex = $.trim($('#gender').val());
    if(sex == '' || (sex!='M' && sex!='F')){
        WealinkMobile.Popup.alert('请选择性别','warn',2);
        return false;
    }
    
    var birth = $.trim($('#brithday').val());
    var birth_arr = birth.split('-');
    if(!birth || birth_arr.length < 3){
		WealinkMobile.Popup.alert('请选择出生年月日','warn',2);
        return false;
    }

    var mobile = $('#phoneNumber').val();
    if(mobile == ''){
        WealinkMobile.Popup.alert('请填写手机','warn',2);
        return false;
    }
    if(!checkMobile()){
        WealinkMobile.Popup.alert('请填写正确手机','warn',2);
        return false;       
    }
    
   /* var work_years_new = $.trim($('#work_years_new').val());
    if(!work_years_new || work_years_new == ''){
        WealinkMobile.Popup.alert('请选择参加工作年份','warn',2);
        return false;
    }

    var marriage = $.trim($("#marriage").val());
    if(!marriage || (marriage!='N' && marriage!='Y' && marriage!='S')){
        WealinkMobile.Popup.alert('请选择婚姻状况','warn',2);
        return false;
    }*/
    
   var region_id = $.trim($('#schoolId').val());
    if(region_id == ''){
        WealinkMobile.Popup.alert('请填写学校','warn',2);
        return false;
    }
    var self_comment = $.trim($('#selfDiscription').val());
    if(self_comment == ''){
        WealinkMobile.Popup.alert('请填写自我评价内容','warn',2);
        return false;
    }
    if ( getTextLength(self_comment) > 1000 ) {
    	WealinkMobile.Popup.alert('自我评价不能大于500字','warn',2);
        return false;
    }
    return true;
}
var _bd_root_url = 'http://m.wealink.com/';
function save_information(){
	$('#id_save_hrinformation').attr('disabled', true);
    var check_flag = checkInformation();
    if(!check_flag){
        $('#id_save_hrinformation').attr('disabled', false);
        return false;
    }
    var _url = _bd_root_url+'qiuzhi/basic/';
    var dataobj = new Object();
    var name = $.trim($('#name').val());
    
    dataobj['name'] = name ;
    var gender;
//    alert(gender);
   if($.trim($('#gender').val())=='M'){
	   gender='男';
   }else{
	   gender = '女';
   }

    dataobj['gender'] = gender; 
    var expirePosition = $.trim($('#expirePosition').val());
    dataobj['expirePosition'] = expirePosition;
    var email = $('#email').val();
    dataobj['email'] = email;   
    var area = $.trim($('#area').val());
    dataobj['area'] = area;               
    var qq = $.trim($("#qq").val());
    dataobj['qq'] = qq;
    
    var brithday = $.trim($("#brithday").val());
    dataobj['brithday'] = brithday;
    var phoneNumber = $.trim($("#phoneNumber").val());
    dataobj['phoneNumber'] = phoneNumber;
    var selfDiscription = $.trim($("#selfDiscription").val());
    dataobj['selfDiscription'] = selfDiscription;
    
    var schoolId = $.trim($('#schoolId').val());
    dataobj['schoolId'] = schoolId;
    
   /* var self_comment = $.trim($('#self_comment').val());
    dataobj['self_comment'] = self_comment;*/
//    dataobj['submitbisic'] = 1;

    $.ajax({
        type: "POST",
        url: 'http://115.28.241.74/jeewx/resumeController.do?saveResume',
        data: dataobj,
        dataType: 'json',
        async: false,
        success: function(data){
            if(data.flag == 'yes') {
                WealinkMobile.Popup.alert('保存成功','correct',2);
                window.location.href = _bd_root_url+'dangan/editqiuzhi/';
            } else {
            	$('#id_save_hrinformation').attr('disabled', false);
                WealinkMobile.Popup.alert(data.msg,'warn',2);
                return false;
            }
            
        }
    
    });
}

function check_intention(){
    var intention = $.trim($("#intention").val());
    if(!intention || intention.length<1){
        WealinkMobile.Popup.alert('请选择求职状态','warn',2);
        return false;
    }

    //暂时不想找工作时，期望行业，期望职能，期望月薪，期望地点为非必填字段
    if(intention==3){
        return true;
    }

    var industry = $.trim($("#industry").val());
    if(!industry || industry.length<1){
        WealinkMobile.Popup.alert('请选择期望行业','warn',2);
        return false;
    }

    var category = $.trim($("#category").val());
    if(!category || category.length<1){
        WealinkMobile.Popup.alert('请选择期望职能','warn',2);
        return false;
    }

    var month_salary = $.trim($("#month_salary").val());
    if(!month_salary || month_salary.length<1 || month_salary==0){
        WealinkMobile.Popup.alert('请选择期望月薪','warn',2);
        return false;
    }

    var hopelocation = $.trim($("#location").val());
    if(!hopelocation || hopelocation.length<1){
        WealinkMobile.Popup.alert('请选择期望地点','warn',2);
        return false;
    }
    return true;
}
function save_intention(){
    $('#id_save_intention').attr('disabled', true);
    var check_flag = check_intention();
    if(!check_flag){
        $('#id_save_intention').attr('disabled', false);
        return false;
    }

    var _url = _bd_root_url + 'qiuzhi/intention/';
    var _data = {
        'intention': $.trim($("#intention").val()),
        'industry': $.trim($("#industry").val()),
        'category': $.trim($("#category").val()),
        'location': $.trim($("#location").val()),
        'month_salary': $.trim($("#month_salary").val()),
        'readytime':$.trim($("#readytime").val()),
        'submitintention': 1
    };

    $.ajax({
        type: "POST",
        url: _url,
        data: _data,
        dataType: 'json',
        async: false,
        success: function(data){
            if(data.code==0){
                WealinkMobile.Popup.alert('保存成功','correct',2);
                window.location.href = _bd_root_url+'dangan/editqiuzhi/';
            }else{
            	$('#id_save_intention').attr('disabled', false);
                WealinkMobile.Popup.alert(data.msg,'warn',2);
            }
        }
    });
}

function check_edu(){
    var school_name = $.trim($("#school_name").val());
    if(!school_name || school_name.length<1){
        WealinkMobile.Popup.alert('请输入学校名称','warn',2);
        return false;
    }
    
    var start_date = $.trim($("#start_date").val());
    var end_date = $.trim($("#end_date").val());
    if(!start_date || start_date=='0000-00'){
        WealinkMobile.Popup.alert('请选择入学时间','warn',2);
        return false;
    }
    if(!end_date){
        WealinkMobile.Popup.alert('请选择毕业时间','warn',2);
        return false;
    }
	
    var d1 = new Date();
    var d2 = new Date();
    var start_date_arr = start_date.split('-');
    d1.setFullYear(start_date_arr[0]);
    d1.setMonth(start_date_arr[1]-1);
    d1.setDate(1);

    if(end_date != '0000-00'){
    	var end_date_arr = end_date.split('-');
        d2.setFullYear(end_date_arr[0]);
        d2.setMonth(end_date_arr[1]-1);
        d2.setDate(1);
	    if(d1.getTime()>d2.getTime()){
	        WealinkMobile.Popup.alert('教育经历结束时间不能早于开始时间','warn',2);
	        return false;
	    }
    }
    
    var major_name = $.trim($("#major_name").val());
    if(!major_name || major_name.length<1){
        WealinkMobile.Popup.alert('请填写专业','warn',2);
        return false;
    }
    
    var degree = $.trim($("#degree").val());
    if(!degree || degree.length<1 || degree=='请选择'){
        WealinkMobile.Popup.alert('请选择学历','warn',2);
        return false;
    }
    return true;
}

//201311 教育经历--保存执行
function save_edu(){
	if(check_edu()){
	    var _url = _bd_root_url + 'qiuzhi/doedu/';
	    var school_name = $.trim($("#school_name").val());
	    var major_name = $.trim($("#major_name").val());
	    var edu_id = $.trim($("#edu_id").val());
	    var start_date = $.trim($("#start_date").val());
	    var end_date = $.trim($("#end_date").val());
	    var _data = {
	        'edu_id': edu_id,
	        'school_name': school_name,
	        'degree': $.trim($("#degree").val()),
	        'major_name': major_name,
	        'start_date': $.trim(start_date),
	        'end_date': $.trim(end_date),
	        'submitedu': 1
	    };
	    $.ajax({
	        type: "POST",
	        url: _url,
	        data: _data,
	        dataType: 'json',
	        async: false,
	        success: function(data){
	            if(data.code==0){
	                WealinkMobile.Popup.alert('保存成功','correct',2);
	                window.location.href = _bd_root_url+'qiuzhi/education/';
	            }else{
	                WealinkMobile.Popup.alert(data.msg,'warn',2);
	            }
	        }
	    });
	}
}

function check_work(work_id, new_id){
    var start_date = $.trim($("#start_date").val());
    var end_date = $.trim($("#end_date").val());
    if(!start_date || start_date=='0000-00'){
        WealinkMobile.Popup.alert('请选择工作时间','warn',2);
        return false;
    }
    if(!end_date){
        WealinkMobile.Popup.alert('请选择工作时间','warn',2);
        return false;
    }
	
    var d1 = new Date();
    var d2 = new Date();
    var start_date_arr = start_date.split('-');
    d1.setFullYear(start_date_arr[0]);
    d1.setMonth(start_date_arr[1]-1);
    d1.setDate(1);

    if(end_date != '0000-00'){
    	var end_date_arr = end_date.split('-');
        d2.setFullYear(end_date_arr[0]);
        d2.setMonth(end_date_arr[1]-1);
        d2.setDate(1);
	    if(d1.getTime()>d2.getTime()){
	        WealinkMobile.Popup.alert('工作经历结束时间不能早于开始时间','warn',2);
	        return false;
	    }
    }

    var company_name = $.trim($("#company_name").val());
    if(!company_name || company_name.length<1){
        WealinkMobile.Popup.alert('请填写所在公司','warn',2);
        return false;
    }

    var industry = $.trim($("#industry").val());
    if(!industry || industry.length<1){
        WealinkMobile.Popup.alert('请选择公司行业','warn',2);
        return false;
    }

    var category = $.trim($("#category").val());
    if(!category){
        WealinkMobile.Popup.alert('请重新选择担任职务','warn',2);
        return false;
    }

    var description = $.trim($("#description").val());
    if(!description || description.length<1){
        WealinkMobile.Popup.alert('请填写工作描述','warn',2);
        return false;
    }
    
    if(description && getTextLength( description ) >1000){
        WealinkMobile.Popup.alert('工作描述不能大于500字','warn',2);
        return false;
    }

    return true;
}

//201311 工作经历--保存执行
function save_work(){
	if(check_work()){
	    var _url = _bd_root_url + 'qiuzhi/dowork/';
	    var start_date = $.trim($("#start_date").val());
	    var end_date = $.trim($("#end_date").val());
	    var company_name = $.trim($("#company_name").val());
	    var industry = $.trim($("#industry").val());
	    var category = $.trim($("#category").val());
	    var description = $.trim($("#description").val());
	    var work_id = $.trim($("#work_id").val());
	    var _data = {
	        'work_id':work_id,
	        'start_date':start_date,
	        'end_date':end_date,
	        'company_name':company_name,
	        'industry':industry,
	        'category':category,
	        'description':description,
	        'submitwork':1,
	    };
	
	    $.ajax({
	        type: "POST",
	        url: _url,
	        data: _data,
	        dataType: 'json',
	        async: false,
	        success: function(data){
	            if(data.code==0){
	                WealinkMobile.Popup.alert('保存成功','correct',2);
	                window.location.href = _bd_root_url+'qiuzhi/work/';
	            }else{
	                WealinkMobile.Popup.alert(data.msg,'warn',2);
	            }
	        }
	    });
	}
}