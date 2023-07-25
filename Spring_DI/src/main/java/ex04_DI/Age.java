package ex04_DI;

import java.util.Calendar;

public class Age {
	public int count(String jumin, int gender) {
		int result = 0;
		// gender 1 또는 2는 1900년대생
		// gender 3 또는 4는 2000년대생

		// 현재 연도 - 태어난 연도 = 나이
		int cYear = Calendar.getInstance().get(Calendar.YEAR);
		int bYear = 0;
		if (gender == 1 || gender == 2) {
			bYear = 1900 + Integer.parseInt(jumin.substring(0, 2));
		} else if (gender == 3 || gender == 4) {
			bYear = 2000 + Integer.parseInt(jumin.substring(0, 2));
		}
		result = cYear - bYear;
		return result;
	}
}
