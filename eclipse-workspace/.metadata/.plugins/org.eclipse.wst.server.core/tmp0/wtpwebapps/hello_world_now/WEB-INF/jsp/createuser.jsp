<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
	integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
	crossorigin="anonymous"></script>
<title>どこつぶ</title>
</head>
<body>
	<div class="jumbotron">
		<div class="panel-heading">
			<h1>ユーザー登録画面</h1>
		</div>
		<div id="errorMessage"></div>
		<form action="/hello_world_now/Createuser" id="form1" method="post" onsubmit="return errorcheck();">
			<div class="form-group">
				<label for="InputEmail">ユーザー名</label> <input type="text"
					class="form-control" name="name" placeholder="ユーザー名を入れてください。"
					required>
			</div>
			<div class="form-group">
				<label for="InputEmail">パスワード</label> <input type="password"
					class="form-control" name="pass1" placeholder="パスワードを入れてください。"
					required>
			</div>
			<div class="form-group">
				<label for="InputEmail">確認用パスワード</label> <input type="password"
					class="form-control" name="pass2" placeholder="パスワードを入れてください。"
					required>
			</div>
			<input class="btn btn-primary" type="submit" value="登録">
		</form>
		<a href="/hello_world_now/">TOPへ</a>
	</div>
</body>
<script type="text/javascript">
	var form = document.forms[0];
	function errorcheck() {
		var pass1 = form.pass1.value;
		var pass2 = form.pass2.value;
		// 比較おかしい？
 		if (pass1 != pass2) {
			document.getElementById("errorMessage").innerHTML = "入力値にエラーがあります";
			// 一致していなかったら、エラーメッセージを表示する、こいつが犯人
/* 			form.pass1.setCustomValidity("パスワードと確認用パスワードが一致しません");
			form.pass2.setCustomValidity("パスワードと確認用パスワードが一致しません"); */
			return false;
		}
		return true;
	}
<%/* var form = document.forms[0];
							form.onsubmit = function() {
								// エラーメッセージをクリアする
								form.pass1.setCustomValidity("");
								form.pass2.setCustomValidity("");
								// パスワードの一致確認
								if (form.pass1.value != form.pass2.value) {
									// 一致していなかったら、エラーメッセージを表示する
									form.pass1.setCustomValidity("パスワードと確認用パスワードが一致しません");
									form.pass2.setCustomValidity("パスワードと確認用パスワードが一致しません");
								}
							};
							// 入力値チェックエラーが発生したときの処理
							form.addEventListener("invalid", function() {
								document.getElementById("errorMessage").innerHTML = "入力値にエラーがあります";
							}, false); */%>
	
</script>
</html>