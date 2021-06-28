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

import hrms.hrms.business.abstracts.EmployeesService;
import hrms.hrms.core.utilies.result.DataResult;
import hrms.hrms.core.utilies.result.ErrorDataResult;
import hrms.hrms.entities.concretes.Employees;

@RestController
@RequestMapping("/api/employees")
public class EmployeesController {
	private EmployeesService employeesService;

	@Autowired
	public EmployeesController(EmployeesService employeesService) {
		super();
		this.employeesService = employeesService;
	}
	
	@GetMapping("/getall")
	public DataResult<List<Employees>> getAll(){
		return this.employeesService.getAll();
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> add(@Valid @RequestBody Employees employees) {
		return ResponseEntity.ok(this.employeesService.add(employees));
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
	public DataResult<Employees> getByEmail(String email){
		return this.employeesService.getByEmail(email);		
	}
}
