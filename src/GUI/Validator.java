package GUI;

public class Validator {

	public static boolean validation(String type, String value) {
		if(value == null)
			return false;
		else if(type.equals("Double")) {
			try {
		        double num = Double.parseDouble(value);
		    } catch (NumberFormatException nfe) {
		        return false;
		    }
		    return true;
		}
		else if(type.equals("Integer")) {
			try {
		        int num = Integer.parseInt(value);
		    } catch (NumberFormatException nfe) {
		        return false;
		    }
		    return true;
		}
		
		else
			return true;
		
	}
}
