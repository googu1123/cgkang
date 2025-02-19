<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
	<title>한옥카페 주문 현황</title>
    <meta charset="utf-8" content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/favicon.ico" type="image/x-icon">
    <!-- bootstrap 3.0.2 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <!-- font Awesome -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.min.css">
    
    <!-- datepicker -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/zebra_datepicker_metallic.css">
    
    <!-- Theme style -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/layer.css">
    <!--[if lt IE 9]>
        <script src="/js/html5shiv.js"></script>
        <script src="/js/respond.min.js"></script>
    <![endif]-->
    <script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery-ui-1.10.3.min.js"></script>
	
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/zebra_datepicker.js"></script>
	<script src="${pageContext.request.contextPath}/js/common.js"></script>
	<script src="${pageContext.request.contextPath}/js/paging.js"></script>
</head>
<body class="skin-blue">
	<header class="header">
       <%@include file="../header.jsp" %>
    </header>
    <div class="wrapper row-offcanvas row-offcanvas-left">
    	<aside class="left-side sidebar-offcanvas">                
            <%@include file="../gnb_left.jsp" %>
            <!-- /.sidebar -->
        </aside>
        <aside class="right-side">                
            <section class="content-header">
                <h1>
                   한옥카페 주문 현황<br>
                    <small></small>
                </h1>
                <ol class="breadcrumb">
                     <%//@include file="./gnb/home.jsp" %>
                    <li class="active">한옥카페 주문 현황</li>
                    <!-- <li class="active">한옥카페 주문 현황</li> -->
                </ol>
            </section>                
            <!-- Main content -->
            <form method="post"  id="mainForm">
           	<input type="hidden" name="nowPage" id="nowPage" value="${nowPage }"/>
           	<input type="hidden" name="thisPage" id="thisPage" value="${nowPage}"/>
            <section class="content">
            <div class="search_form">
				<div class="dis_table">
					<dl class="dis_ftr">
						<dt>검색 조건</dt>
                   		<dd>	
                       		<div class="form-inline">
								<select class="form-control" id="search_type" name="search_type">
									<option value="id" <c:if test="${search.search_type  eq 'id' }">selected="selected"</c:if>>사번</option>
									<option value="orderNo" <c:if test="${search.search_type  eq 'orderNo' }">selected="selected"</c:if>>주문번호</option>
								</select>
						
                        	   	<div class="form-group">
									<input type="text" class="form-control" id="search_value" placeholder="검색어 입력" name="search_value" value="${search.search_value }">
								</div>
								<span class="txt_help"></span>
								<button type="button" class="btn btn-sm  btn-primary" id="btnSearch">검색</button>
							</div>
						</dd>
					</dl>
				</div> 
				<div class="dis_table">
					<dl class="dis_ftr">
						<dt>주문기간</dt>
						<dd>
							<div class="form-inline">
								<div class="form-group">
                               		<input type="text" class="form-control input_datebox" id="date_start" name="date_start" value="">
                           		</div>
                           		~
                           		<div class="form-group">
                               		<input type="text" class="form-control input_datebox" id="date_end" name="date_end" value="">
                           		</div>
                           		<%-- <input type="hidden"  id="date_start" name="start_date" value="${search.start_date }">
                           		<input type="hidden"  id="date_end" name="last_date" value="${search.last_date }"> --%>
                           		<%-- <input type="hidden" class="form-control" id="phone_num" placeholder="검색어 입력" name="search_num" value="${search.search_num }"> --%>
                       		</div>
                   		</dd>
               		</dl>
				</div>
                   
				</div>
				<div class="list_bot">
					<div class="text-center paging"><p class="txt_count text-orange" style="margin-right:5px;"><b>총 접수건수 : <span id='totalCount'></span>건&nbsp; / 총 주문잔수 : <span id='sumCount'></span>잔</b></p></div>
					<br>
					<div class="table-responsive mart10">
                       <table class="table tbl_list tbl_fixed">
                       	<colgroup>
							<col style="width:50px;">
							<col style="width:80px;">
							<col style="width:80px;">
							<col style="width:100px;">
							<col style="">
							<col style="width:100px;">
							<col style="width:150px;">
                           </colgroup>
                           <thead>
                               <tr>
                                   <th scope="col">순번</th>
                                   <th scope="col">이름</th>
                                   <th scope="col">사번</th>
                                   <th scope="col">부서</th>
                                   <th scope="col">주문번호</th>
                                   <th scope="col">주문 카운트</th>
                                   <th scope="col">주문시간</th>
                               </tr>
                           </thead>
                           <!-- ************* 1번영역 ***************** -->
                           <tbody id="boardList"></tbody>
                       </table>
                   </div><!-- /.table-responsive -->
                   <div class="row">
					<div class="col-xs-6"></div>
                          <div class="col-xs-6 text-right">
                              <div class="form-inline">
                              	<a href="#" class="btn btn-sm  btn-success" id="btnExcel"><i class="fa fa-file-excel-o"></i> XLS 다운로드</a>
                              </div>
                          </div>
					</div>
                   <br>
                   <div id="paging" class="text-center paging"></div>
			</div>	
			</section><!-- /.content -->
	        </form>
        </aside><!-- /.right-side -->
    </div><!-- ./wrapper -->
</body>
<script type="text/javascript">
 	
 	$(document).ready(function(){
 		
 		var now = new Date();
	    var firstData, lastDate;
	    
	  	first_day = new Date(now.getFullYear(),now.getMonth(),1);
	    last_day = new Date(now.getFullYear(),now.getMonth()+1,0);
	    
		console.log(dateFormat(first_day));
	    console.log(dateFormat(last_day));
			    
	    $("#date_start").val(dateFormat(first_day));
	    $("#date_end").val(dateFormat(last_day));
	    
	    boardMain.init();
	    
		//DatePicker
	    $("#date_start").Zebra_DatePicker({
	        first_day_of_week: 0,
	        direction: false,
	        pair: $("#date_end")
	    });
	    $("#date_end").Zebra_DatePicker({
	        first_day_of_week: 0,
	        direction: true
	    });
	    
	    $("input").keydown(function(key) {
        	if (key.keyCode == 13) {
        		boardMain.getOrderList();
        	}
        });
	    
	});
 	
 	function dateFormat(date) {
        let month = date.getMonth() + 1;
        let day = date.getDate();
        let hour = date.getHours();
        let minute = date.getMinutes();
        let second = date.getSeconds();

        month = month >= 10 ? month : '0' + month;
        day = day >= 10 ? day : '0' + day;
        hour = hour >= 10 ? hour : '0' + hour;
        minute = minute >= 10 ? minute : '0' + minute;
        second = second >= 10 ? second : '0' + second;

        return date.getFullYear() + '-' + month + '-' + day ;
        //return date.getFullYear() + '-' + month + '-' + day + ' ' + hour + ':' + minute + ':' + second;
	}
 	
 	var boardMain = {
		init : function(){ 
			var _this = this;
			_this.btnEvent();
			_this.getOrderList(); 
		}
 		,btnEvent : function(){
			/* 상세보기 이동 */ 
    		$(document).on('click','.seq_tr',function(){
    			console.info($(this).attr('id'));
    		});
			
		},getOrderList :function(no){
			
			var pageNo = (no || 1); 
    		var search_type = $("select[name=search_type]").val();
    		var search_value =$("#search_value").val() ;
    		var date_start =$("#date_start").val() ;
    		var date_end =$("#date_end").val() ;

    		if (pageNo>1){
    			skip = (pageNo-1)*LIMIT;
    		}
    		
    		$.ajax({ 
    			
    			type:"POST",
    			url: 'getOrderList.do', 
    			dataType: "json", 
    			data :JSON.stringify({
    				pageSize: LIMIT,
    				pageBlock: PAGEGROUPCNT,
    				pageNo : pageNo,
    				search_type : search_type,
    				search_value : search_value,
    				date_start: date_start,
    				date_end : date_end
    			}),
    			contentType:"application/json; charset=utf-8", 
    			cache : false, 
    			success : function(resData){
    				
    				//console.info(resData);
    				
    				var item = JSON.parse(resData.orderList);
					var selectHtml=[]; 
					var len = item.length;
					
					var totalCount = resData.totalCount;
					var sumCount = resData.orderSumCnt;
    				var page_regList = Paging(totalCount, LIMIT, PAGEGROUPCNT ,pageNo, "orderList"); //공통 페이징 처리 함수 호출
    				
    				if(len > 0){
    					for (var i = 0; i < item.length; i++){
    						
    						var idx = totalCount -((pageNo-1)*LIMIT+i);
    						//var dateStr = item[i].regdate;
    						//console.info("dateStr >>" + dateStr);
    						var idt = new Date();
    						
        					selectHtml.push('<tr style="height:40px;" class="seq_tr" id="'+item[i].seq+'">'); 
	    						selectHtml.push('<td>'+idx+'</td>');
	    						selectHtml.push('<td>'+item[i].name+'</td>');
	    						selectHtml.push('<td>'+item[i].id+'</td>');
	    						selectHtml.push('<td>'+item[i].dept+'</td>');
	    						selectHtml.push('<td class="boardTitle">'+item[i].orderNo+'</td>');
	    						selectHtml.push('<td>'+item[i].orderCnt+'</td>');
	    						selectHtml.push('<td>'+item[i].regdate+'</td>');
    						selectHtml.push('</tr>');
    						
        				}	
    				}else{
    					selectHtml.push('<tr height=60px>'); 
						selectHtml.push('<td colspan="7">조회된 결과가 없습니다.</td>'); 
						selectHtml.push('</tr>');	
    				}
    				
    				
    				$("#boardList").empty().html(selectHtml.join('')); 
    				
    				//총카운트 표기
    				$("#totalCount").empty().html(addComma(totalCount));
    				$("#sumCount").empty().html(addComma(sumCount));
    				
    				//페이징 그리기 
    				$("#paging").empty().html(page_regList);
    				  				
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
 	
 	var goPaging_orderList = function(cPage){
    	boardMain.getOrderList(cPage); 
    };
 	
    $("#btnSearch").on("click",function(){
    	$("#nowPage").val("1");
    	const btnElement = document.getElementById("btnSearch");
		btnElement.innerText = "검색 중 ...";
    	/* 
    	if($("#search_value").val() == ""){
    		alert("검색어를 입력해 주세요.");
    		return;
    	}
    	 */
    	 
    	boardMain.getOrderList();
    	btnElement.innerText = "검색";
    	//$("#mainForm").attr({"action":"msgHistoryByPhone.tsis"}).submit();
    	
    });
    
    $("#btnExcel").on("click",function(){
    	
    	$("#mainForm").attr({"action":"orderListExcel.do"}).submit();
		//location.href='orderListExcel.do';    
    });
    
    function addComma(value){
        value = value.replace(/\B(?=(\d{3})+(?!\d))/g, ",");
        return value; 
    }
 </script>
</html>
