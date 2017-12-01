/*
 * Copyright 2012-2016 the original author or authors.
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

package sample.web.ui.mvc;

import javax.validation.Valid;

import sample.web.ui.RomanNumeralConvert;
import sample.web.ui.RomanNumeralConvertRepository;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author Rob Winch
 * @author Doo-Hwan Kwak
 */
@Controller
@RequestMapping("/")
public class MessageController {

	private final RomanNumeralConvertRepository romanNumeralConvertRepository;

	public MessageController(RomanNumeralConvertRepository romanNumeralConvertRepository) {
		this.romanNumeralConvertRepository = romanNumeralConvertRepository;
	}

	@GetMapping
	public ModelAndView list() {
		Iterable<RomanNumeralConvert> messages = this.romanNumeralConvertRepository.findAll();
		return new ModelAndView("messages/list", "messages", messages);
	}

	@GetMapping("{id}")
	public ModelAndView view(@PathVariable("id") RomanNumeralConvert romanNumeralConvert) {
		return new ModelAndView("messages/view", "romanNumeralConvert", romanNumeralConvert);
	}

	@GetMapping(params = "form")
	public String createForm(@ModelAttribute RomanNumeralConvert romanNumeralConvert) {
		return "messages/form";
	}

	@PostMapping
	public ModelAndView create(@Valid RomanNumeralConvert romanNumeralConvert, BindingResult result,
							   RedirectAttributes redirect) {
		if (result.hasErrors()) {
			return new ModelAndView("messages/form", "formErrors", result.getAllErrors());
		}
		romanNumeralConvert = this.romanNumeralConvertRepository.save(romanNumeralConvert);
		redirect.addFlashAttribute("globalMessage", "Successfully created a new romanNumeralConvert");
		return new ModelAndView("redirect:/{romanNumeralConvert.id}", "romanNumeralConvert.id", romanNumeralConvert.getId());
	}

	@RequestMapping("foo")
	public String foo() {
		throw new RuntimeException("Expected exception in controller");
	}

	@GetMapping(value = "delete/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {
		this.romanNumeralConvertRepository.deleteMessage(id);
		Iterable<RomanNumeralConvert> messages = this.romanNumeralConvertRepository.findAll();
		return new ModelAndView("messages/list", "messages", messages);
	}

	@GetMapping(value = "modify/{id}")
	public ModelAndView modifyForm(@PathVariable("id") RomanNumeralConvert romanNumeralConvert) {
		return new ModelAndView("messages/form", "romanNumeralConvert", romanNumeralConvert);
	}

}
