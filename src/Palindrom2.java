import java.util.Stack;

public class Palindrom2 {
	
	public static void main(String[] args) {
		
		breakPalindrome("acca");
		
		String[] result = new String[1];
		result[0]="{}[]";
		//braces(result);

	}
	
	private static String breakPalindrome(String s) {
		 int length = s.length();
		 
		 char[] stringArray = s.toCharArray();
		 char[] resultArray = new char[length];

		 for(int i=0 ,j=length-1; i<=j-1;i++,j--) {
			 
			 if(j-i ==2) {
				 if(stringArray[i+1]=='a') {
					 if(stringArray[i]=='a' && stringArray[j]=='a') {
						 return"IMPOSSIBLE";
					 }
					 resultArray[i]='a';
					 resultArray[i+1]='a';
					 resultArray[j]=stringArray[j];
					 break;
				 }
			 }
			 if(j-i ==1) {
				 if(stringArray[i]=='a' && stringArray[j]=='a') {
					 return"IMPOSSIBLE";
				 }
					 resultArray[i]=stringArray[i];
					 resultArray[j]='a';
					 break;
				 }
			 resultArray[i]='a';
			 resultArray[j]='a';
		 }
		 
		return String.valueOf(resultArray);

	}
	

	public static String breakPalindromeRecursivaly(String s) throws Exception {
		 int length = s.length();
	        if(length == 0) {
	            return "";
	        }
	        if(s.length() ==1) {
	            return s;
	        }
	        if(s.length()==2) {
	        	if((s.equalsIgnoreCase("aa"))) {
	        		throw new Exception();
	        	}
	            return "a"+breakPalindrome( s.substring(1, length));
	        }
	        if(s.length()==3 ) {
	        	if((s.equalsIgnoreCase("aaa"))) {
	        		throw new Exception();
	        	}
	            return "a"+breakPalindrome( s.substring(1, length-1))+"b";
	        }
	        else {
	            return "a"+breakPalindrome( s.substring(1, length-1))+"a";

	        }
	    }
	
	
	static String[] braces(String[] values) {
		String[] result = new String[values.length];
		Stack<Character> stack = new Stack<>();
		char lastBrace = 0;
		for (int i = 0; i < values.length; i++) {
			stack.clear();
			String string = values[i];
			for (int j = 0; j < string.length(); j++) {
				char brace = string.charAt(j);
				if (stack.isEmpty()) {
					stack.push(brace);
				} else {
					lastBrace = stack.pop();
					if (!isMatch(lastBrace,brace)) {
						stack.push(lastBrace);
						stack.push(brace);
					} 
				}
			}
			
			if(stack.isEmpty()) {
				result[i] = "YES";
			}
			else {
				result[i] = "NO";
			}
		}
		 
		 return result;

	    }

	private static boolean isMatch(char lastBrace, char brace) {
		if(lastBrace=='{' &&  brace=='}') {
			return true;
		}
		if(lastBrace=='[' &&  brace==']') {
			return true;
		}
		if(lastBrace=='(' &&  brace==')') {
			return true;
		}
		return false;
	}
	

}
