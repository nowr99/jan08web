package com.poseidon.util;

import javax.servlet.http.HttpServletRequest;

public class Util {

	// 문자를 포함한 숫자가 들어있을 시 문자를 제거하고 숫자만 출력하는 메소드
	// Detail.java 에 가져다 쓰기
	public static int str2Int(String str) {
		// A59 -> 59
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {
			if (Character.isDigit(str.charAt(i))) {
				sb.append(str.charAt(i));
			}
		}
		int number = 0;
		if (sb.length() > 0) {
			number = Integer.parseInt(sb.toString());
		}
		System.out.println("게시물 번호 " + number);
		return number;
	}

	public static int str2Int2(String str) { // 정규식 쓴 2번째 방법(간단)
		// A59 -> 59
		// 5A9 -> 59

		String numberOnly = str.replaceAll("[^0-9]", "");
		return Integer.parseInt(numberOnly);
	}

	// String 값이 들어오면 int 타입인지 확인해보는 메소드
	// 127 -> true
	// 1A2A3 -> false

	public static boolean intCheck(String str) {
		// try catch를 이용한 방법 (더 효율적)
		try {
			Integer.parseInt(str); // 이게 된다면 true return
			return true;
		} catch (Exception e) {
			return false; // 안된다면 false return
		}
	}

	// 2번째버전
	public static boolean intCheck2(String str) {
		boolean result = true;
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i))) {
				result = false;
				break;
			}
		}
		return result;
	}

	// ip가져오기
	public static String getIP(HttpServletRequest request) {
		String ip = request.getHeader("X-FORWARDED-FOR");

		if (ip == null) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null) {
			ip = request.getRemoteAddr();
		}
		return ip;

	}

	// 24.01.23 HTML 태그를 특수기호로 변경하기 <:&lt, >:&gt
	public static String removeTag(String str) {
		str = str.replace("<", "&lt");
		str = str.replace(">", "&gt");
		return str;
	}

	// 24.01.23 엔터처리
	public static String addBR(String str) {
		return str.replaceAll("(\r\n|\r|\n|\n\r)", "<br>");
	}

	
	// 24.01.23 아이피 중간을 가리고 ♡로 출력되게 하기 172.30.1.99 -> 172.♡.1.99
	public static String ipMasking(String ip) {
		if (ip.indexOf('.') != -1) {
			StringBuffer sb = new StringBuffer(); // 멀티스레드 환경에서도 동기화 지원
			sb.append(ip.substring(0, ip.indexOf('.')+1));
			sb.append("♡");
			sb.append(ip.substring(ip.indexOf('.',ip.indexOf('.')+1)));
			return sb.toString();
		} else {
			return ip;
		}
	}
}
