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

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Dave Syer
 */
public class InMemoryRomanNumeralConvertRepository implements RomanNumeralConvertRepository {

	private static AtomicLong counter = new AtomicLong();

	private final ConcurrentMap<Long, RomanNumeralConvert> messages = new ConcurrentHashMap<>();

	@Override
	public Iterable<RomanNumeralConvert> findAll() {
		return this.messages.values();
	}

	@Override
	public RomanNumeralConvert save(RomanNumeralConvert romanNumeralConvert) {
		Long id = romanNumeralConvert.getId();
		if (id == null) {
			id = counter.incrementAndGet();
			romanNumeralConvert.setId(id);
		}
		romanNumeralConvert.convertToRoman(romanNumeralConvert.getNumberToConvert());
		this.messages.put(id, romanNumeralConvert);
		return romanNumeralConvert;
	}

	@Override
	public RomanNumeralConvert findMessage(Long id) {
		return this.messages.get(id);
	}

	@Override
	public void deleteMessage(Long id) {
		this.messages.remove(id);
	}

}
