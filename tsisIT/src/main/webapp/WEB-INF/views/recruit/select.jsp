<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style_recruit.css">
    <script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery-ui-1.10.3.min.js"></script>
	<script>
		$(document).ready(function(){
			boardMain.init();
			
			/* alert('면접 일정 등록이 마감 되었습니다. ');
			history.back() */
		});

		var boardMain = {
		
			init : function(){ 
				var _this = this;
				_this.getRegisterList();
				_this.selectEvent();
				_this.btnEvent();
			}
			,selectEvent : function(){
				$("#selectBox").change(function(){
					boardMain.getRegisterList();
				});
			}
	 		,btnEvent : function(){
				 
	    		$(document).on('click','.book',function(){
	    			console.info($(this).attr('id'));
	    			
	    			//alert('면접 일정 등록이 마감 되었습니다. ');
	    			//return false;
	    			
	    			var form = document.createElement("form");
   	    		    
   	    		    form.action = "tsis_recruit_form.do";
   	    		    form.method = "POST";

   	    		    var element1 = document.createElement("input"); 
   	    		    
   	    		    element1.name="seq";
   	    		    element1.type="text";
   	    		    element1.value=$(this).attr('id');
   	    		    
   	    		    form.appendChild(element1);  
   	    		    
   	    		    document.body.appendChild(form);
   	    		 	form.submit();	
	    		    form.remove();
	    			
	    		});
			},getRegisterList :function(no){
				
	    		//console.log('post');
	    		$.ajax({ 
	    			type:"POST",
	    			url: 'getInterviewList.do',
	    			//url: 'getInterviewListGubun.do',
	    			dataType: "json", 
	    			data :JSON.stringify({
	    				//interview_day: '2021-02-04'
	    				interview_day: $("#selectBox option:selected").val()
	    			}),
	    			contentType:"application/json; charset=utf-8", 
	    			cache : false, 
	    			success : function(resData){
	    				
	    				var item = JSON.parse(resData.reglist);
						var selectHtml=[]; 
						var len = item.length;
						            
	    				if(len > 0){
	    					for (var i = 0; i < item.length; i++){
	    						var seq = item[i].seq;
	    						var day = item[i].interview_day;
	    						var timezone = item[i].timezone;
	    						
	    						var cnt = item[i].cnt;
	    						
	    						//var cntA = item[i].cntA;
	    						//var cntB = item[i].cntB;
	    						
	    						//console.log(timezone + " >> cntA :"+ cntA + " , cntB :"+ cntB);
	    						console.log(timezone + " >> seq :"+ seq);
	    						console.log(timezone + " >> cnt :"+ cnt);
	    						var limitCnt = 3;
	    						
	    						if (seq=='22'){
	    							limitCnt = 4;
	    						}
	    						
	    						//if(cntA < 3 || cntB < 3)
	    						if(cnt < limitCnt)
	    						{
	    							selectHtml.push('<div style="cursor: pointer" class="book" id="'+item[i].seq+'">'+timezone+' ~ </div>');
	    							
	    							/* selectHtml.push('<div class="book" style="cursor: pointer" id="'+item[i].seq+'">');
	    							selectHtml.push('	<div class="time">'+timezone+' ~</div>');
	    							selectHtml.push('	<div>');
	    							selectHtml.push('		<div><span class="num">'+cnt+'/3</span></div>');
	    							selectHtml.push('	</div>');
	    							selectHtml.push('</div>'); */ 
	    							
	    							
	    						}else{
	    							selectHtml.push('<div class="full">'+timezone+' ~ </div>');
	    							
	    							
	    							/* selectHtml.push('<div class="full">');
	    							selectHtml.push('	<div class="time">'+timezone+' ~</div>');
	    							selectHtml.push('	<div>');
	    							selectHtml.push('		<div><span class="num">'+cnt+'/3</span></div>');
	    							selectHtml.push('	</div>');
									selectHtml.push('</div>'); */ 
	    						}
	        				}	
	    				}else{
	    						
	    				}
	    				//console.log("selectHtml:"+selectHtml);
	    				$("#timeTable").empty().html(selectHtml.join(''));
	    			}, 
	    				
	    			/* ajax error 확인방법 */ 
	   				error : function(request,status,error){ 
	   					var jsonObj1 = JSON.stringify(request);
	   					var jsonData1 = JSON.parse(jsonObj1);
	   					//alert(jsonData1.responseText);
	   					alert('시스템 오류 발생 하였습니다. \n'+jsonData1.responseText);
	   					
	   					/* 
	   					console.log("1: "+ JSON.stringify(request));
	   					console.log("2: "+ request); 
	   					console.log("3: "+ status); 
	   					console.log("4: "+ error);
	   					*/
	   				} 
	    		});
			}	
		};

</script>
    <title>면접 일정 선택</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/favicon.ico" type="image/x-icon">
</head>
<body>
    <div class="wrap">
        <div class="header">
            <img src="img/logo-tsis.svg">
            <h1>태광그룹 (주)티시스 신입사원 공개채용</h1>
            <h2>면접 일정 선택</h2>
        </div>
        <div class="content">
            <select id="selectBox">
                <option value="2021-12-21">2021년 12월 21일 (화)</option>
                <option value="2021-12-22">2021년 12월 22일 (수)</option>
            </select>
            <div class="desc">
                <span class="ic-full"></span>
                <span>마감</span>
                <span class="ic-book"></span>
                <span>선택가능</span>
            </div>
            <div class="wrap-table" id ="timeTable">
            	
                <!-- 
                <div class="book">09:00</div>
                <div class="book">09:35</div>
                <div class="full">10:10</div>
                <div class="book">10:45</div>
                <div class="book">11:20</div>
                <div class="book">13:00</div>
                <div class="full">13:35</div>
                <div class="full">14:15</div>
                <div class="book">14:45</div>
                <div class="book">15:20</div>
                <div class="book">16:30</div>
                <div class="book">17:05</div>
                 -->
            </div>
        </div>
    </div>
</body>
</html>