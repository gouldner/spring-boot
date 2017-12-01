/*
 * Copyright 2012-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sample.web.ui;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Calendar;

import javax.validation.constraints.NotEmpty;

/**
 * @author Rob Winch
 */
public class RomanNumeralConvert {

	static  Pair[] romans = {
			new Pair(new Integer(1000),"M"),
			new Pair(new Integer(900),"CM"),
			new Pair(new Integer(500),"D"),
			new Pair(new Integer(400),"CD"),
			new Pair(new Integer(100),"C"),
			new Pair(new Integer(90),"XC"),
			new Pair(new Integer(50),"L"),
			new Pair(new Integer(40),"XL"),
			new Pair(new Integer(10),"X"),
			new Pair(new Integer(9),"IX"),
			new Pair(new Integer(5),"V"),
			new Pair(new Integer(4),"IV"),
			new Pair(new Integer(1),"I"),
	};

	private Long id;

	@NotEmpty(message = "Number to convert is required.")
	private String numberToConvert;

	private String romanNumeral;

	private Calendar created = Calendar.getInstance();

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Calendar getCreated() {
		return this.created;
	}

	public void setCreated(Calendar created) {
		this.created = created;
	}

	public String getNumberToConvert() {
		return numberToConvert;
	}

	public void setNumberToConvert(String numberToConvert) {
		this.numberToConvert = numberToConvert;
	}

	public String getRomanNumeral() {
		return romanNumeral;
	}

	public void setRomanNumeral(String romanNumeral) {
		this.romanNumeral = romanNumeral;
	}

	public void convertToRoman(String numberToConvertStr) {
		String romanNumeral = new String("");
		try {
            Integer intToConvert = Integer.parseInt(numberToConvertStr);
            if (intToConvert <= 0) {
				romanNumeral += intToConvert + " has no Roman Conversion.";
			} else if (intToConvert > 10000){
				romanNumeral += "Too large to convert.";
			} else {
				for (Pair roman : romans) {
					long checkVal = (long) intToConvert / (Integer) roman.getKey();
					if (checkVal != 0) {
						for (int i = 0; i < checkVal; i++) {
							romanNumeral += roman.getValue();
							intToConvert -= (Integer) roman.getKey();
						}
					}
				}
			}
        } catch (NumberFormatException exp) {
			romanNumeral += "Can't convert.";
		}
		this.romanNumeral = romanNumeral;
	}
}
