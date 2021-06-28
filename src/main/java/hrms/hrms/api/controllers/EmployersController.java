package hrms.hrms.api.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import hrms.hrms.business.abstracts.EmployersService;
import hrms.hrms.core.utilies.result.DataResult;
import hrms.hrms.core.utilies.result.ErrorDataResult;
import hrms.hrms.entities.concretes.Employers;

@RestController
@RequestMapping("/api/employers")
public class EmployersController {
	
	private EmployersService employersService;

	@Autowired
	public EmployersController(EmployersService employersService) {
		super();
		this.employersService = employersService;
	}
	
	@GetMapping("/getall")
	public DataResult<List<Employers>> getAll(){
		return this.employersService.getAll();
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> add(@Valid @RequestBody Employers employers) {
		return ResponseEntity.ok(this.employersService.add(employers));
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException exception){
		
		Map<String, String> validationErrors = new HashMap<String, String>();
		
		for(FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
			validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		
		ErrorDataResult<Object> errors = new ErrorDataResult<Object>(validationErrors,"Doğrulama hataları");
		
		return errors;
		
	}
	
	@GetMapping("/getByEmail")
	public DataResult<Employers> getByEmail(String email){
		return this.employersService.getByEmail(email);		
	}
	
	@GetMapping("/getByWebAddress")
	public DataResult<Employers> getByWebAddress(String webAddress) {
		return this.employersService.getByWebAddress(webAddress);
	}
}
