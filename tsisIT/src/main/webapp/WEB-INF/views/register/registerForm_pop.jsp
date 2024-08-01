<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="kor">

<head>
  <meta charset="UTF-8">
  <meta name="viewport"
    content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style_delivery.css">
  <title>명절선물 수령 주소 입력</title>
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/favicon.ico" type="image/x-icon">
  <script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
  <script src="${pageContext.request.contextPath}/js/jquery-ui-1.10.3.min.js"></script>
  <script type="text/javascript">
  	$(document).on('click','#btn_search',function()
	{
		if($('#s_id').val()==""){
			alert('조회 할 사번을 입력해주세요.');	
			$('#s_id').focus();
			return false;
		}
		
		var obj = new Object();
		
		obj.s_id = $('#s_id').val();
		obj.gubun = $('#gubun').val();
		
		var json_data = JSON.stringify(obj);
		console.log("json_data : " + json_data);
		
		var request = $.ajax({
			url: "tsis_search.do",
			type: 'POST',
			data:json_data,
	 	    dataType: "json",
			processData: false,
			contentType: false
		});
		
		request.done(function(data)  // 전송 후, 결과 값 받아오는 부분
		{ 
			if (data != null)
			{
				var jsonObj = JSON.stringify(data);
				var jsonData = JSON.parse(jsonObj);
				
				if (jsonData.rt_code=='0000')
	    		{	
					alert(jsonData.rt_msg);
					//alert('success!!');
					console.info("success!!");	
				}else{
					alert(jsonData.rt_msg+'['+jsonData.rt_code+']');
					return false;
				}
			}
			});
		
		request.fail(function(jqXHR, textStatus) // 에러 발생
		{
			alert("서버 통신 오류 발생!");
		});
		
	});
	
	$(document).on('click','#btn_save',function(){
		var form = new FormData(document.getElementById('mainform'));
		
		 
		if($('#s_id').val()==""){
			alert('사번을 입력해주세요.');	
			$('#s_id').focus();
			return false;
		}
		
		if($('#s_name').val()==""){
			alert('이름을 입력해주세요.');	
			$('#s_name').focus();
			return false;
		}
		
		if($('#s_phone').val()==""){
			alert('발신자 전화번호 입력해주세요.');	
			$('#s_phone').focus();
			return false;
		}
		
		if($('#r_name').val()==""){
			alert('상품 수령자 이름을 입력해주세요.');
			$('#r_name').focus();
			return false;
		}
		
		if($('#r_phone').val()==""){
			alert('상품 수령자 전화번호 입력해주세요.');	
			$('#r_phone').focus();
			return false;
		}
		
		if($('#r_post').val()==""){
			alert('상품 수령자 우편번호를 입력해주세요.');
			$('#r_post').focus();
			return false;
		}
		
		if($('#r_address').val()==""){
			alert('상품 수령자 주소를 입력해주세요.');
			$('#r_address').focus();
			return false;
		}
		 
		 
		if($('r_detailAddress').val()==""){
			alert('상품 수령자 주소를 입력해주세요.');
			$('#r_detailAddress').focus();
			return false;
		}
		
		 
		if (confirm("위 정보로 상품 수령지를 등록 하시겠습니까?")){
			
			var request = $.ajax({
				url: "tsis_save.do",
				type: 'POST',
				data: form,
		 	    dataType: "json",
				processData: false,
				contentType: false
			});
			
			request.done(function(data)  // 전송 후, 결과 값 받아오는 부분
			{ 
	    		if (data != null)
	    		{
	    			var jsonObj = JSON.stringify(data);
	    			var jsonData = JSON.parse(jsonObj);
	    			
	    			if (jsonData.rt_code=='0000')
		    		{	
	    				alert('정상적으로 상품 배송지 등록하였습니다.');
	    				console.info("success!!");
	    				
	    				$('form').each(function() {
	    	                this.reset();
	    	            });
	    				
	    			}else{
	    				alert(jsonData.rt_msg+':'+jsonData.rt_code);
						//alert('상품배송지 등록에 실패하였습니다.\n관리자에게 문의하세요. ['+jsonData.rt_msg+']');
						return false;
					}
	    			
	    		}
	   		});
			
			request.fail(function(jqXHR, textStatus) // 에러 발생
			{
				alert("서버 통신 오류 발생!");
			});
			
		}
	});
</script>
</head>
<body>
<form method="post" id="mainform">
<input type="hidden" id="gubun" name="gubun" value="202009">
  <div>
    <div class="wrap_header">
      <div class="header">
        <img src="img/logo_tsis.png" alt="tsis">
        <h1>명절선물 배송 정보 입력</h1>
      </div>
    </div>
    <div class="wrap_content">
      <div class="content">
        <h2>기본정보</h2>
        <label>사번</label>
        <input type="text" id="s_id" name="s_id" style='width:200px'> <a href="#" id='btn_search'>중복 조회</a>
        <!-- <label>업체명</label>
        <input type="text"> -->
      </div>
      <div class="content">
        <h2>발신인 정보 입력</h2>
        <label>발신인 성함</label>
        <input type="text" id="s_name" name="s_name">
        <label>발신인 전화번호</label>
        <input type="text" id="s_phone" name="s_phone" onKeyup="inputTelNumber(this);" maxlength="13">
      </div>
      <div class="content">
        <h2>수신인 정보 입력</h2>
        <label>수신인 성함</label>
        <input type="text" id="r_name" name="r_name">
        <label>수신인 전화번호</label>
        <input type="text" id="r_phone" name="r_phone" onKeyup="inputTelNumber(this);" maxlength="13">
        <label>상품 수령 주소</label>
        <input readonly type="text" class="postnum" id="r_post" name="r_post" placeholder="우편번호"><a href="javascript:daumPostcode();">주소검색</a>
        <input readonly type="text" id="r_address" name="r_address" placeholder="주소">
        <input type="text" id="r_detailAddress" name="r_detailAddress" placeholder="상세주소" class="mt10">
        <input type="hidden" id="r_extraAddress" name="r_extraAddress" placeholder="참고항목">
      </div>
      <p>기재하신 정보로 선물이 발송되오니, <br>작성 완료 전 한번 더 내용을 확인 해주세요.</p>
      <div class="wrap_btn">
        <button type="button" id="btn_save">입력 완료</button>
      </div>
    </div>
  </div>
</form>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    function inputTelNumber(obj) {

      var number = obj.value.replace(/[^0-9]/g, "");
      var tel = "";

      // 서울 지역번호(02)가 들어오는 경우
      if (number.substring(0, 2).indexOf('02') == 0) {
        if (number.length < 3) {
          return number;
        } else if (number.length < 6) {
          tel += number.substr(0, 2);
          tel += "-";
          tel += number.substr(2);
        } else if (number.length < 10) {
          tel += number.substr(0, 2);
          tel += "-";
          tel += number.substr(2, 3);
          tel += "-";
          tel += number.substr(5);
        } else {
          tel += number.substr(0, 2);
          tel += "-";
          tel += number.substr(2, 4);
          tel += "-";
          tel += number.substr(6);
        }

        // 서울 지역번호(02)가 아닌경우
      } else {
        if (number.length < 4) {
          return number;
        } else if (number.length < 7) {
          tel += number.substr(0, 3);
          tel += "-";
          tel += number.substr(3);
        } else if (number.length < 11) {
          tel += number.substr(0, 3);
          tel += "-";
          tel += number.substr(3, 3);
          tel += "-";
          tel += number.substr(6);
        } else {
          tel += number.substr(0, 3);
          tel += "-";
          tel += number.substr(3, 4);
          tel += "-";
          tel += number.substr(7);
        }
      }

      obj.value = tel;
    }
    
    function daumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById("r_extraAddress").value = extraAddr;
                
                } else {
                    document.getElementById("r_extraAddress").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('r_post').value = data.zonecode;
                document.getElementById("r_address").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("r_detailAddress").focus();
            }
        }).open();
    }
  </script>
</body>
</html>