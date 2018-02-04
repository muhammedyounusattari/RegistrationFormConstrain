package younus.attari;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@SpringBootApplication
@Component("younus.attari")

@EnableWebMvc
@Configuration
public class MainSpringClass {

	public static void main(String arg[]) {
		SpringApplication.run(MainSpringClass.class, arg);
	}

	@Bean
	public ViewResolver getViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
}

@Controller
class ControllerClass {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getRegistrationForm(Model model) {
		model.addAttribute("register", new Register());// this is used to bind
		return "/register";
	}

	@RequestMapping(value = "/home", method = RequestMethod.POST)
	public String getRegistered(@ModelAttribute(value = "register") @Valid Register register,
			BindingResult result, Model mode) {

		if (result.hasErrors()) {
			return "/register";
		}
		
		return "redirect:/sucess";
		
	}
}

class Register {

	@NotBlank(message = "Please enter firstname")
	@NotNull(message = "Please enter firstname")
	private String fname;

	@NotBlank(message = "Please enter firstname")
	@NotNull(message = "Please enter lastname")
	private String lname;

	@NotBlank(message = "Please enter firstname")
	@NotNull(message = "Please enter middlename")
	private String mname;

	@ValidateDateOfBirth(message = "You should be greater than 18 years")
	private String dateOfBirth;

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
}

@Retention(RetentionPolicy.RUNTIME)
@Target(value = { ElementType.FIELD })
@Constraint(validatedBy = ValidateDOBConstrain.class)
@interface ValidateDateOfBirth {
	String message() default "Your dateofbirth should be pastdate";

	Class<?>[] groups() default {};

	String value() default "";

	Class<? extends Payload>[] payload() default {};
}

class ValidateDOBConstrain implements ConstraintValidator<ValidateDateOfBirth, String> {

	String value = "", message = "";

	public void initialize(ValidateDateOfBirth constraintAnnotation) {
		this.value = constraintAnnotation.value();
		this.message = constraintAnnotation.message();
	}

	public boolean isValid(String value, ConstraintValidatorContext context) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date userDate = sdf.parse(value);
			Date currentDate = new Date(System.currentTimeMillis());
			currentDate.setYear(userDate.getYear() - 18);
			return userDate.compareTo(currentDate) > 0 ? false : true;
		} catch (ParseException e) {
			// e.printStackTrace();
		}
		return false;
	}

}