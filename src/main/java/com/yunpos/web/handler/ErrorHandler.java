package com.yunpos.web.handler;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.yunpos.exception.ServiceException;
import com.yunpos.utils.jqgrid.ResponseError;


/**
 * 
 * 功能描述：springMVC统一错误处理器，错误信息以json格式返回
 * <p>
 * 版权所有：小牛信息科技有限公司
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * 
 * @author Devin_Yang 新增日期：2015年8月4日
 * @author Devin_Yang 修改日期：2015年8月4日
 *
 */

@ControllerAdvice
public class ErrorHandler {
	private final Logger logger = LoggerFactory.getLogger(ErrorHandler.class);
	
	@Autowired
	private MessageSource messageSource;
	
	/**
	 * BindException 异常处理器
	 * @param ex
	 * @param locale
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(BindException.class)
	public ResponseError handle(BindException ex, Locale locale) {

		BindingResult bindingResult = ex.getBindingResult();
		ResponseError ajaxError = new ResponseError();
		ajaxError.setValidationErrors(fetchValidationErrorsAsStringArray(bindingResult, locale));
		ajaxError.setLocalErrorMessage(fetchErrorMessage("error_validation_error_message", locale));
		return ajaxError;
	}
	
	/**
	 * 用户自定义异常ServiceException处理器
	 * @param ex
	 * @param locale
	 * @return
	 */

	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ServiceException.class)
	public ResponseError handle(ServiceException ex, Locale locale) {
		
		logger.error(null, ex);
		ex.printStackTrace();
		ResponseError ajaxError = new ResponseError();
		ajaxError.setLocalErrorMessage(fetchErrorMessage(ex.getErrorCode().toString(), locale));
		return ajaxError;
	}
	
	/**
	 * Throwable 异常处理器
	 * @param ex
	 * @return
	 */

	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(Throwable.class)
	public ResponseError handle(Throwable ex) {

		logger.error(null, ex);
		ResponseError ajaxError = new ResponseError();
		ajaxError.setGlobalErrorCode("error_unexpected");
		return ajaxError;
	}

	private String[] fetchValidationErrorsAsStringArray(BindingResult bindingResult, Locale locale) {

		String[] errorArray = null;

		if (bindingResult.hasErrors()) {
			List<FieldError> errors = bindingResult.getFieldErrors();
			errorArray = new String[errors.size() * 2];
			int counter = 0;

			for (FieldError error : errors) {
				errorArray[counter] = error.getField();
				errorArray[counter + 1] = messageSource.getMessage(error, locale);
				counter += 2;
			}
		}
		return errorArray;
	}

	private String fetchErrorMessage(String errorCode, Locale locale) {
		return messageSource.getMessage(errorCode, null, "Unexpected error.", locale);
	}
}
