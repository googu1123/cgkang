<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
	<title>임직원 목록</title>
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
  	
 	<script type="text/javascript">
 	
 	$(document).ready(function(){
	    boardMain.init();
	});

 	var boardMain = {
		init : function(){ 
			var _this = this;
			_this.btnEvent();
			_this.getTsisList(); 
		}
 		,btnEvent : function(){
			/* 상세보기 이동 */ 
    		$(document).on('click','.vod_tr',function(){
    			console.info($(this).attr('id'));
    		});
			
		},getTsisList :function(no){
			
			var pageNo = (no || 1); 
    		
    		if (pageNo>1){
    			skip = (pageNo-1)*LIMIT;
    		}
    		
    		$.ajax({ 
    			type:"POST",
    			url: 'getTsisList.do', 
    			dataType: "json", 
    			data :JSON.stringify({
    				pageSize: LIMIT,
    				pageBlock: PAGEGROUPCNT,
    				pageNo : pageNo
    			}),
    			contentType:"application/json; charset=utf-8", 
    			cache : false, 
    			success : function(resData){
    				
    				var item = JSON.parse(resData.tsislist);
					var selectHtml=[]; 
					var len = item.length;
					
					var totalCount = resData.totalCount;
    				var page_tsisList = Paging(totalCount, LIMIT, PAGEGROUPCNT ,pageNo, "tsisList"); //공통 페이징 처리 함수 호출
    				
    				if(len > 0){
    					
    					for (var i = 0; i < item.length; i++){
    						
    						var idx = totalCount -((pageNo-1)*LIMIT+i);
    						var dateStr = item[i].regdate;
    						
    						//console.info("dateStr >>" + dateStr);
    						var idt = new Date();
    						${paginationInfo.totalRecordCount - ((paginationInfo.currentPageNo-1) * paginationInfo.recordCountPerPage + status.index) }

        					selectHtml.push('<tr style="height:40px;" class="seq_tr" id="'+item[i].seq+'">'); 
	    						//selectHtml.push('<td>'+item[i].seq+'</td>');
	    						selectHtml.push('<td>'+idx+'</td>');
	    						selectHtml.push('<td>'+item[i].id+'</td>');
	    						selectHtml.push('<td>'+item[i].name+'</td>');
	    						selectHtml.push('<td>'+item[i].dept+'</td>');
	    						selectHtml.push('<td id="'+item[i].team+'">'+item[i].team+'</td>');
	    						selectHtml.push('<td>'+item[i].part+'</td>');
	    						selectHtml.push('<td>'+item[i].jobgroup+'</td>');
	    						selectHtml.push('<td>'+item[i].regdate+'</td>');
    						selectHtml.push('</tr>');
    						
        				}	
    					
    				}else{
    					selectHtml.push('<tr height=60px>'); 
						selectHtml.push('<td colspan="8">조회된 결과가 없습니다.</td>'); 
						selectHtml.push('</tr>');	
    				}
    				
    				
    				$("#vodList").empty().html(selectHtml.join('')); 
    				
    				//총카운트 표기
    				$("#totalCount").empty().html(totalCount);
    				
    				//페이징 그리기 
    				$("#paging").empty().html(page_tsisList);
    				  				
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
 	
 	var goPaging_tsisList = function(cPage){
    	boardMain.getTsisList(cPage); 
    };
 
 </script>
</head>
<body class="skin-blue">
	<header class="header">
       <%@include file="header.jsp" %>
    </header>
    <div class="wrapper row-offcanvas row-offcanvas-left">
    	<aside class="left-side sidebar-offcanvas">                
            <%@include file="./gnb_left.jsp" %>
            <!-- /.sidebar -->
        </aside>
        <aside class="right-side">                
            <section class="content-header">
                <h1>
                   임직원 목록<br>
                    <small></small>
                </h1>
                <ol class="breadcrumb">
                     <%//@include file="./gnb/home.jsp" %>
                    <li class="active">티시스 임직원 관리</li>
                    <li class="active">임직원 목록</li>
                </ol>
            </section>                
            <!-- Main content -->
            <form method="post"  id="mainForm">
           	<input type="hidden" name="nowPage" id="nowPage" value="${nowPage }"/>
           	<input type="hidden" name="thisPage" id="thisPage" value="${nowPage}"/>
            <section class="content">
                <div class="list_bot">
					<div class="row">
						<div class="col-xs-6"></div>
                           <div class="col-xs-6 text-right">
                               <div class="form-inline">
                               	<!-- <button type="button" id="vod_refresh" class="btn btn-info btnDetail btn-sm">새로고침</button> -->
                               </div>
                           </div>
					</div>
                    <div class="table-responsive mart10">
                        <table class="table tbl_list tbl_fixed">
                        	<colgroup>
								<col style="width:50px;">
								<col style="width:100px;">
								<col style="width:100px;">
								<col style="width:100px;">
								<col style="width:150px;">
								<col style="width:150px;">
								<col style="width:100px;">
								<col style="width:100px;">
                            </colgroup>
                            <thead>
                                <tr>
                                    <th scope="col">순번</th>
                                    <th scope="col">사번</th>
                                    <th scope="col">성명</th>
                                    <th scope="col">사업부</th>
                                    <th scope="col">팀</th>
                                    <th scope="col">파트</th>
                                    <th scope="col">직군</th>
                                    <th scope="col">등록일</th>
                                </tr>
                            </thead>
                            <!-- ************* 1번영역 ***************** -->
                            <tbody id="vodList"></tbody>
                        </table>
                    </div><!-- /.table-responsive -->
                    <div class="text-center paging"><p class="txt_count text-orange" style="margin-right:5px;"><b>총 : <span id='totalCount'></span> 명</b></p></div>
                    <br>
                    <div id="paging" class="text-center paging"></div>
				</div>	
			</section><!-- /.content -->
	        </form>
        </aside><!-- /.right-side -->
    </div><!-- ./wrapper -->
</body>
</html>
